package androidx.compose.foundation.layout;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FillElement extends ModifierNodeElement {
    public final int direction;
    public final float fraction;

    public FillElement(int i, float f, String str) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "direction");
        this.direction = i;
        this.fraction = f;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.foundation.layout.FillNode, androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        int i = this.direction;
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "direction");
        ?? node = new Modifier.Node();
        node.direction = i;
        node.fraction = this.fraction;
        return node;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FillElement)) {
            return false;
        }
        FillElement fillElement = (FillElement) obj;
        return this.direction == fillElement.direction && this.fraction == fillElement.fraction;
    }

    public final int hashCode() {
        return Float.hashCode(this.fraction) + (AnimationEndReason$EnumUnboxingLocalUtility.ordinal(this.direction) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        FillNode fillNode = (FillNode) node;
        ResultKt.checkNotNullParameter(fillNode, "node");
        int i = this.direction;
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "<set-?>");
        fillNode.direction = i;
        fillNode.fraction = this.fraction;
    }
}
