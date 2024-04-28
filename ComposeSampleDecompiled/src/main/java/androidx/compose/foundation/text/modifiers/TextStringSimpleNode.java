package androidx.compose.foundation.text.modifiers;

import _COROUTINE.ArtificialStackFrames;
import _COROUTINE._BOUNDARY;
import androidx.compose.foundation.layout.FillNode$measure$1;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.collections.EmptyList;
import kotlin.reflect.KProperty;

/* loaded from: classes.dex */
public final class TextStringSimpleNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public ParagraphLayoutCache _layoutCache;
    public Map baselineCache;
    public FontFamily.Resolver fontFamilyResolver;
    public int maxLines;
    public int minLines;
    public int overflow;
    public AbstractMap$toString$1 semanticsTextLayoutResult;
    public boolean softWrap;
    public TextStyle style;
    public String text;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        AbstractMap$toString$1 abstractMap$toString$1 = this.semanticsTextLayoutResult;
        if (abstractMap$toString$1 == null) {
            abstractMap$toString$1 = new AbstractMap$toString$1(7, this);
            this.semanticsTextLayoutResult = abstractMap$toString$1;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text);
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        semanticsConfiguration.set(SemanticsProperties.Text, ResultKt.listOf(annotatedString));
        semanticsConfiguration.set(SemanticsActions.GetTextLayoutResult, new AccessibilityAction(null, abstractMap$toString$1));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
        AndroidParagraph androidParagraph = getLayoutCache().paragraph;
        if (androidParagraph == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Canvas canvas = ((LayoutNodeDrawScope) contentDrawScope).canvasDrawScope.drawContext.getCanvas();
        boolean z = getLayoutCache().didOverflow;
        if (z) {
            Rect m4Recttz77jQw = _BOUNDARY.m4Recttz77jQw(Offset.Zero, _BOUNDARY.Size((int) (getLayoutCache().layoutSize >> 32), (int) (getLayoutCache().layoutSize & 4294967295L)));
            canvas.save();
            canvas.mo88clipRectN_I0leg(m4Recttz77jQw.left, m4Recttz77jQw.top, m4Recttz77jQw.right, m4Recttz77jQw.bottom, 1);
        }
        try {
            SpanStyle spanStyle = this.style.spanStyle;
            TextDecoration textDecoration = spanStyle.textDecoration;
            if (textDecoration == null) {
                textDecoration = TextDecoration.None;
            }
            TextDecoration textDecoration2 = textDecoration;
            Shadow shadow = spanStyle.shadow;
            if (shadow == null) {
                shadow = Shadow.None;
            }
            Shadow shadow2 = shadow;
            DrawStyle drawStyle = spanStyle.drawStyle;
            if (drawStyle == null) {
                drawStyle = Fill.INSTANCE;
            }
            DrawStyle drawStyle2 = drawStyle;
            Brush brush = spanStyle.textForegroundStyle.getBrush();
            if (brush != null) {
                androidParagraph.m242painthn5TExg(canvas, brush, this.style.spanStyle.textForegroundStyle.getAlpha(), shadow2, textDecoration2, drawStyle2, 3);
            } else {
                long j = Color.Unspecified;
                if (j == j) {
                    j = this.style.m250getColor0d7_KjU() != j ? this.style.m250getColor0d7_KjU() : Color.Black;
                }
                androidParagraph.m241paintLG529CI(canvas, j, shadow2, textDecoration2, drawStyle2, 3);
            }
            if (z) {
                canvas.restore();
            }
        } catch (Throwable th) {
            if (z) {
                canvas.restore();
            }
            throw th;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.foundation.text.modifiers.ParagraphLayoutCache, java.lang.Object] */
    public final ParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            String str = this.text;
            TextStyle textStyle = this.style;
            FontFamily.Resolver resolver = this.fontFamilyResolver;
            int i = this.overflow;
            boolean z = this.softWrap;
            int i2 = this.maxLines;
            int i3 = this.minLines;
            ResultKt.checkNotNullParameter(str, "text");
            ResultKt.checkNotNullParameter(textStyle, "style");
            ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
            ?? obj = new Object();
            obj.text = str;
            obj.style = textStyle;
            obj.fontFamilyResolver = resolver;
            obj.overflow = i;
            obj.softWrap = z;
            obj.maxLines = i2;
            obj.minLines = i3;
            obj.layoutSize = ResultKt.IntSize(0, 0);
            obj.prevConstraints = DrawResult.m66fixedJhjzzOo(0, 0);
            this._layoutCache = obj;
        }
        ParagraphLayoutCache paragraphLayoutCache = this._layoutCache;
        ResultKt.checkNotNull(paragraphLayoutCache);
        return paragraphLayoutCache;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        long j2;
        boolean z;
        ParagraphIntrinsics paragraphIntrinsics;
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        ParagraphLayoutCache layoutCache = getLayoutCache();
        Density density = layoutCache.density;
        if (density == null) {
            layoutCache.density = measureScope;
        } else if (density.getDensity() != measureScope.getDensity() || density.getFontScale() != measureScope.getFontScale()) {
            layoutCache.density = measureScope;
            layoutCache.paragraph = null;
            layoutCache.paragraphIntrinsics = null;
            layoutCache.intrinsicsLayoutDirection = null;
            layoutCache.prevConstraints = DrawResult.m66fixedJhjzzOo(0, 0);
            layoutCache.layoutSize = ResultKt.IntSize(0, 0);
            layoutCache.didOverflow = false;
        }
        LayoutDirection layoutDirection = measureScope.getLayoutDirection();
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        if (layoutCache.minLines > 1) {
            MinLinesConstrainer minLinesConstrainer = layoutCache.mMinLinesConstrainer;
            TextStyle textStyle = layoutCache.style;
            Density density2 = layoutCache.density;
            ResultKt.checkNotNull(density2);
            MinLinesConstrainer from = ArtificialStackFrames.from(minLinesConstrainer, layoutDirection, textStyle, density2, layoutCache.fontFamilyResolver);
            layoutCache.mMinLinesConstrainer = from;
            j2 = from.m40coerceMinLinesOh53vG4$foundation_release(j, layoutCache.minLines);
        } else {
            j2 = j;
        }
        AndroidParagraph androidParagraph = layoutCache.paragraph;
        if (androidParagraph == null || (paragraphIntrinsics = layoutCache.paragraphIntrinsics) == null || paragraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != layoutCache.intrinsicsLayoutDirection || (!Constraints.m273equalsimpl0(j2, layoutCache.prevConstraints) && (Constraints.m276getMaxWidthimpl(j2) != Constraints.m276getMaxWidthimpl(layoutCache.prevConstraints) || Constraints.m275getMaxHeightimpl(j2) < androidParagraph.getHeight() || androidParagraph.layout.didExceedMaxLines))) {
            ParagraphIntrinsics paragraphIntrinsics2 = layoutCache.paragraphIntrinsics;
            if (paragraphIntrinsics2 == null || layoutDirection != layoutCache.intrinsicsLayoutDirection || paragraphIntrinsics2.getHasStaleResolvedFonts()) {
                layoutCache.intrinsicsLayoutDirection = layoutDirection;
                String str = layoutCache.text;
                TextStyle resolveDefaults = _BOUNDARY.resolveDefaults(layoutCache.style, layoutDirection);
                Density density3 = layoutCache.density;
                ResultKt.checkNotNull(density3);
                FontFamily.Resolver resolver = layoutCache.fontFamilyResolver;
                EmptyList emptyList = EmptyList.INSTANCE;
                paragraphIntrinsics2 = _BOUNDARY.ParagraphIntrinsics(resolveDefaults, resolver, density3, str, emptyList, emptyList);
            }
            layoutCache.paragraphIntrinsics = paragraphIntrinsics2;
            long m8finalConstraintstfFHcEY = _BOUNDARY.m8finalConstraintstfFHcEY(j2, layoutCache.softWrap, layoutCache.overflow, paragraphIntrinsics2.getMaxIntrinsicWidth());
            boolean z2 = layoutCache.softWrap;
            int i = layoutCache.overflow;
            int i2 = layoutCache.maxLines;
            if ((!z2 && _BOUNDARY.m7equalsimpl0$1(i, 2)) || i2 < 1) {
                i2 = 1;
            }
            AndroidParagraph m3Paragraph_EkL_Y = _BOUNDARY.m3Paragraph_EkL_Y(i2, m8finalConstraintstfFHcEY, paragraphIntrinsics2, _BOUNDARY.m7equalsimpl0$1(layoutCache.overflow, 2));
            layoutCache.prevConstraints = j2;
            long m295constrain4WqzIAM = ResultKt.m295constrain4WqzIAM(j2, ResultKt.IntSize(_BOUNDARY.ceilToIntPx(m3Paragraph_EkL_Y.getWidth()), _BOUNDARY.ceilToIntPx(m3Paragraph_EkL_Y.getHeight())));
            layoutCache.layoutSize = m295constrain4WqzIAM;
            layoutCache.didOverflow = !_BOUNDARY.m7equalsimpl0$1(layoutCache.overflow, 3) && (((float) ((int) (m295constrain4WqzIAM >> 32))) < m3Paragraph_EkL_Y.getWidth() || ((float) ((int) (m295constrain4WqzIAM & 4294967295L))) < m3Paragraph_EkL_Y.getHeight());
            layoutCache.paragraph = m3Paragraph_EkL_Y;
            z = true;
        } else {
            if (!Constraints.m273equalsimpl0(j2, layoutCache.prevConstraints)) {
                AndroidParagraph androidParagraph2 = layoutCache.paragraph;
                ResultKt.checkNotNull(androidParagraph2);
                long m295constrain4WqzIAM2 = ResultKt.m295constrain4WqzIAM(j2, ResultKt.IntSize(_BOUNDARY.ceilToIntPx(androidParagraph2.getWidth()), _BOUNDARY.ceilToIntPx(androidParagraph2.getHeight())));
                layoutCache.layoutSize = m295constrain4WqzIAM2;
                layoutCache.didOverflow = !_BOUNDARY.m7equalsimpl0$1(layoutCache.overflow, 3) && (((float) ((int) (m295constrain4WqzIAM2 >> 32))) < androidParagraph2.getWidth() || ((float) ((int) (m295constrain4WqzIAM2 & 4294967295L))) < androidParagraph2.getHeight());
            }
            z = false;
        }
        ParagraphIntrinsics paragraphIntrinsics3 = layoutCache.paragraphIntrinsics;
        if (paragraphIntrinsics3 != null) {
            paragraphIntrinsics3.getHasStaleResolvedFonts();
        }
        AndroidParagraph androidParagraph3 = layoutCache.paragraph;
        ResultKt.checkNotNull(androidParagraph3);
        long j3 = layoutCache.layoutSize;
        if (z) {
            Snake.invalidateLayer(this);
            Map map = this.baselineCache;
            if (map == null) {
                map = new LinkedHashMap(2);
            }
            HorizontalAlignmentLine horizontalAlignmentLine = AlignmentLineKt.FirstBaseline;
            TextLayout textLayout = androidParagraph3.layout;
            map.put(horizontalAlignmentLine, Integer.valueOf(ResultKt.roundToInt(textLayout.getLineBaseline(0))));
            map.put(AlignmentLineKt.LastBaseline, Integer.valueOf(ResultKt.roundToInt(textLayout.getLineBaseline(textLayout.lineCount - 1))));
            this.baselineCache = map;
        }
        int i3 = (int) (j3 >> 32);
        int i4 = (int) (4294967295L & j3);
        Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(DrawResult.m66fixedJhjzzOo(i3, i4));
        Map map2 = this.baselineCache;
        ResultKt.checkNotNull(map2);
        return new MeasureScope$layout$1(i3, i4, map2, measureScope, new FillNode$measure$1(mo164measureBRTryo0, 3));
    }
}
