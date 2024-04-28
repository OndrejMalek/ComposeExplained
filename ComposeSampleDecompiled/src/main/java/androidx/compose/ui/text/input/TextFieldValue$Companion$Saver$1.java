package androidx.compose.ui.text.input;

import androidx.compose.ui.text.SaversKt;
import androidx.compose.ui.text.TextRange;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class TextFieldValue$Companion$Saver$1 extends Lambda implements Function2 {
    public static final TextFieldValue$Companion$Saver$1 INSTANCE = new Lambda(2);

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
        TextFieldValue textFieldValue = (TextFieldValue) obj2;
        ResultKt.checkNotNullParameter(null, "$this$Saver");
        ResultKt.checkNotNullParameter(textFieldValue, "it");
        return ResultKt.arrayListOf(SaversKt.save(textFieldValue.annotatedString, SaversKt.AnnotatedStringSaver), SaversKt.save(new TextRange(textFieldValue.selection), SaversKt.TextRangeSaver));
    }
}
