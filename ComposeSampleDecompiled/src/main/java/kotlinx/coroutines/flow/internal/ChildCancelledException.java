package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;

/* loaded from: classes.dex */
public final class ChildCancelledException extends CancellationException {
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChildCancelledException(int i) {
        super("Child of the scoped flow was cancelled");
        this.$r8$classId = i;
        if (i == 1) {
            super("Mutation interrupted");
            return;
        }
        if (i == 2) {
            super("The coroutine scope left the composition");
            return;
        }
        if (i == 3) {
            super("The Modifier.Node was detached");
        } else if (i != 4) {
        } else {
            super("Pointer input was reset");
        }
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        switch (this.$r8$classId) {
            case 0:
                setStackTrace(new StackTraceElement[0]);
                return this;
            case 1:
                setStackTrace(new StackTraceElement[0]);
                return this;
            case 2:
                setStackTrace(new StackTraceElement[0]);
                return this;
            case 3:
                setStackTrace(new StackTraceElement[0]);
                return this;
            default:
                setStackTrace(new StackTraceElement[0]);
                return this;
        }
    }
}
