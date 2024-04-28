package androidx.compose.animation.core;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public interface VectorizedDurationBasedAnimationSpec extends VectorizedFiniteAnimationSpec {
    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    default long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        VectorizedTweenSpec vectorizedTweenSpec = (VectorizedTweenSpec) this;
        return (vectorizedTweenSpec.delayMillis + vectorizedTweenSpec.durationMillis) * 1000000;
    }
}
