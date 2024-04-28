package androidx.compose.ui.semantics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class EmptySemanticsElement extends ModifierNodeElement {
    public static final EmptySemanticsElement INSTANCE = new EmptySemanticsElement();

    private EmptySemanticsElement() {
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new Modifier.Node();
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ResultKt.checkNotNullParameter((EmptySemanticsModifier) node, "node");
    }
}
