package androidx.compose.runtime;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ProvidedValue {
    public final boolean canOverride;
    public final CompositionLocal compositionLocal;
    public final Object value;

    public ProvidedValue(CompositionLocal compositionLocal, Object obj, boolean z) {
        ResultKt.checkNotNullParameter(compositionLocal, "compositionLocal");
        this.compositionLocal = compositionLocal;
        this.value = obj;
        this.canOverride = z;
    }
}
