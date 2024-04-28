package androidx.compose.runtime.snapshots;

import java.util.ListIterator;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class SubList$listIterator$1 implements ListIterator, KMappedMarker {
    public final /* synthetic */ Ref$IntRef $current;
    public final /* synthetic */ SubList this$0;

    public SubList$listIterator$1(Ref$IntRef ref$IntRef, SubList subList) {
        this.$current = ref$IntRef;
        this.this$0 = subList;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        Object obj2 = SnapshotStateListKt.sync;
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.$current.element < this.this$0.size - 1;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.$current.element >= 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        Ref$IntRef ref$IntRef = this.$current;
        int i = ref$IntRef.element + 1;
        SubList subList = this.this$0;
        SnapshotStateListKt.access$validateRange(i, subList.size);
        ref$IntRef.element = i;
        return subList.get(i);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.$current.element + 1;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        Ref$IntRef ref$IntRef = this.$current;
        int i = ref$IntRef.element;
        SubList subList = this.this$0;
        SnapshotStateListKt.access$validateRange(i, subList.size);
        ref$IntRef.element = i - 1;
        return subList.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.$current.element;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        Object obj = SnapshotStateListKt.sync;
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        Object obj2 = SnapshotStateListKt.sync;
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }
}
