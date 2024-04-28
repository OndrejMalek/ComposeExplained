package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.BackwardsCompatNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.HashSet;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ModifierLocalManager {
    public final MutableVector inserted;
    public final MutableVector insertedLocal;
    public boolean invalidated;
    public final Owner owner;
    public final MutableVector removed;
    public final MutableVector removedLocal;

    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public ModifierLocalManager(Owner owner) {
        ResultKt.checkNotNullParameter(owner, "owner");
        this.owner = owner;
        ?? obj = new Object();
        obj.content = new BackwardsCompatNode[16];
        obj.size = 0;
        this.inserted = obj;
        ?? obj2 = new Object();
        obj2.content = new ModifierLocal[16];
        obj2.size = 0;
        this.insertedLocal = obj2;
        ?? obj3 = new Object();
        obj3.content = new LayoutNode[16];
        obj3.size = 0;
        this.removed = obj3;
        ?? obj4 = new Object();
        obj4.content = new ModifierLocal[16];
        obj4.size = 0;
        this.removedLocal = obj4;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0098 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x00a1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0041 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0041 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x00a7 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static void invalidateConsumersOfNodeForKey(Modifier.Node node, ModifierLocal modifierLocal, HashSet hashSet) {
        Modifier.Node node2 = node.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitSubtreeIf called on an unattached node".toString());
        }
        ?? obj = new Object();
        obj.content = new Modifier.Node[16];
        obj.size = 0;
        Modifier.Node node3 = node2.child;
        if (node3 == null) {
            Snake.access$addLayoutNodeChildren(obj, node2);
        } else {
            obj.add(node3);
        }
        while (obj.isNotEmpty()) {
            Modifier.Node node4 = (Modifier.Node) obj.removeAt(obj.size - 1);
            if ((node4.aggregateChildKindSet & 32) != 0) {
                for (Modifier.Node node5 = node4; node5 != null; node5 = node5.child) {
                    if ((node5.kindSet & 32) != 0) {
                        DelegatingNode delegatingNode = node5;
                        ?? r7 = 0;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) delegatingNode;
                                if (modifierLocalModifierNode instanceof BackwardsCompatNode) {
                                    BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) modifierLocalModifierNode;
                                    if ((backwardsCompatNode.element instanceof ModifierLocalConsumer) && backwardsCompatNode.readValues.contains(modifierLocal)) {
                                        hashSet.add(modifierLocalModifierNode);
                                    }
                                }
                                if (!(!modifierLocalModifierNode.getProvidedValues().contains$ui_release(modifierLocal))) {
                                    break;
                                }
                            } else if ((delegatingNode.kindSet & 32) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node6 = delegatingNode.delegate;
                                int i = 0;
                                delegatingNode = delegatingNode;
                                r7 = r7;
                                while (node6 != null) {
                                    if ((node6.kindSet & 32) != 0) {
                                        i++;
                                        r7 = r7;
                                        if (i == 1) {
                                            delegatingNode = node6;
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
                                            r7.add(node6);
                                        }
                                    }
                                    node6 = node6.child;
                                    delegatingNode = delegatingNode;
                                    r7 = r7;
                                }
                                if (i == 1) {
                                }
                            }
                            delegatingNode = Snake.access$pop(r7);
                        }
                    }
                }
            }
            Snake.access$addLayoutNodeChildren(obj, node4);
        }
    }

    public final void invalidate() {
        if (this.invalidated) {
            return;
        }
        this.invalidated = true;
        ((AndroidComposeView) this.owner).registerOnEndApplyChangesListener(new LayoutNode$_foldedChildren$1(8, this));
    }
}
