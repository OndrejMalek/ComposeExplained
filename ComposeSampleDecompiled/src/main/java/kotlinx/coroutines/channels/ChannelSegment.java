package kotlinx.coroutines.channels;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class ChannelSegment extends Segment {
    public final BufferedChannel _channel;
    public final AtomicReferenceArray data;

    public ChannelSegment(long j, ChannelSegment channelSegment, BufferedChannel bufferedChannel, int i) {
        super(j, channelSegment, i);
        this._channel = bufferedChannel;
        this.data = new AtomicReferenceArray(BufferedChannelKt.SEGMENT_SIZE * 2);
    }

    public final boolean casState$kotlinx_coroutines_core(Object obj, int i, Object obj2) {
        AtomicReferenceArray atomicReferenceArray = this.data;
        int i2 = (i * 2) + 1;
        while (!atomicReferenceArray.compareAndSet(i2, obj, obj2)) {
            if (atomicReferenceArray.get(i2) != obj) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlinx.coroutines.internal.Segment
    public final int getNumberOfSlots() {
        return BufferedChannelKt.SEGMENT_SIZE;
    }

    public final Object getState$kotlinx_coroutines_core(int i) {
        return this.data.get((i * 2) + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x005b, code lost:
    
        setElementLazy(r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x005e, code lost:
    
        if (r1 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0060, code lost:
    
        kotlin.ResultKt.checkNotNull(r5);
        r7 = r5.onUndeliveredElement;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0065, code lost:
    
        if (r7 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0067, code lost:
    
        r7 = kotlinx.coroutines.JobKt.callUndeliveredElementCatchingException(r7, r0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x006b, code lost:
    
        if (r7 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x006d, code lost:
    
        kotlin.ResultKt.handleCoroutineException(r8, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0070, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
    
        return;
     */
    @Override // kotlinx.coroutines.internal.Segment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCancellation(int r7, kotlin.coroutines.CoroutineContext r8) {
        /*
            r6 = this;
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            if (r7 < r0) goto L6
            r1 = 1
            goto L7
        L6:
            r1 = 0
        L7:
            if (r1 == 0) goto La
            int r7 = r7 - r0
        La:
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r6.data
            int r2 = r7 * 2
            java.lang.Object r0 = r0.get(r2)
        L12:
            java.lang.Object r2 = r6.getState$kotlinx_coroutines_core(r7)
            boolean r3 = r2 instanceof kotlinx.coroutines.Waiter
            r4 = 0
            kotlinx.coroutines.channels.BufferedChannel r5 = r6._channel
            if (r3 != 0) goto L71
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r3 == 0) goto L22
            goto L71
        L22:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            if (r2 == r3) goto L5b
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            if (r2 != r3) goto L2b
            goto L5b
        L2b:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r2 == r3) goto L12
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r2 != r3) goto L34
            goto L12
        L34:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r2 == r7) goto L5a
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r2 != r7) goto L3d
            goto L5a
        L3d:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r2 != r7) goto L42
            return
        L42:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "unexpected state: "
            r8.<init>(r0)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L5a:
            return
        L5b:
            r6.setElementLazy(r7, r4)
            if (r1 == 0) goto L70
            kotlin.ResultKt.checkNotNull(r5)
            kotlin.jvm.functions.Function1 r7 = r5.onUndeliveredElement
            if (r7 == 0) goto L70
            androidx.startup.StartupException r7 = kotlinx.coroutines.JobKt.callUndeliveredElementCatchingException(r7, r0, r4)
            if (r7 == 0) goto L70
            kotlin.ResultKt.handleCoroutineException(r8, r7)
        L70:
            return
        L71:
            if (r1 == 0) goto L76
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            goto L78
        L76:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
        L78:
            boolean r2 = r6.casState$kotlinx_coroutines_core(r2, r7, r3)
            if (r2 == 0) goto L12
            r6.setElementLazy(r7, r4)
            r2 = r1 ^ 1
            r6.onCancelledRequest(r7, r2)
            if (r1 == 0) goto L98
            kotlin.ResultKt.checkNotNull(r5)
            kotlin.jvm.functions.Function1 r7 = r5.onUndeliveredElement
            if (r7 == 0) goto L98
            androidx.startup.StartupException r7 = kotlinx.coroutines.JobKt.callUndeliveredElementCatchingException(r7, r0, r4)
            if (r7 == 0) goto L98
            kotlin.ResultKt.handleCoroutineException(r8, r7)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelSegment.onCancellation(int, kotlin.coroutines.CoroutineContext):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        if (r2 >= r1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0032, code lost:
    
        r3 = r8.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
    
        if (r3 != (r10.get(r7) & 4611686018427387903L)) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:
    
        if (r3 != r8.get(r7)) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0048, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        r3 = r10.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
    
        if (r10.compareAndSet(r7, r3, 4611686018427387904L + (r3 & 4611686018427387903L)) == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005d, code lost:
    
        r1 = r8.get(r7);
        r10 = kotlinx.coroutines.channels.BufferedChannel.completedExpandBuffersAndPauseFlag$FU;
        r3 = r10.get(r7);
        r5 = r3 & 4611686018427387903L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006f, code lost:
    
        if ((r3 & 4611686018427387904L) == 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0071, code lost:
    
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0076, code lost:
    
        if (r1 != r5) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007e, code lost:
    
        if (r1 != r8.get(r7)) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0080, code lost:
    
        r3 = r10.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008c, code lost:
    
        if (r10.compareAndSet(r7, r3, r3 & 4611686018427387903L) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008f, code lost:
    
        if (r15 != false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
    
        r10.compareAndSet(r7, r3, r5 + 4611686018427387904L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
    
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0017, code lost:
    
        if (r7.isRendezvousOrUnlimited() != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001b, code lost:
    
        r8 = kotlinx.coroutines.channels.BufferedChannel.bufferEnd$FU;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
    
        if (r8.get(r7) <= r3) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
    
        r1 = kotlinx.coroutines.channels.BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0029, code lost:
    
        r10 = kotlinx.coroutines.channels.BufferedChannel.completedExpandBuffersAndPauseFlag$FU;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCancelledRequest(int r20, boolean r21) {
        /*
            r19 = this;
            r0 = r19
            if (r21 == 0) goto L98
            kotlinx.coroutines.channels.BufferedChannel r7 = r0._channel
            kotlin.ResultKt.checkNotNull(r7)
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            long r1 = (long) r1
            long r3 = r0.id
            long r3 = r3 * r1
            r1 = r20
            long r1 = (long) r1
            long r3 = r3 + r1
            boolean r1 = r7.isRendezvousOrUnlimited()
            if (r1 == 0) goto L1b
            goto L98
        L1b:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = kotlinx.coroutines.channels.BufferedChannel.bufferEnd$FU
            long r1 = r8.get(r7)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L1b
            int r1 = kotlinx.coroutines.channels.BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS
            r9 = 0
            r2 = r9
        L29:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r10 = kotlinx.coroutines.channels.BufferedChannel.completedExpandBuffersAndPauseFlag$FU
            r11 = 4611686018427387903(0x3fffffffffffffff, double:1.9999999999999998)
            if (r2 >= r1) goto L4b
            long r3 = r8.get(r7)
            long r5 = r10.get(r7)
            long r5 = r5 & r11
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 != 0) goto L48
            long r5 = r8.get(r7)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L48
            goto L98
        L48:
            int r2 = r2 + 1
            goto L29
        L4b:
            long r3 = r10.get(r7)
            long r1 = r3 & r11
            r13 = 4611686018427387904(0x4000000000000000, double:2.0)
            long r5 = r13 + r1
            r1 = r10
            r2 = r7
            boolean r1 = r1.compareAndSet(r2, r3, r5)
            if (r1 == 0) goto L4b
        L5d:
            long r1 = r8.get(r7)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r10 = kotlinx.coroutines.channels.BufferedChannel.completedExpandBuffersAndPauseFlag$FU
            long r3 = r10.get(r7)
            long r5 = r3 & r11
            long r15 = r3 & r13
            r17 = 0
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 == 0) goto L73
            r15 = 1
            goto L74
        L73:
            r15 = r9
        L74:
            int r16 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r16 != 0) goto L8f
            long r16 = r8.get(r7)
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 != 0) goto L8f
        L80:
            long r3 = r10.get(r7)
            long r5 = r3 & r11
            r1 = r10
            r2 = r7
            boolean r1 = r1.compareAndSet(r2, r3, r5)
            if (r1 == 0) goto L80
            goto L98
        L8f:
            if (r15 != 0) goto L5d
            long r5 = r5 + r13
            r1 = r10
            r2 = r7
            r1.compareAndSet(r2, r3, r5)
            goto L5d
        L98:
            r19.onSlotCleaned()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelSegment.onCancelledRequest(int, boolean):void");
    }

    public final void setElementLazy(int i, Object obj) {
        this.data.lazySet(i * 2, obj);
    }

    public final void setState$kotlinx_coroutines_core(int i, Symbol symbol) {
        this.data.set((i * 2) + 1, symbol);
    }
}
