package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class UnsafeLazyImpl implements Lazy, Serializable {
    public Object _value;
    public Function0 initializer;

    @Override // kotlin.Lazy
    public final Object getValue() {
        if (this._value == UNINITIALIZED_VALUE.INSTANCE) {
            Function0 function0 = this.initializer;
            ResultKt.checkNotNull(function0);
            this._value = function0.invoke();
            this.initializer = null;
        }
        return this._value;
    }

    public final String toString() {
        return this._value != UNINITIALIZED_VALUE.INSTANCE ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
