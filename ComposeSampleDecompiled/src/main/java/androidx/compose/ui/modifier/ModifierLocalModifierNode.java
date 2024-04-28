package androidx.compose.ui.modifier;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.Snake;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public interface ModifierLocalModifierNode extends ModifierLocalReadScope, DelegatableNode {
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0077 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0080 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x002d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x002d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0086 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [androidx.compose.ui.modifier.ModifierLocalModifierNode, androidx.compose.ui.node.DelegatableNode] */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    default Object getCurrent(ProvidableModifierLocal providableModifierLocal) {
        NodeChain nodeChain;
        ResultKt.checkNotNullParameter(providableModifierLocal, "<this>");
        Modifier.Node node = ((Modifier.Node) this).node;
        boolean z = node.isAttached;
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!z) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node2 = node.parent;
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(this);
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 32) != 0) {
                while (node2 != null) {
                    if ((node2.kindSet & 32) != 0) {
                        DelegatingNode delegatingNode = node2;
                        ?? r4 = 0;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) delegatingNode;
                                if (modifierLocalModifierNode.getProvidedValues().contains$ui_release(providableModifierLocal)) {
                                    return modifierLocalModifierNode.getProvidedValues().get$ui_release(providableModifierLocal);
                                }
                            } else if ((delegatingNode.kindSet & 32) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node3 = delegatingNode.delegate;
                                int i = 0;
                                delegatingNode = delegatingNode;
                                r4 = r4;
                                while (node3 != null) {
                                    if ((node3.kindSet & 32) != 0) {
                                        i++;
                                        r4 = r4;
                                        if (i == 1) {
                                            delegatingNode = node3;
                                        } else {
                                            if (r4 == 0) {
                                                ?? obj = new Object();
                                                obj.content = new Modifier.Node[16];
                                                obj.size = 0;
                                                r4 = obj;
                                            }
                                            if (delegatingNode != 0) {
                                                r4.add(delegatingNode);
                                                delegatingNode = 0;
                                            }
                                            r4.add(node3);
                                        }
                                    }
                                    node3 = node3.child;
                                    delegatingNode = delegatingNode;
                                    r4 = r4;
                                }
                                if (i == 1) {
                                }
                            }
                            delegatingNode = Snake.access$pop(r4);
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        return providableModifierLocal.defaultFactory.invoke();
    }

    default ResultKt getProvidedValues() {
        return EmptyMap.INSTANCE;
    }
}
