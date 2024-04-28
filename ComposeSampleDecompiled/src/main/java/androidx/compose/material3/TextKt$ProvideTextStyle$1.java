package androidx.compose.material3;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class TextKt$ProvideTextStyle$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $$changed;
    public final /* synthetic */ Object $content;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TextKt$ProvideTextStyle$1(int i, int i2, Object obj, Object obj2) {
        super(2);
        this.$r8$classId = i2;
        this.$value = obj;
        this.$content = obj2;
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
            case 2:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            case 3:
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
        Object obj = this.$content;
        Object obj2 = this.$value;
        switch (i2) {
            case 0:
                TextKt.ProvideTextStyle((TextStyle) obj2, (Function2) obj, composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
            case 1:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return;
                    }
                }
                TextKt.ProvideTextStyle(((Typography) obj2).bodyLarge, (Function2) obj, composer, (i3 >> 6) & 112);
                return;
            case 2:
                ProvidedValue[] providedValueArr = (ProvidedValue[]) obj2;
                _BOUNDARY.CompositionLocalProvider((ProvidedValue[]) Arrays.copyOf(providedValueArr, providedValueArr.length), (Function2) obj, composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
            case 3:
                ResultKt.checkNotNullParameter(composer, "nc");
                ((ComposableLambdaImpl) obj2).invoke(obj, composer, ResultKt.updateChangedFlags(i3) | 1);
                return;
            default:
                AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals((AndroidComposeView) obj2, (Function2) obj, composer, ResultKt.updateChangedFlags(i3 | 1));
                return;
        }
    }
}
