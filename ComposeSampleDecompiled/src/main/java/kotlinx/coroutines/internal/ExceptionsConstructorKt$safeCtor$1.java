package kotlinx.coroutines.internal;

import androidx.compose.runtime.snapshots.ReadonlySnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotIdSet;
import androidx.compose.runtime.snapshots.SnapshotKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ExceptionsConstructorKt$safeCtor$1 extends Lambda implements Function1 {
    public final /* synthetic */ Function1 $block;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExceptionsConstructorKt$safeCtor$1(int i, Function1 function1) {
        super(1);
        this.$r8$classId = i;
        this.$block = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Object obj2;
        int i;
        switch (this.$r8$classId) {
            case 0:
                Throwable th = (Throwable) obj;
                try {
                    Throwable th2 = (Throwable) this.$block.invoke(th);
                    boolean areEqual = ResultKt.areEqual(th.getMessage(), th2.getMessage());
                    obj2 = th2;
                    if (!areEqual) {
                        boolean areEqual2 = ResultKt.areEqual(th2.getMessage(), th.toString());
                        obj2 = th2;
                        if (!areEqual2) {
                            obj2 = null;
                        }
                    }
                } catch (Throwable th3) {
                    obj2 = ResultKt.createFailure(th3);
                }
                return (Throwable) (obj2 instanceof Result.Failure ? null : obj2);
            case 1:
                return this.$block.invoke(Long.valueOf(((Number) obj).longValue()));
            case 2:
                SnapshotIdSet snapshotIdSet = (SnapshotIdSet) obj;
                ResultKt.checkNotNullParameter(snapshotIdSet, "invalid");
                synchronized (SnapshotKt.lock) {
                    i = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId = i + 1;
                }
                return new ReadonlySnapshot(i, snapshotIdSet, this.$block);
            default:
                SnapshotIdSet snapshotIdSet2 = (SnapshotIdSet) obj;
                ResultKt.checkNotNullParameter(snapshotIdSet2, "invalid");
                Snapshot snapshot = (Snapshot) this.$block.invoke(snapshotIdSet2);
                synchronized (SnapshotKt.lock) {
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(snapshot.getId());
                }
                return snapshot;
        }
    }
}
