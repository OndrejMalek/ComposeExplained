package androidx.compose.ui.node;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.unit.IntOffset;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class LookaheadCapablePlaceable extends Placeable implements MeasureScope {
    public boolean isPlacingForAlignment;
    public boolean isShallowPlacing;

    public static void invalidateAlignmentLinesFromPositionChange(NodeCoordinator nodeCoordinator) {
        LookaheadAlignmentLines lookaheadAlignmentLines;
        ResultKt.checkNotNullParameter(nodeCoordinator, "<this>");
        NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrapped;
        LayoutNode layoutNode = nodeCoordinator2 != null ? nodeCoordinator2.layoutNode : null;
        LayoutNode layoutNode2 = nodeCoordinator.layoutNode;
        if (!ResultKt.areEqual(layoutNode, layoutNode2)) {
            layoutNode2.layoutDelegate.measurePassDelegate.alignmentLines.onAlignmentsChanged();
            return;
        }
        AlignmentLinesOwner parentAlignmentLinesOwner = layoutNode2.layoutDelegate.measurePassDelegate.getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null || (lookaheadAlignmentLines = ((LayoutNodeLayoutDelegate.MeasurePassDelegate) parentAlignmentLinesOwner).alignmentLines) == null) {
            return;
        }
        lookaheadAlignmentLines.onAlignmentsChanged();
    }

    public abstract int calculateAlignmentLine(AlignmentLine alignmentLine);

    public final int get(AlignmentLine alignmentLine) {
        int calculateAlignmentLine;
        ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
        if (!getHasMeasureResult() || (calculateAlignmentLine = calculateAlignmentLine(alignmentLine)) == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        long j = this.apparentToRealOffset;
        int i = IntOffset.$r8$clinit;
        return calculateAlignmentLine + ((int) (j & 4294967295L));
    }

    public abstract LookaheadCapablePlaceable getChild();

    public abstract boolean getHasMeasureResult();

    public abstract LayoutNode getLayoutNode();

    public abstract MeasureScope$layout$1 getMeasureResult$ui_release();

    public abstract LookaheadCapablePlaceable getParent();

    /* renamed from: getPosition-nOcc-ac, reason: not valid java name */
    public abstract long mo185getPositionnOccac();

    public abstract void replace$ui_release();
}
