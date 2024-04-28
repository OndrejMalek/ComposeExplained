package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class BlockingCoroutine extends AbstractCoroutine {
    public final Thread blockedThread;
    public final EventLoopImplPlatform eventLoop;

    public BlockingCoroutine(CoroutineContext coroutineContext, Thread thread, EventLoopImplPlatform eventLoopImplPlatform) {
        super(coroutineContext, true);
        this.blockedThread = thread;
        this.eventLoop = eventLoopImplPlatform;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void afterCompletion(Object obj) {
        Thread currentThread = Thread.currentThread();
        Thread thread = this.blockedThread;
        if (ResultKt.areEqual(currentThread, thread)) {
            return;
        }
        LockSupport.unpark(thread);
    }
}
