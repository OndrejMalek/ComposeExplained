package androidx.compose.foundation.layout;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.unit.Dp;

/* loaded from: classes.dex */
public final class PaddingValuesImpl {
    public final float bottom;
    public final float end;
    public final float start;
    public final float top;

    public PaddingValuesImpl(float f, float f2, float f3, float f4) {
        this.start = f;
        this.top = f2;
        this.end = f3;
        this.bottom = f4;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PaddingValuesImpl)) {
            return false;
        }
        PaddingValuesImpl paddingValuesImpl = (PaddingValuesImpl) obj;
        return Dp.m280equalsimpl0(this.start, paddingValuesImpl.start) && Dp.m280equalsimpl0(this.top, paddingValuesImpl.top) && Dp.m280equalsimpl0(this.end, paddingValuesImpl.end) && Dp.m280equalsimpl0(this.bottom, paddingValuesImpl.bottom);
    }

    public final int hashCode() {
        return Float.hashCode(this.bottom) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.end, AnimationEndReason$EnumUnboxingLocalUtility.m(this.top, Float.hashCode(this.start) * 31, 31), 31);
    }

    public final String toString() {
        return "PaddingValues(start=" + ((Object) Dp.m281toStringimpl(this.start)) + ", top=" + ((Object) Dp.m281toStringimpl(this.top)) + ", end=" + ((Object) Dp.m281toStringimpl(this.end)) + ", bottom=" + ((Object) Dp.m281toStringimpl(this.bottom)) + ')';
    }
}
