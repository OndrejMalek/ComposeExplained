package androidx.compose.ui.text.input;

/* loaded from: classes.dex */
public final class ImeAction {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m261equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m262toStringimpl(int i) {
        return m261equalsimpl0(i, 0) ? "None" : m261equalsimpl0(i, 1) ? "Default" : m261equalsimpl0(i, 2) ? "Go" : m261equalsimpl0(i, 3) ? "Search" : m261equalsimpl0(i, 4) ? "Send" : m261equalsimpl0(i, 5) ? "Previous" : m261equalsimpl0(i, 6) ? "Next" : m261equalsimpl0(i, 7) ? "Done" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ImeAction) {
            return this.value == ((ImeAction) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m262toStringimpl(this.value);
    }
}
