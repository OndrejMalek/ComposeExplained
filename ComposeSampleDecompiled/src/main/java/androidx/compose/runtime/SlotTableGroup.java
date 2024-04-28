package androidx.compose.runtime;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class SlotTableGroup implements Iterable, KMappedMarker {
    public final int group;
    public final SlotTable table;
    public final int version;

    public SlotTableGroup(int i, int i2, SlotTable slotTable) {
        ResultKt.checkNotNullParameter(slotTable, "table");
        this.table = slotTable;
        this.group = i;
        this.version = i2;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        SlotTable slotTable = this.table;
        if (slotTable.version != this.version) {
            throw new ConcurrentModificationException();
        }
        int i = this.group;
        return new GroupIterator(i + 1, ResultKt.access$groupSize(slotTable.groups, i) + i, slotTable);
    }
}
