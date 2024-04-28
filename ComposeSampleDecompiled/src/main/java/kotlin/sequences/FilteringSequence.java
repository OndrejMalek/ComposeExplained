package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class FilteringSequence implements Sequence {
    public final Function1 predicate;
    public final boolean sendWhen;
    public final Sequence sequence;

    public FilteringSequence(GeneratorSequence generatorSequence) {
        SequencesKt___SequencesKt$filterNotNull$1 sequencesKt___SequencesKt$filterNotNull$1 = SequencesKt___SequencesKt$filterNotNull$1.INSTANCE;
        this.sequence = generatorSequence;
        this.sendWhen = false;
        this.predicate = sequencesKt___SequencesKt$filterNotNull$1;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new FilteringSequence$iterator$1(this);
    }
}
