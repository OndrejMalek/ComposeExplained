package androidx.compose.material.ripple;

import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.StaticProvidableCompositionLocal;

/* loaded from: classes.dex */
public abstract class RippleThemeKt {
    public static final StaticProvidableCompositionLocal LocalRippleTheme = new CompositionLocal(RippleThemeKt$LocalRippleTheme$1.INSTANCE);
    public static final RippleAlpha LightThemeHighContrastRippleAlpha = new RippleAlpha(0.16f, 0.24f, 0.08f, 0.24f);
    public static final RippleAlpha LightThemeLowContrastRippleAlpha = new RippleAlpha(0.08f, 0.12f, 0.04f, 0.12f);
}
