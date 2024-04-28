package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator$invoke$1;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FocusTargetNode extends Modifier.Node implements ObserverModifierNode, ModifierLocalModifierNode {
    public FocusStateImpl focusState = FocusStateImpl.Inactive;
    public boolean isProcessingCustomEnter;
    public boolean isProcessingCustomExit;

    /* loaded from: classes.dex */
    public final class FocusTargetElement extends ModifierNodeElement {
        public static final FocusTargetElement INSTANCE = new FocusTargetElement();

        private FocusTargetElement() {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final Modifier.Node create() {
            return new FocusTargetNode();
        }

        public final boolean equals(Object obj) {
            return obj == this;
        }

        public final int hashCode() {
            return 1739042953;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final void update(Modifier.Node node) {
            ResultKt.checkNotNullParameter((FocusTargetNode) node, "node");
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x008a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x0093 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x0050 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x0050 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:50:0x0099 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.focus.FocusPropertiesImpl, androidx.compose.ui.focus.FocusProperties, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v8, types: [androidx.compose.ui.focus.FocusPropertiesModifierNode] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final FocusPropertiesImpl fetchFocusProperties$ui_release() {
        NodeChain nodeChain;
        ?? obj = new Object();
        obj.canFocus = true;
        FocusRequester focusRequester = FocusRequester.Default;
        obj.next = focusRequester;
        obj.previous = focusRequester;
        obj.up = focusRequester;
        obj.down = focusRequester;
        obj.left = focusRequester;
        obj.right = focusRequester;
        obj.start = focusRequester;
        obj.end = focusRequester;
        obj.enter = FocusProperties$exit$1.INSTANCE$2;
        obj.exit = FocusProperties$exit$1.INSTANCE$3;
        Modifier.Node node = this.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(this);
        Modifier.Node node2 = node;
        loop0: while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 3072) != 0) {
                while (node2 != null) {
                    int i = node2.kindSet;
                    if ((i & 3072) != 0) {
                        if (node2 != node && (i & 1024) != 0) {
                            break loop0;
                        }
                        if ((i & 2048) != 0) {
                            DelegatingNode delegatingNode = node2;
                            ?? r7 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof FocusPropertiesModifierNode) {
                                    ((FocusPropertiesModifierNode) delegatingNode).applyFocusProperties(obj);
                                } else if ((delegatingNode.kindSet & 2048) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node3 = delegatingNode.delegate;
                                    int i2 = 0;
                                    delegatingNode = delegatingNode;
                                    r7 = r7;
                                    while (node3 != null) {
                                        if ((node3.kindSet & 2048) != 0) {
                                            i2++;
                                            r7 = r7;
                                            if (i2 == 1) {
                                                delegatingNode = node3;
                                            } else {
                                                if (r7 == 0) {
                                                    ?? obj2 = new Object();
                                                    obj2.content = new Modifier.Node[16];
                                                    obj2.size = 0;
                                                    r7 = obj2;
                                                }
                                                if (delegatingNode != 0) {
                                                    r7.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r7.add(node3);
                                            }
                                        }
                                        node3 = node3.child;
                                        delegatingNode = delegatingNode;
                                        r7 = r7;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                delegatingNode = Snake.access$pop(r7);
                            }
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        return obj;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    public final void invalidateFocus$ui_release() {
        int ordinal = this.focusState.ordinal();
        if (ordinal == 0 || ordinal == 2) {
            ?? obj = new Object();
            Snake.observeReads(this, new NodeCoordinator$invoke$1(obj, 4, this));
            Object obj2 = obj.element;
            if (obj2 == null) {
                ResultKt.throwUninitializedPropertyAccessException("focusProperties");
                throw null;
            }
            if (((FocusProperties) obj2).getCanFocus()) {
                return;
            }
            ((FocusOwnerImpl) ((AndroidComposeView) Snake.requireOwner(this)).getFocusOwner()).clearFocus(true, true);
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        FocusStateImpl focusStateImpl = this.focusState;
        invalidateFocus$ui_release();
        if (focusStateImpl != this.focusState) {
            ResultKt.refreshFocusEventNodes(this);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onReset() {
        int ordinal = this.focusState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                scheduleInvalidationForFocusEvents$ui_release();
                this.focusState = FocusStateImpl.Inactive;
                return;
            } else if (ordinal != 2) {
                if (ordinal != 3) {
                    return;
                }
                scheduleInvalidationForFocusEvents$ui_release();
                return;
            }
        }
        ((FocusOwnerImpl) ((AndroidComposeView) Snake.requireOwner(this)).getFocusOwner()).clearFocus(true, true);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:21:0x003f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x0048 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0004 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0004 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x004e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:70:0x00b5 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:74:0x00be */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:79:0x007e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:80:0x007e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:83:0x00c4 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v11, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final void scheduleInvalidationForFocusEvents$ui_release() {
        NodeChain nodeChain;
        DelegatingNode delegatingNode = this.node;
        ?? r2 = 0;
        while (delegatingNode != 0) {
            if (delegatingNode instanceof FocusEventModifierNode) {
                ResultKt.invalidateFocusEvent((FocusEventModifierNode) delegatingNode);
            } else if ((delegatingNode.kindSet & 4096) != 0 && (delegatingNode instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode.delegate;
                int i = 0;
                delegatingNode = delegatingNode;
                r2 = r2;
                while (node != null) {
                    if ((node.kindSet & 4096) != 0) {
                        i++;
                        r2 = r2;
                        if (i == 1) {
                            delegatingNode = node;
                        } else {
                            if (r2 == 0) {
                                ?? obj = new Object();
                                obj.content = new Modifier.Node[16];
                                obj.size = 0;
                                r2 = obj;
                            }
                            if (delegatingNode != 0) {
                                r2.add(delegatingNode);
                                delegatingNode = 0;
                            }
                            r2.add(node);
                        }
                    }
                    node = node.child;
                    delegatingNode = delegatingNode;
                    r2 = r2;
                }
                if (i == 1) {
                }
            }
            delegatingNode = Snake.access$pop(r2);
        }
        Modifier.Node node2 = this.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node3 = node2.parent;
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(this);
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 5120) != 0) {
                while (node3 != null) {
                    int i2 = node3.kindSet;
                    if ((i2 & 5120) != 0 && (i2 & 1024) == 0 && node3.isAttached) {
                        DelegatingNode delegatingNode2 = node3;
                        ?? r7 = 0;
                        while (delegatingNode2 != 0) {
                            if (delegatingNode2 instanceof FocusEventModifierNode) {
                                ResultKt.invalidateFocusEvent((FocusEventModifierNode) delegatingNode2);
                            } else if ((delegatingNode2.kindSet & 4096) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                                Modifier.Node node4 = delegatingNode2.delegate;
                                int i3 = 0;
                                delegatingNode2 = delegatingNode2;
                                r7 = r7;
                                while (node4 != null) {
                                    if ((node4.kindSet & 4096) != 0) {
                                        i3++;
                                        r7 = r7;
                                        if (i3 == 1) {
                                            delegatingNode2 = node4;
                                        } else {
                                            if (r7 == 0) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                r7 = obj2;
                                            }
                                            if (delegatingNode2 != 0) {
                                                r7.add(delegatingNode2);
                                                delegatingNode2 = 0;
                                            }
                                            r7.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    delegatingNode2 = delegatingNode2;
                                    r7 = r7;
                                }
                                if (i3 == 1) {
                                }
                            }
                            delegatingNode2 = Snake.access$pop(r7);
                        }
                    }
                    node3 = node3.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
    }
}
