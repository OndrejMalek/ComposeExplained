package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;

/* loaded from: classes.dex */
public abstract class RowKt {
    public static final RowColumnImplKt$rowColumnMeasurePolicy$1 DefaultRowMeasurePolicy;

    static {
        Arrangement$End$1 arrangement$End$1 = Arrangement.Start;
        DefaultRowMeasurePolicy = PaddingKt.m36rowColumnMeasurePolicyTDGSqEk(1, RowKt$DefaultRowMeasurePolicy$1.INSTANCE, 0, new CrossAxisAlignment$VerticalCrossAxisAlignment(Alignment.Companion.Top));
    }
}
