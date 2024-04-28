package androidx.compose.ui.autofill;

import android.view.ViewStructure;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AutofillApi23Helper {
    public static final AutofillApi23Helper INSTANCE = new Object();

    public final int addChildCount(ViewStructure viewStructure, int i) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        return viewStructure.addChildCount(i);
    }

    public final ViewStructure newChild(ViewStructure viewStructure, int i) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        return viewStructure.newChild(i);
    }

    public final void setDimens(ViewStructure viewStructure, int i, int i2, int i3, int i4, int i5, int i6) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        viewStructure.setDimens(i, i2, i3, i4, i5, i6);
    }

    public final void setId(ViewStructure viewStructure, int i, String str, String str2, String str3) {
        ResultKt.checkNotNullParameter(viewStructure, "structure");
        viewStructure.setId(i, str, str2, str3);
    }
}
