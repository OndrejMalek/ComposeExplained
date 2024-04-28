package androidx.compose.runtime.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class IdentityArraySet implements Set, KMappedMarker {
    public int size;
    public Object[] values = new Object[16];

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        int i;
        ResultKt.checkNotNullParameter(obj, "value");
        int i2 = this.size;
        Object[] objArr = this.values;
        if (i2 > 0) {
            i = find(obj);
            if (i >= 0) {
                return false;
            }
        } else {
            i = -1;
        }
        int i3 = -(i + 1);
        if (i2 == objArr.length) {
            Object[] objArr2 = new Object[objArr.length * 2];
            MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i3 + 1, i3, i2);
            MapsKt___MapsJvmKt.copyInto$default(objArr, objArr2, 0, i3, 6);
            this.values = objArr2;
        } else {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, i3 + 1, i3, i2);
        }
        this.values[i3] = obj;
        this.size++;
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        MapsKt___MapsJvmKt.fill(0, r0.length, this.values);
        this.size = 0;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return obj != null && find(obj) >= 0;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        Collection collection2 = collection;
        if (collection2.isEmpty()) {
            return true;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int find(Object obj) {
        int i = this.size - 1;
        int identityHashCode = System.identityHashCode(obj);
        Object[] objArr = this.values;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj2 = objArr[i3];
            int identityHashCode2 = System.identityHashCode(obj2);
            if (identityHashCode2 < identityHashCode) {
                i2 = i3 + 1;
            } else {
                if (identityHashCode2 <= identityHashCode) {
                    if (obj2 == obj) {
                        return i3;
                    }
                    Object[] objArr2 = this.values;
                    int i4 = this.size;
                    for (int i5 = i3 - 1; -1 < i5; i5--) {
                        Object obj3 = objArr2[i5];
                        if (obj3 == obj) {
                            return i5;
                        }
                        if (System.identityHashCode(obj3) != identityHashCode) {
                            break;
                        }
                    }
                    for (int i6 = i3 + 1; i6 < i4; i6++) {
                        Object obj4 = objArr2[i6];
                        if (obj4 == obj) {
                            return i6;
                        }
                        if (System.identityHashCode(obj4) != identityHashCode) {
                            return -(i6 + 1);
                        }
                    }
                    return -(i4 + 1);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isNotEmpty() {
        return this.size > 0;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new ArrayIterator(1, this);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        int find = find(obj);
        Object[] objArr = this.values;
        int i = this.size;
        if (find < 0) {
            return false;
        }
        int i2 = i - 1;
        if (find < i2) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, find, find + 1, i);
        }
        objArr[i2] = null;
        this.size--;
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.size;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public final String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(this, null, "[", "]", IdentityArraySet$toString$1.INSTANCE, 25);
    }

    @Override // java.util.Set, java.util.Collection
    public final void addAll(Collection collection) {
        Object[] objArr;
        int i;
        Object obj;
        ResultKt.checkNotNullParameter(collection, "collection");
        if (collection.isEmpty()) {
            return;
        }
        if (!(collection instanceof IdentityArraySet)) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                add(it.next());
            }
            return;
        }
        Object[] objArr2 = this.values;
        IdentityArraySet identityArraySet = (IdentityArraySet) collection;
        Object[] objArr3 = identityArraySet.values;
        int i2 = this.size;
        int i3 = identityArraySet.size;
        int i4 = i2 + i3;
        boolean z = objArr2.length < i4;
        boolean z2 = i2 == 0 || System.identityHashCode(objArr2[i2 + (-1)]) < System.identityHashCode(objArr3[0]);
        if (!z && z2) {
            MapsKt___MapsJvmKt.copyInto(objArr3, objArr2, i2, 0, i3);
            this.size += i3;
            return;
        }
        if (z) {
            objArr = new Object[i2 > i3 ? i2 * 2 : i3 * 2];
        } else {
            objArr = objArr2;
        }
        int i5 = i2 - 1;
        int i6 = i3 - 1;
        int i7 = i4 - 1;
        while (true) {
            if (i5 < 0 && i6 < 0) {
                break;
            }
            if (i5 < 0) {
                i = i6 - 1;
                obj = objArr3[i6];
            } else if (i6 < 0) {
                i = i6;
                obj = objArr2[i5];
                i5--;
            } else {
                Object obj2 = objArr2[i5];
                Object obj3 = objArr3[i6];
                int identityHashCode = System.identityHashCode(obj2);
                int identityHashCode2 = System.identityHashCode(obj3);
                if (identityHashCode > identityHashCode2) {
                    i5--;
                } else {
                    if (identityHashCode >= identityHashCode2) {
                        if (obj2 != obj3) {
                            int i8 = i5 - 1;
                            while (i8 >= 0) {
                                int i9 = i8 - 1;
                                Object obj4 = objArr2[i8];
                                if (System.identityHashCode(obj4) != identityHashCode2) {
                                    break;
                                }
                                if (obj3 == obj4) {
                                    i6--;
                                    break;
                                }
                                i8 = i9;
                            }
                        } else {
                            i5--;
                            i6--;
                        }
                    }
                    i = i6 - 1;
                    obj = obj3;
                }
                i = i6;
                obj = obj2;
            }
            objArr[i7] = obj;
            i6 = i;
            i7--;
        }
        if (i7 >= 0) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, 0, i7 + 1, i4);
        }
        int i10 = i4 - (i7 + 1);
        MapsKt___MapsJvmKt.fill(i10, i4, objArr);
        this.values = objArr;
        this.size = i10;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "array");
        return CollectionToArray.toArray(this, objArr);
    }
}
