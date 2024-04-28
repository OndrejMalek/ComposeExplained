package androidx.compose.runtime;

/* loaded from: classes.dex */
public final class StaticProvidableCompositionLocal extends ProvidableCompositionLocal {
    @Override // androidx.compose.runtime.CompositionLocal
    public final State provided$runtime_release(Object obj, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1121811719);
        StaticValueHolder staticValueHolder = new StaticValueHolder(obj);
        composerImpl.end(false);
        return staticValueHolder;
    }
}
