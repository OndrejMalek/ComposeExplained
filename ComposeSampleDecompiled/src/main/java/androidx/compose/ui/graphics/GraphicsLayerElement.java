package androidx.compose.ui.graphics;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Snake;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GraphicsLayerElement extends ModifierNodeElement {
    public final float alpha;
    public final long ambientShadowColor;
    public final float cameraDistance;
    public final boolean clip;
    public final int compositingStrategy;
    public final float rotationX;
    public final float rotationY;
    public final float rotationZ;
    public final float scaleX;
    public final float scaleY;
    public final float shadowElevation;
    public final Shape shape;
    public final long spotShadowColor;
    public final long transformOrigin;
    public final float translationX;
    public final float translationY;

    public GraphicsLayerElement(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i) {
        this.scaleX = f;
        this.scaleY = f2;
        this.alpha = f3;
        this.translationX = f4;
        this.translationY = f5;
        this.shadowElevation = f6;
        this.rotationX = f7;
        this.rotationY = f8;
        this.rotationZ = f9;
        this.cameraDistance = f10;
        this.transformOrigin = j;
        this.shape = shape;
        this.clip = z;
        this.ambientShadowColor = j2;
        this.spotShadowColor = j3;
        this.compositingStrategy = i;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.graphics.SimpleGraphicsLayerModifier, androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v15, types: [androidx.compose.ui.graphics.SimpleGraphicsLayerModifier$layerBlock$1] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        Shape shape = this.shape;
        ResultKt.checkNotNullParameter(shape, "shape");
        final ?? node = new Modifier.Node();
        node.scaleX = this.scaleX;
        node.scaleY = this.scaleY;
        node.alpha = this.alpha;
        node.translationX = this.translationX;
        node.translationY = this.translationY;
        node.shadowElevation = this.shadowElevation;
        node.rotationX = this.rotationX;
        node.rotationY = this.rotationY;
        node.rotationZ = this.rotationZ;
        node.cameraDistance = this.cameraDistance;
        node.transformOrigin = this.transformOrigin;
        node.shape = shape;
        node.clip = this.clip;
        node.ambientShadowColor = this.ambientShadowColor;
        node.spotShadowColor = this.spotShadowColor;
        node.compositingStrategy = this.compositingStrategy;
        node.layerBlock = new Function1() { // from class: androidx.compose.ui.graphics.SimpleGraphicsLayerModifier$layerBlock$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) obj;
                ResultKt.checkNotNullParameter(reusableGraphicsLayerScope, "$this$null");
                SimpleGraphicsLayerModifier simpleGraphicsLayerModifier = SimpleGraphicsLayerModifier.this;
                reusableGraphicsLayerScope.scaleX = simpleGraphicsLayerModifier.scaleX;
                reusableGraphicsLayerScope.scaleY = simpleGraphicsLayerModifier.scaleY;
                reusableGraphicsLayerScope.alpha = simpleGraphicsLayerModifier.alpha;
                reusableGraphicsLayerScope.translationX = simpleGraphicsLayerModifier.translationX;
                reusableGraphicsLayerScope.translationY = simpleGraphicsLayerModifier.translationY;
                reusableGraphicsLayerScope.shadowElevation = simpleGraphicsLayerModifier.shadowElevation;
                reusableGraphicsLayerScope.rotationX = simpleGraphicsLayerModifier.rotationX;
                reusableGraphicsLayerScope.rotationY = simpleGraphicsLayerModifier.rotationY;
                reusableGraphicsLayerScope.rotationZ = simpleGraphicsLayerModifier.rotationZ;
                reusableGraphicsLayerScope.cameraDistance = simpleGraphicsLayerModifier.cameraDistance;
                reusableGraphicsLayerScope.transformOrigin = simpleGraphicsLayerModifier.transformOrigin;
                Shape shape2 = simpleGraphicsLayerModifier.shape;
                ResultKt.checkNotNullParameter(shape2, "<set-?>");
                reusableGraphicsLayerScope.shape = shape2;
                reusableGraphicsLayerScope.clip = simpleGraphicsLayerModifier.clip;
                reusableGraphicsLayerScope.ambientShadowColor = simpleGraphicsLayerModifier.ambientShadowColor;
                reusableGraphicsLayerScope.spotShadowColor = simpleGraphicsLayerModifier.spotShadowColor;
                reusableGraphicsLayerScope.compositingStrategy = simpleGraphicsLayerModifier.compositingStrategy;
                return Unit.INSTANCE;
            }
        };
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphicsLayerElement)) {
            return false;
        }
        GraphicsLayerElement graphicsLayerElement = (GraphicsLayerElement) obj;
        if (Float.compare(this.scaleX, graphicsLayerElement.scaleX) != 0 || Float.compare(this.scaleY, graphicsLayerElement.scaleY) != 0 || Float.compare(this.alpha, graphicsLayerElement.alpha) != 0 || Float.compare(this.translationX, graphicsLayerElement.translationX) != 0 || Float.compare(this.translationY, graphicsLayerElement.translationY) != 0 || Float.compare(this.shadowElevation, graphicsLayerElement.shadowElevation) != 0 || Float.compare(this.rotationX, graphicsLayerElement.rotationX) != 0 || Float.compare(this.rotationY, graphicsLayerElement.rotationY) != 0 || Float.compare(this.rotationZ, graphicsLayerElement.rotationZ) != 0 || Float.compare(this.cameraDistance, graphicsLayerElement.cameraDistance) != 0) {
            return false;
        }
        int i = TransformOrigin.$r8$clinit;
        return this.transformOrigin == graphicsLayerElement.transformOrigin && ResultKt.areEqual(this.shape, graphicsLayerElement.shape) && this.clip == graphicsLayerElement.clip && ResultKt.areEqual(null, null) && Color.m121equalsimpl0(this.ambientShadowColor, graphicsLayerElement.ambientShadowColor) && Color.m121equalsimpl0(this.spotShadowColor, graphicsLayerElement.spotShadowColor) && Brush.m99equalsimpl0$1(this.compositingStrategy, graphicsLayerElement.compositingStrategy);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v14, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public final int hashCode() {
        int m = AnimationEndReason$EnumUnboxingLocalUtility.m(this.cameraDistance, AnimationEndReason$EnumUnboxingLocalUtility.m(this.rotationZ, AnimationEndReason$EnumUnboxingLocalUtility.m(this.rotationY, AnimationEndReason$EnumUnboxingLocalUtility.m(this.rotationX, AnimationEndReason$EnumUnboxingLocalUtility.m(this.shadowElevation, AnimationEndReason$EnumUnboxingLocalUtility.m(this.translationY, AnimationEndReason$EnumUnboxingLocalUtility.m(this.translationX, AnimationEndReason$EnumUnboxingLocalUtility.m(this.alpha, AnimationEndReason$EnumUnboxingLocalUtility.m(this.scaleY, Float.hashCode(this.scaleX) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31);
        int i = TransformOrigin.$r8$clinit;
        int hashCode = (this.shape.hashCode() + ((Long.hashCode(this.transformOrigin) + m) * 31)) * 31;
        boolean z = this.clip;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 961;
        int i4 = Color.$r8$clinit;
        return Integer.hashCode(this.compositingStrategy) + ((Long.hashCode(this.spotShadowColor) + ((Long.hashCode(this.ambientShadowColor) + i3) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GraphicsLayerElement(scaleX=");
        sb.append(this.scaleX);
        sb.append(", scaleY=");
        sb.append(this.scaleY);
        sb.append(", alpha=");
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
        int i = TransformOrigin.$r8$clinit;
        sb.append((Object) ("TransformOrigin(packedValue=" + this.transformOrigin + ')'));
        sb.append(", shape=");
        sb.append(this.shape);
        sb.append(", clip=");
        sb.append(this.clip);
        sb.append(", renderEffect=null, ambientShadowColor=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.ambientShadowColor, sb, ", spotShadowColor=");
        sb.append((Object) Color.m127toStringimpl(this.spotShadowColor));
        sb.append(", compositingStrategy=");
        sb.append((Object) ("CompositingStrategy(value=" + this.compositingStrategy + ')'));
        sb.append(')');
        return sb.toString();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        SimpleGraphicsLayerModifier simpleGraphicsLayerModifier = (SimpleGraphicsLayerModifier) node;
        ResultKt.checkNotNullParameter(simpleGraphicsLayerModifier, "node");
        simpleGraphicsLayerModifier.scaleX = this.scaleX;
        simpleGraphicsLayerModifier.scaleY = this.scaleY;
        simpleGraphicsLayerModifier.alpha = this.alpha;
        simpleGraphicsLayerModifier.translationX = this.translationX;
        simpleGraphicsLayerModifier.translationY = this.translationY;
        simpleGraphicsLayerModifier.shadowElevation = this.shadowElevation;
        simpleGraphicsLayerModifier.rotationX = this.rotationX;
        simpleGraphicsLayerModifier.rotationY = this.rotationY;
        simpleGraphicsLayerModifier.rotationZ = this.rotationZ;
        simpleGraphicsLayerModifier.cameraDistance = this.cameraDistance;
        simpleGraphicsLayerModifier.transformOrigin = this.transformOrigin;
        Shape shape = this.shape;
        ResultKt.checkNotNullParameter(shape, "<set-?>");
        simpleGraphicsLayerModifier.shape = shape;
        simpleGraphicsLayerModifier.clip = this.clip;
        simpleGraphicsLayerModifier.ambientShadowColor = this.ambientShadowColor;
        simpleGraphicsLayerModifier.spotShadowColor = this.spotShadowColor;
        simpleGraphicsLayerModifier.compositingStrategy = this.compositingStrategy;
        NodeCoordinator nodeCoordinator = Snake.m215requireCoordinator64DMado(simpleGraphicsLayerModifier, 2).wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.updateLayerBlock(simpleGraphicsLayerModifier.layerBlock, true);
        }
    }
}
