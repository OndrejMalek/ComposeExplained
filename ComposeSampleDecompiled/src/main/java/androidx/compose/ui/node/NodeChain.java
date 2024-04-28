package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import java.util.HashSet;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class NodeChain {
    public MutableVector buffer;
    public Differ cachedDiffer;
    public MutableVector current;
    public Modifier.Node head;
    public final InnerNodeCoordinator innerCoordinator;
    public final LayoutNode layoutNode;
    public NodeCoordinator outerCoordinator;
    public final TailModifierNode tail;

    /* loaded from: classes.dex */
    public final class Differ {
        public MutableVector after;
        public MutableVector before;
        public Modifier.Node node;
        public int offset;
        public boolean shouldAttachOnInsert;

        public Differ(Modifier.Node node, int i, MutableVector mutableVector, MutableVector mutableVector2, boolean z) {
            this.node = node;
            this.offset = i;
            this.before = mutableVector;
            this.after = mutableVector2;
            this.shouldAttachOnInsert = z;
        }

        public final boolean areItemsTheSame(int i, int i2) {
            MutableVector mutableVector = this.before;
            int i3 = this.offset;
            return NodeChainKt.actionForModifiers((Modifier.Element) mutableVector.content[i + i3], (Modifier.Element) this.after.content[i3 + i2]) != 0;
        }
    }

    public NodeChain(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        InnerNodeCoordinator innerNodeCoordinator = new InnerNodeCoordinator(layoutNode);
        this.innerCoordinator = innerNodeCoordinator;
        this.outerCoordinator = innerNodeCoordinator;
        TailModifierNode tailModifierNode = innerNodeCoordinator.tail;
        this.tail = tailModifierNode;
        this.head = tailModifierNode;
    }

    public static final void access$propagateCoordinator(NodeChain nodeChain, Modifier.Node node, NodeCoordinator nodeCoordinator) {
        nodeChain.getClass();
        for (Modifier.Node node2 = node.parent; node2 != null; node2 = node2.parent) {
            if (node2 == NodeChainKt.SentinelHead) {
                LayoutNode parent$ui_release = nodeChain.layoutNode.getParent$ui_release();
                nodeCoordinator.wrappedBy = parent$ui_release != null ? parent$ui_release.nodes.innerCoordinator : null;
                nodeChain.outerCoordinator = nodeCoordinator;
                return;
            } else {
                if ((node2.kindSet & 2) != 0) {
                    return;
                }
                node2.updateCoordinator$ui_release(nodeCoordinator);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.node.BackwardsCompatNode, androidx.compose.ui.Modifier$Node] */
    public static Modifier.Node createAndInsertNodeAsChild(Modifier.Element element, Modifier.Node node) {
        Modifier.Node node2;
        if (element instanceof ModifierNodeElement) {
            node2 = ((ModifierNodeElement) element).create();
            node2.kindSet = Snake.calculateNodeKindSetFromIncludingDelegates(node2);
        } else {
            ResultKt.checkNotNullParameter(element, "element");
            ?? node3 = new Modifier.Node();
            node3.kindSet = Snake.calculateNodeKindSetFrom(element);
            node3.element = element;
            node3.readValues = new HashSet();
            node2 = node3;
        }
        if (!(!node2.isAttached)) {
            throw new IllegalStateException("A ModifierNodeElement cannot return an already attached node from create() ".toString());
        }
        node2.insertedNodeAwaitingAttachForInvalidation = true;
        Modifier.Node node4 = node.child;
        if (node4 != null) {
            node4.parent = node2;
            node2.child = node4;
        }
        node.child = node2;
        node2.parent = node;
        return node2;
    }

    public static Modifier.Node detachAndRemoveNode(Modifier.Node node) {
        boolean z = node.isAttached;
        if (z) {
            if (!z) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Snake.autoInvalidateNodeIncludingDelegates(node, -1, 2);
            node.runDetachLifecycle$ui_release();
            node.markAsDetached$ui_release();
        }
        Modifier.Node node2 = node.child;
        Modifier.Node node3 = node.parent;
        if (node2 != null) {
            node2.parent = node3;
            node.child = null;
        }
        if (node3 != null) {
            node3.child = node2;
            node.parent = null;
        }
        ResultKt.checkNotNull(node3);
        return node3;
    }

    public static void updateNode(Modifier.Element element, Modifier.Element element2, Modifier.Node node) {
        if ((element instanceof ModifierNodeElement) && (element2 instanceof ModifierNodeElement)) {
            NodeChainKt$SentinelHead$1 nodeChainKt$SentinelHead$1 = NodeChainKt.SentinelHead;
            ResultKt.checkNotNull(node, "null cannot be cast to non-null type T of androidx.compose.ui.node.NodeChainKt.updateUnsafe");
            ((ModifierNodeElement) element2).update(node);
            if (node.isAttached) {
                Snake.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.updatedNodeAwaitingAttachForInvalidation = true;
                return;
            }
        }
        if (!(node instanceof BackwardsCompatNode)) {
            throw new IllegalStateException("Unknown Modifier.Node type".toString());
        }
        BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) node;
        backwardsCompatNode.getClass();
        ResultKt.checkNotNullParameter(element2, "value");
        if (backwardsCompatNode.isAttached) {
            backwardsCompatNode.unInitializeModifier();
        }
        backwardsCompatNode.element = element2;
        backwardsCompatNode.kindSet = Snake.calculateNodeKindSetFrom(element2);
        if (backwardsCompatNode.isAttached) {
            backwardsCompatNode.initializeModifier(false);
        }
        if (node.isAttached) {
            Snake.autoInvalidateUpdatedNode(node);
        } else {
            node.updatedNodeAwaitingAttachForInvalidation = true;
        }
    }

    /* renamed from: has-H91voCI$ui_release, reason: not valid java name */
    public final boolean m190hasH91voCI$ui_release(int i) {
        return (i & this.head.aggregateChildKindSet) != 0;
    }

    public final void runAttachLifecycle() {
        for (Modifier.Node node = this.head; node != null; node = node.child) {
            node.runAttachLifecycle$ui_release();
            if (node.insertedNodeAwaitingAttachForInvalidation) {
                Snake.autoInvalidateInsertedNode(node);
            }
            if (node.updatedNodeAwaitingAttachForInvalidation) {
                Snake.autoInvalidateUpdatedNode(node);
            }
            node.insertedNodeAwaitingAttachForInvalidation = false;
            node.updatedNodeAwaitingAttachForInvalidation = false;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v13 ??, still in use, count: 1, list:
          (r11v13 ?? I:androidx.compose.ui.node.NodeChain$Differ) from 0x001e: IPUT 
          (r11v13 ?? I:androidx.compose.ui.node.NodeChain$Differ)
          (r29v0 'this' ?? I:androidx.compose.ui.node.NodeChain A[IMMUTABLE_TYPE, THIS])
         androidx.compose.ui.node.NodeChain.cachedDiffer androidx.compose.ui.node.NodeChain$Differ
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:88)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:87)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:72)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:54)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:34)
        */
    public final void structuralUpdate(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v13 ??, still in use, count: 1, list:
          (r11v13 ?? I:androidx.compose.ui.node.NodeChain$Differ) from 0x001e: IPUT 
          (r11v13 ?? I:androidx.compose.ui.node.NodeChain$Differ)
          (r29v0 'this' ?? I:androidx.compose.ui.node.NodeChain A[IMMUTABLE_TYPE, THIS])
         androidx.compose.ui.node.NodeChain.cachedDiffer androidx.compose.ui.node.NodeChain$Differ
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:88)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:87)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:72)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:54)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r30v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:237)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

    public final void syncCoordinators() {
        LayoutNode layoutNode;
        LayoutModifierNodeCoordinator layoutModifierNodeCoordinator;
        Modifier.Node node = this.tail.parent;
        NodeCoordinator nodeCoordinator = this.innerCoordinator;
        Modifier.Node node2 = node;
        while (true) {
            layoutNode = this.layoutNode;
            if (node2 == null) {
                break;
            }
            LayoutModifierNode asLayoutModifierNode = Snake.asLayoutModifierNode(node2);
            if (asLayoutModifierNode != null) {
                NodeCoordinator nodeCoordinator2 = node2.coordinator;
                if (nodeCoordinator2 != null) {
                    LayoutModifierNodeCoordinator layoutModifierNodeCoordinator2 = (LayoutModifierNodeCoordinator) nodeCoordinator2;
                    LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator2.layoutModifierNode;
                    layoutModifierNodeCoordinator2.layoutModifierNode = asLayoutModifierNode;
                    layoutModifierNodeCoordinator = layoutModifierNodeCoordinator2;
                    if (layoutModifierNode != node2) {
                        OwnedLayer ownedLayer = layoutModifierNodeCoordinator2.layer;
                        layoutModifierNodeCoordinator = layoutModifierNodeCoordinator2;
                        if (ownedLayer != null) {
                            ownedLayer.invalidate();
                            layoutModifierNodeCoordinator = layoutModifierNodeCoordinator2;
                        }
                    }
                } else {
                    LayoutModifierNodeCoordinator layoutModifierNodeCoordinator3 = new LayoutModifierNodeCoordinator(layoutNode, asLayoutModifierNode);
                    node2.updateCoordinator$ui_release(layoutModifierNodeCoordinator3);
                    layoutModifierNodeCoordinator = layoutModifierNodeCoordinator3;
                }
                nodeCoordinator.wrappedBy = layoutModifierNodeCoordinator;
                layoutModifierNodeCoordinator.wrapped = nodeCoordinator;
                nodeCoordinator = layoutModifierNodeCoordinator;
            } else {
                node2.updateCoordinator$ui_release(nodeCoordinator);
            }
            node2 = node2.parent;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        nodeCoordinator.wrappedBy = parent$ui_release != null ? parent$ui_release.nodes.innerCoordinator : null;
        this.outerCoordinator = nodeCoordinator;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Modifier.Node node = this.head;
        TailModifierNode tailModifierNode = this.tail;
        if (node != tailModifierNode) {
            while (true) {
                if (node == null || node == tailModifierNode) {
                    break;
                }
                sb.append(String.valueOf(node));
                if (node.child == tailModifierNode) {
                    sb.append("]");
                    break;
                }
                sb.append(",");
                node = node.child;
            }
        } else {
            sb.append("]");
        }
        String sb2 = sb.toString();
        ResultKt.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
