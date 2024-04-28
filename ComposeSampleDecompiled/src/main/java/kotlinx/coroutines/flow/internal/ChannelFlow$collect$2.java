package kotlinx.coroutines.flow.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public final class ChannelFlow$collect$2 extends SuspendLambda implements Function2 {
    public final /* synthetic */ FlowCollector $collector;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ChannelFlow this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlow$collect$2(Continuation continuation, FlowCollector flowCollector, ChannelFlow channelFlow) {
        super(2, continuation);
        this.$collector = flowCollector;
        this.this$0 = channelFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ChannelFlow$collect$2 channelFlow$collect$2 = new ChannelFlow$collect$2(continuation, this.$collector, this.this$0);
        channelFlow$collect$2.L$0 = obj;
        return channelFlow$collect$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelFlow$collect$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ChannelFlow channelFlow = this.this$0;
            int i2 = channelFlow.capacity;
            if (i2 == -3) {
                i2 = -2;
            }
            Function2 channelFlow$collectToFun$1 = new ChannelFlow$collectToFun$1(channelFlow, null);
            BufferedChannel Channel$default = JobKt.Channel$default(i2, channelFlow.onBufferOverflow, 4);
            CoroutineContext foldCopies = ResultKt.foldCopies(coroutineScope.getCoroutineContext(), channelFlow.context, true);
            DefaultScheduler defaultScheduler = Dispatchers.Default;
            if (foldCopies != defaultScheduler && foldCopies.get(ContinuationInterceptor.Key.$$INSTANCE) == null) {
                foldCopies = foldCopies.plus(defaultScheduler);
            }
            ProducerCoroutine producerCoroutine = new ProducerCoroutine(foldCopies, Channel$default);
            producerCoroutine.start(3, producerCoroutine, channelFlow$collectToFun$1);
            this.label = 1;
            Object emitAllImpl$FlowKt__ChannelsKt = JobKt.emitAllImpl$FlowKt__ChannelsKt(this.$collector, producerCoroutine, true, this);
            if (emitAllImpl$FlowKt__ChannelsKt != coroutineSingletons) {
                emitAllImpl$FlowKt__ChannelsKt = unit;
            }
            if (emitAllImpl$FlowKt__ChannelsKt == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
