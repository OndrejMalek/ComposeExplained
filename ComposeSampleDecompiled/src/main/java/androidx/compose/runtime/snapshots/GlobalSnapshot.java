package androidx.compose.runtime.snapshots;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.ExceptionsConstructorKt$safeCtor$1;

/* loaded from: classes.dex */
public final class GlobalSnapshot extends MutableSnapshot {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GlobalSnapshot(int r6, androidx.compose.runtime.snapshots.SnapshotIdSet r7) {
        /*
            r5 = this;
            java.lang.String r0 = "invalid"
            kotlin.ResultKt.checkNotNullParameter(r7, r0)
            java.lang.Object r0 = androidx.compose.runtime.snapshots.SnapshotKt.lock
            monitor-enter(r0)
            java.util.ArrayList r1 = androidx.compose.runtime.snapshots.SnapshotKt.globalWriteObservers     // Catch: java.lang.Throwable -> L18
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L18
            r3 = 1
            r2 = r2 ^ r3
            r4 = 0
            if (r2 == 0) goto L1a
            java.util.ArrayList r1 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(r1)     // Catch: java.lang.Throwable -> L18
            goto L1b
        L18:
            r6 = move-exception
            goto L3a
        L1a:
            r1 = r4
        L1b:
            if (r1 == 0) goto L34
            int r2 = r1.size()     // Catch: java.lang.Throwable -> L18
            if (r2 != r3) goto L29
            r2 = 0
            java.lang.Object r2 = r1.get(r2)     // Catch: java.lang.Throwable -> L18
            goto L2a
        L29:
            r2 = r4
        L2a:
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch: java.lang.Throwable -> L18
            if (r2 != 0) goto L35
            androidx.compose.ui.layout.RootMeasurePolicy$measure$4 r2 = new androidx.compose.ui.layout.RootMeasurePolicy$measure$4     // Catch: java.lang.Throwable -> L18
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L18
            goto L35
        L34:
            r2 = r4
        L35:
            monitor-exit(r0)
            r5.<init>(r6, r7, r4, r2)
            return
        L3a:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.GlobalSnapshot.<init>(int, androidx.compose.runtime.snapshots.SnapshotIdSet):void");
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public final ResultKt apply() {
        throw new IllegalStateException("Cannot apply the global snapshot directly. Call Snapshot.advanceGlobalSnapshot".toString());
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void dispose() {
        synchronized (SnapshotKt.lock) {
            int i = this.pinningTrackingHandle;
            if (i >= 0) {
                SnapshotKt.releasePinningLocked(i);
                this.pinningTrackingHandle = -1;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void nestedActivated$runtime_release(Snapshot snapshot) {
        ResultKt.checkNotNullParameter(snapshot, "snapshot");
        SnapshotStateMapKt.unsupported();
        throw null;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void nestedDeactivated$runtime_release(Snapshot snapshot) {
        ResultKt.checkNotNullParameter(snapshot, "snapshot");
        SnapshotStateMapKt.unsupported();
        throw null;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void notifyObjectsInitialized$runtime_release() {
        SnapshotKt.advanceGlobalSnapshot(SnapshotKt$emptyLambda$1.INSTANCE$1);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public final MutableSnapshot takeNestedMutableSnapshot(Function1 function1, Function1 function12) {
        return (MutableSnapshot) ((Snapshot) SnapshotKt.advanceGlobalSnapshot(new ExceptionsConstructorKt$safeCtor$1(3, new SnapshotKt$mergedReadObserver$1(function1, function12, 1))));
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final Snapshot takeNestedSnapshot(Function1 function1) {
        return (Snapshot) SnapshotKt.advanceGlobalSnapshot(new ExceptionsConstructorKt$safeCtor$1(3, new ExceptionsConstructorKt$safeCtor$1(2, function1)));
    }
}
