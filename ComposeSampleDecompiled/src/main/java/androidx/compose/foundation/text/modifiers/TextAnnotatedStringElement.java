package androidx.compose.foundation.text.modifiers;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import java.util.List;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class TextAnnotatedStringElement extends ModifierNodeElement {
    public final FontFamily.Resolver fontFamilyResolver;
    public final int maxLines;
    public final int minLines;
    public final Function1 onPlaceholderLayout;
    public final Function1 onTextLayout;
    public final int overflow;
    public final List placeholders;
    public final boolean softWrap;
    public final TextStyle style;
    public final AnnotatedString text;

    public TextAnnotatedStringElement(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, Function1 function1, int i, boolean z, int i2, int i3) {
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
        this.placeholders = null;
        this.onPlaceholderLayout = null;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new TextAnnotatedStringNode(this.text, this.style, this.fontFamilyResolver, this.onTextLayout, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders, this.onPlaceholderLayout);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextAnnotatedStringElement)) {
            return false;
        }
        TextAnnotatedStringElement textAnnotatedStringElement = (TextAnnotatedStringElement) obj;
        textAnnotatedStringElement.getClass();
        if (!ResultKt.areEqual(null, null) || !ResultKt.areEqual(this.text, textAnnotatedStringElement.text) || !ResultKt.areEqual(this.style, textAnnotatedStringElement.style) || !ResultKt.areEqual(this.placeholders, textAnnotatedStringElement.placeholders) || !ResultKt.areEqual(this.fontFamilyResolver, textAnnotatedStringElement.fontFamilyResolver) || !ResultKt.areEqual(this.onTextLayout, textAnnotatedStringElement.onTextLayout) || !_BOUNDARY.m7equalsimpl0$1(this.overflow, textAnnotatedStringElement.overflow) || this.softWrap != textAnnotatedStringElement.softWrap || this.maxLines != textAnnotatedStringElement.maxLines || this.minLines != textAnnotatedStringElement.minLines || !ResultKt.areEqual(this.onPlaceholderLayout, textAnnotatedStringElement.onPlaceholderLayout)) {
            return false;
        }
        textAnnotatedStringElement.getClass();
        return ResultKt.areEqual(null, null);
    }

    public final int hashCode() {
        int hashCode = (this.fontFamilyResolver.hashCode() + ((this.style.hashCode() + (this.text.hashCode() * 31)) * 31)) * 31;
        Function1 function1 = this.onTextLayout;
        int hashCode2 = (((((Boolean.hashCode(this.softWrap) + ((Integer.hashCode(this.overflow) + ((hashCode + (function1 != null ? function1.hashCode() : 0)) * 31)) * 31)) * 31) + this.maxLines) * 31) + this.minLines) * 31;
        List list = this.placeholders;
        int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        Function1 function12 = this.onPlaceholderLayout;
        return (((hashCode3 + (function12 != null ? function12.hashCode() : 0)) * 31) + 0) * 31;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void update(androidx.compose.ui.Modifier.Node r11) {
        /*
            r10 = this;
            androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode r11 = (androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode) r11
            java.lang.String r0 = "node"
            kotlin.ResultKt.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "style"
            androidx.compose.ui.text.TextStyle r1 = r10.style
            kotlin.ResultKt.checkNotNullParameter(r1, r0)
            r0 = 0
            boolean r0 = kotlin.ResultKt.areEqual(r0, r0)
            r2 = 1
            r0 = r0 ^ r2
            r3 = 0
            if (r0 != 0) goto L2d
            androidx.compose.ui.text.TextStyle r0 = r11.style
            java.lang.String r4 = "other"
            kotlin.ResultKt.checkNotNullParameter(r0, r4)
            if (r1 == r0) goto L2b
            androidx.compose.ui.text.SpanStyle r1 = r1.spanStyle
            androidx.compose.ui.text.SpanStyle r0 = r0.spanStyle
            boolean r0 = r1.hasSameNonLayoutAttributes$ui_text_release(r0)
            if (r0 == 0) goto L2d
        L2b:
            r8 = r3
            goto L2e
        L2d:
            r8 = r2
        L2e:
            java.lang.String r0 = "text"
            androidx.compose.ui.text.AnnotatedString r1 = r10.text
            kotlin.ResultKt.checkNotNullParameter(r1, r0)
            androidx.compose.ui.text.AnnotatedString r0 = r11.text
            boolean r0 = kotlin.ResultKt.areEqual(r0, r1)
            if (r0 == 0) goto L3f
            r9 = r3
            goto L42
        L3f:
            r11.text = r1
            r9 = r2
        L42:
            androidx.compose.ui.text.font.FontFamily$Resolver r6 = r10.fontFamilyResolver
            int r7 = r10.overflow
            androidx.compose.ui.text.TextStyle r1 = r10.style
            java.util.List r2 = r10.placeholders
            int r3 = r10.minLines
            int r4 = r10.maxLines
            boolean r5 = r10.softWrap
            r0 = r11
            boolean r0 = r0.m42updateLayoutRelatedArgsMPT68mk(r1, r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function1 r1 = r10.onTextLayout
            kotlin.jvm.functions.Function1 r2 = r10.onPlaceholderLayout
            boolean r1 = r11.updateCallbacks(r1, r2)
            r11.doInvalidations(r8, r9, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement.update(androidx.compose.ui.Modifier$Node):void");
    }
}
