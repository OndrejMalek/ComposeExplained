package kotlinx.coroutines.internal;

import android.text.TextUtils;
import androidx.emoji2.text.EmojiProcessor;
import androidx.emoji2.text.TypefaceEmojiRasterizer;

/* loaded from: classes.dex */
public final class Symbol implements EmojiProcessor.EmojiProcessCallback {
    public final /* synthetic */ int $r8$classId;
    public final String symbol;

    public /* synthetic */ Symbol(int i, String str) {
        this.$r8$classId = i;
        this.symbol = str;
    }

    @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
    public final Object getResult() {
        return this;
    }

    @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
    public final boolean handleEmoji(String str, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if (!TextUtils.equals(str.subSequence(i, i2), this.symbol)) {
            return true;
        }
        typefaceEmojiRasterizer.mCache = (typefaceEmojiRasterizer.mCache & 3) | 4;
        return false;
    }

    public final String toString() {
        switch (this.$r8$classId) {
            case 0:
                return "<" + this.symbol + '>';
            default:
                return super.toString();
        }
    }
}
