package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.TextLayoutResult;
import java.util.ArrayList;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AccessibilityIterators$LineTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$LineTextSegmentIterator lineInstance;
    public TextLayoutResult layoutResult;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        int i2;
        if (getText().length() <= 0 || i >= getText().length()) {
            return null;
        }
        if (i < 0) {
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            i2 = textLayoutResult.getLineForOffset(0);
        } else {
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            int lineForOffset = textLayoutResult2.getLineForOffset(i);
            i2 = getLineEdgeIndex(lineForOffset, 2) == i ? lineForOffset : lineForOffset + 1;
        }
        TextLayoutResult textLayoutResult3 = this.layoutResult;
        if (textLayoutResult3 == null) {
            ResultKt.throwUninitializedPropertyAccessException("layoutResult");
            throw null;
        }
        if (i2 >= textLayoutResult3.multiParagraph.lineCount) {
            return null;
        }
        return getRange(getLineEdgeIndex(i2, 2), getLineEdgeIndex(i2, 1) + 1);
    }

    public final int getLineEdgeIndex(int i, int i2) {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult == null) {
            ResultKt.throwUninitializedPropertyAccessException("layoutResult");
            throw null;
        }
        int lineStart = textLayoutResult.getLineStart(i);
        TextLayoutResult textLayoutResult2 = this.layoutResult;
        if (textLayoutResult2 == null) {
            ResultKt.throwUninitializedPropertyAccessException("layoutResult");
            throw null;
        }
        if (i2 != textLayoutResult2.getParagraphDirection(lineStart)) {
            TextLayoutResult textLayoutResult3 = this.layoutResult;
            if (textLayoutResult3 != null) {
                return textLayoutResult3.getLineStart(i);
            }
            ResultKt.throwUninitializedPropertyAccessException("layoutResult");
            throw null;
        }
        TextLayoutResult textLayoutResult4 = this.layoutResult;
        if (textLayoutResult4 == null) {
            ResultKt.throwUninitializedPropertyAccessException("layoutResult");
            throw null;
        }
        MultiParagraph multiParagraph = textLayoutResult4.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(_BOUNDARY.findParagraphByLineIndex(i, arrayList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int i3 = i - paragraphInfo.startLineIndex;
        return ((androidParagraph.layout.layout.getEllipsisStart(i3) == 0 ? r0.getLineEnd(i3) : r0.getText().length()) + paragraphInfo.startIndex) - 1;
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] preceding(int i) {
        int i2;
        if (getText().length() <= 0 || i <= 0) {
            return null;
        }
        if (i > getText().length()) {
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            i2 = textLayoutResult.getLineForOffset(getText().length());
        } else {
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            int lineForOffset = textLayoutResult2.getLineForOffset(i);
            i2 = getLineEdgeIndex(lineForOffset, 1) + 1 == i ? lineForOffset : lineForOffset - 1;
        }
        if (i2 < 0) {
            return null;
        }
        return getRange(getLineEdgeIndex(i2, 2), getLineEdgeIndex(i2, 1) + 1);
    }
}
