package androidx.emoji2.text;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Trace;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.os.TraceCompat;
import androidx.core.provider.FontProvider;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import java.nio.MappedByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlinx.coroutines.flow.SafeFlow;

/* loaded from: classes.dex */
public final class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
    public static final ULong.Companion DEFAULT_FONTS_CONTRACT = new ULong.Companion(10);

    /* loaded from: classes.dex */
    public final class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {
        public ResultKt mCallback;
        public final Context mContext;
        public Executor mExecutor;
        public final ULong.Companion mFontProviderHelper;
        public final Object mLock;
        public Handler mMainHandler;
        public ThreadPoolExecutor mMyThreadPoolExecutor;
        public AnonymousClass1 mObserver;
        public final FontRequest mRequest;

        public FontRequestMetadataLoader(Context context, FontRequest fontRequest) {
            ULong.Companion companion = FontRequestEmojiCompatConfig.DEFAULT_FONTS_CONTRACT;
            this.mLock = new Object();
            ResultKt.checkNotNull$1(context, "Context cannot be null");
            this.mContext = context.getApplicationContext();
            this.mRequest = fontRequest;
            this.mFontProviderHelper = companion;
        }

        public final void cleanUp() {
            synchronized (this.mLock) {
                try {
                    this.mCallback = null;
                    AnonymousClass1 anonymousClass1 = this.mObserver;
                    if (anonymousClass1 != null) {
                        ULong.Companion companion = this.mFontProviderHelper;
                        Context context = this.mContext;
                        companion.getClass();
                        context.getContentResolver().unregisterContentObserver(anonymousClass1);
                        this.mObserver = null;
                    }
                    Handler handler = this.mMainHandler;
                    if (handler != null) {
                        handler.removeCallbacks(null);
                    }
                    this.mMainHandler = null;
                    ThreadPoolExecutor threadPoolExecutor = this.mMyThreadPoolExecutor;
                    if (threadPoolExecutor != null) {
                        threadPoolExecutor.shutdown();
                    }
                    this.mExecutor = null;
                    this.mMyThreadPoolExecutor = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public final void load(ResultKt resultKt) {
            synchronized (this.mLock) {
                this.mCallback = resultKt;
            }
            loadInternal();
        }

        public final void loadInternal() {
            synchronized (this.mLock) {
                try {
                    if (this.mCallback == null) {
                        return;
                    }
                    if (this.mExecutor == null) {
                        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ConcurrencyHelpers$$ExternalSyntheticLambda0("emojiCompat"));
                        threadPoolExecutor.allowCoreThreadTimeOut(true);
                        this.mMyThreadPoolExecutor = threadPoolExecutor;
                        this.mExecutor = threadPoolExecutor;
                    }
                    final int i = 0;
                    this.mExecutor.execute(new Runnable(this) { // from class: androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda0
                        public final /* synthetic */ FontRequestEmojiCompatConfig.FontRequestMetadataLoader f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i) {
                                case 0:
                                    FontRequestEmojiCompatConfig.FontRequestMetadataLoader fontRequestMetadataLoader = this.f$0;
                                    synchronized (fontRequestMetadataLoader.mLock) {
                                        try {
                                            if (fontRequestMetadataLoader.mCallback == null) {
                                                return;
                                            }
                                            try {
                                                FontsContractCompat$FontInfo retrieveFontInfo = fontRequestMetadataLoader.retrieveFontInfo();
                                                int i2 = retrieveFontInfo.mResultCode;
                                                if (i2 == 2) {
                                                    synchronized (fontRequestMetadataLoader.mLock) {
                                                    }
                                                }
                                                if (i2 != 0) {
                                                    throw new RuntimeException("fetchFonts result is not OK. (" + i2 + ")");
                                                }
                                                try {
                                                    int i3 = TraceCompat.$r8$clinit;
                                                    Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                                    ULong.Companion companion = fontRequestMetadataLoader.mFontProviderHelper;
                                                    Context context = fontRequestMetadataLoader.mContext;
                                                    companion.getClass();
                                                    Typeface createFromFontInfo = TypefaceCompat.sTypefaceCompatImpl.createFromFontInfo(context, new FontsContractCompat$FontInfo[]{retrieveFontInfo}, 0);
                                                    MappedByteBuffer mmap = ResultKt.mmap(fontRequestMetadataLoader.mContext, retrieveFontInfo.mUri);
                                                    if (mmap == null || createFromFontInfo == null) {
                                                        throw new RuntimeException("Unable to open file.");
                                                    }
                                                    try {
                                                        Trace.beginSection("EmojiCompat.MetadataRepo.create");
                                                        MetadataRepo metadataRepo = new MetadataRepo(createFromFontInfo, _BOUNDARY.read(mmap));
                                                        Trace.endSection();
                                                        Trace.endSection();
                                                        synchronized (fontRequestMetadataLoader.mLock) {
                                                            try {
                                                                ResultKt resultKt = fontRequestMetadataLoader.mCallback;
                                                                if (resultKt != null) {
                                                                    resultKt.onLoaded(metadataRepo);
                                                                }
                                                            } finally {
                                                            }
                                                        }
                                                        fontRequestMetadataLoader.cleanUp();
                                                        return;
                                                    } finally {
                                                        int i4 = TraceCompat.$r8$clinit;
                                                        Trace.endSection();
                                                    }
                                                } catch (Throwable th) {
                                                    throw th;
                                                }
                                            } catch (Throwable th2) {
                                                synchronized (fontRequestMetadataLoader.mLock) {
                                                    try {
                                                        ResultKt resultKt2 = fontRequestMetadataLoader.mCallback;
                                                        if (resultKt2 != null) {
                                                            resultKt2.onFailed(th2);
                                                        }
                                                        fontRequestMetadataLoader.cleanUp();
                                                        return;
                                                    } finally {
                                                    }
                                                }
                                            }
                                        } finally {
                                        }
                                    }
                                default:
                                    this.f$0.loadInternal();
                                    return;
                            }
                        }
                    });
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final FontsContractCompat$FontInfo retrieveFontInfo() {
            try {
                ULong.Companion companion = this.mFontProviderHelper;
                Context context = this.mContext;
                FontRequest fontRequest = this.mRequest;
                companion.getClass();
                SafeFlow fontFamilyResult = FontProvider.getFontFamilyResult(context, fontRequest);
                int i = fontFamilyResult.$r8$classId;
                if (i != 0) {
                    throw new RuntimeException("fetchFonts failed (" + i + ")");
                }
                FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr = (FontsContractCompat$FontInfo[]) fontFamilyResult.block;
                if (fontsContractCompat$FontInfoArr == null || fontsContractCompat$FontInfoArr.length == 0) {
                    throw new RuntimeException("fetchFonts failed (empty result)");
                }
                return fontsContractCompat$FontInfoArr[0];
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException("provider not found", e);
            }
        }
    }
}
