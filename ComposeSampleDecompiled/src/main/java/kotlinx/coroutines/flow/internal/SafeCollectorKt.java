package kotlinx.coroutines.flow.internal;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function3;

/* loaded from: classes.dex */
public abstract class SafeCollectorKt {
    public static final Function3 emitFun;

    static {
        SafeCollectorKt$emitFun$1 safeCollectorKt$emitFun$1 = SafeCollectorKt$emitFun$1.INSTANCE;
        ResultKt.beforeCheckcastToFunctionOfArity(3, safeCollectorKt$emitFun$1);
        emitFun = safeCollectorKt$emitFun$1;
    }
}
