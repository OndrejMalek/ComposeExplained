package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Xyz extends ColorSpace {
    public static float clamp(float f) {
        return ResultKt.coerceIn(f, -2.0f, 2.0f);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        return 2.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        return -2.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final long toXy$ui_graphics_release(float f, float f2, float f3) {
        float clamp = clamp(f);
        float clamp2 = clamp(f2);
        return (Float.floatToIntBits(clamp2) & 4294967295L) | (Float.floatToIntBits(clamp) << 32);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float toZ$ui_graphics_release(float f, float f2, float f3) {
        return clamp(f3);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public final long mo133xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace) {
        ResultKt.checkNotNullParameter(colorSpace, "colorSpace");
        return Brush.Color(clamp(f), clamp(f2), clamp(f3), f4, colorSpace);
    }
}
