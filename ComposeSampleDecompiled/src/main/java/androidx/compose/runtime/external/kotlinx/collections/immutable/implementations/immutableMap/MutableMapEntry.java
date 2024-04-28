package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMutableMap;

/* loaded from: classes.dex */
public final class MutableMapEntry extends MapEntry implements KMutableMap.Entry {
    public final PersistentHashMapBuilderEntriesIterator parentIterator;
    public Object value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableMapEntry(PersistentHashMapBuilderEntriesIterator persistentHashMapBuilderEntriesIterator, Object obj, Object obj2) {
        super(obj, obj2);
        ResultKt.checkNotNullParameter(persistentHashMapBuilderEntriesIterator, "parentIterator");
        this.parentIterator = persistentHashMapBuilderEntriesIterator;
        this.value = obj2;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.MapEntry, java.util.Map.Entry
    public final Object getValue() {
        return this.value;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.MapEntry, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.value;
        this.value = obj;
        PersistentHashMapBuilderBaseIterator persistentHashMapBuilderBaseIterator = this.parentIterator.base;
        PersistentHashMapBuilder persistentHashMapBuilder = persistentHashMapBuilderBaseIterator.builder;
        Object obj3 = this.key;
        if (persistentHashMapBuilder.containsKey(obj3)) {
            boolean z = persistentHashMapBuilderBaseIterator.hasNext;
            if (!z) {
                persistentHashMapBuilder.put(obj3, obj);
            } else {
                if (!z) {
                    throw new NoSuchElementException();
                }
                TrieNodeBaseIterator trieNodeBaseIterator = persistentHashMapBuilderBaseIterator.path[persistentHashMapBuilderBaseIterator.pathLastIndex];
                Object obj4 = trieNodeBaseIterator.buffer[trieNodeBaseIterator.index];
                persistentHashMapBuilder.put(obj3, obj);
                persistentHashMapBuilderBaseIterator.resetPath(obj4 != null ? obj4.hashCode() : 0, persistentHashMapBuilder.node, obj4, 0);
            }
            persistentHashMapBuilderBaseIterator.expectedModCount = persistentHashMapBuilder.modCount;
        }
        return obj2;
    }
}
