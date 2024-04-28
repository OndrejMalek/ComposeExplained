package androidx.compose.ui.text.platform;

import android.graphics.Paint;
import android.text.TextPaint;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidTextPaint extends TextPaint {
    public final AndroidPaint composePaint;
    public DrawStyle drawStyle;
    public Shadow shadow;
    public TextDecoration textDecoration;

    public AndroidTextPaint(float f) {
        super(1);
        ((TextPaint) this).density = f;
        this.composePaint = new AndroidPaint(this);
        this.textDecoration = TextDecoration.None;
        this.shadow = Shadow.None;
    }

    /* renamed from: setBrush-12SF9DM, reason: not valid java name */
    public final void m263setBrush12SF9DM(Brush brush, long j, float f) {
        float coerceIn;
        boolean z = brush instanceof BrushKt$ShaderBrush$1;
        AndroidPaint androidPaint = this.composePaint;
        if (!z || j == Size.Unspecified) {
            if (brush == null) {
                androidPaint.setShader(null);
            }
        } else {
            if (Float.isNaN(f)) {
                ResultKt.checkNotNullParameter(androidPaint.internalPaint, "<this>");
                coerceIn = r8.getAlpha() / 255.0f;
            } else {
                coerceIn = ResultKt.coerceIn(f, 0.0f, 1.0f);
            }
            brush.mo110applyToPq9zytI(coerceIn, j, androidPaint);
        }
    }

    public final void setDrawStyle(DrawStyle drawStyle) {
        if (drawStyle == null || ResultKt.areEqual(this.drawStyle, drawStyle)) {
            return;
        }
        this.drawStyle = drawStyle;
        boolean areEqual = ResultKt.areEqual(drawStyle, Fill.INSTANCE);
        AndroidPaint androidPaint = this.composePaint;
        if (areEqual) {
            androidPaint.m95setStylek9PVt8s(0);
            return;
        }
        if (drawStyle instanceof Stroke) {
            androidPaint.m95setStylek9PVt8s(1);
            Stroke stroke = (Stroke) drawStyle;
            Paint paint = androidPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint, "<this>");
            paint.setStrokeWidth(stroke.width);
            Paint paint2 = androidPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint2, "<this>");
            paint2.setStrokeMiter(stroke.miter);
            androidPaint.m94setStrokeJoinWw9F2mQ(stroke.join);
            androidPaint.m93setStrokeCapBeK7IIE(stroke.cap);
            Paint paint3 = androidPaint.internalPaint;
            ResultKt.checkNotNullParameter(paint3, "<this>");
            paint3.setPathEffect(null);
        }
    }

    public final void setShadow(Shadow shadow) {
        if (shadow == null || ResultKt.areEqual(this.shadow, shadow)) {
            return;
        }
        this.shadow = shadow;
        if (ResultKt.areEqual(shadow, Shadow.None)) {
            clearShadowLayer();
            return;
        }
        Shadow shadow2 = this.shadow;
        float f = shadow2.blurRadius;
        if (f == 0.0f) {
            f = Float.MIN_VALUE;
        }
        setShadowLayer(f, Offset.m76getXimpl(shadow2.offset), Offset.m77getYimpl(this.shadow.offset), Brush.m109toArgb8_81llA(this.shadow.color));
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        if (textDecoration == null || ResultKt.areEqual(this.textDecoration, textDecoration)) {
            return;
        }
        this.textDecoration = textDecoration;
        setUnderlineText(textDecoration.contains(TextDecoration.Underline));
        setStrikeThruText(this.textDecoration.contains(TextDecoration.LineThrough));
    }
}
