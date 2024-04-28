package kotlin.jvm.internal;

import kotlin.jvm.functions.Function0;
import kotlin.reflect.KCallable;
import kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1;

/* loaded from: classes.dex */
public abstract class PropertyReference0Impl extends PropertyReference implements Function0 {
    @Override // kotlin.jvm.internal.CallableReference
    public final KCallable computeReflected() {
        Reflection.factory.getClass();
        return this;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return ((LockFreeLinkedListNode$toString$1) this).receiver.getClass().getSimpleName();
    }
}
