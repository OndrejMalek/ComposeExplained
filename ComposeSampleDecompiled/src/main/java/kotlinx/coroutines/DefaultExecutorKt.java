package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public abstract class DefaultExecutorKt {
    public static final Delay DefaultDelay;

    static {
        String str;
        Delay delay;
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        try {
            str = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str == null || !Boolean.parseBoolean(str)) {
            delay = DefaultExecutor.INSTANCE;
        } else {
            DefaultScheduler defaultScheduler = Dispatchers.Default;
            CoroutineContext.Element element = MainDispatcherLoader.dispatcher;
            HandlerContext handlerContext = ((HandlerContext) element).immediate;
            delay = !(element instanceof Delay) ? DefaultExecutor.INSTANCE : (Delay) element;
        }
        DefaultDelay = delay;
    }
}
