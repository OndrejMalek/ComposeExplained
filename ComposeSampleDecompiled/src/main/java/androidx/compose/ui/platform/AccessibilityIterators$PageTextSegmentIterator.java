package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.TextLayoutResult;
import java.util.ArrayList;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AccessibilityIterators$PageTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    public static AccessibilityIterators$PageTextSegmentIterator pageInstance;
    public TextLayoutResult layoutResult;
    public SemanticsNode node;

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        int i2;
        if (getText().length() <= 0 || i >= getText().length()) {
            return null;
        }
        try {
            SemanticsNode semanticsNode = this.node;
            if (semanticsNode == null) {
                ResultKt.throwUninitializedPropertyAccessException("node");
                throw null;
            }
            int roundToInt = ResultKt.roundToInt(semanticsNode.getBoundsInRoot().getHeight());
            if (i <= 0) {
                i = 0;
            }
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            int lineForOffset = textLayoutResult.getLineForOffset(i);
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            float lineTop = textLayoutResult2.getLineTop(lineForOffset) + roundToInt;
            TextLayoutResult textLayoutResult3 = this.layoutResult;
            if (textLayoutResult3 == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            if (lineTop < textLayoutResult3.getLineTop(textLayoutResult3.multiParagraph.lineCount - 1)) {
                TextLayoutResult textLayoutResult4 = this.layoutResult;
                if (textLayoutResult4 == null) {
                    ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                    throw null;
                }
                i2 = textLayoutResult4.getLineForVerticalPosition(lineTop);
            } else {
                TextLayoutResult textLayoutResult5 = this.layoutResult;
                if (textLayoutResult5 == null) {
                    ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                    throw null;
                }
                i2 = textLayoutResult5.multiParagraph.lineCount;
            }
            return getRange(i, getLineEdgeIndex$1(i2 - 1, 1) + 1);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public final int getLineEdgeIndex$1(int i, int i2) {
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
        try {
            SemanticsNode semanticsNode = this.node;
            if (semanticsNode == null) {
                ResultKt.throwUninitializedPropertyAccessException("node");
                throw null;
            }
            int roundToInt = ResultKt.roundToInt(semanticsNode.getBoundsInRoot().getHeight());
            int length = getText().length();
            if (length <= i) {
                i = length;
            }
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            int lineForOffset = textLayoutResult.getLineForOffset(i);
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 == null) {
                ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            float lineTop = textLayoutResult2.getLineTop(lineForOffset) - roundToInt;
            if (lineTop > 0.0f) {
                TextLayoutResult textLayoutResult3 = this.layoutResult;
                if (textLayoutResult3 == null) {
                    ResultKt.throwUninitializedPropertyAccessException("layoutResult");
                    throw null;
                }
                i2 = textLayoutResult3.getLineForVerticalPosition(lineTop);
            } else {
                i2 = 0;
            }
            if (i == getText().length() && i2 < lineForOffset) {
                i2++;
            }
            return getRange(getLineEdgeIndex$1(i2, 2), i);
        } catch (IllegalStateException unused) {
            return null;
        }
    }
}
