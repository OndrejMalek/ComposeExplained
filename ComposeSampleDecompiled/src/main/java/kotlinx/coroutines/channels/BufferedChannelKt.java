package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public abstract class BufferedChannelKt {
    public static final ChannelSegment NULL_SEGMENT = new ChannelSegment(-1, null, null, 0);
    public static final int SEGMENT_SIZE = JobKt.systemProp$default("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12);
    public static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = JobKt.systemProp$default("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12);
    public static final Symbol BUFFERED = new Symbol(0, "BUFFERED");
    public static final Symbol IN_BUFFER = new Symbol(0, "SHOULD_BUFFER");
    public static final Symbol RESUMING_BY_RCV = new Symbol(0, "S_RESUMING_BY_RCV");
    public static final Symbol RESUMING_BY_EB = new Symbol(0, "RESUMING_BY_EB");
    public static final Symbol POISONED = new Symbol(0, "POISONED");
    public static final Symbol DONE_RCV = new Symbol(0, "DONE_RCV");
    public static final Symbol INTERRUPTED_SEND = new Symbol(0, "INTERRUPTED_SEND");
    public static final Symbol INTERRUPTED_RCV = new Symbol(0, "INTERRUPTED_RCV");
    public static final Symbol CHANNEL_CLOSED = new Symbol(0, "CHANNEL_CLOSED");
    public static final Symbol SUSPEND = new Symbol(0, "SUSPEND");
    public static final Symbol SUSPEND_NO_WAITER = new Symbol(0, "SUSPEND_NO_WAITER");
    public static final Symbol FAILED = new Symbol(0, "FAILED");
    public static final Symbol NO_RECEIVE_RESULT = new Symbol(0, "NO_RECEIVE_RESULT");
    public static final Symbol CLOSE_HANDLER_CLOSED = new Symbol(0, "CLOSE_HANDLER_CLOSED");
    public static final Symbol CLOSE_HANDLER_INVOKED = new Symbol(0, "CLOSE_HANDLER_INVOKED");
    public static final Symbol NO_CLOSE_CAUSE = new Symbol(0, "NO_CLOSE_CAUSE");

    public static final boolean tryResume0(CancellableContinuation cancellableContinuation, Object obj, Function1 function1) {
        Symbol tryResume = cancellableContinuation.tryResume(obj, function1);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }
}
