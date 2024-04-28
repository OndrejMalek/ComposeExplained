package androidx.compose.runtime;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ComposeRuntimeError extends IllegalStateException {
    public final String message;

    public ComposeRuntimeError(String str) {
        ResultKt.checkNotNullParameter(str, "message");
        this.message = str;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.message;
    }
}
