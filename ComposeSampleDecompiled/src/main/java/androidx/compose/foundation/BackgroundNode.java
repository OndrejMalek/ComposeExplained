package androidx.compose.foundation;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Outline$Rectangle;
import androidx.compose.ui.graphics.Outline$Rounded;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class BackgroundNode extends Modifier.Node implements DrawModifierNode {
    public float alpha;
    public Brush brush;
    public long color;
    public LayoutDirection lastLayoutDirection;
    public Brush lastOutline;
    public Shape lastShape;
    public Size lastSize;
    public Shape shape;

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        Brush mo38createOutlinePq9zytI;
        ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
        if (this.shape == Brush.RectangleShape) {
            if (!Color.m121equalsimpl0(this.color, Color.Unspecified)) {
                DrawScope.m145drawRectnJ9OG0$default(contentDrawScope, this.color, 0L, 126);
            }
            Brush brush = this.brush;
            if (brush != null) {
                DrawScope.m144drawRectAsUm42w$default(contentDrawScope, brush, 0L, 0L, this.alpha, null, 118);
            }
        } else {
            LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
            CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
            long mo149getSizeNHjbRc = canvasDrawScope.mo149getSizeNHjbRc();
            Size size = this.lastSize;
            int i = Size.$r8$clinit;
            if ((size instanceof Size) && mo149getSizeNHjbRc == size.packedValue && canvasDrawScope.drawParams.layoutDirection == this.lastLayoutDirection && ResultKt.areEqual(this.lastShape, this.shape)) {
                mo38createOutlinePq9zytI = this.lastOutline;
                ResultKt.checkNotNull(mo38createOutlinePq9zytI);
            } else {
                mo38createOutlinePq9zytI = this.shape.mo38createOutlinePq9zytI(canvasDrawScope.mo149getSizeNHjbRc(), canvasDrawScope.drawParams.layoutDirection, contentDrawScope);
            }
            boolean m121equalsimpl0 = Color.m121equalsimpl0(this.color, Color.Unspecified);
            Fill fill = Fill.INSTANCE;
            if (!m121equalsimpl0) {
                long j = this.color;
                if (mo38createOutlinePq9zytI instanceof Outline$Rectangle) {
                    Rect rect = ((Outline$Rectangle) mo38createOutlinePq9zytI).rect;
                    layoutNodeDrawScope.mo141drawRectnJ9OG0(j, _BOUNDARY.Offset(rect.left, rect.top), _BOUNDARY.Size(rect.getWidth(), rect.getHeight()), 1.0f, fill, null, 3);
                } else {
                    if (!(mo38createOutlinePq9zytI instanceof Outline$Rounded)) {
                        throw new RuntimeException();
                    }
                    Outline$Rounded outline$Rounded = (Outline$Rounded) mo38createOutlinePq9zytI;
                    AndroidPath androidPath = outline$Rounded.roundRectPath;
                    if (androidPath != null) {
                        layoutNodeDrawScope.m178drawPathLG529CI(androidPath, j, 1.0f, fill, null, 3);
                    } else {
                        RoundRect roundRect = outline$Rounded.roundRect;
                        float m72getXimpl = CornerRadius.m72getXimpl(roundRect.bottomLeftCornerRadius);
                        layoutNodeDrawScope.m179drawRoundRectuAw5IA(j, _BOUNDARY.Offset(roundRect.left, roundRect.top), _BOUNDARY.Size(roundRect.getWidth(), roundRect.getHeight()), _BOUNDARY.CornerRadius(m72getXimpl, m72getXimpl), fill, 1.0f, null, 3);
                    }
                }
            }
            Brush brush2 = this.brush;
            if (brush2 != null) {
                float f = this.alpha;
                if (mo38createOutlinePq9zytI instanceof Outline$Rectangle) {
                    Rect rect2 = ((Outline$Rectangle) mo38createOutlinePq9zytI).rect;
                    layoutNodeDrawScope.mo140drawRectAsUm42w(brush2, _BOUNDARY.Offset(rect2.left, rect2.top), _BOUNDARY.Size(rect2.getWidth(), rect2.getHeight()), f, fill, null, 3);
                } else {
                    if (!(mo38createOutlinePq9zytI instanceof Outline$Rounded)) {
                        throw new RuntimeException();
                    }
                    Outline$Rounded outline$Rounded2 = (Outline$Rounded) mo38createOutlinePq9zytI;
                    AndroidPath androidPath2 = outline$Rounded2.roundRectPath;
                    if (androidPath2 != null) {
                        layoutNodeDrawScope.m177drawPathGBMwjPU(androidPath2, brush2, f, fill, null, 3);
                    } else {
                        RoundRect roundRect2 = outline$Rounded2.roundRect;
                        float m72getXimpl2 = CornerRadius.m72getXimpl(roundRect2.bottomLeftCornerRadius);
                        layoutNodeDrawScope.mo142drawRoundRectZuiqVtQ(brush2, _BOUNDARY.Offset(roundRect2.left, roundRect2.top), _BOUNDARY.Size(roundRect2.getWidth(), roundRect2.getHeight()), _BOUNDARY.CornerRadius(m72getXimpl2, m72getXimpl2), f, fill, null, 3);
                    }
                }
            }
            this.lastOutline = mo38createOutlinePq9zytI;
            this.lastSize = new Size(canvasDrawScope.mo149getSizeNHjbRc());
            this.lastLayoutDirection = canvasDrawScope.drawParams.layoutDirection;
            this.lastShape = this.shape;
        }
        ((LayoutNodeDrawScope) contentDrawScope).drawContent();
    }
}
