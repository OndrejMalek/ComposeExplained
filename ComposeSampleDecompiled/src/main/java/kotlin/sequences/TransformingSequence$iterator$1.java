package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class TransformingSequence$iterator$1 implements Iterator, KMappedMarker {
    public final Iterator iterator;
    public final /* synthetic */ GeneratorSequence this$0;

    public TransformingSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.this$0 = generatorSequence;
        this.iterator = ((Sequence) generatorSequence.getInitialValue).iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.this$0.getNextValue.invoke(this.iterator.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
