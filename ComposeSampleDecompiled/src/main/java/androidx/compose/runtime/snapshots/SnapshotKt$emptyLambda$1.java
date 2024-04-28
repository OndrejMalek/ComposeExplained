package androidx.compose.runtime.snapshots;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SnapshotKt$emptyLambda$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final SnapshotKt$emptyLambda$1 INSTANCE$1 = new SnapshotKt$emptyLambda$1(1);
    public static final SnapshotKt$emptyLambda$1 INSTANCE = new SnapshotKt$emptyLambda$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SnapshotKt$emptyLambda$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                SnapshotIdSet snapshotIdSet = (SnapshotIdSet) obj;
                switch (i) {
                    case 0:
                        ResultKt.checkNotNullParameter(snapshotIdSet, "it");
                        return unit;
                    default:
                        ResultKt.checkNotNullParameter(snapshotIdSet, "it");
                        return unit;
                }
            default:
                SnapshotIdSet snapshotIdSet2 = (SnapshotIdSet) obj;
                switch (i) {
                    case 0:
                        ResultKt.checkNotNullParameter(snapshotIdSet2, "it");
                        return unit;
                    default:
                        ResultKt.checkNotNullParameter(snapshotIdSet2, "it");
                        return unit;
                }
        }
    }
}
