package androidx.savedstate;

import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda1;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    public boolean attached;
    public final SavedStateRegistryOwner owner;
    public final SavedStateRegistry savedStateRegistry = new SavedStateRegistry();

    public SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.owner = savedStateRegistryOwner;
    }

    public final void performAttach() {
        SavedStateRegistryOwner savedStateRegistryOwner = this.owner;
        LifecycleRegistry lifecycleRegistry = ((ComponentActivity) savedStateRegistryOwner).lifecycleRegistry;
        if (lifecycleRegistry.state != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
        }
        lifecycleRegistry.addObserver(new Recreator(savedStateRegistryOwner));
        SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
        savedStateRegistry.getClass();
        if (!(!savedStateRegistry.attached)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        lifecycleRegistry.addObserver(new ComponentActivity$$ExternalSyntheticLambda1(2, savedStateRegistry));
        savedStateRegistry.attached = true;
        this.attached = true;
    }
}
