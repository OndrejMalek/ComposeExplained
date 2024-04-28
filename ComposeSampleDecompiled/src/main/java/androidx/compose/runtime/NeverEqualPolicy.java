package androidx.compose.runtime;

/* loaded from: classes.dex */
public final class NeverEqualPolicy implements SnapshotMutationPolicy {
    public static final NeverEqualPolicy INSTANCE = new Object();

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public final boolean equivalent(Object obj, Object obj2) {
        return false;
    }

    public final String toString() {
        return "NeverEqualPolicy";
    }
}
