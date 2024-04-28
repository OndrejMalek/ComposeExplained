package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;

/* loaded from: classes.dex */
public interface LayoutCoordinates {
    LayoutCoordinates getParentLayoutCoordinates();

    /* renamed from: getSize-YbymL2g, reason: not valid java name */
    long mo159getSizeYbymL2g();

    boolean isAttached();

    Rect localBoundingBoxOf(NodeCoordinator nodeCoordinator, boolean z);

    /* renamed from: localPositionOf-R5De75A, reason: not valid java name */
    long mo160localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j);

    /* renamed from: localToRoot-MK-Hz9U, reason: not valid java name */
    long mo161localToRootMKHz9U(long j);

    /* renamed from: localToWindow-MK-Hz9U, reason: not valid java name */
    long mo162localToWindowMKHz9U(long j);
}
