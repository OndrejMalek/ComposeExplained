package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class MeasureScope$layout$1 {
    public final /* synthetic */ Function1 $placementBlock;
    public final /* synthetic */ int $width;
    public final Map alignmentLines;
    public final int height;
    public final /* synthetic */ MeasureScope this$0;
    public final int width;

    public MeasureScope$layout$1(int i, int i2, Map map, MeasureScope measureScope, Function1 function1) {
        this.$width = i;
        this.this$0 = measureScope;
        this.$placementBlock = function1;
        this.width = i;
        this.height = i2;
        this.alignmentLines = map;
    }

    public final void placeChildren() {
        Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.Companion;
        MeasureScope measureScope = this.this$0;
        LayoutDirection layoutDirection = measureScope.getLayoutDirection();
        LookaheadCapablePlaceable lookaheadCapablePlaceable = measureScope instanceof LookaheadCapablePlaceable ? (LookaheadCapablePlaceable) measureScope : null;
        int i = Placeable.PlacementScope.parentWidth;
        LayoutDirection layoutDirection2 = Placeable.PlacementScope.parentLayoutDirection;
        Placeable.PlacementScope.parentWidth = this.$width;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
        boolean access$configureForPlacingForAlignment = Placeable.PlacementScope.Companion.access$configureForPlacingForAlignment(lookaheadCapablePlaceable);
        this.$placementBlock.invoke(companion);
        if (lookaheadCapablePlaceable != null) {
            lookaheadCapablePlaceable.isPlacingForAlignment = access$configureForPlacingForAlignment;
        }
        Placeable.PlacementScope.parentWidth = i;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection2;
    }
}
