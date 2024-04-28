package androidx.compose.ui.platform;

import java.text.BreakIterator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AccessibilityIterators$CharacterTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$CharacterTextSegmentIterator instance;
    public BreakIterator impl;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        int length = getText().length();
        if (length <= 0 || i >= length) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        do {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                ResultKt.throwUninitializedPropertyAccessException("impl");
                throw null;
            }
            if (breakIterator.isBoundary(i)) {
                BreakIterator breakIterator2 = this.impl;
                if (breakIterator2 == null) {
                    ResultKt.throwUninitializedPropertyAccessException("impl");
                    throw null;
                }
                int following = breakIterator2.following(i);
                if (following == -1) {
                    return null;
                }
                return getRange(i, following);
            }
            BreakIterator breakIterator3 = this.impl;
            if (breakIterator3 == null) {
                ResultKt.throwUninitializedPropertyAccessException("impl");
                throw null;
            }
            i = breakIterator3.following(i);
        } while (i != -1);
        return null;
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

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] preceding(int i) {
        int length = getText().length();
        if (length <= 0 || i <= 0) {
            return null;
        }
        if (i > length) {
            i = length;
        }
        do {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                ResultKt.throwUninitializedPropertyAccessException("impl");
                throw null;
            }
            if (breakIterator.isBoundary(i)) {
                BreakIterator breakIterator2 = this.impl;
                if (breakIterator2 == null) {
                    ResultKt.throwUninitializedPropertyAccessException("impl");
                    throw null;
                }
                int preceding = breakIterator2.preceding(i);
                if (preceding == -1) {
                    return null;
                }
                return getRange(preceding, i);
            }
            BreakIterator breakIterator3 = this.impl;
            if (breakIterator3 == null) {
                ResultKt.throwUninitializedPropertyAccessException("impl");
                throw null;
            }
            i = breakIterator3.preceding(i);
        } while (i != -1);
        return null;
    }
}
