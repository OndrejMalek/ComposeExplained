package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class DelegatingNode extends Modifier.Node {
    public Modifier.Node delegate;
    public final int selfKindSet = Snake.calculateNodeKindSetFrom(this);

    public final void delegate(Modifier.Node node) {
        Modifier.Node node2;
        Modifier.Node node3 = node.node;
        if (node3 != node) {
            Modifier.Node node4 = node.parent;
            if (node3 != this.node || !ResultKt.areEqual(node4, this)) {
                throw new IllegalStateException("Cannot delegate to an already delegated node".toString());
            }
            return;
        }
        if (!(!node3.isAttached)) {
            throw new IllegalStateException("Cannot delegate to an already attached node".toString());
        }
        Modifier.Node node5 = this.node;
        ResultKt.checkNotNullParameter(node5, "owner");
        node3.node = node5;
        int i = this.kindSet;
        int calculateNodeKindSetFromIncludingDelegates = Snake.calculateNodeKindSetFromIncludingDelegates(node3);
        node3.kindSet = calculateNodeKindSetFromIncludingDelegates;
        int i2 = this.kindSet;
        int i3 = calculateNodeKindSetFromIncludingDelegates & 2;
        if (i3 != 0 && (i2 & 2) != 0 && !(this instanceof LayoutModifierNode)) {
            throw new IllegalStateException(("Delegating to multiple LayoutModifierNodes without the delegating node implementing LayoutModifierNode itself is not allowed.\nDelegating Node: " + this + "\nDelegate Node: " + node3).toString());
        }
        node3.child = this.delegate;
        this.delegate = node3;
        node3.parent = this;
        int i4 = calculateNodeKindSetFromIncludingDelegates | i2;
        this.kindSet = i4;
        if (i2 != i4) {
            Modifier.Node node6 = this.node;
            if (node6 == this) {
                this.aggregateChildKindSet = i4;
            }
            if (this.isAttached) {
                Modifier.Node node7 = this;
                while (node7 != null) {
                    i4 |= node7.kindSet;
                    node7.kindSet = i4;
                    if (node7 == node6) {
                        break;
                    } else {
                        node7 = node7.parent;
                    }
                }
                int i5 = i4 | ((node7 == null || (node2 = node7.child) == null) ? 0 : node2.aggregateChildKindSet);
                while (node7 != null) {
                    i5 |= node7.kindSet;
                    node7.aggregateChildKindSet = i5;
                    node7 = node7.parent;
                }
            }
        }
        if (this.isAttached) {
            if (i3 == 0 || (i & 2) != 0) {
                updateCoordinator$ui_release(this.coordinator);
            } else {
                NodeChain nodeChain = Snake.requireLayoutNode(this).nodes;
                this.node.updateCoordinator$ui_release(null);
                nodeChain.syncCoordinators();
            }
            node3.markAsAttached$ui_release();
            node3.runAttachLifecycle$ui_release();
            Snake.autoInvalidateInsertedNode(node3);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void markAsAttached$ui_release() {
        super.markAsAttached$ui_release();
        for (Modifier.Node node = this.delegate; node != null; node = node.child) {
            node.updateCoordinator$ui_release(this.coordinator);
            if (!node.isAttached) {
                node.markAsAttached$ui_release();
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void markAsDetached$ui_release() {
        for (Modifier.Node node = this.delegate; node != null; node = node.child) {
            node.markAsDetached$ui_release();
        }
        super.markAsDetached$ui_release();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void reset$ui_release() {
        super.reset$ui_release();
        for (Modifier.Node node = this.delegate; node != null; node = node.child) {
            node.reset$ui_release();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void runAttachLifecycle$ui_release() {
        for (Modifier.Node node = this.delegate; node != null; node = node.child) {
            node.runAttachLifecycle$ui_release();
        }
        super.runAttachLifecycle$ui_release();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void runDetachLifecycle$ui_release() {
        super.runDetachLifecycle$ui_release();
        for (Modifier.Node node = this.delegate; node != null; node = node.child) {
            node.runDetachLifecycle$ui_release();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void updateCoordinator$ui_release(NodeCoordinator nodeCoordinator) {
        this.coordinator = nodeCoordinator;
        for (Modifier.Node node = this.delegate; node != null; node = node.child) {
            node.updateCoordinator$ui_release(nodeCoordinator);
        }
    }
}
