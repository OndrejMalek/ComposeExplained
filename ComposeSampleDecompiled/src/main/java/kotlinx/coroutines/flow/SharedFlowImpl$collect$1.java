package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.Job;

/* loaded from: classes.dex */
public final class SharedFlowImpl$collect$1 extends ContinuationImpl {
    public SharedFlowImpl L$0;
    public FlowCollector L$1;
    public SharedFlowSlot L$2;
    public Job L$3;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ SharedFlowImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedFlowImpl$collect$1(SharedFlowImpl sharedFlowImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = sharedFlowImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        SharedFlowImpl.collect$suspendImpl(this.this$0, null, this);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
