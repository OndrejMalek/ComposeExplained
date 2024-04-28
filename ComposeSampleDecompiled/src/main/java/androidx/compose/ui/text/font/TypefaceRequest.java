package androidx.compose.ui.text.font;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TypefaceRequest {
    public final FontFamily fontFamily;
    public final int fontStyle;
    public final int fontSynthesis;
    public final FontWeight fontWeight;
    public final Object resourceLoaderCacheKey;

    public TypefaceRequest(FontFamily fontFamily, FontWeight fontWeight, int i, int i2, Object obj) {
        ResultKt.checkNotNullParameter(fontWeight, "fontWeight");
        this.fontFamily = fontFamily;
        this.fontWeight = fontWeight;
        this.fontStyle = i;
        this.fontSynthesis = i2;
        this.resourceLoaderCacheKey = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypefaceRequest)) {
            return false;
        }
        TypefaceRequest typefaceRequest = (TypefaceRequest) obj;
        return ResultKt.areEqual(this.fontFamily, typefaceRequest.fontFamily) && ResultKt.areEqual(this.fontWeight, typefaceRequest.fontWeight) && FontStyle.m258equalsimpl0(this.fontStyle, typefaceRequest.fontStyle) && FontSynthesis.m259equalsimpl0(this.fontSynthesis, typefaceRequest.fontSynthesis) && ResultKt.areEqual(this.resourceLoaderCacheKey, typefaceRequest.resourceLoaderCacheKey);
    }

    public final int hashCode() {
        FontFamily fontFamily = this.fontFamily;
        int hashCode = (Integer.hashCode(this.fontSynthesis) + ((Integer.hashCode(this.fontStyle) + ((((fontFamily == null ? 0 : fontFamily.hashCode()) * 31) + this.fontWeight.weight) * 31)) * 31)) * 31;
        Object obj = this.resourceLoaderCacheKey;
        return hashCode + (obj != null ? obj.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TypefaceRequest(fontFamily=");
        sb.append(this.fontFamily);
        sb.append(", fontWeight=");
        sb.append(this.fontWeight);
        sb.append(", fontStyle=");
        int i = this.fontStyle;
        sb.append((Object) (FontStyle.m258equalsimpl0(i, 0) ? "Normal" : FontStyle.m258equalsimpl0(i, 1) ? "Italic" : "Invalid"));
        sb.append(", fontSynthesis=");
        sb.append((Object) FontSynthesis.m260toStringimpl(this.fontSynthesis));
        sb.append(", resourceLoaderCacheKey=");
        sb.append(this.resourceLoaderCacheKey);
        sb.append(')');
        return sb.toString();
    }
}
