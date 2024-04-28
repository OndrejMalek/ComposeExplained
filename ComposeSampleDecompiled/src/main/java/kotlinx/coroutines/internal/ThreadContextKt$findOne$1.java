package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class ThreadContextKt$findOne$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public static final ThreadContextKt$findOne$1 INSTANCE$1 = new ThreadContextKt$findOne$1(1);
    public static final ThreadContextKt$findOne$1 INSTANCE = new ThreadContextKt$findOne$1(0);
    public static final ThreadContextKt$findOne$1 INSTANCE$2 = new ThreadContextKt$findOne$1(2);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ThreadContextKt$findOne$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                return null;
            case 1:
                return obj;
            default:
                return (ThreadState) obj;
        }
    }
}
