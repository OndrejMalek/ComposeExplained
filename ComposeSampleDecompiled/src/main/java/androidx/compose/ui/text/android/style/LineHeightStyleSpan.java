package androidx.compose.ui.text.android.style;

import android.graphics.Paint;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LineHeightStyleSpan implements android.text.style.LineHeightSpan {
    public final int endIndex;
    public int firstAscentDiff;
    public int lastDescentDiff;
    public final float lineHeight;
    public final float topRatio;
    public final boolean trimFirstLineTop;
    public final boolean trimLastLineBottom;
    public final int startIndex = 0;
    public int firstAscent = Integer.MIN_VALUE;
    public int ascent = Integer.MIN_VALUE;
    public int descent = Integer.MIN_VALUE;
    public int lastDescent = Integer.MIN_VALUE;

    public LineHeightStyleSpan(float f, int i, boolean z, boolean z2, float f2) {
        this.lineHeight = f;
        this.endIndex = i;
        this.trimFirstLineTop = z;
        this.trimLastLineBottom = z2;
        this.topRatio = f2;
        if ((0.0f > f2 || f2 > 1.0f) && f2 != -1.0f) {
            throw new IllegalStateException("topRatio should be in [0..1] range or -1".toString());
        }
    }

    @Override // android.text.style.LineHeightSpan
    public final void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(fontMetricsInt, "fontMetricsInt");
        if (fontMetricsInt.descent - fontMetricsInt.ascent <= 0) {
            return;
        }
        boolean z = i == this.startIndex;
        boolean z2 = i2 == this.endIndex;
        boolean z3 = this.trimLastLineBottom;
        boolean z4 = this.trimFirstLineTop;
        if (z && z2 && z4 && z3) {
            return;
        }
        if (this.firstAscent == Integer.MIN_VALUE) {
            int ceil = (int) Math.ceil(this.lineHeight);
            int i5 = ceil - (fontMetricsInt.descent - fontMetricsInt.ascent);
            float f = this.topRatio;
            if (f == -1.0f) {
                f = Math.abs(fontMetricsInt.ascent) / (fontMetricsInt.descent - fontMetricsInt.ascent);
            }
            int ceil2 = (int) (i5 <= 0 ? Math.ceil(i5 * f) : Math.ceil((1.0f - f) * i5));
            int i6 = fontMetricsInt.descent;
            int i7 = ceil2 + i6;
            this.descent = i7;
            int i8 = i7 - ceil;
            this.ascent = i8;
            if (z4) {
                i8 = fontMetricsInt.ascent;
            }
            this.firstAscent = i8;
            if (z3) {
                i7 = i6;
            }
            this.lastDescent = i7;
            this.firstAscentDiff = fontMetricsInt.ascent - i8;
            this.lastDescentDiff = i7 - i6;
        }
        fontMetricsInt.ascent = z ? this.firstAscent : this.ascent;
        fontMetricsInt.descent = z2 ? this.lastDescent : this.descent;
    }
}
