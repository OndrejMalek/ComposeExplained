package androidx.compose.ui.platform;

import android.view.View;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidComposeViewVerificationHelperMethodsO {
    public static final AndroidComposeViewVerificationHelperMethodsO INSTANCE = new Object();

    public final void focusable(View view, int i, boolean z) {
        ResultKt.checkNotNullParameter(view, "view");
        view.setFocusable(i);
        view.setDefaultFocusHighlightEnabled(z);
    }
}
