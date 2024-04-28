package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.view.View;
import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CalculateMatrixToWindowApi21 implements CalculateMatrixToWindow {
    public final int[] tmpLocation = new int[2];
    public final float[] tmpMatrix = Brush.m97constructorimpl$default();

    @Override // androidx.compose.ui.platform.CalculateMatrixToWindow
    /* renamed from: calculateMatrixToWindow-EL8BTi8 */
    public final void mo225calculateMatrixToWindowEL8BTi8(View view, float[] fArr) {
        ResultKt.checkNotNullParameter(view, "view");
        ResultKt.checkNotNullParameter(fArr, "matrix");
        Brush.m106resetimpl(fArr);
        m227transformMatrixToWindowEL8BTi8(view, fArr);
    }

    /* renamed from: preTranslate-3XD1CNM, reason: not valid java name */
    public final void m226preTranslate3XD1CNM(float[] fArr, float f, float f2) {
        float[] fArr2 = this.tmpMatrix;
        Brush.m106resetimpl(fArr2);
        float f3 = (fArr2[8] * 0.0f) + (fArr2[4] * f2) + (fArr2[0] * f) + fArr2[12];
        float f4 = (fArr2[9] * 0.0f) + (fArr2[5] * f2) + (fArr2[1] * f) + fArr2[13];
        float f5 = (fArr2[10] * 0.0f) + (fArr2[6] * f2) + (fArr2[2] * f) + fArr2[14];
        float f6 = (fArr2[11] * 0.0f) + (fArr2[7] * f2) + (fArr2[3] * f) + fArr2[15];
        fArr2[12] = f3;
        fArr2[13] = f4;
        fArr2[14] = f5;
        fArr2[15] = f6;
        InvertMatrixKt.m229access$preTransformJiSxe2E(fArr, fArr2);
    }

    /* renamed from: transformMatrixToWindow-EL8BTi8, reason: not valid java name */
    public final void m227transformMatrixToWindowEL8BTi8(View view, float[] fArr) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            m227transformMatrixToWindowEL8BTi8((View) parent, fArr);
            m226preTranslate3XD1CNM(fArr, -view.getScrollX(), -view.getScrollY());
            m226preTranslate3XD1CNM(fArr, view.getLeft(), view.getTop());
        } else {
            view.getLocationInWindow(this.tmpLocation);
            m226preTranslate3XD1CNM(fArr, -view.getScrollX(), -view.getScrollY());
            m226preTranslate3XD1CNM(fArr, r0[0], r0[1]);
        }
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            return;
        }
        float[] fArr2 = this.tmpMatrix;
        Brush.m107setFromtUYjHk(matrix, fArr2);
        InvertMatrixKt.m229access$preTransformJiSxe2E(fArr, fArr2);
    }
}
