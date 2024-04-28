package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.collections.AbstractCollection;

/* loaded from: classes.dex */
public final class PersistentHashMapValues extends AbstractCollection {
    public final PersistentHashMap map;

    public PersistentHashMapValues(PersistentHashMap persistentHashMap) {
        ResultKt.checkNotNullParameter(persistentHashMap, "map");
        this.map = persistentHashMap;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        PersistentHashMap persistentHashMap = this.map;
        persistentHashMap.getClass();
        return persistentHashMap.size;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new PersistentHashMapKeysIterator(this.map.node, 2);
    }
}
