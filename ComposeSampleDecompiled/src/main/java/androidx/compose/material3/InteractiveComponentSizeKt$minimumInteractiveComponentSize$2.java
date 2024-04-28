package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.Modifier;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class InteractiveComponentSizeKt$minimumInteractiveComponentSize$2 extends Lambda implements Function3 {
    public static final InteractiveComponentSizeKt$minimumInteractiveComponentSize$2 INSTANCE = new Lambda(3);

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ((Number) obj3).intValue();
        ResultKt.checkNotNullParameter((Modifier) obj, "$this$composed");
        ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
        composerImpl.startReplaceableGroup(279503903);
        Object minimumInteractiveComponentSizeModifier = ((Boolean) composerImpl.consume(InteractiveComponentSizeKt.LocalMinimumInteractiveComponentEnforcement)).booleanValue() ? new MinimumInteractiveComponentSizeModifier(InteractiveComponentSizeKt.minimumInteractiveComponentSize) : Modifier.Companion.$$INSTANCE;
        composerImpl.end(false);
        return minimumInteractiveComponentSizeModifier;
    }
}
