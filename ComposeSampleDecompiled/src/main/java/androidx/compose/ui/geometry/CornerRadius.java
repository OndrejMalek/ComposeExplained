package androidx.compose.ui.geometry;

import _COROUTINE._BOUNDARY;

/* loaded from: classes.dex */
public abstract class CornerRadius {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Zero = _BOUNDARY.CornerRadius(0.0f, 0.0f);

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m71equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getX-impl, reason: not valid java name */
    public static final float m72getXimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* renamed from: getY-impl, reason: not valid java name */
    public static final float m73getYimpl(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m74toStringimpl(long j) {
        if (m72getXimpl(j) == m73getYimpl(j)) {
            return "CornerRadius.circular(" + _BOUNDARY.toStringAsFixed(m72getXimpl(j)) + ')';
        }
        return "CornerRadius.elliptical(" + _BOUNDARY.toStringAsFixed(m72getXimpl(j)) + ", " + _BOUNDARY.toStringAsFixed(m73getYimpl(j)) + ')';
    }
}
