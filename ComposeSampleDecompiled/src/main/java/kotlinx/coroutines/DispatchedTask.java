package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TasksKt;

/* loaded from: classes.dex */
public abstract class DispatchedTask extends Task {
    public int resumeMode;

    public DispatchedTask(int i) {
        super(0L, TasksKt.NonBlockingContext);
        this.resumeMode = i;
    }

    public abstract void cancelCompletedResult$kotlinx_coroutines_core(Object obj, CancellationException cancellationException);

    public abstract Continuation getDelegate$kotlinx_coroutines_core();

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    public Object getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj;
    }

    public final void handleFatalException(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            ResultKt.addSuppressed(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        ResultKt.checkNotNull(th);
        ResultKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new Error("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = Unit.INSTANCE;
        ULong.Companion companion = this.taskContext;
        try {
            Continuation delegate$kotlinx_coroutines_core = getDelegate$kotlinx_coroutines_core();
            ResultKt.checkNotNull(delegate$kotlinx_coroutines_core, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) delegate$kotlinx_coroutines_core;
            Continuation continuation = dispatchedContinuation.continuation;
            Object obj2 = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation.getContext();
            Object updateThreadContext = AtomicKt.updateThreadContext(context, obj2);
            UndispatchedCoroutine updateUndispatchedCompletion = updateThreadContext != AtomicKt.NO_THREAD_ELEMENTS ? ResultKt.updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
            try {
                CoroutineContext context2 = continuation.getContext();
                Object takeState$kotlinx_coroutines_core = takeState$kotlinx_coroutines_core();
                Throwable exceptionalResult$kotlinx_coroutines_core = getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
                Job job = (exceptionalResult$kotlinx_coroutines_core == null && ResultKt.isCancellableMode(this.resumeMode)) ? (Job) context2.get(Job.Key.$$INSTANCE) : null;
                if (job != null && !job.isActive()) {
                    CancellationException cancellationException = ((JobSupport) job).getCancellationException();
                    cancelCompletedResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core, cancellationException);
                    continuation.resumeWith(ResultKt.createFailure(cancellationException));
                } else if (exceptionalResult$kotlinx_coroutines_core != null) {
                    continuation.resumeWith(ResultKt.createFailure(exceptionalResult$kotlinx_coroutines_core));
                } else {
                    continuation.resumeWith(getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core));
                }
                if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                    AtomicKt.restoreThreadContext(context, updateThreadContext);
                }
                try {
                    companion.getClass();
                } catch (Throwable th) {
                    obj = ResultKt.createFailure(th);
                }
                handleFatalException(null, Result.m292exceptionOrNullimpl(obj));
            } catch (Throwable th2) {
                if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                    AtomicKt.restoreThreadContext(context, updateThreadContext);
                }
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                companion.getClass();
            } catch (Throwable th4) {
                obj = ResultKt.createFailure(th4);
            }
            handleFatalException(th3, Result.m292exceptionOrNullimpl(obj));
        }
    }

    public abstract Object takeState$kotlinx_coroutines_core();
}
