package androidx.compose.runtime.snapshots;

import java.util.Set;
import kotlin.ResultKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMutableSet;

/* loaded from: classes.dex */
public abstract class SnapshotMapSet implements Set, KMutableSet {
    public final SnapshotStateMap map;

    public SnapshotMapSet(SnapshotStateMap snapshotStateMap) {
        ResultKt.checkNotNullParameter(snapshotStateMap, "map");
        this.map = snapshotStateMap;
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.map.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.map.size();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }
}
