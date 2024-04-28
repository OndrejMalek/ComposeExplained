package androidx.compose.runtime.snapshots;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SnapshotKt$mergedReadObserver$1 extends Lambda implements Function1 {
    public final /* synthetic */ Function1 $parentObserver;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Function1 $readObserver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SnapshotKt$mergedReadObserver$1(Function1 function1, Function1 function12, int i) {
        super(1);
        this.$r8$classId = i;
        this.$readObserver = function1;
        this.$parentObserver = function12;
    }

    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function1.invoke(Ljava/lang/Object;)Ljava/lang/Object; */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m60invoke(Object obj) {
        int i = this.$r8$classId;
        Function1 function1 = this.$parentObserver;
        Function1 function12 = this.$readObserver;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(obj, "state");
                function12.invoke(obj);
                function1.invoke(obj);
                return;
            case 1:
            default:
                ResultKt.checkNotNullParameter(obj, "state");
                function12.invoke(obj);
                function1.invoke(obj);
                return;
            case 2:
                ResultKt.checkNotNullParameter(obj, "state");
                function12.invoke(obj);
                function1.invoke(obj);
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int i;
        switch (this.$r8$classId) {
            case 0:
                m60invoke(obj);
                return Unit.INSTANCE;
            case 1:
                SnapshotIdSet snapshotIdSet = (SnapshotIdSet) obj;
                ResultKt.checkNotNullParameter(snapshotIdSet, "invalid");
                synchronized (SnapshotKt.lock) {
                    i = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId = i + 1;
                }
                return new MutableSnapshot(i, snapshotIdSet, this.$readObserver, this.$parentObserver);
            case 2:
                m60invoke(obj);
                return Unit.INSTANCE;
            default:
                m60invoke(obj);
                return Unit.INSTANCE;
        }
    }
}
