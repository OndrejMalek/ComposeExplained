package androidx.compose.ui.text.android.style;

import _COROUTINE._BOUNDARY;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;
import androidx.compose.ui.text.android.TextAndroidCanvas;
import androidx.compose.ui.text.android.TextLayoutKt;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class IndentationFixSpan implements LeadingMarginSpan {
    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        int lineForOffset;
        if (layout == null || paint == null || (lineForOffset = layout.getLineForOffset(i6)) != layout.getLineCount() - 1) {
            return;
        }
        TextAndroidCanvas textAndroidCanvas = TextLayoutKt.SharedTextAndroidCanvas;
        if (layout.getEllipsisCount(lineForOffset) > 0) {
            float ellipsizedRightPadding = _BOUNDARY.getEllipsizedRightPadding(layout, lineForOffset, paint) + _BOUNDARY.getEllipsizedLeftPadding(layout, lineForOffset, paint);
            if (ellipsizedRightPadding == 0.0f) {
                return;
            }
            ResultKt.checkNotNull(canvas);
            canvas.translate(ellipsizedRightPadding, 0.0f);
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z) {
        return 0;
    }
}
