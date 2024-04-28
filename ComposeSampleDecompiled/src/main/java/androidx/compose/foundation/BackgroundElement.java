package androidx.compose.foundation;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;

/* loaded from: classes.dex */
final class BackgroundElement extends ModifierNodeElement {
    public final float alpha;
    public final Brush brush;
    public final long color;
    public final Shape shape;

    public BackgroundElement(long j, Shape shape) {
        ResultKt.checkNotNullParameter(shape, "shape");
        this.color = j;
        this.brush = null;
        this.alpha = 1.0f;
        this.shape = shape;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.BackgroundNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        Shape shape = this.shape;
        ResultKt.checkNotNullParameter(shape, "shape");
        ?? node = new Modifier.Node();
        node.color = this.color;
        node.brush = this.brush;
        node.alpha = this.alpha;
        node.shape = shape;
        return node;
    }

    public final boolean equals(Object obj) {
        BackgroundElement backgroundElement = obj instanceof BackgroundElement ? (BackgroundElement) obj : null;
        return backgroundElement != null && Color.m121equalsimpl0(this.color, backgroundElement.color) && ResultKt.areEqual(this.brush, backgroundElement.brush) && this.alpha == backgroundElement.alpha && ResultKt.areEqual(this.shape, backgroundElement.shape);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        int hashCode = Long.hashCode(this.color) * 31;
        Brush brush = this.brush;
        return this.shape.hashCode() + AnimationEndReason$EnumUnboxingLocalUtility.m(this.alpha, (hashCode + (brush != null ? brush.hashCode() : 0)) * 31, 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        BackgroundNode backgroundNode = (BackgroundNode) node;
        ResultKt.checkNotNullParameter(backgroundNode, "node");
        backgroundNode.color = this.color;
        backgroundNode.brush = this.brush;
        backgroundNode.alpha = this.alpha;
        Shape shape = this.shape;
        ResultKt.checkNotNullParameter(shape, "<set-?>");
        backgroundNode.shape = shape;
    }
}
