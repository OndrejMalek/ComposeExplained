package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;

/* loaded from: classes.dex */
public interface SendChannel {
    boolean close(Throwable th);

    Object send(Object obj, Continuation continuation);

    /* renamed from: trySend-JP2dKIU */
    Object mo306trySendJP2dKIU(Object obj);
}
