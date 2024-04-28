package androidx.compose.ui.graphics.colorspace;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.graphics.Brush;
import androidx.core.view.ViewCompat$$ExternalSyntheticLambda0;
import java.util.Arrays;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Rgb extends ColorSpace {
    public static final ViewCompat$$ExternalSyntheticLambda0 DoubleIdentity = new ViewCompat$$ExternalSyntheticLambda0(3);
    public final Rgb$$ExternalSyntheticLambda0 eotfFunc;
    public final DoubleFunction eotfOrig;
    public final float[] inverseTransform;
    public final boolean isSrgb;
    public final float max;
    public final float min;
    public final Rgb$$ExternalSyntheticLambda0 oetfFunc;
    public final DoubleFunction oetfOrig;
    public final float[] primaries;
    public final TransferParameters transferParameters;
    public final float[] transform;
    public final WhitePoint whitePoint;

    /* JADX DEBUG: Multi-variable search result rejected for r11v4, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r11v5, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r11v8, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r11v9, resolved type: boolean */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0223, code lost:
    
        if (androidx.compose.ui.draw.DrawResult.cross(r4[4] - r4[0], r4[5] - r4[1], r10[4], r10[5]) >= 0.0f) goto L42;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0232  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Rgb(java.lang.String r32, float[] r33, androidx.compose.ui.graphics.colorspace.WhitePoint r34, float[] r35, androidx.compose.ui.graphics.colorspace.DoubleFunction r36, androidx.compose.ui.graphics.colorspace.DoubleFunction r37, float r38, float r39, androidx.compose.ui.graphics.colorspace.TransferParameters r40, int r41) {
        /*
            r31 = this;
            r0 = r31
            r1 = r32
            r2 = r33
            r3 = r34
            r4 = r35
            r5 = r36
            r6 = r37
            r7 = r38
            r8 = r39
            r9 = r41
            r12 = 9
            r16 = 5
            r10 = 6
            r11 = 0
            r15 = 1
            java.lang.String r14 = "name"
            kotlin.ResultKt.checkNotNullParameter(r1, r14)
            java.lang.String r14 = "primaries"
            kotlin.ResultKt.checkNotNullParameter(r2, r14)
            java.lang.String r14 = "oetf"
            kotlin.ResultKt.checkNotNullParameter(r5, r14)
            java.lang.String r14 = "eotf"
            kotlin.ResultKt.checkNotNullParameter(r6, r14)
            long r13 = androidx.compose.ui.graphics.colorspace.ColorModel.Rgb
            r0.<init>(r1, r13, r9)
            r0.whitePoint = r3
            r0.min = r7
            r0.max = r8
            r1 = r40
            r0.transferParameters = r1
            r0.oetfOrig = r5
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda0 r1 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda0
            r1.<init>(r0, r11)
            r0.oetfFunc = r1
            r0.eotfOrig = r6
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda0 r1 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda0
            r1.<init>(r0, r15)
            r0.eotfFunc = r1
            int r1 = r2.length
            if (r1 == r10) goto L5f
            int r1 = r2.length
            if (r1 != r12) goto L57
            goto L5f
        L57:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ"
            r1.<init>(r2)
            throw r1
        L5f:
            int r1 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r1 >= 0) goto L2d3
            float[] r1 = new float[r10]
            int r13 = r2.length
            if (r13 != r12) goto La8
            r13 = r2[r11]
            r14 = r2[r15]
            float r22 = r13 + r14
            r21 = 2
            r23 = r2[r21]
            float r22 = r22 + r23
            float r13 = r13 / r22
            r1[r11] = r13
            float r14 = r14 / r22
            r1[r15] = r14
            r13 = 3
            r14 = r2[r13]
            r19 = 4
            r20 = r2[r19]
            float r22 = r14 + r20
            r23 = r2[r16]
            float r22 = r22 + r23
            float r14 = r14 / r22
            r21 = 2
            r1[r21] = r14
            float r20 = r20 / r22
            r1[r13] = r20
            r13 = r2[r10]
            r14 = 7
            r22 = r2[r14]
            float r14 = r13 + r22
            r17 = 8
            r2 = r2[r17]
            float r14 = r14 + r2
            float r13 = r13 / r14
            r2 = 4
            r1[r2] = r13
            float r22 = r22 / r14
            r1[r16] = r22
            goto Lab
        La8:
            java.lang.System.arraycopy(r2, r11, r1, r11, r10)
        Lab:
            r0.primaries = r1
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r4 != 0) goto L139
            r4 = r1[r11]
            r13 = r1[r15]
            r14 = 2
            r22 = r1[r14]
            r14 = 3
            r23 = r1[r14]
            r14 = 4
            r24 = r1[r14]
            r14 = r1[r16]
            float r10 = (float) r15
            float r25 = r10 - r4
            float r25 = r25 / r13
            float r26 = r10 - r22
            float r26 = r26 / r23
            float r27 = r10 - r24
            float r27 = r27 / r14
            float r15 = r3.x
            float r10 = r10 - r15
            float r11 = r3.y
            float r10 = r10 / r11
            float r28 = r4 / r13
            float r29 = r22 / r23
            float r30 = r24 / r14
            float r15 = r15 / r11
            float r10 = r10 - r25
            float r29 = r29 - r28
            float r10 = r10 * r29
            float r15 = r15 - r28
            float r26 = r26 - r25
            float r11 = r15 * r26
            float r10 = r10 - r11
            float r27 = r27 - r25
            float r27 = r27 * r29
            float r30 = r30 - r28
            float r26 = r26 * r30
            float r27 = r27 - r26
            float r10 = r10 / r27
            float r30 = r30 * r10
            float r15 = r15 - r30
            float r15 = r15 / r29
            float r11 = r2 - r15
            float r11 = r11 - r10
            float r25 = r11 / r13
            float r26 = r15 / r23
            float r27 = r10 / r14
            float r28 = r25 * r4
            float r4 = r2 - r4
            float r4 = r4 - r13
            float r4 = r4 * r25
            float r13 = r26 * r22
            float r22 = r2 - r22
            float r22 = r22 - r23
            float r22 = r22 * r26
            float r23 = r27 * r24
            float r24 = r2 - r24
            float r24 = r24 - r14
            float r24 = r24 * r27
            float[] r12 = new float[r12]
            r14 = 0
            r12[r14] = r28
            r14 = 1
            r12[r14] = r11
            r11 = 2
            r12[r11] = r4
            r4 = 3
            r12[r4] = r13
            r4 = 4
            r12[r4] = r15
            r12[r16] = r22
            r4 = 6
            r12[r4] = r23
            r4 = 7
            r12[r4] = r10
            r4 = 8
            r12[r4] = r24
            r0.transform = r12
            goto L13e
        L139:
            int r10 = r4.length
            if (r10 != r12) goto L2be
            r0.transform = r4
        L13e:
            float[] r4 = r0.transform
            float[] r4 = _COROUTINE._BOUNDARY.inverse3x3(r4)
            r0.inverseTransform = r4
            float r4 = androidx.compose.ui.draw.DrawResult.area(r1)
            float[] r10 = androidx.compose.ui.graphics.colorspace.ColorSpaces.SrgbPrimaries
            float[] r10 = androidx.compose.ui.graphics.colorspace.ColorSpaces.Ntsc1953Primaries
            float r10 = androidx.compose.ui.draw.DrawResult.area(r10)
            float r4 = r4 / r10
            r10 = 1063675494(0x3f666666, float:0.9)
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 <= 0) goto L229
            float[] r4 = androidx.compose.ui.graphics.colorspace.ColorSpaces.SrgbPrimaries
            r11 = 0
            r12 = r1[r11]
            r13 = r4[r11]
            float r12 = r12 - r13
            r11 = 1
            r14 = r1[r11]
            r15 = r4[r11]
            float r14 = r14 - r15
            r11 = 2
            r17 = r1[r11]
            r18 = r4[r11]
            float r17 = r17 - r18
            r11 = 3
            r18 = r1[r11]
            r22 = r4[r11]
            float r18 = r18 - r22
            r11 = 4
            r19 = r1[r11]
            r22 = r4[r11]
            float r19 = r19 - r22
            r23 = r1[r16]
            r24 = r4[r16]
            float r23 = r23 - r24
            r2 = 6
            float[] r10 = new float[r2]
            r2 = 0
            r10[r2] = r12
            r12 = 1
            r10[r12] = r14
            r14 = 2
            r10[r14] = r17
            r14 = 3
            r10[r14] = r18
            r10[r11] = r19
            r10[r16] = r23
            r11 = r10[r2]
            r14 = r10[r12]
            float r13 = r13 - r22
            float r15 = r15 - r24
            float r11 = androidx.compose.ui.draw.DrawResult.cross(r11, r14, r13, r15)
            r13 = 0
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 < 0) goto L226
            r11 = r4[r2]
            r14 = 2
            r15 = r4[r14]
            float r11 = r11 - r15
            r15 = r4[r12]
            r17 = 3
            r18 = r4[r17]
            float r15 = r15 - r18
            r14 = r10[r2]
            r2 = r10[r12]
            float r2 = androidx.compose.ui.draw.DrawResult.cross(r11, r15, r14, r2)
            int r2 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r2 >= 0) goto L1c2
            goto L229
        L1c2:
            r2 = 2
            r11 = r10[r2]
            r13 = r10[r17]
            r14 = r4[r2]
            r15 = 0
            r18 = r4[r15]
            float r14 = r14 - r18
            r15 = r4[r17]
            r18 = r4[r12]
            float r15 = r15 - r18
            float r11 = androidx.compose.ui.draw.DrawResult.cross(r11, r13, r14, r15)
            r12 = 0
            int r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r11 < 0) goto L229
            r11 = r4[r2]
            r13 = 4
            r14 = r4[r13]
            float r11 = r11 - r14
            r14 = r4[r17]
            r15 = r4[r16]
            float r14 = r14 - r15
            r15 = r10[r2]
            r2 = r10[r17]
            float r2 = androidx.compose.ui.draw.DrawResult.cross(r11, r14, r15, r2)
            int r2 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r2 >= 0) goto L1f5
            goto L229
        L1f5:
            r2 = r10[r13]
            r11 = r10[r16]
            r12 = r4[r13]
            r14 = 2
            r14 = r4[r14]
            float r12 = r12 - r14
            r14 = r4[r16]
            r15 = r4[r17]
            float r14 = r14 - r15
            float r2 = androidx.compose.ui.draw.DrawResult.cross(r2, r11, r12, r14)
            r11 = 0
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 < 0) goto L229
            r2 = r4[r13]
            r14 = 0
            r12 = r4[r14]
            float r2 = r2 - r12
            r12 = r4[r16]
            r15 = 1
            r4 = r4[r15]
            float r12 = r12 - r4
            r4 = r10[r13]
            r10 = r10[r16]
            float r2 = androidx.compose.ui.draw.DrawResult.cross(r2, r12, r4, r10)
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 < 0) goto L227
            goto L22d
        L226:
            r14 = r2
        L227:
            r2 = 0
            goto L22b
        L229:
            r14 = 0
            goto L227
        L22b:
            int r4 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
        L22d:
            if (r9 != 0) goto L232
            r11 = 1
            goto L2bb
        L232:
            float[] r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.SrgbPrimaries
            if (r1 != r2) goto L238
        L236:
            r10 = 1
            goto L25c
        L238:
            r4 = r14
            r9 = 6
        L23a:
            if (r4 >= r9) goto L236
            r10 = r1[r4]
            r11 = r2[r4]
            int r10 = java.lang.Float.compare(r10, r11)
            if (r10 == 0) goto L259
            r10 = r1[r4]
            r11 = r2[r4]
            float r10 = r10 - r11
            float r10 = java.lang.Math.abs(r10)
            r11 = 981668463(0x3a83126f, float:0.001)
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L259
        L256:
            r11 = r14
            goto L2bb
        L259:
            r10 = 1
            int r4 = r4 + r10
            goto L23a
        L25c:
            androidx.compose.ui.graphics.colorspace.WhitePoint r1 = androidx.compose.ui.graphics.colorspace.Illuminant.D65
            boolean r1 = _COROUTINE._BOUNDARY.compare(r3, r1)
            if (r1 != 0) goto L265
            goto L256
        L265:
            r1 = 0
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L256
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r1 != 0) goto L256
            float[] r1 = androidx.compose.ui.graphics.colorspace.ColorSpaces.SrgbPrimaries
            androidx.compose.ui.graphics.colorspace.Rgb r1 = androidx.compose.ui.graphics.colorspace.ColorSpaces.Srgb
            r2 = 0
        L276:
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 > 0) goto L2ba
            androidx.compose.ui.graphics.colorspace.DoubleFunction r4 = r1.oetfOrig
            double r7 = r5.invoke(r2)
            double r11 = r4.invoke(r2)
            double r7 = r7 - r11
            double r7 = java.lang.Math.abs(r7)
            r11 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            int r4 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r4 > 0) goto L296
            r4 = r10
            goto L297
        L296:
            r4 = r14
        L297:
            if (r4 != 0) goto L29a
            goto L256
        L29a:
            androidx.compose.ui.graphics.colorspace.DoubleFunction r4 = r1.eotfOrig
            double r7 = r6.invoke(r2)
            double r15 = r4.invoke(r2)
            double r7 = r7 - r15
            double r7 = java.lang.Math.abs(r7)
            int r4 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r4 > 0) goto L2af
            r4 = r10
            goto L2b0
        L2af:
            r4 = r14
        L2b0:
            if (r4 != 0) goto L2b3
            goto L256
        L2b3:
            r7 = 4571171282956062736(0x3f70101010101010, double:0.00392156862745098)
            double r2 = r2 + r7
            goto L276
        L2ba:
            r11 = r10
        L2bb:
            r0.isSrgb = r11
            return
        L2be:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Transform must have 9 entries! Has "
            r2.<init>(r3)
            int r3 = r4.length
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L2d3:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid range: min="
            r2.<init>(r3)
            r2.append(r7)
            java.lang.String r3 = ", max="
            r2.append(r3)
            r2.append(r8)
            java.lang.String r3 = "; min must be strictly < max"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, float[], androidx.compose.ui.graphics.colorspace.DoubleFunction, androidx.compose.ui.graphics.colorspace.DoubleFunction, float, float, androidx.compose.ui.graphics.colorspace.TransferParameters, int):void");
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Rgb.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        Rgb rgb = (Rgb) obj;
        if (Float.compare(rgb.min, this.min) != 0 || Float.compare(rgb.max, this.max) != 0 || !ResultKt.areEqual(this.whitePoint, rgb.whitePoint) || !Arrays.equals(this.primaries, rgb.primaries)) {
            return false;
        }
        TransferParameters transferParameters = rgb.transferParameters;
        TransferParameters transferParameters2 = this.transferParameters;
        if (transferParameters2 != null) {
            return ResultKt.areEqual(transferParameters2, transferParameters);
        }
        if (transferParameters == null) {
            return true;
        }
        if (ResultKt.areEqual(this.oetfOrig, rgb.oetfOrig)) {
            return ResultKt.areEqual(this.eotfOrig, rgb.eotfOrig);
        }
        return false;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        return this.max;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        return this.min;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final int hashCode() {
        int hashCode = (Arrays.hashCode(this.primaries) + ((this.whitePoint.hashCode() + (super.hashCode() * 31)) * 31)) * 31;
        float f = this.min;
        int floatToIntBits = (hashCode + (f == 0.0f ? 0 : Float.floatToIntBits(f))) * 31;
        float f2 = this.max;
        int floatToIntBits2 = (floatToIntBits + (f2 == 0.0f ? 0 : Float.floatToIntBits(f2))) * 31;
        TransferParameters transferParameters = this.transferParameters;
        int hashCode2 = floatToIntBits2 + (transferParameters != null ? transferParameters.hashCode() : 0);
        if (transferParameters == null) {
            return this.eotfOrig.hashCode() + ((this.oetfOrig.hashCode() + (hashCode2 * 31)) * 31);
        }
        return hashCode2;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final boolean isSrgb() {
        return this.isSrgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final long toXy$ui_graphics_release(float f, float f2, float f3) {
        double d = f;
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.eotfFunc;
        float invoke = (float) rgb$$ExternalSyntheticLambda0.invoke(d);
        float invoke2 = (float) rgb$$ExternalSyntheticLambda0.invoke(f2);
        float invoke3 = (float) rgb$$ExternalSyntheticLambda0.invoke(f3);
        float[] fArr = this.transform;
        float mul3x3Float3_0 = _BOUNDARY.mul3x3Float3_0(fArr, invoke, invoke2, invoke3);
        float mul3x3Float3_1 = _BOUNDARY.mul3x3Float3_1(fArr, invoke, invoke2, invoke3);
        return (Float.floatToIntBits(mul3x3Float3_0) << 32) | (Float.floatToIntBits(mul3x3Float3_1) & 4294967295L);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float toZ$ui_graphics_release(float f, float f2, float f3) {
        double d = f;
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.eotfFunc;
        return _BOUNDARY.mul3x3Float3_2(this.transform, (float) rgb$$ExternalSyntheticLambda0.invoke(d), (float) rgb$$ExternalSyntheticLambda0.invoke(f2), (float) rgb$$ExternalSyntheticLambda0.invoke(f3));
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public final long mo133xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace) {
        ResultKt.checkNotNullParameter(colorSpace, "colorSpace");
        float[] fArr = this.inverseTransform;
        float mul3x3Float3_0 = _BOUNDARY.mul3x3Float3_0(fArr, f, f2, f3);
        float mul3x3Float3_1 = _BOUNDARY.mul3x3Float3_1(fArr, f, f2, f3);
        float mul3x3Float3_2 = _BOUNDARY.mul3x3Float3_2(fArr, f, f2, f3);
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.oetfFunc;
        return Brush.Color((float) rgb$$ExternalSyntheticLambda0.invoke(mul3x3Float3_0), (float) rgb$$ExternalSyntheticLambda0.invoke(mul3x3Float3_1), (float) rgb$$ExternalSyntheticLambda0.invoke(mul3x3Float3_2), f4, colorSpace);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Rgb(java.lang.String r12, float[] r13, androidx.compose.ui.graphics.colorspace.WhitePoint r14, final androidx.compose.ui.graphics.colorspace.TransferParameters r15, int r16) {
        /*
            r11 = this;
            r9 = r15
            double r0 = r9.e
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            double r4 = r9.f
            if (r0 != 0) goto L17
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 != 0) goto L17
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r1 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r6 = 0
            r1.<init>()
        L15:
            r6 = r1
            goto L1e
        L17:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r1 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r6 = 1
            r1.<init>()
            goto L15
        L1e:
            if (r0 != 0) goto L2c
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 != 0) goto L2c
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r1 = 2
            r0.<init>()
        L2a:
            r7 = r0
            goto L33
        L2c:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2
            r1 = 3
            r0.<init>()
            goto L2a
        L33:
            r8 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            r10 = 0
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r6
            r6 = r7
            r7 = r10
            r9 = r15
            r10 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, androidx.compose.ui.graphics.colorspace.TransferParameters, int):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Rgb(java.lang.String r18, float[] r19, androidx.compose.ui.graphics.colorspace.WhitePoint r20, final double r21, float r23, float r24, int r25) {
        /*
            r17 = this;
            r1 = r21
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            androidx.core.view.ViewCompat$$ExternalSyntheticLambda0 r3 = androidx.compose.ui.graphics.colorspace.Rgb.DoubleIdentity
            if (r0 != 0) goto Lc
            r11 = r3
            goto L13
        Lc:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda1 r4 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda1
            r5 = 0
            r4.<init>()
            r11 = r4
        L13:
            if (r0 != 0) goto L17
        L15:
            r12 = r3
            goto L1e
        L17:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda1 r3 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda1
            r0 = 1
            r3.<init>()
            goto L15
        L1e:
            androidx.compose.ui.graphics.colorspace.TransferParameters r15 = new androidx.compose.ui.graphics.colorspace.TransferParameters
            r7 = 0
            r9 = 0
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5 = 0
            r0 = r15
            r1 = r21
            r0.<init>(r1, r3, r5, r7, r9)
            r10 = 0
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r20
            r13 = r23
            r14 = r24
            r16 = r25
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, double, float, float, int):void");
    }
}
