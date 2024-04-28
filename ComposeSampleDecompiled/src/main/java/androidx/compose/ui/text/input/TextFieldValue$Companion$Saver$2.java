package androidx.compose.ui.text.input;

import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SaversKt;
import androidx.compose.ui.text.TextRange;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class TextFieldValue$Companion$Saver$2 extends Lambda implements Function1 {
    public static final TextFieldValue$Companion$Saver$2 INSTANCE = new TextFieldValue$Companion$Saver$2(0);
    public static final TextFieldValue$Companion$Saver$2 INSTANCE$1 = new TextFieldValue$Companion$Saver$2(1);
    public static final TextFieldValue$Companion$Saver$2 INSTANCE$2 = new TextFieldValue$Companion$Saver$2(2);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TextFieldValue$Companion$Saver$2(int i) {
        super(1);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(obj, "it");
                List list = (List) obj;
                Object obj2 = list.get(0);
                SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotatedStringSaver;
                Boolean bool = Boolean.FALSE;
                AnnotatedString annotatedString = (ResultKt.areEqual(obj2, bool) || obj2 == null) ? null : (AnnotatedString) saverKt$Saver$1.$restore.invoke(obj2);
                ResultKt.checkNotNull(annotatedString);
                Object obj3 = list.get(1);
                int i = TextRange.$r8$clinit;
                TextRange textRange = (ResultKt.areEqual(obj3, bool) || obj3 == null) ? null : (TextRange) SaversKt.TextRangeSaver.$restore.invoke(obj3);
                ResultKt.checkNotNull(textRange);
                return new TextFieldValue(annotatedString, textRange.packedValue, null);
            case 1:
                ResultKt.checkNotNullParameter((List) obj, "it");
                return unit;
            default:
                int i2 = ((ImeAction) obj).value;
                return unit;
        }
    }
}
