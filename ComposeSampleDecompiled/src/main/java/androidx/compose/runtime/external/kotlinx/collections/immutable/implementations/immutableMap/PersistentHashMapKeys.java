package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableSet;
import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.collections.AbstractSet;

/* loaded from: classes.dex */
public final class PersistentHashMapKeys extends AbstractSet implements ImmutableSet {
    public final /* synthetic */ int $r8$classId;
    public final PersistentHashMap map;

    public PersistentHashMapKeys(PersistentHashMap persistentHashMap, int i) {
        this.$r8$classId = i;
        if (i != 1) {
            ResultKt.checkNotNullParameter(persistentHashMap, "map");
            this.map = persistentHashMap;
        } else {
            ResultKt.checkNotNullParameter(persistentHashMap, "map");
            this.map = persistentHashMap;
        }
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        int i = this.$r8$classId;
        PersistentHashMap persistentHashMap = this.map;
        switch (i) {
            case 0:
                return persistentHashMap.containsKey(obj);
            default:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                ResultKt.checkNotNullParameter(entry, "element");
                Object obj2 = persistentHashMap.get(entry.getKey());
                return obj2 != null ? ResultKt.areEqual(obj2, entry.getValue()) : entry.getValue() == null && persistentHashMap.containsKey(entry.getKey());
        }
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        int i = this.$r8$classId;
        PersistentHashMap persistentHashMap = this.map;
        switch (i) {
            case 0:
                persistentHashMap.getClass();
                return persistentHashMap.size;
            default:
                persistentHashMap.getClass();
                return persistentHashMap.size;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int i = this.$r8$classId;
        PersistentHashMap persistentHashMap = this.map;
        switch (i) {
            case 0:
                return new PersistentHashMapKeysIterator(persistentHashMap.node, 0);
            default:
                return new PersistentHashMapKeysIterator(persistentHashMap.node, 1);
        }
    }
}
