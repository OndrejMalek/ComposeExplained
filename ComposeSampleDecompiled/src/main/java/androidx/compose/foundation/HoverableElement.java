package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class HoverableElement extends ModifierNodeElement {
    public final MutableInteractionSourceImpl interactionSource;

    public HoverableElement(MutableInteractionSourceImpl mutableInteractionSourceImpl) {
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        this.interactionSource = mutableInteractionSourceImpl;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.HoverableNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        MutableInteractionSourceImpl mutableInteractionSourceImpl = this.interactionSource;
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        ?? node = new Modifier.Node();
        node.interactionSource = mutableInteractionSourceImpl;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HoverableElement) && ResultKt.areEqual(((HoverableElement) obj).interactionSource, this.interactionSource);
    }

    public final int hashCode() {
        return this.interactionSource.hashCode() * 31;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        HoverableNode hoverableNode = (HoverableNode) node;
        ResultKt.checkNotNullParameter(hoverableNode, "node");
        MutableInteractionSourceImpl mutableInteractionSourceImpl = this.interactionSource;
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        if (ResultKt.areEqual(hoverableNode.interactionSource, mutableInteractionSourceImpl)) {
            return;
        }
        hoverableNode.tryEmitExit();
        hoverableNode.interactionSource = mutableInteractionSourceImpl;
    }
}
