package androidx.compose.foundation.text.modifiers;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextStringSimpleElement extends ModifierNodeElement {
    public final FontFamily.Resolver fontFamilyResolver;
    public final int maxLines;
    public final int minLines;
    public final int overflow;
    public final boolean softWrap;
    public final TextStyle style;
    public final String text;

    public TextStringSimpleElement(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3) {
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(textStyle, "style");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.text.modifiers.TextStringSimpleNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        String str = this.text;
        ResultKt.checkNotNullParameter(str, "text");
        TextStyle textStyle = this.style;
        ResultKt.checkNotNullParameter(textStyle, "style");
        FontFamily.Resolver resolver = this.fontFamilyResolver;
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        ?? node = new Modifier.Node();
        node.text = str;
        node.style = textStyle;
        node.fontFamilyResolver = resolver;
        node.overflow = this.overflow;
        node.softWrap = this.softWrap;
        node.maxLines = this.maxLines;
        node.minLines = this.minLines;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStringSimpleElement)) {
            return false;
        }
        TextStringSimpleElement textStringSimpleElement = (TextStringSimpleElement) obj;
        textStringSimpleElement.getClass();
        return ResultKt.areEqual(null, null) && ResultKt.areEqual(this.text, textStringSimpleElement.text) && ResultKt.areEqual(this.style, textStringSimpleElement.style) && ResultKt.areEqual(this.fontFamilyResolver, textStringSimpleElement.fontFamilyResolver) && _BOUNDARY.m7equalsimpl0$1(this.overflow, textStringSimpleElement.overflow) && this.softWrap == textStringSimpleElement.softWrap && this.maxLines == textStringSimpleElement.maxLines && this.minLines == textStringSimpleElement.minLines;
    }

    public final int hashCode() {
        return (((((Boolean.hashCode(this.softWrap) + ((Integer.hashCode(this.overflow) + ((this.fontFamilyResolver.hashCode() + ((this.style.hashCode() + (this.text.hashCode() * 31)) * 31)) * 31)) * 31)) * 31) + this.maxLines) * 31) + this.minLines) * 31;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x003f  */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void update(androidx.compose.ui.Modifier.Node r15) {
        /*
            r14 = this;
            androidx.compose.foundation.text.modifiers.TextStringSimpleNode r15 = (androidx.compose.foundation.text.modifiers.TextStringSimpleNode) r15
            java.lang.String r0 = "node"
            kotlin.ResultKt.checkNotNullParameter(r15, r0)
            androidx.compose.ui.text.TextStyle r0 = r14.style
            java.lang.String r1 = "style"
            kotlin.ResultKt.checkNotNullParameter(r0, r1)
            r2 = 0
            boolean r3 = kotlin.ResultKt.areEqual(r2, r2)
            r4 = 1
            r3 = r3 ^ r4
            r5 = 0
            if (r3 != 0) goto L2d
            androidx.compose.ui.text.TextStyle r3 = r15.style
            java.lang.String r6 = "other"
            kotlin.ResultKt.checkNotNullParameter(r3, r6)
            if (r0 == r3) goto L2b
            androidx.compose.ui.text.SpanStyle r6 = r0.spanStyle
            androidx.compose.ui.text.SpanStyle r3 = r3.spanStyle
            boolean r3 = r6.hasSameNonLayoutAttributes$ui_text_release(r3)
            if (r3 == 0) goto L2d
        L2b:
            r3 = r5
            goto L2e
        L2d:
            r3 = r4
        L2e:
            java.lang.String r6 = r14.text
            java.lang.String r7 = "text"
            kotlin.ResultKt.checkNotNullParameter(r6, r7)
            java.lang.String r8 = r15.text
            boolean r8 = kotlin.ResultKt.areEqual(r8, r6)
            if (r8 == 0) goto L3f
            r6 = r5
            goto L42
        L3f:
            r15.text = r6
            r6 = r4
        L42:
            androidx.compose.ui.text.font.FontFamily$Resolver r8 = r14.fontFamilyResolver
            java.lang.String r9 = "fontFamilyResolver"
            kotlin.ResultKt.checkNotNullParameter(r8, r9)
            androidx.compose.ui.text.TextStyle r10 = r15.style
            boolean r10 = r10.hasSameLayoutAffectingAttributes(r0)
            r10 = r10 ^ r4
            r15.style = r0
            int r0 = r15.minLines
            int r11 = r14.minLines
            if (r0 == r11) goto L5b
            r15.minLines = r11
            r10 = r4
        L5b:
            int r0 = r15.maxLines
            int r11 = r14.maxLines
            if (r0 == r11) goto L64
            r15.maxLines = r11
            r10 = r4
        L64:
            boolean r0 = r15.softWrap
            boolean r11 = r14.softWrap
            if (r0 == r11) goto L6d
            r15.softWrap = r11
            r10 = r4
        L6d:
            androidx.compose.ui.text.font.FontFamily$Resolver r0 = r15.fontFamilyResolver
            boolean r0 = kotlin.ResultKt.areEqual(r0, r8)
            if (r0 != 0) goto L78
            r15.fontFamilyResolver = r8
            r10 = r4
        L78:
            int r0 = r15.overflow
            int r8 = r14.overflow
            boolean r0 = _COROUTINE._BOUNDARY.m7equalsimpl0$1(r0, r8)
            if (r0 != 0) goto L85
            r15.overflow = r8
            goto L86
        L85:
            r4 = r10
        L86:
            if (r6 == 0) goto L97
            androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.Snake.requireLayoutNode(r15)
            r0._collapsedSemantics = r2
            androidx.compose.ui.node.Owner r0 = androidx.compose.ui.node.Snake.requireOwner(r0)
            androidx.compose.ui.platform.AndroidComposeView r0 = (androidx.compose.ui.platform.AndroidComposeView) r0
            r0.onSemanticsChange()
        L97:
            if (r6 != 0) goto L9b
            if (r4 == 0) goto Le2
        L9b:
            androidx.compose.foundation.text.modifiers.ParagraphLayoutCache r0 = r15.getLayoutCache()
            java.lang.String r4 = r15.text
            androidx.compose.ui.text.TextStyle r6 = r15.style
            androidx.compose.ui.text.font.FontFamily$Resolver r8 = r15.fontFamilyResolver
            int r10 = r15.overflow
            boolean r11 = r15.softWrap
            int r12 = r15.maxLines
            int r13 = r15.minLines
            kotlin.ResultKt.checkNotNullParameter(r4, r7)
            kotlin.ResultKt.checkNotNullParameter(r6, r1)
            kotlin.ResultKt.checkNotNullParameter(r8, r9)
            r0.text = r4
            r0.style = r6
            r0.fontFamilyResolver = r8
            r0.overflow = r10
            r0.softWrap = r11
            r0.maxLines = r12
            r0.minLines = r13
            r0.paragraph = r2
            r0.paragraphIntrinsics = r2
            r0.intrinsicsLayoutDirection = r2
            long r1 = androidx.compose.ui.draw.DrawResult.m66fixedJhjzzOo(r5, r5)
            r0.prevConstraints = r1
            long r1 = kotlin.ResultKt.IntSize(r5, r5)
            r0.layoutSize = r1
            r0.didOverflow = r5
            androidx.compose.ui.node.LayoutNode r0 = androidx.compose.ui.node.Snake.requireLayoutNode(r15)
            r0.invalidateMeasurements$ui_release()
            androidx.compose.ui.node.Snake.invalidateDraw(r15)
        Le2:
            if (r3 == 0) goto Le7
            androidx.compose.ui.node.Snake.invalidateDraw(r15)
        Le7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextStringSimpleElement.update(androidx.compose.ui.Modifier$Node):void");
    }
}
