package androidx.compose.runtime;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class SlotWriter$groupSlots$1 implements Iterator, KMappedMarker {
    public final /* synthetic */ int $end;
    public int current;
    public final /* synthetic */ SlotWriter this$0;

    public SlotWriter$groupSlots$1(int i, int i2, SlotWriter slotWriter) {
        this.$end = i2;
        this.this$0 = slotWriter;
        this.current = i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.current < this.$end;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            return null;
        }
        SlotWriter slotWriter = this.this$0;
        Object[] objArr = slotWriter.slots;
        int i = this.current;
        this.current = i + 1;
        return objArr[slotWriter.dataIndexToDataAddress(i)];
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
