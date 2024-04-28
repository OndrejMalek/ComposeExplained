package androidx.compose.ui.graphics;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CanvasZHelper {
    public static final CanvasZHelper INSTANCE = new Object();

    public final void enableZ(android.graphics.Canvas canvas, boolean z) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        if (z) {
            canvas.enableZ();
        } else {
            canvas.disableZ();
        }
    }
}
