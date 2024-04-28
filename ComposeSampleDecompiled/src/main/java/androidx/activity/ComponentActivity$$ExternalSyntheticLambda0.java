package androidx.activity;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ComponentActivity f$0;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda0(ComponentActivity componentActivity, int i) {
        this.$r8$classId = i;
        this.f$0 = componentActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ComponentActivity componentActivity = this.f$0;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(componentActivity, "this$0");
                componentActivity.invalidateOptionsMenu();
                return;
            default:
                ResultKt.checkNotNullParameter(componentActivity, "this$0");
                try {
                    ComponentActivity.access$onBackPressed$s1027565324(componentActivity);
                    return;
                } catch (IllegalStateException e) {
                    if (!ResultKt.areEqual(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e;
                    }
                    return;
                } catch (NullPointerException e2) {
                    if (!ResultKt.areEqual(e2.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                        throw e2;
                    }
                    return;
                }
        }
    }
}
