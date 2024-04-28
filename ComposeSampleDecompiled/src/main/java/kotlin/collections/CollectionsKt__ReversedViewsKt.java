package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public static void addAll(Iterable iterable, Collection collection) {
        ResultKt.checkNotNullParameter(collection, "<this>");
        ResultKt.checkNotNullParameter(iterable, "elements");
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }
}
