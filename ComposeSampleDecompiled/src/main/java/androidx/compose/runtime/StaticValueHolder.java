package androidx.compose.runtime;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class StaticValueHolder implements State {
    public final Object value;

    public StaticValueHolder(Object obj) {
        this.value = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StaticValueHolder) && ResultKt.areEqual(this.value, ((StaticValueHolder) obj).value);
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return this.value;
    }

    public final int hashCode() {
        Object obj = this.value;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "StaticValueHolder(value=" + this.value + ')';
    }
}
