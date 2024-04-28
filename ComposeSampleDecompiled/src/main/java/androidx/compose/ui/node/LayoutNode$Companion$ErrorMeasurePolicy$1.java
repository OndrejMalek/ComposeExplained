package androidx.compose.ui.node;

import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.node.LayoutNode;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LayoutNode$Companion$ErrorMeasurePolicy$1 extends LayoutNode.NoIntrinsicsMeasurePolicy {
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo34measure3p2s80s(MeasureScope measureScope, List list, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        throw new IllegalStateException("Undefined measure and it is required".toString());
    }
}
