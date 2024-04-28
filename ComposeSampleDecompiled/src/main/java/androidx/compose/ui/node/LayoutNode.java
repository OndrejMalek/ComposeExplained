package androidx.compose.ui.node;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Latch;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InvertMatrixKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.platform.WeakCache;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Arrays;
import java.util.List;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class LayoutNode implements ComposeNodeLifecycleCallback, OwnerScope, ComposeUiNode, Owner.OnLayoutCompletedListener {
    public SemanticsConfiguration _collapsedSemantics;
    public final WeakCache _foldedChildren;
    public LayoutNode _foldedParent;
    public NodeCoordinator _innerLayerCoordinator;
    public MutableVector _unfoldedChildren;
    public final MutableVector _zSortedChildren;
    public boolean canMultiMeasure;
    public CompositionLocalMap compositionLocalMap;
    public boolean deactivated;
    public Density density;
    public int depth;
    public boolean ignoreRemeasureRequests;
    public boolean innerLayerCoordinatorIsDirty;
    public final IntrinsicsPolicy intrinsicsPolicy;
    public int intrinsicsUsageByParent;
    public final boolean isVirtual;
    public final LayoutNodeLayoutDelegate layoutDelegate;
    public LayoutDirection layoutDirection;
    public LayoutNode lookaheadRoot;
    public MeasurePolicy measurePolicy;
    public Modifier modifier;
    public boolean needsOnPositionedDispatch;
    public final NodeChain nodes;
    public Owner owner;
    public int previousIntrinsicsUsageByParent;
    public int semanticsId;
    public boolean unfoldedVirtualChildrenListDirty;
    public ViewConfiguration viewConfiguration;
    public int virtualChildrenCount;
    public boolean zSortedChildrenInvalidated;
    public static final LayoutNode$Companion$ErrorMeasurePolicy$1 ErrorMeasurePolicy = new Object();
    public static final LayoutNode$Companion$DummyViewConfiguration$1 DummyViewConfiguration = new Object();
    public static final LayoutNode$$ExternalSyntheticLambda0 ZComparator = new LayoutNode$$ExternalSyntheticLambda0(0);

    /* loaded from: classes.dex */
    public abstract class NoIntrinsicsMeasurePolicy implements MeasurePolicy {
    }

    /* loaded from: classes.dex */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnimationEndReason$EnumUnboxingLocalUtility.values(5).length];
            try {
                iArr[4] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LayoutNode(boolean z, int i) {
        this(SemanticsModifierKt.lastIdentifier.addAndGet(1), (i & 1) != 0 ? false : z);
    }

    /* renamed from: remeasure-_Sx5XlM$ui_release$default, reason: not valid java name */
    public static boolean m172remeasure_Sx5XlM$ui_release$default(LayoutNode layoutNode) {
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNode.layoutDelegate.measurePassDelegate;
        return layoutNode.m173remeasure_Sx5XlM$ui_release(measurePassDelegate.measuredOnce ? new Constraints(measurePassDelegate.measurementConstraints) : null);
    }

    public static void requestLookaheadRemeasure$ui_release$default(LayoutNode layoutNode, boolean z, int i) {
        Owner owner;
        LayoutNode parent$ui_release;
        if ((i & 1) != 0) {
            z = false;
        }
        if (layoutNode.lookaheadRoot == null) {
            throw new IllegalStateException("Lookahead measure cannot be requested on a node that is not a part of theLookaheadLayout".toString());
        }
        Owner owner2 = layoutNode.owner;
        if (owner2 == null || layoutNode.ignoreRemeasureRequests || layoutNode.isVirtual) {
            return;
        }
        ((AndroidComposeView) owner2).onRequestMeasure(layoutNode, true, z, true);
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNode.layoutDelegate.lookaheadPassDelegate;
        ResultKt.checkNotNull(lookaheadPassDelegate);
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
        LayoutNode parent$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
        int i2 = layoutNodeLayoutDelegate.layoutNode.intrinsicsUsageByParent;
        if (parent$ui_release2 == null || i2 == 3) {
            return;
        }
        while (parent$ui_release2.intrinsicsUsageByParent == i2 && (parent$ui_release = parent$ui_release2.getParent$ui_release()) != null) {
            parent$ui_release2 = parent$ui_release;
        }
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(i2);
        if (ordinal == 0) {
            if (parent$ui_release2.lookaheadRoot != null) {
                requestLookaheadRemeasure$ui_release$default(parent$ui_release2, z, 2);
                return;
            } else {
                requestRemeasure$ui_release$default(parent$ui_release2, z, 2);
                return;
            }
        }
        if (ordinal != 1) {
            throw new IllegalStateException("Intrinsics isn't used by the parent".toString());
        }
        if (parent$ui_release2.lookaheadRoot == null) {
            parent$ui_release2.requestRelayout$ui_release(z);
        } else {
            if (parent$ui_release2.isVirtual || (owner = parent$ui_release2.owner) == null) {
                return;
            }
            ((AndroidComposeView) owner).onRequestRelayout(parent$ui_release2, true, z);
        }
    }

    public static void requestRemeasure$ui_release$default(LayoutNode layoutNode, boolean z, int i) {
        Owner owner;
        LayoutNode parent$ui_release;
        if ((i & 1) != 0) {
            z = false;
        }
        if (layoutNode.ignoreRemeasureRequests || layoutNode.isVirtual || (owner = layoutNode.owner) == null) {
            return;
        }
        ((AndroidComposeView) owner).onRequestMeasure(layoutNode, false, z, true);
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
        LayoutNode parent$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
        int i2 = layoutNodeLayoutDelegate.layoutNode.intrinsicsUsageByParent;
        if (parent$ui_release2 == null || i2 == 3) {
            return;
        }
        while (parent$ui_release2.intrinsicsUsageByParent == i2 && (parent$ui_release = parent$ui_release2.getParent$ui_release()) != null) {
            parent$ui_release2 = parent$ui_release;
        }
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(i2);
        if (ordinal == 0) {
            requestRemeasure$ui_release$default(parent$ui_release2, z, 2);
        } else {
            if (ordinal != 1) {
                throw new IllegalStateException("Intrinsics isn't used by the parent".toString());
            }
            parent$ui_release2.requestRelayout$ui_release(z);
        }
    }

    public static void rescheduleRemeasureOrRelayout$ui_release(LayoutNode layoutNode) {
        Owner owner;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (WhenMappings.$EnumSwitchMapping$0[AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate.layoutState)] != 1) {
            throw new IllegalStateException("Unexpected state ".concat(DurationKt$$ExternalSyntheticCheckNotZero0.stringValueOf(layoutNodeLayoutDelegate.layoutState)));
        }
        if (layoutNodeLayoutDelegate.measurePending) {
            requestRemeasure$ui_release$default(layoutNode, true, 2);
            return;
        }
        if (layoutNodeLayoutDelegate.layoutPending) {
            layoutNode.requestRelayout$ui_release(true);
            return;
        }
        if (layoutNodeLayoutDelegate.lookaheadMeasurePending) {
            requestLookaheadRemeasure$ui_release$default(layoutNode, true, 2);
        } else {
            if (!layoutNodeLayoutDelegate.lookaheadLayoutPending || layoutNode.isVirtual || (owner = layoutNode.owner) == null) {
                return;
            }
            ((AndroidComposeView) owner).onRequestRelayout(layoutNode, true, true);
        }
    }

    public final void attach$ui_release(Owner owner) {
        LayoutNode layoutNode;
        ResultKt.checkNotNullParameter(owner, "owner");
        if (this.owner != null) {
            throw new IllegalStateException(("Cannot attach " + this + " as it already is attached.  Tree: " + debugTreeToString(0)).toString());
        }
        LayoutNode layoutNode2 = this._foldedParent;
        if (layoutNode2 != null && !ResultKt.areEqual(layoutNode2.owner, owner)) {
            StringBuilder sb = new StringBuilder("Attaching to a different owner(");
            sb.append(owner);
            sb.append(") than the parent's owner(");
            LayoutNode parent$ui_release = getParent$ui_release();
            sb.append(parent$ui_release != null ? parent$ui_release.owner : null);
            sb.append("). This tree: ");
            sb.append(debugTreeToString(0));
            sb.append(" Parent tree: ");
            LayoutNode layoutNode3 = this._foldedParent;
            sb.append(layoutNode3 != null ? layoutNode3.debugTreeToString(0) : null);
            throw new IllegalStateException(sb.toString().toString());
        }
        LayoutNode parent$ui_release2 = getParent$ui_release();
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        if (parent$ui_release2 == null) {
            layoutNodeLayoutDelegate.measurePassDelegate.isPlaced = true;
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate != null) {
                lookaheadPassDelegate.isPlaced = true;
            }
        }
        NodeChain nodeChain = this.nodes;
        nodeChain.outerCoordinator.wrappedBy = parent$ui_release2 != null ? parent$ui_release2.nodes.innerCoordinator : null;
        this.owner = owner;
        this.depth = (parent$ui_release2 != null ? parent$ui_release2.depth : -1) + 1;
        if (nodeChain.m190hasH91voCI$ui_release(8)) {
            this._collapsedSemantics = null;
            ((AndroidComposeView) Snake.requireOwner(this)).onSemanticsChange();
        }
        LayoutNode layoutNode4 = this._foldedParent;
        if (layoutNode4 == null || (layoutNode = layoutNode4.lookaheadRoot) == null) {
            layoutNode = this.lookaheadRoot;
        }
        setLookaheadRoot(layoutNode);
        if (!this.deactivated) {
            for (Modifier.Node node = nodeChain.head; node != null; node = node.child) {
                node.markAsAttached$ui_release();
            }
        }
        MutableVector mutableVector = (MutableVector) this._foldedChildren.values;
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                ((LayoutNode) objArr[i2]).attach$ui_release(owner);
                i2++;
            } while (i2 < i);
        }
        if (!this.deactivated) {
            nodeChain.runAttachLifecycle();
        }
        invalidateMeasurements$ui_release();
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateMeasurements$ui_release();
        }
        NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
        for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !ResultKt.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
            nodeCoordinator2.updateLayerBlock(nodeCoordinator2.layerBlock, true);
            OwnedLayer ownedLayer = nodeCoordinator2.layer;
            if (ownedLayer != null) {
                ownedLayer.invalidate();
            }
        }
        layoutNodeLayoutDelegate.updateParentData();
        if (this.deactivated) {
            return;
        }
        Modifier.Node node2 = nodeChain.head;
        if ((node2.aggregateChildKindSet & 7168) != 0) {
            while (node2 != null) {
                int i3 = node2.kindSet;
                if (((i3 & 4096) != 0) | ((i3 & 1024) != 0) | ((i3 & 2048) != 0)) {
                    Snake.autoInvalidateInsertedNode(node2);
                }
                node2 = node2.child;
            }
        }
    }

    public final void clearSubtreeIntrinsicsUsage$ui_release() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = 3;
        MutableVector mutableVector = get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                if (layoutNode.intrinsicsUsageByParent != 3) {
                    layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final void clearSubtreePlacementIntrinsicsUsage() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = 3;
        MutableVector mutableVector = get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                if (layoutNode.intrinsicsUsageByParent == 2) {
                    layoutNode.clearSubtreePlacementIntrinsicsUsage();
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final String debugTreeToString(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        sb.append("|-");
        sb.append(toString());
        sb.append('\n');
        MutableVector mutableVector = get_children$ui_release();
        int i3 = mutableVector.size;
        if (i3 > 0) {
            Object[] objArr = mutableVector.content;
            int i4 = 0;
            do {
                sb.append(((LayoutNode) objArr[i4]).debugTreeToString(i + 1));
                i4++;
            } while (i4 < i3);
        }
        String sb2 = sb.toString();
        ResultKt.checkNotNullExpressionValue(sb2, "tree.toString()");
        if (i != 0) {
            return sb2;
        }
        String substring = sb2.substring(0, sb2.length() - 1);
        ResultKt.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x0099 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0040 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0040 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x009f */
    /* JADX WARN: Type inference failed for: r8v11, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final void detach$ui_release() {
        LookaheadAlignmentLines lookaheadAlignmentLines;
        Owner owner = this.owner;
        if (owner == null) {
            StringBuilder sb = new StringBuilder("Cannot detach node that is already detached!  Tree: ");
            LayoutNode parent$ui_release = getParent$ui_release();
            sb.append(parent$ui_release != null ? parent$ui_release.debugTreeToString(0) : null);
            throw new IllegalStateException(sb.toString().toString());
        }
        NodeChain nodeChain = this.nodes;
        int i = nodeChain.head.aggregateChildKindSet & 1024;
        Modifier.Node node = nodeChain.tail;
        if (i != 0) {
            for (Modifier.Node node2 = node; node2 != null; node2 = node2.parent) {
                if ((node2.kindSet & 1024) != 0) {
                    MutableVector mutableVector = null;
                    Modifier.Node node3 = node2;
                    while (node3 != null) {
                        if (node3 instanceof FocusTargetNode) {
                            FocusTargetNode focusTargetNode = (FocusTargetNode) node3;
                            if (focusTargetNode.focusState.isFocused()) {
                                ((FocusOwnerImpl) ((AndroidComposeView) Snake.requireOwner(this)).getFocusOwner()).clearFocus(true, false);
                                focusTargetNode.scheduleInvalidationForFocusEvents$ui_release();
                            }
                        } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                            Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                            int i2 = 0;
                            mutableVector = mutableVector;
                            while (node4 != null) {
                                if ((node4.kindSet & 1024) != 0) {
                                    i2++;
                                    mutableVector = mutableVector;
                                    if (i2 == 1) {
                                        node3 = node4;
                                    } else {
                                        if (mutableVector == null) {
                                            ?? obj = new Object();
                                            obj.content = new Modifier.Node[16];
                                            obj.size = 0;
                                            mutableVector = obj;
                                        }
                                        if (node3 != null) {
                                            mutableVector.add(node3);
                                            node3 = null;
                                        }
                                        mutableVector.add(node4);
                                    }
                                }
                                node4 = node4.child;
                                mutableVector = mutableVector;
                            }
                            if (i2 == 1) {
                            }
                        }
                        node3 = Snake.access$pop(mutableVector);
                    }
                }
            }
        }
        LayoutNode parent$ui_release2 = getParent$ui_release();
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateLayer$ui_release();
            parent$ui_release2.invalidateMeasurements$ui_release();
            LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNodeLayoutDelegate.measurePassDelegate;
            measurePassDelegate.getClass();
            measurePassDelegate.measuredByParent = 3;
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate != null) {
                lookaheadPassDelegate.measuredByParent = 3;
            }
        }
        LookaheadAlignmentLines lookaheadAlignmentLines2 = layoutNodeLayoutDelegate.measurePassDelegate.alignmentLines;
        lookaheadAlignmentLines2.dirty = true;
        lookaheadAlignmentLines2.usedDuringParentMeasurement = false;
        lookaheadAlignmentLines2.previousUsedDuringParentLayout = false;
        lookaheadAlignmentLines2.usedDuringParentLayout = false;
        lookaheadAlignmentLines2.usedByModifierMeasurement = false;
        lookaheadAlignmentLines2.usedByModifierLayout = false;
        lookaheadAlignmentLines2.queryOwner = null;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate2 != null && (lookaheadAlignmentLines = lookaheadPassDelegate2.alignmentLines) != null) {
            lookaheadAlignmentLines.dirty = true;
            lookaheadAlignmentLines.usedDuringParentMeasurement = false;
            lookaheadAlignmentLines.previousUsedDuringParentLayout = false;
            lookaheadAlignmentLines.usedDuringParentLayout = false;
            lookaheadAlignmentLines.usedByModifierMeasurement = false;
            lookaheadAlignmentLines.usedByModifierLayout = false;
            lookaheadAlignmentLines.queryOwner = null;
        }
        if (nodeChain.m190hasH91voCI$ui_release(8)) {
            this._collapsedSemantics = null;
            ((AndroidComposeView) Snake.requireOwner(this)).onSemanticsChange();
        }
        for (Modifier.Node node5 = node; node5 != null; node5 = node5.parent) {
            if (node5.isAttached) {
                node5.runDetachLifecycle$ui_release();
            }
        }
        this.ignoreRemeasureRequests = true;
        MutableVector mutableVector2 = (MutableVector) this._foldedChildren.values;
        int i3 = mutableVector2.size;
        if (i3 > 0) {
            Object[] objArr = mutableVector2.content;
            int i4 = 0;
            do {
                ((LayoutNode) objArr[i4]).detach$ui_release();
                i4++;
            } while (i4 < i3);
        }
        this.ignoreRemeasureRequests = false;
        while (node != null) {
            if (node.isAttached) {
                node.markAsDetached$ui_release();
            }
            node = node.parent;
        }
        AndroidComposeView androidComposeView = (AndroidComposeView) owner;
        MeasureAndLayoutDelegate measureAndLayoutDelegate = androidComposeView.measureAndLayoutDelegate;
        measureAndLayoutDelegate.getClass();
        WeakCache weakCache = measureAndLayoutDelegate.relayoutNodes;
        weakCache.getClass();
        ((Latch) weakCache.values).remove(this);
        ((Latch) weakCache.referenceQueue).remove(this);
        androidComposeView.observationClearRequested = true;
        this.owner = null;
        setLookaheadRoot(null);
        this.depth = 0;
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate2 = layoutNodeLayoutDelegate.measurePassDelegate;
        measurePassDelegate2.placeOrder = Integer.MAX_VALUE;
        measurePassDelegate2.previousPlaceOrder = Integer.MAX_VALUE;
        measurePassDelegate2.isPlaced = false;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate3 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate3 != null) {
            lookaheadPassDelegate3.placeOrder = Integer.MAX_VALUE;
            lookaheadPassDelegate3.previousPlaceOrder = Integer.MAX_VALUE;
            lookaheadPassDelegate3.isPlaced = false;
        }
    }

    public final List getChildren$ui_release() {
        return get_children$ui_release().asMutableList();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    public final SemanticsConfiguration getCollapsedSemantics$ui_release() {
        if (!this.nodes.m190hasH91voCI$ui_release(8) || this._collapsedSemantics != null) {
            return this._collapsedSemantics;
        }
        ?? obj = new Object();
        obj.element = new SemanticsConfiguration();
        OwnerSnapshotObserver snapshotObserver = ((AndroidComposeView) Snake.requireOwner(this)).getSnapshotObserver();
        NodeCoordinator$invoke$1 nodeCoordinator$invoke$1 = new NodeCoordinator$invoke$1(this, 6, obj);
        snapshotObserver.getClass();
        snapshotObserver.observeReads$ui_release(this, snapshotObserver.onCommitAffectingSemantics, nodeCoordinator$invoke$1);
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) obj.element;
        this._collapsedSemantics = semanticsConfiguration;
        return semanticsConfiguration;
    }

    public final int getMeasuredByParent$ui_release() {
        return this.layoutDelegate.measurePassDelegate.measuredByParent;
    }

    public final int getMeasuredByParentInLookahead$ui_release() {
        int i;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.layoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate == null || (i = lookaheadPassDelegate.measuredByParent) == 0) {
            return 3;
        }
        return i;
    }

    public final LayoutNode getParent$ui_release() {
        LayoutNode layoutNode = this._foldedParent;
        while (layoutNode != null && layoutNode.isVirtual) {
            layoutNode = layoutNode._foldedParent;
        }
        return layoutNode;
    }

    public final int getPlaceOrder$ui_release() {
        return this.layoutDelegate.measurePassDelegate.placeOrder;
    }

    public final MutableVector getZSortedChildren() {
        boolean z = this.zSortedChildrenInvalidated;
        MutableVector mutableVector = this._zSortedChildren;
        if (z) {
            mutableVector.clear();
            mutableVector.addAll(mutableVector.size, get_children$ui_release());
            LayoutNode$$ExternalSyntheticLambda0 layoutNode$$ExternalSyntheticLambda0 = ZComparator;
            Object[] objArr = mutableVector.content;
            int i = mutableVector.size;
            ResultKt.checkNotNullParameter(objArr, "<this>");
            Arrays.sort(objArr, 0, i, layoutNode$$ExternalSyntheticLambda0);
            this.zSortedChildrenInvalidated = false;
        }
        return mutableVector;
    }

    public final MutableVector get_children$ui_release() {
        updateChildrenIfDirty$ui_release();
        if (this.virtualChildrenCount == 0) {
            return (MutableVector) this._foldedChildren.values;
        }
        MutableVector mutableVector = this._unfoldedChildren;
        ResultKt.checkNotNull(mutableVector);
        return mutableVector;
    }

    public final void invalidateLayer$ui_release() {
        if (this.innerLayerCoordinatorIsDirty) {
            NodeChain nodeChain = this.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator;
            NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator.wrappedBy;
            this._innerLayerCoordinator = null;
            while (true) {
                if (ResultKt.areEqual(nodeCoordinator, nodeCoordinator2)) {
                    break;
                }
                if ((nodeCoordinator != null ? nodeCoordinator.layer : null) != null) {
                    this._innerLayerCoordinator = nodeCoordinator;
                    break;
                }
                nodeCoordinator = nodeCoordinator != null ? nodeCoordinator.wrappedBy : null;
            }
        }
        NodeCoordinator nodeCoordinator3 = this._innerLayerCoordinator;
        if (nodeCoordinator3 != null && nodeCoordinator3.layer == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        if (nodeCoordinator3 != null) {
            nodeCoordinator3.invalidateLayer();
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
    }

    public final void invalidateLayers$ui_release() {
        NodeChain nodeChain = this.nodes;
        NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator;
        InnerNodeCoordinator innerNodeCoordinator = nodeChain.innerCoordinator;
        while (nodeCoordinator != innerNodeCoordinator) {
            ResultKt.checkNotNull(nodeCoordinator, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) nodeCoordinator;
            OwnedLayer ownedLayer = layoutModifierNodeCoordinator.layer;
            if (ownedLayer != null) {
                ownedLayer.invalidate();
            }
            nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
        }
        OwnedLayer ownedLayer2 = nodeChain.innerCoordinator.layer;
        if (ownedLayer2 != null) {
            ownedLayer2.invalidate();
        }
    }

    public final void invalidateMeasurements$ui_release() {
        if (this.lookaheadRoot != null) {
            requestLookaheadRemeasure$ui_release$default(this, false, 3);
        } else {
            requestRemeasure$ui_release$default(this, false, 3);
        }
    }

    public final void invalidateUnfoldedVirtualChildren() {
        LayoutNode layoutNode;
        if (this.virtualChildrenCount > 0) {
            this.unfoldedVirtualChildrenListDirty = true;
        }
        if (!this.isVirtual || (layoutNode = this._foldedParent) == null) {
            return;
        }
        layoutNode.invalidateUnfoldedVirtualChildren();
    }

    public final boolean isAttached() {
        return this.owner != null;
    }

    public final boolean isPlaced() {
        return this.layoutDelegate.measurePassDelegate.isPlaced;
    }

    public final Boolean isPlacedInLookahead() {
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.layoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            return Boolean.valueOf(lookaheadPassDelegate.isPlaced);
        }
        return null;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return isAttached();
    }

    public final void onChildRemoved(LayoutNode layoutNode) {
        if (layoutNode.layoutDelegate.childrenAccessingCoordinatesDuringPlacement > 0) {
            this.layoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(r0.childrenAccessingCoordinatesDuringPlacement - 1);
        }
        if (this.owner != null) {
            layoutNode.detach$ui_release();
        }
        layoutNode._foldedParent = null;
        layoutNode.nodes.outerCoordinator.wrappedBy = null;
        if (layoutNode.isVirtual) {
            this.virtualChildrenCount--;
            MutableVector mutableVector = (MutableVector) layoutNode._foldedChildren.values;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    ((LayoutNode) objArr[i2]).nodes.outerCoordinator.wrappedBy = null;
                    i2++;
                } while (i2 < i);
            }
        }
        invalidateUnfoldedVirtualChildren();
        onZSortedChildrenInvalidated$ui_release();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0067 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0070 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x002c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x002c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0076 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public final void onLayoutComplete() {
        Modifier.Node node;
        NodeChain nodeChain = this.nodes;
        InnerNodeCoordinator innerNodeCoordinator = nodeChain.innerCoordinator;
        boolean m214getIncludeSelfInTraversalH91voCI = Snake.m214getIncludeSelfInTraversalH91voCI(128);
        if (m214getIncludeSelfInTraversalH91voCI) {
            node = innerNodeCoordinator.tail;
        } else {
            node = innerNodeCoordinator.tail.parent;
            if (node == null) {
                return;
            }
        }
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = NodeCoordinator.graphicsLayerScope;
        for (Modifier.Node headNode = innerNodeCoordinator.headNode(m214getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & 128) != 0; headNode = headNode.child) {
            if ((headNode.kindSet & 128) != 0) {
                DelegatingNode delegatingNode = headNode;
                ?? r6 = 0;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) delegatingNode).onPlaced(nodeChain.innerCoordinator);
                    } else if ((delegatingNode.kindSet & 128) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node2 = delegatingNode.delegate;
                        int i = 0;
                        delegatingNode = delegatingNode;
                        r6 = r6;
                        while (node2 != null) {
                            if ((node2.kindSet & 128) != 0) {
                                i++;
                                r6 = r6;
                                if (i == 1) {
                                    delegatingNode = node2;
                                } else {
                                    if (r6 == 0) {
                                        ?? obj = new Object();
                                        obj.content = new Modifier.Node[16];
                                        obj.size = 0;
                                        r6 = obj;
                                    }
                                    if (delegatingNode != 0) {
                                        r6.add(delegatingNode);
                                        delegatingNode = 0;
                                    }
                                    r6.add(node2);
                                }
                            }
                            node2 = node2.child;
                            delegatingNode = delegatingNode;
                            r6 = r6;
                        }
                        if (i == 1) {
                        }
                    }
                    delegatingNode = Snake.access$pop(r6);
                }
            }
            if (headNode == node) {
                return;
            }
        }
    }

    public final void onZSortedChildrenInvalidated$ui_release() {
        if (!this.isVirtual) {
            this.zSortedChildrenInvalidated = true;
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.onZSortedChildrenInvalidated$ui_release();
        }
    }

    /* renamed from: remeasure-_Sx5XlM$ui_release, reason: not valid java name */
    public final boolean m173remeasure_Sx5XlM$ui_release(Constraints constraints) {
        if (constraints == null) {
            return false;
        }
        if (this.intrinsicsUsageByParent == 3) {
            clearSubtreeIntrinsicsUsage$ui_release();
        }
        return this.layoutDelegate.measurePassDelegate.m183remeasureBRTryo0(constraints.value);
    }

    public final void requestRelayout$ui_release(boolean z) {
        Owner owner;
        if (this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        ((AndroidComposeView) owner).onRequestRelayout(this, false, z);
    }

    public final void resetSubtreeIntrinsicsUsage$ui_release() {
        MutableVector mutableVector = get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                int i3 = layoutNode.previousIntrinsicsUsageByParent;
                layoutNode.intrinsicsUsageByParent = i3;
                if (i3 != 3) {
                    layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                }
                i2++;
            } while (i2 < i);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x00a4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x00ad */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0060 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x0060 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x00b3 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final void setCompositionLocalMap(CompositionLocalMap compositionLocalMap) {
        ResultKt.checkNotNullParameter(compositionLocalMap, "value");
        this.compositionLocalMap = compositionLocalMap;
        StaticProvidableCompositionLocal staticProvidableCompositionLocal = CompositionLocalsKt.LocalDensity;
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = (PersistentCompositionLocalHashMap) compositionLocalMap;
        ResultKt.checkNotNullParameter(staticProvidableCompositionLocal, "key");
        setDensity((Density) _BOUNDARY.read(persistentCompositionLocalHashMap, staticProvidableCompositionLocal));
        StaticProvidableCompositionLocal staticProvidableCompositionLocal2 = CompositionLocalsKt.LocalLayoutDirection;
        ResultKt.checkNotNullParameter(staticProvidableCompositionLocal2, "key");
        LayoutDirection layoutDirection = (LayoutDirection) _BOUNDARY.read(persistentCompositionLocalHashMap, staticProvidableCompositionLocal2);
        ResultKt.checkNotNullParameter(layoutDirection, "value");
        if (this.layoutDirection != layoutDirection) {
            this.layoutDirection = layoutDirection;
            invalidateMeasurements$ui_release();
            LayoutNode parent$ui_release = getParent$ui_release();
            if (parent$ui_release != null) {
                parent$ui_release.invalidateLayer$ui_release();
            }
            invalidateLayers$ui_release();
        }
        StaticProvidableCompositionLocal staticProvidableCompositionLocal3 = CompositionLocalsKt.LocalViewConfiguration;
        ResultKt.checkNotNullParameter(staticProvidableCompositionLocal3, "key");
        setViewConfiguration((ViewConfiguration) _BOUNDARY.read(persistentCompositionLocalHashMap, staticProvidableCompositionLocal3));
        Modifier.Node node = this.nodes.head;
        if ((node.aggregateChildKindSet & 32768) != 0) {
            while (node != null) {
                if ((node.kindSet & 32768) != 0) {
                    DelegatingNode delegatingNode = node;
                    ?? r3 = 0;
                    while (delegatingNode != 0) {
                        if (delegatingNode instanceof CompositionLocalConsumerModifierNode) {
                            Modifier.Node node2 = ((Modifier.Node) ((CompositionLocalConsumerModifierNode) delegatingNode)).node;
                            if (node2.isAttached) {
                                Snake.autoInvalidateUpdatedNode(node2);
                            } else {
                                node2.updatedNodeAwaitingAttachForInvalidation = true;
                            }
                        } else if ((delegatingNode.kindSet & 32768) != 0 && (delegatingNode instanceof DelegatingNode)) {
                            Modifier.Node node3 = delegatingNode.delegate;
                            int i = 0;
                            delegatingNode = delegatingNode;
                            r3 = r3;
                            while (node3 != null) {
                                if ((node3.kindSet & 32768) != 0) {
                                    i++;
                                    r3 = r3;
                                    if (i == 1) {
                                        delegatingNode = node3;
                                    } else {
                                        if (r3 == 0) {
                                            ?? obj = new Object();
                                            obj.content = new Modifier.Node[16];
                                            obj.size = 0;
                                            r3 = obj;
                                        }
                                        if (delegatingNode != 0) {
                                            r3.add(delegatingNode);
                                            delegatingNode = 0;
                                        }
                                        r3.add(node3);
                                    }
                                }
                                node3 = node3.child;
                                delegatingNode = delegatingNode;
                                r3 = r3;
                            }
                            if (i == 1) {
                            }
                        }
                        delegatingNode = Snake.access$pop(r3);
                    }
                }
                if ((node.aggregateChildKindSet & 32768) == 0) {
                    return;
                } else {
                    node = node.child;
                }
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x006a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0073 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0033 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x0033 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0079 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final void setDensity(Density density) {
        ResultKt.checkNotNullParameter(density, "value");
        if (ResultKt.areEqual(this.density, density)) {
            return;
        }
        this.density = density;
        invalidateMeasurements$ui_release();
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
        invalidateLayers$ui_release();
        Modifier.Node node = this.nodes.head;
        if ((node.aggregateChildKindSet & 16) != 0) {
            while (node != null) {
                if ((node.kindSet & 16) != 0) {
                    DelegatingNode delegatingNode = node;
                    ?? r3 = 0;
                    while (delegatingNode != 0) {
                        if (delegatingNode instanceof PointerInputModifierNode) {
                            ((PointerInputModifierNode) delegatingNode).onDensityChange();
                        } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                            Modifier.Node node2 = delegatingNode.delegate;
                            int i = 0;
                            delegatingNode = delegatingNode;
                            r3 = r3;
                            while (node2 != null) {
                                if ((node2.kindSet & 16) != 0) {
                                    i++;
                                    r3 = r3;
                                    if (i == 1) {
                                        delegatingNode = node2;
                                    } else {
                                        if (r3 == 0) {
                                            ?? obj = new Object();
                                            obj.content = new Modifier.Node[16];
                                            obj.size = 0;
                                            r3 = obj;
                                        }
                                        if (delegatingNode != 0) {
                                            r3.add(delegatingNode);
                                            delegatingNode = 0;
                                        }
                                        r3.add(node2);
                                    }
                                }
                                node2 = node2.child;
                                delegatingNode = delegatingNode;
                                r3 = r3;
                            }
                            if (i == 1) {
                            }
                        }
                        delegatingNode = Snake.access$pop(r3);
                    }
                }
                if ((node.aggregateChildKindSet & 16) == 0) {
                    return;
                } else {
                    node = node.child;
                }
            }
        }
    }

    public final void setLookaheadRoot(LayoutNode layoutNode) {
        if (ResultKt.areEqual(layoutNode, this.lookaheadRoot)) {
            return;
        }
        this.lookaheadRoot = layoutNode;
        if (layoutNode != null) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
            if (layoutNodeLayoutDelegate.lookaheadPassDelegate == null) {
                layoutNodeLayoutDelegate.lookaheadPassDelegate = new LayoutNodeLayoutDelegate.LookaheadPassDelegate();
            }
            NodeChain nodeChain = this.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
            for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !ResultKt.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
                nodeCoordinator2.ensureLookaheadDelegateCreated();
            }
        }
        invalidateMeasurements$ui_release();
    }

    public final void setMeasurePolicy(MeasurePolicy measurePolicy) {
        ResultKt.checkNotNullParameter(measurePolicy, "value");
        if (ResultKt.areEqual(this.measurePolicy, measurePolicy)) {
            return;
        }
        this.measurePolicy = measurePolicy;
        IntrinsicsPolicy intrinsicsPolicy = this.intrinsicsPolicy;
        intrinsicsPolicy.getClass();
        intrinsicsPolicy.measurePolicyState$delegate.setValue(measurePolicy);
        invalidateMeasurements$ui_release();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:104:0x00df */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0179  */
    /* JADX WARN: Type inference failed for: r2v13, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v14, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v2, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setModifier(androidx.compose.ui.Modifier r15) {
        /*
            r14 = this;
            java.lang.String r0 = "value"
            kotlin.ResultKt.checkNotNullParameter(r15, r0)
            boolean r0 = r14.isVirtual
            if (r0 == 0) goto L1c
            androidx.compose.ui.Modifier r0 = r14.modifier
            androidx.compose.ui.Modifier$Companion r1 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            if (r0 != r1) goto L10
            goto L1c
        L10:
            java.lang.IllegalArgumentException r15 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Modifiers are not supported on virtual LayoutNodes"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        L1c:
            r14.modifier = r15
            androidx.compose.ui.node.NodeChain r0 = r14.nodes
            r0.getClass()
            androidx.compose.ui.Modifier$Node r1 = r0.head
            androidx.compose.ui.node.NodeChainKt$SentinelHead$1 r5 = androidx.compose.ui.node.NodeChainKt.SentinelHead
            if (r1 == r5) goto L1b7
            r1.parent = r5
            r5.child = r1
            androidx.compose.runtime.collection.MutableVector r7 = r0.current
            r1 = 0
            if (r7 == 0) goto L35
            int r2 = r7.size
            goto L36
        L35:
            r2 = r1
        L36:
            androidx.compose.runtime.collection.MutableVector r3 = r0.buffer
            r4 = 16
            if (r3 != 0) goto L47
            androidx.compose.runtime.collection.MutableVector r3 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Element[] r6 = new androidx.compose.ui.Modifier.Element[r4]
            r3.<init>()
            r3.content = r6
            r3.size = r1
        L47:
            r8 = r3
            int r3 = r8.size
            if (r3 >= r4) goto L4d
            r3 = r4
        L4d:
            androidx.compose.runtime.collection.MutableVector r6 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier[] r3 = new androidx.compose.ui.Modifier[r3]
            r6.<init>()
            r6.content = r3
            r6.size = r1
            r6.add(r15)
        L5b:
            boolean r15 = r6.isNotEmpty()
            r9 = 1
            if (r15 == 0) goto L8f
            int r15 = r6.size
            int r15 = r15 - r9
            java.lang.Object r15 = r6.removeAt(r15)
            androidx.compose.ui.Modifier r15 = (androidx.compose.ui.Modifier) r15
            boolean r3 = r15 instanceof androidx.compose.ui.CombinedModifier
            if (r3 == 0) goto L7c
            androidx.compose.ui.CombinedModifier r15 = (androidx.compose.ui.CombinedModifier) r15
            androidx.compose.ui.Modifier r3 = r15.inner
            r6.add(r3)
            androidx.compose.ui.Modifier r15 = r15.outer
            r6.add(r15)
            goto L5b
        L7c:
            boolean r3 = r15 instanceof androidx.compose.ui.Modifier.Element
            if (r3 == 0) goto L84
            r8.add(r15)
            goto L5b
        L84:
            kotlin.collections.AbstractMap$toString$1 r3 = new kotlin.collections.AbstractMap$toString$1
            r9 = 13
            r3.<init>(r9, r8)
            r15.all(r3)
            goto L5b
        L8f:
            int r15 = r8.size
            androidx.compose.ui.node.TailModifierNode r10 = r0.tail
            r11 = 0
            java.lang.String r3 = "expected prior modifier list to be non-empty"
            androidx.compose.ui.node.LayoutNode r6 = r0.layoutNode
            if (r15 != r2) goto Lf8
            androidx.compose.ui.Modifier$Node r15 = r5.child
            r4 = r1
        L9d:
            if (r15 == 0) goto Lc2
            if (r4 >= r2) goto Lc2
            if (r7 == 0) goto Lc4
            java.lang.Object[] r5 = r7.content
            r5 = r5[r4]
            androidx.compose.ui.Modifier$Element r5 = (androidx.compose.ui.Modifier.Element) r5
            java.lang.Object[] r12 = r8.content
            r12 = r12[r4]
            androidx.compose.ui.Modifier$Element r12 = (androidx.compose.ui.Modifier.Element) r12
            int r13 = androidx.compose.ui.node.NodeChainKt.actionForModifiers(r5, r12)
            if (r13 == 0) goto Lc0
            if (r13 == r9) goto Lb8
            goto Lbb
        Lb8:
            androidx.compose.ui.node.NodeChain.updateNode(r5, r12, r15)
        Lbb:
            androidx.compose.ui.Modifier$Node r15 = r15.child
            int r4 = r4 + 1
            goto L9d
        Lc0:
            androidx.compose.ui.Modifier$Node r15 = r15.parent
        Lc2:
            r5 = r15
            goto Lce
        Lc4:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = r3.toString()
            r15.<init>(r0)
            throw r15
        Lce:
            if (r4 >= r2) goto L171
            if (r7 == 0) goto Lee
            if (r5 == 0) goto Le2
            boolean r6 = r6.isAttached()
            r1 = r0
            r2 = r4
            r3 = r7
            r4 = r8
            r1.structuralUpdate(r2, r3, r4, r5, r6)
        Ldf:
            r1 = r9
            goto L171
        Le2:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "structuralUpdate requires a non-null tail"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        Lee:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = r3.toString()
            r15.<init>(r0)
            throw r15
        Lf8:
            boolean r15 = r6.isAttached()
            if (r15 != 0) goto L122
            if (r2 != 0) goto L122
            r15 = r1
        L101:
            int r2 = r8.size
            if (r15 >= r2) goto L112
            java.lang.Object[] r2 = r8.content
            r2 = r2[r15]
            androidx.compose.ui.Modifier$Element r2 = (androidx.compose.ui.Modifier.Element) r2
            androidx.compose.ui.Modifier$Node r5 = androidx.compose.ui.node.NodeChain.createAndInsertNodeAsChild(r2, r5)
            int r15 = r15 + 1
            goto L101
        L112:
            androidx.compose.ui.Modifier$Node r15 = r10.parent
        L114:
            if (r15 == 0) goto Ldf
            androidx.compose.ui.node.NodeChainKt$SentinelHead$1 r2 = androidx.compose.ui.node.NodeChainKt.SentinelHead
            if (r15 == r2) goto Ldf
            int r2 = r15.kindSet
            r1 = r1 | r2
            r15.aggregateChildKindSet = r1
            androidx.compose.ui.Modifier$Node r15 = r15.parent
            goto L114
        L122:
            int r15 = r8.size
            if (r15 != 0) goto L157
            if (r7 == 0) goto L14d
            androidx.compose.ui.Modifier$Node r15 = r5.child
            r2 = r1
        L12b:
            if (r15 == 0) goto L13a
            int r3 = r7.size
            if (r2 >= r3) goto L13a
            androidx.compose.ui.Modifier$Node r15 = androidx.compose.ui.node.NodeChain.detachAndRemoveNode(r15)
            androidx.compose.ui.Modifier$Node r15 = r15.child
            int r2 = r2 + 1
            goto L12b
        L13a:
            androidx.compose.ui.node.LayoutNode r15 = r6.getParent$ui_release()
            if (r15 == 0) goto L145
            androidx.compose.ui.node.NodeChain r15 = r15.nodes
            androidx.compose.ui.node.InnerNodeCoordinator r15 = r15.innerCoordinator
            goto L146
        L145:
            r15 = r11
        L146:
            androidx.compose.ui.node.InnerNodeCoordinator r2 = r0.innerCoordinator
            r2.wrappedBy = r15
            r0.outerCoordinator = r2
            goto L171
        L14d:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = r3.toString()
            r15.<init>(r0)
            throw r15
        L157:
            if (r7 != 0) goto L164
            androidx.compose.runtime.collection.MutableVector r7 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Element[] r15 = new androidx.compose.ui.Modifier.Element[r4]
            r7.<init>()
            r7.content = r15
            r7.size = r1
        L164:
            r2 = 0
            boolean r6 = r6.isAttached()
            r1 = r0
            r3 = r7
            r4 = r8
            r1.structuralUpdate(r2, r3, r4, r5, r6)
            goto Ldf
        L171:
            r0.current = r8
            if (r7 == 0) goto L179
            r7.clear()
            goto L17a
        L179:
            r7 = r11
        L17a:
            r0.buffer = r7
            androidx.compose.ui.node.NodeChainKt$SentinelHead$1 r15 = androidx.compose.ui.node.NodeChainKt.SentinelHead
            androidx.compose.ui.Modifier$Node r2 = r15.child
            if (r2 != 0) goto L183
            goto L184
        L183:
            r10 = r2
        L184:
            r10.parent = r11
            r15.child = r11
            r2 = -1
            r15.aggregateChildKindSet = r2
            r15.coordinator = r11
            if (r10 == r15) goto L1ab
            r0.head = r10
            if (r1 == 0) goto L196
            r0.syncCoordinators()
        L196:
            androidx.compose.ui.node.LayoutNodeLayoutDelegate r15 = r14.layoutDelegate
            r15.updateParentData()
            r15 = 512(0x200, float:7.175E-43)
            boolean r15 = r0.m190hasH91voCI$ui_release(r15)
            if (r15 == 0) goto L1aa
            androidx.compose.ui.node.LayoutNode r15 = r14.lookaheadRoot
            if (r15 != 0) goto L1aa
            r14.setLookaheadRoot(r14)
        L1aa:
            return
        L1ab:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "trimChain did not update the head"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        L1b7:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "padChain called on already padded chain"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNode.setModifier(androidx.compose.ui.Modifier):void");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:28:0x005b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:32:0x0064 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0024 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x0024 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x006a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final void setViewConfiguration(ViewConfiguration viewConfiguration) {
        ResultKt.checkNotNullParameter(viewConfiguration, "value");
        if (ResultKt.areEqual(this.viewConfiguration, viewConfiguration)) {
            return;
        }
        this.viewConfiguration = viewConfiguration;
        Modifier.Node node = this.nodes.head;
        if ((node.aggregateChildKindSet & 16) != 0) {
            while (node != null) {
                if ((node.kindSet & 16) != 0) {
                    DelegatingNode delegatingNode = node;
                    ?? r3 = 0;
                    while (delegatingNode != 0) {
                        if (delegatingNode instanceof PointerInputModifierNode) {
                            ((PointerInputModifierNode) delegatingNode).onViewConfigurationChange();
                        } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                            Modifier.Node node2 = delegatingNode.delegate;
                            int i = 0;
                            delegatingNode = delegatingNode;
                            r3 = r3;
                            while (node2 != null) {
                                if ((node2.kindSet & 16) != 0) {
                                    i++;
                                    r3 = r3;
                                    if (i == 1) {
                                        delegatingNode = node2;
                                    } else {
                                        if (r3 == 0) {
                                            ?? obj = new Object();
                                            obj.content = new Modifier.Node[16];
                                            obj.size = 0;
                                            r3 = obj;
                                        }
                                        if (delegatingNode != 0) {
                                            r3.add(delegatingNode);
                                            delegatingNode = 0;
                                        }
                                        r3.add(node2);
                                    }
                                }
                                node2 = node2.child;
                                delegatingNode = delegatingNode;
                                r3 = r3;
                            }
                            if (i == 1) {
                            }
                        }
                        delegatingNode = Snake.access$pop(r3);
                    }
                }
                if ((node.aggregateChildKindSet & 16) == 0) {
                    return;
                } else {
                    node = node.child;
                }
            }
        }
    }

    public final String toString() {
        return InvertMatrixKt.simpleIdentityToString(this) + " children: " + getChildren$ui_release().size() + " measurePolicy: " + this.measurePolicy;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final void updateChildrenIfDirty$ui_release() {
        if (this.virtualChildrenCount <= 0 || !this.unfoldedVirtualChildrenListDirty) {
            return;
        }
        int i = 0;
        this.unfoldedVirtualChildrenListDirty = false;
        MutableVector mutableVector = this._unfoldedChildren;
        MutableVector mutableVector2 = mutableVector;
        if (mutableVector == null) {
            ?? obj = new Object();
            obj.content = new LayoutNode[16];
            obj.size = 0;
            this._unfoldedChildren = obj;
            mutableVector2 = obj;
        }
        mutableVector2.clear();
        MutableVector mutableVector3 = (MutableVector) this._foldedChildren.values;
        int i2 = mutableVector3.size;
        if (i2 > 0) {
            Object[] objArr = mutableVector3.content;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i];
                if (layoutNode.isVirtual) {
                    mutableVector2.addAll(mutableVector2.size, layoutNode.get_children$ui_release());
                } else {
                    mutableVector2.add(layoutNode);
                }
                i++;
            } while (i < i2);
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        layoutNodeLayoutDelegate.measurePassDelegate.childDelegatesDirty = true;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.childDelegatesDirty = true;
        }
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public LayoutNode(int i, boolean z) {
        this.isVirtual = z;
        this.semanticsId = i;
        ?? obj = new Object();
        obj.content = new LayoutNode[16];
        obj.size = 0;
        this._foldedChildren = new WeakCache(obj, new LayoutNode$_foldedChildren$1(0, this));
        ?? obj2 = new Object();
        obj2.content = new LayoutNode[16];
        obj2.size = 0;
        this._zSortedChildren = obj2;
        this.zSortedChildrenInvalidated = true;
        this.measurePolicy = ErrorMeasurePolicy;
        this.intrinsicsPolicy = new IntrinsicsPolicy(this);
        this.density = Snake.DefaultDensity;
        this.layoutDirection = LayoutDirection.Ltr;
        this.viewConfiguration = DummyViewConfiguration;
        CompositionLocalMap.Companion.getClass();
        this.compositionLocalMap = CompositionLocalMap.Companion.Empty;
        this.intrinsicsUsageByParent = 3;
        this.previousIntrinsicsUsageByParent = 3;
        this.nodes = new NodeChain(this);
        this.layoutDelegate = new LayoutNodeLayoutDelegate(this);
        this.innerLayerCoordinatorIsDirty = true;
        this.modifier = Modifier.Companion.$$INSTANCE;
    }
}
