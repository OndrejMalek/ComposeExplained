package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Lab extends ColorSpace {
    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        return i == 0 ? 100.0f : 128.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        return i == 0 ? 0.0f : -128.0f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final long toXy$ui_graphics_release(float f, float f2, float f3) {
        float coerceIn = (ResultKt.coerceIn(f, 0.0f, 100.0f) + 16.0f) / 116.0f;
        float coerceIn2 = (ResultKt.coerceIn(f, -128.0f, 128.0f) * 0.002f) + coerceIn;
        float f4 = coerceIn2 > 0.20689656f ? coerceIn2 * coerceIn2 * coerceIn2 : (coerceIn2 - 0.13793103f) * 0.12841855f;
        float f5 = coerceIn > 0.20689656f ? coerceIn * coerceIn * coerceIn : (coerceIn - 0.13793103f) * 0.12841855f;
        float[] fArr = Illuminant.D50Xyz;
        return (Float.floatToIntBits(f4 * fArr[0]) << 32) | (Float.floatToIntBits(f5 * fArr[1]) & 4294967295L);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float toZ$ui_graphics_release(float f, float f2, float f3) {
        float coerceIn = ((ResultKt.coerceIn(f, 0.0f, 100.0f) + 16.0f) / 116.0f) - (ResultKt.coerceIn(f3, -128.0f, 128.0f) * 0.005f);
        return (coerceIn > 0.20689656f ? coerceIn * coerceIn * coerceIn : 0.12841855f * (coerceIn - 0.13793103f)) * Illuminant.D50Xyz[2];
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public final long mo133xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace) {
        ResultKt.checkNotNullParameter(colorSpace, "colorSpace");
        float[] fArr = Illuminant.D50Xyz;
        float f5 = f / fArr[0];
        float f6 = f2 / fArr[1];
        float f7 = f3 / fArr[2];
        float pow = f5 > 0.008856452f ? (float) Math.pow(f5, 0.33333334f) : (f5 * 7.787037f) + 0.13793103f;
        float pow2 = f6 > 0.008856452f ? (float) Math.pow(f6, 0.33333334f) : (f6 * 7.787037f) + 0.13793103f;
        return Brush.Color(ResultKt.coerceIn((116.0f * pow2) - 16.0f, 0.0f, 100.0f), ResultKt.coerceIn((pow - pow2) * 500.0f, -128.0f, 128.0f), ResultKt.coerceIn((pow2 - (f7 > 0.008856452f ? (float) Math.pow(f7, 0.33333334f) : (f7 * 7.787037f) + 0.13793103f)) * 200.0f, -128.0f, 128.0f), f4, colorSpace);
    }
}
