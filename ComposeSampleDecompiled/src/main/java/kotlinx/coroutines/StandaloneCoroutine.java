package kotlinx.coroutines;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public class StandaloneCoroutine extends AbstractCoroutine {
    @Override // kotlinx.coroutines.JobSupport
    public final boolean handleJobException(Throwable th) {
        ResultKt.handleCoroutineException(this.context, th);
        return true;
    }
}
