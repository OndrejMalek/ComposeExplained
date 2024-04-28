package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public class CancellableContinuationImpl extends DispatchedTask implements CancellableContinuation, CoroutineStackFrame, Waiter {
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;
    public final CoroutineContext context;
    public final Continuation delegate;
    public static final AtomicIntegerFieldUpdater _decisionAndIndex$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decisionAndIndex");
    public static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
    public static final AtomicReferenceFieldUpdater _parentHandle$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_parentHandle");

    public CancellableContinuationImpl(int i, Continuation continuation) {
        super(i);
        this.delegate = continuation;
        this.context = continuation.getContext();
        this._decisionAndIndex = 536870911;
        this._state = Active.INSTANCE;
    }

    public static void multipleHandlersError(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    public static Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1 function1) {
        if ((obj instanceof CompletedExceptionally) || !ResultKt.isCancellableMode(i)) {
            return obj;
        }
        if (function1 != null || (notCompleted instanceof CancelHandler)) {
            return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, (CancellationException) null, 16);
        }
        return obj;
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            InvokeOnCancel invokeOnCancel = (InvokeOnCancel) cancelHandler;
            int i = invokeOnCancel.$r8$classId;
            Object obj = invokeOnCancel.handler;
            switch (i) {
                case 0:
                    ((Function1) obj).invoke(th);
                    break;
                default:
                    ((DisposableHandle) obj).dispose();
                    break;
            }
        } catch (Throwable th2) {
            ResultKt.handleCoroutineException(this.context, new RuntimeException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void callOnCancellation(Function1 function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            ResultKt.handleCoroutineException(this.context, new RuntimeException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public final void callSegmentOnCancellation(Segment segment, Throwable th) {
        CoroutineContext coroutineContext = this.context;
        int i = _decisionAndIndex$FU.get(this) & 536870911;
        if (i == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken".toString());
        }
        try {
            segment.onCancellation(i, coroutineContext);
        } catch (Throwable th2) {
            ResultKt.handleCoroutineException(coroutineContext, new RuntimeException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final boolean cancel(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            CancelledContinuation cancelledContinuation = new CancelledContinuation(this, th, (obj instanceof CancelHandler) || (obj instanceof Segment));
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, cancelledContinuation)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            NotCompleted notCompleted = (NotCompleted) obj;
            if (notCompleted instanceof CancelHandler) {
                callCancelHandler((CancelHandler) obj, th);
            } else if (notCompleted instanceof Segment) {
                callSegmentOnCancellation((Segment) obj, th);
            }
            if (!isReusable()) {
                detachChild$kotlinx_coroutines_core();
            }
            dispatchResume(this.resumeMode);
            return true;
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final void cancelCompletedResult$kotlinx_coroutines_core(Object obj, CancellationException cancellationException) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj2 instanceof CompletedExceptionally) {
                return;
            }
            if (!(obj2 instanceof CompletedContinuation)) {
                CompletedContinuation completedContinuation = new CompletedContinuation(obj2, (CancelHandler) null, (Function1) null, cancellationException, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, completedContinuation)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                return;
            }
            CompletedContinuation completedContinuation2 = (CompletedContinuation) obj2;
            if (!(!(completedContinuation2.cancelCause != null))) {
                throw new IllegalStateException("Must be called at most once".toString());
            }
            CompletedContinuation copy$default = CompletedContinuation.copy$default(completedContinuation2, null, cancellationException, 15);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, copy$default)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            CancelHandler cancelHandler = completedContinuation2.cancelHandler;
            if (cancelHandler != null) {
                callCancelHandler(cancelHandler, cancellationException);
            }
            Function1 function1 = completedContinuation2.onCancellation;
            if (function1 != null) {
                callOnCancellation(function1, cancellationException);
                return;
            }
            return;
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final void completeResume(Object obj) {
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _parentHandle$FU;
        DisposableHandle disposableHandle = (DisposableHandle) atomicReferenceFieldUpdater.get(this);
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        atomicReferenceFieldUpdater.set(this, NonDisposableHandle.INSTANCE);
    }

    public final void dispatchResume(int i) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = _decisionAndIndex$FU;
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 != 1) {
                    throw new IllegalStateException("Already resumed".toString());
                }
                boolean z = i == 4;
                Continuation continuation = this.delegate;
                if (z || !(continuation instanceof DispatchedContinuation) || ResultKt.isCancellableMode(i) != ResultKt.isCancellableMode(this.resumeMode)) {
                    ResultKt.resume(this, continuation, z);
                    return;
                }
                CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) continuation).dispatcher;
                CoroutineContext context = continuation.getContext();
                if (coroutineDispatcher.isDispatchNeeded()) {
                    coroutineDispatcher.dispatch(context, this);
                    return;
                }
                EventLoopImplPlatform eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core();
                if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
                    eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
                    return;
                }
                eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
                try {
                    ResultKt.resume(this, continuation, true);
                    do {
                    } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
                } finally {
                    try {
                        return;
                    } finally {
                    }
                }
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 1073741824 + (536870911 & i2)));
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    public Throwable getContinuationCancellationCause(JobSupport jobSupport) {
        return jobSupport.getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(obj);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            return exceptionalResult$kotlinx_coroutines_core;
        }
        return null;
    }

    public final Object getResult() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i;
        boolean isReusable = isReusable();
        do {
            atomicIntegerFieldUpdater = _decisionAndIndex$FU;
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 != 2) {
                    throw new IllegalStateException("Already suspended".toString());
                }
                if (isReusable) {
                    releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                }
                Object obj = _state$FU.get(this);
                if (obj instanceof CompletedExceptionally) {
                    throw ((CompletedExceptionally) obj).cause;
                }
                if (ResultKt.isCancellableMode(this.resumeMode)) {
                    Job job = (Job) this.context.get(Job.Key.$$INSTANCE);
                    if (job != null && !job.isActive()) {
                        CancellationException cancellationException = ((JobSupport) job).getCancellationException();
                        cancelCompletedResult$kotlinx_coroutines_core(obj, cancellationException);
                        throw cancellationException;
                    }
                }
                return getSuccessfulResult$kotlinx_coroutines_core(obj);
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, 536870912 + (536870911 & i)));
        if (((DisposableHandle) _parentHandle$FU.get(this)) == null) {
            installParentHandle();
        }
        if (isReusable) {
            releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj instanceof CompletedContinuation ? ((CompletedContinuation) obj).result : obj;
    }

    public final void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && (!(_state$FU.get(this) instanceof NotCompleted))) {
            installParentHandle.dispose();
            _parentHandle$FU.set(this, NonDisposableHandle.INSTANCE);
        }
    }

    public final DisposableHandle installParentHandle() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Job job = (Job) this.context.get(Job.Key.$$INSTANCE);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default = ResultKt.invokeOnCompletion$default(job, true, new ChildContinuation(this), 2);
        do {
            atomicReferenceFieldUpdater = _parentHandle$FU;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, invokeOnCompletion$default)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return invokeOnCompletion$default;
    }

    @Override // kotlinx.coroutines.Waiter
    public final void invokeOnCancellation(Segment segment, int i) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = _decisionAndIndex$FU;
            i2 = atomicIntegerFieldUpdater.get(this);
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once".toString());
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, ((i2 >> 29) << 29) + i));
        invokeOnCancellationImpl(segment);
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b8, code lost:
    
        multipleHandlersError(r10, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00bb, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invokeOnCancellationImpl(java.lang.Object r10) {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.CancellableContinuationImpl._state$FU
            java.lang.Object r7 = r0.get(r9)
            boolean r1 = r7 instanceof kotlinx.coroutines.Active
            if (r1 == 0) goto L18
        La:
            boolean r1 = r0.compareAndSet(r9, r7, r10)
            if (r1 == 0) goto L11
            return
        L11:
            java.lang.Object r1 = r0.get(r9)
            if (r1 == r7) goto La
            goto L0
        L18:
            boolean r1 = r7 instanceof kotlinx.coroutines.CancelHandler
            r2 = 0
            if (r1 != 0) goto Lb8
            boolean r1 = r7 instanceof kotlinx.coroutines.internal.Segment
            if (r1 != 0) goto Lb8
            boolean r1 = r7 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r1 == 0) goto L5a
            r0 = r7
            kotlinx.coroutines.CompletedExceptionally r0 = (kotlinx.coroutines.CompletedExceptionally) r0
            r0.getClass()
            r3 = 1
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = kotlinx.coroutines.CompletedExceptionally._handled$FU
            r5 = 0
            boolean r3 = r4.compareAndSet(r0, r5, r3)
            if (r3 == 0) goto L56
            boolean r3 = r7 instanceof kotlinx.coroutines.CancelledContinuation
            if (r3 == 0) goto L55
            if (r1 == 0) goto L3c
            goto L3d
        L3c:
            r0 = r2
        L3d:
            if (r0 == 0) goto L41
            java.lang.Throwable r2 = r0.cause
        L41:
            boolean r0 = r10 instanceof kotlinx.coroutines.CancelHandler
            if (r0 == 0) goto L4b
            kotlinx.coroutines.CancelHandler r10 = (kotlinx.coroutines.CancelHandler) r10
            r9.callCancelHandler(r10, r2)
            goto L55
        L4b:
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>"
            kotlin.ResultKt.checkNotNull(r10, r0)
            kotlinx.coroutines.internal.Segment r10 = (kotlinx.coroutines.internal.Segment) r10
            r9.callSegmentOnCancellation(r10, r2)
        L55:
            return
        L56:
            multipleHandlersError(r10, r7)
            throw r2
        L5a:
            boolean r1 = r7 instanceof kotlinx.coroutines.CompletedContinuation
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler"
            if (r1 == 0) goto L93
            r1 = r7
            kotlinx.coroutines.CompletedContinuation r1 = (kotlinx.coroutines.CompletedContinuation) r1
            kotlinx.coroutines.CancelHandler r4 = r1.cancelHandler
            if (r4 != 0) goto L8f
            boolean r4 = r10 instanceof kotlinx.coroutines.internal.Segment
            if (r4 == 0) goto L6c
            return
        L6c:
            kotlin.ResultKt.checkNotNull(r10, r3)
            r3 = r10
            kotlinx.coroutines.CancelHandler r3 = (kotlinx.coroutines.CancelHandler) r3
            java.lang.Throwable r4 = r1.cancelCause
            if (r4 == 0) goto L7a
            r9.callCancelHandler(r3, r4)
            return
        L7a:
            r4 = 29
            kotlinx.coroutines.CompletedContinuation r1 = kotlinx.coroutines.CompletedContinuation.copy$default(r1, r3, r2, r4)
        L80:
            boolean r2 = r0.compareAndSet(r9, r7, r1)
            if (r2 == 0) goto L87
            return
        L87:
            java.lang.Object r2 = r0.get(r9)
            if (r2 == r7) goto L80
            goto L0
        L8f:
            multipleHandlersError(r10, r7)
            throw r2
        L93:
            boolean r1 = r10 instanceof kotlinx.coroutines.internal.Segment
            if (r1 == 0) goto L98
            return
        L98:
            kotlin.ResultKt.checkNotNull(r10, r3)
            r3 = r10
            kotlinx.coroutines.CancelHandler r3 = (kotlinx.coroutines.CancelHandler) r3
            kotlinx.coroutines.CompletedContinuation r8 = new kotlinx.coroutines.CompletedContinuation
            r4 = 0
            r5 = 0
            r6 = 28
            r1 = r8
            r2 = r7
            r1.<init>(r2, r3, r4, r5, r6)
        La9:
            boolean r1 = r0.compareAndSet(r9, r7, r8)
            if (r1 == 0) goto Lb0
            return
        Lb0:
            java.lang.Object r1 = r0.get(r9)
            if (r1 == r7) goto La9
            goto L0
        Lb8:
            multipleHandlersError(r10, r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CancellableContinuationImpl.invokeOnCancellationImpl(java.lang.Object):void");
    }

    public final boolean isReusable() {
        if (this.resumeMode == 2) {
            Continuation continuation = this.delegate;
            ResultKt.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (DispatchedContinuation._reusableCancellableContinuation$FU.get((DispatchedContinuation) continuation) != null) {
                return true;
            }
        }
        return false;
    }

    public String nameString() {
        return "CancellableContinuation";
    }

    public final void releaseClaimedReusableContinuation$kotlinx_coroutines_core() {
        Continuation continuation = this.delegate;
        Throwable th = null;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation == null) {
            return;
        }
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = DispatchedContinuation._reusableCancellableContinuation$FU;
            Object obj = atomicReferenceFieldUpdater.get(dispatchedContinuation);
            Symbol symbol = AtomicKt.REUSABLE_CLAIMED;
            if (obj != symbol) {
                if (!(obj instanceof Throwable)) {
                    throw new IllegalStateException(("Inconsistent state " + obj).toString());
                }
                while (!atomicReferenceFieldUpdater.compareAndSet(dispatchedContinuation, obj, null)) {
                    if (atomicReferenceFieldUpdater.get(dispatchedContinuation) != obj) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                }
                th = (Throwable) obj;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(dispatchedContinuation, symbol, this)) {
                if (atomicReferenceFieldUpdater.get(dispatchedContinuation) != symbol) {
                    break;
                }
            }
        }
        if (th == null) {
            return;
        }
        detachChild$kotlinx_coroutines_core();
        cancel(th);
    }

    public final void resume(Object obj, Function1 function1) {
        resumeImpl(obj, this.resumeMode, function1);
    }

    public final void resumeImpl(Object obj, int i, Function1 function1) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof NotCompleted) {
                Object resumedState = resumedState((NotCompleted) obj2, obj, i, function1);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, resumedState)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                if (!isReusable()) {
                    detachChild$kotlinx_coroutines_core();
                }
                dispatchResume(i);
                return;
            }
            if (obj2 instanceof CancelledContinuation) {
                CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                cancelledContinuation.getClass();
                if (CancelledContinuation._resumed$FU.compareAndSet(cancelledContinuation, 0, 1)) {
                    if (function1 != null) {
                        callOnCancellation(function1, cancelledContinuation.cause);
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final void resumeUndispatched(CoroutineDispatcher coroutineDispatcher) {
        Unit unit = Unit.INSTANCE;
        Continuation continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl(unit, (dispatchedContinuation != null ? dispatchedContinuation.dispatcher : null) == coroutineDispatcher ? 4 : this.resumeMode, null);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Throwable m292exceptionOrNullimpl = Result.m292exceptionOrNullimpl(obj);
        if (m292exceptionOrNullimpl != null) {
            obj = new CompletedExceptionally(m292exceptionOrNullimpl, false);
        }
        resumeImpl(obj, this.resumeMode, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object takeState$kotlinx_coroutines_core() {
        return _state$FU.get(this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nameString());
        sb.append('(');
        sb.append(_BOUNDARY.toDebugString(this.delegate));
        sb.append("){");
        Object obj = _state$FU.get(this);
        sb.append(obj instanceof NotCompleted ? "Active" : obj instanceof CancelledContinuation ? "Cancelled" : "Completed");
        sb.append("}@");
        sb.append(_BOUNDARY.getHexAddress(this));
        return sb.toString();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public final Symbol tryResume(Object obj, Function1 function1) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            boolean z = obj2 instanceof NotCompleted;
            Symbol symbol = JobKt.RESUME_TOKEN;
            if (!z) {
                boolean z2 = obj2 instanceof CompletedContinuation;
                return null;
            }
            Object resumedState = resumedState((NotCompleted) obj2, obj, this.resumeMode, function1);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, resumedState)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            if (isReusable()) {
                return symbol;
            }
            detachChild$kotlinx_coroutines_core();
            return symbol;
        }
    }

    public final void invokeOnCancellation(Function1 function1) {
        invokeOnCancellationImpl(function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(0, function1));
    }
}
