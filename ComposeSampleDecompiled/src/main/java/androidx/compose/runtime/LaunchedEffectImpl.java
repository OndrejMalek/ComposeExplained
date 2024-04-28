package androidx.compose.runtime;

import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.internal.ContextScope;

/* loaded from: classes.dex */
public final class LaunchedEffectImpl implements RememberObserver {
    public StandaloneCoroutine job;
    public final ContextScope scope;
    public final Function2 task;

    public LaunchedEffectImpl(CoroutineContext coroutineContext, Function2 function2) {
        ResultKt.checkNotNullParameter(coroutineContext, "parentCoroutineContext");
        ResultKt.checkNotNullParameter(function2, "task");
        this.task = function2;
        this.scope = ResultKt.CoroutineScope(coroutineContext);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onAbandoned() {
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(new ChildCancelledException(2));
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onForgotten() {
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(new ChildCancelledException(2));
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public final void onRemembered() {
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine != null) {
            CancellationException cancellationException = new CancellationException("Old job was still running!");
            cancellationException.initCause(null);
            standaloneCoroutine.cancel(cancellationException);
        }
        this.job = ResultKt.launch$default(this.scope, null, 0, this.task, 3);
    }
}
