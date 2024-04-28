package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.input.pointer.AndroidPointerIconType;
import androidx.compose.ui.input.pointer.PointerIcon;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidComposeViewVerificationHelperMethodsN {
    public static final AndroidComposeViewVerificationHelperMethodsN INSTANCE = new Object();

    public final void setPointerIcon(View view, PointerIcon pointerIcon) {
        android.view.PointerIcon systemIcon;
        ResultKt.checkNotNullParameter(view, "view");
        if (pointerIcon instanceof AndroidPointerIconType) {
            systemIcon = android.view.PointerIcon.getSystemIcon(view.getContext(), ((AndroidPointerIconType) pointerIcon).type);
            ResultKt.checkNotNullExpressionValue(systemIcon, "getSystemIcon(view.context, icon.type)");
        } else {
            systemIcon = android.view.PointerIcon.getSystemIcon(view.getContext(), 1000);
            ResultKt.checkNotNullExpressionValue(systemIcon, "getSystemIcon(\n         …DEFAULT\n                )");
        }
        if (ResultKt.areEqual(view.getPointerIcon(), systemIcon)) {
            return;
        }
        view.setPointerIcon(systemIcon);
    }
}
