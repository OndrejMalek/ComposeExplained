package kotlinx.coroutines.flow.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.DistinctFlowImpl$collect$2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.AtomicKt;

/* loaded from: classes.dex */
public abstract class ChannelFlowOperator extends ChannelFlow {
    public final Flow flow;

    public ChannelFlowOperator(int i, CoroutineContext coroutineContext, BufferOverflow bufferOverflow, Flow flow) {
        super(coroutineContext, i, bufferOverflow);
        this.flow = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object coroutineScope;
        Unit unit = Unit.INSTANCE;
        if (this.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext plus = context.plus(this.context);
            if (ResultKt.areEqual(plus, context)) {
                coroutineScope = flowCollect(flowCollector, continuation);
                if (coroutineScope != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return unit;
                }
            } else {
                ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
                if (ResultKt.areEqual(plus.get(key), context.get(key))) {
                    CoroutineContext context2 = continuation.getContext();
                    if (!(flowCollector instanceof SendingCollector) && !(flowCollector instanceof NopCollector)) {
                        flowCollector = new DistinctFlowImpl$collect$2(flowCollector, context2);
                    }
                    coroutineScope = JobKt.withContextUndispatched(plus, flowCollector, AtomicKt.threadContextElements(plus), new ChannelFlowOperator$collectWithContextUndispatched$2(this, null), continuation);
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (coroutineScope != coroutineSingletons) {
                        coroutineScope = unit;
                    }
                    if (coroutineScope != coroutineSingletons) {
                        return unit;
                    }
                }
            }
            return coroutineScope;
        }
        coroutineScope = ResultKt.coroutineScope(new ChannelFlow$collect$2(null, flowCollector, this), continuation);
        CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (coroutineScope != coroutineSingletons2) {
            coroutineScope = unit;
        }
        if (coroutineScope != coroutineSingletons2) {
            return unit;
        }
        return coroutineScope;
    }

    public abstract Object flowCollect(FlowCollector flowCollector, Continuation continuation);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final String toString() {
        return this.flow + " -> " + super.toString();
    }
}
