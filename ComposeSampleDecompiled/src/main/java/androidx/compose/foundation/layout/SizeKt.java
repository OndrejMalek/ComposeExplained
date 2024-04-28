package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import androidx.compose.ui.Modifier;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class SizeKt {
    public static final FillElement FillWholeMaxSize;

    static {
        new FillElement(2, 1.0f, "fillMaxWidth");
        new FillElement(1, 1.0f, "fillMaxHeight");
        FillWholeMaxSize = new FillElement(3, 1.0f, "fillMaxSize");
        BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
        new WrapContentElement(2, new ComposedModifierKt$materialize$result$1(3, horizontal), horizontal, "wrapContentWidth");
        BiasAlignment.Horizontal horizontal2 = Alignment.Companion.Start;
        new WrapContentElement(2, new ComposedModifierKt$materialize$result$1(3, horizontal2), horizontal2, "wrapContentWidth");
        BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
        new WrapContentElement(1, new ComposedModifierKt$materialize$result$1(1, vertical), vertical, "wrapContentHeight");
        BiasAlignment.Vertical vertical2 = Alignment.Companion.Top;
        new WrapContentElement(1, new ComposedModifierKt$materialize$result$1(1, vertical2), vertical2, "wrapContentHeight");
        BiasAlignment biasAlignment = Alignment.Companion.Center;
        new WrapContentElement(3, new ComposedModifierKt$materialize$result$1(2, biasAlignment), biasAlignment, "wrapContentSize");
        BiasAlignment biasAlignment2 = Alignment.Companion.TopStart;
        new WrapContentElement(3, new ComposedModifierKt$materialize$result$1(2, biasAlignment2), biasAlignment2, "wrapContentSize");
    }

    /* renamed from: defaultMinSize-VpY3zN4, reason: not valid java name */
    public static final Modifier m37defaultMinSizeVpY3zN4(float f, float f2) {
        return new UnspecifiedConstraintsElement(f, f2);
    }

    public static Modifier fillMaxSize$default() {
        FillElement fillElement = FillWholeMaxSize;
        ResultKt.checkNotNullParameter(fillElement, "other");
        return fillElement;
    }
}
