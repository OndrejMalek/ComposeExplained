package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public interface MonotonicFrameClock extends CoroutineContext.Element {
    public static final /* synthetic */ int $r8$clinit = 0;

    @Override // kotlin.coroutines.CoroutineContext.Element
    default CoroutineContext.Key getKey() {
        return Composer.Companion.$$INSTANCE;
    }

    Object withFrameNanos(Function1 function1, Continuation continuation);
}
