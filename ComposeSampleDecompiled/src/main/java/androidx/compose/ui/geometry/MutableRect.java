package androidx.compose.ui.geometry;

import _COROUTINE._BOUNDARY;

/* loaded from: classes.dex */
public final class MutableRect {
    public float bottom;
    public float left;
    public float right;
    public float top;

    public final void intersect(float f, float f2, float f3, float f4) {
        this.left = Math.max(f, this.left);
        this.top = Math.max(f2, this.top);
        this.right = Math.min(f3, this.right);
        this.bottom = Math.min(f4, this.bottom);
    }

    public final boolean isEmpty() {
        return this.left >= this.right || this.top >= this.bottom;
    }

    public final String toString() {
        return "MutableRect(" + _BOUNDARY.toStringAsFixed(this.left) + ", " + _BOUNDARY.toStringAsFixed(this.top) + ", " + _BOUNDARY.toStringAsFixed(this.right) + ", " + _BOUNDARY.toStringAsFixed(this.bottom) + ')';
    }
}
