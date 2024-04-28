package androidx.compose.material3;

import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.material.ripple.RippleTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.graphics.Color;

/* loaded from: classes.dex */
public final class MaterialRippleTheme implements RippleTheme {
    public static final MaterialRippleTheme INSTANCE = new Object();

    @Override // androidx.compose.material.ripple.RippleTheme
    /* renamed from: defaultColor-WaAFU9c */
    public final long mo43defaultColorWaAFU9c(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-2059468846);
        long j = ((Color) composerImpl.consume(ContentColorKt.LocalContentColor)).value;
        composerImpl.end(false);
        return j;
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    public final RippleAlpha rippleAlpha(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1285764247);
        RippleAlpha rippleAlpha = MaterialThemeKt.DefaultRippleAlpha;
        composerImpl.end(false);
        return rippleAlpha;
    }
}
