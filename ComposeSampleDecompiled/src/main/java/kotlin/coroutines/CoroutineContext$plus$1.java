package kotlin.coroutines;

import kotlin.ResultKt;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class CoroutineContext$plus$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public static final CoroutineContext$plus$1 INSTANCE$1 = new CoroutineContext$plus$1(1);
    public static final CoroutineContext$plus$1 INSTANCE = new CoroutineContext$plus$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CoroutineContext$plus$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CombinedContext combinedContext;
        switch (this.$r8$classId) {
            case 0:
                CoroutineContext coroutineContext = (CoroutineContext) obj;
                CoroutineContext.Element element = (CoroutineContext.Element) obj2;
                ResultKt.checkNotNullParameter(coroutineContext, "acc");
                ResultKt.checkNotNullParameter(element, "element");
                CoroutineContext minusKey = coroutineContext.minusKey(element.getKey());
                EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
                if (minusKey == emptyCoroutineContext) {
                    return element;
                }
                ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
                ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) minusKey.get(key);
                if (continuationInterceptor == null) {
                    combinedContext = new CombinedContext(element, minusKey);
                } else {
                    CoroutineContext minusKey2 = minusKey.minusKey(key);
                    if (minusKey2 == emptyCoroutineContext) {
                        return new CombinedContext(continuationInterceptor, element);
                    }
                    combinedContext = new CombinedContext(continuationInterceptor, new CombinedContext(element, minusKey2));
                }
                return combinedContext;
            default:
                String str = (String) obj;
                CoroutineContext.Element element2 = (CoroutineContext.Element) obj2;
                ResultKt.checkNotNullParameter(str, "acc");
                ResultKt.checkNotNullParameter(element2, "element");
                if (str.length() == 0) {
                    return element2.toString();
                }
                return str + ", " + element2;
        }
    }
}
