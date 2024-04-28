package androidx.compose.ui.graphics;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Region;
import androidx.compose.ui.geometry.Offset;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidCanvas implements Canvas {
    public android.graphics.Canvas internalCanvas = AndroidCanvas_androidKt.EmptyCanvas;

    public AndroidCanvas() {
        new Rect();
        new Rect();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: clipPath-mtrdD-E, reason: not valid java name */
    public final void mo87clipPathmtrdDE(Path path, int i) {
        ResultKt.checkNotNullParameter(path, "path");
        android.graphics.Canvas canvas = this.internalCanvas;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.clipPath(((AndroidPath) path).internalPath, i == 0 ? Region.Op.DIFFERENCE : Region.Op.INTERSECT);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: clipRect-N_I0leg, reason: not valid java name */
    public final void mo88clipRectN_I0leg(float f, float f2, float f3, float f4, int i) {
        this.internalCanvas.clipRect(f, f2, f3, f4, i == 0 ? Region.Op.DIFFERENCE : Region.Op.INTERSECT);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: concat-58bKbWc, reason: not valid java name */
    public final void mo89concat58bKbWc(float[] fArr) {
        int i = 0;
        while (i < 4) {
            int i2 = 0;
            while (i2 < 4) {
                if (fArr[(i * 4) + i2] != (i == i2 ? 1.0f : 0.0f)) {
                    Matrix matrix = new Matrix();
                    float f = fArr[2];
                    if (f == 0.0f) {
                        float f2 = fArr[6];
                        if (f2 == 0.0f && fArr[10] == 1.0f && fArr[14] == 0.0f) {
                            float f3 = fArr[8];
                            if (f3 == 0.0f && fArr[9] == 0.0f && fArr[11] == 0.0f) {
                                float f4 = fArr[0];
                                float f5 = fArr[1];
                                float f6 = fArr[3];
                                float f7 = fArr[4];
                                float f8 = fArr[5];
                                float f9 = fArr[7];
                                float f10 = fArr[12];
                                float f11 = fArr[13];
                                float f12 = fArr[15];
                                fArr[0] = f4;
                                fArr[1] = f7;
                                fArr[2] = f10;
                                fArr[3] = f5;
                                fArr[4] = f8;
                                fArr[5] = f11;
                                fArr[6] = f6;
                                fArr[7] = f9;
                                fArr[8] = f12;
                                matrix.setValues(fArr);
                                fArr[0] = f4;
                                fArr[1] = f5;
                                fArr[2] = f;
                                fArr[3] = f6;
                                fArr[4] = f7;
                                fArr[5] = f8;
                                fArr[6] = f2;
                                fArr[7] = f9;
                                fArr[8] = f3;
                                this.internalCanvas.concat(matrix);
                                return;
                            }
                        }
                    }
                    throw new IllegalArgumentException("Android does not support arbitrary transforms".toString());
                }
                i2++;
            }
            i++;
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void disableZ() {
        Brush.enableZ(this.internalCanvas, false);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: drawCircle-9KIMszo, reason: not valid java name */
    public final void mo90drawCircle9KIMszo(float f, long j, AndroidPaint androidPaint) {
        this.internalCanvas.drawCircle(Offset.m76getXimpl(j), Offset.m77getYimpl(j), f, androidPaint.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawPath(Path path, AndroidPaint androidPaint) {
        ResultKt.checkNotNullParameter(path, "path");
        android.graphics.Canvas canvas = this.internalCanvas;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.drawPath(((AndroidPath) path).internalPath, androidPaint.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawRect(float f, float f2, float f3, float f4, AndroidPaint androidPaint) {
        ResultKt.checkNotNullParameter(androidPaint, "paint");
        this.internalCanvas.drawRect(f, f2, f3, f4, androidPaint.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, AndroidPaint androidPaint) {
        this.internalCanvas.drawRoundRect(f, f2, f3, f4, f5, f6, androidPaint.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void enableZ() {
        Brush.enableZ(this.internalCanvas, true);
    }

    public final android.graphics.Canvas getInternalCanvas() {
        return this.internalCanvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void restore() {
        this.internalCanvas.restore();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void save() {
        this.internalCanvas.save();
    }

    public final void setInternalCanvas(android.graphics.Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "<set-?>");
        this.internalCanvas = canvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void translate(float f, float f2) {
        this.internalCanvas.translate(f, f2);
    }
}
