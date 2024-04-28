package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.ui.Modifier;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FocusableInteractionNode extends Modifier.Node {
    public FocusInteraction$Focus focusedInteraction;
    public MutableInteractionSourceImpl interactionSource;

    public final void emitWithFallback(MutableInteractionSourceImpl mutableInteractionSourceImpl, Interaction interaction) {
        if (this.isAttached) {
            ResultKt.launch$default(getCoroutineScope(), null, 0, new FocusableInteractionNode$emitWithFallback$1(mutableInteractionSourceImpl, interaction, null), 3);
        } else {
            mutableInteractionSourceImpl.interactions.tryEmit(interaction);
        }
    }
}
