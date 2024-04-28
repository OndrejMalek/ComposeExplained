package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.Stack;
import androidx.compose.runtime.WeakReference;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.ui.platform.WeakCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public abstract class SnapshotKt {
    public static final ArrayList applyObservers;
    public static final AtomicReference currentGlobalSnapshot;
    public static final SnapshotWeakSet extraStateObjects;
    public static final ArrayList globalWriteObservers;
    public static int nextSnapshotId;
    public static SnapshotIdSet openSnapshots;
    public static final Stack pendingApplyObserverCount;
    public static final SnapshotDoubleIndexHeap pinningTable;
    public static final Snapshot snapshotInitializer;
    public static final WeakCache threadSnapshot = new WeakCache(2);
    public static final Object lock = new Object();

    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.runtime.snapshots.SnapshotDoubleIndexHeap, java.lang.Object] */
    static {
        SnapshotIdSet snapshotIdSet = SnapshotIdSet.EMPTY;
        openSnapshots = snapshotIdSet;
        nextSnapshotId = 1;
        ?? obj = new Object();
        obj.values = new int[16];
        obj.index = new int[16];
        int[] iArr = new int[16];
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            iArr[i] = i2;
            i = i2;
        }
        obj.handles = iArr;
        pinningTable = obj;
        extraStateObjects = new SnapshotWeakSet();
        applyObservers = new ArrayList();
        globalWriteObservers = new ArrayList();
        int i3 = nextSnapshotId;
        nextSnapshotId = i3 + 1;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i3, snapshotIdSet);
        openSnapshots = openSnapshots.set(globalSnapshot.id);
        AtomicReference atomicReference = new AtomicReference(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        Object obj2 = atomicReference.get();
        ResultKt.checkNotNullExpressionValue(obj2, "currentGlobalSnapshot.get()");
        snapshotInitializer = (Snapshot) obj2;
        pendingApplyObserverCount = new Stack(0, 1);
    }

    public static final Function1 access$mergedWriteObserver(Function1 function1, Function1 function12) {
        return (function1 == null || function12 == null || ResultKt.areEqual(function1, function12)) ? function1 == null ? function12 : function1 : new SnapshotKt$mergedReadObserver$1(function1, function12, 3);
    }

    public static final HashMap access$optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        StateRecord readable;
        IdentityArraySet modified$runtime_release = mutableSnapshot2.getModified$runtime_release();
        int id = mutableSnapshot.getId();
        if (modified$runtime_release == null) {
            return null;
        }
        SnapshotIdSet or = mutableSnapshot2.getInvalid$runtime_release().set(mutableSnapshot2.getId()).or(mutableSnapshot2.previousIds);
        Object[] objArr = modified$runtime_release.values;
        int i = modified$runtime_release.size;
        HashMap hashMap = null;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[i2];
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
            StateObject stateObject = (StateObject) obj;
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord readable2 = readable(firstStateRecord, id, snapshotIdSet);
            if (readable2 != null && (readable = readable(firstStateRecord, id, or)) != null && !ResultKt.areEqual(readable2, readable)) {
                StateRecord readable3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid$runtime_release());
                if (readable3 == null) {
                    readError();
                    throw null;
                }
                StateRecord mergeRecords = stateObject.mergeRecords(readable, readable2, readable3);
                if (mergeRecords == null) {
                    return null;
                }
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(readable2, mergeRecords);
                hashMap = hashMap;
            }
        }
        return hashMap;
    }

    public static final void access$validateOpen(Snapshot snapshot) {
        if (!openSnapshots.get(snapshot.getId())) {
            throw new IllegalStateException("Snapshot is not open".toString());
        }
    }

    public static final SnapshotIdSet addRange(int i, int i2, SnapshotIdSet snapshotIdSet) {
        ResultKt.checkNotNullParameter(snapshotIdSet, "<this>");
        while (i < i2) {
            snapshotIdSet = snapshotIdSet.set(i);
            i++;
        }
        return snapshotIdSet;
    }

    public static final Object advanceGlobalSnapshot(Function1 function1) {
        Object obj;
        IdentityArraySet identityArraySet;
        Object takeNewGlobalSnapshot;
        ArrayList mutableList;
        Snapshot snapshot = snapshotInitializer;
        ResultKt.checkNotNull(snapshot, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.GlobalSnapshot");
        Object obj2 = lock;
        synchronized (obj2) {
            obj = currentGlobalSnapshot.get();
            ResultKt.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
            identityArraySet = ((GlobalSnapshot) obj).modified;
            if (identityArraySet != null) {
                ((AtomicInteger) pendingApplyObserverCount.backing).addAndGet(1);
            }
            takeNewGlobalSnapshot = takeNewGlobalSnapshot((Snapshot) obj, function1);
        }
        if (identityArraySet != null) {
            try {
                synchronized (obj2) {
                    mutableList = CollectionsKt___CollectionsKt.toMutableList(applyObservers);
                }
                int size = mutableList.size();
                for (int i = 0; i < size; i++) {
                    ((Function2) mutableList.get(i)).invoke(identityArraySet, obj);
                }
            } finally {
                ((AtomicInteger) pendingApplyObserverCount.backing).addAndGet(-1);
            }
        }
        synchronized (lock) {
            checkAndOverwriteUnusedRecordsLocked();
            if (identityArraySet != null) {
                Object[] objArr = identityArraySet.values;
                int i2 = identityArraySet.size;
                for (int i3 = 0; i3 < i2; i3++) {
                    Object obj3 = objArr[i3];
                    ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                    processForUnusedRecordsLocked((StateObject) obj3);
                }
            }
        }
        return takeNewGlobalSnapshot;
    }

    public static final void checkAndOverwriteUnusedRecordsLocked() {
        SnapshotWeakSet snapshotWeakSet = extraStateObjects;
        int i = snapshotWeakSet.size;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            WeakReference weakReference = ((WeakReference[]) snapshotWeakSet.values)[i2];
            if ((weakReference != null ? weakReference.get() : null) != null && !(!overwriteUnusedRecordsLocked((StateObject) r5))) {
                if (i3 != i2) {
                    ((WeakReference[]) snapshotWeakSet.values)[i3] = weakReference;
                    Object obj = snapshotWeakSet.hashes;
                    ((int[]) obj)[i3] = ((int[]) obj)[i2];
                }
                i3++;
            }
            i2++;
        }
        for (int i4 = i3; i4 < i; i4++) {
            ((WeakReference[]) snapshotWeakSet.values)[i4] = null;
            ((int[]) snapshotWeakSet.hashes)[i4] = 0;
        }
        if (i3 != i) {
            snapshotWeakSet.size = i3;
        }
    }

    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, Function1 function1, boolean z) {
        boolean z2 = snapshot instanceof MutableSnapshot;
        if (z2 || snapshot == null) {
            return new TransparentObserverMutableSnapshot(z2 ? (MutableSnapshot) snapshot : null, function1, null, false, z);
        }
        return new TransparentObserverSnapshot(snapshot, function1, z);
    }

    public static final StateRecord current(StateRecord stateRecord) {
        StateRecord readable;
        ResultKt.checkNotNullParameter(stateRecord, "r");
        Snapshot currentSnapshot = currentSnapshot();
        StateRecord readable2 = readable(stateRecord, currentSnapshot.getId(), currentSnapshot.getInvalid$runtime_release());
        if (readable2 != null) {
            return readable2;
        }
        synchronized (lock) {
            Snapshot currentSnapshot2 = currentSnapshot();
            readable = readable(stateRecord, currentSnapshot2.getId(), currentSnapshot2.getInvalid$runtime_release());
        }
        if (readable != null) {
            return readable;
        }
        readError();
        throw null;
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = (Snapshot) threadSnapshot.get();
        if (snapshot != null) {
            return snapshot;
        }
        Object obj = currentGlobalSnapshot.get();
        ResultKt.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
        return (Snapshot) obj;
    }

    public static final Function1 mergedReadObserver(Function1 function1, Function1 function12, boolean z) {
        if (!z) {
            function12 = null;
        }
        return (function1 == null || function12 == null || ResultKt.areEqual(function1, function12)) ? function1 == null ? function12 : function1 : new SnapshotKt$mergedReadObserver$1(function1, function12, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        r6 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.runtime.snapshots.StateRecord newOverwritableRecordLocked(androidx.compose.runtime.snapshots.StateRecord r12, androidx.compose.runtime.snapshots.StateObject r13) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "state"
            kotlin.ResultKt.checkNotNullParameter(r13, r0)
            androidx.compose.runtime.snapshots.StateRecord r0 = r13.getFirstStateRecord()
            int r1 = androidx.compose.runtime.snapshots.SnapshotKt.nextSnapshotId
            androidx.compose.runtime.snapshots.SnapshotDoubleIndexHeap r2 = androidx.compose.runtime.snapshots.SnapshotKt.pinningTable
            int r3 = r2.size
            r4 = 0
            if (r3 <= 0) goto L1b
            int[] r1 = r2.values
            r1 = r1[r4]
        L1b:
            r2 = 1
            int r1 = r1 - r2
            r3 = 0
            r5 = r3
        L1f:
            if (r0 == 0) goto L61
            int r6 = r0.snapshotId
            if (r6 != 0) goto L27
        L25:
            r3 = r0
            goto L61
        L27:
            if (r6 == 0) goto L5e
            if (r6 > r1) goto L5e
            int r6 = r6 + 0
            r7 = 0
            r9 = 1
            r11 = 64
            if (r6 < 0) goto L3f
            if (r6 >= r11) goto L3f
            long r9 = r9 << r6
            long r9 = r9 & r7
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 == 0) goto L4e
        L3d:
            r6 = r2
            goto L4f
        L3f:
            if (r6 < r11) goto L4e
            r11 = 128(0x80, float:1.794E-43)
            if (r6 >= r11) goto L4e
            int r6 = r6 + (-64)
            long r9 = r9 << r6
            long r9 = r9 & r7
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 == 0) goto L4e
            goto L3d
        L4e:
            r6 = r4
        L4f:
            if (r6 != 0) goto L5e
            if (r5 != 0) goto L55
            r5 = r0
            goto L5e
        L55:
            int r1 = r0.snapshotId
            int r2 = r5.snapshotId
            if (r1 >= r2) goto L5c
            goto L25
        L5c:
            r3 = r5
            goto L61
        L5e:
            androidx.compose.runtime.snapshots.StateRecord r0 = r0.next
            goto L1f
        L61:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == 0) goto L69
            r3.snapshotId = r0
            goto L78
        L69:
            androidx.compose.runtime.snapshots.StateRecord r3 = r12.create()
            r3.snapshotId = r0
            androidx.compose.runtime.snapshots.StateRecord r12 = r13.getFirstStateRecord()
            r3.next = r12
            r13.prependStateRecord(r3)
        L78:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotKt.newOverwritableRecordLocked(androidx.compose.runtime.snapshots.StateRecord, androidx.compose.runtime.snapshots.StateObject):androidx.compose.runtime.snapshots.StateRecord");
    }

    public static final StateRecord newWritableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot) {
        StateRecord newOverwritableRecordLocked;
        ResultKt.checkNotNullParameter(stateRecord, "<this>");
        ResultKt.checkNotNullParameter(stateObject, "state");
        synchronized (lock) {
            newOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, stateObject);
            newOverwritableRecordLocked.assign(stateRecord);
            newOverwritableRecordLocked.snapshotId = snapshot.getId();
        }
        return newOverwritableRecordLocked;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject stateObject) {
        ResultKt.checkNotNullParameter(stateObject, "state");
        snapshot.setWriteCount$runtime_release(snapshot.getWriteCount$runtime_release() + 1);
        Function1 writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release != null) {
            writeObserver$runtime_release.invoke(stateObject);
        }
    }

    public static final StateRecord overwritableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot, StateRecord stateRecord2) {
        StateRecord newOverwritableRecordLocked;
        ResultKt.checkNotNullParameter(stateRecord, "<this>");
        ResultKt.checkNotNullParameter(stateObject, "state");
        if (snapshot.getReadOnly()) {
            snapshot.recordModified$runtime_release(stateObject);
        }
        int id = snapshot.getId();
        if (stateRecord2.snapshotId == id) {
            return stateRecord2;
        }
        synchronized (lock) {
            newOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, stateObject);
        }
        newOverwritableRecordLocked.snapshotId = id;
        snapshot.recordModified$runtime_release(stateObject);
        return newOverwritableRecordLocked;
    }

    public static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        int i = nextSnapshotId;
        SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = pinningTable;
        if (snapshotDoubleIndexHeap.size > 0) {
            i = snapshotDoubleIndexHeap.values[0];
        }
        StateRecord stateRecord2 = null;
        StateRecord stateRecord3 = null;
        int i2 = 0;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.next) {
            int i3 = firstStateRecord.snapshotId;
            if (i3 != 0) {
                if (i3 >= i) {
                    i2++;
                } else if (stateRecord2 == null) {
                    i2++;
                    stateRecord2 = firstStateRecord;
                } else {
                    if (i3 < stateRecord2.snapshotId) {
                        stateRecord = stateRecord2;
                        stateRecord2 = firstStateRecord;
                    } else {
                        stateRecord = firstStateRecord;
                    }
                    if (stateRecord3 == null) {
                        stateRecord3 = stateObject.getFirstStateRecord();
                        StateRecord stateRecord4 = stateRecord3;
                        while (true) {
                            if (stateRecord3 == null) {
                                stateRecord3 = stateRecord4;
                                break;
                            }
                            int i4 = stateRecord3.snapshotId;
                            if (i4 >= i) {
                                break;
                            }
                            if (stateRecord4.snapshotId < i4) {
                                stateRecord4 = stateRecord3;
                            }
                            stateRecord3 = stateRecord3.next;
                        }
                    }
                    stateRecord2.snapshotId = 0;
                    stateRecord2.assign(stateRecord3);
                    stateRecord2 = stateRecord;
                }
            }
        }
        return i2 > 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: androidx.compose.runtime.WeakReference[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void processForUnusedRecordsLocked(StateObject stateObject) {
        int i;
        if (overwriteUnusedRecordsLocked(stateObject)) {
            SnapshotWeakSet snapshotWeakSet = extraStateObjects;
            snapshotWeakSet.getClass();
            int i2 = snapshotWeakSet.size;
            int identityHashCode = System.identityHashCode(stateObject);
            if (i2 > 0) {
                int i3 = snapshotWeakSet.size - 1;
                int i4 = 0;
                while (true) {
                    if (i4 > i3) {
                        i = -(i4 + 1);
                        break;
                    }
                    i = (i4 + i3) >>> 1;
                    int i5 = ((int[]) snapshotWeakSet.hashes)[i];
                    if (i5 < identityHashCode) {
                        i4 = i + 1;
                    } else if (i5 > identityHashCode) {
                        i3 = i - 1;
                    } else {
                        WeakReference weakReference = ((WeakReference[]) snapshotWeakSet.values)[i];
                        if (stateObject != (weakReference != null ? weakReference.get() : null)) {
                            i = snapshotWeakSet.findExactIndex(i, identityHashCode, stateObject);
                        }
                    }
                }
                if (i >= 0) {
                    return;
                }
            } else {
                i = -1;
            }
            int i6 = -(i + 1);
            WeakReference[] weakReferenceArr = (WeakReference[]) snapshotWeakSet.values;
            int length = weakReferenceArr.length;
            if (i2 == length) {
                int i7 = length * 2;
                WeakReference[] weakReferenceArr2 = new WeakReference[i7];
                int[] iArr = new int[i7];
                int i8 = i6 + 1;
                MapsKt___MapsJvmKt.copyInto(weakReferenceArr, weakReferenceArr2, i8, i6, i2);
                MapsKt___MapsJvmKt.copyInto$default((WeakReference[]) snapshotWeakSet.values, weakReferenceArr2, 0, i6, 6);
                MapsKt___MapsJvmKt.copyInto((int[]) snapshotWeakSet.hashes, iArr, i8, i6, i2);
                MapsKt___MapsJvmKt.copyInto$default((int[]) snapshotWeakSet.hashes, iArr, i6, 6);
                snapshotWeakSet.values = weakReferenceArr2;
                snapshotWeakSet.hashes = iArr;
            } else {
                int i9 = i6 + 1;
                MapsKt___MapsJvmKt.copyInto(weakReferenceArr, weakReferenceArr, i9, i6, i2);
                int[] iArr2 = (int[]) snapshotWeakSet.hashes;
                MapsKt___MapsJvmKt.copyInto(iArr2, iArr2, i9, i6, i2);
            }
            ((WeakReference[]) snapshotWeakSet.values)[i6] = new java.lang.ref.WeakReference(stateObject);
            ((int[]) snapshotWeakSet.hashes)[i6] = identityHashCode;
            snapshotWeakSet.size++;
        }
    }

    public static final void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied".toString());
    }

    public static final StateRecord readable(StateRecord stateRecord, StateObject stateObject) {
        StateRecord readable;
        ResultKt.checkNotNullParameter(stateRecord, "<this>");
        ResultKt.checkNotNullParameter(stateObject, "state");
        Snapshot currentSnapshot = currentSnapshot();
        Function1 readObserver$runtime_release = currentSnapshot.getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(stateObject);
        }
        StateRecord readable2 = readable(stateRecord, currentSnapshot.getId(), currentSnapshot.getInvalid$runtime_release());
        if (readable2 != null) {
            return readable2;
        }
        synchronized (lock) {
            Snapshot currentSnapshot2 = currentSnapshot();
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            ResultKt.checkNotNull(firstStateRecord, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.readable$lambda$9");
            readable = readable(firstStateRecord, currentSnapshot2.getId(), currentSnapshot2.getInvalid$runtime_release());
            if (readable == null) {
                readError();
                throw null;
            }
        }
        return readable;
    }

    public static final void releasePinningLocked(int i) {
        int i2;
        SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = pinningTable;
        int i3 = snapshotDoubleIndexHeap.handles[i];
        snapshotDoubleIndexHeap.swap(i3, snapshotDoubleIndexHeap.size - 1);
        snapshotDoubleIndexHeap.size--;
        int[] iArr = snapshotDoubleIndexHeap.values;
        int i4 = iArr[i3];
        int i5 = i3;
        while (i5 > 0) {
            int i6 = ((i5 + 1) >> 1) - 1;
            if (iArr[i6] <= i4) {
                break;
            }
            snapshotDoubleIndexHeap.swap(i6, i5);
            i5 = i6;
        }
        int[] iArr2 = snapshotDoubleIndexHeap.values;
        int i7 = snapshotDoubleIndexHeap.size >> 1;
        while (i3 < i7) {
            int i8 = (i3 + 1) << 1;
            int i9 = i8 - 1;
            if (i8 < snapshotDoubleIndexHeap.size && (i2 = iArr2[i8]) < iArr2[i9]) {
                if (i2 >= iArr2[i3]) {
                    break;
                }
                snapshotDoubleIndexHeap.swap(i8, i3);
                i3 = i8;
            } else {
                if (iArr2[i9] >= iArr2[i3]) {
                    break;
                }
                snapshotDoubleIndexHeap.swap(i9, i3);
                i3 = i9;
            }
        }
        snapshotDoubleIndexHeap.handles[i] = snapshotDoubleIndexHeap.firstFreeHandle;
        snapshotDoubleIndexHeap.firstFreeHandle = i;
    }

    public static final Object takeNewGlobalSnapshot(Snapshot snapshot, Function1 function1) {
        Object invoke = function1.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (lock) {
            int i = nextSnapshotId;
            nextSnapshotId = i + 1;
            SnapshotIdSet clear = openSnapshots.clear(snapshot.getId());
            openSnapshots = clear;
            currentGlobalSnapshot.set(new GlobalSnapshot(i, clear));
            snapshot.dispose();
            openSnapshots = openSnapshots.set(i);
        }
        return invoke;
    }

    public static final StateRecord writableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot) {
        ResultKt.checkNotNullParameter(stateObject, "state");
        if (snapshot.getReadOnly()) {
            snapshot.recordModified$runtime_release(stateObject);
        }
        StateRecord readable = readable(stateRecord, snapshot.getId(), snapshot.getInvalid$runtime_release());
        if (readable == null) {
            readError();
            throw null;
        }
        if (readable.snapshotId == snapshot.getId()) {
            return readable;
        }
        StateRecord newWritableRecord = newWritableRecord(readable, stateObject, snapshot);
        snapshot.recordModified$runtime_release(stateObject);
        return newWritableRecord;
    }

    public static final StateRecord readable(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        StateRecord stateRecord2 = null;
        while (stateRecord != null) {
            int i2 = stateRecord.snapshotId;
            if (i2 != 0 && i2 <= i && !snapshotIdSet.get(i2) && (stateRecord2 == null || stateRecord2.snapshotId < stateRecord.snapshotId)) {
                stateRecord2 = stateRecord;
            }
            stateRecord = stateRecord.next;
        }
        if (stateRecord2 != null) {
            return stateRecord2;
        }
        return null;
    }
}
