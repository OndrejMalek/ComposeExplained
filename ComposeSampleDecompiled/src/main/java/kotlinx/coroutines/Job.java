package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public interface Job extends CoroutineContext.Element {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* loaded from: classes.dex */
    public final class Key implements CoroutineContext.Key {
        public static final /* synthetic */ Key $$INSTANCE$1 = new Object();
        public static final /* synthetic */ Key $$INSTANCE = new Object();
    }

    void cancel(CancellationException cancellationException);

    boolean isActive();
}
