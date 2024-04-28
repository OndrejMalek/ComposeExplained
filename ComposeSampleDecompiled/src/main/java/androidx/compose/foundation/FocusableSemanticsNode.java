package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import kotlin.ResultKt;
import kotlin.reflect.KProperty;

/* loaded from: classes.dex */
public final class FocusableSemanticsNode extends Modifier.Node implements SemanticsModifierNode, FocusRequesterModifierNode {
    public boolean isFocused;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        boolean z = this.isFocused;
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Focused;
        KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[4];
        Boolean valueOf = Boolean.valueOf(z);
        semanticsPropertyKey.getClass();
        ResultKt.checkNotNullParameter(kProperty, "property");
        semanticsConfiguration.set(semanticsPropertyKey, valueOf);
        semanticsConfiguration.set(SemanticsActions.RequestFocus, new AccessibilityAction(null, new LayoutNode$_foldedChildren$1(2, this)));
    }
}
