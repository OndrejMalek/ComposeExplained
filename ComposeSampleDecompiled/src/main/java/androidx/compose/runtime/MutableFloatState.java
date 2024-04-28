package androidx.compose.runtime;

import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.snapshots.SnapshotKt;

/* loaded from: classes.dex */
public interface MutableFloatState extends MutableState, State {
    @Override // androidx.compose.runtime.State
    default Object getValue() {
        SnapshotMutableFloatStateImpl snapshotMutableFloatStateImpl = (SnapshotMutableFloatStateImpl) this;
        return Float.valueOf(((SnapshotMutableFloatStateImpl.FloatStateStateRecord) SnapshotKt.readable(snapshotMutableFloatStateImpl.next, snapshotMutableFloatStateImpl)).value);
    }

    @Override // androidx.compose.runtime.MutableState
    default void setValue(Object obj) {
        ((SnapshotMutableFloatStateImpl) this).setFloatValue(((Number) obj).floatValue());
    }
}
