package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;
import android.text.TextUtils;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.TextAndroidCanvas;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.TextLayoutKt;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt$NoopSpan$1;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidParagraph {
    public final CharSequence charSequence;
    public final long constraints;
    public final TextLayout layout;
    public final int maxLines;
    public final AndroidParagraphIntrinsics paragraphIntrinsics;
    public final List placeholderRects;

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0061, code lost:
    
        if (androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r0.value, 4) == false) goto L23;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0265 A[LOOP:1: B:88:0x0263->B:89:0x0265, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0282  */
    /* JADX WARN: Type inference failed for: r3v16, types: [android.text.Spannable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AndroidParagraph(androidx.compose.ui.text.platform.AndroidParagraphIntrinsics r26, int r27, boolean r28, long r29) {
        /*
            r25 = this;
            r9 = r25
            r0 = r26
            r10 = r27
            java.lang.String r1 = "paragraphIntrinsics"
            kotlin.ResultKt.checkNotNullParameter(r0, r1)
            r25.<init>()
            r9.paragraphIntrinsics = r0
            r9.maxLines = r10
            r11 = r29
            r9.constraints = r11
            int r1 = androidx.compose.ui.unit.Constraints.m277getMinHeightimpl(r29)
            if (r1 != 0) goto L366
            int r1 = androidx.compose.ui.unit.Constraints.m278getMinWidthimpl(r29)
            if (r1 != 0) goto L366
            r13 = 1
            if (r10 < r13) goto L35a
            r14 = 0
            r1 = 5
            r2 = 4
            java.lang.CharSequence r3 = r0.charSequence
            androidx.compose.ui.text.TextStyle r15 = r0.style
            if (r28 == 0) goto L89
            androidx.compose.ui.text.SpanStyle r0 = r15.spanStyle
            long r4 = r0.letterSpacing
            long r6 = kotlin.ResultKt.getSp(r14)
            boolean r0 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r4, r6)
            if (r0 != 0) goto L89
            androidx.compose.ui.text.SpanStyle r0 = r15.spanStyle
            long r4 = r0.letterSpacing
            long r6 = androidx.compose.ui.unit.TextUnit.Unspecified
            boolean r0 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r4, r6)
            if (r0 != 0) goto L89
            androidx.compose.ui.text.ParagraphStyle r0 = r15.paragraphStyle
            androidx.compose.ui.text.style.TextAlign r4 = r0.textAlign
            if (r4 == 0) goto L89
            int r4 = r4.value
            boolean r4 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r4, r1)
            if (r4 != 0) goto L89
            androidx.compose.ui.text.style.TextAlign r0 = r0.textAlign
            if (r0 != 0) goto L5b
            goto L63
        L5b:
            int r0 = r0.value
            boolean r0 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r0, r2)
            if (r0 != 0) goto L89
        L63:
            int r0 = r3.length()
            if (r0 != 0) goto L6a
            goto L89
        L6a:
            boolean r0 = r3 instanceof android.text.Spannable
            if (r0 == 0) goto L71
            android.text.Spannable r3 = (android.text.Spannable) r3
            goto L77
        L71:
            android.text.SpannableString r0 = new android.text.SpannableString
            r0.<init>(r3)
            r3 = r0
        L77:
            androidx.compose.ui.text.android.style.IndentationFixSpan r0 = new androidx.compose.ui.text.android.style.IndentationFixSpan
            r0.<init>()
            int r4 = r3.length()
            int r4 = r4 - r13
            int r5 = r3.length()
            int r5 = r5 - r13
            _COROUTINE._BOUNDARY.setSpan(r3, r0, r4, r5)
        L89:
            r9.charSequence = r3
            androidx.compose.ui.text.ParagraphStyle r0 = r15.paragraphStyle
            androidx.compose.ui.text.style.TextAlign r0 = r0.textAlign
            r8 = 2
            r3 = 3
            if (r0 != 0) goto L94
            goto L9f
        L94:
            int r4 = r0.value
            boolean r4 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r4, r13)
            if (r4 == 0) goto L9f
            r16 = r3
            goto Ld7
        L9f:
            if (r0 != 0) goto La2
            goto Lad
        La2:
            int r4 = r0.value
            boolean r4 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r4, r8)
            if (r4 == 0) goto Lad
            r16 = r2
            goto Ld7
        Lad:
            if (r0 != 0) goto Lb0
            goto Lbb
        Lb0:
            int r4 = r0.value
            boolean r4 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r4, r3)
            if (r4 == 0) goto Lbb
            r16 = r8
            goto Ld7
        Lbb:
            if (r0 != 0) goto Lbe
            goto Lc9
        Lbe:
            int r4 = r0.value
            boolean r1 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r4, r1)
            if (r1 == 0) goto Lc9
        Lc6:
            r16 = r14
            goto Ld7
        Lc9:
            if (r0 != 0) goto Lcc
            goto Lc6
        Lcc:
            int r0 = r0.value
            r1 = 6
            boolean r0 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r0, r1)
            if (r0 == 0) goto Lc6
            r16 = r13
        Ld7:
            androidx.compose.ui.text.ParagraphStyle r0 = r15.paragraphStyle
            androidx.compose.ui.text.style.TextAlign r1 = r0.textAlign
            if (r1 != 0) goto Le0
            r17 = r14
            goto Le8
        Le0:
            int r1 = r1.value
            boolean r1 = androidx.compose.ui.text.style.TextAlign.m270equalsimpl0(r1, r2)
            r17 = r1
        Le8:
            androidx.compose.ui.text.style.Hyphens r1 = r0.hyphens
            if (r1 != 0) goto Led
            goto Lfd
        Led:
            int r4 = r1.value
            if (r4 != r8) goto Lfd
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 32
            if (r1 > r4) goto Lfa
            r18 = r8
            goto L104
        Lfa:
            r18 = r2
            goto L104
        Lfd:
            if (r1 != 0) goto L100
            goto L102
        L100:
            int r1 = r1.value
        L102:
            r18 = r14
        L104:
            androidx.compose.ui.text.style.LineBreak r0 = r0.lineBreak
            r19 = 0
            if (r0 == 0) goto L114
            int r1 = r0.mask
            r1 = r1 & 255(0xff, float:3.57E-43)
            androidx.compose.ui.text.style.LineBreak$Strategy r4 = new androidx.compose.ui.text.style.LineBreak$Strategy
            r4.<init>(r1)
            goto L116
        L114:
            r4 = r19
        L116:
            if (r4 != 0) goto L119
            goto L124
        L119:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strategy.m265equalsimpl0(r1, r13)
            if (r1 == 0) goto L124
        L121:
            r20 = r14
            goto L13f
        L124:
            if (r4 != 0) goto L127
            goto L132
        L127:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strategy.m265equalsimpl0(r1, r8)
            if (r1 == 0) goto L132
            r20 = r13
            goto L13f
        L132:
            if (r4 != 0) goto L135
            goto L121
        L135:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strategy.m265equalsimpl0(r1, r3)
            if (r1 == 0) goto L121
            r20 = r8
        L13f:
            if (r0 == 0) goto L14d
            int r1 = r0.mask
            int r1 = r1 >> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            androidx.compose.ui.text.style.LineBreak$Strictness r4 = new androidx.compose.ui.text.style.LineBreak$Strictness
            r4.<init>(r1)
            goto L14f
        L14d:
            r4 = r19
        L14f:
            if (r4 != 0) goto L152
            goto L15d
        L152:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strictness.m267equalsimpl0(r1, r13)
            if (r1 == 0) goto L15d
        L15a:
            r21 = r14
            goto L186
        L15d:
            if (r4 != 0) goto L160
            goto L16b
        L160:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strictness.m267equalsimpl0(r1, r8)
            if (r1 == 0) goto L16b
            r21 = r13
            goto L186
        L16b:
            if (r4 != 0) goto L16e
            goto L179
        L16e:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strictness.m267equalsimpl0(r1, r3)
            if (r1 == 0) goto L179
            r21 = r8
            goto L186
        L179:
            if (r4 != 0) goto L17c
            goto L15a
        L17c:
            int r1 = r4.value
            boolean r1 = androidx.compose.ui.text.style.LineBreak.Strictness.m267equalsimpl0(r1, r2)
            if (r1 == 0) goto L15a
            r21 = r3
        L186:
            if (r0 == 0) goto L194
            int r0 = r0.mask
            int r0 = r0 >> 16
            r0 = r0 & 255(0xff, float:3.57E-43)
            androidx.compose.ui.text.style.LineBreak$WordBreak r1 = new androidx.compose.ui.text.style.LineBreak$WordBreak
            r1.<init>(r0)
            goto L196
        L194:
            r1 = r19
        L196:
            if (r1 != 0) goto L199
            goto L1a0
        L199:
            int r0 = r1.value
            if (r0 != r13) goto L1a0
        L19d:
            r22 = r14
            goto L1a9
        L1a0:
            if (r1 != 0) goto L1a3
            goto L19d
        L1a3:
            int r0 = r1.value
            if (r0 != r8) goto L19d
            r22 = r13
        L1a9:
            if (r28 == 0) goto L1b0
            android.text.TextUtils$TruncateAt r0 = android.text.TextUtils.TruncateAt.END
            r23 = r0
            goto L1b2
        L1b0:
            r23 = r19
        L1b2:
            r0 = r25
            r1 = r16
            r2 = r17
            r3 = r23
            r4 = r27
            r5 = r18
            r6 = r20
            r7 = r21
            r24 = r8
            r8 = r22
            androidx.compose.ui.text.android.TextLayout r0 = r0.constructTextLayout(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r28 == 0) goto L211
            int r1 = r0.getHeight()
            int r2 = androidx.compose.ui.unit.Constraints.m275getMaxHeightimpl(r29)
            if (r1 <= r2) goto L211
            if (r10 <= r13) goto L211
            int r1 = androidx.compose.ui.unit.Constraints.m275getMaxHeightimpl(r29)
            r2 = r14
        L1dd:
            int r3 = r0.lineCount
            if (r2 >= r3) goto L1ee
            float r3 = r0.getLineBottom(r2)
            float r4 = (float) r1
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L1eb
            goto L1ef
        L1eb:
            int r2 = r2 + 1
            goto L1dd
        L1ee:
            r2 = r3
        L1ef:
            if (r2 < 0) goto L20e
            int r1 = r9.maxLines
            if (r2 == r1) goto L20e
            if (r2 >= r13) goto L1f9
            r4 = r13
            goto L1fa
        L1f9:
            r4 = r2
        L1fa:
            r0 = r25
            r1 = r16
            r2 = r17
            r3 = r23
            r5 = r18
            r6 = r20
            r7 = r21
            r8 = r22
            androidx.compose.ui.text.android.TextLayout r0 = r0.constructTextLayout(r1, r2, r3, r4, r5, r6, r7, r8)
        L20e:
            r9.layout = r0
            goto L213
        L211:
            r9.layout = r0
        L213:
            androidx.compose.ui.text.platform.AndroidParagraphIntrinsics r0 = r9.paragraphIntrinsics
            androidx.compose.ui.text.platform.AndroidTextPaint r0 = r0.textPaint
            androidx.compose.ui.text.SpanStyle r1 = r15.spanStyle
            androidx.compose.ui.text.style.TextForegroundStyle r2 = r1.textForegroundStyle
            androidx.compose.ui.graphics.Brush r2 = r2.getBrush()
            float r3 = r25.getWidth()
            float r4 = r25.getHeight()
            long r3 = _COROUTINE._BOUNDARY.Size(r3, r4)
            androidx.compose.ui.text.style.TextForegroundStyle r1 = r1.textForegroundStyle
            float r1 = r1.getAlpha()
            r0.m263setBrush12SF9DM(r2, r3, r1)
            androidx.compose.ui.text.android.TextLayout r0 = r9.layout
            java.lang.CharSequence r1 = r0.getText()
            boolean r1 = r1 instanceof android.text.Spanned
            if (r1 != 0) goto L241
            androidx.compose.ui.text.platform.style.ShaderBrushSpan[] r0 = new androidx.compose.ui.text.platform.style.ShaderBrushSpan[r14]
            goto L261
        L241:
            java.lang.CharSequence r1 = r0.getText()
            android.text.Spanned r1 = (android.text.Spanned) r1
            java.lang.CharSequence r0 = r0.getText()
            int r0 = r0.length()
            java.lang.Class<androidx.compose.ui.text.platform.style.ShaderBrushSpan> r2 = androidx.compose.ui.text.platform.style.ShaderBrushSpan.class
            java.lang.Object[] r0 = r1.getSpans(r14, r0, r2)
            androidx.compose.ui.text.platform.style.ShaderBrushSpan[] r0 = (androidx.compose.ui.text.platform.style.ShaderBrushSpan[]) r0
            java.lang.String r1 = "brushSpans"
            kotlin.ResultKt.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.length
            if (r1 != 0) goto L261
            androidx.compose.ui.text.platform.style.ShaderBrushSpan[] r0 = new androidx.compose.ui.text.platform.style.ShaderBrushSpan[r14]
        L261:
            int r1 = r0.length
            r2 = r14
        L263:
            if (r2 >= r1) goto L278
            r3 = r0[r2]
            float r4 = r25.getWidth()
            float r5 = r25.getHeight()
            long r4 = _COROUTINE._BOUNDARY.Size(r4, r5)
            r3.size = r4
            int r2 = r2 + 1
            goto L263
        L278:
            java.lang.CharSequence r0 = r9.charSequence
            boolean r1 = r0 instanceof android.text.Spanned
            if (r1 != 0) goto L282
            kotlin.collections.EmptyList r0 = kotlin.collections.EmptyList.INSTANCE
            goto L34d
        L282:
            r1 = r0
            android.text.Spanned r1 = (android.text.Spanned) r1
            int r2 = r0.length()
            java.lang.Class<androidx.compose.ui.text.android.style.PlaceholderSpan> r3 = androidx.compose.ui.text.android.style.PlaceholderSpan.class
            java.lang.Object[] r1 = r1.getSpans(r14, r2, r3)
            java.lang.String r2 = "getSpans(0, length, PlaceholderSpan::class.java)"
            kotlin.ResultKt.checkNotNullExpressionValue(r1, r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = r1.length
            r2.<init>(r3)
            int r3 = r1.length
            r4 = r14
        L29c:
            if (r4 >= r3) goto L34c
            r5 = r1[r4]
            androidx.compose.ui.text.android.style.PlaceholderSpan r5 = (androidx.compose.ui.text.android.style.PlaceholderSpan) r5
            r6 = r0
            android.text.Spanned r6 = (android.text.Spanned) r6
            int r7 = r6.getSpanStart(r5)
            int r6 = r6.getSpanEnd(r5)
            androidx.compose.ui.text.android.TextLayout r8 = r9.layout
            android.text.Layout r8 = r8.layout
            int r8 = r8.getLineForOffset(r7)
            int r10 = r9.maxLines
            if (r8 < r10) goto L2bb
            r10 = r13
            goto L2bc
        L2bb:
            r10 = r14
        L2bc:
            androidx.compose.ui.text.android.TextLayout r11 = r9.layout
            android.text.Layout r11 = r11.layout
            int r11 = r11.getEllipsisCount(r8)
            if (r11 <= 0) goto L2d2
            androidx.compose.ui.text.android.TextLayout r11 = r9.layout
            android.text.Layout r11 = r11.layout
            int r11 = r11.getEllipsisStart(r8)
            if (r6 <= r11) goto L2d2
            r11 = r13
            goto L2d3
        L2d2:
            r11 = r14
        L2d3:
            androidx.compose.ui.text.android.TextLayout r12 = r9.layout
            android.text.Layout r12 = r12.layout
            int r15 = r12.getEllipsisStart(r8)
            if (r15 != 0) goto L2e2
            int r12 = r12.getLineEnd(r8)
            goto L2ea
        L2e2:
            java.lang.CharSequence r12 = r12.getText()
            int r12 = r12.length()
        L2ea:
            if (r6 <= r12) goto L2ee
            r6 = r13
            goto L2ef
        L2ee:
            r6 = r14
        L2ef:
            if (r11 != 0) goto L343
            if (r6 != 0) goto L343
            if (r10 == 0) goto L2f6
            goto L343
        L2f6:
            androidx.compose.ui.text.android.TextLayout r6 = r9.layout
            android.text.Layout r6 = r6.layout
            boolean r6 = r6.isRtlCharAt(r7)
            if (r6 == 0) goto L303
            r6 = r24
            goto L304
        L303:
            r6 = r13
        L304:
            int r6 = androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility.ordinal(r6)
            if (r6 == 0) goto L31f
            if (r6 != r13) goto L319
            androidx.compose.ui.text.android.TextLayout r6 = r9.layout
            float r6 = r6.getPrimaryHorizontal(r7, r14)
            int r7 = r5.getWidthPx()
            float r7 = (float) r7
            float r6 = r6 - r7
            goto L325
        L319:
            androidx.startup.StartupException r0 = new androidx.startup.StartupException
            r0.<init>()
            throw r0
        L31f:
            androidx.compose.ui.text.android.TextLayout r6 = r9.layout
            float r6 = r6.getPrimaryHorizontal(r7, r14)
        L325:
            int r7 = r5.getWidthPx()
            float r7 = (float) r7
            float r7 = r7 + r6
            androidx.compose.ui.text.android.TextLayout r10 = r9.layout
            float r8 = r10.getLineBaseline(r8)
            int r10 = r5.getHeightPx()
            float r10 = (float) r10
            float r8 = r8 - r10
            int r5 = r5.getHeightPx()
            float r5 = (float) r5
            float r5 = r5 + r8
            androidx.compose.ui.geometry.Rect r10 = new androidx.compose.ui.geometry.Rect
            r10.<init>(r6, r8, r7, r5)
            goto L345
        L343:
            r10 = r19
        L345:
            r2.add(r10)
            int r4 = r4 + 1
            goto L29c
        L34c:
            r0 = r2
        L34d:
            r9.placeholderRects = r0
            androidx.compose.ui.node.LayoutNode$_foldedChildren$1 r0 = new androidx.compose.ui.node.LayoutNode$_foldedChildren$1
            r1 = 12
            r0.<init>(r1, r9)
            kotlin.ResultKt.lazy(r0)
            return
        L35a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "maxLines should be greater than 0"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L366:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.AndroidParagraph.<init>(androidx.compose.ui.text.platform.AndroidParagraphIntrinsics, int, boolean, long):void");
    }

    public final TextLayout constructTextLayout(int i, int i2, TextUtils.TruncateAt truncateAt, int i3, int i4, int i5, int i6, int i7) {
        PlatformParagraphStyle platformParagraphStyle;
        float width = getWidth();
        AndroidParagraphIntrinsics androidParagraphIntrinsics = this.paragraphIntrinsics;
        AndroidTextPaint androidTextPaint = androidParagraphIntrinsics.textPaint;
        AndroidParagraphHelper_androidKt$NoopSpan$1 androidParagraphHelper_androidKt$NoopSpan$1 = AndroidParagraphHelper_androidKt.NoopSpan;
        TextStyle textStyle = androidParagraphIntrinsics.style;
        ResultKt.checkNotNullParameter(textStyle, "<this>");
        PlatformTextStyle platformTextStyle = textStyle.platformStyle;
        return new TextLayout(this.charSequence, width, androidTextPaint, i, truncateAt, androidParagraphIntrinsics.textDirectionHeuristic, (platformTextStyle == null || (platformParagraphStyle = platformTextStyle.paragraphStyle) == null) ? true : platformParagraphStyle.includeFontPadding, i3, i5, i6, i7, i4, i2, androidParagraphIntrinsics.layoutIntrinsics);
    }

    public final float getHeight() {
        return this.layout.getHeight();
    }

    public final float getWidth() {
        return Constraints.m276getMaxWidthimpl(this.constraints);
    }

    public final void paint(Canvas canvas) {
        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        ResultKt.checkNotNullParameter(canvas, "<this>");
        android.graphics.Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
        TextLayout textLayout = this.layout;
        if (textLayout.didExceedMaxLines) {
            canvas3.save();
            canvas3.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        ResultKt.checkNotNullParameter(canvas3, "canvas");
        if (canvas3.getClipBounds(textLayout.rect)) {
            int i = textLayout.topPadding;
            if (i != 0) {
                canvas3.translate(0.0f, i);
            }
            TextAndroidCanvas textAndroidCanvas = TextLayoutKt.SharedTextAndroidCanvas;
            textAndroidCanvas.getClass();
            textAndroidCanvas.nativeCanvas = canvas3;
            textLayout.layout.draw(textAndroidCanvas);
            if (i != 0) {
                canvas3.translate(0.0f, (-1) * i);
            }
        }
        if (textLayout.didExceedMaxLines) {
            canvas3.restore();
        }
    }

    /* renamed from: paint-LG529CI, reason: not valid java name */
    public final void m241paintLG529CI(Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        AndroidParagraphIntrinsics androidParagraphIntrinsics = this.paragraphIntrinsics;
        AndroidTextPaint androidTextPaint = androidParagraphIntrinsics.textPaint;
        AndroidPaint androidPaint = androidTextPaint.composePaint;
        int i2 = androidPaint._blendMode;
        if (j != Color.Unspecified) {
            androidPaint.m92setColor8_81llA(j);
            androidPaint.setShader(null);
        }
        androidTextPaint.setShadow(shadow);
        androidTextPaint.setTextDecoration(textDecoration);
        androidTextPaint.setDrawStyle(drawStyle);
        androidTextPaint.composePaint.m91setBlendModes9anfk8(i);
        paint(canvas);
        androidParagraphIntrinsics.textPaint.composePaint.m91setBlendModes9anfk8(i2);
    }

    /* renamed from: paint-hn5TExg, reason: not valid java name */
    public final void m242painthn5TExg(Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        AndroidParagraphIntrinsics androidParagraphIntrinsics = this.paragraphIntrinsics;
        AndroidTextPaint androidTextPaint = androidParagraphIntrinsics.textPaint;
        int i2 = androidTextPaint.composePaint._blendMode;
        androidTextPaint.m263setBrush12SF9DM(brush, _BOUNDARY.Size(getWidth(), getHeight()), f);
        androidTextPaint.setShadow(shadow);
        androidTextPaint.setTextDecoration(textDecoration);
        androidTextPaint.setDrawStyle(drawStyle);
        androidTextPaint.composePaint.m91setBlendModes9anfk8(i);
        paint(canvas);
        androidParagraphIntrinsics.textPaint.composePaint.m91setBlendModes9anfk8(i2);
    }
}
