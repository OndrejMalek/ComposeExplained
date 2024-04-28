package androidx.compose.ui.text.android;

import android.text.Layout;
import android.text.TextDirectionHeuristics;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class LayoutCompat {
    public static final Layout.Alignment DEFAULT_LAYOUT_ALIGNMENT = Layout.Alignment.ALIGN_NORMAL;

    static {
        ResultKt.checkNotNullExpressionValue(TextDirectionHeuristics.FIRSTSTRONG_LTR, "FIRSTSTRONG_LTR");
    }
}
