package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.graphics.RenderEffect;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ViewLayerVerificationHelper31 {
    public static final ViewLayerVerificationHelper31 INSTANCE = new Object();

    public final void setRenderEffect(View view, RenderEffect renderEffect) {
        ResultKt.checkNotNullParameter(view, "view");
        view.setRenderEffect(null);
    }
}
