package androidx.compose.runtime.snapshots;

import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMutableMap;

/* loaded from: classes.dex */
public final class StateMapMutableEntriesIterator$next$1 implements Map.Entry, KMutableMap.Entry {
    public final Object key;
    public final /* synthetic */ StateMapMutableKeysIterator this$0;
    public Object value;

    public StateMapMutableEntriesIterator$next$1(StateMapMutableKeysIterator stateMapMutableKeysIterator) {
        this.this$0 = stateMapMutableKeysIterator;
        Map.Entry entry = stateMapMutableKeysIterator.current;
        ResultKt.checkNotNull(entry);
        this.key = entry.getKey();
        Map.Entry entry2 = stateMapMutableKeysIterator.current;
        ResultKt.checkNotNull(entry2);
        this.value = entry2.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        StateMapMutableKeysIterator stateMapMutableKeysIterator = this.this$0;
        if (stateMapMutableKeysIterator.map.getReadable$runtime_release().modification != stateMapMutableKeysIterator.modification) {
            throw new ConcurrentModificationException();
        }
        Object obj2 = this.value;
        stateMapMutableKeysIterator.map.put(this.key, obj);
        this.value = obj;
        return obj2;
    }
}
