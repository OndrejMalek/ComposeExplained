package androidx.compose.animation.core;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StructuralEqualityPolicy;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AnimationState implements State {
    public long finishedTimeNanos;
    public boolean isRunning;
    public long lastFrameTimeNanos;
    public final TwoWayConverterImpl typeConverter;
    public final ParcelableSnapshotMutableState value$delegate;
    public AnimationVector velocityVector;

    public AnimationState(TwoWayConverterImpl twoWayConverterImpl, Object obj, AnimationVector animationVector, long j, long j2, boolean z) {
        ResultKt.checkNotNullParameter(twoWayConverterImpl, "typeConverter");
        this.typeConverter = twoWayConverterImpl;
        this.value$delegate = ResultKt.mutableStateOf(obj, StructuralEqualityPolicy.INSTANCE);
        this.velocityVector = animationVector != null ? ResultKt.copy(animationVector) : ResultKt.newInstance((AnimationVector) twoWayConverterImpl.convertToVector.invoke(obj));
        this.lastFrameTimeNanos = j;
        this.finishedTimeNanos = j2;
        this.isRunning = z;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return this.value$delegate.getValue();
    }

    public final String toString() {
        return "AnimationState(value=" + this.value$delegate.getValue() + ", velocity=" + this.typeConverter.convertFromVector.invoke(this.velocityVector) + ", isRunning=" + this.isRunning + ", lastFrameTimeNanos=" + this.lastFrameTimeNanos + ", finishedTimeNanos=" + this.finishedTimeNanos + ')';
    }
}
