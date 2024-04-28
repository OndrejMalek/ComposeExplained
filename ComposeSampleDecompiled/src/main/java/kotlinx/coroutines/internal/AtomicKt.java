package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.EventLoopImplPlatform;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.ThreadContextElement;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

/* loaded from: classes.dex */
public abstract class AtomicKt {
    public static final Symbol NO_DECISION = new Symbol(0, "NO_DECISION");
    public static final Symbol CLOSED = new Symbol(0, "CLOSED");
    public static final Symbol UNDEFINED = new Symbol(0, "UNDEFINED");
    public static final Symbol REUSABLE_CLAIMED = new Symbol(0, "REUSABLE_CLAIMED");
    public static final Symbol CONDITION_FALSE = new Symbol(0, "CONDITION_FALSE");
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol(0, "NO_THREAD_ELEMENTS");

    public static final Object findSegmentInternal(Segment segment, long j, Function2 function2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        while (true) {
            if (segment.id >= j && !segment.isRemoved()) {
                return segment;
            }
            Object obj = ConcurrentLinkedListNode._next$FU.get(segment);
            Symbol symbol = CLOSED;
            if (obj == symbol) {
                return symbol;
            }
            Segment segment2 = (Segment) ((ConcurrentLinkedListNode) obj);
            if (segment2 == null) {
                segment2 = (Segment) function2.invoke(Long.valueOf(segment.id + 1), segment);
                do {
                    atomicReferenceFieldUpdater = ConcurrentLinkedListNode._next$FU;
                    if (atomicReferenceFieldUpdater.compareAndSet(segment, null, segment2)) {
                        if (segment.isRemoved()) {
                            segment.remove();
                        }
                    }
                } while (atomicReferenceFieldUpdater.get(segment) == null);
            }
            segment = segment2;
        }
    }

    public static final void restoreThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj == NO_THREAD_ELEMENTS) {
            return;
        }
        if (!(obj instanceof ThreadState)) {
            Object fold = coroutineContext.fold(null, ThreadContextKt$findOne$1.INSTANCE);
            ResultKt.checkNotNull(fold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            DurationKt$$ExternalSyntheticCheckNotZero0.m(fold);
            throw null;
        }
        ThreadState threadState = (ThreadState) obj;
        ThreadContextElement[] threadContextElementArr = threadState.elements;
        int length = threadContextElementArr.length - 1;
        if (length < 0) {
            return;
        }
        ThreadContextElement threadContextElement = threadContextElementArr[length];
        ResultKt.checkNotNull(null);
        Object obj2 = threadState.values[length];
        throw null;
    }

    public static final void resumeCancellableWith(Continuation continuation, Object obj, Function1 function1) {
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation.resumeWith(obj);
            return;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        Throwable m292exceptionOrNullimpl = Result.m292exceptionOrNullimpl(obj);
        Object completedWithCancellation = m292exceptionOrNullimpl == null ? function1 != null ? new CompletedWithCancellation(obj, function1) : obj : new CompletedExceptionally(m292exceptionOrNullimpl, false);
        Continuation continuation2 = dispatchedContinuation.continuation;
        continuation2.getContext();
        CoroutineDispatcher coroutineDispatcher = dispatchedContinuation.dispatcher;
        if (coroutineDispatcher.isDispatchNeeded()) {
            dispatchedContinuation._state = completedWithCancellation;
            dispatchedContinuation.resumeMode = 1;
            coroutineDispatcher.dispatch(continuation2.getContext(), dispatchedContinuation);
            return;
        }
        EventLoopImplPlatform eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            dispatchedContinuation._state = completedWithCancellation;
            dispatchedContinuation.resumeMode = 1;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedContinuation);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            Job job = (Job) continuation2.getContext().get(Job.Key.$$INSTANCE);
            if (job == null || job.isActive()) {
                Object obj2 = dispatchedContinuation.countOrElement;
                CoroutineContext context = continuation2.getContext();
                Object updateThreadContext = updateThreadContext(context, obj2);
                UndispatchedCoroutine updateUndispatchedCompletion = updateThreadContext != NO_THREAD_ELEMENTS ? ResultKt.updateUndispatchedCompletion(continuation2, context, updateThreadContext) : null;
                try {
                    continuation2.resumeWith(obj);
                } finally {
                    if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                        restoreThreadContext(context, updateThreadContext);
                    }
                }
            } else {
                CancellationException cancellationException = ((JobSupport) job).getCancellationException();
                dispatchedContinuation.cancelCompletedResult$kotlinx_coroutines_core(completedWithCancellation, cancellationException);
                dispatchedContinuation.resumeWith(ResultKt.createFailure(cancellationException));
            }
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }

    public static void startCoroutineCancellable$default(Function2 function2, AbstractCoroutine abstractCoroutine, AbstractCoroutine abstractCoroutine2) {
        try {
            resumeCancellableWith(ResultKt.intercepted(ResultKt.createCoroutineUnintercepted(abstractCoroutine, abstractCoroutine2, function2)), Unit.INSTANCE, null);
        } catch (Throwable th) {
            abstractCoroutine2.resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }

    public static final Object startUndispatchedOrReturn(ScopeCoroutine scopeCoroutine, ScopeCoroutine scopeCoroutine2, Function2 function2) {
        Object completedExceptionally;
        Object makeCompletingOnce$kotlinx_coroutines_core;
        try {
            ResultKt.beforeCheckcastToFunctionOfArity(2, function2);
            completedExceptionally = function2.invoke(scopeCoroutine2, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (completedExceptionally == coroutineSingletons || (makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally)) == JobKt.COMPLETING_WAITING_CHILDREN) {
            return coroutineSingletons;
        }
        if (makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core).cause;
        }
        return JobKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
    }

    public static final Object threadContextElements(CoroutineContext coroutineContext) {
        Object fold = coroutineContext.fold(0, ThreadContextKt$findOne$1.INSTANCE$1);
        ResultKt.checkNotNull(fold);
        return fold;
    }

    public static final Object updateThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = threadContextElements(coroutineContext);
        }
        if (obj == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (obj instanceof Integer) {
            return coroutineContext.fold(new ThreadState(coroutineContext, ((Number) obj).intValue()), ThreadContextKt$findOne$1.INSTANCE$2);
        }
        DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
        throw null;
    }
}
