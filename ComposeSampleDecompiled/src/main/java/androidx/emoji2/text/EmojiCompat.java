package androidx.emoji2.text;

import _COROUTINE._BOUNDARY;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.inputmethod.EditorInfo;
import androidx.collection.ArraySet;
import androidx.compose.runtime.Stack;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.emoji2.text.EmojiProcessor;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.ResultKt;
import kotlin.ULong;

/* loaded from: classes.dex */
public final class EmojiCompat {
    public static final Object INSTANCE_LOCK = new Object();
    public static volatile EmojiCompat sInstance;
    public final DefaultGlyphChecker mGlyphChecker;
    public final CompatInternal19 mHelper;
    public final ArraySet mInitCallbacks;
    public final ReentrantReadWriteLock mInitLock;
    public volatile int mLoadState;
    public final Handler mMainHandler;
    public final int mMetadataLoadStrategy;
    public final MetadataRepoLoader mMetadataLoader;
    public final ULong.Companion mSpanFactory;

    /* loaded from: classes.dex */
    public final class CompatInternal19 extends Stack {
        public volatile MetadataRepo mMetadataRepo;
        public volatile EmojiProcessor mProcessor;

        public final void loadMetadata() {
            try {
                ((EmojiCompat) this.backing).mMetadataLoader.load(new ResultKt() { // from class: androidx.emoji2.text.EmojiCompat.CompatInternal19.1
                    @Override // kotlin.ResultKt
                    public final void onFailed(Throwable th) {
                        ((EmojiCompat) CompatInternal19.this.backing).onMetadataLoadFailed(th);
                    }

                    @Override // kotlin.ResultKt
                    public final void onLoaded(MetadataRepo metadataRepo) {
                        CompatInternal19 compatInternal19 = CompatInternal19.this;
                        compatInternal19.mMetadataRepo = metadataRepo;
                        MetadataRepo metadataRepo2 = compatInternal19.mMetadataRepo;
                        Object obj = compatInternal19.backing;
                        EmojiCompat emojiCompat = (EmojiCompat) obj;
                        ULong.Companion companion = emojiCompat.mSpanFactory;
                        DefaultGlyphChecker defaultGlyphChecker = emojiCompat.mGlyphChecker;
                        ((EmojiCompat) obj).getClass();
                        compatInternal19.mProcessor = new EmojiProcessor(metadataRepo2, companion, defaultGlyphChecker, Build.VERSION.SDK_INT >= 34 ? EmojiExclusions$EmojiExclusions_Api34.getExclusions() : _BOUNDARY.getExclusions());
                        ((EmojiCompat) compatInternal19.backing).onMetadataLoadSuccess();
                    }
                });
            } catch (Throwable th) {
                ((EmojiCompat) this.backing).onMetadataLoadFailed(th);
            }
        }

        /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object, androidx.emoji2.text.UnprecomputeTextOnModificationSpannable] */
        public final CharSequence process(CharSequence charSequence, int i, boolean z) {
            UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable;
            int i2;
            UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable2;
            TypefaceEmojiSpan[] typefaceEmojiSpanArr;
            EmojiProcessor emojiProcessor = this.mProcessor;
            emojiProcessor.getClass();
            int i3 = 0;
            if (charSequence instanceof Spannable) {
                unprecomputeTextOnModificationSpannable = new UnprecomputeTextOnModificationSpannable((Spannable) charSequence);
            } else if (!(charSequence instanceof Spanned) || ((Spanned) charSequence).nextSpanTransition(-1, i + 1, TypefaceEmojiSpan.class) > i) {
                unprecomputeTextOnModificationSpannable = null;
            } else {
                ?? obj = new Object();
                obj.mSafeToWrite = false;
                obj.mDelegate = new SpannableString(charSequence);
                unprecomputeTextOnModificationSpannable = obj;
            }
            if (unprecomputeTextOnModificationSpannable == null || (typefaceEmojiSpanArr = (TypefaceEmojiSpan[]) unprecomputeTextOnModificationSpannable.mDelegate.getSpans(0, i, TypefaceEmojiSpan.class)) == null || typefaceEmojiSpanArr.length <= 0) {
                i2 = i;
            } else {
                int length = typefaceEmojiSpanArr.length;
                int i4 = 0;
                while (i3 < length) {
                    TypefaceEmojiSpan typefaceEmojiSpan = typefaceEmojiSpanArr[i3];
                    int spanStart = unprecomputeTextOnModificationSpannable.mDelegate.getSpanStart(typefaceEmojiSpan);
                    int spanEnd = unprecomputeTextOnModificationSpannable.mDelegate.getSpanEnd(typefaceEmojiSpan);
                    if (spanStart != i) {
                        unprecomputeTextOnModificationSpannable.removeSpan(typefaceEmojiSpan);
                    }
                    i4 = Math.min(spanStart, i4);
                    i = Math.max(spanEnd, i);
                    i3++;
                }
                i2 = i;
                i3 = i4;
            }
            if (i3 == i2) {
                return charSequence;
            }
            String str = (String) charSequence;
            return (i3 < str.length() && (unprecomputeTextOnModificationSpannable2 = (UnprecomputeTextOnModificationSpannable) emojiProcessor.process(str, i3, i2, Integer.MAX_VALUE, z, new EmojiProcessor.EmojiProcessAddSpanCallback(unprecomputeTextOnModificationSpannable, (ULong.Companion) emojiProcessor.mSpanFactory))) != null) ? unprecomputeTextOnModificationSpannable2.mDelegate : charSequence;
        }

        public final void updateEditorInfoAttrs(EditorInfo editorInfo) {
            Bundle bundle = editorInfo.extras;
            MetadataList metadataList = this.mMetadataRepo.mMetadataList;
            int __offset = metadataList.__offset(4);
            bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", __offset != 0 ? metadataList.bb.getInt(__offset + metadataList.bb_pos) : 0);
            Bundle bundle2 = editorInfo.extras;
            ((EmojiCompat) this.backing).getClass();
            bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
        }
    }

    /* loaded from: classes.dex */
    public abstract class Config {
        public final MetadataRepoLoader mMetadataLoader;
        public int mMetadataLoadStrategy = 0;
        public final DefaultGlyphChecker mGlyphChecker = new DefaultGlyphChecker();

        public Config(MetadataRepoLoader metadataRepoLoader) {
            this.mMetadataLoader = metadataRepoLoader;
        }
    }

    /* loaded from: classes.dex */
    public interface GlyphChecker {
    }

    /* loaded from: classes.dex */
    public interface MetadataRepoLoader {
        void load(ResultKt resultKt);
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [androidx.compose.runtime.Stack, androidx.emoji2.text.EmojiCompat$CompatInternal19] */
    public EmojiCompat(FontRequestEmojiCompatConfig fontRequestEmojiCompatConfig) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.mInitLock = reentrantReadWriteLock;
        this.mLoadState = 3;
        this.mMetadataLoader = fontRequestEmojiCompatConfig.mMetadataLoader;
        int i = fontRequestEmojiCompatConfig.mMetadataLoadStrategy;
        this.mMetadataLoadStrategy = i;
        this.mGlyphChecker = fontRequestEmojiCompatConfig.mGlyphChecker;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mInitCallbacks = new ArraySet();
        this.mSpanFactory = new ULong.Companion(9);
        ?? stack = new Stack(15, this);
        this.mHelper = stack;
        reentrantReadWriteLock.writeLock().lock();
        if (i == 0) {
            try {
                this.mLoadState = 0;
            } catch (Throwable th) {
                this.mInitLock.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (getLoadState() == 0) {
            stack.loadMetadata();
        }
    }

    public static EmojiCompat get() {
        EmojiCompat emojiCompat;
        synchronized (INSTANCE_LOCK) {
            try {
                emojiCompat = sInstance;
                if (!(emojiCompat != null)) {
                    throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
                }
            } finally {
            }
        }
        return emojiCompat;
    }

    public final int getLoadState() {
        this.mInitLock.readLock().lock();
        try {
            return this.mLoadState;
        } finally {
            this.mInitLock.readLock().unlock();
        }
    }

    public final void load() {
        if (!(this.mMetadataLoadStrategy == 1)) {
            throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        }
        if (getLoadState() == 1) {
            return;
        }
        this.mInitLock.writeLock().lock();
        try {
            if (this.mLoadState == 0) {
                return;
            }
            this.mLoadState = 0;
            this.mInitLock.writeLock().unlock();
            this.mHelper.loadMetadata();
        } finally {
            this.mInitLock.writeLock().unlock();
        }
    }

    public final void onMetadataLoadFailed(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.mInitLock.writeLock().lock();
        try {
            this.mLoadState = 2;
            arrayList.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
            this.mInitLock.writeLock().unlock();
            this.mMainHandler.post(new CallbackWithHandler$2(arrayList, this.mLoadState, th));
        } catch (Throwable th2) {
            this.mInitLock.writeLock().unlock();
            throw th2;
        }
    }

    public final void onMetadataLoadSuccess() {
        ArrayList arrayList = new ArrayList();
        this.mInitLock.writeLock().lock();
        try {
            this.mLoadState = 1;
            arrayList.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
            this.mInitLock.writeLock().unlock();
            this.mMainHandler.post(new CallbackWithHandler$2(this.mLoadState, arrayList));
        } catch (Throwable th) {
            this.mInitLock.writeLock().unlock();
            throw th;
        }
    }
}
