package androidx.compose.ui.input;

/* loaded from: classes.dex */
public final class InputMode {
    public final int value;

    public final boolean equals(Object obj) {
        if (obj instanceof InputMode) {
            return this.value == ((InputMode) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return i == 1 ? "Touch" : i == 2 ? "Keyboard" : "Error";
    }
}
