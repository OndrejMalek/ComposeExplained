package kotlinx.coroutines.channels;

import androidx.startup.StartupException;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt$bindCancellationFun$1;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public class BufferedChannel implements Channel {
    private volatile Object _closeCause;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    public final int capacity;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    public final Function1 onUndeliveredElement;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;
    public static final AtomicLongFieldUpdater sendersAndCloseStatus$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus");
    public static final AtomicLongFieldUpdater receivers$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers");
    public static final AtomicLongFieldUpdater bufferEnd$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd");
    public static final AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag");
    public static final AtomicReferenceFieldUpdater sendSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment");
    public static final AtomicReferenceFieldUpdater receiveSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment");
    public static final AtomicReferenceFieldUpdater bufferEndSegment$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment");
    public static final AtomicReferenceFieldUpdater _closeCause$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause");
    public static final AtomicReferenceFieldUpdater closeHandler$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler");

    /* loaded from: classes.dex */
    public final class BufferedChannelIterator implements Waiter {
        public CancellableContinuationImpl continuation;
        public Object receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;

        public BufferedChannelIterator() {
        }

        public final Object hasNext(Continuation continuation) {
            CancellableContinuationImpl cancellableContinuationImpl;
            Boolean bool;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = BufferedChannel.receiveSegment$FU;
            BufferedChannel bufferedChannel = BufferedChannel.this;
            ChannelSegment channelSegment = (ChannelSegment) atomicReferenceFieldUpdater.get(bufferedChannel);
            while (true) {
                bufferedChannel.getClass();
                if (bufferedChannel.isClosed(BufferedChannel.sendersAndCloseStatus$FU.get(bufferedChannel), true)) {
                    this.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
                    Throwable closeCause = bufferedChannel.getCloseCause();
                    if (closeCause == null) {
                        return Boolean.FALSE;
                    }
                    int i = StackTraceRecoveryKt.$r8$clinit;
                    throw closeCause;
                }
                long andIncrement = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel);
                long j = BufferedChannelKt.SEGMENT_SIZE;
                long j2 = andIncrement / j;
                int i2 = (int) (andIncrement % j);
                if (channelSegment.id != j2) {
                    ChannelSegment findSegmentReceive = bufferedChannel.findSegmentReceive(j2, channelSegment);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentReceive;
                    }
                }
                Object updateCellReceive = bufferedChannel.updateCellReceive(channelSegment, i2, andIncrement, null);
                Symbol symbol = BufferedChannelKt.SUSPEND;
                if (updateCellReceive == symbol) {
                    throw new IllegalStateException("unreachable".toString());
                }
                Symbol symbol2 = BufferedChannelKt.FAILED;
                if (updateCellReceive != symbol2) {
                    if (updateCellReceive != BufferedChannelKt.SUSPEND_NO_WAITER) {
                        channelSegment.cleanPrev();
                        this.receiveResult = updateCellReceive;
                        return Boolean.TRUE;
                    }
                    BufferedChannel bufferedChannel2 = BufferedChannel.this;
                    CancellableContinuationImpl orCreateCancellableContinuation = ResultKt.getOrCreateCancellableContinuation(ResultKt.intercepted(continuation));
                    try {
                        this.continuation = orCreateCancellableContinuation;
                        cancellableContinuationImpl = orCreateCancellableContinuation;
                    } catch (Throwable th) {
                        th = th;
                        cancellableContinuationImpl = orCreateCancellableContinuation;
                    }
                    try {
                        Object updateCellReceive2 = bufferedChannel2.updateCellReceive(channelSegment, i2, andIncrement, this);
                        if (updateCellReceive2 == symbol) {
                            invokeOnCancellation(channelSegment, i2);
                        } else {
                            OnUndeliveredElementKt$bindCancellationFun$1 onUndeliveredElementKt$bindCancellationFun$1 = null;
                            CoroutineContext coroutineContext = cancellableContinuationImpl.context;
                            Function1 function1 = bufferedChannel2.onUndeliveredElement;
                            if (updateCellReceive2 == symbol2) {
                                if (andIncrement < bufferedChannel2.getSendersCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.receiveSegment$FU.get(bufferedChannel2);
                                while (true) {
                                    if (bufferedChannel2.isClosed(BufferedChannel.sendersAndCloseStatus$FU.get(bufferedChannel2), true)) {
                                        CancellableContinuationImpl cancellableContinuationImpl2 = this.continuation;
                                        ResultKt.checkNotNull(cancellableContinuationImpl2);
                                        this.continuation = null;
                                        this.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
                                        Throwable closeCause2 = bufferedChannel.getCloseCause();
                                        if (closeCause2 == null) {
                                            cancellableContinuationImpl2.resumeWith(Boolean.FALSE);
                                        } else {
                                            cancellableContinuationImpl2.resumeWith(ResultKt.createFailure(closeCause2));
                                        }
                                    } else {
                                        long andIncrement2 = BufferedChannel.receivers$FU.getAndIncrement(bufferedChannel2);
                                        long j3 = BufferedChannelKt.SEGMENT_SIZE;
                                        long j4 = andIncrement2 / j3;
                                        int i3 = (int) (andIncrement2 % j3);
                                        if (channelSegment2.id != j4) {
                                            ChannelSegment findSegmentReceive2 = bufferedChannel2.findSegmentReceive(j4, channelSegment2);
                                            if (findSegmentReceive2 != null) {
                                                channelSegment2 = findSegmentReceive2;
                                            }
                                        }
                                        Function1 function12 = function1;
                                        Object updateCellReceive3 = bufferedChannel2.updateCellReceive(channelSegment2, i3, andIncrement2, this);
                                        if (updateCellReceive3 == BufferedChannelKt.SUSPEND) {
                                            invokeOnCancellation(channelSegment2, i3);
                                            break;
                                        }
                                        if (updateCellReceive3 == BufferedChannelKt.FAILED) {
                                            if (andIncrement2 < bufferedChannel2.getSendersCounter$kotlinx_coroutines_core()) {
                                                channelSegment2.cleanPrev();
                                            }
                                            function1 = function12;
                                        } else {
                                            if (updateCellReceive3 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                                throw new IllegalStateException("unexpected".toString());
                                            }
                                            channelSegment2.cleanPrev();
                                            this.receiveResult = updateCellReceive3;
                                            this.continuation = null;
                                            bool = Boolean.TRUE;
                                            if (function12 != null) {
                                                onUndeliveredElementKt$bindCancellationFun$1 = new OnUndeliveredElementKt$bindCancellationFun$1(function12, updateCellReceive3, coroutineContext);
                                            }
                                        }
                                    }
                                }
                            } else {
                                channelSegment.cleanPrev();
                                this.receiveResult = updateCellReceive2;
                                this.continuation = null;
                                bool = Boolean.TRUE;
                                if (function1 != null) {
                                    onUndeliveredElementKt$bindCancellationFun$1 = new OnUndeliveredElementKt$bindCancellationFun$1(function1, updateCellReceive2, coroutineContext);
                                }
                            }
                            cancellableContinuationImpl.resume(bool, onUndeliveredElementKt$bindCancellationFun$1);
                        }
                        return cancellableContinuationImpl.getResult();
                    } catch (Throwable th2) {
                        th = th2;
                        cancellableContinuationImpl.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                        throw th;
                    }
                }
                if (andIncrement < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
            }
        }

        @Override // kotlinx.coroutines.Waiter
        public final void invokeOnCancellation(Segment segment, int i) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, i);
            }
        }

        public final Object next() {
            Object obj = this.receiveResult;
            Symbol symbol = BufferedChannelKt.NO_RECEIVE_RESULT;
            if (obj == symbol) {
                throw new IllegalStateException("`hasNext()` has not been invoked".toString());
            }
            this.receiveResult = symbol;
            if (obj != BufferedChannelKt.CHANNEL_CLOSED) {
                return obj;
            }
            Throwable closeCause = BufferedChannel.this.getCloseCause();
            if (closeCause == null) {
                closeCause = new NoSuchElementException("Channel was closed");
            }
            int i = StackTraceRecoveryKt.$r8$clinit;
            throw closeCause;
        }
    }

    public BufferedChannel(int i, Function1 function1) {
        this.capacity = i;
        this.onUndeliveredElement = function1;
        if (i < 0) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + i + ", should be >=0").toString());
        }
        ChannelSegment channelSegment = BufferedChannelKt.NULL_SEGMENT;
        this.bufferEnd = i != 0 ? i != Integer.MAX_VALUE ? i : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag = bufferEnd$FU.get(this);
        ChannelSegment channelSegment2 = new ChannelSegment(0L, null, this, 3);
        this.sendSegment = channelSegment2;
        this.receiveSegment = channelSegment2;
        if (isRendezvousOrUnlimited()) {
            channelSegment2 = BufferedChannelKt.NULL_SEGMENT;
            ResultKt.checkNotNull(channelSegment2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = channelSegment2;
        this._closeCause = BufferedChannelKt.NO_CLOSE_CAUSE;
    }

    public static final ChannelSegment access$findSegmentSend(BufferedChannel bufferedChannel, long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        long j3;
        bufferedChannel.getClass();
        ChannelSegment channelSegment2 = BufferedChannelKt.NULL_SEGMENT;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.INSTANCE;
        loop0: while (true) {
            findSegmentInternal = AtomicKt.findSegmentInternal(channelSegment, j, bufferedChannelKt$createSegmentFunction$1);
            if (!JobKt.m304isClosedimpl(findSegmentInternal)) {
                Segment m303getSegmentimpl = JobKt.m303getSegmentimpl(findSegmentInternal);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = sendSegment$FU;
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(bufferedChannel);
                    if (segment.id >= m303getSegmentimpl.id) {
                        break loop0;
                    }
                    if (!m303getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(bufferedChannel, segment, m303getSegmentimpl)) {
                        if (atomicReferenceFieldUpdater.get(bufferedChannel) != segment) {
                            if (m303getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                                m303getSegmentimpl.remove();
                            }
                        }
                    }
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                }
            } else {
                break;
            }
        }
        boolean m304isClosedimpl = JobKt.m304isClosedimpl(findSegmentInternal);
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = receivers$FU;
        if (m304isClosedimpl) {
            bufferedChannel.completeCloseOrCancel();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE >= atomicLongFieldUpdater2.get(bufferedChannel)) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) JobKt.m303getSegmentimpl(findSegmentInternal);
        long j4 = channelSegment3.id;
        if (j4 <= j) {
            return channelSegment3;
        }
        long j5 = BufferedChannelKt.SEGMENT_SIZE * j4;
        do {
            atomicLongFieldUpdater = sendersAndCloseStatus$FU;
            j2 = atomicLongFieldUpdater.get(bufferedChannel);
            j3 = 1152921504606846975L & j2;
            if (j3 >= j5) {
                break;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(bufferedChannel, j2, j3 + (((int) (j2 >> 60)) << 60)));
        if (j4 * BufferedChannelKt.SEGMENT_SIZE >= atomicLongFieldUpdater2.get(bufferedChannel)) {
            return null;
        }
        channelSegment3.cleanPrev();
        return null;
    }

    public static final void access$onClosedSendOnNoWaiterSuspend(BufferedChannel bufferedChannel, Object obj, CancellableContinuationImpl cancellableContinuationImpl) {
        StartupException callUndeliveredElementCatchingException;
        Function1 function1 = bufferedChannel.onUndeliveredElement;
        if (function1 != null && (callUndeliveredElementCatchingException = JobKt.callUndeliveredElementCatchingException(function1, obj, null)) != null) {
            ResultKt.handleCoroutineException(cancellableContinuationImpl.context, callUndeliveredElementCatchingException);
        }
        cancellableContinuationImpl.resumeWith(ResultKt.createFailure(bufferedChannel.getSendException()));
    }

    public static final int access$updateCellSend(BufferedChannel bufferedChannel, ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        bufferedChannel.getClass();
        channelSegment.setElementLazy(i, obj);
        if (z) {
            return bufferedChannel.updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
        }
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        if (state$kotlinx_coroutines_core == null) {
            if (bufferedChannel.bufferOrRendezvousSend(j)) {
                if (channelSegment.casState$kotlinx_coroutines_core(null, i, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(null, i, obj2)) {
                    return 2;
                }
            }
        } else if (state$kotlinx_coroutines_core instanceof Waiter) {
            channelSegment.setElementLazy(i, null);
            if (bufferedChannel.tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                return 0;
            }
            Symbol symbol = BufferedChannelKt.INTERRUPTED_RCV;
            if (channelSegment.data.getAndSet((i * 2) + 1, symbol) != symbol) {
                channelSegment.onCancelledRequest(i, true);
            }
            return 5;
        }
        return bufferedChannel.updateCellSendSlow(channelSegment, i, obj, j, obj2, z);
    }

    public static void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel) {
        bufferedChannel.getClass();
        AtomicLongFieldUpdater atomicLongFieldUpdater = completedExpandBuffersAndPauseFlag$FU;
        if ((atomicLongFieldUpdater.addAndGet(bufferedChannel, 1L) & 4611686018427387904L) == 0) {
            return;
        }
        do {
        } while ((atomicLongFieldUpdater.get(bufferedChannel) & 4611686018427387904L) != 0);
    }

    public static boolean tryResumeSender(Object obj) {
        if (obj instanceof CancellableContinuation) {
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.tryResume0((CancellableContinuation) obj, Unit.INSTANCE, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public final boolean bufferOrRendezvousSend(long j) {
        return j < bufferEnd$FU.get(this) || j < receivers$FU.get(this) + ((long) this.capacity);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        closeOrCancelImpl(cancellationException, true);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean close(Throwable th) {
        return closeOrCancelImpl(th, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
    
        r3 = kotlinx.coroutines.channels.BufferedChannelKt.NO_CLOSE_CAUSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
    
        r4 = kotlinx.coroutines.channels.BufferedChannel._closeCause$FU;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
    
        if (r4.compareAndSet(r13, r3, r14) == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
    
        if (r4.get(r13) == r3) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
    
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        if (r15 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
    
        r5 = r9.get(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        if (r9.compareAndSet(r13, r5, (3 << 60) + (r5 & 1152921504606846975L)) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
    
        completeCloseOrCancel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0071, code lost:
    
        if (r11 == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0073, code lost:
    
        r14 = kotlinx.coroutines.channels.BufferedChannel.closeHandler$FU;
        r15 = r14.get(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0079, code lost:
    
        if (r15 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007b, code lost:
    
        r0 = kotlinx.coroutines.channels.BufferedChannelKt.CLOSE_HANDLER_CLOSED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0084, code lost:
    
        if (r14.compareAndSet(r13, r15, r0) == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x000a, code lost:
    
        if (r15 != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x009a, code lost:
    
        if (r14.get(r13) == r15) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0086, code lost:
    
        if (r15 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0089, code lost:
    
        kotlin.ResultKt.beforeCheckcastToFunctionOfArity(1, r15);
        ((kotlin.jvm.functions.Function1) r15).invoke(getCloseCause());
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007e, code lost:
    
        r0 = kotlinx.coroutines.channels.BufferedChannelKt.CLOSE_HANDLER_INVOKED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x009d, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000c, code lost:
    
        r5 = r9.get(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x004e, code lost:
    
        r5 = r9.get(r13);
        r14 = (int) (r5 >> 60);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0055, code lost:
    
        if (r14 == 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0057, code lost:
    
        if (r14 == 1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x005a, code lost:
    
        r14 = r5 & 1152921504606846975L;
        r3 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x006c, code lost:
    
        if (r9.compareAndSet(r13, r5, (r3 << 60) + r14) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0013, code lost:
    
        if (((int) (r5 >> 60)) != 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0061, code lost:
    
        r14 = r5 & 1152921504606846975L;
        r3 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x002e, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0015, code lost:
    
        r7 = kotlinx.coroutines.channels.BufferedChannelKt.NULL_SEGMENT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0022, code lost:
    
        if (r9.compareAndSet(r13, r5, (1 << 60) + (r5 & 1152921504606846975L)) == false) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean closeOrCancelImpl(java.lang.Throwable r14, boolean r15) {
        /*
            r13 = this;
            r0 = 60
            r1 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r9 = kotlinx.coroutines.channels.BufferedChannel.sendersAndCloseStatus$FU
            r10 = 1
            if (r15 == 0) goto L24
        Lc:
            long r5 = r9.get(r13)
            long r3 = r5 >> r0
            int r3 = (int) r3
            if (r3 != 0) goto L24
            long r3 = r5 & r1
            kotlinx.coroutines.channels.ChannelSegment r7 = kotlinx.coroutines.channels.BufferedChannelKt.NULL_SEGMENT
            long r7 = (long) r10
            long r7 = r7 << r0
            long r7 = r7 + r3
            r3 = r9
            r4 = r13
            boolean r3 = r3.compareAndSet(r4, r5, r7)
            if (r3 == 0) goto Lc
        L24:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.NO_CLOSE_CAUSE
        L26:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.channels.BufferedChannel._closeCause$FU
            boolean r5 = r4.compareAndSet(r13, r3, r14)
            if (r5 == 0) goto L30
            r11 = r10
            goto L38
        L30:
            java.lang.Object r4 = r4.get(r13)
            if (r4 == r3) goto L26
            r14 = 0
            r11 = r14
        L38:
            r12 = 3
            if (r15 == 0) goto L4e
        L3b:
            long r5 = r9.get(r13)
            long r14 = r5 & r1
            long r3 = (long) r12
            long r3 = r3 << r0
            long r7 = r3 + r14
            r3 = r9
            r4 = r13
            boolean r14 = r3.compareAndSet(r4, r5, r7)
            if (r14 == 0) goto L3b
            goto L6e
        L4e:
            long r5 = r9.get(r13)
            long r14 = r5 >> r0
            int r14 = (int) r14
            if (r14 == 0) goto L61
            if (r14 == r10) goto L5a
            goto L6e
        L5a:
            long r14 = r5 & r1
            long r3 = (long) r12
        L5d:
            long r3 = r3 << r0
            long r3 = r3 + r14
            r7 = r3
            goto L66
        L61:
            long r14 = r5 & r1
            r3 = 2
            long r3 = (long) r3
            goto L5d
        L66:
            r3 = r9
            r4 = r13
            boolean r14 = r3.compareAndSet(r4, r5, r7)
            if (r14 == 0) goto L4e
        L6e:
            r13.completeCloseOrCancel()
            if (r11 == 0) goto L9d
        L73:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = kotlinx.coroutines.channels.BufferedChannel.closeHandler$FU
            java.lang.Object r15 = r14.get(r13)
            if (r15 != 0) goto L7e
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.CLOSE_HANDLER_CLOSED
            goto L80
        L7e:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.CLOSE_HANDLER_INVOKED
        L80:
            boolean r1 = r14.compareAndSet(r13, r15, r0)
            if (r1 == 0) goto L96
            if (r15 != 0) goto L89
            goto L9d
        L89:
            kotlin.ResultKt.beforeCheckcastToFunctionOfArity(r10, r15)
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15
            java.lang.Throwable r14 = r13.getCloseCause()
            r15.invoke(r14)
            goto L9d
        L96:
            java.lang.Object r1 = r14.get(r13)
            if (r1 == r15) goto L80
            goto L73
        L9d:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.closeOrCancelImpl(java.lang.Throwable, boolean):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x008f, code lost:
    
        r1 = (kotlinx.coroutines.channels.ChannelSegment) ((kotlinx.coroutines.internal.ConcurrentLinkedListNode) kotlinx.coroutines.internal.ConcurrentLinkedListNode._prev$FU.get(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.channels.ChannelSegment completeClose(long r13) {
        /*
            r12 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.bufferEndSegment$FU
            java.lang.Object r0 = r0.get(r12)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.channels.BufferedChannel.sendSegment$FU
            java.lang.Object r1 = r1.get(r12)
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
            long r2 = r1.id
            r4 = r0
            kotlinx.coroutines.channels.ChannelSegment r4 = (kotlinx.coroutines.channels.ChannelSegment) r4
            long r4 = r4.id
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L1a
            r0 = r1
        L1a:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.channels.BufferedChannel.receiveSegment$FU
            java.lang.Object r1 = r1.get(r12)
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
            long r2 = r1.id
            r4 = r0
            kotlinx.coroutines.channels.ChannelSegment r4 = (kotlinx.coroutines.channels.ChannelSegment) r4
            long r4 = r4.id
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L2e
            r0 = r1
        L2e:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r0
        L30:
            r0.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.internal.ConcurrentLinkedListNode._next$FU
            java.lang.Object r1 = r1.get(r0)
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.internal.AtomicKt.CLOSED
            r3 = 0
            if (r1 != r2) goto L3f
            goto L4b
        L3f:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r1 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r1
            if (r1 != 0) goto L131
        L43:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.internal.ConcurrentLinkedListNode._next$FU
            boolean r4 = r1.compareAndSet(r0, r3, r2)
            if (r4 == 0) goto L129
        L4b:
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
            boolean r1 = r12.isConflatedDropOldest()
            r2 = -1
            r4 = 1
            if (r1 == 0) goto La3
            r1 = r0
        L56:
            int r5 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r5 = r5 - r4
        L59:
            r6 = -1
            if (r2 >= r5) goto L8f
            int r8 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r8 = (long) r8
            long r10 = r1.id
            long r10 = r10 * r8
            long r8 = (long) r5
            long r10 = r10 + r8
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            long r8 = r8.get(r12)
            int r8 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r8 >= 0) goto L71
        L6f:
            r10 = r6
            goto L9c
        L71:
            java.lang.Object r8 = r1.getState$kotlinx_coroutines_core(r5)
            if (r8 == 0) goto L81
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r8 != r9) goto L7c
            goto L81
        L7c:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r8 != r9) goto L8c
            goto L9c
        L81:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r8 = r1.casState$kotlinx_coroutines_core(r8, r5, r9)
            if (r8 == 0) goto L71
            r1.onSlotCleaned()
        L8c:
            int r5 = r5 + (-1)
            goto L59
        L8f:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.internal.ConcurrentLinkedListNode._prev$FU
            java.lang.Object r1 = r5.get(r1)
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r1 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r1
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
            if (r1 != 0) goto L56
            goto L6f
        L9c:
            int r1 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r1 == 0) goto La3
            r12.dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(r10)
        La3:
            r1 = r0
        La4:
            if (r1 == 0) goto L107
            int r5 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r5 = r5 - r4
        La9:
            if (r2 >= r5) goto Lfc
            int r6 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r6 = (long) r6
            long r8 = r1.id
            long r8 = r8 * r6
            long r6 = (long) r5
            long r8 = r8 + r6
            int r6 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r6 < 0) goto L107
        Lb7:
            java.lang.Object r6 = r1.getState$kotlinx_coroutines_core(r5)
            if (r6 == 0) goto Lee
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r6 != r7) goto Lc2
            goto Lee
        Lc2:
            boolean r7 = r6 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r7 == 0) goto Lda
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r7 = r1.casState$kotlinx_coroutines_core(r6, r5, r7)
            if (r7 == 0) goto Lb7
            kotlinx.coroutines.channels.WaiterEB r6 = (kotlinx.coroutines.channels.WaiterEB) r6
            kotlinx.coroutines.Waiter r6 = r6.waiter
            java.lang.Object r3 = kotlinx.coroutines.JobKt.m305plusFjFbRPM(r3, r6)
            r1.onCancelledRequest(r5, r4)
            goto Lf9
        Lda:
            boolean r7 = r6 instanceof kotlinx.coroutines.Waiter
            if (r7 == 0) goto Lf9
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r7 = r1.casState$kotlinx_coroutines_core(r6, r5, r7)
            if (r7 == 0) goto Lb7
            java.lang.Object r3 = kotlinx.coroutines.JobKt.m305plusFjFbRPM(r3, r6)
            r1.onCancelledRequest(r5, r4)
            goto Lf9
        Lee:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r6 = r1.casState$kotlinx_coroutines_core(r6, r5, r7)
            if (r6 == 0) goto Lb7
            r1.onSlotCleaned()
        Lf9:
            int r5 = r5 + (-1)
            goto La9
        Lfc:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.internal.ConcurrentLinkedListNode._prev$FU
            java.lang.Object r1 = r5.get(r1)
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r1 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r1
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
            goto La4
        L107:
            if (r3 == 0) goto L128
            boolean r13 = r3 instanceof java.util.ArrayList
            if (r13 != 0) goto L113
            kotlinx.coroutines.Waiter r3 = (kotlinx.coroutines.Waiter) r3
            r12.resumeWaiterOnClosedChannel(r3, r4)
            goto L128
        L113:
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r13 = r3.size()
            int r13 = r13 - r4
        L11a:
            if (r2 >= r13) goto L128
            java.lang.Object r14 = r3.get(r13)
            kotlinx.coroutines.Waiter r14 = (kotlinx.coroutines.Waiter) r14
            r12.resumeWaiterOnClosedChannel(r14, r4)
            int r13 = r13 + (-1)
            goto L11a
        L128:
            return r0
        L129:
            java.lang.Object r1 = r1.get(r0)
            if (r1 == 0) goto L43
            goto L30
        L131:
            r0 = r1
            goto L30
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.completeClose(long):kotlinx.coroutines.channels.ChannelSegment");
    }

    public final void completeCloseOrCancel() {
        isClosed(sendersAndCloseStatus$FU.get(this), false);
    }

    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long j) {
        StartupException callUndeliveredElementCatchingException;
        ChannelSegment channelSegment = (ChannelSegment) receiveSegment$FU.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = receivers$FU;
            long j2 = atomicLongFieldUpdater.get(this);
            if (j < Math.max(this.capacity + j2, bufferEnd$FU.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j2, j2 + 1)) {
                long j3 = BufferedChannelKt.SEGMENT_SIZE;
                long j4 = j2 / j3;
                int i = (int) (j2 % j3);
                if (channelSegment.id != j4) {
                    ChannelSegment findSegmentReceive = findSegmentReceive(j4, channelSegment);
                    if (findSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = findSegmentReceive;
                    }
                }
                Object updateCellReceive = updateCellReceive(channelSegment, i, j2, null);
                if (updateCellReceive != BufferedChannelKt.FAILED) {
                    channelSegment.cleanPrev();
                    Function1 function1 = this.onUndeliveredElement;
                    if (function1 != null && (callUndeliveredElementCatchingException = JobKt.callUndeliveredElementCatchingException(function1, updateCellReceive, null)) != null) {
                        throw callUndeliveredElementCatchingException;
                    }
                } else if (j2 < getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x00c4, code lost:
    
        if ((r0.addAndGet(r16, r14 - r9) & 4611686018427387904L) != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x00cd, code lost:
    
        if ((r0.get(r16) & 4611686018427387904L) == 0) goto L144;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void expandBuffer() {
        /*
            r16 = this;
            r6 = r16
            boolean r0 = r16.isRendezvousOrUnlimited()
            if (r0 == 0) goto L9
            return
        L9:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = kotlinx.coroutines.channels.BufferedChannel.bufferEndSegment$FU
            java.lang.Object r0 = r7.get(r6)
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
            r8 = r0
        L12:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.bufferEnd$FU
            long r9 = r0.getAndIncrement(r6)
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r0 = (long) r0
            long r0 = r9 / r0
            long r2 = r16.getSendersCounter$kotlinx_coroutines_core()
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 > 0) goto L38
            long r2 = r8.id
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L34
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r2 = r8.getNext()
            if (r2 == 0) goto L34
            r6.moveSegmentBufferEndToSpecifiedOrLast(r0, r8)
        L34:
            incCompletedExpandBufferAttempts$default(r16)
            return
        L38:
            long r2 = r8.id
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 == 0) goto Lda
            kotlinx.coroutines.channels.BufferedChannelKt$createSegmentFunction$1 r2 = kotlinx.coroutines.channels.BufferedChannelKt$createSegmentFunction$1.INSTANCE
        L40:
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.findSegmentInternal(r8, r0, r2)
            boolean r4 = kotlinx.coroutines.JobKt.m304isClosedimpl(r3)
            if (r4 != 0) goto L84
            kotlinx.coroutines.internal.Segment r4 = kotlinx.coroutines.JobKt.m303getSegmentimpl(r3)
        L4e:
            java.lang.Object r5 = r7.get(r6)
            kotlinx.coroutines.internal.Segment r5 = (kotlinx.coroutines.internal.Segment) r5
            long r11 = r5.id
            long r13 = r4.id
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 < 0) goto L5d
            goto L84
        L5d:
            boolean r11 = r4.tryIncPointers$kotlinx_coroutines_core()
            if (r11 != 0) goto L64
            goto L40
        L64:
            boolean r11 = r7.compareAndSet(r6, r5, r4)
            if (r11 == 0) goto L74
            boolean r2 = r5.decPointers$kotlinx_coroutines_core()
            if (r2 == 0) goto L84
            r5.remove()
            goto L84
        L74:
            java.lang.Object r11 = r7.get(r6)
            if (r11 == r5) goto L64
            boolean r5 = r4.decPointers$kotlinx_coroutines_core()
            if (r5 == 0) goto L4e
            r4.remove()
            goto L4e
        L84:
            boolean r2 = kotlinx.coroutines.JobKt.m304isClosedimpl(r3)
            r11 = 0
            if (r2 == 0) goto L95
            r16.completeCloseOrCancel()
            r6.moveSegmentBufferEndToSpecifiedOrLast(r0, r8)
            incCompletedExpandBufferAttempts$default(r16)
            goto Ld5
        L95:
            kotlinx.coroutines.internal.Segment r2 = kotlinx.coroutines.JobKt.m303getSegmentimpl(r3)
            kotlinx.coroutines.channels.ChannelSegment r2 = (kotlinx.coroutines.channels.ChannelSegment) r2
            long r3 = r2.id
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto Ld4
            r0 = 1
            long r12 = r9 + r0
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r0 = (long) r0
            long r14 = r3 * r0
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.bufferEnd$FU
            r1 = r16
            r2 = r12
            r4 = r14
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto Ld0
            long r14 = r14 - r9
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.completedExpandBuffersAndPauseFlag$FU
            long r1 = r0.addAndGet(r6, r14)
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            long r1 = r1 & r3
            r12 = 0
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 == 0) goto Ld5
        Lc6:
            long r1 = r0.get(r6)
            long r1 = r1 & r3
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 == 0) goto Ld5
            goto Lc6
        Ld0:
            incCompletedExpandBufferAttempts$default(r16)
            goto Ld5
        Ld4:
            r11 = r2
        Ld5:
            if (r11 != 0) goto Ld9
            goto L12
        Ld9:
            r8 = r11
        Lda:
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r0 = (long) r0
            long r0 = r9 % r0
            int r0 = (int) r0
            java.lang.Object r1 = r8.getState$kotlinx_coroutines_core(r0)
            boolean r2 = r1 instanceof kotlinx.coroutines.Waiter
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            r4 = 0
            if (r2 == 0) goto L111
            long r11 = r3.get(r6)
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 < 0) goto L111
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            boolean r2 = r8.casState$kotlinx_coroutines_core(r1, r0, r2)
            if (r2 == 0) goto L111
            boolean r1 = tryResumeSender(r1)
            if (r1 == 0) goto L108
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            r8.setState$kotlinx_coroutines_core(r0, r1)
            goto L196
        L108:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            r8.setState$kotlinx_coroutines_core(r0, r1)
            r8.onCancelledRequest(r0, r4)
            goto L152
        L111:
            java.lang.Object r1 = r8.getState$kotlinx_coroutines_core(r0)
            boolean r2 = r1 instanceof kotlinx.coroutines.Waiter
            if (r2 == 0) goto L14e
            long r11 = r3.get(r6)
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 >= 0) goto L131
            kotlinx.coroutines.channels.WaiterEB r2 = new kotlinx.coroutines.channels.WaiterEB
            r5 = r1
            kotlinx.coroutines.Waiter r5 = (kotlinx.coroutines.Waiter) r5
            r2.<init>(r5)
            boolean r1 = r8.casState$kotlinx_coroutines_core(r1, r0, r2)
            if (r1 == 0) goto L111
            goto L196
        L131:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            boolean r2 = r8.casState$kotlinx_coroutines_core(r1, r0, r2)
            if (r2 == 0) goto L111
            boolean r1 = tryResumeSender(r1)
            if (r1 == 0) goto L145
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            r8.setState$kotlinx_coroutines_core(r0, r1)
            goto L196
        L145:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            r8.setState$kotlinx_coroutines_core(r0, r1)
            r8.onCancelledRequest(r0, r4)
            goto L152
        L14e:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            if (r1 != r2) goto L157
        L152:
            incCompletedExpandBufferAttempts$default(r16)
            goto L12
        L157:
            if (r1 != 0) goto L162
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            boolean r1 = r8.casState$kotlinx_coroutines_core(r1, r0, r2)
            if (r1 == 0) goto L111
            goto L196
        L162:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r1 != r2) goto L167
            goto L196
        L167:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            if (r1 == r2) goto L196
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r1 == r2) goto L196
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            if (r1 != r2) goto L174
            goto L196
        L174:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r1 != r2) goto L179
            goto L196
        L179:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r1 != r2) goto L17e
            goto L111
        L17e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unexpected cell state: "
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L196:
            incCompletedExpandBufferAttempts$default(r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.expandBuffer():void");
    }

    public final ChannelSegment findSegmentReceive(long j, ChannelSegment channelSegment) {
        Object findSegmentInternal;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        ChannelSegment channelSegment2 = BufferedChannelKt.NULL_SEGMENT;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.INSTANCE;
        loop0: while (true) {
            findSegmentInternal = AtomicKt.findSegmentInternal(channelSegment, j, bufferedChannelKt$createSegmentFunction$1);
            if (!JobKt.m304isClosedimpl(findSegmentInternal)) {
                Segment m303getSegmentimpl = JobKt.m303getSegmentimpl(findSegmentInternal);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = receiveSegment$FU;
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    if (segment.id >= m303getSegmentimpl.id) {
                        break loop0;
                    }
                    if (!m303getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, segment, m303getSegmentimpl)) {
                        if (atomicReferenceFieldUpdater.get(this) != segment) {
                            if (m303getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                                m303getSegmentimpl.remove();
                            }
                        }
                    }
                    if (segment.decPointers$kotlinx_coroutines_core()) {
                        segment.remove();
                    }
                }
            } else {
                break;
            }
        }
        if (JobKt.m304isClosedimpl(findSegmentInternal)) {
            completeCloseOrCancel();
            if (channelSegment.id * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$kotlinx_coroutines_core()) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        ChannelSegment channelSegment3 = (ChannelSegment) JobKt.m303getSegmentimpl(findSegmentInternal);
        boolean isRendezvousOrUnlimited = isRendezvousOrUnlimited();
        long j3 = channelSegment3.id;
        if (!isRendezvousOrUnlimited && j <= bufferEnd$FU.get(this) / BufferedChannelKt.SEGMENT_SIZE) {
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = bufferEndSegment$FU;
                Segment segment2 = (Segment) atomicReferenceFieldUpdater2.get(this);
                if (segment2.id >= j3) {
                    break;
                }
                if (!channelSegment3.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, segment2, channelSegment3)) {
                    if (atomicReferenceFieldUpdater2.get(this) != segment2) {
                        if (channelSegment3.decPointers$kotlinx_coroutines_core()) {
                            channelSegment3.remove();
                        }
                    }
                }
                if (segment2.decPointers$kotlinx_coroutines_core()) {
                    segment2.remove();
                }
            }
        }
        if (j3 <= j) {
            return channelSegment3;
        }
        long j4 = BufferedChannelKt.SEGMENT_SIZE * j3;
        do {
            atomicLongFieldUpdater = receivers$FU;
            j2 = atomicLongFieldUpdater.get(this);
            if (j2 >= j4) {
                break;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j4));
        if (j3 * BufferedChannelKt.SEGMENT_SIZE >= getSendersCounter$kotlinx_coroutines_core()) {
            return null;
        }
        channelSegment3.cleanPrev();
        return null;
    }

    public final Throwable getCloseCause() {
        return (Throwable) _closeCause$FU.get(this);
    }

    public final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new IllegalStateException("Channel was closed") : closeCause;
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        return sendersAndCloseStatus$FU.get(this) & 1152921504606846975L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x00c6, code lost:
    
        r0 = (kotlinx.coroutines.channels.ChannelSegment) ((kotlinx.coroutines.internal.ConcurrentLinkedListNode) kotlinx.coroutines.internal.ConcurrentLinkedListNode._prev$FU.get(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isClosed(long r19, boolean r21) {
        /*
            r18 = this;
            r6 = r18
            r0 = 60
            long r0 = r19 >> r0
            int r0 = (int) r0
            r7 = 0
            if (r0 == 0) goto L1a1
            r8 = 1
            if (r0 == r8) goto L1a1
            r1 = 2
            java.util.concurrent.atomic.AtomicLongFieldUpdater r9 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            r2 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            if (r0 == r1) goto L112
            r1 = 3
            if (r0 != r1) goto Lfa
            long r0 = r19 & r2
            kotlinx.coroutines.channels.ChannelSegment r0 = r6.completeClose(r0)
            r1 = 0
            r2 = r1
            r3 = r2
        L23:
            int r4 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r4 = r4 - r8
        L26:
            r5 = -1
            if (r5 >= r4) goto Lc6
            int r10 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r10 = (long) r10
            long r12 = r0.id
            long r12 = r12 * r10
            long r10 = (long) r4
            long r12 = r12 + r10
        L31:
            java.lang.Object r10 = r0.getState$kotlinx_coroutines_core(r4)
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r10 == r11) goto Ld2
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            java.util.concurrent.atomic.AtomicReferenceArray r14 = r0.data
            kotlin.jvm.functions.Function1 r15 = r6.onUndeliveredElement
            if (r10 != r11) goto L64
            long r16 = r9.get(r6)
            int r11 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r11 < 0) goto Ld2
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r10 = r0.casState$kotlinx_coroutines_core(r10, r4, r11)
            if (r10 == 0) goto L31
            if (r15 == 0) goto L5d
            int r5 = r4 * 2
            java.lang.Object r5 = r14.get(r5)
            androidx.startup.StartupException r2 = kotlinx.coroutines.JobKt.callUndeliveredElementCatchingException(r15, r5, r2)
        L5d:
            r0.setElementLazy(r4, r1)
            r0.onSlotCleaned()
            goto Lc2
        L64:
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r10 == r11) goto Lb7
            if (r10 != 0) goto L6b
            goto Lb7
        L6b:
            boolean r11 = r10 instanceof kotlinx.coroutines.Waiter
            if (r11 != 0) goto L80
            boolean r11 = r10 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r11 == 0) goto L74
            goto L80
        L74:
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r10 == r11) goto Ld2
            kotlinx.coroutines.internal.Symbol r14 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r10 != r14) goto L7d
            goto Ld2
        L7d:
            if (r10 == r11) goto L31
            goto Lc2
        L80:
            long r16 = r9.get(r6)
            int r11 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r11 < 0) goto Ld2
            boolean r11 = r10 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r11 == 0) goto L92
            r11 = r10
            kotlinx.coroutines.channels.WaiterEB r11 = (kotlinx.coroutines.channels.WaiterEB) r11
            kotlinx.coroutines.Waiter r11 = r11.waiter
            goto L95
        L92:
            r11 = r10
            kotlinx.coroutines.Waiter r11 = (kotlinx.coroutines.Waiter) r11
        L95:
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r5 = r0.casState$kotlinx_coroutines_core(r10, r4, r5)
            if (r5 == 0) goto Lb4
            if (r15 == 0) goto La9
            int r5 = r4 * 2
            java.lang.Object r5 = r14.get(r5)
            androidx.startup.StartupException r2 = kotlinx.coroutines.JobKt.callUndeliveredElementCatchingException(r15, r5, r2)
        La9:
            java.lang.Object r3 = kotlinx.coroutines.JobKt.m305plusFjFbRPM(r3, r11)
            r0.setElementLazy(r4, r1)
            r0.onSlotCleaned()
            goto Lc2
        Lb4:
            r5 = -1
            goto L31
        Lb7:
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r5 = r0.casState$kotlinx_coroutines_core(r10, r4, r5)
            if (r5 == 0) goto Lb4
            r0.onSlotCleaned()
        Lc2:
            int r4 = r4 + (-1)
            goto L26
        Lc6:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.internal.ConcurrentLinkedListNode._prev$FU
            java.lang.Object r0 = r4.get(r0)
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = (kotlinx.coroutines.internal.ConcurrentLinkedListNode) r0
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
            if (r0 != 0) goto L23
        Ld2:
            if (r3 == 0) goto Lf4
            boolean r0 = r3 instanceof java.util.ArrayList
            if (r0 != 0) goto Lde
            kotlinx.coroutines.Waiter r3 = (kotlinx.coroutines.Waiter) r3
            r6.resumeWaiterOnClosedChannel(r3, r7)
            goto Lf4
        Lde:
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r0 = r3.size()
            int r0 = r0 - r8
            r1 = -1
        Le6:
            if (r1 >= r0) goto Lf4
            java.lang.Object r4 = r3.get(r0)
            kotlinx.coroutines.Waiter r4 = (kotlinx.coroutines.Waiter) r4
            r6.resumeWaiterOnClosedChannel(r4, r7)
            int r0 = r0 + (-1)
            goto Le6
        Lf4:
            if (r2 != 0) goto Lf9
        Lf6:
            r7 = r8
            goto L1a1
        Lf9:
            throw r2
        Lfa:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "unexpected close status: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L112:
            long r0 = r19 & r2
            r6.completeClose(r0)
            if (r21 == 0) goto Lf6
        L119:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.receiveSegment$FU
            java.lang.Object r1 = r0.get(r6)
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
            long r2 = r9.get(r6)
            long r4 = r18.getSendersCounter$kotlinx_coroutines_core()
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 > 0) goto L12e
            goto L14b
        L12e:
            int r4 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r4 = (long) r4
            long r10 = r2 / r4
            long r12 = r1.id
            int r12 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r12 == 0) goto L14c
            kotlinx.coroutines.channels.ChannelSegment r1 = r6.findSegmentReceive(r10, r1)
            if (r1 != 0) goto L14c
            java.lang.Object r0 = r0.get(r6)
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
            long r0 = r0.id
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r0 >= 0) goto L119
        L14b:
            goto Lf6
        L14c:
            r1.cleanPrev()
            long r4 = r2 % r4
            int r0 = (int) r4
        L152:
            java.lang.Object r4 = r1.getState$kotlinx_coroutines_core(r0)
            if (r4 == 0) goto L189
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            if (r4 != r5) goto L15d
            goto L189
        L15d:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r4 != r0) goto L162
            goto L1a1
        L162:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            if (r4 != r0) goto L167
            goto L194
        L167:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r4 != r0) goto L16c
            goto L194
        L16c:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r4 != r0) goto L171
            goto L194
        L171:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            if (r4 != r0) goto L176
            goto L194
        L176:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r4 != r0) goto L17b
            goto L1a1
        L17b:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r4 != r0) goto L180
            goto L194
        L180:
            long r0 = r9.get(r6)
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 != 0) goto L194
            goto L1a1
        L189:
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            boolean r4 = r1.casState$kotlinx_coroutines_core(r4, r0, r5)
            if (r4 == 0) goto L152
            r18.expandBuffer()
        L194:
            r0 = 1
            long r4 = r2 + r0
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            r1 = r18
            r0.compareAndSet(r1, r2, r4)
            goto L119
        L1a1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.isClosed(long, boolean):boolean");
    }

    public boolean isConflatedDropOldest() {
        return false;
    }

    public final boolean isRendezvousOrUnlimited() {
        long j = bufferEnd$FU.get(this);
        return j == 0 || j == Long.MAX_VALUE;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final BufferedChannelIterator iterator() {
        return new BufferedChannelIterator();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void moveSegmentBufferEndToSpecifiedOrLast(long r5, kotlinx.coroutines.channels.ChannelSegment r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.id
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r0 = r7.getNext()
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L22
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r5 = r7.getNext()
            kotlinx.coroutines.channels.ChannelSegment r5 = (kotlinx.coroutines.channels.ChannelSegment) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.channels.BufferedChannel.bufferEndSegment$FU
            java.lang.Object r6 = r5.get(r4)
            kotlinx.coroutines.internal.Segment r6 = (kotlinx.coroutines.internal.Segment) r6
            long r0 = r6.id
            long r2 = r7.id
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L33
            goto L49
        L33:
            boolean r0 = r7.tryIncPointers$kotlinx_coroutines_core()
            if (r0 != 0) goto L3a
            goto L11
        L3a:
            boolean r0 = r5.compareAndSet(r4, r6, r7)
            if (r0 == 0) goto L4a
            boolean r5 = r6.decPointers$kotlinx_coroutines_core()
            if (r5 == 0) goto L49
            r6.remove()
        L49:
            return
        L4a:
            java.lang.Object r0 = r5.get(r4)
            if (r0 == r6) goto L3a
            boolean r5 = r7.decPointers$kotlinx_coroutines_core()
            if (r5 == 0) goto L22
            r7.remove()
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.moveSegmentBufferEndToSpecifiedOrLast(long, kotlinx.coroutines.channels.ChannelSegment):void");
    }

    public final Object onClosedSend(Object obj, Continuation continuation) {
        StartupException callUndeliveredElementCatchingException;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException = JobKt.callUndeliveredElementCatchingException(function1, obj, null)) == null) {
            cancellableContinuationImpl.resumeWith(ResultKt.createFailure(getSendException()));
        } else {
            ResultKt.addSuppressed(callUndeliveredElementCatchingException, getSendException());
            cancellableContinuationImpl.resumeWith(ResultKt.createFailure(callUndeliveredElementCatchingException));
        }
        Object result = cancellableContinuationImpl.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    public final void resumeWaiterOnClosedChannel(Waiter waiter, boolean z) {
        Throwable sendException;
        if (waiter instanceof CancellableContinuation) {
            Continuation continuation = (Continuation) waiter;
            if (z) {
                sendException = getCloseCause();
                if (sendException == null) {
                    sendException = new NoSuchElementException("Channel was closed");
                }
            } else {
                sendException = getSendException();
            }
            continuation.resumeWith(ResultKt.createFailure(sendException));
            return;
        }
        if (!(waiter instanceof BufferedChannelIterator)) {
            throw new IllegalStateException(("Unexpected waiter: " + waiter).toString());
        }
        BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) waiter;
        CancellableContinuationImpl cancellableContinuationImpl = bufferedChannelIterator.continuation;
        ResultKt.checkNotNull(cancellableContinuationImpl);
        bufferedChannelIterator.continuation = null;
        bufferedChannelIterator.receiveResult = BufferedChannelKt.CHANNEL_CLOSED;
        Throwable closeCause = BufferedChannel.this.getCloseCause();
        if (closeCause == null) {
            cancellableContinuationImpl.resumeWith(Boolean.FALSE);
        } else {
            cancellableContinuationImpl.resumeWith(ResultKt.createFailure(closeCause));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ee, code lost:
    
        r5 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00f2, code lost:
    
        access$onClosedSendOnNoWaiterSuspend(r26, r27, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00f5, code lost:
    
        r2 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00fa, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00fb, code lost:
    
        r2 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01d1, code lost:
    
        r2.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01d4, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x014c, code lost:
    
        if (r24 >= r5.get(r26)) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x014e, code lost:
    
        r19.cleanPrev();
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0151, code lost:
    
        r1 = r27;
        r2 = r28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [kotlinx.coroutines.CancellableContinuationImpl] */
    @Override // kotlinx.coroutines.channels.SendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object send(java.lang.Object r27, kotlin.coroutines.Continuation r28) {
        /*
            r26 = this;
            r9 = r26
            r0 = r27
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r10 = kotlinx.coroutines.channels.BufferedChannel.sendSegment$FU
            java.lang.Object r1 = r10.get(r9)
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1
        Lc:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r11 = kotlinx.coroutines.channels.BufferedChannel.sendersAndCloseStatus$FU
            long r2 = r11.getAndIncrement(r9)
            r12 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r14 = r2 & r12
            r8 = 0
            boolean r16 = r9.isClosed(r2, r8)
            int r7 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r2 = (long) r7
            long r4 = r14 / r2
            long r2 = r14 % r2
            int r6 = (int) r2
            long r2 = r1.id
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            kotlin.coroutines.intrinsics.CoroutineSingletons r12 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r2 == 0) goto L43
            kotlinx.coroutines.channels.ChannelSegment r2 = access$findSegmentSend(r9, r4, r1)
            if (r2 != 0) goto L41
            if (r16 == 0) goto Lc
            java.lang.Object r0 = r26.onClosedSend(r27, r28)
            if (r0 != r12) goto L1eb
        L3e:
            r3 = r0
            goto L1eb
        L41:
            r13 = r2
            goto L44
        L43:
            r13 = r1
        L44:
            r19 = 0
            r1 = r26
            r2 = r13
            r5 = r3
            r3 = r6
            r4 = r27
            r21 = r5
            r20 = r6
            r5 = r14
            r22 = r7
            r7 = r19
            r0 = r8
            r8 = r16
            int r1 = access$updateCellSend(r1, r2, r3, r4, r5, r7, r8)
            if (r1 == 0) goto L1e6
            r8 = 1
            if (r1 == r8) goto L8b
            r7 = 2
            if (r1 == r7) goto L1d5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r5 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            r6 = 5
            r4 = 4
            r3 = 3
            if (r1 == r3) goto L8f
            if (r1 == r4) goto L78
            if (r1 == r6) goto L71
            goto L74
        L71:
            r13.cleanPrev()
        L74:
            r0 = r27
            r1 = r13
            goto Lc
        L78:
            long r0 = r5.get(r9)
            int r0 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r0 >= 0) goto L83
            r13.cleanPrev()
        L83:
            java.lang.Object r3 = r26.onClosedSend(r27, r28)
            if (r3 != r12) goto L8b
            goto L1eb
        L8b:
            r3 = r21
            goto L1eb
        L8f:
            kotlin.coroutines.Continuation r1 = kotlin.ResultKt.intercepted(r28)
            kotlinx.coroutines.CancellableContinuationImpl r2 = kotlin.ResultKt.getOrCreateCancellableContinuation(r1)
            r16 = 0
            r1 = r26
            r28 = r2
            r2 = r13
            r3 = r20
            r0 = r4
            r4 = r27
            r23 = r5
            r5 = r14
            r0 = r7
            r7 = r28
            r0 = r8
            r8 = r16
            int r1 = access$updateCellSend(r1, r2, r3, r4, r5, r7, r8)     // Catch: java.lang.Throwable -> L13f
            if (r1 == 0) goto L1bd
            if (r1 == r0) goto L17b
            r2 = 2
            if (r1 == r2) goto L1b3
            r2 = 4
            if (r1 == r2) goto L19c
            java.lang.String r14 = "unexpected"
            r15 = 5
            if (r1 != r15) goto L190
            r13.cleanPrev()     // Catch: java.lang.Throwable -> L13f
            java.lang.Object r1 = r10.get(r9)     // Catch: java.lang.Throwable -> L13f
            kotlinx.coroutines.channels.ChannelSegment r1 = (kotlinx.coroutines.channels.ChannelSegment) r1     // Catch: java.lang.Throwable -> L13f
        Lc8:
            long r2 = r11.getAndIncrement(r9)     // Catch: java.lang.Throwable -> L13f
            r16 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r24 = r2 & r16
            r4 = 0
            boolean r10 = r9.isClosed(r2, r4)     // Catch: java.lang.Throwable -> L13f
            int r13 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE     // Catch: java.lang.Throwable -> L13f
            long r2 = (long) r13     // Catch: java.lang.Throwable -> L13f
            long r5 = r24 / r2
            long r2 = r24 % r2
            int r8 = (int) r2     // Catch: java.lang.Throwable -> L13f
            long r2 = r1.id     // Catch: java.lang.Throwable -> L13f
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 == 0) goto L10d
            kotlinx.coroutines.channels.ChannelSegment r2 = access$findSegmentSend(r9, r5, r1)     // Catch: java.lang.Throwable -> L109
            if (r2 != 0) goto L101
            if (r10 == 0) goto Lfe
            r7 = r27
            r5 = r28
            access$onClosedSendOnNoWaiterSuspend(r9, r7, r5)     // Catch: java.lang.Throwable -> Lfa
            r2 = r5
        Lf6:
            r3 = r21
            goto L1c5
        Lfa:
            r0 = move-exception
        Lfb:
            r2 = r5
            goto L1d1
        Lfe:
            r7 = r27
            goto Lc8
        L101:
            r7 = r27
            r5 = r28
            r18 = r4
            r6 = r2
            goto L114
        L109:
            r0 = move-exception
            r5 = r28
            goto Lfb
        L10d:
            r7 = r27
            r5 = r28
            r18 = r4
            r6 = r1
        L114:
            r1 = r26
            r2 = r6
            r3 = r8
            r4 = r27
            r28 = r5
            r19 = r6
            r5 = r24
            r7 = r28
            r20 = r8
            r8 = r10
            int r1 = access$updateCellSend(r1, r2, r3, r4, r5, r7, r8)     // Catch: java.lang.Throwable -> L13f
            if (r1 == 0) goto L183
            if (r1 == r0) goto L17b
            r2 = 2
            if (r1 == r2) goto L168
            r3 = 3
            if (r1 == r3) goto L159
            r4 = 4
            if (r1 == r4) goto L144
            if (r1 == r15) goto L139
            goto L13c
        L139:
            r19.cleanPrev()     // Catch: java.lang.Throwable -> L13f
        L13c:
            r1 = r19
            goto Lc8
        L13f:
            r0 = move-exception
            r2 = r28
            goto L1d1
        L144:
            r0 = r23
            long r0 = r0.get(r9)     // Catch: java.lang.Throwable -> L13f
            int r0 = (r24 > r0 ? 1 : (r24 == r0 ? 0 : -1))
            if (r0 >= 0) goto L151
            r19.cleanPrev()     // Catch: java.lang.Throwable -> L13f
        L151:
            r1 = r27
            r2 = r28
        L155:
            access$onClosedSendOnNoWaiterSuspend(r9, r1, r2)     // Catch: java.lang.Throwable -> L165
            goto Lf6
        L159:
            r2 = r28
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L165
            java.lang.String r1 = r14.toString()     // Catch: java.lang.Throwable -> L165
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L165
            throw r0     // Catch: java.lang.Throwable -> L165
        L165:
            r0 = move-exception
            goto L1d1
        L168:
            r1 = r27
            r2 = r28
            if (r10 == 0) goto L172
            r19.onSlotCleaned()     // Catch: java.lang.Throwable -> L165
            goto L155
        L172:
            int r8 = r20 + r13
            r1 = r19
            r2.invokeOnCancellation(r1, r8)     // Catch: java.lang.Throwable -> L165
            goto Lf6
        L17b:
            r2 = r28
            r3 = r21
            r2.resumeWith(r3)     // Catch: java.lang.Throwable -> L165
            goto L1c5
        L183:
            r2 = r28
            r1 = r19
            r3 = r21
            r1.cleanPrev()     // Catch: java.lang.Throwable -> L165
        L18c:
            r2.resumeWith(r3)     // Catch: java.lang.Throwable -> L165
            goto L1c5
        L190:
            r2 = r28
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L165
            java.lang.String r1 = r14.toString()     // Catch: java.lang.Throwable -> L165
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L165
            throw r0     // Catch: java.lang.Throwable -> L165
        L19c:
            r1 = r27
            r2 = r28
            r3 = r21
            r0 = r23
            long r4 = r0.get(r9)     // Catch: java.lang.Throwable -> L165
            int r0 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r0 >= 0) goto L1af
            r13.cleanPrev()     // Catch: java.lang.Throwable -> L165
        L1af:
            access$onClosedSendOnNoWaiterSuspend(r9, r1, r2)     // Catch: java.lang.Throwable -> L165
            goto L1c5
        L1b3:
            r2 = r28
            r3 = r21
            int r6 = r20 + r22
            r2.invokeOnCancellation(r13, r6)     // Catch: java.lang.Throwable -> L165
            goto L1c5
        L1bd:
            r2 = r28
            r3 = r21
            r13.cleanPrev()     // Catch: java.lang.Throwable -> L165
            goto L18c
        L1c5:
            java.lang.Object r0 = r2.getResult()
            if (r0 != r12) goto L1cc
            goto L1cd
        L1cc:
            r0 = r3
        L1cd:
            if (r0 != r12) goto L1eb
            goto L3e
        L1d1:
            r2.releaseClaimedReusableContinuation$kotlinx_coroutines_core()
            throw r0
        L1d5:
            r1 = r27
            r3 = r21
            if (r16 == 0) goto L1eb
            r13.onSlotCleaned()
            java.lang.Object r0 = r26.onClosedSend(r27, r28)
            if (r0 != r12) goto L1eb
            goto L3e
        L1e6:
            r3 = r21
            r13.cleanPrev()
        L1eb:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v8, resolved type: kotlinx.coroutines.channels.ChannelSegment[] */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0194, code lost:
    
        r3 = (kotlinx.coroutines.channels.ChannelSegment) r3.getNext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x019b, code lost:
    
        if (r3 != null) goto L86;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            r16 = this;
            r0 = r16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = kotlinx.coroutines.channels.BufferedChannel.sendersAndCloseStatus$FU
            long r2 = r2.get(r0)
            r4 = 60
            long r2 = r2 >> r4
            int r2 = (int) r2
            r3 = 2
            r4 = 3
            if (r2 == r3) goto L1e
            if (r2 == r4) goto L18
            goto L23
        L18:
            java.lang.String r2 = "cancelled,"
            r1.append(r2)
            goto L23
        L1e:
            java.lang.String r2 = "closed,"
            r1.append(r2)
        L23:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "capacity="
            r2.<init>(r5)
            int r5 = r0.capacity
            r2.append(r5)
            r5 = 44
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r2 = "data=["
            r1.append(r2)
            kotlinx.coroutines.channels.ChannelSegment[] r2 = new kotlinx.coroutines.channels.ChannelSegment[r4]
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.channels.BufferedChannel.receiveSegment$FU
            java.lang.Object r4 = r4.get(r0)
            r6 = 0
            r2[r6] = r4
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.channels.BufferedChannel.sendSegment$FU
            java.lang.Object r4 = r4.get(r0)
            r7 = 1
            r2[r7] = r4
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.channels.BufferedChannel.bufferEndSegment$FU
            java.lang.Object r4 = r4.get(r0)
            r2[r3] = r4
            java.util.List r2 = kotlin.ResultKt.listOf(r2)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L69:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L7e
            java.lang.Object r4 = r2.next()
            r8 = r4
            kotlinx.coroutines.channels.ChannelSegment r8 = (kotlinx.coroutines.channels.ChannelSegment) r8
            kotlinx.coroutines.channels.ChannelSegment r9 = kotlinx.coroutines.channels.BufferedChannelKt.NULL_SEGMENT
            if (r8 == r9) goto L69
            r3.add(r4)
            goto L69
        L7e:
            java.util.Iterator r2 = r3.iterator()
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L1d2
            java.lang.Object r3 = r2.next()
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L93
            goto Lad
        L93:
            r4 = r3
            kotlinx.coroutines.channels.ChannelSegment r4 = (kotlinx.coroutines.channels.ChannelSegment) r4
            long r8 = r4.id
        L98:
            java.lang.Object r4 = r2.next()
            r10 = r4
            kotlinx.coroutines.channels.ChannelSegment r10 = (kotlinx.coroutines.channels.ChannelSegment) r10
            long r10 = r10.id
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto La7
            r3 = r4
            r8 = r10
        La7:
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L98
        Lad:
            kotlinx.coroutines.channels.ChannelSegment r3 = (kotlinx.coroutines.channels.ChannelSegment) r3
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            long r10 = r2.get(r0)
            long r12 = r16.getSendersCounter$kotlinx_coroutines_core()
        Lb9:
            int r2 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            r4 = r6
        Lbc:
            if (r4 >= r2) goto L194
            long r8 = r3.id
            int r14 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r14 = (long) r14
            long r8 = r8 * r14
            long r14 = (long) r4
            long r8 = r8 + r14
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 < 0) goto Lce
            int r15 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r15 >= 0) goto L19d
        Lce:
            java.lang.Object r15 = r3.getState$kotlinx_coroutines_core(r4)
            java.util.concurrent.atomic.AtomicReferenceArray r6 = r3.data
            int r7 = r4 * 2
            java.lang.Object r6 = r6.get(r7)
            boolean r7 = r15 instanceof kotlinx.coroutines.CancellableContinuation
            if (r7 == 0) goto Lf4
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 >= 0) goto Le8
            if (r14 < 0) goto Le8
            java.lang.String r7 = "receive"
            goto L15d
        Le8:
            if (r14 >= 0) goto Lf0
            if (r7 < 0) goto Lf0
            java.lang.String r7 = "send"
            goto L15d
        Lf0:
            java.lang.String r7 = "cont"
            goto L15d
        Lf4:
            boolean r7 = r15 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r7 == 0) goto L10c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "EB("
            r7.<init>(r8)
            r7.append(r15)
            r8 = 41
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            goto L15d
        L10c:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L115
            goto L11d
        L115:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L120
        L11d:
            java.lang.String r7 = "resuming_sender"
            goto L15d
        L120:
            if (r15 != 0) goto L124
            goto L18e
        L124:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.IN_BUFFER
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L12d
            goto L18e
        L12d:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L136
            goto L18e
        L136:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.POISONED
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L13f
            goto L18e
        L13f:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L148
            goto L18e
        L148:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 == 0) goto L151
            goto L18e
        L151:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            boolean r7 = kotlin.ResultKt.areEqual(r15, r7)
            if (r7 != 0) goto L18e
            java.lang.String r7 = r15.toString()
        L15d:
            if (r6 == 0) goto L17c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "("
            r8.<init>(r9)
            r8.append(r7)
            r8.append(r5)
            r8.append(r6)
            java.lang.String r6 = "),"
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r1.append(r6)
            goto L18e
        L17c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            r1.append(r6)
        L18e:
            int r4 = r4 + 1
            r6 = 0
            r7 = 1
            goto Lbc
        L194:
            kotlinx.coroutines.internal.ConcurrentLinkedListNode r2 = r3.getNext()
            r3 = r2
            kotlinx.coroutines.channels.ChannelSegment r3 = (kotlinx.coroutines.channels.ChannelSegment) r3
            if (r3 != 0) goto L1ce
        L19d:
            int r2 = r1.length()
            if (r2 == 0) goto L1c6
            int r2 = kotlin.text.StringsKt__StringsKt.getLastIndex(r1)
            char r2 = r1.charAt(r2)
            if (r2 != r5) goto L1bc
            int r2 = r1.length()
            r4 = 1
            int r2 = r2 - r4
            java.lang.StringBuilder r2 = r1.deleteCharAt(r2)
            java.lang.String r3 = "this.deleteCharAt(index)"
            kotlin.ResultKt.checkNotNullExpressionValue(r2, r3)
        L1bc:
            java.lang.String r2 = "]"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            return r1
        L1c6:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            java.lang.String r2 = "Char sequence is empty."
            r1.<init>(r2)
            throw r1
        L1ce:
            r6 = 0
            r7 = 1
            goto Lb9
        L1d2:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.toString():java.lang.String");
    }

    public final boolean tryResumeReceiver(Object obj, Object obj2) {
        if (!(obj instanceof BufferedChannelIterator)) {
            if (!(obj instanceof CancellableContinuation)) {
                throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
            }
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
            Function1 function1 = this.onUndeliveredElement;
            return BufferedChannelKt.tryResume0(cancellableContinuation, obj2, function1 != null ? new OnUndeliveredElementKt$bindCancellationFun$1(function1, obj2, cancellableContinuation.getContext()) : null);
        }
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
        BufferedChannelIterator bufferedChannelIterator = (BufferedChannelIterator) obj;
        CancellableContinuationImpl cancellableContinuationImpl = bufferedChannelIterator.continuation;
        ResultKt.checkNotNull(cancellableContinuationImpl);
        bufferedChannelIterator.continuation = null;
        bufferedChannelIterator.receiveResult = obj2;
        Boolean bool = Boolean.TRUE;
        Function1 function12 = BufferedChannel.this.onUndeliveredElement;
        return BufferedChannelKt.tryResume0(cancellableContinuationImpl, bool, function12 != null ? new OnUndeliveredElementKt$bindCancellationFun$1(function12, obj2, cancellableContinuationImpl.context) : null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
    
        return r1;
     */
    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo306trySendJP2dKIU(java.lang.Object r23) {
        /*
            r22 = this;
            r8 = r22
            java.util.concurrent.atomic.AtomicLongFieldUpdater r9 = kotlinx.coroutines.channels.BufferedChannel.sendersAndCloseStatus$FU
            long r0 = r9.get(r8)
            r10 = 0
            boolean r2 = r8.isClosed(r0, r10)
            kotlinx.coroutines.channels.ChannelResult$Failed r11 = kotlinx.coroutines.channels.ChannelResult.failed
            r12 = 1
            r13 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            if (r2 == 0) goto L18
            goto L21
        L18:
            long r0 = r0 & r13
            boolean r0 = r8.bufferOrRendezvousSend(r0)
            r0 = r0 ^ r12
            if (r0 == 0) goto L21
            return r11
        L21:
            kotlinx.coroutines.internal.Symbol r15 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.sendSegment$FU
            java.lang.Object r0 = r0.get(r8)
            kotlinx.coroutines.channels.ChannelSegment r0 = (kotlinx.coroutines.channels.ChannelSegment) r0
        L2b:
            long r1 = r9.getAndIncrement(r8)
            long r16 = r1 & r13
            boolean r18 = r8.isClosed(r1, r10)
            int r7 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r1 = (long) r7
            long r3 = r16 / r1
            long r1 = r16 % r1
            int r6 = (int) r1
            long r1 = r0.id
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L58
            kotlinx.coroutines.channels.ChannelSegment r1 = access$findSegmentSend(r8, r3, r0)
            if (r1 != 0) goto L56
            if (r18 == 0) goto L2b
            java.lang.Throwable r0 = r22.getSendException()
            kotlinx.coroutines.channels.ChannelResult$Closed r11 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r11.<init>(r0)
            goto Ld9
        L56:
            r4 = r1
            goto L59
        L58:
            r4 = r0
        L59:
            r0 = r22
            r1 = r4
            r2 = r6
            r3 = r23
            r19 = r4
            r4 = r16
            r20 = r6
            r6 = r15
            r21 = r7
            r7 = r18
            int r0 = access$updateCellSend(r0, r1, r2, r3, r4, r6, r7)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            if (r0 == 0) goto Ld3
            if (r0 == r12) goto Ld1
            r1 = 2
            if (r0 == r1) goto Laa
            r1 = 3
            if (r0 == r1) goto L9e
            r1 = 4
            if (r0 == r1) goto L87
            r1 = 5
            if (r0 == r1) goto L81
            goto L84
        L81:
            r19.cleanPrev()
        L84:
            r0 = r19
            goto L2b
        L87:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = kotlinx.coroutines.channels.BufferedChannel.receivers$FU
            long r0 = r0.get(r8)
            int r0 = (r16 > r0 ? 1 : (r16 == r0 ? 0 : -1))
            if (r0 >= 0) goto L94
            r19.cleanPrev()
        L94:
            java.lang.Throwable r0 = r22.getSendException()
            kotlinx.coroutines.channels.ChannelResult$Closed r11 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r11.<init>(r0)
            goto Ld9
        L9e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "unexpected"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        Laa:
            if (r18 == 0) goto Lb9
            r19.onSlotCleaned()
            java.lang.Throwable r0 = r22.getSendException()
            kotlinx.coroutines.channels.ChannelResult$Closed r11 = new kotlinx.coroutines.channels.ChannelResult$Closed
            r11.<init>(r0)
            goto Ld9
        Lb9:
            boolean r0 = r15 instanceof kotlinx.coroutines.Waiter
            if (r0 == 0) goto Lc0
            kotlinx.coroutines.Waiter r15 = (kotlinx.coroutines.Waiter) r15
            goto Lc1
        Lc0:
            r15 = 0
        Lc1:
            if (r15 == 0) goto Lcb
            int r6 = r20 + r21
            r0 = r19
            r15.invokeOnCancellation(r0, r6)
            goto Lcd
        Lcb:
            r0 = r19
        Lcd:
            r0.onSlotCleaned()
            goto Ld9
        Ld1:
            r11 = r1
            goto Ld9
        Ld3:
            r0 = r19
            r0.cleanPrev()
            goto Ld1
        Ld9:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BufferedChannel.mo306trySendJP2dKIU(java.lang.Object):java.lang.Object");
    }

    public final Object updateCellReceive(ChannelSegment channelSegment, int i, long j, BufferedChannelIterator bufferedChannelIterator) {
        Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
        AtomicReferenceArray atomicReferenceArray = channelSegment.data;
        AtomicLongFieldUpdater atomicLongFieldUpdater = sendersAndCloseStatus$FU;
        if (state$kotlinx_coroutines_core == null) {
            if (j >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (bufferedChannelIterator == null) {
                    return BufferedChannelKt.SUSPEND_NO_WAITER;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core, i, bufferedChannelIterator)) {
                    expandBuffer();
                    return BufferedChannelKt.SUSPEND;
                }
            }
        } else if (state$kotlinx_coroutines_core == BufferedChannelKt.BUFFERED && channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core, i, BufferedChannelKt.DONE_RCV)) {
            expandBuffer();
            Object obj = atomicReferenceArray.get(i * 2);
            channelSegment.setElementLazy(i, null);
            return obj;
        }
        while (true) {
            Object state$kotlinx_coroutines_core2 = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core2 == null || state$kotlinx_coroutines_core2 == BufferedChannelKt.IN_BUFFER) {
                if (j < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core2, i, BufferedChannelKt.POISONED)) {
                        expandBuffer();
                        return BufferedChannelKt.FAILED;
                    }
                } else {
                    if (bufferedChannelIterator == null) {
                        return BufferedChannelKt.SUSPEND_NO_WAITER;
                    }
                    if (channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core2, i, bufferedChannelIterator)) {
                        expandBuffer();
                        return BufferedChannelKt.SUSPEND;
                    }
                }
            } else {
                if (state$kotlinx_coroutines_core2 != BufferedChannelKt.BUFFERED) {
                    Symbol symbol = BufferedChannelKt.INTERRUPTED_SEND;
                    if (state$kotlinx_coroutines_core2 != symbol && state$kotlinx_coroutines_core2 != BufferedChannelKt.POISONED) {
                        if (state$kotlinx_coroutines_core2 == BufferedChannelKt.CHANNEL_CLOSED) {
                            expandBuffer();
                            return BufferedChannelKt.FAILED;
                        }
                        if (state$kotlinx_coroutines_core2 != BufferedChannelKt.RESUMING_BY_EB && channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core2, i, BufferedChannelKt.RESUMING_BY_RCV)) {
                            boolean z = state$kotlinx_coroutines_core2 instanceof WaiterEB;
                            if (z) {
                                state$kotlinx_coroutines_core2 = ((WaiterEB) state$kotlinx_coroutines_core2).waiter;
                            }
                            if (tryResumeSender(state$kotlinx_coroutines_core2)) {
                                channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                                expandBuffer();
                                Object obj2 = atomicReferenceArray.get(i * 2);
                                channelSegment.setElementLazy(i, null);
                                return obj2;
                            }
                            channelSegment.setState$kotlinx_coroutines_core(i, symbol);
                            channelSegment.onCancelledRequest(i, false);
                            if (z) {
                                expandBuffer();
                            }
                            return BufferedChannelKt.FAILED;
                        }
                    }
                    return BufferedChannelKt.FAILED;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core2, i, BufferedChannelKt.DONE_RCV)) {
                    expandBuffer();
                    Object obj3 = atomicReferenceArray.get(i * 2);
                    channelSegment.setElementLazy(i, null);
                    return obj3;
                }
            }
        }
    }

    public final int updateCellSendSlow(ChannelSegment channelSegment, int i, Object obj, long j, Object obj2, boolean z) {
        while (true) {
            Object state$kotlinx_coroutines_core = channelSegment.getState$kotlinx_coroutines_core(i);
            if (state$kotlinx_coroutines_core == null) {
                if (!bufferOrRendezvousSend(j) || z) {
                    if (z) {
                        if (channelSegment.casState$kotlinx_coroutines_core(null, i, BufferedChannelKt.INTERRUPTED_SEND)) {
                            channelSegment.onCancelledRequest(i, false);
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (channelSegment.casState$kotlinx_coroutines_core(null, i, obj2)) {
                            return 2;
                        }
                    }
                } else if (channelSegment.casState$kotlinx_coroutines_core(null, i, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (state$kotlinx_coroutines_core != BufferedChannelKt.IN_BUFFER) {
                    Symbol symbol = BufferedChannelKt.INTERRUPTED_RCV;
                    if (state$kotlinx_coroutines_core == symbol) {
                        channelSegment.setElementLazy(i, null);
                        return 5;
                    }
                    if (state$kotlinx_coroutines_core == BufferedChannelKt.POISONED) {
                        channelSegment.setElementLazy(i, null);
                        return 5;
                    }
                    if (state$kotlinx_coroutines_core == BufferedChannelKt.CHANNEL_CLOSED) {
                        channelSegment.setElementLazy(i, null);
                        completeCloseOrCancel();
                        return 4;
                    }
                    channelSegment.setElementLazy(i, null);
                    if (state$kotlinx_coroutines_core instanceof WaiterEB) {
                        state$kotlinx_coroutines_core = ((WaiterEB) state$kotlinx_coroutines_core).waiter;
                    }
                    if (tryResumeReceiver(state$kotlinx_coroutines_core, obj)) {
                        channelSegment.setState$kotlinx_coroutines_core(i, BufferedChannelKt.DONE_RCV);
                        return 0;
                    }
                    if (channelSegment.data.getAndSet((i * 2) + 1, symbol) != symbol) {
                        channelSegment.onCancelledRequest(i, true);
                    }
                    return 5;
                }
                if (channelSegment.casState$kotlinx_coroutines_core(state$kotlinx_coroutines_core, i, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            }
        }
    }
}
