package androidx.compose.runtime;

import kotlin.SynchronizedLazyImpl;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class LazyValueHolder implements State {
    public final SynchronizedLazyImpl current$delegate;

    public LazyValueHolder(Function0 function0) {
        this.current$delegate = new SynchronizedLazyImpl(function0);
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return this.current$delegate.getValue();
    }
}
