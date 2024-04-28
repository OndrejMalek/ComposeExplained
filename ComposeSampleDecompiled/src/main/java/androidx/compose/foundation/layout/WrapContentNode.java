package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class WrapContentNode extends Modifier.Node implements LayoutModifierNode {
    public Function2 alignmentCallback;
    public int direction;
    public boolean unbounded;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        final Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(ResultKt.Constraints(this.direction != 1 ? 0 : Constraints.m278getMinWidthimpl(j), (this.direction == 1 || !this.unbounded) ? Constraints.m276getMaxWidthimpl(j) : Integer.MAX_VALUE, this.direction == 2 ? Constraints.m277getMinHeightimpl(j) : 0, (this.direction == 2 || !this.unbounded) ? Constraints.m275getMaxHeightimpl(j) : Integer.MAX_VALUE));
        final int coerceIn = ResultKt.coerceIn(mo164measureBRTryo0.width, Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j));
        final int coerceIn2 = ResultKt.coerceIn(mo164measureBRTryo0.height, Constraints.m277getMinHeightimpl(j), Constraints.m275getMaxHeightimpl(j));
        return MeasureScope.layout$default(measureScope, coerceIn, coerceIn2, new Function1() { // from class: androidx.compose.foundation.layout.WrapContentNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ResultKt.checkNotNullParameter((Placeable.PlacementScope) obj, "$this$layout");
                Function2 function2 = WrapContentNode.this.alignmentCallback;
                Placeable placeable = mo164measureBRTryo0;
                Placeable.PlacementScope.m168place70tqf50(placeable, ((IntOffset) function2.invoke(new IntSize(ResultKt.IntSize(coerceIn - placeable.width, coerceIn2 - placeable.height)), measureScope.getLayoutDirection())).packedValue, 0.0f);
                return Unit.INSTANCE;
            }
        });
    }
}
