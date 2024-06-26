package androidx.collection;

/* loaded from: classes.dex */
public final class SparseArrayCompat implements Cloneable {
    public static final Object DELETED = new Object();
    public int[] mKeys;
    public int mSize;
    public Object[] mValues;

    public SparseArrayCompat() {
        int i;
        int i2 = 4;
        while (true) {
            i = 40;
            if (i2 >= 32) {
                break;
            }
            int i3 = (1 << i2) - 12;
            if (40 <= i3) {
                i = i3;
                break;
            }
            i2++;
        }
        int i4 = i / 4;
        this.mKeys = new int[i4];
        this.mValues = new Object[i4];
    }

    public final Object clone() {
        try {
            SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) super.clone();
            sparseArrayCompat.mKeys = (int[]) this.mKeys.clone();
            sparseArrayCompat.mValues = (Object[]) this.mValues.clone();
            return sparseArrayCompat;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final Object get(int i) {
        Object obj;
        int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch < 0 || (obj = this.mValues[binarySearch]) == DELETED) {
            return null;
        }
        return obj;
    }

    public final void put(int i, Cloneable cloneable) {
        int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = cloneable;
            return;
        }
        int i2 = ~binarySearch;
        int i3 = this.mSize;
        if (i2 < i3) {
            Object[] objArr = this.mValues;
            if (objArr[i2] == DELETED) {
                this.mKeys[i2] = i;
                objArr[i2] = cloneable;
                return;
            }
        }
        if (i3 >= this.mKeys.length) {
            int i4 = (i3 + 1) * 4;
            int i5 = 4;
            while (true) {
                if (i5 >= 32) {
                    break;
                }
                int i6 = (1 << i5) - 12;
                if (i4 <= i6) {
                    i4 = i6;
                    break;
                }
                i5++;
            }
            int i7 = i4 / 4;
            int[] iArr = new int[i7];
            Object[] objArr2 = new Object[i7];
            int[] iArr2 = this.mKeys;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.mValues;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.mKeys = iArr;
            this.mValues = objArr2;
        }
        int i8 = this.mSize - i2;
        if (i8 != 0) {
            int[] iArr3 = this.mKeys;
            int i9 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i9, i8);
            Object[] objArr4 = this.mValues;
            System.arraycopy(objArr4, i2, objArr4, i9, this.mSize - i2);
        }
        this.mKeys[i2] = i;
        this.mValues[i2] = cloneable;
        this.mSize++;
    }

    public final String toString() {
        int i = this.mSize;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.mSize; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(this.mKeys[i2]);
            sb.append('=');
            Object obj = this.mValues[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
