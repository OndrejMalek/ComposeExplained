package androidx.compose.ui.graphics;

import _COROUTINE._BOUNDARY;
import android.graphics.BlendMode;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt$shadow$2$1;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class Brush {
    public static final RectangleShapeKt$RectangleShape$1 RectangleShape = new Object();
    public static Method inorderBarrierMethod;
    public static boolean orderMethodsFetched;
    public static Method reorderBarrierMethod;

    public static final long Color(float f, float f2, float f3, float f4, ColorSpace colorSpace) {
        ResultKt.checkNotNullParameter(colorSpace, "colorSpace");
        float minValue = colorSpace.getMinValue(0);
        if (f <= colorSpace.getMaxValue(0) && minValue <= f) {
            float minValue2 = colorSpace.getMinValue(1);
            if (f2 <= colorSpace.getMaxValue(1) && minValue2 <= f2) {
                float minValue3 = colorSpace.getMinValue(2);
                if (f3 <= colorSpace.getMaxValue(2) && minValue3 <= f3 && 0.0f <= f4 && f4 <= 1.0f) {
                    if (colorSpace.isSrgb()) {
                        long j = (((((((int) ((f * 255.0f) + 0.5f)) << 16) | (((int) ((f4 * 255.0f) + 0.5f)) << 24)) | (((int) ((f2 * 255.0f) + 0.5f)) << 8)) | ((int) ((f3 * 255.0f) + 0.5f))) & 4294967295L) << 32;
                        int i = Color.$r8$clinit;
                        return j;
                    }
                    int i2 = ColorModel.$r8$clinit;
                    if (((int) (colorSpace.model >> 32)) == 3) {
                        int i3 = colorSpace.id;
                        if (i3 != -1) {
                            long m128constructorimpl = ((Float16.m128constructorimpl(f2) & 65535) << 32) | ((Float16.m128constructorimpl(f) & 65535) << 48) | ((Float16.m128constructorimpl(f3) & 65535) << 16) | ((((int) ((Math.max(0.0f, Math.min(f4, 1.0f)) * 1023.0f) + 0.5f)) & 1023) << 6) | (i3 & 63);
                            int i4 = Color.$r8$clinit;
                            return m128constructorimpl;
                        }
                        throw new IllegalArgumentException("Unknown color space, please use a color space in ColorSpaces".toString());
                    }
                    throw new IllegalArgumentException("Color only works with ColorSpaces with 3 components".toString());
                }
            }
        }
        throw new IllegalArgumentException(("red = " + f + ", green = " + f2 + ", blue = " + f3 + ", alpha = " + f4 + " outside the range for " + colorSpace).toString());
    }

    public static long Color$default(int i, int i2, int i3) {
        long j = (((((i & 255) << 16) | (-16777216)) | ((i2 & 255) << 8)) | (i3 & 255)) << 32;
        int i4 = Color.$r8$clinit;
        return j;
    }

    public static final AndroidPaint Paint() {
        return new AndroidPaint(new Paint(7));
    }

    public static final AndroidPath Path() {
        return new AndroidPath(new android.graphics.Path());
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static float[] m97constructorimpl$default() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static void enableZ(android.graphics.Canvas canvas, boolean z) {
        Method method;
        ResultKt.checkNotNullParameter(canvas, "canvas");
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            CanvasZHelper.INSTANCE.enableZ(canvas, z);
            return;
        }
        if (!orderMethodsFetched) {
            try {
                if (i == 28) {
                    Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass());
                    reorderBarrierMethod = (Method) declaredMethod.invoke(android.graphics.Canvas.class, "insertReorderBarrier", new Class[0]);
                    inorderBarrierMethod = (Method) declaredMethod.invoke(android.graphics.Canvas.class, "insertInorderBarrier", new Class[0]);
                } else {
                    reorderBarrierMethod = android.graphics.Canvas.class.getDeclaredMethod("insertReorderBarrier", new Class[0]);
                    inorderBarrierMethod = android.graphics.Canvas.class.getDeclaredMethod("insertInorderBarrier", new Class[0]);
                }
                Method method2 = reorderBarrierMethod;
                if (method2 != null) {
                    method2.setAccessible(true);
                }
                Method method3 = inorderBarrierMethod;
                if (method3 != null) {
                    method3.setAccessible(true);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
            orderMethodsFetched = true;
        }
        if (z) {
            try {
                Method method4 = reorderBarrierMethod;
                if (method4 != null) {
                    method4.invoke(canvas, new Object[0]);
                }
            } catch (IllegalAccessException | InvocationTargetException unused2) {
                return;
            }
        }
        if (z || (method = inorderBarrierMethod) == null) {
            return;
        }
        method.invoke(canvas, new Object[0]);
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m98equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: equals-impl0$1, reason: not valid java name */
    public static final boolean m99equalsimpl0$1(int i, int i2) {
        return i == i2;
    }

    /* renamed from: equals-impl0$2, reason: not valid java name */
    public static final boolean m100equalsimpl0$2(int i, int i2) {
        return i == i2;
    }

    /* renamed from: equals-impl0$3, reason: not valid java name */
    public static final boolean m101equalsimpl0$3(int i, int i2) {
        return i == i2;
    }

    public static final Modifier graphicsLayer(ShadowKt$shadow$2$1 shadowKt$shadow$2$1) {
        return new BlockGraphicsLayerElement(shadowKt$shadow$2$1);
    }

    /* renamed from: graphicsLayer-Ap8cVGQ$default, reason: not valid java name */
    public static Modifier m102graphicsLayerAp8cVGQ$default(Modifier modifier, Shape shape, boolean z, int i) {
        long j = TransformOrigin.Center;
        Shape shape2 = (i & 2048) != 0 ? RectangleShape : shape;
        boolean z2 = (i & 4096) != 0 ? false : z;
        long j2 = GraphicsLayerScopeKt.DefaultShadowColor;
        ResultKt.checkNotNullParameter(modifier, "$this$graphicsLayer");
        ResultKt.checkNotNullParameter(shape2, "shape");
        return modifier.then(new GraphicsLayerElement(1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 8.0f, j, shape2, z2, j2, j2, 0));
    }

    /* renamed from: luminance-8_81llA, reason: not valid java name */
    public static final float m103luminance8_81llA(long j) {
        ColorSpace m124getColorSpaceimpl = Color.m124getColorSpaceimpl(j);
        if (!ColorModel.m131equalsimpl0(m124getColorSpaceimpl.model, ColorModel.Rgb)) {
            throw new IllegalArgumentException(("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) ColorModel.m132toStringimpl(m124getColorSpaceimpl.model))).toString());
        }
        double m126getRedimpl = Color.m126getRedimpl(j);
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = ((Rgb) m124getColorSpaceimpl).eotfFunc;
        double invoke = rgb$$ExternalSyntheticLambda0.invoke(m126getRedimpl);
        float invoke2 = (float) ((rgb$$ExternalSyntheticLambda0.invoke(Color.m123getBlueimpl(j)) * 0.0722d) + (rgb$$ExternalSyntheticLambda0.invoke(Color.m125getGreenimpl(j)) * 0.7152d) + (invoke * 0.2126d));
        float f = 0.0f;
        if (invoke2 > 0.0f) {
            f = 1.0f;
            if (invoke2 < 1.0f) {
                return invoke2;
            }
        }
        return f;
    }

    /* renamed from: map-MK-Hz9U, reason: not valid java name */
    public static final long m104mapMKHz9U(float[] fArr, long j) {
        float m76getXimpl = Offset.m76getXimpl(j);
        float m77getYimpl = Offset.m77getYimpl(j);
        float f = 1 / (((fArr[7] * m77getYimpl) + (fArr[3] * m76getXimpl)) + fArr[15]);
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            f = 0.0f;
        }
        return _BOUNDARY.Offset(((fArr[4] * m77getYimpl) + (fArr[0] * m76getXimpl) + fArr[12]) * f, ((fArr[5] * m77getYimpl) + (fArr[1] * m76getXimpl) + fArr[13]) * f);
    }

    /* renamed from: map-impl, reason: not valid java name */
    public static final void m105mapimpl(float[] fArr, MutableRect mutableRect) {
        long m104mapMKHz9U = m104mapMKHz9U(fArr, _BOUNDARY.Offset(mutableRect.left, mutableRect.top));
        long m104mapMKHz9U2 = m104mapMKHz9U(fArr, _BOUNDARY.Offset(mutableRect.left, mutableRect.bottom));
        long m104mapMKHz9U3 = m104mapMKHz9U(fArr, _BOUNDARY.Offset(mutableRect.right, mutableRect.top));
        long m104mapMKHz9U4 = m104mapMKHz9U(fArr, _BOUNDARY.Offset(mutableRect.right, mutableRect.bottom));
        mutableRect.left = Math.min(Math.min(Offset.m76getXimpl(m104mapMKHz9U), Offset.m76getXimpl(m104mapMKHz9U2)), Math.min(Offset.m76getXimpl(m104mapMKHz9U3), Offset.m76getXimpl(m104mapMKHz9U4)));
        mutableRect.top = Math.min(Math.min(Offset.m77getYimpl(m104mapMKHz9U), Offset.m77getYimpl(m104mapMKHz9U2)), Math.min(Offset.m77getYimpl(m104mapMKHz9U3), Offset.m77getYimpl(m104mapMKHz9U4)));
        mutableRect.right = Math.max(Math.max(Offset.m76getXimpl(m104mapMKHz9U), Offset.m76getXimpl(m104mapMKHz9U2)), Math.max(Offset.m76getXimpl(m104mapMKHz9U3), Offset.m76getXimpl(m104mapMKHz9U4)));
        mutableRect.bottom = Math.max(Math.max(Offset.m77getYimpl(m104mapMKHz9U), Offset.m77getYimpl(m104mapMKHz9U2)), Math.max(Offset.m77getYimpl(m104mapMKHz9U3), Offset.m77getYimpl(m104mapMKHz9U4)));
    }

    /* renamed from: reset-impl, reason: not valid java name */
    public static final void m106resetimpl(float[] fArr) {
        int i = 0;
        while (i < 4) {
            int i2 = 0;
            while (i2 < 4) {
                fArr[(i2 * 4) + i] = i == i2 ? 1.0f : 0.0f;
                i2++;
            }
            i++;
        }
    }

    /* renamed from: setFrom-tU-YjHk, reason: not valid java name */
    public static final void m107setFromtUYjHk(Matrix matrix, float[] fArr) {
        ResultKt.checkNotNullParameter(fArr, "$this$setFrom");
        ResultKt.checkNotNullParameter(matrix, "matrix");
        matrix.getValues(fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = fArr[4];
        float f6 = fArr[5];
        float f7 = fArr[6];
        float f8 = fArr[7];
        float f9 = fArr[8];
        fArr[0] = f;
        fArr[1] = f4;
        fArr[2] = 0.0f;
        fArr[3] = f7;
        fArr[4] = f2;
        fArr[5] = f5;
        fArr[6] = 0.0f;
        fArr[7] = f8;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 1.0f;
        fArr[11] = 0.0f;
        fArr[12] = f3;
        fArr[13] = f6;
        fArr[14] = 0.0f;
        fArr[15] = f9;
    }

    /* renamed from: toAndroidBlendMode-s9anfk8, reason: not valid java name */
    public static final BlendMode m108toAndroidBlendModes9anfk8(int i) {
        BlendMode blendMode;
        BlendMode blendMode2;
        BlendMode blendMode3;
        BlendMode blendMode4;
        BlendMode blendMode5;
        BlendMode blendMode6;
        BlendMode blendMode7;
        BlendMode blendMode8;
        BlendMode blendMode9;
        BlendMode blendMode10;
        BlendMode blendMode11;
        BlendMode blendMode12;
        BlendMode blendMode13;
        BlendMode blendMode14;
        BlendMode blendMode15;
        BlendMode blendMode16;
        BlendMode blendMode17;
        BlendMode blendMode18;
        BlendMode blendMode19;
        BlendMode blendMode20;
        BlendMode blendMode21;
        BlendMode blendMode22;
        BlendMode blendMode23;
        BlendMode blendMode24;
        BlendMode blendMode25;
        BlendMode blendMode26;
        BlendMode blendMode27;
        BlendMode blendMode28;
        BlendMode blendMode29;
        BlendMode blendMode30;
        if (m98equalsimpl0(i, 0)) {
            blendMode30 = BlendMode.CLEAR;
            return blendMode30;
        }
        if (m98equalsimpl0(i, 1)) {
            blendMode29 = BlendMode.SRC;
            return blendMode29;
        }
        if (m98equalsimpl0(i, 2)) {
            blendMode28 = BlendMode.DST;
            return blendMode28;
        }
        if (m98equalsimpl0(i, 3)) {
            blendMode27 = BlendMode.SRC_OVER;
            return blendMode27;
        }
        if (m98equalsimpl0(i, 4)) {
            blendMode26 = BlendMode.DST_OVER;
            return blendMode26;
        }
        if (m98equalsimpl0(i, 5)) {
            blendMode25 = BlendMode.SRC_IN;
            return blendMode25;
        }
        if (m98equalsimpl0(i, 6)) {
            blendMode24 = BlendMode.DST_IN;
            return blendMode24;
        }
        if (m98equalsimpl0(i, 7)) {
            blendMode23 = BlendMode.SRC_OUT;
            return blendMode23;
        }
        if (m98equalsimpl0(i, 8)) {
            blendMode22 = BlendMode.DST_OUT;
            return blendMode22;
        }
        if (m98equalsimpl0(i, 9)) {
            blendMode21 = BlendMode.SRC_ATOP;
            return blendMode21;
        }
        if (m98equalsimpl0(i, 10)) {
            blendMode20 = BlendMode.DST_ATOP;
            return blendMode20;
        }
        if (m98equalsimpl0(i, 11)) {
            blendMode19 = BlendMode.XOR;
            return blendMode19;
        }
        if (m98equalsimpl0(i, 12)) {
            blendMode18 = BlendMode.PLUS;
            return blendMode18;
        }
        if (m98equalsimpl0(i, 13)) {
            blendMode17 = BlendMode.MODULATE;
            return blendMode17;
        }
        if (m98equalsimpl0(i, 14)) {
            blendMode16 = BlendMode.SCREEN;
            return blendMode16;
        }
        if (m98equalsimpl0(i, 15)) {
            blendMode15 = BlendMode.OVERLAY;
            return blendMode15;
        }
        if (m98equalsimpl0(i, 16)) {
            blendMode14 = BlendMode.DARKEN;
            return blendMode14;
        }
        if (m98equalsimpl0(i, 17)) {
            blendMode13 = BlendMode.LIGHTEN;
            return blendMode13;
        }
        if (m98equalsimpl0(i, 18)) {
            blendMode12 = BlendMode.COLOR_DODGE;
            return blendMode12;
        }
        if (m98equalsimpl0(i, 19)) {
            blendMode11 = BlendMode.COLOR_BURN;
            return blendMode11;
        }
        if (m98equalsimpl0(i, 20)) {
            blendMode10 = BlendMode.HARD_LIGHT;
            return blendMode10;
        }
        if (m98equalsimpl0(i, 21)) {
            blendMode9 = BlendMode.SOFT_LIGHT;
            return blendMode9;
        }
        if (m98equalsimpl0(i, 22)) {
            blendMode8 = BlendMode.DIFFERENCE;
            return blendMode8;
        }
        if (m98equalsimpl0(i, 23)) {
            blendMode7 = BlendMode.EXCLUSION;
            return blendMode7;
        }
        if (m98equalsimpl0(i, 24)) {
            blendMode6 = BlendMode.MULTIPLY;
            return blendMode6;
        }
        if (m98equalsimpl0(i, 25)) {
            blendMode5 = BlendMode.HUE;
            return blendMode5;
        }
        if (m98equalsimpl0(i, 26)) {
            blendMode4 = BlendMode.SATURATION;
            return blendMode4;
        }
        if (m98equalsimpl0(i, 27)) {
            blendMode3 = BlendMode.COLOR;
            return blendMode3;
        }
        if (m98equalsimpl0(i, 28)) {
            blendMode2 = BlendMode.LUMINOSITY;
            return blendMode2;
        }
        blendMode = BlendMode.SRC_OVER;
        return blendMode;
    }

    /* renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m109toArgb8_81llA(long j) {
        float[] fArr = ColorSpaces.SrgbPrimaries;
        return (int) (Color.m119convertvNxB06k(j, ColorSpaces.Srgb) >>> 32);
    }

    /* renamed from: applyTo-Pq9zytI, reason: not valid java name */
    public abstract void mo110applyToPq9zytI(float f, long j, AndroidPaint androidPaint);

    public static final long Color(long j) {
        long j2 = (j & 4294967295L) << 32;
        int i = Color.$r8$clinit;
        return j2;
    }
}
