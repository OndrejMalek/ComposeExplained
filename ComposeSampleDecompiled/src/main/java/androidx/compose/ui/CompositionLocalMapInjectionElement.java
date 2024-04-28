package androidx.compose.ui;

import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.Snake;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CompositionLocalMapInjectionElement extends ModifierNodeElement {
    public final CompositionLocalMap map;

    public CompositionLocalMapInjectionElement(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        ResultKt.checkNotNullParameter(persistentCompositionLocalMap, "map");
        this.map = persistentCompositionLocalMap;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.CompositionLocalMapInjectionNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        CompositionLocalMap compositionLocalMap = this.map;
        ResultKt.checkNotNullParameter(compositionLocalMap, "map");
        ?? node = new Modifier.Node();
        node.map = compositionLocalMap;
        return node;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof CompositionLocalMapInjectionElement) && ResultKt.areEqual(((CompositionLocalMapInjectionElement) obj).map, this.map);
    }

    public final int hashCode() {
        return this.map.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        CompositionLocalMapInjectionNode compositionLocalMapInjectionNode = (CompositionLocalMapInjectionNode) node;
        ResultKt.checkNotNullParameter(compositionLocalMapInjectionNode, "node");
        CompositionLocalMap compositionLocalMap = this.map;
        ResultKt.checkNotNullParameter(compositionLocalMap, "value");
        compositionLocalMapInjectionNode.map = compositionLocalMap;
        Snake.requireLayoutNode(compositionLocalMapInjectionNode).setCompositionLocalMap(compositionLocalMap);
    }
}
