package androidx.compose.ui.text.font;

/* loaded from: classes.dex */
public final class FontStyle {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m258equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FontStyle) {
            return this.value == ((FontStyle) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return m258equalsimpl0(i, 0) ? "Normal" : m258equalsimpl0(i, 1) ? "Italic" : "Invalid";
    }
}
