package androidx.compose.runtime.snapshots;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class StateMapMutableIterator {
    public Map.Entry current;
    public final Iterator iterator;
    public final SnapshotStateMap map;
    public int modification;
    public Map.Entry next;

    public StateMapMutableIterator(SnapshotStateMap snapshotStateMap, Iterator it) {
        ResultKt.checkNotNullParameter(snapshotStateMap, "map");
        ResultKt.checkNotNullParameter(it, "iterator");
        this.map = snapshotStateMap;
        this.iterator = it;
        this.modification = snapshotStateMap.getReadable$runtime_release().modification;
        advance();
    }

    public final void advance() {
        this.current = this.next;
        Iterator it = this.iterator;
        this.next = it.hasNext() ? (Map.Entry) it.next() : null;
    }

    public final boolean hasNext() {
        return this.next != null;
    }

    public final void remove() {
        SnapshotStateMap snapshotStateMap = this.map;
        if (snapshotStateMap.getReadable$runtime_release().modification != this.modification) {
            throw new ConcurrentModificationException();
        }
        Map.Entry entry = this.current;
        if (entry == null) {
            throw new IllegalStateException();
        }
        snapshotStateMap.remove(entry.getKey());
        this.current = null;
        this.modification = snapshotStateMap.getReadable$runtime_release().modification;
    }
}
