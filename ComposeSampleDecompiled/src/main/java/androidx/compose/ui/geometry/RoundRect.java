package androidx.compose.ui.geometry;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;

/* loaded from: classes.dex */
public final class RoundRect {
    public final float bottom;
    public final long bottomLeftCornerRadius;
    public final long bottomRightCornerRadius;
    public final float left;
    public final float right;
    public final float top;
    public final long topLeftCornerRadius;
    public final long topRightCornerRadius;

    static {
        long j = CornerRadius.Zero;
        _BOUNDARY.CornerRadius(CornerRadius.m72getXimpl(j), CornerRadius.m73getYimpl(j));
    }

    public RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) obj;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m71equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m71equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m71equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m71equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        int m = AnimationEndReason$EnumUnboxingLocalUtility.m(this.bottom, AnimationEndReason$EnumUnboxingLocalUtility.m(this.right, AnimationEndReason$EnumUnboxingLocalUtility.m(this.top, Float.hashCode(this.left) * 31, 31), 31), 31);
        int i = CornerRadius.$r8$clinit;
        return Long.hashCode(this.bottomLeftCornerRadius) + ((Long.hashCode(this.bottomRightCornerRadius) + ((Long.hashCode(this.topRightCornerRadius) + ((Long.hashCode(this.topLeftCornerRadius) + m) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String str = _BOUNDARY.toStringAsFixed(this.left) + ", " + _BOUNDARY.toStringAsFixed(this.top) + ", " + _BOUNDARY.toStringAsFixed(this.right) + ", " + _BOUNDARY.toStringAsFixed(this.bottom);
        long j = this.topLeftCornerRadius;
        long j2 = this.topRightCornerRadius;
        boolean m71equalsimpl0 = CornerRadius.m71equalsimpl0(j, j2);
        long j3 = this.bottomRightCornerRadius;
        long j4 = this.bottomLeftCornerRadius;
        if (!m71equalsimpl0 || !CornerRadius.m71equalsimpl0(j2, j3) || !CornerRadius.m71equalsimpl0(j3, j4)) {
            return "RoundRect(rect=" + str + ", topLeft=" + ((Object) CornerRadius.m74toStringimpl(j)) + ", topRight=" + ((Object) CornerRadius.m74toStringimpl(j2)) + ", bottomRight=" + ((Object) CornerRadius.m74toStringimpl(j3)) + ", bottomLeft=" + ((Object) CornerRadius.m74toStringimpl(j4)) + ')';
        }
        if (CornerRadius.m72getXimpl(j) == CornerRadius.m73getYimpl(j)) {
            return "RoundRect(rect=" + str + ", radius=" + _BOUNDARY.toStringAsFixed(CornerRadius.m72getXimpl(j)) + ')';
        }
        return "RoundRect(rect=" + str + ", x=" + _BOUNDARY.toStringAsFixed(CornerRadius.m72getXimpl(j)) + ", y=" + _BOUNDARY.toStringAsFixed(CornerRadius.m73getYimpl(j)) + ')';
    }
}
