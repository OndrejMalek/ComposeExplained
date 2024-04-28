package androidx.compose.runtime;

import java.util.Arrays;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class IntStack {
    public final /* synthetic */ int $r8$classId;
    public int[] slots;
    public int tos;

    public IntStack() {
        this.$r8$classId = 0;
        this.slots = new int[10];
    }

    public final int pop() {
        switch (this.$r8$classId) {
            case 0:
                int[] iArr = this.slots;
                int i = this.tos - 1;
                this.tos = i;
                return iArr[i];
            default:
                int[] iArr2 = this.slots;
                int i2 = this.tos - 1;
                this.tos = i2;
                return iArr2[i2];
        }
    }

    public final void push(int i) {
        int i2 = this.tos;
        int[] iArr = this.slots;
        if (i2 >= iArr.length) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length * 2);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.slots = copyOf;
        }
        int[] iArr2 = this.slots;
        int i3 = this.tos;
        this.tos = i3 + 1;
        iArr2[i3] = i;
    }

    public final void pushDiagonal(int i, int i2, int i3) {
        int i4 = this.tos;
        int i5 = i4 + 3;
        int[] iArr = this.slots;
        if (i5 >= iArr.length) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length * 2);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.slots = copyOf;
        }
        int[] iArr2 = this.slots;
        iArr2[i4] = i + i3;
        iArr2[i4 + 1] = i2 + i3;
        iArr2[i4 + 2] = i3;
        this.tos = i5;
    }

    public final void pushRange(int i, int i2, int i3, int i4) {
        int i5 = this.tos;
        int i6 = i5 + 4;
        int[] iArr = this.slots;
        if (i6 >= iArr.length) {
            int[] copyOf = Arrays.copyOf(iArr, iArr.length * 2);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.slots = copyOf;
        }
        int[] iArr2 = this.slots;
        iArr2[i5] = i;
        iArr2[i5 + 1] = i2;
        iArr2[i5 + 2] = i3;
        iArr2[i5 + 3] = i4;
        this.tos = i6;
    }

    public final void quickSort(int i, int i2) {
        if (i < i2) {
            int i3 = i - 3;
            for (int i4 = i; i4 < i2; i4 += 3) {
                int[] iArr = this.slots;
                int i5 = iArr[i4];
                int i6 = iArr[i2];
                if (i5 < i6 || (i5 == i6 && iArr[i4 + 1] <= iArr[i2 + 1])) {
                    i3 += 3;
                    swapDiagonal(i3, i4);
                }
            }
            swapDiagonal(i3 + 3, i2);
            quickSort(i, i3);
            quickSort(i3 + 6, i2);
        }
    }

    public final void swapDiagonal(int i, int i2) {
        int[] iArr = this.slots;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
        int i4 = i + 1;
        int i5 = i2 + 1;
        int i6 = iArr[i4];
        iArr[i4] = iArr[i5];
        iArr[i5] = i6;
        int i7 = i + 2;
        int i8 = i2 + 2;
        int i9 = iArr[i7];
        iArr[i7] = iArr[i8];
        iArr[i8] = i9;
    }

    public IntStack(int i) {
        this.$r8$classId = 1;
        this.slots = new int[i];
    }
}
