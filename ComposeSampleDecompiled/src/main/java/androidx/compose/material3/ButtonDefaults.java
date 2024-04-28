package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.material3.tokens.FilledButtonTokens;

/* loaded from: classes.dex */
public abstract class ButtonDefaults {
    public static final PaddingValuesImpl ContentPadding;
    public static final float MinWidth = 58;
    public static final float MinHeight = 40;

    static {
        float f = 24;
        float f2 = 8;
        ContentPadding = new PaddingValuesImpl(f, f2, f, f2);
        float f3 = FilledButtonTokens.ContainerElevation;
    }
}
