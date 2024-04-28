package androidx.compose.ui.graphics.colorspace;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.graphics.Brush;
import java.util.Arrays;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public class Connector {
    public static final Connector OklabToSrgbPerceptual;
    public static final Connector$Companion$identity$1 SrgbIdentity;
    public static final Connector SrgbToOklabPerceptual;
    public final ColorSpace destination;
    public final float[] transform;
    public final ColorSpace transformDestination;
    public final ColorSpace transformSource;

    /* loaded from: classes.dex */
    public final class RgbConnector extends Connector {
        public final Rgb mDestination;
        public final Rgb mSource;
        public final float[] mTransform;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RgbConnector(Rgb rgb, Rgb rgb2, int i) {
            super(rgb, rgb2, rgb, rgb2, null);
            float[] mul3x3;
            ResultKt.checkNotNullParameter(rgb2, "mDestination");
            this.mSource = rgb;
            this.mDestination = rgb2;
            WhitePoint whitePoint = rgb.whitePoint;
            WhitePoint whitePoint2 = rgb2.whitePoint;
            boolean compare = _BOUNDARY.compare(whitePoint, whitePoint2);
            float[] fArr = rgb.transform;
            float[] fArr2 = rgb2.inverseTransform;
            if (compare) {
                mul3x3 = _BOUNDARY.mul3x3(fArr2, fArr);
            } else {
                float[] xyz$ui_graphics_release = whitePoint.toXyz$ui_graphics_release();
                float[] xyz$ui_graphics_release2 = whitePoint2.toXyz$ui_graphics_release();
                WhitePoint whitePoint3 = Illuminant.D50;
                boolean compare2 = _BOUNDARY.compare(whitePoint, whitePoint3);
                float[] fArr3 = Illuminant.D50Xyz;
                float[] fArr4 = Adaptation.Bradford.transform;
                if (!compare2) {
                    float[] copyOf = Arrays.copyOf(fArr3, 3);
                    ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                    fArr = _BOUNDARY.mul3x3(_BOUNDARY.chromaticAdaptation(fArr4, xyz$ui_graphics_release, copyOf), fArr);
                }
                if (!_BOUNDARY.compare(whitePoint2, whitePoint3)) {
                    float[] copyOf2 = Arrays.copyOf(fArr3, 3);
                    ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
                    fArr2 = _BOUNDARY.inverse3x3(_BOUNDARY.mul3x3(_BOUNDARY.chromaticAdaptation(fArr4, xyz$ui_graphics_release2, copyOf2), rgb2.transform));
                }
                mul3x3 = _BOUNDARY.mul3x3(fArr2, i == 3 ? _BOUNDARY.mul3x3Diag(new float[]{xyz$ui_graphics_release[0] / xyz$ui_graphics_release2[0], xyz$ui_graphics_release[1] / xyz$ui_graphics_release2[1], xyz$ui_graphics_release[2] / xyz$ui_graphics_release2[2]}, fArr) : fArr);
            }
            this.mTransform = mul3x3;
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        /* renamed from: transformToColor-wmQWz5c$ui_graphics_release */
        public final long mo134transformToColorwmQWz5c$ui_graphics_release(float f, float f2, float f3, float f4) {
            Rgb rgb = this.mSource;
            float invoke = (float) rgb.eotfFunc.invoke(f);
            double d = f2;
            Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = rgb.eotfFunc;
            float invoke2 = (float) rgb$$ExternalSyntheticLambda0.invoke(d);
            float invoke3 = (float) rgb$$ExternalSyntheticLambda0.invoke(f3);
            float[] fArr = this.mTransform;
            float mul3x3Float3_0 = _BOUNDARY.mul3x3Float3_0(fArr, invoke, invoke2, invoke3);
            float mul3x3Float3_1 = _BOUNDARY.mul3x3Float3_1(fArr, invoke, invoke2, invoke3);
            float mul3x3Float3_2 = _BOUNDARY.mul3x3Float3_2(fArr, invoke, invoke2, invoke3);
            Rgb rgb2 = this.mDestination;
            float invoke4 = (float) rgb2.oetfFunc.invoke(mul3x3Float3_0);
            double d2 = mul3x3Float3_1;
            Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda02 = rgb2.oetfFunc;
            return Brush.Color(invoke4, (float) rgb$$ExternalSyntheticLambda02.invoke(d2), (float) rgb$$ExternalSyntheticLambda02.invoke(mul3x3Float3_2), f4, rgb2);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1, androidx.compose.ui.graphics.colorspace.Connector] */
    static {
        Rgb rgb = ColorSpaces.Srgb;
        ResultKt.checkNotNullParameter(rgb, "source");
        SrgbIdentity = new Connector(rgb, rgb, 1);
        Oklab oklab = ColorSpaces.Oklab;
        SrgbToOklabPerceptual = new Connector(rgb, oklab, 0);
        OklabToSrgbPerceptual = new Connector(oklab, rgb, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Connector(androidx.compose.ui.graphics.colorspace.ColorSpace r17, androidx.compose.ui.graphics.colorspace.ColorSpace r18, int r19) {
        /*
            r16 = this;
            r1 = r17
            r2 = r18
            r0 = 2
            r3 = 1
            r4 = 0
            r5 = 3
            java.lang.String r6 = "source"
            kotlin.ResultKt.checkNotNullParameter(r1, r6)
            java.lang.String r6 = "destination"
            kotlin.ResultKt.checkNotNullParameter(r2, r6)
            long r6 = androidx.compose.ui.graphics.colorspace.ColorModel.Rgb
            long r8 = r1.model
            boolean r10 = androidx.compose.ui.graphics.colorspace.ColorModel.m131equalsimpl0(r8, r6)
            if (r10 == 0) goto L21
            androidx.compose.ui.graphics.colorspace.ColorSpace r10 = _COROUTINE._BOUNDARY.adapt$default(r17)
            goto L22
        L21:
            r10 = r1
        L22:
            long r11 = r2.model
            boolean r13 = androidx.compose.ui.graphics.colorspace.ColorModel.m131equalsimpl0(r11, r6)
            if (r13 == 0) goto L2f
            androidx.compose.ui.graphics.colorspace.ColorSpace r13 = _COROUTINE._BOUNDARY.adapt$default(r18)
            goto L30
        L2f:
            r13 = r2
        L30:
            r14 = 0
            r15 = r19
            if (r15 != r5) goto L41
            boolean r8 = androidx.compose.ui.graphics.colorspace.ColorModel.m131equalsimpl0(r8, r6)
            boolean r6 = androidx.compose.ui.graphics.colorspace.ColorModel.m131equalsimpl0(r11, r6)
            if (r8 == 0) goto L43
            if (r6 == 0) goto L43
        L41:
            r5 = r14
            goto L77
        L43:
            if (r8 != 0) goto L47
            if (r6 == 0) goto L41
        L47:
            if (r8 == 0) goto L4b
            r7 = r1
            goto L4c
        L4b:
            r7 = r2
        L4c:
            androidx.compose.ui.graphics.colorspace.Rgb r7 = (androidx.compose.ui.graphics.colorspace.Rgb) r7
            float[] r9 = androidx.compose.ui.graphics.colorspace.Illuminant.D50Xyz
            androidx.compose.ui.graphics.colorspace.WhitePoint r7 = r7.whitePoint
            if (r8 == 0) goto L59
            float[] r8 = r7.toXyz$ui_graphics_release()
            goto L5a
        L59:
            r8 = r9
        L5a:
            if (r6 == 0) goto L60
            float[] r9 = r7.toXyz$ui_graphics_release()
        L60:
            r6 = r8[r4]
            r7 = r9[r4]
            float r6 = r6 / r7
            r7 = r8[r3]
            r11 = r9[r3]
            float r7 = r7 / r11
            r8 = r8[r0]
            r9 = r9[r0]
            float r8 = r8 / r9
            float[] r5 = new float[r5]
            r5[r4] = r6
            r5[r3] = r7
            r5[r0] = r8
        L77:
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r10
            r4 = r13
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Connector.<init>(androidx.compose.ui.graphics.colorspace.ColorSpace, androidx.compose.ui.graphics.colorspace.ColorSpace, int):void");
    }

    /* renamed from: transformToColor-wmQWz5c$ui_graphics_release, reason: not valid java name */
    public long mo134transformToColorwmQWz5c$ui_graphics_release(float f, float f2, float f3, float f4) {
        ColorSpace colorSpace = this.transformSource;
        long xy$ui_graphics_release = colorSpace.toXy$ui_graphics_release(f, f2, f3);
        float intBitsToFloat = Float.intBitsToFloat((int) (xy$ui_graphics_release >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (xy$ui_graphics_release & 4294967295L));
        float z$ui_graphics_release = colorSpace.toZ$ui_graphics_release(f, f2, f3);
        float[] fArr = this.transform;
        if (fArr != null) {
            intBitsToFloat *= fArr[0];
            intBitsToFloat2 *= fArr[1];
            z$ui_graphics_release *= fArr[2];
        }
        float f5 = intBitsToFloat2;
        float f6 = intBitsToFloat;
        return this.transformDestination.mo133xyzaToColorJlNiLsg$ui_graphics_release(f6, f5, z$ui_graphics_release, f4, this.destination);
    }

    public Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, float[] fArr) {
        ResultKt.checkNotNullParameter(colorSpace, "source");
        ResultKt.checkNotNullParameter(colorSpace2, "destination");
        ResultKt.checkNotNullParameter(colorSpace4, "transformDestination");
        this.destination = colorSpace2;
        this.transformSource = colorSpace3;
        this.transformDestination = colorSpace4;
        this.transform = fArr;
    }
}
