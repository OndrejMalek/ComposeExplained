package androidx.compose.material3.tokens;

import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;

/* loaded from: classes.dex */
public abstract class TypefaceTokens {
    public static final GenericFontFamily Brand;
    public static final GenericFontFamily Plain;
    public static final FontWeight WeightMedium;
    public static final FontWeight WeightRegular;

    static {
        GenericFontFamily genericFontFamily = FontFamily.SansSerif;
        Brand = genericFontFamily;
        Plain = genericFontFamily;
        WeightMedium = FontWeight.Medium;
        WeightRegular = FontWeight.Normal;
    }
}
