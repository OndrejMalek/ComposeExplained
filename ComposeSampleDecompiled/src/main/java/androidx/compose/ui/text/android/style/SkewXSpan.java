package androidx.compose.ui.text.android.style;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SkewXSpan extends MetricAffectingSpan {
    public final /* synthetic */ int $r8$classId;
    public final float skewX;

    public /* synthetic */ SkewXSpan(float f, int i) {
        this.$r8$classId = i;
        this.skewX = f;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        int i = this.$r8$classId;
        float f = this.skewX;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(textPaint, "textPaint");
                textPaint.setTextSkewX(textPaint.getTextSkewX() + f);
                return;
            default:
                ResultKt.checkNotNullParameter(textPaint, "textPaint");
                textPaint.baselineShift += (int) Math.ceil(textPaint.ascent() * f);
                return;
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        int i = this.$r8$classId;
        float f = this.skewX;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(textPaint, "textPaint");
                textPaint.setTextSkewX(textPaint.getTextSkewX() + f);
                return;
            default:
                ResultKt.checkNotNullParameter(textPaint, "textPaint");
                textPaint.baselineShift += (int) Math.ceil(textPaint.ascent() * f);
                return;
        }
    }
}
