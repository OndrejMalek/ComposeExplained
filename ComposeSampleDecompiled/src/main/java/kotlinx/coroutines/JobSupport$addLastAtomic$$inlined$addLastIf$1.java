package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class JobSupport$addLastAtomic$$inlined$addLastIf$1 extends AtomicOp {
    public final /* synthetic */ Object $expect$inlined;
    public final LockFreeLinkedListNode newNode;
    public LockFreeLinkedListNode oldNext;
    public final /* synthetic */ JobSupport this$0;

    public JobSupport$addLastAtomic$$inlined$addLastIf$1(LockFreeLinkedListNode lockFreeLinkedListNode, JobSupport jobSupport, Object obj) {
        this.this$0 = jobSupport;
        this.$expect$inlined = obj;
        this.newNode = lockFreeLinkedListNode;
    }

    @Override // kotlinx.coroutines.internal.AtomicOp
    public final void complete(Object obj, Object obj2) {
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
        boolean z = obj2 == null;
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.newNode;
        LockFreeLinkedListNode lockFreeLinkedListNode3 = z ? lockFreeLinkedListNode2 : this.oldNext;
        if (lockFreeLinkedListNode3 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(lockFreeLinkedListNode, this, lockFreeLinkedListNode3)) {
                if (atomicReferenceFieldUpdater.get(lockFreeLinkedListNode) != this) {
                    return;
                }
            }
            if (z) {
                LockFreeLinkedListNode lockFreeLinkedListNode4 = this.oldNext;
                ResultKt.checkNotNull(lockFreeLinkedListNode4);
                lockFreeLinkedListNode2.finishAdd(lockFreeLinkedListNode4);
            }
        }
    }

    @Override // kotlinx.coroutines.internal.AtomicOp
    public final Symbol prepare(Object obj) {
        if (this.this$0.getState$kotlinx_coroutines_core() == this.$expect$inlined) {
            return null;
        }
        return AtomicKt.CONDITION_FALSE;
    }
}
