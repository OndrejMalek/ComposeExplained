package androidx.compose.ui.graphics.colorspace;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class Rgb$eotf$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Rgb this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Rgb$eotf$1(Rgb rgb, int i) {
        super(1);
        this.$r8$classId = i;
        this.this$0 = rgb;
    }

    public final Double invoke(double d) {
        int i = this.$r8$classId;
        Rgb rgb = this.this$0;
        switch (i) {
            case 0:
                return Double.valueOf(rgb.eotfOrig.invoke(ResultKt.coerceIn(d, rgb.min, rgb.max)));
            default:
                return Double.valueOf(ResultKt.coerceIn(rgb.oetfOrig.invoke(d), rgb.min, rgb.max));
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return invoke(((Number) obj).doubleValue());
            default:
                return invoke(((Number) obj).doubleValue());
        }
    }
}
