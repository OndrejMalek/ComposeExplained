package androidx.compose.runtime.snapshots;

import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SnapshotStateList$retainAll$1 extends Lambda implements Function1 {
    public final /* synthetic */ Collection $elements;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SnapshotStateList$retainAll$1(int i, Collection collection) {
        super(1);
        this.$r8$classId = i;
        this.$elements = collection;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Collection<?> collection = this.$elements;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                List list = (List) obj;
                ResultKt.checkNotNullParameter(list, "it");
                return Boolean.valueOf(list.retainAll(collection));
            case 1:
                switch (i) {
                    case 1:
                        return Boolean.valueOf(collection.contains(obj));
                    default:
                        return Boolean.valueOf(collection.contains(obj));
                }
            default:
                switch (i) {
                    case 1:
                        return Boolean.valueOf(collection.contains(obj));
                    default:
                        return Boolean.valueOf(collection.contains(obj));
                }
        }
    }
}
