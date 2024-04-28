package androidx.compose.material.ripple;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;

/* loaded from: classes.dex */
public final class DebugRippleTheme implements RippleTheme {
    public static final DebugRippleTheme INSTANCE = new Object();

    @Override // androidx.compose.material.ripple.RippleTheme
    /* renamed from: defaultColor-WaAFU9c, reason: not valid java name */
    public final long mo43defaultColorWaAFU9c(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(2042140174);
        long j = Color.Black;
        Brush.m103luminance8_81llA(j);
        composerImpl.end(false);
        return j;
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    public final RippleAlpha rippleAlpha(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1629816343);
        RippleAlpha rippleAlpha = ((double) Brush.m103luminance8_81llA(Color.Black)) > 0.5d ? RippleThemeKt.LightThemeHighContrastRippleAlpha : RippleThemeKt.LightThemeLowContrastRippleAlpha;
        composerImpl.end(false);
        return rippleAlpha;
    }
}
