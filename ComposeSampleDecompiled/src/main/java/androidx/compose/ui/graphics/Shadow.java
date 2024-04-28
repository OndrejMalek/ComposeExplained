package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class Shadow {
    public static final Shadow None = new Shadow(Brush.Color(4278190080L), Offset.Zero, 0.0f);
    public final float blurRadius;
    public final long color;
    public final long offset;

    public Shadow(long j, long j2, float f) {
        this.color = j;
        this.offset = j2;
        this.blurRadius = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shadow)) {
            return false;
        }
        Shadow shadow = (Shadow) obj;
        return Color.m121equalsimpl0(this.color, shadow.color) && Offset.m75equalsimpl0(this.offset, shadow.offset) && this.blurRadius == shadow.blurRadius;
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Float.hashCode(this.blurRadius) + ((Long.hashCode(this.offset) + (Long.hashCode(this.color) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Shadow(color=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.color, sb, ", offset=");
        sb.append((Object) Offset.m80toStringimpl(this.offset));
        sb.append(", blurRadius=");
        sb.append(this.blurRadius);
        sb.append(')');
        return sb.toString();
    }
}
