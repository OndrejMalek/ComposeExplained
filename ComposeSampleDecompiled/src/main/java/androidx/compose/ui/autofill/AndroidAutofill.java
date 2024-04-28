package androidx.compose.ui.autofill;

import android.view.View;
import android.view.autofill.AutofillManager;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidAutofill implements Autofill {
    public final AutofillManager autofillManager;
    public final AutofillTree autofillTree;
    public final View view;

    public AndroidAutofill(View view, AutofillTree autofillTree) {
        ResultKt.checkNotNullParameter(view, "view");
        ResultKt.checkNotNullParameter(autofillTree, "autofillTree");
        this.view = view;
        this.autofillTree = autofillTree;
        AutofillManager autofillManager = (AutofillManager) view.getContext().getSystemService(AutofillManager.class);
        if (autofillManager == null) {
            throw new IllegalStateException("Autofill service could not be located.".toString());
        }
        this.autofillManager = autofillManager;
        view.setImportantForAutofill(1);
    }
}
