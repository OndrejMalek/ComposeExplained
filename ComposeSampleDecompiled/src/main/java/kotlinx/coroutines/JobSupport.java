package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import androidx.startup.StartupException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;

/* loaded from: classes.dex */
public class JobSupport implements Job, ChildJob, ParentJob {
    private volatile Object _parentHandle;
    private volatile Object _state;
    public static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
    public static final AtomicReferenceFieldUpdater _parentHandle$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_parentHandle");

    /* loaded from: classes.dex */
    public final class AwaitContinuation extends CancellableContinuationImpl {
        public final JobSupport job;

        public AwaitContinuation(Continuation continuation, CompletableDeferredImpl completableDeferredImpl) {
            super(1, continuation);
            this.job = completableDeferredImpl;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public final Throwable getContinuationCancellationCause(JobSupport jobSupport) {
            Throwable rootCause;
            Object state$kotlinx_coroutines_core = this.job.getState$kotlinx_coroutines_core();
            return (!(state$kotlinx_coroutines_core instanceof Finishing) || (rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause()) == null) ? state$kotlinx_coroutines_core instanceof CompletedExceptionally ? ((CompletedExceptionally) state$kotlinx_coroutines_core).cause : jobSupport.getCancellationException() : rootCause;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public final String nameString() {
            return "AwaitContinuation";
        }
    }

    /* loaded from: classes.dex */
    public final class Finishing implements Incomplete {
        private volatile Object _exceptionsHolder;
        private volatile int _isCompleting = 0;
        private volatile Object _rootCause;
        public final NodeList list;
        public static final AtomicIntegerFieldUpdater _isCompleting$FU = AtomicIntegerFieldUpdater.newUpdater(Finishing.class, "_isCompleting");
        public static final AtomicReferenceFieldUpdater _rootCause$FU = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_rootCause");
        public static final AtomicReferenceFieldUpdater _exceptionsHolder$FU = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_exceptionsHolder");

        public Finishing(NodeList nodeList, Throwable th) {
            this.list = nodeList;
            this._rootCause = th;
        }

        public final void addExceptionLocked(Throwable th) {
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                _rootCause$FU.set(this, th);
                return;
            }
            if (th == rootCause) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _exceptionsHolder$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                atomicReferenceFieldUpdater.set(this, th);
                return;
            }
            if (!(obj instanceof Throwable)) {
                if (obj instanceof ArrayList) {
                    ((ArrayList) obj).add(th);
                    return;
                } else {
                    throw new IllegalStateException(("State is " + obj).toString());
                }
            }
            if (th == obj) {
                return;
            }
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(obj);
            arrayList.add(th);
            atomicReferenceFieldUpdater.set(this, arrayList);
        }

        @Override // kotlinx.coroutines.Incomplete
        public final NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable) _rootCause$FU.get(this);
        }

        @Override // kotlinx.coroutines.Incomplete
        public final boolean isActive() {
            return getRootCause() == null;
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        public final boolean isCompleting() {
            return _isCompleting$FU.get(this) != 0;
        }

        public final ArrayList sealLocked(Throwable th) {
            ArrayList arrayList;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _exceptionsHolder$FU;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                arrayList = new ArrayList(4);
            } else if (obj instanceof Throwable) {
                ArrayList arrayList2 = new ArrayList(4);
                arrayList2.add(obj);
                arrayList = arrayList2;
            } else {
                if (!(obj instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + obj).toString());
                }
                arrayList = (ArrayList) obj;
            }
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                arrayList.add(0, rootCause);
            }
            if (th != null && !ResultKt.areEqual(th, rootCause)) {
                arrayList.add(th);
            }
            atomicReferenceFieldUpdater.set(this, JobKt.SEALED);
            return arrayList;
        }

        public final String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + _exceptionsHolder$FU.get(this) + ", list=" + this.list + ']';
        }
    }

    public JobSupport(boolean z) {
        this._state = z ? JobKt.EMPTY_ACTIVE : JobKt.EMPTY_NEW;
    }

    public static ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            LockFreeLinkedListNode correctPrev = lockFreeLinkedListNode.correctPrev();
            if (correctPrev == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._prev$FU;
                Object obj = atomicReferenceFieldUpdater.get(lockFreeLinkedListNode);
                while (true) {
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
                    if (!lockFreeLinkedListNode.isRemoved()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(lockFreeLinkedListNode);
                }
            } else {
                lockFreeLinkedListNode = correctPrev;
            }
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    public static String stateString(Object obj) {
        if (!(obj instanceof Finishing)) {
            return obj instanceof Incomplete ? ((Incomplete) obj).isActive() ? "Active" : "New" : obj instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        }
        Finishing finishing = (Finishing) obj;
        return finishing.isCancelling() ? "Cancelling" : finishing.isCompleting() ? "Completing" : "Active";
    }

    public final boolean addLastAtomic(Object obj, NodeList nodeList, JobNode jobNode) {
        char c;
        JobSupport$addLastAtomic$$inlined$addLastIf$1 jobSupport$addLastAtomic$$inlined$addLastIf$1 = new JobSupport$addLastAtomic$$inlined$addLastIf$1(jobNode, this, obj);
        do {
            LockFreeLinkedListNode correctPrev = nodeList.correctPrev();
            if (correctPrev == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._prev$FU;
                Object obj2 = atomicReferenceFieldUpdater.get(nodeList);
                while (true) {
                    correctPrev = (LockFreeLinkedListNode) obj2;
                    if (!correctPrev.isRemoved()) {
                        break;
                    }
                    obj2 = atomicReferenceFieldUpdater.get(correctPrev);
                }
            }
            LockFreeLinkedListNode._prev$FU.lazySet(jobNode, correctPrev);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = LockFreeLinkedListNode._next$FU;
            atomicReferenceFieldUpdater2.lazySet(jobNode, nodeList);
            jobSupport$addLastAtomic$$inlined$addLastIf$1.oldNext = nodeList;
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(correctPrev, nodeList, jobSupport$addLastAtomic$$inlined$addLastIf$1)) {
                    c = jobSupport$addLastAtomic$$inlined$addLastIf$1.perform(correctPrev) == null ? (char) 1 : (char) 2;
                } else if (atomicReferenceFieldUpdater2.get(correctPrev) != nodeList) {
                    c = 0;
                    break;
                }
            }
            if (c == 1) {
                return true;
            }
        } while (c != 2);
        return false;
    }

    public void afterCompletion(Object obj) {
    }

    public void afterResume(Object obj) {
        afterCompletion(obj);
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0032, code lost:
    
        r0 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r0 != kotlinx.coroutines.JobKt.COMPLETING_WAITING_CHILDREN) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0020, code lost:
    
        r0 = tryMakeCompleting(r0, new kotlinx.coroutines.CompletedExceptionally(createCauseException(r10), false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r0 == kotlinx.coroutines.JobKt.COMPLETING_RETRY) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
    
        if (r0 != kotlinx.coroutines.JobKt.COMPLETING_ALREADY) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        r0 = null;
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
    
        r4 = getState$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
    
        if ((r4 instanceof kotlinx.coroutines.JobSupport.Finishing) == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008e, code lost:
    
        if ((r4 instanceof kotlinx.coroutines.Incomplete) == false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0090, code lost:
    
        if (r1 != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0092, code lost:
    
        r1 = createCauseException(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0096, code lost:
    
        r5 = (kotlinx.coroutines.Incomplete) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0008, code lost:
    
        if (getOnCancelComplete$kotlinx_coroutines_core() != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009d, code lost:
    
        if (r5.isActive() == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c1, code lost:
    
        r5 = tryMakeCompleting(r4, new kotlinx.coroutines.CompletedExceptionally(r1, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00cc, code lost:
    
        if (r5 == kotlinx.coroutines.JobKt.COMPLETING_ALREADY) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d0, code lost:
    
        if (r5 == kotlinx.coroutines.JobKt.COMPLETING_RETRY) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d2, code lost:
    
        r0 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = getState$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00eb, code lost:
    
        throw new java.lang.IllegalStateException(("Cannot happen in " + r4).toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009f, code lost:
    
        r6 = getOrPromoteCancellingList(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a3, code lost:
    
        if (r6 != null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a6, code lost:
    
        r7 = new kotlinx.coroutines.JobSupport.Finishing(r6, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ab, code lost:
    
        r4 = kotlinx.coroutines.JobSupport._state$FU;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b1, code lost:
    
        if (r4.compareAndSet(r9, r5, r7) == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
    
        if ((r0 instanceof kotlinx.coroutines.Incomplete) == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00bd, code lost:
    
        if (r4.get(r9) == r5) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b3, code lost:
    
        notifyCancelling(r6, r1);
        r10 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0058, code lost:
    
        r0 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ec, code lost:
    
        r10 = kotlinx.coroutines.JobKt.TOO_LATE_TO_CANCEL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0047, code lost:
    
        monitor-enter(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0053, code lost:
    
        if (kotlinx.coroutines.JobSupport.Finishing._exceptionsHolder$FU.get((kotlinx.coroutines.JobSupport.Finishing) r4) != kotlinx.coroutines.JobKt.SEALED) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0055, code lost:
    
        r10 = kotlinx.coroutines.JobKt.TOO_LATE_TO_CANCEL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0057, code lost:
    
        monitor-exit(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x005b, code lost:
    
        r5 = ((kotlinx.coroutines.JobSupport.Finishing) r4).isCancelling();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0062, code lost:
    
        if (r1 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0064, code lost:
    
        r1 = createCauseException(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
    
        if ((r0 instanceof kotlinx.coroutines.JobSupport.Finishing) == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x006b, code lost:
    
        ((kotlinx.coroutines.JobSupport.Finishing) r4).addExceptionLocked(r1);
        r10 = ((kotlinx.coroutines.JobSupport.Finishing) r4).getRootCause();
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x007a, code lost:
    
        if ((!r5) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x007c, code lost:
    
        r0 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x007d, code lost:
    
        monitor-exit(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x007e, code lost:
    
        if (r0 == null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0080, code lost:
    
        notifyCancelling(((kotlinx.coroutines.JobSupport.Finishing) r4).list, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0087, code lost:
    
        r10 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0069, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x008b, code lost:
    
        throw r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00f2, code lost:
    
        if (r0 != kotlinx.coroutines.JobKt.COMPLETING_ALREADY) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0104, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00f8, code lost:
    
        if (r0 != kotlinx.coroutines.JobKt.COMPLETING_WAITING_CHILDREN) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00fd, code lost:
    
        if (r0 != kotlinx.coroutines.JobKt.TOO_LATE_TO_CANCEL) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0100, code lost:
    
        afterCompletion(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        if (((kotlinx.coroutines.JobSupport.Finishing) r0).isCompleting() == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean cancelImpl$kotlinx_coroutines_core(java.lang.Object r10) {
        /*
            r9 = this;
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            boolean r1 = r9.getOnCancelComplete$kotlinx_coroutines_core()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L39
        La:
            java.lang.Object r0 = r9.getState$kotlinx_coroutines_core()
            boolean r1 = r0 instanceof kotlinx.coroutines.Incomplete
            if (r1 == 0) goto L32
            boolean r1 = r0 instanceof kotlinx.coroutines.JobSupport.Finishing
            if (r1 == 0) goto L20
            r1 = r0
            kotlinx.coroutines.JobSupport$Finishing r1 = (kotlinx.coroutines.JobSupport.Finishing) r1
            boolean r1 = r1.isCompleting()
            if (r1 == 0) goto L20
            goto L32
        L20:
            kotlinx.coroutines.CompletedExceptionally r1 = new kotlinx.coroutines.CompletedExceptionally
            java.lang.Throwable r4 = r9.createCauseException(r10)
            r1.<init>(r4, r2)
            java.lang.Object r0 = r9.tryMakeCompleting(r0, r1)
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.JobKt.COMPLETING_RETRY
            if (r0 == r1) goto La
            goto L34
        L32:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
        L34:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.JobKt.COMPLETING_WAITING_CHILDREN
            if (r0 != r1) goto L39
            return r3
        L39:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            if (r0 != r1) goto Lf0
            r0 = 0
            r1 = r0
        L3f:
            java.lang.Object r4 = r9.getState$kotlinx_coroutines_core()
            boolean r5 = r4 instanceof kotlinx.coroutines.JobSupport.Finishing
            if (r5 == 0) goto L8c
            monitor-enter(r4)
            r5 = r4
            kotlinx.coroutines.JobSupport$Finishing r5 = (kotlinx.coroutines.JobSupport.Finishing) r5     // Catch: java.lang.Throwable -> L69
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = kotlinx.coroutines.JobSupport.Finishing._exceptionsHolder$FU     // Catch: java.lang.Throwable -> L69
            java.lang.Object r5 = r6.get(r5)     // Catch: java.lang.Throwable -> L69
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.JobKt.SEALED     // Catch: java.lang.Throwable -> L69
            if (r5 != r6) goto L5b
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.TOO_LATE_TO_CANCEL     // Catch: java.lang.Throwable -> L69
            monitor-exit(r4)
        L58:
            r0 = r10
            goto Lf0
        L5b:
            r5 = r4
            kotlinx.coroutines.JobSupport$Finishing r5 = (kotlinx.coroutines.JobSupport.Finishing) r5     // Catch: java.lang.Throwable -> L69
            boolean r5 = r5.isCancelling()     // Catch: java.lang.Throwable -> L69
            if (r1 != 0) goto L6b
            java.lang.Throwable r1 = r9.createCauseException(r10)     // Catch: java.lang.Throwable -> L69
            goto L6b
        L69:
            r10 = move-exception
            goto L8a
        L6b:
            r10 = r4
            kotlinx.coroutines.JobSupport$Finishing r10 = (kotlinx.coroutines.JobSupport.Finishing) r10     // Catch: java.lang.Throwable -> L69
            r10.addExceptionLocked(r1)     // Catch: java.lang.Throwable -> L69
            r10 = r4
            kotlinx.coroutines.JobSupport$Finishing r10 = (kotlinx.coroutines.JobSupport.Finishing) r10     // Catch: java.lang.Throwable -> L69
            java.lang.Throwable r10 = r10.getRootCause()     // Catch: java.lang.Throwable -> L69
            r1 = r5 ^ 1
            if (r1 == 0) goto L7d
            r0 = r10
        L7d:
            monitor-exit(r4)
            if (r0 == 0) goto L87
            kotlinx.coroutines.JobSupport$Finishing r4 = (kotlinx.coroutines.JobSupport.Finishing) r4
            kotlinx.coroutines.NodeList r10 = r4.list
            r9.notifyCancelling(r10, r0)
        L87:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            goto L58
        L8a:
            monitor-exit(r4)
            throw r10
        L8c:
            boolean r5 = r4 instanceof kotlinx.coroutines.Incomplete
            if (r5 == 0) goto Lec
            if (r1 != 0) goto L96
            java.lang.Throwable r1 = r9.createCauseException(r10)
        L96:
            r5 = r4
            kotlinx.coroutines.Incomplete r5 = (kotlinx.coroutines.Incomplete) r5
            boolean r6 = r5.isActive()
            if (r6 == 0) goto Lc1
            kotlinx.coroutines.NodeList r6 = r9.getOrPromoteCancellingList(r5)
            if (r6 != 0) goto La6
            goto L3f
        La6:
            kotlinx.coroutines.JobSupport$Finishing r7 = new kotlinx.coroutines.JobSupport$Finishing
            r7.<init>(r6, r1)
        Lab:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.JobSupport._state$FU
            boolean r8 = r4.compareAndSet(r9, r5, r7)
            if (r8 == 0) goto Lb9
            r9.notifyCancelling(r6, r1)
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            goto L58
        Lb9:
            java.lang.Object r4 = r4.get(r9)
            if (r4 == r5) goto Lab
            goto L3f
        Lc1:
            kotlinx.coroutines.CompletedExceptionally r5 = new kotlinx.coroutines.CompletedExceptionally
            r5.<init>(r1, r2)
            java.lang.Object r5 = r9.tryMakeCompleting(r4, r5)
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            if (r5 == r6) goto Ld4
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.JobKt.COMPLETING_RETRY
            if (r5 == r4) goto L3f
            r0 = r5
            goto Lf0
        Ld4:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot happen in "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        Lec:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.TOO_LATE_TO_CANCEL
            goto L58
        Lf0:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            if (r0 != r10) goto Lf6
        Lf4:
            r2 = r3
            goto L104
        Lf6:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.COMPLETING_WAITING_CHILDREN
            if (r0 != r10) goto Lfb
            goto Lf4
        Lfb:
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.JobKt.TOO_LATE_TO_CANCEL
            if (r0 != r10) goto L100
            goto L104
        L100:
            r9.afterCompletion(r0)
            goto Lf4
        L104:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.cancelImpl$kotlinx_coroutines_core(java.lang.Object):boolean");
    }

    public void cancelInternal(CancellationException cancellationException) {
        cancelImpl$kotlinx_coroutines_core(cancellationException);
    }

    public final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle childHandle = (ChildHandle) _parentHandle$FU.get(this);
        return (childHandle == null || childHandle == NonDisposableHandle.INSTANCE) ? z : childHandle.childCancelled(th) || z;
    }

    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(th) && getHandlesException$kotlinx_coroutines_core();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x008c */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: androidx.startup.StartupException */
    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: kotlinx.coroutines.JobSupport */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.RuntimeException, androidx.startup.StartupException] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Throwable, androidx.startup.StartupException] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.RuntimeException] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final void completeStateFinalization(Incomplete incomplete, Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _parentHandle$FU;
        ChildHandle childHandle = (ChildHandle) atomicReferenceFieldUpdater.get(this);
        if (childHandle != null) {
            childHandle.dispose();
            atomicReferenceFieldUpdater.set(this, NonDisposableHandle.INSTANCE);
        }
        StartupException startupException = 0;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$kotlinx_coroutines_core(new RuntimeException("Exception in completion handler " + incomplete + " for " + this, th2));
                return;
            }
        }
        NodeList list = incomplete.getList();
        if (list != null) {
            Object next = list.getNext();
            ResultKt.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            while (!ResultKt.areEqual(lockFreeLinkedListNode, list)) {
                if (lockFreeLinkedListNode instanceof JobNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (startupException != 0) {
                            ResultKt.addSuppressed(startupException, th3);
                        } else {
                            startupException = new RuntimeException("Exception in completion handler " + jobNode + " for " + this, th3);
                        }
                    }
                }
                lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
                startupException = startupException;
            }
            if (startupException != 0) {
                handleOnCompletionException$kotlinx_coroutines_core(startupException);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Throwable] */
    public final Throwable createCauseException(Object obj) {
        CancellationException cancellationException;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        JobSupport jobSupport = (JobSupport) ((ParentJob) obj);
        Object state$kotlinx_coroutines_core = jobSupport.getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            cancellationException = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            cancellationException = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else {
            if (state$kotlinx_coroutines_core instanceof Incomplete) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$kotlinx_coroutines_core).toString());
            }
            cancellationException = null;
        }
        CancellationException cancellationException2 = cancellationException instanceof CancellationException ? cancellationException : null;
        if (cancellationException2 == null) {
            cancellationException2 = new JobCancellationException("Parent job is ".concat(stateString(state$kotlinx_coroutines_core)), cancellationException, jobSupport);
        }
        return cancellationException2;
    }

    public final Object finalizeFinishingState(Finishing finishing, Object obj) {
        Throwable finalRootCause;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.cause : null;
        synchronized (finishing) {
            finishing.isCancelling();
            ArrayList<Throwable> sealLocked = finishing.sealLocked(th);
            finalRootCause = getFinalRootCause(finishing, sealLocked);
            if (finalRootCause != null && sealLocked.size() > 1) {
                Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(sealLocked.size()));
                for (Throwable th2 : sealLocked) {
                    if (th2 != finalRootCause && th2 != finalRootCause && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                        ResultKt.addSuppressed(finalRootCause, th2);
                    }
                }
            }
        }
        if (finalRootCause != null && finalRootCause != th) {
            obj = new CompletedExceptionally(finalRootCause, false);
        }
        if (finalRootCause != null && (cancelParent(finalRootCause) || handleJobException(finalRootCause))) {
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            CompletedExceptionally._handled$FU.compareAndSet((CompletedExceptionally) obj, 0, 1);
        }
        onCompletionInternal(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        Object incompleteStateBox = obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, finishing, incompleteStateBox) && atomicReferenceFieldUpdater.get(this) == finishing) {
        }
        completeStateFinalization(finishing, obj);
        return obj;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        return ResultKt.get(this, key);
    }

    public final CancellationException getCancellationException() {
        CancellationException cancellationException;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof Finishing)) {
            if (state$kotlinx_coroutines_core instanceof Incomplete) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                return new JobCancellationException(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new JobCancellationException(cancellationExceptionMessage(), th, this) : cancellationException;
        }
        Throwable rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
        if (rootCause == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String concat = getClass().getSimpleName().concat(" is cancelling");
        cancellationException = rootCause instanceof CancellationException ? (CancellationException) rootCause : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (concat == null) {
            concat = cancellationExceptionMessage();
        }
        return new JobCancellationException(concat, rootCause, this);
    }

    public final Throwable getFinalRootCause(Finishing finishing, ArrayList arrayList) {
        Object obj;
        Object obj2 = null;
        if (arrayList.isEmpty()) {
            if (finishing.isCancelling()) {
                return new JobCancellationException(cancellationExceptionMessage(), null, this);
            }
            return null;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!(((Throwable) obj) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) arrayList.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return Job.Key.$$INSTANCE;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return this instanceof CompletableDeferredImpl;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [kotlinx.coroutines.NodeList, kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    public final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list != null) {
            return list;
        }
        if (incomplete instanceof Empty) {
            return new LockFreeLinkedListNode();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + incomplete).toString());
    }

    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = _state$FU.get(this);
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    public boolean handleJobException(Throwable th) {
        return false;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(StartupException startupException) {
        throw startupException;
    }

    public final void initParentJob(Job job) {
        int startInternal;
        NonDisposableHandle nonDisposableHandle = NonDisposableHandle.INSTANCE;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _parentHandle$FU;
        if (job == null) {
            atomicReferenceFieldUpdater.set(this, nonDisposableHandle);
            return;
        }
        JobSupport jobSupport = (JobSupport) job;
        do {
            startInternal = jobSupport.startInternal(jobSupport.getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                break;
            }
        } while (startInternal != 1);
        ChildHandle childHandle = (ChildHandle) ResultKt.invokeOnCompletion$default(jobSupport, true, new ChildHandleNode(this), 2);
        atomicReferenceFieldUpdater.set(this, childHandle);
        if (!(getState$kotlinx_coroutines_core() instanceof Incomplete)) {
            childHandle.dispose();
            atomicReferenceFieldUpdater.set(this, nonDisposableHandle);
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.NodeList, kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 function1) {
        JobNode jobNode;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Throwable th;
        if (z) {
            jobNode = function1 instanceof JobCancellingNode ? (JobCancellingNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            jobNode = function1 instanceof JobNode ? (JobNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCompletion(0, function1);
            }
        }
        jobNode.job = this;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (empty.isActive) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _state$FU;
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, state$kotlinx_coroutines_core, jobNode)) {
                        if (atomicReferenceFieldUpdater2.get(this) != state$kotlinx_coroutines_core) {
                            break;
                        }
                    }
                    return jobNode;
                }
                ?? lockFreeLinkedListNode = new LockFreeLinkedListNode();
                InactiveNodeList inactiveNodeList = empty.isActive ? lockFreeLinkedListNode : new InactiveNodeList(lockFreeLinkedListNode);
                do {
                    atomicReferenceFieldUpdater = _state$FU;
                    if (atomicReferenceFieldUpdater.compareAndSet(this, empty, inactiveNodeList)) {
                        break;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == empty);
            } else {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    if (z2) {
                        CompletedExceptionally completedExceptionally = state$kotlinx_coroutines_core instanceof CompletedExceptionally ? (CompletedExceptionally) state$kotlinx_coroutines_core : null;
                        function1.invoke(completedExceptionally != null ? completedExceptionally.cause : null);
                    }
                    return NonDisposableHandle.INSTANCE;
                }
                NodeList list = ((Incomplete) state$kotlinx_coroutines_core).getList();
                if (list == null) {
                    ResultKt.checkNotNull(state$kotlinx_coroutines_core, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                } else {
                    DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                    if (z && (state$kotlinx_coroutines_core instanceof Finishing)) {
                        synchronized (state$kotlinx_coroutines_core) {
                            try {
                                th = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
                                if (th != null) {
                                    if ((function1 instanceof ChildHandleNode) && !((Finishing) state$kotlinx_coroutines_core).isCompleting()) {
                                    }
                                }
                                if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                                    if (th == null) {
                                        return jobNode;
                                    }
                                    disposableHandle = jobNode;
                                }
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    } else {
                        th = null;
                    }
                    if (th != null) {
                        if (z2) {
                            function1.invoke(th);
                        }
                        return disposableHandle;
                    }
                    if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                        return jobNode;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).isActive();
    }

    public boolean isScopedCoroutine() {
        return this instanceof BlockingCoroutine;
    }

    public final Object join(Continuation continuation) {
        Object state$kotlinx_coroutines_core;
        Unit unit;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            boolean z = state$kotlinx_coroutines_core instanceof Incomplete;
            unit = Unit.INSTANCE;
            if (!z) {
                JobKt.ensureActive(continuation.getContext());
                return unit;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        cancellableContinuationImpl.invokeOnCancellation(new InvokeOnCancel(1, invokeOnCompletion(false, true, new InvokeOnCompletion(3, cancellableContinuationImpl))));
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = unit;
        }
        return result == coroutineSingletons ? result : unit;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (tryMakeCompleting == JobKt.COMPLETING_ALREADY) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
                throw new IllegalStateException(str, completedExceptionally != null ? completedExceptionally.cause : null);
            }
        } while (tryMakeCompleting == JobKt.COMPLETING_RETRY);
        return tryMakeCompleting;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        return ResultKt.minusKey(this, key);
    }

    public String nameString$kotlinx_coroutines_core() {
        return getClass().getSimpleName();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:18:0x003f */
    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: androidx.startup.StartupException */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: kotlinx.coroutines.JobSupport */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Throwable, androidx.startup.StartupException] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.RuntimeException] */
    /* JADX WARN: Type inference failed for: r1v5 */
    public final void notifyCancelling(NodeList nodeList, Throwable th) {
        Object next = nodeList.getNext();
        ResultKt.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
        StartupException startupException = 0;
        while (!ResultKt.areEqual(lockFreeLinkedListNode, nodeList)) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (startupException != 0) {
                        ResultKt.addSuppressed(startupException, th2);
                    } else {
                        startupException = new RuntimeException("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            startupException = startupException;
        }
        if (startupException != 0) {
            handleOnCompletionException$kotlinx_coroutines_core(startupException);
        }
        cancelParent(th);
    }

    public void onCompletionInternal(Object obj) {
    }

    public void onStart() {
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return ResultKt.plus((CoroutineContext.Element) this, coroutineContext);
    }

    public final void promoteSingleToNodeList(JobNode jobNode) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        LockFreeLinkedListNode lockFreeLinkedListNode = new LockFreeLinkedListNode();
        jobNode.getClass();
        LockFreeLinkedListNode._prev$FU.lazySet(lockFreeLinkedListNode, jobNode);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = LockFreeLinkedListNode._next$FU;
        atomicReferenceFieldUpdater2.lazySet(lockFreeLinkedListNode, jobNode);
        loop0: while (true) {
            if (jobNode.getNext() != jobNode) {
                break;
            }
            while (!atomicReferenceFieldUpdater2.compareAndSet(jobNode, jobNode, lockFreeLinkedListNode)) {
                if (atomicReferenceFieldUpdater2.get(jobNode) != jobNode) {
                    break;
                }
            }
            lockFreeLinkedListNode.finishAdd(jobNode);
        }
        LockFreeLinkedListNode nextNode = jobNode.getNextNode();
        do {
            atomicReferenceFieldUpdater = _state$FU;
            if (atomicReferenceFieldUpdater.compareAndSet(this, jobNode, nextNode)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == jobNode);
    }

    public final int startInternal(Object obj) {
        boolean z = obj instanceof Empty;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        if (z) {
            if (((Empty) obj).isActive) {
                return 0;
            }
            Empty empty = JobKt.EMPTY_ACTIVE;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, empty)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            onStart();
            return 1;
        }
        if (!(obj instanceof InactiveNodeList)) {
            return 0;
        }
        NodeList nodeList = ((InactiveNodeList) obj).list;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, nodeList)) {
            if (atomicReferenceFieldUpdater.get(this) != obj) {
                return -1;
            }
        }
        onStart();
        return 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}');
        sb.append('@');
        sb.append(_BOUNDARY.getHexAddress(this));
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00c1, code lost:
    
        if (r2 != null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00d1, code lost:
    
        if (kotlin.ResultKt.invokeOnCompletion$default(r2.childJob, false, new kotlinx.coroutines.JobSupport.ChildCompletion(r6, r1, r2, r8), 1) == kotlinx.coroutines.NonDisposableHandle.INSTANCE) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d6, code lost:
    
        r2 = nextChild(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00da, code lost:
    
        if (r2 != null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:?, code lost:
    
        return kotlinx.coroutines.JobKt.COMPLETING_WAITING_CHILDREN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e0, code lost:
    
        return finalizeFinishingState(r1, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryMakeCompleting(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.Incomplete
            if (r0 != 0) goto L7
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY
            return r7
        L7:
            boolean r0 = r7 instanceof kotlinx.coroutines.Empty
            if (r0 != 0) goto Lf
            boolean r0 = r7 instanceof kotlinx.coroutines.JobNode
            if (r0 == 0) goto L41
        Lf:
            boolean r0 = r7 instanceof kotlinx.coroutines.ChildHandleNode
            if (r0 != 0) goto L41
            boolean r0 = r8 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r0 != 0) goto L41
            r0 = r7
            kotlinx.coroutines.Incomplete r0 = (kotlinx.coroutines.Incomplete) r0
            boolean r7 = r8 instanceof kotlinx.coroutines.Incomplete
            if (r7 == 0) goto L28
            kotlinx.coroutines.IncompleteStateBox r7 = new kotlinx.coroutines.IncompleteStateBox
            r1 = r8
            kotlinx.coroutines.Incomplete r1 = (kotlinx.coroutines.Incomplete) r1
            r7.<init>(r1)
            r1 = r7
            goto L29
        L28:
            r1 = r8
        L29:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = kotlinx.coroutines.JobSupport._state$FU
            boolean r2 = r7.compareAndSet(r6, r0, r1)
            if (r2 == 0) goto L38
            r6.onCompletionInternal(r8)
            r6.completeStateFinalization(r0, r8)
            return r8
        L38:
            java.lang.Object r7 = r7.get(r6)
            if (r7 == r0) goto L29
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobKt.COMPLETING_RETRY
            return r7
        L41:
            kotlinx.coroutines.Incomplete r7 = (kotlinx.coroutines.Incomplete) r7
            kotlinx.coroutines.NodeList r0 = r6.getOrPromoteCancellingList(r7)
            if (r0 != 0) goto L4d
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobKt.COMPLETING_RETRY
            goto Le0
        L4d:
            boolean r1 = r7 instanceof kotlinx.coroutines.JobSupport.Finishing
            r2 = 0
            if (r1 == 0) goto L56
            r1 = r7
            kotlinx.coroutines.JobSupport$Finishing r1 = (kotlinx.coroutines.JobSupport.Finishing) r1
            goto L57
        L56:
            r1 = r2
        L57:
            if (r1 != 0) goto L5e
            kotlinx.coroutines.JobSupport$Finishing r1 = new kotlinx.coroutines.JobSupport$Finishing
            r1.<init>(r0, r2)
        L5e:
            monitor-enter(r1)
            boolean r3 = r1.isCompleting()     // Catch: java.lang.Throwable -> L85
            if (r3 == 0) goto L6a
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobKt.COMPLETING_ALREADY     // Catch: java.lang.Throwable -> L85
            monitor-exit(r1)
            goto Le0
        L6a:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = kotlinx.coroutines.JobSupport.Finishing._isCompleting$FU     // Catch: java.lang.Throwable -> L85
            r4 = 1
            r3.set(r1, r4)     // Catch: java.lang.Throwable -> L85
            if (r1 == r7) goto L87
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = kotlinx.coroutines.JobSupport._state$FU     // Catch: java.lang.Throwable -> L85
        L74:
            boolean r5 = r3.compareAndSet(r6, r7, r1)     // Catch: java.lang.Throwable -> L85
            if (r5 == 0) goto L7b
            goto L87
        L7b:
            java.lang.Object r5 = r3.get(r6)     // Catch: java.lang.Throwable -> L85
            if (r5 == r7) goto L74
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobKt.COMPLETING_RETRY     // Catch: java.lang.Throwable -> L85
            monitor-exit(r1)
            goto Le0
        L85:
            r7 = move-exception
            goto Le1
        L87:
            boolean r3 = r1.isCancelling()     // Catch: java.lang.Throwable -> L85
            boolean r5 = r8 instanceof kotlinx.coroutines.CompletedExceptionally     // Catch: java.lang.Throwable -> L85
            if (r5 == 0) goto L93
            r5 = r8
            kotlinx.coroutines.CompletedExceptionally r5 = (kotlinx.coroutines.CompletedExceptionally) r5     // Catch: java.lang.Throwable -> L85
            goto L94
        L93:
            r5 = r2
        L94:
            if (r5 == 0) goto L9b
            java.lang.Throwable r5 = r5.cause     // Catch: java.lang.Throwable -> L85
            r1.addExceptionLocked(r5)     // Catch: java.lang.Throwable -> L85
        L9b:
            java.lang.Throwable r5 = r1.getRootCause()     // Catch: java.lang.Throwable -> L85
            r3 = r3 ^ r4
            if (r3 == 0) goto La3
            goto La4
        La3:
            r5 = r2
        La4:
            monitor-exit(r1)
            if (r5 == 0) goto Laa
            r6.notifyCancelling(r0, r5)
        Laa:
            boolean r0 = r7 instanceof kotlinx.coroutines.ChildHandleNode
            if (r0 == 0) goto Lb2
            r0 = r7
            kotlinx.coroutines.ChildHandleNode r0 = (kotlinx.coroutines.ChildHandleNode) r0
            goto Lb3
        Lb2:
            r0 = r2
        Lb3:
            if (r0 != 0) goto Lc0
            kotlinx.coroutines.NodeList r7 = r7.getList()
            if (r7 == 0) goto Lc1
            kotlinx.coroutines.ChildHandleNode r2 = nextChild(r7)
            goto Lc1
        Lc0:
            r2 = r0
        Lc1:
            if (r2 == 0) goto Ldc
        Lc3:
            kotlinx.coroutines.JobSupport$ChildCompletion r7 = new kotlinx.coroutines.JobSupport$ChildCompletion
            r7.<init>(r6, r1, r2, r8)
            kotlinx.coroutines.ChildJob r0 = r2.childJob
            r3 = 0
            kotlinx.coroutines.DisposableHandle r7 = kotlin.ResultKt.invokeOnCompletion$default(r0, r3, r7, r4)
            kotlinx.coroutines.NonDisposableHandle r0 = kotlinx.coroutines.NonDisposableHandle.INSTANCE
            if (r7 == r0) goto Ld6
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.JobKt.COMPLETING_WAITING_CHILDREN
            goto Le0
        Ld6:
            kotlinx.coroutines.ChildHandleNode r2 = nextChild(r2)
            if (r2 != 0) goto Lc3
        Ldc:
            java.lang.Object r7 = r6.finalizeFinishingState(r1, r8)
        Le0:
            return r7
        Le1:
            monitor-exit(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.tryMakeCompleting(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* loaded from: classes.dex */
    public final class ChildCompletion extends JobNode {
        public final ChildHandleNode child;
        public final JobSupport parent;
        public final Object proposedUpdate;
        public final Finishing state;

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        
            r8.afterCompletion(r8.finalizeFinishingState(r1, r2));
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:2:0x000f, code lost:
        
            if (r0 != null) goto L4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:4:0x0020, code lost:
        
            if (kotlin.ResultKt.invokeOnCompletion$default(r0.childJob, false, new kotlinx.coroutines.JobSupport.ChildCompletion(r8, r1, r0, r2), 1) == kotlinx.coroutines.NonDisposableHandle.INSTANCE) goto L7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0023, code lost:
        
            r0 = kotlinx.coroutines.JobSupport.nextChild(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0027, code lost:
        
            if (r0 != null) goto L13;
         */
        @Override // kotlinx.coroutines.JobNode
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(java.lang.Throwable r8) {
            /*
                r7 = this;
                kotlinx.coroutines.JobSupport r8 = r7.parent
                r8.getClass()
                kotlinx.coroutines.ChildHandleNode r0 = r7.child
                kotlinx.coroutines.ChildHandleNode r0 = kotlinx.coroutines.JobSupport.nextChild(r0)
                kotlinx.coroutines.JobSupport$Finishing r1 = r7.state
                java.lang.Object r2 = r7.proposedUpdate
                if (r0 == 0) goto L29
            L11:
                kotlinx.coroutines.JobSupport$ChildCompletion r3 = new kotlinx.coroutines.JobSupport$ChildCompletion
                r3.<init>(r8, r1, r0, r2)
                r4 = 0
                r5 = 1
                kotlinx.coroutines.ChildJob r6 = r0.childJob
                kotlinx.coroutines.DisposableHandle r3 = kotlin.ResultKt.invokeOnCompletion$default(r6, r4, r3, r5)
                kotlinx.coroutines.NonDisposableHandle r4 = kotlinx.coroutines.NonDisposableHandle.INSTANCE
                if (r3 == r4) goto L23
                goto L30
            L23:
                kotlinx.coroutines.ChildHandleNode r0 = kotlinx.coroutines.JobSupport.nextChild(r0)
                if (r0 != 0) goto L11
            L29:
                java.lang.Object r0 = r8.finalizeFinishingState(r1, r2)
                r8.afterCompletion(r0)
            L30:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.ChildCompletion.invoke(java.lang.Throwable):void");
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }
    }
}
