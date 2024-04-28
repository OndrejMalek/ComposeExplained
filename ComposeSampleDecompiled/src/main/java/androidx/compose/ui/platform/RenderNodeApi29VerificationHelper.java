package androidx.compose.ui.platform;

import android.graphics.RenderNode;
import androidx.compose.ui.graphics.RenderEffect;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class RenderNodeApi29VerificationHelper {
    public static final RenderNodeApi29VerificationHelper INSTANCE = new Object();

    public final void setRenderEffect(RenderNode renderNode, RenderEffect renderEffect) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        renderNode.setRenderEffect(null);
    }
}
