package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.PointerInputModifierNode;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public abstract class AbstractClickablePointerInputNode extends DelegatingNode implements ModifierLocalModifierNode, CompositionLocalConsumerModifierNode, PointerInputModifierNode {
    public final LayoutNode$_foldedChildren$1 delayPressInteraction = new LayoutNode$_foldedChildren$1(1, this);
    public boolean enabled;
    public final AbstractClickableNode$InteractionData interactionData;
    public MutableInteractionSourceImpl interactionSource;
    public Function0 onClick;
    public final SuspendingPointerInputModifierNode pointerInputNode;

    public AbstractClickablePointerInputNode(boolean z, MutableInteractionSourceImpl mutableInteractionSourceImpl, Function0 function0, AbstractClickableNode$InteractionData abstractClickableNode$InteractionData) {
        this.enabled = z;
        this.interactionSource = mutableInteractionSourceImpl;
        this.onClick = function0;
        this.interactionData = abstractClickableNode$InteractionData;
        AbstractClickablePointerInputNode$pointerInputNode$1 abstractClickablePointerInputNode$pointerInputNode$1 = new AbstractClickablePointerInputNode$pointerInputNode$1(this, null);
        PointerEvent pointerEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
        SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = new SuspendingPointerInputModifierNodeImpl(abstractClickablePointerInputNode$pointerInputNode$1);
        delegate(suspendingPointerInputModifierNodeImpl);
        this.pointerInputNode = suspendingPointerInputModifierNodeImpl;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        ((SuspendingPointerInputModifierNodeImpl) this.pointerInputNode).onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public final void mo25onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        ((SuspendingPointerInputModifierNodeImpl) this.pointerInputNode).mo25onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
    }
}
