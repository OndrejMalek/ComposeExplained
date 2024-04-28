package androidx.compose.ui.node;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.runtime.Latch;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.WeakCache;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class MeasureAndLayoutDelegate {
    public boolean duringMeasureLayout;
    public final long measureIteration;
    public final MutableVector onLayoutCompletedListeners;
    public final OnPositionedDispatcher onPositionedDispatcher;
    public final MutableVector postponedMeasureRequests;
    public final WeakCache relayoutNodes;
    public final LayoutNode root;
    public Constraints rootConstraints;

    /* loaded from: classes.dex */
    public final class PostponedRequest {
        public final boolean isForced;
        public final boolean isLookahead;
        public final LayoutNode node;

        public PostponedRequest(LayoutNode layoutNode, boolean z, boolean z2) {
            ResultKt.checkNotNullParameter(layoutNode, "node");
            this.node = layoutNode;
            this.isLookahead = z;
            this.isForced = z2;
        }
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public MeasureAndLayoutDelegate(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "root");
        this.root = layoutNode;
        this.relayoutNodes = new WeakCache();
        this.onPositionedDispatcher = new OnPositionedDispatcher();
        ?? obj = new Object();
        obj.content = new Owner.OnLayoutCompletedListener[16];
        obj.size = 0;
        this.onLayoutCompletedListeners = obj;
        this.measureIteration = 1L;
        ?? obj2 = new Object();
        obj2.content = new PostponedRequest[16];
        obj2.size = 0;
        this.postponedMeasureRequests = obj2;
    }

    public static boolean getCanAffectParentInLookahead(LayoutNode layoutNode) {
        LookaheadAlignmentLines lookaheadAlignmentLines;
        if (layoutNode.layoutDelegate.lookaheadMeasurePending) {
            if (layoutNode.getMeasuredByParentInLookahead$ui_release() == 1) {
                return true;
            }
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNode.layoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate != null && (lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines) != null && lookaheadAlignmentLines.getRequired$ui_release()) {
                return true;
            }
        }
        return false;
    }

    public final void dispatchOnPositionedCallbacks(boolean z) {
        OnPositionedDispatcher onPositionedDispatcher = this.onPositionedDispatcher;
        if (z) {
            onPositionedDispatcher.getClass();
            LayoutNode layoutNode = this.root;
            ResultKt.checkNotNullParameter(layoutNode, "rootNode");
            MutableVector mutableVector = onPositionedDispatcher.layoutNodes;
            mutableVector.clear();
            mutableVector.add(layoutNode);
            layoutNode.needsOnPositionedDispatch = true;
        }
        OnPositionedDispatcher$Companion$DepthComparator onPositionedDispatcher$Companion$DepthComparator = OnPositionedDispatcher$Companion$DepthComparator.INSTANCE;
        MutableVector mutableVector2 = onPositionedDispatcher.layoutNodes;
        Object[] objArr = mutableVector2.content;
        int i = mutableVector2.size;
        ResultKt.checkNotNullParameter(objArr, "<this>");
        Arrays.sort(objArr, 0, i, onPositionedDispatcher$Companion$DepthComparator);
        int i2 = mutableVector2.size;
        if (i2 > 0) {
            int i3 = i2 - 1;
            Object[] objArr2 = mutableVector2.content;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr2[i3];
                if (layoutNode2.needsOnPositionedDispatch) {
                    OnPositionedDispatcher.dispatchHierarchy(layoutNode2);
                }
                i3--;
            } while (i3 >= 0);
        }
        mutableVector2.clear();
    }

    /* renamed from: doLookaheadRemeasure-sdFAvZA, reason: not valid java name */
    public final boolean m187doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean m181remeasureBRTryo0;
        LayoutNode layoutNode2 = layoutNode.lookaheadRoot;
        if (layoutNode2 == null) {
            return false;
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (constraints != null) {
            if (layoutNode2 != null) {
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                ResultKt.checkNotNull(lookaheadPassDelegate);
                m181remeasureBRTryo0 = lookaheadPassDelegate.m181remeasureBRTryo0(constraints.value);
            }
            m181remeasureBRTryo0 = false;
        } else {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            Constraints constraints2 = lookaheadPassDelegate2 != null ? lookaheadPassDelegate2.lookaheadConstraints : null;
            if (constraints2 != null && layoutNode2 != null) {
                ResultKt.checkNotNull(lookaheadPassDelegate2);
                m181remeasureBRTryo0 = lookaheadPassDelegate2.m181remeasureBRTryo0(constraints2.value);
            }
            m181remeasureBRTryo0 = false;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (m181remeasureBRTryo0 && parent$ui_release != null) {
            if (parent$ui_release.lookaheadRoot == null) {
                requestRemeasure(parent$ui_release, false);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui_release() == 1) {
                requestLookaheadRemeasure(parent$ui_release, false);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui_release() == 2) {
                requestLookaheadRelayout(parent$ui_release, false);
            }
        }
        return m181remeasureBRTryo0;
    }

    /* renamed from: doRemeasure-sdFAvZA, reason: not valid java name */
    public final boolean m188doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean m173remeasure_Sx5XlM$ui_release = constraints != null ? layoutNode.m173remeasure_Sx5XlM$ui_release(constraints) : LayoutNode.m172remeasure_Sx5XlM$ui_release$default(layoutNode);
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (m173remeasure_Sx5XlM$ui_release && parent$ui_release != null) {
            if (layoutNode.getMeasuredByParent$ui_release() == 1) {
                requestRemeasure(parent$ui_release, false);
            } else if (layoutNode.getMeasuredByParent$ui_release() == 2) {
                requestRelayout(parent$ui_release, false);
            }
        }
        return m173remeasure_Sx5XlM$ui_release;
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, final boolean z) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        WeakCache weakCache = this.relayoutNodes;
        boolean isEmpty = ((TreeSet) ((Latch) weakCache.referenceQueue).spareList).isEmpty();
        Object obj = weakCache.values;
        if (isEmpty && ((TreeSet) ((Latch) obj).spareList).isEmpty()) {
            return;
        }
        if (!this.duringMeasureLayout) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Function1 function1 = new Function1() { // from class: androidx.compose.ui.node.MeasureAndLayoutDelegate$forceMeasureTheSubtree$pending$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LayoutNode layoutNode2 = (LayoutNode) obj2;
                ResultKt.checkNotNullParameter(layoutNode2, "it");
                boolean z2 = z;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode2.layoutDelegate;
                return Boolean.valueOf(z2 ? layoutNodeLayoutDelegate.lookaheadMeasurePending : layoutNodeLayoutDelegate.measurePending);
            }
        };
        if (!(!((Boolean) function1.invoke(layoutNode)).booleanValue())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        Object obj2 = weakCache.referenceQueue;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (((Boolean) function1.invoke(layoutNode2)).booleanValue()) {
                    ResultKt.checkNotNullParameter(layoutNode2, "node");
                    if (z ? ((Latch) obj).remove(layoutNode2) : ((Latch) obj2).remove(layoutNode2)) {
                        remeasureAndRelayoutIfNeeded(layoutNode2, z);
                    }
                }
                if (!((Boolean) function1.invoke(layoutNode2)).booleanValue()) {
                    forceMeasureTheSubtree(layoutNode2, z);
                }
                i2++;
            } while (i2 < i);
        }
        if (((Boolean) function1.invoke(layoutNode)).booleanValue()) {
            if (z ? ((Latch) obj).remove(layoutNode) : ((Latch) obj2).remove(layoutNode)) {
                remeasureAndRelayoutIfNeeded(layoutNode, true);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v12, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r2v13, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r2v14, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r2v26, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v20, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r6v5, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r6v6, resolved type: char */
    /* JADX DEBUG: Multi-variable search result rejected for r6v7, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean measureAndLayout(Function0 function0) {
        boolean z;
        Latch latch;
        LayoutNode layoutNode;
        WeakCache weakCache = this.relayoutNodes;
        LayoutNode layoutNode2 = this.root;
        if (!layoutNode2.isAttached()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!layoutNode2.isPlaced()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!this.duringMeasureLayout)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i = 0;
        Object[] objArr = 0;
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            try {
                Object obj = weakCache.referenceQueue;
                Object obj2 = weakCache.referenceQueue;
                boolean isEmpty = ((TreeSet) ((Latch) obj).spareList).isEmpty();
                Object obj3 = weakCache.values;
                if ((isEmpty && ((TreeSet) ((Latch) obj3).spareList).isEmpty()) ^ true) {
                    z = false;
                    while (true) {
                        if (!((((TreeSet) ((Latch) obj2).spareList).isEmpty() && ((TreeSet) ((Latch) obj3).spareList).isEmpty()) ^ true)) {
                            break;
                        }
                        boolean z2 = !((TreeSet) ((Latch) obj3).spareList).isEmpty();
                        if (z2) {
                            latch = (Latch) obj3;
                            layoutNode = (LayoutNode) ((TreeSet) latch.spareList).first();
                            ResultKt.checkNotNullExpressionValue(layoutNode, "node");
                        } else {
                            latch = (Latch) obj2;
                            layoutNode = (LayoutNode) ((TreeSet) latch.spareList).first();
                            ResultKt.checkNotNullExpressionValue(layoutNode, "node");
                        }
                        latch.remove(layoutNode);
                        boolean remeasureAndRelayoutIfNeeded = remeasureAndRelayoutIfNeeded(layoutNode, z2);
                        if (layoutNode == layoutNode2 && remeasureAndRelayoutIfNeeded) {
                            z = true;
                        }
                    }
                    if (function0 != null) {
                        function0.invoke();
                    }
                } else {
                    z = false;
                }
            } finally {
                this.duringMeasureLayout = false;
            }
        } else {
            z = false;
        }
        MutableVector mutableVector = this.onLayoutCompletedListeners;
        int i2 = mutableVector.size;
        if (i2 > 0) {
            Object[] objArr2 = mutableVector.content;
            do {
                ((LayoutNode) ((Owner.OnLayoutCompletedListener) objArr2[i])).onLayoutComplete();
                i++;
            } while (i < i2);
        }
        mutableVector.clear();
        return z;
    }

    public final void measureOnly() {
        LayoutNode layoutNode = this.root;
        if (!layoutNode.isAttached()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!layoutNode.isPlaced()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!this.duringMeasureLayout)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            try {
                recurseRemeasure(layoutNode);
            } finally {
                this.duringMeasureLayout = false;
            }
        }
    }

    public final void recurseRemeasure(LayoutNode layoutNode) {
        remeasureOnly(layoutNode);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (layoutNode2.getMeasuredByParent$ui_release() == 1 || layoutNode2.layoutDelegate.measurePassDelegate.alignmentLines.getRequired$ui_release()) {
                    recurseRemeasure(layoutNode2);
                }
                i2++;
            } while (i2 < i);
        }
        remeasureOnly(layoutNode);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v5, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode, boolean z) {
        Constraints constraints;
        boolean m187doLookaheadRemeasuresdFAvZA;
        boolean m188doRemeasuresdFAvZA;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate;
        LookaheadAlignmentLines lookaheadAlignmentLines;
        boolean isPlaced = layoutNode.isPlaced();
        boolean z2 = false;
        Object[] objArr = 0;
        int i = 0;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (!isPlaced && ((!layoutNodeLayoutDelegate.measurePending || (layoutNode.getMeasuredByParent$ui_release() != 1 && !layoutNodeLayoutDelegate.measurePassDelegate.alignmentLines.getRequired$ui_release())) && !ResultKt.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && !getCanAffectParentInLookahead(layoutNode) && !layoutNodeLayoutDelegate.measurePassDelegate.alignmentLines.getRequired$ui_release() && ((lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate) == null || (lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines) == null || !lookaheadAlignmentLines.getRequired$ui_release()))) {
            return false;
        }
        boolean z3 = layoutNodeLayoutDelegate.lookaheadMeasurePending;
        LayoutNode layoutNode2 = this.root;
        if (z3 || layoutNodeLayoutDelegate.measurePending) {
            if (layoutNode == layoutNode2) {
                constraints = this.rootConstraints;
                ResultKt.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            m187doLookaheadRemeasuresdFAvZA = (layoutNodeLayoutDelegate.lookaheadMeasurePending && z) ? m187doLookaheadRemeasuresdFAvZA(layoutNode, constraints) : false;
            m188doRemeasuresdFAvZA = m188doRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            m188doRemeasuresdFAvZA = false;
            m187doLookaheadRemeasuresdFAvZA = false;
        }
        if ((m187doLookaheadRemeasuresdFAvZA || layoutNodeLayoutDelegate.lookaheadLayoutPending) && ResultKt.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && z) {
            if (layoutNode.intrinsicsUsageByParent == 3) {
                layoutNode.clearSubtreePlacementIntrinsicsUsage();
            }
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            ResultKt.checkNotNull(lookaheadPassDelegate2);
            try {
                lookaheadPassDelegate2.relayoutWithoutParentInProgress = true;
                if (!lookaheadPassDelegate2.placedOnce) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                lookaheadPassDelegate2.mo165placeAtf8xVGno(lookaheadPassDelegate2.lastPosition, 0.0f, null);
            } finally {
                lookaheadPassDelegate2.relayoutWithoutParentInProgress = false;
            }
        }
        if (layoutNodeLayoutDelegate.layoutPending && layoutNode.isPlaced()) {
            LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNodeLayoutDelegate.measurePassDelegate;
            if (layoutNode == layoutNode2) {
                if (layoutNode.intrinsicsUsageByParent == 3) {
                    layoutNode.clearSubtreePlacementIntrinsicsUsage();
                }
                Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.Companion;
                int measuredWidth = measurePassDelegate.getMeasuredWidth();
                LayoutDirection layoutDirection = layoutNode.layoutDirection;
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                InnerNodeCoordinator innerNodeCoordinator = parent$ui_release != null ? parent$ui_release.nodes.innerCoordinator : null;
                int i2 = Placeable.PlacementScope.parentWidth;
                LayoutDirection layoutDirection2 = Placeable.PlacementScope.parentLayoutDirection;
                Placeable.PlacementScope.parentWidth = measuredWidth;
                Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
                boolean access$configureForPlacingForAlignment = Placeable.PlacementScope.Companion.access$configureForPlacingForAlignment(innerNodeCoordinator);
                Placeable.PlacementScope.placeRelative$default(companion, measurePassDelegate);
                if (innerNodeCoordinator != null) {
                    innerNodeCoordinator.isPlacingForAlignment = access$configureForPlacingForAlignment;
                }
                Placeable.PlacementScope.parentWidth = i2;
                Placeable.PlacementScope.parentLayoutDirection = layoutDirection2;
            } else {
                if (layoutNode.intrinsicsUsageByParent == 3) {
                    layoutNode.clearSubtreePlacementIntrinsicsUsage();
                }
                measurePassDelegate.getClass();
                try {
                    measurePassDelegate.relayoutWithoutParentInProgress = true;
                    if (!measurePassDelegate.placedOnce) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    measurePassDelegate.m182placeOuterCoordinatorf8xVGno(measurePassDelegate.lastPosition, measurePassDelegate.lastZIndex, measurePassDelegate.lastLayerBlock);
                } finally {
                    measurePassDelegate.relayoutWithoutParentInProgress = false;
                }
            }
            OnPositionedDispatcher onPositionedDispatcher = this.onPositionedDispatcher;
            onPositionedDispatcher.getClass();
            onPositionedDispatcher.layoutNodes.add(layoutNode);
            layoutNode.needsOnPositionedDispatch = true;
        }
        MutableVector mutableVector = this.postponedMeasureRequests;
        if (mutableVector.isNotEmpty()) {
            int i3 = mutableVector.size;
            if (i3 > 0) {
                Object[] objArr2 = mutableVector.content;
                do {
                    PostponedRequest postponedRequest = (PostponedRequest) objArr2[i];
                    if (postponedRequest.node.isAttached()) {
                        boolean z4 = postponedRequest.isLookahead;
                        boolean z5 = postponedRequest.isForced;
                        LayoutNode layoutNode3 = postponedRequest.node;
                        if (z4) {
                            requestLookaheadRemeasure(layoutNode3, z5);
                        } else {
                            requestRemeasure(layoutNode3, z5);
                        }
                    }
                    i++;
                } while (i < i3);
            }
            mutableVector.clear();
        }
        return m188doRemeasuresdFAvZA;
    }

    public final void remeasureOnly(LayoutNode layoutNode) {
        Constraints constraints;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (layoutNodeLayoutDelegate.measurePending || layoutNodeLayoutDelegate.lookaheadMeasurePending) {
            if (layoutNode == this.root) {
                constraints = this.rootConstraints;
                ResultKt.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            if (layoutNode.layoutDelegate.lookaheadMeasurePending) {
                m187doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
            }
            m188doRemeasuresdFAvZA(layoutNode, constraints);
        }
    }

    public final boolean requestLookaheadRelayout(LayoutNode layoutNode, boolean z) {
        LayoutNode parent$ui_release;
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate.layoutState);
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            throw new RuntimeException();
                        }
                    }
                }
            }
            return false;
        }
        if ((!layoutNodeLayoutDelegate.lookaheadMeasurePending && !layoutNodeLayoutDelegate.lookaheadLayoutPending) || z) {
            layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
            layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = true;
            layoutNodeLayoutDelegate.layoutPending = true;
            layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
            if (ResultKt.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) && (((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.layoutDelegate.lookaheadMeasurePending) && (parent$ui_release == null || !parent$ui_release.layoutDelegate.lookaheadLayoutPending))) {
                this.relayoutNodes.add(layoutNode, true);
            }
            if (!this.duringMeasureLayout) {
                return true;
            }
        }
        return false;
    }

    public final boolean requestLookaheadRemeasure(LayoutNode layoutNode, boolean z) {
        LayoutNode parent$ui_release;
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        if (layoutNode.lookaheadRoot == null) {
            throw new IllegalStateException("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadLayout".toString());
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate.layoutState);
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2 && ordinal != 3) {
                    if (ordinal != 4) {
                        throw new RuntimeException();
                    }
                    if (!layoutNodeLayoutDelegate.lookaheadMeasurePending || z) {
                        layoutNodeLayoutDelegate.lookaheadMeasurePending = true;
                        layoutNodeLayoutDelegate.measurePending = true;
                        if ((ResultKt.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) || getCanAffectParentInLookahead(layoutNode)) && ((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.layoutDelegate.lookaheadMeasurePending)) {
                            this.relayoutNodes.add(layoutNode, true);
                        }
                        if (!this.duringMeasureLayout) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, true, z));
        return false;
    }

    public final boolean requestRelayout(LayoutNode layoutNode, boolean z) {
        LayoutNode parent$ui_release;
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate.layoutState);
        if (ordinal == 0 || ordinal == 1 || ordinal == 2 || ordinal == 3) {
            return false;
        }
        if (ordinal != 4) {
            throw new RuntimeException();
        }
        if (!z && (layoutNodeLayoutDelegate.measurePending || layoutNodeLayoutDelegate.layoutPending)) {
            return false;
        }
        layoutNodeLayoutDelegate.layoutPending = true;
        layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
        if (layoutNode.isPlaced() && (((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.layoutDelegate.layoutPending) && (parent$ui_release == null || !parent$ui_release.layoutDelegate.measurePending))) {
            this.relayoutNodes.add(layoutNode, false);
        }
        return !this.duringMeasureLayout;
    }

    public final boolean requestRemeasure(LayoutNode layoutNode, boolean z) {
        LayoutNode parent$ui_release;
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(layoutNodeLayoutDelegate.layoutState);
        if (ordinal == 0 || ordinal == 1) {
            return false;
        }
        if (ordinal == 2 || ordinal == 3) {
            this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, false, z));
            return false;
        }
        if (ordinal != 4) {
            throw new RuntimeException();
        }
        if (layoutNodeLayoutDelegate.measurePending && !z) {
            return false;
        }
        layoutNodeLayoutDelegate.measurePending = true;
        if ((layoutNode.isPlaced() || (layoutNodeLayoutDelegate.measurePending && (layoutNode.getMeasuredByParent$ui_release() == 1 || layoutNodeLayoutDelegate.measurePassDelegate.alignmentLines.getRequired$ui_release()))) && ((parent$ui_release = layoutNode.getParent$ui_release()) == null || !parent$ui_release.layoutDelegate.measurePending)) {
            this.relayoutNodes.add(layoutNode, false);
        }
        return !this.duringMeasureLayout;
    }

    /* renamed from: updateRootConstraints-BRTryo0, reason: not valid java name */
    public final void m189updateRootConstraintsBRTryo0(long j) {
        Constraints constraints = this.rootConstraints;
        if (constraints != null && Constraints.m273equalsimpl0(constraints.value, j)) {
            return;
        }
        if (!(!this.duringMeasureLayout)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.rootConstraints = new Constraints(j);
        LayoutNode layoutNode = this.root;
        LayoutNode layoutNode2 = layoutNode.lookaheadRoot;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (layoutNode2 != null) {
            layoutNodeLayoutDelegate.lookaheadMeasurePending = true;
        }
        layoutNodeLayoutDelegate.measurePending = true;
        this.relayoutNodes.add(layoutNode, layoutNode2 != null);
    }
}
