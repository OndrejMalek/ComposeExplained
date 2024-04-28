package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Arrangement$Top$1 implements Arrangement.Vertical {
    @Override // androidx.compose.foundation.layout.Arrangement.Vertical
    public final void arrange(Density density, int i, int[] iArr, int[] iArr2) {
        ResultKt.checkNotNullParameter(density, "<this>");
        ResultKt.checkNotNullParameter(iArr, "sizes");
        Arrangement.placeLeftOrTop$foundation_layout_release(iArr, iArr2, false);
    }

    public final String toString() {
        return "Arrangement#Top";
    }
}
