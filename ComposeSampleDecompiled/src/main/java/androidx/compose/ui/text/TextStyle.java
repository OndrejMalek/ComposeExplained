package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.ColorStyle;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class TextStyle {
    public static final TextStyle Default = new TextStyle(0, null, null, 0, 0, 16777215);
    public final ParagraphStyle paragraphStyle;
    public final PlatformTextStyle platformStyle;
    public final SpanStyle spanStyle;

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle, PlatformTextStyle platformTextStyle) {
        ResultKt.checkNotNullParameter(spanStyle, "spanStyle");
        this.spanStyle = spanStyle;
        this.paragraphStyle = paragraphStyle;
        this.platformStyle = platformTextStyle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11, types: [androidx.compose.ui.text.font.FontFamily] */
    /* renamed from: copy-CXVQc50$default */
    public static TextStyle m249copyCXVQc50$default(TextStyle textStyle, long j, FontWeight fontWeight, GenericFontFamily genericFontFamily, long j2, long j3, PlatformTextStyle platformTextStyle, int i) {
        long j4;
        long j5;
        TextForegroundStyle colorStyle;
        long mo264getColor0d7_KjU = textStyle.spanStyle.textForegroundStyle.mo264getColor0d7_KjU();
        long j6 = (i & 2) != 0 ? textStyle.spanStyle.fontSize : j;
        FontWeight fontWeight2 = (i & 4) != 0 ? textStyle.spanStyle.fontWeight : fontWeight;
        SpanStyle spanStyle = textStyle.spanStyle;
        FontStyle fontStyle = spanStyle.fontStyle;
        FontSynthesis fontSynthesis = spanStyle.fontSynthesis;
        GenericFontFamily genericFontFamily2 = (i & 32) != 0 ? spanStyle.fontFamily : genericFontFamily;
        String str = spanStyle.fontFeatureSettings;
        long j7 = (i & 128) != 0 ? spanStyle.letterSpacing : j2;
        BaselineShift baselineShift = spanStyle.baselineShift;
        TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
        LocaleList localeList = spanStyle.localeList;
        long j8 = j7;
        long j9 = spanStyle.background;
        TextDecoration textDecoration = spanStyle.textDecoration;
        Shadow shadow = spanStyle.shadow;
        ParagraphStyle paragraphStyle = textStyle.paragraphStyle;
        TextAlign textAlign = paragraphStyle.textAlign;
        TextDirection textDirection = paragraphStyle.textDirection;
        if ((i & 65536) != 0) {
            j4 = j9;
            j5 = paragraphStyle.lineHeight;
        } else {
            j4 = j9;
            j5 = j3;
        }
        TextIndent textIndent = paragraphStyle.textIndent;
        PlatformTextStyle platformTextStyle2 = (i & 262144) != 0 ? textStyle.platformStyle : platformTextStyle;
        LineHeightStyle lineHeightStyle = paragraphStyle.lineHeightStyle;
        LineBreak lineBreak = paragraphStyle.lineBreak;
        Hyphens hyphens = paragraphStyle.hyphens;
        if (Color.m121equalsimpl0(mo264getColor0d7_KjU, spanStyle.textForegroundStyle.mo264getColor0d7_KjU())) {
            colorStyle = spanStyle.textForegroundStyle;
        } else {
            colorStyle = mo264getColor0d7_KjU != Color.Unspecified ? new ColorStyle(mo264getColor0d7_KjU) : TextForegroundStyle.Unspecified.INSTANCE;
        }
        return new TextStyle(new SpanStyle(colorStyle, j6, fontWeight2, fontStyle, fontSynthesis, genericFontFamily2, str, j8, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow, spanStyle.drawStyle), new ParagraphStyle(textAlign, textDirection, j5, textIndent, platformTextStyle2 != null ? platformTextStyle2.paragraphStyle : null, lineHeightStyle, lineBreak, hyphens, textStyle.paragraphStyle.textMotion), platformTextStyle2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStyle)) {
            return false;
        }
        TextStyle textStyle = (TextStyle) obj;
        return ResultKt.areEqual(this.spanStyle, textStyle.spanStyle) && ResultKt.areEqual(this.paragraphStyle, textStyle.paragraphStyle) && ResultKt.areEqual(this.platformStyle, textStyle.platformStyle);
    }

    /* renamed from: getColor-0d7_KjU */
    public final long m250getColor0d7_KjU() {
        return this.spanStyle.textForegroundStyle.mo264getColor0d7_KjU();
    }

    public final boolean hasSameLayoutAffectingAttributes(TextStyle textStyle) {
        ResultKt.checkNotNullParameter(textStyle, "other");
        return this == textStyle || (ResultKt.areEqual(this.paragraphStyle, textStyle.paragraphStyle) && this.spanStyle.hasSameLayoutAffectingAttributes$ui_text_release(textStyle.spanStyle));
    }

    public final int hashCode() {
        int hashCode = (this.paragraphStyle.hashCode() + (this.spanStyle.hashCode() * 31)) * 31;
        PlatformTextStyle platformTextStyle = this.platformStyle;
        return hashCode + (platformTextStyle != null ? platformTextStyle.hashCode() : 0);
    }

    public final TextStyle merge(TextStyle textStyle) {
        return (textStyle == null || ResultKt.areEqual(textStyle, Default)) ? this : new TextStyle(this.spanStyle.merge(textStyle.spanStyle), this.paragraphStyle.merge(textStyle.paragraphStyle));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextStyle(color=");
        sb.append((Object) Color.m127toStringimpl(m250getColor0d7_KjU()));
        sb.append(", brush=");
        SpanStyle spanStyle = this.spanStyle;
        sb.append(spanStyle.textForegroundStyle.getBrush());
        sb.append(", alpha=");
        sb.append(spanStyle.textForegroundStyle.getAlpha());
        sb.append(", fontSize=");
        sb.append((Object) TextUnit.m286toStringimpl(spanStyle.fontSize));
        sb.append(", fontWeight=");
        sb.append(spanStyle.fontWeight);
        sb.append(", fontStyle=");
        sb.append(spanStyle.fontStyle);
        sb.append(", fontSynthesis=");
        sb.append(spanStyle.fontSynthesis);
        sb.append(", fontFamily=");
        sb.append(spanStyle.fontFamily);
        sb.append(", fontFeatureSettings=");
        sb.append(spanStyle.fontFeatureSettings);
        sb.append(", letterSpacing=");
        sb.append((Object) TextUnit.m286toStringimpl(spanStyle.letterSpacing));
        sb.append(", baselineShift=");
        sb.append(spanStyle.baselineShift);
        sb.append(", textGeometricTransform=");
        sb.append(spanStyle.textGeometricTransform);
        sb.append(", localeList=");
        sb.append(spanStyle.localeList);
        sb.append(", background=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(spanStyle.background, sb, ", textDecoration=");
        sb.append(spanStyle.textDecoration);
        sb.append(", shadow=");
        sb.append(spanStyle.shadow);
        sb.append(", drawStyle=");
        sb.append(spanStyle.drawStyle);
        sb.append(", textAlign=");
        ParagraphStyle paragraphStyle = this.paragraphStyle;
        sb.append(paragraphStyle.textAlign);
        sb.append(", textDirection=");
        sb.append(paragraphStyle.textDirection);
        sb.append(", lineHeight=");
        sb.append((Object) TextUnit.m286toStringimpl(paragraphStyle.lineHeight));
        sb.append(", textIndent=");
        sb.append(paragraphStyle.textIndent);
        sb.append(", platformStyle=");
        sb.append(this.platformStyle);
        sb.append(", lineHeightStyle=");
        sb.append(paragraphStyle.lineHeightStyle);
        sb.append(", lineBreak=");
        sb.append(paragraphStyle.lineBreak);
        sb.append(", hyphens=");
        sb.append(paragraphStyle.hyphens);
        sb.append(", textMotion=");
        sb.append(paragraphStyle.textMotion);
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextStyle(long r30, androidx.compose.ui.text.font.FontWeight r32, androidx.compose.ui.text.font.DefaultFontFamily r33, long r34, long r36, int r38) {
        /*
            r29 = this;
            r0 = r38
            long r15 = androidx.compose.ui.graphics.Color.Unspecified
            r1 = r0 & 2
            if (r1 == 0) goto Lc
            long r1 = androidx.compose.ui.unit.TextUnit.Unspecified
            r3 = r1
            goto Le
        Lc:
            r3 = r30
        Le:
            r1 = r0 & 4
            r23 = 0
            if (r1 == 0) goto L17
            r5 = r23
            goto L19
        L17:
            r5 = r32
        L19:
            r1 = r0 & 32
            if (r1 == 0) goto L20
            r8 = r23
            goto L22
        L20:
            r8 = r33
        L22:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L2a
            long r1 = androidx.compose.ui.unit.TextUnit.Unspecified
            r10 = r1
            goto L2c
        L2a:
            r10 = r34
        L2c:
            r1 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 & r1
            if (r0 == 0) goto L36
            long r0 = androidx.compose.ui.unit.TextUnit.Unspecified
            r20 = r0
            goto L38
        L36:
            r20 = r36
        L38:
            androidx.compose.ui.text.SpanStyle r1 = new androidx.compose.ui.text.SpanStyle
            r6 = 0
            r7 = 0
            r9 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r0 = r1
            r28 = r1
            r1 = r15
            r0.<init>(r1, r3, r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r17, r18, r19)
            androidx.compose.ui.text.ParagraphStyle r0 = new androidx.compose.ui.text.ParagraphStyle
            r18 = 0
            r19 = 0
            r22 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r17 = r0
            r17.<init>(r18, r19, r20, r22, r23, r24, r25, r26, r27)
            r1 = 0
            r2 = r29
            r3 = r28
            r2.<init>(r3, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextStyle.<init>(long, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.DefaultFontFamily, long, long, int):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextStyle(androidx.compose.ui.text.SpanStyle r3, androidx.compose.ui.text.ParagraphStyle r4) {
        /*
            r2 = this;
            java.lang.String r0 = "spanStyle"
            kotlin.ResultKt.checkNotNullParameter(r3, r0)
            androidx.compose.ui.text.PlatformParagraphStyle r0 = r4.platformStyle
            if (r0 != 0) goto Lb
            r0 = 0
            goto L11
        Lb:
            androidx.compose.ui.text.PlatformTextStyle r1 = new androidx.compose.ui.text.PlatformTextStyle
            r1.<init>(r0)
            r0 = r1
        L11:
            r2.<init>(r3, r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.TextStyle.<init>(androidx.compose.ui.text.SpanStyle, androidx.compose.ui.text.ParagraphStyle):void");
    }
}
