package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.snapshots.SnapshotWeakSet;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class RecomposeScopeImpl implements RecomposeScope {
    public Anchor anchor;
    public Function2 block;
    public int currentToken;
    public int flags;
    public RecomposeScopeOwner owner;
    public SnapshotWeakSet trackedDependencies;
    public IdentityArrayIntMap trackedInstances;

    public RecomposeScopeImpl(CompositionImpl compositionImpl) {
        this.owner = compositionImpl;
    }

    public final int invalidateForResult(Object obj) {
        int invalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner == null || (invalidate = recomposeScopeOwner.invalidate(this, obj)) == 0) {
            return 1;
        }
        return invalidate;
    }

    public final void setRereading(boolean z) {
        if (z) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }
}
