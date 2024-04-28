package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SavedStateHandleController implements LifecycleEventObserver {
    public final SavedStateHandle handle;
    public boolean isAttached;
    public final String key;

    public SavedStateHandleController(String str, SavedStateHandle savedStateHandle) {
        this.key = str;
        this.handle = savedStateHandle;
    }

    public final void attachToLifecycle(LifecycleRegistry lifecycleRegistry, SavedStateRegistry savedStateRegistry) {
        ResultKt.checkNotNullParameter(savedStateRegistry, "registry");
        ResultKt.checkNotNullParameter(lifecycleRegistry, "lifecycle");
        if (!(!this.isAttached)) {
            throw new IllegalStateException("Already attached to lifecycleOwner".toString());
        }
        this.isAttached = true;
        lifecycleRegistry.addObserver(this);
        savedStateRegistry.registerSavedStateProvider(this.key, this.handle.savedStateProvider);
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.isAttached = false;
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
