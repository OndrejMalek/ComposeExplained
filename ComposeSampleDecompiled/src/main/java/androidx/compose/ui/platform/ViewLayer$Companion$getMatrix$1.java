package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ViewLayer$Companion$getMatrix$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public static final ViewLayer$Companion$getMatrix$1 INSTANCE$1 = new ViewLayer$Companion$getMatrix$1(1);
    public static final ViewLayer$Companion$getMatrix$1 INSTANCE$2 = new ViewLayer$Companion$getMatrix$1(2);
    public static final ViewLayer$Companion$getMatrix$1 INSTANCE = new ViewLayer$Companion$getMatrix$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewLayer$Companion$getMatrix$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                View view = (View) obj;
                Matrix matrix = (Matrix) obj2;
                ResultKt.checkNotNullParameter(view, "view");
                ResultKt.checkNotNullParameter(matrix, "matrix");
                matrix.set(view.getMatrix());
                return unit;
            case 1:
                Composer composer = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                    }
                }
                return unit;
            default:
                DeviceRenderNode deviceRenderNode = (DeviceRenderNode) obj;
                Matrix matrix2 = (Matrix) obj2;
                ResultKt.checkNotNullParameter(deviceRenderNode, "rn");
                ResultKt.checkNotNullParameter(matrix2, "matrix");
                deviceRenderNode.getMatrix(matrix2);
                return unit;
        }
    }
}
