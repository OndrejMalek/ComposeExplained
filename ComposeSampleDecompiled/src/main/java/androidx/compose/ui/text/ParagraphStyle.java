package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ParagraphStyle {
    public final Hyphens hyphens;
    public final int hyphensOrDefault;
    public final LineBreak lineBreak;
    public final int lineBreakOrDefault;
    public final long lineHeight;
    public final LineHeightStyle lineHeightStyle;
    public final PlatformParagraphStyle platformStyle;
    public final TextAlign textAlign;
    public final int textAlignOrDefault;
    public final TextDirection textDirection;
    public final TextIndent textIndent;
    public final TextMotion textMotion;

    public ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, TextMotion textMotion) {
        this.textAlign = textAlign;
        this.textDirection = textDirection;
        this.lineHeight = j;
        this.textIndent = textIndent;
        this.platformStyle = platformParagraphStyle;
        this.lineHeightStyle = lineHeightStyle;
        this.lineBreak = lineBreak;
        this.hyphens = hyphens;
        this.textMotion = textMotion;
        this.textAlignOrDefault = textAlign != null ? textAlign.value : 5;
        this.lineBreakOrDefault = lineBreak != null ? lineBreak.mask : LineBreak.Simple;
        this.hyphensOrDefault = hyphens != null ? hyphens.value : 1;
        if (TextUnit.m283equalsimpl0(j, TextUnit.Unspecified) || TextUnit.m285getValueimpl(j) >= 0.0f) {
            return;
        }
        throw new IllegalStateException(("lineHeight can't be negative (" + TextUnit.m285getValueimpl(j) + ')').toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParagraphStyle)) {
            return false;
        }
        ParagraphStyle paragraphStyle = (ParagraphStyle) obj;
        return ResultKt.areEqual(this.textAlign, paragraphStyle.textAlign) && ResultKt.areEqual(this.textDirection, paragraphStyle.textDirection) && TextUnit.m283equalsimpl0(this.lineHeight, paragraphStyle.lineHeight) && ResultKt.areEqual(this.textIndent, paragraphStyle.textIndent) && ResultKt.areEqual(this.platformStyle, paragraphStyle.platformStyle) && ResultKt.areEqual(this.lineHeightStyle, paragraphStyle.lineHeightStyle) && ResultKt.areEqual(this.lineBreak, paragraphStyle.lineBreak) && ResultKt.areEqual(this.hyphens, paragraphStyle.hyphens) && ResultKt.areEqual(this.textMotion, paragraphStyle.textMotion);
    }

    public final int hashCode() {
        TextAlign textAlign = this.textAlign;
        int hashCode = (textAlign != null ? Integer.hashCode(textAlign.value) : 0) * 31;
        TextDirection textDirection = this.textDirection;
        int hashCode2 = (hashCode + (textDirection != null ? Integer.hashCode(textDirection.value) : 0)) * 31;
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        int hashCode3 = (Long.hashCode(this.lineHeight) + hashCode2) * 31;
        TextIndent textIndent = this.textIndent;
        int hashCode4 = (hashCode3 + (textIndent != null ? textIndent.hashCode() : 0)) * 31;
        PlatformParagraphStyle platformParagraphStyle = this.platformStyle;
        int hashCode5 = (hashCode4 + (platformParagraphStyle != null ? platformParagraphStyle.hashCode() : 0)) * 31;
        LineHeightStyle lineHeightStyle = this.lineHeightStyle;
        int hashCode6 = (hashCode5 + (lineHeightStyle != null ? lineHeightStyle.hashCode() : 0)) * 31;
        LineBreak lineBreak = this.lineBreak;
        int hashCode7 = (hashCode6 + (lineBreak != null ? Integer.hashCode(lineBreak.mask) : 0)) * 31;
        Hyphens hyphens = this.hyphens;
        int hashCode8 = (hashCode7 + (hyphens != null ? Integer.hashCode(hyphens.value) : 0)) * 31;
        TextMotion textMotion = this.textMotion;
        return hashCode8 + (textMotion != null ? textMotion.hashCode() : 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ae, code lost:
    
        if (kotlin.ResultKt.areEqual(r1, r10) == false) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.text.ParagraphStyle merge(androidx.compose.ui.text.ParagraphStyle r34) {
        /*
            r33 = this;
            r0 = r33
            r1 = r34
            if (r1 != 0) goto L7
            return r0
        L7:
            int r2 = androidx.compose.ui.text.ParagraphStyleKt.$r8$clinit
            androidx.compose.ui.text.style.TextAlign r2 = r1.textAlign
            androidx.compose.ui.text.style.TextDirection r3 = r1.textDirection
            long r4 = r1.lineHeight
            androidx.compose.ui.text.style.TextIndent r6 = r1.textIndent
            androidx.compose.ui.text.PlatformParagraphStyle r7 = r1.platformStyle
            androidx.compose.ui.text.style.LineHeightStyle r8 = r1.lineHeightStyle
            androidx.compose.ui.text.style.LineBreak r9 = r1.lineBreak
            androidx.compose.ui.text.style.Hyphens r10 = r1.hyphens
            androidx.compose.ui.text.style.TextMotion r1 = r1.textMotion
            androidx.compose.ui.text.style.TextMotion r11 = r0.textMotion
            androidx.compose.ui.text.style.Hyphens r12 = r0.hyphens
            androidx.compose.ui.text.style.LineBreak r13 = r0.lineBreak
            androidx.compose.ui.text.style.LineHeightStyle r14 = r0.lineHeightStyle
            androidx.compose.ui.text.PlatformParagraphStyle r15 = r0.platformStyle
            r34 = r11
            androidx.compose.ui.text.style.TextDirection r11 = r0.textDirection
            r16 = r1
            androidx.compose.ui.text.style.TextIndent r1 = r0.textIndent
            r17 = r12
            r18 = r13
            long r12 = r0.lineHeight
            r19 = r10
            androidx.compose.ui.text.style.TextAlign r10 = r0.textAlign
            if (r2 == 0) goto L4e
            boolean r20 = kotlin.ResultKt.areEqual(r2, r10)
            if (r20 == 0) goto L40
            goto L4e
        L40:
            r0 = r19
            r19 = r17
            r17 = r1
        L46:
            r1 = r16
            r16 = r10
            r10 = r34
            goto Lb0
        L4e:
            boolean r20 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r4)
            r20 = r20 ^ 1
            if (r20 == 0) goto L5c
            boolean r20 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r4, r12)
            if (r20 == 0) goto L40
        L5c:
            if (r6 == 0) goto L64
            boolean r20 = kotlin.ResultKt.areEqual(r6, r1)
            if (r20 == 0) goto L40
        L64:
            if (r3 == 0) goto L6c
            boolean r20 = kotlin.ResultKt.areEqual(r3, r11)
            if (r20 == 0) goto L40
        L6c:
            if (r7 == 0) goto L74
            boolean r20 = kotlin.ResultKt.areEqual(r7, r15)
            if (r20 == 0) goto L40
        L74:
            if (r8 == 0) goto L7c
            boolean r20 = kotlin.ResultKt.areEqual(r8, r14)
            if (r20 == 0) goto L40
        L7c:
            r0 = r18
            if (r9 == 0) goto L8a
            boolean r18 = kotlin.ResultKt.areEqual(r9, r0)
            if (r18 == 0) goto L87
            goto L8a
        L87:
            r18 = r0
            goto L40
        L8a:
            r18 = r0
            r0 = r19
            r32 = r17
            r17 = r1
            r1 = r32
            if (r19 == 0) goto La0
            boolean r19 = kotlin.ResultKt.areEqual(r0, r1)
            if (r19 == 0) goto L9d
            goto La0
        L9d:
            r19 = r1
            goto L46
        La0:
            if (r16 == 0) goto Lfd
            r19 = r1
            r1 = r16
            r16 = r10
            r10 = r34
            boolean r20 = kotlin.ResultKt.areEqual(r1, r10)
            if (r20 != 0) goto Lfd
        Lb0:
            boolean r20 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r4)
            if (r20 == 0) goto Lb9
            r24 = r12
            goto Lbb
        Lb9:
            r24 = r4
        Lbb:
            if (r6 != 0) goto Lc0
            r26 = r17
            goto Lc2
        Lc0:
            r26 = r6
        Lc2:
            if (r2 != 0) goto Lc7
            r22 = r16
            goto Lc9
        Lc7:
            r22 = r2
        Lc9:
            if (r3 != 0) goto Lce
            r23 = r11
            goto Ld0
        Lce:
            r23 = r3
        Ld0:
            if (r15 != 0) goto Ld5
        Ld2:
            r27 = r7
            goto Ld9
        Ld5:
            if (r7 != 0) goto Ld2
            r27 = r15
        Ld9:
            if (r8 != 0) goto Lde
            r28 = r14
            goto Le0
        Lde:
            r28 = r8
        Le0:
            if (r9 != 0) goto Le5
            r29 = r18
            goto Le7
        Le5:
            r29 = r9
        Le7:
            if (r0 != 0) goto Lec
            r30 = r19
            goto Lee
        Lec:
            r30 = r0
        Lee:
            if (r1 != 0) goto Lf3
            r31 = r10
            goto Lf5
        Lf3:
            r31 = r1
        Lf5:
            androidx.compose.ui.text.ParagraphStyle r0 = new androidx.compose.ui.text.ParagraphStyle
            r21 = r0
            r21.<init>(r22, r23, r24, r26, r27, r28, r29, r30, r31)
            goto Lff
        Lfd:
            r0 = r33
        Lff:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.ParagraphStyle.merge(androidx.compose.ui.text.ParagraphStyle):androidx.compose.ui.text.ParagraphStyle");
    }

    public final String toString() {
        return "ParagraphStyle(textAlign=" + this.textAlign + ", textDirection=" + this.textDirection + ", lineHeight=" + ((Object) TextUnit.m286toStringimpl(this.lineHeight)) + ", textIndent=" + this.textIndent + ", platformStyle=" + this.platformStyle + ", lineHeightStyle=" + this.lineHeightStyle + ", lineBreak=" + this.lineBreak + ", hyphens=" + this.hyphens + ", textMotion=" + this.textMotion + ')';
    }

    public ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, LineBreak lineBreak, Hyphens hyphens, int i) {
        this(textAlign, textDirection, j, textIndent, (i & 16) != 0 ? null : platformParagraphStyle, (i & 32) != 0 ? null : lineHeightStyle, (i & 64) != 0 ? null : lineBreak, (i & 128) != 0 ? null : hyphens, (TextMotion) null);
    }
}
