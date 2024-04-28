package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes.dex */
public class SemaphoreImpl {
    private volatile int _availablePermits;
    private volatile long deqIdx;
    private volatile long enqIdx;
    private volatile Object head;
    public final AbstractMap$toString$1 onCancellationRelease;
    public final int permits = 1;
    private volatile Object tail;
    public static final AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
    public static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    public static final AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    public static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    public static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("The number of acquired permits should be in 0..1".toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment;
        this.tail = semaphoreSegment;
        this._availablePermits = 1 - i;
        this.onCancellationRelease = new AbstractMap$toString$1(23, this);
    }

    public final void acquire(MutexImpl.CancellableContinuationWithOwner cancellableContinuationWithOwner) {
        Object findSegmentInternal;
        Unit unit;
        CancellableContinuationImpl cancellableContinuationImpl;
        SemaphoreImpl$addAcquireToQueue$createNewSegment$1 semaphoreImpl$addAcquireToQueue$createNewSegment$1;
        long j;
        while (true) {
            int andDecrement = _availablePermits$FU.getAndDecrement(this);
            if (andDecrement <= this.permits) {
                Unit unit2 = Unit.INSTANCE;
                CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationWithOwner.cont;
                MutexImpl mutexImpl = MutexImpl.this;
                Object obj = cancellableContinuationWithOwner.owner;
                if (andDecrement > 0) {
                    MutexImpl.owner$FU.set(mutexImpl, obj);
                    cancellableContinuationImpl2.resume(unit2, new MutexImpl$CancellableContinuationWithOwner$resume$2(mutexImpl, cancellableContinuationWithOwner, 0));
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = tail$FU;
                SemaphoreSegment semaphoreSegment = (SemaphoreSegment) atomicReferenceFieldUpdater.get(this);
                long andIncrement = enqIdx$FU.getAndIncrement(this);
                SemaphoreImpl$addAcquireToQueue$createNewSegment$1 semaphoreImpl$addAcquireToQueue$createNewSegment$12 = SemaphoreImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
                long j2 = andIncrement / SemaphoreKt.SEGMENT_SIZE;
                while (true) {
                    findSegmentInternal = AtomicKt.findSegmentInternal(semaphoreSegment, j2, semaphoreImpl$addAcquireToQueue$createNewSegment$12);
                    if (!JobKt.m304isClosedimpl(findSegmentInternal)) {
                        Segment m303getSegmentimpl = JobKt.m303getSegmentimpl(findSegmentInternal);
                        while (true) {
                            Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                            semaphoreImpl$addAcquireToQueue$createNewSegment$1 = semaphoreImpl$addAcquireToQueue$createNewSegment$12;
                            j = j2;
                            unit = unit2;
                            cancellableContinuationImpl = cancellableContinuationImpl2;
                            if (segment.id >= m303getSegmentimpl.id) {
                                break;
                            }
                            if (!m303getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                                break;
                            }
                            while (!atomicReferenceFieldUpdater.compareAndSet(this, segment, m303getSegmentimpl)) {
                                if (atomicReferenceFieldUpdater.get(this) != segment) {
                                    if (m303getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                                        m303getSegmentimpl.remove();
                                    }
                                    unit2 = unit;
                                    semaphoreImpl$addAcquireToQueue$createNewSegment$12 = semaphoreImpl$addAcquireToQueue$createNewSegment$1;
                                    j2 = j;
                                    cancellableContinuationImpl2 = cancellableContinuationImpl;
                                }
                            }
                            if (segment.decPointers$kotlinx_coroutines_core()) {
                                segment.remove();
                            }
                        }
                    } else {
                        unit = unit2;
                        cancellableContinuationImpl = cancellableContinuationImpl2;
                        break;
                    }
                    unit2 = unit;
                    semaphoreImpl$addAcquireToQueue$createNewSegment$12 = semaphoreImpl$addAcquireToQueue$createNewSegment$1;
                    j2 = j;
                    cancellableContinuationImpl2 = cancellableContinuationImpl;
                }
                SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) JobKt.m303getSegmentimpl(findSegmentInternal);
                int i = (int) (andIncrement % SemaphoreKt.SEGMENT_SIZE);
                AtomicReferenceArray atomicReferenceArray = semaphoreSegment2.acquirers;
                while (!atomicReferenceArray.compareAndSet(i, null, cancellableContinuationWithOwner)) {
                    if (atomicReferenceArray.get(i) != null) {
                        Symbol symbol = SemaphoreKt.PERMIT;
                        Symbol symbol2 = SemaphoreKt.TAKEN;
                        while (!atomicReferenceArray.compareAndSet(i, symbol, symbol2)) {
                            CancellableContinuationImpl cancellableContinuationImpl3 = cancellableContinuationImpl;
                            if (atomicReferenceArray.get(i) != symbol) {
                                break;
                            } else {
                                cancellableContinuationImpl = cancellableContinuationImpl3;
                            }
                        }
                        MutexImpl.owner$FU.set(mutexImpl, obj);
                        cancellableContinuationImpl.resume(unit, new MutexImpl$CancellableContinuationWithOwner$resume$2(mutexImpl, cancellableContinuationWithOwner, 0));
                        return;
                    }
                }
                cancellableContinuationWithOwner.invokeOnCancellation(semaphoreSegment2, i);
                return;
            }
        }
    }

    public final void release() {
        int i;
        Object findSegmentInternal;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _availablePermits$FU;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            int i2 = this.permits;
            if (andIncrement >= i2) {
                do {
                    i = atomicIntegerFieldUpdater.get(this);
                    if (i <= i2) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, i2));
                throw new IllegalStateException(("The number of released permits cannot be greater than " + i2).toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = head$FU;
            SemaphoreSegment semaphoreSegment = (SemaphoreSegment) atomicReferenceFieldUpdater.get(this);
            long andIncrement2 = deqIdx$FU.getAndIncrement(this);
            long j = andIncrement2 / SemaphoreKt.SEGMENT_SIZE;
            SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 = SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
            while (true) {
                findSegmentInternal = AtomicKt.findSegmentInternal(semaphoreSegment, j, semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1);
                if (JobKt.m304isClosedimpl(findSegmentInternal)) {
                    break;
                }
                Segment m303getSegmentimpl = JobKt.m303getSegmentimpl(findSegmentInternal);
                while (true) {
                    Segment segment = (Segment) atomicReferenceFieldUpdater.get(this);
                    if (segment.id >= m303getSegmentimpl.id) {
                        break;
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
            }
            SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) JobKt.m303getSegmentimpl(findSegmentInternal);
            semaphoreSegment2.cleanPrev();
            if (semaphoreSegment2.id <= j) {
                int i3 = (int) (andIncrement2 % SemaphoreKt.SEGMENT_SIZE);
                Symbol symbol = SemaphoreKt.PERMIT;
                AtomicReferenceArray atomicReferenceArray = semaphoreSegment2.acquirers;
                Object andSet = atomicReferenceArray.getAndSet(i3, symbol);
                if (andSet == null) {
                    int i4 = SemaphoreKt.MAX_SPIN_CYCLES;
                    boolean z = false;
                    for (int i5 = 0; i5 < i4; i5++) {
                        if (atomicReferenceArray.get(i3) == SemaphoreKt.TAKEN) {
                            return;
                        }
                    }
                    Symbol symbol2 = SemaphoreKt.PERMIT;
                    Symbol symbol3 = SemaphoreKt.BROKEN;
                    while (true) {
                        if (!atomicReferenceArray.compareAndSet(i3, symbol2, symbol3)) {
                            if (atomicReferenceArray.get(i3) != symbol2) {
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        return;
                    }
                } else if (andSet == SemaphoreKt.CANCELLED) {
                    continue;
                } else {
                    if (!(andSet instanceof CancellableContinuation)) {
                        throw new IllegalStateException(("unexpected: " + andSet).toString());
                    }
                    CancellableContinuation cancellableContinuation = (CancellableContinuation) andSet;
                    Symbol tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, this.onCancellationRelease);
                    if (tryResume != null) {
                        cancellableContinuation.completeResume(tryResume);
                        return;
                    }
                }
            }
        }
    }
}
