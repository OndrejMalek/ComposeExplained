package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Outline$Rectangle extends Brush {
    public final Rect rect;

    public Outline$Rectangle(Rect rect) {
        this.rect = rect;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Outline$Rectangle) {
            return ResultKt.areEqual(this.rect, ((Outline$Rectangle) obj).rect);
        }
        return false;
    }

    public final int hashCode() {
        return this.rect.hashCode();
    }
}
