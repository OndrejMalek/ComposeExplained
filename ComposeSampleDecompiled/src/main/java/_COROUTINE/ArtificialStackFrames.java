package _COROUTINE;

import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.Latch$await$2$2;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.Recomposer$effectJob$1$1;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.EndOfChain;
import androidx.compose.runtime.snapshots.GlobalSnapshot;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.Snapshot$Companion$registerApplyObserver$2;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1;
import androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot;
import androidx.compose.ui.platform.WeakCache;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* loaded from: classes.dex */
public final class ArtificialStackFrames {
    public final /* synthetic */ int $r8$classId;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [_COROUTINE.ArtificialStackFrames.<init>(int, int):void, androidx.compose.runtime.Composer.Companion.<clinit>():void, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder.<init>(androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList, java.lang.Object[], java.lang.Object[], int):void, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder.build():androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder.<init>(androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap):void, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder.build():androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap, androidx.compose.runtime.internal.PersistentCompositionLocalHashMap.Builder.build():androidx.compose.runtime.internal.PersistentCompositionLocalHashMap] */
    public /* synthetic */ ArtificialStackFrames(int i) {
        this.$r8$classId = i;
    }

    public static final void access$removeRunning(Stack stack) {
        StateFlowImpl stateFlowImpl;
        Object obj;
        PersistentOrderedSet persistentOrderedSet;
        StateFlowImpl stateFlowImpl2 = Recomposer._runningRecomposers;
        do {
            stateFlowImpl = Recomposer._runningRecomposers;
            obj = (PersistentSet) stateFlowImpl.getValue();
            persistentOrderedSet = (PersistentOrderedSet) obj;
            PersistentHashMap persistentHashMap = persistentOrderedSet.hashMap;
            Links links = (Links) persistentHashMap.get(stack);
            if (links != null) {
                int hashCode = stack != null ? stack.hashCode() : 0;
                TrieNode trieNode = persistentHashMap.node;
                TrieNode remove = trieNode.remove(hashCode, 0, stack);
                if (trieNode != remove) {
                    if (remove == null) {
                        persistentHashMap = PersistentHashMap.EMPTY;
                        ResultKt.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
                    } else {
                        persistentHashMap = new PersistentHashMap(remove, persistentHashMap.size - 1);
                    }
                }
                EndOfChain endOfChain = EndOfChain.INSTANCE;
                Object obj2 = links.previous;
                boolean z = obj2 != endOfChain;
                Object obj3 = links.next;
                if (z) {
                    Object obj4 = persistentHashMap.get(obj2);
                    ResultKt.checkNotNull(obj4);
                    persistentHashMap = persistentHashMap.put(obj2, new Links(((Links) obj4).previous, obj3));
                }
                if (obj3 != endOfChain) {
                    Object obj5 = persistentHashMap.get(obj3);
                    ResultKt.checkNotNull(obj5);
                    persistentHashMap = persistentHashMap.put(obj3, new Links(obj2, ((Links) obj5).next));
                }
                Object obj6 = obj2 != endOfChain ? persistentOrderedSet.firstElement : obj3;
                if (obj3 != endOfChain) {
                    obj2 = persistentOrderedSet.lastElement;
                }
                persistentOrderedSet = new PersistentOrderedSet(obj6, obj2, persistentHashMap);
            }
            if (obj == persistentOrderedSet) {
                return;
            }
            Object obj7 = NullSurrogateKt.NULL;
            if (obj == null) {
                obj = obj7;
            }
        } while (!stateFlowImpl.updateState(obj, persistentOrderedSet));
    }

    public static MinLinesConstrainer from(MinLinesConstrainer minLinesConstrainer, LayoutDirection layoutDirection, TextStyle textStyle, Density density, FontFamily.Resolver resolver) {
        ResultKt.checkNotNullParameter(textStyle, "paramStyle");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        if (minLinesConstrainer != null && layoutDirection == minLinesConstrainer.layoutDirection && ResultKt.areEqual(textStyle, minLinesConstrainer.inputTextStyle) && density.getDensity() == minLinesConstrainer.density.getDensity() && resolver == minLinesConstrainer.fontFamilyResolver) {
            return minLinesConstrainer;
        }
        MinLinesConstrainer minLinesConstrainer2 = MinLinesConstrainer.last;
        if (minLinesConstrainer2 != null && layoutDirection == minLinesConstrainer2.layoutDirection && ResultKt.areEqual(textStyle, minLinesConstrainer2.inputTextStyle) && density.getDensity() == minLinesConstrainer2.density.getDensity() && resolver == minLinesConstrainer2.fontFamilyResolver) {
            return minLinesConstrainer2;
        }
        MinLinesConstrainer minLinesConstrainer3 = new MinLinesConstrainer(layoutDirection, _BOUNDARY.resolveDefaults(textStyle, layoutDirection), density, resolver);
        MinLinesConstrainer.last = minLinesConstrainer3;
        return minLinesConstrainer3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v6, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    public static List moveGroup(SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3) {
        EmptyList emptyList;
        int i2;
        boolean z4;
        int i3;
        int i4;
        int groupSize = slotWriter.groupSize(i);
        int i5 = i + groupSize;
        int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i));
        int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i5));
        int i6 = dataIndex2 - dataIndex;
        boolean z5 = i >= 0 && (slotWriter.groups[(slotWriter.groupIndexToAddress(i) * 5) + 1] & 201326592) != 0;
        slotWriter2.insertGroups(groupSize);
        slotWriter2.insertSlots(i6, slotWriter2.currentGroup);
        if (slotWriter.groupGapStart < i5) {
            slotWriter.moveGroupGapTo(i5);
        }
        if (slotWriter.slotsGapStart < dataIndex2) {
            slotWriter.moveSlotGapTo(dataIndex2, i5);
        }
        int[] iArr = slotWriter2.groups;
        int i7 = slotWriter2.currentGroup;
        int i8 = i7 * 5;
        MapsKt___MapsJvmKt.copyInto(slotWriter.groups, iArr, i8, i * 5, i5 * 5);
        Object[] objArr = slotWriter2.slots;
        int i9 = slotWriter2.currentSlot;
        MapsKt___MapsJvmKt.copyInto(slotWriter.slots, objArr, i9, dataIndex, dataIndex2);
        int i10 = slotWriter2.parent;
        iArr[i8 + 2] = i10;
        int i11 = i7 - i;
        int i12 = i7 + groupSize;
        int dataIndex3 = i9 - slotWriter2.dataIndex(iArr, i7);
        int i13 = slotWriter2.slotsGapOwner;
        int i14 = slotWriter2.slotsGapLen;
        int length = objArr.length;
        int i15 = i13;
        boolean z6 = z5;
        int i16 = i7;
        while (i16 < i12) {
            if (i16 != i7) {
                int i17 = (i16 * 5) + 2;
                iArr[i17] = iArr[i17] + i11;
            }
            int i18 = i9;
            int dataIndex4 = slotWriter2.dataIndex(iArr, i16) + dataIndex3;
            if (i15 < i16) {
                i3 = i12;
                i4 = 0;
            } else {
                i3 = i12;
                i4 = slotWriter2.slotsGapStart;
            }
            iArr[(i16 * 5) + 4] = SlotWriter.dataIndexToDataAnchor(dataIndex4, i4, i14, length);
            if (i16 == i15) {
                i15++;
            }
            i16++;
            i12 = i3;
            i9 = i18;
        }
        int i19 = i9;
        int i20 = i12;
        slotWriter2.slotsGapOwner = i15;
        int access$locationOf = ResultKt.access$locationOf(slotWriter.anchors, i, slotWriter.getSize$runtime_release());
        int access$locationOf2 = ResultKt.access$locationOf(slotWriter.anchors, i5, slotWriter.getSize$runtime_release());
        if (access$locationOf < access$locationOf2) {
            ArrayList arrayList = slotWriter.anchors;
            ArrayList arrayList2 = new ArrayList(access$locationOf2 - access$locationOf);
            for (int i21 = access$locationOf; i21 < access$locationOf2; i21++) {
                Object obj = arrayList.get(i21);
                ResultKt.checkNotNullExpressionValue(obj, "sourceAnchors[anchorIndex]");
                Anchor anchor = (Anchor) obj;
                anchor.location += i11;
                arrayList2.add(anchor);
            }
            slotWriter2.anchors.addAll(ResultKt.access$locationOf(slotWriter2.anchors, slotWriter2.currentGroup, slotWriter2.getSize$runtime_release()), arrayList2);
            arrayList.subList(access$locationOf, access$locationOf2).clear();
            emptyList = arrayList2;
        } else {
            emptyList = EmptyList.INSTANCE;
        }
        int parent = slotWriter.parent(slotWriter.groups, i);
        if (!z3) {
            i2 = 1;
            z4 = false;
        } else if (z) {
            boolean z7 = parent >= 0;
            if (z7) {
                slotWriter.startGroup();
                slotWriter.advanceBy(parent - slotWriter.currentGroup);
                slotWriter.startGroup();
            }
            slotWriter.advanceBy(i - slotWriter.currentGroup);
            boolean removeGroup = slotWriter.removeGroup();
            if (z7) {
                slotWriter.skipToGroupEnd();
                slotWriter.endGroup();
                slotWriter.skipToGroupEnd();
                slotWriter.endGroup();
            }
            z4 = removeGroup;
            i2 = 1;
        } else {
            boolean removeGroups = slotWriter.removeGroups(i, groupSize);
            i2 = 1;
            slotWriter.removeSlots(dataIndex, i6, i - 1);
            z4 = removeGroups;
        }
        if (!(!z4)) {
            EffectsKt.composeRuntimeError("Unexpectedly removed anchors".toString());
            throw null;
        }
        slotWriter2.nodeCount += ResultKt.access$isNode(iArr, i7) ? i2 : ResultKt.access$nodeCount(iArr, i7);
        if (z2) {
            slotWriter2.currentGroup = i20;
            slotWriter2.currentSlot = i19 + i6;
        }
        if (z6) {
            slotWriter2.updateContainsMark(i10);
        }
        return emptyList;
    }

    public static Object observe(Function1 function1, Function0 function0) {
        Snapshot transparentObserverMutableSnapshot;
        ResultKt.checkNotNullParameter(function0, "block");
        if (function1 == null) {
            return function0.invoke();
        }
        Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
        if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
            transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function1, null, true, false);
        } else {
            if (function1 == null) {
                return function0.invoke();
            }
            transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(function1);
        }
        try {
            Snapshot makeCurrent = transparentObserverMutableSnapshot.makeCurrent();
            try {
                return function0.invoke();
            } finally {
                Snapshot.restoreCurrent(makeCurrent);
            }
        } finally {
            transparentObserverMutableSnapshot.dispose();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function2 */
    /* JADX WARN: Multi-variable type inference failed */
    public static Snapshot$Companion$registerApplyObserver$2 registerApplyObserver(Function2 function2) {
        ResultKt.checkNotNullParameter(function2, "observer");
        WeakCache weakCache = SnapshotKt.threadSnapshot;
        SnapshotKt.advanceGlobalSnapshot(SnapshotKt$emptyLambda$1.INSTANCE);
        synchronized (SnapshotKt.lock) {
            SnapshotKt.applyObservers.add(function2);
        }
        return new Snapshot$Companion$registerApplyObserver$2((Lambda) function2, 0);
    }

    public static void sendApplyNotifications() {
        boolean z;
        synchronized (SnapshotKt.lock) {
            IdentityArraySet identityArraySet = ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).modified;
            z = false;
            if (identityArraySet != null) {
                if (identityArraySet.isNotEmpty()) {
                    z = true;
                }
            }
        }
        if (z) {
            SnapshotKt.advanceGlobalSnapshot(SnapshotKt$emptyLambda$1.INSTANCE$1);
        }
    }

    public static MutableSnapshot takeMutableSnapshot(Recomposer$effectJob$1$1 recomposer$effectJob$1$1, Latch$await$2$2 latch$await$2$2) {
        MutableSnapshot takeNestedMutableSnapshot;
        Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
        MutableSnapshot mutableSnapshot = currentSnapshot instanceof MutableSnapshot ? (MutableSnapshot) currentSnapshot : null;
        if (mutableSnapshot == null || (takeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(recomposer$effectJob$1$1, latch$await$2$2)) == null) {
            throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot".toString());
        }
        return takeNestedMutableSnapshot;
    }

    public final String toString() {
        switch (this.$r8$classId) {
            case 10:
                return "Empty";
            default:
                return super.toString();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ArtificialStackFrames(int i, int i2) {
        this(1);
        this.$r8$classId = i;
        switch (i) {
            case 2:
                this(2);
                return;
            case 3:
                this(3);
                return;
            case 4:
                this(4);
                return;
            case 5:
                this(5);
                return;
            case 6:
                this(6);
                return;
            case 7:
                this(7);
                return;
            case 8:
                this(8);
                return;
            case 9:
                this(9);
                return;
            case 10:
            case 23:
            default:
                return;
            case 11:
                this(11);
                return;
            case 12:
                this(12);
                return;
            case 13:
                this(13);
                return;
            case 14:
                this(14);
                return;
            case 15:
                this(15);
                return;
            case 16:
                this(16);
                return;
            case 17:
                this(17);
                return;
            case 18:
                this(18);
                return;
            case 19:
                this(19);
                return;
            case 20:
                this(20);
                return;
            case 21:
                this(21);
                return;
            case 22:
                this(22);
                return;
            case 24:
                this(24);
                return;
            case 25:
                this(25);
                return;
            case 26:
                this(26);
                return;
            case 27:
                this(27);
                return;
            case 28:
                this(28);
                return;
            case 29:
                this(29);
                return;
        }
    }
}
