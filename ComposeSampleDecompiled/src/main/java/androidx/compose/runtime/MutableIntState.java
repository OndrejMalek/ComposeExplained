package androidx.compose.runtime;

import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.snapshots.SnapshotKt;

/* loaded from: classes.dex */
public interface MutableIntState extends MutableState, State {
    @Override // androidx.compose.runtime.State
    default Object getValue() {
        SnapshotMutableIntStateImpl snapshotMutableIntStateImpl = (SnapshotMutableIntStateImpl) this;
        return Integer.valueOf(((SnapshotMutableIntStateImpl.IntStateStateRecord) SnapshotKt.readable(snapshotMutableIntStateImpl.next, snapshotMutableIntStateImpl)).value);
    }

    @Override // androidx.compose.runtime.MutableState
    default void setValue(Object obj) {
        ((SnapshotMutableIntStateImpl) this).setIntValue(((Number) obj).intValue());
    }
}
