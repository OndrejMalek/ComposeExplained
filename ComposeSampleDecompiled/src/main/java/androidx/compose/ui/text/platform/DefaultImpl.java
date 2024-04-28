package androidx.compose.ui.text.platform;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.emoji2.text.EmojiCompat;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DefaultImpl {
    public State loadState;

    public final State getFontLoadState() {
        EmojiCompat emojiCompat = EmojiCompat.get();
        ResultKt.checkNotNullExpressionValue(emojiCompat, "get()");
        if (emojiCompat.getLoadState() == 1) {
            return new ImmutableBool(true);
        }
        ParcelableSnapshotMutableState mutableStateOf = ResultKt.mutableStateOf(Boolean.FALSE, StructuralEqualityPolicy.INSTANCE);
        DefaultImpl$getFontLoadState$initCallback$1 defaultImpl$getFontLoadState$initCallback$1 = new DefaultImpl$getFontLoadState$initCallback$1(mutableStateOf, this);
        emojiCompat.mInitLock.writeLock().lock();
        try {
            if (emojiCompat.mLoadState != 1 && emojiCompat.mLoadState != 2) {
                emojiCompat.mInitCallbacks.add(defaultImpl$getFontLoadState$initCallback$1);
                emojiCompat.mInitLock.writeLock().unlock();
                return mutableStateOf;
            }
            emojiCompat.mMainHandler.post(new CallbackWithHandler$2(defaultImpl$getFontLoadState$initCallback$1, emojiCompat.mLoadState));
            emojiCompat.mInitLock.writeLock().unlock();
            return mutableStateOf;
        } catch (Throwable th) {
            emojiCompat.mInitLock.writeLock().unlock();
            throw th;
        }
    }
}
