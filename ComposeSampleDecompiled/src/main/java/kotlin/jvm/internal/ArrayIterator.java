package kotlin.jvm.internal;

import androidx.compose.runtime.collection.IdentityArraySet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public class ArrayIterator implements Iterator, KMappedMarker {
    public final /* synthetic */ int $r8$classId;
    public final Object array;
    public int index;

    public /* synthetic */ ArrayIterator(int i, Object obj) {
        this.$r8$classId = i;
        this.array = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.$r8$classId;
        Object obj = this.array;
        switch (i) {
            case 0:
                return this.index < ((Object[]) obj).length;
            case 1:
                return this.index < ((IdentityArraySet) obj).size;
            default:
                return this.index < ((AbstractList) obj).getSize();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.$r8$classId;
        Object obj = this.array;
        switch (i) {
            case 0:
                try {
                    int i2 = this.index;
                    this.index = i2 + 1;
                    return ((Object[]) obj)[i2];
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.index--;
                    throw new NoSuchElementException(e.getMessage());
                }
            case 1:
                Object[] objArr = ((IdentityArraySet) obj).values;
                int i3 = this.index;
                this.index = i3 + 1;
                Object obj2 = objArr[i3];
                ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                return obj2;
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i4 = this.index;
                this.index = i4 + 1;
                return ((AbstractList) obj).get(i4);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.$r8$classId) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public ArrayIterator(Object[] objArr) {
        this.$r8$classId = 0;
        ResultKt.checkNotNullParameter(objArr, "array");
        this.array = objArr;
    }
}
