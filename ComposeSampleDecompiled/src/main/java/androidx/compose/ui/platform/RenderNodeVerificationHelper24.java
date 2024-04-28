package androidx.compose.ui.platform;

import android.view.RenderNode;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class RenderNodeVerificationHelper24 {
    public static final RenderNodeVerificationHelper24 INSTANCE = new Object();

    public final void discardDisplayList(RenderNode renderNode) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        renderNode.discardDisplayList();
    }
}
