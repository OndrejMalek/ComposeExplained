package kotlinx.coroutines.scheduling;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

/* loaded from: classes.dex */
public abstract class SchedulerCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    public final CoroutineScheduler coroutineScheduler;

    public SchedulerCoroutineDispatcher(int i, int i2, long j, String str) {
        this.coroutineScheduler = new CoroutineScheduler(i, i2, j, str);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable, false, 6);
    }
}
