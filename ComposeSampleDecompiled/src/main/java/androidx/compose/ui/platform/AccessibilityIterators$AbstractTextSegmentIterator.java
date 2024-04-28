package androidx.compose.ui.platform;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class AccessibilityIterators$AbstractTextSegmentIterator implements AccessibilityIterators$TextSegmentIterator {
    public final int[] segment = new int[2];
    public String text;

    public final int[] getRange(int i, int i2) {
        if (i < 0 || i2 < 0 || i == i2) {
            return null;
        }
        int[] iArr = this.segment;
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public final String getText() {
        String str = this.text;
        if (str != null) {
            return str;
        }
        ResultKt.throwUninitializedPropertyAccessException("text");
        throw null;
    }
}
