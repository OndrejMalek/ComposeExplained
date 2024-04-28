package kotlinx.coroutines.flow;

import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public abstract class StateFlowKt {
    public static final Symbol NO_VALUE = new Symbol(0, "NO_VALUE");
    public static final Symbol NONE = new Symbol(0, "NONE");
    public static final Symbol PENDING = new Symbol(0, "PENDING");

    public static final void access$setBufferAt(Object[] objArr, long j, Object obj) {
        objArr[((int) j) & (objArr.length - 1)] = obj;
    }
}
