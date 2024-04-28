package androidx.compose.runtime;

import androidx.compose.runtime.CompositionImpl;
import androidx.compose.ui.node.UiApplier;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposerImpl$createNode$3 extends Lambda implements Function3 {
    public final /* synthetic */ Object $groupAnchor;
    public final /* synthetic */ int $insertIndex;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposerImpl$createNode$3(int i, int i2, Object obj) {
        super(3);
        this.$r8$classId = i2;
        this.$groupAnchor = obj;
        this.$insertIndex = i;
    }

    public final void invoke(Applier applier, SlotWriter slotWriter, CompositionImpl.RememberEventDispatcher rememberEventDispatcher) {
        int i = this.$r8$classId;
        int i2 = this.$insertIndex;
        Object obj = this.$groupAnchor;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(applier, "applier");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                Anchor anchor = (Anchor) obj;
                ResultKt.checkNotNullParameter(anchor, "anchor");
                int groupIndexToAddress = slotWriter.groupIndexToAddress(slotWriter.anchorIndex(anchor));
                Object obj2 = ResultKt.access$isNode(slotWriter.groups, groupIndexToAddress) ? slotWriter.slots[slotWriter.dataIndexToDataAddress(slotWriter.dataIndex(slotWriter.groups, groupIndexToAddress))] : null;
                ((AbstractApplier) applier).up();
                ((UiApplier) applier).insertBottomUp(i2, obj2);
                return;
            default:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "rememberManager");
                if (obj instanceof RememberObserver) {
                    rememberEventDispatcher.remembering((RememberObserver) obj);
                }
                int slotIndex = slotWriter.slotIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.currentGroup));
                int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.currentGroup + 1));
                int i3 = slotIndex + i2;
                if (i3 >= slotIndex && i3 < dataIndex) {
                    int dataIndexToDataAddress = slotWriter.dataIndexToDataAddress(i3);
                    Object[] objArr = slotWriter.slots;
                    Object obj3 = objArr[dataIndexToDataAddress];
                    objArr[dataIndexToDataAddress] = obj;
                    if (obj3 instanceof RememberObserver) {
                        rememberEventDispatcher.forgetting((RememberObserver) obj3);
                        return;
                    }
                    if (obj3 instanceof RecomposeScopeImpl) {
                        RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj3;
                        RecomposeScopeOwner recomposeScopeOwner = recomposeScopeImpl.owner;
                        if (recomposeScopeOwner != null) {
                            recomposeScopeOwner.recomposeScopeReleased(recomposeScopeImpl);
                        }
                        recomposeScopeImpl.owner = null;
                        recomposeScopeImpl.trackedInstances = null;
                        recomposeScopeImpl.trackedDependencies = null;
                        return;
                    }
                    return;
                }
                EffectsKt.composeRuntimeError(("Write to an invalid slot index " + i2 + " for group " + slotWriter.currentGroup).toString());
                throw null;
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            default:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
        }
    }
}
