package kotlinx.coroutines.sync;

import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public abstract class SemaphoreKt {
    public static final int MAX_SPIN_CYCLES = JobKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12);
    public static final Symbol PERMIT = new Symbol(0, "PERMIT");
    public static final Symbol TAKEN = new Symbol(0, "TAKEN");
    public static final Symbol BROKEN = new Symbol(0, "BROKEN");
    public static final Symbol CANCELLED = new Symbol(0, "CANCELLED");
    public static final int SEGMENT_SIZE = JobKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12);
}
