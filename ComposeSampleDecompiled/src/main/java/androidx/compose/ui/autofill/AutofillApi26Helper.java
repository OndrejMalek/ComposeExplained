package androidx.compose.ui.autofill;

import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AutofillApi26Helper {
    public static final AutofillApi26Helper INSTANCE = new Object();

    public final AutofillId getAutofillId(ViewStructure viewStructure) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        return viewStructure.getAutofillId();
    }

    public final boolean isDate(AutofillValue autofillValue) {
        ResultKt.checkNotNullParameter(autofillValue, "value");
        return autofillValue.isDate();
    }

    public final boolean isList(AutofillValue autofillValue) {
        ResultKt.checkNotNullParameter(autofillValue, "value");
        return autofillValue.isList();
    }

    public final boolean isText(AutofillValue autofillValue) {
        ResultKt.checkNotNullParameter(autofillValue, "value");
        return autofillValue.isText();
    }

    public final boolean isToggle(AutofillValue autofillValue) {
        ResultKt.checkNotNullParameter(autofillValue, "value");
        return autofillValue.isToggle();
    }

    public final void setAutofillHints(ViewStructure viewStructure, String[] strArr) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        ResultKt.checkNotNullParameter(strArr, "hints");
        viewStructure.setAutofillHints(strArr);
    }

    public final void setAutofillId(ViewStructure viewStructure, AutofillId autofillId, int i) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        ResultKt.checkNotNullParameter(autofillId, "parent");
        viewStructure.setAutofillId(autofillId, i);
    }

    public final void setAutofillType(ViewStructure viewStructure, int i) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        viewStructure.setAutofillType(i);
    }

    public final CharSequence textValue(AutofillValue autofillValue) {
        ResultKt.checkNotNullParameter(autofillValue, "value");
        CharSequence textValue = autofillValue.getTextValue();
        ResultKt.checkNotNullExpressionValue(textValue, "value.textValue");
        return textValue;
    }
}
