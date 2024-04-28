package androidx.compose.runtime;

import android.view.Choreographer;
import androidx.compose.runtime.Recomposer;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupport;

/* loaded from: classes.dex */
public final class Recomposer$effectJob$1$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Recomposer$effectJob$1$1(int i, Object obj) {
        super(1);
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    public final void invoke(Throwable th) {
        switch (this.$r8$classId) {
            case 0:
                CancellationException cancellationException = new CancellationException("Recomposer effect job completed");
                cancellationException.initCause(th);
                Recomposer recomposer = (Recomposer) this.this$0;
                synchronized (recomposer.stateLock) {
                    try {
                        Job job = recomposer.runnerJob;
                        if (job != null) {
                            recomposer._state.setValue(Recomposer.State.ShuttingDown);
                            job.cancel(cancellationException);
                            recomposer.workContinuation = null;
                            ((JobSupport) job).invokeOnCompletion(false, true, new Latch$await$2$2(recomposer, 3, th));
                        } else {
                            recomposer.closeCause = cancellationException;
                            recomposer._state.setValue(Recomposer.State.ShutDown);
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                return;
            default:
                DefaultChoreographerFrameClock.choreographer.removeFrameCallback((Choreographer.FrameCallback) this.this$0);
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Throwable) obj);
                return unit;
            case 1:
                ResultKt.checkNotNullParameter(obj, "value");
                ((CompositionImpl) this.this$0).recordReadOf(obj);
                return unit;
            default:
                invoke((Throwable) obj);
                return unit;
        }
    }
}
