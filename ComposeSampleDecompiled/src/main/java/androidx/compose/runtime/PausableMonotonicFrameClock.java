package androidx.compose.runtime;

import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class PausableMonotonicFrameClock implements MonotonicFrameClock {
    public final MonotonicFrameClock frameClock;
    public final Latch latch = new Latch();

    public PausableMonotonicFrameClock(MonotonicFrameClock monotonicFrameClock) {
        this.frameClock = monotonicFrameClock;
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

    /* JADX WARN: Removed duplicated region for block: B:11:0x008d A[PHI: r8
      0x008d: PHI (r8v9 java.lang.Object) = (r8v8 java.lang.Object), (r8v1 java.lang.Object) binds: [B:17:0x008a, B:10:0x0026] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    @Override // androidx.compose.runtime.MonotonicFrameClock
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object withFrameNanos(kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof androidx.compose.runtime.PausableMonotonicFrameClock$withFrameNanos$1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.runtime.PausableMonotonicFrameClock$withFrameNanos$1 r0 = (androidx.compose.runtime.PausableMonotonicFrameClock$withFrameNanos$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.runtime.PausableMonotonicFrameClock$withFrameNanos$1 r0 = new androidx.compose.runtime.PausableMonotonicFrameClock$withFrameNanos$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 == r4) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L8d
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            kotlin.jvm.functions.Function1 r7 = r0.L$1
            androidx.compose.runtime.PausableMonotonicFrameClock r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7d
        L3a:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.runtime.Latch r8 = r6.latch
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r2 = r8.lock
            monitor-enter(r2)
            boolean r5 = r8._isOpen     // Catch: java.lang.Throwable -> L91
            monitor-exit(r2)
            if (r5 == 0) goto L50
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            goto L79
        L50:
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.ResultKt.intercepted(r0)
            r2.<init>(r4, r5)
            r2.initCancellability()
            java.lang.Object r4 = r8.lock
            monitor-enter(r4)
            java.lang.Object r5 = r8.awaiters     // Catch: java.lang.Throwable -> L8e
            java.util.List r5 = (java.util.List) r5     // Catch: java.lang.Throwable -> L8e
            r5.add(r2)     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r4)
            androidx.compose.runtime.Latch$await$2$2 r4 = new androidx.compose.runtime.Latch$await$2$2
            r5 = 0
            r4.<init>(r8, r5, r2)
            r2.invokeOnCancellation(r4)
            java.lang.Object r8 = r2.getResult()
            if (r8 != r1) goto L77
            goto L79
        L77:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L79:
            if (r8 != r1) goto L7c
            return r1
        L7c:
            r2 = r6
        L7d:
            androidx.compose.runtime.MonotonicFrameClock r8 = r2.frameClock
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r8 = r8.withFrameNanos(r7, r0)
            if (r8 != r1) goto L8d
            return r1
        L8d:
            return r8
        L8e:
            r7 = move-exception
            monitor-exit(r4)
            throw r7
        L91:
            r7 = move-exception
            monitor-exit(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.PausableMonotonicFrameClock.withFrameNanos(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
