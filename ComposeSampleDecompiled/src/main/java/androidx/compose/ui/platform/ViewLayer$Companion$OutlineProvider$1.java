package androidx.compose.ui.platform;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ViewLayer$Companion$OutlineProvider$1 extends ViewOutlineProvider {
    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        ResultKt.checkNotNullParameter(view, "view");
        ResultKt.checkNotNullParameter(outline, "outline");
        Outline outline2 = ((ViewLayer) view).outlineResolver.getOutline();
        ResultKt.checkNotNull(outline2);
        outline.set(outline2);
    }
}
