package androidx.lifecycle;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class Lifecycle {
    public static final ViewModelProvider$NewInstanceFactory SAVED_STATE_REGISTRY_OWNER_KEY = new Object();
    public static final ViewModelProvider$NewInstanceFactory VIEW_MODEL_STORE_OWNER_KEY = new Object();
    public static final ViewModelProvider$NewInstanceFactory DEFAULT_ARGS_KEY = new Object();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public final class Event {
        private static final /* synthetic */ Event[] $VALUES;
        public static final Companion Companion;
        public static final Event ON_ANY;
        public static final Event ON_CREATE;
        public static final Event ON_DESTROY;
        public static final Event ON_PAUSE;
        public static final Event ON_RESUME;
        public static final Event ON_START;
        public static final Event ON_STOP;

        /* loaded from: classes.dex */
        public final class Companion {
            public static Event upFrom(State state) {
                ResultKt.checkNotNullParameter(state, "state");
                int ordinal = state.ordinal();
                if (ordinal == 1) {
                    return Event.ON_CREATE;
                }
                if (ordinal == 2) {
                    return Event.ON_START;
                }
                if (ordinal != 3) {
                    return null;
                }
                return Event.ON_RESUME;
            }
        }

        /* loaded from: classes.dex */
        public abstract /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Event.values().length];
                try {
                    iArr[Event.ON_CREATE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Event.ON_STOP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Event.ON_START.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Event.ON_PAUSE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[Event.ON_RESUME.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[Event.ON_DESTROY.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[Event.ON_ANY.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, androidx.lifecycle.Lifecycle$Event$Companion] */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Enum, androidx.lifecycle.Lifecycle$Event] */
        static {
            ?? r0 = new Enum("ON_CREATE", 0);
            ON_CREATE = r0;
            ?? r1 = new Enum("ON_START", 1);
            ON_START = r1;
            ?? r2 = new Enum("ON_RESUME", 2);
            ON_RESUME = r2;
            ?? r3 = new Enum("ON_PAUSE", 3);
            ON_PAUSE = r3;
            ?? r4 = new Enum("ON_STOP", 4);
            ON_STOP = r4;
            ?? r5 = new Enum("ON_DESTROY", 5);
            ON_DESTROY = r5;
            ?? r6 = new Enum("ON_ANY", 6);
            ON_ANY = r6;
            $VALUES = new Event[]{r0, r1, r2, r3, r4, r5, r6};
            Companion = new Object();
        }

        public static Event valueOf(String str) {
            return (Event) Enum.valueOf(Event.class, str);
        }

        public static Event[] values() {
            return (Event[]) $VALUES.clone();
        }

        public final State getTargetState() {
            switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
                case 1:
                case 2:
                    return State.CREATED;
                case 3:
                case 4:
                    return State.STARTED;
                case 5:
                    return State.RESUMED;
                case 6:
                    return State.DESTROYED;
                default:
                    throw new IllegalArgumentException(this + " has no target state");
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public final class State {
        public static final /* synthetic */ State[] $VALUES;
        public static final State CREATED;
        public static final State DESTROYED;
        public static final State INITIALIZED;
        public static final State RESUMED;
        public static final State STARTED;

        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.Lifecycle$State, java.lang.Enum] */
        /* JADX WARN: Type inference failed for: r1v1, types: [androidx.lifecycle.Lifecycle$State, java.lang.Enum] */
        /* JADX WARN: Type inference failed for: r2v2, types: [androidx.lifecycle.Lifecycle$State, java.lang.Enum] */
        /* JADX WARN: Type inference failed for: r3v2, types: [androidx.lifecycle.Lifecycle$State, java.lang.Enum] */
        /* JADX WARN: Type inference failed for: r4v2, types: [androidx.lifecycle.Lifecycle$State, java.lang.Enum] */
        static {
            ?? r0 = new Enum("DESTROYED", 0);
            DESTROYED = r0;
            ?? r1 = new Enum("INITIALIZED", 1);
            INITIALIZED = r1;
            ?? r2 = new Enum("CREATED", 2);
            CREATED = r2;
            ?? r3 = new Enum("STARTED", 3);
            STARTED = r3;
            ?? r4 = new Enum("RESUMED", 4);
            RESUMED = r4;
            $VALUES = new State[]{r0, r1, r2, r3, r4};
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public static final void attachHandleIfNeeded(ViewModel viewModel, SavedStateRegistry savedStateRegistry, LifecycleRegistry lifecycleRegistry) {
        Object obj;
        ResultKt.checkNotNullParameter(savedStateRegistry, "registry");
        ResultKt.checkNotNullParameter(lifecycleRegistry, "lifecycle");
        HashMap hashMap = viewModel.mBagOfTags;
        if (hashMap == null) {
            obj = null;
        } else {
            synchronized (hashMap) {
                obj = viewModel.mBagOfTags.get("androidx.lifecycle.savedstate.vm.tag");
            }
        }
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
        if (savedStateHandleController == null || savedStateHandleController.isAttached) {
            return;
        }
        savedStateHandleController.attachToLifecycle(lifecycleRegistry, savedStateRegistry);
        State state = lifecycleRegistry.state;
        if (state == State.INITIALIZED || state.compareTo(State.STARTED) >= 0) {
            savedStateRegistry.runOnNextRecreation();
        } else {
            lifecycleRegistry.addObserver(new DefaultLifecycleObserverAdapter(lifecycleRegistry, savedStateRegistry));
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModelProvider$Factory, java.lang.Object] */
    public static final SavedStateHandlesVM getSavedStateHandlesVM(ViewModelStoreOwner viewModelStoreOwner) {
        CreationExtras creationExtras;
        ResultKt.checkNotNullParameter(viewModelStoreOwner, "<this>");
        ?? obj = new Object();
        ViewModelStore viewModelStore = ((ComponentActivity) viewModelStoreOwner).getViewModelStore();
        if (viewModelStoreOwner instanceof HasDefaultViewModelProviderFactory) {
            ComponentActivity componentActivity = (ComponentActivity) ((HasDefaultViewModelProviderFactory) viewModelStoreOwner);
            creationExtras = new MutableCreationExtras(CreationExtras.Empty.INSTANCE);
            Application application = componentActivity.getApplication();
            LinkedHashMap linkedHashMap = creationExtras.map;
            if (application != null) {
                ViewModelProvider$NewInstanceFactory viewModelProvider$NewInstanceFactory = ViewModelProvider$NewInstanceFactory.INSTANCE;
                Application application2 = componentActivity.getApplication();
                ResultKt.checkNotNullExpressionValue(application2, "application");
                linkedHashMap.put(viewModelProvider$NewInstanceFactory, application2);
            }
            linkedHashMap.put(SAVED_STATE_REGISTRY_OWNER_KEY, componentActivity);
            linkedHashMap.put(VIEW_MODEL_STORE_OWNER_KEY, componentActivity);
            Intent intent = componentActivity.getIntent();
            Bundle extras = intent != null ? intent.getExtras() : null;
            if (extras != null) {
                linkedHashMap.put(DEFAULT_ARGS_KEY, extras);
            }
        } else {
            creationExtras = CreationExtras.Empty.INSTANCE;
        }
        ResultKt.checkNotNullParameter(creationExtras, "defaultCreationExtras");
        HashMap hashMap = viewModelStore.map;
        ViewModel viewModel = (ViewModel) hashMap.get("androidx.lifecycle.internal.SavedStateHandlesVM");
        if (SavedStateHandlesVM.class.isInstance(viewModel)) {
            ViewModelProvider$OnRequeryFactory viewModelProvider$OnRequeryFactory = obj instanceof ViewModelProvider$OnRequeryFactory ? (ViewModelProvider$OnRequeryFactory) obj : null;
            if (viewModelProvider$OnRequeryFactory != null) {
                ResultKt.checkNotNull(viewModel);
                SavedStateViewModelFactory savedStateViewModelFactory = (SavedStateViewModelFactory) viewModelProvider$OnRequeryFactory;
                LifecycleRegistry lifecycleRegistry = savedStateViewModelFactory.lifecycle;
                if (lifecycleRegistry != null) {
                    SavedStateRegistry savedStateRegistry = savedStateViewModelFactory.savedStateRegistry;
                    ResultKt.checkNotNull(savedStateRegistry);
                    attachHandleIfNeeded(viewModel, savedStateRegistry, lifecycleRegistry);
                }
            }
            ResultKt.checkNotNull(viewModel, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
        } else {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.putAll(creationExtras.map);
            linkedHashMap2.put(ViewModelProvider$NewInstanceFactory.INSTANCE$1, "androidx.lifecycle.internal.SavedStateHandlesVM");
            try {
                viewModel = new SavedStateHandlesVM();
            } catch (AbstractMethodError unused) {
                obj.create();
                throw null;
            }
        }
        return (SavedStateHandlesVM) viewModel;
    }

    public abstract void removeObserver(LifecycleObserver lifecycleObserver);
}
