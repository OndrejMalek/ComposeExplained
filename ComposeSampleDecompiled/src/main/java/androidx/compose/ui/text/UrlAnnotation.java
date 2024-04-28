package androidx.compose.ui.text;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class UrlAnnotation {
    public final String url;

    public UrlAnnotation(String str) {
        ResultKt.checkNotNullParameter(str, "url");
        this.url = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UrlAnnotation) {
            return ResultKt.areEqual(this.url, ((UrlAnnotation) obj).url);
        }
        return false;
    }

    public final int hashCode() {
        return this.url.hashCode();
    }

    public final String toString() {
        return "UrlAnnotation(url=" + this.url + ')';
    }
}
