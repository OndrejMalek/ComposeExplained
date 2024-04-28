package androidx.compose.runtime.snapshots;

import androidx.compose.ui.platform.WeakCache;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class NestedReadonlySnapshot extends Snapshot {
    public final Snapshot parent;
    public final Function1 readObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NestedReadonlySnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Snapshot snapshot) {
        super(i, snapshotIdSet);
        ResultKt.checkNotNullParameter(snapshotIdSet, "invalid");
        ResultKt.checkNotNullParameter(snapshot, "parent");
        this.parent = snapshot;
        snapshot.nestedActivated$runtime_release(this);
        if (function1 != null) {
            Function1 readObserver$runtime_release = snapshot.getReadObserver$runtime_release();
            if (readObserver$runtime_release != null) {
                function1 = new SnapshotKt$mergedReadObserver$1(function1, readObserver$runtime_release, 2);
            }
        } else {
            function1 = snapshot.getReadObserver$runtime_release();
        }
        this.readObserver = function1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void dispose() {
        if (this.disposed) {
            return;
        }
        int i = this.id;
        Snapshot snapshot = this.parent;
        if (i != snapshot.getId()) {
            closeAndReleasePinning$runtime_release();
        }
        snapshot.nestedDeactivated$runtime_release(this);
        this.disposed = true;
        synchronized (SnapshotKt.lock) {
            int i2 = this.pinningTrackingHandle;
            if (i2 >= 0) {
                SnapshotKt.releasePinningLocked(i2);
                this.pinningTrackingHandle = -1;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getReadObserver$runtime_release() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final boolean getReadOnly() {
        return true;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getWriteObserver$runtime_release() {
        return null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void nestedActivated$runtime_release(Snapshot snapshot) {
        ResultKt.checkNotNullParameter(snapshot, "snapshot");
        SnapshotStateMapKt.unsupported();
        throw null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void nestedDeactivated$runtime_release(Snapshot snapshot) {
        ResultKt.checkNotNullParameter(snapshot, "snapshot");
        SnapshotStateMapKt.unsupported();
        throw null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void notifyObjectsInitialized$runtime_release() {
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void recordModified$runtime_release(StateObject stateObject) {
        ResultKt.checkNotNullParameter(stateObject, "state");
        WeakCache weakCache = SnapshotKt.threadSnapshot;
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot".toString());
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Snapshot takeNestedSnapshot(Function1 function1) {
        return new NestedReadonlySnapshot(this.id, this.invalid, function1, this.parent);
    }
}
