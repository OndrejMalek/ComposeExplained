package androidx.compose.runtime.snapshots;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class NestedMutableSnapshot extends MutableSnapshot {
    public boolean deactivated;
    public final MutableSnapshot parent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NestedMutableSnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Function1 function12, MutableSnapshot mutableSnapshot) {
        super(i, snapshotIdSet, function1, function12);
        ResultKt.checkNotNullParameter(snapshotIdSet, "invalid");
        ResultKt.checkNotNullParameter(mutableSnapshot, "parent");
        this.parent = mutableSnapshot;
        mutableSnapshot.nestedActivated$runtime_release(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0066 A[Catch: all -> 0x0050, TryCatch #0 {all -> 0x0050, blocks: (B:11:0x0020, B:13:0x0025, B:16:0x002a, B:21:0x0044, B:23:0x004c, B:24:0x005e, B:26:0x0066, B:27:0x006b, B:29:0x008c, B:30:0x00a2, B:31:0x00ae, B:34:0x00b7, B:35:0x00b8, B:44:0x00c8, B:47:0x00e0, B:48:0x00ce, B:51:0x00f5, B:52:0x00f6, B:53:0x009f, B:54:0x0053, B:55:0x005b, B:33:0x00af), top: B:10:0x0020, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c A[Catch: all -> 0x0050, TryCatch #0 {all -> 0x0050, blocks: (B:11:0x0020, B:13:0x0025, B:16:0x002a, B:21:0x0044, B:23:0x004c, B:24:0x005e, B:26:0x0066, B:27:0x006b, B:29:0x008c, B:30:0x00a2, B:31:0x00ae, B:34:0x00b7, B:35:0x00b8, B:44:0x00c8, B:47:0x00e0, B:48:0x00ce, B:51:0x00f5, B:52:0x00f6, B:53:0x009f, B:54:0x0053, B:55:0x005b, B:33:0x00af), top: B:10:0x0020, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009f A[Catch: all -> 0x0050, TryCatch #0 {all -> 0x0050, blocks: (B:11:0x0020, B:13:0x0025, B:16:0x002a, B:21:0x0044, B:23:0x004c, B:24:0x005e, B:26:0x0066, B:27:0x006b, B:29:0x008c, B:30:0x00a2, B:31:0x00ae, B:34:0x00b7, B:35:0x00b8, B:44:0x00c8, B:47:0x00e0, B:48:0x00ce, B:51:0x00f5, B:52:0x00f6, B:53:0x009f, B:54:0x0053, B:55:0x005b, B:33:0x00af), top: B:10:0x0020, inners: #1 }] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, kotlin.ResultKt] */
    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.ResultKt apply() {
        /*
            r7 = this;
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent
            boolean r1 = r0.applied
            if (r1 != 0) goto Lf9
            boolean r1 = r0.disposed
            if (r1 == 0) goto Lc
            goto Lf9
        Lc:
            androidx.compose.runtime.collection.IdentityArraySet r1 = r7.modified
            int r2 = r7.id
            r3 = 0
            if (r1 == 0) goto L1c
            androidx.compose.runtime.snapshots.SnapshotIdSet r4 = r0.getInvalid$runtime_release()
            java.util.HashMap r0 = androidx.compose.runtime.snapshots.SnapshotKt.access$optimisticMerges(r0, r7, r4)
            goto L1d
        L1c:
            r0 = r3
        L1d:
            java.lang.Object r4 = androidx.compose.runtime.snapshots.SnapshotKt.lock
            monitor-enter(r4)
            androidx.compose.runtime.snapshots.SnapshotKt.access$validateOpen(r7)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L5b
            int r5 = r1.size     // Catch: java.lang.Throwable -> L50
            if (r5 != 0) goto L2a
            goto L5b
        L2a:
            androidx.compose.runtime.snapshots.MutableSnapshot r5 = r7.parent     // Catch: java.lang.Throwable -> L50
            int r5 = r5.getId()     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.MutableSnapshot r6 = r7.parent     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r6 = r6.getInvalid$runtime_release()     // Catch: java.lang.Throwable -> L50
            kotlin.ResultKt r0 = r7.innerApplyLocked$runtime_release(r5, r0, r6)     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r5 = androidx.compose.runtime.snapshots.SnapshotApplyResult$Success.INSTANCE     // Catch: java.lang.Throwable -> L50
            boolean r5 = kotlin.ResultKt.areEqual(r0, r5)     // Catch: java.lang.Throwable -> L50
            if (r5 != 0) goto L44
            monitor-exit(r4)
            return r0
        L44:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.collection.IdentityArraySet r0 = r0.getModified$runtime_release()     // Catch: java.lang.Throwable -> L50
            if (r0 == 0) goto L53
            r0.addAll(r1)     // Catch: java.lang.Throwable -> L50
            goto L5e
        L50:
            r0 = move-exception
            goto Lf7
        L53:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            r0.setModified(r1)     // Catch: java.lang.Throwable -> L50
            r7.modified = r3     // Catch: java.lang.Throwable -> L50
            goto L5e
        L5b:
            r7.closeAndReleasePinning$runtime_release()     // Catch: java.lang.Throwable -> L50
        L5e:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            int r0 = r0.getId()     // Catch: java.lang.Throwable -> L50
            if (r0 >= r2) goto L6b
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            r0.advance$runtime_release()     // Catch: java.lang.Throwable -> L50
        L6b:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r0.getInvalid$runtime_release()     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r1.clear(r2)     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r3 = r7.previousIds     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r1.andNot(r3)     // Catch: java.lang.Throwable -> L50
            r0.setInvalid$runtime_release(r1)     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            r0.recordPrevious$runtime_release(r2)     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            int r1 = r7.pinningTrackingHandle     // Catch: java.lang.Throwable -> L50
            r2 = -1
            r7.pinningTrackingHandle = r2     // Catch: java.lang.Throwable -> L50
            if (r1 < 0) goto L9f
            int[] r2 = r0.previousPinnedSnapshots     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r2, r3)     // Catch: java.lang.Throwable -> L50
            int r3 = r2.length     // Catch: java.lang.Throwable -> L50
            int r5 = r3 + 1
            int[] r2 = java.util.Arrays.copyOf(r2, r5)     // Catch: java.lang.Throwable -> L50
            r2[r3] = r1     // Catch: java.lang.Throwable -> L50
            r0.previousPinnedSnapshots = r2     // Catch: java.lang.Throwable -> L50
            goto La2
        L9f:
            r0.getClass()     // Catch: java.lang.Throwable -> L50
        La2:
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r7.previousIds     // Catch: java.lang.Throwable -> L50
            r0.getClass()     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = "snapshots"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)     // Catch: java.lang.Throwable -> L50
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r0.previousIds     // Catch: java.lang.Throwable -> Lf4
            androidx.compose.runtime.snapshots.SnapshotIdSet r1 = r2.or(r1)     // Catch: java.lang.Throwable -> Lf4
            r0.previousIds = r1     // Catch: java.lang.Throwable -> Lf4
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L50
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent     // Catch: java.lang.Throwable -> L50
            int[] r1 = r7.previousPinnedSnapshots     // Catch: java.lang.Throwable -> L50
            r0.getClass()     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = "handles"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)     // Catch: java.lang.Throwable -> L50
            int r2 = r1.length     // Catch: java.lang.Throwable -> L50
            if (r2 != 0) goto Lc8
            goto Le2
        Lc8:
            int[] r2 = r0.previousPinnedSnapshots     // Catch: java.lang.Throwable -> L50
            int r3 = r2.length     // Catch: java.lang.Throwable -> L50
            if (r3 != 0) goto Lce
            goto Le0
        Lce:
            int r3 = r2.length     // Catch: java.lang.Throwable -> L50
            int r5 = r1.length     // Catch: java.lang.Throwable -> L50
            int r6 = r3 + r5
            int[] r2 = java.util.Arrays.copyOf(r2, r6)     // Catch: java.lang.Throwable -> L50
            r6 = 0
            java.lang.System.arraycopy(r1, r6, r2, r3, r5)     // Catch: java.lang.Throwable -> L50
            java.lang.String r1 = "result"
            kotlin.ResultKt.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Throwable -> L50
            r1 = r2
        Le0:
            r0.previousPinnedSnapshots = r1     // Catch: java.lang.Throwable -> L50
        Le2:
            monitor-exit(r4)
            r0 = 1
            r7.applied = r0
            boolean r1 = r7.deactivated
            if (r1 != 0) goto Lf1
            r7.deactivated = r0
            androidx.compose.runtime.snapshots.MutableSnapshot r0 = r7.parent
            r0.nestedDeactivated$runtime_release(r7)
        Lf1:
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r0 = androidx.compose.runtime.snapshots.SnapshotApplyResult$Success.INSTANCE
            return r0
        Lf4:
            r0 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L50
            throw r0     // Catch: java.lang.Throwable -> L50
        Lf7:
            monitor-exit(r4)
            throw r0
        Lf9:
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure r0 = new androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.NestedMutableSnapshot.apply():kotlin.ResultKt");
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void dispose() {
        if (this.disposed) {
            return;
        }
        super.dispose();
        if (this.deactivated) {
            return;
        }
        this.deactivated = true;
        this.parent.nestedDeactivated$runtime_release(this);
    }
}
