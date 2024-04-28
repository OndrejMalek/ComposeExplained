package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlinx.coroutines.channels.BufferedChannel;

/* loaded from: classes.dex */
public interface ReceiveChannel {
    void cancel(CancellationException cancellationException);

    BufferedChannel.BufferedChannelIterator iterator();
}
