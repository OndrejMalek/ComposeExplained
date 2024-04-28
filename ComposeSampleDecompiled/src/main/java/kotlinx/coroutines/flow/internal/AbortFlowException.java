package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2;

/* loaded from: classes.dex */
public final class AbortFlowException extends CancellationException {
    public final transient FlowCollector owner;

    public AbortFlowException(FlowKt__ReduceKt$first$$inlined$collectWhile$2 flowKt__ReduceKt$first$$inlined$collectWhile$2) {
        super("Flow was aborted, no more elements needed");
        this.owner = flowKt__ReduceKt$first$$inlined$collectWhile$2;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
