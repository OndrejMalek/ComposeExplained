package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMutableMap;

/* loaded from: classes.dex */
public class PersistentHashMapBuilder extends AbstractMap implements PersistentMap.Builder, Map, KMutableMap {
    public PersistentHashMap map;
    public int modCount;
    public TrieNode node;
    public Object operationResult;
    public ArtificialStackFrames ownership;
    public int size;

    public PersistentHashMapBuilder(PersistentHashMap persistentHashMap) {
        ResultKt.checkNotNullParameter(persistentHashMap, "map");
        this.map = persistentHashMap;
        this.ownership = new ArtificialStackFrames(23);
        this.node = persistentHashMap.node;
        this.size = persistentHashMap.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        TrieNode trieNode = TrieNode.EMPTY;
        ResultKt.checkNotNull(trieNode, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        this.node = trieNode;
        setSize(0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return this.node.containsKey(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    /* JADX DEBUG: Method merged with bridge method: entrySet()Ljava/util/Set; */
    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: entrySet$kotlin$collections$AbstractMutableMap, reason: merged with bridge method [inline-methods] */
    public final Set entrySet() {
        return new PersistentHashMapBuilderKeys(1, this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        return this.node.get(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    /* JADX DEBUG: Method merged with bridge method: keySet()Ljava/util/Set; */
    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: keySet$kotlin$collections$AbstractMutableMap, reason: merged with bridge method [inline-methods] */
    public final Set keySet() {
        return new PersistentHashMapBuilderKeys(0, this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.operationResult = null;
        this.node = this.node.mutablePut(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        return this.operationResult;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter, java.lang.Object] */
    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        ResultKt.checkNotNullParameter(map, "from");
        PersistentHashMap persistentHashMap = null;
        PersistentHashMap persistentHashMap2 = map instanceof PersistentHashMap ? (PersistentHashMap) map : null;
        if (persistentHashMap2 == null) {
            PersistentHashMapBuilder persistentHashMapBuilder = map instanceof PersistentHashMapBuilder ? (PersistentHashMapBuilder) map : null;
            if (persistentHashMapBuilder != null) {
                persistentHashMap = persistentHashMapBuilder.build();
            }
        } else {
            persistentHashMap = persistentHashMap2;
        }
        if (persistentHashMap == null) {
            super.putAll(map);
            return;
        }
        ?? obj = new Object();
        obj.count = 0;
        int i = this.size;
        TrieNode trieNode = this.node;
        TrieNode trieNode2 = persistentHashMap.node;
        ResultKt.checkNotNull(trieNode2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        this.node = trieNode.mutablePutAll(trieNode2, 0, obj, this);
        int i2 = (persistentHashMap.size + i) - obj.count;
        if (i != i2) {
            setSize(i2);
        }
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        int i = this.size;
        TrieNode mutableRemove = this.node.mutableRemove(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        if (mutableRemove == null) {
            mutableRemove = TrieNode.EMPTY;
            ResultKt.checkNotNull(mutableRemove, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        }
        this.node = mutableRemove;
        return i != this.size;
    }

    public final void setSize(int i) {
        this.size = i;
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return new PersistentHashMapBuilderValues(this);
    }

    /* JADX DEBUG: Method merged with bridge method: build()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap; */
    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap.Builder
    public PersistentHashMap build() {
        TrieNode trieNode = this.node;
        PersistentHashMap persistentHashMap = this.map;
        if (trieNode != persistentHashMap.node) {
            this.ownership = new ArtificialStackFrames(23);
            persistentHashMap = new PersistentHashMap(this.node, this.size);
        }
        this.map = persistentHashMap;
        return persistentHashMap;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        this.operationResult = null;
        TrieNode mutableRemove = this.node.mutableRemove(obj != null ? obj.hashCode() : 0, obj, 0, this);
        if (mutableRemove == null) {
            mutableRemove = TrieNode.EMPTY;
            ResultKt.checkNotNull(mutableRemove, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        }
        this.node = mutableRemove;
        return this.operationResult;
    }
}
