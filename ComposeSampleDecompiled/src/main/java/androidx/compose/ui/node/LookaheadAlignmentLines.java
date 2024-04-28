package androidx.compose.ui.node;

import androidx.compose.ui.layout.AlignmentLine;
import java.util.Map;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class LookaheadAlignmentLines extends AlignmentLines {
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LookaheadAlignmentLines(AlignmentLinesOwner alignmentLinesOwner, int i) {
        super(alignmentLinesOwner);
        this.$r8$classId = i;
        if (i != 1) {
            ResultKt.checkNotNullParameter(alignmentLinesOwner, "alignmentLinesOwner");
        } else {
            ResultKt.checkNotNullParameter(alignmentLinesOwner, "alignmentLinesOwner");
            super(alignmentLinesOwner);
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLines
    public final Map getAlignmentLinesMap(NodeCoordinator nodeCoordinator) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(nodeCoordinator, "<this>");
                LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
                ResultKt.checkNotNull(lookaheadDelegate);
                return lookaheadDelegate.getMeasureResult$ui_release().alignmentLines;
            default:
                ResultKt.checkNotNullParameter(nodeCoordinator, "<this>");
                return nodeCoordinator.getMeasureResult$ui_release().alignmentLines;
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLines
    public final int getPositionFor(NodeCoordinator nodeCoordinator, AlignmentLine alignmentLine) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
                LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
                ResultKt.checkNotNull(lookaheadDelegate);
                return lookaheadDelegate.get(alignmentLine);
            default:
                ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
                return nodeCoordinator.get(alignmentLine);
        }
    }
}
