package androidx.compose.runtime;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class GroupIterator implements Iterator, KMappedMarker {
    public final int end;
    public int index;
    public final SlotTable table;
    public final int version;

    public GroupIterator(int i, int i2, SlotTable slotTable) {
        ResultKt.checkNotNullParameter(slotTable, "table");
        this.table = slotTable;
        this.end = i2;
        this.index = i;
        this.version = slotTable.version;
        if (slotTable.writer) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.index < this.end;
    }

    @Override // java.util.Iterator
    public final Object next() {
        SlotTable slotTable = this.table;
        int i = slotTable.version;
        int i2 = this.version;
        if (i != i2) {
            throw new ConcurrentModificationException();
        }
        int i3 = this.index;
        this.index = ResultKt.access$groupSize(slotTable.groups, i3) + i3;
        return new SlotTableGroup(i3, i2, slotTable);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
