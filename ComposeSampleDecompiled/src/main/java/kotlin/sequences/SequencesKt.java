package kotlin.sequences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* loaded from: classes.dex */
public abstract class SequencesKt extends MathKt {
    public static Object firstOrNull(FilteringSequence filteringSequence) {
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(filteringSequence);
        if (filteringSequence$iterator$1.hasNext()) {
            return filteringSequence$iterator$1.next();
        }
        return null;
    }

    public static FilteringSequence mapNotNull(Sequence sequence, Function1 function1) {
        return new FilteringSequence(new GeneratorSequence(sequence, function1));
    }

    public static List toList(Sequence sequence) {
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ResultKt.listOf(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
