package androidx.compose.ui.text.android;

import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class StaticLayoutParams {
    public final Layout.Alignment alignment;
    public final int breakStrategy;
    public final TextUtils.TruncateAt ellipsize;
    public final int ellipsizedWidth;
    public final int end;
    public final int hyphenationFrequency;
    public final boolean includePadding;
    public final int justificationMode;
    public final int[] leftIndents;
    public final int lineBreakStyle;
    public final int lineBreakWordStyle;
    public final float lineSpacingExtra;
    public final float lineSpacingMultiplier;
    public final int maxLines;
    public final TextPaint paint;
    public final int[] rightIndents;
    public final int start;
    public final CharSequence text;
    public final TextDirectionHeuristic textDir;
    public final boolean useFallbackLineSpacing;
    public final int width;

    public StaticLayoutParams(CharSequence charSequence, int i, int i2, AndroidTextPaint androidTextPaint, int i3, TextDirectionHeuristic textDirectionHeuristic, Layout.Alignment alignment, int i4, TextUtils.TruncateAt truncateAt, int i5, float f, float f2, int i6, boolean z, boolean z2, int i7, int i8, int i9, int i10, int[] iArr, int[] iArr2) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(androidTextPaint, "paint");
        ResultKt.checkNotNullParameter(textDirectionHeuristic, "textDir");
        ResultKt.checkNotNullParameter(alignment, "alignment");
        this.text = charSequence;
        this.start = i;
        this.end = i2;
        this.paint = androidTextPaint;
        this.width = i3;
        this.textDir = textDirectionHeuristic;
        this.alignment = alignment;
        this.maxLines = i4;
        this.ellipsize = truncateAt;
        this.ellipsizedWidth = i5;
        this.lineSpacingMultiplier = f;
        this.lineSpacingExtra = f2;
        this.justificationMode = i6;
        this.includePadding = z;
        this.useFallbackLineSpacing = z2;
        this.breakStrategy = i7;
        this.lineBreakStyle = i8;
        this.lineBreakWordStyle = i9;
        this.hyphenationFrequency = i10;
        this.leftIndents = iArr;
        this.rightIndents = iArr2;
        if (i < 0 || i > i2) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int length = charSequence.length();
        if (i2 < 0 || i2 > length) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i5 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (f < 0.0f) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }
}
