package androidx.compose.ui.platform;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class WindowRecomposerPolicy {
    public static final AtomicReference factory;

    static {
        WindowRecomposerFactory.Companion.getClass();
        factory = new AtomicReference(WindowRecomposerFactory$Companion$LifecycleAware$1.INSTANCE);
    }
}
