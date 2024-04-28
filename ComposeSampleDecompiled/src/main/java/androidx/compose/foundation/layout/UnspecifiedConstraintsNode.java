package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class UnspecifiedConstraintsNode extends Modifier.Node implements LayoutModifierNode {
    public float minHeight;
    public float minWidth;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m278getMinWidthimpl;
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        int i = 0;
        if (Dp.m280equalsimpl0(this.minWidth, Float.NaN) || Constraints.m278getMinWidthimpl(j) != 0) {
            m278getMinWidthimpl = Constraints.m278getMinWidthimpl(j);
        } else {
            m278getMinWidthimpl = measureScope.mo180roundToPx0680j_4(this.minWidth);
            int m276getMaxWidthimpl = Constraints.m276getMaxWidthimpl(j);
            if (m278getMinWidthimpl > m276getMaxWidthimpl) {
                m278getMinWidthimpl = m276getMaxWidthimpl;
            }
            if (m278getMinWidthimpl < 0) {
                m278getMinWidthimpl = 0;
            }
        }
        int m276getMaxWidthimpl2 = Constraints.m276getMaxWidthimpl(j);
        if (Dp.m280equalsimpl0(this.minHeight, Float.NaN) || Constraints.m277getMinHeightimpl(j) != 0) {
            i = Constraints.m277getMinHeightimpl(j);
        } else {
            int mo180roundToPx0680j_4 = measureScope.mo180roundToPx0680j_4(this.minHeight);
            int m275getMaxHeightimpl = Constraints.m275getMaxHeightimpl(j);
            if (mo180roundToPx0680j_4 > m275getMaxHeightimpl) {
                mo180roundToPx0680j_4 = m275getMaxHeightimpl;
            }
            if (mo180roundToPx0680j_4 >= 0) {
                i = mo180roundToPx0680j_4;
            }
        }
        Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(ResultKt.Constraints(m278getMinWidthimpl, m276getMaxWidthimpl2, i, Constraints.m275getMaxHeightimpl(j)));
        return MeasureScope.layout$default(measureScope, mo164measureBRTryo0.width, mo164measureBRTryo0.height, new FillNode$measure$1(mo164measureBRTryo0, 1));
    }
}
