package androidx.compose.animation.core;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class VectorizedTweenSpec implements VectorizedDurationBasedAnimationSpec {
    public final VectorizedFloatAnimationSpec anim;
    public final int delayMillis;
    public final int durationMillis;

    public VectorizedTweenSpec(int i, int i2, Easing easing) {
        ResultKt.checkNotNullParameter(easing, "easing");
        this.durationMillis = i;
        this.delayMillis = i2;
        this.anim = new VectorizedFloatAnimationSpec(new FloatTweenSpec(i, i2, easing));
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        ResultKt.checkNotNullParameter(animationVector3, "initialVelocity");
        return this.anim.getValueFromNanos(j, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        ResultKt.checkNotNullParameter(animationVector3, "initialVelocity");
        return this.anim.getVelocityFromNanos(j, animationVector, animationVector2, animationVector3);
    }
}
