package androidx.compose.ui.text.style;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextMotion {
    public final int linearity;
    public final boolean subpixelTextPositioning;
    public static final TextMotion Static = new TextMotion(2, false);
    public static final TextMotion Animated = new TextMotion(1, true);

    public TextMotion(int i, boolean z) {
        this.linearity = i;
        this.subpixelTextPositioning = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextMotion)) {
            return false;
        }
        TextMotion textMotion = (TextMotion) obj;
        return this.linearity == textMotion.linearity && this.subpixelTextPositioning == textMotion.subpixelTextPositioning;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.subpixelTextPositioning) + (Integer.hashCode(this.linearity) * 31);
    }

    public final String toString() {
        return ResultKt.areEqual(this, Static) ? "TextMotion.Static" : ResultKt.areEqual(this, Animated) ? "TextMotion.Animated" : "Invalid";
    }
}
