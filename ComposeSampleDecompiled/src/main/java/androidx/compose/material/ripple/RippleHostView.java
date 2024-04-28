package androidx.compose.material.ripple;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.material.ripple.UnprojectedRipple;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class RippleHostView extends View {
    public static final int[] PressedState = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] RestingState = new int[0];
    public Boolean bounded;
    public Long lastRippleStateChangeTimeMillis;
    public Function0 onInvalidateRipple;
    public FullyDrawnReporter$$ExternalSyntheticLambda0 resetRippleRunnable;
    public UnprojectedRipple ripple;

    /* JADX DEBUG: Method not inlined, still used in: [androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0.run():void] */
    /* renamed from: $r8$lambda$kwnYusj11673lL3l--Z3fgj8B_w */
    public static /* synthetic */ void m45$r8$lambda$kwnYusj11673lL3lZ3fgj8B_w(RippleHostView rippleHostView) {
        setRippleState$lambda$2(rippleHostView);
    }

    private final void setRippleState(boolean z) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        Runnable runnable = this.resetRippleRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            runnable.run();
        }
        Long l = this.lastRippleStateChangeTimeMillis;
        long longValue = currentAnimationTimeMillis - (l != null ? l.longValue() : 0L);
        if (z || longValue >= 5) {
            int[] iArr = z ? PressedState : RestingState;
            UnprojectedRipple unprojectedRipple = this.ripple;
            if (unprojectedRipple != null) {
                unprojectedRipple.setState(iArr);
            }
        } else {
            FullyDrawnReporter$$ExternalSyntheticLambda0 fullyDrawnReporter$$ExternalSyntheticLambda0 = new FullyDrawnReporter$$ExternalSyntheticLambda0(2, this);
            this.resetRippleRunnable = fullyDrawnReporter$$ExternalSyntheticLambda0;
            postDelayed(fullyDrawnReporter$$ExternalSyntheticLambda0, 50L);
        }
        this.lastRippleStateChangeTimeMillis = Long.valueOf(currentAnimationTimeMillis);
    }

    public static final void setRippleState$lambda$2(RippleHostView rippleHostView) {
        ResultKt.checkNotNullParameter(rippleHostView, "this$0");
        UnprojectedRipple unprojectedRipple = rippleHostView.ripple;
        if (unprojectedRipple != null) {
            unprojectedRipple.setState(RestingState);
        }
        rippleHostView.resetRippleRunnable = null;
    }

    /* renamed from: addRipple-KOepWvA */
    public final void m46addRippleKOepWvA(PressInteraction$Press pressInteraction$Press, boolean z, long j, int i, long j2, float f, LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1) {
        ResultKt.checkNotNullParameter(pressInteraction$Press, "interaction");
        ResultKt.checkNotNullParameter(layoutNode$_foldedChildren$1, "onInvalidateRipple");
        if (this.ripple == null || !ResultKt.areEqual(Boolean.valueOf(z), this.bounded)) {
            UnprojectedRipple unprojectedRipple = new UnprojectedRipple(z);
            setBackground(unprojectedRipple);
            this.ripple = unprojectedRipple;
            this.bounded = Boolean.valueOf(z);
        }
        UnprojectedRipple unprojectedRipple2 = this.ripple;
        ResultKt.checkNotNull(unprojectedRipple2);
        this.onInvalidateRipple = layoutNode$_foldedChildren$1;
        m47updateRipplePropertiesbiQXAtU(j, i, j2, f);
        if (z) {
            long j3 = pressInteraction$Press.pressPosition;
            unprojectedRipple2.setHotspot(Offset.m76getXimpl(j3), Offset.m77getYimpl(j3));
        } else {
            unprojectedRipple2.setHotspot(unprojectedRipple2.getBounds().centerX(), unprojectedRipple2.getBounds().centerY());
        }
        setRippleState(true);
    }

    public final void disposeRipple() {
        this.onInvalidateRipple = null;
        FullyDrawnReporter$$ExternalSyntheticLambda0 fullyDrawnReporter$$ExternalSyntheticLambda0 = this.resetRippleRunnable;
        if (fullyDrawnReporter$$ExternalSyntheticLambda0 != null) {
            removeCallbacks(fullyDrawnReporter$$ExternalSyntheticLambda0);
            FullyDrawnReporter$$ExternalSyntheticLambda0 fullyDrawnReporter$$ExternalSyntheticLambda02 = this.resetRippleRunnable;
            ResultKt.checkNotNull(fullyDrawnReporter$$ExternalSyntheticLambda02);
            fullyDrawnReporter$$ExternalSyntheticLambda02.run();
        } else {
            UnprojectedRipple unprojectedRipple = this.ripple;
            if (unprojectedRipple != null) {
                unprojectedRipple.setState(RestingState);
            }
        }
        UnprojectedRipple unprojectedRipple2 = this.ripple;
        if (unprojectedRipple2 == null) {
            return;
        }
        unprojectedRipple2.setVisible(false, false);
        unscheduleDrawable(unprojectedRipple2);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        ResultKt.checkNotNullParameter(drawable, "who");
        Function0 function0 = this.onInvalidateRipple;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
    }

    public final void removeRipple() {
        setRippleState(false);
    }

    /* renamed from: updateRippleProperties-biQXAtU */
    public final void m47updateRipplePropertiesbiQXAtU(long j, int i, long j2, float f) {
        long Color;
        UnprojectedRipple unprojectedRipple = this.ripple;
        if (unprojectedRipple == null) {
            return;
        }
        Integer num = unprojectedRipple.rippleRadius;
        if (num == null || num.intValue() != i) {
            unprojectedRipple.rippleRadius = Integer.valueOf(i);
            UnprojectedRipple.MRadiusHelper.INSTANCE.setRadius(unprojectedRipple, i);
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        Color = Brush.Color(Color.m126getRedimpl(j2), Color.m125getGreenimpl(j2), Color.m123getBlueimpl(j2), f, Color.m124getColorSpaceimpl(j2));
        Color color = unprojectedRipple.rippleColor;
        if (color == null || !Color.m121equalsimpl0(color.value, Color)) {
            unprojectedRipple.rippleColor = new Color(Color);
            unprojectedRipple.setColor(ColorStateList.valueOf(Brush.m109toArgb8_81llA(Color)));
        }
        Rect rect = new Rect(0, 0, ResultKt.roundToInt(Size.m85getWidthimpl(j)), ResultKt.roundToInt(Size.m83getHeightimpl(j)));
        setLeft(rect.left);
        setTop(rect.top);
        setRight(rect.right);
        setBottom(rect.bottom);
        unprojectedRipple.setBounds(rect);
    }
}
