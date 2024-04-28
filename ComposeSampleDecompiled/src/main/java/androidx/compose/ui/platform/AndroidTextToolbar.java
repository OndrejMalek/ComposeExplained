package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.text.font.Font$ResourceLoader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidTextToolbar implements Font$ResourceLoader, TextToolbar {
    public static final /* synthetic */ AndroidTextToolbar $$INSTANCE = new Object();

    public static final boolean access$getIsShowingLayoutBounds() {
        Class cls = AndroidComposeView.systemPropertiesClass;
        try {
            if (AndroidComposeView.systemPropertiesClass == null) {
                Class<?> cls2 = Class.forName("android.os.SystemProperties");
                AndroidComposeView.systemPropertiesClass = cls2;
                AndroidComposeView.getBooleanMethod = cls2.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            }
            Method method = AndroidComposeView.getBooleanMethod;
            Object invoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
            Boolean bool = invoke instanceof Boolean ? (Boolean) invoke : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void updateDisplayList(View view) {
        ResultKt.checkNotNullParameter(view, "view");
        try {
            if (!ViewLayer.hasRetrievedMethod) {
                ViewLayer.hasRetrievedMethod = true;
                ViewLayer.updateDisplayListIfDirtyMethod = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                ViewLayer.recreateDisplayList = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                Method method = ViewLayer.updateDisplayListIfDirtyMethod;
                if (method != null) {
                    method.setAccessible(true);
                }
                Field field = ViewLayer.recreateDisplayList;
                if (field != null) {
                    field.setAccessible(true);
                }
            }
            Field field2 = ViewLayer.recreateDisplayList;
            if (field2 != null) {
                field2.setBoolean(view, true);
            }
            Method method2 = ViewLayer.updateDisplayListIfDirtyMethod;
            if (method2 != null) {
                method2.invoke(view, new Object[0]);
            }
        } catch (Throwable unused) {
            ViewLayer.shouldUseDispatchDraw = true;
        }
    }
}
