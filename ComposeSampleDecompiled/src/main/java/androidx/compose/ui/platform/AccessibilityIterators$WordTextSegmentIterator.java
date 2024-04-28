package androidx.compose.ui.platform;

import java.text.BreakIterator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AccessibilityIterators$WordTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$WordTextSegmentIterator instance;
    public BreakIterator impl;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        if (getText().length() <= 0 || i >= getText().length()) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        while (!isLetterOrDigit(i) && (!isLetterOrDigit(i) || (i != 0 && isLetterOrDigit(i - 1)))) {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                ResultKt.throwUninitializedPropertyAccessException("impl");
                throw null;
            }
            i = breakIterator.following(i);
            if (i == -1) {
                return null;
            }
        }
        BreakIterator breakIterator2 = this.impl;
        if (breakIterator2 == null) {
            ResultKt.throwUninitializedPropertyAccessException("impl");
            throw null;
        }
        int following = breakIterator2.following(i);
        if (following == -1 || !isEndBoundary$1(following)) {
            return null;
        }
        return getRange(i, following);
    }

    public final void initialize(String str) {
        ResultKt.checkNotNullParameter(str, "text");
        this.text = str;
        BreakIterator breakIterator = this.impl;
        if (breakIterator != null) {
            breakIterator.setText(str);
        } else {
            ResultKt.throwUninitializedPropertyAccessException("impl");
            throw null;
        }
    }

    public final boolean isEndBoundary$1(int i) {
        return i > 0 && isLetterOrDigit(i + (-1)) && (i == getText().length() || !isLetterOrDigit(i));
    }

    public final boolean isLetterOrDigit(int i) {
        if (i < 0 || i >= getText().length()) {
            return false;
        }
        return Character.isLetterOrDigit(getText().codePointAt(i));
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] preceding(int i) {
        int length = getText().length();
        if (length <= 0 || i <= 0) {
            return null;
        }
        if (i > length) {
            i = length;
        }
        while (i > 0 && !isLetterOrDigit(i - 1) && !isEndBoundary$1(i)) {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                ResultKt.throwUninitializedPropertyAccessException("impl");
                throw null;
            }
            i = breakIterator.preceding(i);
            if (i == -1) {
                return null;
            }
        }
        BreakIterator breakIterator2 = this.impl;
        if (breakIterator2 == null) {
            ResultKt.throwUninitializedPropertyAccessException("impl");
            throw null;
        }
        int preceding = breakIterator2.preceding(i);
        if (preceding == -1 || !isLetterOrDigit(preceding) || (preceding != 0 && isLetterOrDigit(preceding - 1))) {
            return null;
        }
        return getRange(preceding, i);
    }
}
