package androidx.lifecycle;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public class ViewModelProvider$NewInstanceFactory implements ViewModelProvider$Factory {
    public static final ViewModelProvider$NewInstanceFactory INSTANCE = new Object();
    public static final ViewModelProvider$NewInstanceFactory INSTANCE$1 = new Object();
    public static ViewModelProvider$NewInstanceFactory sInstance;

    @Override // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create() {
        try {
            Object newInstance = SavedStateHandlesVM.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            ResultKt.checkNotNullExpressionValue(newInstance, "{\n                modelC…wInstance()\n            }");
            return (ViewModel) newInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e3);
        }
    }
}
