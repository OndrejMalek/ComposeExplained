package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Placeable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class FillNode$measure$1 extends Lambda implements Function1 {
    public final /* synthetic */ Placeable $placeable;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FillNode$measure$1(Placeable placeable, int i) {
        super(1);
        this.$r8$classId = i;
        this.$placeable = placeable;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        Placeable placeable = this.$placeable;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable);
                return;
            case 1:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable);
                return;
            case 2:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0);
                return;
            case 3:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0);
                return;
            default:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, placeable);
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Placeable.PlacementScope) obj);
                return unit;
            case 1:
                invoke((Placeable.PlacementScope) obj);
                return unit;
            case 2:
                invoke((Placeable.PlacementScope) obj);
                return unit;
            case 3:
                invoke((Placeable.PlacementScope) obj);
                return unit;
            default:
                invoke((Placeable.PlacementScope) obj);
                return unit;
        }
    }
}
