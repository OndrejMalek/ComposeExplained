package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import kotlin.ResultKt;
import kotlin.ULong;

/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider$OnRequeryFactory implements ViewModelProvider$Factory {
    public final Application application;
    public final Bundle defaultArgs;
    public final ViewModelProvider$AndroidViewModelFactory factory;
    public final LifecycleRegistry lifecycle;
    public final SavedStateRegistry savedStateRegistry;

    public SavedStateViewModelFactory(Application application, ComponentActivity componentActivity, Bundle bundle) {
        ViewModelProvider$AndroidViewModelFactory viewModelProvider$AndroidViewModelFactory;
        ResultKt.checkNotNullParameter(componentActivity, "owner");
        this.savedStateRegistry = componentActivity.savedStateRegistryController.savedStateRegistry;
        this.lifecycle = componentActivity.lifecycleRegistry;
        this.defaultArgs = bundle;
        this.application = application;
        if (application != null) {
            if (ViewModelProvider$AndroidViewModelFactory.sInstance == null) {
                ViewModelProvider$AndroidViewModelFactory.sInstance = new ViewModelProvider$AndroidViewModelFactory(application);
            }
            viewModelProvider$AndroidViewModelFactory = ViewModelProvider$AndroidViewModelFactory.sInstance;
            ResultKt.checkNotNull(viewModelProvider$AndroidViewModelFactory);
        } else {
            viewModelProvider$AndroidViewModelFactory = new ViewModelProvider$AndroidViewModelFactory(null);
        }
        this.factory = viewModelProvider$AndroidViewModelFactory;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v5, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v16, types: [java.lang.Object, androidx.lifecycle.ViewModelProvider$NewInstanceFactory] */
    public final ViewModel create(String str) {
        Constructor findMatchingConstructor;
        ViewModel newInstance;
        Object obj;
        Application application;
        LifecycleRegistry lifecycleRegistry = this.lifecycle;
        if (lifecycleRegistry != null) {
            boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(SavedStateHandlesVM.class);
            if (isAssignableFrom && this.application != null) {
                findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE);
            } else {
                findMatchingConstructor = SavedStateViewModelFactoryKt.findMatchingConstructor(SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE);
            }
            if (findMatchingConstructor == null) {
                if (this.application != null) {
                    return this.factory.create();
                }
                if (ViewModelProvider$NewInstanceFactory.sInstance == null) {
                    ViewModelProvider$NewInstanceFactory.sInstance = new Object();
                }
                ViewModelProvider$NewInstanceFactory viewModelProvider$NewInstanceFactory = ViewModelProvider$NewInstanceFactory.sInstance;
                ResultKt.checkNotNull(viewModelProvider$NewInstanceFactory);
                return viewModelProvider$NewInstanceFactory.create();
            }
            SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
            ResultKt.checkNotNull(savedStateRegistry);
            Bundle bundle = this.defaultArgs;
            Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(str);
            Class[] clsArr = SavedStateHandle.ACCEPTABLE_CLASSES;
            SavedStateHandle createHandle = ULong.Companion.createHandle(consumeRestoredStateForKey, bundle);
            SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, createHandle);
            savedStateHandleController.attachToLifecycle(lifecycleRegistry, savedStateRegistry);
            Lifecycle.State state = lifecycleRegistry.state;
            if (state != Lifecycle.State.INITIALIZED && state.compareTo(Lifecycle.State.STARTED) < 0) {
                lifecycleRegistry.addObserver(new DefaultLifecycleObserverAdapter(lifecycleRegistry, savedStateRegistry));
            } else {
                savedStateRegistry.runOnNextRecreation();
            }
            if (isAssignableFrom && (application = this.application) != null) {
                newInstance = SavedStateViewModelFactoryKt.newInstance(findMatchingConstructor, application, createHandle);
            } else {
                newInstance = SavedStateViewModelFactoryKt.newInstance(findMatchingConstructor, createHandle);
            }
            synchronized (newInstance.mBagOfTags) {
                try {
                    obj = newInstance.mBagOfTags.get("androidx.lifecycle.savedstate.vm.tag");
                    if (obj == 0) {
                        newInstance.mBagOfTags.put("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (obj != 0) {
                savedStateHandleController = obj;
            }
            if (newInstance.mCleared) {
                ViewModel.closeWithRuntimeException(savedStateHandleController);
            }
            return newInstance;
        }
        throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
    }

    @Override // androidx.lifecycle.ViewModelProvider$Factory
    public final ViewModel create() {
        String canonicalName = SavedStateHandlesVM.class.getCanonicalName();
        if (canonicalName != null) {
            return create(canonicalName);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
