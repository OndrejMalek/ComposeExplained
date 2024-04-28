package androidx.compose.ui.input.rotary;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;

/* loaded from: classes.dex */
public final class RotaryScrollEvent {
    public final float horizontalScrollPixels;
    public final long uptimeMillis;
    public final float verticalScrollPixels;

    public RotaryScrollEvent(float f, float f2, long j) {
        this.verticalScrollPixels = f;
        this.horizontalScrollPixels = f2;
        this.uptimeMillis = j;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof RotaryScrollEvent) {
            RotaryScrollEvent rotaryScrollEvent = (RotaryScrollEvent) obj;
            if (rotaryScrollEvent.verticalScrollPixels == this.verticalScrollPixels && rotaryScrollEvent.horizontalScrollPixels == this.horizontalScrollPixels && rotaryScrollEvent.uptimeMillis == this.uptimeMillis) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.uptimeMillis) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.horizontalScrollPixels, Float.hashCode(this.verticalScrollPixels) * 31, 31);
    }

    public final String toString() {
        return "RotaryScrollEvent(verticalScrollPixels=" + this.verticalScrollPixels + ",horizontalScrollPixels=" + this.horizontalScrollPixels + ",uptimeMillis=" + this.uptimeMillis + ')';
    }
}
