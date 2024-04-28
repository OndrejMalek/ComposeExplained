package androidx.compose.ui.node;

import java.util.Comparator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class OnPositionedDispatcher$Companion$DepthComparator implements Comparator {
    public static final OnPositionedDispatcher$Companion$DepthComparator INSTANCE = new Object();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        LayoutNode layoutNode = (LayoutNode) obj;
        LayoutNode layoutNode2 = (LayoutNode) obj2;
        ResultKt.checkNotNullParameter(layoutNode, "a");
        ResultKt.checkNotNullParameter(layoutNode2, "b");
        int compare = ResultKt.compare(layoutNode2.depth, layoutNode.depth);
        return compare != 0 ? compare : ResultKt.compare(layoutNode.hashCode(), layoutNode2.hashCode());
    }
}
