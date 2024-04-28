package androidx.compose.ui.layout;

import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.layout.Placeable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class RootMeasurePolicy$measure$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final RootMeasurePolicy$measure$1 INSTANCE$1 = new RootMeasurePolicy$measure$1(1);
    public static final RootMeasurePolicy$measure$1 INSTANCE = new RootMeasurePolicy$measure$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RootMeasurePolicy$measure$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter((Placeable.PlacementScope) obj, "$this$layout");
                return unit;
            default:
                ResultKt.checkNotNullParameter((ReusableGraphicsLayerScope) obj, "$this$null");
                return unit;
        }
    }
}
