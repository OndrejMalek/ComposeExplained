package androidx.compose.ui;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class CombinedModifier$toString$1 extends Lambda implements Function2 {
    public static final CombinedModifier$toString$1 INSTANCE = new Lambda(2);

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        String str = (String) obj;
        Modifier.Element element = (Modifier.Element) obj2;
        ResultKt.checkNotNullParameter(str, "acc");
        ResultKt.checkNotNullParameter(element, "element");
        if (str.length() == 0) {
            return element.toString();
        }
        return str + ", " + element;
    }
}
