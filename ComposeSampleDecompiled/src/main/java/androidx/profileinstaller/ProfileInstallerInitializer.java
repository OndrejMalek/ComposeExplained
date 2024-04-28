package androidx.profileinstaller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import kotlin.ULong;

/* loaded from: classes.dex */
public class ProfileInstallerInitializer implements Initializer {

    /* loaded from: classes.dex */
    public abstract class Choreographer16Impl {
        public static void postFrameCallback(final Runnable runnable) {
            final int i = 1;
            Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0
                @Override // android.view.Choreographer.FrameCallback
                public final void doFrame(long j) {
                    int i2 = i;
                    Runnable runnable2 = runnable;
                    switch (i2) {
                        case 0:
                            runnable2.run();
                            return;
                        default:
                            runnable2.run();
                            return;
                    }
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public abstract class Handler28Impl {
        public static Handler createAsync(Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    @Override // androidx.startup.Initializer
    public final Object create(Context context) {
        Choreographer16Impl.postFrameCallback(new ProfileInstallerInitializer$$ExternalSyntheticLambda0(this, 0, context.getApplicationContext()));
        return new ULong.Companion(21);
    }

    @Override // androidx.startup.Initializer
    public final List dependencies() {
        return Collections.emptyList();
    }
}
