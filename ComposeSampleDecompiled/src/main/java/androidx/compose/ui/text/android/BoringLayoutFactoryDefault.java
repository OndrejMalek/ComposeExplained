package androidx.compose.ui.text.android;

import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class BoringLayoutFactoryDefault {
    public static final BoringLayout create(CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, float f, float f2, BoringLayout.Metrics metrics, boolean z, TextUtils.TruncateAt truncateAt, int i2) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(textPaint, "paint");
        ResultKt.checkNotNullParameter(alignment, "alignment");
        ResultKt.checkNotNullParameter(metrics, "metrics");
        return new BoringLayout(charSequence, textPaint, i, alignment, f, f2, metrics, z, truncateAt, i2);
    }

    public static final BoringLayout.Metrics isBoring(CharSequence charSequence, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(textPaint, "paint");
        ResultKt.checkNotNullParameter(textDirectionHeuristic, "textDir");
        if (textDirectionHeuristic.isRtl(charSequence, 0, charSequence.length())) {
            return null;
        }
        return BoringLayout.isBoring(charSequence, textPaint, null);
    }
}
