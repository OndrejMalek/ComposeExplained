package androidx.compose.foundation;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class BorderKt$drawRectBorder$1 extends Lambda implements Function1 {
    public final /* synthetic */ Brush $brush;
    public final /* synthetic */ long $rectTopLeft;
    public final /* synthetic */ long $size;
    public final /* synthetic */ DrawStyle $style;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BorderKt$drawRectBorder$1(Brush brush, long j, long j2, DrawStyle drawStyle) {
        super(1);
        this.$brush = brush;
        this.$rectTopLeft = j;
        this.$size = j2;
        this.$style = drawStyle;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
        ResultKt.checkNotNullParameter(contentDrawScope, "$this$onDrawWithContent");
        ((LayoutNodeDrawScope) contentDrawScope).drawContent();
        DrawScope.m144drawRectAsUm42w$default(contentDrawScope, this.$brush, this.$rectTopLeft, this.$size, 0.0f, this.$style, 104);
        return Unit.INSTANCE;
    }
}
