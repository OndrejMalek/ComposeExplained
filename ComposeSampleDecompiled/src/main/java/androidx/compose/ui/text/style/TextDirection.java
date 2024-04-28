package androidx.compose.ui.text.style;

/* loaded from: classes.dex */
public final class TextDirection {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m271equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextDirection) {
            return this.value == ((TextDirection) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return m271equalsimpl0(i, 1) ? "Ltr" : m271equalsimpl0(i, 2) ? "Rtl" : m271equalsimpl0(i, 3) ? "Content" : m271equalsimpl0(i, 4) ? "ContentOrLtr" : m271equalsimpl0(i, 5) ? "ContentOrRtl" : "Invalid";
    }
}
