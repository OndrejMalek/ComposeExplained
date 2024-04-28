package androidx.compose.foundation;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class NoIndication implements Indication {
    public static final NoIndication INSTANCE = new Object();

    @Override // androidx.compose.foundation.Indication
    public final IndicationInstance rememberUpdatedInstance(InteractionSource interactionSource, Composer composer) {
        ResultKt.checkNotNullParameter(interactionSource, "interactionSource");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(285654452);
        DefaultDebugIndication defaultDebugIndication = DefaultDebugIndication.INSTANCE$1;
        composerImpl.end(false);
        return defaultDebugIndication;
    }
}
