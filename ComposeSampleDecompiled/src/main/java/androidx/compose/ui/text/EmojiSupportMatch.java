package androidx.compose.ui.text;

/* loaded from: classes.dex */
public final class EmojiSupportMatch {
    public final int value;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m243toStringimpl(int i) {
        if (i == 0) {
            return "EmojiSupportMatch.Default";
        }
        if (i == 1) {
            return "EmojiSupportMatch.None";
        }
        return "Invalid(value=" + i + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof EmojiSupportMatch) {
            return this.value == ((EmojiSupportMatch) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m243toStringimpl(this.value);
    }
}
