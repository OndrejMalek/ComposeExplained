package androidx.compose.ui.graphics.drawscope;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.unit.Density;

/* loaded from: classes.dex */
public interface DrawScope extends Density {
    /* renamed from: drawRect-AsUm42w$default, reason: not valid java name */
    static void m144drawRectAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, int i) {
        long j3 = (i & 2) != 0 ? Offset.Zero : j;
        drawScope.mo140drawRectAsUm42w(brush, j3, (i & 4) != 0 ? m147offsetSizePENXr5M(drawScope.mo149getSizeNHjbRc(), j3) : j2, (i & 8) != 0 ? 1.0f : f, (i & 16) != 0 ? Fill.INSTANCE : drawStyle, null, 3);
    }

    /* renamed from: drawRect-n-J9OG0$default, reason: not valid java name */
    static void m145drawRectnJ9OG0$default(DrawScope drawScope, long j, long j2, int i) {
        long j3 = Offset.Zero;
        drawScope.mo141drawRectnJ9OG0(j, j3, (i & 4) != 0 ? m147offsetSizePENXr5M(drawScope.mo149getSizeNHjbRc(), j3) : j2, 1.0f, Fill.INSTANCE, null, 3);
    }

    /* renamed from: drawRoundRect-ZuiqVtQ$default, reason: not valid java name */
    static void m146drawRoundRectZuiqVtQ$default(DrawScope drawScope, Brush brush, long j, long j2, long j3, Stroke stroke, int i) {
        long j4 = (i & 2) != 0 ? Offset.Zero : j;
        drawScope.mo142drawRoundRectZuiqVtQ(brush, j4, (i & 4) != 0 ? m147offsetSizePENXr5M(drawScope.mo149getSizeNHjbRc(), j4) : j2, j3, 1.0f, (i & 32) != 0 ? Fill.INSTANCE : stroke, null, 3);
    }

    /* renamed from: offsetSize-PENXr5M, reason: not valid java name */
    static long m147offsetSizePENXr5M(long j, long j2) {
        return _BOUNDARY.Size(Size.m85getWidthimpl(j) - Offset.m76getXimpl(j2), Size.m83getHeightimpl(j) - Offset.m77getYimpl(j2));
    }

    /* renamed from: drawCircle-VaOC9Bg */
    void mo137drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, Brush brush, int i);

    /* renamed from: drawRect-AsUm42w */
    void mo140drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, Brush brush2, int i);

    /* renamed from: drawRect-n-J9OG0 */
    void mo141drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, Brush brush, int i);

    /* renamed from: drawRoundRect-ZuiqVtQ */
    void mo142drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, Brush brush2, int i);

    /* renamed from: getCenter-F1C5BW0, reason: not valid java name */
    default long mo148getCenterF1C5BW0() {
        long j = getDrawContext().this$0.drawParams.size;
        return _BOUNDARY.Offset(Size.m85getWidthimpl(j) / 2.0f, Size.m83getHeightimpl(j) / 2.0f);
    }

    CanvasDrawScope$drawContext$1 getDrawContext();

    /* renamed from: getSize-NH-jbRc, reason: not valid java name */
    default long mo149getSizeNHjbRc() {
        return getDrawContext().this$0.drawParams.size;
    }
}
