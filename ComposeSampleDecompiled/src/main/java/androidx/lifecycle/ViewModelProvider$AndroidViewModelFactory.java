package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ViewModelProvider$AndroidViewModelFactory extends ViewModelProvider$NewInstanceFactory {
    public static ViewModelProvider$AndroidViewModelFactory sInstance;
    public final Application application;

    public ViewModelProvider$AndroidViewModelFactory(Application application) {
        this.application = application;
    }

    @Override // androidx.lifecycle.ViewModelProvider$NewInstanceFactory, androidx.lifecycle.ViewModelProvider$Factory
    public final ViewModel create() {
        Application application = this.application;
        if (application != null) {
            return create(application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }

    public final ViewModel create(Application application) {
        if (AndroidViewModel.class.isAssignableFrom(SavedStateHandlesVM.class)) {
            try {
                ViewModel viewModel = (ViewModel) SavedStateHandlesVM.class.getConstructor(Application.class).newInstance(application);
                ResultKt.checkNotNullExpressionValue(viewModel, "{\n                try {\n…          }\n            }");
                return viewModel;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Cannot create an instance of " + SavedStateHandlesVM.class, e4);
            }
        }
        return super.create();
    }
}
