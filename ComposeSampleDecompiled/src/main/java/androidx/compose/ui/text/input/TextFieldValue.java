package androidx.compose.ui.text.input;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextFieldValue {
    public final AnnotatedString annotatedString;
    public final TextRange composition;
    public final long selection;

    static {
        SaverKt.Saver(TextFieldValue$Companion$Saver$1.INSTANCE, TextFieldValue$Companion$Saver$2.INSTANCE);
    }

    public TextFieldValue(AnnotatedString annotatedString, long j, TextRange textRange) {
        TextRange textRange2;
        this.annotatedString = annotatedString;
        String str = annotatedString.text;
        int length = str.length();
        int i = TextRange.$r8$clinit;
        int i2 = (int) (j >> 32);
        int coerceIn = ResultKt.coerceIn(i2, 0, length);
        int i3 = (int) (j & 4294967295L);
        int coerceIn2 = ResultKt.coerceIn(i3, 0, length);
        this.selection = (coerceIn == i2 && coerceIn2 == i3) ? j : _BOUNDARY.TextRange(coerceIn, coerceIn2);
        if (textRange != null) {
            int length2 = str.length();
            long j2 = textRange.packedValue;
            int i4 = (int) (j2 >> 32);
            int coerceIn3 = ResultKt.coerceIn(i4, 0, length2);
            int i5 = (int) (j2 & 4294967295L);
            int coerceIn4 = ResultKt.coerceIn(i5, 0, length2);
            textRange2 = new TextRange((coerceIn3 == i4 && coerceIn4 == i5) ? j2 : _BOUNDARY.TextRange(coerceIn3, coerceIn4));
        } else {
            textRange2 = null;
        }
        this.composition = textRange2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextFieldValue)) {
            return false;
        }
        TextFieldValue textFieldValue = (TextFieldValue) obj;
        long j = textFieldValue.selection;
        int i = TextRange.$r8$clinit;
        return this.selection == j && ResultKt.areEqual(this.composition, textFieldValue.composition) && ResultKt.areEqual(this.annotatedString, textFieldValue.annotatedString);
    }

    public final int hashCode() {
        int hashCode = this.annotatedString.hashCode() * 31;
        int i = TextRange.$r8$clinit;
        int hashCode2 = (Long.hashCode(this.selection) + hashCode) * 31;
        TextRange textRange = this.composition;
        return hashCode2 + (textRange != null ? Long.hashCode(textRange.packedValue) : 0);
    }

    public final String toString() {
        return "TextFieldValue(text='" + ((Object) this.annotatedString) + "', selection=" + ((Object) TextRange.m248toStringimpl(this.selection)) + ", composition=" + this.composition + ')';
    }
}
