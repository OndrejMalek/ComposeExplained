package androidx.compose.material.ripple;

import _COROUTINE.ArtificialStackFrames;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationInstance;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.LaunchedEffectImpl;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class PlatformRipple implements Indication {
    public final boolean bounded;
    public final State color;
    public final float radius;

    public PlatformRipple(boolean z, float f, MutableState mutableState) {
        this.bounded = z;
        this.radius = f;
        this.color = mutableState;
    }

    /* JADX DEBUG: Method merged with bridge method: equals(Ljava/lang/Object;)Z */
    /* renamed from: equals$androidx$compose$material$ripple$Ripple, reason: merged with bridge method [inline-methods] */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlatformRipple)) {
            return false;
        }
        PlatformRipple platformRipple = (PlatformRipple) obj;
        return this.bounded == platformRipple.bounded && Dp.m280equalsimpl0(this.radius, platformRipple.radius) && ResultKt.areEqual(this.color, platformRipple.color);
    }

    /* JADX DEBUG: Method merged with bridge method: hashCode()I */
    /* renamed from: hashCode$androidx$compose$material$ripple$Ripple, reason: merged with bridge method [inline-methods] */
    public final int hashCode() {
        return this.color.hashCode() + AnimationEndReason$EnumUnboxingLocalUtility.m(this.radius, Boolean.hashCode(this.bounded) * 31, 31);
    }

    @Override // androidx.compose.foundation.Indication
    public final IndicationInstance rememberUpdatedInstance(InteractionSource interactionSource, Composer composer) {
        View view;
        RippleIndicationInstance rippleIndicationInstance;
        ResultKt.checkNotNullParameter(interactionSource, "interactionSource");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(988743187);
        RippleTheme rippleTheme = (RippleTheme) composerImpl.consume(RippleThemeKt.LocalRippleTheme);
        composerImpl.startReplaceableGroup(-1524341038);
        State state = this.color;
        long mo43defaultColorWaAFU9c = ((Color) state.getValue()).value != Color.Unspecified ? ((Color) state.getValue()).value : rippleTheme.mo43defaultColorWaAFU9c(composerImpl);
        composerImpl.end(false);
        MutableState rememberUpdatedState = ResultKt.rememberUpdatedState(new Color(mo43defaultColorWaAFU9c), composerImpl);
        MutableState rememberUpdatedState2 = ResultKt.rememberUpdatedState(rippleTheme.rippleAlpha(composerImpl), composerImpl);
        composerImpl.startReplaceableGroup(331259447);
        composerImpl.startReplaceableGroup(-1737891121);
        Object consume = composerImpl.consume(AndroidCompositionLocals_androidKt.LocalView);
        while (!(consume instanceof ViewGroup)) {
            ViewParent parent = ((View) consume).getParent();
            if (!(parent instanceof View)) {
                throw new IllegalArgumentException(("Couldn't find a valid parent for " + consume + ". Are you overriding LocalView and providing a View that is not attached to the view hierarchy?").toString());
            }
            ResultKt.checkNotNullExpressionValue(parent, "parent");
            consume = parent;
        }
        ViewGroup viewGroup = (ViewGroup) consume;
        composerImpl.end(false);
        composerImpl.startReplaceableGroup(1643267286);
        boolean isInEditMode = viewGroup.isInEditMode();
        ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
        boolean z = this.bounded;
        float f = this.radius;
        if (isInEditMode) {
            composerImpl.startReplaceableGroup(511388516);
            boolean changed = composerImpl.changed(interactionSource) | composerImpl.changed(this);
            Object nextSlot = composerImpl.nextSlot();
            if (changed || nextSlot == artificialStackFrames) {
                nextSlot = new CommonRippleIndicationInstance(z, f, rememberUpdatedState, rememberUpdatedState2);
                composerImpl.updateValue(nextSlot);
            }
            composerImpl.end(false);
            rippleIndicationInstance = (CommonRippleIndicationInstance) nextSlot;
            composerImpl.end(false);
            composerImpl.end(false);
        } else {
            composerImpl.end(false);
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    view = null;
                    break;
                }
                view = viewGroup.getChildAt(i);
                if (view instanceof RippleContainer) {
                    break;
                }
                i++;
            }
            if (view == null) {
                Context context = viewGroup.getContext();
                ResultKt.checkNotNullExpressionValue(context, "view.context");
                view = new RippleContainer(context);
                viewGroup.addView(view);
            }
            composerImpl.startReplaceableGroup(1618982084);
            boolean changed2 = composerImpl.changed(interactionSource) | composerImpl.changed(this) | composerImpl.changed(view);
            Object nextSlot2 = composerImpl.nextSlot();
            if (changed2 || nextSlot2 == artificialStackFrames) {
                nextSlot2 = new AndroidRippleIndicationInstance(z, f, rememberUpdatedState, rememberUpdatedState2, (RippleContainer) view);
                composerImpl.updateValue(nextSlot2);
            }
            composerImpl.end(false);
            rippleIndicationInstance = (AndroidRippleIndicationInstance) nextSlot2;
            composerImpl.end(false);
        }
        Ripple$rememberUpdatedInstance$1 ripple$rememberUpdatedInstance$1 = new Ripple$rememberUpdatedInstance$1(interactionSource, rippleIndicationInstance, null);
        composerImpl.startReplaceableGroup(590241125);
        CoroutineContext coroutineContext = ((Recomposer) composerImpl.parentContext).effectCoroutineContext;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed3 = composerImpl.changed(interactionSource) | composerImpl.changed(rippleIndicationInstance);
        Object nextSlot3 = composerImpl.nextSlot();
        if (changed3 || nextSlot3 == artificialStackFrames) {
            composerImpl.updateValue(new LaunchedEffectImpl(coroutineContext, ripple$rememberUpdatedInstance$1));
        }
        composerImpl.end(false);
        composerImpl.end(false);
        composerImpl.end(false);
        return rippleIndicationInstance;
    }
}
