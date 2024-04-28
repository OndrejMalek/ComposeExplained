package androidx.compose.runtime.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;

/* loaded from: classes.dex */
public final class MutableVector implements RandomAccess {
    public Object[] content;
    public MutableVectorList list;
    public int size;

    /* loaded from: classes.dex */
    public final class MutableVectorList implements List, KMutableCollection {
        public final MutableVector vector;

        public MutableVectorList(MutableVector mutableVector) {
            ResultKt.checkNotNullParameter(mutableVector, "vector");
            this.vector = mutableVector;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean add(Object obj) {
            this.vector.add(obj);
            return true;
        }

        @Override // java.util.List
        public final boolean addAll(int i, Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            return this.vector.addAll(i, collection);
        }

        @Override // java.util.List, java.util.Collection
        public final void clear() {
            this.vector.clear();
        }

        @Override // java.util.List, java.util.Collection
        public final boolean contains(Object obj) {
            return this.vector.contains(obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean containsAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            MutableVector mutableVector = this.vector;
            mutableVector.getClass();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!mutableVector.contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public final Object get(int i) {
            ResultKt.access$checkIndex(i, this);
            return this.vector.content[i];
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            MutableVector mutableVector = this.vector;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                while (!ResultKt.areEqual(obj, objArr[i2])) {
                    i2++;
                    if (i2 >= i) {
                    }
                }
                return i2;
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean isEmpty() {
            return this.vector.size == 0;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            MutableVector mutableVector = this.vector;
            int i = mutableVector.size;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = mutableVector.content;
                while (!ResultKt.areEqual(obj, objArr[i2])) {
                    i2--;
                    if (i2 < 0) {
                    }
                }
                return i2;
            }
            return -1;
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean remove(Object obj) {
            return this.vector.remove(obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean removeAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            MutableVector mutableVector = this.vector;
            mutableVector.getClass();
            if (collection.isEmpty()) {
                return false;
            }
            int i = mutableVector.size;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                mutableVector.remove(it.next());
            }
            return i != mutableVector.size;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean retainAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            MutableVector mutableVector = this.vector;
            mutableVector.getClass();
            int i = mutableVector.size;
            for (int i2 = i - 1; -1 < i2; i2--) {
                if (!collection.contains(mutableVector.content[i2])) {
                    mutableVector.removeAt(i2);
                }
            }
            return i != mutableVector.size;
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            ResultKt.access$checkIndex(i, this);
            Object[] objArr = this.vector.content;
            Object obj2 = objArr[i];
            objArr[i] = obj;
            return obj2;
        }

        @Override // java.util.List, java.util.Collection
        public final int size() {
            return this.vector.size;
        }

        @Override // java.util.List
        public final List subList(int i, int i2) {
            ResultKt.access$checkSubIndex(this, i, i2);
            return new SubList(this, i, i2);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            this.vector.add(i, obj);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean addAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            MutableVector mutableVector = this.vector;
            mutableVector.getClass();
            return mutableVector.addAll(mutableVector.size, collection);
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            return new VectorListIterator(i, this);
        }

        @Override // java.util.List
        public final Object remove(int i) {
            ResultKt.access$checkIndex(i, this);
            return this.vector.removeAt(i);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            ResultKt.checkNotNullParameter(objArr, "array");
            return CollectionToArray.toArray(this, objArr);
        }
    }

    /* loaded from: classes.dex */
    public final class SubList implements List, KMutableCollection {
        public int end;
        public final List list;
        public final int start;

        public SubList(List list, int i, int i2) {
            ResultKt.checkNotNullParameter(list, "list");
            this.list = list;
            this.start = i;
            this.end = i2;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean add(Object obj) {
            int i = this.end;
            this.end = i + 1;
            this.list.add(i, obj);
            return true;
        }

        @Override // java.util.List
        public final boolean addAll(int i, Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            this.list.addAll(i + this.start, collection);
            this.end = collection.size() + this.end;
            return collection.size() > 0;
        }

        @Override // java.util.List, java.util.Collection
        public final void clear() {
            int i = this.end - 1;
            int i2 = this.start;
            if (i2 <= i) {
                while (true) {
                    this.list.remove(i);
                    if (i == i2) {
                        break;
                    } else {
                        i--;
                    }
                }
            }
            this.end = i2;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean contains(Object obj) {
            int i = this.end;
            for (int i2 = this.start; i2 < i; i2++) {
                if (ResultKt.areEqual(this.list.get(i2), obj)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean containsAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public final Object get(int i) {
            ResultKt.access$checkIndex(i, this);
            return this.list.get(i + this.start);
        }

        @Override // java.util.List
        public final int indexOf(Object obj) {
            int i = this.end;
            int i2 = this.start;
            for (int i3 = i2; i3 < i; i3++) {
                if (ResultKt.areEqual(this.list.get(i3), obj)) {
                    return i3 - i2;
                }
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean isEmpty() {
            return this.end == this.start;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List
        public final int lastIndexOf(Object obj) {
            int i = this.end - 1;
            int i2 = this.start;
            if (i2 > i) {
                return -1;
            }
            while (!ResultKt.areEqual(this.list.get(i), obj)) {
                if (i == i2) {
                    return -1;
                }
                i--;
            }
            return i - i2;
        }

        @Override // java.util.List
        public final ListIterator listIterator() {
            return new VectorListIterator(0, this);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean remove(Object obj) {
            int i = this.end;
            for (int i2 = this.start; i2 < i; i2++) {
                List list = this.list;
                if (ResultKt.areEqual(list.get(i2), obj)) {
                    list.remove(i2);
                    this.end--;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean removeAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            int i = this.end;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
            return i != this.end;
        }

        @Override // java.util.List, java.util.Collection
        public final boolean retainAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            int i = this.end;
            int i2 = i - 1;
            int i3 = this.start;
            if (i3 <= i2) {
                while (true) {
                    List list = this.list;
                    if (!collection.contains(list.get(i2))) {
                        list.remove(i2);
                        this.end--;
                    }
                    if (i2 == i3) {
                        break;
                    }
                    i2--;
                }
            }
            return i != this.end;
        }

        @Override // java.util.List
        public final Object set(int i, Object obj) {
            ResultKt.access$checkIndex(i, this);
            return this.list.set(i + this.start, obj);
        }

        @Override // java.util.List, java.util.Collection
        public final int size() {
            return this.end - this.start;
        }

        @Override // java.util.List
        public final List subList(int i, int i2) {
            ResultKt.access$checkSubIndex(this, i, i2);
            return new SubList(this, i, i2);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.List
        public final void add(int i, Object obj) {
            this.list.add(i + this.start, obj);
            this.end++;
        }

        @Override // java.util.List
        public final ListIterator listIterator(int i) {
            return new VectorListIterator(i, this);
        }

        @Override // java.util.List, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            ResultKt.checkNotNullParameter(objArr, "array");
            return CollectionToArray.toArray(this, objArr);
        }

        @Override // java.util.List, java.util.Collection
        public final boolean addAll(Collection collection) {
            ResultKt.checkNotNullParameter(collection, "elements");
            this.list.addAll(this.end, collection);
            this.end = collection.size() + this.end;
            return collection.size() > 0;
        }

        @Override // java.util.List
        public final Object remove(int i) {
            ResultKt.access$checkIndex(i, this);
            this.end--;
            return this.list.remove(i + this.start);
        }
    }

    /* loaded from: classes.dex */
    public final class VectorListIterator implements ListIterator, KMappedMarker {
        public int index;
        public final List list;

        public VectorListIterator(int i, List list) {
            ResultKt.checkNotNullParameter(list, "list");
            this.list = list;
            this.index = i;
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            this.list.add(this.index, obj);
            this.index++;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.index < this.list.size();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            int i = this.index;
            this.index = i + 1;
            return this.list.get(i);
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            int i = this.index - 1;
            this.index = i;
            return this.list.get(i);
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            int i = this.index - 1;
            this.index = i;
            this.list.remove(i);
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            this.list.set(this.index, obj);
        }
    }

    public final void add(Object obj) {
        ensureCapacity(this.size + 1);
        Object[] objArr = this.content;
        int i = this.size;
        objArr[i] = obj;
        this.size = i + 1;
    }

    public final boolean addAll(int i, Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        int i2 = 0;
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(collection.size() + this.size);
        Object[] objArr = this.content;
        if (i != this.size) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, collection.size() + i, i, this.size);
        }
        for (Object obj : collection) {
            int i3 = i2 + 1;
            if (i2 >= 0) {
                objArr[i2 + i] = obj;
                i2 = i3;
            } else {
                throw new ArithmeticException("Index overflow has happened.");
            }
        }
        this.size = collection.size() + this.size;
        return true;
    }

    public final List asMutableList() {
        MutableVectorList mutableVectorList = this.list;
        if (mutableVectorList != null) {
            return mutableVectorList;
        }
        MutableVectorList mutableVectorList2 = new MutableVectorList(this);
        this.list = mutableVectorList2;
        return mutableVectorList2;
    }

    public final void clear() {
        Object[] objArr = this.content;
        int i = this.size;
        while (true) {
            i--;
            if (-1 >= i) {
                this.size = 0;
                return;
            }
            objArr[i] = null;
        }
    }

    public final boolean contains(Object obj) {
        int i = this.size - 1;
        if (i >= 0) {
            for (int i2 = 0; !ResultKt.areEqual(this.content[i2], obj); i2++) {
                if (i2 != i) {
                }
            }
            return true;
        }
        return false;
    }

    public final void ensureCapacity(int i) {
        Object[] objArr = this.content;
        if (objArr.length < i) {
            Object[] copyOf = Arrays.copyOf(objArr, Math.max(i, objArr.length * 2));
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.content = copyOf;
        }
    }

    public final boolean isNotEmpty() {
        return this.size != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean remove(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.size
            r1 = 0
            if (r0 <= 0) goto L15
            java.lang.Object[] r2 = r5.content
            r3 = r1
        L8:
            r4 = r2[r3]
            boolean r4 = kotlin.ResultKt.areEqual(r6, r4)
            if (r4 == 0) goto L11
            goto L16
        L11:
            int r3 = r3 + 1
            if (r3 < r0) goto L8
        L15:
            r3 = -1
        L16:
            if (r3 < 0) goto L1d
            r5.removeAt(r3)
            r6 = 1
            return r6
        L1d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.MutableVector.remove(java.lang.Object):boolean");
    }

    public final Object removeAt(int i) {
        Object[] objArr = this.content;
        Object obj = objArr[i];
        int i2 = this.size;
        if (i != i2 - 1) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, i, i + 1, i2);
        }
        int i3 = this.size - 1;
        this.size = i3;
        objArr[i3] = null;
        return obj;
    }

    public final void removeRange(int i, int i2) {
        if (i2 > i) {
            int i3 = this.size;
            if (i2 < i3) {
                Object[] objArr = this.content;
                MapsKt___MapsJvmKt.copyInto(objArr, objArr, i, i2, i3);
            }
            int i4 = this.size;
            int i5 = i4 - (i2 - i);
            int i6 = i4 - 1;
            if (i5 <= i6) {
                int i7 = i5;
                while (true) {
                    this.content[i7] = null;
                    if (i7 == i6) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
            this.size = i5;
        }
    }

    public final void add(int i, Object obj) {
        ensureCapacity(this.size + 1);
        Object[] objArr = this.content;
        int i2 = this.size;
        if (i != i2) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, i + 1, i, i2);
        }
        objArr[i] = obj;
        this.size++;
    }

    public final void addAll(int i, MutableVector mutableVector) {
        ResultKt.checkNotNullParameter(mutableVector, "elements");
        int i2 = mutableVector.size;
        if (i2 == 0) {
            return;
        }
        ensureCapacity(this.size + i2);
        Object[] objArr = this.content;
        int i3 = this.size;
        if (i != i3) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, mutableVector.size + i, i, i3);
        }
        MapsKt___MapsJvmKt.copyInto(mutableVector.content, objArr, i, 0, mutableVector.size);
        this.size += mutableVector.size;
    }
}
