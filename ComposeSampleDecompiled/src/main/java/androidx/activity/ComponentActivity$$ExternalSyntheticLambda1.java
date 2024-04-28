package androidx.activity;

import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda1 implements LifecycleEventObserver {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Window window;
        View peekDecorView;
        switch (this.$r8$classId) {
            case 0:
                ComponentActivity componentActivity = (ComponentActivity) this.f$0;
                ResultKt.checkNotNullParameter(componentActivity, "this$0");
                if (event != Lifecycle.Event.ON_STOP || (window = componentActivity.getWindow()) == null || (peekDecorView = window.peekDecorView()) == null) {
                    return;
                }
                peekDecorView.cancelPendingInputEvents();
                return;
            case 1:
                ComponentActivity componentActivity2 = (ComponentActivity) this.f$0;
                ResultKt.checkNotNullParameter(componentActivity2, "this$0");
                if (event == Lifecycle.Event.ON_DESTROY) {
                    componentActivity2.contextAwareHelper.context = null;
                    if (!componentActivity2.isChangingConfigurations()) {
                        componentActivity2.getViewModelStore().clear();
                    }
                    ComponentActivity.ReportFullyDrawnExecutorImpl reportFullyDrawnExecutorImpl = componentActivity2.reportFullyDrawnExecutor;
                    ComponentActivity componentActivity3 = ComponentActivity.this;
                    componentActivity3.getWindow().getDecorView().removeCallbacks(reportFullyDrawnExecutorImpl);
                    componentActivity3.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(reportFullyDrawnExecutorImpl);
                    return;
                }
                return;
            default:
                SavedStateRegistry savedStateRegistry = (SavedStateRegistry) this.f$0;
                ResultKt.checkNotNullParameter(savedStateRegistry, "this$0");
                if (event == Lifecycle.Event.ON_START) {
                    savedStateRegistry.isAllowingSavingState = true;
                    return;
                } else {
                    if (event == Lifecycle.Event.ON_STOP) {
                        savedStateRegistry.isAllowingSavingState = false;
                        return;
                    }
                    return;
                }
        }
    }
}
