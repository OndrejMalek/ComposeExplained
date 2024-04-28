package kotlinx.coroutines.scheduling;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.random.Random;
import kotlinx.coroutines.internal.LockFreeTaskQueue;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    private volatile int _isTerminated;
    private volatile long controlState;
    public final int corePoolSize;
    public final GlobalQueue globalBlockingQueue;
    public final GlobalQueue globalCpuQueue;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    private volatile long parkedWorkersStack;
    public final String schedulerName;
    public final ResizableAtomicArray workers;
    public static final AtomicLongFieldUpdater parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
    public static final AtomicLongFieldUpdater controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
    public static final AtomicIntegerFieldUpdater _isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");
    public static final Symbol NOT_IN_STACK = new Symbol(0, "NOT_IN_STACK");

    /* loaded from: classes.dex */
    public final class Worker extends Thread {
        public static final AtomicIntegerFieldUpdater workerCtl$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");
        private volatile int indexInArray;
        public final WorkQueue localQueue;
        public boolean mayHaveLocalTasks;
        public long minDelayUntilStealableTaskNs;
        private volatile Object nextParkedWorker;
        public int rngState;
        public int state;
        public final Ref$ObjectRef stolenTask;
        public long terminationDeadline;
        private volatile int workerCtl;

        /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
        public Worker(int i) {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.stolenTask = new Object();
            this.state = 4;
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            Random.Default.getClass();
            this.rngState = Random.defaultRandom.getImpl().nextInt();
            setIndexInArray(i);
        }

        public final Task findTask(boolean z) {
            Task pollGlobalQueues;
            Task pollGlobalQueues2;
            CoroutineScheduler coroutineScheduler;
            long j;
            int i = this.state;
            Task task = null;
            WorkQueue workQueue = this.localQueue;
            CoroutineScheduler coroutineScheduler2 = CoroutineScheduler.this;
            if (i != 1) {
                AtomicLongFieldUpdater atomicLongFieldUpdater = CoroutineScheduler.controlState$FU;
                do {
                    coroutineScheduler = CoroutineScheduler.this;
                    j = atomicLongFieldUpdater.get(coroutineScheduler);
                    if (((int) ((9223367638808264704L & j) >> 42)) == 0) {
                        workQueue.getClass();
                        loop1: while (true) {
                            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = WorkQueue.lastScheduledTask$FU;
                            Task task2 = (Task) atomicReferenceFieldUpdater.get(workQueue);
                            if (task2 == null || task2.taskContext.$r8$classId != 1) {
                                break;
                            }
                            while (!atomicReferenceFieldUpdater.compareAndSet(workQueue, task2, null)) {
                                if (atomicReferenceFieldUpdater.get(workQueue) != task2) {
                                    break;
                                }
                            }
                            task = task2;
                        }
                        int i2 = WorkQueue.consumerIndex$FU.get(workQueue);
                        int i3 = WorkQueue.producerIndex$FU.get(workQueue);
                        while (true) {
                            if (i2 == i3 || WorkQueue.blockingTasksInBuffer$FU.get(workQueue) == 0) {
                                break;
                            }
                            i3--;
                            Task tryExtractFromTheMiddle = workQueue.tryExtractFromTheMiddle(i3, true);
                            if (tryExtractFromTheMiddle != null) {
                                task = tryExtractFromTheMiddle;
                                break;
                            }
                        }
                        if (task != null) {
                            return task;
                        }
                        Task task3 = (Task) coroutineScheduler2.globalBlockingQueue.removeFirstOrNull();
                        return task3 == null ? trySteal(1) : task3;
                    }
                } while (!CoroutineScheduler.controlState$FU.compareAndSet(coroutineScheduler, j, j - 4398046511104L));
                this.state = 1;
            }
            if (z) {
                boolean z2 = nextInt(coroutineScheduler2.corePoolSize * 2) == 0;
                if (z2 && (pollGlobalQueues2 = pollGlobalQueues()) != null) {
                    return pollGlobalQueues2;
                }
                workQueue.getClass();
                Task task4 = (Task) WorkQueue.lastScheduledTask$FU.getAndSet(workQueue, null);
                if (task4 == null) {
                    task4 = workQueue.pollBuffer();
                }
                if (task4 != null) {
                    return task4;
                }
                if (!z2 && (pollGlobalQueues = pollGlobalQueues()) != null) {
                    return pollGlobalQueues;
                }
            } else {
                Task pollGlobalQueues3 = pollGlobalQueues();
                if (pollGlobalQueues3 != null) {
                    return pollGlobalQueues3;
                }
            }
            return trySteal(3);
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final int nextInt(int i) {
            int i2 = this.rngState;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.rngState = i5;
            int i6 = i - 1;
            return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
        }

        public final Task pollGlobalQueues() {
            int nextInt = nextInt(2);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            if (nextInt == 0) {
                Task task = (Task) coroutineScheduler.globalCpuQueue.removeFirstOrNull();
                return task != null ? task : (Task) coroutineScheduler.globalBlockingQueue.removeFirstOrNull();
            }
            Task task2 = (Task) coroutineScheduler.globalBlockingQueue.removeFirstOrNull();
            return task2 != null ? task2 : (Task) coroutineScheduler.globalCpuQueue.removeFirstOrNull();
        }

        /* JADX WARN: Code restructure failed: missing block: B:67:0x0004, code lost:
        
            continue;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                r20 = this;
                r1 = r20
                r2 = 0
            L3:
                r0 = r2
            L4:
                kotlinx.coroutines.scheduling.CoroutineScheduler r3 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                r3.getClass()
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = kotlinx.coroutines.scheduling.CoroutineScheduler._isTerminated$FU
                int r3 = r4.get(r3)
                r4 = 5
                if (r3 == 0) goto L15
            L12:
                r2 = r4
                goto L18d
            L15:
                int r3 = r1.state
                if (r3 == r4) goto L12
                boolean r3 = r1.mayHaveLocalTasks
                kotlinx.coroutines.scheduling.Task r3 = r1.findTask(r3)
                r5 = 3
                r6 = -2097152(0xffffffffffe00000, double:NaN)
                r8 = 0
                if (r3 == 0) goto L7c
                r1.minDelayUntilStealableTaskNs = r8
                kotlin.ULong$Companion r0 = r3.taskContext
                int r10 = r0.$r8$classId
                r1.terminationDeadline = r8
                int r0 = r1.state
                r8 = 2
                if (r0 != r5) goto L36
                r1.state = r8
            L36:
                kotlinx.coroutines.scheduling.CoroutineScheduler r5 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                if (r10 != 0) goto L3b
                goto L58
            L3b:
                boolean r0 = r1.tryReleaseCpu(r8)
                if (r0 == 0) goto L58
                boolean r0 = r5.tryUnpark()
                if (r0 == 0) goto L48
                goto L58
            L48:
                java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU
                long r8 = r0.get(r5)
                boolean r0 = r5.tryCreateWorker(r8)
                if (r0 == 0) goto L55
                goto L58
            L55:
                r5.tryUnpark()
            L58:
                r5.getClass()
                r3.run()     // Catch: java.lang.Throwable -> L5f
                goto L6c
            L5f:
                r0 = move-exception
                r3 = r0
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                java.lang.Thread$UncaughtExceptionHandler r8 = r0.getUncaughtExceptionHandler()
                r8.uncaughtException(r0, r3)
            L6c:
                if (r10 != 0) goto L6f
                goto L3
            L6f:
                java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU
                r0.addAndGet(r5, r6)
                int r0 = r1.state
                if (r0 == r4) goto L3
                r0 = 4
                r1.state = r0
                goto L3
            L7c:
                r1.mayHaveLocalTasks = r2
                long r10 = r1.minDelayUntilStealableTaskNs
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                r10 = 1
                if (r3 == 0) goto L99
                if (r0 != 0) goto L8a
                r0 = r10
                goto L4
            L8a:
                r1.tryReleaseCpu(r5)
                java.lang.Thread.interrupted()
                long r3 = r1.minDelayUntilStealableTaskNs
                java.util.concurrent.locks.LockSupport.parkNanos(r3)
                r1.minDelayUntilStealableTaskNs = r8
                goto L3
            L99:
                java.lang.Object r3 = r1.nextParkedWorker
                kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.NOT_IN_STACK
                if (r3 == r11) goto La1
                r3 = r10
                goto La2
            La1:
                r3 = r2
            La2:
                r12 = 2097151(0x1fffff, double:1.0361303E-317)
                if (r3 != 0) goto Ld7
                kotlinx.coroutines.scheduling.CoroutineScheduler r3 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                r3.getClass()
                java.lang.Object r4 = r1.nextParkedWorker
                if (r4 == r11) goto Lb2
                goto L4
            Lb2:
                java.util.concurrent.atomic.AtomicLongFieldUpdater r14 = kotlinx.coroutines.scheduling.CoroutineScheduler.parkedWorkersStack$FU
                long r16 = r14.get(r3)
                long r4 = r16 & r12
                int r4 = (int) r4
                r8 = 2097152(0x200000, double:1.0361308E-317)
                long r8 = r16 + r8
                long r8 = r8 & r6
                int r5 = r1.indexInArray
                kotlinx.coroutines.internal.ResizableAtomicArray r10 = r3.workers
                java.lang.Object r4 = r10.get(r4)
                r1.nextParkedWorker = r4
                long r4 = (long) r5
                long r18 = r8 | r4
                r15 = r3
                boolean r4 = r14.compareAndSet(r15, r16, r18)
                if (r4 == 0) goto Lb2
                goto L4
            Ld7:
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.workerCtl$FU
                r6 = -1
                r3.set(r1, r6)
            Ldd:
                java.lang.Object r3 = r1.nextParkedWorker
                kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.scheduling.CoroutineScheduler.NOT_IN_STACK
                if (r3 == r7) goto L4
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.workerCtl$FU
                int r7 = r3.get(r1)
                if (r7 != r6) goto L4
                kotlinx.coroutines.scheduling.CoroutineScheduler r7 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                r7.getClass()
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r11 = kotlinx.coroutines.scheduling.CoroutineScheduler._isTerminated$FU
                int r7 = r11.get(r7)
                if (r7 == 0) goto Lfa
                goto L4
            Lfa:
                int r7 = r1.state
                if (r7 != r4) goto L100
                goto L4
            L100:
                r1.tryReleaseCpu(r5)
                java.lang.Thread.interrupted()
                long r14 = r1.terminationDeadline
                int r7 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
                if (r7 != 0) goto L117
                long r14 = java.lang.System.nanoTime()
                kotlinx.coroutines.scheduling.CoroutineScheduler r7 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                long r4 = r7.idleWorkerKeepAliveNs
                long r14 = r14 + r4
                r1.terminationDeadline = r14
            L117:
                kotlinx.coroutines.scheduling.CoroutineScheduler r4 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                long r4 = r4.idleWorkerKeepAliveNs
                java.util.concurrent.locks.LockSupport.parkNanos(r4)
                long r4 = java.lang.System.nanoTime()
                long r14 = r1.terminationDeadline
                long r4 = r4 - r14
                int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r4 < 0) goto L189
                r1.terminationDeadline = r8
                kotlinx.coroutines.scheduling.CoroutineScheduler r4 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.coroutines.internal.ResizableAtomicArray r5 = r4.workers
                monitor-enter(r5)
                int r7 = r11.get(r4)     // Catch: java.lang.Throwable -> L17a
                if (r7 == 0) goto L138
                r7 = r10
                goto L139
            L138:
                r7 = r2
            L139:
                if (r7 == 0) goto L13d
                monitor-exit(r5)
                goto L189
            L13d:
                java.util.concurrent.atomic.AtomicLongFieldUpdater r7 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU     // Catch: java.lang.Throwable -> L17a
                long r14 = r7.get(r4)     // Catch: java.lang.Throwable -> L17a
                long r14 = r14 & r12
                int r11 = (int) r14     // Catch: java.lang.Throwable -> L17a
                int r14 = r4.corePoolSize     // Catch: java.lang.Throwable -> L17a
                if (r11 > r14) goto L14b
                monitor-exit(r5)
                goto L189
            L14b:
                boolean r3 = r3.compareAndSet(r1, r6, r10)     // Catch: java.lang.Throwable -> L17a
                if (r3 != 0) goto L153
                monitor-exit(r5)
                goto L189
            L153:
                int r3 = r1.indexInArray     // Catch: java.lang.Throwable -> L17a
                r1.setIndexInArray(r2)     // Catch: java.lang.Throwable -> L17a
                r4.parkedWorkersStackTopUpdate(r1, r3, r2)     // Catch: java.lang.Throwable -> L17a
                long r14 = r7.getAndDecrement(r4)     // Catch: java.lang.Throwable -> L17a
                long r14 = r14 & r12
                int r7 = (int) r14     // Catch: java.lang.Throwable -> L17a
                if (r7 == r3) goto L17c
                kotlinx.coroutines.internal.ResizableAtomicArray r11 = r4.workers     // Catch: java.lang.Throwable -> L17a
                java.lang.Object r11 = r11.get(r7)     // Catch: java.lang.Throwable -> L17a
                kotlin.ResultKt.checkNotNull(r11)     // Catch: java.lang.Throwable -> L17a
                kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r11 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r11     // Catch: java.lang.Throwable -> L17a
                kotlinx.coroutines.internal.ResizableAtomicArray r14 = r4.workers     // Catch: java.lang.Throwable -> L17a
                r14.setSynchronized(r3, r11)     // Catch: java.lang.Throwable -> L17a
                r11.setIndexInArray(r3)     // Catch: java.lang.Throwable -> L17a
                r4.parkedWorkersStackTopUpdate(r11, r7, r3)     // Catch: java.lang.Throwable -> L17a
                goto L17c
            L17a:
                r0 = move-exception
                goto L187
            L17c:
                kotlinx.coroutines.internal.ResizableAtomicArray r3 = r4.workers     // Catch: java.lang.Throwable -> L17a
                r4 = 0
                r3.setSynchronized(r7, r4)     // Catch: java.lang.Throwable -> L17a
                monitor-exit(r5)
                r3 = 5
                r1.state = r3
                goto L189
            L187:
                monitor-exit(r5)
                throw r0
            L189:
                r4 = 5
                r5 = 3
                goto Ldd
            L18d:
                r1.tryReleaseCpu(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.run():void");
        }

        public final void setIndexInArray(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.schedulerName);
            sb.append("-worker-");
            sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(sb.toString());
            this.indexInArray = i;
        }

        public final void setNextParkedWorker(Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryReleaseCpu(int i) {
            int i2 = this.state;
            boolean z = i2 == 1;
            if (z) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 4398046511104L);
            }
            if (i2 != i) {
                this.state = i;
            }
            return z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:53:0x0082, code lost:
        
            r19 = r6;
            r6 = -2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final kotlinx.coroutines.scheduling.Task trySteal(int r24) {
            /*
                r23 = this;
                r0 = r23
                r1 = r24
                java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU
                kotlinx.coroutines.scheduling.CoroutineScheduler r3 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                long r4 = r2.get(r3)
                r6 = 2097151(0x1fffff, double:1.0361303E-317)
                long r4 = r4 & r6
                int r2 = (int) r4
                r4 = 2
                r5 = 0
                if (r2 >= r4) goto L16
                return r5
            L16:
                int r6 = r0.nextInt(r2)
                r10 = 0
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            L20:
                if (r10 >= r2) goto Le7
                r15 = 1
                int r6 = r6 + r15
                if (r6 <= r2) goto L27
                r6 = r15
            L27:
                kotlinx.coroutines.internal.ResizableAtomicArray r4 = r3.workers
                java.lang.Object r4 = r4.get(r6)
                kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r4 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r4
                if (r4 == 0) goto Ldd
                if (r4 == r0) goto Ldd
                r7 = 3
                kotlinx.coroutines.scheduling.WorkQueue r4 = r4.localQueue
                if (r1 != r7) goto L3d
                kotlinx.coroutines.scheduling.Task r7 = r4.pollBuffer()
                goto L69
            L3d:
                r4.getClass()
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = kotlinx.coroutines.scheduling.WorkQueue.consumerIndex$FU
                int r7 = r7.get(r4)
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r8 = kotlinx.coroutines.scheduling.WorkQueue.producerIndex$FU
                int r8 = r8.get(r4)
                if (r1 != r15) goto L50
                r9 = r15
                goto L51
            L50:
                r9 = 0
            L51:
                if (r7 == r8) goto L5d
                if (r9 == 0) goto L5f
                java.util.concurrent.atomic.AtomicIntegerFieldUpdater r13 = kotlinx.coroutines.scheduling.WorkQueue.blockingTasksInBuffer$FU
                int r13 = r13.get(r4)
                if (r13 != 0) goto L5f
            L5d:
                r7 = r5
                goto L69
            L5f:
                int r13 = r7 + 1
                kotlinx.coroutines.scheduling.Task r7 = r4.tryExtractFromTheMiddle(r7, r9)
                if (r7 != 0) goto L69
                r7 = r13
                goto L51
            L69:
                kotlin.jvm.internal.Ref$ObjectRef r13 = r0.stolenTask
                if (r7 == 0) goto L76
                r13.element = r7
                r19 = r6
            L71:
                r6 = -1
            L73:
                r8 = -1
                goto Lb9
            L76:
                java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = kotlinx.coroutines.scheduling.WorkQueue.lastScheduledTask$FU
                java.lang.Object r14 = r7.get(r4)
                kotlinx.coroutines.scheduling.Task r14 = (kotlinx.coroutines.scheduling.Task) r14
                r18 = -2
                if (r14 != 0) goto L89
            L82:
                r21 = r18
                r19 = r6
                r6 = r21
                goto L73
            L89:
                kotlin.ULong$Companion r8 = r14.taskContext
                int r8 = r8.$r8$classId
                if (r8 != r15) goto L91
                r8 = r15
                goto L92
            L91:
                r8 = 2
            L92:
                r8 = r8 & r1
                if (r8 != 0) goto L96
                goto L82
            L96:
                kotlinx.coroutines.scheduling.NanoTimeSource r8 = kotlinx.coroutines.scheduling.TasksKt.schedulerTimeSource
                r8.getClass()
                long r8 = java.lang.System.nanoTime()
                r19 = r6
                long r5 = r14.submissionTime
                long r8 = r8 - r5
                long r5 = kotlinx.coroutines.scheduling.TasksKt.WORK_STEALING_TIME_RESOLUTION_NS
                int r20 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
                if (r20 >= 0) goto Laf
                long r4 = r5 - r8
                r6 = r4
                r5 = 0
                goto L73
            Laf:
                r5 = 0
                boolean r6 = r7.compareAndSet(r4, r14, r5)
                if (r6 == 0) goto Lcf
                r13.element = r14
                goto L71
            Lb9:
                int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r4 != 0) goto Lc4
                java.lang.Object r1 = r13.element
                kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
                r13.element = r5
                return r1
            Lc4:
                r16 = 0
                int r4 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
                if (r4 <= 0) goto Ldf
                long r11 = java.lang.Math.min(r11, r6)
                goto Ldf
            Lcf:
                r8 = -1
                r16 = 0
                java.lang.Object r5 = r7.get(r4)
                if (r5 == r14) goto Laf
                r6 = r19
                r5 = 0
                goto L76
            Ldd:
                r19 = r6
            Ldf:
                int r10 = r10 + 1
                r6 = r19
                r4 = 2
                r5 = 0
                goto L20
            Le7:
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r16 = 0
                int r1 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
                if (r1 == 0) goto Lf3
                goto Lf5
            Lf3:
                r11 = r16
            Lf5:
                r0.minDelayUntilStealableTaskNs = r11
                r1 = 0
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.trySteal(int):kotlinx.coroutines.scheduling.Task");
        }
    }

    /* JADX WARN: Type inference failed for: r4v11, types: [kotlinx.coroutines.scheduling.GlobalQueue, kotlinx.coroutines.internal.LockFreeTaskQueue] */
    /* JADX WARN: Type inference failed for: r4v12, types: [kotlinx.coroutines.scheduling.GlobalQueue, kotlinx.coroutines.internal.LockFreeTaskQueue] */
    public CoroutineScheduler(int i, int i2, long j, String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if (i < 1) {
            throw new IllegalArgumentException(("Core pool size " + i + " should be at least 1").toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should be greater than or equals to core pool size " + i).toString());
        }
        if (i2 > 2097150) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
        }
        this.globalCpuQueue = new LockFreeTaskQueue();
        this.globalBlockingQueue = new LockFreeTaskQueue();
        this.workers = new ResizableAtomicArray((i + 1) * 2);
        this.controlState = i << 42;
        this._isTerminated = 0;
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler coroutineScheduler, Runnable runnable, boolean z, int i) {
        ULong.Companion companion = TasksKt.NonBlockingContext;
        if ((i & 4) != 0) {
            z = false;
        }
        coroutineScheduler.dispatch(runnable, companion, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0087, code lost:
    
        if (r1 == null) goto L39;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void close() {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler._isTerminated$FU
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto Lc
            goto Laf
        Lc:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof kotlinx.coroutines.scheduling.CoroutineScheduler.Worker
            r3 = 0
            if (r1 == 0) goto L18
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r0 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r0
            goto L19
        L18:
            r0 = r3
        L19:
            if (r0 == 0) goto L24
            kotlinx.coroutines.scheduling.CoroutineScheduler r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
            boolean r1 = kotlin.ResultKt.areEqual(r1, r8)
            if (r1 == 0) goto L24
            goto L25
        L24:
            r0 = r3
        L25:
            kotlinx.coroutines.internal.ResizableAtomicArray r1 = r8.workers
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU     // Catch: java.lang.Throwable -> Lc1
            long r4 = r4.get(r8)     // Catch: java.lang.Throwable -> Lc1
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L77
            r1 = r2
        L37:
            kotlinx.coroutines.internal.ResizableAtomicArray r5 = r8.workers
            java.lang.Object r5 = r5.get(r1)
            kotlin.ResultKt.checkNotNull(r5)
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r5 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r5
            if (r5 == r0) goto L72
        L44:
            boolean r6 = r5.isAlive()
            if (r6 == 0) goto L53
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L44
        L53:
            kotlinx.coroutines.scheduling.WorkQueue r5 = r5.localQueue
            kotlinx.coroutines.scheduling.GlobalQueue r6 = r8.globalBlockingQueue
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = kotlinx.coroutines.scheduling.WorkQueue.lastScheduledTask$FU
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            kotlinx.coroutines.scheduling.Task r7 = (kotlinx.coroutines.scheduling.Task) r7
            if (r7 == 0) goto L67
            r6.addLast(r7)
        L67:
            kotlinx.coroutines.scheduling.Task r7 = r5.pollBuffer()
            if (r7 != 0) goto L6e
            goto L72
        L6e:
            r6.addLast(r7)
            goto L67
        L72:
            if (r1 == r4) goto L77
            int r1 = r1 + 1
            goto L37
        L77:
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r8.globalBlockingQueue
            r1.close()
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r8.globalCpuQueue
            r1.close()
        L81:
            if (r0 == 0) goto L89
            kotlinx.coroutines.scheduling.Task r1 = r0.findTask(r2)
            if (r1 != 0) goto Lb0
        L89:
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r8.globalCpuQueue
            java.lang.Object r1 = r1.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
            if (r1 != 0) goto Lb0
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r8.globalBlockingQueue
            java.lang.Object r1 = r1.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
            if (r1 != 0) goto Lb0
            if (r0 == 0) goto La3
            r1 = 5
            r0.tryReleaseCpu(r1)
        La3:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.parkedWorkersStack$FU
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU
            r0.set(r8, r1)
        Laf:
            return
        Lb0:
            r1.run()     // Catch: java.lang.Throwable -> Lb4
            goto L81
        Lb4:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L81
        Lc1:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.close():void");
    }

    public final int createNewWorker() {
        synchronized (this.workers) {
            try {
                if (_isTerminated$FU.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = controlState$FU;
                long j = atomicLongFieldUpdater.get(this);
                int i = (int) (j & 2097151);
                int i2 = i - ((int) ((j & 4398044413952L) >> 21));
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i2 >= this.corePoolSize) {
                    return 0;
                }
                if (i >= this.maxPoolSize) {
                    return 0;
                }
                int i3 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i3 <= 0 || this.workers.get(i3) != null) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                Worker worker = new Worker(i3);
                this.workers.setSynchronized(i3, worker);
                if (i3 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                int i4 = i2 + 1;
                worker.start();
                return i4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void dispatch(Runnable runnable, ULong.Companion companion, boolean z) {
        Task taskImpl;
        int i;
        TasksKt.schedulerTimeSource.getClass();
        long nanoTime = System.nanoTime();
        if (runnable instanceof Task) {
            taskImpl = (Task) runnable;
            taskImpl.submissionTime = nanoTime;
            taskImpl.taskContext = companion;
        } else {
            taskImpl = new TaskImpl(runnable, nanoTime, companion);
        }
        boolean z2 = false;
        boolean z3 = taskImpl.taskContext.$r8$classId == 1;
        AtomicLongFieldUpdater atomicLongFieldUpdater = controlState$FU;
        long addAndGet = z3 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread currentThread = Thread.currentThread();
        Worker worker = currentThread instanceof Worker ? (Worker) currentThread : null;
        if (worker == null || !ResultKt.areEqual(CoroutineScheduler.this, this)) {
            worker = null;
        }
        if (worker != null && (i = worker.state) != 5 && (taskImpl.taskContext.$r8$classId != 0 || i != 2)) {
            worker.mayHaveLocalTasks = true;
            WorkQueue workQueue = worker.localQueue;
            if (z) {
                taskImpl = workQueue.addLast(taskImpl);
            } else {
                workQueue.getClass();
                Task task = (Task) WorkQueue.lastScheduledTask$FU.getAndSet(workQueue, taskImpl);
                taskImpl = task == null ? null : workQueue.addLast(task);
            }
        }
        if (taskImpl != null) {
            if (!(taskImpl.taskContext.$r8$classId == 1 ? this.globalBlockingQueue.addLast(taskImpl) : this.globalCpuQueue.addLast(taskImpl))) {
                throw new RejectedExecutionException(this.schedulerName + " was terminated");
            }
        }
        if (z && worker != null) {
            z2 = true;
        }
        if (z3) {
            if (z2 || tryUnpark() || tryCreateWorker(addAndGet)) {
                return;
            }
            tryUnpark();
            return;
        }
        if (z2 || tryUnpark() || tryCreateWorker(atomicLongFieldUpdater.get(this))) {
            return;
        }
        tryUnpark();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        dispatch$default(this, runnable, false, 6);
    }

    public final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        while (true) {
            long j = parkedWorkersStack$FU.get(this);
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    Object nextParkedWorker = worker.getNextParkedWorker();
                    while (true) {
                        if (nextParkedWorker == NOT_IN_STACK) {
                            i3 = -1;
                            break;
                        }
                        if (nextParkedWorker == null) {
                            i3 = 0;
                            break;
                        }
                        Worker worker2 = (Worker) nextParkedWorker;
                        int indexInArray = worker2.getIndexInArray();
                        if (indexInArray != 0) {
                            i3 = indexInArray;
                            break;
                        }
                        nextParkedWorker = worker2.getNextParkedWorker();
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0) {
                if (parkedWorkersStack$FU.compareAndSet(this, j, i3 | j2)) {
                    return;
                }
            }
        }
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: r12v5, 100 */
    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: r12v5, 98 */
    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: r12v5, 99 */
    public final String toString() {
        ArrayList arrayList = new ArrayList();
        ResizableAtomicArray resizableAtomicArray = this.workers;
        int currentLength = resizableAtomicArray.currentLength();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < currentLength; i6++) {
            Worker worker = (Worker) resizableAtomicArray.get(i6);
            if (worker != null) {
                WorkQueue workQueue = worker.localQueue;
                workQueue.getClass();
                int i7 = WorkQueue.lastScheduledTask$FU.get(workQueue) != null ? (WorkQueue.producerIndex$FU.get(workQueue) - WorkQueue.consumerIndex$FU.get(workQueue)) + 1 : WorkQueue.producerIndex$FU.get(workQueue) - WorkQueue.consumerIndex$FU.get(workQueue);
                int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(worker.state);
                if (ordinal == 0) {
                    i++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i7);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (ordinal == 1) {
                    i2++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i7);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (ordinal == 2) {
                    i3++;
                } else if (ordinal == 3) {
                    i4++;
                    if (i7 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i7);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (ordinal == 4) {
                    i5++;
                }
            }
        }
        long j = controlState$FU.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.schedulerName);
        sb4.append('@');
        sb4.append(_BOUNDARY.getHexAddress(this));
        sb4.append("[Pool Size {core = ");
        int i8 = this.corePoolSize;
        sb4.append(i8);
        sb4.append(", max = ");
        sb4.append(this.maxPoolSize);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i);
        sb4.append(", blocking = ");
        sb4.append(i2);
        sb4.append(", parked = ");
        sb4.append(i3);
        sb4.append(", dormant = ");
        sb4.append(i4);
        sb4.append(", terminated = ");
        sb4.append(i5);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.globalCpuQueue.getSize());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.globalBlockingQueue.getSize());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i8 - ((int) ((j & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }

    public final boolean tryCreateWorker(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        int i2 = this.corePoolSize;
        if (i < i2) {
            int createNewWorker = createNewWorker();
            if (createNewWorker == 1 && i2 > 1) {
                createNewWorker();
            }
            if (createNewWorker > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean tryUnpark() {
        Symbol symbol;
        int i;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = parkedWorkersStack$FU;
            long j = atomicLongFieldUpdater.get(this);
            Worker worker = (Worker) this.workers.get((int) (2097151 & j));
            if (worker == null) {
                worker = null;
            } else {
                long j2 = (2097152 + j) & (-2097152);
                Object nextParkedWorker = worker.getNextParkedWorker();
                while (true) {
                    symbol = NOT_IN_STACK;
                    if (nextParkedWorker == symbol) {
                        i = -1;
                        break;
                    }
                    if (nextParkedWorker == null) {
                        i = 0;
                        break;
                    }
                    Worker worker2 = (Worker) nextParkedWorker;
                    i = worker2.getIndexInArray();
                    if (i != 0) {
                        break;
                    }
                    nextParkedWorker = worker2.getNextParkedWorker();
                }
                if (i >= 0 && atomicLongFieldUpdater.compareAndSet(this, j, j2 | i)) {
                    worker.setNextParkedWorker(symbol);
                }
            }
            if (worker == null) {
                return false;
            }
            if (Worker.workerCtl$FU.compareAndSet(worker, -1, 0)) {
                LockSupport.unpark(worker);
                return true;
            }
        }
    }
}
