package androidx.compose.ui.input.pointer;

import _COROUTINE._BOUNDARY;
import android.view.MotionEvent;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class MotionEventHelper {
    public static final MotionEventHelper INSTANCE = new Object();

    /* renamed from: toRawOffset-dBAh8RU, reason: not valid java name */
    public final long m154toRawOffsetdBAh8RU(MotionEvent motionEvent, int i) {
        float rawX;
        float rawY;
        ResultKt.checkNotNullParameter(motionEvent, "motionEvent");
        rawX = motionEvent.getRawX(i);
        rawY = motionEvent.getRawY(i);
        return _BOUNDARY.Offset(rawX, rawY);
    }
}
