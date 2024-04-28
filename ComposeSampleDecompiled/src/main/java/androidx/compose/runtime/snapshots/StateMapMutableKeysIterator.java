package androidx.compose.runtime.snapshots;

import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class StateMapMutableKeysIterator extends StateMapMutableIterator implements Iterator, KMappedMarker {
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StateMapMutableKeysIterator(SnapshotStateMap snapshotStateMap, Iterator it, int i) {
        super(snapshotStateMap, it);
        this.$r8$classId = i;
        if (i == 1) {
            ResultKt.checkNotNullParameter(snapshotStateMap, "map");
            ResultKt.checkNotNullParameter(it, "iterator");
            super(snapshotStateMap, it);
        } else if (i != 2) {
            ResultKt.checkNotNullParameter(snapshotStateMap, "map");
            ResultKt.checkNotNullParameter(it, "iterator");
        } else {
            ResultKt.checkNotNullParameter(snapshotStateMap, "map");
            ResultKt.checkNotNullParameter(it, "iterator");
            super(snapshotStateMap, it);
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.$r8$classId) {
            case 0:
                Map.Entry entry = this.next;
                if (entry == null) {
                    throw new IllegalStateException();
                }
                advance();
                return entry.getKey();
            case 1:
                advance();
                if (this.current != null) {
                    return new StateMapMutableEntriesIterator$next$1(this);
                }
                throw new IllegalStateException();
            default:
                Map.Entry entry2 = this.next;
                if (entry2 == null) {
                    throw new IllegalStateException();
                }
                advance();
                return entry2.getValue();
        }
    }
}
