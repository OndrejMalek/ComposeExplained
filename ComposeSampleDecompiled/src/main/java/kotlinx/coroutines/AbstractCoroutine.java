package kotlinx.coroutines;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.startup.StartupException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.AtomicKt;

/* loaded from: classes.dex */
public abstract class AbstractCoroutine extends JobSupport implements Continuation, CoroutineScope {
    public final CoroutineContext context;

    public AbstractCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(z);
        initParentJob((Job) coroutineContext.get(Job.Key.$$INSTANCE));
        this.context = coroutineContext.plus(this);
    }

    @Override // kotlinx.coroutines.JobSupport
    public final String cancellationExceptionMessage() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(StartupException startupException) {
        ResultKt.handleCoroutineException(this.context, startupException);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    public String nameString$kotlinx_coroutines_core() {
        return super.nameString$kotlinx_coroutines_core();
    }

    public void onCancelled(Throwable th, boolean z) {
    }

    public void onCompleted(Object obj) {
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void onCompletionInternal(Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            onCompleted(obj);
            return;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
        Throwable th = completedExceptionally.cause;
        completedExceptionally.getClass();
        onCancelled(th, CompletedExceptionally._handled$FU.get(completedExceptionally) != 0);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Throwable m292exceptionOrNullimpl = Result.m292exceptionOrNullimpl(obj);
        if (m292exceptionOrNullimpl != null) {
            obj = new CompletedExceptionally(m292exceptionOrNullimpl, false);
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(obj);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        afterResume(makeCompletingOnce$kotlinx_coroutines_core);
    }

    public final void start(int i, AbstractCoroutine abstractCoroutine, Function2 function2) {
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(i);
        if (ordinal == 0) {
            AtomicKt.startCoroutineCancellable$default(function2, abstractCoroutine, this);
            return;
        }
        if (ordinal != 1) {
            if (ordinal == 2) {
                ResultKt.checkNotNullParameter(function2, "<this>");
                ResultKt.intercepted(ResultKt.createCoroutineUnintercepted(abstractCoroutine, this, function2)).resumeWith(Unit.INSTANCE);
                return;
            }
            if (ordinal != 3) {
                throw new RuntimeException();
            }
            try {
                CoroutineContext coroutineContext = this.context;
                Object updateThreadContext = AtomicKt.updateThreadContext(coroutineContext, null);
                try {
                    ResultKt.beforeCheckcastToFunctionOfArity(2, function2);
                    Object invoke = function2.invoke(abstractCoroutine, this);
                    if (invoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        resumeWith(invoke);
                    }
                } finally {
                    AtomicKt.restoreThreadContext(coroutineContext, updateThreadContext);
                }
            } catch (Throwable th) {
                resumeWith(ResultKt.createFailure(th));
            }
        }
    }
}
