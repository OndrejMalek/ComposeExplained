package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FocusableElement extends ModifierNodeElement {
    public final MutableInteractionSourceImpl interactionSource;

    public FocusableElement(MutableInteractionSourceImpl mutableInteractionSourceImpl) {
        this.interactionSource = mutableInteractionSourceImpl;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new FocusableNode(this.interactionSource);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FocusableElement) {
            return ResultKt.areEqual(this.interactionSource, ((FocusableElement) obj).interactionSource);
        }
        return false;
    }

    public final int hashCode() {
        MutableInteractionSourceImpl mutableInteractionSourceImpl = this.interactionSource;
        if (mutableInteractionSourceImpl != null) {
            return mutableInteractionSourceImpl.hashCode();
        }
        return 0;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        FocusInteraction$Focus focusInteraction$Focus;
        FocusableNode focusableNode = (FocusableNode) node;
        ResultKt.checkNotNullParameter(focusableNode, "node");
        FocusableInteractionNode focusableInteractionNode = focusableNode.focusableInteractionNode;
        MutableInteractionSourceImpl mutableInteractionSourceImpl = focusableInteractionNode.interactionSource;
        MutableInteractionSourceImpl mutableInteractionSourceImpl2 = this.interactionSource;
        if (ResultKt.areEqual(mutableInteractionSourceImpl, mutableInteractionSourceImpl2)) {
            return;
        }
        MutableInteractionSourceImpl mutableInteractionSourceImpl3 = focusableInteractionNode.interactionSource;
        if (mutableInteractionSourceImpl3 != null && (focusInteraction$Focus = focusableInteractionNode.focusedInteraction) != null) {
            mutableInteractionSourceImpl3.interactions.tryEmit(new FocusInteraction$Unfocus(focusInteraction$Focus));
        }
        focusableInteractionNode.focusedInteraction = null;
        focusableInteractionNode.interactionSource = mutableInteractionSourceImpl2;
    }
}
