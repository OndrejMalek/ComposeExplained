package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.jvm.functions.Function5;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class PaddingKt {
    public static final Modifier padding(Modifier modifier, PaddingValuesImpl paddingValuesImpl) {
        ResultKt.checkNotNullParameter(modifier, "<this>");
        ResultKt.checkNotNullParameter(paddingValuesImpl, "paddingValues");
        return modifier.then(new PaddingValuesElement(paddingValuesImpl, new AbstractMap$toString$1(4, paddingValuesImpl)));
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1] */
    /* renamed from: rowColumnMeasurePolicy-TDGSqEk, reason: not valid java name */
    public static final RowColumnImplKt$rowColumnMeasurePolicy$1 m36rowColumnMeasurePolicyTDGSqEk(final int i, final Function5 function5, final float f, final CrossAxisAlignment$VerticalCrossAxisAlignment crossAxisAlignment$VerticalCrossAxisAlignment) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "orientation");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(1, "crossAxisSize");
        return new MeasurePolicy() { // from class: androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1
            public final /* synthetic */ int $crossAxisSize = 1;

            /* JADX DEBUG: Multi-variable search result rejected for r1v10, resolved type: kotlin.jvm.functions.Function5 */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r10v3, types: [int[], java.io.Serializable] */
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public final MeasureScope$layout$1 mo34measure3p2s80s(MeasureScope measureScope, List list, long j) {
                Placeable[] placeableArr;
                int i2;
                int max;
                String str;
                int i3;
                long Constraints;
                ResultKt.checkNotNullParameter(measureScope, "$this$measure");
                RowColumnMeasurementHelper rowColumnMeasurementHelper = new RowColumnMeasurementHelper(i, function5, f, this.$crossAxisSize, crossAxisAlignment$VerticalCrossAxisAlignment, list, new Placeable[list.size()]);
                int size = list.size();
                int i4 = rowColumnMeasurementHelper.orientation;
                String str2 = "orientation";
                DurationKt$$ExternalSyntheticCheckNotZero0.m(i4, "orientation");
                long Constraints2 = ResultKt.Constraints(i4 == 1 ? Constraints.m278getMinWidthimpl(j) : Constraints.m277getMinHeightimpl(j), i4 == 1 ? Constraints.m276getMaxWidthimpl(j) : Constraints.m275getMaxHeightimpl(j), i4 == 1 ? Constraints.m277getMinHeightimpl(j) : Constraints.m278getMinWidthimpl(j), i4 == 1 ? Constraints.m275getMaxHeightimpl(j) : Constraints.m276getMaxWidthimpl(j));
                int mo180roundToPx0680j_4 = measureScope.mo180roundToPx0680j_4(rowColumnMeasurementHelper.arrangementSpacing);
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                while (true) {
                    placeableArr = rowColumnMeasurementHelper.placeables;
                    if (i5 >= size) {
                        break;
                    }
                    Measurable measurable = (Measurable) rowColumnMeasurementHelper.measurables.get(i5);
                    PaddingKt paddingKt = rowColumnMeasurementHelper.rowColumnParentData[i5];
                    int m276getMaxWidthimpl = Constraints.m276getMaxWidthimpl(Constraints2);
                    Placeable placeable = placeableArr[i5];
                    if (placeable == null) {
                        long Constraints3 = ResultKt.Constraints(0, m276getMaxWidthimpl != Integer.MAX_VALUE ? m276getMaxWidthimpl - i6 : Integer.MAX_VALUE, 0, Constraints.m275getMaxHeightimpl(Constraints2));
                        DurationKt$$ExternalSyntheticCheckNotZero0.m(i4, str2);
                        if (i4 == 1) {
                            str = str2;
                            Constraints = ResultKt.Constraints(Constraints.m278getMinWidthimpl(Constraints3), Constraints.m276getMaxWidthimpl(Constraints3), Constraints.m277getMinHeightimpl(Constraints3), Constraints.m275getMaxHeightimpl(Constraints3));
                        } else {
                            str = str2;
                            Constraints = ResultKt.Constraints(Constraints.m277getMinHeightimpl(Constraints3), Constraints.m275getMaxHeightimpl(Constraints3), Constraints.m278getMinWidthimpl(Constraints3), Constraints.m276getMaxWidthimpl(Constraints3));
                        }
                        i3 = size;
                        placeable = measurable.mo164measureBRTryo0(Constraints);
                    } else {
                        str = str2;
                        i3 = size;
                    }
                    Placeable placeable2 = placeable;
                    i7 = Math.min(mo180roundToPx0680j_4, (m276getMaxWidthimpl - i6) - rowColumnMeasurementHelper.mainAxisSize(placeable2));
                    i6 += rowColumnMeasurementHelper.mainAxisSize(placeable2) + i7;
                    i8 = Math.max(i8, i4 == 1 ? placeable2.height : placeable2.width);
                    placeableArr[i5] = placeable2;
                    i5++;
                    size = i3;
                    str2 = str;
                }
                int i9 = size;
                int max2 = Math.max(i6 - i7, Constraints.m278getMinWidthimpl(Constraints2));
                if (Constraints.m275getMaxHeightimpl(Constraints2) == Integer.MAX_VALUE || rowColumnMeasurementHelper.crossAxisSize != 2) {
                    i2 = 0;
                    max = Math.max(i8, Math.max(Constraints.m277getMinHeightimpl(Constraints2), 0));
                } else {
                    max = Constraints.m275getMaxHeightimpl(Constraints2);
                    i2 = 0;
                }
                ?? r10 = new int[i9];
                for (int i10 = i2; i10 < i9; i10++) {
                    r10[i10] = i2;
                }
                int[] iArr = new int[i9];
                while (i2 < i9) {
                    Placeable placeable3 = placeableArr[i2];
                    ResultKt.checkNotNull(placeable3);
                    iArr[i2] = rowColumnMeasurementHelper.mainAxisSize(placeable3);
                    i2++;
                }
                rowColumnMeasurementHelper.arrangement.invoke(Integer.valueOf(max2), iArr, measureScope.getLayoutDirection(), measureScope, r10);
                RowColumnMeasureHelperResult rowColumnMeasureHelperResult = new RowColumnMeasureHelperResult(max, max2, i9, r10);
                if (i != 1) {
                    int i11 = max;
                    max = max2;
                    max2 = i11;
                }
                return MeasureScope.layout$default(measureScope, max2, max, new PaddingValuesModifier$measure$2(rowColumnMeasurementHelper, rowColumnMeasureHelperResult, measureScope));
            }
        };
    }

    public abstract int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable);
}
