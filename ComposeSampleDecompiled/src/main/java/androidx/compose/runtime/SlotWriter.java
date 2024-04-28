package androidx.compose.runtime;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.runtime.Composer;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt___MapsJvmKt;

/* loaded from: classes.dex */
public final class SlotWriter {
    public ArrayList anchors;
    public boolean closed;
    public int currentGroup;
    public int currentGroupEnd;
    public int currentSlot;
    public int currentSlotEnd;
    public final IntStack endStack;
    public int groupGapLen;
    public int groupGapStart;
    public int[] groups;
    public int insertCount;
    public int nodeCount;
    public final IntStack nodeCountStack;
    public int parent;
    public Stack pendingRecalculateMarks;
    public Object[] slots;
    public int slotsGapLen;
    public int slotsGapOwner;
    public int slotsGapStart;
    public final IntStack startStack;
    public final SlotTable table;

    public SlotWriter(SlotTable slotTable) {
        ResultKt.checkNotNullParameter(slotTable, "table");
        this.table = slotTable;
        int[] iArr = slotTable.groups;
        this.groups = iArr;
        Object[] objArr = slotTable.slots;
        this.slots = objArr;
        this.anchors = slotTable.anchors;
        int i = slotTable.groupsSize;
        this.groupGapStart = i;
        this.groupGapLen = (iArr.length / 5) - i;
        this.currentGroupEnd = i;
        int i2 = slotTable.slotsSize;
        this.slotsGapStart = i2;
        this.slotsGapLen = objArr.length - i2;
        this.slotsGapOwner = i;
        this.startStack = new IntStack();
        this.endStack = new IntStack();
        this.nodeCountStack = new IntStack();
        this.parent = -1;
    }

    public static int dataIndexToDataAnchor(int i, int i2, int i3, int i4) {
        return i > i2 ? -(((i4 - i3) - i) + 1) : i;
    }

    public final void advanceBy(int i) {
        if (!(i >= 0)) {
            EffectsKt.composeRuntimeError("Cannot seek backwards".toString());
            throw null;
        }
        if (this.insertCount > 0) {
            throw new IllegalStateException("Cannot call seek() while inserting".toString());
        }
        if (i == 0) {
            return;
        }
        int i2 = this.currentGroup + i;
        if (i2 >= this.parent && i2 <= this.currentGroupEnd) {
            this.currentGroup = i2;
            int dataIndex = dataIndex(this.groups, groupIndexToAddress(i2));
            this.currentSlot = dataIndex;
            this.currentSlotEnd = dataIndex;
            return;
        }
        EffectsKt.composeRuntimeError(("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')').toString());
        throw null;
    }

    public final Anchor anchor(int i) {
        ArrayList arrayList = this.anchors;
        int search = ResultKt.search(arrayList, i, getSize$runtime_release());
        if (search >= 0) {
            Object obj = arrayList.get(search);
            ResultKt.checkNotNullExpressionValue(obj, "get(location)");
            return (Anchor) obj;
        }
        if (i > this.groupGapStart) {
            i = -(getSize$runtime_release() - i);
        }
        Anchor anchor = new Anchor(i);
        arrayList.add(-(search + 1), anchor);
        return anchor;
    }

    public final int anchorIndex(Anchor anchor) {
        ResultKt.checkNotNullParameter(anchor, "anchor");
        int i = anchor.location;
        return i < 0 ? i + getSize$runtime_release() : i;
    }

    public final int auxIndex(int[] iArr, int i) {
        return ResultKt.countOneBits(iArr[(i * 5) + 1] >> 29) + dataIndex(iArr, i);
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
        }
    }

    public final void close() {
        this.closed = true;
        if (this.startStack.tos == 0) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            int i = this.slotsGapStart;
            MapsKt___MapsJvmKt.fill(i, this.slotsGapLen + i, this.slots);
            recalculateMarks();
        }
        int[] iArr = this.groups;
        int i2 = this.groupGapStart;
        Object[] objArr = this.slots;
        int i3 = this.slotsGapStart;
        ArrayList arrayList = this.anchors;
        SlotTable slotTable = this.table;
        slotTable.getClass();
        ResultKt.checkNotNullParameter(iArr, "groups");
        ResultKt.checkNotNullParameter(objArr, "slots");
        ResultKt.checkNotNullParameter(arrayList, "anchors");
        if (!slotTable.writer) {
            throw new IllegalArgumentException("Unexpected writer close()".toString());
        }
        slotTable.writer = false;
        slotTable.groups = iArr;
        slotTable.groupsSize = i2;
        slotTable.slots = objArr;
        slotTable.slotsSize = i3;
        slotTable.anchors = arrayList;
    }

    public final int dataIndex(int[] iArr, int i) {
        if (i >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        int access$dataAnchor = ResultKt.access$dataAnchor(iArr, i);
        return access$dataAnchor < 0 ? (this.slots.length - this.slotsGapLen) + access$dataAnchor + 1 : access$dataAnchor;
    }

    public final int dataIndexToDataAddress(int i) {
        return i < this.slotsGapStart ? i : i + this.slotsGapLen;
    }

    public final void endGroup() {
        boolean z = this.insertCount > 0;
        int i = this.currentGroup;
        int i2 = this.currentGroupEnd;
        int i3 = this.parent;
        int groupIndexToAddress = groupIndexToAddress(i3);
        int i4 = this.nodeCount;
        int i5 = i - i3;
        boolean access$isNode = ResultKt.access$isNode(this.groups, groupIndexToAddress);
        IntStack intStack = this.nodeCountStack;
        if (z) {
            ResultKt.access$updateGroupSize(this.groups, groupIndexToAddress, i5);
            ResultKt.access$updateNodeCount(this.groups, groupIndexToAddress, i4);
            this.nodeCount = intStack.pop() + (access$isNode ? 1 : i4);
            this.parent = parent(this.groups, i3);
            return;
        }
        if (i != i2) {
            EffectsKt.composeRuntimeError("Expected to be at the end of a group".toString());
            throw null;
        }
        int access$groupSize = ResultKt.access$groupSize(this.groups, groupIndexToAddress);
        int access$nodeCount = ResultKt.access$nodeCount(this.groups, groupIndexToAddress);
        ResultKt.access$updateGroupSize(this.groups, groupIndexToAddress, i5);
        ResultKt.access$updateNodeCount(this.groups, groupIndexToAddress, i4);
        int pop = this.startStack.pop();
        this.currentGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.parent = pop;
        int parent = parent(this.groups, i3);
        int pop2 = intStack.pop();
        this.nodeCount = pop2;
        if (parent == pop) {
            this.nodeCount = pop2 + (access$isNode ? 0 : i4 - access$nodeCount);
            return;
        }
        int i6 = i5 - access$groupSize;
        int i7 = access$isNode ? 0 : i4 - access$nodeCount;
        if (i6 != 0 || i7 != 0) {
            while (parent != 0 && parent != pop && (i7 != 0 || i6 != 0)) {
                int groupIndexToAddress2 = groupIndexToAddress(parent);
                if (i6 != 0) {
                    ResultKt.access$updateGroupSize(this.groups, groupIndexToAddress2, ResultKt.access$groupSize(this.groups, groupIndexToAddress2) + i6);
                }
                if (i7 != 0) {
                    int[] iArr = this.groups;
                    ResultKt.access$updateNodeCount(iArr, groupIndexToAddress2, ResultKt.access$nodeCount(iArr, groupIndexToAddress2) + i7);
                }
                if (ResultKt.access$isNode(this.groups, groupIndexToAddress2)) {
                    i7 = 0;
                }
                parent = parent(this.groups, parent);
            }
        }
        this.nodeCount += i7;
    }

    public final void endInsert() {
        int i;
        int i2;
        int i3 = this.insertCount;
        if (i3 <= 0) {
            throw new IllegalStateException("Unbalanced begin/end insert".toString());
        }
        int i4 = i3 - 1;
        this.insertCount = i4;
        if (i4 == 0) {
            IntStack intStack = this.nodeCountStack;
            switch (intStack.$r8$classId) {
                case 0:
                    i = intStack.tos;
                    break;
                default:
                    i = intStack.tos;
                    break;
            }
            IntStack intStack2 = this.startStack;
            switch (intStack2.$r8$classId) {
                case 0:
                    i2 = intStack2.tos;
                    break;
                default:
                    i2 = intStack2.tos;
                    break;
            }
            if (i == i2) {
                this.currentGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
            } else {
                EffectsKt.composeRuntimeError("startGroup/endGroup mismatch while inserting".toString());
                throw null;
            }
        }
    }

    public final void ensureStarted(int i) {
        if (!(this.insertCount <= 0)) {
            EffectsKt.composeRuntimeError("Cannot call ensureStarted() while inserting".toString());
            throw null;
        }
        int i2 = this.parent;
        if (i2 != i) {
            if (i < i2 || i >= this.currentGroupEnd) {
                EffectsKt.composeRuntimeError(("Started group at " + i + " must be a subgroup of the group at " + i2).toString());
                throw null;
            }
            int i3 = this.currentGroup;
            int i4 = this.currentSlot;
            int i5 = this.currentSlotEnd;
            this.currentGroup = i;
            startGroup();
            this.currentGroup = i3;
            this.currentSlot = i4;
            this.currentSlotEnd = i5;
        }
    }

    public final void fixParentAnchorsFor(int i, int i2, int i3) {
        if (i >= this.groupGapStart) {
            i = -((getSize$runtime_release() - i) + 2);
        }
        while (i3 < i2) {
            this.groups[(groupIndexToAddress(i3) * 5) + 2] = i;
            int access$groupSize = ResultKt.access$groupSize(this.groups, groupIndexToAddress(i3)) + i3;
            fixParentAnchorsFor(i3, access$groupSize, i3 + 1);
            i3 = access$groupSize;
        }
    }

    public final int getCapacity() {
        return this.groups.length / 5;
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    public final int groupIndexToAddress(int i) {
        return i < this.groupGapStart ? i : i + this.groupGapLen;
    }

    public final int groupSize(int i) {
        return ResultKt.access$groupSize(this.groups, groupIndexToAddress(i));
    }

    public final void insertGroups(int i) {
        if (i > 0) {
            int i2 = this.currentGroup;
            moveGroupGapTo(i2);
            int i3 = this.groupGapStart;
            int i4 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i5 = length - i4;
            if (i4 < i) {
                int max = Math.max(Math.max(length * 2, i5 + i), 32);
                int[] iArr2 = new int[max * 5];
                int i6 = max - i5;
                MapsKt___MapsJvmKt.copyInto(iArr, iArr2, 0, 0, i3 * 5);
                MapsKt___MapsJvmKt.copyInto(iArr, iArr2, (i3 + i6) * 5, (i4 + i3) * 5, length * 5);
                this.groups = iArr2;
                i4 = i6;
            }
            int i7 = this.currentGroupEnd;
            if (i7 >= i3) {
                this.currentGroupEnd = i7 + i;
            }
            int i8 = i3 + i;
            this.groupGapStart = i8;
            this.groupGapLen = i4 - i;
            int dataIndexToDataAnchor = dataIndexToDataAnchor(i5 > 0 ? dataIndex(this.groups, groupIndexToAddress(i2 + i)) : 0, this.slotsGapOwner >= i3 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            for (int i9 = i3; i9 < i8; i9++) {
                this.groups[(i9 * 5) + 4] = dataIndexToDataAnchor;
            }
            int i10 = this.slotsGapOwner;
            if (i10 >= i3) {
                this.slotsGapOwner = i10 + i;
            }
        }
    }

    public final void insertSlots(int i, int i2) {
        if (i > 0) {
            moveSlotGapTo(this.currentSlot, i2);
            int i3 = this.slotsGapStart;
            int i4 = this.slotsGapLen;
            if (i4 < i) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i5 = length - i4;
                int max = Math.max(Math.max(length * 2, i5 + i), 32);
                Object[] objArr2 = new Object[max];
                for (int i6 = 0; i6 < max; i6++) {
                    objArr2[i6] = null;
                }
                int i7 = max - i5;
                MapsKt___MapsJvmKt.copyInto(objArr, objArr2, 0, 0, i3);
                MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i3 + i7, i4 + i3, length);
                this.slots = objArr2;
                i4 = i7;
            }
            int i8 = this.currentSlotEnd;
            if (i8 >= i3) {
                this.currentSlotEnd = i8 + i;
            }
            this.slotsGapStart = i3 + i;
            this.slotsGapLen = i4 - i;
        }
    }

    public final void moveFrom(SlotTable slotTable, int i) {
        ResultKt.checkNotNullParameter(slotTable, "table");
        EffectsKt.runtimeCheck(this.insertCount > 0);
        if (i == 0 && this.currentGroup == 0 && this.table.groupsSize == 0) {
            int access$groupSize = ResultKt.access$groupSize(slotTable.groups, i);
            int i2 = slotTable.groupsSize;
            if (access$groupSize == i2) {
                int[] iArr = this.groups;
                Object[] objArr = this.slots;
                ArrayList arrayList = this.anchors;
                int[] iArr2 = slotTable.groups;
                Object[] objArr2 = slotTable.slots;
                int i3 = slotTable.slotsSize;
                this.groups = iArr2;
                this.slots = objArr2;
                this.anchors = slotTable.anchors;
                this.groupGapStart = i2;
                this.groupGapLen = (iArr2.length / 5) - i2;
                this.slotsGapStart = i3;
                this.slotsGapLen = objArr2.length - i3;
                this.slotsGapOwner = i2;
                ResultKt.checkNotNullParameter(iArr, "groups");
                ResultKt.checkNotNullParameter(objArr, "slots");
                ResultKt.checkNotNullParameter(arrayList, "anchors");
                slotTable.groups = iArr;
                slotTable.groupsSize = 0;
                slotTable.slots = objArr;
                slotTable.slotsSize = 0;
                slotTable.anchors = arrayList;
                return;
            }
        }
        SlotWriter openWriter = slotTable.openWriter();
        try {
            ArtificialStackFrames.moveGroup(openWriter, i, this, true, true, false);
        } finally {
            openWriter.close();
        }
    }

    public final void moveGroupGapTo(int i) {
        int i2;
        int i3 = this.groupGapLen;
        int i4 = this.groupGapStart;
        if (i4 != i) {
            if (!this.anchors.isEmpty()) {
                int capacity = getCapacity() - this.groupGapLen;
                if (i4 >= i) {
                    for (int access$locationOf = ResultKt.access$locationOf(this.anchors, i, capacity); access$locationOf < this.anchors.size(); access$locationOf++) {
                        Object obj = this.anchors.get(access$locationOf);
                        ResultKt.checkNotNullExpressionValue(obj, "anchors[index]");
                        Anchor anchor = (Anchor) obj;
                        int i5 = anchor.location;
                        if (i5 < 0) {
                            break;
                        }
                        anchor.location = -(capacity - i5);
                    }
                } else {
                    for (int access$locationOf2 = ResultKt.access$locationOf(this.anchors, i4, capacity); access$locationOf2 < this.anchors.size(); access$locationOf2++) {
                        Object obj2 = this.anchors.get(access$locationOf2);
                        ResultKt.checkNotNullExpressionValue(obj2, "anchors[index]");
                        Anchor anchor2 = (Anchor) obj2;
                        int i6 = anchor2.location;
                        if (i6 >= 0 || (i2 = i6 + capacity) >= i) {
                            break;
                        }
                        anchor2.location = i2;
                    }
                }
            }
            if (i3 > 0) {
                int[] iArr = this.groups;
                int i7 = i * 5;
                int i8 = i3 * 5;
                int i9 = i4 * 5;
                if (i < i4) {
                    MapsKt___MapsJvmKt.copyInto(iArr, iArr, i8 + i7, i7, i9);
                } else {
                    MapsKt___MapsJvmKt.copyInto(iArr, iArr, i9, i9 + i8, i7 + i8);
                }
            }
            if (i < i4) {
                i4 = i + i3;
            }
            int capacity2 = getCapacity();
            EffectsKt.runtimeCheck(i4 < capacity2);
            while (i4 < capacity2) {
                int access$parentAnchor = ResultKt.access$parentAnchor(this.groups, i4);
                int size$runtime_release = access$parentAnchor > -2 ? access$parentAnchor : (getSize$runtime_release() + access$parentAnchor) - (-2);
                if (size$runtime_release >= i) {
                    size$runtime_release = -((getSize$runtime_release() - size$runtime_release) - (-2));
                }
                if (size$runtime_release != access$parentAnchor) {
                    this.groups[(i4 * 5) + 2] = size$runtime_release;
                }
                i4++;
                if (i4 == i) {
                    i4 += i3;
                }
            }
        }
        this.groupGapStart = i;
    }

    public final void moveSlotGapTo(int i, int i2) {
        int i3 = this.slotsGapLen;
        int i4 = this.slotsGapStart;
        int i5 = this.slotsGapOwner;
        if (i4 != i) {
            Object[] objArr = this.slots;
            if (i < i4) {
                MapsKt___MapsJvmKt.copyInto(objArr, objArr, i + i3, i, i4);
            } else {
                MapsKt___MapsJvmKt.copyInto(objArr, objArr, i4, i4 + i3, i + i3);
            }
        }
        int min = Math.min(i2 + 1, getSize$runtime_release());
        if (i5 != min) {
            int length = this.slots.length - i3;
            if (min < i5) {
                int groupIndexToAddress = groupIndexToAddress(min);
                int groupIndexToAddress2 = groupIndexToAddress(i5);
                int i6 = this.groupGapStart;
                while (groupIndexToAddress < groupIndexToAddress2) {
                    int access$dataAnchor = ResultKt.access$dataAnchor(this.groups, groupIndexToAddress);
                    if (access$dataAnchor < 0) {
                        EffectsKt.composeRuntimeError("Unexpected anchor value, expected a positive anchor".toString());
                        throw null;
                    }
                    this.groups[(groupIndexToAddress * 5) + 4] = -((length - access$dataAnchor) + 1);
                    groupIndexToAddress++;
                    if (groupIndexToAddress == i6) {
                        groupIndexToAddress += this.groupGapLen;
                    }
                }
            } else {
                int groupIndexToAddress3 = groupIndexToAddress(i5);
                int groupIndexToAddress4 = groupIndexToAddress(min);
                while (groupIndexToAddress3 < groupIndexToAddress4) {
                    int access$dataAnchor2 = ResultKt.access$dataAnchor(this.groups, groupIndexToAddress3);
                    if (access$dataAnchor2 >= 0) {
                        EffectsKt.composeRuntimeError("Unexpected anchor value, expected a negative anchor".toString());
                        throw null;
                    }
                    this.groups[(groupIndexToAddress3 * 5) + 4] = access$dataAnchor2 + length + 1;
                    groupIndexToAddress3++;
                    if (groupIndexToAddress3 == this.groupGapStart) {
                        groupIndexToAddress3 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = min;
        }
        this.slotsGapStart = i;
    }

    public final int parent(int[] iArr, int i) {
        int access$parentAnchor = ResultKt.access$parentAnchor(iArr, groupIndexToAddress(i));
        return access$parentAnchor > -2 ? access$parentAnchor : getSize$runtime_release() + access$parentAnchor + 2;
    }

    public final void recalculateMarks() {
        boolean z;
        Stack stack = this.pendingRecalculateMarks;
        if (stack != null) {
            while (stack.isNotEmpty()) {
                int takeMax = stack.takeMax();
                int groupIndexToAddress = groupIndexToAddress(takeMax);
                int i = takeMax + 1;
                int groupSize = groupSize(takeMax) + takeMax;
                while (true) {
                    if (i >= groupSize) {
                        z = false;
                        break;
                    } else {
                        if ((this.groups[(groupIndexToAddress(i) * 5) + 1] & 201326592) != 0) {
                            z = true;
                            break;
                        }
                        i += groupSize(i);
                    }
                }
                if (ResultKt.access$containsMark(this.groups, groupIndexToAddress) != z) {
                    int[] iArr = this.groups;
                    int i2 = (groupIndexToAddress * 5) + 1;
                    if (z) {
                        iArr[i2] = iArr[i2] | 67108864;
                    } else {
                        iArr[i2] = iArr[i2] & (-67108865);
                    }
                    int parent = parent(iArr, takeMax);
                    if (parent >= 0) {
                        stack.add$1(parent);
                    }
                }
            }
        }
    }

    public final boolean removeGroup() {
        if (this.insertCount != 0) {
            EffectsKt.composeRuntimeError("Cannot remove group while inserting".toString());
            throw null;
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int skipGroup = skipGroup();
        Stack stack = this.pendingRecalculateMarks;
        if (stack != null) {
            while (stack.isNotEmpty() && ((Number) CollectionsKt___CollectionsKt.first((List) stack.backing)).intValue() >= i) {
                stack.takeMax();
            }
        }
        boolean removeGroups = removeGroups(i, this.currentGroup - i);
        removeSlots(i2, this.currentSlot - i2, i - 1);
        this.currentGroup = i;
        this.currentSlot = i2;
        this.nodeCount -= skipGroup;
        return removeGroups;
    }

    public final boolean removeGroups(int i, int i2) {
        if (i2 > 0) {
            ArrayList arrayList = this.anchors;
            moveGroupGapTo(i);
            if (!arrayList.isEmpty()) {
                int i3 = i + i2;
                int access$locationOf = ResultKt.access$locationOf(this.anchors, i3, getCapacity() - this.groupGapLen);
                if (access$locationOf >= this.anchors.size()) {
                    access$locationOf--;
                }
                int i4 = access$locationOf + 1;
                int i5 = 0;
                while (access$locationOf >= 0) {
                    Object obj = this.anchors.get(access$locationOf);
                    ResultKt.checkNotNullExpressionValue(obj, "anchors[index]");
                    Anchor anchor = (Anchor) obj;
                    int anchorIndex = anchorIndex(anchor);
                    if (anchorIndex < i) {
                        break;
                    }
                    if (anchorIndex < i3) {
                        anchor.location = Integer.MIN_VALUE;
                        if (i5 == 0) {
                            i5 = access$locationOf + 1;
                        }
                        i4 = access$locationOf;
                    }
                    access$locationOf--;
                }
                r0 = i4 < i5;
                if (r0) {
                    this.anchors.subList(i4, i5).clear();
                }
            }
            this.groupGapStart = i;
            this.groupGapLen += i2;
            int i6 = this.slotsGapOwner;
            if (i6 > i) {
                this.slotsGapOwner = Math.max(i, i6 - i2);
            }
            int i7 = this.currentGroupEnd;
            if (i7 >= this.groupGapStart) {
                this.currentGroupEnd = i7 - i2;
            }
            int i8 = this.parent;
            if (i8 >= 0 && ResultKt.access$containsMark(this.groups, groupIndexToAddress(i8))) {
                updateContainsMark(this.parent);
            }
        }
        return r0;
    }

    public final void removeSlots(int i, int i2, int i3) {
        if (i2 > 0) {
            int i4 = this.slotsGapLen;
            int i5 = i + i2;
            moveSlotGapTo(i5, i3);
            this.slotsGapStart = i;
            this.slotsGapLen = i4 + i2;
            MapsKt___MapsJvmKt.fill(i, i5, this.slots);
            int i6 = this.currentSlotEnd;
            if (i6 >= i) {
                this.currentSlotEnd = i6 - i2;
            }
        }
    }

    public final int skipGroup() {
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int access$groupSize = ResultKt.access$groupSize(this.groups, groupIndexToAddress) + this.currentGroup;
        this.currentGroup = access$groupSize;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(access$groupSize));
        if (ResultKt.access$isNode(this.groups, groupIndexToAddress)) {
            return 1;
        }
        return ResultKt.access$nodeCount(this.groups, groupIndexToAddress);
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i));
    }

    public final int slotIndex(int[] iArr, int i) {
        if (i >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        int access$slotAnchor = ResultKt.access$slotAnchor(iArr, i);
        return access$slotAnchor < 0 ? (this.slots.length - this.slotsGapLen) + access$slotAnchor + 1 : access$slotAnchor;
    }

    public final void startGroup() {
        if (this.insertCount == 0) {
            ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
            startGroup(0, artificialStackFrames, false, artificialStackFrames);
        } else {
            EffectsKt.composeRuntimeError("Key must be supplied when inserting".toString());
            throw null;
        }
    }

    public final String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    public final void update(Object obj) {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        Object obj2 = objArr[dataIndexToDataAddress(i)];
        int i2 = this.currentSlot;
        if (i2 <= this.currentSlotEnd) {
            this.slots[dataIndexToDataAddress(i2 - 1)] = obj;
        } else {
            EffectsKt.composeRuntimeError("Writing to an invalid slot".toString());
            throw null;
        }
    }

    public final void updateAux(Object obj) {
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (ResultKt.access$hasAux(this.groups, groupIndexToAddress)) {
            this.slots[dataIndexToDataAddress(auxIndex(this.groups, groupIndexToAddress))] = obj;
        } else {
            EffectsKt.composeRuntimeError("Updating the data of a group that was not created with a data slot".toString());
            throw null;
        }
    }

    public final void updateContainsMark(int i) {
        if (i >= 0) {
            Stack stack = this.pendingRecalculateMarks;
            if (stack == null) {
                stack = new Stack();
                this.pendingRecalculateMarks = stack;
            }
            stack.add$1(i);
        }
    }

    public final void updateNodeOfGroup(int i, Object obj) {
        int groupIndexToAddress = groupIndexToAddress(i);
        int[] iArr = this.groups;
        if (groupIndexToAddress < iArr.length && ResultKt.access$isNode(iArr, groupIndexToAddress)) {
            this.slots[dataIndexToDataAddress(dataIndex(this.groups, groupIndexToAddress))] = obj;
            return;
        }
        EffectsKt.composeRuntimeError(("Updating the node of a group at " + i + " that was not created with as a node group").toString());
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void startGroup(int i, Object obj, boolean z, Object obj2) {
        int access$groupSize;
        Object[] objArr = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
        if (objArr != false) {
            insertGroups(1);
            int i2 = this.currentGroup;
            int groupIndexToAddress = groupIndexToAddress(i2);
            int i3 = obj != artificialStackFrames ? 1 : 0;
            int i4 = (z || obj2 == artificialStackFrames) ? 0 : 1;
            int[] iArr = this.groups;
            int i5 = this.parent;
            int i6 = this.currentSlot;
            int i7 = z ? 1073741824 : 0;
            int i8 = i3 != 0 ? 536870912 : 0;
            int i9 = i4 != 0 ? 268435456 : 0;
            int i10 = groupIndexToAddress * 5;
            iArr[i10] = i;
            iArr[i10 + 1] = i7 | i8 | i9;
            iArr[i10 + 2] = i5;
            iArr[i10 + 3] = 0;
            iArr[i10 + 4] = i6;
            this.currentSlotEnd = i6;
            int i11 = (z ? 1 : 0) + i3 + i4;
            if (i11 > 0) {
                insertSlots(i11, i2);
                Object[] objArr2 = this.slots;
                int i12 = this.currentSlot;
                if (z) {
                    objArr2[i12] = obj2;
                    i12++;
                }
                if (i3 != 0) {
                    objArr2[i12] = obj;
                    i12++;
                }
                if (i4 != 0) {
                    objArr2[i12] = obj2;
                    i12++;
                }
                this.currentSlot = i12;
            }
            this.nodeCount = 0;
            access$groupSize = i2 + 1;
            this.parent = i2;
            this.currentGroup = access$groupSize;
        } else {
            this.startStack.push(this.parent);
            this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
            int i13 = this.currentGroup;
            int groupIndexToAddress2 = groupIndexToAddress(i13);
            if (!ResultKt.areEqual(obj2, artificialStackFrames)) {
                if (z) {
                    updateNodeOfGroup(this.currentGroup, obj2);
                } else {
                    updateAux(obj2);
                }
            }
            this.currentSlot = slotIndex(this.groups, groupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = ResultKt.access$nodeCount(this.groups, groupIndexToAddress2);
            this.parent = i13;
            this.currentGroup = i13 + 1;
            access$groupSize = i13 + ResultKt.access$groupSize(this.groups, groupIndexToAddress2);
        }
        this.currentGroupEnd = access$groupSize;
    }
}
