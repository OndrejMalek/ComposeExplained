package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.ColorStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class SpanStyle {
    public final long background;
    public final BaselineShift baselineShift;
    public final DrawStyle drawStyle;
    public final FontFamily fontFamily;
    public final String fontFeatureSettings;
    public final long fontSize;
    public final FontStyle fontStyle;
    public final FontSynthesis fontSynthesis;
    public final FontWeight fontWeight;
    public final long letterSpacing;
    public final LocaleList localeList;
    public final Shadow shadow;
    public final TextDecoration textDecoration;
    public final TextForegroundStyle textForegroundStyle;
    public final TextGeometricTransform textGeometricTransform;

    public SpanStyle(TextForegroundStyle textForegroundStyle, long j, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j2, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j3, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle) {
        ResultKt.checkNotNullParameter(textForegroundStyle, "textForegroundStyle");
        this.textForegroundStyle = textForegroundStyle;
        this.fontSize = j;
        this.fontWeight = fontWeight;
        this.fontStyle = fontStyle;
        this.fontSynthesis = fontSynthesis;
        this.fontFamily = fontFamily;
        this.fontFeatureSettings = str;
        this.letterSpacing = j2;
        this.baselineShift = baselineShift;
        this.textGeometricTransform = textGeometricTransform;
        this.localeList = localeList;
        this.background = j3;
        this.textDecoration = textDecoration;
        this.shadow = shadow;
        this.drawStyle = drawStyle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpanStyle)) {
            return false;
        }
        SpanStyle spanStyle = (SpanStyle) obj;
        return hasSameLayoutAffectingAttributes$ui_text_release(spanStyle) && hasSameNonLayoutAttributes$ui_text_release(spanStyle);
    }

    public final boolean hasSameLayoutAffectingAttributes$ui_text_release(SpanStyle spanStyle) {
        ResultKt.checkNotNullParameter(spanStyle, "other");
        if (this == spanStyle) {
            return true;
        }
        return TextUnit.m283equalsimpl0(this.fontSize, spanStyle.fontSize) && ResultKt.areEqual(this.fontWeight, spanStyle.fontWeight) && ResultKt.areEqual(this.fontStyle, spanStyle.fontStyle) && ResultKt.areEqual(this.fontSynthesis, spanStyle.fontSynthesis) && ResultKt.areEqual(this.fontFamily, spanStyle.fontFamily) && ResultKt.areEqual(this.fontFeatureSettings, spanStyle.fontFeatureSettings) && TextUnit.m283equalsimpl0(this.letterSpacing, spanStyle.letterSpacing) && ResultKt.areEqual(this.baselineShift, spanStyle.baselineShift) && ResultKt.areEqual(this.textGeometricTransform, spanStyle.textGeometricTransform) && ResultKt.areEqual(this.localeList, spanStyle.localeList) && Color.m121equalsimpl0(this.background, spanStyle.background) && ResultKt.areEqual(null, null);
    }

    public final boolean hasSameNonLayoutAttributes$ui_text_release(SpanStyle spanStyle) {
        ResultKt.checkNotNullParameter(spanStyle, "other");
        return ResultKt.areEqual(this.textForegroundStyle, spanStyle.textForegroundStyle) && ResultKt.areEqual(this.textDecoration, spanStyle.textDecoration) && ResultKt.areEqual(this.shadow, spanStyle.shadow) && ResultKt.areEqual(this.drawStyle, spanStyle.drawStyle);
    }

    public final int hashCode() {
        TextForegroundStyle textForegroundStyle = this.textForegroundStyle;
        long mo264getColor0d7_KjU = textForegroundStyle.mo264getColor0d7_KjU();
        int i = Color.$r8$clinit;
        int hashCode = Long.hashCode(mo264getColor0d7_KjU) * 31;
        Brush brush = textForegroundStyle.getBrush();
        int hashCode2 = (Float.hashCode(textForegroundStyle.getAlpha()) + ((hashCode + (brush != null ? brush.hashCode() : 0)) * 31)) * 31;
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        int hashCode3 = (Long.hashCode(this.fontSize) + hashCode2) * 31;
        FontWeight fontWeight = this.fontWeight;
        int i2 = (hashCode3 + (fontWeight != null ? fontWeight.weight : 0)) * 31;
        FontStyle fontStyle = this.fontStyle;
        int hashCode4 = (i2 + (fontStyle != null ? Integer.hashCode(fontStyle.value) : 0)) * 31;
        FontSynthesis fontSynthesis = this.fontSynthesis;
        int hashCode5 = (hashCode4 + (fontSynthesis != null ? Integer.hashCode(fontSynthesis.value) : 0)) * 31;
        FontFamily fontFamily = this.fontFamily;
        int hashCode6 = (hashCode5 + (fontFamily != null ? fontFamily.hashCode() : 0)) * 31;
        String str = this.fontFeatureSettings;
        int hashCode7 = (Long.hashCode(this.letterSpacing) + ((hashCode6 + (str != null ? str.hashCode() : 0)) * 31)) * 31;
        BaselineShift baselineShift = this.baselineShift;
        int hashCode8 = (hashCode7 + (baselineShift != null ? Float.hashCode(baselineShift.multiplier) : 0)) * 31;
        TextGeometricTransform textGeometricTransform = this.textGeometricTransform;
        int hashCode9 = (hashCode8 + (textGeometricTransform != null ? textGeometricTransform.hashCode() : 0)) * 31;
        LocaleList localeList = this.localeList;
        int hashCode10 = (Long.hashCode(this.background) + ((hashCode9 + (localeList != null ? localeList.localeList.hashCode() : 0)) * 31)) * 31;
        TextDecoration textDecoration = this.textDecoration;
        int i3 = (hashCode10 + (textDecoration != null ? textDecoration.mask : 0)) * 31;
        Shadow shadow = this.shadow;
        int hashCode11 = (i3 + (shadow != null ? shadow.hashCode() : 0)) * 961;
        DrawStyle drawStyle = this.drawStyle;
        return hashCode11 + (drawStyle != null ? drawStyle.hashCode() : 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:88:0x00b7, code lost:
    
        if (androidx.compose.ui.graphics.Color.m121equalsimpl0(r3, r13.mo264getColor0d7_KjU()) != false) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.text.SpanStyle merge(androidx.compose.ui.text.SpanStyle r64) {
        /*
            r63 = this;
            r0 = r63
            r1 = r64
            if (r1 != 0) goto L7
            return r0
        L7:
            androidx.compose.ui.text.style.TextForegroundStyle r2 = r1.textForegroundStyle
            long r3 = r2.mo264getColor0d7_KjU()
            androidx.compose.ui.graphics.Brush r5 = r2.getBrush()
            float r2 = r2.getAlpha()
            int r6 = androidx.compose.ui.text.SpanStyleKt.$r8$clinit
            long r6 = r1.fontSize
            boolean r8 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r6)
            r8 = r8 ^ 1
            androidx.compose.ui.text.font.FontWeight r9 = r1.fontWeight
            androidx.compose.ui.text.font.FontStyle r10 = r1.fontStyle
            androidx.compose.ui.text.font.FontSynthesis r11 = r1.fontSynthesis
            androidx.compose.ui.text.font.FontFamily r12 = r1.fontFamily
            java.lang.String r13 = r1.fontFeatureSettings
            long r14 = r1.letterSpacing
            r16 = r13
            androidx.compose.ui.text.style.BaselineShift r13 = r1.baselineShift
            r17 = r13
            androidx.compose.ui.text.style.TextGeometricTransform r13 = r1.textGeometricTransform
            r18 = r13
            androidx.compose.ui.text.intl.LocaleList r13 = r1.localeList
            r19 = r14
            r15 = r13
            long r13 = r1.background
            r21 = r13
            androidx.compose.ui.text.style.TextDecoration r13 = r1.textDecoration
            androidx.compose.ui.graphics.Shadow r14 = r1.shadow
            androidx.compose.ui.graphics.drawscope.DrawStyle r1 = r1.drawStyle
            r64 = r1
            androidx.compose.ui.graphics.drawscope.DrawStyle r1 = r0.drawStyle
            r23 = r1
            androidx.compose.ui.graphics.Shadow r1 = r0.shadow
            r25 = r14
            r24 = r15
            long r14 = r0.background
            r26 = r1
            androidx.compose.ui.text.intl.LocaleList r1 = r0.localeList
            r27 = r14
            androidx.compose.ui.text.style.TextGeometricTransform r14 = r0.textGeometricTransform
            androidx.compose.ui.text.style.BaselineShift r15 = r0.baselineShift
            r29 = r1
            java.lang.String r1 = r0.fontFeatureSettings
            r30 = r14
            androidx.compose.ui.text.font.FontSynthesis r14 = r0.fontSynthesis
            r31 = r15
            androidx.compose.ui.text.style.TextDecoration r15 = r0.textDecoration
            r33 = r1
            r32 = r2
            long r1 = r0.letterSpacing
            r34 = r14
            androidx.compose.ui.text.font.FontFamily r14 = r0.fontFamily
            r35 = r11
            androidx.compose.ui.text.font.FontWeight r11 = r0.fontWeight
            r36 = r15
            androidx.compose.ui.text.font.FontStyle r15 = r0.fontStyle
            r37 = r1
            long r1 = r0.fontSize
            r39 = r13
            androidx.compose.ui.text.style.TextForegroundStyle r13 = r0.textForegroundStyle
            if (r8 == 0) goto La5
            boolean r8 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r6, r1)
            if (r8 == 0) goto L8b
            goto La5
        L8b:
            r40 = r1
            r2 = r24
            r8 = r35
            r1 = r64
        L93:
            r35 = r34
            r34 = r11
            r11 = r16
            r61 = r19
            r19 = r14
            r20 = r15
            r14 = r21
            r21 = r61
            goto L1fd
        La5:
            if (r5 != 0) goto Lc1
            long r40 = androidx.compose.ui.graphics.Color.Unspecified
            int r8 = (r3 > r40 ? 1 : (r3 == r40 ? 0 : -1))
            if (r8 == 0) goto Lc1
            r40 = r1
            long r0 = r13.mo264getColor0d7_KjU()
            boolean r0 = androidx.compose.ui.graphics.Color.m121equalsimpl0(r3, r0)
            if (r0 == 0) goto Lba
            goto Lc3
        Lba:
            r1 = r64
            r2 = r24
            r8 = r35
            goto L93
        Lc1:
            r40 = r1
        Lc3:
            if (r10 == 0) goto Lcb
            boolean r0 = kotlin.ResultKt.areEqual(r10, r15)
            if (r0 == 0) goto Lba
        Lcb:
            if (r9 == 0) goto Ld3
            boolean r0 = kotlin.ResultKt.areEqual(r9, r11)
            if (r0 == 0) goto Lba
        Ld3:
            if (r12 == 0) goto Ld7
            if (r12 != r14) goto Lba
        Ld7:
            boolean r0 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r19)
            r0 = r0 ^ 1
            r2 = r14
            r8 = r15
            if (r0 == 0) goto L104
            r0 = r19
            r14 = r37
            boolean r19 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r0, r14)
            if (r19 == 0) goto Lec
            goto L108
        Lec:
            r19 = r2
            r20 = r8
        Lf0:
            r37 = r14
            r14 = r21
            r2 = r24
            r8 = r35
            r21 = r0
            r35 = r34
            r1 = r64
            r34 = r11
            r11 = r16
            goto L1fd
        L104:
            r0 = r19
            r14 = r37
        L108:
            r19 = r2
            r20 = r8
            if (r39 == 0) goto L120
            r8 = r36
            r2 = r39
            boolean r36 = kotlin.ResultKt.areEqual(r2, r8)
            if (r36 == 0) goto L11b
            r36 = r8
            goto L122
        L11b:
            r39 = r2
            r36 = r8
            goto Lf0
        L120:
            r2 = r39
        L122:
            androidx.compose.ui.graphics.Brush r8 = r13.getBrush()
            boolean r8 = kotlin.ResultKt.areEqual(r5, r8)
            if (r8 == 0) goto L137
            if (r5 == 0) goto L13a
            float r8 = r13.getAlpha()
            int r8 = (r32 > r8 ? 1 : (r32 == r8 ? 0 : -1))
            if (r8 != 0) goto L137
            goto L13a
        L137:
            r39 = r2
            goto Lf0
        L13a:
            r8 = r35
            r61 = r34
            r34 = r11
            r11 = r61
            if (r35 == 0) goto L15d
            boolean r35 = kotlin.ResultKt.areEqual(r8, r11)
            if (r35 == 0) goto L14b
            goto L15d
        L14b:
            r39 = r2
            r35 = r11
            r37 = r14
            r11 = r16
        L153:
            r14 = r21
            r2 = r24
        L157:
            r21 = r0
        L159:
            r1 = r64
            goto L1fd
        L15d:
            r35 = r11
            r37 = r14
            r11 = r16
            r14 = r33
            if (r16 == 0) goto L173
            boolean r15 = kotlin.ResultKt.areEqual(r11, r14)
            if (r15 == 0) goto L16e
            goto L173
        L16e:
            r39 = r2
            r33 = r14
            goto L153
        L173:
            r33 = r14
            r15 = r17
            r14 = r31
            if (r17 == 0) goto L189
            boolean r16 = kotlin.ResultKt.areEqual(r15, r14)
            if (r16 == 0) goto L182
            goto L189
        L182:
            r39 = r2
            r31 = r14
        L186:
            r17 = r15
            goto L153
        L189:
            r39 = r2
            r31 = r14
            r14 = r18
            r2 = r30
            if (r18 == 0) goto L19f
            boolean r16 = kotlin.ResultKt.areEqual(r14, r2)
            if (r16 == 0) goto L19a
            goto L19f
        L19a:
            r30 = r2
            r18 = r14
            goto L186
        L19f:
            r30 = r2
            r18 = r14
            r2 = r24
            r14 = r29
            if (r24 == 0) goto L1b7
            boolean r16 = kotlin.ResultKt.areEqual(r2, r14)
            if (r16 == 0) goto L1b0
            goto L1b7
        L1b0:
            r29 = r14
            r17 = r15
            r14 = r21
            goto L157
        L1b7:
            long r16 = androidx.compose.ui.graphics.Color.Unspecified
            int r16 = (r21 > r16 ? 1 : (r21 == r16 ? 0 : -1))
            r29 = r14
            r17 = r15
            r14 = r21
            r21 = r0
            r0 = r27
            if (r16 == 0) goto L1d1
            boolean r16 = androidx.compose.ui.graphics.Color.m121equalsimpl0(r14, r0)
            if (r16 == 0) goto L1ce
            goto L1d1
        L1ce:
            r27 = r0
            goto L159
        L1d1:
            r27 = r0
            r0 = r25
            r1 = r26
            if (r25 == 0) goto L1e6
            boolean r16 = kotlin.ResultKt.areEqual(r0, r1)
            if (r16 == 0) goto L1e0
            goto L1e6
        L1e0:
            r25 = r0
            r26 = r1
            goto L159
        L1e6:
            if (r64 == 0) goto L1f9
            r25 = r0
            r26 = r1
            r0 = r23
            r1 = r64
            boolean r16 = kotlin.ResultKt.areEqual(r1, r0)
            if (r16 != 0) goto L1f9
            r23 = r0
            goto L1fd
        L1f9:
            r0 = r63
            goto L2ce
        L1fd:
            androidx.compose.ui.text.style.TextForegroundStyle$Unspecified r0 = androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.INSTANCE
            if (r5 == 0) goto L20b
            androidx.compose.ui.text.style.BrushStyle r3 = new androidx.compose.ui.text.style.BrushStyle
            androidx.compose.ui.graphics.BrushKt$ShaderBrush$1 r5 = (androidx.compose.ui.graphics.BrushKt$ShaderBrush$1) r5
            r4 = r32
            r3.<init>(r5, r4)
            goto L219
        L20b:
            long r42 = androidx.compose.ui.graphics.Color.Unspecified
            int r5 = (r3 > r42 ? 1 : (r3 == r42 ? 0 : -1))
            if (r5 == 0) goto L218
            androidx.compose.ui.text.style.ColorStyle r5 = new androidx.compose.ui.text.style.ColorStyle
            r5.<init>(r3)
            r3 = r5
            goto L219
        L218:
            r3 = r0
        L219:
            r13.getClass()
            boolean r4 = r3 instanceof androidx.compose.ui.text.style.BrushStyle
            if (r4 == 0) goto L23f
            boolean r5 = r13 instanceof androidx.compose.ui.text.style.BrushStyle
            if (r5 == 0) goto L23f
            androidx.compose.ui.text.style.BrushStyle r0 = new androidx.compose.ui.text.style.BrushStyle
            r4 = r3
            androidx.compose.ui.text.style.BrushStyle r4 = (androidx.compose.ui.text.style.BrushStyle) r4
            float r3 = r3.getAlpha()
            boolean r5 = java.lang.Float.isNaN(r3)
            if (r5 == 0) goto L237
            float r3 = r13.getAlpha()
        L237:
            androidx.compose.ui.graphics.BrushKt$ShaderBrush$1 r4 = r4.value
            r0.<init>(r4, r3)
            r43 = r0
            goto L259
        L23f:
            if (r4 == 0) goto L248
            boolean r5 = r13 instanceof androidx.compose.ui.text.style.BrushStyle
            if (r5 != 0) goto L248
            r43 = r3
            goto L259
        L248:
            if (r4 != 0) goto L251
            boolean r4 = r13 instanceof androidx.compose.ui.text.style.BrushStyle
            if (r4 == 0) goto L251
        L24e:
            r43 = r13
            goto L259
        L251:
            boolean r0 = kotlin.ResultKt.areEqual(r3, r0)
            if (r0 != 0) goto L24e
            r13 = r3
            goto L24e
        L259:
            if (r12 != 0) goto L25e
            r49 = r19
            goto L260
        L25e:
            r49 = r12
        L260:
            boolean r0 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r6)
            if (r0 != 0) goto L269
            r44 = r6
            goto L26b
        L269:
            r44 = r40
        L26b:
            if (r9 != 0) goto L270
            r46 = r34
            goto L272
        L270:
            r46 = r9
        L272:
            if (r10 != 0) goto L277
            r47 = r20
            goto L279
        L277:
            r47 = r10
        L279:
            if (r8 != 0) goto L27e
            r48 = r35
            goto L280
        L27e:
            r48 = r8
        L280:
            if (r11 != 0) goto L285
            r50 = r33
            goto L287
        L285:
            r50 = r11
        L287:
            boolean r0 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r21)
            if (r0 != 0) goto L290
            r51 = r21
            goto L292
        L290:
            r51 = r37
        L292:
            if (r17 != 0) goto L297
            r53 = r31
            goto L299
        L297:
            r53 = r17
        L299:
            if (r18 != 0) goto L29e
            r54 = r30
            goto L2a0
        L29e:
            r54 = r18
        L2a0:
            if (r2 != 0) goto L2a5
            r55 = r29
            goto L2a7
        L2a5:
            r55 = r2
        L2a7:
            long r2 = androidx.compose.ui.graphics.Color.Unspecified
            int r0 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r0 == 0) goto L2b0
            r56 = r14
            goto L2b2
        L2b0:
            r56 = r27
        L2b2:
            if (r39 != 0) goto L2b7
            r58 = r36
            goto L2b9
        L2b7:
            r58 = r39
        L2b9:
            if (r25 != 0) goto L2be
            r59 = r26
            goto L2c0
        L2be:
            r59 = r25
        L2c0:
            if (r1 != 0) goto L2c5
            r60 = r23
            goto L2c7
        L2c5:
            r60 = r1
        L2c7:
            androidx.compose.ui.text.SpanStyle r0 = new androidx.compose.ui.text.SpanStyle
            r42 = r0
            r42.<init>(r43, r44, r46, r47, r48, r49, r50, r51, r53, r54, r55, r56, r58, r59, r60)
        L2ce:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SpanStyle.merge(androidx.compose.ui.text.SpanStyle):androidx.compose.ui.text.SpanStyle");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SpanStyle(color=");
        TextForegroundStyle textForegroundStyle = this.textForegroundStyle;
        sb.append((Object) Color.m127toStringimpl(textForegroundStyle.mo264getColor0d7_KjU()));
        sb.append(", brush=");
        sb.append(textForegroundStyle.getBrush());
        sb.append(", alpha=");
        sb.append(textForegroundStyle.getAlpha());
        sb.append(", fontSize=");
        sb.append((Object) TextUnit.m286toStringimpl(this.fontSize));
        sb.append(", fontWeight=");
        sb.append(this.fontWeight);
        sb.append(", fontStyle=");
        sb.append(this.fontStyle);
        sb.append(", fontSynthesis=");
        sb.append(this.fontSynthesis);
        sb.append(", fontFamily=");
        sb.append(this.fontFamily);
        sb.append(", fontFeatureSettings=");
        sb.append(this.fontFeatureSettings);
        sb.append(", letterSpacing=");
        sb.append((Object) TextUnit.m286toStringimpl(this.letterSpacing));
        sb.append(", baselineShift=");
        sb.append(this.baselineShift);
        sb.append(", textGeometricTransform=");
        sb.append(this.textGeometricTransform);
        sb.append(", localeList=");
        sb.append(this.localeList);
        sb.append(", background=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.background, sb, ", textDecoration=");
        sb.append(this.textDecoration);
        sb.append(", shadow=");
        sb.append(this.shadow);
        sb.append(", platformStyle=null, drawStyle=");
        sb.append(this.drawStyle);
        sb.append(')');
        return sb.toString();
    }

    public SpanStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, int i) {
        this((i & 1) != 0 ? Color.Unspecified : j, (i & 2) != 0 ? TextUnit.Unspecified : j2, (i & 4) != 0 ? null : fontWeight, (i & 8) != 0 ? null : fontStyle, (i & 16) != 0 ? null : fontSynthesis, (i & 32) != 0 ? null : fontFamily, (i & 64) != 0 ? null : str, (i & 128) != 0 ? TextUnit.Unspecified : j3, (i & 256) != 0 ? null : baselineShift, (i & 512) != 0 ? null : textGeometricTransform, (i & 1024) != 0 ? null : localeList, (i & 2048) != 0 ? Color.Unspecified : j4, (i & 4096) != 0 ? null : textDecoration, (i & 8192) != 0 ? null : shadow, (DrawStyle) null);
    }

    public SpanStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle) {
        this(j != Color.Unspecified ? new ColorStyle(j) : TextForegroundStyle.Unspecified.INSTANCE, j2, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j3, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow, drawStyle);
    }
}
