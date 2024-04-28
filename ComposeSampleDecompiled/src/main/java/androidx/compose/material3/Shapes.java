package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShape;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Shapes {
    public final CornerBasedShape extraLarge;
    public final CornerBasedShape extraSmall;
    public final CornerBasedShape large;
    public final CornerBasedShape medium;
    public final CornerBasedShape small;

    public Shapes() {
        RoundedCornerShape roundedCornerShape = ShapeDefaults.ExtraSmall;
        RoundedCornerShape roundedCornerShape2 = ShapeDefaults.Small;
        RoundedCornerShape roundedCornerShape3 = ShapeDefaults.Medium;
        RoundedCornerShape roundedCornerShape4 = ShapeDefaults.Large;
        RoundedCornerShape roundedCornerShape5 = ShapeDefaults.ExtraLarge;
        ResultKt.checkNotNullParameter(roundedCornerShape, "extraSmall");
        ResultKt.checkNotNullParameter(roundedCornerShape2, "small");
        ResultKt.checkNotNullParameter(roundedCornerShape3, "medium");
        ResultKt.checkNotNullParameter(roundedCornerShape4, "large");
        ResultKt.checkNotNullParameter(roundedCornerShape5, "extraLarge");
        this.extraSmall = roundedCornerShape;
        this.small = roundedCornerShape2;
        this.medium = roundedCornerShape3;
        this.large = roundedCornerShape4;
        this.extraLarge = roundedCornerShape5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shapes)) {
            return false;
        }
        Shapes shapes = (Shapes) obj;
        return ResultKt.areEqual(this.extraSmall, shapes.extraSmall) && ResultKt.areEqual(this.small, shapes.small) && ResultKt.areEqual(this.medium, shapes.medium) && ResultKt.areEqual(this.large, shapes.large) && ResultKt.areEqual(this.extraLarge, shapes.extraLarge);
    }

    public final int hashCode() {
        return this.extraLarge.hashCode() + ((this.large.hashCode() + ((this.medium.hashCode() + ((this.small.hashCode() + (this.extraSmall.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Shapes(extraSmall=" + this.extraSmall + ", small=" + this.small + ", medium=" + this.medium + ", large=" + this.large + ", extraLarge=" + this.extraLarge + ')';
    }
}
