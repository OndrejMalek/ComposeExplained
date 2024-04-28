package androidx.compose.runtime.internal;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.State;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PersistentCompositionLocalHashMap extends PersistentHashMap implements PersistentCompositionLocalMap {
    public static final PersistentCompositionLocalHashMap Empty;

    /* loaded from: classes.dex */
    public final class Builder extends PersistentHashMapBuilder {
        public PersistentCompositionLocalHashMap map;

        /* JADX DEBUG: Method merged with bridge method: build()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap; */
        /* JADX DEBUG: Method merged with bridge method: build()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMap; */
        @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap.Builder
        public final PersistentCompositionLocalHashMap build() {
            TrieNode trieNode = this.node;
            PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = this.map;
            if (trieNode != persistentCompositionLocalHashMap.node) {
                this.ownership = new ArtificialStackFrames(23);
                persistentCompositionLocalHashMap = new PersistentCompositionLocalHashMap(this.node, this.size);
            }
            this.map = persistentCompositionLocalHashMap;
            return persistentCompositionLocalHashMap;
        }

        @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof CompositionLocal) {
                return super.containsKey((CompositionLocal) obj);
            }
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsValue(Object obj) {
            if (obj instanceof State) {
                return super.containsValue((State) obj);
            }
            return false;
        }

        @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Object get(Object obj) {
            if (obj instanceof CompositionLocal) {
                return (State) super.get((CompositionLocal) obj);
            }
            return null;
        }

        @Override // java.util.Map
        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return !(obj instanceof CompositionLocal) ? obj2 : (State) super.getOrDefault((CompositionLocal) obj, (State) obj2);
        }

        @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Object remove(Object obj) {
            if (obj instanceof CompositionLocal) {
                return (State) super.remove((CompositionLocal) obj);
            }
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.internal.PersistentCompositionLocalHashMap, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap] */
    static {
        TrieNode trieNode = TrieNode.EMPTY;
        ResultKt.checkNotNull(trieNode, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<androidx.compose.runtime.CompositionLocal<kotlin.Any?>, androidx.compose.runtime.State<kotlin.Any?>>");
        Empty = new PersistentHashMap(trieNode, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentCompositionLocalHashMap(TrieNode trieNode, int i) {
        super(trieNode, i);
        ResultKt.checkNotNullParameter(trieNode, "node");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap$Builder, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, androidx.compose.runtime.internal.PersistentCompositionLocalHashMap$Builder] */
    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap
    public final PersistentMap.Builder builder() {
        ?? persistentHashMapBuilder = new PersistentHashMapBuilder(this);
        persistentHashMapBuilder.map = this;
        return persistentHashMapBuilder;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof CompositionLocal) {
            return super.containsKey((CompositionLocal) obj);
        }
        return false;
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof State) {
            return super.containsValue((State) obj);
        }
        return false;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap, java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj instanceof CompositionLocal) {
            return (State) super.get((CompositionLocal) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof CompositionLocal) ? obj2 : (State) super.getOrDefault((CompositionLocal) obj, (State) obj2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, androidx.compose.runtime.internal.PersistentCompositionLocalHashMap$Builder] */
    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap
    public final PersistentHashMapBuilder builder() {
        ?? persistentHashMapBuilder = new PersistentHashMapBuilder(this);
        persistentHashMapBuilder.map = this;
        return persistentHashMapBuilder;
    }
}
