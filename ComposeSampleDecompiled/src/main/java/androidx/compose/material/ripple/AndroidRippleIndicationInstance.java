package androidx.compose.material.ripple;

import android.content.Context;
import android.view.View;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.WeakCache;
import java.util.ArrayList;
import java.util.Map;
import kotlin.ResultKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class AndroidRippleIndicationInstance extends RippleIndicationInstance implements RememberObserver {
    public final boolean bounded;
    public final State color;
    public final ParcelableSnapshotMutableState invalidateTick$delegate;
    public final LayoutNode$_foldedChildren$1 onInvalidateRipple;
    public final float radius;
    public final State rippleAlpha;
    public final RippleContainer rippleContainer;
    public final ParcelableSnapshotMutableState rippleHostView$delegate;
    public int rippleRadius;
    public long rippleSize;

    public AndroidRippleIndicationInstance(boolean z, float f, MutableState mutableState, MutableState mutableState2, RippleContainer rippleContainer) {
        super(z, mutableState2);
        this.bounded = z;
        this.radius = f;
        this.color = mutableState;
        this.rippleAlpha = mutableState2;
        this.rippleContainer = rippleContainer;
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this.rippleHostView$delegate = ResultKt.mutableStateOf(null, structuralEqualityPolicy);
        this.invalidateTick$delegate = ResultKt.mutableStateOf(Boolean.TRUE, structuralEqualityPolicy);
        this.rippleSize = Size.Zero;
        this.rippleRadius = -1;
        this.onInvalidateRipple = new LayoutNode$_foldedChildren$1(4, this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: androidx.compose.material.ripple.RippleHostView */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public final void addRipple(PressInteraction$Press pressInteraction$Press, CoroutineScope coroutineScope) {
        View view;
        ResultKt.checkNotNullParameter(pressInteraction$Press, "interaction");
        ResultKt.checkNotNullParameter(coroutineScope, "scope");
        RippleContainer rippleContainer = this.rippleContainer;
        rippleContainer.getClass();
        WeakCache weakCache = rippleContainer.rippleHostMap;
        weakCache.getClass();
        Object obj = weakCache.values;
        View view2 = (RippleHostView) ((Map) obj).get(this);
        if (view2 == null) {
            ArrayList arrayList = rippleContainer.unusedRippleHosts;
            ResultKt.checkNotNullParameter(arrayList, "<this>");
            view2 = (RippleHostView) (arrayList.isEmpty() ? null : arrayList.remove(0));
            Object obj2 = weakCache.referenceQueue;
            if (view2 == null) {
                int i = rippleContainer.nextHostIndex;
                ArrayList arrayList2 = rippleContainer.rippleHosts;
                if (i > ResultKt.getLastIndex(arrayList2)) {
                    Context context = rippleContainer.getContext();
                    ResultKt.checkNotNullExpressionValue(context, "context");
                    view = new View(context);
                    rippleContainer.addView(view);
                    arrayList2.add(view);
                } else {
                    RippleHostView rippleHostView = (RippleHostView) arrayList2.get(rippleContainer.nextHostIndex);
                    ResultKt.checkNotNullParameter(rippleHostView, "rippleHostView");
                    AndroidRippleIndicationInstance androidRippleIndicationInstance = (AndroidRippleIndicationInstance) ((Map) obj2).get(rippleHostView);
                    if (androidRippleIndicationInstance != null) {
                        androidRippleIndicationInstance.rippleHostView$delegate.setValue(null);
                        weakCache.remove(androidRippleIndicationInstance);
                        rippleHostView.disposeRipple();
                    }
                    view = rippleHostView;
                }
                int i2 = rippleContainer.nextHostIndex;
                if (i2 < rippleContainer.MaxRippleHosts - 1) {
                    rippleContainer.nextHostIndex = i2 + 1;
                } else {
                    rippleContainer.nextHostIndex = 0;
                }
                view2 = view;
            }
            ((Map) obj).put(this, view2);
            ((Map) obj2).put(view2, this);
        }
        RippleHostView rippleHostView2 = view2;
        rippleHostView2.m46addRippleKOepWvA(pressInteraction$Press, this.bounded, this.rippleSize, this.rippleRadius, ((Color) this.color.getValue()).value, ((RippleAlpha) this.rippleAlpha.getValue()).pressedAlpha, this.onInvalidateRipple);
        this.rippleHostView$delegate.setValue(rippleHostView2);
    }

    public final void dispose() {
        RippleContainer rippleContainer = this.rippleContainer;
        rippleContainer.getClass();
        this.rippleHostView$delegate.setValue(null);
        WeakCache weakCache = rippleContainer.rippleHostMap;
        weakCache.getClass();
        RippleHostView rippleHostView = (RippleHostView) ((Map) weakCache.values).get(this);
        if (rippleHostView != null) {
            rippleHostView.disposeRipple();
            weakCache.remove(this);
            rippleContainer.unusedRippleHosts.add(rippleHostView);
        }
    }

    @Override // androidx.compose.foundation.IndicationInstance
    public final void drawIndication(ContentDrawScope contentDrawScope) {
        int mo180roundToPx0680j_4;
        ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
        LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        this.rippleSize = canvasDrawScope.mo149getSizeNHjbRc();
        float f = this.radius;
        if (Float.isNaN(f)) {
            mo180roundToPx0680j_4 = ResultKt.roundToInt(RippleAnimationKt.m44getRippleEndRadiuscSwnlzA(contentDrawScope, this.bounded, canvasDrawScope.mo149getSizeNHjbRc()));
        } else {
            mo180roundToPx0680j_4 = canvasDrawScope.mo180roundToPx0680j_4(f);
        }
        this.rippleRadius = mo180roundToPx0680j_4;
        long j = ((Color) this.color.getValue()).value;
        float f2 = ((RippleAlpha) this.rippleAlpha.getValue()).pressedAlpha;
        layoutNodeDrawScope.drawContent();
        m48drawStateLayerH2RKhps(contentDrawScope, f, j);
        Canvas canvas = canvasDrawScope.drawContext.getCanvas();
        ((Boolean) this.invalidateTick$delegate.getValue()).booleanValue();
        RippleHostView rippleHostView = (RippleHostView) this.rippleHostView$delegate.getValue();
        if (rippleHostView != null) {
            rippleHostView.m47updateRipplePropertiesbiQXAtU(canvasDrawScope.mo149getSizeNHjbRc(), this.rippleRadius, j, f2);
            android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
            ResultKt.checkNotNullParameter(canvas, "<this>");
            rippleHostView.draw(((AndroidCanvas) canvas).internalCanvas);
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
        dispose();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        dispose();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public final void removeRipple(PressInteraction$Press pressInteraction$Press) {
        ResultKt.checkNotNullParameter(pressInteraction$Press, "interaction");
        RippleHostView rippleHostView = (RippleHostView) this.rippleHostView$delegate.getValue();
        if (rippleHostView != null) {
            rippleHostView.removeRipple();
        }
    }
}
