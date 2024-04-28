package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class ConcurrentLinkedListNode {
    public static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    public static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public ConcurrentLinkedListNode(ConcurrentLinkedListNode concurrentLinkedListNode) {
        this._prev = concurrentLinkedListNode;
    }

    public final void cleanPrev() {
        _prev$FU.lazySet(this, null);
    }

    public final ConcurrentLinkedListNode getNext() {
        Object obj = _next$FU.get(this);
        if (obj == AtomicKt.CLOSED) {
            return null;
        }
        return (ConcurrentLinkedListNode) obj;
    }

    public abstract boolean isRemoved();

    public final void remove() {
        ConcurrentLinkedListNode next;
        if (getNext() == null) {
            return;
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _prev$FU;
            ConcurrentLinkedListNode concurrentLinkedListNode = (ConcurrentLinkedListNode) atomicReferenceFieldUpdater.get(this);
            while (concurrentLinkedListNode != null && concurrentLinkedListNode.isRemoved()) {
                concurrentLinkedListNode = (ConcurrentLinkedListNode) atomicReferenceFieldUpdater.get(concurrentLinkedListNode);
            }
            ConcurrentLinkedListNode next2 = getNext();
            ResultKt.checkNotNull(next2);
            while (next2.isRemoved() && (next = next2.getNext()) != null) {
                next2 = next;
            }
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(next2);
                ConcurrentLinkedListNode concurrentLinkedListNode2 = ((ConcurrentLinkedListNode) obj) == null ? null : concurrentLinkedListNode;
                while (!atomicReferenceFieldUpdater.compareAndSet(next2, obj, concurrentLinkedListNode2)) {
                    if (atomicReferenceFieldUpdater.get(next2) != obj) {
                        break;
                    }
                }
            }
            if (concurrentLinkedListNode != null) {
                _next$FU.set(concurrentLinkedListNode, next2);
            }
            if (!next2.isRemoved() || next2.getNext() == null) {
                if (concurrentLinkedListNode == null || !concurrentLinkedListNode.isRemoved()) {
                    return;
                }
            }
        }
    }
}
