package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class PaddingValuesElement extends ModifierNodeElement {
    public final PaddingValuesImpl paddingValues;

    public PaddingValuesElement(PaddingValuesImpl paddingValuesImpl, AbstractMap$toString$1 abstractMap$toString$1) {
        ResultKt.checkNotNullParameter(paddingValuesImpl, "paddingValues");
        this.paddingValues = paddingValuesImpl;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.layout.PaddingValuesModifier, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        PaddingValuesImpl paddingValuesImpl = this.paddingValues;
        ResultKt.checkNotNullParameter(paddingValuesImpl, "paddingValues");
        ?? node = new Modifier.Node();
        node.paddingValues = paddingValuesImpl;
        return node;
    }

    public final boolean equals(Object obj) {
        PaddingValuesElement paddingValuesElement = obj instanceof PaddingValuesElement ? (PaddingValuesElement) obj : null;
        if (paddingValuesElement == null) {
            return false;
        }
        return ResultKt.areEqual(this.paddingValues, paddingValuesElement.paddingValues);
    }

    public final int hashCode() {
        return this.paddingValues.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        PaddingValuesModifier paddingValuesModifier = (PaddingValuesModifier) node;
        ResultKt.checkNotNullParameter(paddingValuesModifier, "node");
        PaddingValuesImpl paddingValuesImpl = this.paddingValues;
        ResultKt.checkNotNullParameter(paddingValuesImpl, "<set-?>");
        paddingValuesModifier.paddingValues = paddingValuesImpl;
    }
}
