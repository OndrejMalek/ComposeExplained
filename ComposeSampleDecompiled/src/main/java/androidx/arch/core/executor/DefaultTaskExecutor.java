package androidx.arch.core.executor;

import _COROUTINE._BOUNDARY;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class DefaultTaskExecutor extends _BOUNDARY {
    public final Object mLock;

    public DefaultTaskExecutor() {
        Executors.newFixedThreadPool(4, new ThreadFactory() { // from class: androidx.arch.core.executor.DefaultTaskExecutor.1
            public final AtomicInteger mThreadId = new AtomicInteger(0);

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("arch_disk_io_" + this.mThreadId.getAndIncrement());
                return thread;
            }
        });
    }
}
