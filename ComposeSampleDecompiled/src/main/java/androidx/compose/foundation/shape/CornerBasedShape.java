package androidx.compose.foundation.shape;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Outline$Rectangle;
import androidx.compose.ui.graphics.Outline$Rounded;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class CornerBasedShape implements Shape {
    public final CornerSize bottomEnd;
    public final CornerSize bottomStart;
    public final CornerSize topEnd;
    public final CornerSize topStart;

    public CornerBasedShape(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
        this.topStart = cornerSize;
        this.topEnd = cornerSize2;
        this.bottomEnd = cornerSize3;
        this.bottomStart = cornerSize4;
    }

    @Override // androidx.compose.ui.graphics.Shape
    /* renamed from: createOutline-Pq9zytI, reason: not valid java name */
    public final Brush mo38createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        ResultKt.checkNotNullParameter(density, "density");
        float mo39toPxTmRCtEA = this.topStart.mo39toPxTmRCtEA(j, density);
        float mo39toPxTmRCtEA2 = this.topEnd.mo39toPxTmRCtEA(j, density);
        float mo39toPxTmRCtEA3 = this.bottomEnd.mo39toPxTmRCtEA(j, density);
        float mo39toPxTmRCtEA4 = this.bottomStart.mo39toPxTmRCtEA(j, density);
        float m84getMinDimensionimpl = Size.m84getMinDimensionimpl(j);
        float f = mo39toPxTmRCtEA + mo39toPxTmRCtEA4;
        if (f > m84getMinDimensionimpl) {
            float f2 = m84getMinDimensionimpl / f;
            mo39toPxTmRCtEA *= f2;
            mo39toPxTmRCtEA4 *= f2;
        }
        float f3 = mo39toPxTmRCtEA2 + mo39toPxTmRCtEA3;
        if (f3 > m84getMinDimensionimpl) {
            float f4 = m84getMinDimensionimpl / f3;
            mo39toPxTmRCtEA2 *= f4;
            mo39toPxTmRCtEA3 *= f4;
        }
        if (mo39toPxTmRCtEA < 0.0f || mo39toPxTmRCtEA2 < 0.0f || mo39toPxTmRCtEA3 < 0.0f || mo39toPxTmRCtEA4 < 0.0f) {
            throw new IllegalArgumentException(("Corner size in Px can't be negative(topStart = " + mo39toPxTmRCtEA + ", topEnd = " + mo39toPxTmRCtEA2 + ", bottomEnd = " + mo39toPxTmRCtEA3 + ", bottomStart = " + mo39toPxTmRCtEA4 + ")!").toString());
        }
        if (mo39toPxTmRCtEA + mo39toPxTmRCtEA2 + mo39toPxTmRCtEA3 + mo39toPxTmRCtEA4 == 0.0f) {
            return new Outline$Rectangle(_BOUNDARY.m4Recttz77jQw(Offset.Zero, j));
        }
        Rect m4Recttz77jQw = _BOUNDARY.m4Recttz77jQw(Offset.Zero, j);
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        float f5 = layoutDirection == layoutDirection2 ? mo39toPxTmRCtEA : mo39toPxTmRCtEA2;
        long CornerRadius = _BOUNDARY.CornerRadius(f5, f5);
        if (layoutDirection == layoutDirection2) {
            mo39toPxTmRCtEA = mo39toPxTmRCtEA2;
        }
        long CornerRadius2 = _BOUNDARY.CornerRadius(mo39toPxTmRCtEA, mo39toPxTmRCtEA);
        float f6 = layoutDirection == layoutDirection2 ? mo39toPxTmRCtEA3 : mo39toPxTmRCtEA4;
        long CornerRadius3 = _BOUNDARY.CornerRadius(f6, f6);
        if (layoutDirection != layoutDirection2) {
            mo39toPxTmRCtEA4 = mo39toPxTmRCtEA3;
        }
        return new Outline$Rounded(new RoundRect(m4Recttz77jQw.left, m4Recttz77jQw.top, m4Recttz77jQw.right, m4Recttz77jQw.bottom, CornerRadius, CornerRadius2, CornerRadius3, _BOUNDARY.CornerRadius(mo39toPxTmRCtEA4, mo39toPxTmRCtEA4)));
    }
}
