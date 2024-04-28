package androidx.compose.ui;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposedModifierKt$materialize$1 extends Lambda implements Function1 {
    public static final ComposedModifierKt$materialize$1 INSTANCE = new Lambda(1);

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ResultKt.checkNotNullParameter((Modifier.Element) obj, "it");
        return Boolean.valueOf(!(r2 instanceof ComposedModifier));
    }
}
