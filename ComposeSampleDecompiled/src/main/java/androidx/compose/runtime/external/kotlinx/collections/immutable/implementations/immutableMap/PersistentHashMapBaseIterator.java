package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public abstract class PersistentHashMapBaseIterator implements Iterator, KMappedMarker {
    public boolean hasNext;
    public final TrieNodeBaseIterator[] path;
    public int pathLastIndex;

    public PersistentHashMapBaseIterator(TrieNode trieNode, TrieNodeBaseIterator[] trieNodeBaseIteratorArr) {
        ResultKt.checkNotNullParameter(trieNode, "node");
        this.path = trieNodeBaseIteratorArr;
        this.hasNext = true;
        TrieNodeBaseIterator trieNodeBaseIterator = trieNodeBaseIteratorArr[0];
        Object[] objArr = trieNode.buffer;
        int bitCount = Integer.bitCount(trieNode.dataMap) * 2;
        trieNodeBaseIterator.getClass();
        ResultKt.checkNotNullParameter(objArr, "buffer");
        trieNodeBaseIterator.buffer = objArr;
        trieNodeBaseIterator.dataSize = bitCount;
        trieNodeBaseIterator.index = 0;
        this.pathLastIndex = 0;
        ensureNextEntryIsReady();
    }

    public final void ensureNextEntryIsReady() {
        int i = this.pathLastIndex;
        TrieNodeBaseIterator[] trieNodeBaseIteratorArr = this.path;
        TrieNodeBaseIterator trieNodeBaseIterator = trieNodeBaseIteratorArr[i];
        if (trieNodeBaseIterator.index < trieNodeBaseIterator.dataSize) {
            return;
        }
        while (-1 < i) {
            int moveToNextNodeWithData = moveToNextNodeWithData(i);
            if (moveToNextNodeWithData == -1) {
                TrieNodeBaseIterator trieNodeBaseIterator2 = trieNodeBaseIteratorArr[i];
                int i2 = trieNodeBaseIterator2.index;
                Object[] objArr = trieNodeBaseIterator2.buffer;
                if (i2 < objArr.length) {
                    int length = objArr.length;
                    trieNodeBaseIterator2.index = i2 + 1;
                    moveToNextNodeWithData = moveToNextNodeWithData(i);
                }
            }
            if (moveToNextNodeWithData != -1) {
                this.pathLastIndex = moveToNextNodeWithData;
                return;
            }
            if (i > 0) {
                TrieNodeBaseIterator trieNodeBaseIterator3 = trieNodeBaseIteratorArr[i - 1];
                int i3 = trieNodeBaseIterator3.index;
                int length2 = trieNodeBaseIterator3.buffer.length;
                trieNodeBaseIterator3.index = i3 + 1;
            }
            TrieNodeBaseIterator trieNodeBaseIterator4 = trieNodeBaseIteratorArr[i];
            Object[] objArr2 = TrieNode.EMPTY.buffer;
            trieNodeBaseIterator4.getClass();
            ResultKt.checkNotNullParameter(objArr2, "buffer");
            trieNodeBaseIterator4.buffer = objArr2;
            trieNodeBaseIterator4.dataSize = 0;
            trieNodeBaseIterator4.index = 0;
            i--;
        }
        this.hasNext = false;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.hasNext;
    }

    public final int moveToNextNodeWithData(int i) {
        TrieNodeBaseIterator[] trieNodeBaseIteratorArr = this.path;
        TrieNodeBaseIterator trieNodeBaseIterator = trieNodeBaseIteratorArr[i];
        int i2 = trieNodeBaseIterator.index;
        if (i2 < trieNodeBaseIterator.dataSize) {
            return i;
        }
        Object[] objArr = trieNodeBaseIterator.buffer;
        if (i2 >= objArr.length) {
            return -1;
        }
        int length = objArr.length;
        Object obj = objArr[i2];
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator>");
        TrieNode trieNode = (TrieNode) obj;
        if (i == 6) {
            TrieNodeBaseIterator trieNodeBaseIterator2 = trieNodeBaseIteratorArr[i + 1];
            Object[] objArr2 = trieNode.buffer;
            int length2 = objArr2.length;
            trieNodeBaseIterator2.getClass();
            trieNodeBaseIterator2.buffer = objArr2;
            trieNodeBaseIterator2.dataSize = length2;
            trieNodeBaseIterator2.index = 0;
        } else {
            TrieNodeBaseIterator trieNodeBaseIterator3 = trieNodeBaseIteratorArr[i + 1];
            Object[] objArr3 = trieNode.buffer;
            int bitCount = Integer.bitCount(trieNode.dataMap) * 2;
            trieNodeBaseIterator3.getClass();
            ResultKt.checkNotNullParameter(objArr3, "buffer");
            trieNodeBaseIterator3.buffer = objArr3;
            trieNodeBaseIterator3.dataSize = bitCount;
            trieNodeBaseIterator3.index = 0;
        }
        return moveToNextNodeWithData(i + 1);
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        Object next = this.path[this.pathLastIndex].next();
        ensureNextEntryIsReady();
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
