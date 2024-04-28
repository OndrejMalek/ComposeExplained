package androidx.compose.animation.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class Animatable$snapTo$2 extends SuspendLambda implements Function1 {
    public final /* synthetic */ Object $targetValue;
    public final /* synthetic */ Animatable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Animatable$snapTo$2(Animatable animatable, Object obj, Continuation continuation) {
        super(1, continuation);
        this.this$0 = animatable;
        this.$targetValue = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Animatable$snapTo$2 animatable$snapTo$2 = new Animatable$snapTo$2(this.this$0, this.$targetValue, (Continuation) obj);
        Unit unit = Unit.INSTANCE;
        animatable$snapTo$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ResultKt.throwOnFailure(obj);
        Animatable animatable = this.this$0;
        AnimationState animationState = animatable.internalState;
        animationState.velocityVector.reset$animation_core_release();
        animationState.lastFrameTimeNanos = Long.MIN_VALUE;
        animatable.isRunning$delegate.setValue(Boolean.FALSE);
        Object access$clampToBounds = Animatable.access$clampToBounds(animatable, this.$targetValue);
        animatable.internalState.value$delegate.setValue(access$clampToBounds);
        animatable.targetValue$delegate.setValue(access$clampToBounds);
        return Unit.INSTANCE;
    }
}
