package androidx.compose.ui.text.font;

/* loaded from: classes.dex */
public final class FontSynthesis {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m259equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m260toStringimpl(int i) {
        return m259equalsimpl0(i, 0) ? "None" : m259equalsimpl0(i, 1) ? "All" : m259equalsimpl0(i, 2) ? "Weight" : m259equalsimpl0(i, 3) ? "Style" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FontSynthesis) {
            return this.value == ((FontSynthesis) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m260toStringimpl(this.value);
    }
}
