package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class PaddingValuesModifier$measure$2 extends Lambda implements Function1 {
    public final /* synthetic */ Object $placeable;
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ MeasureScope $this_measure;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PaddingValuesModifier$measure$2(RowColumnMeasurementHelper rowColumnMeasurementHelper, RowColumnMeasureHelperResult rowColumnMeasureHelperResult, MeasureScope measureScope) {
        super(1);
        this.$placeable = rowColumnMeasurementHelper;
        this.this$0 = rowColumnMeasureHelperResult;
        this.$this_measure = measureScope;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        int i;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        int i2 = this.$r8$classId;
        MeasureScope measureScope = this.$this_measure;
        Object obj = this.this$0;
        Object obj2 = this.$placeable;
        switch (i2) {
            case 0:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable placeable = (Placeable) obj2;
                PaddingValuesModifier paddingValuesModifier = (PaddingValuesModifier) obj;
                PaddingValuesImpl paddingValuesImpl = paddingValuesModifier.paddingValues;
                LayoutDirection layoutDirection2 = measureScope.getLayoutDirection();
                paddingValuesImpl.getClass();
                ResultKt.checkNotNullParameter(layoutDirection2, "layoutDirection");
                Placeable.PlacementScope.place$default(placementScope, placeable, measureScope.mo180roundToPx0680j_4(layoutDirection2 == layoutDirection ? paddingValuesImpl.start : paddingValuesImpl.end), measureScope.mo180roundToPx0680j_4(paddingValuesModifier.paddingValues.top));
                return;
            default:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                RowColumnMeasurementHelper rowColumnMeasurementHelper = (RowColumnMeasurementHelper) obj2;
                RowColumnMeasureHelperResult rowColumnMeasureHelperResult = (RowColumnMeasureHelperResult) obj;
                LayoutDirection layoutDirection3 = measureScope.getLayoutDirection();
                rowColumnMeasurementHelper.getClass();
                ResultKt.checkNotNullParameter(rowColumnMeasureHelperResult, "measureResult");
                ResultKt.checkNotNullParameter(layoutDirection3, "layoutDirection");
                int i3 = rowColumnMeasureHelperResult.startIndex;
                for (int i4 = i3; i4 < rowColumnMeasureHelperResult.endIndex; i4++) {
                    Placeable placeable2 = rowColumnMeasurementHelper.placeables[i4];
                    ResultKt.checkNotNull(placeable2);
                    ((Measurable) rowColumnMeasurementHelper.measurables.get(i4)).getParentData();
                    int i5 = rowColumnMeasurementHelper.orientation;
                    if (i5 == 1) {
                        i = placeable2.height;
                    } else {
                        i = placeable2.width;
                    }
                    int align$foundation_layout_release = rowColumnMeasurementHelper.crossAxisAlignment.align$foundation_layout_release(rowColumnMeasureHelperResult.crossAxisSize - i, i5 == 1 ? layoutDirection : layoutDirection3, placeable2);
                    int[] iArr = rowColumnMeasureHelperResult.mainAxisPositions;
                    if (i5 == 1) {
                        Placeable.PlacementScope.place$default(placementScope, placeable2, iArr[i4 - i3], align$foundation_layout_release);
                    } else {
                        Placeable.PlacementScope.place$default(placementScope, placeable2, align$foundation_layout_release, iArr[i4 - i3]);
                    }
                }
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PaddingValuesModifier$measure$2(Placeable placeable, MeasureScope measureScope, PaddingValuesModifier paddingValuesModifier) {
        super(1);
        this.$placeable = placeable;
        this.$this_measure = measureScope;
        this.this$0 = paddingValuesModifier;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Placeable.PlacementScope) obj);
                return unit;
            default:
                invoke((Placeable.PlacementScope) obj);
                return unit;
        }
    }
}
