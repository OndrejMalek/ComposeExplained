package androidx.compose.material.ripple;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Density;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class RippleAnimationKt {
    public static final float BoundedRippleExtraRadius = 10;

    /* renamed from: getRippleEndRadius-cSwnlzA, reason: not valid java name */
    public static final float m44getRippleEndRadiuscSwnlzA(Density density, boolean z, long j) {
        ResultKt.checkNotNullParameter(density, "$this$getRippleEndRadius");
        long Offset = _BOUNDARY.Offset(Size.m85getWidthimpl(j), Size.m83getHeightimpl(j));
        float sqrt = ((float) Math.sqrt((Offset.m77getYimpl(Offset) * Offset.m77getYimpl(Offset)) + (Offset.m76getXimpl(Offset) * Offset.m76getXimpl(Offset)))) / 2.0f;
        return z ? sqrt + density.mo32toPx0680j_4(BoundedRippleExtraRadius) : sqrt;
    }
}
