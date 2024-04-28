package androidx.compose.runtime.snapshots;

import androidx.compose.ui.platform.WeakCache;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class Snapshot {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean disposed;
    public int id;
    public SnapshotIdSet invalid;
    public int pinningTrackingHandle;

    public Snapshot(int i, SnapshotIdSet snapshotIdSet) {
        int i2;
        int access$lowestBitOf;
        this.invalid = snapshotIdSet;
        this.id = i;
        if (i != 0) {
            SnapshotIdSet invalid$runtime_release = getInvalid$runtime_release();
            WeakCache weakCache = SnapshotKt.threadSnapshot;
            ResultKt.checkNotNullParameter(invalid$runtime_release, "invalid");
            int[] iArr = invalid$runtime_release.belowBound;
            if (iArr != null) {
                i = iArr[0];
            } else {
                long j = invalid$runtime_release.lowerSet;
                int i3 = invalid$runtime_release.lowerBound;
                if (j != 0) {
                    access$lowestBitOf = ResultKt.access$lowestBitOf(j);
                } else {
                    long j2 = invalid$runtime_release.upperSet;
                    if (j2 != 0) {
                        i3 += 64;
                        access$lowestBitOf = ResultKt.access$lowestBitOf(j2);
                    }
                }
                i = access$lowestBitOf + i3;
            }
            synchronized (SnapshotKt.lock) {
                i2 = SnapshotKt.pinningTable.add(i);
            }
        } else {
            i2 = -1;
        }
        this.pinningTrackingHandle = i2;
    }

    public static void restoreCurrent(Snapshot snapshot) {
        SnapshotKt.threadSnapshot.set(snapshot);
    }

    public final void closeAndReleasePinning$runtime_release() {
        synchronized (SnapshotKt.lock) {
            closeLocked$runtime_release();
            releasePinnedSnapshotsForCloseLocked$runtime_release();
        }
    }

    public void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId());
    }

    public abstract void dispose();

    public int getId() {
        return this.id;
    }

    public SnapshotIdSet getInvalid$runtime_release() {
        return this.invalid;
    }

    public abstract Function1 getReadObserver$runtime_release();

    public abstract boolean getReadOnly();

    public int getWriteCount$runtime_release() {
        return 0;
    }

    public abstract Function1 getWriteObserver$runtime_release();

    public final Snapshot makeCurrent() {
        WeakCache weakCache = SnapshotKt.threadSnapshot;
        Snapshot snapshot = (Snapshot) weakCache.get();
        weakCache.set(this);
        return snapshot;
    }

    public abstract void nestedActivated$runtime_release(Snapshot snapshot);

    public abstract void nestedDeactivated$runtime_release(Snapshot snapshot);

    public abstract void notifyObjectsInitialized$runtime_release();

    public abstract void recordModified$runtime_release(StateObject stateObject);

    public void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        int i = this.pinningTrackingHandle;
        if (i >= 0) {
            SnapshotKt.releasePinningLocked(i);
            this.pinningTrackingHandle = -1;
        }
    }

    public void setId$runtime_release(int i) {
        this.id = i;
    }

    public void setInvalid$runtime_release(SnapshotIdSet snapshotIdSet) {
        ResultKt.checkNotNullParameter(snapshotIdSet, "<set-?>");
        this.invalid = snapshotIdSet;
    }

    public void setWriteCount$runtime_release(int i) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot".toString());
    }

    public abstract Snapshot takeNestedSnapshot(Function1 function1);
}
