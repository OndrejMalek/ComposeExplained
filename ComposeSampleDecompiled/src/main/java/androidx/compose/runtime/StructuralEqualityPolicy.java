package androidx.compose.runtime;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class StructuralEqualityPolicy implements SnapshotMutationPolicy {
    public static final StructuralEqualityPolicy INSTANCE = new Object();

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public final boolean equivalent(Object obj, Object obj2) {
        return ResultKt.areEqual(obj, obj2);
    }

    public final String toString() {
        return "StructuralEqualityPolicy";
    }
}
