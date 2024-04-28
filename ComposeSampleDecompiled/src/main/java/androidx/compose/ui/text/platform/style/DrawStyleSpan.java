package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DrawStyleSpan extends CharacterStyle implements UpdateAppearance {
    public final DrawStyle drawStyle;

    public DrawStyleSpan(DrawStyle drawStyle) {
        this.drawStyle = drawStyle;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            Fill fill = Fill.INSTANCE;
            DrawStyle drawStyle = this.drawStyle;
            if (ResultKt.areEqual(drawStyle, fill)) {
                textPaint.setStyle(Paint.Style.FILL);
                return;
            }
            if (drawStyle instanceof Stroke) {
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setStrokeWidth(((Stroke) drawStyle).width);
                textPaint.setStrokeMiter(((Stroke) drawStyle).miter);
                int i = ((Stroke) drawStyle).join;
                textPaint.setStrokeJoin(Brush.m101equalsimpl0$3(i, 0) ? Paint.Join.MITER : Brush.m101equalsimpl0$3(i, 1) ? Paint.Join.ROUND : Brush.m101equalsimpl0$3(i, 2) ? Paint.Join.BEVEL : Paint.Join.MITER);
                int i2 = ((Stroke) drawStyle).cap;
                textPaint.setStrokeCap(Brush.m100equalsimpl0$2(i2, 0) ? Paint.Cap.BUTT : Brush.m100equalsimpl0$2(i2, 1) ? Paint.Cap.ROUND : Brush.m100equalsimpl0$2(i2, 2) ? Paint.Cap.SQUARE : Paint.Cap.BUTT);
                ((Stroke) drawStyle).getClass();
                textPaint.setPathEffect(null);
            }
        }
    }
}
