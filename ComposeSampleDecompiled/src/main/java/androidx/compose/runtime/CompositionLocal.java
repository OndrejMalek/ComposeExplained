package androidx.compose.runtime;

import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public abstract class CompositionLocal {
    public final LazyValueHolder defaultValueHolder;

    public CompositionLocal(Function0 function0) {
        this.defaultValueHolder = new LazyValueHolder(function0);
    }

    public abstract State provided$runtime_release(Object obj, Composer composer);
}
