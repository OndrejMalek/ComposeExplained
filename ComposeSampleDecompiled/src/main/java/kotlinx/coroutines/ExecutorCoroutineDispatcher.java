package kotlinx.coroutines;

import java.io.Closeable;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable {
    static {
        ResultKt.checkNotNullParameter(CoroutineDispatcher.Key, "baseKey");
    }
}
