package androidx.compose.material3;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.SmallPersistentVector;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.unit.Dp;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class ButtonElevation {
    public final float defaultElevation;
    public final float disabledElevation;
    public final float focusedElevation;
    public final float hoveredElevation;
    public final float pressedElevation;

    public ButtonElevation(float f, float f2, float f3, float f4, float f5) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.focusedElevation = f3;
        this.hoveredElevation = f4;
        this.disabledElevation = f5;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v1, resolved type: androidx.compose.runtime.ComposerImpl */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.compose.runtime.snapshots.SnapshotStateList, java.lang.Object] */
    public final AnimationState animateElevation(boolean z, MutableInteractionSourceImpl mutableInteractionSourceImpl, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1312510462);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
        Object obj = nextSlot;
        if (nextSlot == artificialStackFrames) {
            ?? obj2 = new Object();
            obj2.firstStateRecord = new SnapshotStateList.StateListStateRecord(SmallPersistentVector.EMPTY);
            composerImpl.updateValue(obj2);
            obj = obj2;
        }
        composerImpl.end(false);
        SnapshotStateList snapshotStateList = (SnapshotStateList) obj;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed = composerImpl.changed(mutableInteractionSourceImpl) | composerImpl.changed(snapshotStateList);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed || nextSlot2 == artificialStackFrames) {
            nextSlot2 = new ButtonElevation$animateElevation$1$1(mutableInteractionSourceImpl, snapshotStateList, null);
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(mutableInteractionSourceImpl, (Function2) nextSlot2, composerImpl);
        Interaction interaction = (Interaction) CollectionsKt___CollectionsKt.lastOrNull(snapshotStateList);
        float f = !z ? this.disabledElevation : interaction instanceof PressInteraction$Press ? this.pressedElevation : interaction instanceof HoverInteraction$Enter ? this.hoveredElevation : interaction instanceof FocusInteraction$Focus ? this.focusedElevation : this.defaultElevation;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot3 = composerImpl.nextSlot();
        if (nextSlot3 == artificialStackFrames) {
            nextSlot3 = new Animatable(new Dp(f), VectorConvertersKt.DpToVector, null, 12);
            composerImpl.updateValue(nextSlot3);
        }
        composerImpl.end(false);
        Animatable animatable = (Animatable) nextSlot3;
        if (z) {
            composerImpl.startReplaceableGroup(-719929940);
            EffectsKt.LaunchedEffect(new Dp(f), new ButtonElevation$animateElevation$3(animatable, this, f, interaction, null), composerImpl);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceableGroup(-719930083);
            EffectsKt.LaunchedEffect(new Dp(f), new ButtonElevation$animateElevation$2(animatable, f, null), composerImpl);
            composerImpl.end(false);
        }
        AnimationState animationState = animatable.internalState;
        composerImpl.end(false);
        return animationState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ButtonElevation)) {
            return false;
        }
        ButtonElevation buttonElevation = (ButtonElevation) obj;
        return Dp.m280equalsimpl0(this.defaultElevation, buttonElevation.defaultElevation) && Dp.m280equalsimpl0(this.pressedElevation, buttonElevation.pressedElevation) && Dp.m280equalsimpl0(this.focusedElevation, buttonElevation.focusedElevation) && Dp.m280equalsimpl0(this.hoveredElevation, buttonElevation.hoveredElevation) && Dp.m280equalsimpl0(this.disabledElevation, buttonElevation.disabledElevation);
    }

    public final int hashCode() {
        return Float.hashCode(this.disabledElevation) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.hoveredElevation, AnimationEndReason$EnumUnboxingLocalUtility.m(this.focusedElevation, AnimationEndReason$EnumUnboxingLocalUtility.m(this.pressedElevation, Float.hashCode(this.defaultElevation) * 31, 31), 31), 31);
    }
}
