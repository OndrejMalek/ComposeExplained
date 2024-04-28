package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.NoSuchElementException;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class BufferIterator extends AbstractListIterator {
    public final /* synthetic */ int $r8$classId = 0;
    public final Object buffer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferIterator(int i, int i2, Object[] objArr) {
        super(i, i2);
        ResultKt.checkNotNullParameter(objArr, "buffer");
        this.buffer = objArr;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i = this.$r8$classId;
        Object obj = this.buffer;
        switch (i) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i2 = this.index;
                this.index = i2 + 1;
                return ((Object[]) obj)[i2];
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.index++;
                return obj;
        }
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        int i = this.$r8$classId;
        Object obj = this.buffer;
        switch (i) {
            case 0:
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                int i2 = this.index - 1;
                this.index = i2;
                return ((Object[]) obj)[i2];
            default:
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                this.index--;
                return obj;
        }
    }

    public BufferIterator(int i, Object obj) {
        super(i, 1);
        this.buffer = obj;
    }
}
