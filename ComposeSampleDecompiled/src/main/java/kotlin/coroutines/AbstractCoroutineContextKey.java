package kotlin.coroutines;

import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineDispatcher;

/* loaded from: classes.dex */
public abstract class AbstractCoroutineContextKey implements CoroutineContext.Key {
    public final Function1 safeCast;
    public final CoroutineContext.Key topmostKey;

    public AbstractCoroutineContextKey(CoroutineContext.Key key, CoroutineDispatcher.Key.AnonymousClass1 anonymousClass1) {
        ResultKt.checkNotNullParameter(key, "baseKey");
        this.safeCast = anonymousClass1;
        this.topmostKey = key instanceof AbstractCoroutineContextKey ? ((AbstractCoroutineContextKey) key).topmostKey : key;
    }
}
