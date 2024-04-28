package kotlinx.coroutines.flow;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.JobKt;

/* loaded from: classes.dex */
public final class FlowKt__ReduceKt$first$3 extends ContinuationImpl {
    public Function2 L$0;
    public Ref$ObjectRef L$1;
    public FlowKt__ReduceKt$first$$inlined$collectWhile$2 L$2;
    public int label;
    public /* synthetic */ Object result;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return JobKt.first(null, null, this);
    }
}
