package androidx.compose.ui.unit;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DpOffset {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Unspecified;
    public final long packedValue;

    static {
        float f = 0;
        ResultKt.m293DpOffsetYgX7TsA(f, f);
        Unspecified = ResultKt.m293DpOffsetYgX7TsA(Float.NaN, Float.NaN);
    }

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.animation.core.VectorConvertersKt$DpToVector$1.invoke(java.lang.Object):java.lang.Object] */
    public /* synthetic */ DpOffset(long j) {
        this.packedValue = j;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof DpOffset) {
            return this.packedValue == ((DpOffset) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        long j = this.packedValue;
        long j2 = Unspecified;
        if (j == j2) {
            return "DpOffset.Unspecified";
        }
        StringBuilder sb = new StringBuilder("(");
        if (j == j2) {
            throw new IllegalStateException("DpOffset is unspecified".toString());
        }
        sb.append((Object) Dp.m281toStringimpl(Float.intBitsToFloat((int) (j >> 32))));
        sb.append(", ");
        if (j == j2) {
            throw new IllegalStateException("DpOffset is unspecified".toString());
        }
        sb.append((Object) Dp.m281toStringimpl(Float.intBitsToFloat((int) (j & 4294967295L))));
        sb.append(')');
        return sb.toString();
    }
}
