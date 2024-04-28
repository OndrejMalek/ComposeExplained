package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class Placeable {
    public long apparentToRealOffset;
    public int height;
    public long measuredSize = ResultKt.IntSize(0, 0);
    public long measurementConstraints = PlaceableKt.DefaultConstraints;
    public int width;

    /* loaded from: classes.dex */
    public abstract class PlacementScope {
        public static final Companion Companion = new Object();
        public static LayoutDirection parentLayoutDirection = LayoutDirection.Ltr;
        public static int parentWidth;

        /* loaded from: classes.dex */
        public final class Companion extends PlacementScope {
            public static final boolean access$configureForPlacingForAlignment(LookaheadCapablePlaceable lookaheadCapablePlaceable) {
                if (lookaheadCapablePlaceable == null) {
                    return false;
                }
                boolean z = lookaheadCapablePlaceable.isPlacingForAlignment;
                LookaheadCapablePlaceable parent = lookaheadCapablePlaceable.getParent();
                if (parent != null && parent.isPlacingForAlignment) {
                    lookaheadCapablePlaceable.isPlacingForAlignment = true;
                }
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = lookaheadCapablePlaceable.getLayoutNode().layoutDelegate;
                if (!lookaheadCapablePlaceable.isPlacingForAlignment) {
                    boolean z2 = lookaheadCapablePlaceable.isShallowPlacing;
                }
                return z;
            }
        }

        public static void place$default(PlacementScope placementScope, Placeable placeable, int i, int i2) {
            placementScope.getClass();
            ResultKt.checkNotNullParameter(placeable, "<this>");
            long IntOffset = ResultKt.IntOffset(i, i2);
            long j = placeable.apparentToRealOffset;
            int i3 = IntOffset.$r8$clinit;
            placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (j >> 32)), ((int) (IntOffset & 4294967295L)) + ((int) (j & 4294967295L))), 0.0f, null);
        }

        /* renamed from: place-70tqf50 */
        public static void m168place70tqf50(Placeable placeable, long j, float f) {
            ResultKt.checkNotNullParameter(placeable, "$this$place");
            long j2 = placeable.apparentToRealOffset;
            int i = IntOffset.$r8$clinit;
            placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (j >> 32)) + ((int) (j2 >> 32)), ((int) (j & 4294967295L)) + ((int) (j2 & 4294967295L))), f, null);
        }

        /* renamed from: place-70tqf50$default */
        public static /* synthetic */ void m169place70tqf50$default(PlacementScope placementScope, Placeable placeable, long j) {
            placementScope.getClass();
            m168place70tqf50(placeable, j, 0.0f);
        }

        public static void placeRelative$default(PlacementScope placementScope, Placeable placeable) {
            int i;
            placementScope.getClass();
            ResultKt.checkNotNullParameter(placeable, "<this>");
            long IntOffset = ResultKt.IntOffset(0, 0);
            if (parentLayoutDirection == LayoutDirection.Ltr || (i = parentWidth) == 0) {
                long j = placeable.apparentToRealOffset;
                int i2 = IntOffset.$r8$clinit;
                placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (j >> 32)), ((int) (IntOffset & 4294967295L)) + ((int) (j & 4294967295L))), 0.0f, null);
            } else {
                int i3 = i - placeable.width;
                int i4 = IntOffset.$r8$clinit;
                long IntOffset2 = ResultKt.IntOffset(i3 - ((int) (IntOffset >> 32)), (int) (IntOffset & 4294967295L));
                long j2 = placeable.apparentToRealOffset;
                placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (IntOffset2 >> 32)) + ((int) (j2 >> 32)), ((int) (IntOffset2 & 4294967295L)) + ((int) (j2 & 4294967295L))), 0.0f, null);
            }
        }

        public static void placeRelativeWithLayer$default(PlacementScope placementScope, Placeable placeable) {
            int i;
            int i2 = PlaceableKt.$r8$clinit;
            RootMeasurePolicy$measure$1 rootMeasurePolicy$measure$1 = RootMeasurePolicy$measure$1.INSTANCE$1;
            placementScope.getClass();
            ResultKt.checkNotNullParameter(placeable, "<this>");
            long IntOffset = ResultKt.IntOffset(0, 0);
            if (parentLayoutDirection == LayoutDirection.Ltr || (i = parentWidth) == 0) {
                long j = placeable.apparentToRealOffset;
                int i3 = IntOffset.$r8$clinit;
                placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (j >> 32)), ((int) (IntOffset & 4294967295L)) + ((int) (j & 4294967295L))), 0.0f, rootMeasurePolicy$measure$1);
            } else {
                int i4 = i - placeable.width;
                int i5 = IntOffset.$r8$clinit;
                long IntOffset2 = ResultKt.IntOffset(i4 - ((int) (IntOffset >> 32)), (int) (IntOffset & 4294967295L));
                long j2 = placeable.apparentToRealOffset;
                placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (IntOffset2 >> 32)) + ((int) (j2 >> 32)), ((int) (IntOffset2 & 4294967295L)) + ((int) (j2 & 4294967295L))), 0.0f, rootMeasurePolicy$measure$1);
            }
        }

        public static void placeWithLayer$default(PlacementScope placementScope, Placeable placeable, Function1 function1) {
            placementScope.getClass();
            ResultKt.checkNotNullParameter(placeable, "<this>");
            ResultKt.checkNotNullParameter(function1, "layerBlock");
            long IntOffset = ResultKt.IntOffset(0, 0);
            long j = placeable.apparentToRealOffset;
            int i = IntOffset.$r8$clinit;
            placeable.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (IntOffset >> 32)) + ((int) (j >> 32)), ((int) (IntOffset & 4294967295L)) + ((int) (j & 4294967295L))), 0.0f, function1);
        }
    }

    public Placeable() {
        int i = IntOffset.$r8$clinit;
        this.apparentToRealOffset = IntOffset.Zero;
    }

    public int getMeasuredWidth() {
        return (int) (this.measuredSize >> 32);
    }

    public final void onMeasuredSizeChanged() {
        this.width = ResultKt.coerceIn((int) (this.measuredSize >> 32), Constraints.m278getMinWidthimpl(this.measurementConstraints), Constraints.m276getMaxWidthimpl(this.measurementConstraints));
        int coerceIn = ResultKt.coerceIn((int) (this.measuredSize & 4294967295L), Constraints.m277getMinHeightimpl(this.measurementConstraints), Constraints.m275getMaxHeightimpl(this.measurementConstraints));
        this.height = coerceIn;
        int i = this.width;
        long j = this.measuredSize;
        this.apparentToRealOffset = ResultKt.IntOffset((i - ((int) (j >> 32))) / 2, (coerceIn - ((int) (j & 4294967295L))) / 2);
    }

    /* renamed from: placeAt-f8xVGno */
    public abstract void mo165placeAtf8xVGno(long j, float f, Function1 function1);

    /* renamed from: setMeasuredSize-ozmzZPI */
    public final void m166setMeasuredSizeozmzZPI(long j) {
        if (this.measuredSize == j) {
            return;
        }
        this.measuredSize = j;
        onMeasuredSizeChanged();
    }

    /* renamed from: setMeasurementConstraints-BRTryo0 */
    public final void m167setMeasurementConstraintsBRTryo0(long j) {
        if (Constraints.m273equalsimpl0(this.measurementConstraints, j)) {
            return;
        }
        this.measurementConstraints = j;
        onMeasuredSizeChanged();
    }
}
