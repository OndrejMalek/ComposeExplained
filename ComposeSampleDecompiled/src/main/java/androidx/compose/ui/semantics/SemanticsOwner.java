package androidx.compose.ui.semantics;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.Snake;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SemanticsOwner {
    public final LayoutNode rootNode;

    public SemanticsOwner(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "rootNode");
        this.rootNode = layoutNode;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0057 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0018 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x0018 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x005d */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final SemanticsNode getUnmergedRootSemanticsNode() {
        LayoutNode layoutNode = this.rootNode;
        Modifier.Node node = layoutNode.nodes.head;
        Object obj = null;
        if ((node.aggregateChildKindSet & 8) != 0) {
            loop0: while (true) {
                if (node != null) {
                    if ((node.kindSet & 8) != 0) {
                        Modifier.Node node2 = node;
                        MutableVector mutableVector = null;
                        while (node2 != null) {
                            if (node2 instanceof SemanticsModifierNode) {
                                obj = node2;
                                break loop0;
                            }
                            if ((node2.kindSet & 8) != 0 && (node2 instanceof DelegatingNode)) {
                                Modifier.Node node3 = ((DelegatingNode) node2).delegate;
                                int i = 0;
                                mutableVector = mutableVector;
                                while (node3 != null) {
                                    if ((node3.kindSet & 8) != 0) {
                                        i++;
                                        mutableVector = mutableVector;
                                        if (i == 1) {
                                            node2 = node3;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector = obj2;
                                            }
                                            if (node2 != null) {
                                                mutableVector.add(node2);
                                                node2 = null;
                                            }
                                            mutableVector.add(node3);
                                        }
                                    }
                                    node3 = node3.child;
                                    mutableVector = mutableVector;
                                }
                                if (i == 1) {
                                }
                            }
                            node2 = Snake.access$pop(mutableVector);
                        }
                    }
                    if ((node.aggregateChildKindSet & 8) == 0) {
                        break;
                    }
                    node = node.child;
                } else {
                    break;
                }
            }
        }
        ResultKt.checkNotNull(obj);
        return new SemanticsNode(((Modifier.Node) ((SemanticsModifierNode) obj)).node, false, layoutNode, new SemanticsConfiguration());
    }
}
