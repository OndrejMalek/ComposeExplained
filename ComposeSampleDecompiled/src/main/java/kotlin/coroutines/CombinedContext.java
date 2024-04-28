package kotlin.coroutines;

import java.io.Serializable;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class CombinedContext implements CoroutineContext, Serializable {
    public final CoroutineContext.Element element;
    public final CoroutineContext left;

    public CombinedContext(CoroutineContext.Element element, CoroutineContext coroutineContext) {
        ResultKt.checkNotNullParameter(coroutineContext, "left");
        ResultKt.checkNotNullParameter(element, "element");
        this.left = coroutineContext;
        this.element = element;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CombinedContext) {
                CombinedContext combinedContext = (CombinedContext) obj;
                combinedContext.getClass();
                int i = 2;
                CombinedContext combinedContext2 = combinedContext;
                int i2 = 2;
                while (true) {
                    CoroutineContext coroutineContext = combinedContext2.left;
                    combinedContext2 = coroutineContext instanceof CombinedContext ? (CombinedContext) coroutineContext : null;
                    if (combinedContext2 == null) {
                        break;
                    }
                    i2++;
                }
                CombinedContext combinedContext3 = this;
                while (true) {
                    CoroutineContext coroutineContext2 = combinedContext3.left;
                    combinedContext3 = coroutineContext2 instanceof CombinedContext ? (CombinedContext) coroutineContext2 : null;
                    if (combinedContext3 == null) {
                        break;
                    }
                    i++;
                }
                if (i2 == i) {
                    CombinedContext combinedContext4 = this;
                    while (true) {
                        CoroutineContext.Element element = combinedContext4.element;
                        if (!ResultKt.areEqual(combinedContext.get(element.getKey()), element)) {
                            break;
                        }
                        CoroutineContext coroutineContext3 = combinedContext4.left;
                        if (coroutineContext3 instanceof CombinedContext) {
                            combinedContext4 = (CombinedContext) coroutineContext3;
                        } else {
                            ResultKt.checkNotNull(coroutineContext3, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                            CoroutineContext.Element element2 = (CoroutineContext.Element) coroutineContext3;
                            if (ResultKt.areEqual(combinedContext.get(element2.getKey()), element2)) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(this.left.fold(obj, function2), this.element);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        ResultKt.checkNotNullParameter(key, "key");
        CombinedContext combinedContext = this;
        while (true) {
            CoroutineContext.Element element = combinedContext.element.get(key);
            if (element != null) {
                return element;
            }
            CoroutineContext coroutineContext = combinedContext.left;
            if (!(coroutineContext instanceof CombinedContext)) {
                return coroutineContext.get(key);
            }
            combinedContext = (CombinedContext) coroutineContext;
        }
    }

    public final int hashCode() {
        return this.element.hashCode() + this.left.hashCode();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        ResultKt.checkNotNullParameter(key, "key");
        CoroutineContext.Element element = this.element;
        CoroutineContext.Element element2 = element.get(key);
        CoroutineContext coroutineContext = this.left;
        if (element2 != null) {
            return coroutineContext;
        }
        CoroutineContext minusKey = coroutineContext.minusKey(key);
        return minusKey == coroutineContext ? this : minusKey == EmptyCoroutineContext.INSTANCE ? element : new CombinedContext(element, minusKey);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return ResultKt.plus(this, coroutineContext);
    }

    public final String toString() {
        return "[" + ((String) fold("", CoroutineContext$plus$1.INSTANCE$1)) + ']';
    }
}
