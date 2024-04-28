package androidx.compose.ui.text;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PlatformTextStyle {
    public final PlatformParagraphStyle paragraphStyle;

    public PlatformTextStyle(PlatformParagraphStyle platformParagraphStyle) {
        this.paragraphStyle = platformParagraphStyle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlatformTextStyle)) {
            return false;
        }
        PlatformTextStyle platformTextStyle = (PlatformTextStyle) obj;
        if (!ResultKt.areEqual(this.paragraphStyle, platformTextStyle.paragraphStyle)) {
            return false;
        }
        platformTextStyle.getClass();
        return ResultKt.areEqual(null, null);
    }

    public final int hashCode() {
        PlatformParagraphStyle platformParagraphStyle = this.paragraphStyle;
        if (platformParagraphStyle != null) {
            return platformParagraphStyle.hashCode();
        }
        return 0;
    }

    public final String toString() {
        return "PlatformTextStyle(spanStyle=null, paragraphSyle=" + this.paragraphStyle + ')';
    }
}
