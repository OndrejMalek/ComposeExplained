package androidx.compose.ui.text.caches;

import androidx.compose.ui.draw.DrawResult;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;

/* loaded from: classes.dex */
public final class LruCache {
    public int hitCount;
    public int missCount;
    public int size;
    public final DrawResult monitor = new Object();
    public final HashMap map = new HashMap(0, 0.75f);
    public final LinkedHashSet keySet = new LinkedHashSet();

    public final Object get(Object obj) {
        synchronized (this.monitor) {
            Object obj2 = this.map.get(obj);
            if (obj2 == null) {
                this.missCount++;
                return null;
            }
            this.keySet.remove(obj);
            this.keySet.add(obj);
            this.hitCount++;
            return obj2;
        }
    }

    public final Object put(Object obj, Object obj2) {
        Object put;
        Object obj3;
        Object obj4;
        if (obj == null) {
            throw null;
        }
        if (obj2 == null) {
            throw null;
        }
        synchronized (this.monitor) {
            try {
                this.size = size() + 1;
                put = this.map.put(obj, obj2);
                if (put != null) {
                    this.size = size() - 1;
                }
                if (this.keySet.contains(obj)) {
                    this.keySet.remove(obj);
                }
                this.keySet.add(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
        while (true) {
            synchronized (this.monitor) {
                try {
                    if (size() >= 0) {
                        if (this.map.isEmpty() && size() != 0) {
                            break;
                        }
                        if (this.map.isEmpty() != this.keySet.isEmpty()) {
                            break;
                        }
                        if (size() <= 16 || this.map.isEmpty()) {
                            obj3 = null;
                            obj4 = null;
                        } else {
                            Collection collection = this.keySet;
                            ResultKt.checkNotNullParameter(collection, "<this>");
                            if (collection instanceof List) {
                                obj3 = CollectionsKt___CollectionsKt.first((List) collection);
                            } else {
                                Iterator it = collection.iterator();
                                if (!it.hasNext()) {
                                    throw new NoSuchElementException("Collection is empty.");
                                }
                                obj3 = it.next();
                            }
                            obj4 = this.map.get(obj3);
                            if (obj4 == null) {
                                throw new IllegalStateException("inconsistent state");
                            }
                            HashMap hashMap = this.map;
                            ResultKt.asMutableMap(hashMap);
                            hashMap.remove(obj3);
                            LinkedHashSet linkedHashSet = this.keySet;
                            if ((linkedHashSet instanceof KMappedMarker) && !(linkedHashSet instanceof KMutableCollection)) {
                                ResultKt.throwCce(linkedHashSet, "kotlin.collections.MutableCollection");
                                throw null;
                            }
                            linkedHashSet.remove(obj3);
                            int size = size();
                            ResultKt.checkNotNull(obj3);
                            this.size = size - 1;
                        }
                    } else {
                        break;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            if (obj3 == null && obj4 == null) {
                return put;
            }
            ResultKt.checkNotNull(obj3);
            ResultKt.checkNotNull(obj4);
        }
        throw new IllegalStateException("map/keySet size inconsistency");
    }

    public final Object remove(Object obj) {
        Object remove;
        obj.getClass();
        synchronized (this.monitor) {
            remove = this.map.remove(obj);
            this.keySet.remove(obj);
            if (remove != null) {
                this.size = size() - 1;
            }
        }
        return remove;
    }

    public final int size() {
        int i;
        synchronized (this.monitor) {
            i = this.size;
        }
        return i;
    }

    public final String toString() {
        String str;
        synchronized (this.monitor) {
            try {
                int i = this.hitCount;
                int i2 = this.missCount + i;
                str = "LruCache[maxSize=16,hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + (i2 != 0 ? (i * 100) / i2 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
