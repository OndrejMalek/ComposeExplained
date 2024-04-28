package kotlin.collections;

import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilderKeys;
import java.util.Set;
import kotlin.jvm.internal.markers.KMutableSet;

/* loaded from: classes.dex */
public abstract class AbstractMutableSet extends java.util.AbstractSet implements Set, KMutableSet {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        PersistentHashMapBuilderKeys persistentHashMapBuilderKeys = (PersistentHashMapBuilderKeys) this;
        int i = persistentHashMapBuilderKeys.$r8$classId;
        PersistentHashMapBuilder persistentHashMapBuilder = persistentHashMapBuilderKeys.builder;
        switch (i) {
            case 0:
                return persistentHashMapBuilder.size;
            default:
                return persistentHashMapBuilder.size;
        }
    }
}
