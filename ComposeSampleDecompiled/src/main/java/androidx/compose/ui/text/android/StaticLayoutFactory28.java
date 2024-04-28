package androidx.compose.ui.text.android;

import android.text.StaticLayout;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class StaticLayoutFactory28 {
    public static final void setUseLineSpacingFromFallbacks(StaticLayout.Builder builder, boolean z) {
        ResultKt.checkNotNullParameter(builder, "builder");
        builder.setUseLineSpacingFromFallbacks(z);
    }
}
