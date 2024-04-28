package androidx.emoji2.text;

import android.content.Context;
import android.os.Looper;
import android.os.Trace;
import androidx.compose.runtime.Stack;
import androidx.core.os.TraceCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public class EmojiCompatInitializer implements Initializer {

    /* loaded from: classes.dex */
    public final class LoadEmojiCompatRunnable implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            try {
                int i = TraceCompat.$r8$clinit;
                Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                if (EmojiCompat.sInstance != null) {
                    EmojiCompat.get().load();
                }
                Trace.endSection();
            } catch (Throwable th) {
                int i2 = TraceCompat.$r8$clinit;
                Trace.endSection();
                throw th;
            }
        }
    }

    @Override // androidx.startup.Initializer
    public final /* bridge */ /* synthetic */ Object create(Context context) {
        m288create(context);
        return Boolean.TRUE;
    }

    @Override // androidx.startup.Initializer
    public final List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    /* JADX DEBUG: Possible override for method androidx.startup.Initializer.create(Landroid/content/Context;)Ljava/lang/Object; */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.emoji2.text.EmojiCompat$Config, androidx.emoji2.text.FontRequestEmojiCompatConfig] */
    /* renamed from: create, reason: collision with other method in class */
    public final void m288create(Context context) {
        Object obj;
        ?? config = new EmojiCompat.Config(new Stack(context));
        config.mMetadataLoadStrategy = 1;
        if (EmojiCompat.sInstance == null) {
            synchronized (EmojiCompat.INSTANCE_LOCK) {
                try {
                    if (EmojiCompat.sInstance == null) {
                        EmojiCompat.sInstance = new EmojiCompat(config);
                    }
                } finally {
                }
            }
        }
        AppInitializer appInitializer = AppInitializer.getInstance(context);
        appInitializer.getClass();
        synchronized (AppInitializer.sLock) {
            try {
                obj = appInitializer.mInitialized.get(ProcessLifecycleInitializer.class);
                if (obj == null) {
                    obj = appInitializer.doInitialize(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        final LifecycleRegistry lifecycle = ((LifecycleOwner) obj).getLifecycle();
        lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            /* JADX DEBUG: Multi-variable search result rejected for r4v3, resolved type: android.os.Handler */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.lang.Runnable] */
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onResume(LifecycleOwner lifecycleOwner) {
                EmojiCompatInitializer.this.getClass();
                ConcurrencyHelpers$Handler28Impl.createAsync(Looper.getMainLooper()).postDelayed(new Object(), 500L);
                lifecycle.removeObserver(this);
            }
        });
    }
}
