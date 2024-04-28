package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import kotlin.ResultKt;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    public static final Key Key = new Key(0);

    /* loaded from: classes.dex */
    public final class Key extends AbstractCoroutineContextKey {

        /* renamed from: kotlinx.coroutines.CoroutineDispatcher$Key$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends Lambda implements Function1 {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1(0);
            public static final AnonymousClass1 INSTANCE$1 = new AnonymousClass1(1);
            public final /* synthetic */ int $r8$classId;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public /* synthetic */ AnonymousClass1(int i) {
                super(1);
                this.$r8$classId = i;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                switch (this.$r8$classId) {
                    case 0:
                        CoroutineContext.Element element = (CoroutineContext.Element) obj;
                        if (element instanceof CoroutineDispatcher) {
                            return (CoroutineDispatcher) element;
                        }
                        return null;
                    default:
                        CoroutineContext.Element element2 = (CoroutineContext.Element) obj;
                        if (element2 instanceof ExecutorCoroutineDispatcher) {
                            return (ExecutorCoroutineDispatcher) element2;
                        }
                        return null;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Key(int i) {
            super(ContinuationInterceptor.Key.$$INSTANCE, AnonymousClass1.INSTANCE);
            if (i != 1) {
            } else {
                super(CoroutineDispatcher.Key, AnonymousClass1.INSTANCE$1);
            }
        }
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key.$$INSTANCE);
    }

    public abstract void dispatch(CoroutineContext coroutineContext, Runnable runnable);

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        ResultKt.checkNotNullParameter(key, "key");
        if (!(key instanceof AbstractCoroutineContextKey)) {
            if (ContinuationInterceptor.Key.$$INSTANCE == key) {
                return this;
            }
            return null;
        }
        AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
        CoroutineContext.Key key2 = this.key;
        ResultKt.checkNotNullParameter(key2, "key");
        if (key2 != abstractCoroutineContextKey && abstractCoroutineContextKey.topmostKey != key2) {
            return null;
        }
        CoroutineContext.Element element = (CoroutineContext.Element) abstractCoroutineContextKey.safeCast.invoke(this);
        if (element instanceof CoroutineContext.Element) {
            return element;
        }
        return null;
    }

    public boolean isDispatchNeeded() {
        return !(this instanceof Unconfined);
    }

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        ResultKt.checkNotNullParameter(key, "key");
        boolean z = key instanceof AbstractCoroutineContextKey;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        if (z) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            CoroutineContext.Key key2 = this.key;
            ResultKt.checkNotNullParameter(key2, "key");
            if ((key2 == abstractCoroutineContextKey || abstractCoroutineContextKey.topmostKey == key2) && ((CoroutineContext.Element) abstractCoroutineContextKey.safeCast.invoke(this)) != null) {
                return emptyCoroutineContext;
            }
        } else if (ContinuationInterceptor.Key.$$INSTANCE == key) {
            return emptyCoroutineContext;
        }
        return this;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + _BOUNDARY.getHexAddress(this);
    }
}
