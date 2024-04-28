package androidx.compose.ui.modifier;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class EmptyMap extends ResultKt {
    public static final EmptyMap INSTANCE = new Object();

    @Override // kotlin.ResultKt
    public final boolean contains$ui_release(ModifierLocal modifierLocal) {
        ResultKt.checkNotNullParameter(modifierLocal, "key");
        return false;
    }

    @Override // kotlin.ResultKt
    public final Object get$ui_release(ProvidableModifierLocal providableModifierLocal) {
        ResultKt.checkNotNullParameter(providableModifierLocal, "key");
        throw new IllegalStateException("".toString());
    }
}
