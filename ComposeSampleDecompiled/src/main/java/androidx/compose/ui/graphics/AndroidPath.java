package androidx.compose.ui.graphics;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.RoundRect;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidPath implements Path {
    public final android.graphics.Path internalPath;
    public final float[] radii;
    public final RectF rectF;

    public AndroidPath(android.graphics.Path path) {
        ResultKt.checkNotNullParameter(path, "internalPath");
        this.internalPath = path;
        this.rectF = new RectF();
        this.radii = new float[8];
        new Matrix();
    }

    public final void addRoundRect(RoundRect roundRect) {
        ResultKt.checkNotNullParameter(roundRect, "roundRect");
        RectF rectF = this.rectF;
        rectF.set(roundRect.left, roundRect.top, roundRect.right, roundRect.bottom);
        long j = roundRect.topLeftCornerRadius;
        float m72getXimpl = CornerRadius.m72getXimpl(j);
        float[] fArr = this.radii;
        fArr[0] = m72getXimpl;
        fArr[1] = CornerRadius.m73getYimpl(j);
        long j2 = roundRect.topRightCornerRadius;
        fArr[2] = CornerRadius.m72getXimpl(j2);
        fArr[3] = CornerRadius.m73getYimpl(j2);
        long j3 = roundRect.bottomRightCornerRadius;
        fArr[4] = CornerRadius.m72getXimpl(j3);
        fArr[5] = CornerRadius.m73getYimpl(j3);
        long j4 = roundRect.bottomLeftCornerRadius;
        fArr[6] = CornerRadius.m72getXimpl(j4);
        fArr[7] = CornerRadius.m73getYimpl(j4);
        this.internalPath.addRoundRect(rectF, fArr, Path.Direction.CCW);
    }

    /* renamed from: op-N5in7k0, reason: not valid java name */
    public final boolean m96opN5in7k0(Path path, Path path2, int i) {
        Path.Op op = i == 0 ? Path.Op.DIFFERENCE : i == 1 ? Path.Op.INTERSECT : i == 4 ? Path.Op.REVERSE_DIFFERENCE : i == 2 ? Path.Op.UNION : Path.Op.XOR;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        AndroidPath androidPath = (AndroidPath) path;
        if (path2 instanceof AndroidPath) {
            return this.internalPath.op(androidPath.internalPath, ((AndroidPath) path2).internalPath, op);
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }
}
