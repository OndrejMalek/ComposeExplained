package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FillNode extends Modifier.Node implements LayoutModifierNode {
    public int direction;
    public float fraction;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m278getMinWidthimpl;
        int m276getMaxWidthimpl;
        int m275getMaxHeightimpl;
        int i;
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        if ((Constraints.WidthMask[(int) (3 & j)] & ((int) (j >> 33))) == 0 || this.direction == 1) {
            m278getMinWidthimpl = Constraints.m278getMinWidthimpl(j);
            m276getMaxWidthimpl = Constraints.m276getMaxWidthimpl(j);
        } else {
            m278getMinWidthimpl = ResultKt.coerceIn(ResultKt.roundToInt(Constraints.m276getMaxWidthimpl(j) * this.fraction), Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j));
            m276getMaxWidthimpl = m278getMinWidthimpl;
        }
        if (!Constraints.m274getHasBoundedHeightimpl(j) || this.direction == 2) {
            int m277getMinHeightimpl = Constraints.m277getMinHeightimpl(j);
            m275getMaxHeightimpl = Constraints.m275getMaxHeightimpl(j);
            i = m277getMinHeightimpl;
        } else {
            i = ResultKt.coerceIn(ResultKt.roundToInt(Constraints.m275getMaxHeightimpl(j) * this.fraction), Constraints.m277getMinHeightimpl(j), Constraints.m275getMaxHeightimpl(j));
            m275getMaxHeightimpl = i;
        }
        Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(ResultKt.Constraints(m278getMinWidthimpl, m276getMaxWidthimpl, i, m275getMaxHeightimpl));
        return MeasureScope.layout$default(measureScope, mo164measureBRTryo0.width, mo164measureBRTryo0.height, new FillNode$measure$1(mo164measureBRTryo0, 0));
    }
}
