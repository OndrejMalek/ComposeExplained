package androidx.compose.ui.input.rotary;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
final class RotaryInputElement extends ModifierNodeElement {
    public final Function1 onRotaryScrollEvent = InspectableValueKt$NoInspectorInfo$1.INSTANCE$2;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.input.rotary.RotaryInputNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        ?? node = new Modifier.Node();
        node.onEvent = this.onRotaryScrollEvent;
        node.onPreEvent = null;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RotaryInputElement) {
            return ResultKt.areEqual(this.onRotaryScrollEvent, ((RotaryInputElement) obj).onRotaryScrollEvent) && ResultKt.areEqual(null, null);
        }
        return false;
    }

    public final int hashCode() {
        Function1 function1 = this.onRotaryScrollEvent;
        return (function1 == null ? 0 : function1.hashCode()) * 31;
    }

    public final String toString() {
        return "RotaryInputElement(onRotaryScrollEvent=" + this.onRotaryScrollEvent + ", onPreRotaryScrollEvent=null)";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        RotaryInputNode rotaryInputNode = (RotaryInputNode) node;
        ResultKt.checkNotNullParameter(rotaryInputNode, "node");
        rotaryInputNode.onEvent = this.onRotaryScrollEvent;
        rotaryInputNode.onPreEvent = null;
    }
}
