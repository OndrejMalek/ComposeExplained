package androidx.compose.ui.text.android;

import android.text.StaticLayout;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class StaticLayoutFactory26 {
    public static final void setJustificationMode(StaticLayout.Builder builder, int i) {
        ResultKt.checkNotNullParameter(builder, "builder");
        builder.setJustificationMode(i);
    }
}
