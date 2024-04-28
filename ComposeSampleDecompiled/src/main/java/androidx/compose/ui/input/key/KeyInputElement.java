package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
final class KeyInputElement extends ModifierNodeElement {
    public final Function1 onKeyEvent;

    public KeyInputElement(AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        this.onKeyEvent = androidComposeView$focusOwner$1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.input.key.KeyInputNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        ?? node = new Modifier.Node();
        node.onEvent = this.onKeyEvent;
        node.onPreEvent = null;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KeyInputElement) {
            return ResultKt.areEqual(this.onKeyEvent, ((KeyInputElement) obj).onKeyEvent) && ResultKt.areEqual(null, null);
        }
        return false;
    }

    public final int hashCode() {
        Function1 function1 = this.onKeyEvent;
        return (function1 == null ? 0 : function1.hashCode()) * 31;
    }

    public final String toString() {
        return "KeyInputElement(onKeyEvent=" + this.onKeyEvent + ", onPreKeyEvent=null)";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        KeyInputNode keyInputNode = (KeyInputNode) node;
        ResultKt.checkNotNullParameter(keyInputNode, "node");
        keyInputNode.onEvent = this.onKeyEvent;
        keyInputNode.onPreEvent = null;
    }
}
