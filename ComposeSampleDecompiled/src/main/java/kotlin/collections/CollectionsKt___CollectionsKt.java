package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class CollectionsKt___CollectionsKt extends CollectionsKt__ReversedViewsKt {
    public static Object first(List list) {
        ResultKt.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object firstOrNull(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final void joinTo(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1) {
        ResultKt.checkNotNullParameter(iterable, "<this>");
        ResultKt.checkNotNullParameter(charSequence, "separator");
        ResultKt.checkNotNullParameter(charSequence2, "prefix");
        ResultKt.checkNotNullParameter(charSequence3, "postfix");
        ResultKt.checkNotNullParameter(charSequence4, "truncated");
        sb.append(charSequence2);
        int i2 = 0;
        for (Object obj : iterable) {
            i2++;
            if (i2 > 1) {
                sb.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                sb.append((CharSequence) function1.invoke(obj));
            } else if (obj == null || (obj instanceof CharSequence)) {
                sb.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb.append(((Character) obj).charValue());
            } else {
                sb.append((CharSequence) String.valueOf(obj));
            }
        }
        if (i >= 0 && i2 > i) {
            sb.append(charSequence4);
        }
        sb.append(charSequence3);
    }

    public static String joinToString$default(Iterable iterable, String str, String str2, String str3, Function1 function1, int i) {
        if ((i & 1) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i & 2) != 0 ? "" : str2;
        String str6 = (i & 4) != 0 ? "" : str3;
        if ((i & 32) != 0) {
            function1 = null;
        }
        ResultKt.checkNotNullParameter(iterable, "<this>");
        ResultKt.checkNotNullParameter(str4, "separator");
        ResultKt.checkNotNullParameter(str5, "prefix");
        ResultKt.checkNotNullParameter(str6, "postfix");
        StringBuilder sb = new StringBuilder();
        joinTo(iterable, sb, str4, str5, str6, -1, "...", function1);
        String sb2 = sb.toString();
        ResultKt.checkNotNullExpressionValue(sb2, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb2;
    }

    public static Object lastOrNull(List list) {
        ResultKt.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static ArrayList plus(List list, Collection collection) {
        ArrayList arrayList = new ArrayList(list.size() + collection.size());
        arrayList.addAll(collection);
        arrayList.addAll(list);
        return arrayList;
    }

    public static final void toCollection(Iterable iterable, java.util.AbstractCollection abstractCollection) {
        ResultKt.checkNotNullParameter(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static List toList(Iterable iterable) {
        ArrayList arrayList;
        ResultKt.checkNotNullParameter(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        EmptyList emptyList = EmptyList.INSTANCE;
        if (z) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return emptyList;
            }
            if (size != 1) {
                return toMutableList(collection);
            }
            return ResultKt.listOf(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
        }
        if (z) {
            arrayList = toMutableList((Collection) iterable);
        } else {
            ArrayList arrayList2 = new ArrayList();
            toCollection(iterable, arrayList2);
            arrayList = arrayList2;
        }
        int size2 = arrayList.size();
        return size2 != 0 ? size2 != 1 ? arrayList : ResultKt.listOf(arrayList.get(0)) : emptyList;
    }

    public static ArrayList toMutableList(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "<this>");
        return new ArrayList(collection);
    }

    public static Set toSet(Iterable iterable) {
        ResultKt.checkNotNullParameter(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        EmptySet emptySet = EmptySet.INSTANCE;
        if (!z) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            toCollection(iterable, linkedHashSet);
            int size = linkedHashSet.size();
            if (size == 0) {
                return emptySet;
            }
            if (size != 1) {
                return linkedHashSet;
            }
            Set singleton = Collections.singleton(linkedHashSet.iterator().next());
            ResultKt.checkNotNullExpressionValue(singleton, "singleton(element)");
            return singleton;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return emptySet;
        }
        if (size2 != 1) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(ResultKt.mapCapacity(collection.size()));
            toCollection(iterable, linkedHashSet2);
            return linkedHashSet2;
        }
        Set singleton2 = Collections.singleton(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
        ResultKt.checkNotNullExpressionValue(singleton2, "singleton(element)");
        return singleton2;
    }
}
