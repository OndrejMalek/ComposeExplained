package androidx.compose.foundation.shape;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class RoundedCornerShape extends CornerBasedShape {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundedCornerShape)) {
            return false;
        }
        RoundedCornerShape roundedCornerShape = (RoundedCornerShape) obj;
        if (!ResultKt.areEqual(this.topStart, roundedCornerShape.topStart)) {
            return false;
        }
        if (!ResultKt.areEqual(this.topEnd, roundedCornerShape.topEnd)) {
            return false;
        }
        if (ResultKt.areEqual(this.bottomEnd, roundedCornerShape.bottomEnd)) {
            return ResultKt.areEqual(this.bottomStart, roundedCornerShape.bottomStart);
        }
        return false;
    }

    public final int hashCode() {
        return this.bottomStart.hashCode() + ((this.bottomEnd.hashCode() + ((this.topEnd.hashCode() + (this.topStart.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "RoundedCornerShape(topStart = " + this.topStart + ", topEnd = " + this.topEnd + ", bottomEnd = " + this.bottomEnd + ", bottomStart = " + this.bottomStart + ')';
    }
}
