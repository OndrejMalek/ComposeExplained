package androidx.compose.ui.geometry;

import _COROUTINE._BOUNDARY;

/* loaded from: classes.dex */
public final class Size {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final long packedValue;
    public static final long Zero = _BOUNDARY.Size(0.0f, 0.0f);
    public static final long Unspecified = _BOUNDARY.Size(Float.NaN, Float.NaN);

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.animation.core.VectorConvertersKt$DpToVector$1.invoke(java.lang.Object):java.lang.Object, androidx.compose.ui.text.platform.style.ShaderBrushSpan.updateDrawState(android.text.TextPaint):void] */
    public /* synthetic */ Size(long j) {
        this.packedValue = j;
    }

    /* renamed from: getHeight-impl, reason: not valid java name */
    public static final float m83getHeightimpl(long j) {
        if (j != Unspecified) {
            return Float.intBitsToFloat((int) (j & 4294967295L));
        }
        throw new IllegalStateException("Size is unspecified".toString());
    }

    /* renamed from: getMinDimension-impl, reason: not valid java name */
    public static final float m84getMinDimensionimpl(long j) {
        return Math.min(Math.abs(m85getWidthimpl(j)), Math.abs(m83getHeightimpl(j)));
    }

    /* renamed from: getWidth-impl, reason: not valid java name */
    public static final float m85getWidthimpl(long j) {
        if (j != Unspecified) {
            return Float.intBitsToFloat((int) (j >> 32));
        }
        throw new IllegalStateException("Size is unspecified".toString());
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m86toStringimpl(long j) {
        if (j == Unspecified) {
            return "Size.Unspecified";
        }
        return "Size(" + _BOUNDARY.toStringAsFixed(m85getWidthimpl(j)) + ", " + _BOUNDARY.toStringAsFixed(m83getHeightimpl(j)) + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Size) {
            return this.packedValue == ((Size) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m86toStringimpl(this.packedValue);
    }
}
