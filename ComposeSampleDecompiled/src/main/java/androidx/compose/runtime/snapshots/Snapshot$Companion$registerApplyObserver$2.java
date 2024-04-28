package androidx.compose.runtime.snapshots;

import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class Snapshot$Companion$registerApplyObserver$2 {
    public final /* synthetic */ Function $observer;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ Snapshot$Companion$registerApplyObserver$2(Lambda lambda, int i) {
        this.$r8$classId = i;
        this.$observer = lambda;
    }

    public final void dispose() {
        switch (this.$r8$classId) {
            case 0:
                Function2 function2 = (Function2) this.$observer;
                synchronized (SnapshotKt.lock) {
                    SnapshotKt.applyObservers.remove(function2);
                }
                return;
            default:
                Function1 function1 = (Function1) this.$observer;
                synchronized (SnapshotKt.lock) {
                    SnapshotKt.globalWriteObservers.remove(function1);
                }
                SnapshotKt.advanceGlobalSnapshot(SnapshotKt$emptyLambda$1.INSTANCE$1);
                return;
        }
    }
}
