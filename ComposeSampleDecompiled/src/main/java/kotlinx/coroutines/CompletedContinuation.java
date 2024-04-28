package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class CompletedContinuation {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function1 onCancellation;
    public final Object result;

    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th) {
        this.result = obj;
        this.cancelHandler = cancelHandler;
        this.onCancellation = function1;
        this.idempotentResume = obj2;
        this.cancelCause = th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.Throwable] */
    public static CompletedContinuation copy$default(CompletedContinuation completedContinuation, CancelHandler cancelHandler, CancellationException cancellationException, int i) {
        Object obj = completedContinuation.result;
        if ((i & 2) != 0) {
            cancelHandler = completedContinuation.cancelHandler;
        }
        CancelHandler cancelHandler2 = cancelHandler;
        Function1 function1 = completedContinuation.onCancellation;
        Object obj2 = completedContinuation.idempotentResume;
        CancellationException cancellationException2 = cancellationException;
        if ((i & 16) != 0) {
            cancellationException2 = completedContinuation.cancelCause;
        }
        completedContinuation.getClass();
        return new CompletedContinuation(obj, cancelHandler2, function1, obj2, cancellationException2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        return ResultKt.areEqual(this.result, completedContinuation.result) && ResultKt.areEqual(this.cancelHandler, completedContinuation.cancelHandler) && ResultKt.areEqual(this.onCancellation, completedContinuation.onCancellation) && ResultKt.areEqual(this.idempotentResume, completedContinuation.idempotentResume) && ResultKt.areEqual(this.cancelCause, completedContinuation.cancelCause);
    }

    public final int hashCode() {
        Object obj = this.result;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler = this.cancelHandler;
        int hashCode2 = (hashCode + (cancelHandler == null ? 0 : cancelHandler.hashCode())) * 31;
        Function1 function1 = this.onCancellation;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.idempotentResume;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.cancelCause;
        return hashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=" + this.idempotentResume + ", cancelCause=" + this.cancelCause + ')';
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, CancellationException cancellationException, int i) {
        this(obj, (i & 2) != 0 ? null : cancelHandler, (i & 4) != 0 ? null : function1, (Object) null, (i & 16) != 0 ? null : cancellationException);
    }
}
