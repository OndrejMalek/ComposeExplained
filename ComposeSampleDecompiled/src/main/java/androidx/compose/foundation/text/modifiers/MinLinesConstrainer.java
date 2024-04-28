package androidx.compose.foundation.text.modifiers;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class MinLinesConstrainer {
    public static MinLinesConstrainer last;
    public final Density density;
    public final FontFamily.Resolver fontFamilyResolver;
    public final TextStyle inputTextStyle;
    public final LayoutDirection layoutDirection;
    public float lineHeightCache = Float.NaN;
    public float oneLineHeightCache = Float.NaN;
    public final TextStyle resolvedStyle;

    public MinLinesConstrainer(LayoutDirection layoutDirection, TextStyle textStyle, Density density, FontFamily.Resolver resolver) {
        this.layoutDirection = layoutDirection;
        this.inputTextStyle = textStyle;
        this.density = density;
        this.fontFamilyResolver = resolver;
        this.resolvedStyle = _BOUNDARY.resolveDefaults(textStyle, layoutDirection);
    }

    /* renamed from: coerceMinLines-Oh53vG4$foundation_release, reason: not valid java name */
    public final long m40coerceMinLinesOh53vG4$foundation_release(long j, int i) {
        int m277getMinHeightimpl;
        float f = this.oneLineHeightCache;
        float f2 = this.lineHeightCache;
        if (Float.isNaN(f) || Float.isNaN(f2)) {
            float height = _BOUNDARY.m2ParagraphUdtVg6A$default(MinLinesConstrainerKt.EmptyTextReplacement, this.resolvedStyle, ResultKt.Constraints$default(0, 0, 15), this.density, this.fontFamilyResolver, 1).getHeight();
            float height2 = _BOUNDARY.m2ParagraphUdtVg6A$default(MinLinesConstrainerKt.TwoLineTextReplacement, this.resolvedStyle, ResultKt.Constraints$default(0, 0, 15), this.density, this.fontFamilyResolver, 2).getHeight() - height;
            this.oneLineHeightCache = height;
            this.lineHeightCache = height2;
            f2 = height2;
            f = height;
        }
        if (i != 1) {
            int roundToInt = ResultKt.roundToInt((f2 * (i - 1)) + f);
            m277getMinHeightimpl = roundToInt >= 0 ? roundToInt : 0;
            int m275getMaxHeightimpl = Constraints.m275getMaxHeightimpl(j);
            if (m277getMinHeightimpl > m275getMaxHeightimpl) {
                m277getMinHeightimpl = m275getMaxHeightimpl;
            }
        } else {
            m277getMinHeightimpl = Constraints.m277getMinHeightimpl(j);
        }
        return ResultKt.Constraints(Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j), m277getMinHeightimpl, Constraints.m275getMaxHeightimpl(j));
    }
}
