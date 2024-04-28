package androidx.compose.foundation.text.modifiers;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

/* loaded from: classes.dex */
public final class TextAnnotatedStringNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public MultiParagraphLayoutCache _layoutCache;
    public LinkedHashMap baselineCache;
    public FontFamily.Resolver fontFamilyResolver;
    public int maxLines;
    public int minLines;
    public Function1 onPlaceholderLayout;
    public Function1 onTextLayout;
    public int overflow;
    public List placeholders;
    public AbstractMap$toString$1 semanticsTextLayoutResult;
    public boolean softWrap;
    public TextStyle style;
    public AnnotatedString text;

    public TextAnnotatedStringNode(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, Function1 function1, int i, boolean z, int i2, int i3, List list, Function1 function12) {
        ResultKt.checkNotNullParameter(annotatedString, "text");
        ResultKt.checkNotNullParameter(textStyle, "style");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        this.text = annotatedString;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.onTextLayout = function1;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.placeholders = list;
        this.onPlaceholderLayout = function12;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        AbstractMap$toString$1 abstractMap$toString$1 = this.semanticsTextLayoutResult;
        if (abstractMap$toString$1 == null) {
            abstractMap$toString$1 = new AbstractMap$toString$1(6, this);
            this.semanticsTextLayoutResult = abstractMap$toString$1;
        }
        AnnotatedString annotatedString = this.text;
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        ResultKt.checkNotNullParameter(annotatedString, "value");
        semanticsConfiguration.set(SemanticsProperties.Text, ResultKt.listOf(annotatedString));
        semanticsConfiguration.set(SemanticsActions.GetTextLayoutResult, new AccessibilityAction(null, abstractMap$toString$1));
    }

    public final void doInvalidations(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z2) {
            LayoutNode requireLayoutNode = Snake.requireLayoutNode(this);
            requireLayoutNode._collapsedSemantics = null;
            ((AndroidComposeView) Snake.requireOwner(requireLayoutNode)).onSemanticsChange();
        }
        if (z2 || z3 || z4) {
            MultiParagraphLayoutCache layoutCache = getLayoutCache();
            AnnotatedString annotatedString = this.text;
            TextStyle textStyle = this.style;
            FontFamily.Resolver resolver = this.fontFamilyResolver;
            int i = this.overflow;
            boolean z5 = this.softWrap;
            int i2 = this.maxLines;
            int i3 = this.minLines;
            List list = this.placeholders;
            ResultKt.checkNotNullParameter(annotatedString, "text");
            ResultKt.checkNotNullParameter(textStyle, "style");
            ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
            layoutCache.text = annotatedString;
            layoutCache.style = textStyle;
            layoutCache.fontFamilyResolver = resolver;
            layoutCache.overflow = i;
            layoutCache.softWrap = z5;
            layoutCache.maxLines = i2;
            layoutCache.minLines = i3;
            layoutCache.placeholders = list;
            layoutCache.paragraphIntrinsics = null;
            layoutCache.layoutCache = null;
            Snake.requireLayoutNode(this).invalidateMeasurements$ui_release();
            Snake.invalidateDraw(this);
        }
        if (z) {
            Snake.invalidateDraw(this);
        }
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
        Canvas canvas = layoutNodeDrawScope.canvasDrawScope.drawContext.getCanvas();
        TextLayoutResult textLayoutResult = getLayoutCache().layoutCache;
        if (textLayoutResult == null) {
            throw new IllegalStateException("You must call layoutWithConstraints first");
        }
        long j = textLayoutResult.size;
        float f = (int) (j >> 32);
        MultiParagraph multiParagraph = textLayoutResult.multiParagraph;
        boolean z = (f < multiParagraph.width || multiParagraph.didExceedMaxLines || ((float) ((int) (j & 4294967295L))) < multiParagraph.height) && !_BOUNDARY.m7equalsimpl0$1(this.overflow, 3);
        if (z) {
            Rect m4Recttz77jQw = _BOUNDARY.m4Recttz77jQw(Offset.Zero, _BOUNDARY.Size(f, (int) (j & 4294967295L)));
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
                MultiParagraph.m245painthn5TExg$default(multiParagraph, canvas, brush, this.style.spanStyle.textForegroundStyle.getAlpha(), shadow2, textDecoration2, drawStyle2);
            } else {
                long j2 = Color.Unspecified;
                if (j2 == j2) {
                    j2 = this.style.m250getColor0d7_KjU() != j2 ? this.style.m250getColor0d7_KjU() : Color.Black;
                }
                MultiParagraph.m244paintLG529CI$default(multiParagraph, canvas, j2, shadow2, textDecoration2, drawStyle2);
            }
            if (z) {
                canvas.restore();
            }
            List list = this.placeholders;
            if (list == null || list.isEmpty()) {
                return;
            }
            layoutNodeDrawScope.drawContent();
        } catch (Throwable th) {
            if (z) {
                canvas.restore();
            }
            throw th;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.foundation.text.modifiers.MultiParagraphLayoutCache, java.lang.Object] */
    public final MultiParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            AnnotatedString annotatedString = this.text;
            TextStyle textStyle = this.style;
            FontFamily.Resolver resolver = this.fontFamilyResolver;
            int i = this.overflow;
            boolean z = this.softWrap;
            int i2 = this.maxLines;
            int i3 = this.minLines;
            List list = this.placeholders;
            ResultKt.checkNotNullParameter(annotatedString, "text");
            ResultKt.checkNotNullParameter(textStyle, "style");
            ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
            ?? obj = new Object();
            obj.text = annotatedString;
            obj.style = textStyle;
            obj.fontFamilyResolver = resolver;
            obj.overflow = i;
            obj.softWrap = z;
            obj.maxLines = i2;
            obj.minLines = i3;
            obj.placeholders = list;
            this._layoutCache = obj;
        }
        MultiParagraphLayoutCache multiParagraphLayoutCache = this._layoutCache;
        ResultKt.checkNotNull(multiParagraphLayoutCache);
        return multiParagraphLayoutCache;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x019f  */
    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.layout.MeasureScope$layout$1 mo35measure3p2s80s(androidx.compose.ui.layout.MeasureScope r23, androidx.compose.ui.layout.Measurable r24, long r25) {
        /*
            r22 = this;
            r0 = r22
            r5 = r23
            java.lang.String r1 = "$this$measure"
            kotlin.ResultKt.checkNotNullParameter(r5, r1)
            androidx.compose.foundation.text.modifiers.MultiParagraphLayoutCache r1 = r22.getLayoutCache()
            androidx.compose.ui.unit.Density r2 = r1.density
            if (r2 != 0) goto L14
            r1.density = r5
            goto L34
        L14:
            float r3 = r2.getDensity()
            float r4 = r23.getDensity()
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L2d
            float r2 = r2.getFontScale()
            float r3 = r23.getFontScale()
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L2d
            goto L34
        L2d:
            r1.density = r5
            r2 = 0
            r1.paragraphIntrinsics = r2
            r1.layoutCache = r2
        L34:
            androidx.compose.ui.unit.LayoutDirection r2 = r23.getLayoutDirection()
            java.lang.String r3 = "layoutDirection"
            kotlin.ResultKt.checkNotNullParameter(r2, r3)
            int r3 = r1.minLines
            r4 = 1
            if (r3 <= r4) goto L5c
            androidx.compose.foundation.text.modifiers.MinLinesConstrainer r3 = r1.mMinLinesConstrainer
            androidx.compose.ui.text.TextStyle r6 = r1.style
            androidx.compose.ui.unit.Density r7 = r1.density
            kotlin.ResultKt.checkNotNull(r7)
            androidx.compose.ui.text.font.FontFamily$Resolver r8 = r1.fontFamilyResolver
            androidx.compose.foundation.text.modifiers.MinLinesConstrainer r3 = _COROUTINE.ArtificialStackFrames.from(r3, r2, r6, r7, r8)
            r1.mMinLinesConstrainer = r3
            int r6 = r1.minLines
            r7 = r25
            long r6 = r3.m40coerceMinLinesOh53vG4$foundation_release(r7, r6)
            goto L5f
        L5c:
            r7 = r25
            r6 = r7
        L5f:
            androidx.compose.ui.text.TextLayoutResult r3 = r1.layoutCache
            r8 = 2
            if (r3 != 0) goto L65
            goto Lbb
        L65:
            androidx.compose.ui.text.MultiParagraph r9 = r3.multiParagraph
            androidx.compose.ui.text.MultiParagraphIntrinsics r10 = r9.intrinsics
            boolean r10 = r10.getHasStaleResolvedFonts()
            if (r10 == 0) goto L70
            goto Lbb
        L70:
            androidx.compose.ui.text.TextLayoutInput r3 = r3.layoutInput
            androidx.compose.ui.unit.LayoutDirection r10 = r3.layoutDirection
            if (r2 == r10) goto L77
            goto Lbb
        L77:
            long r10 = r3.constraints
            boolean r3 = androidx.compose.ui.unit.Constraints.m273equalsimpl0(r6, r10)
            if (r3 == 0) goto L80
            goto L9b
        L80:
            int r3 = androidx.compose.ui.unit.Constraints.m276getMaxWidthimpl(r6)
            int r10 = androidx.compose.ui.unit.Constraints.m276getMaxWidthimpl(r10)
            if (r3 == r10) goto L8b
            goto Lbb
        L8b:
            int r3 = androidx.compose.ui.unit.Constraints.m275getMaxHeightimpl(r6)
            float r3 = (float) r3
            float r10 = r9.height
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 < 0) goto Lbb
            boolean r3 = r9.didExceedMaxLines
            if (r3 == 0) goto L9b
            goto Lbb
        L9b:
            androidx.compose.ui.text.TextLayoutResult r3 = r1.layoutCache
            kotlin.ResultKt.checkNotNull(r3)
            androidx.compose.ui.text.TextLayoutInput r3 = r3.layoutInput
            long r9 = r3.constraints
            boolean r3 = androidx.compose.ui.unit.Constraints.m273equalsimpl0(r6, r9)
            if (r3 == 0) goto Lad
            r4 = 0
            goto L120
        Lad:
            androidx.compose.ui.text.TextLayoutResult r3 = r1.layoutCache
            kotlin.ResultKt.checkNotNull(r3)
            androidx.compose.ui.text.MultiParagraph r3 = r3.multiParagraph
            androidx.compose.ui.text.TextLayoutResult r2 = r1.m41textLayoutResultVKLhPVY(r2, r6, r3)
            r1.layoutCache = r2
            goto L120
        Lbb:
            androidx.compose.ui.text.MultiParagraphIntrinsics r3 = r1.paragraphIntrinsics
            if (r3 == 0) goto Lc9
            androidx.compose.ui.unit.LayoutDirection r9 = r1.intrinsicsLayoutDirection
            if (r2 != r9) goto Lc9
            boolean r9 = r3.getHasStaleResolvedFonts()
            if (r9 == 0) goto Le7
        Lc9:
            r1.intrinsicsLayoutDirection = r2
            androidx.compose.ui.text.AnnotatedString r11 = r1.text
            androidx.compose.ui.text.TextStyle r3 = r1.style
            androidx.compose.ui.text.TextStyle r12 = _COROUTINE._BOUNDARY.resolveDefaults(r3, r2)
            androidx.compose.ui.unit.Density r14 = r1.density
            kotlin.ResultKt.checkNotNull(r14)
            androidx.compose.ui.text.font.FontFamily$Resolver r15 = r1.fontFamilyResolver
            java.util.List r3 = r1.placeholders
            if (r3 != 0) goto Le0
            kotlin.collections.EmptyList r3 = kotlin.collections.EmptyList.INSTANCE
        Le0:
            r13 = r3
            androidx.compose.ui.text.MultiParagraphIntrinsics r3 = new androidx.compose.ui.text.MultiParagraphIntrinsics
            r10 = r3
            r10.<init>(r11, r12, r13, r14, r15)
        Le7:
            r1.paragraphIntrinsics = r3
            androidx.compose.ui.text.MultiParagraph r9 = new androidx.compose.ui.text.MultiParagraph
            boolean r10 = r1.softWrap
            int r11 = r1.overflow
            float r12 = r3.getMaxIntrinsicWidth()
            long r18 = _COROUTINE._BOUNDARY.m8finalConstraintstfFHcEY(r6, r10, r11, r12)
            boolean r10 = r1.softWrap
            int r11 = r1.overflow
            int r12 = r1.maxLines
            if (r10 != 0) goto L108
            boolean r10 = _COROUTINE._BOUNDARY.m7equalsimpl0$1(r11, r8)
            if (r10 == 0) goto L108
            r20 = r4
            goto L10d
        L108:
            if (r12 >= r4) goto L10b
            r12 = r4
        L10b:
            r20 = r12
        L10d:
            int r10 = r1.overflow
            boolean r21 = _COROUTINE._BOUNDARY.m7equalsimpl0$1(r10, r8)
            r16 = r9
            r17 = r3
            r16.<init>(r17, r18, r20, r21)
            androidx.compose.ui.text.TextLayoutResult r2 = r1.m41textLayoutResultVKLhPVY(r2, r6, r9)
            r1.layoutCache = r2
        L120:
            androidx.compose.ui.text.TextLayoutResult r1 = r1.layoutCache
            if (r1 == 0) goto L19f
            androidx.compose.ui.text.MultiParagraph r2 = r1.multiParagraph
            androidx.compose.ui.text.MultiParagraphIntrinsics r2 = r2.intrinsics
            r2.getHasStaleResolvedFonts()
            if (r4 == 0) goto L16b
            androidx.compose.ui.node.Snake.invalidateLayer(r22)
            kotlin.jvm.functions.Function1 r2 = r0.onTextLayout
            if (r2 == 0) goto L137
            r2.invoke(r1)
        L137:
            androidx.compose.ui.layout.HorizontalAlignmentLine r2 = androidx.compose.ui.layout.AlignmentLineKt.FirstBaseline
            float r3 = r1.firstBaseline
            int r3 = kotlin.ResultKt.roundToInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            kotlin.Pair r4 = new kotlin.Pair
            r4.<init>(r2, r3)
            androidx.compose.ui.layout.HorizontalAlignmentLine r2 = androidx.compose.ui.layout.AlignmentLineKt.LastBaseline
            float r3 = r1.lastBaseline
            int r3 = kotlin.ResultKt.roundToInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            kotlin.Pair r6 = new kotlin.Pair
            r6.<init>(r2, r3)
            kotlin.Pair[] r2 = new kotlin.Pair[]{r4, r6}
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            int r4 = kotlin.ResultKt.mapCapacity(r8)
            r3.<init>(r4)
            kotlin.collections.MapsKt___MapsJvmKt.putAll(r3, r2)
            r0.baselineCache = r3
        L16b:
            kotlin.jvm.functions.Function1 r2 = r0.onPlaceholderLayout
            if (r2 == 0) goto L174
            java.util.List r3 = r1.placeholderRects
            r2.invoke(r3)
        L174:
            r2 = 32
            long r3 = r1.size
            long r1 = r3 >> r2
            int r2 = (int) r1
            r6 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r3 = r3 & r6
            int r3 = (int) r3
            long r6 = androidx.compose.ui.draw.DrawResult.m66fixedJhjzzOo(r2, r3)
            r1 = r24
            androidx.compose.ui.layout.Placeable r1 = r1.mo164measureBRTryo0(r6)
            java.util.LinkedHashMap r4 = r0.baselineCache
            kotlin.ResultKt.checkNotNull(r4)
            androidx.compose.foundation.layout.FillNode$measure$1 r6 = new androidx.compose.foundation.layout.FillNode$measure$1
            r6.<init>(r1, r8)
            androidx.compose.ui.layout.MeasureScope$layout$1 r7 = new androidx.compose.ui.layout.MeasureScope$layout$1
            r1 = r7
            r5 = r23
            r1.<init>(r2, r3, r4, r5, r6)
            return r7
        L19f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "You must call layoutWithConstraints first"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.mo35measure3p2s80s(androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Measurable, long):androidx.compose.ui.layout.MeasureScope$layout$1");
    }

    public final boolean updateCallbacks(Function1 function1, Function1 function12) {
        boolean z;
        if (ResultKt.areEqual(this.onTextLayout, function1)) {
            z = false;
        } else {
            this.onTextLayout = function1;
            z = true;
        }
        if (!ResultKt.areEqual(this.onPlaceholderLayout, function12)) {
            this.onPlaceholderLayout = function12;
            z = true;
        }
        if (ResultKt.areEqual(null, null)) {
            return z;
        }
        return true;
    }

    /* renamed from: updateLayoutRelatedArgs-MPT68mk, reason: not valid java name */
    public final boolean m42updateLayoutRelatedArgsMPT68mk(TextStyle textStyle, List list, int i, int i2, boolean z, FontFamily.Resolver resolver, int i3) {
        ResultKt.checkNotNullParameter(textStyle, "style");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        boolean z2 = !this.style.hasSameLayoutAffectingAttributes(textStyle);
        this.style = textStyle;
        if (!ResultKt.areEqual(this.placeholders, list)) {
            this.placeholders = list;
            z2 = true;
        }
        if (this.minLines != i) {
            this.minLines = i;
            z2 = true;
        }
        if (this.maxLines != i2) {
            this.maxLines = i2;
            z2 = true;
        }
        if (this.softWrap != z) {
            this.softWrap = z;
            z2 = true;
        }
        if (!ResultKt.areEqual(this.fontFamilyResolver, resolver)) {
            this.fontFamilyResolver = resolver;
            z2 = true;
        }
        if (_BOUNDARY.m7equalsimpl0$1(this.overflow, i3)) {
            return z2;
        }
        this.overflow = i3;
        return true;
    }
}
