package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class NodeChainKt {
    public static final NodeChainKt$SentinelHead$1 SentinelHead;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.Modifier$Node, androidx.compose.ui.node.NodeChainKt$SentinelHead$1] */
    static {
        ?? node = new Modifier.Node();
        node.aggregateChildKindSet = -1;
        SentinelHead = node;
    }

    public static final int actionForModifiers(Modifier.Element element, Modifier.Element element2) {
        ResultKt.checkNotNullParameter(element, "prev");
        ResultKt.checkNotNullParameter(element2, "next");
        if (ResultKt.areEqual(element, element2)) {
            return 2;
        }
        return element.getClass() == element2.getClass() ? 1 : 0;
    }
}
