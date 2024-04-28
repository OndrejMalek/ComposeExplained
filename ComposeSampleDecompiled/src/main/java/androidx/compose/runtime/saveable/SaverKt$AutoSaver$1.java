package androidx.compose.runtime.saveable;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class SaverKt$AutoSaver$1 extends Lambda implements Function2 {
    public static final SaverKt$AutoSaver$1 INSTANCE = new Lambda(2);

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
        ResultKt.checkNotNullParameter(null, "$this$Saver");
        return obj2;
    }
}
