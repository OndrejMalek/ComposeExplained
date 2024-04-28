package androidx.compose.ui.focus;

/* loaded from: classes.dex */
public final class FocusDirection {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m67equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m68toStringimpl(int i) {
        return m67equalsimpl0(i, 1) ? "Next" : m67equalsimpl0(i, 2) ? "Previous" : m67equalsimpl0(i, 3) ? "Left" : m67equalsimpl0(i, 4) ? "Right" : m67equalsimpl0(i, 5) ? "Up" : m67equalsimpl0(i, 6) ? "Down" : m67equalsimpl0(i, 7) ? "Enter" : m67equalsimpl0(i, 8) ? "Exit" : "Invalid FocusDirection";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FocusDirection) {
            return this.value == ((FocusDirection) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m68toStringimpl(this.value);
    }
}
