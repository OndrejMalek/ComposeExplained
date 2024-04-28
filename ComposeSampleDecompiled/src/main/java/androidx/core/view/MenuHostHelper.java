package androidx.core.view;

import androidx.activity.ComponentActivity$$ExternalSyntheticLambda0;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.font.TypefaceResult;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class MenuHostHelper {
    public final Object mMenuProviders;
    public final Object mOnInvalidateMenuCallback;
    public final Object mProviderToLifecycleContainers;

    public MenuHostHelper(TypefaceResult typefaceResult, MenuHostHelper menuHostHelper) {
        ResultKt.checkNotNullParameter(typefaceResult, "resolveResult");
        this.mOnInvalidateMenuCallback = typefaceResult;
        this.mMenuProviders = menuHostHelper;
        this.mProviderToLifecycleContainers = typefaceResult.getValue();
    }

    public final boolean isStaleResolvedFont() {
        if (((State) this.mOnInvalidateMenuCallback).getValue() == this.mProviderToLifecycleContainers) {
            Object obj = this.mMenuProviders;
            if (((MenuHostHelper) obj) == null || !((MenuHostHelper) obj).isStaleResolvedFont()) {
                return false;
            }
        }
        return true;
    }

    public MenuHostHelper(Method method, Method method2, Method method3) {
        this.mOnInvalidateMenuCallback = method;
        this.mMenuProviders = method2;
        this.mProviderToLifecycleContainers = method3;
    }

    public MenuHostHelper(ComponentActivity$$ExternalSyntheticLambda0 componentActivity$$ExternalSyntheticLambda0) {
        this.mMenuProviders = new CopyOnWriteArrayList();
        this.mProviderToLifecycleContainers = new HashMap();
        this.mOnInvalidateMenuCallback = componentActivity$$ExternalSyntheticLambda0;
    }
}
