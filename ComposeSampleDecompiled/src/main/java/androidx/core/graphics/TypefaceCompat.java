package androidx.core.graphics;

import android.os.Build;
import androidx.compose.runtime.Stack;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public abstract class TypefaceCompat {
    public static final Stack sTypefaceCompatImpl;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            sTypefaceCompatImpl = new Stack(11);
        } else {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        }
        new LinkedHashMap(0, 0.75f, true);
    }
}
