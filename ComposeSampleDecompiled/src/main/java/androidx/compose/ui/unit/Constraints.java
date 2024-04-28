package androidx.compose.ui.unit;

import androidx.compose.ui.draw.DrawResult;

/* loaded from: classes.dex */
public final class Constraints {
    public final long value;
    public static final int[] MinHeightOffsets = {18, 20, 17, 15};
    public static final int[] WidthMask = {65535, 262143, 32767, 8191};
    public static final int[] HeightMask = {32767, 8191, 65535, 262143};

    /* renamed from: copy-Zbe2FdA$default, reason: not valid java name */
    public static long m272copyZbe2FdA$default(long j) {
        int m276getMaxWidthimpl = m276getMaxWidthimpl(j);
        int m275getMaxHeightimpl = m275getMaxHeightimpl(j);
        if (m276getMaxWidthimpl < 0 && m276getMaxWidthimpl != Integer.MAX_VALUE) {
            throw new IllegalArgumentException(("maxWidth(" + m276getMaxWidthimpl + ") must be >= minWidth(0)").toString());
        }
        if (m275getMaxHeightimpl >= 0 || m275getMaxHeightimpl == Integer.MAX_VALUE) {
            return DrawResult.m65createConstraintsZbe2FdA$ui_unit_release(0, m276getMaxWidthimpl, 0, m275getMaxHeightimpl);
        }
        throw new IllegalArgumentException(("maxHeight(" + m275getMaxHeightimpl + ") must be >= minHeight(0)").toString());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m273equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getHasBoundedHeight-impl, reason: not valid java name */
    public static final boolean m274getHasBoundedHeightimpl(long j) {
        int i = (int) (3 & j);
        return (((int) (j >> (MinHeightOffsets[i] + 31))) & HeightMask[i]) != 0;
    }

    /* renamed from: getMaxHeight-impl, reason: not valid java name */
    public static final int m275getMaxHeightimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((int) (j >> (MinHeightOffsets[i] + 31))) & HeightMask[i];
        if (i2 == 0) {
            return Integer.MAX_VALUE;
        }
        return i2 - 1;
    }

    /* renamed from: getMaxWidth-impl, reason: not valid java name */
    public static final int m276getMaxWidthimpl(long j) {
        int i = ((int) (j >> 33)) & WidthMask[(int) (3 & j)];
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        return i - 1;
    }

    /* renamed from: getMinHeight-impl, reason: not valid java name */
    public static final int m277getMinHeightimpl(long j) {
        int i = (int) (3 & j);
        return ((int) (j >> MinHeightOffsets[i])) & HeightMask[i];
    }

    /* renamed from: getMinWidth-impl, reason: not valid java name */
    public static final int m278getMinWidthimpl(long j) {
        return ((int) (j >> 2)) & WidthMask[(int) (3 & j)];
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m279toStringimpl(long j) {
        int m276getMaxWidthimpl = m276getMaxWidthimpl(j);
        String valueOf = m276getMaxWidthimpl == Integer.MAX_VALUE ? "Infinity" : String.valueOf(m276getMaxWidthimpl);
        int m275getMaxHeightimpl = m275getMaxHeightimpl(j);
        return "Constraints(minWidth = " + m278getMinWidthimpl(j) + ", maxWidth = " + valueOf + ", minHeight = " + m277getMinHeightimpl(j) + ", maxHeight = " + (m275getMaxHeightimpl != Integer.MAX_VALUE ? String.valueOf(m275getMaxHeightimpl) : "Infinity") + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Constraints) {
            return this.value == ((Constraints) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m279toStringimpl(this.value);
    }
}
