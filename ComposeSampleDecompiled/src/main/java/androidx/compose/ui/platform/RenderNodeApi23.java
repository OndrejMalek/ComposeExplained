package androidx.compose.ui.platform;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.view.DisplayListCanvas;
import android.view.RenderNode;
import androidx.compose.runtime.Stack;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Path;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class RenderNodeApi23 implements DeviceRenderNode {
    public static boolean needToValidateAccess = true;
    public int bottom;
    public boolean clipToBounds;
    public int left;
    public final RenderNode renderNode;
    public int right;
    public int top;

    public RenderNodeApi23(AndroidComposeView androidComposeView) {
        RenderNode create = RenderNode.create("Compose", androidComposeView);
        ResultKt.checkNotNullExpressionValue(create, "create(\"Compose\", ownerView)");
        this.renderNode = create;
        if (needToValidateAccess) {
            create.setScaleX(create.getScaleX());
            create.setScaleY(create.getScaleY());
            create.setTranslationX(create.getTranslationX());
            create.setTranslationY(create.getTranslationY());
            create.setElevation(create.getElevation());
            create.setRotation(create.getRotation());
            create.setRotationX(create.getRotationX());
            create.setRotationY(create.getRotationY());
            create.setCameraDistance(create.getCameraDistance());
            create.setPivotX(create.getPivotX());
            create.setPivotY(create.getPivotY());
            create.setClipToOutline(create.getClipToOutline());
            create.setClipToBounds(false);
            create.setAlpha(create.getAlpha());
            create.isValid();
            create.setLeftTopRightBottom(0, 0, 0, 0);
            create.offsetLeftAndRight(0);
            create.offsetTopAndBottom(0);
            RenderNodeVerificationHelper28 renderNodeVerificationHelper28 = RenderNodeVerificationHelper28.INSTANCE;
            renderNodeVerificationHelper28.setAmbientShadowColor(create, renderNodeVerificationHelper28.getAmbientShadowColor(create));
            renderNodeVerificationHelper28.setSpotShadowColor(create, renderNodeVerificationHelper28.getSpotShadowColor(create));
            RenderNodeVerificationHelper24.INSTANCE.discardDisplayList(create);
            create.setLayerType(0);
            create.setHasOverlappingRendering(create.hasOverlappingRendering());
            needToValidateAccess = false;
        }
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void discardDisplayList() {
        RenderNodeVerificationHelper24.INSTANCE.discardDisplayList(this.renderNode);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void drawInto(Canvas canvas) {
        ((DisplayListCanvas) canvas).drawRenderNode(this.renderNode);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final float getAlpha() {
        return this.renderNode.getAlpha();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final int getBottom() {
        return this.bottom;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final boolean getClipToBounds() {
        return this.clipToBounds;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final boolean getClipToOutline() {
        return this.renderNode.getClipToOutline();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final float getElevation() {
        return this.renderNode.getElevation();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final boolean getHasDisplayList() {
        return this.renderNode.isValid();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final int getHeight() {
        return this.bottom - this.top;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final int getLeft() {
        return this.left;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void getMatrix(Matrix matrix) {
        ResultKt.checkNotNullParameter(matrix, "matrix");
        this.renderNode.getMatrix(matrix);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final int getRight() {
        return this.right;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final int getTop() {
        return this.top;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final int getWidth() {
        return this.right - this.left;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void offsetLeftAndRight(int i) {
        this.left += i;
        this.right += i;
        this.renderNode.offsetLeftAndRight(i);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void offsetTopAndBottom(int i) {
        this.top += i;
        this.bottom += i;
        this.renderNode.offsetTopAndBottom(i);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void record(Stack stack, Path path, Function1 function1) {
        ResultKt.checkNotNullParameter(stack, "canvasHolder");
        int width = getWidth();
        int height = getHeight();
        RenderNode renderNode = this.renderNode;
        DisplayListCanvas start = renderNode.start(width, height);
        ResultKt.checkNotNullExpressionValue(start, "renderNode.start(width, height)");
        Canvas internalCanvas = stack.getAndroidCanvas().getInternalCanvas();
        stack.getAndroidCanvas().setInternalCanvas((Canvas) start);
        AndroidCanvas androidCanvas = stack.getAndroidCanvas();
        if (path != null) {
            androidCanvas.save();
            androidCanvas.mo87clipPathmtrdDE(path, 1);
        }
        function1.invoke(androidCanvas);
        if (path != null) {
            androidCanvas.restore();
        }
        stack.getAndroidCanvas().setInternalCanvas(internalCanvas);
        renderNode.end(start);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setAlpha(float f) {
        this.renderNode.setAlpha(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setAmbientShadowColor(int i) {
        RenderNodeVerificationHelper28.INSTANCE.setAmbientShadowColor(this.renderNode, i);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setCameraDistance(float f) {
        this.renderNode.setCameraDistance(-f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setClipToBounds(boolean z) {
        this.clipToBounds = z;
        this.renderNode.setClipToBounds(z);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setClipToOutline(boolean z) {
        this.renderNode.setClipToOutline(z);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    /* renamed from: setCompositingStrategy-aDBOjCE */
    public final void mo228setCompositingStrategyaDBOjCE(int i) {
        boolean m99equalsimpl0$1 = Brush.m99equalsimpl0$1(i, 1);
        RenderNode renderNode = this.renderNode;
        if (m99equalsimpl0$1) {
            renderNode.setLayerType(2);
            renderNode.setHasOverlappingRendering(true);
        } else if (Brush.m99equalsimpl0$1(i, 2)) {
            renderNode.setLayerType(0);
            renderNode.setHasOverlappingRendering(false);
        } else {
            renderNode.setLayerType(0);
            renderNode.setHasOverlappingRendering(true);
        }
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setElevation(float f) {
        this.renderNode.setElevation(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final boolean setHasOverlappingRendering() {
        return this.renderNode.setHasOverlappingRendering(true);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setOutline(Outline outline) {
        this.renderNode.setOutline(outline);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setPivotX(float f) {
        this.renderNode.setPivotX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setPivotY(float f) {
        this.renderNode.setPivotY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final boolean setPosition(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        return this.renderNode.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setRenderEffect() {
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setRotationX(float f) {
        this.renderNode.setRotationX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setRotationY(float f) {
        this.renderNode.setRotationY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setRotationZ(float f) {
        this.renderNode.setRotation(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setScaleX(float f) {
        this.renderNode.setScaleX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setScaleY(float f) {
        this.renderNode.setScaleY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setSpotShadowColor(int i) {
        RenderNodeVerificationHelper28.INSTANCE.setSpotShadowColor(this.renderNode, i);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setTranslationX(float f) {
        this.renderNode.setTranslationX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public final void setTranslationY(float f) {
        this.renderNode.setTranslationY(f);
    }
}
