package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt$shadow$2$1;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Snake;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BlockGraphicsLayerElement extends ModifierNodeElement {
    public final Function1 block;

    public BlockGraphicsLayerElement(ShadowKt$shadow$2$1 shadowKt$shadow$2$1) {
        this.block = shadowKt$shadow$2$1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.Modifier$Node, androidx.compose.ui.graphics.BlockGraphicsLayerModifier] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        Function1 function1 = this.block;
        ResultKt.checkNotNullParameter(function1, "layerBlock");
        ?? node = new Modifier.Node();
        node.layerBlock = function1;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BlockGraphicsLayerElement) && ResultKt.areEqual(this.block, ((BlockGraphicsLayerElement) obj).block);
    }

    public final int hashCode() {
        return this.block.hashCode();
    }

    public final String toString() {
        return "BlockGraphicsLayerElement(block=" + this.block + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        BlockGraphicsLayerModifier blockGraphicsLayerModifier = (BlockGraphicsLayerModifier) node;
        ResultKt.checkNotNullParameter(blockGraphicsLayerModifier, "node");
        Function1 function1 = this.block;
        ResultKt.checkNotNullParameter(function1, "<set-?>");
        blockGraphicsLayerModifier.layerBlock = function1;
        NodeCoordinator nodeCoordinator = Snake.m215requireCoordinator64DMado(blockGraphicsLayerModifier, 2).wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.updateLayerBlock(blockGraphicsLayerModifier.layerBlock, true);
        }
    }
}
