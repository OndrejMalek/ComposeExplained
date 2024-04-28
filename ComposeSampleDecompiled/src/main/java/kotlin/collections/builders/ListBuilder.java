package kotlin.collections.builders;

import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.runtime.snapshots.SnapshotStateListKt;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class ListBuilder extends AbstractMutableList implements RandomAccess, Serializable {
    public static final ListBuilder Empty;
    public Object[] array;
    public final ListBuilder backing;
    public boolean isReadOnly;
    public int length;
    public final int offset;
    public final ListBuilder root;

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    public ListBuilder(Object[] objArr, int i, int i2, boolean z, ListBuilder listBuilder, ListBuilder listBuilder2) {
        this.array = objArr;
        this.offset = i;
        this.length = i2;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        checkIsMutable();
        addAtInternal(this.offset + this.length, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        int size = collection.size();
        addAllInternal(this.offset + this.length, size, collection);
        return size > 0;
    }

    public final void addAllInternal(int i, int i2, Collection collection) {
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.addAllInternal(i, i2, collection);
            this.array = listBuilder.array;
            this.length += i2;
        } else {
            insertAtInternal(i, i2);
            Iterator it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.array[i + i3] = it.next();
            }
        }
    }

    public final void addAtInternal(int i, Object obj) {
        ListBuilder listBuilder = this.backing;
        if (listBuilder == null) {
            insertAtInternal(i, 1);
            this.array[i] = obj;
        } else {
            listBuilder.addAtInternal(i, obj);
            this.array = listBuilder.array;
            this.length++;
        }
    }

    public final void checkIsMutable() {
        ListBuilder listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        checkIsMutable();
        removeRangeInternal(this.offset, this.length);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            Object[] objArr = this.array;
            int i = this.length;
            if (i != list.size()) {
                return false;
            }
            for (int i2 = 0; i2 < i; i2++) {
                if (!ResultKt.areEqual(objArr[this.offset + i2], list.get(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        ULong.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return this.array[this.offset + i];
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        Object[] objArr = this.array;
        int i = this.length;
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[this.offset + i3];
            i2 = (i2 * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (ResultKt.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public final void insertAtInternal(int i, int i2) {
        int i3 = this.length + i2;
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        if (i3 < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr = this.array;
        if (i3 > objArr.length) {
            int length = objArr.length;
            int i4 = length + (length >> 1);
            if (i4 - i3 < 0) {
                i4 = i3;
            }
            if (i4 - 2147483639 > 0) {
                i4 = i3 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            Object[] copyOf = Arrays.copyOf(objArr, i4);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.array = copyOf;
        }
        Object[] objArr2 = this.array;
        MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, i + i2, i, this.offset + this.length);
        this.length += i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new Itr(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (ResultKt.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return new Itr(this, 0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        checkIsMutable();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            removeAt(indexOf);
        }
        return indexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        return retainOrRemoveAllInternal(this.offset, this.length, collection, false) > 0;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        checkIsMutable();
        ULong.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return removeAtInternal(this.offset + i);
    }

    public final Object removeAtInternal(int i) {
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return listBuilder.removeAtInternal(i);
        }
        Object[] objArr = this.array;
        Object obj = objArr[i];
        int i2 = this.length;
        int i3 = this.offset;
        MapsKt___MapsJvmKt.copyInto(objArr, objArr, i, i + 1, i2 + i3);
        Object[] objArr2 = this.array;
        int i4 = (i3 + this.length) - 1;
        ResultKt.checkNotNullParameter(objArr2, "<this>");
        objArr2[i4] = null;
        this.length--;
        return obj;
    }

    public final void removeRangeInternal(int i, int i2) {
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.removeRangeInternal(i, i2);
        } else {
            Object[] objArr = this.array;
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, i, i + i2, this.length);
            Object[] objArr2 = this.array;
            int i3 = this.length;
            ResultKt.checkNotNullParameter(objArr2, "<this>");
            for (int i4 = i3 - i2; i4 < i3; i4++) {
                objArr2[i4] = null;
            }
        }
        this.length -= i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        return retainOrRemoveAllInternal(this.offset, this.length, collection, true) > 0;
    }

    public final int retainOrRemoveAllInternal(int i, int i2, Collection collection, boolean z) {
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            int retainOrRemoveAllInternal = listBuilder.retainOrRemoveAllInternal(i, i2, collection, z);
            this.length -= retainOrRemoveAllInternal;
            return retainOrRemoveAllInternal;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i + i3;
            if (collection.contains(this.array[i5]) == z) {
                Object[] objArr = this.array;
                i3++;
                objArr[i4 + i] = objArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        Object[] objArr2 = this.array;
        MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, i + i4, i2 + i, this.length);
        Object[] objArr3 = this.array;
        int i7 = this.length;
        ResultKt.checkNotNullParameter(objArr3, "<this>");
        for (int i8 = i7 - i6; i8 < i7; i8++) {
            objArr3[i8] = null;
        }
        this.length -= i6;
        return i6;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        checkIsMutable();
        ULong.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        Object[] objArr = this.array;
        int i2 = this.offset + i;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        ULong.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
        Object[] objArr = this.array;
        int i3 = this.offset + i;
        int i4 = i2 - i;
        boolean z = this.isReadOnly;
        ListBuilder listBuilder = this.root;
        return new ListBuilder(objArr, i3, i4, z, this, listBuilder == null ? this : listBuilder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "destination");
        int length = objArr.length;
        int i = this.length;
        int i2 = this.offset;
        if (length < i) {
            Object[] copyOfRange = Arrays.copyOfRange(this.array, i2, i + i2, objArr.getClass());
            ResultKt.checkNotNullExpressionValue(copyOfRange, "copyOfRange(array, offse…h, destination.javaClass)");
            return copyOfRange;
        }
        MapsKt___MapsJvmKt.copyInto(this.array, objArr, 0, i2, i + i2);
        int length2 = objArr.length;
        int i3 = this.length;
        if (length2 > i3) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        Object[] objArr = this.array;
        int i = this.length;
        StringBuilder sb = new StringBuilder((i * 3) + 2);
        sb.append("[");
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(objArr[this.offset + i2]);
        }
        sb.append("]");
        String sb2 = sb.toString();
        ResultKt.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        ULong.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        return new Itr(this, i);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        checkIsMutable();
        ULong.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        addAtInternal(this.offset + i, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        ULong.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        int size = collection.size();
        addAllInternal(this.offset + i, size, collection);
        return size > 0;
    }

    /* loaded from: classes.dex */
    public final class Itr implements ListIterator, KMappedMarker {
        public final /* synthetic */ int $r8$classId = 1;
        public int index;
        public int lastIndex;
        public final Object list;

        public Itr(SnapshotStateList snapshotStateList, int i) {
            ResultKt.checkNotNullParameter(snapshotStateList, "list");
            this.list = snapshotStateList;
            this.index = i - 1;
            this.lastIndex = snapshotStateList.getModification$runtime_release();
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            Object obj2 = this.list;
            switch (this.$r8$classId) {
                case 0:
                    int i = this.index;
                    this.index = i + 1;
                    ((ListBuilder) obj2).add(i, obj);
                    this.lastIndex = -1;
                    return;
                default:
                    validateModification();
                    SnapshotStateList snapshotStateList = (SnapshotStateList) obj2;
                    snapshotStateList.add(this.index + 1, obj);
                    this.index++;
                    this.lastIndex = snapshotStateList.getModification$runtime_release();
                    return;
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            Object obj = this.list;
            switch (this.$r8$classId) {
                case 0:
                    return this.index < ((ListBuilder) obj).length;
                default:
                    return this.index < ((SnapshotStateList) obj).size() - 1;
            }
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            switch (this.$r8$classId) {
                case 0:
                    return this.index > 0;
                default:
                    return this.index >= 0;
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            Object obj = this.list;
            switch (this.$r8$classId) {
                case 0:
                    int i = this.index;
                    ListBuilder listBuilder = (ListBuilder) obj;
                    if (i >= listBuilder.length) {
                        throw new NoSuchElementException();
                    }
                    this.index = i + 1;
                    this.lastIndex = i;
                    return listBuilder.array[listBuilder.offset + i];
                default:
                    validateModification();
                    int i2 = this.index + 1;
                    SnapshotStateList snapshotStateList = (SnapshotStateList) obj;
                    SnapshotStateListKt.access$validateRange(i2, snapshotStateList.size());
                    Object obj2 = snapshotStateList.get(i2);
                    this.index = i2;
                    return obj2;
            }
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            switch (this.$r8$classId) {
                case 0:
                    return this.index;
                default:
                    return this.index + 1;
            }
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            Object obj = this.list;
            switch (this.$r8$classId) {
                case 0:
                    int i = this.index;
                    if (i <= 0) {
                        throw new NoSuchElementException();
                    }
                    int i2 = i - 1;
                    this.index = i2;
                    this.lastIndex = i2;
                    ListBuilder listBuilder = (ListBuilder) obj;
                    return listBuilder.array[listBuilder.offset + i2];
                default:
                    validateModification();
                    SnapshotStateList snapshotStateList = (SnapshotStateList) obj;
                    SnapshotStateListKt.access$validateRange(this.index, snapshotStateList.size());
                    this.index--;
                    return snapshotStateList.get(this.index);
            }
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            switch (this.$r8$classId) {
                case 0:
                    return this.index - 1;
                default:
                    return this.index;
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            Object obj = this.list;
            switch (this.$r8$classId) {
                case 0:
                    int i = this.lastIndex;
                    if (i == -1) {
                        throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
                    }
                    ((ListBuilder) obj).removeAt(i);
                    this.index = this.lastIndex;
                    this.lastIndex = -1;
                    return;
                default:
                    validateModification();
                    SnapshotStateList snapshotStateList = (SnapshotStateList) obj;
                    snapshotStateList.remove(this.index);
                    this.index--;
                    this.lastIndex = snapshotStateList.getModification$runtime_release();
                    return;
            }
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            Object obj2 = this.list;
            switch (this.$r8$classId) {
                case 0:
                    int i = this.lastIndex;
                    if (i == -1) {
                        throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
                    }
                    ((ListBuilder) obj2).set(i, obj);
                    return;
                default:
                    validateModification();
                    SnapshotStateList snapshotStateList = (SnapshotStateList) obj2;
                    snapshotStateList.set(this.index, obj);
                    this.lastIndex = snapshotStateList.getModification$runtime_release();
                    return;
            }
        }

        public final void validateModification() {
            if (((SnapshotStateList) this.list).getModification$runtime_release() != this.lastIndex) {
                throw new ConcurrentModificationException();
            }
        }

        public Itr(ListBuilder listBuilder, int i) {
            ResultKt.checkNotNullParameter(listBuilder, "list");
            this.list = listBuilder;
            this.index = i;
            this.lastIndex = -1;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        Object[] objArr = this.array;
        int i = this.length;
        int i2 = this.offset;
        return MapsKt___MapsJvmKt.copyOfRange(i2, i + i2, objArr);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ListBuilder(int i) {
        this(new Object[i], 0, 0, false, null, null);
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.".toString());
    }
}
