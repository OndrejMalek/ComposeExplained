package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class Paint29 {
    public static final void getTextBounds(Paint paint, CharSequence charSequence, int i, int i2, Rect rect) {
        ResultKt.checkNotNullParameter(paint, "paint");
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(rect, "rect");
        paint.getTextBounds(charSequence, i, i2, rect);
    }
}
