package androidx.compose.ui.platform.coreshims;

import android.view.View;
import android.view.autofill.AutofillId;

/* loaded from: classes.dex */
public abstract class ViewCompatShims$Api26Impl {
    public static AutofillId getAutofillId(View view) {
        return view.getAutofillId();
    }
}
