package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import kotlin.ResultKt;
import kotlin.ULong;

/* loaded from: classes.dex */
public final class ReportFragment extends Fragment {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ProcessLifecycleOwner$initializationListener$1 processListener;

    /* loaded from: classes.dex */
    public final class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        public static final Companion Companion = new Object();

        /* loaded from: classes.dex */
        public final class Companion {
        }

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            ResultKt.checkNotNullParameter(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            ResultKt.checkNotNullParameter(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            ResultKt.checkNotNullParameter(activity, "activity");
            int i = ReportFragment.$r8$clinit;
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            int i = ReportFragment.$r8$clinit;
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            int i = ReportFragment.$r8$clinit;
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            int i = ReportFragment.$r8$clinit;
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            int i = ReportFragment.$r8$clinit;
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            int i = ReportFragment.$r8$clinit;
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, Lifecycle.Event.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            ResultKt.checkNotNullParameter(activity, "activity");
            ResultKt.checkNotNullParameter(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
        }
    }

    public final void dispatch(Lifecycle.Event event) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            ResultKt.checkNotNullExpressionValue(activity, "activity");
            ULong.Companion.dispatch$lifecycle_runtime_release(activity, event);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        dispatch(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        dispatch(Lifecycle.Event.ON_DESTROY);
        this.processListener = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        dispatch(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        ProcessLifecycleOwner$initializationListener$1 processLifecycleOwner$initializationListener$1 = this.processListener;
        if (processLifecycleOwner$initializationListener$1 != null) {
            processLifecycleOwner$initializationListener$1.this$0.activityResumed$lifecycle_process_release();
        }
        dispatch(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        ProcessLifecycleOwner$initializationListener$1 processLifecycleOwner$initializationListener$1 = this.processListener;
        if (processLifecycleOwner$initializationListener$1 != null) {
            ProcessLifecycleOwner processLifecycleOwner = processLifecycleOwner$initializationListener$1.this$0;
            int i = processLifecycleOwner.startedCounter + 1;
            processLifecycleOwner.startedCounter = i;
            if (i == 1 && processLifecycleOwner.stopSent) {
                processLifecycleOwner.registry.handleLifecycleEvent(Lifecycle.Event.ON_START);
                processLifecycleOwner.stopSent = false;
            }
        }
        dispatch(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        dispatch(Lifecycle.Event.ON_STOP);
    }
}
