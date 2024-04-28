package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ComposerImpl$derivedStateObserver$1 implements DerivedStateObserver {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ComposerImpl$derivedStateObserver$1(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    public final void done(DerivedSnapshotState derivedSnapshotState) {
        int i = this.$r8$classId;
        Object obj = this.this$0;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(derivedSnapshotState, "derivedState");
                ComposerImpl composerImpl = (ComposerImpl) obj;
                composerImpl.childrenComposing--;
                return;
            default:
                ResultKt.checkNotNullParameter(derivedSnapshotState, "derivedState");
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = (SnapshotStateObserver.ObservedScopeMap) obj;
                observedScopeMap.deriveStateScopeCount--;
                return;
        }
    }

    public final void start(DerivedSnapshotState derivedSnapshotState) {
        int i = this.$r8$classId;
        Object obj = this.this$0;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(derivedSnapshotState, "derivedState");
                ((ComposerImpl) obj).childrenComposing++;
                return;
            default:
                ResultKt.checkNotNullParameter(derivedSnapshotState, "derivedState");
                ((SnapshotStateObserver.ObservedScopeMap) obj).deriveStateScopeCount++;
                return;
        }
    }
}
