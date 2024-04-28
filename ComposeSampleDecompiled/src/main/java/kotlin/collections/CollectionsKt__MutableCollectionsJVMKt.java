package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class CollectionsKt__MutableCollectionsJVMKt extends MapsKt___MapsJvmKt {
    public static void sortWith(List list, Comparator comparator) {
        ResultKt.checkNotNullParameter(list, "<this>");
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
