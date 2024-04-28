package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class RootMeasurePolicy$measure$4 extends Lambda implements Function1 {
    public final /* synthetic */ List $placeables;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RootMeasurePolicy$measure$4(int i, ArrayList arrayList) {
        super(1);
        this.$r8$classId = i;
        this.$placeables = arrayList;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        int i = this.$r8$classId;
        int i2 = 0;
        List list = this.$placeables;
        switch (i) {
            case 0:
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                int size = list.size();
                while (i2 < size) {
                    Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, (Placeable) list.get(i2));
                    i2++;
                }
                return unit;
            default:
                ResultKt.checkNotNullParameter(obj, "state");
                int size2 = list.size();
                while (i2 < size2) {
                    ((Function1) list.get(i2)).invoke(obj);
                    i2++;
                }
                return unit;
        }
    }
}
