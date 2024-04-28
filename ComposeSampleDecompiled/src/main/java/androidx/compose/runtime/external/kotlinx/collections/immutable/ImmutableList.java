package androidx.compose.runtime.external.kotlinx.collections.immutable;

import _COROUTINE._BOUNDARY;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.AbstractCollection;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public interface ImmutableList extends List, Collection, KMappedMarker {

    /* loaded from: classes.dex */
    public final class SubList extends AbstractList implements ImmutableList {
        public final int _size;
        public final int fromIndex;
        public final ImmutableList source;

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList */
        /* JADX WARN: Multi-variable type inference failed */
        public SubList(ImmutableList immutableList, int i, int i2) {
            ResultKt.checkNotNullParameter(immutableList, "source");
            this.source = immutableList;
            this.fromIndex = i;
            _BOUNDARY.checkRangeIndexes$runtime_release(i, i2, ((AbstractCollection) immutableList).getSize());
            this._size = i2 - i;
        }

        @Override // java.util.List
        public final Object get(int i) {
            _BOUNDARY.checkElementIndex$runtime_release(i, this._size);
            return this.source.get(this.fromIndex + i);
        }

        @Override // kotlin.collections.AbstractCollection
        public final int getSize() {
            return this._size;
        }

        @Override // java.util.List
        public final List subList(int i, int i2) {
            _BOUNDARY.checkRangeIndexes$runtime_release(i, i2, this._size);
            int i3 = this.fromIndex;
            return new SubList(this.source, i + i3, i3 + i2);
        }
    }
}
