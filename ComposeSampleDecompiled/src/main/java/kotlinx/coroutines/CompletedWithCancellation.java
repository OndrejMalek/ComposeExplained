package kotlinx.coroutines;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class CompletedWithCancellation {
    public final Function1 onCancellation;
    public final Object result;

    public CompletedWithCancellation(Object obj, Function1 function1) {
        this.result = obj;
        this.onCancellation = function1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedWithCancellation)) {
            return false;
        }
        CompletedWithCancellation completedWithCancellation = (CompletedWithCancellation) obj;
        return ResultKt.areEqual(this.result, completedWithCancellation.result) && ResultKt.areEqual(this.onCancellation, completedWithCancellation.onCancellation);
    }

    public final int hashCode() {
        Object obj = this.result;
        return this.onCancellation.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public final String toString() {
        return "CompletedWithCancellation(result=" + this.result + ", onCancellation=" + this.onCancellation + ')';
    }
}
