package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.SmallPersistentVector;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.collections.AbstractCollection;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMutableCollection;

/* loaded from: classes.dex */
public final class SnapshotStateList implements List, StateObject, KMutableCollection {
    public StateListStateRecord firstStateRecord;

    /* loaded from: classes.dex */
    public final class StateListStateRecord extends StateRecord {
        public PersistentList list;
        public int modification;

        public StateListStateRecord(PersistentList persistentList) {
            ResultKt.checkNotNullParameter(persistentList, "list");
            this.list = persistentList;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            ResultKt.checkNotNullParameter(stateRecord, "value");
            synchronized (SnapshotStateListKt.sync) {
                this.list = ((StateListStateRecord) stateRecord).list;
                this.modification = ((StateListStateRecord) stateRecord).modification;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateListStateRecord(this.list);
        }

        public final void setList$runtime_release(PersistentList persistentList) {
            ResultKt.checkNotNullParameter(persistentList, "<set-?>");
            this.list = persistentList;
        }
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentList add = persistentList.add(obj);
            z = false;
            if (ResultKt.areEqual(add, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj2) {
                    if (stateListStateRecord4.modification == i) {
                        stateListStateRecord4.list = add;
                        stateListStateRecord4.modification++;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(final int i, final Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        return mutateBoolean(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$addAll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List list = (List) obj;
                ResultKt.checkNotNullParameter(list, "it");
                return Boolean.valueOf(list.addAll(i, collection));
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        Snapshot currentSnapshot;
        StateListStateRecord stateListStateRecord = this.firstStateRecord;
        ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        synchronized (SnapshotKt.lock) {
            currentSnapshot = SnapshotKt.currentSnapshot();
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord, this, currentSnapshot);
            synchronized (SnapshotStateListKt.sync) {
                stateListStateRecord2.setList$runtime_release(SmallPersistentVector.EMPTY);
                stateListStateRecord2.modification++;
            }
        }
        SnapshotKt.notifyWrite(currentSnapshot, this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return ((AbstractPersistentList) getReadable$runtime_release().list).contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        return ((AbstractPersistentList) getReadable$runtime_release().list).containsAll(collection);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return getReadable$runtime_release().list.get(i);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public final int getModification$runtime_release() {
        StateListStateRecord stateListStateRecord = this.firstStateRecord;
        ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return ((StateListStateRecord) SnapshotKt.current(stateListStateRecord)).modification;
    }

    public final StateListStateRecord getReadable$runtime_release() {
        StateListStateRecord stateListStateRecord = this.firstStateRecord;
        ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
        return (StateListStateRecord) SnapshotKt.readable(stateListStateRecord, this);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return getReadable$runtime_release().list.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return ((AbstractCollection) getReadable$runtime_release().list).isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return getReadable$runtime_release().list.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new ListBuilder.Itr(this, 0);
    }

    public final boolean mutateBoolean(Function1 function1) {
        int i;
        PersistentList persistentList;
        Object invoke;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentVectorBuilder builder = persistentList.builder();
            invoke = function1.invoke(builder);
            PersistentList build = builder.build();
            if (ResultKt.areEqual(build, persistentList)) {
                break;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
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
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return ((Boolean) invoke).booleanValue();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        stateRecord.next = this.firstStateRecord;
        this.firstStateRecord = (StateListStateRecord) stateRecord;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        int i2;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        Object obj = get(i);
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i2 = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentList removeAt = persistentList.removeAt(i);
            if (ResultKt.areEqual(removeAt, persistentList)) {
                break;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj2) {
                    if (stateListStateRecord4.modification == i2) {
                        stateListStateRecord4.setList$runtime_release(removeAt);
                        z = true;
                        stateListStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return obj;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        ResultKt.checkNotNullParameter(collection, "elements");
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentList removeAll = ((AbstractPersistentList) persistentList).removeAll(new SnapshotStateList$retainAll$1(1, collection));
            z = false;
            if (ResultKt.areEqual(removeAll, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj) {
                    if (stateListStateRecord4.modification == i) {
                        stateListStateRecord4.setList$runtime_release(removeAll);
                        stateListStateRecord4.modification++;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        return mutateBoolean(new SnapshotStateList$retainAll$1(0, collection));
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        int i2;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        Object obj2 = get(i);
        do {
            Object obj3 = SnapshotStateListKt.sync;
            synchronized (obj3) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i2 = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentList persistentList2 = persistentList.set(i, obj);
            if (ResultKt.areEqual(persistentList2, persistentList)) {
                break;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj3) {
                    if (stateListStateRecord4.modification == i2) {
                        stateListStateRecord4.list = persistentList2;
                        z = true;
                        stateListStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return ((AbstractCollection) getReadable$runtime_release().list).getSize();
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        if (i < 0 || i > i2 || i2 > size()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return new SubList(this, i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        ResultKt.checkNotNullParameter(collection, "elements");
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentList addAll = persistentList.addAll(collection);
            z = false;
            if (ResultKt.areEqual(addAll, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj) {
                    if (stateListStateRecord4.modification == i) {
                        stateListStateRecord4.setList$runtime_release(addAll);
                        stateListStateRecord4.modification++;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return new ListBuilder.Itr(this, i);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        int i2;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i2 = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            PersistentList add = persistentList.add(i, obj);
            if (ResultKt.areEqual(add, persistentList)) {
                return;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj2) {
                    if (stateListStateRecord4.modification == i2) {
                        stateListStateRecord4.list = add;
                        z = true;
                        stateListStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateListStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
                StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.current(stateListStateRecord);
                i = stateListStateRecord2.modification;
                persistentList = stateListStateRecord2.list;
            }
            ResultKt.checkNotNull(persistentList);
            AbstractPersistentList abstractPersistentList = (AbstractPersistentList) persistentList;
            int indexOf = abstractPersistentList.indexOf(obj);
            PersistentList persistentList2 = abstractPersistentList;
            if (indexOf != -1) {
                persistentList2 = abstractPersistentList.removeAt(indexOf);
            }
            z = false;
            if (ResultKt.areEqual(persistentList2, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateListStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateList.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateList>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord4 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord3, this, currentSnapshot);
                synchronized (obj2) {
                    if (stateListStateRecord4.modification == i) {
                        stateListStateRecord4.setList$runtime_release(persistentList2);
                        stateListStateRecord4.modification++;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }
}
