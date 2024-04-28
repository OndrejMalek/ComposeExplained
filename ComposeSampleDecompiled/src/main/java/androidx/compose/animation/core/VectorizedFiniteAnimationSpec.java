package androidx.compose.animation.core;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public interface VectorizedFiniteAnimationSpec {
    long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    default AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        return getVelocityFromNanos(getDurationNanos(animationVector, animationVector2, animationVector3), animationVector, animationVector2, animationVector3);
    }

    AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    default void isInfinite() {
    }
}
