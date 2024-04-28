package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CrossAxisAlignment$VerticalCrossAxisAlignment extends PaddingKt {
    public final /* synthetic */ int $r8$classId;
    public final Object vertical;

    public CrossAxisAlignment$VerticalCrossAxisAlignment() {
        BiasAlignment.Horizontal horizontal = Alignment.Companion.Start;
        this.$r8$classId = 1;
        this.vertical = horizontal;
    }

    @Override // androidx.compose.foundation.layout.PaddingKt
    public final int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable) {
        int i2 = this.$r8$classId;
        Object obj = this.vertical;
        switch (i2) {
            case 0:
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                return ResultKt.roundToInt((1 + ((BiasAlignment.Vertical) ((Alignment.Vertical) obj)).bias) * (i / 2.0f));
            default:
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                return ((BiasAlignment.Horizontal) ((Alignment.Horizontal) obj)).align(i, layoutDirection);
        }
    }

    public CrossAxisAlignment$VerticalCrossAxisAlignment(BiasAlignment.Vertical vertical) {
        this.$r8$classId = 0;
        this.vertical = vertical;
    }
}
