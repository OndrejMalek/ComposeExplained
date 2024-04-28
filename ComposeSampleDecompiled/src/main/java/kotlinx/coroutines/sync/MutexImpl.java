package kotlinx.coroutines.sync;

import _COROUTINE._BOUNDARY;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class MutexImpl extends SemaphoreImpl implements Mutex {
    public static final AtomicReferenceFieldUpdater owner$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "owner");
    private volatile Object owner;

    /* loaded from: classes.dex */
    public final class CancellableContinuationWithOwner implements CancellableContinuation, Waiter {
        public final CancellableContinuationImpl cont;
        public final Object owner;

        public CancellableContinuationWithOwner(CancellableContinuationImpl cancellableContinuationImpl, Object obj) {
            this.cont = cancellableContinuationImpl;
            this.owner = obj;
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final boolean cancel(Throwable th) {
            return this.cont.cancel(th);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final void completeResume(Object obj) {
            this.cont.completeResume(obj);
        }

        @Override // kotlin.coroutines.Continuation
        public final CoroutineContext getContext() {
            return this.cont.context;
        }

        @Override // kotlinx.coroutines.Waiter
        public final void invokeOnCancellation(Segment segment, int i) {
            this.cont.invokeOnCancellation(segment, i);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final void resumeUndispatched(CoroutineDispatcher coroutineDispatcher) {
            this.cont.resumeUndispatched(coroutineDispatcher);
        }

        @Override // kotlin.coroutines.Continuation
        public final void resumeWith(Object obj) {
            this.cont.resumeWith(obj);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final Symbol tryResume(Object obj, Function1 function1) {
            MutexImpl mutexImpl = MutexImpl.this;
            MutexImpl$CancellableContinuationWithOwner$resume$2 mutexImpl$CancellableContinuationWithOwner$resume$2 = new MutexImpl$CancellableContinuationWithOwner$resume$2(mutexImpl, this, 1);
            Symbol tryResume = this.cont.tryResume((Unit) obj, mutexImpl$CancellableContinuationWithOwner$resume$2);
            if (tryResume != null) {
                MutexImpl.owner$FU.set(mutexImpl, this.owner);
            }
            return tryResume;
        }
    }

    public MutexImpl(boolean z) {
        super(z ? 1 : 0);
        this.owner = z ? null : MutexKt.NO_OWNER;
    }

    public final boolean isLocked() {
        return Math.max(SemaphoreImpl._availablePermits$FU.get(this), 0) == 0;
    }

    public final Object lock(Object obj, Continuation continuation) {
        int i;
        char c;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = SemaphoreImpl._availablePermits$FU;
            int i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = this.permits;
            if (i2 > i3) {
                do {
                    i = atomicIntegerFieldUpdater.get(this);
                    if (i > i3) {
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, i3));
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = owner$FU;
                if (i2 <= 0) {
                    if (obj == null) {
                        break;
                    }
                    while (true) {
                        if (!isLocked()) {
                            break;
                        }
                        Object obj2 = atomicReferenceFieldUpdater.get(this);
                        if (obj2 != MutexKt.NO_OWNER) {
                            if (obj2 == obj) {
                                c = 2;
                                break;
                            }
                        }
                    }
                    if (isLocked()) {
                        break;
                    }
                } else if (atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 - 1)) {
                    atomicReferenceFieldUpdater.set(this, obj);
                    c = 0;
                    break;
                }
            }
        }
        c = 1;
        Unit unit = Unit.INSTANCE;
        if (c == 0) {
            return unit;
        }
        if (c != 1) {
            if (c != 2) {
                throw new IllegalStateException("unexpected".toString());
            }
            throw new IllegalStateException(("This mutex is already locked by the specified owner: " + obj).toString());
        }
        CancellableContinuationImpl orCreateCancellableContinuation = ResultKt.getOrCreateCancellableContinuation(ResultKt.intercepted(continuation));
        try {
            acquire(new CancellableContinuationWithOwner(orCreateCancellableContinuation, obj));
            Object result = orCreateCancellableContinuation.getResult();
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (result != coroutineSingletons) {
                result = unit;
            }
            return result == coroutineSingletons ? result : unit;
        } catch (Throwable th) {
            orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw th;
        }
    }

    public final String toString() {
        return "Mutex@" + _BOUNDARY.getHexAddress(this) + "[isLocked=" + isLocked() + ",owner=" + owner$FU.get(this) + ']';
    }

    public final void unlock(Object obj) {
        while (isLocked()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = owner$FU;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            Symbol symbol = MutexKt.NO_OWNER;
            if (obj2 != symbol) {
                if (obj2 == obj || obj == null) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, symbol)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj2) {
                            break;
                        }
                    }
                    release();
                    return;
                }
                throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
            }
        }
        throw new IllegalStateException("This mutex is not locked".toString());
    }
}
