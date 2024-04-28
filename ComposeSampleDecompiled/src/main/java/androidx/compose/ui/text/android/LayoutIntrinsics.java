package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.BoringLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LayoutIntrinsics {
    public BoringLayout.Metrics _boringMetrics;
    public float _maxIntrinsicWidth;
    public float _minIntrinsicWidth;
    public boolean boringMetricsIsInit;
    public final CharSequence charSequence;
    public final int textDirectionHeuristic;
    public final TextPaint textPaint;

    public LayoutIntrinsics(CharSequence charSequence, AndroidTextPaint androidTextPaint, int i) {
        ResultKt.checkNotNullParameter(charSequence, "charSequence");
        ResultKt.checkNotNullParameter(androidTextPaint, "textPaint");
        this.charSequence = charSequence;
        this.textPaint = androidTextPaint;
        this.textDirectionHeuristic = i;
        this._maxIntrinsicWidth = Float.NaN;
        this._minIntrinsicWidth = Float.NaN;
    }

    public final BoringLayout.Metrics getBoringMetrics() {
        if (!this.boringMetricsIsInit) {
            TextDirectionHeuristic textDirectionHeuristic = TextLayoutKt.getTextDirectionHeuristic(this.textDirectionHeuristic);
            CharSequence charSequence = this.charSequence;
            ResultKt.checkNotNullParameter(charSequence, "text");
            TextPaint textPaint = this.textPaint;
            ResultKt.checkNotNullParameter(textPaint, "paint");
            this._boringMetrics = Build.VERSION.SDK_INT >= 33 ? BoringLayoutFactory33.isBoring(charSequence, textPaint, textDirectionHeuristic) : BoringLayoutFactoryDefault.isBoring(charSequence, textPaint, textDirectionHeuristic);
            this.boringMetricsIsInit = true;
        }
        return this._boringMetrics;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0069, code lost:
    
        if (r2.nextSpanTransition(-1, r2.length(), androidx.compose.ui.text.android.style.LetterSpacingSpanEm.class) != r2.length()) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
    
        if (r1.getLetterSpacing() == 0.0f) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float getMaxIntrinsicWidth() {
        /*
            r7 = this;
            float r0 = r7._maxIntrinsicWidth
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto Lc
            float r0 = r7._maxIntrinsicWidth
            goto L86
        Lc:
            android.text.BoringLayout$Metrics r0 = r7.getBoringMetrics()
            if (r0 == 0) goto L1a
            int r0 = r0.width
            float r0 = (float) r0
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            goto L1b
        L1a:
            r0 = 0
        L1b:
            android.text.TextPaint r1 = r7.textPaint
            java.lang.CharSequence r2 = r7.charSequence
            if (r0 != 0) goto L34
            int r0 = r2.length()
            r3 = 0
            float r0 = android.text.Layout.getDesiredWidth(r2, r3, r0, r1)
            double r3 = (double) r0
            double r3 = java.lang.Math.ceil(r3)
            float r0 = (float) r3
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
        L34:
            float r3 = r0.floatValue()
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L3e
            goto L80
        L3e:
            boolean r3 = r2 instanceof android.text.Spanned
            if (r3 == 0) goto L6c
            android.text.Spanned r2 = (android.text.Spanned) r2
            java.lang.String r3 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r2, r3)
            int r3 = r2.length()
            r5 = -1
            java.lang.Class<androidx.compose.ui.text.android.style.LetterSpacingSpanPx> r6 = androidx.compose.ui.text.android.style.LetterSpacingSpanPx.class
            int r3 = r2.nextSpanTransition(r5, r3, r6)
            int r6 = r2.length()
            if (r3 == r6) goto L5b
            goto L75
        L5b:
            int r3 = r2.length()
            java.lang.Class<androidx.compose.ui.text.android.style.LetterSpacingSpanEm> r6 = androidx.compose.ui.text.android.style.LetterSpacingSpanEm.class
            int r3 = r2.nextSpanTransition(r5, r3, r6)
            int r2 = r2.length()
            if (r3 == r2) goto L6c
            goto L75
        L6c:
            float r1 = r1.getLetterSpacing()
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 != 0) goto L75
            goto L80
        L75:
            float r0 = r0.floatValue()
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
        L80:
            float r0 = r0.floatValue()
            r7._maxIntrinsicWidth = r0
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutIntrinsics.getMaxIntrinsicWidth():float");
    }
}
