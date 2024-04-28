package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import kotlin.ULong;

/* loaded from: classes.dex */
public abstract class Table {
    public ByteBuffer bb;
    public int bb_pos;
    public int vtable_size;
    public int vtable_start;

    public Table() {
        if (ULong.Companion.DEFAULT == null) {
            ULong.Companion.DEFAULT = new ULong.Companion();
        }
    }

    public final int __offset(int i) {
        if (i < this.vtable_size) {
            return this.bb.getShort(this.vtable_start + i);
        }
        return 0;
    }
}
