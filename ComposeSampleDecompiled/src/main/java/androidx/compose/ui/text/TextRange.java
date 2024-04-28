package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;

/* loaded from: classes.dex */
public final class TextRange {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Zero = _BOUNDARY.TextRange(0, 0);
    public final long packedValue;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.ui.text.input.TextFieldValue.<init>(androidx.compose.ui.text.AnnotatedString, long, androidx.compose.ui.text.TextRange):void] */
    public /* synthetic */ TextRange(long j) {
        this.packedValue = j;
    }

    /* renamed from: getMax-impl, reason: not valid java name */
    public static final int m246getMaximpl(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        return i > i2 ? i : i2;
    }

    /* renamed from: getMin-impl, reason: not valid java name */
    public static final int m247getMinimpl(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        return i > i2 ? i2 : i;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m248toStringimpl(long j) {
        return "TextRange(" + ((int) (j >> 32)) + ", " + ((int) (j & 4294967295L)) + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextRange) {
            return this.packedValue == ((TextRange) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m248toStringimpl(this.packedValue);
    }
}
