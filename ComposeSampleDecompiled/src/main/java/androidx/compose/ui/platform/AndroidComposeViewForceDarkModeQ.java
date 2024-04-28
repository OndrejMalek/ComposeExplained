package androidx.compose.ui.platform;

import android.view.View;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidComposeViewForceDarkModeQ {
    public static final AndroidComposeViewForceDarkModeQ INSTANCE = new Object();

    public final void disallowForceDark(View view) {
        ResultKt.checkNotNullParameter(view, "view");
        view.setForceDarkAllowed(false);
    }
}
