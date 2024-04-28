package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class SnapshotMutableStateImpl implements StateObject, SnapshotMutableState {
    public StateStateRecord next;
    public final SnapshotMutationPolicy policy;

    /* loaded from: classes.dex */
    public final class StateStateRecord extends StateRecord {
        public Object value;

        public StateStateRecord(Object obj) {
            this.value = obj;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            ResultKt.checkNotNullParameter(stateRecord, "value");
            this.value = ((StateStateRecord) stateRecord).value;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateStateRecord(this.value);
        }
    }

    public SnapshotMutableStateImpl(Object obj, SnapshotMutationPolicy snapshotMutationPolicy) {
        ResultKt.checkNotNullParameter(snapshotMutationPolicy, "policy");
        this.policy = snapshotMutationPolicy;
        this.next = new StateStateRecord(obj);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.next;
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotMutableState
    public final SnapshotMutationPolicy getPolicy() {
        return this.policy;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return ((StateStateRecord) SnapshotKt.readable(this.next, this)).value;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        if (this.policy.equivalent(((StateStateRecord) stateRecord2).value, ((StateStateRecord) stateRecord3).value)) {
            return stateRecord2;
        }
        return null;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.next = (StateStateRecord) stateRecord;
    }

    @Override // androidx.compose.runtime.MutableState
    public final void setValue(Object obj) {
        Snapshot currentSnapshot;
        StateStateRecord stateStateRecord = (StateStateRecord) SnapshotKt.current(this.next);
        if (this.policy.equivalent(stateStateRecord.value, obj)) {
            return;
        }
        StateStateRecord stateStateRecord2 = this.next;
        synchronized (SnapshotKt.lock) {
            currentSnapshot = SnapshotKt.currentSnapshot();
            ((StateStateRecord) SnapshotKt.overwritableRecord(stateStateRecord2, this, currentSnapshot, stateStateRecord)).value = obj;
        }
        SnapshotKt.notifyWrite(currentSnapshot, this);
    }

    public final String toString() {
        return "MutableState(value=" + ((StateStateRecord) SnapshotKt.current(this.next)).value + ")@" + hashCode();
    }
}
