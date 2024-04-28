package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.platform.WrappedComposition$setContent$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class BlockGraphicsLayerModifier extends Modifier.Node implements LayoutModifierNode {
    public Function1 layerBlock;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(j);
        return MeasureScope.layout$default(measureScope, mo164measureBRTryo0.width, mo164measureBRTryo0.height, new WrappedComposition$setContent$1(mo164measureBRTryo0, 5, this));
    }

    public final String toString() {
        return "BlockGraphicsLayerModifier(block=" + this.layerBlock + ')';
    }
}
