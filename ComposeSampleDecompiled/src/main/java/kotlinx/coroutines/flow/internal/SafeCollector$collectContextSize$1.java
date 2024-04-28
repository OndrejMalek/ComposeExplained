package kotlinx.coroutines.flow.internal;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SafeCollector$collectContextSize$1 extends Lambda implements Function2 {
    public static final SafeCollector$collectContextSize$1 INSTANCE = new Lambda(2);

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return Integer.valueOf(((Number) obj).intValue() + 1);
    }
}
