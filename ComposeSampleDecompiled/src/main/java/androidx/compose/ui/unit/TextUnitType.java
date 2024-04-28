package androidx.compose.ui.unit;

/* loaded from: classes.dex */
public final class TextUnitType {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final long type;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m287equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextUnitType) {
            return this.type == ((TextUnitType) obj).type;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.type);
    }

    public final String toString() {
        long j = this.type;
        return m287equalsimpl0(j, 0L) ? "Unspecified" : m287equalsimpl0(j, 4294967296L) ? "Sp" : m287equalsimpl0(j, 8589934592L) ? "Em" : "Invalid";
    }
}
