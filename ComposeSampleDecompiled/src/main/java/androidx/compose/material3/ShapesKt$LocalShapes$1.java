package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokensKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ShapesKt$LocalShapes$1 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public static final ShapesKt$LocalShapes$1 INSTANCE$1 = new ShapesKt$LocalShapes$1(1);
    public static final ShapesKt$LocalShapes$1 INSTANCE$2 = new ShapesKt$LocalShapes$1(2);
    public static final ShapesKt$LocalShapes$1 INSTANCE$3 = new ShapesKt$LocalShapes$1(3);
    public static final ShapesKt$LocalShapes$1 INSTANCE = new ShapesKt$LocalShapes$1(0);
    public static final ShapesKt$LocalShapes$1 INSTANCE$4 = new ShapesKt$LocalShapes$1(4);
    public static final ShapesKt$LocalShapes$1 INSTANCE$5 = new ShapesKt$LocalShapes$1(5);
    public static final ShapesKt$LocalShapes$1 INSTANCE$6 = new ShapesKt$LocalShapes$1(6);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ShapesKt$LocalShapes$1(int i) {
        super(0);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return new Shapes();
            case 1:
                return ColorSchemeKt.m55lightColorSchemeG1PFcw$default(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 536870911);
            case 2:
                return new Color(Color.Black);
            case 3:
                return Boolean.TRUE;
            case 4:
                return new Dp(0);
            case 5:
                return TypographyTokensKt.DefaultTextStyle;
            default:
                return new Typography(null, 32767);
        }
    }
}
