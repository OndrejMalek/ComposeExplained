package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.ResultKt;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;

/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;
    public static final AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    public static final AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    public static final AtomicIntegerFieldUpdater _isCompleted$FU = AtomicIntegerFieldUpdater.newUpdater(EventLoopImplBase.class, "_isCompleted");

    /* loaded from: classes.dex */
    public final class DelayedResumeTask extends DelayedTask {
        public final CancellableContinuation cont;

        public DelayedResumeTask(long j, CancellableContinuationImpl cancellableContinuationImpl) {
            super(j);
            this.cont = cancellableContinuationImpl;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.cont.resumeUndispatched(EventLoopImplBase.this);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public final String toString() {
            return super.toString() + this.cont;
        }
    }

    /* loaded from: classes.dex */
    public abstract class DelayedTask implements Runnable, Comparable, DisposableHandle {
        private volatile Object _heap;
        public int index = -1;
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            long j = this.nanoTime - ((DelayedTask) obj).nanoTime;
            if (j > 0) {
                return 1;
            }
            return j < 0 ? -1 : 0;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            synchronized (this) {
                try {
                    Object obj = this._heap;
                    Symbol symbol = JobKt.DISPOSED_TASK;
                    if (obj == symbol) {
                        return;
                    }
                    DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
                    if (delayedTaskQueue != null) {
                        synchronized (delayedTaskQueue) {
                            if (getHeap() != null) {
                                delayedTaskQueue.removeAtImpl(this.index);
                            }
                        }
                    }
                    this._heap = symbol;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final ThreadSafeHeap getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        public final int scheduleTask(long j, DelayedTaskQueue delayedTaskQueue, EventLoopImplBase eventLoopImplBase) {
            synchronized (this) {
                if (this._heap == JobKt.DISPOSED_TASK) {
                    return 2;
                }
                synchronized (delayedTaskQueue) {
                    try {
                        DelayedTask[] delayedTaskArr = delayedTaskQueue.a;
                        DelayedTask delayedTask = delayedTaskArr != null ? delayedTaskArr[0] : null;
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = EventLoopImplBase._queue$FU;
                        eventLoopImplBase.getClass();
                        if (EventLoopImplBase._isCompleted$FU.get(eventLoopImplBase) != 0) {
                            return 1;
                        }
                        if (delayedTask == null) {
                            delayedTaskQueue.timeNow = j;
                        } else {
                            long j2 = delayedTask.nanoTime;
                            if (j2 - j < 0) {
                                j = j2;
                            }
                            if (j - delayedTaskQueue.timeNow > 0) {
                                delayedTaskQueue.timeNow = j;
                            }
                        }
                        long j3 = this.nanoTime;
                        long j4 = delayedTaskQueue.timeNow;
                        if (j3 - j4 < 0) {
                            this.nanoTime = j4;
                        }
                        delayedTaskQueue.addImpl(this);
                        return 0;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public final void setHeap(DelayedTaskQueue delayedTaskQueue) {
            if (this._heap == JobKt.DISPOSED_TASK) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this._heap = delayedTaskQueue;
        }

        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + ']';
        }
    }

    /* loaded from: classes.dex */
    public final class DelayedTaskQueue extends ThreadSafeHeap {
        public long timeNow;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        enqueue(runnable);
    }

    public void enqueue(Runnable runnable) {
        if (!enqueueImpl(runnable)) {
            DefaultExecutor.INSTANCE.enqueue(runnable);
            return;
        }
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            LockSupport.unpark(thread);
        }
    }

    public final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _queue$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (_isCompleted$FU.get(this) != 0) {
                return false;
            }
            if (obj == null) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                return true;
            }
            if (!(obj instanceof LockFreeTaskQueueCore)) {
                if (obj == JobKt.CLOSED_EMPTY) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                lockFreeTaskQueueCore.addLast(runnable);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return true;
            }
            LockFreeTaskQueueCore lockFreeTaskQueueCore2 = (LockFreeTaskQueueCore) obj;
            int addLast = lockFreeTaskQueueCore2.addLast(runnable);
            if (addLast == 0) {
                return true;
            }
            if (addLast == 1) {
                LockFreeTaskQueueCore next = lockFreeTaskQueueCore2.next();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, next) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            } else if (addLast == 2) {
                return false;
            }
        }
    }

    public final boolean isEmpty() {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (!(arrayDeque != null ? arrayDeque.isEmpty() : true)) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) _delayed$FU.get(this);
        if (delayedTaskQueue != null && ThreadSafeHeap._size$FU.get(delayedTaskQueue) != 0) {
            return false;
        }
        Object obj = _queue$FU.get(this);
        if (obj == null) {
            return true;
        }
        if (obj instanceof LockFreeTaskQueueCore) {
            long j = LockFreeTaskQueueCore._state$FU.get((LockFreeTaskQueueCore) obj);
            if (((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30))) {
                return true;
            }
        } else if (obj == JobKt.CLOSED_EMPTY) {
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public final long processNextEvent() {
        Runnable runnable;
        DelayedTask delayedTask;
        DelayedTask removeAtImpl;
        if (processUnconfinedEvent()) {
            return 0L;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) _delayed$FU.get(this);
        if (delayedTaskQueue != null && ThreadSafeHeap._size$FU.get(delayedTaskQueue) != 0) {
            long nanoTime = System.nanoTime();
            do {
                synchronized (delayedTaskQueue) {
                    DelayedTask[] delayedTaskArr = delayedTaskQueue.a;
                    DelayedTask delayedTask2 = delayedTaskArr != null ? delayedTaskArr[0] : null;
                    removeAtImpl = delayedTask2 == null ? null : (nanoTime - delayedTask2.nanoTime < 0 || !enqueueImpl(delayedTask2)) ? null : delayedTaskQueue.removeAtImpl(0);
                }
            } while (removeAtImpl != null);
        }
        loop1: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _queue$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                break;
            }
            if (!(obj instanceof LockFreeTaskQueueCore)) {
                if (obj == JobKt.CLOSED_EMPTY) {
                    break;
                }
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                runnable = (Runnable) obj;
                break loop1;
            }
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
            Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
            if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                runnable = (Runnable) removeFirstOrNull;
                break;
            }
            LockFreeTaskQueueCore next = lockFreeTaskQueueCore.next();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, next) && atomicReferenceFieldUpdater.get(this) == obj) {
            }
        }
        runnable = null;
        if (runnable != null) {
            runnable.run();
            return 0L;
        }
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (((arrayDeque == null || arrayDeque.isEmpty()) ? Long.MAX_VALUE : 0L) == 0) {
            return 0L;
        }
        Object obj2 = _queue$FU.get(this);
        if (obj2 != null) {
            if (!(obj2 instanceof LockFreeTaskQueueCore)) {
                if (obj2 != JobKt.CLOSED_EMPTY) {
                    return 0L;
                }
                return Long.MAX_VALUE;
            }
            long j = LockFreeTaskQueueCore._state$FU.get((LockFreeTaskQueueCore) obj2);
            if (((int) (1073741823 & j)) != ((int) ((j & 1152921503533105152L) >> 30))) {
                return 0L;
            }
        }
        DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) _delayed$FU.get(this);
        if (delayedTaskQueue2 != null) {
            synchronized (delayedTaskQueue2) {
                DelayedTask[] delayedTaskArr2 = delayedTaskQueue2.a;
                delayedTask = delayedTaskArr2 != null ? delayedTaskArr2[0] : null;
            }
            if (delayedTask != null) {
                long nanoTime2 = delayedTask.nanoTime - System.nanoTime();
                if (nanoTime2 < 0) {
                    return 0L;
                }
                return nanoTime2;
            }
        }
        return Long.MAX_VALUE;
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue, java.lang.Object] */
    public final void schedule(long j, DelayedTask delayedTask) {
        int scheduleTask;
        Thread thread;
        boolean z = _isCompleted$FU.get(this) != 0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _delayed$FU;
        if (z) {
            scheduleTask = 1;
        } else {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) atomicReferenceFieldUpdater.get(this);
            if (delayedTaskQueue == null) {
                ?? obj = new Object();
                obj.timeNow = j;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, obj) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                Object obj2 = atomicReferenceFieldUpdater.get(this);
                ResultKt.checkNotNull(obj2);
                delayedTaskQueue = (DelayedTaskQueue) obj2;
            }
            scheduleTask = delayedTask.scheduleTask(j, delayedTaskQueue, this);
        }
        if (scheduleTask != 0) {
            if (scheduleTask == 1) {
                reschedule(j, delayedTask);
                return;
            } else {
                if (scheduleTask != 2) {
                    throw new IllegalStateException("unexpected result".toString());
                }
                return;
            }
        }
        DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) atomicReferenceFieldUpdater.get(this);
        if (delayedTaskQueue2 != null) {
            synchronized (delayedTaskQueue2) {
                DelayedTask[] delayedTaskArr = delayedTaskQueue2.a;
                r4 = delayedTaskArr != null ? delayedTaskArr[0] : null;
            }
        }
        if (r4 != delayedTask || Thread.currentThread() == (thread = getThread())) {
            return;
        }
        LockSupport.unpark(thread);
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        long j2 = j > 0 ? j >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j : 0L;
        if (j2 < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(j2 + nanoTime, cancellableContinuationImpl);
            schedule(nanoTime, delayedResumeTask);
            cancellableContinuationImpl.invokeOnCancellation(new InvokeOnCancel(1, delayedResumeTask));
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public void shutdown() {
        DelayedTask removeAtImpl;
        ThreadLocal threadLocal = ThreadLocalEventLoop.ref;
        ThreadLocalEventLoop.ref.set(null);
        _isCompleted$FU.set(this, 1);
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _queue$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            Symbol symbol = JobKt.CLOSED_EMPTY;
            if (obj != null) {
                if (!(obj instanceof LockFreeTaskQueueCore)) {
                    if (obj != symbol) {
                        LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                        lockFreeTaskQueueCore.addLast((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((LockFreeTaskQueueCore) obj).close();
                break;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, symbol)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            break loop0;
        }
        do {
        } while (processNextEvent() <= 0);
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) _delayed$FU.get(this);
            if (delayedTaskQueue == null) {
                return;
            }
            synchronized (delayedTaskQueue) {
                removeAtImpl = ThreadSafeHeap._size$FU.get(delayedTaskQueue) > 0 ? delayedTaskQueue.removeAtImpl(0) : null;
            }
            if (removeAtImpl == null) {
                return;
            } else {
                reschedule(nanoTime, removeAtImpl);
            }
        }
    }
}
