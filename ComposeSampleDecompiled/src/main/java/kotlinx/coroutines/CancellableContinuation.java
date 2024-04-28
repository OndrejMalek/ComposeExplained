package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public interface CancellableContinuation extends Continuation {
    boolean cancel(Throwable th);

    void completeResume(Object obj);

    void resumeUndispatched(CoroutineDispatcher coroutineDispatcher);

    Symbol tryResume(Object obj, Function1 function1);
}
