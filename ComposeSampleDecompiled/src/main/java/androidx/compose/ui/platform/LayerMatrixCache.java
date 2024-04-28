package androidx.compose.ui.platform;

import android.graphics.Matrix;
import androidx.compose.ui.graphics.Brush;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class LayerMatrixCache {
    public Matrix androidMatrixCache;
    public final Function2 getMatrix;
    public float[] inverseMatrixCache;
    public boolean isDirty = true;
    public boolean isInverseDirty = true;
    public boolean isInverseValid = true;
    public float[] matrixCache;
    public Matrix previousAndroidMatrix;

    public LayerMatrixCache(ViewLayer$Companion$getMatrix$1 viewLayer$Companion$getMatrix$1) {
        this.getMatrix = viewLayer$Companion$getMatrix$1;
    }

    /* renamed from: calculateInverseMatrix-bWbORWo, reason: not valid java name */
    public final float[] m234calculateInverseMatrixbWbORWo(Object obj) {
        float[] fArr = this.inverseMatrixCache;
        if (fArr == null) {
            fArr = Brush.m97constructorimpl$default();
            this.inverseMatrixCache = fArr;
        }
        if (this.isInverseDirty) {
            this.isInverseValid = InvertMatrixKt.m232invertToJiSxe2E(m235calculateMatrixGrdbGEg(obj), fArr);
            this.isInverseDirty = false;
        }
        if (this.isInverseValid) {
            return fArr;
        }
        return null;
    }

    /* renamed from: calculateMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m235calculateMatrixGrdbGEg(Object obj) {
        float[] fArr = this.matrixCache;
        if (fArr == null) {
            fArr = Brush.m97constructorimpl$default();
            this.matrixCache = fArr;
        }
        if (!this.isDirty) {
            return fArr;
        }
        Matrix matrix = this.androidMatrixCache;
        if (matrix == null) {
            matrix = new Matrix();
            this.androidMatrixCache = matrix;
        }
        this.getMatrix.invoke(obj, matrix);
        Matrix matrix2 = this.previousAndroidMatrix;
        if (matrix2 == null || !ResultKt.areEqual(matrix, matrix2)) {
            Brush.m107setFromtUYjHk(matrix, fArr);
            this.androidMatrixCache = matrix2;
            this.previousAndroidMatrix = matrix;
        }
        this.isDirty = false;
        return fArr;
    }

    public final void invalidate() {
        this.isDirty = true;
        this.isInverseDirty = true;
    }
}
