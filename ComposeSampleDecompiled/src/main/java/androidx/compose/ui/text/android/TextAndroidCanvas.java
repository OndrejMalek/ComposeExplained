package androidx.compose.ui.text.android;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextAndroidCanvas extends Canvas {
    public Canvas nativeCanvas;

    @Override // android.graphics.Canvas
    public final boolean clipOutPath(Path path) {
        ResultKt.checkNotNullParameter(path, "path");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutPath(path);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(RectF rectF) {
        ResultKt.checkNotNullParameter(rectF, "rect");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(rectF);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipPath(Path path, Region.Op op) {
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(op, "op");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipPath(path, op);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(RectF rectF, Region.Op op) {
        ResultKt.checkNotNullParameter(rectF, "rect");
        ResultKt.checkNotNullParameter(op, "op");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rectF, op);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void concat(Matrix matrix) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.concat(matrix);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void disableZ() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.disableZ();
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawARGB(int i, int i2, int i3, int i4) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawARGB(i, i2, i3, i4);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawArc(RectF rectF, float f, float f2, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(rectF, "oval");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawArc(rectF, f, f2, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(bitmap, "bitmap");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmapMesh(Bitmap bitmap, int i, int i2, float[] fArr, int i3, int[] iArr, int i4, Paint paint) {
        ResultKt.checkNotNullParameter(bitmap, "bitmap");
        ResultKt.checkNotNullParameter(fArr, "verts");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmapMesh(bitmap, i, i2, fArr, i3, iArr, i4, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawCircle(float f, float f2, float f3, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawCircle(f, f2, f3, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(i);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawDoubleRoundRect(RectF rectF, float f, float f2, RectF rectF2, float f3, float f4, Paint paint) {
        ResultKt.checkNotNullParameter(rectF, "outer");
        ResultKt.checkNotNullParameter(rectF2, "inner");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawDoubleRoundRect(rectF, f, f2, rectF2, f3, f4, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawGlyphs(int[] iArr, int i, float[] fArr, int i2, int i3, Font font, Paint paint) {
        ResultKt.checkNotNullParameter(iArr, "glyphIds");
        ResultKt.checkNotNullParameter(fArr, "positions");
        ResultKt.checkNotNullParameter(font, "font");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawGlyphs(iArr, i, fArr, i2, i3, font, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLine(float f, float f2, float f3, float f4, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawLine(f, f2, f3, f4, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLines(float[] fArr, int i, int i2, Paint paint) {
        ResultKt.checkNotNullParameter(fArr, "pts");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawLines(fArr, i, i2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawOval(RectF rectF, Paint paint) {
        ResultKt.checkNotNullParameter(rectF, "oval");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawOval(rectF, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPaint(Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPaint(paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPatch(NinePatch ninePatch, Rect rect, Paint paint) {
        ResultKt.checkNotNullParameter(ninePatch, "patch");
        ResultKt.checkNotNullParameter(rect, "dst");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPatch(ninePatch, rect, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPath(Path path, Paint paint) {
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPath(path, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture) {
        ResultKt.checkNotNullParameter(picture, "picture");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPicture(picture);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoint(float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPoint(f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoints(float[] fArr, int i, int i2, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPoints(fArr, i, i2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPosText(char[] cArr, int i, int i2, float[] fArr, Paint paint) {
        ResultKt.checkNotNullParameter(cArr, "text");
        ResultKt.checkNotNullParameter(fArr, "pos");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPosText(cArr, i, i2, fArr, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRGB(int i, int i2, int i3) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRGB(i, i2, i3);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(RectF rectF, Paint paint) {
        ResultKt.checkNotNullParameter(rectF, "rect");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRect(rectF, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRenderNode(RenderNode renderNode) {
        ResultKt.checkNotNullParameter(renderNode, "renderNode");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRenderNode(renderNode);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRoundRect(RectF rectF, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(rectF, "rect");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRoundRect(rectF, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(char[] cArr, int i, int i2, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(cArr, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(cArr, i, i2, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextOnPath(char[] cArr, int i, int i2, Path path, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(cArr, "text");
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextOnPath(cArr, i, i2, path, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(char[] cArr, int i, int i2, int i3, int i4, float f, float f2, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(cArr, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextRun(cArr, i, i2, i3, i4, f, f2, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawVertices(Canvas.VertexMode vertexMode, int i, float[] fArr, int i2, float[] fArr2, int i3, int[] iArr, int i4, short[] sArr, int i5, int i6, Paint paint) {
        ResultKt.checkNotNullParameter(vertexMode, "mode");
        ResultKt.checkNotNullParameter(fArr, "verts");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawVertices(vertexMode, i, fArr, i2, fArr2, i3, iArr, i4, sArr, i5, i6, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void enableZ() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.enableZ();
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean getClipBounds(Rect rect) {
        ResultKt.checkNotNullParameter(rect, "bounds");
        Canvas canvas = this.nativeCanvas;
        if (canvas == null) {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
        boolean clipBounds = canvas.getClipBounds(rect);
        if (clipBounds) {
            rect.set(0, 0, rect.width(), Integer.MAX_VALUE);
        }
        return clipBounds;
    }

    @Override // android.graphics.Canvas
    public final int getDensity() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getDensity();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final DrawFilter getDrawFilter() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getDrawFilter();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getHeight() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getHeight();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void getMatrix(Matrix matrix) {
        ResultKt.checkNotNullParameter(matrix, "ctm");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.getMatrix(matrix);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final int getMaximumBitmapHeight() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getMaximumBitmapHeight();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getMaximumBitmapWidth() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getMaximumBitmapWidth();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getSaveCount() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getSaveCount();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getWidth() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.getWidth();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean isOpaque() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.isOpaque();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(RectF rectF, Canvas.EdgeType edgeType) {
        ResultKt.checkNotNullParameter(rectF, "rect");
        ResultKt.checkNotNullParameter(edgeType, "type");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(rectF, edgeType);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void restore() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.restore();
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void restoreToCount(int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.restoreToCount(i);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void rotate(float f) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.rotate(f);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final int save() {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.save();
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(RectF rectF, Paint paint, int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(rectF, paint, i);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(RectF rectF, int i, int i2) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(rectF, i, i2);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void scale(float f, float f2) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.scale(f, f2);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setBitmap(Bitmap bitmap) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setBitmap(bitmap);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setDensity(int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setDensity(i);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setDrawFilter(DrawFilter drawFilter) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setDrawFilter(drawFilter);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setMatrix(Matrix matrix) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.setMatrix(matrix);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void skew(float f, float f2) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.skew(f, f2);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void translate(float f, float f2) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.translate(f, f2);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(Rect rect) {
        ResultKt.checkNotNullParameter(rect, "rect");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(rect);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipPath(Path path) {
        ResultKt.checkNotNullParameter(path, "path");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipPath(path);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(Rect rect, Region.Op op) {
        ResultKt.checkNotNullParameter(rect, "rect");
        ResultKt.checkNotNullParameter(op, "op");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rect, op);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawArc(float f, float f2, float f3, float f4, float f5, float f6, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawArc(f, f2, f3, f4, f5, f6, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Rect rect, RectF rectF, Paint paint) {
        ResultKt.checkNotNullParameter(bitmap, "bitmap");
        ResultKt.checkNotNullParameter(rectF, "dst");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, rect, rectF, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(long j) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(j);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawDoubleRoundRect(RectF rectF, float[] fArr, RectF rectF2, float[] fArr2, Paint paint) {
        ResultKt.checkNotNullParameter(rectF, "outer");
        ResultKt.checkNotNullParameter(fArr, "outerRadii");
        ResultKt.checkNotNullParameter(rectF2, "inner");
        ResultKt.checkNotNullParameter(fArr2, "innerRadii");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawDoubleRoundRect(rectF, fArr, rectF2, fArr2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLines(float[] fArr, Paint paint) {
        ResultKt.checkNotNullParameter(fArr, "pts");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawLines(fArr, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawOval(float f, float f2, float f3, float f4, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawOval(f, f2, f3, f4, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPatch(NinePatch ninePatch, RectF rectF, Paint paint) {
        ResultKt.checkNotNullParameter(ninePatch, "patch");
        ResultKt.checkNotNullParameter(rectF, "dst");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPatch(ninePatch, rectF, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture, RectF rectF) {
        ResultKt.checkNotNullParameter(picture, "picture");
        ResultKt.checkNotNullParameter(rectF, "dst");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPicture(picture, rectF);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoints(float[] fArr, Paint paint) {
        ResultKt.checkNotNullParameter(fArr, "pts");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPoints(fArr, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPosText(String str, float[] fArr, Paint paint) {
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(fArr, "pos");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPosText(str, fArr, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(Rect rect, Paint paint) {
        ResultKt.checkNotNullParameter(rect, "r");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRect(rect, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRoundRect(f, f2, f3, f4, f5, f6, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(String str, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(str, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextOnPath(String str, Path path, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextOnPath(str, path, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(CharSequence charSequence, int i, int i2, int i3, int i4, float f, float f2, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextRun(charSequence, i, i2, i3, i4, f, f2, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(RectF rectF) {
        boolean quickReject;
        ResultKt.checkNotNullParameter(rectF, "rect");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            quickReject = canvas.quickReject(rectF);
            return quickReject;
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(RectF rectF, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(rectF, paint);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(RectF rectF, int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(rectF, i);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(float f, float f2, float f3, float f4) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(f, f2, f3, f4);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(RectF rectF) {
        ResultKt.checkNotNullParameter(rectF, "rect");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rectF);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Rect rect, Rect rect2, Paint paint) {
        ResultKt.checkNotNullParameter(bitmap, "bitmap");
        ResultKt.checkNotNullParameter(rect2, "dst");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, rect, rect2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i, PorterDuff.Mode mode) {
        ResultKt.checkNotNullParameter(mode, "mode");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(i, mode);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture, Rect rect) {
        ResultKt.checkNotNullParameter(picture, "picture");
        ResultKt.checkNotNullParameter(rect, "dst");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawPicture(picture, rect);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(float f, float f2, float f3, float f4, Paint paint) {
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawRect(f, f2, f3, f4, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(String str, int i, int i2, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(str, i, i2, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(MeasuredText measuredText, int i, int i2, int i3, int i4, float f, float f2, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(measuredText, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawTextRun(measuredText, i, i2, i3, i4, f, f2, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(Path path, Canvas.EdgeType edgeType) {
        ResultKt.checkNotNullParameter(path, "path");
        ResultKt.checkNotNullParameter(edgeType, "type");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(path, edgeType);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(float f, float f2, float f3, float f4, Paint paint, int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(f, f2, f3, f4, paint, i);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(float f, float f2, float f3, float f4, int i, int i2) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(f, f2, f3, f4, i, i2);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(int i, int i2, int i3, int i4) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipOutRect(i, i2, i3, i4);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(Rect rect) {
        ResultKt.checkNotNullParameter(rect, "rect");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(rect);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(int[] iArr, int i, int i2, float f, float f2, int i3, int i4, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(iArr, "colors");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(iArr, i, i2, f, f2, i3, i4, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i, BlendMode blendMode) {
        ResultKt.checkNotNullParameter(blendMode, "mode");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(i, blendMode);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(CharSequence charSequence, int i, int i2, float f, float f2, Paint paint) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(paint, "paint");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawText(charSequence, i, i2, f, f2, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(Path path) {
        boolean quickReject;
        ResultKt.checkNotNullParameter(path, "path");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            quickReject = canvas.quickReject(path);
            return quickReject;
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(float f, float f2, float f3, float f4, Paint paint) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayer(f, f2, f3, f4, paint);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(float f, float f2, float f3, float f4, int i) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.saveLayerAlpha(f, f2, f3, f4, i);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(float f, float f2, float f3, float f4, Region.Op op) {
        ResultKt.checkNotNullParameter(op, "op");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(f, f2, f3, f4, op);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z, Paint paint) {
        ResultKt.checkNotNullParameter(iArr, "colors");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(iArr, i, i2, i3, i4, i5, i6, z, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(long j, BlendMode blendMode) {
        ResultKt.checkNotNullParameter(blendMode, "mode");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawColor(j, blendMode);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(float f, float f2, float f3, float f4, Canvas.EdgeType edgeType) {
        ResultKt.checkNotNullParameter(edgeType, "type");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.quickReject(f, f2, f3, f4, edgeType);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(float f, float f2, float f3, float f4) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(f, f2, f3, f4);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        ResultKt.checkNotNullParameter(bitmap, "bitmap");
        ResultKt.checkNotNullParameter(matrix, "matrix");
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, matrix, paint);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(float f, float f2, float f3, float f4) {
        boolean quickReject;
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            quickReject = canvas.quickReject(f, f2, f3, f4);
            return quickReject;
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(int i, int i2, int i3, int i4) {
        Canvas canvas = this.nativeCanvas;
        if (canvas != null) {
            return canvas.clipRect(i, i2, i3, i4);
        }
        ResultKt.throwUninitializedPropertyAccessException("nativeCanvas");
        throw null;
    }
}
