package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PaddingValuesModifier extends Modifier.Node implements LayoutModifierNode {
    public PaddingValuesImpl paddingValues;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        PaddingValuesImpl paddingValuesImpl = this.paddingValues;
        LayoutDirection layoutDirection = measureScope.getLayoutDirection();
        paddingValuesImpl.getClass();
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        float f = 0;
        if (Float.compare(layoutDirection == layoutDirection2 ? paddingValuesImpl.start : paddingValuesImpl.end, f) >= 0 && Float.compare(this.paddingValues.top, f) >= 0) {
            PaddingValuesImpl paddingValuesImpl2 = this.paddingValues;
            LayoutDirection layoutDirection3 = measureScope.getLayoutDirection();
            paddingValuesImpl2.getClass();
            ResultKt.checkNotNullParameter(layoutDirection3, "layoutDirection");
            if (Float.compare(layoutDirection3 == layoutDirection2 ? paddingValuesImpl2.end : paddingValuesImpl2.start, f) >= 0 && Float.compare(this.paddingValues.bottom, f) >= 0) {
                PaddingValuesImpl paddingValuesImpl3 = this.paddingValues;
                LayoutDirection layoutDirection4 = measureScope.getLayoutDirection();
                paddingValuesImpl3.getClass();
                ResultKt.checkNotNullParameter(layoutDirection4, "layoutDirection");
                int mo180roundToPx0680j_4 = measureScope.mo180roundToPx0680j_4(layoutDirection4 == layoutDirection2 ? paddingValuesImpl3.start : paddingValuesImpl3.end);
                PaddingValuesImpl paddingValuesImpl4 = this.paddingValues;
                LayoutDirection layoutDirection5 = measureScope.getLayoutDirection();
                paddingValuesImpl4.getClass();
                ResultKt.checkNotNullParameter(layoutDirection5, "layoutDirection");
                int mo180roundToPx0680j_42 = measureScope.mo180roundToPx0680j_4(layoutDirection5 == layoutDirection2 ? paddingValuesImpl4.end : paddingValuesImpl4.start) + mo180roundToPx0680j_4;
                int mo180roundToPx0680j_43 = measureScope.mo180roundToPx0680j_4(this.paddingValues.bottom) + measureScope.mo180roundToPx0680j_4(this.paddingValues.top);
                int i = -mo180roundToPx0680j_42;
                int i2 = -mo180roundToPx0680j_43;
                int m278getMinWidthimpl = Constraints.m278getMinWidthimpl(j) + i;
                if (m278getMinWidthimpl < 0) {
                    m278getMinWidthimpl = 0;
                }
                int m276getMaxWidthimpl = Constraints.m276getMaxWidthimpl(j);
                if (m276getMaxWidthimpl != Integer.MAX_VALUE && (m276getMaxWidthimpl = m276getMaxWidthimpl + i) < 0) {
                    m276getMaxWidthimpl = 0;
                }
                int m277getMinHeightimpl = Constraints.m277getMinHeightimpl(j) + i2;
                if (m277getMinHeightimpl < 0) {
                    m277getMinHeightimpl = 0;
                }
                int m275getMaxHeightimpl = Constraints.m275getMaxHeightimpl(j);
                if (m275getMaxHeightimpl != Integer.MAX_VALUE) {
                    int i3 = m275getMaxHeightimpl + i2;
                    m275getMaxHeightimpl = i3 >= 0 ? i3 : 0;
                }
                Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(ResultKt.Constraints(m278getMinWidthimpl, m276getMaxWidthimpl, m277getMinHeightimpl, m275getMaxHeightimpl));
                return MeasureScope.layout$default(measureScope, ResultKt.coerceIn(mo164measureBRTryo0.width + mo180roundToPx0680j_42, Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j)), ResultKt.coerceIn(mo164measureBRTryo0.height + mo180roundToPx0680j_43, Constraints.m277getMinHeightimpl(j), Constraints.m275getMaxHeightimpl(j)), new PaddingValuesModifier$measure$2(mo164measureBRTryo0, measureScope, this));
            }
        }
        throw new IllegalArgumentException("Padding must be non-negative".toString());
    }
}
