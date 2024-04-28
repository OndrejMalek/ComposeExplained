package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.collection.IdentityArraySet;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public class MutableSnapshot extends Snapshot {
    public static final int[] EmptyIntArray = new int[0];
    public boolean applied;
    public ArrayList merged;
    public IdentityArraySet modified;
    public SnapshotIdSet previousIds;
    public int[] previousPinnedSnapshots;
    public final Function1 readObserver;
    public int snapshots;
    public int writeCount;
    public final Function1 writeObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableSnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Function1 function12) {
        super(i, snapshotIdSet);
        ResultKt.checkNotNullParameter(snapshotIdSet, "invalid");
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.EMPTY;
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    public final void advance$runtime_release() {
        recordPrevious$runtime_release(getId());
        if (this.applied || this.disposed) {
            return;
        }
        int id = getId();
        synchronized (SnapshotKt.lock) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            setId$runtime_release(i);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(id + 1, getId(), getInvalid$runtime_release()));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ad A[LOOP:0: B:24:0x00ab->B:25:0x00ad, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c9 A[LOOP:1: B:31:0x00c7->B:32:0x00c9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public kotlin.ResultKt apply() {
        /*
            r9 = this;
            androidx.compose.runtime.collection.IdentityArraySet r0 = r9.getModified$runtime_release()
            r1 = 0
            if (r0 == 0) goto L27
            java.util.concurrent.atomic.AtomicReference r2 = androidx.compose.runtime.snapshots.SnapshotKt.currentGlobalSnapshot
            java.lang.Object r3 = r2.get()
            java.lang.String r4 = "currentGlobalSnapshot.get()"
            kotlin.ResultKt.checkNotNullExpressionValue(r3, r4)
            androidx.compose.runtime.snapshots.MutableSnapshot r3 = (androidx.compose.runtime.snapshots.MutableSnapshot) r3
            androidx.compose.runtime.snapshots.SnapshotIdSet r4 = androidx.compose.runtime.snapshots.SnapshotKt.openSnapshots
            java.lang.Object r2 = r2.get()
            androidx.compose.runtime.snapshots.GlobalSnapshot r2 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r2
            int r2 = r2.id
            androidx.compose.runtime.snapshots.SnapshotIdSet r2 = r4.clear(r2)
            java.util.HashMap r2 = androidx.compose.runtime.snapshots.SnapshotKt.access$optimisticMerges(r3, r9, r2)
            goto L28
        L27:
            r2 = r1
        L28:
            kotlin.collections.EmptyList r3 = kotlin.collections.EmptyList.INSTANCE
            java.lang.Object r4 = androidx.compose.runtime.snapshots.SnapshotKt.lock
            monitor-enter(r4)
            androidx.compose.runtime.snapshots.SnapshotKt.access$validateOpen(r9)     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L70
            int r5 = r0.size     // Catch: java.lang.Throwable -> L6d
            if (r5 != 0) goto L37
            goto L70
        L37:
            java.util.concurrent.atomic.AtomicReference r3 = androidx.compose.runtime.snapshots.SnapshotKt.currentGlobalSnapshot     // Catch: java.lang.Throwable -> L6d
            java.lang.Object r3 = r3.get()     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.GlobalSnapshot r3 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r3     // Catch: java.lang.Throwable -> L6d
            int r5 = androidx.compose.runtime.snapshots.SnapshotKt.nextSnapshotId     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotIdSet r6 = androidx.compose.runtime.snapshots.SnapshotKt.openSnapshots     // Catch: java.lang.Throwable -> L6d
            int r7 = r3.id     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotIdSet r6 = r6.clear(r7)     // Catch: java.lang.Throwable -> L6d
            kotlin.ResultKt r2 = r9.innerApplyLocked$runtime_release(r5, r2, r6)     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r5 = androidx.compose.runtime.snapshots.SnapshotApplyResult$Success.INSTANCE     // Catch: java.lang.Throwable -> L6d
            boolean r5 = kotlin.ResultKt.areEqual(r2, r5)     // Catch: java.lang.Throwable -> L6d
            if (r5 != 0) goto L57
            monitor-exit(r4)
            return r2
        L57:
            r9.closeLocked$runtime_release()     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1 r2 = androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1.INSTANCE     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotKt.takeNewGlobalSnapshot(r3, r2)     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.collection.IdentityArraySet r2 = r3.modified     // Catch: java.lang.Throwable -> L6d
            r9.setModified(r1)     // Catch: java.lang.Throwable -> L6d
            r3.modified = r1     // Catch: java.lang.Throwable -> L6d
            java.util.ArrayList r3 = androidx.compose.runtime.snapshots.SnapshotKt.applyObservers     // Catch: java.lang.Throwable -> L6d
            java.util.ArrayList r3 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(r3)     // Catch: java.lang.Throwable -> L6d
            goto L98
        L6d:
            r0 = move-exception
            goto L12e
        L70:
            r9.closeLocked$runtime_release()     // Catch: java.lang.Throwable -> L6d
            java.util.concurrent.atomic.AtomicReference r2 = androidx.compose.runtime.snapshots.SnapshotKt.currentGlobalSnapshot     // Catch: java.lang.Throwable -> L6d
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.GlobalSnapshot r2 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r2     // Catch: java.lang.Throwable -> L6d
            java.lang.String r5 = "previousGlobalSnapshot"
            kotlin.ResultKt.checkNotNullExpressionValue(r2, r5)     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1 r5 = androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1.INSTANCE     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.snapshots.SnapshotKt.takeNewGlobalSnapshot(r2, r5)     // Catch: java.lang.Throwable -> L6d
            androidx.compose.runtime.collection.IdentityArraySet r2 = r2.modified     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L97
            boolean r5 = r2.isEmpty()     // Catch: java.lang.Throwable -> L6d
            if (r5 == 0) goto L90
            goto L97
        L90:
            java.util.ArrayList r3 = androidx.compose.runtime.snapshots.SnapshotKt.applyObservers     // Catch: java.lang.Throwable -> L6d
            java.util.ArrayList r3 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(r3)     // Catch: java.lang.Throwable -> L6d
            goto L98
        L97:
            r2 = r1
        L98:
            monitor-exit(r4)
            r4 = 1
            r9.applied = r4
            r4 = 0
            if (r2 == 0) goto Lb9
            boolean r5 = r2.isEmpty()
            if (r5 == 0) goto La6
            goto Lb9
        La6:
            int r5 = r3.size()
            r6 = r4
        Lab:
            if (r6 >= r5) goto Lb9
            java.lang.Object r7 = r3.get(r6)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r7.invoke(r2, r9)
            int r6 = r6 + 1
            goto Lab
        Lb9:
            if (r0 == 0) goto Ld5
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto Lc2
            goto Ld5
        Lc2:
            int r5 = r3.size()
            r6 = r4
        Lc7:
            if (r6 >= r5) goto Ld5
            java.lang.Object r7 = r3.get(r6)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r7.invoke(r0, r9)
            int r6 = r6 + 1
            goto Lc7
        Ld5:
            java.lang.Object r3 = androidx.compose.runtime.snapshots.SnapshotKt.lock
            monitor-enter(r3)
            r9.releasePinnedSnapshotsForCloseLocked$runtime_release()     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.SnapshotKt.checkAndOverwriteUnusedRecordsLocked()     // Catch: java.lang.Throwable -> Lf6
            if (r2 == 0) goto Lf8
            java.lang.Object[] r5 = r2.values     // Catch: java.lang.Throwable -> Lf6
            int r2 = r2.size     // Catch: java.lang.Throwable -> Lf6
            r6 = r4
        Le5:
            if (r6 >= r2) goto Lf8
            r7 = r5[r6]     // Catch: java.lang.Throwable -> Lf6
            java.lang.String r8 = "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet"
            kotlin.ResultKt.checkNotNull(r7, r8)     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.StateObject r7 = (androidx.compose.runtime.snapshots.StateObject) r7     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.SnapshotKt.processForUnusedRecordsLocked(r7)     // Catch: java.lang.Throwable -> Lf6
            int r6 = r6 + 1
            goto Le5
        Lf6:
            r0 = move-exception
            goto L12c
        Lf8:
            if (r0 == 0) goto L110
            java.lang.Object[] r2 = r0.values     // Catch: java.lang.Throwable -> Lf6
            int r0 = r0.size     // Catch: java.lang.Throwable -> Lf6
            r5 = r4
        Lff:
            if (r5 >= r0) goto L110
            r6 = r2[r5]     // Catch: java.lang.Throwable -> Lf6
            java.lang.String r7 = "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet"
            kotlin.ResultKt.checkNotNull(r6, r7)     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.StateObject r6 = (androidx.compose.runtime.snapshots.StateObject) r6     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.SnapshotKt.processForUnusedRecordsLocked(r6)     // Catch: java.lang.Throwable -> Lf6
            int r5 = r5 + 1
            goto Lff
        L110:
            java.util.ArrayList r0 = r9.merged     // Catch: java.lang.Throwable -> Lf6
            if (r0 == 0) goto L126
            int r2 = r0.size()     // Catch: java.lang.Throwable -> Lf6
        L118:
            if (r4 >= r2) goto L126
            java.lang.Object r5 = r0.get(r4)     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.StateObject r5 = (androidx.compose.runtime.snapshots.StateObject) r5     // Catch: java.lang.Throwable -> Lf6
            androidx.compose.runtime.snapshots.SnapshotKt.processForUnusedRecordsLocked(r5)     // Catch: java.lang.Throwable -> Lf6
            int r4 = r4 + 1
            goto L118
        L126:
            r9.merged = r1     // Catch: java.lang.Throwable -> Lf6
            monitor-exit(r3)
            androidx.compose.runtime.snapshots.SnapshotApplyResult$Success r0 = androidx.compose.runtime.snapshots.SnapshotApplyResult$Success.INSTANCE
            return r0
        L12c:
            monitor-exit(r3)
            throw r0
        L12e:
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.apply():kotlin.ResultKt");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        synchronized (SnapshotKt.lock) {
            int i = this.pinningTrackingHandle;
            if (i >= 0) {
                SnapshotKt.releasePinningLocked(i);
                this.pinningTrackingHandle = -1;
            }
        }
        nestedDeactivated$runtime_release(this);
    }

    public IdentityArraySet getModified$runtime_release() {
        return this.modified;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getReadObserver$runtime_release() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public int getWriteCount$runtime_release() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, kotlin.ResultKt] */
    public final ResultKt innerApplyLocked$runtime_release(int i, HashMap hashMap, SnapshotIdSet snapshotIdSet) {
        StateRecord readable;
        StateRecord mergeRecords;
        SnapshotIdSet snapshotIdSet2 = snapshotIdSet;
        ResultKt.checkNotNullParameter(snapshotIdSet2, "invalidSnapshots");
        SnapshotIdSet or = getInvalid$runtime_release().set(getId()).or(this.previousIds);
        IdentityArraySet modified$runtime_release = getModified$runtime_release();
        ResultKt.checkNotNull(modified$runtime_release);
        Object[] objArr = modified$runtime_release.values;
        int i2 = modified$runtime_release.size;
        int i3 = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        while (i3 < i2) {
            Object obj = objArr[i3];
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
            StateObject stateObject = (StateObject) obj;
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord readable2 = SnapshotKt.readable(firstStateRecord, i, snapshotIdSet2);
            if (readable2 != null && (readable = SnapshotKt.readable(firstStateRecord, getId(), or)) != null && !ResultKt.areEqual(readable2, readable)) {
                StateRecord readable3 = SnapshotKt.readable(firstStateRecord, getId(), getInvalid$runtime_release());
                if (readable3 == null) {
                    SnapshotKt.readError();
                    throw null;
                }
                if (hashMap == null || (mergeRecords = (StateRecord) hashMap.get(readable2)) == null) {
                    mergeRecords = stateObject.mergeRecords(readable, readable2, readable3);
                }
                if (mergeRecords == null) {
                    return new Object();
                }
                if (!ResultKt.areEqual(mergeRecords, readable3)) {
                    if (ResultKt.areEqual(mergeRecords, readable2)) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(new Pair(stateObject, readable2.create()));
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(stateObject);
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(!ResultKt.areEqual(mergeRecords, readable) ? new Pair(stateObject, mergeRecords) : new Pair(stateObject, readable.create()));
                    }
                }
            }
            i3++;
            snapshotIdSet2 = snapshotIdSet;
        }
        if (arrayList != null) {
            advance$runtime_release();
            int size = arrayList.size();
            for (int i4 = 0; i4 < size; i4++) {
                Pair pair = (Pair) arrayList.get(i4);
                StateObject stateObject2 = (StateObject) pair.first;
                StateRecord stateRecord = (StateRecord) pair.second;
                stateRecord.snapshotId = getId();
                synchronized (SnapshotKt.lock) {
                    stateRecord.next = stateObject2.getFirstStateRecord();
                    stateObject2.prependStateRecord(stateRecord);
                }
            }
        }
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i5 = 0; i5 < size2; i5++) {
                modified$runtime_release.remove((StateObject) arrayList2.get(i5));
            }
            ArrayList arrayList3 = this.merged;
            if (arrayList3 != null) {
                arrayList2 = CollectionsKt___CollectionsKt.plus(arrayList2, arrayList3);
            }
            this.merged = arrayList2;
        }
        return SnapshotApplyResult$Success.INSTANCE;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void nestedActivated$runtime_release(Snapshot snapshot) {
        ResultKt.checkNotNullParameter(snapshot, "snapshot");
        this.snapshots++;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0090, code lost:
    
        continue;
     */
    @Override // androidx.compose.runtime.snapshots.Snapshot
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void nestedDeactivated$runtime_release(androidx.compose.runtime.snapshots.Snapshot r11) {
        /*
            r10 = this;
            java.lang.String r0 = "snapshot"
            kotlin.ResultKt.checkNotNullParameter(r11, r0)
            int r11 = r10.snapshots
            if (r11 <= 0) goto La6
            int r11 = r11 + (-1)
            r10.snapshots = r11
            if (r11 != 0) goto La5
            boolean r11 = r10.applied
            if (r11 != 0) goto La5
            androidx.compose.runtime.collection.IdentityArraySet r11 = r10.getModified$runtime_release()
            if (r11 == 0) goto La2
            boolean r0 = r10.applied
            r0 = r0 ^ 1
            if (r0 == 0) goto L96
            r0 = 0
            r10.setModified(r0)
            int r0 = r10.getId()
            java.lang.Object[] r1 = r11.values
            int r11 = r11.size
            r2 = 0
            r3 = r2
        L2d:
            if (r3 >= r11) goto La2
            r4 = r1[r3]
            java.lang.String r5 = "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet"
            kotlin.ResultKt.checkNotNull(r4, r5)
            androidx.compose.runtime.snapshots.StateObject r4 = (androidx.compose.runtime.snapshots.StateObject) r4
            androidx.compose.runtime.snapshots.StateRecord r4 = r4.getFirstStateRecord()
        L3c:
            if (r4 == 0) goto L93
            int r5 = r4.snapshotId
            if (r5 == r0) goto L8e
            androidx.compose.runtime.snapshots.SnapshotIdSet r6 = r10.previousIds
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r7 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r6, r7)
            boolean r7 = r6 instanceof java.util.Collection
            if (r7 == 0) goto L5a
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L90
            goto L8e
        L5a:
            boolean r7 = r6 instanceof java.util.List
            if (r7 == 0) goto L65
            java.util.List r6 = (java.util.List) r6
            int r5 = r6.indexOf(r5)
            goto L80
        L65:
            java.util.Iterator r6 = r6.iterator()
            r7 = r2
        L6a:
            r8 = r6
            kotlin.sequences.SequenceBuilderIterator r8 = (kotlin.sequences.SequenceBuilderIterator) r8
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L90
            java.lang.Object r8 = r8.next()
            if (r7 < 0) goto L86
            boolean r8 = kotlin.ResultKt.areEqual(r5, r8)
            if (r8 == 0) goto L83
            r5 = r7
        L80:
            if (r5 < 0) goto L90
            goto L8e
        L83:
            int r7 = r7 + 1
            goto L6a
        L86:
            java.lang.ArithmeticException r11 = new java.lang.ArithmeticException
            java.lang.String r0 = "Index overflow has happened."
            r11.<init>(r0)
            throw r11
        L8e:
            r4.snapshotId = r2
        L90:
            androidx.compose.runtime.snapshots.StateRecord r4 = r4.next
            goto L3c
        L93:
            int r3 = r3 + 1
            goto L2d
        L96:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unsupported operation on a snapshot that has been applied"
            java.lang.String r0 = r0.toString()
            r11.<init>(r0)
            throw r11
        La2:
            r10.closeAndReleasePinning$runtime_release()
        La5:
            return
        La6:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Failed requirement."
            java.lang.String r0 = r0.toString()
            r11.<init>(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.nestedDeactivated$runtime_release(androidx.compose.runtime.snapshots.Snapshot):void");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (this.applied || this.disposed) {
            return;
        }
        advance$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void recordModified$runtime_release(StateObject stateObject) {
        ResultKt.checkNotNullParameter(stateObject, "state");
        IdentityArraySet modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release == null) {
            modified$runtime_release = new IdentityArraySet();
            setModified(modified$runtime_release);
        }
        modified$runtime_release.add(stateObject);
    }

    public final void recordPrevious$runtime_release(int i) {
        synchronized (SnapshotKt.lock) {
            this.previousIds = this.previousIds.set(i);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
        int i2 = this.pinningTrackingHandle;
        if (i2 >= 0) {
            SnapshotKt.releasePinningLocked(i2);
            this.pinningTrackingHandle = -1;
        }
    }

    public void setModified(IdentityArraySet identityArraySet) {
        this.modified = identityArraySet;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime_release(int i) {
        this.writeCount = i;
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1 function1, Function1 function12) {
        NestedMutableSnapshot nestedMutableSnapshot;
        if (!(!this.disposed)) {
            throw new IllegalArgumentException("Cannot use a disposed snapshot".toString());
        }
        if (this.applied && this.pinningTrackingHandle < 0) {
            throw new IllegalStateException("Unsupported operation on a disposed or applied snapshot".toString());
        }
        recordPrevious$runtime_release(getId());
        Object obj = SnapshotKt.lock;
        synchronized (obj) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            SnapshotIdSet invalid$runtime_release = getInvalid$runtime_release();
            setInvalid$runtime_release(invalid$runtime_release.set(i));
            nestedMutableSnapshot = new NestedMutableSnapshot(i, SnapshotKt.addRange(getId() + 1, i, invalid$runtime_release), SnapshotKt.mergedReadObserver(function1, this.readObserver, true), SnapshotKt.access$mergedWriteObserver(function12, this.writeObserver), this);
        }
        if (!this.applied && !this.disposed) {
            int id = getId();
            synchronized (obj) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(id + 1, getId(), getInvalid$runtime_release()));
        }
        return nestedMutableSnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1 function1) {
        NestedReadonlySnapshot nestedReadonlySnapshot;
        if (!(!this.disposed)) {
            throw new IllegalArgumentException("Cannot use a disposed snapshot".toString());
        }
        if (this.applied && this.pinningTrackingHandle < 0) {
            throw new IllegalStateException("Unsupported operation on a disposed or applied snapshot".toString());
        }
        int id = getId();
        recordPrevious$runtime_release(getId());
        Object obj = SnapshotKt.lock;
        synchronized (obj) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i, SnapshotKt.addRange(id + 1, i, getInvalid$runtime_release()), function1, this);
        }
        if (!this.applied && !this.disposed) {
            int id2 = getId();
            synchronized (obj) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(id2 + 1, getId(), getInvalid$runtime_release()));
        }
        return nestedReadonlySnapshot;
    }
}
