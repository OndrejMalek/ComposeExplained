package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

/* loaded from: classes.dex */
public final class TrieNodeKeysIterator extends TrieNodeBaseIterator {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.$r8$classId) {
            case 0:
                int i = this.index;
                this.index = i + 2;
                return this.buffer[i];
            case 1:
                int i2 = this.index;
                this.index = i2 + 2;
                Object[] objArr = this.buffer;
                return new MapEntry(objArr[i2], objArr[i2 + 1]);
            default:
                int i3 = this.index;
                this.index = i3 + 2;
                return this.buffer[i3 + 1];
        }
    }
}
