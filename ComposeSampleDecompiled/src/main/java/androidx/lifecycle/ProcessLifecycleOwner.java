package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0;
import androidx.lifecycle.Lifecycle;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ProcessLifecycleOwner implements LifecycleOwner {
    public static final ProcessLifecycleOwner newInstance = new ProcessLifecycleOwner();
    public Handler handler;
    public int resumedCounter;
    public int startedCounter;
    public boolean pauseSent = true;
    public boolean stopSent = true;
    public final LifecycleRegistry registry = new LifecycleRegistry(this);
    public final FullyDrawnReporter$$ExternalSyntheticLambda0 delayedPauseRunnable = new FullyDrawnReporter$$ExternalSyntheticLambda0(6, this);
    public final ProcessLifecycleOwner$initializationListener$1 initializationListener = new ProcessLifecycleOwner$initializationListener$1(this);

    /* loaded from: classes.dex */
    public abstract class Api29Impl {
        public static final void registerActivityLifecycleCallbacks(Activity activity, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            ResultKt.checkNotNullParameter(activity, "activity");
            ResultKt.checkNotNullParameter(activityLifecycleCallbacks, "callback");
            activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    public final void activityResumed$lifecycle_process_release() {
        int i = this.resumedCounter + 1;
        this.resumedCounter = i;
        if (i == 1) {
            if (this.pauseSent) {
                this.registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
                this.pauseSent = false;
            } else {
                Handler handler = this.handler;
                ResultKt.checkNotNull(handler);
                handler.removeCallbacks(this.delayedPauseRunnable);
            }
        }
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final LifecycleRegistry getLifecycle() {
        return this.registry;
    }
}
