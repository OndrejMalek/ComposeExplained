package androidx.compose.foundation.layout;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
final class WrapContentElement extends ModifierNodeElement {
    public final Object align;
    public final Function2 alignmentCallback;
    public final int direction;
    public final boolean unbounded;

    public WrapContentElement(int i, ComposedModifierKt$materialize$result$1 composedModifierKt$materialize$result$1, Object obj, String str) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "direction");
        this.direction = i;
        this.unbounded = false;
        this.alignmentCallback = composedModifierKt$materialize$result$1;
        this.align = obj;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.layout.WrapContentNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        int i = this.direction;
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "direction");
        Function2 function2 = this.alignmentCallback;
        ResultKt.checkNotNullParameter(function2, "alignmentCallback");
        ?? node = new Modifier.Node();
        node.direction = i;
        node.unbounded = this.unbounded;
        node.alignmentCallback = function2;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!ResultKt.areEqual(WrapContentElement.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.foundation.layout.WrapContentElement");
        WrapContentElement wrapContentElement = (WrapContentElement) obj;
        return this.direction == wrapContentElement.direction && this.unbounded == wrapContentElement.unbounded && ResultKt.areEqual(this.align, wrapContentElement.align);
    }

    public final int hashCode() {
        return this.align.hashCode() + ((Boolean.hashCode(this.unbounded) + (AnimationEndReason$EnumUnboxingLocalUtility.ordinal(this.direction) * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        WrapContentNode wrapContentNode = (WrapContentNode) node;
        ResultKt.checkNotNullParameter(wrapContentNode, "node");
        int i = this.direction;
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "<set-?>");
        wrapContentNode.direction = i;
        wrapContentNode.unbounded = this.unbounded;
        Function2 function2 = this.alignmentCallback;
        ResultKt.checkNotNullParameter(function2, "<set-?>");
        wrapContentNode.alignmentCallback = function2;
    }
}
