package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import _COROUTINE.ArtificialStackFrames;
import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.collections.AbstractCollection;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.internal.ArrayIterator;

/* loaded from: classes.dex */
public final class PersistentVectorBuilder extends AbstractMutableList implements Collection {
    public ArtificialStackFrames ownership;
    public Object[] root;
    public int rootShift;
    public int size;
    public Object[] tail;
    public PersistentList vector;
    public Object[] vectorRoot;
    public Object[] vectorTail;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList */
    /* JADX WARN: Multi-variable type inference failed */
    public PersistentVectorBuilder(PersistentList persistentList, Object[] objArr, Object[] objArr2, int i) {
        ResultKt.checkNotNullParameter(persistentList, "vector");
        ResultKt.checkNotNullParameter(objArr2, "vectorTail");
        this.vector = persistentList;
        this.vectorRoot = objArr;
        this.vectorTail = objArr2;
        this.rootShift = i;
        this.ownership = new ArtificialStackFrames(23);
        this.root = objArr;
        this.tail = objArr2;
        this.size = ((AbstractCollection) persistentList).size();
    }

    public static void copyToBuffer(Object[] objArr, int i, Iterator it) {
        while (i < 32 && it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        _BOUNDARY.checkPositionIndex$runtime_release(i, getSize());
        if (i == getSize()) {
            add(obj);
            return;
        }
        ((AbstractList) this).modCount++;
        int rootSize$1 = rootSize$1();
        if (i >= rootSize$1) {
            insertIntoTail(this.root, i - rootSize$1, obj);
            return;
        }
        AccessibilityNodeProviderCompat accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(null);
        Object[] objArr = this.root;
        ResultKt.checkNotNull(objArr);
        insertIntoTail(insertIntoRoot$1(objArr, this.rootShift, i, obj, accessibilityNodeProviderCompat), 0, accessibilityNodeProviderCompat.mProvider);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        Object[] mutableBuffer;
        ResultKt.checkNotNullParameter(collection, "elements");
        _BOUNDARY.checkPositionIndex$runtime_release(i, this.size);
        if (i == this.size) {
            return addAll(collection);
        }
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i2 = (i >> 5) << 5;
        int size = ((collection.size() + (this.size - i2)) - 1) / 32;
        if (size == 0) {
            int i3 = i & 31;
            int size2 = ((collection.size() + i) - 1) & 31;
            Object[] objArr = this.tail;
            Object[] makeMutable = makeMutable(objArr);
            MapsKt___MapsJvmKt.copyInto(objArr, makeMutable, size2 + 1, i3, tailSize());
            copyToBuffer(makeMutable, i3, collection.iterator());
            this.tail = makeMutable;
            this.size = collection.size() + this.size;
            return true;
        }
        Object[][] objArr2 = new Object[size];
        int tailSize = tailSize();
        int size3 = collection.size() + this.size;
        if (size3 > 32) {
            size3 -= (size3 - 1) & (-32);
        }
        if (i >= rootSize$1()) {
            mutableBuffer = mutableBuffer();
            splitToBuffers(collection, i, this.tail, tailSize, objArr2, size, mutableBuffer);
        } else if (size3 > tailSize) {
            int i4 = size3 - tailSize;
            mutableBuffer = makeMutableShiftingRight(this.tail, i4);
            insertIntoRoot(collection, i, i4, objArr2, size, mutableBuffer);
        } else {
            Object[] objArr3 = this.tail;
            mutableBuffer = mutableBuffer();
            int i5 = tailSize - size3;
            MapsKt___MapsJvmKt.copyInto(objArr3, mutableBuffer, 0, i5, tailSize);
            int i6 = 32 - i5;
            Object[] makeMutableShiftingRight = makeMutableShiftingRight(this.tail, i6);
            int i7 = size - 1;
            objArr2[i7] = makeMutableShiftingRight;
            insertIntoRoot(collection, i, i6, objArr2, i7, makeMutableShiftingRight);
        }
        this.root = pushBuffersIncreasingHeightIfNeeded(this.root, i2, objArr2);
        this.tail = mutableBuffer;
        this.size = collection.size() + this.size;
        return true;
    }

    public final PersistentList build() {
        PersistentList persistentVector;
        Object[] objArr = this.root;
        if (objArr == this.vectorRoot && this.tail == this.vectorTail) {
            persistentVector = this.vector;
        } else {
            this.ownership = new ArtificialStackFrames(23);
            this.vectorRoot = objArr;
            Object[] objArr2 = this.tail;
            this.vectorTail = objArr2;
            if (objArr != null) {
                Object[] objArr3 = this.root;
                ResultKt.checkNotNull(objArr3);
                persistentVector = new PersistentVector(objArr3, this.tail, getSize(), this.rootShift);
            } else if (objArr2.length == 0) {
                persistentVector = SmallPersistentVector.EMPTY;
            } else {
                Object[] copyOf = Arrays.copyOf(this.tail, getSize());
                ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                persistentVector = new SmallPersistentVector(copyOf);
            }
        }
        this.vector = persistentVector;
        return persistentVector;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        Object[] objArr;
        _BOUNDARY.checkElementIndex$runtime_release(i, getSize());
        if (rootSize$1() <= i) {
            objArr = this.tail;
        } else {
            objArr = this.root;
            ResultKt.checkNotNull(objArr);
            for (int i2 = this.rootShift; i2 > 0; i2 -= 5) {
                Object obj = objArr[ResultKt.indexSegment(i, i2)];
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArr = (Object[]) obj;
            }
        }
        return objArr[i & 31];
    }

    public final int getModCount$runtime_release() {
        return ((AbstractList) this).modCount;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.size;
    }

    public final void insertIntoRoot(Collection collection, int i, int i2, Object[][] objArr, int i3, Object[] objArr2) {
        if (this.root == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int i4 = i >> 5;
        AbstractListIterator leafBufferIterator = leafBufferIterator(rootSize$1() >> 5);
        int i5 = i3;
        Object[] objArr3 = objArr2;
        while (leafBufferIterator.index - 1 != i4) {
            Object[] objArr4 = (Object[]) leafBufferIterator.previous();
            MapsKt___MapsJvmKt.copyInto(objArr4, objArr3, 0, 32 - i2, 32);
            objArr3 = makeMutableShiftingRight(objArr4, i2);
            i5--;
            objArr[i5] = objArr3;
        }
        Object[] objArr5 = (Object[]) leafBufferIterator.previous();
        int rootSize$1 = i3 - (((rootSize$1() >> 5) - 1) - i4);
        if (rootSize$1 < i3) {
            objArr2 = objArr[rootSize$1];
            ResultKt.checkNotNull(objArr2);
        }
        splitToBuffers(collection, i, objArr5, 32, objArr, rootSize$1, objArr2);
    }

    public final Object[] insertIntoRoot$1(Object[] objArr, int i, int i2, Object obj, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        Object obj2;
        int indexSegment = ResultKt.indexSegment(i2, i);
        if (i == 0) {
            accessibilityNodeProviderCompat.mProvider = objArr[31];
            Object[] makeMutable = makeMutable(objArr);
            MapsKt___MapsJvmKt.copyInto(objArr, makeMutable, indexSegment + 1, indexSegment, 31);
            makeMutable[indexSegment] = obj;
            return makeMutable;
        }
        Object[] makeMutable2 = makeMutable(objArr);
        int i3 = i - 5;
        Object obj3 = makeMutable2[indexSegment];
        ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        makeMutable2[indexSegment] = insertIntoRoot$1((Object[]) obj3, i3, i2, obj, accessibilityNodeProviderCompat);
        while (true) {
            indexSegment++;
            if (indexSegment >= 32 || (obj2 = makeMutable2[indexSegment]) == null) {
                break;
            }
            makeMutable2[indexSegment] = insertIntoRoot$1((Object[]) obj2, i3, 0, accessibilityNodeProviderCompat.mProvider, accessibilityNodeProviderCompat);
        }
        return makeMutable2;
    }

    public final void insertIntoTail(Object[] objArr, int i, Object obj) {
        int tailSize = tailSize();
        Object[] makeMutable = makeMutable(this.tail);
        if (tailSize < 32) {
            MapsKt___MapsJvmKt.copyInto(this.tail, makeMutable, i + 1, i, tailSize);
            makeMutable[i] = obj;
            this.root = objArr;
            this.tail = makeMutable;
            this.size++;
            return;
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[31];
        MapsKt___MapsJvmKt.copyInto(objArr2, makeMutable, i + 1, i, 31);
        makeMutable[i] = obj;
        pushFilledTail(objArr, makeMutable, mutableBufferWith(obj2));
    }

    public final boolean isMutable(Object[] objArr) {
        return objArr.length == 33 && objArr[32] == this.ownership;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final AbstractListIterator leafBufferIterator(int i) {
        if (this.root == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        int rootSize$1 = rootSize$1() >> 5;
        _BOUNDARY.checkPositionIndex$runtime_release(i, rootSize$1);
        int i2 = this.rootShift;
        if (i2 == 0) {
            Object[] objArr = this.root;
            ResultKt.checkNotNull(objArr);
            return new BufferIterator(i, objArr);
        }
        Object[] objArr2 = this.root;
        ResultKt.checkNotNull(objArr2);
        return new TrieIterator(objArr2, i, rootSize$1, i2 / 5);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        _BOUNDARY.checkPositionIndex$runtime_release(i, getSize());
        return new PersistentVectorMutableIterator(this, i);
    }

    public final Object[] makeMutable(Object[] objArr) {
        if (objArr == null) {
            return mutableBuffer();
        }
        if (isMutable(objArr)) {
            return objArr;
        }
        Object[] mutableBuffer = mutableBuffer();
        int length = objArr.length;
        if (length > 32) {
            length = 32;
        }
        MapsKt___MapsJvmKt.copyInto$default(objArr, mutableBuffer, 0, length, 6);
        return mutableBuffer;
    }

    public final Object[] makeMutableShiftingRight(Object[] objArr, int i) {
        if (isMutable(objArr)) {
            MapsKt___MapsJvmKt.copyInto(objArr, objArr, i, 0, 32 - i);
            return objArr;
        }
        Object[] mutableBuffer = mutableBuffer();
        MapsKt___MapsJvmKt.copyInto(objArr, mutableBuffer, i, 0, 32 - i);
        return mutableBuffer;
    }

    public final Object[] mutableBuffer() {
        Object[] objArr = new Object[33];
        objArr[32] = this.ownership;
        return objArr;
    }

    public final Object[] mutableBufferWith(Object obj) {
        Object[] objArr = new Object[33];
        objArr[0] = obj;
        objArr[32] = this.ownership;
        return objArr;
    }

    public final Object[] nullifyAfter(int i, int i2, Object[] objArr) {
        if (i2 < 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (i2 == 0) {
            return objArr;
        }
        int indexSegment = ResultKt.indexSegment(i, i2);
        Object obj = objArr[indexSegment];
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object nullifyAfter = nullifyAfter(i, i2 - 5, (Object[]) obj);
        if (indexSegment < 31) {
            int i3 = indexSegment + 1;
            if (objArr[i3] != null) {
                if (isMutable(objArr)) {
                    Arrays.fill(objArr, i3, 32, (Object) null);
                }
                Object[] mutableBuffer = mutableBuffer();
                MapsKt___MapsJvmKt.copyInto(objArr, mutableBuffer, 0, 0, i3);
                objArr = mutableBuffer;
            }
        }
        if (nullifyAfter == objArr[indexSegment]) {
            return objArr;
        }
        Object[] makeMutable = makeMutable(objArr);
        makeMutable[indexSegment] = nullifyAfter;
        return makeMutable;
    }

    public final Object[] pullLastBuffer$1(Object[] objArr, int i, int i2, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        Object[] pullLastBuffer$1;
        int indexSegment = ResultKt.indexSegment(i2 - 1, i);
        if (i == 5) {
            accessibilityNodeProviderCompat.mProvider = objArr[indexSegment];
            pullLastBuffer$1 = null;
        } else {
            Object obj = objArr[indexSegment];
            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            pullLastBuffer$1 = pullLastBuffer$1((Object[]) obj, i - 5, i2, accessibilityNodeProviderCompat);
        }
        if (pullLastBuffer$1 == null && indexSegment == 0) {
            return null;
        }
        Object[] makeMutable = makeMutable(objArr);
        makeMutable[indexSegment] = pullLastBuffer$1;
        return makeMutable;
    }

    public final void pullLastBufferFromRoot(int i, int i2, Object[] objArr) {
        if (i2 == 0) {
            this.root = null;
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.tail = objArr;
            this.size = i;
            this.rootShift = i2;
            return;
        }
        AccessibilityNodeProviderCompat accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(null);
        ResultKt.checkNotNull(objArr);
        Object[] pullLastBuffer$1 = pullLastBuffer$1(objArr, i2, i, accessibilityNodeProviderCompat);
        ResultKt.checkNotNull(pullLastBuffer$1);
        Object obj = accessibilityNodeProviderCompat.mProvider;
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        this.tail = (Object[]) obj;
        this.size = i;
        if (pullLastBuffer$1[1] == null) {
            this.root = (Object[]) pullLastBuffer$1[0];
            this.rootShift = i2 - 5;
        } else {
            this.root = pullLastBuffer$1;
            this.rootShift = i2;
        }
    }

    public final Object[] pushBuffers(Object[] objArr, int i, int i2, Iterator it) {
        if (!it.hasNext()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (i2 < 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (i2 == 0) {
            return (Object[]) it.next();
        }
        Object[] makeMutable = makeMutable(objArr);
        int indexSegment = ResultKt.indexSegment(i, i2);
        int i3 = i2 - 5;
        makeMutable[indexSegment] = pushBuffers((Object[]) makeMutable[indexSegment], i, i3, it);
        while (true) {
            indexSegment++;
            if (indexSegment >= 32 || !it.hasNext()) {
                break;
            }
            makeMutable[indexSegment] = pushBuffers((Object[]) makeMutable[indexSegment], 0, i3, it);
        }
        return makeMutable;
    }

    public final Object[] pushBuffersIncreasingHeightIfNeeded(Object[] objArr, int i, Object[][] objArr2) {
        ArrayIterator arrayIterator = new ArrayIterator(objArr2);
        int i2 = i >> 5;
        int i3 = this.rootShift;
        Object[] pushBuffers = i2 < (1 << i3) ? pushBuffers(objArr, i, i3, arrayIterator) : makeMutable(objArr);
        while (arrayIterator.hasNext()) {
            this.rootShift += 5;
            pushBuffers = mutableBufferWith(pushBuffers);
            int i4 = this.rootShift;
            pushBuffers(pushBuffers, 1 << i4, i4, arrayIterator);
        }
        return pushBuffers;
    }

    public final void pushFilledTail(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i = this.size;
        int i2 = i >> 5;
        int i3 = this.rootShift;
        if (i2 > (1 << i3)) {
            this.root = pushTail(this.rootShift + 5, mutableBufferWith(objArr), objArr2);
            this.tail = objArr3;
            this.rootShift += 5;
            this.size++;
            return;
        }
        if (objArr == null) {
            this.root = objArr2;
            this.tail = objArr3;
            this.size = i + 1;
        } else {
            this.root = pushTail(i3, objArr, objArr2);
            this.tail = objArr3;
            this.size++;
        }
    }

    public final Object[] pushTail(int i, Object[] objArr, Object[] objArr2) {
        int indexSegment = ResultKt.indexSegment(getSize() - 1, i);
        Object[] makeMutable = makeMutable(objArr);
        if (i == 5) {
            makeMutable[indexSegment] = objArr2;
        } else {
            makeMutable[indexSegment] = pushTail(i - 5, (Object[]) makeMutable[indexSegment], objArr2);
        }
        return makeMutable;
    }

    public final int recyclableRemoveAll(SnapshotStateList$retainAll$1 snapshotStateList$retainAll$1, Object[] objArr, int i, int i2, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat, ArrayList arrayList, ArrayList arrayList2) {
        if (isMutable(objArr)) {
            arrayList.add(objArr);
        }
        Object obj = accessibilityNodeProviderCompat.mProvider;
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr2 = (Object[]) obj;
        Object[] objArr3 = objArr2;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj2 = objArr[i3];
            if (!((Boolean) snapshotStateList$retainAll$1.invoke(obj2)).booleanValue()) {
                if (i2 == 32) {
                    objArr3 = arrayList.isEmpty() ^ true ? (Object[]) arrayList.remove(arrayList.size() - 1) : mutableBuffer();
                    i2 = 0;
                }
                objArr3[i2] = obj2;
                i2++;
            }
        }
        accessibilityNodeProviderCompat.mProvider = objArr3;
        if (objArr2 != objArr3) {
            arrayList2.add(objArr2);
        }
        return i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        return removeAllWithPredicate(new SnapshotStateList$retainAll$1(2, collection));
    }

    public final int removeAllFromTail(SnapshotStateList$retainAll$1 snapshotStateList$retainAll$1, int i, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        int removeAll = removeAll(snapshotStateList$retainAll$1, this.tail, i, accessibilityNodeProviderCompat);
        if (removeAll == i) {
            return i;
        }
        Object obj = accessibilityNodeProviderCompat.mProvider;
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) obj;
        Arrays.fill(objArr, removeAll, i, (Object) null);
        this.tail = objArr;
        this.size -= i - removeAll;
        return removeAll;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
    
        if (r0 != r10) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
    
        if (removeAllFromTail(r19, r10, r11) != r10) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001a, code lost:
    
        r14 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean removeAllWithPredicate(androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1 r19) {
        /*
            r18 = this;
            r8 = r18
            r9 = r19
            int r10 = r18.tailSize()
            androidx.core.view.accessibility.AccessibilityNodeProviderCompat r11 = new androidx.core.view.accessibility.AccessibilityNodeProviderCompat
            r12 = 0
            r11.<init>(r12)
            java.lang.Object[] r0 = r8.root
            r13 = 1
            r14 = 0
            if (r0 != 0) goto L1d
            int r0 = r8.removeAllFromTail(r9, r10, r11)
            if (r0 == r10) goto Le7
        L1a:
            r14 = r13
            goto Le7
        L1d:
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator r15 = r8.leafBufferIterator(r14)
            r7 = 32
            r0 = r7
        L24:
            if (r0 != r7) goto L37
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L37
            java.lang.Object r0 = r15.next()
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            int r0 = r8.removeAll(r9, r0, r7, r11)
            goto L24
        L37:
            if (r0 != r7) goto L4b
            int r0 = r8.removeAllFromTail(r9, r10, r11)
            if (r0 != 0) goto L48
            java.lang.Object[] r1 = r8.root
            int r2 = r8.size
            int r3 = r8.rootShift
            r8.pullLastBufferFromRoot(r2, r3, r1)
        L48:
            if (r0 == r10) goto Le7
            goto L1a
        L4b:
            int r1 = r15.index
            int r1 = r1 - r13
            int r6 = r1 << 5
            java.util.ArrayList r16 = new java.util.ArrayList
            r16.<init>()
            java.util.ArrayList r17 = new java.util.ArrayList
            r17.<init>()
            r4 = r0
        L5b:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L7e
            java.lang.Object r0 = r15.next()
            r2 = r0
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r3 = 32
            r0 = r18
            r1 = r19
            r5 = r11
            r13 = r6
            r6 = r17
            r14 = r7
            r7 = r16
            int r4 = r0.recyclableRemoveAll(r1, r2, r3, r4, r5, r6, r7)
            r6 = r13
            r7 = r14
            r13 = 1
            r14 = 0
            goto L5b
        L7e:
            r13 = r6
            r14 = r7
            java.lang.Object[] r2 = r8.tail
            r0 = r18
            r1 = r19
            r3 = r10
            r5 = r11
            r6 = r17
            r7 = r16
            int r0 = r0.recyclableRemoveAll(r1, r2, r3, r4, r5, r6, r7)
            java.lang.Object r1 = r11.mProvider
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>"
            kotlin.ResultKt.checkNotNull(r1, r2)
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            java.util.Arrays.fill(r1, r0, r14, r12)
            boolean r3 = r16.isEmpty()
            if (r3 == 0) goto La8
            java.lang.Object[] r3 = r8.root
            kotlin.ResultKt.checkNotNull(r3)
            goto Lb4
        La8:
            java.lang.Object[] r3 = r8.root
            int r4 = r8.rootShift
            java.util.Iterator r5 = r16.iterator()
            java.lang.Object[] r3 = r8.pushBuffers(r3, r13, r4, r5)
        Lb4:
            int r4 = r16.size()
            int r4 = r4 << 5
            int r6 = r13 + r4
            r4 = r6 & 31
            if (r4 != 0) goto Lf0
            if (r6 != 0) goto Lc6
            r4 = 0
            r8.rootShift = r4
            goto Ldf
        Lc6:
            r4 = 0
            int r5 = r6 + (-1)
        Lc9:
            int r7 = r8.rootShift
            int r9 = r5 >> r7
            if (r9 != 0) goto Ldb
            int r7 = r7 + (-5)
            r8.rootShift = r7
            r3 = r3[r4]
            kotlin.ResultKt.checkNotNull(r3, r2)
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            goto Lc9
        Ldb:
            java.lang.Object[] r12 = r8.nullifyAfter(r5, r7, r3)
        Ldf:
            r8.root = r12
            r8.tail = r1
            int r6 = r6 + r0
            r8.size = r6
            r14 = 1
        Le7:
            if (r14 == 0) goto Lef
            int r0 = r8.modCount
            r1 = 1
            int r0 = r0 + r1
            r8.modCount = r0
        Lef:
            return r14
        Lf0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Check failed."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder.removeAllWithPredicate(androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1):boolean");
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        _BOUNDARY.checkElementIndex$runtime_release(i, getSize());
        ((AbstractList) this).modCount++;
        int rootSize$1 = rootSize$1();
        if (i >= rootSize$1) {
            return removeFromTailAt(this.root, rootSize$1, this.rootShift, i - rootSize$1);
        }
        AccessibilityNodeProviderCompat accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(this.tail[0]);
        Object[] objArr = this.root;
        ResultKt.checkNotNull(objArr);
        removeFromTailAt(removeFromRootAt$1(objArr, this.rootShift, i, accessibilityNodeProviderCompat), rootSize$1, this.rootShift, 0);
        return accessibilityNodeProviderCompat.mProvider;
    }

    public final Object[] removeFromRootAt$1(Object[] objArr, int i, int i2, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        int indexSegment = ResultKt.indexSegment(i2, i);
        if (i == 0) {
            Object obj = objArr[indexSegment];
            Object[] makeMutable = makeMutable(objArr);
            MapsKt___MapsJvmKt.copyInto(objArr, makeMutable, indexSegment, indexSegment + 1, 32);
            makeMutable[31] = accessibilityNodeProviderCompat.mProvider;
            accessibilityNodeProviderCompat.mProvider = obj;
            return makeMutable;
        }
        int indexSegment2 = objArr[31] == null ? ResultKt.indexSegment(rootSize$1() - 1, i) : 31;
        Object[] makeMutable2 = makeMutable(objArr);
        int i3 = i - 5;
        int i4 = indexSegment + 1;
        if (i4 <= indexSegment2) {
            while (true) {
                Object obj2 = makeMutable2[indexSegment2];
                ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                makeMutable2[indexSegment2] = removeFromRootAt$1((Object[]) obj2, i3, 0, accessibilityNodeProviderCompat);
                if (indexSegment2 == i4) {
                    break;
                }
                indexSegment2--;
            }
        }
        Object obj3 = makeMutable2[indexSegment];
        ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        makeMutable2[indexSegment] = removeFromRootAt$1((Object[]) obj3, i3, i2, accessibilityNodeProviderCompat);
        return makeMutable2;
    }

    public final Object removeFromTailAt(Object[] objArr, int i, int i2, int i3) {
        int i4 = this.size - i;
        if (i4 == 1) {
            Object obj = this.tail[0];
            pullLastBufferFromRoot(i, i2, objArr);
            return obj;
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[i3];
        Object[] makeMutable = makeMutable(objArr2);
        MapsKt___MapsJvmKt.copyInto(objArr2, makeMutable, i3, i3 + 1, i4);
        makeMutable[i4 - 1] = null;
        this.root = objArr;
        this.tail = makeMutable;
        this.size = (i + i4) - 1;
        this.rootShift = i2;
        return obj2;
    }

    public final int rootSize$1() {
        int i = this.size;
        if (i <= 32) {
            return 0;
        }
        return (i - 1) & (-32);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        _BOUNDARY.checkElementIndex$runtime_release(i, getSize());
        if (rootSize$1() > i) {
            AccessibilityNodeProviderCompat accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(null);
            Object[] objArr = this.root;
            ResultKt.checkNotNull(objArr);
            this.root = setInRoot(objArr, this.rootShift, i, obj, accessibilityNodeProviderCompat);
            return accessibilityNodeProviderCompat.mProvider;
        }
        Object[] makeMutable = makeMutable(this.tail);
        if (makeMutable != this.tail) {
            ((AbstractList) this).modCount++;
        }
        int i2 = i & 31;
        Object obj2 = makeMutable[i2];
        makeMutable[i2] = obj;
        this.tail = makeMutable;
        return obj2;
    }

    public final Object[] setInRoot(Object[] objArr, int i, int i2, Object obj, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        int indexSegment = ResultKt.indexSegment(i2, i);
        Object[] makeMutable = makeMutable(objArr);
        if (i != 0) {
            Object obj2 = makeMutable[indexSegment];
            ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            makeMutable[indexSegment] = setInRoot((Object[]) obj2, i - 5, i2, obj, accessibilityNodeProviderCompat);
            return makeMutable;
        }
        if (makeMutable != objArr) {
            ((AbstractList) this).modCount++;
        }
        accessibilityNodeProviderCompat.mProvider = makeMutable[indexSegment];
        makeMutable[indexSegment] = obj;
        return makeMutable;
    }

    public final void splitToBuffers(Collection collection, int i, Object[] objArr, int i2, Object[][] objArr2, int i3, Object[] objArr3) {
        Object[] mutableBuffer;
        if (i3 < 1) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Object[] makeMutable = makeMutable(objArr);
        objArr2[0] = makeMutable;
        int i4 = i & 31;
        int size = ((collection.size() + i) - 1) & 31;
        int i5 = (i2 - i4) + size;
        if (i5 < 32) {
            MapsKt___MapsJvmKt.copyInto(makeMutable, objArr3, size + 1, i4, i2);
        } else {
            int i6 = i5 - 31;
            if (i3 == 1) {
                mutableBuffer = makeMutable;
            } else {
                mutableBuffer = mutableBuffer();
                i3--;
                objArr2[i3] = mutableBuffer;
            }
            int i7 = i2 - i6;
            MapsKt___MapsJvmKt.copyInto(makeMutable, objArr3, 0, i7, i2);
            MapsKt___MapsJvmKt.copyInto(makeMutable, mutableBuffer, size + 1, i4, i7);
            objArr3 = mutableBuffer;
        }
        Iterator it = collection.iterator();
        copyToBuffer(makeMutable, i4, it);
        for (int i8 = 1; i8 < i3; i8++) {
            Object[] mutableBuffer2 = mutableBuffer();
            copyToBuffer(mutableBuffer2, 0, it);
            objArr2[i8] = mutableBuffer2;
        }
        copyToBuffer(objArr3, 0, it);
    }

    public final int tailSize() {
        int i = this.size;
        return i <= 32 ? i : i - ((i - 1) & (-32));
    }

    public final int removeAll(SnapshotStateList$retainAll$1 snapshotStateList$retainAll$1, Object[] objArr, int i, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        Object[] objArr2 = objArr;
        int i2 = i;
        boolean z = false;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (((Boolean) snapshotStateList$retainAll$1.invoke(obj)).booleanValue()) {
                if (!z) {
                    objArr2 = makeMutable(objArr);
                    z = true;
                    i2 = i3;
                }
            } else if (z) {
                objArr2[i2] = obj;
                i2++;
            }
        }
        accessibilityNodeProviderCompat.mProvider = objArr2;
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ((AbstractList) this).modCount++;
        int tailSize = tailSize();
        if (tailSize < 32) {
            Object[] makeMutable = makeMutable(this.tail);
            makeMutable[tailSize] = obj;
            this.tail = makeMutable;
            this.size = getSize() + 1;
        } else {
            pushFilledTail(this.root, this.tail, mutableBufferWith(obj));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        ResultKt.checkNotNullParameter(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int tailSize = tailSize();
        Iterator it = collection.iterator();
        if (32 - tailSize >= collection.size()) {
            Object[] makeMutable = makeMutable(this.tail);
            copyToBuffer(makeMutable, tailSize, it);
            this.tail = makeMutable;
            this.size = collection.size() + this.size;
        } else {
            int size = ((collection.size() + tailSize) - 1) / 32;
            Object[][] objArr = new Object[size];
            Object[] makeMutable2 = makeMutable(this.tail);
            copyToBuffer(makeMutable2, tailSize, it);
            objArr[0] = makeMutable2;
            for (int i = 1; i < size; i++) {
                Object[] mutableBuffer = mutableBuffer();
                copyToBuffer(mutableBuffer, 0, it);
                objArr[i] = mutableBuffer;
            }
            this.root = pushBuffersIncreasingHeightIfNeeded(this.root, rootSize$1(), objArr);
            Object[] mutableBuffer2 = mutableBuffer();
            copyToBuffer(mutableBuffer2, 0, it);
            this.tail = mutableBuffer2;
            this.size = collection.size() + this.size;
        }
        return true;
    }
}
