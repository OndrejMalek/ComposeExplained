package androidx.compose.ui.node;

import android.graphics.Paint;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {
    public static final AndroidPaint modifierBoundsPaint;
    public LayoutModifierNode layoutModifierNode;
    public LookaheadDelegate lookaheadDelegate;

    static {
        AndroidPaint Paint = Brush.Paint();
        Paint.m92setColor8_81llA(Color.Blue);
        Paint paint = Paint.internalPaint;
        ResultKt.checkNotNullParameter(paint, "<this>");
        paint.setStrokeWidth(1.0f);
        Paint.m95setStylek9PVt8s(1);
        modifierBoundsPaint = Paint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode layoutModifierNode) {
        super(layoutNode);
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutModifierNode = layoutModifierNode;
        this.lookaheadDelegate = layoutNode.lookaheadRoot != null ? new InnerNodeCoordinator.LookaheadDelegateImpl(this) : null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
        ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        if (lookaheadDelegate == null) {
            return Snake.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
        }
        Integer num = (Integer) lookaheadDelegate.cachedAlignmentLinesMap.get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void ensureLookaheadDelegateCreated() {
        if (this.lookaheadDelegate == null) {
            this.lookaheadDelegate = new InnerNodeCoordinator.LookaheadDelegateImpl(this);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final Modifier.Node getTail() {
        return ((Modifier.Node) this.layoutModifierNode).node;
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public final Placeable mo164measureBRTryo0(long j) {
        m167setMeasurementConstraintsBRTryo0(j);
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        NodeCoordinator nodeCoordinator = this.wrapped;
        ResultKt.checkNotNull(nodeCoordinator);
        setMeasureResult$ui_release(layoutModifierNode.mo35measure3p2s80s(this, nodeCoordinator, j));
        onMeasured();
        return this;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void performDraw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        NodeCoordinator nodeCoordinator = this.wrapped;
        ResultKt.checkNotNull(nodeCoordinator);
        nodeCoordinator.draw(canvas);
        if (((AndroidComposeView) Snake.requireOwner(this.layoutNode)).getShowLayoutBounds()) {
            drawBorder(canvas, modifierBoundsPaint);
        }
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo165placeAtf8xVGno(long j, float f, Function1 function1) {
        m200placeSelff8xVGno(j, f, function1);
        if (this.isShallowPlacing) {
            return;
        }
        onPlaced();
        int i = (int) (this.measuredSize >> 32);
        LayoutDirection layoutDirection = this.layoutNode.layoutDirection;
        int i2 = Placeable.PlacementScope.parentWidth;
        LayoutDirection layoutDirection2 = Placeable.PlacementScope.parentLayoutDirection;
        Placeable.PlacementScope.parentWidth = i;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
        boolean access$configureForPlacingForAlignment = Placeable.PlacementScope.Companion.access$configureForPlacingForAlignment(this);
        getMeasureResult$ui_release().placeChildren();
        this.isPlacingForAlignment = access$configureForPlacingForAlignment;
        Placeable.PlacementScope.parentWidth = i2;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection2;
    }
}
