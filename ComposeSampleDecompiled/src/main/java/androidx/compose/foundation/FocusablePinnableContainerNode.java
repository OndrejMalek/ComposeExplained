package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.NodeCoordinator$invoke$1;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.Snake;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class FocusablePinnableContainerNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, ObserverModifierNode {
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        ?? obj = new Object();
        Snake.observeReads(this, new NodeCoordinator$invoke$1(obj, 1, this));
        DurationKt$$ExternalSyntheticCheckNotZero0.m(obj.element);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onReset() {
    }
}
