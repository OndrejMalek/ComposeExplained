package androidx.compose.runtime.external.kotlinx.collections.immutable;

import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder;
import androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1;
import java.util.Collection;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public interface PersistentList extends ImmutableList, Collection, KMappedMarker {
    @Override // java.util.List
    PersistentList add(int i, Object obj);

    @Override // java.util.List, java.util.Collection
    PersistentList add(Object obj);

    @Override // java.util.List, java.util.Collection
    PersistentList addAll(Collection collection);

    PersistentVectorBuilder builder();

    PersistentList removeAll(SnapshotStateList$retainAll$1 snapshotStateList$retainAll$1);

    PersistentList removeAt(int i);

    @Override // java.util.List
    PersistentList set(int i, Object obj);
}
