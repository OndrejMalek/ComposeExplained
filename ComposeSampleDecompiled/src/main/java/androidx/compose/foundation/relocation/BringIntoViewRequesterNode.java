package androidx.compose.foundation.relocation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class BringIntoViewRequesterNode extends Modifier.Node implements ModifierLocalModifierNode, LayoutAwareModifierNode, CompositionLocalConsumerModifierNode {
    public final BringIntoViewResponder_androidKt$defaultBringIntoViewParent$1 defaultParent = new BringIntoViewResponder_androidKt$defaultBringIntoViewParent$1(this);
    public LayoutCoordinates layoutCoordinates;
    public BringIntoViewRequesterImpl requester;

    public BringIntoViewRequesterNode(BringIntoViewRequesterImpl bringIntoViewRequesterImpl) {
        this.requester = bringIntoViewRequesterImpl;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        BringIntoViewRequesterImpl bringIntoViewRequesterImpl = this.requester;
        ResultKt.checkNotNullParameter(bringIntoViewRequesterImpl, "requester");
        BringIntoViewRequesterImpl bringIntoViewRequesterImpl2 = this.requester;
        if (bringIntoViewRequesterImpl2 instanceof BringIntoViewRequesterImpl) {
            ResultKt.checkNotNull(bringIntoViewRequesterImpl2, "null cannot be cast to non-null type androidx.compose.foundation.relocation.BringIntoViewRequesterImpl");
            bringIntoViewRequesterImpl2.modifiers.remove(this);
        }
        bringIntoViewRequesterImpl.modifiers.add(this);
        this.requester = bringIntoViewRequesterImpl;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        BringIntoViewRequesterImpl bringIntoViewRequesterImpl = this.requester;
        if (bringIntoViewRequesterImpl instanceof BringIntoViewRequesterImpl) {
            ResultKt.checkNotNull(bringIntoViewRequesterImpl, "null cannot be cast to non-null type androidx.compose.foundation.relocation.BringIntoViewRequesterImpl");
            bringIntoViewRequesterImpl.modifiers.remove(this);
        }
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public final void onPlaced(NodeCoordinator nodeCoordinator) {
        ResultKt.checkNotNullParameter(nodeCoordinator, "coordinates");
        this.layoutCoordinates = nodeCoordinator;
    }
}
