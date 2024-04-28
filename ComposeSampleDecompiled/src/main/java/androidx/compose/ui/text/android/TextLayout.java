package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import kotlin.Lazy;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextLayout {
    public final int bottomPadding;
    public final boolean didExceedMaxLines;
    public final boolean fallbackLineSpacing;
    public final boolean includePadding;
    public final boolean isBoringLayout;
    public final int lastLineExtra;
    public final Paint.FontMetricsInt lastLineFontMetrics;
    public final Layout layout;
    public final Lazy layoutHelper$delegate;
    public final float leftPadding;
    public final int lineCount;
    public final LineHeightStyleSpan[] lineHeightSpans;
    public final Rect rect;
    public final float rightPadding;
    public final int topPadding;

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0152, code lost:
    
        if (r10 != false) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextLayout(java.lang.CharSequence r33, float r34, androidx.compose.ui.text.platform.AndroidTextPaint r35, int r36, android.text.TextUtils.TruncateAt r37, int r38, boolean r39, int r40, int r41, int r42, int r43, int r44, int r45, androidx.compose.ui.text.android.LayoutIntrinsics r46) {
        /*
            r32 = this;
            r1 = r32
            r0 = r33
            r2 = r34
            r3 = r36
            r15 = r39
            r12 = r40
            java.lang.String r4 = "charSequence"
            kotlin.ResultKt.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "textPaint"
            r11 = r35
            kotlin.ResultKt.checkNotNullParameter(r11, r4)
            java.lang.String r4 = "layoutIntrinsics"
            r5 = r46
            kotlin.ResultKt.checkNotNullParameter(r5, r4)
            r32.<init>()
            r1.includePadding = r15
            r10 = 1
            r1.fallbackLineSpacing = r10
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r1.rect = r4
            int r4 = r33.length()
            android.text.TextDirectionHeuristic r27 = androidx.compose.ui.text.android.TextLayoutKt.getTextDirectionHeuristic(r38)
            android.text.Layout$Alignment r6 = androidx.compose.ui.text.android.TextAlignmentAdapter.ALIGN_LEFT_FRAMEWORK
            r14 = 1
            if (r3 == 0) goto L56
            if (r3 == r14) goto L53
            r6 = 2
            if (r3 == r6) goto L50
            r6 = 3
            if (r3 == r6) goto L4d
            r6 = 4
            if (r3 == r6) goto L4a
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
        L48:
            r9 = r3
            goto L59
        L4a:
            android.text.Layout$Alignment r3 = androidx.compose.ui.text.android.TextAlignmentAdapter.ALIGN_RIGHT_FRAMEWORK
            goto L48
        L4d:
            android.text.Layout$Alignment r3 = androidx.compose.ui.text.android.TextAlignmentAdapter.ALIGN_LEFT_FRAMEWORK
            goto L48
        L50:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
            goto L48
        L53:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            goto L48
        L56:
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            goto L48
        L59:
            boolean r3 = r0 instanceof android.text.Spanned
            r13 = 0
            if (r3 == 0) goto L6c
            r3 = r0
            android.text.Spanned r3 = (android.text.Spanned) r3
            r6 = -1
            java.lang.Class<androidx.compose.ui.text.android.style.SkewXSpan> r7 = androidx.compose.ui.text.android.style.SkewXSpan.class
            int r3 = r3.nextSpanTransition(r6, r4, r7)
            if (r3 >= r4) goto L6c
            r3 = r14
            goto L6d
        L6c:
            r3 = r13
        L6d:
            java.lang.String r4 = "TextLayout:initLayout"
            android.os.Trace.beginSection(r4)
            android.text.BoringLayout$Metrics r6 = r46.getBoringMetrics()     // Catch: java.lang.Throwable -> Laa
            double r7 = (double) r2     // Catch: java.lang.Throwable -> Laa
            double r10 = java.lang.Math.ceil(r7)     // Catch: java.lang.Throwable -> Laa
            float r4 = (float) r10
            int r10 = (int) r4
            androidx.compose.ui.text.android.StaticLayoutFactory23 r11 = androidx.compose.ui.text.android.StaticLayoutFactory.delegate
            java.lang.String r4 = "alignment"
            if (r6 == 0) goto Lad
            float r5 = r46.getMaxIntrinsicWidth()     // Catch: java.lang.Throwable -> Laa
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 > 0) goto Lad
            if (r3 != 0) goto Lad
            r1.isBoringLayout = r14     // Catch: java.lang.Throwable -> Laa
            r2 = r33
            r3 = r35
            r8 = r4
            r4 = r10
            r5 = r6
            r6 = r9
            r7 = r39
            r9 = r8
            r16 = 1
            r8 = r16
            r14 = r9
            r9 = r37
            android.text.BoringLayout r2 = androidx.compose.ui.text.android.StaticLayoutFactory.create(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Laa
            r3 = r2
            r2 = r11
            r28 = r14
            goto L100
        Laa:
            r0 = move-exception
            goto L32a
        Lad:
            r14 = r4
            r16 = 1
            r1.isBoringLayout = r13     // Catch: java.lang.Throwable -> Laa
            int r5 = r33.length()     // Catch: java.lang.Throwable -> Laa
            double r2 = java.lang.Math.ceil(r7)     // Catch: java.lang.Throwable -> Laa
            float r2 = (float) r2     // Catch: java.lang.Throwable -> Laa
            int r8 = (int) r2     // Catch: java.lang.Throwable -> Laa
            kotlin.ResultKt.checkNotNullParameter(r9, r14)     // Catch: java.lang.Throwable -> Laa
            androidx.compose.ui.text.android.StaticLayoutParams r7 = new androidx.compose.ui.text.android.StaticLayoutParams     // Catch: java.lang.Throwable -> Laa
            r23 = 0
            r22 = 0
            r2 = 1065353216(0x3f800000, float:1.0)
            r6 = r13
            r13 = r2
            r2 = 0
            r4 = r14
            r3 = 1
            r14 = r2
            r2 = 0
            r28 = r4
            r4 = r2
            r2 = r7
            r3 = r33
            r6 = r35
            r29 = r7
            r7 = r10
            r17 = r8
            r8 = r27
            r18 = r16
            r10 = r40
            r30 = r11
            r11 = r37
            r12 = r17
            r15 = r45
            r16 = r39
            r17 = r18
            r18 = r41
            r19 = r42
            r20 = r43
            r21 = r44
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)     // Catch: java.lang.Throwable -> Laa
            r3 = r29
            r2 = r30
            android.text.StaticLayout r3 = r2.create(r3)     // Catch: java.lang.Throwable -> Laa
        L100:
            r1.layout = r3     // Catch: java.lang.Throwable -> Laa
            android.os.Trace.endSection()
            int r4 = r3.getLineCount()
            r5 = r40
            int r4 = java.lang.Math.min(r4, r5)
            r1.lineCount = r4
            int r12 = r4 + (-1)
            if (r4 >= r5) goto L117
        L115:
            r14 = 0
            goto L128
        L117:
            int r5 = r3.getEllipsisCount(r12)
            if (r5 > 0) goto L127
            int r5 = r3.getLineEnd(r12)
            int r0 = r33.length()
            if (r5 == r0) goto L115
        L127:
            r14 = 1
        L128:
            r1.didExceedMaxLines = r14
            long r5 = androidx.compose.ui.text.android.TextLayoutKt.ZeroVerticalPadding
            r7 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r0 = 32
            r9 = 33
            if (r39 != 0) goto L154
            boolean r10 = r1.isBoringLayout
            if (r10 == 0) goto L147
            r10 = r3
            android.text.BoringLayout r10 = (android.text.BoringLayout) r10
            int r11 = android.os.Build.VERSION.SDK_INT
            if (r11 < r9) goto L157
            boolean r10 = androidx.compose.ui.text.android.BoringLayoutFactory33$$ExternalSyntheticApiModelOutline0.m(r10)
            goto L152
        L147:
            r10 = r3
            android.text.StaticLayout r10 = (android.text.StaticLayout) r10
            int r11 = android.os.Build.VERSION.SDK_INT
            if (r11 < r9) goto L154
            boolean r10 = androidx.compose.ui.text.android.StaticLayoutFactory33.isFallbackLineSpacingEnabled(r10)
        L152:
            if (r10 == 0) goto L157
        L154:
            r9 = 1
            r15 = 0
            goto L1ad
        L157:
            android.text.TextPaint r10 = r3.getPaint()
            java.lang.CharSequence r11 = r3.getText()
            java.lang.String r13 = "paint"
            kotlin.ResultKt.checkNotNullExpressionValue(r10, r13)
            java.lang.String r13 = "text"
            kotlin.ResultKt.checkNotNullExpressionValue(r11, r13)
            r15 = 0
            int r13 = r3.getLineStart(r15)
            int r14 = r3.getLineEnd(r15)
            android.graphics.Rect r13 = _COROUTINE._BOUNDARY.getCharSequenceBounds(r10, r11, r13, r14)
            int r14 = r3.getLineAscent(r15)
            int r9 = r13.top
            if (r9 >= r14) goto L181
            int r14 = r14 - r9
        L17f:
            r9 = 1
            goto L186
        L181:
            int r14 = r3.getTopPadding()
            goto L17f
        L186:
            if (r4 != r9) goto L189
            goto L195
        L189:
            int r4 = r3.getLineStart(r12)
            int r13 = r3.getLineEnd(r12)
            android.graphics.Rect r13 = _COROUTINE._BOUNDARY.getCharSequenceBounds(r10, r11, r4, r13)
        L195:
            int r4 = r3.getLineDescent(r12)
            int r10 = r13.bottom
            if (r10 <= r4) goto L19f
            int r10 = r10 - r4
            goto L1a3
        L19f:
            int r10 = r3.getBottomPadding()
        L1a3:
            if (r14 != 0) goto L1a8
            if (r10 != 0) goto L1a8
            goto L1ad
        L1a8:
            long r3 = (long) r14
            long r3 = r3 << r0
            long r5 = (long) r10
            long r5 = r5 & r7
            long r5 = r5 | r3
        L1ad:
            java.lang.CharSequence r3 = r32.getText()
            boolean r3 = r3 instanceof android.text.Spanned
            if (r3 != 0) goto L1b8
            androidx.compose.ui.text.android.style.LineHeightStyleSpan[] r3 = new androidx.compose.ui.text.android.style.LineHeightStyleSpan[r15]
            goto L1d8
        L1b8:
            java.lang.CharSequence r3 = r32.getText()
            android.text.Spanned r3 = (android.text.Spanned) r3
            java.lang.CharSequence r4 = r32.getText()
            int r4 = r4.length()
            java.lang.Class<androidx.compose.ui.text.android.style.LineHeightStyleSpan> r10 = androidx.compose.ui.text.android.style.LineHeightStyleSpan.class
            java.lang.Object[] r3 = r3.getSpans(r15, r4, r10)
            androidx.compose.ui.text.android.style.LineHeightStyleSpan[] r3 = (androidx.compose.ui.text.android.style.LineHeightStyleSpan[]) r3
            java.lang.String r4 = "lineHeightStyleSpans"
            kotlin.ResultKt.checkNotNullExpressionValue(r3, r4)
            int r4 = r3.length
            if (r4 != 0) goto L1d8
            androidx.compose.ui.text.android.style.LineHeightStyleSpan[] r3 = new androidx.compose.ui.text.android.style.LineHeightStyleSpan[r15]
        L1d8:
            r1.lineHeightSpans = r3
            int r4 = r3.length
            r10 = r15
            r11 = r10
            r13 = r11
        L1de:
            if (r13 >= r4) goto L1fe
            r14 = r3[r13]
            int r15 = r14.firstAscentDiff
            if (r15 >= 0) goto L1ee
            int r15 = java.lang.Math.abs(r15)
            int r10 = java.lang.Math.max(r10, r15)
        L1ee:
            int r14 = r14.lastDescentDiff
            if (r14 >= 0) goto L1fa
            int r11 = java.lang.Math.abs(r14)
            int r11 = java.lang.Math.max(r10, r11)
        L1fa:
            int r13 = r13 + 1
            r15 = 0
            goto L1de
        L1fe:
            if (r10 != 0) goto L205
            if (r11 != 0) goto L205
            long r3 = androidx.compose.ui.text.android.TextLayoutKt.ZeroVerticalPadding
            goto L20a
        L205:
            long r3 = (long) r10
            long r3 = r3 << r0
            long r10 = (long) r11
            long r10 = r10 & r7
            long r3 = r3 | r10
        L20a:
            long r10 = r5 >> r0
            int r10 = (int) r10
            long r13 = r3 >> r0
            int r0 = (int) r13
            int r0 = java.lang.Math.max(r10, r0)
            r1.topPadding = r0
            long r5 = r5 & r7
            int r0 = (int) r5
            long r3 = r3 & r7
            int r3 = (int) r3
            int r0 = java.lang.Math.max(r0, r3)
            r1.bottomPadding = r0
            androidx.compose.ui.text.android.style.LineHeightStyleSpan[] r0 = r1.lineHeightSpans
            int r3 = r1.lineCount
            int r3 = r3 - r9
            android.text.Layout r4 = r1.layout
            int r5 = r4.getLineStart(r3)
            int r4 = r4.getLineEnd(r3)
            if (r5 != r4) goto L2de
            int r4 = r0.length
            if (r4 != 0) goto L236
            r14 = r9
            goto L237
        L236:
            r14 = 0
        L237:
            r4 = r14 ^ 1
            if (r4 == 0) goto L2de
            android.text.SpannableString r6 = new android.text.SpannableString
            java.lang.String r4 = "\u200b"
            r6.<init>(r4)
            int r4 = r0.length
            if (r4 == 0) goto L2d6
            r4 = 0
            r0 = r0[r4]
            int r4 = r6.length()
            if (r3 == 0) goto L254
            boolean r3 = r0.trimLastLineBottom
            if (r3 == 0) goto L254
            r13 = 0
            goto L256
        L254:
            boolean r13 = r0.trimLastLineBottom
        L256:
            androidx.compose.ui.text.android.style.LineHeightStyleSpan r3 = new androidx.compose.ui.text.android.style.LineHeightStyleSpan
            boolean r5 = r0.trimLastLineBottom
            float r7 = r0.topRatio
            float r0 = r0.lineHeight
            r36 = r3
            r37 = r0
            r38 = r4
            r39 = r13
            r40 = r5
            r41 = r7
            r36.<init>(r37, r38, r39, r40, r41)
            int r0 = r6.length()
            r4 = 0
            r5 = 33
            r6.setSpan(r3, r4, r0, r5)
            int r8 = r6.length()
            android.text.Layout$Alignment r0 = androidx.compose.ui.text.android.LayoutCompat.DEFAULT_LAYOUT_ALIGNMENT
            r3 = r28
            kotlin.ResultKt.checkNotNullParameter(r0, r3)
            androidx.compose.ui.text.android.StaticLayoutParams r3 = new androidx.compose.ui.text.android.StaticLayoutParams
            r5 = r3
            boolean r7 = r1.includePadding
            r19 = r7
            boolean r7 = r1.fallbackLineSpacing
            r20 = r7
            r23 = 0
            r24 = 0
            r7 = 0
            r10 = 2147483647(0x7fffffff, float:NaN)
            r13 = 2147483647(0x7fffffff, float:NaN)
            r14 = 0
            r15 = 2147483647(0x7fffffff, float:NaN)
            r16 = 1065353216(0x3f800000, float:1.0)
            r17 = 0
            r18 = 0
            r21 = 0
            r22 = 0
            r25 = 0
            r26 = 0
            r9 = r35
            r11 = r27
            r31 = r12
            r12 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            android.text.StaticLayout r0 = r2.create(r3)
            android.graphics.Paint$FontMetricsInt r2 = new android.graphics.Paint$FontMetricsInt
            r2.<init>()
            int r3 = r0.getLineAscent(r4)
            r2.ascent = r3
            int r3 = r0.getLineDescent(r4)
            r2.descent = r3
            int r3 = r0.getLineTop(r4)
            r2.top = r3
            int r0 = r0.getLineBottom(r4)
            r2.bottom = r0
            goto L2e2
        L2d6:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            java.lang.String r2 = "Array is empty."
            r0.<init>(r2)
            throw r0
        L2de:
            r31 = r12
            r4 = 0
            r2 = 0
        L2e2:
            if (r2 == 0) goto L2f5
            int r0 = r2.bottom
            r3 = r31
            float r4 = r1.getLineBottom(r3)
            float r5 = r1.getLineTop(r3)
            float r4 = r4 - r5
            int r4 = (int) r4
            int r13 = r0 - r4
            goto L2f8
        L2f5:
            r3 = r31
            r13 = r4
        L2f8:
            r1.lastLineExtra = r13
            r1.lastLineFontMetrics = r2
            android.text.Layout r0 = r1.layout
            android.text.TextPaint r2 = r0.getPaint()
            java.lang.String r4 = "this.paint"
            kotlin.ResultKt.checkNotNullExpressionValue(r2, r4)
            float r0 = _COROUTINE._BOUNDARY.getEllipsizedLeftPadding(r0, r3, r2)
            r1.leftPadding = r0
            android.text.Layout r0 = r1.layout
            android.text.TextPaint r2 = r0.getPaint()
            kotlin.ResultKt.checkNotNullExpressionValue(r2, r4)
            float r0 = _COROUTINE._BOUNDARY.getEllipsizedRightPadding(r0, r3, r2)
            r1.rightPadding = r0
            androidx.compose.ui.node.LayoutNode$_foldedChildren$1 r0 = new androidx.compose.ui.node.LayoutNode$_foldedChildren$1
            r2 = 13
            r0.<init>(r2, r1)
            kotlin.Lazy r0 = kotlin.ResultKt.lazy(r0)
            r1.layoutHelper$delegate = r0
            return
        L32a:
            android.os.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.TextLayout.<init>(java.lang.CharSequence, float, androidx.compose.ui.text.platform.AndroidTextPaint, int, android.text.TextUtils$TruncateAt, int, boolean, int, int, int, int, int, int, androidx.compose.ui.text.android.LayoutIntrinsics):void");
    }

    public final int getHeight() {
        boolean z = this.didExceedMaxLines;
        Layout layout = this.layout;
        return (z ? layout.getLineBottom(this.lineCount - 1) : layout.getHeight()) + this.topPadding + this.bottomPadding + this.lastLineExtra;
    }

    public final float getHorizontalPadding(int i) {
        if (i == this.lineCount - 1) {
            return this.leftPadding + this.rightPadding;
        }
        return 0.0f;
    }

    public final float getLineBaseline(int i) {
        Paint.FontMetricsInt fontMetricsInt;
        return this.topPadding + ((i != this.lineCount + (-1) || (fontMetricsInt = this.lastLineFontMetrics) == null) ? this.layout.getLineBaseline(i) : getLineTop(i) - fontMetricsInt.ascent);
    }

    public final float getLineBottom(int i) {
        Paint.FontMetricsInt fontMetricsInt;
        int i2 = this.lineCount;
        int i3 = i2 - 1;
        Layout layout = this.layout;
        if (i != i3 || (fontMetricsInt = this.lastLineFontMetrics) == null) {
            return this.topPadding + layout.getLineBottom(i) + (i == i2 + (-1) ? this.bottomPadding : 0);
        }
        return layout.getLineBottom(i - 1) + fontMetricsInt.bottom;
    }

    public final float getLineTop(int i) {
        return this.layout.getLineTop(i) + (i == 0 ? 0 : this.topPadding);
    }

    public final float getPrimaryHorizontal(int i, boolean z) {
        return getHorizontalPadding(this.layout.getLineForOffset(i)) + ((LayoutHelper) this.layoutHelper$delegate.getValue()).getHorizontalPosition(i, true, z);
    }

    public final float getSecondaryHorizontal(int i, boolean z) {
        return getHorizontalPadding(this.layout.getLineForOffset(i)) + ((LayoutHelper) this.layoutHelper$delegate.getValue()).getHorizontalPosition(i, false, z);
    }

    public final CharSequence getText() {
        CharSequence text = this.layout.getText();
        ResultKt.checkNotNullExpressionValue(text, "layout.text");
        return text;
    }
}
