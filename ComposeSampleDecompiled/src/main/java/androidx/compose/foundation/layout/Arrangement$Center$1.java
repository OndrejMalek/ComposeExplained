package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Arrangement$Center$1 implements Arrangement.Horizontal, Arrangement.Vertical {
    public final /* synthetic */ int $r8$classId;
    public final float spacing;

    public Arrangement$Center$1(int i) {
        this.$r8$classId = i;
        if (i == 1) {
            this.spacing = 0;
            return;
        }
        if (i == 2) {
            this.spacing = 0;
        } else if (i != 3) {
            this.spacing = 0;
        } else {
            this.spacing = 0;
        }
    }

    @Override // androidx.compose.foundation.layout.Arrangement.Horizontal
    public final void arrange(int i, Density density, LayoutDirection layoutDirection, int[] iArr, int[] iArr2) {
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                if (layoutDirection == layoutDirection2) {
                    Arrangement.placeCenter$foundation_layout_release(i, iArr, iArr2, false);
                    return;
                } else {
                    Arrangement.placeCenter$foundation_layout_release(i, iArr, iArr2, true);
                    return;
                }
            case 1:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                if (layoutDirection == layoutDirection2) {
                    Arrangement.placeSpaceAround$foundation_layout_release(i, iArr, iArr2, false);
                    return;
                } else {
                    Arrangement.placeSpaceAround$foundation_layout_release(i, iArr, iArr2, true);
                    return;
                }
            case 2:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                if (layoutDirection == layoutDirection2) {
                    Arrangement.placeSpaceBetween$foundation_layout_release(i, iArr, iArr2, false);
                    return;
                } else {
                    Arrangement.placeSpaceBetween$foundation_layout_release(i, iArr, iArr2, true);
                    return;
                }
            default:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                if (layoutDirection == layoutDirection2) {
                    Arrangement.placeSpaceEvenly$foundation_layout_release(i, iArr, iArr2, false);
                    return;
                } else {
                    Arrangement.placeSpaceEvenly$foundation_layout_release(i, iArr, iArr2, true);
                    return;
                }
        }
    }

    public final String toString() {
        switch (this.$r8$classId) {
            case 0:
                return "Arrangement#Center";
            case 1:
                return "Arrangement#SpaceAround";
            case 2:
                return "Arrangement#SpaceBetween";
            default:
                return "Arrangement#SpaceEvenly";
        }
    }

    @Override // androidx.compose.foundation.layout.Arrangement.Vertical
    public final void arrange(Density density, int i, int[] iArr, int[] iArr2) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                Arrangement.placeCenter$foundation_layout_release(i, iArr, iArr2, false);
                return;
            case 1:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                Arrangement.placeSpaceAround$foundation_layout_release(i, iArr, iArr2, false);
                return;
            case 2:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                Arrangement.placeSpaceBetween$foundation_layout_release(i, iArr, iArr2, false);
                return;
            default:
                ResultKt.checkNotNullParameter(density, "<this>");
                ResultKt.checkNotNullParameter(iArr, "sizes");
                Arrangement.placeSpaceEvenly$foundation_layout_release(i, iArr, iArr2, false);
                return;
        }
    }
}
