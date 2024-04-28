package androidx.compose.runtime;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SkippableUpdater {
    public final Composer composer;

    public final boolean equals(Object obj) {
        if (obj instanceof SkippableUpdater) {
            return ResultKt.areEqual(this.composer, ((SkippableUpdater) obj).composer);
        }
        return false;
    }

    public final int hashCode() {
        return this.composer.hashCode();
    }

    public final String toString() {
        return "SkippableUpdater(composer=" + this.composer + ')';
    }
}
