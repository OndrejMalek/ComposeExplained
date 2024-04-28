package kotlin.collections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.ULong;

/* loaded from: classes.dex */
public final class ArrayDeque extends AbstractMutableList {
    public static final Object[] emptyElementData = new Object[0];
    public Object[] elementData = emptyElementData;
    public int head;
    public int size;

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        ULong.Companion.checkPositionIndex$kotlin_stdlib(i, this.size);
        int i3 = this.size;
        if (i == i3) {
            addLast(obj);
            return;
        }
        if (i == 0) {
            ensureCapacity(i3 + 1);
            int i4 = this.head;
            if (i4 == 0) {
                Object[] objArr = this.elementData;
                ResultKt.checkNotNullParameter(objArr, "<this>");
                i4 = objArr.length;
            }
            int i5 = i4 - 1;
            this.head = i5;
            this.elementData[i5] = obj;
            this.size++;
            return;
        }
        ensureCapacity(i3 + 1);
        int positiveMod = positiveMod(this.head + i);
        int i6 = this.size;
        if (i < ((i6 + 1) >> 1)) {
            if (positiveMod == 0) {
                Object[] objArr2 = this.elementData;
                ResultKt.checkNotNullParameter(objArr2, "<this>");
                i2 = objArr2.length - 1;
            } else {
                i2 = positiveMod - 1;
            }
            int i7 = this.head;
            if (i7 == 0) {
                Object[] objArr3 = this.elementData;
                ResultKt.checkNotNullParameter(objArr3, "<this>");
                i7 = objArr3.length;
            }
            int i8 = i7 - 1;
            int i9 = this.head;
            if (i2 >= i9) {
                Object[] objArr4 = this.elementData;
                objArr4[i8] = objArr4[i9];
                MapsKt___MapsJvmKt.copyInto(objArr4, objArr4, i9, i9 + 1, i2 + 1);
            } else {
                Object[] objArr5 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr5, objArr5, i9 - 1, i9, objArr5.length);
                Object[] objArr6 = this.elementData;
                objArr6[objArr6.length - 1] = objArr6[0];
                MapsKt___MapsJvmKt.copyInto(objArr6, objArr6, 0, 1, i2 + 1);
            }
            this.elementData[i2] = obj;
            this.head = i8;
        } else {
            int positiveMod2 = positiveMod(i6 + this.head);
            if (positiveMod < positiveMod2) {
                Object[] objArr7 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr7, objArr7, positiveMod + 1, positiveMod, positiveMod2);
            } else {
                Object[] objArr8 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr8, objArr8, 1, 0, positiveMod2);
                Object[] objArr9 = this.elementData;
                objArr9[0] = objArr9[objArr9.length - 1];
                MapsKt___MapsJvmKt.copyInto(objArr9, objArr9, positiveMod + 1, positiveMod, objArr9.length - 1);
            }
            this.elementData[positiveMod] = obj;
        }
        this.size++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        ULong.Companion.checkPositionIndex$kotlin_stdlib(i, this.size);
        if (collection.isEmpty()) {
            return false;
        }
        int i2 = this.size;
        if (i == i2) {
            return addAll(collection);
        }
        ensureCapacity(collection.size() + i2);
        int positiveMod = positiveMod(this.size + this.head);
        int positiveMod2 = positiveMod(this.head + i);
        int size = collection.size();
        if (i < ((this.size + 1) >> 1)) {
            int i3 = this.head;
            int i4 = i3 - size;
            if (positiveMod2 < i3) {
                Object[] objArr = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr, objArr, i4, i3, objArr.length);
                if (size >= positiveMod2) {
                    Object[] objArr2 = this.elementData;
                    MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, positiveMod2);
                } else {
                    Object[] objArr3 = this.elementData;
                    MapsKt___MapsJvmKt.copyInto(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.elementData;
                    MapsKt___MapsJvmKt.copyInto(objArr4, objArr4, 0, size, positiveMod2);
                }
            } else if (i4 >= 0) {
                Object[] objArr5 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr5, objArr5, i4, i3, positiveMod2);
            } else {
                Object[] objArr6 = this.elementData;
                i4 += objArr6.length;
                int i5 = positiveMod2 - i3;
                int length = objArr6.length - i4;
                if (length >= i5) {
                    MapsKt___MapsJvmKt.copyInto(objArr6, objArr6, i4, i3, positiveMod2);
                } else {
                    MapsKt___MapsJvmKt.copyInto(objArr6, objArr6, i4, i3, i3 + length);
                    Object[] objArr7 = this.elementData;
                    MapsKt___MapsJvmKt.copyInto(objArr7, objArr7, 0, this.head + length, positiveMod2);
                }
            }
            this.head = i4;
            int i6 = positiveMod2 - size;
            if (i6 < 0) {
                i6 += this.elementData.length;
            }
            copyCollectionElements(i6, collection);
        } else {
            int i7 = positiveMod2 + size;
            if (positiveMod2 < positiveMod) {
                int i8 = size + positiveMod;
                Object[] objArr8 = this.elementData;
                if (i8 <= objArr8.length) {
                    MapsKt___MapsJvmKt.copyInto(objArr8, objArr8, i7, positiveMod2, positiveMod);
                } else if (i7 >= objArr8.length) {
                    MapsKt___MapsJvmKt.copyInto(objArr8, objArr8, i7 - objArr8.length, positiveMod2, positiveMod);
                } else {
                    int length2 = positiveMod - (i8 - objArr8.length);
                    MapsKt___MapsJvmKt.copyInto(objArr8, objArr8, 0, length2, positiveMod);
                    Object[] objArr9 = this.elementData;
                    MapsKt___MapsJvmKt.copyInto(objArr9, objArr9, i7, positiveMod2, length2);
                }
            } else {
                Object[] objArr10 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr10, objArr10, size, 0, positiveMod);
                Object[] objArr11 = this.elementData;
                if (i7 >= objArr11.length) {
                    MapsKt___MapsJvmKt.copyInto(objArr11, objArr11, i7 - objArr11.length, positiveMod2, objArr11.length);
                } else {
                    MapsKt___MapsJvmKt.copyInto(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.elementData;
                    MapsKt___MapsJvmKt.copyInto(objArr12, objArr12, i7, positiveMod2, objArr12.length - size);
                }
            }
            copyCollectionElements(positiveMod2, collection);
        }
        return true;
    }

    public final void addLast(Object obj) {
        ensureCapacity(getSize() + 1);
        this.elementData[positiveMod(getSize() + this.head)] = obj;
        this.size = getSize() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int positiveMod = positiveMod(this.size + this.head);
        int i = this.head;
        if (i < positiveMod) {
            MapsKt___MapsJvmKt.fill(i, positiveMod, this.elementData);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            MapsKt___MapsJvmKt.fill(this.head, objArr.length, objArr);
            MapsKt___MapsJvmKt.fill(0, positiveMod, this.elementData);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void copyCollectionElements(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.elementData.length;
        while (i < length && it.hasNext()) {
            this.elementData[i] = it.next();
            i++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size = collection.size() + getSize();
    }

    public final void ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.elementData;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == emptyElementData) {
            if (i < 10) {
                i = 10;
            }
            this.elementData = new Object[i];
            return;
        }
        int length = objArr.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i2];
        MapsKt___MapsJvmKt.copyInto(objArr, objArr2, 0, this.head, objArr.length);
        Object[] objArr3 = this.elementData;
        int length2 = objArr3.length;
        int i3 = this.head;
        MapsKt___MapsJvmKt.copyInto(objArr3, objArr2, length2 - i3, 0, i3);
        this.head = 0;
        this.elementData = objArr2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        ULong.Companion.checkElementIndex$kotlin_stdlib(i, this.size);
        return this.elementData[positiveMod(this.head + i)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.size;
    }

    public final int incremented(int i) {
        ResultKt.checkNotNullParameter(this.elementData, "<this>");
        if (i == r0.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int positiveMod = positiveMod(getSize() + this.head);
        int i2 = this.head;
        if (i2 < positiveMod) {
            while (i2 < positiveMod) {
                if (ResultKt.areEqual(obj, this.elementData[i2])) {
                    i = this.head;
                } else {
                    i2++;
                }
            }
            return -1;
        }
        if (i2 < positiveMod) {
            return -1;
        }
        int length = this.elementData.length;
        while (true) {
            if (i2 >= length) {
                for (int i3 = 0; i3 < positiveMod; i3++) {
                    if (ResultKt.areEqual(obj, this.elementData[i3])) {
                        i2 = i3 + this.elementData.length;
                        i = this.head;
                    }
                }
                return -1;
            }
            if (ResultKt.areEqual(obj, this.elementData[i2])) {
                i = this.head;
                break;
            }
            i2++;
        }
        return i2 - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return getSize() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int positiveMod = positiveMod(this.size + this.head);
        int i2 = this.head;
        if (i2 < positiveMod) {
            length = positiveMod - 1;
            if (i2 <= length) {
                while (!ResultKt.areEqual(obj, this.elementData[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.head;
                return length - i;
            }
            return -1;
        }
        if (i2 > positiveMod) {
            int i3 = positiveMod - 1;
            while (true) {
                if (-1 >= i3) {
                    Object[] objArr = this.elementData;
                    ResultKt.checkNotNullParameter(objArr, "<this>");
                    length = objArr.length - 1;
                    int i4 = this.head;
                    if (i4 <= length) {
                        while (!ResultKt.areEqual(obj, this.elementData[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.head;
                    }
                } else {
                    if (ResultKt.areEqual(obj, this.elementData[i3])) {
                        length = i3 + this.elementData.length;
                        i = this.head;
                        break;
                    }
                    i3--;
                }
            }
        }
        return -1;
    }

    public final int positiveMod(int i) {
        Object[] objArr = this.elementData;
        return i >= objArr.length ? i - objArr.length : i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int positiveMod;
        ResultKt.checkNotNullParameter(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int positiveMod2 = positiveMod(this.size + this.head);
            int i = this.head;
            if (i < positiveMod2) {
                positiveMod = i;
                while (i < positiveMod2) {
                    Object obj = this.elementData[i];
                    if (!collection.contains(obj)) {
                        this.elementData[positiveMod] = obj;
                        positiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                MapsKt___MapsJvmKt.fill(positiveMod, positiveMod2, this.elementData);
            } else {
                int length = this.elementData.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (!collection.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                positiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < positiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (!collection.contains(obj3)) {
                        this.elementData[positiveMod] = obj3;
                        positiveMod = incremented(positiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                int i4 = positiveMod - this.head;
                if (i4 < 0) {
                    i4 += this.elementData.length;
                }
                this.size = i4;
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        ULong.Companion.checkElementIndex$kotlin_stdlib(i, this.size);
        if (i == ResultKt.getLastIndex(this)) {
            if (isEmpty()) {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            int positiveMod = positiveMod(ResultKt.getLastIndex(this) + this.head);
            Object[] objArr = this.elementData;
            Object obj = objArr[positiveMod];
            objArr[positiveMod] = null;
            this.size--;
            return obj;
        }
        if (i == 0) {
            return removeFirst();
        }
        int positiveMod2 = positiveMod(this.head + i);
        Object[] objArr2 = this.elementData;
        Object obj2 = objArr2[positiveMod2];
        if (i < (this.size >> 1)) {
            int i2 = this.head;
            if (positiveMod2 >= i2) {
                MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, i2 + 1, i2, positiveMod2);
            } else {
                MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, 1, 0, positiveMod2);
                Object[] objArr3 = this.elementData;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i3 = this.head;
                MapsKt___MapsJvmKt.copyInto(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
            }
            Object[] objArr4 = this.elementData;
            int i4 = this.head;
            objArr4[i4] = null;
            this.head = incremented(i4);
        } else {
            int positiveMod3 = positiveMod(ResultKt.getLastIndex(this) + this.head);
            if (positiveMod2 <= positiveMod3) {
                Object[] objArr5 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr5, objArr5, positiveMod2, positiveMod2 + 1, positiveMod3 + 1);
            } else {
                Object[] objArr6 = this.elementData;
                MapsKt___MapsJvmKt.copyInto(objArr6, objArr6, positiveMod2, positiveMod2 + 1, objArr6.length);
                Object[] objArr7 = this.elementData;
                objArr7[objArr7.length - 1] = objArr7[0];
                MapsKt___MapsJvmKt.copyInto(objArr7, objArr7, 0, 1, positiveMod3 + 1);
            }
            this.elementData[positiveMod3] = null;
        }
        this.size--;
        return obj2;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.elementData;
        int i = this.head;
        Object obj = objArr[i];
        objArr[i] = null;
        this.head = incremented(i);
        this.size = getSize() - 1;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int positiveMod;
        ResultKt.checkNotNullParameter(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int positiveMod2 = positiveMod(this.size + this.head);
            int i = this.head;
            if (i < positiveMod2) {
                positiveMod = i;
                while (i < positiveMod2) {
                    Object obj = this.elementData[i];
                    if (collection.contains(obj)) {
                        this.elementData[positiveMod] = obj;
                        positiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                MapsKt___MapsJvmKt.fill(positiveMod, positiveMod2, this.elementData);
            } else {
                int length = this.elementData.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                positiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < positiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        this.elementData[positiveMod] = obj3;
                        positiveMod = incremented(positiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                int i4 = positiveMod - this.head;
                if (i4 < 0) {
                    i4 += this.elementData.length;
                }
                this.size = i4;
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        ULong.Companion.checkElementIndex$kotlin_stdlib(i, this.size);
        int positiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        Object obj2 = objArr[positiveMod];
        objArr[positiveMod] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[getSize()]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "array");
        int length = objArr.length;
        int i = this.size;
        if (length < i) {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i);
            ResultKt.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) newInstance;
        }
        int positiveMod = positiveMod(this.size + this.head);
        int i2 = this.head;
        if (i2 < positiveMod) {
            MapsKt___MapsJvmKt.copyInto$default(this.elementData, objArr, i2, positiveMod, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.elementData;
            MapsKt___MapsJvmKt.copyInto(objArr2, objArr, 0, this.head, objArr2.length);
            Object[] objArr3 = this.elementData;
            MapsKt___MapsJvmKt.copyInto(objArr3, objArr, objArr3.length - this.head, 0, positiveMod);
        }
        int length2 = objArr.length;
        int i3 = this.size;
        if (length2 > i3) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(collection.size() + getSize());
        copyCollectionElements(positiveMod(getSize() + this.head), collection);
        return true;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }
}
