package kotlinx.coroutines;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* loaded from: classes.dex */
public final class UndispatchedCoroutine extends ScopeCoroutine {
    private volatile boolean threadLocalIsSet;
    public final ThreadLocal threadStateToRecover;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public UndispatchedCoroutine(kotlin.coroutines.Continuation r3, kotlin.coroutines.CoroutineContext r4) {
        /*
            r2 = this;
            kotlinx.coroutines.UndispatchedMarker r0 = kotlinx.coroutines.UndispatchedMarker.INSTANCE
            kotlin.coroutines.CoroutineContext$Element r1 = r4.get(r0)
            if (r1 != 0) goto Ld
            kotlin.coroutines.CoroutineContext r0 = r4.plus(r0)
            goto Le
        Ld:
            r0 = r4
        Le:
            r2.<init>(r3, r0)
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            r2.threadStateToRecover = r0
            kotlin.coroutines.CoroutineContext r3 = r3.getContext()
            kotlin.coroutines.ContinuationInterceptor$Key r0 = kotlin.coroutines.ContinuationInterceptor.Key.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r0)
            boolean r3 = r3 instanceof kotlinx.coroutines.CoroutineDispatcher
            if (r3 != 0) goto L31
            r3 = 0
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.updateThreadContext(r4, r3)
            kotlinx.coroutines.internal.AtomicKt.restoreThreadContext(r4, r3)
            r2.saveThreadContext(r4, r3)
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.UndispatchedCoroutine.<init>(kotlin.coroutines.Continuation, kotlin.coroutines.CoroutineContext):void");
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public final void afterResume(Object obj) {
        if (this.threadLocalIsSet) {
            Pair pair = (Pair) this.threadStateToRecover.get();
            if (pair != null) {
                AtomicKt.restoreThreadContext((CoroutineContext) pair.first, pair.second);
            }
            this.threadStateToRecover.remove();
        }
        Object recoverResult = ResultKt.recoverResult(obj);
        Continuation continuation = this.uCont;
        CoroutineContext context = continuation.getContext();
        Object updateThreadContext = AtomicKt.updateThreadContext(context, null);
        UndispatchedCoroutine updateUndispatchedCompletion = updateThreadContext != AtomicKt.NO_THREAD_ELEMENTS ? ResultKt.updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
        try {
            this.uCont.resumeWith(recoverResult);
        } finally {
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                AtomicKt.restoreThreadContext(context, updateThreadContext);
            }
        }
    }

    public final boolean clearThreadContext() {
        boolean z = this.threadLocalIsSet && this.threadStateToRecover.get() == null;
        this.threadStateToRecover.remove();
        return !z;
    }

    public final void saveThreadContext(CoroutineContext coroutineContext, Object obj) {
        this.threadLocalIsSet = true;
        this.threadStateToRecover.set(new Pair(coroutineContext, obj));
    }
}
