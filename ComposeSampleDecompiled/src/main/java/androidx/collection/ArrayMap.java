package androidx.collection;

import androidx.collection.MapCollections;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class ArrayMap extends SimpleArrayMap implements Map {
    public AnonymousClass1 mCollections;

    /* renamed from: androidx.collection.ArrayMap$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends MapCollections {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // androidx.collection.MapCollections
        public final Object colGetEntry(int i, int i2) {
            int i3 = this.$r8$classId;
            Object obj = this.this$0;
            switch (i3) {
                case 0:
                    return ((ArrayMap) obj).mArray[(i << 1) + i2];
                default:
                    return ((ArraySet) obj).mArray[i];
            }
        }

        @Override // androidx.collection.MapCollections
        public final int colGetSize() {
            int i = this.$r8$classId;
            Object obj = this.this$0;
            switch (i) {
                case 0:
                    return ((ArrayMap) obj).mSize;
                default:
                    return ((ArraySet) obj).mSize;
            }
        }

        @Override // androidx.collection.MapCollections
        public final void colRemoveAt(int i) {
            int i2 = this.$r8$classId;
            Object obj = this.this$0;
            switch (i2) {
                case 0:
                    ((ArrayMap) obj).removeAt(i);
                    return;
                default:
                    ((ArraySet) obj).removeAt(i);
                    return;
            }
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.mCollections == null) {
            this.mCollections = new AnonymousClass1(0, this);
        }
        AnonymousClass1 anonymousClass1 = this.mCollections;
        if (anonymousClass1.mEntrySet == null) {
            anonymousClass1.mEntrySet = new MapCollections.KeySet(anonymousClass1, 1);
        }
        return anonymousClass1.mEntrySet;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.mCollections == null) {
            this.mCollections = new AnonymousClass1(0, this);
        }
        AnonymousClass1 anonymousClass1 = this.mCollections;
        if (anonymousClass1.mKeySet == null) {
            anonymousClass1.mKeySet = new MapCollections.KeySet(anonymousClass1, 0);
        }
        return anonymousClass1.mKeySet;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        int size = map.size() + this.mSize;
        int i = this.mSize;
        int[] iArr = this.mHashes;
        if (iArr.length < size) {
            Object[] objArr = this.mArray;
            allocArrays(size);
            if (this.mSize > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i);
                System.arraycopy(objArr, 0, this.mArray, 0, i << 1);
            }
            SimpleArrayMap.freeArrays(iArr, objArr, i);
        }
        if (this.mSize != i) {
            throw new ConcurrentModificationException();
        }
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.mCollections == null) {
            this.mCollections = new AnonymousClass1(0, this);
        }
        AnonymousClass1 anonymousClass1 = this.mCollections;
        if (anonymousClass1.mValues == null) {
            anonymousClass1.mValues = new MapCollections.ValuesCollection();
        }
        return anonymousClass1.mValues;
    }
}
