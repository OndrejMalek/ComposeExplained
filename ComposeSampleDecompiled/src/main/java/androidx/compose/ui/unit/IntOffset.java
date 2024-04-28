package androidx.compose.ui.unit;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class IntOffset {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Zero = ResultKt.IntOffset(0, 0);
    public final long packedValue;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.animation.core.VectorConvertersKt$DpToVector$1.invoke(java.lang.Object):java.lang.Object, androidx.compose.ui.ComposedModifierKt$materialize$result$1.invoke(java.lang.Object, java.lang.Object):java.lang.Object] */
    public /* synthetic */ IntOffset(long j) {
        this.packedValue = j;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof IntOffset) {
            return this.packedValue == ((IntOffset) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        long j = this.packedValue;
        sb.append((int) (j >> 32));
        sb.append(", ");
        sb.append((int) (j & 4294967295L));
        sb.append(')');
        return sb.toString();
    }
}
