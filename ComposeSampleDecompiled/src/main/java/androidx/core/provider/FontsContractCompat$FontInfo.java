package androidx.core.provider;

import android.net.Uri;

/* loaded from: classes.dex */
public final class FontsContractCompat$FontInfo {
    public final boolean mItalic;
    public final int mResultCode;
    public final int mTtcIndex;
    public final Uri mUri;
    public final int mWeight;

    public FontsContractCompat$FontInfo(Uri uri, int i, int i2, boolean z, int i3) {
        uri.getClass();
        this.mUri = uri;
        this.mTtcIndex = i;
        this.mWeight = i2;
        this.mItalic = z;
        this.mResultCode = i3;
    }
}
