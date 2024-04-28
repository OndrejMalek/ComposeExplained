package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.RoundRect;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Outline$Rounded extends Brush {
    public final RoundRect roundRect;
    public final AndroidPath roundRectPath;

    public Outline$Rounded(RoundRect roundRect) {
        AndroidPath androidPath;
        this.roundRect = roundRect;
        long j = roundRect.bottomLeftCornerRadius;
        float m72getXimpl = CornerRadius.m72getXimpl(j);
        long j2 = roundRect.bottomRightCornerRadius;
        float m72getXimpl2 = CornerRadius.m72getXimpl(j2);
        boolean z = false;
        long j3 = roundRect.topLeftCornerRadius;
        long j4 = roundRect.topRightCornerRadius;
        boolean z2 = m72getXimpl == m72getXimpl2 && CornerRadius.m72getXimpl(j2) == CornerRadius.m72getXimpl(j4) && CornerRadius.m72getXimpl(j4) == CornerRadius.m72getXimpl(j3);
        if (CornerRadius.m73getYimpl(j) == CornerRadius.m73getYimpl(j2) && CornerRadius.m73getYimpl(j2) == CornerRadius.m73getYimpl(j4) && CornerRadius.m73getYimpl(j4) == CornerRadius.m73getYimpl(j3)) {
            z = true;
        }
        if (z2 && z) {
            androidPath = null;
        } else {
            AndroidPath Path = Brush.Path();
            Path.addRoundRect(roundRect);
            androidPath = Path;
        }
        this.roundRectPath = androidPath;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Outline$Rounded) {
            return ResultKt.areEqual(this.roundRect, ((Outline$Rounded) obj).roundRect);
        }
        return false;
    }

    public final int hashCode() {
        return this.roundRect.hashCode();
    }
}
