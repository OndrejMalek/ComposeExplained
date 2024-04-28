package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Removed;

/* loaded from: classes.dex */
public abstract class JobNode extends LockFreeLinkedListNode implements DisposableHandle, Incomplete, Function1 {
    public JobSupport job;

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2;
        JobSupport job = getJob();
        while (true) {
            Object state$kotlinx_coroutines_core = job.getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof JobNode)) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((Incomplete) state$kotlinx_coroutines_core).getList() == null) {
                    return;
                }
                while (true) {
                    Object next = getNext();
                    if (next instanceof Removed) {
                        LockFreeLinkedListNode lockFreeLinkedListNode = ((Removed) next).ref;
                        return;
                    }
                    if (next == this) {
                        return;
                    }
                    ResultKt.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                    LockFreeLinkedListNode lockFreeLinkedListNode2 = (LockFreeLinkedListNode) next;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = LockFreeLinkedListNode._removedRef$FU;
                    Removed removed = (Removed) atomicReferenceFieldUpdater3.get(lockFreeLinkedListNode2);
                    if (removed == null) {
                        removed = new Removed(lockFreeLinkedListNode2);
                        atomicReferenceFieldUpdater3.lazySet(lockFreeLinkedListNode2, removed);
                    }
                    do {
                        atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
                        if (atomicReferenceFieldUpdater.compareAndSet(this, next, removed)) {
                            lockFreeLinkedListNode2.correctPrev();
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == next);
                }
            } else {
                if (state$kotlinx_coroutines_core != this) {
                    return;
                }
                Empty empty = JobKt.EMPTY_ACTIVE;
                do {
                    atomicReferenceFieldUpdater2 = JobSupport._state$FU;
                    if (atomicReferenceFieldUpdater2.compareAndSet(job, state$kotlinx_coroutines_core, empty)) {
                        return;
                    }
                } while (atomicReferenceFieldUpdater2.get(job) == state$kotlinx_coroutines_core);
            }
        }
    }

    public final JobSupport getJob() {
        JobSupport jobSupport = this.job;
        if (jobSupport != null) {
            return jobSupport;
        }
        ResultKt.throwUninitializedPropertyAccessException("job");
        throw null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public final NodeList getList() {
        return null;
    }

    public Job getParent() {
        return getJob();
    }

    public abstract void invoke(Throwable th);

    @Override // kotlinx.coroutines.Incomplete
    public final boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final String toString() {
        return getClass().getSimpleName() + '@' + _BOUNDARY.getHexAddress(this) + "[job@" + _BOUNDARY.getHexAddress(getJob()) + ']';
    }
}
