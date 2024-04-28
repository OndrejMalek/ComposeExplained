package eu.malek.composesample2.ui.theme;

import androidx.compose.material3.Typography;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.DefaultFontFamily;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class TypeKt {
    public static final Typography Typography;

    static {
        DefaultFontFamily defaultFontFamily = FontFamily.Default;
        FontWeight fontWeight = FontWeight.Normal;
        Typography = new Typography(new TextStyle(ResultKt.getSp(16), fontWeight, defaultFontFamily, ResultKt.getSp(0.5d), ResultKt.getSp(24), 16645977), 32255);
    }
}
