package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.platform.WrappedComposition$setContent$1;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class SimpleGraphicsLayerModifier extends Modifier.Node implements LayoutModifierNode {
    public float alpha;
    public long ambientShadowColor;
    public float cameraDistance;
    public boolean clip;
    public int compositingStrategy;
    public SimpleGraphicsLayerModifier$layerBlock$1 layerBlock;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float scaleX;
    public float scaleY;
    public float shadowElevation;
    public Shape shape;
    public long spotShadowColor;
    public long transformOrigin;
    public float translationX;
    public float translationY;

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(j);
        return MeasureScope.layout$default(measureScope, mo164measureBRTryo0.width, mo164measureBRTryo0.height, new WrappedComposition$setContent$1(mo164measureBRTryo0, 6, this));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimpleGraphicsLayerModifier(scaleX=");
        sb.append(this.scaleX);
        sb.append(", scaleY=");
        sb.append(this.scaleY);
        sb.append(", alpha = ");
        sb.append(this.alpha);
        sb.append(", translationX=");
        sb.append(this.translationX);
        sb.append(", translationY=");
        sb.append(this.translationY);
        sb.append(", shadowElevation=");
        sb.append(this.shadowElevation);
        sb.append(", rotationX=");
        sb.append(this.rotationX);
        sb.append(", rotationY=");
        sb.append(this.rotationY);
        sb.append(", rotationZ=");
        sb.append(this.rotationZ);
        sb.append(", cameraDistance=");
        sb.append(this.cameraDistance);
        sb.append(", transformOrigin=");
        long j = this.transformOrigin;
        int i = TransformOrigin.$r8$clinit;
        sb.append((Object) ("TransformOrigin(packedValue=" + j + ')'));
        sb.append(", shape=");
        sb.append(this.shape);
        sb.append(", clip=");
        sb.append(this.clip);
        sb.append(", renderEffect=null, ambientShadowColor=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.ambientShadowColor, sb, ", spotShadowColor=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.spotShadowColor, sb, ", compositingStrategy=");
        sb.append((Object) ("CompositingStrategy(value=" + this.compositingStrategy + ')'));
        sb.append(')');
        return sb.toString();
    }
}
