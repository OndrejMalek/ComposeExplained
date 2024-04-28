package androidx.activity;

import android.window.BackEvent;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Api34Impl {
    public static final Api34Impl INSTANCE = new Object();

    public final BackEvent createOnBackEvent(float f, float f2, float f3, int i) {
        return new BackEvent(f, f2, f3, i);
    }

    public final float progress(BackEvent backEvent) {
        ResultKt.checkNotNullParameter(backEvent, "backEvent");
        return backEvent.getProgress();
    }

    public final int swipeEdge(BackEvent backEvent) {
        ResultKt.checkNotNullParameter(backEvent, "backEvent");
        return backEvent.getSwipeEdge();
    }

    public final float touchX(BackEvent backEvent) {
        ResultKt.checkNotNullParameter(backEvent, "backEvent");
        return backEvent.getTouchX();
    }

    public final float touchY(BackEvent backEvent) {
        ResultKt.checkNotNullParameter(backEvent, "backEvent");
        return backEvent.getTouchY();
    }
}
