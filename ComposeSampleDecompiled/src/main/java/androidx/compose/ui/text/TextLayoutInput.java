package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextLayoutInput {
    public final long constraints;
    public final Density density;
    public final FontFamily.Resolver fontFamilyResolver;
    public final LayoutDirection layoutDirection;
    public final int maxLines;
    public final int overflow;
    public final List placeholders;
    public final boolean softWrap;
    public final TextStyle style;
    public final AnnotatedString text;

    public TextLayoutInput(AnnotatedString annotatedString, TextStyle textStyle, List list, int i, boolean z, int i2, Density density, LayoutDirection layoutDirection, FontFamily.Resolver resolver, long j) {
        ResultKt.checkNotNullParameter(annotatedString, "text");
        ResultKt.checkNotNullParameter(textStyle, "style");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        this.text = annotatedString;
        this.style = textStyle;
        this.placeholders = list;
        this.maxLines = i;
        this.softWrap = z;
        this.overflow = i2;
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.fontFamilyResolver = resolver;
        this.constraints = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutInput)) {
            return false;
        }
        TextLayoutInput textLayoutInput = (TextLayoutInput) obj;
        return ResultKt.areEqual(this.text, textLayoutInput.text) && ResultKt.areEqual(this.style, textLayoutInput.style) && ResultKt.areEqual(this.placeholders, textLayoutInput.placeholders) && this.maxLines == textLayoutInput.maxLines && this.softWrap == textLayoutInput.softWrap && _BOUNDARY.m7equalsimpl0$1(this.overflow, textLayoutInput.overflow) && ResultKt.areEqual(this.density, textLayoutInput.density) && this.layoutDirection == textLayoutInput.layoutDirection && ResultKt.areEqual(this.fontFamilyResolver, textLayoutInput.fontFamilyResolver) && Constraints.m273equalsimpl0(this.constraints, textLayoutInput.constraints);
    }

    public final int hashCode() {
        return Long.hashCode(this.constraints) + ((this.fontFamilyResolver.hashCode() + ((this.layoutDirection.hashCode() + ((this.density.hashCode() + ((Integer.hashCode(this.overflow) + ((Boolean.hashCode(this.softWrap) + ((((this.placeholders.hashCode() + ((this.style.hashCode() + (this.text.hashCode() * 31)) * 31)) * 31) + this.maxLines) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "TextLayoutInput(text=" + ((Object) this.text) + ", style=" + this.style + ", placeholders=" + this.placeholders + ", maxLines=" + this.maxLines + ", softWrap=" + this.softWrap + ", overflow=" + ((Object) _BOUNDARY.m22toStringimpl(this.overflow)) + ", density=" + this.density + ", layoutDirection=" + this.layoutDirection + ", fontFamilyResolver=" + this.fontFamilyResolver + ", constraints=" + ((Object) Constraints.m279toStringimpl(this.constraints)) + ')';
    }
}
