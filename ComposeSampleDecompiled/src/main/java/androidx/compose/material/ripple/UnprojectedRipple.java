package androidx.compose.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.compose.ui.graphics.Color;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class UnprojectedRipple extends RippleDrawable {
    public final boolean bounded;
    public boolean projected;
    public Color rippleColor;
    public Integer rippleRadius;

    /* loaded from: classes.dex */
    public final class MRadiusHelper {
        public static final MRadiusHelper INSTANCE = new Object();

        public final void setRadius(RippleDrawable rippleDrawable, int i) {
            ResultKt.checkNotNullParameter(rippleDrawable, "ripple");
            rippleDrawable.setRadius(i);
        }
    }

    public UnprojectedRipple(boolean z) {
        super(ColorStateList.valueOf(-16777216), null, z ? new ColorDrawable(-1) : null);
        this.bounded = z;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.Drawable
    public final Rect getDirtyBounds() {
        if (!this.bounded) {
            this.projected = true;
        }
        Rect dirtyBounds = super.getDirtyBounds();
        ResultKt.checkNotNullExpressionValue(dirtyBounds, "super.getDirtyBounds()");
        this.projected = false;
        return dirtyBounds;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean isProjected() {
        return this.projected;
    }
}
