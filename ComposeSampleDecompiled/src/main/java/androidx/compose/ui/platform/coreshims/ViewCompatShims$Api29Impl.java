package androidx.compose.ui.platform.coreshims;

import android.view.View;
import android.view.contentcapture.ContentCaptureSession;

/* loaded from: classes.dex */
public abstract class ViewCompatShims$Api29Impl {
    public static ContentCaptureSession getContentCaptureSession(View view) {
        return view.getContentCaptureSession();
    }
}
