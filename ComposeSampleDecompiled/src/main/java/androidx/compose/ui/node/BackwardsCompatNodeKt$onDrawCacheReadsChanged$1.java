package androidx.compose.ui.node;

import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$1 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(1);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$2 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(2);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$3 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(3);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$4 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(4);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$5 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(5);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$6 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(6);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$7 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(7);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$8 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(8);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$9 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(9);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$10 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(10);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$11 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(11);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$12 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(12);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$13 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(13);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$14 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(14);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$15 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(15);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$16 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(16);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$17 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(17);
    public static final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 INSTANCE$18 = new BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(18);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BackwardsCompatNodeKt$onDrawCacheReadsChanged$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    public final void invoke(AlignmentLinesOwner alignmentLinesOwner) {
        switch (this.$r8$classId) {
            case 2:
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "child");
                alignmentLinesOwner.getAlignmentLines().usedDuringParentLayout = false;
                return;
            case 3:
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "child");
                alignmentLinesOwner.getAlignmentLines().previousUsedDuringParentLayout = alignmentLinesOwner.getAlignmentLines().usedDuringParentLayout;
                return;
            case 4:
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "it");
                alignmentLinesOwner.getAlignmentLines().usedDuringParentMeasurement = false;
                return;
            case 5:
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "it");
                alignmentLinesOwner.getAlignmentLines().usedDuringParentLayout = false;
                return;
            case 6:
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "it");
                alignmentLinesOwner.getAlignmentLines().previousUsedDuringParentLayout = alignmentLinesOwner.getAlignmentLines().usedDuringParentLayout;
                return;
            default:
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "it");
                alignmentLinesOwner.getAlignmentLines().usedDuringParentMeasurement = false;
                return;
        }
    }

    public final void invoke(LayoutNode layoutNode) {
        Owner owner;
        Owner owner2;
        switch (this.$r8$classId) {
            case 12:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (layoutNode.isAttached()) {
                    layoutNode.requestRelayout$ui_release(false);
                    return;
                }
                return;
            case 13:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (layoutNode.isAttached()) {
                    layoutNode.requestRelayout$ui_release(false);
                    return;
                }
                return;
            case 14:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (!layoutNode.isAttached() || layoutNode.isVirtual || (owner = layoutNode.owner) == null) {
                    return;
                }
                ((AndroidComposeView) owner).onRequestRelayout(layoutNode, true, false);
                return;
            case 15:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (!layoutNode.isAttached() || layoutNode.isVirtual || (owner2 = layoutNode.owner) == null) {
                    return;
                }
                ((AndroidComposeView) owner2).onRequestRelayout(layoutNode, true, false);
                return;
            case 16:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (layoutNode.isAttached()) {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, false, 3);
                    return;
                }
                return;
            case 17:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (layoutNode.isAttached()) {
                    LayoutNode.requestRemeasure$ui_release$default(layoutNode, false, 3);
                    return;
                }
                return;
            default:
                ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
                if (layoutNode.isAttached()) {
                    layoutNode._collapsedSemantics = null;
                    ((AndroidComposeView) Snake.requireOwner(layoutNode)).onSemanticsChange();
                    return;
                }
                return;
        }
    }

    public final void invoke(NodeCoordinator nodeCoordinator) {
        switch (this.$r8$classId) {
            case 8:
                ResultKt.checkNotNullParameter(nodeCoordinator, "coordinator");
                OwnedLayer ownedLayer = nodeCoordinator.layer;
                if (ownedLayer != null) {
                    ownedLayer.invalidate();
                    return;
                }
                return;
            default:
                ResultKt.checkNotNullParameter(nodeCoordinator, "coordinator");
                if (nodeCoordinator.isValidOwnerScope()) {
                    LayerPositionalProperties layerPositionalProperties = nodeCoordinator.layerPositionalProperties;
                    if (layerPositionalProperties == null) {
                        nodeCoordinator.updateLayerParameters(true);
                        return;
                    }
                    LayerPositionalProperties layerPositionalProperties2 = NodeCoordinator.tmpLayerPositionalProperties;
                    layerPositionalProperties2.getClass();
                    layerPositionalProperties2.scaleX = layerPositionalProperties.scaleX;
                    layerPositionalProperties2.scaleY = layerPositionalProperties.scaleY;
                    layerPositionalProperties2.translationX = layerPositionalProperties.translationX;
                    layerPositionalProperties2.translationY = layerPositionalProperties.translationY;
                    layerPositionalProperties2.rotationX = layerPositionalProperties.rotationX;
                    layerPositionalProperties2.rotationY = layerPositionalProperties.rotationY;
                    layerPositionalProperties2.rotationZ = layerPositionalProperties.rotationZ;
                    layerPositionalProperties2.cameraDistance = layerPositionalProperties.cameraDistance;
                    layerPositionalProperties2.transformOrigin = layerPositionalProperties.transformOrigin;
                    nodeCoordinator.updateLayerParameters(true);
                    if (layerPositionalProperties2.scaleX == layerPositionalProperties.scaleX && layerPositionalProperties2.scaleY == layerPositionalProperties.scaleY && layerPositionalProperties2.translationX == layerPositionalProperties.translationX && layerPositionalProperties2.translationY == layerPositionalProperties.translationY && layerPositionalProperties2.rotationX == layerPositionalProperties.rotationX && layerPositionalProperties2.rotationY == layerPositionalProperties.rotationY && layerPositionalProperties2.rotationZ == layerPositionalProperties.rotationZ && layerPositionalProperties2.cameraDistance == layerPositionalProperties.cameraDistance && layerPositionalProperties2.transformOrigin == layerPositionalProperties.transformOrigin) {
                        return;
                    }
                    LayoutNode layoutNode = nodeCoordinator.layoutNode;
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
                    if (layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement > 0) {
                        if (layoutNodeLayoutDelegate.coordinatesAccessedDuringModifierPlacement || layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement) {
                            layoutNode.requestRelayout$ui_release(false);
                        }
                        layoutNodeLayoutDelegate.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                    }
                    Owner owner = layoutNode.owner;
                    if (owner != null) {
                        AndroidComposeView androidComposeView = (AndroidComposeView) owner;
                        MeasureAndLayoutDelegate measureAndLayoutDelegate = androidComposeView.measureAndLayoutDelegate;
                        measureAndLayoutDelegate.getClass();
                        OnPositionedDispatcher onPositionedDispatcher = measureAndLayoutDelegate.onPositionedDispatcher;
                        onPositionedDispatcher.getClass();
                        onPositionedDispatcher.layoutNodes.add(layoutNode);
                        layoutNode.needsOnPositionedDispatch = true;
                        androidComposeView.scheduleMeasureAndLayout(null);
                        return;
                    }
                    return;
                }
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) obj;
                switch (i) {
                    case 0:
                        ResultKt.checkNotNullParameter(backwardsCompatNode, "it");
                        Snake.invalidateDraw(backwardsCompatNode);
                        return unit;
                    default:
                        ResultKt.checkNotNullParameter(backwardsCompatNode, "it");
                        backwardsCompatNode.updateModifierLocalConsumer();
                        return unit;
                }
            case 1:
                BackwardsCompatNode backwardsCompatNode2 = (BackwardsCompatNode) obj;
                switch (i) {
                    case 0:
                        ResultKt.checkNotNullParameter(backwardsCompatNode2, "it");
                        Snake.invalidateDraw(backwardsCompatNode2);
                        return unit;
                    default:
                        ResultKt.checkNotNullParameter(backwardsCompatNode2, "it");
                        backwardsCompatNode2.updateModifierLocalConsumer();
                        return unit;
                }
            case 2:
                invoke((AlignmentLinesOwner) obj);
                return unit;
            case 3:
                invoke((AlignmentLinesOwner) obj);
                return unit;
            case 4:
                invoke((AlignmentLinesOwner) obj);
                return unit;
            case 5:
                invoke((AlignmentLinesOwner) obj);
                return unit;
            case 6:
                invoke((AlignmentLinesOwner) obj);
                return unit;
            case 7:
                invoke((AlignmentLinesOwner) obj);
                return unit;
            case 8:
                invoke((NodeCoordinator) obj);
                return unit;
            case 9:
                invoke((NodeCoordinator) obj);
                return unit;
            case 10:
                ObserverNodeOwnerScope observerNodeOwnerScope = (ObserverNodeOwnerScope) obj;
                ResultKt.checkNotNullParameter(observerNodeOwnerScope, "it");
                if (observerNodeOwnerScope.isValidOwnerScope()) {
                    observerNodeOwnerScope.observerNode.onObservedReadsChanged();
                }
                return unit;
            case 11:
                ResultKt.checkNotNullParameter(obj, "it");
                return Boolean.valueOf(!((OwnerScope) obj).isValidOwnerScope());
            case 12:
                invoke((LayoutNode) obj);
                return unit;
            case 13:
                invoke((LayoutNode) obj);
                return unit;
            case 14:
                invoke((LayoutNode) obj);
                return unit;
            case 15:
                invoke((LayoutNode) obj);
                return unit;
            case 16:
                invoke((LayoutNode) obj);
                return unit;
            case 17:
                invoke((LayoutNode) obj);
                return unit;
            default:
                invoke((LayoutNode) obj);
                return unit;
        }
    }
}
