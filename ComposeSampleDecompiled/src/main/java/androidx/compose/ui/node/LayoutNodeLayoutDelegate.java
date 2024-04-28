package androidx.compose.ui.node;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class LayoutNodeLayoutDelegate {
    public int childrenAccessingCoordinatesDuringPlacement;
    public boolean coordinatesAccessedDuringModifierPlacement;
    public boolean coordinatesAccessedDuringPlacement;
    public final LayoutNode layoutNode;
    public boolean layoutPending;
    public boolean layoutPendingForAlignment;
    public int layoutState;
    public boolean lookaheadLayoutPending;
    public boolean lookaheadLayoutPendingForAlignment;
    public boolean lookaheadMeasurePending;
    public LookaheadPassDelegate lookaheadPassDelegate;
    public final MeasurePassDelegate measurePassDelegate;
    public boolean measurePending;
    public int nextChildLookaheadPlaceOrder;
    public int nextChildPlaceOrder;

    /* loaded from: classes.dex */
    public final class LookaheadPassDelegate extends Placeable implements Measurable, AlignmentLinesOwner {
        public final MutableVector _childDelegates;
        public boolean childDelegatesDirty;
        public boolean isPlaced;
        public boolean layingOutChildren;
        public Constraints lookaheadConstraints;
        public boolean placedOnce;
        public boolean relayoutWithoutParentInProgress;
        public int previousPlaceOrder = Integer.MAX_VALUE;
        public int placeOrder = Integer.MAX_VALUE;
        public int measuredByParent = 3;
        public long lastPosition = IntOffset.Zero;
        public final LookaheadAlignmentLines alignmentLines = new LookaheadAlignmentLines(this, 0);

        /* JADX WARN: Type inference failed for: r0v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
        public LookaheadPassDelegate() {
            ?? obj = new Object();
            obj.content = new LookaheadPassDelegate[16];
            obj.size = 0;
            this._childDelegates = obj;
            this.childDelegatesDirty = true;
            LayoutNodeLayoutDelegate.this.measurePassDelegate.getClass();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void forEachChildAlignmentLinesOwner(Function1 function1) {
            MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LookaheadPassDelegate lookaheadPassDelegate = ((LayoutNode) objArr[i2]).layoutDelegate.lookaheadPassDelegate;
                    ResultKt.checkNotNull(lookaheadPassDelegate);
                    function1.invoke(lookaheadPassDelegate);
                    i2++;
                } while (i2 < i);
            }
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final LookaheadAlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final InnerNodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.nodes.innerCoordinator;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || (layoutNodeLayoutDelegate = parent$ui_release.layoutDelegate) == null) {
                return null;
            }
            return layoutNodeLayoutDelegate.lookaheadPassDelegate;
        }

        @Override // androidx.compose.ui.layout.Measurable
        public final Object getParentData() {
            return null;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final boolean isPlaced() {
            return this.isPlaced;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void layoutChildren() {
            MutableVector mutableVector;
            int i;
            this.layingOutChildren = true;
            LookaheadAlignmentLines lookaheadAlignmentLines = this.alignmentLines;
            lookaheadAlignmentLines.recalculateQueryOwner();
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            boolean z = layoutNodeLayoutDelegate.lookaheadLayoutPending;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            if (z && (i = (mutableVector = layoutNode.get_children$ui_release()).size) > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    if (layoutNode2.layoutDelegate.lookaheadMeasurePending && layoutNode2.getMeasuredByParentInLookahead$ui_release() == 1) {
                        LookaheadPassDelegate lookaheadPassDelegate = layoutNode2.layoutDelegate.lookaheadPassDelegate;
                        ResultKt.checkNotNull(lookaheadPassDelegate);
                        Constraints constraints = this.lookaheadConstraints;
                        ResultKt.checkNotNull(constraints);
                        if (lookaheadPassDelegate.m181remeasureBRTryo0(constraints.value)) {
                            LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, false, 3);
                        }
                    }
                    i2++;
                } while (i2 < i);
            }
            LookaheadDelegate lookaheadDelegate = getInnerCoordinator().lookaheadDelegate;
            ResultKt.checkNotNull(lookaheadDelegate);
            if (layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment || (!lookaheadDelegate.isPlacingForAlignment && layoutNodeLayoutDelegate.lookaheadLayoutPending)) {
                layoutNodeLayoutDelegate.lookaheadLayoutPending = false;
                int i3 = layoutNodeLayoutDelegate.layoutState;
                layoutNodeLayoutDelegate.layoutState = 4;
                Owner requireOwner = Snake.requireOwner(layoutNode);
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(false);
                OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) requireOwner).getSnapshotObserver();
                NodeCoordinator$invoke$1 nodeCoordinator$invoke$1 = new NodeCoordinator$invoke$1(this, 7, lookaheadDelegate);
                snapshotObserver.getClass();
                ResultKt.checkNotNullParameter(layoutNode, "node");
                if (layoutNode.lookaheadRoot != null) {
                    snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLookaheadLayout, nodeCoordinator$invoke$1);
                } else {
                    snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLayout, nodeCoordinator$invoke$1);
                }
                layoutNodeLayoutDelegate.layoutState = i3;
                if (layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement && lookaheadDelegate.isPlacingForAlignment) {
                    requestLayout();
                }
                layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = false;
            }
            if (lookaheadAlignmentLines.usedDuringParentLayout) {
                lookaheadAlignmentLines.previousUsedDuringParentLayout = true;
            }
            if (lookaheadAlignmentLines.dirty && lookaheadAlignmentLines.getRequired$ui_release()) {
                lookaheadAlignmentLines.recalculate();
            }
            this.layingOutChildren = false;
        }

        public final void markNodeAndSubtreeAsPlaced() {
            boolean z = this.isPlaced;
            this.isPlaced = true;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (!z && layoutNodeLayoutDelegate.lookaheadMeasurePending) {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNodeLayoutDelegate.layoutNode, true, 2);
            }
            MutableVector mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode = (LayoutNode) objArr[i2];
                    if (layoutNode.getPlaceOrder$ui_release() != Integer.MAX_VALUE) {
                        LookaheadPassDelegate lookaheadPassDelegate = layoutNode.layoutDelegate.lookaheadPassDelegate;
                        ResultKt.checkNotNull(lookaheadPassDelegate);
                        lookaheadPassDelegate.markNodeAndSubtreeAsPlaced();
                        LayoutNode.rescheduleRemeasureOrRelayout$ui_release(layoutNode);
                    }
                    i2++;
                } while (i2 < i);
            }
        }

        public final void markSubtreeAsNotPlaced() {
            if (this.isPlaced) {
                int i = 0;
                this.isPlaced = false;
                MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                int i2 = mutableVector.size;
                if (i2 > 0) {
                    Object[] objArr = mutableVector.content;
                    do {
                        LookaheadPassDelegate lookaheadPassDelegate = ((LayoutNode) objArr[i]).layoutDelegate.lookaheadPassDelegate;
                        ResultKt.checkNotNull(lookaheadPassDelegate);
                        lookaheadPassDelegate.markSubtreeAsNotPlaced();
                        i++;
                    } while (i < i2);
                }
            }
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo164measureBRTryo0(long j) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if (parent$ui_release == null) {
                this.measuredByParent = 3;
            } else {
                if (this.measuredByParent != 3 && !layoutNode.canMultiMeasure) {
                    throw new IllegalStateException("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()".toString());
                }
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = parent$ui_release.layoutDelegate;
                int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate2.layoutState);
                int i = 1;
                if (ordinal != 0 && ordinal != 1) {
                    i = 2;
                    if (ordinal != 2 && ordinal != 3) {
                        throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is ".concat(DurationKt$$ExternalSyntheticCheckNotZero0.stringValueOf(layoutNodeLayoutDelegate2.layoutState)));
                    }
                }
                this.measuredByParent = i;
            }
            LayoutNode layoutNode2 = layoutNodeLayoutDelegate.layoutNode;
            if (layoutNode2.intrinsicsUsageByParent == 3) {
                layoutNode2.clearSubtreeIntrinsicsUsage$ui_release();
            }
            m181remeasureBRTryo0(j);
            return this;
        }

        public final void notifyChildrenUsingCoordinatesWhilePlacing() {
            MutableVector mutableVector;
            int i;
            Owner owner;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement <= 0 || (i = (mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release()).size) <= 0) {
                return;
            }
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode.layoutDelegate;
                if ((layoutNodeLayoutDelegate2.coordinatesAccessedDuringPlacement || layoutNodeLayoutDelegate2.coordinatesAccessedDuringModifierPlacement) && !layoutNodeLayoutDelegate2.layoutPending && !layoutNode.isVirtual && (owner = layoutNode.owner) != null) {
                    ((AndroidComposeView) owner).onRequestRelayout(layoutNode, true, false);
                }
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate2.lookaheadPassDelegate;
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                }
                i2++;
            } while (i2 < i);
        }

        public final void onNodePlaced$ui_release() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
            int i;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (!this.isPlaced) {
                markNodeAndSubtreeAsPlaced();
            }
            if (parent$ui_release == null) {
                this.placeOrder = 0;
            } else if (!this.relayoutWithoutParentInProgress && ((i = (layoutNodeLayoutDelegate = parent$ui_release.layoutDelegate).layoutState) == 3 || i == 4)) {
                if (this.placeOrder != Integer.MAX_VALUE) {
                    throw new IllegalStateException("Place was called on a node which was placed already".toString());
                }
                int i2 = layoutNodeLayoutDelegate.nextChildLookaheadPlaceOrder;
                this.placeOrder = i2;
                layoutNodeLayoutDelegate.nextChildLookaheadPlaceOrder = i2 + 1;
            }
            layoutChildren();
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo165placeAtf8xVGno(long j, float f, Function1 function1) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            layoutNodeLayoutDelegate.layoutState = 4;
            int i = 1;
            this.placedOnce = true;
            long j2 = this.lastPosition;
            int i2 = IntOffset.$r8$clinit;
            if (j != j2) {
                if (layoutNodeLayoutDelegate.coordinatesAccessedDuringModifierPlacement || layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement) {
                    layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
                }
                notifyChildrenUsingCoordinatesWhilePlacing();
            }
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            Owner requireOwner = Snake.requireOwner(layoutNode);
            if (layoutNodeLayoutDelegate.lookaheadLayoutPending || !this.isPlaced) {
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringModifierPlacement(false);
                this.alignmentLines.usedByModifierLayout = false;
                OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) requireOwner).getSnapshotObserver();
                LayoutNodeLayoutDelegate$performMeasure$2 layoutNodeLayoutDelegate$performMeasure$2 = new LayoutNodeLayoutDelegate$performMeasure$2(layoutNodeLayoutDelegate, j, i);
                snapshotObserver.getClass();
                ResultKt.checkNotNullParameter(layoutNode, "node");
                if (layoutNode.lookaheadRoot != null) {
                    snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLayoutModifierInLookahead, layoutNodeLayoutDelegate$performMeasure$2);
                } else {
                    snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLayoutModifier, layoutNodeLayoutDelegate$performMeasure$2);
                }
            } else {
                onNodePlaced$ui_release();
            }
            this.lastPosition = j;
            layoutNodeLayoutDelegate.layoutState = 5;
        }

        /* renamed from: remeasure-BRTryo0, reason: not valid java name */
        public final boolean m181remeasureBRTryo0(long j) {
            Constraints constraints;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            layoutNode.canMultiMeasure = layoutNode.canMultiMeasure || (parent$ui_release != null && parent$ui_release.canMultiMeasure);
            if (!layoutNode.layoutDelegate.lookaheadMeasurePending && (constraints = this.lookaheadConstraints) != null && Constraints.m273equalsimpl0(constraints.value, j)) {
                Owner owner = layoutNode.owner;
                if (owner != null) {
                    ((AndroidComposeView) owner).forceMeasureTheSubtree(layoutNode, true);
                }
                layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                return false;
            }
            this.lookaheadConstraints = new Constraints(j);
            this.alignmentLines.usedByModifierMeasurement = false;
            forEachChildAlignmentLinesOwner(BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$4);
            LookaheadDelegate lookaheadDelegate = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate == null) {
                throw new IllegalStateException("Lookahead result from lookaheadRemeasure cannot be null".toString());
            }
            long IntSize = ResultKt.IntSize(lookaheadDelegate.width, lookaheadDelegate.height);
            layoutNodeLayoutDelegate.layoutState = 2;
            layoutNodeLayoutDelegate.lookaheadMeasurePending = false;
            OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSnapshotObserver();
            LayoutNodeLayoutDelegate$performMeasure$2 layoutNodeLayoutDelegate$performMeasure$2 = new LayoutNodeLayoutDelegate$performMeasure$2(layoutNodeLayoutDelegate, j, 2);
            snapshotObserver.getClass();
            if (layoutNode.lookaheadRoot != null) {
                snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLookaheadMeasure, layoutNodeLayoutDelegate$performMeasure$2);
            } else {
                snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingMeasure, layoutNodeLayoutDelegate$performMeasure$2);
            }
            layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
            layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = true;
            if (LayoutNodeLayoutDelegate.isOutMostLookaheadRoot(layoutNode)) {
                layoutNodeLayoutDelegate.layoutPending = true;
                layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
            } else {
                layoutNodeLayoutDelegate.measurePending = true;
            }
            layoutNodeLayoutDelegate.layoutState = 5;
            m166setMeasuredSizeozmzZPI(ResultKt.IntSize(lookaheadDelegate.width, lookaheadDelegate.height));
            return (((int) (IntSize >> 32)) == lookaheadDelegate.width && ((int) (4294967295L & IntSize)) == lookaheadDelegate.height) ? false : true;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestLayout() {
            Owner owner;
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            if (layoutNode.isVirtual || (owner = layoutNode.owner) == null) {
                return;
            }
            ((AndroidComposeView) owner).onRequestRelayout(layoutNode, true, false);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestMeasure() {
            LayoutNode.requestLookaheadRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, 3);
        }
    }

    /* loaded from: classes.dex */
    public final class MeasurePassDelegate extends Placeable implements Measurable, AlignmentLinesOwner {
        public final MutableVector _childDelegates;
        public boolean childDelegatesDirty;
        public boolean isPlaced;
        public Function1 lastLayerBlock;
        public float lastZIndex;
        public boolean layingOutChildren;
        public boolean measuredOnce;
        public boolean placedOnce;
        public boolean relayoutWithoutParentInProgress;
        public float zIndex;
        public int previousPlaceOrder = Integer.MAX_VALUE;
        public int placeOrder = Integer.MAX_VALUE;
        public int measuredByParent = 3;
        public long lastPosition = IntOffset.Zero;
        public final LookaheadAlignmentLines alignmentLines = new LookaheadAlignmentLines(this, 1);

        /* JADX WARN: Type inference failed for: r3v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
        public MeasurePassDelegate() {
            ?? obj = new Object();
            obj.content = new MeasurePassDelegate[16];
            obj.size = 0;
            this._childDelegates = obj;
            this.childDelegatesDirty = true;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void forEachChildAlignmentLinesOwner(Function1 function1) {
            MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    function1.invoke(((LayoutNode) objArr[i2]).layoutDelegate.measurePassDelegate);
                    i2++;
                } while (i2 < i);
            }
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final LookaheadAlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final InnerNodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.nodes.innerCoordinator;
        }

        @Override // androidx.compose.ui.layout.Placeable
        public final int getMeasuredWidth() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getMeasuredWidth();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || (layoutNodeLayoutDelegate = parent$ui_release.layoutDelegate) == null) {
                return null;
            }
            return layoutNodeLayoutDelegate.measurePassDelegate;
        }

        @Override // androidx.compose.ui.layout.Measurable
        public final Object getParentData() {
            return null;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final boolean isPlaced() {
            return this.isPlaced;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void layoutChildren() {
            MutableVector mutableVector;
            int i;
            this.layingOutChildren = true;
            LookaheadAlignmentLines lookaheadAlignmentLines = this.alignmentLines;
            lookaheadAlignmentLines.recalculateQueryOwner();
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            boolean z = layoutNodeLayoutDelegate.layoutPending;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            if (z && (i = (mutableVector = layoutNode.get_children$ui_release()).size) > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    if (layoutNode2.layoutDelegate.measurePending && layoutNode2.getMeasuredByParent$ui_release() == 1 && LayoutNode.m172remeasure_Sx5XlM$ui_release$default(layoutNode2)) {
                        LayoutNode.requestRemeasure$ui_release$default(layoutNode, false, 3);
                    }
                    i2++;
                } while (i2 < i);
            }
            if (layoutNodeLayoutDelegate.layoutPendingForAlignment || (!getInnerCoordinator().isPlacingForAlignment && layoutNodeLayoutDelegate.layoutPending)) {
                layoutNodeLayoutDelegate.layoutPending = false;
                int i3 = layoutNodeLayoutDelegate.layoutState;
                layoutNodeLayoutDelegate.layoutState = 3;
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(false);
                OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSnapshotObserver();
                NodeCoordinator$invoke$1 nodeCoordinator$invoke$1 = new NodeCoordinator$invoke$1(this, 8, layoutNode);
                snapshotObserver.getClass();
                snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLayout, nodeCoordinator$invoke$1);
                layoutNodeLayoutDelegate.layoutState = i3;
                if (getInnerCoordinator().isPlacingForAlignment && layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement) {
                    requestLayout();
                }
                layoutNodeLayoutDelegate.layoutPendingForAlignment = false;
            }
            if (lookaheadAlignmentLines.usedDuringParentLayout) {
                lookaheadAlignmentLines.previousUsedDuringParentLayout = true;
            }
            if (lookaheadAlignmentLines.dirty && lookaheadAlignmentLines.getRequired$ui_release()) {
                lookaheadAlignmentLines.recalculate();
            }
            this.layingOutChildren = false;
        }

        public final void markNodeAndSubtreeAsPlaced$1() {
            boolean z = this.isPlaced;
            this.isPlaced = true;
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            if (!z) {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
                if (layoutNodeLayoutDelegate.measurePending) {
                    LayoutNode.requestRemeasure$ui_release$default(layoutNode, true, 2);
                } else if (layoutNodeLayoutDelegate.lookaheadMeasurePending) {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, true, 2);
                }
            }
            NodeChain nodeChain = layoutNode.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
            for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !ResultKt.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
                if (nodeCoordinator2.lastLayerDrawingWasSkipped) {
                    nodeCoordinator2.invalidateLayer();
                }
            }
            MutableVector mutableVector = layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    if (layoutNode2.getPlaceOrder$ui_release() != Integer.MAX_VALUE) {
                        layoutNode2.layoutDelegate.measurePassDelegate.markNodeAndSubtreeAsPlaced$1();
                        LayoutNode.rescheduleRemeasureOrRelayout$ui_release(layoutNode2);
                    }
                    i2++;
                } while (i2 < i);
            }
        }

        public final void markSubtreeAsNotPlaced$1() {
            if (this.isPlaced) {
                int i = 0;
                this.isPlaced = false;
                MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                int i2 = mutableVector.size;
                if (i2 > 0) {
                    Object[] objArr = mutableVector.content;
                    do {
                        ((LayoutNode) objArr[i]).layoutDelegate.measurePassDelegate.markSubtreeAsNotPlaced$1();
                        i++;
                    } while (i < i2);
                }
            }
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo164measureBRTryo0(long j) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            if (layoutNode.intrinsicsUsageByParent == 3) {
                layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
            }
            LayoutNode layoutNode2 = layoutNodeLayoutDelegate.layoutNode;
            int i = 1;
            if (LayoutNodeLayoutDelegate.isOutMostLookaheadRoot(layoutNode2)) {
                this.measuredOnce = true;
                m167setMeasurementConstraintsBRTryo0(j);
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                ResultKt.checkNotNull(lookaheadPassDelegate);
                lookaheadPassDelegate.measuredByParent = 3;
                lookaheadPassDelegate.mo164measureBRTryo0(j);
            }
            LayoutNode parent$ui_release = layoutNode2.getParent$ui_release();
            if (parent$ui_release == null) {
                this.measuredByParent = 3;
            } else {
                if (this.measuredByParent != 3 && !layoutNode2.canMultiMeasure) {
                    throw new IllegalStateException("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()".toString());
                }
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = parent$ui_release.layoutDelegate;
                int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate2.layoutState);
                if (ordinal != 0) {
                    i = 2;
                    if (ordinal != 2) {
                        throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is ".concat(DurationKt$$ExternalSyntheticCheckNotZero0.stringValueOf(layoutNodeLayoutDelegate2.layoutState)));
                    }
                }
                this.measuredByParent = i;
            }
            m183remeasureBRTryo0(j);
            return this;
        }

        public final void notifyChildrenUsingCoordinatesWhilePlacing() {
            MutableVector mutableVector;
            int i;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement <= 0 || (i = (mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release()).size) <= 0) {
                return;
            }
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode.layoutDelegate;
                if ((layoutNodeLayoutDelegate2.coordinatesAccessedDuringPlacement || layoutNodeLayoutDelegate2.coordinatesAccessedDuringModifierPlacement) && !layoutNodeLayoutDelegate2.layoutPending) {
                    layoutNode.requestRelayout$ui_release(false);
                }
                layoutNodeLayoutDelegate2.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                i2++;
            } while (i2 < i);
        }

        public final void onNodePlaced$ui_release() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            float f = getInnerCoordinator().zIndex;
            NodeChain nodeChain = layoutNodeLayoutDelegate.layoutNode.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator;
            while (nodeCoordinator != nodeChain.innerCoordinator) {
                ResultKt.checkNotNull(nodeCoordinator, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
                LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) nodeCoordinator;
                f += layoutModifierNodeCoordinator.zIndex;
                nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
            }
            if (f != this.zIndex) {
                this.zIndex = f;
                if (parent$ui_release != null) {
                    parent$ui_release.onZSortedChildrenInvalidated$ui_release();
                }
                if (parent$ui_release != null) {
                    parent$ui_release.invalidateLayer$ui_release();
                }
            }
            if (!this.isPlaced) {
                if (parent$ui_release != null) {
                    parent$ui_release.invalidateLayer$ui_release();
                }
                markNodeAndSubtreeAsPlaced$1();
            }
            if (parent$ui_release == null) {
                this.placeOrder = 0;
            } else if (!this.relayoutWithoutParentInProgress) {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = parent$ui_release.layoutDelegate;
                if (layoutNodeLayoutDelegate2.layoutState == 3) {
                    if (this.placeOrder != Integer.MAX_VALUE) {
                        throw new IllegalStateException("Place was called on a node which was placed already".toString());
                    }
                    int i = layoutNodeLayoutDelegate2.nextChildPlaceOrder;
                    this.placeOrder = i;
                    layoutNodeLayoutDelegate2.nextChildPlaceOrder = i + 1;
                }
            }
            layoutChildren();
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo165placeAtf8xVGno(long j, float f, Function1 function1) {
            long j2 = this.lastPosition;
            int i = IntOffset.$r8$clinit;
            boolean z = j == j2;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (!z) {
                if (layoutNodeLayoutDelegate.coordinatesAccessedDuringModifierPlacement || layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement) {
                    layoutNodeLayoutDelegate.layoutPending = true;
                }
                notifyChildrenUsingCoordinatesWhilePlacing();
            }
            if (LayoutNodeLayoutDelegate.isOutMostLookaheadRoot(layoutNodeLayoutDelegate.layoutNode)) {
                Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.Companion;
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                ResultKt.checkNotNull(lookaheadPassDelegate);
                LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
                if (parent$ui_release != null) {
                    parent$ui_release.layoutDelegate.nextChildLookaheadPlaceOrder = 0;
                }
                lookaheadPassDelegate.placeOrder = Integer.MAX_VALUE;
                Placeable.PlacementScope.place$default(companion, lookaheadPassDelegate, (int) (j >> 32), (int) (4294967295L & j));
            }
            m182placeOuterCoordinatorf8xVGno(j, f, function1);
        }

        /* renamed from: placeOuterCoordinator-f8xVGno, reason: not valid java name */
        public final void m182placeOuterCoordinatorf8xVGno(final long j, final float f, final Function1 function1) {
            final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            layoutNodeLayoutDelegate.layoutState = 3;
            this.lastPosition = j;
            this.lastZIndex = f;
            this.lastLayerBlock = function1;
            this.placedOnce = true;
            Owner requireOwner = Snake.requireOwner(layoutNodeLayoutDelegate.layoutNode);
            if (layoutNodeLayoutDelegate.layoutPending || !this.isPlaced) {
                this.alignmentLines.usedByModifierLayout = false;
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringModifierPlacement(false);
                OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) requireOwner).getSnapshotObserver();
                LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
                Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$placeOuterCoordinator$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Function1 function12 = Function1.this;
                        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNodeLayoutDelegate;
                        long j2 = j;
                        float f2 = f;
                        if (function12 == null) {
                            Placeable.PlacementScope.m168place70tqf50(layoutNodeLayoutDelegate2.getOuterCoordinator(), j2, f2);
                        } else {
                            NodeCoordinator outerCoordinator = layoutNodeLayoutDelegate2.getOuterCoordinator();
                            ResultKt.checkNotNullParameter(outerCoordinator, "$this$placeWithLayer");
                            long j3 = outerCoordinator.apparentToRealOffset;
                            int i = IntOffset.$r8$clinit;
                            outerCoordinator.mo165placeAtf8xVGno(ResultKt.IntOffset(((int) (j2 >> 32)) + ((int) (j3 >> 32)), ((int) (j2 & 4294967295L)) + ((int) (j3 & 4294967295L))), f2, function12);
                        }
                        return Unit.INSTANCE;
                    }
                };
                snapshotObserver.getClass();
                ResultKt.checkNotNullParameter(layoutNode, "node");
                snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingLayoutModifier, function0);
            } else {
                NodeCoordinator outerCoordinator = layoutNodeLayoutDelegate.getOuterCoordinator();
                long j2 = outerCoordinator.apparentToRealOffset;
                int i = IntOffset.$r8$clinit;
                outerCoordinator.m200placeSelff8xVGno(ResultKt.IntOffset(((int) (j >> 32)) + ((int) (j2 >> 32)), ((int) (j & 4294967295L)) + ((int) (j2 & 4294967295L))), f, function1);
                onNodePlaced$ui_release();
            }
            layoutNodeLayoutDelegate.layoutState = 5;
        }

        /* renamed from: remeasure-BRTryo0, reason: not valid java name */
        public final boolean m183remeasureBRTryo0(long j) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            Owner requireOwner = Snake.requireOwner(layoutNodeLayoutDelegate.layoutNode);
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            boolean z = true;
            int i = 0;
            layoutNode.canMultiMeasure = layoutNode.canMultiMeasure || (parent$ui_release != null && parent$ui_release.canMultiMeasure);
            if (!layoutNode.layoutDelegate.measurePending && Constraints.m273equalsimpl0(this.measurementConstraints, j)) {
                ((AndroidComposeView) requireOwner).forceMeasureTheSubtree(layoutNode, false);
                layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                return false;
            }
            this.alignmentLines.usedByModifierMeasurement = false;
            forEachChildAlignmentLinesOwner(BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$7);
            this.measuredOnce = true;
            long j2 = layoutNodeLayoutDelegate.getOuterCoordinator().measuredSize;
            m167setMeasurementConstraintsBRTryo0(j);
            if (layoutNodeLayoutDelegate.layoutState != 5) {
                throw new IllegalStateException("layout state is not idle before measure starts".toString());
            }
            layoutNodeLayoutDelegate.layoutState = 1;
            layoutNodeLayoutDelegate.measurePending = false;
            OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSnapshotObserver();
            LayoutNodeLayoutDelegate$performMeasure$2 layoutNodeLayoutDelegate$performMeasure$2 = new LayoutNodeLayoutDelegate$performMeasure$2(layoutNodeLayoutDelegate, j, i);
            snapshotObserver.getClass();
            snapshotObserver.observeReads$ui_release(layoutNode, snapshotObserver.onCommitAffectingMeasure, layoutNodeLayoutDelegate$performMeasure$2);
            if (layoutNodeLayoutDelegate.layoutState == 1) {
                layoutNodeLayoutDelegate.layoutPending = true;
                layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
                layoutNodeLayoutDelegate.layoutState = 5;
            }
            if (layoutNodeLayoutDelegate.getOuterCoordinator().measuredSize == j2 && layoutNodeLayoutDelegate.getOuterCoordinator().width == this.width && layoutNodeLayoutDelegate.getOuterCoordinator().height == this.height) {
                z = false;
            }
            m166setMeasuredSizeozmzZPI(ResultKt.IntSize(layoutNodeLayoutDelegate.getOuterCoordinator().width, layoutNodeLayoutDelegate.getOuterCoordinator().height));
            return z;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestLayout() {
            LayoutNodeLayoutDelegate.this.layoutNode.requestRelayout$ui_release(false);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestMeasure() {
            LayoutNode.requestRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, 3);
        }
    }

    public LayoutNodeLayoutDelegate(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        this.layoutState = 5;
        this.measurePassDelegate = new MeasurePassDelegate();
    }

    public static boolean isOutMostLookaheadRoot(LayoutNode layoutNode) {
        if (layoutNode.lookaheadRoot != null) {
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if ((parent$ui_release != null ? parent$ui_release.lookaheadRoot : null) == null) {
                return true;
            }
        }
        return false;
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNode.nodes.outerCoordinator;
    }

    public final void setChildrenAccessingCoordinatesDuringPlacement(int i) {
        int i2 = this.childrenAccessingCoordinatesDuringPlacement;
        this.childrenAccessingCoordinatesDuringPlacement = i;
        if ((i2 == 0) != (i == 0)) {
            LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = parent$ui_release != null ? parent$ui_release.layoutDelegate : null;
            if (layoutNodeLayoutDelegate != null) {
                if (i == 0) {
                    layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement - 1);
                } else {
                    layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    public final void setCoordinatesAccessedDuringModifierPlacement(boolean z) {
        if (this.coordinatesAccessedDuringModifierPlacement != z) {
            this.coordinatesAccessedDuringModifierPlacement = z;
            if (z && !this.coordinatesAccessedDuringPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.coordinatesAccessedDuringPlacement) {
                    return;
                }
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void setCoordinatesAccessedDuringPlacement(boolean z) {
        if (this.coordinatesAccessedDuringPlacement != z) {
            this.coordinatesAccessedDuringPlacement = z;
            if (z && !this.coordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.coordinatesAccessedDuringModifierPlacement) {
                    return;
                }
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void updateParentData() {
        MeasurePassDelegate measurePassDelegate = this.measurePassDelegate;
        measurePassDelegate.getClass();
        LayoutNodeLayoutDelegate.this.getOuterCoordinator().getParentData();
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            ResultKt.checkNotNull(lookaheadDelegate);
            lookaheadDelegate.getParentData();
        }
    }
}
