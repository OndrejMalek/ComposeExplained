package androidx.compose.runtime.saveable;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public abstract class SaverKt {
    public static final SaverKt$Saver$1 Saver(Function2 function2, Function1 function1) {
        return new SaverKt$Saver$1(function2, function1);
    }
}
