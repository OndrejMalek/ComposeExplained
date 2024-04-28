package androidx.compose.foundation.text;

import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class EmptyMeasurePolicy implements MeasurePolicy {
    public static final EmptyMeasurePolicy INSTANCE = new Object();

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo34measure3p2s80s(MeasureScope measureScope, List list, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        return MeasureScope.layout$default(measureScope, Constraints.m276getMaxWidthimpl(j), Constraints.m275getMaxHeightimpl(j), EmptyMeasurePolicy$placementBlock$1.INSTANCE);
    }
}
