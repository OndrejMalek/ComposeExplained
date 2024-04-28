package androidx.compose.ui.node;

import androidx.compose.ui.semantics.SemanticsConfiguration;

/* loaded from: classes.dex */
public interface SemanticsModifierNode extends DelegatableNode {
    void applySemantics(SemanticsConfiguration semanticsConfiguration);

    default boolean getShouldClearDescendantSemantics() {
        return false;
    }

    default boolean getShouldMergeDescendantSemantics() {
        return false;
    }
}
