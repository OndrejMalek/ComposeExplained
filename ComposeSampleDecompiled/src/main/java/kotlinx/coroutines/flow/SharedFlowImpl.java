package kotlinx.coroutines.flow;

import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.InvokeOnCancel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.ChannelFlowOperator;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public class SharedFlowImpl extends AbstractSharedFlow implements MutableSharedFlow, Flow, FusibleFlow {
    public Object[] buffer;
    public final int bufferCapacity;
    public int bufferSize;
    public long minCollectorIndex;
    public final BufferOverflow onBufferOverflow;
    public int queueSize;
    public final int replay;
    public long replayIndex;

    /* loaded from: classes.dex */
    public final class Emitter implements DisposableHandle {
        public final Continuation cont;
        public final SharedFlowImpl flow;
        public final long index;
        public final Object value;

        public Emitter(SharedFlowImpl sharedFlowImpl, long j, Object obj, CancellableContinuationImpl cancellableContinuationImpl) {
            this.flow = sharedFlowImpl;
            this.index = j;
            this.value = obj;
            this.cont = cancellableContinuationImpl;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            SharedFlowImpl sharedFlowImpl = this.flow;
            synchronized (sharedFlowImpl) {
                if (this.index < sharedFlowImpl.getHead()) {
                    return;
                }
                Object[] objArr = sharedFlowImpl.buffer;
                ResultKt.checkNotNull(objArr);
                long j = this.index;
                if (objArr[((int) j) & (objArr.length - 1)] != this) {
                    return;
                }
                StateFlowKt.access$setBufferAt(objArr, j, StateFlowKt.NO_VALUE);
                sharedFlowImpl.cleanupTailLocked();
            }
        }
    }

    public SharedFlowImpl(int i, int i2, BufferOverflow bufferOverflow) {
        this.replay = i;
        this.bufferCapacity = i2;
        this.onBufferOverflow = bufferOverflow;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:56:0x0038 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:7:0x0020 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0082 A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:14:0x0031, B:18:0x007a, B:20:0x0082, B:28:0x0095, B:31:0x009c, B:32:0x00a2, B:34:0x00a3, B:40:0x004b), top: B:7:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0093 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /* JADX WARN: Type inference failed for: r5v1, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlow] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [kotlinx.coroutines.flow.SharedFlowImpl] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r9v0, types: [kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r9v8, types: [kotlinx.coroutines.flow.SharedFlowSlot] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00b1 -> B:15:0x0034). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void collect$suspendImpl(kotlinx.coroutines.flow.SharedFlowImpl r8, kotlinx.coroutines.flow.FlowCollector r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.SharedFlowImpl$collect$1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.flow.SharedFlowImpl$collect$1 r0 = (kotlinx.coroutines.flow.SharedFlowImpl$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.SharedFlowImpl$collect$1 r0 = new kotlinx.coroutines.flow.SharedFlowImpl$collect$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 2
            if (r2 == 0) goto L5e
            r8 = 1
            if (r2 == r8) goto L4f
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            kotlinx.coroutines.Job r8 = r0.L$3
            kotlinx.coroutines.flow.SharedFlowSlot r9 = r0.L$2
            kotlinx.coroutines.flow.FlowCollector r2 = r0.L$1
            kotlinx.coroutines.flow.SharedFlowImpl r5 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L38
        L34:
            r10 = r2
            r2 = r8
            r8 = r5
            goto L77
        L38:
            r8 = move-exception
            goto Lb7
        L3b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L43:
            kotlinx.coroutines.Job r8 = r0.L$3
            kotlinx.coroutines.flow.SharedFlowSlot r9 = r0.L$2
            kotlinx.coroutines.flow.FlowCollector r2 = r0.L$1
            kotlinx.coroutines.flow.SharedFlowImpl r5 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L38
            goto L7a
        L4f:
            kotlinx.coroutines.flow.SharedFlowSlot r9 = r0.L$2
            kotlinx.coroutines.flow.FlowCollector r8 = r0.L$1
            kotlinx.coroutines.flow.SharedFlowImpl r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L5b
            r10 = r8
            r8 = r2
            goto L6a
        L5b:
            r8 = move-exception
            r5 = r2
            goto Lb7
        L5e:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot r10 = r8.allocateSlot()
            kotlinx.coroutines.flow.SharedFlowSlot r10 = (kotlinx.coroutines.flow.SharedFlowSlot) r10
            r7 = r10
            r10 = r9
            r9 = r7
        L6a:
            kotlin.coroutines.CoroutineContext r2 = r0._context     // Catch: java.lang.Throwable -> Lb4
            kotlin.ResultKt.checkNotNull(r2)     // Catch: java.lang.Throwable -> Lb4
            kotlinx.coroutines.Job$Key r5 = kotlinx.coroutines.Job.Key.$$INSTANCE     // Catch: java.lang.Throwable -> Lb4
            kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r5)     // Catch: java.lang.Throwable -> Lb4
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2     // Catch: java.lang.Throwable -> Lb4
        L77:
            r5 = r8
            r8 = r2
            r2 = r10
        L7a:
            java.lang.Object r10 = r5.tryTakeValue(r9)     // Catch: java.lang.Throwable -> L38
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.flow.StateFlowKt.NO_VALUE     // Catch: java.lang.Throwable -> L38
            if (r10 != r6) goto L93
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L38
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L38
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L38
            r0.L$3 = r8     // Catch: java.lang.Throwable -> L38
            r0.label = r4     // Catch: java.lang.Throwable -> L38
            java.lang.Object r10 = r5.awaitValue(r9, r0)     // Catch: java.lang.Throwable -> L38
            if (r10 != r1) goto L7a
            return
        L93:
            if (r8 == 0) goto La3
            boolean r6 = r8.isActive()     // Catch: java.lang.Throwable -> L38
            if (r6 == 0) goto L9c
            goto La3
        L9c:
            kotlinx.coroutines.JobSupport r8 = (kotlinx.coroutines.JobSupport) r8     // Catch: java.lang.Throwable -> L38
            java.util.concurrent.CancellationException r8 = r8.getCancellationException()     // Catch: java.lang.Throwable -> L38
            throw r8     // Catch: java.lang.Throwable -> L38
        La3:
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L38
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L38
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L38
            r0.L$3 = r8     // Catch: java.lang.Throwable -> L38
            r0.label = r3     // Catch: java.lang.Throwable -> L38
            java.lang.Object r10 = r2.emit(r10, r0)     // Catch: java.lang.Throwable -> L38
            if (r10 != r1) goto L34
            return
        Lb4:
            r10 = move-exception
            r5 = r8
            r8 = r10
        Lb7:
            r5.freeSlot(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.collect$suspendImpl(kotlinx.coroutines.flow.SharedFlowImpl, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):void");
    }

    public final Object awaitValue(SharedFlowSlot sharedFlowSlot, SharedFlowImpl$collect$1 sharedFlowImpl$collect$1) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(sharedFlowImpl$collect$1));
        cancellableContinuationImpl.initCancellability();
        synchronized (this) {
            if (tryPeekLocked(sharedFlowSlot) < 0) {
                sharedFlowSlot.cont = cancellableContinuationImpl;
            } else {
                cancellableContinuationImpl.resumeWith(Unit.INSTANCE);
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    public final void cleanupTailLocked() {
        if (this.bufferCapacity != 0 || this.queueSize > 1) {
            Object[] objArr = this.buffer;
            ResultKt.checkNotNull(objArr);
            while (this.queueSize > 0) {
                long head = getHead();
                int i = this.bufferSize;
                int i2 = this.queueSize;
                if (objArr[((int) ((head + (i + i2)) - 1)) & (objArr.length - 1)] != StateFlowKt.NO_VALUE) {
                    return;
                }
                this.queueSize = i2 - 1;
                StateFlowKt.access$setBufferAt(objArr, getHead() + this.bufferSize + this.queueSize, null);
            }
        }
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        collect$suspendImpl(this, flowCollector, continuation);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot, kotlinx.coroutines.flow.SharedFlowSlot, java.lang.Object] */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final AbstractSharedFlowSlot createSlot() {
        ?? obj = new Object();
        obj.index = -1L;
        return obj;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final AbstractSharedFlowSlot[] createSlotArray() {
        return new SharedFlowSlot[2];
    }

    public final void dropOldestLocked() {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        Object[] objArr = this.buffer;
        ResultKt.checkNotNull(objArr);
        StateFlowKt.access$setBufferAt(objArr, getHead(), null);
        this.bufferSize--;
        long head = getHead() + 1;
        if (this.replayIndex < head) {
            this.replayIndex = head;
        }
        if (this.minCollectorIndex < head) {
            if (this.nCollectors != 0 && (abstractSharedFlowSlotArr = this.slots) != null) {
                for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
                    if (abstractSharedFlowSlot != null) {
                        SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                        long j = sharedFlowSlot.index;
                        if (j >= 0 && j < head) {
                            sharedFlowSlot.index = head;
                        }
                    }
                }
            }
            this.minCollectorIndex = head;
        }
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Continuation[] continuationArr;
        Emitter emitter;
        if (tryEmit(obj)) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        Continuation[] continuationArr2 = NullSurrogateKt.EMPTY_RESUMES;
        synchronized (this) {
            try {
                if (tryEmitLocked(obj)) {
                    cancellableContinuationImpl.resumeWith(Unit.INSTANCE);
                    continuationArr = findSlotsToResumeLocked(continuationArr2);
                    emitter = null;
                } else {
                    Emitter emitter2 = new Emitter(this, this.bufferSize + this.queueSize + getHead(), obj, cancellableContinuationImpl);
                    enqueueLocked(emitter2);
                    this.queueSize++;
                    if (this.bufferCapacity == 0) {
                        continuationArr2 = findSlotsToResumeLocked(continuationArr2);
                    }
                    continuationArr = continuationArr2;
                    emitter = emitter2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (emitter != null) {
            cancellableContinuationImpl.invokeOnCancellation(new InvokeOnCancel(1, emitter));
        }
        for (Continuation continuation2 : continuationArr) {
            if (continuation2 != null) {
                continuation2.resumeWith(Unit.INSTANCE);
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = Unit.INSTANCE;
        }
        return result == coroutineSingletons ? result : Unit.INSTANCE;
    }

    public final void enqueueLocked(Object obj) {
        int i = this.bufferSize + this.queueSize;
        Object[] objArr = this.buffer;
        if (objArr == null) {
            objArr = growBuffer(0, 2, null);
        } else if (i >= objArr.length) {
            objArr = growBuffer(i, objArr.length * 2, objArr);
        }
        StateFlowKt.access$setBufferAt(objArr, getHead() + i, obj);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:21:0x0040 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:22:0x0043 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object[], java.lang.Object] */
    public final Continuation[] findSlotsToResumeLocked(Continuation[] continuationArr) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        SharedFlowSlot sharedFlowSlot;
        CancellableContinuationImpl cancellableContinuationImpl;
        int length = continuationArr.length;
        if (this.nCollectors != 0 && (abstractSharedFlowSlotArr = this.slots) != null) {
            int length2 = abstractSharedFlowSlotArr.length;
            int i = 0;
            continuationArr = continuationArr;
            while (i < length2) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i];
                if (abstractSharedFlowSlot != null && (cancellableContinuationImpl = (sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot).cont) != null && tryPeekLocked(sharedFlowSlot) >= 0) {
                    int length3 = continuationArr.length;
                    continuationArr = continuationArr;
                    if (length >= length3) {
                        ?? copyOf = Arrays.copyOf(continuationArr, Math.max(2, continuationArr.length * 2));
                        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                        continuationArr = copyOf;
                    }
                    continuationArr[length] = cancellableContinuationImpl;
                    sharedFlowSlot.cont = null;
                    length++;
                }
                i++;
                continuationArr = continuationArr;
            }
        }
        return continuationArr;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public final Flow fuse(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        return ((i == 0 || i == -3) && bufferOverflow == BufferOverflow.SUSPEND) ? this : new ChannelFlowOperator(i, coroutineContext, bufferOverflow, this);
    }

    public final long getHead() {
        return Math.min(this.minCollectorIndex, this.replayIndex);
    }

    public final Object[] growBuffer(int i, int i2, Object[] objArr) {
        if (i2 <= 0) {
            throw new IllegalStateException("Buffer size overflow".toString());
        }
        Object[] objArr2 = new Object[i2];
        this.buffer = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long head = getHead();
        for (int i3 = 0; i3 < i; i3++) {
            long j = i3 + head;
            StateFlowKt.access$setBufferAt(objArr2, j, objArr[((int) j) & (objArr.length - 1)]);
        }
        return objArr2;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public final void resetReplayCache() {
        synchronized (this) {
            updateBufferLocked(getHead() + this.bufferSize, this.minCollectorIndex, getHead() + this.bufferSize, getHead() + this.bufferSize + this.queueSize);
        }
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public final boolean tryEmit(Object obj) {
        int i;
        boolean z;
        Continuation[] continuationArr = NullSurrogateKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(obj)) {
                continuationArr = findSlotsToResumeLocked(continuationArr);
                z = true;
            } else {
                z = false;
            }
        }
        for (Continuation continuation : continuationArr) {
            if (continuation != null) {
                continuation.resumeWith(Unit.INSTANCE);
            }
        }
        return z;
    }

    public final boolean tryEmitLocked(Object obj) {
        int i = this.nCollectors;
        int i2 = this.replay;
        if (i == 0) {
            if (i2 != 0) {
                enqueueLocked(obj);
                int i3 = this.bufferSize + 1;
                this.bufferSize = i3;
                if (i3 > i2) {
                    dropOldestLocked();
                }
                this.minCollectorIndex = getHead() + this.bufferSize;
            }
            return true;
        }
        int i4 = this.bufferSize;
        int i5 = this.bufferCapacity;
        if (i4 >= i5 && this.minCollectorIndex <= this.replayIndex) {
            int ordinal = this.onBufferOverflow.ordinal();
            if (ordinal == 0) {
                return false;
            }
            if (ordinal == 2) {
                return true;
            }
        }
        enqueueLocked(obj);
        int i6 = this.bufferSize + 1;
        this.bufferSize = i6;
        if (i6 > i5) {
            dropOldestLocked();
        }
        long head = getHead() + this.bufferSize;
        long j = this.replayIndex;
        if (((int) (head - j)) > i2) {
            updateBufferLocked(j + 1, this.minCollectorIndex, getHead() + this.bufferSize, getHead() + this.bufferSize + this.queueSize);
        }
        return true;
    }

    public final long tryPeekLocked(SharedFlowSlot sharedFlowSlot) {
        long j = sharedFlowSlot.index;
        if (j < getHead() + this.bufferSize) {
            return j;
        }
        if (this.bufferCapacity <= 0 && j <= getHead() && this.queueSize != 0) {
            return j;
        }
        return -1L;
    }

    public final Object tryTakeValue(SharedFlowSlot sharedFlowSlot) {
        Object obj;
        Continuation[] continuationArr = NullSurrogateKt.EMPTY_RESUMES;
        synchronized (this) {
            try {
                long tryPeekLocked = tryPeekLocked(sharedFlowSlot);
                if (tryPeekLocked < 0) {
                    obj = StateFlowKt.NO_VALUE;
                } else {
                    long j = sharedFlowSlot.index;
                    Object[] objArr = this.buffer;
                    ResultKt.checkNotNull(objArr);
                    Object obj2 = objArr[((int) tryPeekLocked) & (objArr.length - 1)];
                    if (obj2 instanceof Emitter) {
                        obj2 = ((Emitter) obj2).value;
                    }
                    sharedFlowSlot.index = tryPeekLocked + 1;
                    Object obj3 = obj2;
                    continuationArr = updateCollectorIndexLocked$kotlinx_coroutines_core(j);
                    obj = obj3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (Continuation continuation : continuationArr) {
            if (continuation != null) {
                continuation.resumeWith(Unit.INSTANCE);
            }
        }
        return obj;
    }

    public final void updateBufferLocked(long j, long j2, long j3, long j4) {
        long min = Math.min(j2, j);
        for (long head = getHead(); head < min; head++) {
            Object[] objArr = this.buffer;
            ResultKt.checkNotNull(objArr);
            StateFlowKt.access$setBufferAt(objArr, head, null);
        }
        this.replayIndex = j;
        this.minCollectorIndex = j2;
        this.bufferSize = (int) (j3 - min);
        this.queueSize = (int) (j4 - j3);
    }

    public final Continuation[] updateCollectorIndexLocked$kotlinx_coroutines_core(long j) {
        long j2;
        long j3;
        Continuation[] continuationArr;
        long j4;
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        long j5 = this.minCollectorIndex;
        Continuation[] continuationArr2 = NullSurrogateKt.EMPTY_RESUMES;
        if (j > j5) {
            return continuationArr2;
        }
        long head = getHead();
        long j6 = this.bufferSize + head;
        int i = this.bufferCapacity;
        if (i == 0 && this.queueSize > 0) {
            j6++;
        }
        if (this.nCollectors != 0 && (abstractSharedFlowSlotArr = this.slots) != null) {
            for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
                if (abstractSharedFlowSlot != null) {
                    long j7 = ((SharedFlowSlot) abstractSharedFlowSlot).index;
                    if (j7 >= 0 && j7 < j6) {
                        j6 = j7;
                    }
                }
            }
        }
        if (j6 <= this.minCollectorIndex) {
            return continuationArr2;
        }
        long head2 = getHead() + this.bufferSize;
        int min = this.nCollectors > 0 ? Math.min(this.queueSize, i - ((int) (head2 - j6))) : this.queueSize;
        long j8 = this.queueSize + head2;
        Symbol symbol = StateFlowKt.NO_VALUE;
        if (min > 0) {
            Continuation[] continuationArr3 = new Continuation[min];
            Object[] objArr = this.buffer;
            ResultKt.checkNotNull(objArr);
            long j9 = head2;
            int i2 = 0;
            while (true) {
                if (head2 >= j8) {
                    j2 = j6;
                    j3 = j8;
                    break;
                }
                j2 = j6;
                Object obj = objArr[((int) head2) & (objArr.length - 1)];
                if (obj != symbol) {
                    ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    Emitter emitter = (Emitter) obj;
                    int i3 = i2 + 1;
                    j3 = j8;
                    continuationArr3[i2] = emitter.cont;
                    StateFlowKt.access$setBufferAt(objArr, head2, symbol);
                    StateFlowKt.access$setBufferAt(objArr, j9, emitter.value);
                    j4 = 1;
                    j9++;
                    if (i3 >= min) {
                        break;
                    }
                    i2 = i3;
                } else {
                    j3 = j8;
                    j4 = 1;
                }
                head2 += j4;
                j6 = j2;
                j8 = j3;
            }
            continuationArr = continuationArr3;
            head2 = j9;
        } else {
            j2 = j6;
            j3 = j8;
            continuationArr = continuationArr2;
        }
        int i4 = (int) (head2 - head);
        long j10 = this.nCollectors == 0 ? head2 : j2;
        long max = Math.max(this.replayIndex, head2 - Math.min(this.replay, i4));
        if (i == 0 && max < j3) {
            Object[] objArr2 = this.buffer;
            ResultKt.checkNotNull(objArr2);
            if (ResultKt.areEqual(objArr2[((int) max) & (objArr2.length - 1)], symbol)) {
                head2++;
                max++;
            }
        }
        updateBufferLocked(max, j10, head2, j3);
        cleanupTailLocked();
        return (continuationArr.length == 0) ^ true ? findSlotsToResumeLocked(continuationArr) : continuationArr;
    }
}
