package androidx.compose.ui.text.android.style;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LetterSpacingSpanEm extends MetricAffectingSpan {
    public final float letterSpacing;

    public LetterSpacingSpanEm(float f) {
        this.letterSpacing = f;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        ResultKt.checkNotNullParameter(textPaint, "textPaint");
        textPaint.setLetterSpacing(this.letterSpacing);
    }

    @Override // android.text.style.MetricAffectingSpan
    public final void updateMeasureState(TextPaint textPaint) {
        ResultKt.checkNotNullParameter(textPaint, "textPaint");
        textPaint.setLetterSpacing(this.letterSpacing);
    }
}
