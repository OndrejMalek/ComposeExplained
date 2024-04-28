package androidx.compose.ui.input.pointer;

/* loaded from: classes.dex */
public final class PointerId {
    public final long value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m156equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m157toStringimpl(long j) {
        return "PointerId(value=" + j + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof PointerId) {
            return this.value == ((PointerId) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m157toStringimpl(this.value);
    }
}
