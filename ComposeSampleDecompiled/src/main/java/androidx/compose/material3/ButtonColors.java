package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;

/* loaded from: classes.dex */
public final class ButtonColors {
    public final long containerColor;
    public final long contentColor;
    public final long disabledContainerColor;
    public final long disabledContentColor;

    public ButtonColors(long j, long j2, long j3, long j4) {
        this.containerColor = j;
        this.contentColor = j2;
        this.disabledContainerColor = j3;
        this.disabledContentColor = j4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ButtonColors)) {
            return false;
        }
        ButtonColors buttonColors = (ButtonColors) obj;
        return Color.m121equalsimpl0(this.containerColor, buttonColors.containerColor) && Color.m121equalsimpl0(this.contentColor, buttonColors.contentColor) && Color.m121equalsimpl0(this.disabledContainerColor, buttonColors.disabledContainerColor) && Color.m121equalsimpl0(this.disabledContentColor, buttonColors.disabledContentColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.disabledContentColor) + ((Long.hashCode(this.disabledContainerColor) + ((Long.hashCode(this.contentColor) + (Long.hashCode(this.containerColor) * 31)) * 31)) * 31);
    }
}
