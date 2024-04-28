package androidx.compose.ui.unit;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextUnit {
    public static final TextUnitType[] TextUnitTypes = {new TextUnitType(0), new TextUnitType(4294967296L), new TextUnitType(8589934592L)};
    public static final long Unspecified = ResultKt.pack(Float.NaN, 0);
    public final long packedValue;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m283equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getType-UIouoOA, reason: not valid java name */
    public static final long m284getTypeUIouoOA(long j) {
        return TextUnitTypes[(int) ((j & 1095216660480L) >>> 32)].type;
    }

    /* renamed from: getValue-impl, reason: not valid java name */
    public static final float m285getValueimpl(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m286toStringimpl(long j) {
        long m284getTypeUIouoOA = m284getTypeUIouoOA(j);
        if (TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 0L)) {
            return "Unspecified";
        }
        if (TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 4294967296L)) {
            return m285getValueimpl(j) + ".sp";
        }
        if (!TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 8589934592L)) {
            return "Invalid";
        }
        return m285getValueimpl(j) + ".em";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextUnit) {
            return this.packedValue == ((TextUnit) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m286toStringimpl(this.packedValue);
    }
}
