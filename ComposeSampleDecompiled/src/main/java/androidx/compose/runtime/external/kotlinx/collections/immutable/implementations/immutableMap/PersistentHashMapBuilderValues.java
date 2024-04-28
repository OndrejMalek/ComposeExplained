package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMutableCollection;

/* loaded from: classes.dex */
public final class PersistentHashMapBuilderValues extends AbstractCollection implements Collection, KMutableCollection {
    public final PersistentHashMapBuilder builder;

    public PersistentHashMapBuilderValues(PersistentHashMapBuilder persistentHashMapBuilder) {
        ResultKt.checkNotNullParameter(persistentHashMapBuilder, "builder");
        this.builder = persistentHashMapBuilder;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.builder.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.builder.containsValue(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new PersistentHashMapBuilderKeysIterator(1, this.builder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.builder.size;
    }
}
