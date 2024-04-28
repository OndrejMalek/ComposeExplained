package androidx.compose.ui.text.android.style;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TypefaceSpan extends MetricAffectingSpan {
    public final /* synthetic */ int $r8$classId = 0;
    public final Object typeface;

    public TypefaceSpan(Typeface typeface) {
        ResultKt.checkNotNullParameter(typeface, "typeface");
        this.typeface = typeface;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        int i = this.$r8$classId;
        Object obj = this.typeface;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(textPaint, "ds");
                textPaint.setTypeface((Typeface) obj);
                return;
            default:
                ResultKt.checkNotNullParameter(textPaint, "textPaint");
                textPaint.setFontFeatureSettings((String) obj);
                return;
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        int i = this.$r8$classId;
        Object obj = this.typeface;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(textPaint, "paint");
                textPaint.setTypeface((Typeface) obj);
                return;
            default:
                ResultKt.checkNotNullParameter(textPaint, "textPaint");
                textPaint.setFontFeatureSettings((String) obj);
                return;
        }
    }

    public TypefaceSpan(String str) {
        this.typeface = str;
    }
}
