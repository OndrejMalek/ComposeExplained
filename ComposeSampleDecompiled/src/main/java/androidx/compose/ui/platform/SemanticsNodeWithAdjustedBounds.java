package androidx.compose.ui.platform;

import android.graphics.Rect;
import androidx.compose.ui.semantics.SemanticsNode;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class SemanticsNodeWithAdjustedBounds {
    public final Rect adjustedBounds;
    public final SemanticsNode semanticsNode;

    public SemanticsNodeWithAdjustedBounds(SemanticsNode semanticsNode, Rect rect) {
        ResultKt.checkNotNullParameter(semanticsNode, "semanticsNode");
        this.semanticsNode = semanticsNode;
        this.adjustedBounds = rect;
    }
}
