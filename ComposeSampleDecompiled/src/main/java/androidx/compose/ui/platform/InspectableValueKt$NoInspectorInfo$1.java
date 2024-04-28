package androidx.compose.ui.platform;

import android.content.res.Configuration;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNode;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class InspectableValueKt$NoInspectorInfo$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$1 = new InspectableValueKt$NoInspectorInfo$1(1);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$2 = new InspectableValueKt$NoInspectorInfo$1(2);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$3 = new InspectableValueKt$NoInspectorInfo$1(3);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$4 = new InspectableValueKt$NoInspectorInfo$1(4);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$5 = new InspectableValueKt$NoInspectorInfo$1(5);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$6 = new InspectableValueKt$NoInspectorInfo$1(6);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$7 = new InspectableValueKt$NoInspectorInfo$1(7);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$8 = new InspectableValueKt$NoInspectorInfo$1(8);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$9 = new InspectableValueKt$NoInspectorInfo$1(9);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$10 = new InspectableValueKt$NoInspectorInfo$1(10);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$11 = new InspectableValueKt$NoInspectorInfo$1(11);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$12 = new InspectableValueKt$NoInspectorInfo$1(12);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$13 = new InspectableValueKt$NoInspectorInfo$1(13);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$14 = new InspectableValueKt$NoInspectorInfo$1(14);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$15 = new InspectableValueKt$NoInspectorInfo$1(15);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE$16 = new InspectableValueKt$NoInspectorInfo$1(16);
    public static final InspectableValueKt$NoInspectorInfo$1 INSTANCE = new InspectableValueKt$NoInspectorInfo$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ InspectableValueKt$NoInspectorInfo$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    public final Boolean invoke(LayoutNode layoutNode) {
        boolean z = false;
        switch (this.$r8$classId) {
            case 11:
                ResultKt.checkNotNullParameter(layoutNode, "it");
                SemanticsConfiguration collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release();
                if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.isMergingSemanticsOfDescendants) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 12:
                ResultKt.checkNotNullParameter(layoutNode, "it");
                return Boolean.valueOf(layoutNode.nodes.m190hasH91voCI$ui_release(8));
            default:
                ResultKt.checkNotNullParameter(layoutNode, "it");
                SemanticsConfiguration collapsedSemantics$ui_release2 = layoutNode.getCollapsedSemantics$ui_release();
                if (collapsedSemantics$ui_release2 != null && collapsedSemantics$ui_release2.isMergingSemanticsOfDescendants && collapsedSemantics$ui_release2.contains(SemanticsActions.SetText)) {
                    z = true;
                }
                return Boolean.valueOf(z);
        }
    }

    public final Float invoke(SemanticsNode semanticsNode) {
        switch (this.$r8$classId) {
            case 3:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().right);
            case 4:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().top);
            case 5:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().bottom);
            case 6:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().left);
            case 7:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().left);
            case 8:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().top);
            case 9:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().bottom);
            default:
                ResultKt.checkNotNullParameter(semanticsNode, "it");
                return Float.valueOf(semanticsNode.getBoundsInWindow().right);
        }
    }

    public final Float invoke(Pair pair) {
        switch (this.$r8$classId) {
            case 13:
                ResultKt.checkNotNullParameter(pair, "it");
                return Float.valueOf(((Rect) pair.first).top);
            default:
                ResultKt.checkNotNullParameter(pair, "it");
                return Float.valueOf(((Rect) pair.first).bottom);
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                ResultKt.checkNotNullParameter(null, "$this$null");
                return unit;
            case 1:
                ResultKt.checkNotNullParameter((Configuration) obj, "it");
                return unit;
            case 2:
                ResultKt.checkNotNullParameter((RotaryScrollEvent) obj, "it");
                return Boolean.FALSE;
            case 3:
                return invoke((SemanticsNode) obj);
            case 4:
                return invoke((SemanticsNode) obj);
            case 5:
                return invoke((SemanticsNode) obj);
            case 6:
                return invoke((SemanticsNode) obj);
            case 7:
                return invoke((SemanticsNode) obj);
            case 8:
                return invoke((SemanticsNode) obj);
            case 9:
                return invoke((SemanticsNode) obj);
            case 10:
                return invoke((SemanticsNode) obj);
            case 11:
                return invoke((LayoutNode) obj);
            case 12:
                return invoke((LayoutNode) obj);
            case 13:
                return invoke((Pair) obj);
            case 14:
                return invoke((Pair) obj);
            case 15:
                return invoke((LayoutNode) obj);
            default:
                ResultKt.checkNotNullParameter(obj, "it");
                return Boolean.valueOf(InvertMatrixKt.canBeSavedToBundle(obj));
        }
    }
}
