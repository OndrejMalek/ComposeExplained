package androidx.compose.runtime;

import android.view.Choreographer;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public final class DefaultChoreographerFrameClock implements MonotonicFrameClock {
    public static final Choreographer choreographer;

    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    static {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        choreographer = (Choreographer) ResultKt.runBlocking(((HandlerContext) MainDispatcherLoader.dispatcher).immediate, new SuspendLambda(2, null));
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

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public final Object withFrameNanos(final Function1 function1, Continuation continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() { // from class: androidx.compose.runtime.DefaultChoreographerFrameClock$withFrameNanos$2$callback$1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                Object createFailure;
                Choreographer choreographer2 = DefaultChoreographerFrameClock.choreographer;
                try {
                    createFailure = function1.invoke(Long.valueOf(j));
                } catch (Throwable th) {
                    createFailure = ResultKt.createFailure(th);
                }
                cancellableContinuationImpl.resumeWith(createFailure);
            }
        };
        choreographer.postFrameCallback(frameCallback);
        cancellableContinuationImpl.invokeOnCancellation(new Recomposer$effectJob$1$1(2, frameCallback));
        return cancellableContinuationImpl.getResult();
    }
}
