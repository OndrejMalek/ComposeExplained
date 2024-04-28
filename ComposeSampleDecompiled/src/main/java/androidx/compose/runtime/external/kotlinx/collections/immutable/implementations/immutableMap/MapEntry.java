package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Map;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public class MapEntry implements Map.Entry, KMappedMarker {
    public final Object key;
    public final Object value;

    public MapEntry(Object obj, Object obj2) {
        this.key = obj;
        this.value = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Map.Entry entry = obj instanceof Map.Entry ? (Map.Entry) obj : null;
        return entry != null && ResultKt.areEqual(entry.getKey(), this.key) && ResultKt.areEqual(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.key;
        int hashCode = obj != null ? obj.hashCode() : 0;
        Object value = getValue();
        return (value != null ? value.hashCode() : 0) ^ hashCode;
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: (wrap:java.lang.Object:IGET), 61, (wrap:java.lang.Object:INVOKE) */
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.key);
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
