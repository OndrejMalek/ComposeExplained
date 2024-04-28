package kotlinx.coroutines;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class InvokeOnCompletion extends JobNode {
    public final /* synthetic */ int $r8$classId;
    public final Object handler;

    public InvokeOnCompletion(int i, Object obj) {
        this.$r8$classId = i;
        this.handler = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Throwable) obj);
                return unit;
            case 1:
                invoke((Throwable) obj);
                return unit;
            case 2:
                invoke((Throwable) obj);
                return unit;
            default:
                invoke((Throwable) obj);
                return unit;
        }
    }

    @Override // kotlinx.coroutines.JobNode
    public final void invoke(Throwable th) {
        int i = this.$r8$classId;
        Object obj = this.handler;
        switch (i) {
            case 0:
                ((Function1) obj).invoke(th);
                return;
            case 1:
                ((DisposableHandle) obj).dispose();
                return;
            case 2:
                Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
                if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                    ((CancellableContinuationImpl) obj).resumeWith(ResultKt.createFailure(((CompletedExceptionally) state$kotlinx_coroutines_core).cause));
                    return;
                } else {
                    ((CancellableContinuationImpl) obj).resumeWith(JobKt.unboxState(state$kotlinx_coroutines_core));
                    return;
                }
            default:
                ((Continuation) obj).resumeWith(Unit.INSTANCE);
                return;
        }
    }
}
