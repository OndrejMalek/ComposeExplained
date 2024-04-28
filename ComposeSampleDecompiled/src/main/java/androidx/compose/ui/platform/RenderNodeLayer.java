package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.os.Build;
import androidx.compose.runtime.Stack;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class RenderNodeLayer implements OwnedLayer {
    public final Stack canvasHolder;
    public Function1 drawBlock;
    public boolean drawnWithZ;
    public Function0 invalidateParentLayer;
    public boolean isDestroyed;
    public boolean isDirty;
    public final LayerMatrixCache matrixCache;
    public final OutlineResolver outlineResolver;
    public final AndroidComposeView ownerView;
    public final DeviceRenderNode renderNode;
    public AndroidPaint softwareLayerPaint;
    public long transformOrigin;

    public RenderNodeLayer(AndroidComposeView androidComposeView, Function1 function1, LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1) {
        ResultKt.checkNotNullParameter(function1, "drawBlock");
        this.ownerView = androidComposeView;
        this.drawBlock = function1;
        this.invalidateParentLayer = layoutNode$_foldedChildren$1;
        this.outlineResolver = new OutlineResolver(androidComposeView.getDensity());
        this.matrixCache = new LayerMatrixCache(ViewLayer$Companion$getMatrix$1.INSTANCE$2);
        this.canvasHolder = new Stack(5);
        this.transformOrigin = TransformOrigin.Center;
        DeviceRenderNode renderNodeApi29 = Build.VERSION.SDK_INT >= 29 ? new RenderNodeApi29(androidComposeView) : new RenderNodeApi23(androidComposeView);
        renderNodeApi29.setHasOverlappingRendering();
        this.renderNode = renderNodeApi29;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        DeviceRenderNode deviceRenderNode = this.renderNode;
        if (deviceRenderNode.getHasDisplayList()) {
            deviceRenderNode.discardDisplayList();
        }
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty(false);
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.observationClearRequested = true;
        androidComposeView.recycle$ui_release(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        android.graphics.Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
        boolean isHardwareAccelerated = canvas3.isHardwareAccelerated();
        DeviceRenderNode deviceRenderNode = this.renderNode;
        if (isHardwareAccelerated) {
            updateDisplayList();
            boolean z = deviceRenderNode.getElevation() > 0.0f;
            this.drawnWithZ = z;
            if (z) {
                canvas.enableZ();
            }
            deviceRenderNode.drawInto(canvas3);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        float left = deviceRenderNode.getLeft();
        float top = deviceRenderNode.getTop();
        float right = deviceRenderNode.getRight();
        float bottom = deviceRenderNode.getBottom();
        if (deviceRenderNode.getAlpha() < 1.0f) {
            AndroidPaint androidPaint = this.softwareLayerPaint;
            if (androidPaint == null) {
                androidPaint = Brush.Paint();
                this.softwareLayerPaint = androidPaint;
            }
            androidPaint.setAlpha(deviceRenderNode.getAlpha());
            canvas3.saveLayer(left, top, right, bottom, androidPaint.internalPaint);
        } else {
            canvas.save();
        }
        canvas.translate(left, top);
        canvas.mo89concat58bKbWc(this.matrixCache.m235calculateMatrixGrdbGEg(deviceRenderNode));
        if (deviceRenderNode.getClipToOutline() || deviceRenderNode.getClipToBounds()) {
            this.outlineResolver.clipToOutline(canvas);
        }
        Function1 function1 = this.drawBlock;
        if (function1 != null) {
            function1.invoke(canvas);
        }
        canvas.restore();
        setDirty(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        this.ownerView.invalidate();
        setDirty(true);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo206isInLayerk4lQ0M(long j) {
        float m76getXimpl = Offset.m76getXimpl(j);
        float m77getYimpl = Offset.m77getYimpl(j);
        DeviceRenderNode deviceRenderNode = this.renderNode;
        if (deviceRenderNode.getClipToBounds()) {
            return 0.0f <= m76getXimpl && m76getXimpl < ((float) deviceRenderNode.getWidth()) && 0.0f <= m77getYimpl && m77getYimpl < ((float) deviceRenderNode.getHeight());
        }
        if (deviceRenderNode.getClipToOutline()) {
            return this.outlineResolver.m236isInOutlinek4lQ0M(j);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        DeviceRenderNode deviceRenderNode = this.renderNode;
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (!z) {
            Brush.m105mapimpl(layerMatrixCache.m235calculateMatrixGrdbGEg(deviceRenderNode), mutableRect);
            return;
        }
        float[] m234calculateInverseMatrixbWbORWo = layerMatrixCache.m234calculateInverseMatrixbWbORWo(deviceRenderNode);
        if (m234calculateInverseMatrixbWbORWo != null) {
            Brush.m105mapimpl(m234calculateInverseMatrixbWbORWo, mutableRect);
            return;
        }
        mutableRect.left = 0.0f;
        mutableRect.top = 0.0f;
        mutableRect.right = 0.0f;
        mutableRect.bottom = 0.0f;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public final long mo207mapOffset8S9VItk(long j, boolean z) {
        DeviceRenderNode deviceRenderNode = this.renderNode;
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (!z) {
            return Brush.m104mapMKHz9U(layerMatrixCache.m235calculateMatrixGrdbGEg(deviceRenderNode), j);
        }
        float[] m234calculateInverseMatrixbWbORWo = layerMatrixCache.m234calculateInverseMatrixbWbORWo(deviceRenderNode);
        return m234calculateInverseMatrixbWbORWo != null ? Brush.m104mapMKHz9U(m234calculateInverseMatrixbWbORWo, j) : Offset.Infinite;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo208movegyyYBs(long j) {
        DeviceRenderNode deviceRenderNode = this.renderNode;
        int left = deviceRenderNode.getLeft();
        int top = deviceRenderNode.getTop();
        int i = IntOffset.$r8$clinit;
        int i2 = (int) (j >> 32);
        int i3 = (int) (j & 4294967295L);
        if (left == i2 && top == i3) {
            return;
        }
        if (left != i2) {
            deviceRenderNode.offsetLeftAndRight(i2 - left);
        }
        if (top != i3) {
            deviceRenderNode.offsetTopAndBottom(i3 - top);
        }
        WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(this.ownerView);
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo209resizeozmzZPI(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        long j2 = this.transformOrigin;
        int i3 = TransformOrigin.$r8$clinit;
        float f = i;
        float intBitsToFloat = Float.intBitsToFloat((int) (j2 >> 32)) * f;
        DeviceRenderNode deviceRenderNode = this.renderNode;
        deviceRenderNode.setPivotX(intBitsToFloat);
        float f2 = i2;
        deviceRenderNode.setPivotY(Float.intBitsToFloat((int) (4294967295L & this.transformOrigin)) * f2);
        if (deviceRenderNode.setPosition(deviceRenderNode.getLeft(), deviceRenderNode.getTop(), deviceRenderNode.getLeft() + i, deviceRenderNode.getTop() + i2)) {
            long Size = _BOUNDARY.Size(f, f2);
            OutlineResolver outlineResolver = this.outlineResolver;
            long j3 = outlineResolver.size;
            int i4 = Size.$r8$clinit;
            if (j3 != Size) {
                outlineResolver.size = Size;
                outlineResolver.cacheIsDirty = true;
            }
            deviceRenderNode.setOutline(outlineResolver.getOutline());
            if (!this.isDirty && !this.isDestroyed) {
                this.ownerView.invalidate();
                setDirty(true);
            }
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1, Function1 function1) {
        ResultKt.checkNotNullParameter(function1, "drawBlock");
        setDirty(false);
        this.isDestroyed = false;
        this.drawnWithZ = false;
        this.transformOrigin = TransformOrigin.Center;
        this.drawBlock = function1;
        this.invalidateParentLayer = layoutNode$_foldedChildren$1;
    }

    public final void setDirty(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // androidx.compose.ui.node.OwnedLayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateDisplayList() {
        /*
            r4 = this;
            boolean r0 = r4.isDirty
            androidx.compose.ui.platform.DeviceRenderNode r1 = r4.renderNode
            if (r0 != 0) goto Lc
            boolean r0 = r1.getHasDisplayList()
            if (r0 != 0) goto L2e
        Lc:
            r0 = 0
            r4.setDirty(r0)
            boolean r0 = r1.getClipToOutline()
            if (r0 == 0) goto L24
            androidx.compose.ui.platform.OutlineResolver r0 = r4.outlineResolver
            boolean r2 = r0.usePathForClip
            r2 = r2 ^ 1
            if (r2 != 0) goto L24
            r0.updateCache()
            androidx.compose.ui.graphics.Path r0 = r0.outlinePath
            goto L25
        L24:
            r0 = 0
        L25:
            kotlin.jvm.functions.Function1 r2 = r4.drawBlock
            if (r2 == 0) goto L2e
            androidx.compose.runtime.Stack r3 = r4.canvasHolder
            r1.record(r3, r0, r2)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.RenderNodeLayer.updateDisplayList():void");
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: updateLayerProperties-dDxr-wY */
    public final void mo210updateLayerPropertiesdDxrwY(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i, LayoutDirection layoutDirection, Density density) {
        Function0 function0;
        ResultKt.checkNotNullParameter(shape, "shape");
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        ResultKt.checkNotNullParameter(density, "density");
        this.transformOrigin = j;
        DeviceRenderNode deviceRenderNode = this.renderNode;
        boolean clipToOutline = deviceRenderNode.getClipToOutline();
        OutlineResolver outlineResolver = this.outlineResolver;
        boolean z2 = false;
        boolean z3 = clipToOutline && !(outlineResolver.usePathForClip ^ true);
        deviceRenderNode.setScaleX(f);
        deviceRenderNode.setScaleY(f2);
        deviceRenderNode.setAlpha(f3);
        deviceRenderNode.setTranslationX(f4);
        deviceRenderNode.setTranslationY(f5);
        deviceRenderNode.setElevation(f6);
        deviceRenderNode.setAmbientShadowColor(Brush.m109toArgb8_81llA(j2));
        deviceRenderNode.setSpotShadowColor(Brush.m109toArgb8_81llA(j3));
        deviceRenderNode.setRotationZ(f9);
        deviceRenderNode.setRotationX(f7);
        deviceRenderNode.setRotationY(f8);
        deviceRenderNode.setCameraDistance(f10);
        int i2 = TransformOrigin.$r8$clinit;
        deviceRenderNode.setPivotX(Float.intBitsToFloat((int) (j >> 32)) * deviceRenderNode.getWidth());
        deviceRenderNode.setPivotY(Float.intBitsToFloat((int) (j & 4294967295L)) * deviceRenderNode.getHeight());
        RectangleShapeKt$RectangleShape$1 rectangleShapeKt$RectangleShape$1 = Brush.RectangleShape;
        deviceRenderNode.setClipToOutline(z && shape != rectangleShapeKt$RectangleShape$1);
        deviceRenderNode.setClipToBounds(z && shape == rectangleShapeKt$RectangleShape$1);
        deviceRenderNode.setRenderEffect();
        deviceRenderNode.mo228setCompositingStrategyaDBOjCE(i);
        boolean update = this.outlineResolver.update(shape, deviceRenderNode.getAlpha(), deviceRenderNode.getClipToOutline(), deviceRenderNode.getElevation(), layoutDirection, density);
        deviceRenderNode.setOutline(outlineResolver.getOutline());
        if (deviceRenderNode.getClipToOutline() && !(!outlineResolver.usePathForClip)) {
            z2 = true;
        }
        AndroidComposeView androidComposeView = this.ownerView;
        if (z3 == z2 && (!z2 || !update)) {
            WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(androidComposeView);
        } else if (!this.isDirty && !this.isDestroyed) {
            androidComposeView.invalidate();
            setDirty(true);
        }
        if (!this.drawnWithZ && deviceRenderNode.getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        this.matrixCache.invalidate();
    }
}
