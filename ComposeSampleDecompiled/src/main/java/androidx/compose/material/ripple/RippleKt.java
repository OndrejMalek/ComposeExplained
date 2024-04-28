package androidx.compose.material.ripple;

import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class RippleKt {
    public static final TweenSpec DefaultTweenSpec = new TweenSpec(15, 0, EasingKt.LinearEasing);

    /* renamed from: rememberRipple-9IZ8Weo, reason: not valid java name */
    public static final PlatformRipple m49rememberRipple9IZ8Weo(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1635163520);
        MutableState rememberUpdatedState = ResultKt.rememberUpdatedState(new Color(Color.Unspecified), composerImpl);
        Dp dp = new Dp(Float.NaN);
        composerImpl.startReplaceableGroup(511388516);
        boolean changed = composerImpl.changed((Object) true) | composerImpl.changed(dp);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.Empty) {
            nextSlot = new PlatformRipple(true, Float.NaN, rememberUpdatedState);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.end(false);
        PlatformRipple platformRipple = (PlatformRipple) nextSlot;
        composerImpl.end(false);
        return platformRipple;
    }
}
