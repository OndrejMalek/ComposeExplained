package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class DynamicProvidableCompositionLocal extends ProvidableCompositionLocal {
    public final SnapshotMutationPolicy policy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicProvidableCompositionLocal(SnapshotMutationPolicy snapshotMutationPolicy, Function0 function0) {
        super(function0);
        ResultKt.checkNotNullParameter(snapshotMutationPolicy, "policy");
        this.policy = snapshotMutationPolicy;
    }

    @Override // androidx.compose.runtime.CompositionLocal
    public final State provided$runtime_release(Object obj, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-84026900);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.Empty) {
            nextSlot = ResultKt.mutableStateOf(obj, this.policy);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.end(false);
        MutableState mutableState = (MutableState) nextSlot;
        mutableState.setValue(obj);
        composerImpl.end(false);
        return mutableState;
    }
}
