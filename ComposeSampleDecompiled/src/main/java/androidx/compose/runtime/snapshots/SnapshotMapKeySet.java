package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;

/* loaded from: classes.dex */
public final class SnapshotMapKeySet extends SnapshotMapSet {
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotMapKeySet(SnapshotStateMap snapshotStateMap, int i) {
        super(snapshotStateMap);
        this.$r8$classId = i;
        if (i == 1) {
            ResultKt.checkNotNullParameter(snapshotStateMap, "map");
            super(snapshotStateMap);
        } else if (i != 2) {
            ResultKt.checkNotNullParameter(snapshotStateMap, "map");
        } else {
            ResultKt.checkNotNullParameter(snapshotStateMap, "map");
            super(snapshotStateMap);
        }
    }

    private boolean retainAll$androidx$compose$runtime$snapshots$SnapshotMapEntrySet(Collection collection) {
        PersistentMap persistentMap;
        int i;
        boolean z;
        Snapshot currentSnapshot;
        ResultKt.checkNotNullParameter(collection, "elements");
        Collection<Map.Entry> collection2 = collection;
        int mapCapacity = ResultKt.mapCapacity(MapsKt___MapsJvmKt.collectionSizeOrDefault(collection2));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Map.Entry entry : collection2) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        SnapshotStateMap snapshotStateMap = this.map;
        boolean z2 = false;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                SnapshotStateMap.StateMapStateRecord stateMapStateRecord = snapshotStateMap.firstStateRecord;
                ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                SnapshotStateMap.StateMapStateRecord stateMapStateRecord2 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                persistentMap = stateMapStateRecord2.map;
                i = stateMapStateRecord2.modification;
            }
            ResultKt.checkNotNull(persistentMap);
            PersistentMap.Builder builder = persistentMap.builder();
            Object it = snapshotStateMap.entries.iterator();
            while (true) {
                z = true;
                if (!((StateMapMutableIterator) it).hasNext()) {
                    break;
                }
                Map.Entry entry2 = (Map.Entry) ((StateMapMutableKeysIterator) it).next();
                if (!linkedHashMap.containsKey(entry2.getKey()) || !ResultKt.areEqual(linkedHashMap.get(entry2.getKey()), entry2.getValue())) {
                    builder.remove(entry2.getKey());
                    z2 = true;
                }
            }
            PersistentMap build = builder.build();
            if (ResultKt.areEqual(build, persistentMap)) {
                break;
            }
            SnapshotStateMap.StateMapStateRecord stateMapStateRecord3 = snapshotStateMap.firstStateRecord;
            ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                SnapshotStateMap.StateMapStateRecord stateMapStateRecord4 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, snapshotStateMap, currentSnapshot);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord4.modification == i) {
                        stateMapStateRecord4.setMap$runtime_release(build);
                        stateMapStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, snapshotStateMap);
        } while (!z);
        return z2;
    }

    private boolean retainAll$androidx$compose$runtime$snapshots$SnapshotMapKeySet(Collection collection) {
        PersistentMap persistentMap;
        int i;
        boolean z;
        Snapshot currentSnapshot;
        ResultKt.checkNotNullParameter(collection, "elements");
        Set set = CollectionsKt___CollectionsKt.toSet(collection);
        SnapshotStateMap snapshotStateMap = this.map;
        boolean z2 = false;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                SnapshotStateMap.StateMapStateRecord stateMapStateRecord = snapshotStateMap.firstStateRecord;
                ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                SnapshotStateMap.StateMapStateRecord stateMapStateRecord2 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                persistentMap = stateMapStateRecord2.map;
                i = stateMapStateRecord2.modification;
            }
            ResultKt.checkNotNull(persistentMap);
            PersistentMap.Builder builder = persistentMap.builder();
            Object it = snapshotStateMap.entries.iterator();
            while (true) {
                z = true;
                if (!((StateMapMutableIterator) it).hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) ((StateMapMutableKeysIterator) it).next();
                if (!set.contains(entry.getKey())) {
                    builder.remove(entry.getKey());
                    z2 = true;
                }
            }
            PersistentMap build = builder.build();
            if (ResultKt.areEqual(build, persistentMap)) {
                break;
            }
            SnapshotStateMap.StateMapStateRecord stateMapStateRecord3 = snapshotStateMap.firstStateRecord;
            ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                SnapshotStateMap.StateMapStateRecord stateMapStateRecord4 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, snapshotStateMap, currentSnapshot);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord4.modification == i) {
                        stateMapStateRecord4.setMap$runtime_release(build);
                        stateMapStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, snapshotStateMap);
        } while (!z);
        return z2;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        SnapshotStateMapKt.unsupported();
                        throw null;
                    default:
                        SnapshotStateMapKt.unsupported();
                        throw null;
                }
            case 1:
                ResultKt.checkNotNullParameter((Map.Entry) obj, "element");
                SnapshotStateMapKt.unsupported();
                throw null;
            default:
                switch (i) {
                    case 0:
                        SnapshotStateMapKt.unsupported();
                        throw null;
                    default:
                        SnapshotStateMapKt.unsupported();
                        throw null;
                }
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        switch (this.$r8$classId) {
            case 0:
                addAll$1(collection);
                throw null;
            case 1:
                addAll$1(collection);
                throw null;
            default:
                addAll$1(collection);
                throw null;
        }
    }

    public final void addAll$1(Collection collection) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(collection, "elements");
                SnapshotStateMapKt.unsupported();
                throw null;
            case 1:
                ResultKt.checkNotNullParameter(collection, "elements");
                SnapshotStateMapKt.unsupported();
                throw null;
            default:
                ResultKt.checkNotNullParameter(collection, "elements");
                SnapshotStateMapKt.unsupported();
                throw null;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        int i = this.$r8$classId;
        SnapshotStateMap snapshotStateMap = this.map;
        switch (i) {
            case 0:
                return snapshotStateMap.containsKey(obj);
            case 1:
                if (!(obj instanceof Map.Entry) || ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap.Entry))) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                ResultKt.checkNotNullParameter(entry, "element");
                return ResultKt.areEqual(snapshotStateMap.get(entry.getKey()), entry.getValue());
            default:
                return snapshotStateMap.containsValue(obj);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        int i = this.$r8$classId;
        SnapshotStateMap snapshotStateMap = this.map;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(collection, "elements");
                Collection collection2 = collection;
                if (!collection2.isEmpty()) {
                    Iterator it = collection2.iterator();
                    while (it.hasNext()) {
                        if (!snapshotStateMap.containsKey(it.next())) {
                            return false;
                        }
                    }
                }
                return true;
            case 1:
                ResultKt.checkNotNullParameter(collection, "elements");
                Collection collection3 = collection;
                if (!collection3.isEmpty()) {
                    Iterator it2 = collection3.iterator();
                    while (it2.hasNext()) {
                        if (!contains((Map.Entry) it2.next())) {
                            return false;
                        }
                    }
                }
                return true;
            default:
                ResultKt.checkNotNullParameter(collection, "elements");
                Collection collection4 = collection;
                if (!collection4.isEmpty()) {
                    Iterator it3 = collection4.iterator();
                    while (it3.hasNext()) {
                        if (!snapshotStateMap.containsValue(it3.next())) {
                            return false;
                        }
                    }
                }
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i = this.$r8$classId;
        SnapshotStateMap snapshotStateMap = this.map;
        switch (i) {
            case 0:
                return new StateMapMutableKeysIterator(snapshotStateMap, ((ImmutableSet) ((AbstractMap) snapshotStateMap.getReadable$runtime_release().map).entrySet()).iterator(), 0);
            case 1:
                return new StateMapMutableKeysIterator(snapshotStateMap, ((ImmutableSet) ((AbstractMap) snapshotStateMap.getReadable$runtime_release().map).entrySet()).iterator(), 1);
            default:
                return new StateMapMutableKeysIterator(snapshotStateMap, ((ImmutableSet) ((AbstractMap) snapshotStateMap.getReadable$runtime_release().map).entrySet()).iterator(), 2);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        Object obj2;
        int i = this.$r8$classId;
        SnapshotStateMap snapshotStateMap = this.map;
        switch (i) {
            case 0:
                return snapshotStateMap.remove(obj) != null;
            case 1:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                ResultKt.checkNotNullParameter(entry, "element");
                return snapshotStateMap.remove(entry.getKey()) != null;
            default:
                Object it = snapshotStateMap.entries.iterator();
                while (true) {
                    if (((StateMapMutableIterator) it).hasNext()) {
                        obj2 = ((StateMapMutableKeysIterator) it).next();
                        if (ResultKt.areEqual(((Map.Entry) obj2).getValue(), obj)) {
                        }
                    } else {
                        obj2 = null;
                    }
                }
                Map.Entry entry2 = (Map.Entry) obj2;
                if (entry2 == null) {
                    return false;
                }
                snapshotStateMap.remove(entry2.getKey());
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        PersistentMap persistentMap;
        int i;
        Snapshot currentSnapshot;
        boolean z;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(collection, "elements");
                Iterator it = collection.iterator();
                while (true) {
                    boolean z2 = false;
                    while (it.hasNext()) {
                        if (this.map.remove(it.next()) != null || z2) {
                            z2 = true;
                        }
                    }
                    return z2;
                    break;
                }
                break;
            case 1:
                ResultKt.checkNotNullParameter(collection, "elements");
                Iterator it2 = collection.iterator();
                while (true) {
                    boolean z3 = false;
                    while (it2.hasNext()) {
                        if (this.map.remove(((Map.Entry) it2.next()).getKey()) != null || z3) {
                            z3 = true;
                        }
                    }
                    return z3;
                    break;
                }
                break;
            default:
                ResultKt.checkNotNullParameter(collection, "elements");
                Set set = CollectionsKt___CollectionsKt.toSet(collection);
                SnapshotStateMap snapshotStateMap = this.map;
                boolean z4 = false;
                do {
                    synchronized (SnapshotStateMapKt.sync) {
                        SnapshotStateMap.StateMapStateRecord stateMapStateRecord = snapshotStateMap.firstStateRecord;
                        ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                        SnapshotStateMap.StateMapStateRecord stateMapStateRecord2 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                        persistentMap = stateMapStateRecord2.map;
                        i = stateMapStateRecord2.modification;
                    }
                    ResultKt.checkNotNull(persistentMap);
                    PersistentMap.Builder builder = persistentMap.builder();
                    Object it3 = snapshotStateMap.entries.iterator();
                    while (((StateMapMutableIterator) it3).hasNext()) {
                        Map.Entry entry = (Map.Entry) ((StateMapMutableKeysIterator) it3).next();
                        if (set.contains(entry.getValue())) {
                            builder.remove(entry.getKey());
                            z4 = true;
                        }
                    }
                    PersistentMap build = builder.build();
                    if (!ResultKt.areEqual(build, persistentMap)) {
                        SnapshotStateMap.StateMapStateRecord stateMapStateRecord3 = snapshotStateMap.firstStateRecord;
                        ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                        synchronized (SnapshotKt.lock) {
                            currentSnapshot = SnapshotKt.currentSnapshot();
                            SnapshotStateMap.StateMapStateRecord stateMapStateRecord4 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, snapshotStateMap, currentSnapshot);
                            synchronized (SnapshotStateMapKt.sync) {
                                if (stateMapStateRecord4.modification == i) {
                                    stateMapStateRecord4.setMap$runtime_release(build);
                                    stateMapStateRecord4.modification++;
                                    z = true;
                                } else {
                                    z = false;
                                }
                            }
                        }
                        SnapshotKt.notifyWrite(currentSnapshot, snapshotStateMap);
                    }
                    return z4;
                } while (!z);
                return z4;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        PersistentMap persistentMap;
        int i;
        boolean z;
        Snapshot currentSnapshot;
        switch (this.$r8$classId) {
            case 0:
                return retainAll$androidx$compose$runtime$snapshots$SnapshotMapKeySet(collection);
            case 1:
                return retainAll$androidx$compose$runtime$snapshots$SnapshotMapEntrySet(collection);
            default:
                ResultKt.checkNotNullParameter(collection, "elements");
                Set set = CollectionsKt___CollectionsKt.toSet(collection);
                SnapshotStateMap snapshotStateMap = this.map;
                boolean z2 = false;
                do {
                    synchronized (SnapshotStateMapKt.sync) {
                        SnapshotStateMap.StateMapStateRecord stateMapStateRecord = snapshotStateMap.firstStateRecord;
                        ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                        SnapshotStateMap.StateMapStateRecord stateMapStateRecord2 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                        persistentMap = stateMapStateRecord2.map;
                        i = stateMapStateRecord2.modification;
                    }
                    ResultKt.checkNotNull(persistentMap);
                    PersistentMap.Builder builder = persistentMap.builder();
                    Object it = snapshotStateMap.entries.iterator();
                    while (true) {
                        z = true;
                        if (!((StateMapMutableIterator) it).hasNext()) {
                            PersistentMap build = builder.build();
                            if (!ResultKt.areEqual(build, persistentMap)) {
                                SnapshotStateMap.StateMapStateRecord stateMapStateRecord3 = snapshotStateMap.firstStateRecord;
                                ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                                synchronized (SnapshotKt.lock) {
                                    currentSnapshot = SnapshotKt.currentSnapshot();
                                    SnapshotStateMap.StateMapStateRecord stateMapStateRecord4 = (SnapshotStateMap.StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, snapshotStateMap, currentSnapshot);
                                    synchronized (SnapshotStateMapKt.sync) {
                                        if (stateMapStateRecord4.modification == i) {
                                            stateMapStateRecord4.setMap$runtime_release(build);
                                            stateMapStateRecord4.modification++;
                                        } else {
                                            z = false;
                                        }
                                    }
                                }
                                SnapshotKt.notifyWrite(currentSnapshot, snapshotStateMap);
                            }
                            return z2;
                        }
                        Map.Entry entry = (Map.Entry) ((StateMapMutableKeysIterator) it).next();
                        if (!set.contains(entry.getValue())) {
                            builder.remove(entry.getKey());
                            z2 = true;
                        }
                    }
                } while (!z);
                return z2;
        }
    }
}
