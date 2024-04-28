package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;

/* loaded from: classes.dex */
public final class BroadcastFrameClock implements MonotonicFrameClock {
    public Throwable failureCause;
    public final Function0 onNewAwaiters;
    public final Object lock = new Object();
    public List awaiters = new ArrayList();
    public List spareList = new ArrayList();

    /* loaded from: classes.dex */
    public final class FrameAwaiter {
        public final Continuation continuation;
        public final Function1 onFrame;

        public FrameAwaiter(Function1 function1, CancellableContinuationImpl cancellableContinuationImpl) {
            ResultKt.checkNotNullParameter(function1, "onFrame");
            this.onFrame = function1;
            this.continuation = cancellableContinuationImpl;
        }
    }

    public BroadcastFrameClock(Pending$keyMap$2 pending$keyMap$2) {
        this.onNewAwaiters = pending$keyMap$2;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        ResultKt.checkNotNullParameter(key, "key");
        return ResultKt.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        ResultKt.checkNotNullParameter(key, "key");
        return ResultKt.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        ResultKt.checkNotNullParameter(coroutineContext, "context");
        return ResultKt.plus((CoroutineContext) this, coroutineContext);
    }

    public final void sendFrame(long j) {
        Object createFailure;
        synchronized (this.lock) {
            try {
                List list = this.awaiters;
                this.awaiters = this.spareList;
                this.spareList = list;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    FrameAwaiter frameAwaiter = (FrameAwaiter) list.get(i);
                    frameAwaiter.getClass();
                    try {
                        createFailure = frameAwaiter.onFrame.invoke(Long.valueOf(j));
                    } catch (Throwable th) {
                        createFailure = ResultKt.createFailure(th);
                    }
                    frameAwaiter.continuation.resumeWith(createFailure);
                }
                list.clear();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    @Override // androidx.compose.runtime.MonotonicFrameClock
    public final Object withFrameNanos(Function1 function1, Continuation continuation) {
        Function0 function0;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        ?? obj = new Object();
        synchronized (this.lock) {
            Throwable th = this.failureCause;
            if (th != null) {
                cancellableContinuationImpl.resumeWith(ResultKt.createFailure(th));
            } else {
                obj.element = new FrameAwaiter(function1, cancellableContinuationImpl);
                boolean isEmpty = this.awaiters.isEmpty();
                List list = this.awaiters;
                Object obj2 = obj.element;
                if (obj2 == null) {
                    ResultKt.throwUninitializedPropertyAccessException("awaiter");
                    throw null;
                }
                list.add((FrameAwaiter) obj2);
                cancellableContinuationImpl.invokeOnCancellation(new Latch$await$2$2(this, 2, obj));
                if (isEmpty && (function0 = this.onNewAwaiters) != null) {
                    try {
                        function0.invoke();
                    } catch (Throwable th2) {
                        synchronized (this.lock) {
                            try {
                                if (this.failureCause == null) {
                                    this.failureCause = th2;
                                    List list2 = this.awaiters;
                                    int size = list2.size();
                                    for (int i = 0; i < size; i++) {
                                        ((FrameAwaiter) list2.get(i)).continuation.resumeWith(ResultKt.createFailure(th2));
                                    }
                                    this.awaiters.clear();
                                }
                            } catch (Throwable th3) {
                                throw th3;
                            }
                        }
                    }
                }
            }
        }
        return cancellableContinuationImpl.getResult();
    }
}
