package androidx.compose.ui.input.pointer;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidPointerIconType implements PointerIcon {
    public final int type;

    public AndroidPointerIconType(int i) {
        this.type = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!ResultKt.areEqual(AndroidPointerIconType.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.AndroidPointerIconType");
        return this.type == ((AndroidPointerIconType) obj).type;
    }

    public final int hashCode() {
        return this.type;
    }

    public final String toString() {
        return "AndroidPointerIcon(type=" + this.type + ')';
    }
}
