package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.semantics.Role;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ClickableElement extends ModifierNodeElement {
    public final boolean enabled;
    public final MutableInteractionSourceImpl interactionSource;
    public final Function0 onClick;
    public final String onClickLabel;
    public final Role role;

    public ClickableElement(MutableInteractionSourceImpl mutableInteractionSourceImpl, boolean z, String str, Role role, Function0 function0) {
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        ResultKt.checkNotNullParameter(function0, "onClick");
        this.interactionSource = mutableInteractionSourceImpl;
        this.enabled = z;
        this.onClickLabel = str;
        this.role = role;
        this.onClick = function0;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new ClickableNode(this.interactionSource, this.enabled, this.onClickLabel, this.role, this.onClick);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!ResultKt.areEqual(ClickableElement.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.foundation.ClickableElement");
        ClickableElement clickableElement = (ClickableElement) obj;
        return ResultKt.areEqual(this.interactionSource, clickableElement.interactionSource) && this.enabled == clickableElement.enabled && ResultKt.areEqual(this.onClickLabel, clickableElement.onClickLabel) && ResultKt.areEqual(this.role, clickableElement.role) && ResultKt.areEqual(this.onClick, clickableElement.onClick);
    }

    public final int hashCode() {
        int hashCode = (Boolean.hashCode(this.enabled) + (this.interactionSource.hashCode() * 31)) * 31;
        String str = this.onClickLabel;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Role role = this.role;
        return this.onClick.hashCode() + ((hashCode2 + (role != null ? Integer.hashCode(role.value) : 0)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ClickableNode clickableNode = (ClickableNode) node;
        ResultKt.checkNotNullParameter(clickableNode, "node");
        MutableInteractionSourceImpl mutableInteractionSourceImpl = this.interactionSource;
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        Function0 function0 = this.onClick;
        ResultKt.checkNotNullParameter(function0, "onClick");
        if (!ResultKt.areEqual(clickableNode.interactionSource, mutableInteractionSourceImpl)) {
            clickableNode.disposeInteractionSource$1();
            clickableNode.interactionSource = mutableInteractionSourceImpl;
        }
        boolean z = clickableNode.enabled;
        boolean z2 = this.enabled;
        if (z != z2) {
            if (!z2) {
                clickableNode.disposeInteractionSource$1();
            }
            clickableNode.enabled = z2;
        }
        clickableNode.onClick = function0;
        ClickableSemanticsNode clickableSemanticsNode = clickableNode.clickableSemanticsNode;
        clickableSemanticsNode.getClass();
        clickableSemanticsNode.enabled = z2;
        clickableSemanticsNode.onClickLabel = this.onClickLabel;
        clickableSemanticsNode.role = this.role;
        clickableSemanticsNode.onClick = function0;
        clickableSemanticsNode.onLongClickLabel = null;
        clickableSemanticsNode.onLongClick = null;
        ClickablePointerInputNode clickablePointerInputNode = clickableNode.clickablePointerInputNode;
        clickablePointerInputNode.getClass();
        clickablePointerInputNode.enabled = z2;
        clickablePointerInputNode.onClick = function0;
        clickablePointerInputNode.interactionSource = mutableInteractionSourceImpl;
    }
}
