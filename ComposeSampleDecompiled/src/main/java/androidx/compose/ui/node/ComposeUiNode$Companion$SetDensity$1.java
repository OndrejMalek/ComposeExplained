package androidx.compose.ui.node;

import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposeUiNode$Companion$SetDensity$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE$1 = new ComposeUiNode$Companion$SetDensity$1(1);
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE = new ComposeUiNode$Companion$SetDensity$1(0);
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE$2 = new ComposeUiNode$Companion$SetDensity$1(2);
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE$3 = new ComposeUiNode$Companion$SetDensity$1(3);
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE$4 = new ComposeUiNode$Companion$SetDensity$1(4);
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE$5 = new ComposeUiNode$Companion$SetDensity$1(5);
    public static final ComposeUiNode$Companion$SetDensity$1 INSTANCE$6 = new ComposeUiNode$Companion$SetDensity$1(6);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposeUiNode$Companion$SetDensity$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                ComposeUiNode composeUiNode = (ComposeUiNode) obj;
                Density density = (Density) obj2;
                ResultKt.checkNotNullParameter(composeUiNode, "$this$null");
                ResultKt.checkNotNullParameter(density, "it");
                ((LayoutNode) composeUiNode).setDensity(density);
                return unit;
            case 1:
                ((Number) obj2).intValue();
                ResultKt.checkNotNullParameter((ComposeUiNode) obj, "$this$null");
                return unit;
            case 2:
                ComposeUiNode composeUiNode2 = (ComposeUiNode) obj;
                LayoutDirection layoutDirection = (LayoutDirection) obj2;
                ResultKt.checkNotNullParameter(composeUiNode2, "$this$null");
                ResultKt.checkNotNullParameter(layoutDirection, "it");
                LayoutNode layoutNode = (LayoutNode) composeUiNode2;
                if (layoutNode.layoutDirection != layoutDirection) {
                    layoutNode.layoutDirection = layoutDirection;
                    layoutNode.invalidateMeasurements$ui_release();
                    LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                    if (parent$ui_release != null) {
                        parent$ui_release.invalidateLayer$ui_release();
                    }
                    layoutNode.invalidateLayers$ui_release();
                }
                return unit;
            case 3:
                ComposeUiNode composeUiNode3 = (ComposeUiNode) obj;
                MeasurePolicy measurePolicy = (MeasurePolicy) obj2;
                ResultKt.checkNotNullParameter(composeUiNode3, "$this$null");
                ResultKt.checkNotNullParameter(measurePolicy, "it");
                ((LayoutNode) composeUiNode3).setMeasurePolicy(measurePolicy);
                return unit;
            case 4:
                ComposeUiNode composeUiNode4 = (ComposeUiNode) obj;
                Modifier modifier = (Modifier) obj2;
                ResultKt.checkNotNullParameter(composeUiNode4, "$this$null");
                ResultKt.checkNotNullParameter(modifier, "it");
                ((LayoutNode) composeUiNode4).setModifier(modifier);
                return unit;
            case 5:
                ComposeUiNode composeUiNode5 = (ComposeUiNode) obj;
                CompositionLocalMap compositionLocalMap = (CompositionLocalMap) obj2;
                ResultKt.checkNotNullParameter(composeUiNode5, "$this$null");
                ResultKt.checkNotNullParameter(compositionLocalMap, "it");
                ((LayoutNode) composeUiNode5).setCompositionLocalMap(compositionLocalMap);
                return unit;
            default:
                ComposeUiNode composeUiNode6 = (ComposeUiNode) obj;
                ViewConfiguration viewConfiguration = (ViewConfiguration) obj2;
                ResultKt.checkNotNullParameter(composeUiNode6, "$this$null");
                ResultKt.checkNotNullParameter(viewConfiguration, "it");
                ((LayoutNode) composeUiNode6).setViewConfiguration(viewConfiguration);
                return unit;
        }
    }
}
