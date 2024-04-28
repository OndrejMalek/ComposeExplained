package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import java.util.ArrayList;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SlotReader {
    public boolean closed;
    public int currentEnd;
    public int currentGroup;
    public int currentSlot;
    public int currentSlotEnd;
    public int emptyCount;
    public final int[] groups;
    public final int groupsSize;
    public int parent;
    public final Object[] slots;
    public final int slotsSize;
    public final SlotTable table;

    public SlotReader(SlotTable slotTable) {
        ResultKt.checkNotNullParameter(slotTable, "table");
        this.table = slotTable;
        this.groups = slotTable.groups;
        int i = slotTable.groupsSize;
        this.groupsSize = i;
        this.slots = slotTable.slots;
        this.slotsSize = slotTable.slotsSize;
        this.currentEnd = i;
        this.parent = -1;
    }

    public final Anchor anchor(int i) {
        ArrayList arrayList = this.table.anchors;
        int search = ResultKt.search(arrayList, i, this.groupsSize);
        if (search < 0) {
            Anchor anchor = new Anchor(i);
            arrayList.add(-(search + 1), anchor);
            return anchor;
        }
        Object obj = arrayList.get(search);
        ResultKt.checkNotNullExpressionValue(obj, "get(location)");
        return (Anchor) obj;
    }

    public final Object aux(int[] iArr, int i) {
        int countOneBits;
        if (!ResultKt.access$hasAux(iArr, i)) {
            return Composer.Companion.Empty;
        }
        int i2 = i * 5;
        if (i2 >= iArr.length) {
            countOneBits = iArr.length;
        } else {
            countOneBits = ResultKt.countOneBits(iArr[i2 + 1] >> 29) + iArr[i2 + 4];
        }
        return this.slots[countOneBits];
    }

    public final void close() {
        this.closed = true;
        SlotTable slotTable = this.table;
        slotTable.getClass();
        int i = slotTable.readers;
        if (i > 0) {
            slotTable.readers = i - 1;
        } else {
            EffectsKt.composeRuntimeError("Unexpected reader close()".toString());
            throw null;
        }
    }

    public final void endGroup() {
        if (this.emptyCount == 0) {
            if (this.currentGroup != this.currentEnd) {
                EffectsKt.composeRuntimeError("endGroup() not called at the end of a group".toString());
                throw null;
            }
            int i = this.parent;
            int[] iArr = this.groups;
            int access$parentAnchor = ResultKt.access$parentAnchor(iArr, i);
            this.parent = access$parentAnchor;
            this.currentEnd = access$parentAnchor < 0 ? this.groupsSize : access$parentAnchor + iArr[(access$parentAnchor * 5) + 3];
        }
    }

    public final Object getGroupAux() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return aux(this.groups, i);
        }
        return 0;
    }

    public final int getGroupKey() {
        int i = this.currentGroup;
        if (i >= this.currentEnd) {
            return 0;
        }
        return this.groups[i * 5];
    }

    public final Object groupGet(int i, int i2) {
        int[] iArr = this.groups;
        int access$slotAnchor = ResultKt.access$slotAnchor(iArr, i);
        int i3 = i + 1;
        int i4 = access$slotAnchor + i2;
        return i4 < (i3 < this.groupsSize ? iArr[(i3 * 5) + 4] : this.slotsSize) ? this.slots[i4] : Composer.Companion.Empty;
    }

    public final Object node(int i) {
        int[] iArr = this.groups;
        if (!ResultKt.access$isNode(iArr, i)) {
            return null;
        }
        if (!ResultKt.access$isNode(iArr, i)) {
            return Composer.Companion.Empty;
        }
        return this.slots[iArr[(i * 5) + 4]];
    }

    public final Object objectKey(int[] iArr, int i) {
        if (!ResultKt.access$hasObjectKey(iArr, i)) {
            return null;
        }
        int i2 = i * 5;
        return this.slots[ResultKt.countOneBits(iArr[i2 + 1] >> 30) + iArr[i2 + 4]];
    }

    public final void reposition(int i) {
        if (this.emptyCount != 0) {
            EffectsKt.composeRuntimeError("Cannot reposition while in an empty region".toString());
            throw null;
        }
        this.currentGroup = i;
        int[] iArr = this.groups;
        int i2 = this.groupsSize;
        int access$parentAnchor = i < i2 ? ResultKt.access$parentAnchor(iArr, i) : -1;
        this.parent = access$parentAnchor;
        if (access$parentAnchor < 0) {
            this.currentEnd = i2;
        } else {
            this.currentEnd = ResultKt.access$groupSize(iArr, access$parentAnchor) + access$parentAnchor;
        }
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final int skipGroup() {
        if (this.emptyCount != 0) {
            EffectsKt.composeRuntimeError("Cannot skip while in an empty region".toString());
            throw null;
        }
        int i = this.currentGroup;
        int[] iArr = this.groups;
        int access$nodeCount = ResultKt.access$isNode(iArr, i) ? 1 : ResultKt.access$nodeCount(iArr, this.currentGroup);
        int i2 = this.currentGroup;
        this.currentGroup = iArr[(i2 * 5) + 3] + i2;
        return access$nodeCount;
    }

    public final void skipToGroupEnd() {
        if (this.emptyCount == 0) {
            this.currentGroup = this.currentEnd;
        } else {
            EffectsKt.composeRuntimeError("Cannot skip the enclosing group while in an empty region".toString());
            throw null;
        }
    }

    public final void startGroup() {
        if (this.emptyCount <= 0) {
            int i = this.currentGroup;
            int[] iArr = this.groups;
            if (ResultKt.access$parentAnchor(iArr, i) != this.parent) {
                throw new IllegalArgumentException("Invalid slot table detected".toString());
            }
            int i2 = this.currentGroup;
            this.parent = i2;
            this.currentEnd = ResultKt.access$groupSize(iArr, i2) + i2;
            int i3 = this.currentGroup;
            int i4 = i3 + 1;
            this.currentGroup = i4;
            this.currentSlot = ResultKt.access$slotAnchor(iArr, i3);
            this.currentSlotEnd = i3 >= this.groupsSize + (-1) ? this.slotsSize : ResultKt.access$dataAnchor(iArr, i4);
        }
    }

    public final String toString() {
        return "SlotReader(current=" + this.currentGroup + ", key=" + getGroupKey() + ", parent=" + this.parent + ", end=" + this.currentEnd + ')';
    }
}
