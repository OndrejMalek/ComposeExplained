package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* loaded from: classes.dex */
public final class DistinctFlowImpl implements Flow {
    public final Function2 areEquivalent;
    public final Function1 keySelector;
    public final Flow upstream;

    public DistinctFlowImpl(Flow flow) {
        FlowKt__DistinctKt$defaultKeySelector$1 flowKt__DistinctKt$defaultKeySelector$1 = FlowKt__DistinctKt$defaultKeySelector$1.INSTANCE;
        FlowKt__DistinctKt$defaultAreEquivalent$1 flowKt__DistinctKt$defaultAreEquivalent$1 = FlowKt__DistinctKt$defaultAreEquivalent$1.INSTANCE;
        this.upstream = flow;
        this.keySelector = flowKt__DistinctKt$defaultKeySelector$1;
        this.areEquivalent = flowKt__DistinctKt$defaultAreEquivalent$1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        ?? obj = new Object();
        obj.element = NullSurrogateKt.NULL;
        Object collect = this.upstream.collect(new DistinctFlowImpl$collect$2(this, obj, flowCollector), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
