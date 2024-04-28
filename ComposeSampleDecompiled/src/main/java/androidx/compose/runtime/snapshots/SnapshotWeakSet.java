package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.WeakReference;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;

/* loaded from: classes.dex */
public final class SnapshotWeakSet {
    public final /* synthetic */ int $r8$classId;
    public Object hashes;
    public int size;
    public Object[] values;

    public SnapshotWeakSet(int i) {
        this.$r8$classId = 1;
        this.hashes = new Object[i];
        this.values = new Object[i];
    }

    public final int find(Object obj) {
        int identityHashCode = System.identityHashCode(obj);
        int i = this.size - 1;
        Object[] objArr = (Object[]) this.hashes;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj2 = objArr[i3];
            int identityHashCode2 = System.identityHashCode(obj2);
            if (identityHashCode2 < identityHashCode) {
                i2 = i3 + 1;
            } else {
                if (identityHashCode2 <= identityHashCode) {
                    return obj == obj2 ? i3 : findExactIndex(i3, identityHashCode, obj);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int findExactIndex(int r6, int r7, java.lang.Object r8) {
        /*
            r5 = this;
            int r0 = r5.$r8$classId
            r1 = -1
            switch(r0) {
                case 0: goto L3a;
                default: goto L6;
            }
        L6:
            java.lang.Object r0 = r5.hashes
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            int r2 = r5.size
            int r3 = r6 + (-1)
        Le:
            if (r1 >= r3) goto L1f
            r4 = r0[r3]
            if (r4 != r8) goto L15
            goto L39
        L15:
            int r4 = java.lang.System.identityHashCode(r4)
            if (r4 == r7) goto L1c
            goto L1f
        L1c:
            int r3 = r3 + (-1)
            goto Le
        L1f:
            int r6 = r6 + 1
            r3 = r6
        L22:
            if (r3 >= r2) goto L36
            r6 = r0[r3]
            if (r6 != r8) goto L29
            goto L39
        L29:
            int r6 = java.lang.System.identityHashCode(r6)
            if (r6 == r7) goto L33
            int r3 = r3 + 1
            int r3 = -r3
            goto L39
        L33:
            int r3 = r3 + 1
            goto L22
        L36:
            int r2 = r2 + 1
            int r3 = -r2
        L39:
            return r3
        L3a:
            int r0 = r6 + (-1)
        L3c:
            r2 = 0
            if (r1 >= r0) goto L5a
            java.lang.Object r3 = r5.hashes
            int[] r3 = (int[]) r3
            r3 = r3[r0]
            if (r3 == r7) goto L48
            goto L5a
        L48:
            java.lang.Object[] r3 = r5.values
            androidx.compose.runtime.WeakReference[] r3 = (androidx.compose.runtime.WeakReference[]) r3
            r3 = r3[r0]
            if (r3 == 0) goto L54
            java.lang.Object r2 = r3.get()
        L54:
            if (r2 != r8) goto L57
            goto L84
        L57:
            int r0 = r0 + (-1)
            goto L3c
        L5a:
            int r6 = r6 + 1
            int r0 = r5.size
        L5e:
            if (r6 >= r0) goto L81
            java.lang.Object r1 = r5.hashes
            int[] r1 = (int[]) r1
            r1 = r1[r6]
            if (r1 == r7) goto L6c
        L68:
            int r6 = r6 + 1
            int r0 = -r6
            goto L84
        L6c:
            java.lang.Object[] r1 = r5.values
            androidx.compose.runtime.WeakReference[] r1 = (androidx.compose.runtime.WeakReference[]) r1
            r1 = r1[r6]
            if (r1 == 0) goto L79
            java.lang.Object r1 = r1.get()
            goto L7a
        L79:
            r1 = r2
        L7a:
            if (r1 != r8) goto L7e
            r0 = r6
            goto L84
        L7e:
            int r6 = r6 + 1
            goto L5e
        L81:
            int r6 = r5.size
            goto L68
        L84:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotWeakSet.findExactIndex(int, int, java.lang.Object):int");
    }

    public final Object get(Object obj) {
        ResultKt.checkNotNullParameter(obj, "key");
        int find = find(obj);
        if (find >= 0) {
            return this.values[find];
        }
        return null;
    }

    public final void set(Object obj, Object obj2) {
        ResultKt.checkNotNullParameter(obj, "key");
        Object[] objArr = (Object[]) this.hashes;
        Object[] objArr2 = this.values;
        int i = this.size;
        int find = find(obj);
        if (find >= 0) {
            objArr2[find] = obj2;
            return;
        }
        int i2 = -(find + 1);
        boolean z = i == objArr.length;
        Object[] objArr3 = z ? new Object[i * 2] : objArr;
        int i3 = i2 + 1;
        MapsKt___MapsJvmKt.copyInto(objArr, objArr3, i3, i2, i);
        if (z) {
            MapsKt___MapsJvmKt.copyInto$default(objArr, objArr3, 0, i2, 6);
        }
        objArr3[i2] = obj;
        this.hashes = objArr3;
        Object[] objArr4 = z ? new Object[i * 2] : objArr2;
        MapsKt___MapsJvmKt.copyInto(objArr2, objArr4, i3, i2, i);
        if (z) {
            MapsKt___MapsJvmKt.copyInto$default(objArr2, objArr4, 0, i2, 6);
        }
        objArr4[i2] = obj2;
        this.values = objArr4;
        this.size++;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SnapshotWeakSet(Object obj) {
        this(16);
        this.$r8$classId = 1;
    }

    public SnapshotWeakSet() {
        this.$r8$classId = 0;
        this.hashes = new int[16];
        this.values = new WeakReference[16];
    }
}
