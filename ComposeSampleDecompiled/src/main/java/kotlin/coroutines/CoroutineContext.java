package kotlin.coroutines;

import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public interface CoroutineContext {

    /* loaded from: classes.dex */
    public interface Element extends CoroutineContext {
        Key getKey();
    }

    /* loaded from: classes.dex */
    public interface Key {
    }

    Object fold(Object obj, Function2 function2);

    Element get(Key key);

    CoroutineContext minusKey(Key key);

    CoroutineContext plus(CoroutineContext coroutineContext);
}
