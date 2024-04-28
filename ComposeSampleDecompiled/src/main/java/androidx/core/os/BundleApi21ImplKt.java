package androidx.core.os;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;

/* loaded from: classes.dex */
public abstract class BundleApi21ImplKt {
    public static final void putSize(Bundle bundle, String str, Size size) {
        bundle.putSize(str, size);
    }

    public static final void putSizeF(Bundle bundle, String str, SizeF sizeF) {
        bundle.putSizeF(str, sizeF);
    }
}
