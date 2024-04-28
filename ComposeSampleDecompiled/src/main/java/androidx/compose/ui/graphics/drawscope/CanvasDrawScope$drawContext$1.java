package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.Canvas;

/* loaded from: classes.dex */
public final class CanvasDrawScope$drawContext$1 {
    public final /* synthetic */ CanvasDrawScope this$0;
    public final CanvasDrawScopeKt$asDrawTransform$1 transform = new CanvasDrawScopeKt$asDrawTransform$1(this);

    public CanvasDrawScope$drawContext$1(CanvasDrawScope canvasDrawScope) {
        this.this$0 = canvasDrawScope;
    }

    public final Canvas getCanvas() {
        return this.this$0.drawParams.canvas;
    }
}
