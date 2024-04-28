package androidx.compose.ui.geometry;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;

/* loaded from: classes.dex */
public final class Rect {
    public static final Rect Zero = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
    public final float bottom;
    public final float left;
    public final float right;
    public final float top;

    public Rect(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rect)) {
            return false;
        }
        Rect rect = (Rect) obj;
        return Float.compare(this.left, rect.left) == 0 && Float.compare(this.top, rect.top) == 0 && Float.compare(this.right, rect.right) == 0 && Float.compare(this.bottom, rect.bottom) == 0;
    }

    /* renamed from: getCenter-F1C5BW0, reason: not valid java name */
    public final long m81getCenterF1C5BW0() {
        return _BOUNDARY.Offset((getWidth() / 2.0f) + this.left, (getHeight() / 2.0f) + this.top);
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        return Float.hashCode(this.bottom) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.right, AnimationEndReason$EnumUnboxingLocalUtility.m(this.top, Float.hashCode(this.left) * 31, 31), 31);
    }

    public final Rect intersect(Rect rect) {
        return new Rect(Math.max(this.left, rect.left), Math.max(this.top, rect.top), Math.min(this.right, rect.right), Math.min(this.bottom, rect.bottom));
    }

    public final String toString() {
        return "Rect.fromLTRB(" + _BOUNDARY.toStringAsFixed(this.left) + ", " + _BOUNDARY.toStringAsFixed(this.top) + ", " + _BOUNDARY.toStringAsFixed(this.right) + ", " + _BOUNDARY.toStringAsFixed(this.bottom) + ')';
    }

    public final Rect translate(float f, float f2) {
        return new Rect(this.left + f, this.top + f2, this.right + f, this.bottom + f2);
    }

    /* renamed from: translate-k-4lQ0M, reason: not valid java name */
    public final Rect m82translatek4lQ0M(long j) {
        return new Rect(Offset.m76getXimpl(j) + this.left, Offset.m77getYimpl(j) + this.top, Offset.m76getXimpl(j) + this.right, Offset.m77getYimpl(j) + this.bottom);
    }
}
