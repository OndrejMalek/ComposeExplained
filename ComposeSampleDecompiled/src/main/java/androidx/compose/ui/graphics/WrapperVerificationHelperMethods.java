package androidx.compose.ui.graphics;

import android.graphics.Paint;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class WrapperVerificationHelperMethods {
    public static final WrapperVerificationHelperMethods INSTANCE = new Object();

    /* renamed from: setBlendMode-GB0RdKg, reason: not valid java name */
    public final void m130setBlendModeGB0RdKg(Paint paint, int i) {
        ResultKt.checkNotNullParameter(paint, "paint");
        paint.setBlendMode(Brush.m108toAndroidBlendModes9anfk8(i));
    }
}
