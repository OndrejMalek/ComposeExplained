package kotlinx.coroutines.channels;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class ChannelResult {
    public static final Failed failed = new Object();

    /* loaded from: classes.dex */
    public final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable th) {
            this.cause = th;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Closed) {
                if (ResultKt.areEqual(this.cause, ((Closed) obj).cause)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public final String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    /* loaded from: classes.dex */
    public class Failed {
        public String toString() {
            return "Failed";
        }
    }
}
