package kotlinx.coroutines.sync;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes.dex */
public final /* synthetic */ class SemaphoreImpl$addAcquireToQueue$createNewSegment$1 extends FunctionReferenceImpl implements Function2 {
    public static final SemaphoreImpl$addAcquireToQueue$createNewSegment$1 INSTANCE = new FunctionReferenceImpl(2, SemaphoreKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", 1);

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        int i = SemaphoreKt.MAX_SPIN_CYCLES;
        return new SemaphoreSegment(((Number) obj).longValue(), (SemaphoreSegment) obj2, 0);
    }
}
