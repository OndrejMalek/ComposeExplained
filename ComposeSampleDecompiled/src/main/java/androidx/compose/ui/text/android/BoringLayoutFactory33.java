package androidx.compose.ui.text.android;

import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class BoringLayoutFactory33 {
    public static final BoringLayout create(CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, float f, float f2, BoringLayout.Metrics metrics, boolean z, boolean z2, TextUtils.TruncateAt truncateAt, int i2) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(textPaint, "paint");
        ResultKt.checkNotNullParameter(alignment, "alignment");
        ResultKt.checkNotNullParameter(metrics, "metrics");
        return BoringLayoutFactory33$$ExternalSyntheticApiModelOutline0.m(charSequence, textPaint, i, alignment, f, f2, metrics, z, truncateAt, i2, z2);
    }

    public static final BoringLayout.Metrics isBoring(CharSequence charSequence, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic) {
        BoringLayout.Metrics isBoring;
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(textPaint, "paint");
        ResultKt.checkNotNullParameter(textDirectionHeuristic, "textDir");
        isBoring = BoringLayout.isBoring(charSequence, textPaint, textDirectionHeuristic, true, null);
        return isBoring;
    }
}
