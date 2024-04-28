package androidx.compose.animation.core;

import kotlin.ResultKt;
import kotlinx.coroutines.flow.SafeFlow;

/* loaded from: classes.dex */
public final class VectorizedSpringSpec implements VectorizedFiniteAnimationSpec {
    public final /* synthetic */ VectorizedFloatAnimationSpec $$delegate_0;

    public VectorizedSpringSpec(float f, float f2, AnimationVector animationVector) {
        this.$$delegate_0 = new VectorizedFloatAnimationSpec(animationVector != null ? new SafeFlow(f, f2, animationVector) : new SafeFlow(f, f2));
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        return this.$$delegate_0.getDurationNanos(animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        return this.$$delegate_0.getEndVelocity(animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        ResultKt.checkNotNullParameter(animationVector3, "initialVelocity");
        return this.$$delegate_0.getValueFromNanos(j, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        ResultKt.checkNotNullParameter(animationVector3, "initialVelocity");
        return this.$$delegate_0.getVelocityFromNanos(j, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final void isInfinite() {
        this.$$delegate_0.getClass();
    }
}
