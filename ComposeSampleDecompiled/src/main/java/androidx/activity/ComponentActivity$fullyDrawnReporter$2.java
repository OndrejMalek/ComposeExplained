package androidx.activity;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.profileinstaller.ProfileInstallerInitializer$$ExternalSyntheticLambda0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComponentActivity$fullyDrawnReporter$2 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ComponentActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComponentActivity$fullyDrawnReporter$2(ComponentActivity componentActivity, int i) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = componentActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        int i = this.$r8$classId;
        ComponentActivity componentActivity = this.this$0;
        switch (i) {
            case 0:
                return new FullyDrawnReporter(componentActivity.reportFullyDrawnExecutor, new ComponentActivity$fullyDrawnReporter$2(componentActivity, 2));
            case 1:
                return new SavedStateViewModelFactory(componentActivity.getApplication(), componentActivity, componentActivity.getIntent() != null ? componentActivity.getIntent().getExtras() : null);
            case 2:
                componentActivity.reportFullyDrawn();
                return Unit.INSTANCE;
            default:
                OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher(new ComponentActivity$$ExternalSyntheticLambda0(componentActivity, 1));
                if (Build.VERSION.SDK_INT >= 33) {
                    if (ResultKt.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                        componentActivity.getClass();
                        componentActivity.lifecycleRegistry.addObserver(new ComponentActivity$$ExternalSyntheticLambda3(componentActivity, onBackPressedDispatcher));
                    } else {
                        new Handler(Looper.getMainLooper()).post(new ProfileInstallerInitializer$$ExternalSyntheticLambda0(componentActivity, 1, onBackPressedDispatcher));
                    }
                }
                return onBackPressedDispatcher;
        }
    }
}
