package androidx.compose.ui.semantics;

import androidx.compose.ui.node.LayoutNode;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SemanticsNode$parent$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final SemanticsNode$parent$1 INSTANCE$1 = new SemanticsNode$parent$1(1);
    public static final SemanticsNode$parent$1 INSTANCE = new SemanticsNode$parent$1(0);
    public static final SemanticsNode$parent$1 INSTANCE$2 = new SemanticsNode$parent$1(2);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SemanticsNode$parent$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    public final Boolean invoke(LayoutNode layoutNode) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(layoutNode, "it");
                SemanticsConfiguration collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release();
                return Boolean.valueOf(collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.isMergingSemanticsOfDescendants);
            case 1:
                ResultKt.checkNotNullParameter(layoutNode, "it");
                SemanticsConfiguration collapsedSemantics$ui_release2 = layoutNode.getCollapsedSemantics$ui_release();
                return Boolean.valueOf(collapsedSemantics$ui_release2 != null && collapsedSemantics$ui_release2.isMergingSemanticsOfDescendants);
            default:
                ResultKt.checkNotNullParameter(layoutNode, "it");
                return Boolean.valueOf(layoutNode.nodes.m190hasH91voCI$ui_release(8));
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return invoke((LayoutNode) obj);
            case 1:
                return invoke((LayoutNode) obj);
            default:
                return invoke((LayoutNode) obj);
        }
    }
}
