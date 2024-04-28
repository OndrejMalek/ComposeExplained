package androidx.collection;

import androidx.collection.ArrayMap;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class MapCollections {
    public KeySet mEntrySet;
    public KeySet mKeySet;
    public ValuesCollection mValues;

    /* loaded from: classes.dex */
    public final class ArrayIterator implements Iterator {
        public boolean mCanRemove = false;
        public int mIndex;
        public final int mOffset;
        public int mSize;

        public ArrayIterator(int i) {
            this.mOffset = i;
            this.mSize = MapCollections.this.colGetSize();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.mIndex < this.mSize;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object colGetEntry = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
            this.mIndex++;
            this.mCanRemove = true;
            return colGetEntry;
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.mCanRemove) {
                throw new IllegalStateException();
            }
            int i = this.mIndex - 1;
            this.mIndex = i;
            this.mSize--;
            this.mCanRemove = false;
            MapCollections.this.colRemoveAt(i);
        }
    }

    /* loaded from: classes.dex */
    public final class MapIterator implements Iterator, Map.Entry {
        public int mEnd;
        public boolean mEntryValid = false;
        public int mIndex = -1;

        public MapIterator() {
            this.mEnd = MapCollections.this.colGetSize() - 1;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            int i = this.mIndex;
            MapCollections mapCollections = MapCollections.this;
            Object colGetEntry = mapCollections.colGetEntry(i, 0);
            if (key != colGetEntry && (key == null || !key.equals(colGetEntry))) {
                return false;
            }
            Object value = entry.getValue();
            Object colGetEntry2 = mapCollections.colGetEntry(this.mIndex, 1);
            return value == colGetEntry2 || (value != null && value.equals(colGetEntry2));
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colGetEntry(this.mIndex, 0);
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colGetEntry(this.mIndex, 1);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.mIndex < this.mEnd;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            int i = this.mIndex;
            MapCollections mapCollections = MapCollections.this;
            Object colGetEntry = mapCollections.colGetEntry(i, 0);
            Object colGetEntry2 = mapCollections.colGetEntry(this.mIndex, 1);
            return (colGetEntry == null ? 0 : colGetEntry.hashCode()) ^ (colGetEntry2 != null ? colGetEntry2.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.mIndex++;
            this.mEntryValid = true;
            return this;
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.mEntryValid) {
                throw new IllegalStateException();
            }
            MapCollections.this.colRemoveAt(this.mIndex);
            this.mIndex--;
            this.mEnd--;
            this.mEntryValid = false;
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            int i = this.mIndex;
            ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) MapCollections.this;
            switch (anonymousClass1.$r8$classId) {
                case 0:
                    int i2 = (i << 1) + 1;
                    Object[] objArr = ((ArrayMap) anonymousClass1.this$0).mArray;
                    Object obj2 = objArr[i2];
                    objArr[i2] = obj;
                    return obj2;
                default:
                    throw new UnsupportedOperationException("not a map");
            }
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* loaded from: classes.dex */
    public final class ValuesCollection implements Collection {
        public ValuesCollection() {
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final void clear() {
            ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) MapCollections.this;
            int i = anonymousClass1.$r8$classId;
            Object obj = anonymousClass1.this$0;
            switch (i) {
                case 0:
                    ((ArrayMap) obj).clear();
                    return;
                default:
                    ((ArraySet) obj).clear();
                    return;
            }
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            int indexOfValue;
            ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) MapCollections.this;
            int i = anonymousClass1.$r8$classId;
            Object obj2 = anonymousClass1.this$0;
            switch (i) {
                case 0:
                    indexOfValue = ((ArrayMap) obj2).indexOfValue(obj);
                    break;
                default:
                    indexOfValue = ((ArraySet) obj2).indexOf(obj);
                    break;
            }
            return indexOfValue >= 0;
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new ArrayIterator(1);
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            int indexOfValue;
            MapCollections mapCollections = MapCollections.this;
            ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) mapCollections;
            int i = anonymousClass1.$r8$classId;
            Object obj2 = anonymousClass1.this$0;
            switch (i) {
                case 0:
                    indexOfValue = ((ArrayMap) obj2).indexOfValue(obj);
                    break;
                default:
                    indexOfValue = ((ArraySet) obj2).indexOf(obj);
                    break;
            }
            if (indexOfValue < 0) {
                return false;
            }
            mapCollections.colRemoveAt(indexOfValue);
            return true;
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection collection) {
            MapCollections mapCollections = MapCollections.this;
            int colGetSize = mapCollections.colGetSize();
            int i = 0;
            boolean z = false;
            while (i < colGetSize) {
                if (collection.contains(mapCollections.colGetEntry(i, 1))) {
                    mapCollections.colRemoveAt(i);
                    i--;
                    colGetSize--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection collection) {
            MapCollections mapCollections = MapCollections.this;
            int colGetSize = mapCollections.colGetSize();
            int i = 0;
            boolean z = false;
            while (i < colGetSize) {
                if (!collection.contains(mapCollections.colGetEntry(i, 1))) {
                    mapCollections.colRemoveAt(i);
                    i--;
                    colGetSize--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        @Override // java.util.Collection
        public final int size() {
            return MapCollections.this.colGetSize();
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            return MapCollections.this.toArrayHelper(objArr, 1);
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            MapCollections mapCollections = MapCollections.this;
            int colGetSize = mapCollections.colGetSize();
            Object[] objArr = new Object[colGetSize];
            for (int i = 0; i < colGetSize; i++) {
                objArr[i] = mapCollections.colGetEntry(i, 1);
            }
            return objArr;
        }
    }

    public static boolean equalsSetHelper(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public abstract Object colGetEntry(int i, int i2);

    public abstract int colGetSize();

    public abstract void colRemoveAt(int i);

    public final Object[] toArrayHelper(Object[] objArr, int i) {
        int colGetSize = colGetSize();
        if (objArr.length < colGetSize) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), colGetSize);
        }
        for (int i2 = 0; i2 < colGetSize; i2++) {
            objArr[i2] = colGetEntry(i2, i);
        }
        if (objArr.length > colGetSize) {
            objArr[colGetSize] = null;
        }
        return objArr;
    }

    /* loaded from: classes.dex */
    public final class KeySet implements Set {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ MapCollections this$0;

        public /* synthetic */ KeySet(MapCollections mapCollections, int i) {
            this.$r8$classId = i;
            this.this$0 = mapCollections;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    throw new UnsupportedOperationException();
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection collection) {
            switch (this.$r8$classId) {
                case 0:
                    throw new UnsupportedOperationException();
                default:
                    MapCollections mapCollections = this.this$0;
                    int colGetSize = mapCollections.colGetSize();
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) mapCollections;
                        int i = anonymousClass1.$r8$classId;
                        Object obj = anonymousClass1.this$0;
                        switch (i) {
                            case 0:
                                ((ArrayMap) obj).put(key, value);
                                break;
                            default:
                                ((ArraySet) obj).add(key);
                                break;
                        }
                    }
                    return colGetSize != mapCollections.colGetSize();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            int i = this.$r8$classId;
            MapCollections mapCollections = this.this$0;
            switch (i) {
                case 0:
                    ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) mapCollections;
                    int i2 = anonymousClass1.$r8$classId;
                    Object obj = anonymousClass1.this$0;
                    switch (i2) {
                        case 0:
                            ((ArrayMap) obj).clear();
                            return;
                        default:
                            ((ArraySet) obj).clear();
                            return;
                    }
                default:
                    ArrayMap.AnonymousClass1 anonymousClass12 = (ArrayMap.AnonymousClass1) mapCollections;
                    int i3 = anonymousClass12.$r8$classId;
                    Object obj2 = anonymousClass12.this$0;
                    switch (i3) {
                        case 0:
                            ((ArrayMap) obj2).clear();
                            return;
                        default:
                            ((ArraySet) obj2).clear();
                            return;
                    }
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            int indexOfKey;
            int indexOfKey2;
            int i = this.$r8$classId;
            boolean z = true;
            MapCollections mapCollections = this.this$0;
            switch (i) {
                case 0:
                    ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) mapCollections;
                    int i2 = anonymousClass1.$r8$classId;
                    Object obj2 = anonymousClass1.this$0;
                    switch (i2) {
                        case 0:
                            indexOfKey = ((ArrayMap) obj2).indexOfKey(obj);
                            break;
                        default:
                            indexOfKey = ((ArraySet) obj2).indexOf(obj);
                            break;
                    }
                    return indexOfKey >= 0;
                default:
                    if (!(obj instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry entry = (Map.Entry) obj;
                    Object key = entry.getKey();
                    ArrayMap.AnonymousClass1 anonymousClass12 = (ArrayMap.AnonymousClass1) mapCollections;
                    int i3 = anonymousClass12.$r8$classId;
                    Object obj3 = anonymousClass12.this$0;
                    switch (i3) {
                        case 0:
                            indexOfKey2 = ((ArrayMap) obj3).indexOfKey(key);
                            break;
                        default:
                            indexOfKey2 = ((ArraySet) obj3).indexOf(key);
                            break;
                    }
                    if (indexOfKey2 < 0) {
                        return false;
                    }
                    Object colGetEntry = mapCollections.colGetEntry(indexOfKey2, 1);
                    Object value = entry.getValue();
                    if (colGetEntry != value && (colGetEntry == null || !colGetEntry.equals(value))) {
                        z = false;
                    }
                    return z;
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection collection) {
            switch (this.$r8$classId) {
                case 0:
                    ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) this.this$0;
                    switch (anonymousClass1.$r8$classId) {
                        case 0:
                            ArrayMap arrayMap = (ArrayMap) anonymousClass1.this$0;
                            Iterator it = collection.iterator();
                            while (it.hasNext()) {
                                if (!arrayMap.containsKey(it.next())) {
                                    return false;
                                }
                            }
                            return true;
                        default:
                            throw new UnsupportedOperationException("not a map");
                    }
                default:
                    Iterator it2 = collection.iterator();
                    while (it2.hasNext()) {
                        if (!contains(it2.next())) {
                            return false;
                        }
                    }
                    return true;
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    return MapCollections.equalsSetHelper(this, obj);
                default:
                    return MapCollections.equalsSetHelper(this, obj);
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            int i = this.$r8$classId;
            MapCollections mapCollections = this.this$0;
            switch (i) {
                case 0:
                    int i2 = 0;
                    for (int colGetSize = mapCollections.colGetSize() - 1; colGetSize >= 0; colGetSize--) {
                        Object colGetEntry = mapCollections.colGetEntry(colGetSize, 0);
                        i2 += colGetEntry == null ? 0 : colGetEntry.hashCode();
                    }
                    return i2;
                default:
                    int i3 = 0;
                    for (int colGetSize2 = mapCollections.colGetSize() - 1; colGetSize2 >= 0; colGetSize2--) {
                        Object colGetEntry2 = mapCollections.colGetEntry(colGetSize2, 0);
                        Object colGetEntry3 = mapCollections.colGetEntry(colGetSize2, 1);
                        i3 += (colGetEntry2 == null ? 0 : colGetEntry2.hashCode()) ^ (colGetEntry3 == null ? 0 : colGetEntry3.hashCode());
                    }
                    return i3;
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            int i = this.$r8$classId;
            MapCollections mapCollections = this.this$0;
            switch (i) {
                case 0:
                    return mapCollections.colGetSize() == 0;
                default:
                    return mapCollections.colGetSize() == 0;
            }
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            int i = this.$r8$classId;
            MapCollections mapCollections = this.this$0;
            switch (i) {
                case 0:
                    return new ArrayIterator(0);
                default:
                    return new MapIterator();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            int indexOfKey;
            switch (this.$r8$classId) {
                case 0:
                    MapCollections mapCollections = this.this$0;
                    ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) mapCollections;
                    int i = anonymousClass1.$r8$classId;
                    Object obj2 = anonymousClass1.this$0;
                    switch (i) {
                        case 0:
                            indexOfKey = ((ArrayMap) obj2).indexOfKey(obj);
                            break;
                        default:
                            indexOfKey = ((ArraySet) obj2).indexOf(obj);
                            break;
                    }
                    if (indexOfKey < 0) {
                        return false;
                    }
                    mapCollections.colRemoveAt(indexOfKey);
                    return true;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection collection) {
            switch (this.$r8$classId) {
                case 0:
                    ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) this.this$0;
                    switch (anonymousClass1.$r8$classId) {
                        case 0:
                            ArrayMap arrayMap = (ArrayMap) anonymousClass1.this$0;
                            int i = arrayMap.mSize;
                            Iterator it = collection.iterator();
                            while (it.hasNext()) {
                                arrayMap.remove(it.next());
                            }
                            return i != arrayMap.mSize;
                        default:
                            throw new UnsupportedOperationException("not a map");
                    }
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection collection) {
            switch (this.$r8$classId) {
                case 0:
                    ArrayMap.AnonymousClass1 anonymousClass1 = (ArrayMap.AnonymousClass1) this.this$0;
                    switch (anonymousClass1.$r8$classId) {
                        case 0:
                            ArrayMap arrayMap = (ArrayMap) anonymousClass1.this$0;
                            int i = arrayMap.mSize;
                            Iterator it = ((KeySet) arrayMap.keySet()).iterator();
                            while (it.hasNext()) {
                                if (!collection.contains(it.next())) {
                                    it.remove();
                                }
                            }
                            return i != arrayMap.mSize;
                        default:
                            throw new UnsupportedOperationException("not a map");
                    }
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            int i = this.$r8$classId;
            MapCollections mapCollections = this.this$0;
            switch (i) {
                case 0:
                    return mapCollections.colGetSize();
                default:
                    return mapCollections.colGetSize();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            switch (this.$r8$classId) {
                case 0:
                    MapCollections mapCollections = this.this$0;
                    int colGetSize = mapCollections.colGetSize();
                    Object[] objArr = new Object[colGetSize];
                    for (int i = 0; i < colGetSize; i++) {
                        objArr[i] = mapCollections.colGetEntry(i, 0);
                    }
                    return objArr;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            switch (this.$r8$classId) {
                case 0:
                    return this.this$0.toArrayHelper(objArr, 0);
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
