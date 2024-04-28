package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import kotlin.ResultKt;
import kotlin.ULong;

/* loaded from: classes.dex */
public abstract class ComponentActivity extends Activity implements LifecycleOwner {
    public final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        ResultKt.checkNotNullExpressionValue(getWindow().getDecorView(), "window.decorView");
        int i = ViewCompat.$r8$clinit;
        return superDispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        ResultKt.checkNotNullExpressionValue(getWindow().getDecorView(), "window.decorView");
        int i = ViewCompat.$r8$clinit;
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = ReportFragment.$r8$clinit;
        ULong.Companion.injectIfNeededIn(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        ResultKt.checkNotNullParameter(bundle, "outState");
        Lifecycle.State state = Lifecycle.State.CREATED;
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
        lifecycleRegistry.moveToState(state);
        super.onSaveInstanceState(bundle);
    }

    public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }
}
