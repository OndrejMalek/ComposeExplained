package androidx.compose.ui.focus;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import java.util.Arrays;
import java.util.Comparator;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;

/* loaded from: classes.dex */
public final class FocusableChildrenComparator implements Comparator {
    public static final FocusableChildrenComparator INSTANCE = new Object();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        FocusTargetNode focusTargetNode = (FocusTargetNode) obj;
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) obj2;
        if (focusTargetNode == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        if (focusTargetNode2 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        int i = 0;
        if (!_BOUNDARY.isEligibleForFocusSearch(focusTargetNode) || !_BOUNDARY.isEligibleForFocusSearch(focusTargetNode2)) {
            if (_BOUNDARY.isEligibleForFocusSearch(focusTargetNode)) {
                return -1;
            }
            return _BOUNDARY.isEligibleForFocusSearch(focusTargetNode2) ? 1 : 0;
        }
        NodeCoordinator nodeCoordinator = focusTargetNode.coordinator;
        LayoutNode layoutNode = nodeCoordinator != null ? nodeCoordinator.layoutNode : null;
        if (layoutNode == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        NodeCoordinator nodeCoordinator2 = focusTargetNode2.coordinator;
        LayoutNode layoutNode2 = nodeCoordinator2 != null ? nodeCoordinator2.layoutNode : null;
        if (layoutNode2 == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (ResultKt.areEqual(layoutNode, layoutNode2)) {
            return 0;
        }
        Object[] objArr = new LayoutNode[16];
        int i2 = 0;
        while (layoutNode != null) {
            int i3 = i2 + 1;
            if (objArr.length < i3) {
                objArr = Arrays.copyOf(objArr, Math.max(i3, objArr.length * 2));
                ResultKt.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
            }
            if (i2 != 0) {
                MapsKt___MapsJvmKt.copyInto(objArr, objArr, 0 + 1, 0, i2);
            }
            objArr[0] = layoutNode;
            i2++;
            layoutNode = layoutNode.getParent$ui_release();
        }
        Object[] objArr2 = new LayoutNode[16];
        int i4 = 0;
        while (layoutNode2 != null) {
            int i5 = i4 + 1;
            if (objArr2.length < i5) {
                objArr2 = Arrays.copyOf(objArr2, Math.max(i5, objArr2.length * 2));
                ResultKt.checkNotNullExpressionValue(objArr2, "copyOf(this, newSize)");
            }
            if (i4 != 0) {
                MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, 0 + 1, 0, i4);
            }
            objArr2[0] = layoutNode2;
            i4++;
            layoutNode2 = layoutNode2.getParent$ui_release();
        }
        int min = Math.min(i2 - 1, i4 - 1);
        if (min >= 0) {
            while (ResultKt.areEqual(objArr[i], objArr2[i])) {
                if (i != min) {
                    i++;
                }
            }
            return ResultKt.compare(((LayoutNode) objArr[i]).getPlaceOrder$ui_release(), ((LayoutNode) objArr2[i]).getPlaceOrder$ui_release());
        }
        throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.".toString());
    }
}
