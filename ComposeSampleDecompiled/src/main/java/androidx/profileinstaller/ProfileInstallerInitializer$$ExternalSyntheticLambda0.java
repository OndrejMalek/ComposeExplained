package androidx.profileinstaller;

import android.content.Context;
import android.os.Looper;
import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda3;
import androidx.activity.OnBackPressedDispatcher;
import androidx.profileinstaller.ProfileInstallerInitializer;
import java.util.Random;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final /* synthetic */ class ProfileInstallerInitializer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ ProfileInstallerInitializer$$ExternalSyntheticLambda0(Object obj, int i, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$1;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((ProfileInstallerInitializer) obj2).getClass();
                ProfileInstallerInitializer.Handler28Impl.createAsync(Looper.getMainLooper()).postDelayed(new ProfileInstallerInitializer$$ExternalSyntheticLambda1((Context) obj, 0), new Random().nextInt(Math.max(1000, 1)) + 5000);
                return;
            default:
                ComponentActivity componentActivity = (ComponentActivity) obj2;
                OnBackPressedDispatcher onBackPressedDispatcher = (OnBackPressedDispatcher) obj;
                ResultKt.checkNotNullParameter(componentActivity, "this$0");
                ResultKt.checkNotNullParameter(onBackPressedDispatcher, "$dispatcher");
                int i2 = ComponentActivity.$r8$clinit;
                componentActivity.lifecycleRegistry.addObserver(new ComponentActivity$$ExternalSyntheticLambda3(componentActivity, onBackPressedDispatcher));
                return;
        }
    }
}
