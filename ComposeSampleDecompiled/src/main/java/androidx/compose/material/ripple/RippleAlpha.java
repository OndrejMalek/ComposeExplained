package androidx.compose.material.ripple;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;

/* loaded from: classes.dex */
public final class RippleAlpha {
    public final float draggedAlpha;
    public final float focusedAlpha;
    public final float hoveredAlpha;
    public final float pressedAlpha;

    public RippleAlpha(float f, float f2, float f3, float f4) {
        this.draggedAlpha = f;
        this.focusedAlpha = f2;
        this.hoveredAlpha = f3;
        this.pressedAlpha = f4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RippleAlpha)) {
            return false;
        }
        RippleAlpha rippleAlpha = (RippleAlpha) obj;
        return this.draggedAlpha == rippleAlpha.draggedAlpha && this.focusedAlpha == rippleAlpha.focusedAlpha && this.hoveredAlpha == rippleAlpha.hoveredAlpha && this.pressedAlpha == rippleAlpha.pressedAlpha;
    }

    public final int hashCode() {
        return Float.hashCode(this.pressedAlpha) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.hoveredAlpha, AnimationEndReason$EnumUnboxingLocalUtility.m(this.focusedAlpha, Float.hashCode(this.draggedAlpha) * 31, 31), 31);
    }

    public final String toString() {
        return "RippleAlpha(draggedAlpha=" + this.draggedAlpha + ", focusedAlpha=" + this.focusedAlpha + ", hoveredAlpha=" + this.hoveredAlpha + ", pressedAlpha=" + this.pressedAlpha + ')';
    }
}
