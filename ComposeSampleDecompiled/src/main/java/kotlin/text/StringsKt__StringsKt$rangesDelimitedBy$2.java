package kotlin.text;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntProgression;

/* loaded from: classes.dex */
public final class StringsKt__StringsKt$rangesDelimitedBy$2 extends Lambda implements Function2 {
    public final /* synthetic */ Object $delimitersList;
    public final /* synthetic */ boolean $ignoreCase;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ StringsKt__StringsKt$rangesDelimitedBy$2(int i, Object obj, boolean z) {
        super(2);
        this.$r8$classId = i;
        this.$delimitersList = obj;
        this.$ignoreCase = z;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Object obj3;
        Pair pair;
        Object obj4;
        switch (this.$r8$classId) {
            case 0:
                CharSequence charSequence = (CharSequence) obj;
                int intValue = ((Number) obj2).intValue();
                ResultKt.checkNotNullParameter(charSequence, "$this$$receiver");
                List list = (List) this.$delimitersList;
                boolean z = this.$ignoreCase;
                if (z || list.size() != 1) {
                    if (intValue < 0) {
                        intValue = 0;
                    }
                    boolean z2 = charSequence instanceof String;
                    int i = new IntProgression(intValue, charSequence.length(), 1).last;
                    if (z2) {
                        if (intValue <= i) {
                            while (true) {
                                Iterator it = list.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        obj4 = it.next();
                                        String str = (String) obj4;
                                        if (StringsKt__StringsKt.regionMatches(intValue, str.length(), str, (String) charSequence, z)) {
                                        }
                                    } else {
                                        obj4 = null;
                                    }
                                }
                                String str2 = (String) obj4;
                                if (str2 != null) {
                                    pair = new Pair(Integer.valueOf(intValue), str2);
                                } else if (intValue != i) {
                                    intValue++;
                                }
                            }
                        }
                        pair = null;
                    } else {
                        if (intValue <= i) {
                            while (true) {
                                Iterator it2 = list.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        obj3 = it2.next();
                                        String str3 = (String) obj3;
                                        if (StringsKt__StringsKt.regionMatchesImpl(str3, charSequence, intValue, str3.length(), z)) {
                                        }
                                    } else {
                                        obj3 = null;
                                    }
                                }
                                String str4 = (String) obj3;
                                if (str4 != null) {
                                    pair = new Pair(Integer.valueOf(intValue), str4);
                                } else if (intValue != i) {
                                    intValue++;
                                }
                            }
                        }
                        pair = null;
                    }
                } else {
                    int size = list.size();
                    if (size == 0) {
                        throw new NoSuchElementException("List is empty.");
                    }
                    if (size != 1) {
                        throw new IllegalArgumentException("List has more than one element.");
                    }
                    String str5 = (String) list.get(0);
                    int indexOf$default = StringsKt__StringsKt.indexOf$default(charSequence, str5, intValue, 4);
                    if (indexOf$default >= 0) {
                        pair = new Pair(Integer.valueOf(indexOf$default), str5);
                    }
                    pair = null;
                }
                if (pair == null) {
                    return null;
                }
                return new Pair(pair.first, Integer.valueOf(((String) pair.second).length()));
            default:
                return ((CoroutineContext) obj).plus((CoroutineContext.Element) obj2);
        }
    }
}
