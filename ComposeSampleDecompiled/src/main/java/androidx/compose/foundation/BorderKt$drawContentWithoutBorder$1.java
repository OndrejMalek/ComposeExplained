package androidx.compose.foundation;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class BorderKt$drawContentWithoutBorder$1 extends Lambda implements Function1 {
    public static final BorderKt$drawContentWithoutBorder$1 INSTANCE = new Lambda(1);

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
        ResultKt.checkNotNullParameter(contentDrawScope, "$this$onDrawWithContent");
        ((LayoutNodeDrawScope) contentDrawScope).drawContent();
        return Unit.INSTANCE;
    }
}
