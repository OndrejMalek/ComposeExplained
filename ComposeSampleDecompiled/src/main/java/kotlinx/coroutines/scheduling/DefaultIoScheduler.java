package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.internal.LimitedDispatcher;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;

/* loaded from: classes.dex */
public final class DefaultIoScheduler extends ExecutorCoroutineDispatcher implements Executor {
    public static final DefaultIoScheduler INSTANCE = new CoroutineDispatcher();

    /* renamed from: default, reason: not valid java name */
    public static final CoroutineDispatcher f4default;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.coroutines.scheduling.DefaultIoScheduler, kotlinx.coroutines.CoroutineDispatcher] */
    /* JADX WARN: Type inference failed for: r2v3, types: [kotlinx.coroutines.internal.LimitedDispatcher] */
    static {
        UnlimitedIoScheduler unlimitedIoScheduler = UnlimitedIoScheduler.INSTANCE;
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        if (64 >= i) {
            i = 64;
        }
        int systemProp$default = JobKt.systemProp$default("kotlinx.coroutines.io.parallelism", i, 0, 0, 12);
        unlimitedIoScheduler.getClass();
        if (systemProp$default < 1) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but got " + systemProp$default).toString());
        }
        if (systemProp$default < TasksKt.MAX_POOL_SIZE) {
            if (systemProp$default < 1) {
                throw new IllegalArgumentException(("Expected positive parallelism level, but got " + systemProp$default).toString());
            }
            unlimitedIoScheduler = new LimitedDispatcher(unlimitedIoScheduler, systemProp$default);
        }
        f4default = unlimitedIoScheduler;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        f4default.dispatch(coroutineContext, runnable);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        dispatch(EmptyCoroutineContext.INSTANCE, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final String toString() {
        return "Dispatchers.IO";
    }
}
