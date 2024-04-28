package eu.malek.composesample2;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.platform.ComposeView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class MainActivityKt$Greetings$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $$changed;
    public final /* synthetic */ Object $dp;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MainActivityKt$Greetings$1(int i, int i2, Object obj) {
        super(2);
        this.$r8$classId = i2;
        this.$dp = obj;
        this.$$changed = i;
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
        int i3 = this.$$changed;
        Object obj = this.$dp;
        switch (i2) {
            case 0:
                ResultKt.Greetings((DataPackage) obj, composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
            case 1:
                ResultKt.access$GreetingsGreetings((DataPackage) obj, composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
            default:
                ((ComposeView) obj).Content(composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
        }
    }
}
