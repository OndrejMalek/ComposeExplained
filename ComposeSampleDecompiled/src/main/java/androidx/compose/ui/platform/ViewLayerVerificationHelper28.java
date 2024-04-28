package androidx.compose.ui.platform;

import android.view.View;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ViewLayerVerificationHelper28 {
    public static final ViewLayerVerificationHelper28 INSTANCE = new Object();

    public final void setOutlineAmbientShadowColor(View view, int i) {
        ResultKt.checkNotNullParameter(view, "view");
        view.setOutlineAmbientShadowColor(i);
    }

    public final void setOutlineSpotShadowColor(View view, int i) {
        ResultKt.checkNotNullParameter(view, "view");
        view.setOutlineSpotShadowColor(i);
    }
}
