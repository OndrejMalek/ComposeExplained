package androidx.compose.animation.core;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class TargetBasedAnimation implements Animation {
    public final VectorizedFiniteAnimationSpec animationSpec;
    public final long durationNanos;
    public final AnimationVector endVelocity;
    public final Object initialValue;
    public final AnimationVector initialValueVector;
    public final AnimationVector initialVelocityVector;
    public final Object targetValue;
    public final AnimationVector targetValueVector;
    public final TwoWayConverterImpl typeConverter;

    public TargetBasedAnimation(AnimationSpec animationSpec, TwoWayConverterImpl twoWayConverterImpl, Object obj, Comparable comparable, AnimationVector animationVector) {
        VectorizedFiniteAnimationSpec vectorize = animationSpec.vectorize(twoWayConverterImpl);
        this.animationSpec = vectorize;
        this.typeConverter = twoWayConverterImpl;
        this.initialValue = obj;
        this.targetValue = comparable;
        Function1 function1 = twoWayConverterImpl.convertToVector;
        AnimationVector animationVector2 = (AnimationVector) function1.invoke(obj);
        this.initialValueVector = animationVector2;
        AnimationVector animationVector3 = (AnimationVector) function1.invoke(comparable);
        this.targetValueVector = animationVector3;
        AnimationVector copy = animationVector != null ? ResultKt.copy(animationVector) : ResultKt.newInstance((AnimationVector) function1.invoke(obj));
        this.initialVelocityVector = copy;
        this.durationNanos = vectorize.getDurationNanos(animationVector2, animationVector3, copy);
        this.endVelocity = vectorize.getEndVelocity(animationVector2, animationVector3, copy);
    }

    public final Object getValueFromNanos(long j) {
        if (j >= this.durationNanos) {
            return this.targetValue;
        }
        AnimationVector valueFromNanos = this.animationSpec.getValueFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        int size$animation_core_release = valueFromNanos.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            if (!(!Float.isNaN(valueFromNanos.get$animation_core_release(i)))) {
                throw new IllegalStateException(("AnimationVector cannot contain a NaN. " + valueFromNanos + ". Animation: " + this + ", playTimeNanos: " + j).toString());
            }
        }
        return this.typeConverter.convertFromVector.invoke(valueFromNanos);
    }

    public final String toString() {
        return "TargetBasedAnimation: " + this.initialValue + " -> " + this.targetValue + ",initial velocity: " + this.initialVelocityVector + ", duration: " + (this.durationNanos / 1000000) + " ms,animationSpec: " + this.animationSpec;
    }
}
