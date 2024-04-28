package androidx.compose.ui.input.pointer;

import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ModifierLocalReadScope;

/* loaded from: classes.dex */
public abstract class PointerIconModifierLocal implements PointerIcon, ModifierLocalProvider, ModifierLocalConsumer {
    public abstract void onModifierLocalsUpdated(ModifierLocalReadScope modifierLocalReadScope);
}
