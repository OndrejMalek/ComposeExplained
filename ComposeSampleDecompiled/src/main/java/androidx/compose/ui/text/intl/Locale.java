package androidx.compose.ui.text.intl;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Locale {
    public final AndroidLocale platformLocale;

    public Locale(AndroidLocale androidLocale) {
        this.platformLocale = androidLocale;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Locale)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return ResultKt.areEqual(toLanguageTag(), ((Locale) obj).toLanguageTag());
    }

    public final int hashCode() {
        return toLanguageTag().hashCode();
    }

    public final String toLanguageTag() {
        String languageTag = this.platformLocale.javaLocale.toLanguageTag();
        ResultKt.checkNotNullExpressionValue(languageTag, "javaLocale.toLanguageTag()");
        return languageTag;
    }

    public final String toString() {
        return toLanguageTag();
    }
}
