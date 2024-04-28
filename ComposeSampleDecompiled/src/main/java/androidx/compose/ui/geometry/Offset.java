package androidx.compose.ui.geometry;

import _COROUTINE._BOUNDARY;

/* loaded from: classes.dex */
public final class Offset {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final long packedValue;
    public static final long Zero = _BOUNDARY.Offset(0.0f, 0.0f);
    public static final long Infinite = _BOUNDARY.Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    public static final long Unspecified = _BOUNDARY.Offset(Float.NaN, Float.NaN);

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m75equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getX-impl, reason: not valid java name */
    public static final float m76getXimpl(long j) {
        if (j != Unspecified) {
            return Float.intBitsToFloat((int) (j >> 32));
        }
        throw new IllegalStateException("Offset is unspecified".toString());
    }

    /* renamed from: getY-impl, reason: not valid java name */
    public static final float m77getYimpl(long j) {
        if (j != Unspecified) {
            return Float.intBitsToFloat((int) (j & 4294967295L));
        }
        throw new IllegalStateException("Offset is unspecified".toString());
    }

    /* renamed from: minus-MK-Hz9U, reason: not valid java name */
    public static final long m78minusMKHz9U(long j, long j2) {
        return _BOUNDARY.Offset(m76getXimpl(j) - m76getXimpl(j2), m77getYimpl(j) - m77getYimpl(j2));
    }

    /* renamed from: plus-MK-Hz9U, reason: not valid java name */
    public static final long m79plusMKHz9U(long j, long j2) {
        return _BOUNDARY.Offset(m76getXimpl(j2) + m76getXimpl(j), m77getYimpl(j2) + m77getYimpl(j));
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m80toStringimpl(long j) {
        if (j == Unspecified) {
            return "Offset.Unspecified";
        }
        return "Offset(" + _BOUNDARY.toStringAsFixed(m76getXimpl(j)) + ", " + _BOUNDARY.toStringAsFixed(m77getYimpl(j)) + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Offset) {
            return this.packedValue == ((Offset) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m80toStringimpl(this.packedValue);
    }
}
