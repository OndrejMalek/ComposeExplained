package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewParent;
import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CalculateMatrixToWindowApi29 implements CalculateMatrixToWindow {
    public final Matrix tmpMatrix = new Matrix();
    public final int[] tmpPosition = new int[2];

    @Override // androidx.compose.ui.platform.CalculateMatrixToWindow
    /* renamed from: calculateMatrixToWindow-EL8BTi8 */
    public void mo225calculateMatrixToWindowEL8BTi8(View view, float[] fArr) {
        ResultKt.checkNotNullParameter(view, "view");
        ResultKt.checkNotNullParameter(fArr, "matrix");
        Matrix matrix = this.tmpMatrix;
        matrix.reset();
        view.transformMatrixToGlobal(matrix);
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            view = parent;
            parent = view.getParent();
        }
        int[] iArr = this.tmpPosition;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        view.getLocationInWindow(iArr);
        matrix.postTranslate(iArr[0] - i, iArr[1] - i2);
        Brush.m107setFromtUYjHk(matrix, fArr);
    }
}
