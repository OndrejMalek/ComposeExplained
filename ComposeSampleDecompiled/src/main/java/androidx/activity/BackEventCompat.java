package androidx.activity;

import android.window.BackEvent;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class BackEventCompat {
    public final float progress;
    public final int swipeEdge;
    public final float touchX;
    public final float touchY;

    public BackEventCompat(BackEvent backEvent) {
        ResultKt.checkNotNullParameter(backEvent, "backEvent");
        Api34Impl api34Impl = Api34Impl.INSTANCE;
        float f = api34Impl.touchX(backEvent);
        float f2 = api34Impl.touchY(backEvent);
        float progress = api34Impl.progress(backEvent);
        int swipeEdge = api34Impl.swipeEdge(backEvent);
        this.touchX = f;
        this.touchY = f2;
        this.progress = progress;
        this.swipeEdge = swipeEdge;
    }

    public final String toString() {
        return "BackEventCompat{touchX=" + this.touchX + ", touchY=" + this.touchY + ", progress=" + this.progress + ", swipeEdge=" + this.swipeEdge + '}';
    }
}
