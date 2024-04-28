package androidx.compose.ui.text;

/* loaded from: classes.dex */
public final class PlatformParagraphStyle {
    public final boolean includeFontPadding = true;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlatformParagraphStyle) {
            return this.includeFontPadding == ((PlatformParagraphStyle) obj).includeFontPadding;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(0) + (Boolean.hashCode(this.includeFontPadding) * 31);
    }

    public final String toString() {
        return "PlatformParagraphStyle(includeFontPadding=" + this.includeFontPadding + ", emojiSupportMatch=" + ((Object) EmojiSupportMatch.m243toStringimpl(0)) + ')';
    }
}
