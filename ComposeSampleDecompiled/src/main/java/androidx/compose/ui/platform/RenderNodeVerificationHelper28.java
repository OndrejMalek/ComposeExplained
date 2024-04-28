package androidx.compose.ui.platform;

import android.view.RenderNode;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class RenderNodeVerificationHelper28 {
    public static final RenderNodeVerificationHelper28 INSTANCE = new Object();

    public final int getAmbientShadowColor(RenderNode renderNode) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        return renderNode.getAmbientShadowColor();
    }

    public final int getSpotShadowColor(RenderNode renderNode) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        return renderNode.getSpotShadowColor();
    }

    public final void setAmbientShadowColor(RenderNode renderNode, int i) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        renderNode.setAmbientShadowColor(i);
    }

    public final void setSpotShadowColor(RenderNode renderNode, int i) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        renderNode.setSpotShadowColor(i);
    }
}
