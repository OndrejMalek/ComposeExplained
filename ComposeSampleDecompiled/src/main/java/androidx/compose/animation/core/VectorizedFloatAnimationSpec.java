package androidx.compose.animation.core;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.ranges.IntProgressionIterator;
import kotlinx.coroutines.flow.SafeFlow;

/* loaded from: classes.dex */
public final class VectorizedFloatAnimationSpec implements VectorizedFiniteAnimationSpec {
    public final Animations anims;
    public AnimationVector endVelocityVector;
    public AnimationVector valueVector;
    public AnimationVector velocityVector;

    public VectorizedFloatAnimationSpec(SafeFlow safeFlow) {
        this.anims = safeFlow;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        Iterator it = ResultKt.until(0, animationVector.getSize$animation_core_release()).iterator();
        long j = 0;
        while (((IntProgressionIterator) it).hasNext) {
            int nextInt = ((IntProgressionIterator) it).nextInt();
            j = Math.max(j, ((SafeFlow) this.anims).get(nextInt).getDurationNanos(animationVector.get$animation_core_release(nextInt), animationVector2.get$animation_core_release(nextInt), animationVector3.get$animation_core_release(nextInt)));
        }
        return j;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        if (this.endVelocityVector == null) {
            this.endVelocityVector = animationVector3.newVector$animation_core_release();
        }
        AnimationVector animationVector4 = this.endVelocityVector;
        if (animationVector4 == null) {
            ResultKt.throwUninitializedPropertyAccessException("endVelocityVector");
            throw null;
        }
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector5 = this.endVelocityVector;
            if (animationVector5 == null) {
                ResultKt.throwUninitializedPropertyAccessException("endVelocityVector");
                throw null;
            }
            animationVector5.set$animation_core_release(((SafeFlow) this.anims).get(i).getEndVelocity(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)), i);
        }
        AnimationVector animationVector6 = this.endVelocityVector;
        if (animationVector6 != null) {
            return animationVector6;
        }
        ResultKt.throwUninitializedPropertyAccessException("endVelocityVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        ResultKt.checkNotNullParameter(animationVector3, "initialVelocity");
        if (this.valueVector == null) {
            this.valueVector = animationVector.newVector$animation_core_release();
        }
        AnimationVector animationVector4 = this.valueVector;
        if (animationVector4 == null) {
            ResultKt.throwUninitializedPropertyAccessException("valueVector");
            throw null;
        }
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector5 = this.valueVector;
            if (animationVector5 == null) {
                ResultKt.throwUninitializedPropertyAccessException("valueVector");
                throw null;
            }
            animationVector5.set$animation_core_release(((SafeFlow) this.anims).get(i).getValueFromNanos(j, animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)), i);
        }
        AnimationVector animationVector6 = this.valueVector;
        if (animationVector6 != null) {
            return animationVector6;
        }
        ResultKt.throwUninitializedPropertyAccessException("valueVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        ResultKt.checkNotNullParameter(animationVector, "initialValue");
        ResultKt.checkNotNullParameter(animationVector2, "targetValue");
        ResultKt.checkNotNullParameter(animationVector3, "initialVelocity");
        if (this.velocityVector == null) {
            this.velocityVector = animationVector3.newVector$animation_core_release();
        }
        AnimationVector animationVector4 = this.velocityVector;
        if (animationVector4 == null) {
            ResultKt.throwUninitializedPropertyAccessException("velocityVector");
            throw null;
        }
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector5 = this.velocityVector;
            if (animationVector5 == null) {
                ResultKt.throwUninitializedPropertyAccessException("velocityVector");
                throw null;
            }
            animationVector5.set$animation_core_release(((SafeFlow) this.anims).get(i).getVelocityFromNanos(j, animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)), i);
        }
        AnimationVector animationVector6 = this.velocityVector;
        if (animationVector6 != null) {
            return animationVector6;
        }
        ResultKt.throwUninitializedPropertyAccessException("velocityVector");
        throw null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VectorizedFloatAnimationSpec(FloatAnimationSpec floatAnimationSpec) {
        this(new SafeFlow(floatAnimationSpec));
        ResultKt.checkNotNullParameter(floatAnimationSpec, "anim");
    }
}
