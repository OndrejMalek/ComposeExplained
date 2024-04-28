package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class TextLayoutKt {
    public static final TextAndroidCanvas SharedTextAndroidCanvas = new Canvas();
    public static final long ZeroVerticalPadding;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.text.android.TextAndroidCanvas, android.graphics.Canvas] */
    static {
        long j = 0;
        ZeroVerticalPadding = (j & 4294967295L) | (j << 32);
    }

    public static final TextDirectionHeuristic getTextDirectionHeuristic(int i) {
        if (i == 0) {
            TextDirectionHeuristic textDirectionHeuristic = TextDirectionHeuristics.LTR;
            ResultKt.checkNotNullExpressionValue(textDirectionHeuristic, "LTR");
            return textDirectionHeuristic;
        }
        if (i == 1) {
            TextDirectionHeuristic textDirectionHeuristic2 = TextDirectionHeuristics.RTL;
            ResultKt.checkNotNullExpressionValue(textDirectionHeuristic2, "RTL");
            return textDirectionHeuristic2;
        }
        if (i == 2) {
            TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            ResultKt.checkNotNullExpressionValue(textDirectionHeuristic3, "FIRSTSTRONG_LTR");
            return textDirectionHeuristic3;
        }
        if (i == 3) {
            TextDirectionHeuristic textDirectionHeuristic4 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
            ResultKt.checkNotNullExpressionValue(textDirectionHeuristic4, "FIRSTSTRONG_RTL");
            return textDirectionHeuristic4;
        }
        if (i == 4) {
            TextDirectionHeuristic textDirectionHeuristic5 = TextDirectionHeuristics.ANYRTL_LTR;
            ResultKt.checkNotNullExpressionValue(textDirectionHeuristic5, "ANYRTL_LTR");
            return textDirectionHeuristic5;
        }
        if (i != 5) {
            TextDirectionHeuristic textDirectionHeuristic6 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            ResultKt.checkNotNullExpressionValue(textDirectionHeuristic6, "FIRSTSTRONG_LTR");
            return textDirectionHeuristic6;
        }
        TextDirectionHeuristic textDirectionHeuristic7 = TextDirectionHeuristics.LOCALE;
        ResultKt.checkNotNullExpressionValue(textDirectionHeuristic7, "LOCALE");
        return textDirectionHeuristic7;
    }
}
