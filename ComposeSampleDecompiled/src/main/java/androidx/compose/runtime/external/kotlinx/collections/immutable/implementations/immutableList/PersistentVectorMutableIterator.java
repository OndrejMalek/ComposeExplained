package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PersistentVectorMutableIterator extends AbstractListIterator {
    public final PersistentVectorBuilder builder;
    public int expectedModCount;
    public int lastIteratedIndex;
    public TrieIterator trieIterator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersistentVectorMutableIterator(PersistentVectorBuilder persistentVectorBuilder, int i) {
        super(i, persistentVectorBuilder.getSize());
        ResultKt.checkNotNullParameter(persistentVectorBuilder, "builder");
        this.builder = persistentVectorBuilder;
        this.expectedModCount = persistentVectorBuilder.getModCount$runtime_release();
        this.lastIteratedIndex = -1;
        setupTrieIterator();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator
    public final void add(Object obj) {
        checkForComodification();
        int i = this.index;
        PersistentVectorBuilder persistentVectorBuilder = this.builder;
        persistentVectorBuilder.add(i, obj);
        this.index++;
        this.size = persistentVectorBuilder.getSize();
        this.expectedModCount = persistentVectorBuilder.getModCount$runtime_release();
        this.lastIteratedIndex = -1;
        setupTrieIterator();
    }

    public final void checkForComodification() {
        if (this.expectedModCount != this.builder.getModCount$runtime_release()) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        checkForComodification();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = this.index;
        this.lastIteratedIndex = i;
        TrieIterator trieIterator = this.trieIterator;
        PersistentVectorBuilder persistentVectorBuilder = this.builder;
        if (trieIterator == null) {
            Object[] objArr = persistentVectorBuilder.tail;
            this.index = i + 1;
            return objArr[i];
        }
        if (trieIterator.hasNext()) {
            this.index++;
            return trieIterator.next();
        }
        Object[] objArr2 = persistentVectorBuilder.tail;
        int i2 = this.index;
        this.index = i2 + 1;
        return objArr2[i2 - trieIterator.size];
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        checkForComodification();
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i = this.index;
        this.lastIteratedIndex = i - 1;
        TrieIterator trieIterator = this.trieIterator;
        PersistentVectorBuilder persistentVectorBuilder = this.builder;
        if (trieIterator == null) {
            Object[] objArr = persistentVectorBuilder.tail;
            int i2 = i - 1;
            this.index = i2;
            return objArr[i2];
        }
        int i3 = trieIterator.size;
        if (i <= i3) {
            this.index = i - 1;
            return trieIterator.previous();
        }
        Object[] objArr2 = persistentVectorBuilder.tail;
        int i4 = i - 1;
        this.index = i4;
        return objArr2[i4 - i3];
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        checkForComodification();
        int i = this.lastIteratedIndex;
        if (i == -1) {
            throw new IllegalStateException();
        }
        PersistentVectorBuilder persistentVectorBuilder = this.builder;
        persistentVectorBuilder.removeAt(i);
        int i2 = this.lastIteratedIndex;
        if (i2 < this.index) {
            this.index = i2;
        }
        this.size = persistentVectorBuilder.getSize();
        this.expectedModCount = persistentVectorBuilder.getModCount$runtime_release();
        this.lastIteratedIndex = -1;
        setupTrieIterator();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator
    public final void set(Object obj) {
        checkForComodification();
        int i = this.lastIteratedIndex;
        if (i == -1) {
            throw new IllegalStateException();
        }
        PersistentVectorBuilder persistentVectorBuilder = this.builder;
        persistentVectorBuilder.set(i, obj);
        this.expectedModCount = persistentVectorBuilder.getModCount$runtime_release();
        setupTrieIterator();
    }

    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v4 */
    public final void setupTrieIterator() {
        PersistentVectorBuilder persistentVectorBuilder = this.builder;
        Object[] objArr = persistentVectorBuilder.root;
        if (objArr == null) {
            this.trieIterator = null;
            return;
        }
        int i = (persistentVectorBuilder.size - 1) & (-32);
        int i2 = this.index;
        if (i2 > i) {
            i2 = i;
        }
        int i3 = (persistentVectorBuilder.rootShift / 5) + 1;
        TrieIterator trieIterator = this.trieIterator;
        if (trieIterator == null) {
            this.trieIterator = new TrieIterator(objArr, i2, i, i3);
            return;
        }
        trieIterator.index = i2;
        trieIterator.size = i;
        trieIterator.height = i3;
        if (trieIterator.path.length < i3) {
            trieIterator.path = new Object[i3];
        }
        trieIterator.path[0] = objArr;
        ?? r6 = i2 == i ? 1 : 0;
        trieIterator.isInRightEdge = r6;
        trieIterator.fillPath(i2 - r6, 1);
    }
}
