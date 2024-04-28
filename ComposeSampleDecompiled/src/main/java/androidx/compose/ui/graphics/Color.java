package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Color {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Black = Brush.Color(4278190080L);
    public static final long Blue;
    public static final long Red;
    public static final long Transparent;
    public static final long Unspecified;
    public final long value;

    static {
        Brush.Color(4282664004L);
        Brush.Color(4287137928L);
        Brush.Color(4291611852L);
        Brush.Color(4294967295L);
        Red = Brush.Color(4294901760L);
        Brush.Color(4278255360L);
        Blue = Brush.Color(4278190335L);
        Brush.Color(4294967040L);
        Brush.Color(4278255615L);
        Brush.Color(4294902015L);
        Transparent = 0 << 32;
        Unspecified = Brush.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.Unspecified);
    }

    /* JADX DEBUG: Method not inlined, still used in: [_COROUTINE._BOUNDARY.Button(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ButtonColors, androidx.compose.material3.ButtonElevation, androidx.compose.foundation.layout.PaddingValuesImpl, androidx.compose.foundation.interaction.MutableInteractionSourceImpl, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void, kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(long, androidx.compose.runtime.StructuralEqualityPolicy):androidx.compose.runtime.ParcelableSnapshotMutableState] */
    public /* synthetic */ Color(long j) {
        this.value = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* renamed from: convert-vNxB06k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long m119convertvNxB06k(long r7, androidx.compose.ui.graphics.colorspace.ColorSpace r9) {
        /*
            java.lang.String r0 = "colorSpace"
            kotlin.ResultKt.checkNotNullParameter(r9, r0)
            androidx.compose.ui.graphics.colorspace.ColorSpace r0 = m124getColorSpaceimpl(r7)
            boolean r1 = kotlin.ResultKt.areEqual(r9, r0)
            if (r1 == 0) goto L10
            return r7
        L10:
            java.lang.String r1 = "$this$connect"
            kotlin.ResultKt.checkNotNullParameter(r0, r1)
            androidx.compose.ui.graphics.colorspace.Rgb r1 = androidx.compose.ui.graphics.colorspace.ColorSpaces.Srgb
            if (r0 != r1) goto L25
            if (r9 != r1) goto L1e
            androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1 r9 = androidx.compose.ui.graphics.colorspace.Connector.SrgbIdentity
            goto L5d
        L1e:
            androidx.compose.ui.graphics.colorspace.Oklab r1 = androidx.compose.ui.graphics.colorspace.ColorSpaces.Oklab
            if (r9 != r1) goto L2e
            androidx.compose.ui.graphics.colorspace.Connector r9 = androidx.compose.ui.graphics.colorspace.Connector.SrgbToOklabPerceptual
            goto L5d
        L25:
            androidx.compose.ui.graphics.colorspace.Oklab r2 = androidx.compose.ui.graphics.colorspace.ColorSpaces.Oklab
            if (r0 != r2) goto L2e
            if (r9 != r1) goto L2e
            androidx.compose.ui.graphics.colorspace.Connector r9 = androidx.compose.ui.graphics.colorspace.Connector.OklabToSrgbPerceptual
            goto L5d
        L2e:
            if (r0 != r9) goto L39
            androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1 r9 = androidx.compose.ui.graphics.colorspace.Connector.SrgbIdentity
            androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1 r9 = new androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1
            r1 = 1
            r9.<init>(r0, r0, r1)
            goto L5d
        L39:
            long r1 = androidx.compose.ui.graphics.colorspace.ColorModel.Rgb
            long r3 = r0.model
            boolean r3 = androidx.compose.ui.graphics.colorspace.ColorModel.m131equalsimpl0(r3, r1)
            r4 = 0
            if (r3 == 0) goto L57
            long r5 = r9.model
            boolean r1 = androidx.compose.ui.graphics.colorspace.ColorModel.m131equalsimpl0(r5, r1)
            if (r1 == 0) goto L57
            androidx.compose.ui.graphics.colorspace.Connector$RgbConnector r1 = new androidx.compose.ui.graphics.colorspace.Connector$RgbConnector
            androidx.compose.ui.graphics.colorspace.Rgb r0 = (androidx.compose.ui.graphics.colorspace.Rgb) r0
            androidx.compose.ui.graphics.colorspace.Rgb r9 = (androidx.compose.ui.graphics.colorspace.Rgb) r9
            r1.<init>(r0, r9, r4)
        L55:
            r9 = r1
            goto L5d
        L57:
            androidx.compose.ui.graphics.colorspace.Connector r1 = new androidx.compose.ui.graphics.colorspace.Connector
            r1.<init>(r0, r9, r4)
            goto L55
        L5d:
            float r0 = m126getRedimpl(r7)
            float r1 = m125getGreenimpl(r7)
            float r2 = m123getBlueimpl(r7)
            float r7 = m122getAlphaimpl(r7)
            long r7 = r9.mo134transformToColorwmQWz5c$ui_graphics_release(r0, r1, r2, r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.Color.m119convertvNxB06k(long, androidx.compose.ui.graphics.colorspace.ColorSpace):long");
    }

    /* JADX DEBUG: Method not inlined, still used in: [_COROUTINE._BOUNDARY.Button(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ButtonColors, androidx.compose.material3.ButtonElevation, androidx.compose.foundation.layout.PaddingValuesImpl, androidx.compose.foundation.interaction.MutableInteractionSourceImpl, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void, androidx.compose.ui.graphics.drawscope.CanvasDrawScope.configurePaint-2qPWKa0$default(androidx.compose.ui.graphics.drawscope.CanvasDrawScope, long, androidx.compose.ui.graphics.drawscope.DrawStyle, float, androidx.compose.ui.graphics.Brush, int):androidx.compose.ui.graphics.AndroidPaint] */
    /* renamed from: copy-wmQWz5c$default */
    public static long m120copywmQWz5c$default(long j, float f) {
        return Brush.Color(m126getRedimpl(j), m125getGreenimpl(j), m123getBlueimpl(j), f, m124getColorSpaceimpl(j));
    }

    /* renamed from: equals-impl0 */
    public static final boolean m121equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getAlpha-impl */
    public static final float m122getAlphaimpl(long j) {
        float ulongToDouble;
        float f;
        if ((63 & j) == 0) {
            ulongToDouble = (float) ResultKt.ulongToDouble((j >>> 56) & 255);
            f = 255.0f;
        } else {
            ulongToDouble = (float) ResultKt.ulongToDouble((j >>> 6) & 1023);
            f = 1023.0f;
        }
        return ulongToDouble / f;
    }

    /* renamed from: getBlue-impl */
    public static final float m123getBlueimpl(long j) {
        return (63 & j) == 0 ? ((float) ResultKt.ulongToDouble((j >>> 32) & 255)) / 255.0f : Float16.m129toFloatimpl((short) ((j >>> 16) & 65535));
    }

    /* renamed from: getColorSpace-impl */
    public static final ColorSpace m124getColorSpaceimpl(long j) {
        float[] fArr = ColorSpaces.SrgbPrimaries;
        return ColorSpaces.ColorSpacesArray[(int) (j & 63)];
    }

    /* renamed from: getGreen-impl */
    public static final float m125getGreenimpl(long j) {
        return (63 & j) == 0 ? ((float) ResultKt.ulongToDouble((j >>> 40) & 255)) / 255.0f : Float16.m129toFloatimpl((short) ((j >>> 32) & 65535));
    }

    /* renamed from: getRed-impl */
    public static final float m126getRedimpl(long j) {
        return (63 & j) == 0 ? ((float) ResultKt.ulongToDouble((j >>> 48) & 255)) / 255.0f : Float16.m129toFloatimpl((short) ((j >>> 48) & 65535));
    }

    /* renamed from: toString-impl */
    public static String m127toStringimpl(long j) {
        return "Color(" + m126getRedimpl(j) + ", " + m125getGreenimpl(j) + ", " + m123getBlueimpl(j) + ", " + m122getAlphaimpl(j) + ", " + m124getColorSpaceimpl(j).name + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Color) {
            return this.value == ((Color) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m127toStringimpl(this.value);
    }
}
