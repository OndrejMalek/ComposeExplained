package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap;
import kotlin.jvm.internal.markers.KMutableMap;

/* loaded from: classes.dex */
public final class SnapshotStateMap implements Map, StateObject, KMutableMap {
    public final SnapshotMapKeySet entries;
    public StateMapStateRecord firstStateRecord;
    public final SnapshotMapKeySet keys;
    public final SnapshotMapKeySet values;

    /* loaded from: classes.dex */
    public final class StateMapStateRecord extends StateRecord {
        public PersistentMap map;
        public int modification;

        public StateMapStateRecord(PersistentMap persistentMap) {
            ResultKt.checkNotNullParameter(persistentMap, "map");
            this.map = persistentMap;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            ResultKt.checkNotNullParameter(stateRecord, "value");
            StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) stateRecord;
            synchronized (SnapshotStateMapKt.sync) {
                this.map = stateMapStateRecord.map;
                this.modification = stateMapStateRecord.modification;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateMapStateRecord(this.map);
        }

        public final void setMap$runtime_release(PersistentMap persistentMap) {
            ResultKt.checkNotNullParameter(persistentMap, "<set-?>");
            this.map = persistentMap;
        }
    }

    public SnapshotStateMap() {
        PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
        ResultKt.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
        this.firstStateRecord = new StateMapStateRecord(persistentHashMap);
        this.entries = new SnapshotMapKeySet(this, 1);
        this.keys = new SnapshotMapKeySet(this, 0);
        this.values = new SnapshotMapKeySet(this, 2);
    }

    @Override // java.util.Map
    public final void clear() {
        Snapshot currentSnapshot;
        StateMapStateRecord stateMapStateRecord = this.firstStateRecord;
        ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
        StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
        PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
        ResultKt.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
        if (persistentHashMap != stateMapStateRecord2.map) {
            StateMapStateRecord stateMapStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord4 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, this, currentSnapshot);
                synchronized (SnapshotStateMapKt.sync) {
                    stateMapStateRecord4.map = persistentHashMap;
                    stateMapStateRecord4.modification++;
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        }
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return getReadable$runtime_release().map.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return getReadable$runtime_release().map.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.entries;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return getReadable$runtime_release().map.get(obj);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public final StateMapStateRecord getReadable$runtime_release() {
        StateMapStateRecord stateMapStateRecord = this.firstStateRecord;
        ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
        return (StateMapStateRecord) SnapshotKt.readable(stateMapStateRecord, this);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return ((AbstractMap) getReadable$runtime_release().map).isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        return this.keys;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.firstStateRecord = (StateMapStateRecord) stateRecord;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        PersistentMap persistentMap;
        int i;
        Object put;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj3 = SnapshotStateMapKt.sync;
            synchronized (obj3) {
                StateMapStateRecord stateMapStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                persistentMap = stateMapStateRecord2.map;
                i = stateMapStateRecord2.modification;
            }
            ResultKt.checkNotNull(persistentMap);
            PersistentHashMapBuilder persistentHashMapBuilder = (PersistentHashMapBuilder) persistentMap.builder();
            put = persistentHashMapBuilder.put(obj, obj2);
            PersistentMap build = persistentHashMapBuilder.build();
            if (ResultKt.areEqual(build, persistentMap)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord4 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, this, currentSnapshot);
                synchronized (obj3) {
                    if (stateMapStateRecord4.modification == i) {
                        stateMapStateRecord4.setMap$runtime_release(build);
                        z = true;
                        stateMapStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return put;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        PersistentMap persistentMap;
        int i;
        Snapshot currentSnapshot;
        boolean z;
        ResultKt.checkNotNullParameter(map, "from");
        do {
            Object obj = SnapshotStateMapKt.sync;
            synchronized (obj) {
                StateMapStateRecord stateMapStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                persistentMap = stateMapStateRecord2.map;
                i = stateMapStateRecord2.modification;
            }
            ResultKt.checkNotNull(persistentMap);
            PersistentHashMapBuilder persistentHashMapBuilder = (PersistentHashMapBuilder) persistentMap.builder();
            persistentHashMapBuilder.putAll(map);
            PersistentMap build = persistentHashMapBuilder.build();
            if (ResultKt.areEqual(build, persistentMap)) {
                return;
            }
            StateMapStateRecord stateMapStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord4 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, this, currentSnapshot);
                synchronized (obj) {
                    if (stateMapStateRecord4.modification == i) {
                        stateMapStateRecord4.setMap$runtime_release(build);
                        z = true;
                        stateMapStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        PersistentMap persistentMap;
        int i;
        Object remove;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj2 = SnapshotStateMapKt.sync;
            synchronized (obj2) {
                StateMapStateRecord stateMapStateRecord = this.firstStateRecord;
                ResultKt.checkNotNull(stateMapStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
                StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) SnapshotKt.current(stateMapStateRecord);
                persistentMap = stateMapStateRecord2.map;
                i = stateMapStateRecord2.modification;
            }
            ResultKt.checkNotNull(persistentMap);
            PersistentMap.Builder builder = persistentMap.builder();
            remove = builder.remove(obj);
            PersistentMap build = builder.build();
            if (ResultKt.areEqual(build, persistentMap)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord3 = this.firstStateRecord;
            ResultKt.checkNotNull(stateMapStateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.SnapshotStateMap.StateMapStateRecord<K of androidx.compose.runtime.snapshots.SnapshotStateMap, V of androidx.compose.runtime.snapshots.SnapshotStateMap>");
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord4 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord3, this, currentSnapshot);
                synchronized (obj2) {
                    if (stateMapStateRecord4.modification == i) {
                        stateMapStateRecord4.setMap$runtime_release(build);
                        z = true;
                        stateMapStateRecord4.modification++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return remove;
    }

    @Override // java.util.Map
    public final int size() {
        AbstractMap abstractMap = (AbstractMap) getReadable$runtime_release().map;
        abstractMap.getClass();
        return ((PersistentHashMap) abstractMap).size;
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.values;
    }
}
