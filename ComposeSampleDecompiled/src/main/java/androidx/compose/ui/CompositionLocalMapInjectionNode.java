package androidx.compose.ui;

import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.Snake;

/* loaded from: classes.dex */
public final class CompositionLocalMapInjectionNode extends Modifier.Node {
    public CompositionLocalMap map;

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        Snake.requireLayoutNode(this).setCompositionLocalMap(this.map);
    }
}
