package kotlinx.coroutines.channels;

import kotlinx.coroutines.JobKt;

/* loaded from: classes.dex */
public interface Channel extends SendChannel, ReceiveChannel {
    public static final Factory Factory = Factory.$$INSTANCE;

    /* loaded from: classes.dex */
    public final class Factory {
        public static final /* synthetic */ Factory $$INSTANCE = new Object();
        public static final int CHANNEL_DEFAULT_CAPACITY = (int) JobKt.systemProp("kotlinx.coroutines.channels.defaultBuffer", 64, 1, 2147483646);
    }
}
