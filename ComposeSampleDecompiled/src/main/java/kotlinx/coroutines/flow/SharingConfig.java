package kotlinx.coroutines.flow;

import androidx.compose.runtime.collection.IdentityArraySet;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;

/* loaded from: classes.dex */
public final class SharingConfig {
    public Object context;
    public int extraBufferCapacity;
    public Serializable onBufferOverflow;
    public Object upstream;

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.io.Serializable] */
    public SharingConfig() {
        int[] iArr = new int[50];
        for (int i = 0; i < 50; i++) {
            iArr[i] = i;
        }
        this.upstream = iArr;
        this.onBufferOverflow = new Object[50];
        this.context = new IdentityArraySet[50];
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object[], java.lang.Object, java.io.Serializable] */
    public final void add(Object obj, Object obj2) {
        int i;
        IdentityArraySet identityArraySet;
        ResultKt.checkNotNullParameter(obj, "value");
        ResultKt.checkNotNullParameter(obj2, "scope");
        int i2 = this.extraBufferCapacity;
        int[] iArr = (int[]) this.upstream;
        Object[] objArr = (Object[]) this.onBufferOverflow;
        IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) this.context;
        if (i2 > 0) {
            i = find(obj);
            if (i >= 0) {
                identityArraySet = scopeSetAt(i);
                identityArraySet.add(obj2);
            }
        } else {
            i = -1;
        }
        int i3 = -(i + 1);
        if (i2 < iArr.length) {
            int i4 = iArr[i2];
            objArr[i4] = obj;
            identityArraySet = identityArraySetArr[i4];
            if (identityArraySet == null) {
                identityArraySet = new IdentityArraySet();
                identityArraySetArr[i4] = identityArraySet;
            }
            if (i3 < i2) {
                MapsKt___MapsJvmKt.copyInto(iArr, iArr, i3 + 1, i3, i2);
            }
            iArr[i3] = i4;
            this.extraBufferCapacity++;
        } else {
            int length = iArr.length * 2;
            Object[] copyOf = Arrays.copyOf(identityArraySetArr, length);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            IdentityArraySet[] identityArraySetArr2 = (IdentityArraySet[]) copyOf;
            IdentityArraySet identityArraySet2 = new IdentityArraySet();
            identityArraySetArr2[i2] = identityArraySet2;
            ?? copyOf2 = Arrays.copyOf(objArr, length);
            ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
            copyOf2[i2] = obj;
            int[] iArr2 = new int[length];
            for (int i5 = i2 + 1; i5 < length; i5++) {
                iArr2[i5] = i5;
            }
            if (i3 < i2) {
                MapsKt___MapsJvmKt.copyInto(iArr, iArr2, i3 + 1, i3, i2);
            }
            iArr2[i3] = i2;
            if (i3 > 0) {
                MapsKt___MapsJvmKt.copyInto$default(iArr, iArr2, i3, 6);
            }
            this.context = identityArraySetArr2;
            this.onBufferOverflow = copyOf2;
            this.upstream = iArr2;
            this.extraBufferCapacity++;
            identityArraySet = identityArraySet2;
        }
        identityArraySet.add(obj2);
    }

    public final void clear() {
        IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) this.context;
        int[] iArr = (int[]) this.upstream;
        Object[] objArr = (Object[]) this.onBufferOverflow;
        int length = identityArraySetArr.length;
        for (int i = 0; i < length; i++) {
            IdentityArraySet identityArraySet = identityArraySetArr[i];
            if (identityArraySet != null) {
                identityArraySet.clear();
            }
            iArr[i] = i;
            objArr[i] = null;
        }
        this.extraBufferCapacity = 0;
    }

    public final boolean contains(Object obj) {
        ResultKt.checkNotNullParameter(obj, "element");
        return find(obj) >= 0;
    }

    public final int find(Object obj) {
        int identityHashCode = System.identityHashCode(obj);
        int i = this.extraBufferCapacity - 1;
        Object[] objArr = (Object[]) this.onBufferOverflow;
        int[] iArr = (int[]) this.upstream;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj2 = objArr[iArr[i3]];
            int identityHashCode2 = System.identityHashCode(obj2);
            if (identityHashCode2 < identityHashCode) {
                i2 = i3 + 1;
            } else {
                if (identityHashCode2 <= identityHashCode) {
                    if (obj == obj2) {
                        return i3;
                    }
                    Object[] objArr2 = (Object[]) this.onBufferOverflow;
                    int[] iArr2 = (int[]) this.upstream;
                    for (int i4 = i3 - 1; -1 < i4; i4--) {
                        Object obj3 = objArr2[iArr2[i4]];
                        if (obj3 == obj) {
                            return i4;
                        }
                        if (System.identityHashCode(obj3) != identityHashCode) {
                            break;
                        }
                    }
                    int i5 = this.extraBufferCapacity;
                    for (int i6 = i3 + 1; i6 < i5; i6++) {
                        Object obj4 = objArr2[iArr2[i6]];
                        if (obj4 == obj) {
                            return i6;
                        }
                        if (System.identityHashCode(obj4) != identityHashCode) {
                            return -(i6 + 1);
                        }
                    }
                    return -(this.extraBufferCapacity + 1);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public final boolean remove(Object obj, Object obj2) {
        int i;
        IdentityArraySet identityArraySet;
        ResultKt.checkNotNullParameter(obj, "value");
        int find = find(obj);
        int[] iArr = (int[]) this.upstream;
        IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) this.context;
        Object[] objArr = (Object[]) this.onBufferOverflow;
        int i2 = this.extraBufferCapacity;
        if (find < 0 || (identityArraySet = identityArraySetArr[(i = iArr[find])]) == null) {
            return false;
        }
        boolean remove = identityArraySet.remove(obj2);
        if (identityArraySet.size == 0) {
            int i3 = find + 1;
            if (i3 < i2) {
                MapsKt___MapsJvmKt.copyInto(iArr, iArr, find, i3, i2);
            }
            int i4 = i2 - 1;
            iArr[i4] = i;
            objArr[i] = null;
            this.extraBufferCapacity = i4;
        }
        return remove;
    }

    public final void removeScope(Object obj) {
        ResultKt.checkNotNullParameter(obj, "scope");
        int[] iArr = (int[]) this.upstream;
        IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) this.context;
        Object[] objArr = (Object[]) this.onBufferOverflow;
        int i = this.extraBufferCapacity;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            IdentityArraySet identityArraySet = identityArraySetArr[i4];
            ResultKt.checkNotNull(identityArraySet);
            identityArraySet.remove(obj);
            if (identityArraySet.size > 0) {
                if (i2 != i3) {
                    int i5 = iArr[i2];
                    iArr[i2] = i4;
                    iArr[i3] = i5;
                }
                i2++;
            }
        }
        int i6 = this.extraBufferCapacity;
        for (int i7 = i2; i7 < i6; i7++) {
            objArr[iArr[i7]] = null;
        }
        this.extraBufferCapacity = i2;
    }

    public final IdentityArraySet scopeSetAt(int i) {
        IdentityArraySet identityArraySet = ((IdentityArraySet[]) this.context)[((int[]) this.upstream)[i]];
        ResultKt.checkNotNull(identityArraySet);
        return identityArraySet;
    }

    public SharingConfig(int i, CoroutineContext coroutineContext, BufferOverflow bufferOverflow, Flow flow) {
        this.upstream = flow;
        this.extraBufferCapacity = i;
        this.onBufferOverflow = bufferOverflow;
        this.context = coroutineContext;
    }
}
