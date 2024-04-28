package androidx.compose.ui.node;

import androidx.compose.runtime.AbstractApplier;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.platform.WeakCache;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class UiApplier extends AbstractApplier {
    public final void insertBottomUp(int i, Object obj) {
        LayoutNode layoutNode = (LayoutNode) obj;
        ResultKt.checkNotNullParameter(layoutNode, "instance");
        LayoutNode layoutNode2 = (LayoutNode) this.current;
        layoutNode2.getClass();
        if (layoutNode._foldedParent != null) {
            StringBuilder sb = new StringBuilder("Cannot insert ");
            sb.append(layoutNode);
            sb.append(" because it already has a parent. This tree: ");
            sb.append(layoutNode2.debugTreeToString(0));
            sb.append(" Other tree: ");
            LayoutNode layoutNode3 = layoutNode._foldedParent;
            sb.append(layoutNode3 != null ? layoutNode3.debugTreeToString(0) : null);
            throw new IllegalStateException(sb.toString().toString());
        }
        if (layoutNode.owner != null) {
            throw new IllegalStateException(("Cannot insert " + layoutNode + " because it already has an owner. This tree: " + layoutNode2.debugTreeToString(0) + " Other tree: " + layoutNode.debugTreeToString(0)).toString());
        }
        layoutNode._foldedParent = layoutNode2;
        WeakCache weakCache = layoutNode2._foldedChildren;
        ((MutableVector) weakCache.values).add(i, layoutNode);
        ((Function0) weakCache.referenceQueue).invoke();
        layoutNode2.onZSortedChildrenInvalidated$ui_release();
        if (layoutNode.isVirtual) {
            layoutNode2.virtualChildrenCount++;
        }
        layoutNode2.invalidateUnfoldedVirtualChildren();
        Owner owner = layoutNode2.owner;
        if (owner != null) {
            layoutNode.attach$ui_release(owner);
        }
        if (layoutNode.layoutDelegate.childrenAccessingCoordinatesDuringPlacement > 0) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode2.layoutDelegate;
            layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
        }
    }

    @Override // androidx.compose.runtime.Applier
    public final void insertTopDown(int i, Object obj) {
        ResultKt.checkNotNullParameter((LayoutNode) obj, "instance");
    }

    @Override // androidx.compose.runtime.Applier
    public final void move(int i, int i2, int i3) {
        LayoutNode layoutNode = (LayoutNode) this.current;
        layoutNode.getClass();
        if (i == i2) {
            return;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i > i2 ? i + i4 : i;
            int i6 = i > i2 ? i2 + i4 : (i2 + i3) - 2;
            WeakCache weakCache = layoutNode._foldedChildren;
            Object removeAt = ((MutableVector) weakCache.values).removeAt(i5);
            Object obj = weakCache.referenceQueue;
            ((Function0) obj).invoke();
            ((MutableVector) weakCache.values).add(i6, (LayoutNode) removeAt);
            ((Function0) obj).invoke();
        }
        layoutNode.onZSortedChildrenInvalidated$ui_release();
        layoutNode.invalidateUnfoldedVirtualChildren();
        layoutNode.invalidateMeasurements$ui_release();
    }

    @Override // androidx.compose.runtime.Applier
    public final void remove(int i, int i2) {
        LayoutNode layoutNode = (LayoutNode) this.current;
        layoutNode.getClass();
        if (i2 < 0) {
            throw new IllegalArgumentException(("count (" + i2 + ") must be greater than 0").toString());
        }
        int i3 = (i2 + i) - 1;
        if (i > i3) {
            return;
        }
        while (true) {
            WeakCache weakCache = layoutNode._foldedChildren;
            Object removeAt = ((MutableVector) weakCache.values).removeAt(i3);
            ((Function0) weakCache.referenceQueue).invoke();
            layoutNode.onChildRemoved((LayoutNode) removeAt);
            if (i3 == i) {
                return;
            } else {
                i3--;
            }
        }
    }
}
