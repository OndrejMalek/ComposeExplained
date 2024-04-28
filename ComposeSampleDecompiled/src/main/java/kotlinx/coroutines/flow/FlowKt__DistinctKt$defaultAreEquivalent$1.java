package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class FlowKt__DistinctKt$defaultAreEquivalent$1 extends Lambda implements Function2 {
    public static final FlowKt__DistinctKt$defaultAreEquivalent$1 INSTANCE = new Lambda(2);

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return Boolean.valueOf(ResultKt.areEqual(obj, obj2));
    }
}
