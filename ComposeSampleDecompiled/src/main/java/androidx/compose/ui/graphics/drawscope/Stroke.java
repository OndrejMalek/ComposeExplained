package androidx.compose.ui.graphics.drawscope;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Stroke extends DrawStyle {
    public final float width;
    public final float miter = 4.0f;
    public final int cap = 0;
    public final int join = 0;

    public Stroke(float f) {
        this.width = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Stroke)) {
            return false;
        }
        Stroke stroke = (Stroke) obj;
        if (this.width != stroke.width || this.miter != stroke.miter || !Brush.m100equalsimpl0$2(this.cap, stroke.cap) || !Brush.m101equalsimpl0$3(this.join, stroke.join)) {
            return false;
        }
        stroke.getClass();
        return ResultKt.areEqual(null, null);
    }

    public final int hashCode() {
        return (Integer.hashCode(this.join) + ((Integer.hashCode(this.cap) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.miter, Float.hashCode(this.width) * 31, 31)) * 31)) * 31;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Stroke(width=");
        sb.append(this.width);
        sb.append(", miter=");
        sb.append(this.miter);
        sb.append(", cap=");
        int i = this.cap;
        String str = "Unknown";
        sb.append((Object) (Brush.m100equalsimpl0$2(i, 0) ? "Butt" : Brush.m100equalsimpl0$2(i, 1) ? "Round" : Brush.m100equalsimpl0$2(i, 2) ? "Square" : "Unknown"));
        sb.append(", join=");
        int i2 = this.join;
        if (Brush.m101equalsimpl0$3(i2, 0)) {
            str = "Miter";
        } else if (Brush.m101equalsimpl0$3(i2, 1)) {
            str = "Round";
        } else if (Brush.m101equalsimpl0$3(i2, 2)) {
            str = "Bevel";
        }
        sb.append((Object) str);
        sb.append(", pathEffect=null)");
        return sb.toString();
    }
}
