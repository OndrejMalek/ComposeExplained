package androidx.compose.material3;

import android.content.Context;
import androidx.compose.ui.graphics.Color;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ColorResourceHelper {
    public static final ColorResourceHelper INSTANCE = new Object();

    /* renamed from: getColor-WaAFU9c, reason: not valid java name */
    public final long m50getColorWaAFU9c(Context context, int i) {
        ResultKt.checkNotNullParameter(context, "context");
        long color = context.getResources().getColor(i, context.getTheme()) << 32;
        int i2 = Color.$r8$clinit;
        return color;
    }
}
