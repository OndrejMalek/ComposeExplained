package androidx.compose.ui.text.style;

/* loaded from: classes.dex */
public final class Hyphens {
    public final int value;

    public final boolean equals(Object obj) {
        if (obj instanceof Hyphens) {
            return this.value == ((Hyphens) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return i == 1 ? "Hyphens.None" : i == 2 ? "Hyphens.Auto" : "Invalid";
    }
}
