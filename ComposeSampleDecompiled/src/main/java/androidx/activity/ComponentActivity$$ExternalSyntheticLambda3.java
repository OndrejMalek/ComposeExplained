package androidx.activity;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda3 implements LifecycleEventObserver {
    public final /* synthetic */ OnBackPressedDispatcher f$0;
    public final /* synthetic */ ComponentActivity f$1;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda3(ComponentActivity componentActivity, OnBackPressedDispatcher onBackPressedDispatcher) {
        this.f$0 = onBackPressedDispatcher;
        this.f$1 = componentActivity;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        OnBackPressedDispatcher onBackPressedDispatcher = this.f$0;
        ResultKt.checkNotNullParameter(onBackPressedDispatcher, "$dispatcher");
        ComponentActivity componentActivity = this.f$1;
        ResultKt.checkNotNullParameter(componentActivity, "this$0");
        if (event == Lifecycle.Event.ON_CREATE) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = ComponentActivity.Api33Impl.INSTANCE.getOnBackInvokedDispatcher(componentActivity);
            ResultKt.checkNotNullParameter(onBackInvokedDispatcher, "invoker");
            OnBackInvokedCallback onBackInvokedCallback = onBackPressedDispatcher.onBackInvokedCallback;
            if (onBackInvokedCallback != null) {
                OnBackPressedDispatcher.Api33Impl api33Impl = OnBackPressedDispatcher.Api33Impl.INSTANCE;
                if (onBackPressedDispatcher.backInvokedCallbackRegistered) {
                    api33Impl.unregisterOnBackInvokedCallback(onBackInvokedDispatcher, onBackInvokedCallback);
                    onBackPressedDispatcher.backInvokedCallbackRegistered = false;
                }
            }
        }
    }
}
