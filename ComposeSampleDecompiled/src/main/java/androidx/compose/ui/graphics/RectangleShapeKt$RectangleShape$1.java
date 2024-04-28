package androidx.compose.ui.graphics;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class RectangleShapeKt$RectangleShape$1 implements Shape {
    @Override // androidx.compose.ui.graphics.Shape
    /* renamed from: createOutline-Pq9zytI */
    public final Brush mo38createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
        ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
        ResultKt.checkNotNullParameter(density, "density");
        return new Outline$Rectangle(_BOUNDARY.m4Recttz77jQw(Offset.Zero, j));
    }

    public final String toString() {
        return "RectangleShape";
    }
}
