package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap;
import kotlinx.coroutines.flow.SafeFlow;

/* loaded from: classes.dex */
public class PersistentHashMap extends AbstractMap implements PersistentMap {
    public static final PersistentHashMap EMPTY = new PersistentHashMap(TrieNode.EMPTY, 0);
    public final TrieNode node;
    public final int size;

    public PersistentHashMap(TrieNode trieNode, int i) {
        ResultKt.checkNotNullParameter(trieNode, "node");
        this.node = trieNode;
        this.size = i;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.node.containsKey(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.node.get(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    public final PersistentHashMap put(Object obj, Links links) {
        SafeFlow put = this.node.put(obj != null ? obj.hashCode() : 0, 0, obj, links);
        return put == null ? this : new PersistentHashMap((TrieNode) put.block, this.size + put.$r8$classId);
    }

    /* JADX DEBUG: Method merged with bridge method: builder()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap$Builder; */
    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap
    public PersistentHashMapBuilder builder() {
        return new PersistentHashMapBuilder(this);
    }
}
