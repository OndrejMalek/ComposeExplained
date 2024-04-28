package androidx.compose.foundation.interaction;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PressInteraction$Release implements Interaction {
    public final PressInteraction$Press press;

    public PressInteraction$Release(PressInteraction$Press pressInteraction$Press) {
        ResultKt.checkNotNullParameter(pressInteraction$Press, "press");
        this.press = pressInteraction$Press;
    }
}
