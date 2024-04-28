package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ThreadContextElement;

/* loaded from: classes.dex */
public final class ThreadState {
    public final ThreadContextElement[] elements;
    public final Object[] values;

    public ThreadState(CoroutineContext coroutineContext, int i) {
        this.values = new Object[i];
        this.elements = new ThreadContextElement[i];
    }
}
