package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class FocusedBoundsNode extends Modifier.Node implements ModifierLocalModifierNode, GlobalPositionAwareModifierNode {
    public boolean isFocused;
    public LayoutCoordinates layoutCoordinates;

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public final void onGloballyPositioned(NodeCoordinator nodeCoordinator) {
        this.layoutCoordinates = nodeCoordinator;
        if (this.isFocused) {
            if (!nodeCoordinator.isAttached()) {
                Function1 function1 = this.isAttached ? (Function1) getCurrent(FocusedBoundsKt.ModifierLocalFocusedBoundsObserver) : null;
                if (function1 != null) {
                    function1.invoke(null);
                    return;
                }
                return;
            }
            LayoutCoordinates layoutCoordinates = this.layoutCoordinates;
            if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
                return;
            }
            Function1 function12 = this.isAttached ? (Function1) getCurrent(FocusedBoundsKt.ModifierLocalFocusedBoundsObserver) : null;
            if (function12 != null) {
                function12.invoke(this.layoutCoordinates);
            }
        }
    }
}
