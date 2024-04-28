package androidx.compose.foundation;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class BorderModifierNode$drawRoundRectBorder$1 extends Lambda implements Function1 {
    public final /* synthetic */ long $borderSize;
    public final /* synthetic */ Stroke $borderStroke;
    public final /* synthetic */ Brush $brush;
    public final /* synthetic */ long $cornerRadius;
    public final /* synthetic */ boolean $fillArea;
    public final /* synthetic */ float $halfStroke;
    public final /* synthetic */ float $strokeWidth;
    public final /* synthetic */ long $topLeft;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BorderModifierNode$drawRoundRectBorder$1(boolean z, Brush brush, long j, float f, float f2, long j2, long j3, Stroke stroke) {
        super(1);
        this.$fillArea = z;
        this.$brush = brush;
        this.$cornerRadius = j;
        this.$halfStroke = f;
        this.$strokeWidth = f2;
        this.$topLeft = j2;
        this.$borderSize = j3;
        this.$borderStroke = stroke;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
        ResultKt.checkNotNullParameter(contentDrawScope, "$this$onDrawWithContent");
        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
        layoutNodeDrawScope.drawContent();
        if (this.$fillArea) {
            DrawScope.m146drawRoundRectZuiqVtQ$default(contentDrawScope, this.$brush, 0L, 0L, this.$cornerRadius, null, 246);
        } else {
            long j = this.$cornerRadius;
            float m72getXimpl = CornerRadius.m72getXimpl(j);
            float f = this.$halfStroke;
            if (m72getXimpl < f) {
                CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                float m85getWidthimpl = Size.m85getWidthimpl(canvasDrawScope.mo149getSizeNHjbRc());
                float f2 = this.$strokeWidth;
                float m83getHeightimpl = Size.m83getHeightimpl(canvasDrawScope.mo149getSizeNHjbRc()) - f2;
                CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                long j2 = canvasDrawScope$drawContext$1.this$0.drawParams.size;
                canvasDrawScope$drawContext$1.getCanvas().save();
                Canvas canvas = canvasDrawScope$drawContext$1.transform.$this_asDrawTransform.getCanvas();
                float f3 = this.$strokeWidth;
                canvas.mo88clipRectN_I0leg(f3, f3, m85getWidthimpl - f2, m83getHeightimpl, 0);
                DrawScope.m146drawRoundRectZuiqVtQ$default(contentDrawScope, this.$brush, 0L, 0L, this.$cornerRadius, null, 246);
                canvasDrawScope$drawContext$1.getCanvas().restore();
                canvasDrawScope$drawContext$1.this$0.drawParams.size = j2;
            } else {
                DrawScope.m146drawRoundRectZuiqVtQ$default(contentDrawScope, this.$brush, this.$topLeft, this.$borderSize, BorderKt.m28shrinkKibmq7A(f, j), this.$borderStroke, 208);
            }
        }
        return Unit.INSTANCE;
    }
}
