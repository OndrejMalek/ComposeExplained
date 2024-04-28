package androidx.compose.animation.core;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SpringSpec implements AnimationSpec {
    public final float dampingRatio = 1.0f;
    public final float stiffness = 1500.0f;
    public final Object visibilityThreshold;

    public SpringSpec(Object obj) {
        this.visibilityThreshold = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SpringSpec)) {
            return false;
        }
        SpringSpec springSpec = (SpringSpec) obj;
        return springSpec.dampingRatio == this.dampingRatio && springSpec.stiffness == this.stiffness && ResultKt.areEqual(springSpec.visibilityThreshold, this.visibilityThreshold);
    }

    public final int hashCode() {
        Object obj = this.visibilityThreshold;
        return Float.hashCode(this.stiffness) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.dampingRatio, (obj != null ? obj.hashCode() : 0) * 31, 31);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverterImpl twoWayConverterImpl) {
        Object obj = this.visibilityThreshold;
        return new VectorizedSpringSpec(this.dampingRatio, this.stiffness, obj == null ? null : (AnimationVector) twoWayConverterImpl.convertToVector.invoke(obj));
    }
}
