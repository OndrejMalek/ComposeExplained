package androidx.compose.runtime;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class JoinedKey {
    public final Object left;
    public final Object right;

    public JoinedKey(Integer num, Object obj) {
        this.left = num;
        this.right = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JoinedKey)) {
            return false;
        }
        JoinedKey joinedKey = (JoinedKey) obj;
        return ResultKt.areEqual(this.left, joinedKey.left) && ResultKt.areEqual(this.right, joinedKey.right);
    }

    public final int hashCode() {
        Object obj = this.left;
        int i = 0;
        int ordinal = (obj instanceof Enum ? ((Enum) obj).ordinal() : obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.right;
        if (obj2 instanceof Enum) {
            i = ((Enum) obj2).ordinal();
        } else if (obj2 != null) {
            i = obj2.hashCode();
        }
        return i + ordinal;
    }

    public final String toString() {
        return "JoinedKey(left=" + this.left + ", right=" + this.right + ')';
    }
}
