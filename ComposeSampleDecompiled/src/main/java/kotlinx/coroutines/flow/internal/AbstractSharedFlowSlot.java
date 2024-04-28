package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public abstract class AbstractSharedFlowSlot {
    public abstract boolean allocateLocked(AbstractSharedFlow abstractSharedFlow);

    public abstract Continuation[] freeLocked(AbstractSharedFlow abstractSharedFlow);
}
