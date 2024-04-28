package androidx.savedstate;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryImpl;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Recreator implements LifecycleEventObserver {
    public final SavedStateRegistryOwner owner;

    public Recreator(SavedStateRegistryOwner savedStateRegistryOwner) {
        ResultKt.checkNotNullParameter(savedStateRegistryOwner, "owner");
        this.owner = savedStateRegistryOwner;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        lifecycleOwner.getLifecycle().removeObserver(this);
        SavedStateRegistryOwner savedStateRegistryOwner = this.owner;
        Bundle consumeRestoredStateForKey = ((ComponentActivity) savedStateRegistryOwner).savedStateRegistryController.savedStateRegistry.consumeRestoredStateForKey("androidx.savedstate.Restarter");
        if (consumeRestoredStateForKey == null) {
            return;
        }
        ArrayList<String> stringArrayList = consumeRestoredStateForKey.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> asSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.AutoRecreated.class);
                ResultKt.checkNotNullExpressionValue(asSubclass, "{\n                Class.…class.java)\n            }");
                try {
                    Constructor declaredConstructor = asSubclass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    try {
                        Object newInstance = declaredConstructor.newInstance(new Object[0]);
                        ResultKt.checkNotNullExpressionValue(newInstance, "{\n                constr…wInstance()\n            }");
                        ResultKt.checkNotNullParameter(savedStateRegistryOwner, "owner");
                        if (!(savedStateRegistryOwner instanceof ViewModelStoreOwner)) {
                            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
                        }
                        ViewModelStore viewModelStore = ((ComponentActivity) ((ViewModelStoreOwner) savedStateRegistryOwner)).getViewModelStore();
                        ComponentActivity componentActivity = (ComponentActivity) savedStateRegistryOwner;
                        SavedStateRegistry savedStateRegistry = componentActivity.savedStateRegistryController.savedStateRegistry;
                        HashMap hashMap = viewModelStore.map;
                        Iterator it = new HashSet(hashMap.keySet()).iterator();
                        while (it.hasNext()) {
                            String str2 = (String) it.next();
                            ResultKt.checkNotNullParameter(str2, "key");
                            ViewModel viewModel = (ViewModel) hashMap.get(str2);
                            ResultKt.checkNotNull(viewModel);
                            Lifecycle.attachHandleIfNeeded(viewModel, savedStateRegistry, componentActivity.lifecycleRegistry);
                        }
                        if (!new HashSet(hashMap.keySet()).isEmpty()) {
                            savedStateRegistry.runOnNextRecreation();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to instantiate " + str, e);
                    }
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException("Class " + asSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                }
            } catch (ClassNotFoundException e3) {
                throw new RuntimeException("Class " + str + " wasn't found", e3);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class SavedStateProvider implements SavedStateRegistry.SavedStateProvider {
        public final /* synthetic */ int $r8$classId = 1;
        public final Object classes;

        public SavedStateProvider(SaveableStateRegistryImpl saveableStateRegistryImpl) {
            this.classes = saveableStateRegistryImpl;
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        public final Bundle saveState() {
            int i = this.$r8$classId;
            Object obj = this.classes;
            switch (i) {
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("classes_to_restore", new ArrayList<>((Set) obj));
                    return bundle;
                default:
                    Map performSave = ((SaveableStateRegistry) obj).performSave();
                    Bundle bundle2 = new Bundle();
                    for (Map.Entry entry : performSave.entrySet()) {
                        String str = (String) entry.getKey();
                        List list = (List) entry.getValue();
                        bundle2.putParcelableArrayList(str, list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
                    }
                    return bundle2;
            }
        }

        public SavedStateProvider(SavedStateRegistry savedStateRegistry) {
            ResultKt.checkNotNullParameter(savedStateRegistry, "registry");
            this.classes = new LinkedHashSet();
            savedStateRegistry.registerSavedStateProvider("androidx.savedstate.Restarter", this);
        }
    }
}
