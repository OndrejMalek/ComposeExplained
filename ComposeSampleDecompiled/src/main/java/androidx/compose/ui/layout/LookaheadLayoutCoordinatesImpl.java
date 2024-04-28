package androidx.compose.ui.layout;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LookaheadLayoutCoordinatesImpl implements LayoutCoordinates {
    public final LookaheadDelegate lookaheadDelegate;

    public LookaheadLayoutCoordinatesImpl(LookaheadDelegate lookaheadDelegate) {
        ResultKt.checkNotNullParameter(lookaheadDelegate, "lookaheadDelegate");
        this.lookaheadDelegate = lookaheadDelegate;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            throw new IllegalStateException("LayoutCoordinate operations are only valid when isAttached is true".toString());
        }
        NodeCoordinator nodeCoordinator = this.lookaheadDelegate.coordinator.layoutNode.nodes.outerCoordinator.wrappedBy;
        if (nodeCoordinator == null || (lookaheadDelegate = nodeCoordinator.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.lookaheadLayoutCoordinates;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: getSize-YbymL2g */
    public final long mo159getSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        return ResultKt.IntSize(lookaheadDelegate.width, lookaheadDelegate.height);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final boolean isAttached() {
        return this.lookaheadDelegate.coordinator.isAttached();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final Rect localBoundingBoxOf(NodeCoordinator nodeCoordinator, boolean z) {
        ResultKt.checkNotNullParameter(nodeCoordinator, "sourceCoordinates");
        return this.lookaheadDelegate.coordinator.localBoundingBoxOf(nodeCoordinator, z);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-R5De75A */
    public final long mo160localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j) {
        ResultKt.checkNotNullParameter(layoutCoordinates, "sourceCoordinates");
        boolean z = layoutCoordinates instanceof LookaheadLayoutCoordinatesImpl;
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        if (!z) {
            LookaheadDelegate rootLookaheadDelegate = _BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate);
            long mo160localPositionOfR5De75A = mo160localPositionOfR5De75A(rootLookaheadDelegate.lookaheadLayoutCoordinates, j);
            NodeCoordinator nodeCoordinator = rootLookaheadDelegate.coordinator;
            nodeCoordinator.getClass();
            return Offset.m79plusMKHz9U(mo160localPositionOfR5De75A, nodeCoordinator.mo160localPositionOfR5De75A(layoutCoordinates, Offset.Zero));
        }
        LookaheadDelegate lookaheadDelegate2 = ((LookaheadLayoutCoordinatesImpl) layoutCoordinates).lookaheadDelegate;
        lookaheadDelegate2.coordinator.onCoordinatesUsed$ui_release();
        LookaheadDelegate lookaheadDelegate3 = lookaheadDelegate.coordinator.findCommonAncestor$ui_release(lookaheadDelegate2.coordinator).getLookaheadDelegate();
        if (lookaheadDelegate3 != null) {
            long m186positionInBjo55l4$ui_release = lookaheadDelegate2.m186positionInBjo55l4$ui_release(lookaheadDelegate3);
            long IntOffset = ResultKt.IntOffset(ResultKt.roundToInt(Offset.m76getXimpl(j)), ResultKt.roundToInt(Offset.m77getYimpl(j)));
            long IntOffset2 = ResultKt.IntOffset(((int) (m186positionInBjo55l4$ui_release >> 32)) + ((int) (IntOffset >> 32)), ((int) (m186positionInBjo55l4$ui_release & 4294967295L)) + ((int) (IntOffset & 4294967295L)));
            long m186positionInBjo55l4$ui_release2 = lookaheadDelegate.m186positionInBjo55l4$ui_release(lookaheadDelegate3);
            long IntOffset3 = ResultKt.IntOffset(((int) (IntOffset2 >> 32)) - ((int) (m186positionInBjo55l4$ui_release2 >> 32)), ((int) (IntOffset2 & 4294967295L)) - ((int) (m186positionInBjo55l4$ui_release2 & 4294967295L)));
            return _BOUNDARY.Offset((int) (IntOffset3 >> 32), (int) (IntOffset3 & 4294967295L));
        }
        LookaheadDelegate rootLookaheadDelegate2 = _BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate2);
        long m186positionInBjo55l4$ui_release3 = lookaheadDelegate2.m186positionInBjo55l4$ui_release(rootLookaheadDelegate2);
        long j2 = rootLookaheadDelegate2.position;
        long IntOffset4 = ResultKt.IntOffset(((int) (m186positionInBjo55l4$ui_release3 >> 32)) + ((int) (j2 >> 32)), ((int) (m186positionInBjo55l4$ui_release3 & 4294967295L)) + ((int) (j2 & 4294967295L)));
        long IntOffset5 = ResultKt.IntOffset(ResultKt.roundToInt(Offset.m76getXimpl(j)), ResultKt.roundToInt(Offset.m77getYimpl(j)));
        long IntOffset6 = ResultKt.IntOffset(((int) (IntOffset4 >> 32)) + ((int) (IntOffset5 >> 32)), ((int) (IntOffset4 & 4294967295L)) + ((int) (IntOffset5 & 4294967295L)));
        long m186positionInBjo55l4$ui_release4 = lookaheadDelegate.m186positionInBjo55l4$ui_release(_BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate));
        long j3 = _BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate).position;
        long IntOffset7 = ResultKt.IntOffset(((int) (m186positionInBjo55l4$ui_release4 >> 32)) + ((int) (j3 >> 32)), ((int) (m186positionInBjo55l4$ui_release4 & 4294967295L)) + ((int) (j3 & 4294967295L)));
        long IntOffset8 = ResultKt.IntOffset(((int) (IntOffset6 >> 32)) - ((int) (IntOffset7 >> 32)), ((int) (IntOffset6 & 4294967295L)) - ((int) (IntOffset7 & 4294967295L)));
        NodeCoordinator nodeCoordinator2 = _BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate).coordinator.wrappedBy;
        ResultKt.checkNotNull(nodeCoordinator2);
        NodeCoordinator nodeCoordinator3 = rootLookaheadDelegate2.coordinator.wrappedBy;
        ResultKt.checkNotNull(nodeCoordinator3);
        return nodeCoordinator2.mo160localPositionOfR5De75A(nodeCoordinator3, _BOUNDARY.Offset((int) (IntOffset8 >> 32), (int) (IntOffset8 & 4294967295L)));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToRoot-MK-Hz9U */
    public final long mo161localToRootMKHz9U(long j) {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        NodeCoordinator nodeCoordinator = lookaheadDelegate.coordinator;
        LookaheadDelegate rootLookaheadDelegate = _BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate);
        long j2 = Offset.Zero;
        return nodeCoordinator.mo161localToRootMKHz9U(Offset.m79plusMKHz9U(j, Offset.m78minusMKHz9U(mo160localPositionOfR5De75A(rootLookaheadDelegate.lookaheadLayoutCoordinates, j2), lookaheadDelegate.coordinator.mo160localPositionOfR5De75A(rootLookaheadDelegate.coordinator, j2))));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public final long mo162localToWindowMKHz9U(long j) {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        NodeCoordinator nodeCoordinator = lookaheadDelegate.coordinator;
        LookaheadDelegate rootLookaheadDelegate = _BOUNDARY.getRootLookaheadDelegate(lookaheadDelegate);
        long j2 = Offset.Zero;
        return nodeCoordinator.mo162localToWindowMKHz9U(Offset.m79plusMKHz9U(j, Offset.m78minusMKHz9U(mo160localPositionOfR5De75A(rootLookaheadDelegate.lookaheadLayoutCoordinates, j2), lookaheadDelegate.coordinator.mo160localPositionOfR5De75A(rootLookaheadDelegate.coordinator, j2))));
    }
}
