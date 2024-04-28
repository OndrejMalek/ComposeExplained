package androidx.compose.ui.text.caches;

import java.util.Map;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SimpleArrayMap {
    public int _size;
    public int[] hashes;
    public Object[] keyValues;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (!(obj instanceof SimpleArrayMap)) {
                if (!(obj instanceof Map) || this._size != ((Map) obj).size()) {
                    return false;
                }
                int i = this._size;
                for (int i2 = 0; i2 < i; i2++) {
                    Object[] objArr = this.keyValues;
                    int i3 = i2 << 1;
                    Object obj2 = objArr[i3];
                    Object obj3 = objArr[i3 + 1];
                    Object obj4 = ((Map) obj).get(obj2);
                    if (obj3 == null) {
                        if (obj4 != null || !((Map) obj).containsKey(obj2)) {
                            return false;
                        }
                    } else if (!ResultKt.areEqual(obj3, obj4)) {
                        return false;
                    }
                }
                return true;
            }
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            int i4 = this._size;
            if (i4 != simpleArrayMap._size) {
                return false;
            }
            for (int i5 = 0; i5 < i4; i5++) {
                Object[] objArr2 = this.keyValues;
                int i6 = i5 << 1;
                Object obj5 = objArr2[i6];
                Object obj6 = objArr2[i6 + 1];
                int indexOfNull = obj5 == null ? simpleArrayMap.indexOfNull() : simpleArrayMap.indexOf(obj5.hashCode(), obj5);
                Object obj7 = indexOfNull >= 0 ? simpleArrayMap.keyValues[(indexOfNull << 1) + 1] : null;
                if (obj6 == null) {
                    if (obj7 == null) {
                        if ((obj5 == null ? simpleArrayMap.indexOfNull() : simpleArrayMap.indexOf(obj5.hashCode(), obj5)) >= 0) {
                        }
                    }
                    return false;
                }
                if (!ResultKt.areEqual(obj6, obj7)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final int hashCode() {
        int[] iArr = this.hashes;
        Object[] objArr = this.keyValues;
        int i = this._size;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj != null ? obj.hashCode() : 0) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public final int indexOf(int i, Object obj) {
        ResultKt.checkNotNullParameter(obj, "key");
        int i2 = this._size;
        if (i2 == 0) {
            return -1;
        }
        int binarySearchInternal = ContainerHelpersKt.binarySearchInternal(this.hashes, i2, i);
        if (binarySearchInternal < 0 || ResultKt.areEqual(obj, this.keyValues[binarySearchInternal << 1])) {
            return binarySearchInternal;
        }
        int i3 = binarySearchInternal + 1;
        while (i3 < i2 && this.hashes[i3] == i) {
            if (ResultKt.areEqual(obj, this.keyValues[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = binarySearchInternal - 1; i4 >= 0 && this.hashes[i4] == i; i4--) {
            if (ResultKt.areEqual(obj, this.keyValues[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    public final int indexOfNull() {
        int i = this._size;
        if (i == 0) {
            return -1;
        }
        int binarySearchInternal = ContainerHelpersKt.binarySearchInternal(this.hashes, i, 0);
        if (binarySearchInternal < 0 || this.keyValues[binarySearchInternal << 1] == null) {
            return binarySearchInternal;
        }
        int i2 = binarySearchInternal + 1;
        while (i2 < i && this.hashes[i2] == 0) {
            if (this.keyValues[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = binarySearchInternal - 1; i3 >= 0 && this.hashes[i3] == 0; i3--) {
            if (this.keyValues[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final String toString() {
        int i = this._size;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        int i2 = this._size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            int i4 = i3 << 1;
            Object obj = this.keyValues[i4];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.keyValues[i4 + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        ResultKt.checkNotNullExpressionValue(sb2, "buffer.toString()");
        return sb2;
    }
}
