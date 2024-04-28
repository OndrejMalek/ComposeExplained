package androidx.compose.ui.node;

import _COROUTINE._BOUNDARY;
import androidx.compose.material3.MinimumInteractiveComponentSizeModifier;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusInvalidationManager;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.compose.ui.unit.DensityImpl;
import androidx.compose.ui.unit.IntOffset;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public abstract class Snake {
    public static final BackwardsCompatNodeKt$DetachedModifierLocalReadScope$1 DetachedModifierLocalReadScope = new Object();
    public static final DensityImpl DefaultDensity = new DensityImpl(1.0f, 1.0f);

    public static final long access$DistanceAndInLayer(float f, boolean z) {
        return ((z ? 1L : 0L) & 4294967295L) | (Float.floatToIntBits(f) << 32);
    }

    public static final void access$addLayoutNodeChildren(MutableVector mutableVector, Modifier.Node node) {
        MutableVector mutableVector2 = requireLayoutNode(node).get_children$ui_release();
        int i = mutableVector2.size;
        if (i > 0) {
            int i2 = i - 1;
            Object[] objArr = mutableVector2.content;
            do {
                mutableVector.add(((LayoutNode) objArr[i2]).nodes.head);
                i2--;
            } while (i2 >= 0);
        }
    }

    public static final int access$calculateAlignmentAndPlaceChildAsNeeded(LookaheadCapablePlaceable lookaheadCapablePlaceable, AlignmentLine alignmentLine) {
        long j;
        LookaheadCapablePlaceable child = lookaheadCapablePlaceable.getChild();
        if (child == null) {
            throw new IllegalStateException(("Child of " + lookaheadCapablePlaceable + " cannot be null when calculating alignment line").toString());
        }
        if (lookaheadCapablePlaceable.getMeasureResult$ui_release().alignmentLines.containsKey(alignmentLine)) {
            Integer num = (Integer) lookaheadCapablePlaceable.getMeasureResult$ui_release().alignmentLines.get(alignmentLine);
            if (num != null) {
                return num.intValue();
            }
            return Integer.MIN_VALUE;
        }
        int i = child.get(alignmentLine);
        if (i == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        child.isShallowPlacing = true;
        lookaheadCapablePlaceable.isPlacingForAlignment = true;
        lookaheadCapablePlaceable.replace$ui_release();
        child.isShallowPlacing = false;
        lookaheadCapablePlaceable.isPlacingForAlignment = false;
        if (alignmentLine instanceof HorizontalAlignmentLine) {
            long mo185getPositionnOccac = child.mo185getPositionnOccac();
            int i2 = IntOffset.$r8$clinit;
            j = mo185getPositionnOccac & 4294967295L;
        } else {
            long mo185getPositionnOccac2 = child.mo185getPositionnOccac();
            int i3 = IntOffset.$r8$clinit;
            j = mo185getPositionnOccac2 >> 32;
        }
        return i + ((int) j);
    }

    /* renamed from: access$nextUntil-hw7D004 */
    public static final Modifier.Node m211access$nextUntilhw7D004(DelegatableNode delegatableNode, int i) {
        Modifier.Node node = ((Modifier.Node) delegatableNode).node.child;
        if (node == null || (node.aggregateChildKindSet & i) == 0) {
            return null;
        }
        while (node != null) {
            int i2 = node.kindSet;
            if ((i2 & 2) != 0) {
                return null;
            }
            if ((i2 & i) != 0) {
                return node;
            }
            node = node.child;
        }
        return null;
    }

    public static final Modifier.Node access$pop(MutableVector mutableVector) {
        int i;
        if (mutableVector == null || (i = mutableVector.size) == 0) {
            return null;
        }
        return (Modifier.Node) mutableVector.removeAt(i - 1);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:20:0x001b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:21:0x001b */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX DEBUG: Multi-variable search result rejected for r2v3, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX DEBUG: Multi-variable search result rejected for r2v5, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX DEBUG: Multi-variable search result rejected for r2v8, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        ResultKt.checkNotNullParameter(node, "<this>");
        if ((node.kindSet & 2) != 0) {
            if (node instanceof LayoutModifierNode) {
                return (LayoutModifierNode) node;
            }
            if (node instanceof DelegatingNode) {
                Modifier.Node node2 = ((DelegatingNode) node).delegate;
                while (node2 != 0) {
                    if (node2 instanceof LayoutModifierNode) {
                        return (LayoutModifierNode) node2;
                    }
                    node2 = (!(node2 instanceof DelegatingNode) || (node2.kindSet & 2) == 0) ? node2.child : ((DelegatingNode) node2).delegate;
                }
            }
        }
        return null;
    }

    public static final void autoInvalidateInsertedNode(Modifier.Node node) {
        ResultKt.checkNotNullParameter(node, "node");
        if (!node.isAttached) {
            throw new IllegalStateException("Check failed.".toString());
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 1);
    }

    public static final void autoInvalidateNodeIncludingDelegates(Modifier.Node node, int i, int i2) {
        ResultKt.checkNotNullParameter(node, "node");
        if (!(node instanceof DelegatingNode)) {
            autoInvalidateNodeSelf(node, i & node.kindSet, i2);
            return;
        }
        DelegatingNode delegatingNode = (DelegatingNode) node;
        int i3 = delegatingNode.selfKindSet;
        autoInvalidateNodeSelf(node, i3 & i, i2);
        int i4 = (~i3) & i;
        for (Modifier.Node node2 = delegatingNode.delegate; node2 != null; node2 = node2.child) {
            autoInvalidateNodeIncludingDelegates(node2, i4, i2);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:88:0x0160 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:93:0x010a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:94:0x010a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:97:0x0166 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: androidx.compose.ui.focus.FocusPropertiesModifierNode */
    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v16, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final void autoInvalidateNodeSelf(Modifier.Node node, int i, int i2) {
        if (i2 != 0 || node.getShouldAutoInvalidate()) {
            if ((i & 2) != 0 && (node instanceof LayoutModifierNode)) {
                LayoutModifierNode layoutModifierNode = (LayoutModifierNode) node;
                ResultKt.checkNotNullParameter(layoutModifierNode, "<this>");
                requireLayoutNode(layoutModifierNode).invalidateMeasurements$ui_release();
                if (i2 == 2) {
                    NodeCoordinator m215requireCoordinator64DMado = m215requireCoordinator64DMado(node, 2);
                    m215requireCoordinator64DMado.released = true;
                    if (m215requireCoordinator64DMado.layer != null) {
                        m215requireCoordinator64DMado.updateLayerBlock(null, false);
                    }
                }
            }
            if ((i & 256) != 0 && (node instanceof GlobalPositionAwareModifierNode)) {
                requireLayoutNode(node).invalidateMeasurements$ui_release();
            }
            if ((i & 4) != 0 && (node instanceof DrawModifierNode)) {
                invalidateDraw((DrawModifierNode) node);
            }
            if ((i & 8) != 0 && (node instanceof SemanticsModifierNode)) {
                SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) node;
                ResultKt.checkNotNullParameter(semanticsModifierNode, "<this>");
                LayoutNode requireLayoutNode = requireLayoutNode(semanticsModifierNode);
                requireLayoutNode._collapsedSemantics = null;
                ((AndroidComposeView) requireOwner(requireLayoutNode)).onSemanticsChange();
            }
            if ((i & 64) != 0 && (node instanceof ParentDataModifierNode)) {
                ParentDataModifierNode parentDataModifierNode = (ParentDataModifierNode) node;
                ResultKt.checkNotNullParameter(parentDataModifierNode, "<this>");
                requireLayoutNode(parentDataModifierNode).layoutDelegate.measurePassDelegate.getClass();
            }
            if ((i & 1024) != 0 && (node instanceof FocusTargetNode)) {
                if (i2 == 2) {
                    node.onReset();
                } else {
                    FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) ((AndroidComposeView) requireOwner(node)).getFocusOwner();
                    focusOwnerImpl.getClass();
                    FocusInvalidationManager focusInvalidationManager = focusOwnerImpl.focusInvalidationManager;
                    focusInvalidationManager.getClass();
                    focusInvalidationManager.scheduleInvalidation(focusInvalidationManager.focusTargetNodes, (FocusTargetNode) node);
                }
            }
            if ((i & 2048) != 0 && (node instanceof FocusPropertiesModifierNode)) {
                FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) node;
                CanFocusChecker.canFocusValue = null;
                focusPropertiesModifierNode.applyFocusProperties(CanFocusChecker.INSTANCE);
                if (CanFocusChecker.canFocusValue != null) {
                    if (i2 == 2) {
                        Modifier.Node node2 = ((Modifier.Node) focusPropertiesModifierNode).node;
                        if (!node2.isAttached) {
                            throw new IllegalStateException("visitChildren called on an unattached node".toString());
                        }
                        ?? obj = new Object();
                        obj.content = new Modifier.Node[16];
                        obj.size = 0;
                        Modifier.Node node3 = node2.child;
                        if (node3 == null) {
                            access$addLayoutNodeChildren(obj, node2);
                        } else {
                            obj.add(node3);
                        }
                        while (obj.isNotEmpty()) {
                            Modifier.Node node4 = (Modifier.Node) obj.removeAt(obj.size - 1);
                            if ((node4.aggregateChildKindSet & 1024) == 0) {
                                access$addLayoutNodeChildren(obj, node4);
                            } else {
                                while (true) {
                                    if (node4 == null) {
                                        break;
                                    }
                                    if ((node4.kindSet & 1024) != 0) {
                                        MutableVector mutableVector = null;
                                        while (node4 != null) {
                                            if (node4 instanceof FocusTargetNode) {
                                                FocusTargetNode focusTargetNode = (FocusTargetNode) node4;
                                                FocusOwnerImpl focusOwnerImpl2 = (FocusOwnerImpl) ((AndroidComposeView) requireOwner(focusTargetNode)).getFocusOwner();
                                                focusOwnerImpl2.getClass();
                                                FocusInvalidationManager focusInvalidationManager2 = focusOwnerImpl2.focusInvalidationManager;
                                                focusInvalidationManager2.getClass();
                                                focusInvalidationManager2.scheduleInvalidation(focusInvalidationManager2.focusTargetNodes, focusTargetNode);
                                            } else if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                                Modifier.Node node5 = ((DelegatingNode) node4).delegate;
                                                int i3 = 0;
                                                mutableVector = mutableVector;
                                                while (node5 != null) {
                                                    if ((node5.kindSet & 1024) != 0) {
                                                        i3++;
                                                        mutableVector = mutableVector;
                                                        if (i3 == 1) {
                                                            node4 = node5;
                                                        } else {
                                                            if (mutableVector == null) {
                                                                ?? obj2 = new Object();
                                                                obj2.content = new Modifier.Node[16];
                                                                obj2.size = 0;
                                                                mutableVector = obj2;
                                                            }
                                                            if (node4 != null) {
                                                                mutableVector.add(node4);
                                                                node4 = null;
                                                            }
                                                            mutableVector.add(node5);
                                                        }
                                                    }
                                                    node5 = node5.child;
                                                    mutableVector = mutableVector;
                                                }
                                                if (i3 == 1) {
                                                }
                                            }
                                            node4 = access$pop(mutableVector);
                                        }
                                    } else {
                                        node4 = node4.child;
                                    }
                                }
                            }
                        }
                    } else {
                        FocusOwnerImpl focusOwnerImpl3 = (FocusOwnerImpl) ((AndroidComposeView) requireOwner(focusPropertiesModifierNode)).getFocusOwner();
                        focusOwnerImpl3.getClass();
                        FocusInvalidationManager focusInvalidationManager3 = focusOwnerImpl3.focusInvalidationManager;
                        focusInvalidationManager3.getClass();
                        focusInvalidationManager3.scheduleInvalidation(focusInvalidationManager3.focusPropertiesNodes, focusPropertiesModifierNode);
                    }
                }
            }
            if ((i & 4096) == 0 || !(node instanceof FocusEventModifierNode)) {
                return;
            }
            ResultKt.invalidateFocusEvent((FocusEventModifierNode) node);
        }
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        ResultKt.checkNotNullParameter(node, "node");
        if (!node.isAttached) {
            throw new IllegalStateException("Check failed.".toString());
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 0);
    }

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        ResultKt.checkNotNullParameter(element, "element");
        int i = element instanceof MinimumInteractiveComponentSizeModifier ? 3 : 1;
        if (element instanceof DrawModifier) {
            i |= 4;
        }
        if (element instanceof SemanticsModifier) {
            i |= 8;
        }
        return ((element instanceof ModifierLocalConsumer) || (element instanceof ModifierLocalProvider)) ? i | 32 : i;
    }

    public static final int calculateNodeKindSetFromIncludingDelegates(Modifier.Node node) {
        ResultKt.checkNotNullParameter(node, "node");
        if (!(node instanceof DelegatingNode)) {
            return calculateNodeKindSetFrom(node);
        }
        DelegatingNode delegatingNode = (DelegatingNode) node;
        int i = delegatingNode.selfKindSet;
        for (Modifier.Node node2 = delegatingNode.delegate; node2 != null; node2 = node2.child) {
            i |= calculateNodeKindSetFromIncludingDelegates(node2);
        }
        return i;
    }

    /* renamed from: compareTo-S_HNhKs */
    public static final int m212compareToS_HNhKs(long j, long j2) {
        boolean z = ((int) (j & 4294967295L)) != 0;
        return z != (((int) (4294967295L & j2)) != 0) ? z ? -1 : 1 : (int) Math.signum(Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: androidx.compose.ui.node.CompositionLocalConsumerModifierNode */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Object currentValueOf(CompositionLocalConsumerModifierNode compositionLocalConsumerModifierNode, ProvidableCompositionLocal providableCompositionLocal) {
        ResultKt.checkNotNullParameter(compositionLocalConsumerModifierNode, "<this>");
        ResultKt.checkNotNullParameter(providableCompositionLocal, "local");
        if (!((Modifier.Node) compositionLocalConsumerModifierNode).node.isAttached) {
            throw new IllegalStateException("Cannot read CompositionLocal because the Modifier node is not currently attached.".toString());
        }
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = (PersistentCompositionLocalHashMap) requireLayoutNode(compositionLocalConsumerModifierNode).compositionLocalMap;
        persistentCompositionLocalHashMap.getClass();
        return _BOUNDARY.read(persistentCompositionLocalHashMap, providableCompositionLocal);
    }

    /* renamed from: getDiagonalSize-impl */
    public static final int m213getDiagonalSizeimpl(int[] iArr) {
        return Math.min(iArr[2] - iArr[0], iArr[3] - iArr[1]);
    }

    /* renamed from: getIncludeSelfInTraversal-H91voCI */
    public static final boolean m214getIncludeSelfInTraversalH91voCI(int i) {
        return (i & 128) != 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: androidx.compose.ui.node.DrawModifierNode */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void invalidateDraw(DrawModifierNode drawModifierNode) {
        ResultKt.checkNotNullParameter(drawModifierNode, "<this>");
        if (((Modifier.Node) drawModifierNode).node.isAttached) {
            m215requireCoordinator64DMado(drawModifierNode, 1).invalidateLayer();
        }
    }

    public static final void invalidateLayer(LayoutModifierNode layoutModifierNode) {
        ResultKt.checkNotNullParameter(layoutModifierNode, "<this>");
        m215requireCoordinator64DMado(layoutModifierNode, 2).invalidateLayer();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.compose.ui.Modifier$Node */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void observeReads(Modifier.Node node, Function0 function0) {
        ResultKt.checkNotNullParameter(node, "<this>");
        ObserverNodeOwnerScope observerNodeOwnerScope = node.ownerScope;
        if (observerNodeOwnerScope == null) {
            observerNodeOwnerScope = new ObserverNodeOwnerScope((ObserverModifierNode) node);
            node.ownerScope = observerNodeOwnerScope;
        }
        ((AndroidComposeView) requireOwner(node)).getSnapshotObserver().observeReads$ui_release(observerNodeOwnerScope, BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$10, function0);
    }

    /* renamed from: requireCoordinator-64DMado */
    public static final NodeCoordinator m215requireCoordinator64DMado(DelegatableNode delegatableNode, int i) {
        ResultKt.checkNotNullParameter(delegatableNode, "$this$requireCoordinator");
        NodeCoordinator nodeCoordinator = ((Modifier.Node) delegatableNode).node.coordinator;
        ResultKt.checkNotNull(nodeCoordinator);
        if (nodeCoordinator.getTail() != delegatableNode || !m214getIncludeSelfInTraversalH91voCI(i)) {
            return nodeCoordinator;
        }
        NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrapped;
        ResultKt.checkNotNull(nodeCoordinator2);
        return nodeCoordinator2;
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode delegatableNode) {
        ResultKt.checkNotNullParameter(delegatableNode, "<this>");
        NodeCoordinator nodeCoordinator = ((Modifier.Node) delegatableNode).node.coordinator;
        if (nodeCoordinator != null) {
            return nodeCoordinator.layoutNode;
        }
        throw new IllegalStateException("Cannot obtain node coordinator. Is the Modifier.Node attached?".toString());
    }

    public static final Owner requireOwner(DelegatableNode delegatableNode) {
        ResultKt.checkNotNullParameter(delegatableNode, "<this>");
        Owner owner = requireLayoutNode(delegatableNode).owner;
        if (owner != null) {
            return owner;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public static final Owner requireOwner(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "<this>");
        Owner owner = layoutNode.owner;
        if (owner != null) {
            return owner;
        }
        throw new IllegalStateException("LayoutNode should be attached to an owner".toString());
    }

    public static final int calculateNodeKindSetFrom(Modifier.Node node) {
        ResultKt.checkNotNullParameter(node, "node");
        int i = node.kindSet;
        if (i != 0) {
            return i;
        }
        int i2 = node instanceof LayoutModifierNode ? 3 : 1;
        if (node instanceof DrawModifierNode) {
            i2 |= 4;
        }
        if (node instanceof SemanticsModifierNode) {
            i2 |= 8;
        }
        if (node instanceof PointerInputModifierNode) {
            i2 |= 16;
        }
        if (node instanceof ModifierLocalModifierNode) {
            i2 |= 32;
        }
        if (node instanceof ParentDataModifierNode) {
            i2 |= 64;
        }
        if (node instanceof LayoutAwareModifierNode) {
            i2 |= 128;
        }
        if (node instanceof GlobalPositionAwareModifierNode) {
            i2 |= 256;
        }
        if (node instanceof FocusTargetNode) {
            i2 |= 1024;
        }
        if (node instanceof FocusPropertiesModifierNode) {
            i2 |= 2048;
        }
        if (node instanceof FocusEventModifierNode) {
            i2 |= 4096;
        }
        if (node instanceof KeyInputModifierNode) {
            i2 |= 8192;
        }
        if (node instanceof RotaryInputModifierNode) {
            i2 |= 16384;
        }
        return node instanceof CompositionLocalConsumerModifierNode ? i2 | 32768 : i2;
    }
}
