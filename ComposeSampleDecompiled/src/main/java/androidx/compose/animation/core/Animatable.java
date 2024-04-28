package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public final class Animatable {
    public final AnimationState internalState;
    public final ParcelableSnapshotMutableState isRunning$delegate;
    public final AnimationVector lowerBoundVector;
    public final MutatorMutex mutatorMutex;
    public final AnimationVector negativeInfinityBounds;
    public final AnimationVector positiveInfinityBounds;
    public final ParcelableSnapshotMutableState targetValue$delegate;
    public final TwoWayConverterImpl typeConverter;
    public final AnimationVector upperBoundVector;
    public final Object visibilityThreshold;

    public Animatable(Comparable comparable, TwoWayConverterImpl twoWayConverterImpl, Float f, int i) {
        ResultKt.checkNotNullParameter(twoWayConverterImpl, "typeConverter");
        this.typeConverter = twoWayConverterImpl;
        this.internalState = new AnimationState(twoWayConverterImpl, comparable, null, Long.MIN_VALUE, Long.MIN_VALUE, false);
        Boolean bool = Boolean.FALSE;
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this.isRunning$delegate = ResultKt.mutableStateOf(bool, structuralEqualityPolicy);
        this.targetValue$delegate = ResultKt.mutableStateOf(comparable, structuralEqualityPolicy);
        this.mutatorMutex = new MutatorMutex();
        AnimationVector animationVector = (AnimationVector) twoWayConverterImpl.convertToVector.invoke(comparable);
        int size$animation_core_release = animationVector.getSize$animation_core_release();
        for (int i2 = 0; i2 < size$animation_core_release; i2++) {
            animationVector.set$animation_core_release(Float.NEGATIVE_INFINITY, i2);
        }
        this.negativeInfinityBounds = animationVector;
        AnimationVector animationVector2 = (AnimationVector) this.typeConverter.convertToVector.invoke(comparable);
        int size$animation_core_release2 = animationVector2.getSize$animation_core_release();
        for (int i3 = 0; i3 < size$animation_core_release2; i3++) {
            animationVector2.set$animation_core_release(Float.POSITIVE_INFINITY, i3);
        }
        this.positiveInfinityBounds = animationVector2;
        this.lowerBoundVector = animationVector;
        this.upperBoundVector = animationVector2;
    }

    public static final Object access$clampToBounds(Animatable animatable, Object obj) {
        AnimationVector animationVector = animatable.negativeInfinityBounds;
        AnimationVector animationVector2 = animatable.lowerBoundVector;
        boolean areEqual = ResultKt.areEqual(animationVector2, animationVector);
        AnimationVector animationVector3 = animatable.upperBoundVector;
        if (areEqual && ResultKt.areEqual(animationVector3, animatable.positiveInfinityBounds)) {
            return obj;
        }
        TwoWayConverterImpl twoWayConverterImpl = animatable.typeConverter;
        AnimationVector animationVector4 = (AnimationVector) twoWayConverterImpl.convertToVector.invoke(obj);
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        boolean z = false;
        for (int i = 0; i < size$animation_core_release; i++) {
            if (animationVector4.get$animation_core_release(i) < animationVector2.get$animation_core_release(i) || animationVector4.get$animation_core_release(i) > animationVector3.get$animation_core_release(i)) {
                animationVector4.set$animation_core_release(ResultKt.coerceIn(animationVector4.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)), i);
                z = true;
            }
        }
        return z ? twoWayConverterImpl.convertFromVector.invoke(animationVector4) : obj;
    }

    public static Object animateTo$default(Animatable animatable, Comparable comparable, AnimationSpec animationSpec, Continuation continuation) {
        Object invoke = animatable.typeConverter.convertFromVector.invoke(animatable.internalState.velocityVector);
        Object value = animatable.getValue();
        ResultKt.checkNotNullParameter(animationSpec, "animationSpec");
        TwoWayConverterImpl twoWayConverterImpl = animatable.typeConverter;
        ResultKt.checkNotNullParameter(twoWayConverterImpl, "typeConverter");
        Animatable$runAnimation$2 animatable$runAnimation$2 = new Animatable$runAnimation$2(animatable, invoke, new TargetBasedAnimation(animationSpec, twoWayConverterImpl, value, comparable, (AnimationVector) twoWayConverterImpl.convertToVector.invoke(invoke)), animatable.internalState.lastFrameTimeNanos, null, null);
        MutatorMutex mutatorMutex = animatable.mutatorMutex;
        mutatorMutex.getClass();
        return ResultKt.coroutineScope(new MutatorMutex$mutate$2(1, mutatorMutex, animatable$runAnimation$2, null), continuation);
    }

    public final Object getValue() {
        return this.internalState.value$delegate.getValue();
    }
}
