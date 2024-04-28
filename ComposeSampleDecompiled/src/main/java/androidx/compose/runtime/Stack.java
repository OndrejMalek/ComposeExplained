package androidx.compose.runtime;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.compose.runtime.Stack;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import androidx.profileinstaller.ProfileInstallReceiver;
import androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.ULong;

/* loaded from: classes.dex */
public class Stack implements EmojiCompat.MetadataRepoLoader, ProfileInstaller$DiagnosticsCallback {
    public final /* synthetic */ int $r8$classId;
    public final Object backing;

    public /* synthetic */ Stack(int i, Object obj) {
        this.$r8$classId = i;
        this.backing = obj;
    }

    public static Object findBestFont(Object[] objArr, int i, ULong.Companion companion) {
        int i2;
        boolean z;
        int i3 = (i & 1) == 0 ? 400 : 700;
        boolean z2 = (i & 2) != 0;
        Object obj = null;
        int i4 = Integer.MAX_VALUE;
        for (Object obj2 : objArr) {
            int i5 = companion.$r8$classId;
            switch (i5) {
                case 0:
                    i2 = ((FontsContractCompat$FontInfo) obj2).mWeight;
                    break;
                default:
                    i2 = 0;
                    ((FontResourcesParserCompat$FontFileResourceEntry) obj2).getClass();
                    break;
            }
            int abs = Math.abs(i2 - i3) * 2;
            switch (i5) {
                case 0:
                    z = ((FontsContractCompat$FontInfo) obj2).mItalic;
                    break;
                default:
                    z = false;
                    ((FontResourcesParserCompat$FontFileResourceEntry) obj2).getClass();
                    break;
            }
            int i6 = abs + (z == z2 ? 0 : 1);
            if (obj == null || i4 > i6) {
                obj = obj2;
                i4 = i6;
            }
        }
        return obj;
    }

    public final void add$1(int i) {
        if (!((List) this.backing).isEmpty()) {
            if (((Number) ((List) this.backing).get(0)).intValue() == i) {
                return;
            }
            if (((Number) ((List) this.backing).get(((List) r0).size() - 1)).intValue() == i) {
                return;
            }
        }
        int size = ((List) this.backing).size();
        ((List) this.backing).add(Integer.valueOf(i));
        while (size > 0) {
            int i2 = ((size + 1) >>> 1) - 1;
            int intValue = ((Number) ((List) this.backing).get(i2)).intValue();
            if (i <= intValue) {
                break;
            }
            ((List) this.backing).set(size, Integer.valueOf(intValue));
            size = i2;
        }
        ((List) this.backing).set(size, Integer.valueOf(i));
    }

    public final void clear() {
        switch (this.$r8$classId) {
            case 0:
                ((ArrayList) this.backing).clear();
                return;
            default:
                ((SparseArray) this.backing).clear();
                return;
        }
    }

    public Typeface createFromFontInfo(Context context, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (fontsContractCompat$FontInfoArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(findBestInfo(i, fontsContractCompat$FontInfoArr).mUri);
            try {
                Typeface createFromInputStream = createFromInputStream(context, inputStream);
                ResultKt.closeQuietly(inputStream);
                return createFromInputStream;
            } catch (IOException unused) {
                ResultKt.closeQuietly(inputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                ResultKt.closeQuietly(inputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = ResultKt.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (ResultKt.copyToFile(tempFile, inputStream)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    public FontsContractCompat$FontInfo findBestInfo(int i, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr) {
        return (FontsContractCompat$FontInfo) findBestFont(fontsContractCompat$FontInfoArr, i, new ULong.Companion(0));
    }

    public final AndroidCanvas getAndroidCanvas() {
        return (AndroidCanvas) this.backing;
    }

    public final boolean isNotEmpty() {
        switch (this.$r8$classId) {
            case 0:
                return !((ArrayList) this.backing).isEmpty();
            default:
                return !((List) this.backing).isEmpty();
        }
    }

    @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
    public final void load(final ResultKt resultKt) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ConcurrencyHelpers$$ExternalSyntheticLambda0("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new Runnable() { // from class: androidx.emoji2.text.EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Stack stack = Stack.this;
                final ResultKt resultKt2 = resultKt;
                final ThreadPoolExecutor threadPoolExecutor2 = threadPoolExecutor;
                stack.getClass();
                try {
                    FontRequestEmojiCompatConfig create = _BOUNDARY.create((Context) stack.backing);
                    if (create == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    FontRequestEmojiCompatConfig.FontRequestMetadataLoader fontRequestMetadataLoader = (FontRequestEmojiCompatConfig.FontRequestMetadataLoader) create.mMetadataLoader;
                    synchronized (fontRequestMetadataLoader.mLock) {
                        fontRequestMetadataLoader.mExecutor = threadPoolExecutor2;
                    }
                    create.mMetadataLoader.load(new ResultKt() { // from class: androidx.emoji2.text.EmojiCompatInitializer$BackgroundDefaultLoader$1
                        @Override // kotlin.ResultKt
                        public final void onFailed(Throwable th) {
                            ThreadPoolExecutor threadPoolExecutor3 = threadPoolExecutor2;
                            try {
                                ResultKt.this.onFailed(th);
                            } finally {
                                threadPoolExecutor3.shutdown();
                            }
                        }

                        @Override // kotlin.ResultKt
                        public final void onLoaded(MetadataRepo metadataRepo) {
                            ThreadPoolExecutor threadPoolExecutor3 = threadPoolExecutor2;
                            try {
                                ResultKt.this.onLoaded(metadataRepo);
                            } finally {
                                threadPoolExecutor3.shutdown();
                            }
                        }
                    });
                } catch (Throwable th) {
                    resultKt2.onFailed(th);
                    threadPoolExecutor2.shutdown();
                }
            }
        });
    }

    @Override // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
    public final void onDiagnosticReceived() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
    public final void onResultReceived(int i, Object obj) {
        String str;
        switch (i) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i == 6 || i == 7 || i == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.backing).setResultCode(i);
    }

    public final Object pop() {
        return ((ArrayList) this.backing).remove(((ArrayList) r0).size() - 1);
    }

    public final void push(Object obj) {
        ((ArrayList) this.backing).add(obj);
    }

    public final long readUnsignedInt() {
        return ((ByteBuffer) this.backing).getInt() & 4294967295L;
    }

    public final void skip(int i) {
        Object obj = this.backing;
        ((ByteBuffer) obj).position(((ByteBuffer) obj).position() + i);
    }

    public final int takeMax() {
        int intValue;
        if (((List) this.backing).size() <= 0) {
            EffectsKt.composeRuntimeError("Set is empty".toString());
            throw null;
        }
        int intValue2 = ((Number) ((List) this.backing).get(0)).intValue();
        while ((!((List) this.backing).isEmpty()) && ((Number) ((List) this.backing).get(0)).intValue() == intValue2) {
            List list = (List) this.backing;
            ResultKt.checkNotNullParameter(list, "<this>");
            if (list.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            list.set(0, list.get(ResultKt.getLastIndex(list)));
            ((List) this.backing).remove(r2.size() - 1);
            int size = ((List) this.backing).size();
            int size2 = ((List) this.backing).size() >>> 1;
            int i = 0;
            while (i < size2) {
                int intValue3 = ((Number) ((List) this.backing).get(i)).intValue();
                int i2 = (i + 1) * 2;
                int i3 = i2 - 1;
                int intValue4 = ((Number) ((List) this.backing).get(i3)).intValue();
                if (i2 >= size || (intValue = ((Number) ((List) this.backing).get(i2)).intValue()) <= intValue4) {
                    if (intValue4 > intValue3) {
                        ((List) this.backing).set(i, Integer.valueOf(intValue4));
                        ((List) this.backing).set(i3, Integer.valueOf(intValue3));
                        i = i3;
                    }
                } else if (intValue > intValue3) {
                    ((List) this.backing).set(i, Integer.valueOf(intValue));
                    ((List) this.backing).set(i2, Integer.valueOf(intValue3));
                    i = i2;
                }
            }
        }
        return intValue2;
    }

    public Stack(int i) {
        this.$r8$classId = i;
        if (i == 5) {
            this.backing = new AndroidCanvas();
            return;
        }
        if (i == 9) {
            this.backing = new Object();
        } else if (i != 11) {
            this.backing = new ArrayList();
        } else {
            this.backing = new ConcurrentHashMap();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Stack(Object obj) {
        this(10, 4);
        this.$r8$classId = 4;
    }

    public Stack(Locale locale, CharSequence charSequence) {
        this.$r8$classId = 8;
        this.backing = new DrawResult(charSequence, charSequence.length(), locale);
    }

    public Stack(View view, int i) {
        this.$r8$classId = i;
        if (i == 7) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.backing = new Stack(view, 6);
                return;
            } else {
                this.backing = new Stack(view, 6);
                return;
            }
        }
        if (i != 12) {
            this.backing = view;
        } else if (Build.VERSION.SDK_INT >= 30) {
            this.backing = new ULong.Companion() { // from class: androidx.core.view.SoftwareKeyboardControllerCompat$Impl20
            };
        } else {
            this.backing = new ULong.Companion() { // from class: androidx.core.view.SoftwareKeyboardControllerCompat$Impl20
            };
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Stack(int r2, int r3) {
        /*
            r1 = this;
            r1.$r8$classId = r3
            r0 = 4
            if (r3 == r0) goto L10
            r1.<init>()
            java.util.concurrent.atomic.AtomicInteger r3 = new java.util.concurrent.atomic.AtomicInteger
            r3.<init>(r2)
            r1.backing = r3
            return
        L10:
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>(r2)
            r1.<init>(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Stack.<init>(int, int):void");
    }

    public Stack(Context context) {
        this.$r8$classId = 16;
        this.backing = context.getApplicationContext();
    }

    public Stack(ByteBuffer byteBuffer) {
        this.$r8$classId = 17;
        this.backing = byteBuffer;
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Stack() {
        this((List) new ArrayList());
        this.$r8$classId = 2;
    }

    public Stack(List list) {
        this.$r8$classId = 2;
        ResultKt.checkNotNullParameter(list, "list");
        this.backing = list;
    }
}
