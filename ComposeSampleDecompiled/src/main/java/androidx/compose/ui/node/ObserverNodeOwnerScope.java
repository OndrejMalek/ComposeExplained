package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ObserverNodeOwnerScope implements OwnerScope {
    public final ObserverModifierNode observerNode;

    public ObserverNodeOwnerScope(ObserverModifierNode observerModifierNode) {
        ResultKt.checkNotNullParameter(observerModifierNode, "observerNode");
        this.observerNode = observerModifierNode;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return ((Modifier.Node) this.observerNode).node.isAttached;
    }
}
