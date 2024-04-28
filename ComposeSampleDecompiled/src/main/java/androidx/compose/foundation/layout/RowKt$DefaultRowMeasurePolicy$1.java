package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.io.Serializable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class RowKt$DefaultRowMeasurePolicy$1 extends Lambda implements Function5 {
    public final /* synthetic */ int $r8$classId;
    public static final RowKt$DefaultRowMeasurePolicy$1 INSTANCE$1 = new RowKt$DefaultRowMeasurePolicy$1(1);
    public static final RowKt$DefaultRowMeasurePolicy$1 INSTANCE = new RowKt$DefaultRowMeasurePolicy$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RowKt$DefaultRowMeasurePolicy$1(int i) {
        super(5);
        this.$r8$classId = i;
    }

    public final void invoke(int i, Density density, LayoutDirection layoutDirection, int[] iArr, int[] iArr2) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(iArr, "size");
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                ResultKt.checkNotNullParameter(density, "density");
                Arrangement.Start.arrange(i, density, layoutDirection, iArr, iArr2);
                return;
            default:
                ResultKt.checkNotNullParameter(iArr, "size");
                ResultKt.checkNotNullParameter(layoutDirection, "<anonymous parameter 2>");
                ResultKt.checkNotNullParameter(density, "density");
                Arrangement.Top.arrange(density, i, iArr, iArr2);
                return;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r12v0, resolved type: java.io.Serializable */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function5
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Serializable serializable) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                Density density = (Density) obj4;
                invoke(((Number) obj).intValue(), density, (LayoutDirection) obj3, (int[]) obj2, (int[]) serializable);
                return unit;
            default:
                Density density2 = (Density) obj4;
                invoke(((Number) obj).intValue(), density2, (LayoutDirection) obj3, (int[]) obj2, (int[]) serializable);
                return unit;
        }
    }
}
