package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.foundation.relocation.BringIntoViewRequesterImpl;
import androidx.compose.foundation.relocation.BringIntoViewRequesterNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeCoordinator$invoke$1;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class FocusableNode extends DelegatingNode implements FocusEventModifierNode, LayoutAwareModifierNode, SemanticsModifierNode, GlobalPositionAwareModifierNode {
    public final BringIntoViewRequesterImpl bringIntoViewRequester;
    public final BringIntoViewRequesterNode bringIntoViewRequesterNode;
    public FocusState focusState;
    public final FocusableInteractionNode focusableInteractionNode;
    public final FocusablePinnableContainerNode focusablePinnableContainer;
    public final FocusableSemanticsNode focusableSemanticsNode;
    public final FocusedBoundsNode focusedBoundsNode;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.compose.foundation.FocusableNode */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.FocusableSemanticsNode, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.FocusableInteractionNode, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.compose.foundation.FocusablePinnableContainerNode, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.foundation.FocusedBoundsNode, androidx.compose.ui.Modifier$Node] */
    public FocusableNode(MutableInteractionSourceImpl mutableInteractionSourceImpl) {
        ?? node = new Modifier.Node();
        new LinkedHashMap();
        delegate(node);
        this.focusableSemanticsNode = node;
        ?? node2 = new Modifier.Node();
        node2.interactionSource = mutableInteractionSourceImpl;
        delegate(node2);
        this.focusableInteractionNode = node2;
        ?? node3 = new Modifier.Node();
        delegate(node3);
        this.focusablePinnableContainer = node3;
        ?? node4 = new Modifier.Node();
        delegate(node4);
        this.focusedBoundsNode = node4;
        BringIntoViewRequesterImpl bringIntoViewRequesterImpl = new BringIntoViewRequesterImpl();
        this.bringIntoViewRequester = bringIntoViewRequesterImpl;
        BringIntoViewRequesterNode bringIntoViewRequesterNode = new BringIntoViewRequesterNode(bringIntoViewRequesterImpl);
        delegate(bringIntoViewRequesterNode);
        this.bringIntoViewRequesterNode = bringIntoViewRequesterNode;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        this.focusableSemanticsNode.applySemantics(semanticsConfiguration);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: androidx.compose.foundation.FocusableInteractionNode */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.foundation.interaction.FocusInteraction$Focus, androidx.compose.foundation.interaction.Interaction, java.lang.Object] */
    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public final void onFocusEvent(FocusStateImpl focusStateImpl) {
        if (ResultKt.areEqual(this.focusState, focusStateImpl)) {
            return;
        }
        boolean isFocused = focusStateImpl.isFocused();
        if (isFocused) {
            ResultKt.launch$default(getCoroutineScope(), null, 0, new FocusableNode$onFocusEvent$1(this, null), 3);
        }
        if (this.isAttached) {
            LayoutNode requireLayoutNode = Snake.requireLayoutNode(this);
            requireLayoutNode._collapsedSemantics = null;
            ((AndroidComposeView) Snake.requireOwner(requireLayoutNode)).onSemanticsChange();
        }
        FocusableInteractionNode focusableInteractionNode = this.focusableInteractionNode;
        MutableInteractionSourceImpl mutableInteractionSourceImpl = focusableInteractionNode.interactionSource;
        if (mutableInteractionSourceImpl != null) {
            if (isFocused) {
                FocusInteraction$Focus focusInteraction$Focus = focusableInteractionNode.focusedInteraction;
                if (focusInteraction$Focus != null) {
                    focusableInteractionNode.emitWithFallback(mutableInteractionSourceImpl, new FocusInteraction$Unfocus(focusInteraction$Focus));
                    focusableInteractionNode.focusedInteraction = null;
                }
                ?? obj = new Object();
                focusableInteractionNode.emitWithFallback(mutableInteractionSourceImpl, obj);
                focusableInteractionNode.focusedInteraction = obj;
            } else {
                FocusInteraction$Focus focusInteraction$Focus2 = focusableInteractionNode.focusedInteraction;
                if (focusInteraction$Focus2 != null) {
                    focusableInteractionNode.emitWithFallback(mutableInteractionSourceImpl, new FocusInteraction$Unfocus(focusInteraction$Focus2));
                    focusableInteractionNode.focusedInteraction = null;
                }
            }
        }
        FocusedBoundsNode focusedBoundsNode = this.focusedBoundsNode;
        if (isFocused != focusedBoundsNode.isFocused) {
            if (isFocused) {
                LayoutCoordinates layoutCoordinates = focusedBoundsNode.layoutCoordinates;
                if (layoutCoordinates != null && layoutCoordinates.isAttached()) {
                    Function1 function1 = focusedBoundsNode.isAttached ? (Function1) focusedBoundsNode.getCurrent(FocusedBoundsKt.ModifierLocalFocusedBoundsObserver) : null;
                    if (function1 != null) {
                        function1.invoke(focusedBoundsNode.layoutCoordinates);
                    }
                }
            } else {
                Function1 function12 = focusedBoundsNode.isAttached ? (Function1) focusedBoundsNode.getCurrent(FocusedBoundsKt.ModifierLocalFocusedBoundsObserver) : null;
                if (function12 != null) {
                    function12.invoke(null);
                }
            }
            focusedBoundsNode.isFocused = isFocused;
        }
        FocusablePinnableContainerNode focusablePinnableContainerNode = this.focusablePinnableContainer;
        if (isFocused) {
            focusablePinnableContainerNode.getClass();
            ?? obj2 = new Object();
            Snake.observeReads(focusablePinnableContainerNode, new NodeCoordinator$invoke$1(obj2, 1, focusablePinnableContainerNode));
            DurationKt$$ExternalSyntheticCheckNotZero0.m(obj2.element);
        }
        focusablePinnableContainerNode.getClass();
        this.focusableSemanticsNode.isFocused = isFocused;
        this.focusState = focusStateImpl;
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public final void onGloballyPositioned(NodeCoordinator nodeCoordinator) {
        this.focusedBoundsNode.onGloballyPositioned(nodeCoordinator);
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public final void onPlaced(NodeCoordinator nodeCoordinator) {
        ResultKt.checkNotNullParameter(nodeCoordinator, "coordinates");
        BringIntoViewRequesterNode bringIntoViewRequesterNode = this.bringIntoViewRequesterNode;
        bringIntoViewRequesterNode.getClass();
        bringIntoViewRequesterNode.layoutCoordinates = nodeCoordinator;
    }
}
