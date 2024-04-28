package androidx.compose.foundation;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class IndicationKt$LocalIndication$1 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public static final IndicationKt$LocalIndication$1 INSTANCE$1 = new IndicationKt$LocalIndication$1(1);
    public static final IndicationKt$LocalIndication$1 INSTANCE = new IndicationKt$LocalIndication$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ IndicationKt$LocalIndication$1(int i) {
        super(0);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return DefaultDebugIndication.INSTANCE;
            default:
                return null;
        }
    }
}
