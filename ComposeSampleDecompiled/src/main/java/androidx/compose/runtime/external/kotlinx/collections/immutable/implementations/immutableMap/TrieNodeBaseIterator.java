package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public abstract class TrieNodeBaseIterator implements Iterator, KMappedMarker {
    public Object[] buffer = TrieNode.EMPTY.buffer;
    public int dataSize;
    public int index;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.index < this.dataSize;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
