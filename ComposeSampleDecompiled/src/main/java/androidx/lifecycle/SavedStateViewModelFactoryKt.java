package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.ArrayAsCollection;
import kotlin.collections.EmptyList;

/* loaded from: classes.dex */
public abstract class SavedStateViewModelFactoryKt {
    public static final List ANDROID_VIEWMODEL_SIGNATURE = ResultKt.listOf((Object[]) new Class[]{Application.class, SavedStateHandle.class});
    public static final List VIEWMODEL_SIGNATURE = ResultKt.listOf(SavedStateHandle.class);

    public static final Constructor findMatchingConstructor(List list) {
        ResultKt.checkNotNullParameter(list, "signature");
        Constructor<?>[] constructors = SavedStateHandlesVM.class.getConstructors();
        ResultKt.checkNotNullExpressionValue(constructors, "modelClass.constructors");
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            ResultKt.checkNotNullExpressionValue(parameterTypes, "constructor.parameterTypes");
            int length = parameterTypes.length;
            List arrayList = length != 0 ? length != 1 ? new ArrayList(new ArrayAsCollection(parameterTypes, false)) : ResultKt.listOf(parameterTypes[0]) : EmptyList.INSTANCE;
            if (ResultKt.areEqual(list, arrayList)) {
                return constructor;
            }
            if (list.size() == arrayList.size() && arrayList.containsAll(list)) {
                throw new UnsupportedOperationException("Class SavedStateHandlesVM must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final ViewModel newInstance(Constructor constructor, Object... objArr) {
        try {
            return (ViewModel) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access " + SavedStateHandlesVM.class, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + SavedStateHandlesVM.class + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("An exception happened in constructor of " + SavedStateHandlesVM.class, e3.getCause());
        }
    }
}
