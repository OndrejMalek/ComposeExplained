package androidx.compose.foundation.interaction;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PressInteraction$Cancel implements Interaction {
    public final PressInteraction$Press press;

    public PressInteraction$Cancel(PressInteraction$Press pressInteraction$Press) {
        ResultKt.checkNotNullParameter(pressInteraction$Press, "press");
        this.press = pressInteraction$Press;
    }
}
