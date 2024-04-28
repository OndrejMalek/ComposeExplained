package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$BooleanRef;

/* loaded from: classes.dex */
public final class Animatable$runAnimation$2 extends SuspendLambda implements Function1 {
    public final /* synthetic */ Animation $animation;
    public final /* synthetic */ Function1 $block;
    public final /* synthetic */ Object $initialVelocity;
    public final /* synthetic */ long $startTime;
    public AnimationState L$0;
    public Ref$BooleanRef L$1;
    public int label;
    public final /* synthetic */ Animatable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Animatable$runAnimation$2(Animatable animatable, Object obj, Animation animation, long j, Function1 function1, Continuation continuation) {
        super(1, continuation);
        this.this$0 = animatable;
        this.$initialVelocity = obj;
        this.$animation = animation;
        this.$startTime = j;
        this.$block = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        long j = this.$startTime;
        Function1 function1 = this.$block;
        return new Animatable$runAnimation$2(this.this$0, this.$initialVelocity, this.$animation, j, function1, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r12v1, types: [kotlin.jvm.internal.Ref$BooleanRef, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.compose.animation.core.Animatable$runAnimation$2$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref$BooleanRef ref$BooleanRef;
        AnimationState animationState;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        int i2 = 1;
        final Animatable animatable = this.this$0;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AnimationState animationState2 = animatable.internalState;
                AnimationVector animationVector = (AnimationVector) animatable.typeConverter.convertToVector.invoke(this.$initialVelocity);
                animationState2.getClass();
                ResultKt.checkNotNullParameter(animationVector, "<set-?>");
                animationState2.velocityVector = animationVector;
                animatable.targetValue$delegate.setValue(((TargetBasedAnimation) this.$animation).targetValue);
                animatable.isRunning$delegate.setValue(Boolean.TRUE);
                AnimationState animationState3 = animatable.internalState;
                final AnimationState animationState4 = new AnimationState(animationState3.typeConverter, animationState3.value$delegate.getValue(), ResultKt.copy(animationState3.velocityVector), animationState3.lastFrameTimeNanos, Long.MIN_VALUE, animationState3.isRunning);
                final ?? obj2 = new Object();
                Animation animation = this.$animation;
                long j = this.$startTime;
                final Function1 function1 = this.$block;
                ?? r5 = new Function1() { // from class: androidx.compose.animation.core.Animatable$runAnimation$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        AnimationScope animationScope = (AnimationScope) obj3;
                        ResultKt.checkNotNullParameter(animationScope, "$this$animate");
                        Animatable animatable2 = Animatable.this;
                        ResultKt.updateState(animationScope, animatable2.internalState);
                        ParcelableSnapshotMutableState parcelableSnapshotMutableState = animationScope.value$delegate;
                        Object access$clampToBounds = Animatable.access$clampToBounds(animatable2, parcelableSnapshotMutableState.getValue());
                        boolean areEqual = ResultKt.areEqual(access$clampToBounds, parcelableSnapshotMutableState.getValue());
                        Function1 function12 = function1;
                        if (!areEqual) {
                            animatable2.internalState.value$delegate.setValue(access$clampToBounds);
                            animationState4.value$delegate.setValue(access$clampToBounds);
                            if (function12 != null) {
                                function12.invoke(animatable2);
                            }
                            animationScope.isRunning$delegate.setValue(Boolean.FALSE);
                            animationScope.onCancel.invoke();
                            obj2.element = true;
                        } else if (function12 != null) {
                            function12.invoke(animatable2);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.L$0 = animationState4;
                this.L$1 = obj2;
                this.label = 1;
                if (ResultKt.animate(animationState4, animation, j, r5, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                ref$BooleanRef = obj2;
                animationState = animationState4;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ref$BooleanRef = this.L$1;
                animationState = this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            if (!ref$BooleanRef.element) {
                i2 = 2;
            }
            AnimationState animationState5 = animatable.internalState;
            animationState5.velocityVector.reset$animation_core_release();
            animationState5.lastFrameTimeNanos = Long.MIN_VALUE;
            animatable.isRunning$delegate.setValue(Boolean.FALSE);
            return new AnimationResult(animationState, i2);
        } catch (CancellationException e) {
            AnimationState animationState6 = animatable.internalState;
            animationState6.velocityVector.reset$animation_core_release();
            animationState6.lastFrameTimeNanos = Long.MIN_VALUE;
            animatable.isRunning$delegate.setValue(Boolean.FALSE);
            throw e;
        }
    }
}
