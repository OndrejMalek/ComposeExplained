package androidx.compose.material.ripple;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.runtime.snapshots.StateMapMutableIterator;
import androidx.compose.runtime.snapshots.StateMapMutableKeysIterator;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class CommonRippleIndicationInstance extends RippleIndicationInstance implements RememberObserver {
    public final boolean bounded;
    public final State color;
    public final float radius;
    public final State rippleAlpha;
    public final SnapshotStateMap ripples;

    public CommonRippleIndicationInstance(boolean z, float f, MutableState mutableState, MutableState mutableState2) {
        super(z, mutableState2);
        this.bounded = z;
        this.radius = f;
        this.color = mutableState;
        this.rippleAlpha = mutableState2;
        this.ripples = new SnapshotStateMap();
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public final void addRipple(PressInteraction$Press pressInteraction$Press, CoroutineScope coroutineScope) {
        ResultKt.checkNotNullParameter(pressInteraction$Press, "interaction");
        ResultKt.checkNotNullParameter(coroutineScope, "scope");
        SnapshotStateMap snapshotStateMap = this.ripples;
        Iterator it = snapshotStateMap.entries.iterator();
        while (it.hasNext()) {
            ((RippleAnimation) ((Map.Entry) it.next()).getValue()).finish();
        }
        boolean z = this.bounded;
        RippleAnimation rippleAnimation = new RippleAnimation(z ? new Offset(pressInteraction$Press.pressPosition) : null, this.radius, z);
        snapshotStateMap.put(pressInteraction$Press, rippleAnimation);
        ResultKt.launch$default(coroutineScope, null, 0, new CommonRippleIndicationInstance$addRipple$2(rippleAnimation, this, pressInteraction$Press, null), 3);
    }

    @Override // androidx.compose.foundation.IndicationInstance
    public final void drawIndication(ContentDrawScope contentDrawScope) {
        long Color;
        long j;
        long Color2;
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        ResultKt.checkNotNullParameter(contentDrawScope2, "<this>");
        long j2 = ((Color) this.color.getValue()).value;
        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope2;
        layoutNodeDrawScope.drawContent();
        m48drawStateLayerH2RKhps(contentDrawScope2, this.radius, j2);
        Object it = this.ripples.entries.iterator();
        while (((StateMapMutableIterator) it).hasNext()) {
            RippleAnimation rippleAnimation = (RippleAnimation) ((Map.Entry) ((StateMapMutableKeysIterator) it).next()).getValue();
            float f = ((RippleAlpha) this.rippleAlpha.getValue()).pressedAlpha;
            if (f == 0.0f) {
                j = j2;
            } else {
                Color = Brush.Color(Color.m126getRedimpl(j2), Color.m125getGreenimpl(j2), Color.m123getBlueimpl(j2), f, Color.m124getColorSpaceimpl(j2));
                rippleAnimation.getClass();
                Float f2 = rippleAnimation.startRadius;
                CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                if (f2 == null) {
                    long mo149getSizeNHjbRc = canvasDrawScope.mo149getSizeNHjbRc();
                    float f3 = RippleAnimationKt.BoundedRippleExtraRadius;
                    rippleAnimation.startRadius = Float.valueOf(Math.max(Size.m85getWidthimpl(mo149getSizeNHjbRc), Size.m83getHeightimpl(mo149getSizeNHjbRc)) * 0.3f);
                }
                Float f4 = rippleAnimation.targetRadius;
                boolean z = rippleAnimation.bounded;
                if (f4 == null) {
                    float f5 = rippleAnimation.radius;
                    rippleAnimation.targetRadius = Float.isNaN(f5) ? Float.valueOf(RippleAnimationKt.m44getRippleEndRadiuscSwnlzA(contentDrawScope2, z, canvasDrawScope.mo149getSizeNHjbRc())) : Float.valueOf(layoutNodeDrawScope.mo32toPx0680j_4(f5));
                }
                if (rippleAnimation.origin == null) {
                    rippleAnimation.origin = new Offset(canvasDrawScope.mo148getCenterF1C5BW0());
                }
                if (rippleAnimation.targetCenter == null) {
                    rippleAnimation.targetCenter = new Offset(_BOUNDARY.Offset(Size.m85getWidthimpl(canvasDrawScope.mo149getSizeNHjbRc()) / 2.0f, Size.m83getHeightimpl(canvasDrawScope.mo149getSizeNHjbRc()) / 2.0f));
                }
                float floatValue = (!((Boolean) rippleAnimation.finishRequested$delegate.getValue()).booleanValue() || ((Boolean) rippleAnimation.finishedFadingIn$delegate.getValue()).booleanValue()) ? ((Number) rippleAnimation.animatedAlpha.getValue()).floatValue() : 1.0f;
                Float f6 = rippleAnimation.startRadius;
                ResultKt.checkNotNull(f6);
                float floatValue2 = f6.floatValue();
                Float f7 = rippleAnimation.targetRadius;
                ResultKt.checkNotNull(f7);
                float floatValue3 = f7.floatValue();
                float floatValue4 = ((Number) rippleAnimation.animatedRadiusPercent.getValue()).floatValue();
                float f8 = 1;
                float f9 = (floatValue4 * floatValue3) + ((f8 - floatValue4) * floatValue2);
                Offset offset = rippleAnimation.origin;
                ResultKt.checkNotNull(offset);
                float m76getXimpl = Offset.m76getXimpl(offset.packedValue);
                Offset offset2 = rippleAnimation.targetCenter;
                ResultKt.checkNotNull(offset2);
                j = j2;
                float m76getXimpl2 = Offset.m76getXimpl(offset2.packedValue);
                Animatable animatable = rippleAnimation.animatedCenterPercent;
                float floatValue5 = ((Number) animatable.getValue()).floatValue();
                float f10 = (floatValue5 * m76getXimpl2) + ((f8 - floatValue5) * m76getXimpl);
                Offset offset3 = rippleAnimation.origin;
                ResultKt.checkNotNull(offset3);
                float m77getYimpl = Offset.m77getYimpl(offset3.packedValue);
                Offset offset4 = rippleAnimation.targetCenter;
                ResultKt.checkNotNull(offset4);
                float m77getYimpl2 = Offset.m77getYimpl(offset4.packedValue);
                float floatValue6 = ((Number) animatable.getValue()).floatValue();
                long Offset = _BOUNDARY.Offset(f10, (floatValue6 * m77getYimpl2) + ((f8 - floatValue6) * m77getYimpl));
                Color2 = Brush.Color(Color.m126getRedimpl(Color), Color.m125getGreenimpl(Color), Color.m123getBlueimpl(Color), Color.m122getAlphaimpl(Color) * floatValue, Color.m124getColorSpaceimpl(Color));
                if (z) {
                    float m85getWidthimpl = Size.m85getWidthimpl(canvasDrawScope.mo149getSizeNHjbRc());
                    float m83getHeightimpl = Size.m83getHeightimpl(canvasDrawScope.mo149getSizeNHjbRc());
                    CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                    long j3 = canvasDrawScope$drawContext$1.this$0.drawParams.size;
                    canvasDrawScope$drawContext$1.getCanvas().save();
                    canvasDrawScope$drawContext$1.transform.$this_asDrawTransform.getCanvas().mo88clipRectN_I0leg(0.0f, 0.0f, m85getWidthimpl, m83getHeightimpl, 1);
                    contentDrawScope.mo137drawCircleVaOC9Bg(Color2, f9, (r18 & 4) != 0 ? contentDrawScope.mo148getCenterF1C5BW0() : Offset, 1.0f, Fill.INSTANCE, null, 3);
                    canvasDrawScope$drawContext$1.getCanvas().restore();
                    canvasDrawScope$drawContext$1.this$0.drawParams.size = j3;
                } else {
                    contentDrawScope.mo137drawCircleVaOC9Bg(Color2, f9, (r18 & 4) != 0 ? contentDrawScope.mo148getCenterF1C5BW0() : Offset, 1.0f, Fill.INSTANCE, null, 3);
                }
            }
            contentDrawScope2 = contentDrawScope;
            j2 = j;
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
        this.ripples.clear();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        this.ripples.clear();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public final void removeRipple(PressInteraction$Press pressInteraction$Press) {
        ResultKt.checkNotNullParameter(pressInteraction$Press, "interaction");
        RippleAnimation rippleAnimation = (RippleAnimation) this.ripples.get(pressInteraction$Press);
        if (rippleAnimation != null) {
            rippleAnimation.finish();
        }
    }
}
