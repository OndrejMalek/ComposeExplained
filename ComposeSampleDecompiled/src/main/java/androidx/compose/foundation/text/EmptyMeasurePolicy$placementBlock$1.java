package androidx.compose.foundation.text;

import androidx.compose.ui.layout.Placeable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class EmptyMeasurePolicy$placementBlock$1 extends Lambda implements Function1 {
    public static final EmptyMeasurePolicy$placementBlock$1 INSTANCE = new Lambda(1);

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ResultKt.checkNotNullParameter((Placeable.PlacementScope) obj, "$this$null");
        return Unit.INSTANCE;
    }
}
