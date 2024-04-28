package androidx.compose.ui.platform;

import android.graphics.Canvas;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ViewLayerContainer extends DrawChildContainer {
    @Override // androidx.compose.ui.platform.DrawChildContainer, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
    }

    public final void dispatchGetDisplayList() {
    }
}
