package androidx.compose.ui.text.style;

/* loaded from: classes.dex */
public final class TextAlign {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m270equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextAlign) {
            return this.value == ((TextAlign) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return m270equalsimpl0(i, 1) ? "Left" : m270equalsimpl0(i, 2) ? "Right" : m270equalsimpl0(i, 3) ? "Center" : m270equalsimpl0(i, 4) ? "Justify" : m270equalsimpl0(i, 5) ? "Start" : m270equalsimpl0(i, 6) ? "End" : "Invalid";
    }
}
