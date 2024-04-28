package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.ChannelFlowOperator;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class StateFlowImpl extends AbstractSharedFlow implements MutableStateFlow, Flow, FusibleFlow {
    public static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowImpl.class, Object.class, "_state");
    private volatile Object _state;
    public int sequence;

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX DEBUG: Duplicate block (B:37:0x00fd) to fix multi-entry loop: BACK_EDGE: B:37:0x00fd -> B:15:0x007e */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0086 A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:13:0x0039, B:15:0x007e, B:17:0x0086, B:20:0x008d, B:21:0x0093, B:25:0x0096, B:27:0x00b7, B:30:0x00ca, B:31:0x00e2, B:37:0x00f6, B:33:0x00ed, B:36:0x00f3, B:46:0x009c, B:49:0x00a3, B:57:0x0053, B:59:0x005d, B:60:0x006e), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ca A[Catch: all -> 0x003e, TryCatch #0 {all -> 0x003e, blocks: (B:13:0x0039, B:15:0x007e, B:17:0x0086, B:20:0x008d, B:21:0x0093, B:25:0x0096, B:27:0x00b7, B:30:0x00ca, B:31:0x00e2, B:37:0x00f6, B:33:0x00ed, B:36:0x00f3, B:46:0x009c, B:49:0x00a3, B:57:0x0053, B:59:0x005d, B:60:0x006e), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a2  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00c9 -> B:15:0x007e). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r17, kotlin.coroutines.Continuation r18) {
        /*
            r16 = this;
            r0 = r18
            boolean r1 = r0 instanceof kotlinx.coroutines.flow.StateFlowImpl$collect$1
            if (r1 == 0) goto L17
            r1 = r0
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r1 = (kotlinx.coroutines.flow.StateFlowImpl$collect$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.label = r2
            r2 = r16
            goto L1e
        L17:
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r1 = new kotlinx.coroutines.flow.StateFlowImpl$collect$1
            r2 = r16
            r1.<init>(r2, r0)
        L1e:
            java.lang.Object r0 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r1.label
            r6 = 3
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L61
            if (r4 == r8) goto L57
            if (r4 == r7) goto L49
            if (r4 != r6) goto L41
            java.lang.Object r4 = r1.L$4
            kotlinx.coroutines.Job r9 = r1.L$3
            kotlinx.coroutines.flow.StateFlowSlot r10 = r1.L$2
            kotlinx.coroutines.flow.FlowCollector r11 = r1.L$1
            kotlinx.coroutines.flow.StateFlowImpl r12 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3e
            r0 = r4
            goto L7e
        L3e:
            r0 = move-exception
            goto L100
        L41:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L49:
            java.lang.Object r4 = r1.L$4
            kotlinx.coroutines.Job r9 = r1.L$3
            kotlinx.coroutines.flow.StateFlowSlot r10 = r1.L$2
            kotlinx.coroutines.flow.FlowCollector r11 = r1.L$1
            kotlinx.coroutines.flow.StateFlowImpl r12 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3e
            goto Lb6
        L57:
            kotlinx.coroutines.flow.StateFlowSlot r10 = r1.L$2
            kotlinx.coroutines.flow.FlowCollector r4 = r1.L$1
            kotlinx.coroutines.flow.StateFlowImpl r12 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3e
            goto L6e
        L61:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot r0 = r16.allocateSlot()
            kotlinx.coroutines.flow.StateFlowSlot r0 = (kotlinx.coroutines.flow.StateFlowSlot) r0
            r4 = r17
            r10 = r0
            r12 = r2
        L6e:
            kotlin.coroutines.CoroutineContext r0 = r1._context     // Catch: java.lang.Throwable -> L3e
            kotlin.ResultKt.checkNotNull(r0)     // Catch: java.lang.Throwable -> L3e
            kotlinx.coroutines.Job$Key r9 = kotlinx.coroutines.Job.Key.$$INSTANCE     // Catch: java.lang.Throwable -> L3e
            kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r9)     // Catch: java.lang.Throwable -> L3e
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0     // Catch: java.lang.Throwable -> L3e
            r9 = r0
            r11 = r4
            r0 = 0
        L7e:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.flow.StateFlowImpl._state$FU     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r4 = r4.get(r12)     // Catch: java.lang.Throwable -> L3e
            if (r9 == 0) goto L94
            boolean r13 = r9.isActive()     // Catch: java.lang.Throwable -> L3e
            if (r13 == 0) goto L8d
            goto L94
        L8d:
            kotlinx.coroutines.JobSupport r9 = (kotlinx.coroutines.JobSupport) r9     // Catch: java.lang.Throwable -> L3e
            java.util.concurrent.CancellationException r0 = r9.getCancellationException()     // Catch: java.lang.Throwable -> L3e
            throw r0     // Catch: java.lang.Throwable -> L3e
        L94:
            if (r0 == 0) goto L9c
            boolean r13 = kotlin.ResultKt.areEqual(r0, r4)     // Catch: java.lang.Throwable -> L3e
            if (r13 != 0) goto Lb7
        L9c:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch: java.lang.Throwable -> L3e
            if (r4 != r0) goto La2
            r0 = 0
            goto La3
        La2:
            r0 = r4
        La3:
            r1.L$0 = r12     // Catch: java.lang.Throwable -> L3e
            r1.L$1 = r11     // Catch: java.lang.Throwable -> L3e
            r1.L$2 = r10     // Catch: java.lang.Throwable -> L3e
            r1.L$3 = r9     // Catch: java.lang.Throwable -> L3e
            r1.L$4 = r4     // Catch: java.lang.Throwable -> L3e
            r1.label = r7     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r0 = r11.emit(r0, r1)     // Catch: java.lang.Throwable -> L3e
            if (r0 != r3) goto Lb6
            return r3
        Lb6:
            r0 = r4
        Lb7:
            r10.getClass()     // Catch: java.lang.Throwable -> L3e
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.StateFlowKt.NONE     // Catch: java.lang.Throwable -> L3e
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r13 = kotlinx.coroutines.flow.StateFlowSlot._state$FU     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r13 = r13.getAndSet(r10, r4)     // Catch: java.lang.Throwable -> L3e
            kotlin.ResultKt.checkNotNull(r13)     // Catch: java.lang.Throwable -> L3e
            kotlinx.coroutines.internal.Symbol r14 = kotlinx.coroutines.flow.StateFlowKt.PENDING     // Catch: java.lang.Throwable -> L3e
            if (r13 != r14) goto Lca
            goto L7e
        Lca:
            r1.L$0 = r12     // Catch: java.lang.Throwable -> L3e
            r1.L$1 = r11     // Catch: java.lang.Throwable -> L3e
            r1.L$2 = r10     // Catch: java.lang.Throwable -> L3e
            r1.L$3 = r9     // Catch: java.lang.Throwable -> L3e
            r1.L$4 = r0     // Catch: java.lang.Throwable -> L3e
            r1.label = r6     // Catch: java.lang.Throwable -> L3e
            kotlinx.coroutines.CancellableContinuationImpl r13 = new kotlinx.coroutines.CancellableContinuationImpl     // Catch: java.lang.Throwable -> L3e
            kotlin.coroutines.Continuation r14 = kotlin.ResultKt.intercepted(r1)     // Catch: java.lang.Throwable -> L3e
            r13.<init>(r8, r14)     // Catch: java.lang.Throwable -> L3e
            r13.initCancellability()     // Catch: java.lang.Throwable -> L3e
        Le2:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = kotlinx.coroutines.flow.StateFlowSlot._state$FU     // Catch: java.lang.Throwable -> L3e
            boolean r15 = r14.compareAndSet(r10, r4, r13)     // Catch: java.lang.Throwable -> L3e
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L3e
            if (r15 == 0) goto Led
            goto Lf6
        Led:
            java.lang.Object r14 = r14.get(r10)     // Catch: java.lang.Throwable -> L3e
            if (r14 == r4) goto Le2
            r13.resumeWith(r5)     // Catch: java.lang.Throwable -> L3e
        Lf6:
            java.lang.Object r4 = r13.getResult()     // Catch: java.lang.Throwable -> L3e
            if (r4 != r3) goto Lfd
            r5 = r4
        Lfd:
            if (r5 != r3) goto L7e
            return r3
        L100:
            r12.freeSlot(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot, java.lang.Object] */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final AbstractSharedFlowSlot createSlot() {
        return new Object();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final AbstractSharedFlowSlot[] createSlotArray() {
        return new StateFlowSlot[2];
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        setValue(obj);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public final Flow fuse(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        return ((((i < 0 || i >= 2) && i != -2) || bufferOverflow != BufferOverflow.DROP_OLDEST) && !((i == 0 || i == -3) && bufferOverflow == BufferOverflow.SUSPEND)) ? new ChannelFlowOperator(i, coroutineContext, bufferOverflow, this) : this;
    }

    @Override // kotlinx.coroutines.flow.StateFlow
    public final Object getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        Object obj = _state$FU.get(this);
        if (obj == symbol) {
            return null;
        }
        return obj;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public final void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    public final void setValue(Object obj) {
        if (obj == null) {
            obj = NullSurrogateKt.NULL;
        }
        updateState(null, obj);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public final boolean tryEmit(Object obj) {
        setValue(obj);
        return true;
    }

    public final boolean updateState(Object obj, Object obj2) {
        int i;
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        Symbol symbol;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !ResultKt.areEqual(obj3, obj)) {
                return false;
            }
            if (ResultKt.areEqual(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i2 = this.sequence;
            if ((i2 & 1) != 0) {
                this.sequence = i2 + 2;
                return true;
            }
            int i3 = i2 + 1;
            this.sequence = i3;
            AbstractSharedFlowSlot[] abstractSharedFlowSlotArr2 = this.slots;
            while (true) {
                StateFlowSlot[] stateFlowSlotArr = (StateFlowSlot[]) abstractSharedFlowSlotArr2;
                if (stateFlowSlotArr != null) {
                    for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                        if (stateFlowSlot != null) {
                            while (true) {
                                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = StateFlowSlot._state$FU;
                                Object obj4 = atomicReferenceFieldUpdater2.get(stateFlowSlot);
                                if (obj4 != null && obj4 != (symbol = StateFlowKt.PENDING)) {
                                    Symbol symbol2 = StateFlowKt.NONE;
                                    if (obj4 != symbol2) {
                                        while (!atomicReferenceFieldUpdater2.compareAndSet(stateFlowSlot, obj4, symbol2)) {
                                            if (atomicReferenceFieldUpdater2.get(stateFlowSlot) != obj4) {
                                                break;
                                            }
                                        }
                                        ((CancellableContinuationImpl) obj4).resumeWith(Unit.INSTANCE);
                                        break;
                                    }
                                    while (!atomicReferenceFieldUpdater2.compareAndSet(stateFlowSlot, obj4, symbol)) {
                                        if (atomicReferenceFieldUpdater2.get(stateFlowSlot) != obj4) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                synchronized (this) {
                    i = this.sequence;
                    if (i == i3) {
                        this.sequence = i3 + 1;
                        return true;
                    }
                    abstractSharedFlowSlotArr = this.slots;
                }
                abstractSharedFlowSlotArr2 = abstractSharedFlowSlotArr;
                i3 = i;
            }
        }
    }
}
