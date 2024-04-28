package androidx.compose.ui.graphics.colorspace;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final /* synthetic */ class Rgb$$ExternalSyntheticLambda0 implements DoubleFunction {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Rgb f$0;

    public /* synthetic */ Rgb$$ExternalSyntheticLambda0(Rgb rgb, int i) {
        this.$r8$classId = i;
        this.f$0 = rgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
    public final double invoke(double d) {
        int i = this.$r8$classId;
        Rgb rgb = this.f$0;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(rgb, "this$0");
                return ResultKt.coerceIn(rgb.oetfOrig.invoke(d), rgb.min, rgb.max);
            default:
                ResultKt.checkNotNullParameter(rgb, "this$0");
                return rgb.eotfOrig.invoke(ResultKt.coerceIn(d, rgb.min, rgb.max));
        }
    }
}
