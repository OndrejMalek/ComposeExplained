package kotlin.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Pair;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class MapsKt___MapsJvmKt extends ResultKt {
    public static int collectionSizeOrDefault(Iterable iterable) {
        ResultKt.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }

    public static void copyInto(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        ResultKt.checkNotNullParameter(iArr, "<this>");
        ResultKt.checkNotNullParameter(iArr2, "destination");
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
    }

    public static /* synthetic */ void copyInto$default(int[] iArr, int[] iArr2, int i, int i2) {
        if ((i2 & 8) != 0) {
            i = iArr.length;
        }
        copyInto(iArr, iArr2, 0, 0, i);
    }

    public static Object[] copyOfRange(int i, int i2, Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "<this>");
        int length = objArr.length;
        if (i2 <= length) {
            Object[] copyOfRange = Arrays.copyOfRange(objArr, i, i2);
            ResultKt.checkNotNullExpressionValue(copyOfRange, "copyOfRange(this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + length + ").");
    }

    public static void fill(int i, int i2, Object[] objArr) {
        ResultKt.checkNotNullParameter(objArr, "<this>");
        Arrays.fill(objArr, i, i2, (Object) null);
    }

    public static int indexOf(Object[] objArr, Object obj) {
        ResultKt.checkNotNullParameter(objArr, "<this>");
        int i = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i < length) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i < length2) {
            if (ResultKt.areEqual(obj, objArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final void putAll(HashMap hashMap, Pair[] pairArr) {
        for (Pair pair : pairArr) {
            hashMap.put(pair.first, pair.second);
        }
    }

    public static void copyInto(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        ResultKt.checkNotNullParameter(objArr, "<this>");
        ResultKt.checkNotNullParameter(objArr2, "destination");
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static /* synthetic */ void copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        copyInto(objArr, objArr2, 0, i, i2);
    }
}
