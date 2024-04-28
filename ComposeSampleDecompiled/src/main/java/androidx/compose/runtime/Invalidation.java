package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArraySet;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Invalidation {
    public IdentityArraySet instances;
    public final int location;
    public final RecomposeScopeImpl scope;

    public Invalidation(RecomposeScopeImpl recomposeScopeImpl, int i, IdentityArraySet identityArraySet) {
        ResultKt.checkNotNullParameter(recomposeScopeImpl, "scope");
        this.scope = recomposeScopeImpl;
        this.location = i;
        this.instances = identityArraySet;
    }
}
