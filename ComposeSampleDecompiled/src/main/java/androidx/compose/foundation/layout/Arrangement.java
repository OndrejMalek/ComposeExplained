package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class Arrangement {
    public static final Arrangement$End$1 Start = new Object();
    public static final Arrangement$Top$1 Top = new Object();
    public static final Arrangement$Center$1 Center = new Arrangement$Center$1(0);

    /* loaded from: classes.dex */
    public interface Horizontal {
        void arrange(int i, Density density, LayoutDirection layoutDirection, int[] iArr, int[] iArr2);
    }

    /* loaded from: classes.dex */
    public interface Vertical {
        void arrange(Density density, int i, int[] iArr, int[] iArr2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, androidx.compose.foundation.layout.Arrangement$End$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.layout.Arrangement$Top$1, java.lang.Object] */
    static {
        new Arrangement$Center$1(3);
        new Arrangement$Center$1(2);
        new Arrangement$Center$1(1);
    }

    public static void placeCenter$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        ResultKt.checkNotNullParameter(iArr, "size");
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float f = (i - i3) / 2;
        if (!z) {
            int length = iArr.length;
            int i5 = 0;
            while (i2 < length) {
                int i6 = iArr[i2];
                iArr2[i5] = ResultKt.roundToInt(f);
                f += i6;
                i2++;
                i5++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i7 = iArr[length2];
            iArr2[length2] = ResultKt.roundToInt(f);
            f += i7;
        }
    }

    public static void placeLeftOrTop$foundation_layout_release(int[] iArr, int[] iArr2, boolean z) {
        ResultKt.checkNotNullParameter(iArr, "size");
        int i = 0;
        if (!z) {
            int length = iArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int i4 = iArr[i];
                iArr2[i2] = i3;
                i3 += i4;
                i++;
                i2++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i5 = iArr[length2];
            iArr2[length2] = i;
            i += i5;
        }
    }

    public static void placeSpaceAround$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        ResultKt.checkNotNullParameter(iArr, "size");
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float length = (iArr.length == 0) ^ true ? (i - i3) / iArr.length : 0.0f;
        float f = length / 2;
        if (z) {
            for (int length2 = iArr.length - 1; -1 < length2; length2--) {
                int i5 = iArr[length2];
                iArr2[length2] = ResultKt.roundToInt(f);
                f += i5 + length;
            }
            return;
        }
        int length3 = iArr.length;
        int i6 = 0;
        while (i2 < length3) {
            int i7 = iArr[i2];
            iArr2[i6] = ResultKt.roundToInt(f);
            f += i7 + length;
            i2++;
            i6++;
        }
    }

    public static void placeSpaceBetween$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        ResultKt.checkNotNullParameter(iArr, "size");
        if (iArr.length == 0) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float max = (i - i3) / Math.max(iArr.length - 1, 1);
        float f = (z && iArr.length == 1) ? max : 0.0f;
        if (z) {
            for (int length = iArr.length - 1; -1 < length; length--) {
                int i5 = iArr[length];
                iArr2[length] = ResultKt.roundToInt(f);
                f += i5 + max;
            }
            return;
        }
        int length2 = iArr.length;
        int i6 = 0;
        while (i2 < length2) {
            int i7 = iArr[i2];
            iArr2[i6] = ResultKt.roundToInt(f);
            f += i7 + max;
            i2++;
            i6++;
        }
    }

    public static void placeSpaceEvenly$foundation_layout_release(int i, int[] iArr, int[] iArr2, boolean z) {
        ResultKt.checkNotNullParameter(iArr, "size");
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        float length = (i - i3) / (iArr.length + 1);
        if (z) {
            float f = length;
            for (int length2 = iArr.length - 1; -1 < length2; length2--) {
                int i5 = iArr[length2];
                iArr2[length2] = ResultKt.roundToInt(f);
                f += i5 + length;
            }
            return;
        }
        int length3 = iArr.length;
        float f2 = length;
        int i6 = 0;
        while (i2 < length3) {
            int i7 = iArr[i2];
            iArr2[i6] = ResultKt.roundToInt(f2);
            f2 += i7 + length;
            i2++;
            i6++;
        }
    }
}
