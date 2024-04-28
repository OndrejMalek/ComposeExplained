package androidx.lifecycle;

import androidx.lifecycle.ClassesInfoCache;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import java.util.HashMap;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DefaultLifecycleObserverAdapter implements LifecycleEventObserver {
    public final /* synthetic */ int $r8$classId = 0;
    public final Object defaultLifecycleObserver;
    public final Object lifecycleEventObserver;

    /* loaded from: classes.dex */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DefaultLifecycleObserverAdapter(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleEventObserver lifecycleEventObserver) {
        ResultKt.checkNotNullParameter(defaultLifecycleObserver, "defaultLifecycleObserver");
        this.defaultLifecycleObserver = defaultLifecycleObserver;
        this.lifecycleEventObserver = lifecycleEventObserver;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        int i = this.$r8$classId;
        Object obj = this.defaultLifecycleObserver;
        Object obj2 = this.lifecycleEventObserver;
        switch (i) {
            case 0:
                switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
                    case 1:
                        ((DefaultLifecycleObserver) obj).getClass();
                        break;
                    case 2:
                        ((DefaultLifecycleObserver) obj).getClass();
                        break;
                    case 3:
                        ((DefaultLifecycleObserver) obj).onResume(lifecycleOwner);
                        break;
                    case 4:
                        ((DefaultLifecycleObserver) obj).getClass();
                        break;
                    case 5:
                        ((DefaultLifecycleObserver) obj).getClass();
                        break;
                    case 6:
                        ((DefaultLifecycleObserver) obj).getClass();
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                }
                LifecycleEventObserver lifecycleEventObserver = (LifecycleEventObserver) obj2;
                if (lifecycleEventObserver != null) {
                    lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
                    return;
                }
                return;
            case 1:
                if (event == Lifecycle.Event.ON_START) {
                    ((Lifecycle) obj).removeObserver(this);
                    ((SavedStateRegistry) obj2).runOnNextRecreation();
                    return;
                }
                return;
            default:
                HashMap hashMap = ((ClassesInfoCache.CallbackInfo) obj2).mEventToHandlers;
                ClassesInfoCache.CallbackInfo.invokeMethodsForEvent((List) hashMap.get(event), lifecycleOwner, event, obj);
                ClassesInfoCache.CallbackInfo.invokeMethodsForEvent((List) hashMap.get(Lifecycle.Event.ON_ANY), lifecycleOwner, event, obj);
                return;
        }
    }

    public DefaultLifecycleObserverAdapter(Object obj) {
        this.defaultLifecycleObserver = obj;
        this.lifecycleEventObserver = ClassesInfoCache.sInstance.getInfo(obj.getClass());
    }

    public DefaultLifecycleObserverAdapter(LifecycleRegistry lifecycleRegistry, SavedStateRegistry savedStateRegistry) {
        this.defaultLifecycleObserver = lifecycleRegistry;
        this.lifecycleEventObserver = savedStateRegistry;
    }
}
