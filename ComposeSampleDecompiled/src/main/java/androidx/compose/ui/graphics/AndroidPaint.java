package androidx.compose.ui.graphics;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Build;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidPaint {
    public int _blendMode;
    public final Paint internalPaint;
    public Shader internalShader;

    public AndroidPaint(Paint paint) {
        ResultKt.checkNotNullParameter(paint, "internalPaint");
        this.internalPaint = paint;
        this._blendMode = 3;
    }

    public final void setAlpha(float f) {
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "<this>");
        paint.setAlpha((int) Math.rint(f * 255.0f));
    }

    /* renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m91setBlendModes9anfk8(int i) {
        if (Brush.m98equalsimpl0(this._blendMode, i)) {
            return;
        }
        this._blendMode = i;
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "$this$setNativeBlendMode");
        if (Build.VERSION.SDK_INT >= 29) {
            WrapperVerificationHelperMethods.INSTANCE.m130setBlendModeGB0RdKg(paint, i);
        } else {
            paint.setXfermode(new PorterDuffXfermode(Brush.m98equalsimpl0(i, 0) ? PorterDuff.Mode.CLEAR : Brush.m98equalsimpl0(i, 1) ? PorterDuff.Mode.SRC : Brush.m98equalsimpl0(i, 2) ? PorterDuff.Mode.DST : Brush.m98equalsimpl0(i, 3) ? PorterDuff.Mode.SRC_OVER : Brush.m98equalsimpl0(i, 4) ? PorterDuff.Mode.DST_OVER : Brush.m98equalsimpl0(i, 5) ? PorterDuff.Mode.SRC_IN : Brush.m98equalsimpl0(i, 6) ? PorterDuff.Mode.DST_IN : Brush.m98equalsimpl0(i, 7) ? PorterDuff.Mode.SRC_OUT : Brush.m98equalsimpl0(i, 8) ? PorterDuff.Mode.DST_OUT : Brush.m98equalsimpl0(i, 9) ? PorterDuff.Mode.SRC_ATOP : Brush.m98equalsimpl0(i, 10) ? PorterDuff.Mode.DST_ATOP : Brush.m98equalsimpl0(i, 11) ? PorterDuff.Mode.XOR : Brush.m98equalsimpl0(i, 12) ? PorterDuff.Mode.ADD : Brush.m98equalsimpl0(i, 14) ? PorterDuff.Mode.SCREEN : Brush.m98equalsimpl0(i, 15) ? PorterDuff.Mode.OVERLAY : Brush.m98equalsimpl0(i, 16) ? PorterDuff.Mode.DARKEN : Brush.m98equalsimpl0(i, 17) ? PorterDuff.Mode.LIGHTEN : Brush.m98equalsimpl0(i, 13) ? PorterDuff.Mode.MULTIPLY : PorterDuff.Mode.SRC_OVER));
        }
    }

    /* renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m92setColor8_81llA(long j) {
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "$this$setNativeColor");
        paint.setColor(Brush.m109toArgb8_81llA(j));
    }

    public final void setShader(Shader shader) {
        this.internalShader = shader;
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "<this>");
        paint.setShader(shader);
    }

    /* renamed from: setStrokeCap-BeK7IIE, reason: not valid java name */
    public final void m93setStrokeCapBeK7IIE(int i) {
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "$this$setNativeStrokeCap");
        paint.setStrokeCap(Brush.m100equalsimpl0$2(i, 2) ? Paint.Cap.SQUARE : Brush.m100equalsimpl0$2(i, 1) ? Paint.Cap.ROUND : Brush.m100equalsimpl0$2(i, 0) ? Paint.Cap.BUTT : Paint.Cap.BUTT);
    }

    /* renamed from: setStrokeJoin-Ww9F2mQ, reason: not valid java name */
    public final void m94setStrokeJoinWw9F2mQ(int i) {
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "$this$setNativeStrokeJoin");
        paint.setStrokeJoin(Brush.m101equalsimpl0$3(i, 0) ? Paint.Join.MITER : Brush.m101equalsimpl0$3(i, 2) ? Paint.Join.BEVEL : Brush.m101equalsimpl0$3(i, 1) ? Paint.Join.ROUND : Paint.Join.MITER);
    }

    /* renamed from: setStyle-k9PVt8s, reason: not valid java name */
    public final void m95setStylek9PVt8s(int i) {
        Paint paint = this.internalPaint;
        ResultKt.checkNotNullParameter(paint, "$this$setNativeStyle");
        paint.setStyle(i == 1 ? Paint.Style.STROKE : Paint.Style.FILL);
    }
}
