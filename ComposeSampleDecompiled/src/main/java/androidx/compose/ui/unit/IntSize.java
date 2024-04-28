package androidx.compose.ui.unit;

/* loaded from: classes.dex */
public final class IntSize {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final long packedValue;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m282toStringimpl(long j) {
        return ((int) (j >> 32)) + " x " + ((int) (j & 4294967295L));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof IntSize) {
            return this.packedValue == ((IntSize) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m282toStringimpl(this.packedValue);
    }
}
