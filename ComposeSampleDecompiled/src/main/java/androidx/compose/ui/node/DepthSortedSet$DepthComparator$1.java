package androidx.compose.ui.node;

import androidx.compose.runtime.Invalidation;
import androidx.compose.ui.text.AnnotatedString;
import java.util.Comparator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DepthSortedSet$DepthComparator$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.runtime.ComposerImpl.doCompose(androidx.compose.runtime.snapshots.SnapshotWeakSet, androidx.compose.runtime.internal.ComposableLambdaImpl):void, androidx.compose.runtime.Latch.<init>(int):void, androidx.compose.ui.text.AnnotatedString.<init>(java.lang.String, java.util.List, java.util.List, java.util.List):void] */
    public /* synthetic */ DepthSortedSet$DepthComparator$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                LayoutNode layoutNode = (LayoutNode) obj;
                LayoutNode layoutNode2 = (LayoutNode) obj2;
                ResultKt.checkNotNullParameter(layoutNode, "l1");
                ResultKt.checkNotNullParameter(layoutNode2, "l2");
                int compare = ResultKt.compare(layoutNode.depth, layoutNode2.depth);
                return compare != 0 ? compare : ResultKt.compare(layoutNode.hashCode(), layoutNode2.hashCode());
            case 1:
                return ResultKt.compareValues(Integer.valueOf(((Invalidation) obj).location), Integer.valueOf(((Invalidation) obj2).location));
            default:
                return ResultKt.compareValues(Integer.valueOf(((AnnotatedString.Range) obj).start), Integer.valueOf(((AnnotatedString.Range) obj2).start));
        }
    }
}
