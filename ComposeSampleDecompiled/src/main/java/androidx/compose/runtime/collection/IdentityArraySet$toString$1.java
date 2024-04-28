package androidx.compose.runtime.collection;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class IdentityArraySet$toString$1 extends Lambda implements Function1 {
    public static final IdentityArraySet$toString$1 INSTANCE = new Lambda(1);

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ResultKt.checkNotNullParameter(obj, "it");
        return obj.toString();
    }
}
