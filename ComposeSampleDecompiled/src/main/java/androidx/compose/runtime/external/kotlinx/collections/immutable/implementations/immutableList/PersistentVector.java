package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.util.Arrays;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;

/* loaded from: classes.dex */
public final class PersistentVector extends AbstractPersistentList {
    public final Object[] root;
    public final int rootShift;
    public final int size;
    public final Object[] tail;

    public PersistentVector(Object[] objArr, Object[] objArr2, int i, int i2) {
        ResultKt.checkNotNullParameter(objArr, "root");
        ResultKt.checkNotNullParameter(objArr2, "tail");
        this.root = objArr;
        this.tail = objArr2;
        this.size = i;
        this.rootShift = i2;
        if (getSize() > 32) {
            return;
        }
        throw new IllegalArgumentException(("Trie-based persistent vector should have at least 33 elements, got " + getSize()).toString());
    }

    public static Object[] insertIntoRoot(Object[] objArr, int i, int i2, Object obj, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        Object[] copyOf;
        int indexSegment = ResultKt.indexSegment(i2, i);
        if (i == 0) {
            if (indexSegment == 0) {
                copyOf = new Object[32];
            } else {
                copyOf = Arrays.copyOf(objArr, 32);
                ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            }
            MapsKt___MapsJvmKt.copyInto(objArr, copyOf, indexSegment + 1, indexSegment, 31);
            accessibilityNodeProviderCompat.mProvider = objArr[31];
            copyOf[indexSegment] = obj;
            return copyOf;
        }
        Object[] copyOf2 = Arrays.copyOf(objArr, 32);
        ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        int i3 = i - 5;
        Object obj2 = objArr[indexSegment];
        ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        copyOf2[indexSegment] = insertIntoRoot((Object[]) obj2, i3, i2, obj, accessibilityNodeProviderCompat);
        while (true) {
            indexSegment++;
            if (indexSegment >= 32 || copyOf2[indexSegment] == null) {
                break;
            }
            Object obj3 = objArr[indexSegment];
            ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            copyOf2[indexSegment] = insertIntoRoot((Object[]) obj3, i3, 0, accessibilityNodeProviderCompat.mProvider, accessibilityNodeProviderCompat);
        }
        return copyOf2;
    }

    public static Object[] pullLastBuffer(Object[] objArr, int i, int i2, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        Object[] pullLastBuffer;
        int indexSegment = ResultKt.indexSegment(i2, i);
        if (i == 5) {
            accessibilityNodeProviderCompat.mProvider = objArr[indexSegment];
            pullLastBuffer = null;
        } else {
            Object obj = objArr[indexSegment];
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            pullLastBuffer = pullLastBuffer((Object[]) obj, i - 5, i2, accessibilityNodeProviderCompat);
        }
        if (pullLastBuffer == null && indexSegment == 0) {
            return null;
        }
        Object[] copyOf = Arrays.copyOf(objArr, 32);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        copyOf[indexSegment] = pullLastBuffer;
        return copyOf;
    }

    public static Object[] setInRoot(int i, int i2, Object obj, Object[] objArr) {
        int indexSegment = ResultKt.indexSegment(i2, i);
        Object[] copyOf = Arrays.copyOf(objArr, 32);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        if (i == 0) {
            copyOf[indexSegment] = obj;
        } else {
            Object obj2 = copyOf[indexSegment];
            ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            copyOf[indexSegment] = setInRoot(i - 5, i2, obj, (Object[]) obj2);
        }
        return copyOf;
    }

    /* JADX DEBUG: Possible override for method kotlin.collections.AbstractCollection.add(Ljava/lang/Object;)Z */
    @Override // java.util.Collection, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList add(Object obj) {
        int rootSize = rootSize();
        int i = this.size;
        int i2 = i - rootSize;
        Object[] objArr = this.root;
        Object[] objArr2 = this.tail;
        if (i2 < 32) {
            Object[] copyOf = Arrays.copyOf(objArr2, 32);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            copyOf[i2] = obj;
            return new PersistentVector(objArr, copyOf, i + 1, this.rootShift);
        }
        Object[] objArr3 = new Object[32];
        objArr3[0] = obj;
        return pushFilledTail(objArr, objArr2, objArr3);
    }

    /* JADX DEBUG: Method merged with bridge method: builder()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVectorBuilder; */
    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    /* renamed from: builder$1, reason: merged with bridge method [inline-methods] */
    public final PersistentVectorBuilder builder() {
        return new PersistentVectorBuilder(this, this.root, this.tail, this.rootShift);
    }

    @Override // java.util.List
    public final Object get(int i) {
        Object[] objArr;
        _BOUNDARY.checkElementIndex$runtime_release(i, getSize());
        if (rootSize() <= i) {
            objArr = this.tail;
        } else {
            objArr = this.root;
            for (int i2 = this.rootShift; i2 > 0; i2 -= 5) {
                Object obj = objArr[ResultKt.indexSegment(i, i2)];
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArr = (Object[]) obj;
            }
        }
        return objArr[i & 31];
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.size;
    }

    public final PersistentVector insertIntoTail(Object[] objArr, int i, Object obj) {
        int rootSize = rootSize();
        int i2 = this.size;
        int i3 = i2 - rootSize;
        Object[] objArr2 = this.tail;
        Object[] copyOf = Arrays.copyOf(objArr2, 32);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        if (i3 < 32) {
            MapsKt___MapsJvmKt.copyInto(objArr2, copyOf, i + 1, i, i3);
            copyOf[i] = obj;
            return new PersistentVector(objArr, copyOf, i2 + 1, this.rootShift);
        }
        Object obj2 = objArr2[31];
        MapsKt___MapsJvmKt.copyInto(objArr2, copyOf, i + 1, i, i3 - 1);
        copyOf[i] = obj;
        Object[] objArr3 = new Object[32];
        objArr3[0] = obj2;
        return pushFilledTail(objArr, copyOf, objArr3);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        _BOUNDARY.checkPositionIndex$runtime_release(i, getSize());
        return new PersistentVectorIterator(this.root, this.tail, i, getSize(), (this.rootShift / 5) + 1);
    }

    public final PersistentVector pushFilledTail(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i = this.size;
        int i2 = i >> 5;
        int i3 = this.rootShift;
        if (i2 <= (1 << i3)) {
            return new PersistentVector(pushTail(i3, objArr, objArr2), objArr3, i + 1, i3);
        }
        Object[] objArr4 = new Object[32];
        objArr4[0] = objArr;
        int i4 = i3 + 5;
        return new PersistentVector(pushTail(i4, objArr4, objArr2), objArr3, i + 1, i4);
    }

    public final Object[] pushTail(int i, Object[] objArr, Object[] objArr2) {
        Object[] objArr3;
        int indexSegment = ResultKt.indexSegment(getSize() - 1, i);
        if (objArr != null) {
            objArr3 = Arrays.copyOf(objArr, 32);
            ResultKt.checkNotNullExpressionValue(objArr3, "copyOf(this, newSize)");
        } else {
            objArr3 = new Object[32];
        }
        if (i == 5) {
            objArr3[indexSegment] = objArr2;
        } else {
            objArr3[indexSegment] = pushTail(i - 5, (Object[]) objArr3[indexSegment], objArr2);
        }
        return objArr3;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList removeAll(SnapshotStateList$retainAll$1 snapshotStateList$retainAll$1) {
        PersistentVectorBuilder builder = builder();
        builder.removeAllWithPredicate(snapshotStateList$retainAll$1);
        return builder.build();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList removeAt(int i) {
        _BOUNDARY.checkElementIndex$runtime_release(i, this.size);
        int rootSize = rootSize();
        Object[] objArr = this.root;
        int i2 = this.rootShift;
        return i >= rootSize ? removeFromTailAt(objArr, rootSize, i2, i - rootSize) : removeFromTailAt(removeFromRootAt(objArr, i2, i, new AccessibilityNodeProviderCompat(this.tail[0])), rootSize, i2, 0);
    }

    public final Object[] removeFromRootAt(Object[] objArr, int i, int i2, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        Object[] copyOf;
        int indexSegment = ResultKt.indexSegment(i2, i);
        if (i == 0) {
            if (indexSegment == 0) {
                copyOf = new Object[32];
            } else {
                copyOf = Arrays.copyOf(objArr, 32);
                ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            }
            MapsKt___MapsJvmKt.copyInto(objArr, copyOf, indexSegment, indexSegment + 1, 32);
            copyOf[31] = accessibilityNodeProviderCompat.mProvider;
            accessibilityNodeProviderCompat.mProvider = objArr[indexSegment];
            return copyOf;
        }
        int indexSegment2 = objArr[31] == null ? ResultKt.indexSegment(rootSize() - 1, i) : 31;
        Object[] copyOf2 = Arrays.copyOf(objArr, 32);
        ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        int i3 = i - 5;
        int i4 = indexSegment + 1;
        if (i4 <= indexSegment2) {
            while (true) {
                Object obj = copyOf2[indexSegment2];
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                copyOf2[indexSegment2] = removeFromRootAt((Object[]) obj, i3, 0, accessibilityNodeProviderCompat);
                if (indexSegment2 == i4) {
                    break;
                }
                indexSegment2--;
            }
        }
        Object obj2 = copyOf2[indexSegment];
        ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        copyOf2[indexSegment] = removeFromRootAt((Object[]) obj2, i3, i2, accessibilityNodeProviderCompat);
        return copyOf2;
    }

    public final AbstractPersistentList removeFromTailAt(Object[] objArr, int i, int i2, int i3) {
        PersistentVector persistentVector;
        int i4 = this.size - i;
        if (i4 != 1) {
            Object[] objArr2 = this.tail;
            Object[] copyOf = Arrays.copyOf(objArr2, 32);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            int i5 = i4 - 1;
            if (i3 < i5) {
                MapsKt___MapsJvmKt.copyInto(objArr2, copyOf, i3, i3 + 1, i4);
            }
            copyOf[i5] = null;
            return new PersistentVector(objArr, copyOf, (i + i4) - 1, i2);
        }
        if (i2 == 0) {
            if (objArr.length == 33) {
                objArr = Arrays.copyOf(objArr, 32);
                ResultKt.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
            }
            return new SmallPersistentVector(objArr);
        }
        AccessibilityNodeProviderCompat accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(null);
        Object[] pullLastBuffer = pullLastBuffer(objArr, i2, i - 1, accessibilityNodeProviderCompat);
        ResultKt.checkNotNull(pullLastBuffer);
        Object obj = accessibilityNodeProviderCompat.mProvider;
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr3 = (Object[]) obj;
        if (pullLastBuffer[1] == null) {
            Object obj2 = pullLastBuffer[0];
            ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            persistentVector = new PersistentVector((Object[]) obj2, objArr3, i, i2 - 5);
        } else {
            persistentVector = new PersistentVector(pullLastBuffer, objArr3, i, i2);
        }
        return persistentVector;
    }

    public final int rootSize() {
        return (this.size - 1) & (-32);
    }

    @Override // kotlin.collections.AbstractList, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList set(int i, Object obj) {
        int i2 = this.size;
        _BOUNDARY.checkElementIndex$runtime_release(i, i2);
        int rootSize = rootSize();
        Object[] objArr = this.root;
        Object[] objArr2 = this.tail;
        int i3 = this.rootShift;
        if (rootSize > i) {
            return new PersistentVector(setInRoot(i3, i, obj, objArr), objArr2, i2, i3);
        }
        Object[] copyOf = Arrays.copyOf(objArr2, 32);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        copyOf[i & 31] = obj;
        return new PersistentVector(objArr, copyOf, i2, i3);
    }

    /* JADX DEBUG: Possible override for method kotlin.collections.AbstractList.add(ILjava/lang/Object;)V */
    @Override // java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public final PersistentList add(int i, Object obj) {
        int i2 = this.size;
        _BOUNDARY.checkPositionIndex$runtime_release(i, i2);
        if (i == i2) {
            return add(obj);
        }
        int rootSize = rootSize();
        Object[] objArr = this.root;
        if (i >= rootSize) {
            return insertIntoTail(objArr, i - rootSize, obj);
        }
        AccessibilityNodeProviderCompat accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(null);
        return insertIntoTail(insertIntoRoot(objArr, this.rootShift, i, obj, accessibilityNodeProviderCompat), 0, accessibilityNodeProviderCompat.mProvider);
    }
}
