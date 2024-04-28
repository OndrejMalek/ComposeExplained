package kotlinx.coroutines.internal;

import _COROUTINE._BOUNDARY;

/* loaded from: classes.dex */
public abstract class OpDescriptor {
    public abstract Object perform(Object obj);

    public final String toString() {
        return getClass().getSimpleName() + '@' + _BOUNDARY.getHexAddress(this);
    }
}
