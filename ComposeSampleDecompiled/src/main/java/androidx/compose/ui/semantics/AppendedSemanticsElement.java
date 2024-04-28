package androidx.compose.ui.semantics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class AppendedSemanticsElement extends ModifierNodeElement implements SemanticsModifier {
    public final boolean mergeDescendants;
    public final Function1 properties;

    public AppendedSemanticsElement(Function1 function1, boolean z) {
        this.mergeDescendants = z;
        this.properties = function1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.Modifier$Node, androidx.compose.ui.semantics.CoreSemanticsModifierNode] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        Function1 function1 = this.properties;
        ResultKt.checkNotNullParameter(function1, "properties");
        ?? node = new Modifier.Node();
        node.mergeDescendants = this.mergeDescendants;
        node.isClearingSemantics = false;
        node.properties = function1;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppendedSemanticsElement)) {
            return false;
        }
        AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) obj;
        return this.mergeDescendants == appendedSemanticsElement.mergeDescendants && ResultKt.areEqual(this.properties, appendedSemanticsElement.properties);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    public final int hashCode() {
        boolean z = this.mergeDescendants;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return this.properties.hashCode() + (r0 * 31);
    }

    public final String toString() {
        return "AppendedSemanticsElement(mergeDescendants=" + this.mergeDescendants + ", properties=" + this.properties + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        CoreSemanticsModifierNode coreSemanticsModifierNode = (CoreSemanticsModifierNode) node;
        ResultKt.checkNotNullParameter(coreSemanticsModifierNode, "node");
        coreSemanticsModifierNode.mergeDescendants = this.mergeDescendants;
        Function1 function1 = this.properties;
        ResultKt.checkNotNullParameter(function1, "<set-?>");
        coreSemanticsModifierNode.properties = function1;
    }
}
