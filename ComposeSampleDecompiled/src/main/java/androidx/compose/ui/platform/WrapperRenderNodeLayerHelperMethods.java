package androidx.compose.ui.platform;

import android.view.ViewParent;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class WrapperRenderNodeLayerHelperMethods {
    public static final WrapperRenderNodeLayerHelperMethods INSTANCE = new Object();

    public final void onDescendantInvalidated(AndroidComposeView androidComposeView) {
        ResultKt.checkNotNullParameter(androidComposeView, "ownerView");
        ViewParent parent = androidComposeView.getParent();
        if (parent != null) {
            parent.onDescendantInvalidated(androidComposeView, androidComposeView);
        }
    }
}
