package androidx.compose.ui.node;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.unit.IntOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;

/* loaded from: classes.dex */
public abstract class AlignmentLines {
    public final AlignmentLinesOwner alignmentLinesOwner;
    public boolean previousUsedDuringParentLayout;
    public AlignmentLinesOwner queryOwner;
    public boolean usedByModifierLayout;
    public boolean usedByModifierMeasurement;
    public boolean usedDuringParentLayout;
    public boolean usedDuringParentMeasurement;
    public boolean dirty = true;
    public final HashMap alignmentLineMap = new HashMap();

    public AlignmentLines(AlignmentLinesOwner alignmentLinesOwner) {
        this.alignmentLinesOwner = alignmentLinesOwner;
    }

    public static final void access$addAlignmentLine(AlignmentLines alignmentLines, AlignmentLine alignmentLine, int i, NodeCoordinator nodeCoordinator) {
        alignmentLines.getClass();
        float f = i;
        long Offset = _BOUNDARY.Offset(f, f);
        while (true) {
            switch (((LookaheadAlignmentLines) alignmentLines).$r8$classId) {
                case 0:
                    ResultKt.checkNotNullParameter(nodeCoordinator, "$this$calculatePositionInParent");
                    LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
                    ResultKt.checkNotNull(lookaheadDelegate);
                    long j = lookaheadDelegate.position;
                    int i2 = IntOffset.$r8$clinit;
                    Offset = Offset.m79plusMKHz9U(_BOUNDARY.Offset((int) (j >> 32), (int) (j & 4294967295L)), Offset);
                    break;
                default:
                    ResultKt.checkNotNullParameter(nodeCoordinator, "$this$calculatePositionInParent");
                    Offset = nodeCoordinator.m202toParentPositionMKHz9U(Offset);
                    break;
            }
            nodeCoordinator = nodeCoordinator.wrappedBy;
            ResultKt.checkNotNull(nodeCoordinator);
            if (ResultKt.areEqual(nodeCoordinator, alignmentLines.alignmentLinesOwner.getInnerCoordinator())) {
                int roundToInt = alignmentLine instanceof HorizontalAlignmentLine ? ResultKt.roundToInt(Offset.m77getYimpl(Offset)) : ResultKt.roundToInt(Offset.m76getXimpl(Offset));
                HashMap hashMap = alignmentLines.alignmentLineMap;
                if (hashMap.containsKey(alignmentLine)) {
                    ResultKt.checkNotNullParameter(hashMap, "<this>");
                    Object obj = hashMap.get(alignmentLine);
                    if (obj == null && !hashMap.containsKey(alignmentLine)) {
                        throw new NoSuchElementException("Key " + alignmentLine + " is missing in the map.");
                    }
                    int intValue = ((Number) obj).intValue();
                    HorizontalAlignmentLine horizontalAlignmentLine = AlignmentLineKt.FirstBaseline;
                    ResultKt.checkNotNullParameter(alignmentLine, "<this>");
                    roundToInt = ((Number) alignmentLine.merger.invoke(Integer.valueOf(intValue), Integer.valueOf(roundToInt))).intValue();
                }
                hashMap.put(alignmentLine, Integer.valueOf(roundToInt));
                return;
            }
            if (alignmentLines.getAlignmentLinesMap(nodeCoordinator).containsKey(alignmentLine)) {
                float positionFor = alignmentLines.getPositionFor(nodeCoordinator, alignmentLine);
                Offset = _BOUNDARY.Offset(positionFor, positionFor);
            }
        }
    }

    public abstract Map getAlignmentLinesMap(NodeCoordinator nodeCoordinator);

    public abstract int getPositionFor(NodeCoordinator nodeCoordinator, AlignmentLine alignmentLine);

    public final boolean getQueried$ui_release() {
        return this.usedDuringParentMeasurement || this.previousUsedDuringParentLayout || this.usedByModifierMeasurement || this.usedByModifierLayout;
    }

    public final boolean getRequired$ui_release() {
        recalculateQueryOwner();
        return this.queryOwner != null;
    }

    public final void onAlignmentsChanged() {
        this.dirty = true;
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        AlignmentLinesOwner parentAlignmentLinesOwner = alignmentLinesOwner.getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null) {
            return;
        }
        if (this.usedDuringParentMeasurement) {
            parentAlignmentLinesOwner.requestMeasure();
        } else if (this.previousUsedDuringParentLayout || this.usedDuringParentLayout) {
            parentAlignmentLinesOwner.requestLayout();
        }
        if (this.usedByModifierMeasurement) {
            alignmentLinesOwner.requestMeasure();
        }
        if (this.usedByModifierLayout) {
            alignmentLinesOwner.requestLayout();
        }
        parentAlignmentLinesOwner.getAlignmentLines().onAlignmentsChanged();
    }

    public final void recalculate() {
        HashMap hashMap = this.alignmentLineMap;
        hashMap.clear();
        AbstractMap$toString$1 abstractMap$toString$1 = new AbstractMap$toString$1(12, this);
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        alignmentLinesOwner.forEachChildAlignmentLinesOwner(abstractMap$toString$1);
        hashMap.putAll(getAlignmentLinesMap(alignmentLinesOwner.getInnerCoordinator()));
        this.dirty = false;
    }

    public final void recalculateQueryOwner() {
        LookaheadAlignmentLines alignmentLines;
        LookaheadAlignmentLines alignmentLines2;
        boolean queried$ui_release = getQueried$ui_release();
        AlignmentLinesOwner alignmentLinesOwner = this.alignmentLinesOwner;
        if (!queried$ui_release) {
            AlignmentLinesOwner parentAlignmentLinesOwner = alignmentLinesOwner.getParentAlignmentLinesOwner();
            if (parentAlignmentLinesOwner == null) {
                return;
            }
            alignmentLinesOwner = parentAlignmentLinesOwner.getAlignmentLines().queryOwner;
            if (alignmentLinesOwner == null || !alignmentLinesOwner.getAlignmentLines().getQueried$ui_release()) {
                AlignmentLinesOwner alignmentLinesOwner2 = this.queryOwner;
                if (alignmentLinesOwner2 == null || alignmentLinesOwner2.getAlignmentLines().getQueried$ui_release()) {
                    return;
                }
                AlignmentLinesOwner parentAlignmentLinesOwner2 = alignmentLinesOwner2.getParentAlignmentLinesOwner();
                if (parentAlignmentLinesOwner2 != null && (alignmentLines2 = parentAlignmentLinesOwner2.getAlignmentLines()) != null) {
                    alignmentLines2.recalculateQueryOwner();
                }
                AlignmentLinesOwner parentAlignmentLinesOwner3 = alignmentLinesOwner2.getParentAlignmentLinesOwner();
                alignmentLinesOwner = (parentAlignmentLinesOwner3 == null || (alignmentLines = parentAlignmentLinesOwner3.getAlignmentLines()) == null) ? null : alignmentLines.queryOwner;
            }
        }
        this.queryOwner = alignmentLinesOwner;
    }
}
