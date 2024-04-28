package androidx.compose.ui;

import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class CombinedModifier implements Modifier {
    public final Modifier inner;
    public final Modifier outer;

    public CombinedModifier(Modifier modifier, Modifier modifier2) {
        ResultKt.checkNotNullParameter(modifier, "outer");
        ResultKt.checkNotNullParameter(modifier2, "inner");
        this.outer = modifier;
        this.inner = modifier2;
    }

    @Override // androidx.compose.ui.Modifier
    public final boolean all(Function1 function1) {
        return this.outer.all(function1) && this.inner.all(function1);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof CombinedModifier) {
            CombinedModifier combinedModifier = (CombinedModifier) obj;
            if (ResultKt.areEqual(this.outer, combinedModifier.outer) && ResultKt.areEqual(this.inner, combinedModifier.inner)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.Modifier
    public final Object foldIn(Object obj, Function2 function2) {
        return this.inner.foldIn(this.outer.foldIn(obj, function2), function2);
    }

    public final int hashCode() {
        return (this.inner.hashCode() * 31) + this.outer.hashCode();
    }

    public final String toString() {
        return "[" + ((String) foldIn("", CombinedModifier$toString$1.INSTANCE)) + ']';
    }
}
