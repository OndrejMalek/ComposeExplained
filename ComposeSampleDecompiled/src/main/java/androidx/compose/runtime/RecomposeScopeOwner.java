package androidx.compose.runtime;

/* loaded from: classes.dex */
public interface RecomposeScopeOwner {
    int invalidate(RecomposeScopeImpl recomposeScopeImpl, Object obj);

    void recomposeScopeReleased(RecomposeScopeImpl recomposeScopeImpl);

    void recordReadOf(Object obj);
}
