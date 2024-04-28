package androidx.compose.ui.text;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class VerbatimTtsAnnotation extends TtsAnnotation {
    public final String verbatim;

    public VerbatimTtsAnnotation(String str) {
        ResultKt.checkNotNullParameter(str, "verbatim");
        this.verbatim = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VerbatimTtsAnnotation) {
            return ResultKt.areEqual(this.verbatim, ((VerbatimTtsAnnotation) obj).verbatim);
        }
        return false;
    }

    public final int hashCode() {
        return this.verbatim.hashCode();
    }

    public final String toString() {
        return "VerbatimTtsAnnotation(verbatim=" + this.verbatim + ')';
    }
}
