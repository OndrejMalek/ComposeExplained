package kotlin.sequences;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 implements Sequence {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $this_asSequence$inlined;

    public /* synthetic */ SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1(int i, Object obj) {
        this.$r8$classId = i;
        this.$this_asSequence$inlined = obj;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.coroutines.Continuation, kotlin.sequences.SequenceBuilderIterator, java.util.Iterator, java.lang.Object] */
    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        int i = this.$r8$classId;
        Object obj = this.$this_asSequence$inlined;
        switch (i) {
            case 0:
                return (Iterator) obj;
            default:
                Function2 function2 = (Function2) obj;
                ResultKt.checkNotNullParameter(function2, "block");
                ?? obj2 = new Object();
                obj2.nextStep = ResultKt.createCoroutineUnintercepted(obj2, obj2, function2);
                return obj2;
        }
    }
}
