package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LayoutNodeDrawScope implements DrawScope, ContentDrawScope {
    public final CanvasDrawScope canvasDrawScope = new CanvasDrawScope();
    public DrawModifierNode drawNode;

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg */
    public final void mo137drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, Brush brush, int i) {
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.canvasDrawScope.mo137drawCircleVaOC9Bg(j, f, j2, f2, drawStyle, brush, i);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:29:0x0096 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0034 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0034 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x009c */
    /* JADX WARN: Type inference failed for: r9v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final void drawContent() {
        Canvas canvas = this.canvasDrawScope.drawContext.getCanvas();
        DelegatableNode delegatableNode = this.drawNode;
        ResultKt.checkNotNull(delegatableNode);
        Modifier.Node node = (Modifier.Node) delegatableNode;
        Modifier.Node node2 = node.node.child;
        if (node2 != null && (node2.aggregateChildKindSet & 4) != 0) {
            while (node2 != null) {
                int i = node2.kindSet;
                if ((i & 2) != 0) {
                    break;
                } else if ((i & 4) != 0) {
                    break;
                } else {
                    node2 = node2.child;
                }
            }
        }
        node2 = null;
        if (node2 == null) {
            NodeCoordinator m215requireCoordinator64DMado = Snake.m215requireCoordinator64DMado(delegatableNode, 4);
            if (m215requireCoordinator64DMado.getTail() == node.node) {
                m215requireCoordinator64DMado = m215requireCoordinator64DMado.wrapped;
                ResultKt.checkNotNull(m215requireCoordinator64DMado);
            }
            m215requireCoordinator64DMado.performDraw(canvas);
            return;
        }
        MutableVector mutableVector = null;
        while (node2 != null) {
            if (node2 instanceof DrawModifierNode) {
                DrawModifierNode drawModifierNode = (DrawModifierNode) node2;
                ResultKt.checkNotNullParameter(canvas, "canvas");
                NodeCoordinator m215requireCoordinator64DMado2 = Snake.m215requireCoordinator64DMado(drawModifierNode, 4);
                long m300toSizeozmzZPI = ResultKt.m300toSizeozmzZPI(m215requireCoordinator64DMado2.measuredSize);
                LayoutNode layoutNode = m215requireCoordinator64DMado2.layoutNode;
                layoutNode.getClass();
                ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSharedDrawScope().m176drawDirectx_KDEd0$ui_release(canvas, m300toSizeozmzZPI, m215requireCoordinator64DMado2, drawModifierNode);
            } else if ((node2.kindSet & 4) != 0 && (node2 instanceof DelegatingNode)) {
                Modifier.Node node3 = ((DelegatingNode) node2).delegate;
                int i2 = 0;
                mutableVector = mutableVector;
                while (node3 != null) {
                    if ((node3.kindSet & 4) != 0) {
                        i2++;
                        mutableVector = mutableVector;
                        if (i2 == 1) {
                            node2 = node3;
                        } else {
                            if (mutableVector == null) {
                                ?? obj = new Object();
                                obj.content = new Modifier.Node[16];
                                obj.size = 0;
                                mutableVector = obj;
                            }
                            if (node2 != null) {
                                mutableVector.add(node2);
                                node2 = null;
                            }
                            mutableVector.add(node3);
                        }
                    }
                    node3 = node3.child;
                    mutableVector = mutableVector;
                }
                if (i2 == 1) {
                }
            }
            node2 = Snake.access$pop(mutableVector);
        }
    }

    /* renamed from: drawDirect-x_KDEd0$ui_release, reason: not valid java name */
    public final void m176drawDirectx_KDEd0$ui_release(Canvas canvas, long j, NodeCoordinator nodeCoordinator, DrawModifierNode drawModifierNode) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        ResultKt.checkNotNullParameter(nodeCoordinator, "coordinator");
        DrawModifierNode drawModifierNode2 = this.drawNode;
        this.drawNode = drawModifierNode;
        LayoutDirection layoutDirection = nodeCoordinator.layoutNode.layoutDirection;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope.drawParams;
        Density density = drawParams.density;
        LayoutDirection layoutDirection2 = drawParams.layoutDirection;
        Canvas canvas2 = drawParams.canvas;
        long j2 = drawParams.size;
        drawParams.density = nodeCoordinator;
        ResultKt.checkNotNullParameter(layoutDirection, "<set-?>");
        drawParams.layoutDirection = layoutDirection;
        drawParams.canvas = canvas;
        drawParams.size = j;
        canvas.save();
        drawModifierNode.draw(this);
        canvas.restore();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope.drawParams;
        drawParams2.getClass();
        ResultKt.checkNotNullParameter(density, "<set-?>");
        drawParams2.density = density;
        ResultKt.checkNotNullParameter(layoutDirection2, "<set-?>");
        drawParams2.layoutDirection = layoutDirection2;
        ResultKt.checkNotNullParameter(canvas2, "<set-?>");
        drawParams2.canvas = canvas2;
        drawParams2.size = j2;
        this.drawNode = drawModifierNode2;
    }

    /* renamed from: drawPath-GBMwjPU, reason: not valid java name */
    public final void m177drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, Brush brush2, int i) {
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(brush, "brush");
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.canvasDrawScope.m138drawPathGBMwjPU(path, brush, f, drawStyle, brush2, i);
    }

    /* renamed from: drawPath-LG529CI, reason: not valid java name */
    public final void m178drawPathLG529CI(AndroidPath androidPath, long j, float f, DrawStyle drawStyle, Brush brush, int i) {
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.canvasDrawScope.m139drawPathLG529CI(androidPath, j, f, drawStyle, brush, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-AsUm42w */
    public final void mo140drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, Brush brush2, int i) {
        ResultKt.checkNotNullParameter(brush, "brush");
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.canvasDrawScope.mo140drawRectAsUm42w(brush, j, j2, f, drawStyle, brush2, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0 */
    public final void mo141drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, Brush brush, int i) {
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.canvasDrawScope.mo141drawRectnJ9OG0(j, j2, j3, f, drawStyle, brush, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ */
    public final void mo142drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, Brush brush2, int i) {
        ResultKt.checkNotNullParameter(brush, "brush");
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.canvasDrawScope.mo142drawRoundRectZuiqVtQ(brush, j, j2, j3, f, drawStyle, brush2, i);
    }

    /* renamed from: drawRoundRect-u-Aw5IA, reason: not valid java name */
    public final void m179drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, Brush brush, int i) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        canvasDrawScope.drawParams.canvas.drawRoundRect(Offset.m76getXimpl(j2), Offset.m77getYimpl(j2), Size.m85getWidthimpl(j3) + Offset.m76getXimpl(j2), Size.m83getHeightimpl(j3) + Offset.m77getYimpl(j2), CornerRadius.m72getXimpl(j4), CornerRadius.m73getYimpl(j4), CanvasDrawScope.m135configurePaint2qPWKa0$default(canvasDrawScope, j, drawStyle, f, brush, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getCenter-F1C5BW0 */
    public final long mo148getCenterF1C5BW0() {
        return this.canvasDrawScope.mo148getCenterF1C5BW0();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.canvasDrawScope.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final CanvasDrawScope$drawContext$1 getDrawContext() {
        return this.canvasDrawScope.drawContext;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.canvasDrawScope.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getSize-NH-jbRc */
    public final long mo149getSizeNHjbRc() {
        return this.canvasDrawScope.mo149getSizeNHjbRc();
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4, reason: not valid java name */
    public final int mo180roundToPx0680j_4(float f) {
        return this.canvasDrawScope.mo180roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public final float mo31toPxR2X_6o(long j) {
        return this.canvasDrawScope.mo31toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public final float mo32toPx0680j_4(float f) {
        return this.canvasDrawScope.getDensity() * f;
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ */
    public final long mo33toSizeXkaWNTQ(long j) {
        return this.canvasDrawScope.mo33toSizeXkaWNTQ(j);
    }
}
