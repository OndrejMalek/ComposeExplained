package androidx.compose.ui.platform;

import android.content.Context;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidClipboardManager implements ClipboardManager {
    public final android.content.ClipboardManager clipboardManager;

    public AndroidClipboardManager(Context context) {
        Object systemService = context.getSystemService("clipboard");
        ResultKt.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        this.clipboardManager = (android.content.ClipboardManager) systemService;
    }
}
