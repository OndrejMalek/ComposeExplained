package androidx.compose.ui.semantics;

/* loaded from: classes.dex */
public final class Role {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m238equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Role) {
            return this.value == ((Role) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return m238equalsimpl0(i, 0) ? "Button" : m238equalsimpl0(i, 1) ? "Checkbox" : m238equalsimpl0(i, 2) ? "Switch" : m238equalsimpl0(i, 3) ? "RadioButton" : m238equalsimpl0(i, 4) ? "Tab" : m238equalsimpl0(i, 5) ? "Image" : m238equalsimpl0(i, 6) ? "DropdownList" : "Unknown";
    }
}
