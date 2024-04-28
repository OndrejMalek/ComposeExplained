package androidx.compose.foundation.text.selection;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;

/* loaded from: classes.dex */
public abstract class TextSelectionColorsKt {
    public static final TextSelectionColors DefaultTextSelectionColors;
    public static final DynamicProvidableCompositionLocal LocalTextSelectionColors = _BOUNDARY.compositionLocalOf$default(SelectionRegistrarKt$LocalSelectionRegistrar$1.INSTANCE$1);

    static {
        long Color;
        long Color2 = Brush.Color(4282550004L);
        Color = Brush.Color(Color.m126getRedimpl(Color2), Color.m125getGreenimpl(Color2), Color.m123getBlueimpl(Color2), 0.4f, Color.m124getColorSpaceimpl(Color2));
        DefaultTextSelectionColors = new TextSelectionColors(Color2, Color);
    }
}
