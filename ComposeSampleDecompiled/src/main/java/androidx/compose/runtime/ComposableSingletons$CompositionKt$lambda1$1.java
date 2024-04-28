package androidx.compose.runtime;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* renamed from: androidx.compose.runtime.ComposableSingletons$CompositionKt$lambda-1$1, reason: invalid class name */
/* loaded from: classes.dex */
public final class ComposableSingletons$CompositionKt$lambda1$1 extends Lambda implements Function2 {
    public static final ComposableSingletons$CompositionKt$lambda1$1 INSTANCE = new ComposableSingletons$CompositionKt$lambda1$1(0);
    public static final ComposableSingletons$CompositionKt$lambda1$1 INSTANCE$1 = new ComposableSingletons$CompositionKt$lambda1$1(1);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposableSingletons$CompositionKt$lambda1$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            default:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
        }
    }

    public final void invoke(Composer composer, int i) {
        switch (this.$r8$classId) {
            case 0:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return;
                    }
                    return;
                }
                return;
            default:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return;
                    }
                    return;
                }
                return;
        }
    }
}
