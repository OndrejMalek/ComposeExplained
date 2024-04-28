package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.scheduling.UnlimitedIoScheduler;

/* loaded from: classes.dex */
public final class LimitedDispatcher extends CoroutineDispatcher implements Delay {
    public static final AtomicIntegerFieldUpdater runningWorkers$FU = AtomicIntegerFieldUpdater.newUpdater(LimitedDispatcher.class, "runningWorkers");
    public final /* synthetic */ Delay $$delegate_0;
    public final CoroutineDispatcher dispatcher;
    public final int parallelism;
    public final LockFreeTaskQueue queue;
    private volatile int runningWorkers;
    public final Object workerAllocationLock;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: kotlinx.coroutines.scheduling.UnlimitedIoScheduler */
    /* JADX WARN: Multi-variable type inference failed */
    public LimitedDispatcher(UnlimitedIoScheduler unlimitedIoScheduler, int i) {
        this.dispatcher = unlimitedIoScheduler;
        this.parallelism = i;
        Delay delay = unlimitedIoScheduler instanceof Delay ? (Delay) unlimitedIoScheduler : null;
        this.$$delegate_0 = delay == null ? DefaultExecutorKt.DefaultDelay : delay;
        this.queue = new LockFreeTaskQueue();
        this.workerAllocationLock = new Object();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        Runnable obtainTaskOrDeallocateWorker;
        this.queue.addLast(runnable);
        if (runningWorkers$FU.get(this) >= this.parallelism || !tryAllocateWorker() || (obtainTaskOrDeallocateWorker = obtainTaskOrDeallocateWorker()) == null) {
            return;
        }
        this.dispatcher.dispatch(this, new Worker(this, obtainTaskOrDeallocateWorker));
    }

    public final Runnable obtainTaskOrDeallocateWorker() {
        while (true) {
            Runnable runnable = (Runnable) this.queue.removeFirstOrNull();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.workerAllocationLock) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = runningWorkers$FU;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.queue.getSize() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        this.$$delegate_0.scheduleResumeAfterDelay(j, cancellableContinuationImpl);
    }

    public final boolean tryAllocateWorker() {
        synchronized (this.workerAllocationLock) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = runningWorkers$FU;
            if (atomicIntegerFieldUpdater.get(this) >= this.parallelism) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public final class Worker implements Runnable {
        public final /* synthetic */ int $r8$classId = 1;
        public Object currentTask;
        public final /* synthetic */ CoroutineDispatcher this$0;

        public Worker(CancellableContinuationImpl cancellableContinuationImpl, HandlerContext handlerContext) {
            this.currentTask = cancellableContinuationImpl;
            this.this$0 = handlerContext;
        }

        @Override // java.lang.Runnable
        public final void run() {
            CoroutineDispatcher coroutineDispatcher = this.this$0;
            switch (this.$r8$classId) {
                case 0:
                    int i = 0;
                    while (true) {
                        try {
                            ((Runnable) this.currentTask).run();
                        } catch (Throwable th) {
                            ResultKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, th);
                        }
                        LimitedDispatcher limitedDispatcher = (LimitedDispatcher) coroutineDispatcher;
                        Runnable obtainTaskOrDeallocateWorker = limitedDispatcher.obtainTaskOrDeallocateWorker();
                        if (obtainTaskOrDeallocateWorker == null) {
                            return;
                        }
                        this.currentTask = obtainTaskOrDeallocateWorker;
                        i++;
                        if (i >= 16) {
                            CoroutineDispatcher coroutineDispatcher2 = limitedDispatcher.dispatcher;
                            if (coroutineDispatcher2.isDispatchNeeded()) {
                                coroutineDispatcher2.dispatch(limitedDispatcher, this);
                                return;
                            }
                        }
                    }
                default:
                    ((CancellableContinuation) this.currentTask).resumeUndispatched((HandlerContext) coroutineDispatcher);
                    return;
            }
        }

        public Worker(LimitedDispatcher limitedDispatcher, Runnable runnable) {
            this.this$0 = limitedDispatcher;
            this.currentTask = runnable;
        }
    }
}
