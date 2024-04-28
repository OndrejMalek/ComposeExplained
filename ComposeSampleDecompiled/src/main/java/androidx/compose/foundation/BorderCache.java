package androidx.compose.foundation;

import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class BorderCache {
    public final Canvas canvas = null;
    public final CanvasDrawScope canvasDrawScope = null;
    public Path borderPath = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderCache)) {
            return false;
        }
        BorderCache borderCache = (BorderCache) obj;
        borderCache.getClass();
        return ResultKt.areEqual(null, null) && ResultKt.areEqual(this.canvas, borderCache.canvas) && ResultKt.areEqual(this.canvasDrawScope, borderCache.canvasDrawScope) && ResultKt.areEqual(this.borderPath, borderCache.borderPath);
    }

    public final int hashCode() {
        int i = 0 * 31;
        Canvas canvas = this.canvas;
        int hashCode = (i + (canvas == null ? 0 : canvas.hashCode())) * 31;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        int hashCode2 = (hashCode + (canvasDrawScope == null ? 0 : canvasDrawScope.hashCode())) * 31;
        Path path = this.borderPath;
        return hashCode2 + (path != null ? path.hashCode() : 0);
    }

    public final String toString() {
        return "BorderCache(imageBitmap=" + ((Object) null) + ", canvas=" + this.canvas + ", canvasDrawScope=" + this.canvasDrawScope + ", borderPath=" + this.borderPath + ')';
    }
}
