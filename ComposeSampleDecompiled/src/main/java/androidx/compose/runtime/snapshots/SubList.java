package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.ranges.IntProgressionIterator;

/* loaded from: classes.dex */
public final class SubList implements List, KMutableCollection {
    public int modification;
    public final int offset;
    public final SnapshotStateList parentList;
    public int size;

    public SubList(SnapshotStateList snapshotStateList, int i, int i2) {
        ResultKt.checkNotNullParameter(snapshotStateList, "parentList");
        this.parentList = snapshotStateList;
        this.offset = i;
        this.modification = snapshotStateList.getModification$runtime_release();
        this.size = i2 - i;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        validateModification$1();
        int i = this.offset + this.size;
        SnapshotStateList snapshotStateList = this.parentList;
        snapshotStateList.add(i, obj);
        this.size++;
        this.modification = snapshotStateList.getModification$runtime_release();
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        return addAll(this.size, collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        int i;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        if (this.size > 0) {
            validateModification$1();
            SnapshotStateList snapshotStateList = this.parentList;
            int i2 = this.offset;
            int i3 = this.size + i2;
            snapshotStateList.getClass();
            do {
                Object obj = SnapshotStateListKt.sync;
                synchronized (obj) {
                    SnapshotStateList.StateListStateRecord stateListStateRecord = snapshotStateList.firstStateRecord;
                    ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                    SnapshotStateList.StateListStateRecord stateListStateRecord2 = (SnapshotStateList.StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                    i = stateListStateRecord2.modification;
                    persistentList = stateListStateRecord2.list;
                }
                ResultKt.checkNotNull(persistentList);
                PersistentVectorBuilder builder = persistentList.builder();
                builder.subList(i2, i3).clear();
                PersistentList build = builder.build();
                if (ResultKt.areEqual(build, persistentList)) {
                    break;
                }
                SnapshotStateList.StateListStateRecord stateListStateRecord3 = snapshotStateList.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                synchronized (SnapshotKt.lock) {
                    currentSnapshot = SnapshotKt.currentSnapshot();
                    SnapshotStateList.StateListStateRecord stateListStateRecord4 = (SnapshotStateList.StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, snapshotStateList, currentSnapshot);
                    synchronized (obj) {
                        if (stateListStateRecord4.modification == i) {
                            stateListStateRecord4.setList$runtime_release(build);
                            z = true;
                            stateListStateRecord4.modification++;
                        } else {
                            z = false;
                        }
                    }
                }
                SnapshotKt.notifyWrite(currentSnapshot, snapshotStateList);
            } while (!z);
            this.size = 0;
            this.modification = this.parentList.getModification$runtime_release();
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        Collection collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public final Object get(int i) {
        validateModification$1();
        SnapshotStateListKt.access$validateRange(i, this.size);
        return this.parentList.get(this.offset + i);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        validateModification$1();
        int i = this.size;
        int i2 = this.offset;
        Iterator it = ResultKt.until(i2, i + i2).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntProgressionIterator) it).nextInt();
            if (ResultKt.areEqual(obj, this.parentList.get(nextInt))) {
                return nextInt - i2;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        validateModification$1();
        int i = this.size;
        int i2 = this.offset;
        for (int i3 = (i + i2) - 1; i3 >= i2; i3--) {
            if (ResultKt.areEqual(obj, this.parentList.get(i3))) {
                return i3 - i2;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        Iterator it = collection.iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                if (remove(it.next()) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        int i;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        ResultKt.checkNotNullParameter(collection, "elements");
        validateModification$1();
        SnapshotStateList snapshotStateList = this.parentList;
        int i2 = this.offset;
        int i3 = this.size + i2;
        snapshotStateList.getClass();
        int size = snapshotStateList.size();
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                SnapshotStateList.StateListStateRecord stateListStateRecord = snapshotStateList.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                SnapshotStateList.StateListStateRecord stateListStateRecord2 = (SnapshotStateList.StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentVectorBuilder builder = persistentList.builder();
            builder.subList(i2, i3).retainAll(collection);
            PersistentList build = builder.build();
            if (ResultKt.areEqual(build, persistentList)) {
                break;
            }
            SnapshotStateList.StateListStateRecord stateListStateRecord3 = snapshotStateList.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                SnapshotStateList.StateListStateRecord stateListStateRecord4 = (SnapshotStateList.StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, snapshotStateList, currentSnapshot);
                synchronized (obj) {
                    if (stateListStateRecord4.modification == i) {
                        stateListStateRecord4.setList$runtime_release(build);
                        stateListStateRecord4.modification++;
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, snapshotStateList);
        } while (!z);
        int size2 = size - snapshotStateList.size();
        if (size2 > 0) {
            this.modification = this.parentList.getModification$runtime_release();
            this.size -= size2;
        }
        return size2 > 0;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        SnapshotStateListKt.access$validateRange(i, this.size);
        validateModification$1();
        int i2 = i + this.offset;
        SnapshotStateList snapshotStateList = this.parentList;
        Object obj2 = snapshotStateList.set(i2, obj);
        this.modification = snapshotStateList.getModification$runtime_release();
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.size;
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        if (i < 0 || i > i2 || i2 > this.size) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        validateModification$1();
        int i3 = this.offset;
        return new SubList(this.parentList, i + i3, i2 + i3);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public final void validateModification$1() {
        if (this.parentList.getModification$runtime_release() != this.modification) {
            throw new ConcurrentModificationException();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.Ref$IntRef, java.lang.Object] */
    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        validateModification$1();
        ?? obj = new Object();
        obj.element = i - 1;
        return new SubList$listIterator$1(obj, this);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        validateModification$1();
        int i2 = i + this.offset;
        SnapshotStateList snapshotStateList = this.parentList;
        boolean addAll = snapshotStateList.addAll(i2, collection);
        if (addAll) {
            this.size = collection.size() + this.size;
            this.modification = snapshotStateList.getModification$runtime_release();
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        validateModification$1();
        int i2 = this.offset + i;
        SnapshotStateList snapshotStateList = this.parentList;
        Object remove = snapshotStateList.remove(i2);
        this.size--;
        this.modification = snapshotStateList.getModification$runtime_release();
        return remove;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        validateModification$1();
        int i2 = this.offset + i;
        SnapshotStateList snapshotStateList = this.parentList;
        snapshotStateList.add(i2, obj);
        this.size++;
        this.modification = snapshotStateList.getModification$runtime_release();
    }
}
