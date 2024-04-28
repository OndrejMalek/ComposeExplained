package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Outline$Rectangle;
import androidx.compose.ui.graphics.Outline$Rounded;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class OutlineResolver {
    public boolean cacheIsDirty;
    public final Outline cachedOutline;
    public AndroidPath cachedRrectPath;
    public Brush calculatedOutline;
    public Density density;
    public boolean isSupportedOutline;
    public LayoutDirection layoutDirection;
    public boolean outlineNeeded;
    public Path outlinePath;
    public long rectSize;
    public long rectTopLeft;
    public float roundedCornerRadius;
    public Shape shape;
    public long size;
    public Path tmpPath;
    public RoundRect tmpRoundRect;
    public boolean usePathForClip;

    public OutlineResolver(Density density) {
        ResultKt.checkNotNullParameter(density, "density");
        this.density = density;
        this.isSupportedOutline = true;
        Outline outline = new Outline();
        outline.setAlpha(1.0f);
        this.cachedOutline = outline;
        long j = Size.Zero;
        this.size = j;
        this.shape = Brush.RectangleShape;
        this.rectTopLeft = Offset.Zero;
        this.rectSize = j;
        this.layoutDirection = LayoutDirection.Ltr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x006a, code lost:
    
        if (androidx.compose.ui.geometry.CornerRadius.m72getXimpl(r5.topLeftCornerRadius) == r2) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void clipToOutline(androidx.compose.ui.graphics.Canvas r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            java.lang.String r2 = "canvas"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            r20.updateCache()
            androidx.compose.ui.graphics.Path r2 = r0.outlinePath
            r3 = 1
            if (r2 == 0) goto L16
            r1.mo87clipPathmtrdDE(r2, r3)
            goto Lfc
        L16:
            float r2 = r0.roundedCornerRadius
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto Ld0
            androidx.compose.ui.graphics.Path r4 = r0.tmpPath
            androidx.compose.ui.geometry.RoundRect r5 = r0.tmpRoundRect
            if (r4 == 0) goto L6d
            long r6 = r0.rectTopLeft
            long r8 = r0.rectSize
            if (r5 == 0) goto L6d
            boolean r10 = _COROUTINE._BOUNDARY.isSimple(r5)
            if (r10 != 0) goto L30
            goto L6d
        L30:
            float r10 = androidx.compose.ui.geometry.Offset.m76getXimpl(r6)
            float r11 = r5.left
            int r10 = (r11 > r10 ? 1 : (r11 == r10 ? 0 : -1))
            if (r10 != 0) goto L6d
            float r10 = androidx.compose.ui.geometry.Offset.m77getYimpl(r6)
            float r11 = r5.top
            int r10 = (r11 > r10 ? 1 : (r11 == r10 ? 0 : -1))
            if (r10 != 0) goto L6d
            float r10 = androidx.compose.ui.geometry.Offset.m76getXimpl(r6)
            float r11 = androidx.compose.ui.geometry.Size.m85getWidthimpl(r8)
            float r11 = r11 + r10
            float r10 = r5.right
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 != 0) goto L6d
            float r6 = androidx.compose.ui.geometry.Offset.m77getYimpl(r6)
            float r7 = androidx.compose.ui.geometry.Size.m83getHeightimpl(r8)
            float r7 = r7 + r6
            float r6 = r5.bottom
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 != 0) goto L6d
            long r5 = r5.topLeftCornerRadius
            float r5 = androidx.compose.ui.geometry.CornerRadius.m72getXimpl(r5)
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 != 0) goto L6d
            goto Lcc
        L6d:
            long r5 = r0.rectTopLeft
            float r8 = androidx.compose.ui.geometry.Offset.m76getXimpl(r5)
            long r5 = r0.rectTopLeft
            float r9 = androidx.compose.ui.geometry.Offset.m77getYimpl(r5)
            long r5 = r0.rectTopLeft
            float r2 = androidx.compose.ui.geometry.Offset.m76getXimpl(r5)
            long r5 = r0.rectSize
            float r5 = androidx.compose.ui.geometry.Size.m85getWidthimpl(r5)
            float r10 = r5 + r2
            long r5 = r0.rectTopLeft
            float r2 = androidx.compose.ui.geometry.Offset.m77getYimpl(r5)
            long r5 = r0.rectSize
            float r5 = androidx.compose.ui.geometry.Size.m83getHeightimpl(r5)
            float r11 = r5 + r2
            float r2 = r0.roundedCornerRadius
            long r5 = _COROUTINE._BOUNDARY.CornerRadius(r2, r2)
            float r2 = androidx.compose.ui.geometry.CornerRadius.m72getXimpl(r5)
            float r5 = androidx.compose.ui.geometry.CornerRadius.m73getYimpl(r5)
            long r18 = _COROUTINE._BOUNDARY.CornerRadius(r2, r5)
            androidx.compose.ui.geometry.RoundRect r2 = new androidx.compose.ui.geometry.RoundRect
            r7 = r2
            r12 = r18
            r14 = r18
            r16 = r18
            r7.<init>(r8, r9, r10, r11, r12, r14, r16, r18)
            if (r4 != 0) goto Lba
            androidx.compose.ui.graphics.AndroidPath r4 = androidx.compose.ui.graphics.Brush.Path()
            goto Lc2
        Lba:
            r5 = r4
            androidx.compose.ui.graphics.AndroidPath r5 = (androidx.compose.ui.graphics.AndroidPath) r5
            android.graphics.Path r5 = r5.internalPath
            r5.reset()
        Lc2:
            r5 = r4
            androidx.compose.ui.graphics.AndroidPath r5 = (androidx.compose.ui.graphics.AndroidPath) r5
            r5.addRoundRect(r2)
            r0.tmpRoundRect = r2
            r0.tmpPath = r4
        Lcc:
            r1.mo87clipPathmtrdDE(r4, r3)
            goto Lfc
        Ld0:
            long r2 = r0.rectTopLeft
            float r2 = androidx.compose.ui.geometry.Offset.m76getXimpl(r2)
            long r3 = r0.rectTopLeft
            float r3 = androidx.compose.ui.geometry.Offset.m77getYimpl(r3)
            long r4 = r0.rectTopLeft
            float r4 = androidx.compose.ui.geometry.Offset.m76getXimpl(r4)
            long r5 = r0.rectSize
            float r5 = androidx.compose.ui.geometry.Size.m85getWidthimpl(r5)
            float r4 = r4 + r5
            long r5 = r0.rectTopLeft
            float r5 = androidx.compose.ui.geometry.Offset.m77getYimpl(r5)
            long r6 = r0.rectSize
            float r6 = androidx.compose.ui.geometry.Size.m83getHeightimpl(r6)
            float r5 = r5 + r6
            r6 = 1
            r1 = r21
            r1.mo88clipRectN_I0leg(r2, r3, r4, r5, r6)
        Lfc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.OutlineResolver.clipToOutline(androidx.compose.ui.graphics.Canvas):void");
    }

    public final Outline getOutline() {
        updateCache();
        if (this.outlineNeeded && this.isSupportedOutline) {
            return this.cachedOutline;
        }
        return null;
    }

    /* renamed from: isInOutline-k-4lQ0M, reason: not valid java name */
    public final boolean m236isInOutlinek4lQ0M(long j) {
        Brush brush;
        float f;
        if (!this.outlineNeeded || (brush = this.calculatedOutline) == null) {
            return true;
        }
        float m76getXimpl = Offset.m76getXimpl(j);
        float m77getYimpl = Offset.m77getYimpl(j);
        boolean z = false;
        if (brush instanceof Outline$Rectangle) {
            Rect rect = ((Outline$Rectangle) brush).rect;
            if (rect.left <= m76getXimpl && m76getXimpl < rect.right && rect.top <= m77getYimpl && m77getYimpl < rect.bottom) {
                return true;
            }
        } else {
            if (!(brush instanceof Outline$Rounded)) {
                throw new RuntimeException();
            }
            RoundRect roundRect = ((Outline$Rounded) brush).roundRect;
            if (m76getXimpl >= roundRect.left) {
                float f2 = roundRect.right;
                if (m76getXimpl < f2) {
                    float f3 = roundRect.top;
                    if (m77getYimpl >= f3) {
                        float f4 = roundRect.bottom;
                        if (m77getYimpl < f4) {
                            long j2 = roundRect.topLeftCornerRadius;
                            float m72getXimpl = CornerRadius.m72getXimpl(j2);
                            long j3 = roundRect.topRightCornerRadius;
                            if (CornerRadius.m72getXimpl(j3) + m72getXimpl <= roundRect.getWidth()) {
                                long j4 = roundRect.bottomLeftCornerRadius;
                                float m72getXimpl2 = CornerRadius.m72getXimpl(j4);
                                f = m76getXimpl;
                                long j5 = roundRect.bottomRightCornerRadius;
                                if (CornerRadius.m72getXimpl(j5) + m72getXimpl2 <= roundRect.getWidth()) {
                                    if (CornerRadius.m73getYimpl(j4) + CornerRadius.m73getYimpl(j2) <= roundRect.getHeight()) {
                                        if (CornerRadius.m73getYimpl(j5) + CornerRadius.m73getYimpl(j3) <= roundRect.getHeight()) {
                                            float m72getXimpl3 = CornerRadius.m72getXimpl(j2);
                                            float f5 = roundRect.left;
                                            float f6 = m72getXimpl3 + f5;
                                            float m73getYimpl = CornerRadius.m73getYimpl(j2) + f3;
                                            float m72getXimpl4 = f2 - CornerRadius.m72getXimpl(j3);
                                            float m73getYimpl2 = f3 + CornerRadius.m73getYimpl(j3);
                                            float m72getXimpl5 = f2 - CornerRadius.m72getXimpl(j5);
                                            float m73getYimpl3 = f4 - CornerRadius.m73getYimpl(j5);
                                            float m73getYimpl4 = f4 - CornerRadius.m73getYimpl(j4);
                                            float m72getXimpl6 = f5 + CornerRadius.m72getXimpl(j4);
                                            z = (f >= f6 || m77getYimpl >= m73getYimpl) ? (f >= m72getXimpl6 || m77getYimpl <= m73getYimpl4) ? (f <= m72getXimpl4 || m77getYimpl >= m73getYimpl2) ? (f <= m72getXimpl5 || m77getYimpl <= m73getYimpl3) ? true : InvertMatrixKt.m233isWithinEllipseVE1yxkc(f, m77getYimpl, m72getXimpl5, m73getYimpl3, roundRect.bottomRightCornerRadius) : InvertMatrixKt.m233isWithinEllipseVE1yxkc(f, m77getYimpl, m72getXimpl4, m73getYimpl2, roundRect.topRightCornerRadius) : InvertMatrixKt.m233isWithinEllipseVE1yxkc(f, m77getYimpl, m72getXimpl6, m73getYimpl4, roundRect.bottomLeftCornerRadius) : InvertMatrixKt.m233isWithinEllipseVE1yxkc(f, m77getYimpl, f6, m73getYimpl, roundRect.topLeftCornerRadius);
                                        }
                                    }
                                }
                            } else {
                                f = m76getXimpl;
                            }
                            AndroidPath Path = Brush.Path();
                            Path.addRoundRect(roundRect);
                            float f7 = f - 0.005f;
                            float f8 = m77getYimpl - 0.005f;
                            float f9 = f + 0.005f;
                            float f10 = m77getYimpl + 0.005f;
                            AndroidPath Path2 = Brush.Path();
                            if (!(!Float.isNaN(f7))) {
                                throw new IllegalStateException("Rect.left is NaN".toString());
                            }
                            if (!(!Float.isNaN(f8))) {
                                throw new IllegalStateException("Rect.top is NaN".toString());
                            }
                            if (!(!Float.isNaN(f9))) {
                                throw new IllegalStateException("Rect.right is NaN".toString());
                            }
                            if (!(!Float.isNaN(f10))) {
                                throw new IllegalStateException("Rect.bottom is NaN".toString());
                            }
                            RectF rectF = Path2.rectF;
                            rectF.set(f7, f8, f9, f10);
                            Path.Direction direction = Path.Direction.CCW;
                            android.graphics.Path path = Path2.internalPath;
                            path.addRect(rectF, direction);
                            AndroidPath Path3 = Brush.Path();
                            Path3.m96opN5in7k0(Path, Path2, 1);
                            android.graphics.Path path2 = Path3.internalPath;
                            boolean isEmpty = path2.isEmpty();
                            path2.reset();
                            path.reset();
                            z = !isEmpty;
                        }
                    }
                }
            }
        }
        return z;
    }

    public final boolean update(Shape shape, float f, boolean z, float f2, LayoutDirection layoutDirection, Density density) {
        ResultKt.checkNotNullParameter(shape, "shape");
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        ResultKt.checkNotNullParameter(density, "density");
        this.cachedOutline.setAlpha(f);
        boolean z2 = !ResultKt.areEqual(this.shape, shape);
        if (z2) {
            this.shape = shape;
            this.cacheIsDirty = true;
        }
        boolean z3 = z || f2 > 0.0f;
        if (this.outlineNeeded != z3) {
            this.outlineNeeded = z3;
            this.cacheIsDirty = true;
        }
        if (this.layoutDirection != layoutDirection) {
            this.layoutDirection = layoutDirection;
            this.cacheIsDirty = true;
        }
        if (!ResultKt.areEqual(this.density, density)) {
            this.density = density;
            this.cacheIsDirty = true;
        }
        return z2;
    }

    public final void updateCache() {
        if (this.cacheIsDirty) {
            this.rectTopLeft = Offset.Zero;
            long j = this.size;
            this.rectSize = j;
            this.roundedCornerRadius = 0.0f;
            this.outlinePath = null;
            this.cacheIsDirty = false;
            this.usePathForClip = false;
            boolean z = this.outlineNeeded;
            Outline outline = this.cachedOutline;
            if (!z || Size.m85getWidthimpl(j) <= 0.0f || Size.m83getHeightimpl(this.size) <= 0.0f) {
                outline.setEmpty();
                return;
            }
            this.isSupportedOutline = true;
            Brush mo38createOutlinePq9zytI = this.shape.mo38createOutlinePq9zytI(this.size, this.layoutDirection, this.density);
            this.calculatedOutline = mo38createOutlinePq9zytI;
            if (mo38createOutlinePq9zytI instanceof Outline$Rectangle) {
                Rect rect = ((Outline$Rectangle) mo38createOutlinePq9zytI).rect;
                float f = rect.left;
                float f2 = rect.top;
                this.rectTopLeft = _BOUNDARY.Offset(f, f2);
                this.rectSize = _BOUNDARY.Size(rect.getWidth(), rect.getHeight());
                outline.setRect(ResultKt.roundToInt(rect.left), ResultKt.roundToInt(f2), ResultKt.roundToInt(rect.right), ResultKt.roundToInt(rect.bottom));
                return;
            }
            if (mo38createOutlinePq9zytI instanceof Outline$Rounded) {
                RoundRect roundRect = ((Outline$Rounded) mo38createOutlinePq9zytI).roundRect;
                float m72getXimpl = CornerRadius.m72getXimpl(roundRect.topLeftCornerRadius);
                float f3 = roundRect.left;
                float f4 = roundRect.top;
                this.rectTopLeft = _BOUNDARY.Offset(f3, f4);
                this.rectSize = _BOUNDARY.Size(roundRect.getWidth(), roundRect.getHeight());
                if (_BOUNDARY.isSimple(roundRect)) {
                    this.cachedOutline.setRoundRect(ResultKt.roundToInt(f3), ResultKt.roundToInt(f4), ResultKt.roundToInt(roundRect.right), ResultKt.roundToInt(roundRect.bottom), m72getXimpl);
                    this.roundedCornerRadius = m72getXimpl;
                    return;
                }
                AndroidPath androidPath = this.cachedRrectPath;
                if (androidPath == null) {
                    androidPath = Brush.Path();
                    this.cachedRrectPath = androidPath;
                }
                android.graphics.Path path = androidPath.internalPath;
                path.reset();
                androidPath.addRoundRect(roundRect);
                if (Build.VERSION.SDK_INT > 28 || path.isConvex()) {
                    outline.setConvexPath(path);
                    this.usePathForClip = true ^ outline.canClip();
                } else {
                    this.isSupportedOutline = false;
                    outline.setEmpty();
                    this.usePathForClip = true;
                }
                this.outlinePath = androidPath;
            }
        }
    }
}
