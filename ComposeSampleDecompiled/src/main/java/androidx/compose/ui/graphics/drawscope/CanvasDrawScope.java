package androidx.compose.ui.graphics.drawscope;

import android.graphics.Paint;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityImpl;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CanvasDrawScope implements DrawScope {
    public final CanvasDrawScope$drawContext$1 drawContext;
    public final DrawParams drawParams;
    public AndroidPaint fillPaint;
    public AndroidPaint strokePaint;

    /* loaded from: classes.dex */
    public final class DrawParams {
        public Canvas canvas;
        public Density density;
        public LayoutDirection layoutDirection;
        public long size;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrawParams)) {
                return false;
            }
            DrawParams drawParams = (DrawParams) obj;
            if (!ResultKt.areEqual(this.density, drawParams.density) || this.layoutDirection != drawParams.layoutDirection || !ResultKt.areEqual(this.canvas, drawParams.canvas)) {
                return false;
            }
            long j = this.size;
            long j2 = drawParams.size;
            int i = Size.$r8$clinit;
            return j == j2;
        }

        public final int hashCode() {
            int hashCode = (this.canvas.hashCode() + ((this.layoutDirection.hashCode() + (this.density.hashCode() * 31)) * 31)) * 31;
            long j = this.size;
            int i = Size.$r8$clinit;
            return Long.hashCode(j) + hashCode;
        }

        public final String toString() {
            return "DrawParams(density=" + this.density + ", layoutDirection=" + this.layoutDirection + ", canvas=" + this.canvas + ", size=" + ((Object) Size.m86toStringimpl(this.size)) + ')';
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, androidx.compose.ui.graphics.drawscope.CanvasDrawScope$DrawParams] */
    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.compose.ui.graphics.Canvas, java.lang.Object] */
    public CanvasDrawScope() {
        DensityImpl densityImpl = DrawStyle.DefaultDensity;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        ?? obj = new Object();
        long j = Size.Zero;
        ?? obj2 = new Object();
        obj2.density = densityImpl;
        obj2.layoutDirection = layoutDirection;
        obj2.canvas = obj;
        obj2.size = j;
        this.drawParams = obj2;
        this.drawContext = new CanvasDrawScope$drawContext$1(this);
    }

    /* renamed from: configurePaint-2qPWKa0$default, reason: not valid java name */
    public static AndroidPaint m135configurePaint2qPWKa0$default(CanvasDrawScope canvasDrawScope, long j, DrawStyle drawStyle, float f, Brush brush, int i) {
        AndroidPaint selectPaint = canvasDrawScope.selectPaint(drawStyle);
        if (f != 1.0f) {
            j = Color.m120copywmQWz5c$default(j, Color.m122getAlphaimpl(j) * f);
        }
        ResultKt.checkNotNullParameter(selectPaint.internalPaint, "<this>");
        if (!Color.m121equalsimpl0(r6.getColor() << 32, j)) {
            selectPaint.m92setColor8_81llA(j);
        }
        if (selectPaint.internalShader != null) {
            selectPaint.setShader(null);
        }
        selectPaint.getClass();
        if (!ResultKt.areEqual(null, brush)) {
            selectPaint.getClass();
            Paint paint = selectPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint, "<this>");
            paint.setColorFilter(null);
        }
        if (!Brush.m98equalsimpl0(selectPaint._blendMode, i)) {
            selectPaint.m91setBlendModes9anfk8(i);
        }
        Paint paint2 = selectPaint.internalPaint;
        ResultKt.checkNotNullParameter(paint2, "<this>");
        if (!paint2.isFilterBitmap()) {
            Paint paint3 = selectPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint3, "$this$setNativeFilterQuality");
            paint3.setFilterBitmap(true);
        }
        return selectPaint;
    }

    /* renamed from: configurePaint-swdJneE, reason: not valid java name */
    public final AndroidPaint m136configurePaintswdJneE(Brush brush, DrawStyle drawStyle, float f, Brush brush2, int i, int i2) {
        AndroidPaint selectPaint = selectPaint(drawStyle);
        if (brush != null) {
            brush.mo110applyToPq9zytI(f, mo149getSizeNHjbRc(), selectPaint);
        } else {
            ResultKt.checkNotNullParameter(selectPaint.internalPaint, "<this>");
            if (r5.getAlpha() / 255.0f != f) {
                selectPaint.setAlpha(f);
            }
        }
        selectPaint.getClass();
        if (!ResultKt.areEqual(null, brush2)) {
            selectPaint.getClass();
            Paint paint = selectPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint, "<this>");
            paint.setColorFilter(null);
        }
        if (!Brush.m98equalsimpl0(selectPaint._blendMode, i)) {
            selectPaint.m91setBlendModes9anfk8(i);
        }
        Paint paint2 = selectPaint.internalPaint;
        ResultKt.checkNotNullParameter(paint2, "<this>");
        if (paint2.isFilterBitmap() != i2) {
            Paint paint3 = selectPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint3, "$this$setNativeFilterQuality");
            paint3.setFilterBitmap(true ^ (i2 == 0));
        }
        return selectPaint;
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg, reason: not valid java name */
    public final void mo137drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, Brush brush, int i) {
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.drawParams.canvas.mo90drawCircle9KIMszo(f, j2, m135configurePaint2qPWKa0$default(this, j, drawStyle, f2, brush, i));
    }

    /* renamed from: drawPath-GBMwjPU, reason: not valid java name */
    public final void m138drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, Brush brush2, int i) {
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(brush, "brush");
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.drawParams.canvas.drawPath(path, m136configurePaintswdJneE(brush, drawStyle, f, brush2, i, 1));
    }

    /* renamed from: drawPath-LG529CI, reason: not valid java name */
    public final void m139drawPathLG529CI(AndroidPath androidPath, long j, float f, DrawStyle drawStyle, Brush brush, int i) {
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.drawParams.canvas.drawPath(androidPath, m135configurePaint2qPWKa0$default(this, j, drawStyle, f, brush, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-AsUm42w, reason: not valid java name */
    public final void mo140drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, Brush brush2, int i) {
        ResultKt.checkNotNullParameter(brush, "brush");
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.drawParams.canvas.drawRect(Offset.m76getXimpl(j), Offset.m77getYimpl(j), Size.m85getWidthimpl(j2) + Offset.m76getXimpl(j), Size.m83getHeightimpl(j2) + Offset.m77getYimpl(j), m136configurePaintswdJneE(brush, drawStyle, f, brush2, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0, reason: not valid java name */
    public final void mo141drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, Brush brush, int i) {
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.drawParams.canvas.drawRect(Offset.m76getXimpl(j2), Offset.m77getYimpl(j2), Size.m85getWidthimpl(j3) + Offset.m76getXimpl(j2), Size.m83getHeightimpl(j3) + Offset.m77getYimpl(j2), m135configurePaint2qPWKa0$default(this, j, drawStyle, f, brush, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ, reason: not valid java name */
    public final void mo142drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, Brush brush2, int i) {
        ResultKt.checkNotNullParameter(brush, "brush");
        ResultKt.checkNotNullParameter(drawStyle, "style");
        this.drawParams.canvas.drawRoundRect(Offset.m76getXimpl(j), Offset.m77getYimpl(j), Size.m85getWidthimpl(j2) + Offset.m76getXimpl(j), Size.m83getHeightimpl(j2) + Offset.m77getYimpl(j), CornerRadius.m72getXimpl(j3), CornerRadius.m73getYimpl(j3), m136configurePaintswdJneE(brush, drawStyle, f, brush2, i, 1));
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.drawParams.density.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final CanvasDrawScope$drawContext$1 getDrawContext() {
        return this.drawContext;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.drawParams.density.getFontScale();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.graphics.AndroidPaint selectPaint(androidx.compose.ui.graphics.drawscope.DrawStyle r11) {
        /*
            r10 = this;
            androidx.compose.ui.graphics.drawscope.Fill r0 = androidx.compose.ui.graphics.drawscope.Fill.INSTANCE
            boolean r0 = kotlin.ResultKt.areEqual(r11, r0)
            r1 = 0
            if (r0 == 0) goto L18
            androidx.compose.ui.graphics.AndroidPaint r11 = r10.fillPaint
            if (r11 != 0) goto Lb6
            androidx.compose.ui.graphics.AndroidPaint r11 = androidx.compose.ui.graphics.Brush.Paint()
            r11.m95setStylek9PVt8s(r1)
            r10.fillPaint = r11
            goto Lb6
        L18:
            boolean r0 = r11 instanceof androidx.compose.ui.graphics.drawscope.Stroke
            if (r0 == 0) goto Lb7
            androidx.compose.ui.graphics.AndroidPaint r0 = r10.strokePaint
            r2 = 1
            if (r0 != 0) goto L2a
            androidx.compose.ui.graphics.AndroidPaint r0 = androidx.compose.ui.graphics.Brush.Paint()
            r0.m95setStylek9PVt8s(r2)
            r10.strokePaint = r0
        L2a:
            android.graphics.Paint r3 = r0.internalPaint
            java.lang.String r4 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r3, r4)
            float r5 = r3.getStrokeWidth()
            androidx.compose.ui.graphics.drawscope.Stroke r11 = (androidx.compose.ui.graphics.drawscope.Stroke) r11
            float r6 = r11.width
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 != 0) goto L3e
            goto L46
        L3e:
            android.graphics.Paint r5 = r0.internalPaint
            kotlin.ResultKt.checkNotNullParameter(r5, r4)
            r5.setStrokeWidth(r6)
        L46:
            android.graphics.Paint$Cap r5 = r3.getStrokeCap()
            r6 = -1
            if (r5 != 0) goto L4f
            r5 = r6
            goto L57
        L4f:
            int[] r7 = androidx.compose.ui.graphics.AndroidPaint_androidKt$WhenMappings.$EnumSwitchMapping$1
            int r5 = r5.ordinal()
            r5 = r7[r5]
        L57:
            r7 = 3
            r8 = 2
            if (r5 == r2) goto L5f
            if (r5 == r8) goto L63
            if (r5 == r7) goto L61
        L5f:
            r5 = r1
            goto L64
        L61:
            r5 = r8
            goto L64
        L63:
            r5 = r2
        L64:
            int r9 = r11.cap
            boolean r5 = androidx.compose.ui.graphics.Brush.m100equalsimpl0$2(r5, r9)
            if (r5 != 0) goto L6f
            r0.m93setStrokeCapBeK7IIE(r9)
        L6f:
            float r5 = r3.getStrokeMiter()
            float r9 = r11.miter
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 != 0) goto L7a
            goto L82
        L7a:
            android.graphics.Paint r5 = r0.internalPaint
            kotlin.ResultKt.checkNotNullParameter(r5, r4)
            r5.setStrokeMiter(r9)
        L82:
            android.graphics.Paint$Join r3 = r3.getStrokeJoin()
            if (r3 != 0) goto L89
            goto L91
        L89:
            int[] r5 = androidx.compose.ui.graphics.AndroidPaint_androidKt$WhenMappings.$EnumSwitchMapping$2
            int r3 = r3.ordinal()
            r6 = r5[r3]
        L91:
            if (r6 == r2) goto L9b
            if (r6 == r8) goto L9a
            if (r6 == r7) goto L98
            goto L9b
        L98:
            r1 = r2
            goto L9b
        L9a:
            r1 = r8
        L9b:
            int r11 = r11.join
            boolean r1 = androidx.compose.ui.graphics.Brush.m101equalsimpl0$3(r1, r11)
            if (r1 != 0) goto La6
            r0.m94setStrokeJoinWw9F2mQ(r11)
        La6:
            r11 = 0
            boolean r1 = kotlin.ResultKt.areEqual(r11, r11)
            if (r1 != 0) goto Lb5
            android.graphics.Paint r1 = r0.internalPaint
            kotlin.ResultKt.checkNotNullParameter(r1, r4)
            r1.setPathEffect(r11)
        Lb5:
            r11 = r0
        Lb6:
            return r11
        Lb7:
            androidx.startup.StartupException r11 = new androidx.startup.StartupException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.drawscope.CanvasDrawScope.selectPaint(androidx.compose.ui.graphics.drawscope.DrawStyle):androidx.compose.ui.graphics.AndroidPaint");
    }
}
