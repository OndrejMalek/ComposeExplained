package androidx.compose.ui.graphics.colorspace;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Oklab extends ColorSpace {
    public static final float[] InverseM1;
    public static final float[] InverseM2;
    public static final float[] M1;
    public static final float[] M2;

    static {
        float[] mul3x3 = _BOUNDARY.mul3x3(new float[]{0.818933f, 0.032984544f, 0.0482003f, 0.36186674f, 0.9293119f, 0.26436627f, -0.12885971f, 0.03614564f, 0.6338517f}, _BOUNDARY.chromaticAdaptation(Adaptation.Bradford.transform, new float[]{0.964212f, 1.0f, 0.8251883f}, new float[]{0.95042855f, 1.0f, 1.0889004f}));
        M1 = mul3x3;
        float[] fArr = {0.21045426f, 1.9779985f, 0.025904037f, 0.7936178f, -2.4285922f, 0.78277177f, -0.004072047f, 0.4505937f, -0.80867577f};
        M2 = fArr;
        InverseM1 = _BOUNDARY.inverse3x3(mul3x3);
        InverseM2 = _BOUNDARY.inverse3x3(fArr);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        return i == 0 ? 1.0f : 0.5f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        return i == 0 ? 0.0f : -0.5f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final long toXy$ui_graphics_release(float f, float f2, float f3) {
        float coerceIn = ResultKt.coerceIn(f, 0.0f, 1.0f);
        float coerceIn2 = ResultKt.coerceIn(f2, -0.5f, 0.5f);
        float coerceIn3 = ResultKt.coerceIn(f3, -0.5f, 0.5f);
        float[] fArr = InverseM2;
        float mul3x3Float3_0 = _BOUNDARY.mul3x3Float3_0(fArr, coerceIn, coerceIn2, coerceIn3);
        float mul3x3Float3_1 = _BOUNDARY.mul3x3Float3_1(fArr, coerceIn, coerceIn2, coerceIn3);
        float mul3x3Float3_2 = _BOUNDARY.mul3x3Float3_2(fArr, coerceIn, coerceIn2, coerceIn3);
        float f4 = mul3x3Float3_0 * mul3x3Float3_0 * mul3x3Float3_0;
        float f5 = mul3x3Float3_1 * mul3x3Float3_1 * mul3x3Float3_1;
        float f6 = mul3x3Float3_2 * mul3x3Float3_2 * mul3x3Float3_2;
        float[] fArr2 = InverseM1;
        float mul3x3Float3_02 = _BOUNDARY.mul3x3Float3_0(fArr2, f4, f5, f6);
        float mul3x3Float3_12 = _BOUNDARY.mul3x3Float3_1(fArr2, f4, f5, f6);
        return (Float.floatToIntBits(mul3x3Float3_02) << 32) | (Float.floatToIntBits(mul3x3Float3_12) & 4294967295L);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float toZ$ui_graphics_release(float f, float f2, float f3) {
        float coerceIn = ResultKt.coerceIn(f, 0.0f, 1.0f);
        float coerceIn2 = ResultKt.coerceIn(f2, -0.5f, 0.5f);
        float coerceIn3 = ResultKt.coerceIn(f3, -0.5f, 0.5f);
        float[] fArr = InverseM2;
        float mul3x3Float3_0 = _BOUNDARY.mul3x3Float3_0(fArr, coerceIn, coerceIn2, coerceIn3);
        float mul3x3Float3_1 = _BOUNDARY.mul3x3Float3_1(fArr, coerceIn, coerceIn2, coerceIn3);
        float mul3x3Float3_2 = _BOUNDARY.mul3x3Float3_2(fArr, coerceIn, coerceIn2, coerceIn3);
        float f4 = mul3x3Float3_2 * mul3x3Float3_2 * mul3x3Float3_2;
        return _BOUNDARY.mul3x3Float3_2(InverseM1, mul3x3Float3_0 * mul3x3Float3_0 * mul3x3Float3_0, mul3x3Float3_1 * mul3x3Float3_1 * mul3x3Float3_1, f4);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public final long mo133xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace) {
        ResultKt.checkNotNullParameter(colorSpace, "colorSpace");
        float[] fArr = M1;
        float mul3x3Float3_0 = _BOUNDARY.mul3x3Float3_0(fArr, f, f2, f3);
        float mul3x3Float3_1 = _BOUNDARY.mul3x3Float3_1(fArr, f, f2, f3);
        float mul3x3Float3_2 = _BOUNDARY.mul3x3Float3_2(fArr, f, f2, f3);
        double d = 0.33333334f;
        float signum = Math.signum(mul3x3Float3_0) * ((float) Math.pow(Math.abs(mul3x3Float3_0), d));
        float signum2 = Math.signum(mul3x3Float3_1) * ((float) Math.pow(Math.abs(mul3x3Float3_1), d));
        float signum3 = Math.signum(mul3x3Float3_2) * ((float) Math.pow(Math.abs(mul3x3Float3_2), d));
        float[] fArr2 = M2;
        return Brush.Color(_BOUNDARY.mul3x3Float3_0(fArr2, signum, signum2, signum3), _BOUNDARY.mul3x3Float3_1(fArr2, signum, signum2, signum3), _BOUNDARY.mul3x3Float3_2(fArr2, signum, signum2, signum3), f4, colorSpace);
    }
}
