package androidx.compose.ui.input.rotary;

import androidx.compose.ui.Modifier;

/* loaded from: classes.dex */
public abstract class RotaryInputModifierKt {
    public static final Modifier onRotaryScrollEvent() {
        return new RotaryInputElement();
    }
}
