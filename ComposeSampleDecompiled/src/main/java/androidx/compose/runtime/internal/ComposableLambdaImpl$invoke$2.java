package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidUriHandler;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposableLambdaImpl$invoke$2 extends Lambda implements Function2 {
    public final /* synthetic */ int $changed;
    public final /* synthetic */ Object $p1;
    public final /* synthetic */ Object $p2;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposableLambdaImpl$invoke$2(Object obj, Object obj2, Object obj3, int i, int i2) {
        super(2);
        this.$r8$classId = i2;
        this.this$0 = obj;
        this.$p1 = obj2;
        this.$p2 = obj3;
        this.$changed = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            case 1:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            default:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
        }
    }

    public final void invoke(Composer composer, int i) {
        int i2 = this.$r8$classId;
        int i3 = this.$changed;
        Object obj = this.$p2;
        Object obj2 = this.$p1;
        Object obj3 = this.this$0;
        switch (i2) {
            case 0:
                ResultKt.checkNotNullParameter(composer, "nc");
                ((ComposableLambdaImpl) obj3).invoke(obj2, obj, composer, ResultKt.updateChangedFlags(i3) | 1);
                return;
            case 1:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return;
                    }
                }
                CompositionLocalsKt.ProvideCommonCompositionLocals((AndroidComposeView) obj3, (AndroidUriHandler) obj2, (Function2) obj, composer, ((i3 << 3) & 896) | 72);
                return;
            default:
                CompositionLocalsKt.ProvideCommonCompositionLocals((Owner) obj3, (UriHandler) obj2, (Function2) obj, composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
        }
    }
}
