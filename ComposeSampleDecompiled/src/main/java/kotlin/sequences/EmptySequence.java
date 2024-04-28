package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.EmptyIterator;

/* loaded from: classes.dex */
public final class EmptySequence implements Sequence {
    public static final EmptySequence INSTANCE = new Object();

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }
}
