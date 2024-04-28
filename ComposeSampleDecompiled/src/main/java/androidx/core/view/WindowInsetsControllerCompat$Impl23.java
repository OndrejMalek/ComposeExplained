package androidx.core.view;

import android.view.View;
import android.view.Window;

/* loaded from: classes.dex */
public abstract class WindowInsetsControllerCompat$Impl23 extends WindowInsetsControllerCompat$Impl20 {
    @Override // kotlin.ULong.Companion
    public final void setAppearanceLightStatusBars(boolean z) {
        Window window = this.mWindow;
        if (!z) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
        } else {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() | 8192);
        }
    }
}
