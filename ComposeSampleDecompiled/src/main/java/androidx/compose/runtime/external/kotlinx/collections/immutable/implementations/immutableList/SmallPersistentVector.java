package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;

/* loaded from: classes.dex */
public final class SmallPersistentVector extends AbstractPersistentList implements ImmutableList {
    public static final SmallPersistentVector EMPTY = new SmallPersistentVector(new Object[0]);
    public final Object[] buffer;

    public SmallPersistentVector(Object[] objArr) {
        this.buffer = objArr;
    }

    /* JADX DEBUG: Possible override for method kotlin.collections.AbstractList.add(ILjava/lang/Object;)V */
    @Override // java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList add(int i, Object obj) {
        Object[] objArr = this.buffer;
        _BOUNDARY.checkPositionIndex$runtime_release(i, objArr.length);
        if (i == objArr.length) {
            return add(obj);
        }
        if (objArr.length < 32) {
            Object[] objArr2 = new Object[objArr.length + 1];
            MapsKt___MapsJvmKt.copyInto$default(objArr, objArr2, 0, i, 6);
            MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i + 1, i, objArr.length);
            objArr2[i] = obj;
            return new SmallPersistentVector(objArr2);
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        MapsKt___MapsJvmKt.copyInto(objArr, copyOf, i + 1, i, objArr.length - 1);
        copyOf[i] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = objArr[31];
        return new PersistentVector(copyOf, objArr3, objArr.length + 1, 0);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList, java.util.Collection, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList addAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        Object[] objArr = this.buffer;
        if (collection.size() + objArr.length > 32) {
            PersistentVectorBuilder builder = builder();
            builder.addAll(collection);
            return builder.build();
        }
        Object[] copyOf = Arrays.copyOf(objArr, collection.size() + objArr.length);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        int length = objArr.length;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            copyOf[length] = it.next();
            length++;
        }
        return new SmallPersistentVector(copyOf);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentVectorBuilder builder() {
        return new PersistentVectorBuilder(this, null, this.buffer, 0);
    }

    @Override // java.util.List
    public final Object get(int i) {
        _BOUNDARY.checkElementIndex$runtime_release(i, getSize());
        return this.buffer[i];
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.buffer.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        return MapsKt___MapsJvmKt.indexOf(this.buffer, obj);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        Object[] objArr = this.buffer;
        ResultKt.checkNotNullParameter(objArr, "<this>");
        if (obj == null) {
            int length = objArr.length - 1;
            if (length < 0) {
                return -1;
            }
            while (true) {
                int i = length - 1;
                if (objArr[length] == null) {
                    return length;
                }
                if (i < 0) {
                    return -1;
                }
                length = i;
            }
        } else {
            int length2 = objArr.length - 1;
            if (length2 < 0) {
                return -1;
            }
            while (true) {
                int i2 = length2 - 1;
                if (ResultKt.areEqual(obj, objArr[length2])) {
                    return length2;
                }
                if (i2 < 0) {
                    return -1;
                }
                length2 = i2;
            }
        }
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        _BOUNDARY.checkPositionIndex$runtime_release(i, getSize());
        return new BufferIterator(i, getSize(), this.buffer);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList removeAll(SnapshotStateList$retainAll$1 snapshotStateList$retainAll$1) {
        Object[] objArr = this.buffer;
        int length = objArr.length;
        int length2 = objArr.length;
        Object[] objArr2 = objArr;
        boolean z = false;
        for (int i = 0; i < length2; i++) {
            Object obj = objArr[i];
            if (((Boolean) snapshotStateList$retainAll$1.invoke(obj)).booleanValue()) {
                if (!z) {
                    objArr2 = Arrays.copyOf(objArr, objArr.length);
                    ResultKt.checkNotNullExpressionValue(objArr2, "copyOf(this, size)");
                    z = true;
                    length = i;
                }
            } else if (z) {
                objArr2[length] = obj;
                length++;
            }
        }
        return length == objArr.length ? this : length == 0 ? EMPTY : new SmallPersistentVector(MapsKt___MapsJvmKt.copyOfRange(0, length, objArr2));
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList removeAt(int i) {
        Object[] objArr = this.buffer;
        _BOUNDARY.checkElementIndex$runtime_release(i, objArr.length);
        if (objArr.length == 1) {
            return EMPTY;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length - 1);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        MapsKt___MapsJvmKt.copyInto(objArr, copyOf, i, i + 1, objArr.length);
        return new SmallPersistentVector(copyOf);
    }

    @Override // kotlin.collections.AbstractList, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList set(int i, Object obj) {
        _BOUNDARY.checkElementIndex$runtime_release(i, getSize());
        Object[] objArr = this.buffer;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        copyOf[i] = obj;
        return new SmallPersistentVector(copyOf);
    }

    /* JADX DEBUG: Possible override for method kotlin.collections.AbstractCollection.add(Ljava/lang/Object;)Z */
    @Override // java.util.Collection, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList add(Object obj) {
        Object[] objArr = this.buffer;
        if (objArr.length < 32) {
            Object[] copyOf = Arrays.copyOf(objArr, objArr.length + 1);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            copyOf[objArr.length] = obj;
            return new SmallPersistentVector(copyOf);
        }
        Object[] objArr2 = new Object[32];
        objArr2[0] = obj;
        return new PersistentVector(objArr, objArr2, objArr.length + 1, 0);
    }
}
