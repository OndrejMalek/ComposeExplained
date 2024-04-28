package androidx.compose.foundation.text.selection;

import androidx.compose.ui.graphics.Color;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class TextSelectionColors {
    public final long backgroundColor;
    public final long handleColor;

    public TextSelectionColors(long j, long j2) {
        this.handleColor = j;
        this.backgroundColor = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextSelectionColors)) {
            return false;
        }
        TextSelectionColors textSelectionColors = (TextSelectionColors) obj;
        return Color.m121equalsimpl0(this.handleColor, textSelectionColors.handleColor) && Color.m121equalsimpl0(this.backgroundColor, textSelectionColors.backgroundColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.backgroundColor) + (Long.hashCode(this.handleColor) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SelectionColors(selectionHandleColor=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.handleColor, sb, ", selectionBackgroundColor=");
        sb.append((Object) Color.m127toStringimpl(this.backgroundColor));
        sb.append(')');
        return sb.toString();
    }
}
