package androidx.compose.ui.layout;

import androidx.compose.foundation.layout.FillNode$measure$1;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class RootMeasurePolicy extends LayoutNode.NoIntrinsicsMeasurePolicy {
    public static final RootMeasurePolicy INSTANCE = new Object();

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo34measure3p2s80s(MeasureScope measureScope, List list, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        if (list.isEmpty()) {
            return MeasureScope.layout$default(measureScope, Constraints.m278getMinWidthimpl(j), Constraints.m277getMinHeightimpl(j), RootMeasurePolicy$measure$1.INSTANCE);
        }
        int i = 0;
        if (list.size() == 1) {
            Placeable mo164measureBRTryo0 = ((Measurable) list.get(0)).mo164measureBRTryo0(j);
            return MeasureScope.layout$default(measureScope, ResultKt.coerceIn(mo164measureBRTryo0.width, Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j)), ResultKt.coerceIn(mo164measureBRTryo0.height, Constraints.m277getMinHeightimpl(j), Constraints.m275getMaxHeightimpl(j)), new FillNode$measure$1(mo164measureBRTryo0, 4));
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(((Measurable) list.get(i2)).mo164measureBRTryo0(j));
        }
        int size2 = arrayList.size();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size2; i5++) {
            Placeable placeable = (Placeable) arrayList.get(i5);
            i3 = Math.max(placeable.width, i3);
            i4 = Math.max(placeable.height, i4);
        }
        return MeasureScope.layout$default(measureScope, ResultKt.coerceIn(i3, Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j)), ResultKt.coerceIn(i4, Constraints.m277getMinHeightimpl(j), Constraints.m275getMaxHeightimpl(j)), new RootMeasurePolicy$measure$4(i, arrayList));
    }
}
