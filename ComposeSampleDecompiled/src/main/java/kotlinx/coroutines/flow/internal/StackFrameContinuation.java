package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

/* loaded from: classes.dex */
public final class StackFrameContinuation implements Continuation, CoroutineStackFrame {
    public final CoroutineContext context;
    public final Continuation uCont;

    public StackFrameContinuation(Continuation continuation, CoroutineContext coroutineContext) {
        this.uCont = continuation;
        this.context = coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        this.uCont.resumeWith(obj);
    }
}
