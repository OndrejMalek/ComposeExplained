package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {
    void handleException(CoroutineContext coroutineContext, Throwable th);
}
