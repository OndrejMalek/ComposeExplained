package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public class PersistentHashMapBuilderBaseIterator extends PersistentHashMapBaseIterator {
    public final PersistentHashMapBuilder builder;
    public int expectedModCount;
    public Object lastIteratedKey;
    public boolean nextWasInvoked;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentHashMapBuilderBaseIterator(PersistentHashMapBuilder persistentHashMapBuilder, TrieNodeBaseIterator[] trieNodeBaseIteratorArr) {
        super(persistentHashMapBuilder.node, trieNodeBaseIteratorArr);
        ResultKt.checkNotNullParameter(persistentHashMapBuilder, "builder");
        this.builder = persistentHashMapBuilder;
        this.expectedModCount = persistentHashMapBuilder.modCount;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBaseIterator, java.util.Iterator
    public final Object next() {
        if (this.builder.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        TrieNodeBaseIterator trieNodeBaseIterator = this.path[this.pathLastIndex];
        this.lastIteratedKey = trieNodeBaseIterator.buffer[trieNodeBaseIterator.index];
        this.nextWasInvoked = true;
        return super.next();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBaseIterator, java.util.Iterator
    public final void remove() {
        if (!this.nextWasInvoked) {
            throw new IllegalStateException();
        }
        boolean z = this.hasNext;
        PersistentHashMapBuilder persistentHashMapBuilder = this.builder;
        if (!z) {
            Object obj = this.lastIteratedKey;
            ResultKt.asMutableMap(persistentHashMapBuilder);
            persistentHashMapBuilder.remove(obj);
        } else {
            if (!z) {
                throw new NoSuchElementException();
            }
            TrieNodeBaseIterator trieNodeBaseIterator = this.path[this.pathLastIndex];
            Object obj2 = trieNodeBaseIterator.buffer[trieNodeBaseIterator.index];
            Object obj3 = this.lastIteratedKey;
            ResultKt.asMutableMap(persistentHashMapBuilder);
            persistentHashMapBuilder.remove(obj3);
            resetPath(obj2 != null ? obj2.hashCode() : 0, persistentHashMapBuilder.node, obj2, 0);
        }
        this.lastIteratedKey = null;
        this.nextWasInvoked = false;
        this.expectedModCount = persistentHashMapBuilder.modCount;
    }

    public final void resetPath(int i, TrieNode trieNode, Object obj, int i2) {
        int i3 = i2 * 5;
        TrieNodeBaseIterator[] trieNodeBaseIteratorArr = this.path;
        if (i3 <= 30) {
            int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i3);
            if (trieNode.hasEntryAt$runtime_release(indexSegment$1)) {
                int entryKeyIndex$runtime_release = trieNode.entryKeyIndex$runtime_release(indexSegment$1);
                TrieNodeBaseIterator trieNodeBaseIterator = trieNodeBaseIteratorArr[i2];
                Object[] objArr = trieNode.buffer;
                int bitCount = Integer.bitCount(trieNode.dataMap) * 2;
                trieNodeBaseIterator.getClass();
                ResultKt.checkNotNullParameter(objArr, "buffer");
                trieNodeBaseIterator.buffer = objArr;
                trieNodeBaseIterator.dataSize = bitCount;
                trieNodeBaseIterator.index = entryKeyIndex$runtime_release;
                this.pathLastIndex = i2;
                return;
            }
            int nodeIndex$runtime_release = trieNode.nodeIndex$runtime_release(indexSegment$1);
            TrieNode nodeAtIndex$runtime_release = trieNode.nodeAtIndex$runtime_release(nodeIndex$runtime_release);
            TrieNodeBaseIterator trieNodeBaseIterator2 = trieNodeBaseIteratorArr[i2];
            Object[] objArr2 = trieNode.buffer;
            int bitCount2 = Integer.bitCount(trieNode.dataMap) * 2;
            trieNodeBaseIterator2.getClass();
            ResultKt.checkNotNullParameter(objArr2, "buffer");
            trieNodeBaseIterator2.buffer = objArr2;
            trieNodeBaseIterator2.dataSize = bitCount2;
            trieNodeBaseIterator2.index = nodeIndex$runtime_release;
            resetPath(i, nodeAtIndex$runtime_release, obj, i2 + 1);
            return;
        }
        TrieNodeBaseIterator trieNodeBaseIterator3 = trieNodeBaseIteratorArr[i2];
        Object[] objArr3 = trieNode.buffer;
        int length = objArr3.length;
        trieNodeBaseIterator3.getClass();
        trieNodeBaseIterator3.buffer = objArr3;
        trieNodeBaseIterator3.dataSize = length;
        trieNodeBaseIterator3.index = 0;
        while (true) {
            TrieNodeBaseIterator trieNodeBaseIterator4 = trieNodeBaseIteratorArr[i2];
            if (ResultKt.areEqual(trieNodeBaseIterator4.buffer[trieNodeBaseIterator4.index], obj)) {
                this.pathLastIndex = i2;
                return;
            } else {
                trieNodeBaseIteratorArr[i2].index += 2;
            }
        }
    }
}
