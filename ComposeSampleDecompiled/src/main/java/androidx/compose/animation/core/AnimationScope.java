package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class AnimationScope {
    public long finishedTimeNanos;
    public final ParcelableSnapshotMutableState isRunning$delegate;
    public long lastFrameTimeNanos;
    public final Function0 onCancel;
    public final long startTimeNanos;
    public final Object targetValue;
    public final ParcelableSnapshotMutableState value$delegate;
    public AnimationVector velocityVector;

    public AnimationScope(Object obj, TwoWayConverterImpl twoWayConverterImpl, AnimationVector animationVector, long j, Object obj2, long j2, SuspendAnimationKt$animate$7 suspendAnimationKt$animate$7) {
        ResultKt.checkNotNullParameter(twoWayConverterImpl, "typeConverter");
        ResultKt.checkNotNullParameter(animationVector, "initialVelocityVector");
        this.targetValue = obj2;
        this.startTimeNanos = j2;
        this.onCancel = suspendAnimationKt$animate$7;
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this.value$delegate = ResultKt.mutableStateOf(obj, structuralEqualityPolicy);
        this.velocityVector = ResultKt.copy(animationVector);
        this.lastFrameTimeNanos = j;
        this.finishedTimeNanos = Long.MIN_VALUE;
        this.isRunning$delegate = ResultKt.mutableStateOf(Boolean.TRUE, structuralEqualityPolicy);
    }
}
