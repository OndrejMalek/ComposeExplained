package kotlin;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class Result implements Serializable {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* loaded from: classes.dex */
    public final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            ResultKt.checkNotNullParameter(th, "exception");
            this.exception = th;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Failure) {
                if (ResultKt.areEqual(this.exception, ((Failure) obj).exception)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return this.exception.hashCode();
        }

        public final String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m292exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }
}
