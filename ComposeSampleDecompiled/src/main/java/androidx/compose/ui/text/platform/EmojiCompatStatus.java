package androidx.compose.ui.text.platform;

import androidx.emoji2.text.EmojiCompat;

/* loaded from: classes.dex */
public final class EmojiCompatStatus {
    public static final DefaultImpl delegate;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.text.platform.DefaultImpl, java.lang.Object] */
    static {
        ?? obj = new Object();
        obj.loadState = EmojiCompat.sInstance != null ? obj.getFontLoadState() : null;
        delegate = obj;
    }
}
