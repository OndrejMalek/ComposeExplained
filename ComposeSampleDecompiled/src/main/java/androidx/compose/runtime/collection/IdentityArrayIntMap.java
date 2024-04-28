package androidx.compose.runtime.collection;

/* loaded from: classes.dex */
public final class IdentityArrayIntMap {
    public int size;
    public Object[] keys = new Object[4];
    public int[] values = new int[4];

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0059, code lost:
    
        r7 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int add(int r11, java.lang.Object r12) {
        /*
            r10 = this;
            java.lang.String r0 = "key"
            kotlin.ResultKt.checkNotNullParameter(r12, r0)
            int[] r0 = r10.values
            int r1 = r10.size
            r2 = 0
            r3 = -1
            if (r1 <= 0) goto L69
            int r1 = r1 + (-1)
            int r4 = java.lang.System.identityHashCode(r12)
            java.lang.Object[] r5 = r10.keys
            r6 = r2
        L16:
            if (r6 > r1) goto L5f
            int r7 = r6 + r1
            int r7 = r7 >>> 1
            r8 = r5[r7]
            int r9 = java.lang.System.identityHashCode(r8)
            if (r9 >= r4) goto L27
            int r6 = r7 + 1
            goto L16
        L27:
            if (r9 <= r4) goto L2c
            int r1 = r7 + (-1)
            goto L16
        L2c:
            if (r8 != r12) goto L2f
            goto L62
        L2f:
            java.lang.Object[] r1 = r10.keys
            int r5 = r10.size
            int r6 = r7 + (-1)
        L35:
            if (r3 >= r6) goto L47
            r8 = r1[r6]
            if (r8 != r12) goto L3d
            r7 = r6
            goto L62
        L3d:
            int r8 = java.lang.System.identityHashCode(r8)
            if (r8 == r4) goto L44
            goto L47
        L44:
            int r6 = r6 + (-1)
            goto L35
        L47:
            int r7 = r7 + 1
            if (r7 >= r5) goto L5b
            r6 = r1[r7]
            if (r6 != r12) goto L50
            goto L62
        L50:
            int r6 = java.lang.System.identityHashCode(r6)
            if (r6 == r4) goto L47
            int r7 = r7 + 1
            int r1 = -r7
        L59:
            r7 = r1
            goto L62
        L5b:
            int r5 = r5 + 1
            int r1 = -r5
            goto L59
        L5f:
            int r6 = r6 + 1
            int r7 = -r6
        L62:
            if (r7 < 0) goto L6a
            r12 = r0[r7]
            r0[r7] = r11
            return r12
        L69:
            r7 = r3
        L6a:
            int r7 = r7 + 1
            int r1 = -r7
            java.lang.Object[] r4 = r10.keys
            int r5 = r10.size
            int r6 = r4.length
            if (r5 != r6) goto L92
            int r6 = r4.length
            int r6 = r6 * 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            int r7 = r4.length
            int r7 = r7 * 2
            int[] r7 = new int[r7]
            int r8 = r1 + 1
            kotlin.collections.MapsKt___MapsJvmKt.copyInto(r4, r6, r8, r1, r5)
            kotlin.collections.MapsKt___MapsJvmKt.copyInto(r0, r7, r8, r1, r5)
            r5 = 6
            kotlin.collections.MapsKt___MapsJvmKt.copyInto$default(r4, r6, r2, r1, r5)
            kotlin.collections.MapsKt___MapsJvmKt.copyInto$default(r0, r7, r1, r5)
            r10.keys = r6
            r10.values = r7
            goto L9a
        L92:
            int r2 = r1 + 1
            kotlin.collections.MapsKt___MapsJvmKt.copyInto(r4, r4, r2, r1, r5)
            kotlin.collections.MapsKt___MapsJvmKt.copyInto(r0, r0, r2, r1, r5)
        L9a:
            java.lang.Object[] r0 = r10.keys
            r0[r1] = r12
            int[] r12 = r10.values
            r12[r1] = r11
            int r11 = r10.size
            int r11 = r11 + 1
            r10.size = r11
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.IdentityArrayIntMap.add(int, java.lang.Object):int");
    }
}
