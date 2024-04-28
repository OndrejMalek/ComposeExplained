package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function5;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class RowColumnMeasurementHelper {
    public final Function5 arrangement;
    public final float arrangementSpacing;
    public final PaddingKt crossAxisAlignment;
    public final int crossAxisSize;
    public final List measurables;
    public final int orientation;
    public final Placeable[] placeables;
    public final PaddingKt[] rowColumnParentData;

    public RowColumnMeasurementHelper(int i, Function5 function5, float f, int i2, PaddingKt paddingKt, List list, Placeable[] placeableArr) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "orientation");
        ResultKt.checkNotNullParameter(function5, "arrangement");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i2, "crossAxisSize");
        ResultKt.checkNotNullParameter(paddingKt, "crossAxisAlignment");
        this.orientation = i;
        this.arrangement = function5;
        this.arrangementSpacing = f;
        this.crossAxisSize = i2;
        this.crossAxisAlignment = paddingKt;
        this.measurables = list;
        this.placeables = placeableArr;
        int size = list.size();
        PaddingKt[] paddingKtArr = new PaddingKt[size];
        for (int i3 = 0; i3 < size; i3++) {
            Measurable measurable = (Measurable) this.measurables.get(i3);
            ResultKt.checkNotNullParameter(measurable, "<this>");
            measurable.getParentData();
            paddingKtArr[i3] = null;
        }
        this.rowColumnParentData = paddingKtArr;
    }

    public final int mainAxisSize(Placeable placeable) {
        ResultKt.checkNotNullParameter(placeable, "<this>");
        return this.orientation == 1 ? placeable.width : placeable.height;
    }
}
