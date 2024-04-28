package androidx.compose.ui.node;

import androidx.compose.ui.layout.LookaheadLayoutCoordinatesImpl;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class LookaheadDelegate extends LookaheadCapablePlaceable implements Measurable {
    public MeasureScope$layout$1 _measureResult;
    public final LinkedHashMap cachedAlignmentLinesMap;
    public final NodeCoordinator coordinator;
    public final LookaheadLayoutCoordinatesImpl lookaheadLayoutCoordinates;
    public LinkedHashMap oldAlignmentLines;
    public long position;

    public LookaheadDelegate(NodeCoordinator nodeCoordinator) {
        ResultKt.checkNotNullParameter(nodeCoordinator, "coordinator");
        this.coordinator = nodeCoordinator;
        this.position = IntOffset.Zero;
        this.lookaheadLayoutCoordinates = new LookaheadLayoutCoordinatesImpl(this);
        this.cachedAlignmentLinesMap = new LinkedHashMap();
    }

    public static final void access$set_measureResult(LookaheadDelegate lookaheadDelegate, MeasureScope$layout$1 measureScope$layout$1) {
        Unit unit;
        lookaheadDelegate.getClass();
        if (measureScope$layout$1 != null) {
            lookaheadDelegate.m166setMeasuredSizeozmzZPI(ResultKt.IntSize(measureScope$layout$1.width, measureScope$layout$1.height));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            lookaheadDelegate.m166setMeasuredSizeozmzZPI(0L);
        }
        if (!ResultKt.areEqual(lookaheadDelegate._measureResult, measureScope$layout$1) && measureScope$layout$1 != null) {
            LinkedHashMap linkedHashMap = lookaheadDelegate.oldAlignmentLines;
            Map map = measureScope$layout$1.alignmentLines;
            if (((linkedHashMap != null && !linkedHashMap.isEmpty()) || (!map.isEmpty())) && !ResultKt.areEqual(map, lookaheadDelegate.oldAlignmentLines)) {
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = lookaheadDelegate.coordinator.layoutNode.layoutDelegate.lookaheadPassDelegate;
                ResultKt.checkNotNull(lookaheadPassDelegate);
                lookaheadPassDelegate.alignmentLines.onAlignmentsChanged();
                LinkedHashMap linkedHashMap2 = lookaheadDelegate.oldAlignmentLines;
                if (linkedHashMap2 == null) {
                    linkedHashMap2 = new LinkedHashMap();
                    lookaheadDelegate.oldAlignmentLines = linkedHashMap2;
                }
                linkedHashMap2.clear();
                linkedHashMap2.putAll(map);
            }
        }
        lookaheadDelegate._measureResult = measureScope$layout$1;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getChild() {
        NodeCoordinator nodeCoordinator = this.coordinator.wrapped;
        if (nodeCoordinator != null) {
            return nodeCoordinator.getLookaheadDelegate();
        }
        return null;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.coordinator.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.coordinator.getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public final LayoutDirection getLayoutDirection() {
        return this.coordinator.layoutNode.layoutDirection;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LayoutNode getLayoutNode() {
        return this.coordinator.layoutNode;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final MeasureScope$layout$1 getMeasureResult$ui_release() {
        MeasureScope$layout$1 measureScope$layout$1 = this._measureResult;
        if (measureScope$layout$1 != null) {
            return measureScope$layout$1;
        }
        throw new IllegalStateException("LookaheadDelegate has not been measured yet when measureResult is requested.".toString());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getParent() {
        NodeCoordinator nodeCoordinator = this.coordinator.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.getLookaheadDelegate();
        }
        return null;
    }

    @Override // androidx.compose.ui.layout.Measurable
    public final Object getParentData() {
        this.coordinator.getParentData();
        return null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* renamed from: getPosition-nOcc-ac */
    public final long mo185getPositionnOccac() {
        return this.position;
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo165placeAtf8xVGno(long j, float f, Function1 function1) {
        long j2 = this.position;
        int i = IntOffset.$r8$clinit;
        if (j2 != j) {
            this.position = j;
            NodeCoordinator nodeCoordinator = this.coordinator;
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = nodeCoordinator.layoutNode.layoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate != null) {
                lookaheadPassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
            }
            LookaheadCapablePlaceable.invalidateAlignmentLinesFromPositionChange(nodeCoordinator);
        }
        if (this.isShallowPlacing) {
            return;
        }
        InnerNodeCoordinator.LookaheadDelegateImpl lookaheadDelegateImpl = (InnerNodeCoordinator.LookaheadDelegateImpl) this;
        int i2 = lookaheadDelegateImpl.$r8$classId;
        NodeCoordinator nodeCoordinator2 = lookaheadDelegateImpl.coordinator;
        switch (i2) {
            case 0:
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = nodeCoordinator2.layoutNode.layoutDelegate.lookaheadPassDelegate;
                ResultKt.checkNotNull(lookaheadPassDelegate2);
                lookaheadPassDelegate2.onNodePlaced$ui_release();
                return;
            default:
                MeasureScope$layout$1 measureResult$ui_release = lookaheadDelegateImpl.getMeasureResult$ui_release();
                LayoutDirection layoutDirection = nodeCoordinator2.layoutNode.layoutDirection;
                int i3 = Placeable.PlacementScope.parentWidth;
                LayoutDirection layoutDirection2 = Placeable.PlacementScope.parentLayoutDirection;
                Placeable.PlacementScope.parentWidth = measureResult$ui_release.width;
                Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
                boolean access$configureForPlacingForAlignment = Placeable.PlacementScope.Companion.access$configureForPlacingForAlignment(lookaheadDelegateImpl);
                lookaheadDelegateImpl.getMeasureResult$ui_release().placeChildren();
                lookaheadDelegateImpl.isPlacingForAlignment = access$configureForPlacingForAlignment;
                Placeable.PlacementScope.parentWidth = i3;
                Placeable.PlacementScope.parentLayoutDirection = layoutDirection2;
                return;
        }
    }

    /* renamed from: positionIn-Bjo55l4$ui_release */
    public final long m186positionInBjo55l4$ui_release(LookaheadDelegate lookaheadDelegate) {
        long j = IntOffset.Zero;
        LookaheadDelegate lookaheadDelegate2 = this;
        while (!ResultKt.areEqual(lookaheadDelegate2, lookaheadDelegate)) {
            long j2 = lookaheadDelegate2.position;
            j = ResultKt.IntOffset(((int) (j >> 32)) + ((int) (j2 >> 32)), ((int) (j & 4294967295L)) + ((int) (j2 & 4294967295L)));
            NodeCoordinator nodeCoordinator = lookaheadDelegate2.coordinator.wrappedBy;
            ResultKt.checkNotNull(nodeCoordinator);
            lookaheadDelegate2 = nodeCoordinator.getLookaheadDelegate();
            ResultKt.checkNotNull(lookaheadDelegate2);
        }
        return j;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final void replace$ui_release() {
        mo165placeAtf8xVGno(this.position, 0.0f, null);
    }
}
