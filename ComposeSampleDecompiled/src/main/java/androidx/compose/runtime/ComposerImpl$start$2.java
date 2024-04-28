package androidx.compose.runtime;

import androidx.compose.runtime.CompositionImpl;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposerImpl$start$2 extends Lambda implements Function3 {
    public final /* synthetic */ int $currentRelativePosition;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposerImpl$start$2(int i, int i2) {
        super(3);
        this.$r8$classId = i2;
        this.$currentRelativePosition = i;
    }

    public final void invoke(Applier applier, SlotWriter slotWriter, CompositionImpl.RememberEventDispatcher rememberEventDispatcher) {
        int i = this.$r8$classId;
        int i2 = this.$currentRelativePosition;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                if (!(slotWriter.insertCount == 0)) {
                    EffectsKt.composeRuntimeError("Cannot move a group while inserting".toString());
                    throw null;
                }
                if (!(i2 >= 0)) {
                    EffectsKt.composeRuntimeError("Parameter offset is out of bounds".toString());
                    throw null;
                }
                if (i2 == 0) {
                    return;
                }
                int i3 = slotWriter.currentGroup;
                int i4 = slotWriter.parent;
                int i5 = slotWriter.currentGroupEnd;
                int i6 = i3;
                while (i2 > 0) {
                    i6 += ResultKt.access$groupSize(slotWriter.groups, slotWriter.groupIndexToAddress(i6));
                    if (i6 > i5) {
                        EffectsKt.composeRuntimeError("Parameter offset is out of bounds".toString());
                        throw null;
                    }
                    i2--;
                }
                int access$groupSize = ResultKt.access$groupSize(slotWriter.groups, slotWriter.groupIndexToAddress(i6));
                int i7 = slotWriter.currentSlot;
                int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i6));
                int i8 = i6 + access$groupSize;
                int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i8));
                int i9 = dataIndex2 - dataIndex;
                slotWriter.insertSlots(i9, Math.max(slotWriter.currentGroup - 1, 0));
                slotWriter.insertGroups(access$groupSize);
                int[] iArr = slotWriter.groups;
                int groupIndexToAddress = slotWriter.groupIndexToAddress(i8) * 5;
                MapsKt___MapsJvmKt.copyInto(iArr, iArr, slotWriter.groupIndexToAddress(i3) * 5, groupIndexToAddress, (access$groupSize * 5) + groupIndexToAddress);
                if (i9 > 0) {
                    Object[] objArr = slotWriter.slots;
                    MapsKt___MapsJvmKt.copyInto(objArr, objArr, i7, slotWriter.dataIndexToDataAddress(dataIndex + i9), slotWriter.dataIndexToDataAddress(dataIndex2 + i9));
                }
                int i10 = dataIndex + i9;
                int i11 = i10 - i7;
                int i12 = slotWriter.slotsGapStart;
                int i13 = slotWriter.slotsGapLen;
                int length = slotWriter.slots.length;
                int i14 = slotWriter.slotsGapOwner;
                int i15 = i3 + access$groupSize;
                int i16 = i3;
                while (i16 < i15) {
                    int groupIndexToAddress2 = slotWriter.groupIndexToAddress(i16);
                    int i17 = i15;
                    int i18 = i11;
                    iArr[(groupIndexToAddress2 * 5) + 4] = SlotWriter.dataIndexToDataAnchor(SlotWriter.dataIndexToDataAnchor(slotWriter.dataIndex(iArr, groupIndexToAddress2) - i11, i14 < groupIndexToAddress2 ? 0 : i12, i13, length), slotWriter.slotsGapStart, slotWriter.slotsGapLen, slotWriter.slots.length);
                    i16++;
                    i11 = i18;
                    i15 = i17;
                    i12 = i12;
                    i13 = i13;
                }
                int i19 = i8 + access$groupSize;
                int size$runtime_release = slotWriter.getSize$runtime_release();
                int access$locationOf = ResultKt.access$locationOf(slotWriter.anchors, i8, size$runtime_release);
                ArrayList arrayList = new ArrayList();
                if (access$locationOf >= 0) {
                    while (access$locationOf < slotWriter.anchors.size()) {
                        Object obj = slotWriter.anchors.get(access$locationOf);
                        ResultKt.checkNotNullExpressionValue(obj, "anchors[index]");
                        Anchor anchor = (Anchor) obj;
                        int anchorIndex = slotWriter.anchorIndex(anchor);
                        if (anchorIndex >= i8 && anchorIndex < i19) {
                            arrayList.add(anchor);
                            slotWriter.anchors.remove(access$locationOf);
                        }
                    }
                }
                int i20 = i3 - i8;
                int size = arrayList.size();
                for (int i21 = 0; i21 < size; i21++) {
                    Anchor anchor2 = (Anchor) arrayList.get(i21);
                    int anchorIndex2 = slotWriter.anchorIndex(anchor2) + i20;
                    if (anchorIndex2 >= slotWriter.groupGapStart) {
                        anchor2.location = -(size$runtime_release - anchorIndex2);
                    } else {
                        anchor2.location = anchorIndex2;
                    }
                    slotWriter.anchors.add(ResultKt.access$locationOf(slotWriter.anchors, anchorIndex2, size$runtime_release), anchor2);
                }
                if (!slotWriter.removeGroups(i8, access$groupSize)) {
                    slotWriter.fixParentAnchorsFor(i4, slotWriter.currentGroupEnd, i3);
                    if (i9 > 0) {
                        slotWriter.removeSlots(i10, i9, i8 - 1);
                        return;
                    }
                    return;
                }
                EffectsKt.composeRuntimeError("Unexpectedly removed anchors".toString());
                throw null;
            case 1:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                slotWriter.advanceBy(i2);
                return;
            default:
                ResultKt.checkNotNullParameter(applier, "applier");
                ResultKt.checkNotNullParameter(slotWriter, "<anonymous parameter 1>");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                for (int i22 = 0; i22 < i2; i22++) {
                    ((AbstractApplier) applier).up();
                }
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            case 1:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            default:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
        }
    }
}
