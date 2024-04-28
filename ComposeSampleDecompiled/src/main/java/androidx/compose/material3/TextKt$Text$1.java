package androidx.compose.material3;

import androidx.compose.ui.text.TextLayoutResult;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class TextKt$Text$1 extends Lambda implements Function1 {
    public static final TextKt$Text$1 INSTANCE = new Lambda(1);

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ResultKt.checkNotNullParameter((TextLayoutResult) obj, "it");
        return Unit.INSTANCE;
    }
}
