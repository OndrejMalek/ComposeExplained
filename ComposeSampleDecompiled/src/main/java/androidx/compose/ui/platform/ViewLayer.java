package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.compose.runtime.Stack;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class ViewLayer extends View implements OwnedLayer {
    public static final ViewLayer$Companion$OutlineProvider$1 OutlineProvider = new ViewOutlineProvider();
    public static boolean hasRetrievedMethod;
    public static Field recreateDisplayList;
    public static boolean shouldUseDispatchDraw;
    public static Method updateDisplayListIfDirtyMethod;
    public final Stack canvasHolder;
    public Rect clipBoundsCache;
    public boolean clipToBounds;
    public final DrawChildContainer container;
    public Function1 drawBlock;
    public boolean drawnWithZ;
    public Function0 invalidateParentLayer;
    public boolean isInvalidated;
    public final long layerId;
    public boolean mHasOverlappingRendering;
    public long mTransformOrigin;
    public final LayerMatrixCache matrixCache;
    public final OutlineResolver outlineResolver;
    public final AndroidComposeView ownerView;

    /* loaded from: classes.dex */
    public abstract class UniqueDrawingIdApi29 {
        public static final long getUniqueDrawingId(View view) {
            long uniqueDrawingId;
            ResultKt.checkNotNullParameter(view, "view");
            uniqueDrawingId = view.getUniqueDrawingId();
            return uniqueDrawingId;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewLayer(AndroidComposeView androidComposeView, DrawChildContainer drawChildContainer, Function1 function1, LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1) {
        super(androidComposeView.getContext());
        ResultKt.checkNotNullParameter(function1, "drawBlock");
        this.ownerView = androidComposeView;
        this.container = drawChildContainer;
        this.drawBlock = function1;
        this.invalidateParentLayer = layoutNode$_foldedChildren$1;
        this.outlineResolver = new OutlineResolver(androidComposeView.getDensity());
        this.canvasHolder = new Stack(5);
        this.matrixCache = new LayerMatrixCache(ViewLayer$Companion$getMatrix$1.INSTANCE);
        this.mTransformOrigin = TransformOrigin.Center;
        this.mHasOverlappingRendering = true;
        setWillNotDraw(false);
        drawChildContainer.addView(this);
        this.layerId = View.generateViewId();
    }

    private final Path getManualClipPath() {
        if (getClipToOutline()) {
            OutlineResolver outlineResolver = this.outlineResolver;
            if (!(!outlineResolver.usePathForClip)) {
                outlineResolver.updateCache();
                return outlineResolver.outlinePath;
            }
        }
        return null;
    }

    private final void setInvalidated(boolean z) {
        if (z != this.isInvalidated) {
            this.isInvalidated = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        setInvalidated(false);
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.observationClearRequested = true;
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        androidComposeView.recycle$ui_release(this);
        this.container.removeViewInLayout(this);
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        boolean z = false;
        setInvalidated(false);
        Stack stack = this.canvasHolder;
        Object obj = stack.backing;
        Canvas canvas2 = ((AndroidCanvas) obj).internalCanvas;
        AndroidCanvas androidCanvas = (AndroidCanvas) obj;
        androidCanvas.getClass();
        androidCanvas.internalCanvas = canvas;
        AndroidCanvas androidCanvas2 = (AndroidCanvas) stack.backing;
        if (getManualClipPath() != null || !canvas.isHardwareAccelerated()) {
            androidCanvas2.save();
            this.outlineResolver.clipToOutline(androidCanvas2);
            z = true;
        }
        Function1 function1 = this.drawBlock;
        if (function1 != null) {
            function1.invoke(androidCanvas2);
        }
        if (z) {
            androidCanvas2.restore();
        }
        ((AndroidCanvas) stack.backing).setInternalCanvas(canvas2);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(androidx.compose.ui.graphics.Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        boolean z = getElevation() > 0.0f;
        this.drawnWithZ = z;
        if (z) {
            canvas.enableZ();
        }
        this.container.drawChild$ui_release(canvas, this, getDrawingTime());
        if (this.drawnWithZ) {
            canvas.disableZ();
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
    }

    public final float getCameraDistancePx() {
        return getCameraDistance() / getResources().getDisplayMetrics().densityDpi;
    }

    public final DrawChildContainer getContainer() {
        return this.container;
    }

    public long getLayerId() {
        return this.layerId;
    }

    public final AndroidComposeView getOwnerView() {
        return this.ownerView;
    }

    public long getOwnerViewId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return UniqueDrawingIdApi29.getUniqueDrawingId(this.ownerView);
        }
        return -1L;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.mHasOverlappingRendering;
    }

    @Override // android.view.View, androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        if (this.isInvalidated) {
            return;
        }
        setInvalidated(true);
        super.invalidate();
        this.ownerView.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo206isInLayerk4lQ0M(long j) {
        float m76getXimpl = Offset.m76getXimpl(j);
        float m77getYimpl = Offset.m77getYimpl(j);
        if (this.clipToBounds) {
            return 0.0f <= m76getXimpl && m76getXimpl < ((float) getWidth()) && 0.0f <= m77getYimpl && m77getYimpl < ((float) getHeight());
        }
        if (getClipToOutline()) {
            return this.outlineResolver.m236isInOutlinek4lQ0M(j);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (!z) {
            Brush.m105mapimpl(layerMatrixCache.m235calculateMatrixGrdbGEg(this), mutableRect);
            return;
        }
        float[] m234calculateInverseMatrixbWbORWo = layerMatrixCache.m234calculateInverseMatrixbWbORWo(this);
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
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (!z) {
            return Brush.m104mapMKHz9U(layerMatrixCache.m235calculateMatrixGrdbGEg(this), j);
        }
        float[] m234calculateInverseMatrixbWbORWo = layerMatrixCache.m234calculateInverseMatrixbWbORWo(this);
        return m234calculateInverseMatrixbWbORWo != null ? Brush.m104mapMKHz9U(m234calculateInverseMatrixbWbORWo, j) : Offset.Infinite;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo208movegyyYBs(long j) {
        int i = IntOffset.$r8$clinit;
        int i2 = (int) (j >> 32);
        int left = getLeft();
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (i2 != left) {
            offsetLeftAndRight(i2 - getLeft());
            layerMatrixCache.invalidate();
        }
        int i3 = (int) (j & 4294967295L);
        if (i3 != getTop()) {
            offsetTopAndBottom(i3 - getTop());
            layerMatrixCache.invalidate();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public final void resetClipBounds() {
        Rect rect;
        if (this.clipToBounds) {
            Rect rect2 = this.clipBoundsCache;
            if (rect2 == null) {
                this.clipBoundsCache = new Rect(0, 0, getWidth(), getHeight());
            } else {
                ResultKt.checkNotNull(rect2);
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.clipBoundsCache;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo209resizeozmzZPI(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        if (i == getWidth() && i2 == getHeight()) {
            return;
        }
        long j2 = this.mTransformOrigin;
        int i3 = TransformOrigin.$r8$clinit;
        float f = i;
        setPivotX(Float.intBitsToFloat((int) (j2 >> 32)) * f);
        float f2 = i2;
        setPivotY(Float.intBitsToFloat((int) (4294967295L & this.mTransformOrigin)) * f2);
        long Size = _BOUNDARY.Size(f, f2);
        OutlineResolver outlineResolver = this.outlineResolver;
        long j3 = outlineResolver.size;
        int i4 = Size.$r8$clinit;
        if (j3 != Size) {
            outlineResolver.size = Size;
            outlineResolver.cacheIsDirty = true;
        }
        setOutlineProvider(outlineResolver.getOutline() != null ? OutlineProvider : null);
        layout(getLeft(), getTop(), getLeft() + i, getTop() + i2);
        resetClipBounds();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1, Function1 function1) {
        ResultKt.checkNotNullParameter(function1, "drawBlock");
        this.container.addView(this);
        this.clipToBounds = false;
        this.drawnWithZ = false;
        this.mTransformOrigin = TransformOrigin.Center;
        this.drawBlock = function1;
        this.invalidateParentLayer = layoutNode$_foldedChildren$1;
    }

    public final void setCameraDistancePx(float f) {
        setCameraDistance(f * getResources().getDisplayMetrics().densityDpi);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateDisplayList() {
        if (!this.isInvalidated || shouldUseDispatchDraw) {
            return;
        }
        setInvalidated(false);
        AndroidTextToolbar.updateDisplayList(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: updateLayerProperties-dDxr-wY */
    public final void mo210updateLayerPropertiesdDxrwY(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i, LayoutDirection layoutDirection, Density density) {
        Function0 function0;
        ResultKt.checkNotNullParameter(shape, "shape");
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        ResultKt.checkNotNullParameter(density, "density");
        this.mTransformOrigin = j;
        setScaleX(f);
        setScaleY(f2);
        setAlpha(f3);
        setTranslationX(f4);
        setTranslationY(f5);
        setElevation(f6);
        setRotation(f9);
        setRotationX(f7);
        setRotationY(f8);
        long j4 = this.mTransformOrigin;
        int i2 = TransformOrigin.$r8$clinit;
        setPivotX(Float.intBitsToFloat((int) (j4 >> 32)) * getWidth());
        setPivotY(Float.intBitsToFloat((int) (this.mTransformOrigin & 4294967295L)) * getHeight());
        setCameraDistancePx(f10);
        RectangleShapeKt$RectangleShape$1 rectangleShapeKt$RectangleShape$1 = Brush.RectangleShape;
        boolean z2 = false;
        this.clipToBounds = z && shape == rectangleShapeKt$RectangleShape$1;
        resetClipBounds();
        boolean z3 = getManualClipPath() != null;
        setClipToOutline(z && shape != rectangleShapeKt$RectangleShape$1);
        boolean update = this.outlineResolver.update(shape, getAlpha(), getClipToOutline(), getElevation(), layoutDirection, density);
        setOutlineProvider(this.outlineResolver.getOutline() != null ? OutlineProvider : null);
        boolean z4 = getManualClipPath() != null;
        if (z3 != z4 || (z4 && update)) {
            invalidate();
        }
        if (!this.drawnWithZ && getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        this.matrixCache.invalidate();
        int i3 = Build.VERSION.SDK_INT;
        ViewLayerVerificationHelper28 viewLayerVerificationHelper28 = ViewLayerVerificationHelper28.INSTANCE;
        viewLayerVerificationHelper28.setOutlineAmbientShadowColor(this, Brush.m109toArgb8_81llA(j2));
        viewLayerVerificationHelper28.setOutlineSpotShadowColor(this, Brush.m109toArgb8_81llA(j3));
        if (i3 >= 31) {
            ViewLayerVerificationHelper31.INSTANCE.setRenderEffect(this, null);
        }
        if (Brush.m99equalsimpl0$1(i, 1)) {
            setLayerType(2, null);
        } else {
            if (Brush.m99equalsimpl0$1(i, 2)) {
                setLayerType(0, null);
                this.mHasOverlappingRendering = z2;
            }
            setLayerType(0, null);
        }
        z2 = true;
        this.mHasOverlappingRendering = z2;
    }
}
