package androidx.compose.material3;

import _COROUTINE._BOUNDARY;
import androidx.compose.foundation.BorderKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class SurfaceKt {
    public static final DynamicProvidableCompositionLocal LocalAbsoluteTonalElevation = _BOUNDARY.compositionLocalOf$default(ShapesKt$LocalShapes$1.INSTANCE$4);

    /* JADX WARN: Type inference failed for: r9v0, types: [androidx.compose.ui.draw.ShadowKt$shadow$2$1] */
    /* renamed from: access$surface-8ww4TTg */
    public static final Modifier m56access$surface8ww4TTg(Modifier modifier, final Shape shape, long j, final float f) {
        final long j2 = GraphicsLayerScopeKt.DefaultShadowColor;
        ResultKt.checkNotNullParameter(modifier, "$this$shadow");
        ResultKt.checkNotNullParameter(shape, "shape");
        if (Float.compare(f, 0) > 0) {
            final boolean z = false;
            modifier = InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt$NoInspectorInfo$1.INSTANCE, Brush.graphicsLayer(new Function1() { // from class: androidx.compose.ui.draw.ShadowKt$shadow$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) obj;
                    ResultKt.checkNotNullParameter(reusableGraphicsLayerScope, "$this$graphicsLayer");
                    reusableGraphicsLayerScope.shadowElevation = reusableGraphicsLayerScope.graphicsDensity.getDensity() * f;
                    Shape shape2 = shape;
                    ResultKt.checkNotNullParameter(shape2, "<set-?>");
                    reusableGraphicsLayerScope.shape = shape2;
                    reusableGraphicsLayerScope.clip = z;
                    reusableGraphicsLayerScope.ambientShadowColor = j2;
                    reusableGraphicsLayerScope.spotShadowColor = j2;
                    return Unit.INSTANCE;
                }
            }));
        }
        return Brush.m102graphicsLayerAp8cVGQ$default(BorderKt.m26backgroundbw27NRU(modifier.then(Modifier.Companion.$$INSTANCE), j, shape), shape, true, 124927);
    }

    /* renamed from: access$surfaceColorAtElevation-CLU3JFs */
    public static final long m57access$surfaceColorAtElevationCLU3JFs(long j, float f, Composer composer) {
        long Color;
        float f2;
        float f3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-2079918090);
        StaticProvidableCompositionLocal staticProvidableCompositionLocal = ColorSchemeKt.LocalColorScheme;
        if (Color.m121equalsimpl0(j, ((ColorScheme) composerImpl.consume(staticProvidableCompositionLocal)).m53getSurface0d7_KjU())) {
            ColorScheme colorScheme = (ColorScheme) composerImpl.consume(staticProvidableCompositionLocal);
            ResultKt.checkNotNullParameter(colorScheme, "$this$surfaceColorAtElevation");
            if (Dp.m280equalsimpl0(f, 0)) {
                j = colorScheme.m53getSurface0d7_KjU();
            } else {
                Color = Brush.Color(Color.m126getRedimpl(r0), Color.m125getGreenimpl(r0), Color.m123getBlueimpl(r0), ((((float) Math.log(f + 1)) * 4.5f) + 2.0f) / 100.0f, Color.m124getColorSpaceimpl(((Color) colorScheme.surfaceTint$delegate.getValue()).value));
                long m53getSurface0d7_KjU = colorScheme.m53getSurface0d7_KjU();
                long m119convertvNxB06k = Color.m119convertvNxB06k(Color, Color.m124getColorSpaceimpl(m53getSurface0d7_KjU));
                float m122getAlphaimpl = Color.m122getAlphaimpl(m53getSurface0d7_KjU);
                float m122getAlphaimpl2 = Color.m122getAlphaimpl(m119convertvNxB06k);
                float f4 = 1.0f - m122getAlphaimpl2;
                float f5 = (m122getAlphaimpl * f4) + m122getAlphaimpl2;
                float m126getRedimpl = Color.m126getRedimpl(m119convertvNxB06k);
                float m126getRedimpl2 = Color.m126getRedimpl(m53getSurface0d7_KjU);
                float f6 = 0.0f;
                if (f5 == 0.0f) {
                    f2 = 0.0f;
                } else {
                    f2 = (((m126getRedimpl2 * m122getAlphaimpl) * f4) + (m126getRedimpl * m122getAlphaimpl2)) / f5;
                }
                float m125getGreenimpl = Color.m125getGreenimpl(m119convertvNxB06k);
                float m125getGreenimpl2 = Color.m125getGreenimpl(m53getSurface0d7_KjU);
                if (f5 == 0.0f) {
                    f3 = 0.0f;
                } else {
                    f3 = (((m125getGreenimpl2 * m122getAlphaimpl) * f4) + (m125getGreenimpl * m122getAlphaimpl2)) / f5;
                }
                float m123getBlueimpl = Color.m123getBlueimpl(m119convertvNxB06k);
                float m123getBlueimpl2 = Color.m123getBlueimpl(m53getSurface0d7_KjU);
                if (f5 != 0.0f) {
                    f6 = (((m123getBlueimpl2 * m122getAlphaimpl) * f4) + (m123getBlueimpl * m122getAlphaimpl2)) / f5;
                }
                j = Brush.Color(f2, f3, f6, f5, Color.m124getColorSpaceimpl(m53getSurface0d7_KjU));
            }
        }
        composerImpl.end(false);
        return j;
    }
}
