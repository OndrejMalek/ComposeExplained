package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SequencesKt___SequencesKt$filterNotNull$1 extends Lambda implements Function1 {
    public static final SequencesKt___SequencesKt$filterNotNull$1 INSTANCE = new Lambda(1);

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return Boolean.valueOf(obj == null);
    }
}
