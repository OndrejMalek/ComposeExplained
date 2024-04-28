package androidx.compose.ui.platform;

import android.view.MotionEvent;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class MotionEventVerifierApi29 {
    public static final MotionEventVerifierApi29 INSTANCE = new Object();

    public final boolean isValidMotionEvent(MotionEvent motionEvent, int i) {
        float rawX;
        float rawY;
        ResultKt.checkNotNullParameter(motionEvent, "event");
        rawX = motionEvent.getRawX(i);
        if (!Float.isInfinite(rawX) && !Float.isNaN(rawX)) {
            rawY = motionEvent.getRawY(i);
            if (!Float.isInfinite(rawY) && !Float.isNaN(rawY)) {
                return true;
            }
        }
        return false;
    }
}
