package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;

/* loaded from: classes.dex */
public final class OnPositionedDispatcher {
    public final MutableVector layoutNodes;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public OnPositionedDispatcher() {
        ?? obj = new Object();
        obj.content = new LayoutNode[16];
        obj.size = 0;
        this.layoutNodes = obj;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:33:0x006b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0074 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0030 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0030 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x007a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v6 */
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
    public static void dispatchHierarchy(LayoutNode layoutNode) {
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        int i = 0;
        if (layoutNodeLayoutDelegate.layoutState == 5 && !layoutNodeLayoutDelegate.layoutPending && !layoutNodeLayoutDelegate.measurePending && layoutNode.isPlaced()) {
            Modifier.Node node = layoutNode.nodes.head;
            if ((node.aggregateChildKindSet & 256) != 0) {
                while (node != null) {
                    if ((node.kindSet & 256) != 0) {
                        DelegatingNode delegatingNode = node;
                        ?? r6 = 0;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof GlobalPositionAwareModifierNode) {
                                GlobalPositionAwareModifierNode globalPositionAwareModifierNode = (GlobalPositionAwareModifierNode) delegatingNode;
                                globalPositionAwareModifierNode.onGloballyPositioned(Snake.m215requireCoordinator64DMado(globalPositionAwareModifierNode, 256));
                            } else if ((delegatingNode.kindSet & 256) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node2 = delegatingNode.delegate;
                                int i2 = 0;
                                delegatingNode = delegatingNode;
                                r6 = r6;
                                while (node2 != null) {
                                    if ((node2.kindSet & 256) != 0) {
                                        i2++;
                                        r6 = r6;
                                        if (i2 == 1) {
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
                                if (i2 == 1) {
                                }
                            }
                            delegatingNode = Snake.access$pop(r6);
                        }
                    }
                    if ((node.aggregateChildKindSet & 256) == 0) {
                        break;
                    } else {
                        node = node.child;
                    }
                }
            }
        }
        layoutNode.needsOnPositionedDispatch = false;
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i3 = mutableVector.size;
        if (i3 > 0) {
            Object[] objArr = mutableVector.content;
            do {
                dispatchHierarchy((LayoutNode) objArr[i]);
                i++;
            } while (i < i3);
        }
    }
}
