package kotlinx.coroutines.internal;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.ResultKt;
import kotlinx.coroutines.EventLoopImplBase;

/* loaded from: classes.dex */
public class ThreadSafeHeap {
    public static final AtomicIntegerFieldUpdater _size$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadSafeHeap.class, "_size");
    private volatile int _size;
    public EventLoopImplBase.DelayedTask[] a;

    public final void addImpl(EventLoopImplBase.DelayedTask delayedTask) {
        delayedTask.setHeap((EventLoopImplBase.DelayedTaskQueue) this);
        EventLoopImplBase.DelayedTask[] delayedTaskArr = this.a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = _size$FU;
        if (delayedTaskArr == null) {
            delayedTaskArr = new EventLoopImplBase.DelayedTask[4];
            this.a = delayedTaskArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= delayedTaskArr.length) {
            Object[] copyOf = Arrays.copyOf(delayedTaskArr, atomicIntegerFieldUpdater.get(this) * 2);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            delayedTaskArr = (EventLoopImplBase.DelayedTask[]) copyOf;
            this.a = delayedTaskArr;
        }
        int i = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i + 1);
        delayedTaskArr[i] = delayedTask;
        delayedTask.index = i;
        siftUpFrom(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0060, code lost:
    
        if (r6.compareTo(r7) < 0) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.EventLoopImplBase.DelayedTask removeAtImpl(int r9) {
        /*
            r8 = this;
            kotlinx.coroutines.EventLoopImplBase$DelayedTask[] r0 = r8.a
            kotlin.ResultKt.checkNotNull(r0)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = kotlinx.coroutines.internal.ThreadSafeHeap._size$FU
            int r2 = r1.get(r8)
            r3 = -1
            int r2 = r2 + r3
            r1.set(r8, r2)
            int r2 = r1.get(r8)
            if (r9 >= r2) goto L7a
            int r2 = r1.get(r8)
            r8.swap(r9, r2)
            int r2 = r9 + (-1)
            int r2 = r2 / 2
            if (r9 <= 0) goto L3a
            r4 = r0[r9]
            kotlin.ResultKt.checkNotNull(r4)
            r5 = r0[r2]
            kotlin.ResultKt.checkNotNull(r5)
            int r4 = r4.compareTo(r5)
            if (r4 >= 0) goto L3a
            r8.swap(r9, r2)
            r8.siftUpFrom(r2)
            goto L7a
        L3a:
            int r2 = r9 * 2
            int r4 = r2 + 1
            int r5 = r1.get(r8)
            if (r4 < r5) goto L45
            goto L7a
        L45:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask[] r5 = r8.a
            kotlin.ResultKt.checkNotNull(r5)
            int r2 = r2 + 2
            int r6 = r1.get(r8)
            if (r2 >= r6) goto L63
            r6 = r5[r2]
            kotlin.ResultKt.checkNotNull(r6)
            r7 = r5[r4]
            kotlin.ResultKt.checkNotNull(r7)
            int r6 = r6.compareTo(r7)
            if (r6 >= 0) goto L63
            goto L64
        L63:
            r2 = r4
        L64:
            r4 = r5[r9]
            kotlin.ResultKt.checkNotNull(r4)
            r5 = r5[r2]
            kotlin.ResultKt.checkNotNull(r5)
            int r4 = r4.compareTo(r5)
            if (r4 > 0) goto L75
            goto L7a
        L75:
            r8.swap(r9, r2)
            r9 = r2
            goto L3a
        L7a:
            int r9 = r1.get(r8)
            r9 = r0[r9]
            kotlin.ResultKt.checkNotNull(r9)
            r2 = 0
            r9.setHeap(r2)
            r9.index = r3
            int r1 = r1.get(r8)
            r0[r1] = r2
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ThreadSafeHeap.removeAtImpl(int):kotlinx.coroutines.EventLoopImplBase$DelayedTask");
    }

    public final void siftUpFrom(int i) {
        while (i > 0) {
            EventLoopImplBase.DelayedTask[] delayedTaskArr = this.a;
            ResultKt.checkNotNull(delayedTaskArr);
            int i2 = (i - 1) / 2;
            EventLoopImplBase.DelayedTask delayedTask = delayedTaskArr[i2];
            ResultKt.checkNotNull(delayedTask);
            EventLoopImplBase.DelayedTask delayedTask2 = delayedTaskArr[i];
            ResultKt.checkNotNull(delayedTask2);
            if (delayedTask.compareTo(delayedTask2) <= 0) {
                return;
            }
            swap(i, i2);
            i = i2;
        }
    }

    public final void swap(int i, int i2) {
        EventLoopImplBase.DelayedTask[] delayedTaskArr = this.a;
        ResultKt.checkNotNull(delayedTaskArr);
        EventLoopImplBase.DelayedTask delayedTask = delayedTaskArr[i2];
        ResultKt.checkNotNull(delayedTask);
        EventLoopImplBase.DelayedTask delayedTask2 = delayedTaskArr[i];
        ResultKt.checkNotNull(delayedTask2);
        delayedTaskArr[i] = delayedTask;
        delayedTaskArr[i2] = delayedTask2;
        delayedTask.index = i;
        delayedTask2.index = i2;
    }
}
