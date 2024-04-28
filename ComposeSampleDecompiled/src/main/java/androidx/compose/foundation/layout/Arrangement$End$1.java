package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Arrangement$End$1 implements Arrangement.Horizontal {
    @Override // androidx.compose.foundation.layout.Arrangement.Horizontal
    public final void arrange(int i, Density density, LayoutDirection layoutDirection, int[] iArr, int[] iArr2) {
        ResultKt.checkNotNullParameter(density, "<this>");
        ResultKt.checkNotNullParameter(iArr, "sizes");
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        if (layoutDirection == LayoutDirection.Ltr) {
            Arrangement.placeLeftOrTop$foundation_layout_release(iArr, iArr2, false);
            return;
        }
        Arrangement$End$1 arrangement$End$1 = Arrangement.Start;
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        int i4 = i - i2;
        int length = iArr.length;
        while (true) {
            length--;
            if (-1 >= length) {
                return;
            }
            int i5 = iArr[length];
            iArr2[length] = i4;
            i4 += i5;
        }
    }

    public final String toString() {
        return "Arrangement#Start";
    }
}
