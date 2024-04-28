package androidx.compose.runtime;

import androidx.compose.runtime.CompositionImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposerImpl$useNode$2 extends Lambda implements Function3 {
    public final /* synthetic */ int $r8$classId;
    public static final ComposerImpl$useNode$2 INSTANCE$1 = new ComposerImpl$useNode$2(1);
    public static final ComposerImpl$useNode$2 INSTANCE = new ComposerImpl$useNode$2(0);
    public static final ComposerImpl$useNode$2 INSTANCE$2 = new ComposerImpl$useNode$2(2);
    public static final ComposerImpl$useNode$2 INSTANCE$3 = new ComposerImpl$useNode$2(3);
    public static final ComposerImpl$useNode$2 INSTANCE$4 = new ComposerImpl$useNode$2(4);
    public static final ComposerImpl$useNode$2 INSTANCE$6 = new ComposerImpl$useNode$2(6);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposerImpl$useNode$2(int i) {
        super(3);
        this.$r8$classId = i;
    }

    public final void invoke(Applier applier, SlotWriter slotWriter, CompositionImpl.RememberEventDispatcher rememberEventDispatcher) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(applier, "applier");
                ResultKt.checkNotNullParameter(slotWriter, "<anonymous parameter 1>");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                Object obj = ((AbstractApplier) applier).current;
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.ComposeNodeLifecycleCallback");
                LayoutNode layoutNode = (LayoutNode) ((ComposeNodeLifecycleCallback) obj);
                if (layoutNode.isAttached()) {
                    boolean z = layoutNode.deactivated;
                    NodeChain nodeChain = layoutNode.nodes;
                    if (z) {
                        layoutNode.deactivated = false;
                    } else {
                        for (Modifier.Node node = nodeChain.tail; node != null; node = node.parent) {
                            if (node.isAttached) {
                                node.reset$ui_release();
                            }
                        }
                        Modifier.Node node2 = nodeChain.tail;
                        for (Modifier.Node node3 = node2; node3 != null; node3 = node3.parent) {
                            if (node3.isAttached) {
                                node3.runDetachLifecycle$ui_release();
                            }
                        }
                        while (node2 != null) {
                            if (node2.isAttached) {
                                node2.markAsDetached$ui_release();
                            }
                            node2 = node2.parent;
                        }
                    }
                    layoutNode.semanticsId = SemanticsModifierKt.lastIdentifier.addAndGet(1);
                    for (Modifier.Node node4 = nodeChain.head; node4 != null; node4 = node4.child) {
                        node4.markAsAttached$ui_release();
                    }
                    nodeChain.runAttachLifecycle();
                    return;
                }
                throw new IllegalArgumentException("onReuse is only expected on attached node".toString());
            case 1:
                ResultKt.checkNotNullParameter(applier, "applier");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                ComposerImpl.insertMovableContentGuarded$positionToParentOf(slotWriter, applier, 0);
                slotWriter.endGroup();
                return;
            case 2:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                slotWriter.endGroup();
                return;
            case 3:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "rememberManager");
                EffectsKt.removeCurrentGroup(slotWriter, rememberEventDispatcher);
                return;
            case 4:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                if (slotWriter.insertCount == 0) {
                    slotWriter.recalculateMarks();
                    slotWriter.currentGroup = 0;
                    slotWriter.currentGroupEnd = slotWriter.getCapacity() - slotWriter.groupGapLen;
                    slotWriter.currentSlot = 0;
                    slotWriter.currentSlotEnd = 0;
                    slotWriter.nodeCount = 0;
                    return;
                }
                EffectsKt.composeRuntimeError("Cannot reset when inserting".toString());
                throw null;
            case 5:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                slotWriter.skipToGroupEnd();
                return;
            default:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                slotWriter.ensureStarted(0);
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
            case 2:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            case 3:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            case 4:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            case 5:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            default:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
        }
    }
}
