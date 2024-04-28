package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.collections.AbstractMutableSet;

/* loaded from: classes.dex */
public final class PersistentHashMapBuilderKeys extends AbstractMutableSet {
    public final /* synthetic */ int $r8$classId;
    public final PersistentHashMapBuilder builder;

    public PersistentHashMapBuilderKeys(int i, PersistentHashMapBuilder persistentHashMapBuilder) {
        this.$r8$classId = i;
        if (i != 1) {
            ResultKt.checkNotNullParameter(persistentHashMapBuilder, "builder");
            this.builder = persistentHashMapBuilder;
        } else {
            ResultKt.checkNotNullParameter(persistentHashMapBuilder, "builder");
            this.builder = persistentHashMapBuilder;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                ResultKt.checkNotNullParameter((Map.Entry) obj, "element");
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        int i = this.$r8$classId;
        PersistentHashMapBuilder persistentHashMapBuilder = this.builder;
        switch (i) {
            case 0:
                persistentHashMapBuilder.clear();
                return;
            default:
                persistentHashMapBuilder.clear();
                return;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return this.builder.containsKey(obj);
            default:
                return contains$androidx$compose$runtime$external$kotlinx$collections$immutable$implementations$immutableMap$AbstractMapBuilderEntries(obj);
        }
    }

    public final boolean contains$androidx$compose$runtime$external$kotlinx$collections$immutable$implementations$immutableMap$AbstractMapBuilderEntries(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        ResultKt.checkNotNullParameter(entry, "element");
        Object key = entry.getKey();
        PersistentHashMapBuilder persistentHashMapBuilder = this.builder;
        Object obj2 = persistentHashMapBuilder.get(key);
        return obj2 != null ? ResultKt.areEqual(obj2, entry.getValue()) : entry.getValue() == null && persistentHashMapBuilder.containsKey(entry.getKey());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int i = this.$r8$classId;
        PersistentHashMapBuilder persistentHashMapBuilder = this.builder;
        switch (i) {
            case 0:
                return new PersistentHashMapBuilderKeysIterator(0, persistentHashMapBuilder);
            default:
                return new PersistentHashMapBuilderEntriesIterator(persistentHashMapBuilder);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                PersistentHashMapBuilder persistentHashMapBuilder = this.builder;
                if (!persistentHashMapBuilder.containsKey(obj)) {
                    return false;
                }
                persistentHashMapBuilder.remove(obj);
                return true;
            default:
                return remove$androidx$compose$runtime$external$kotlinx$collections$immutable$implementations$immutableMap$AbstractMapBuilderEntries(obj);
        }
    }

    public final boolean remove$androidx$compose$runtime$external$kotlinx$collections$immutable$implementations$immutableMap$AbstractMapBuilderEntries(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        ResultKt.checkNotNullParameter(entry, "element");
        return this.builder.remove(entry.getKey(), entry.getValue());
    }
}
