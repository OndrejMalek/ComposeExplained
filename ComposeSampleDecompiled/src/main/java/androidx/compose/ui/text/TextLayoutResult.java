package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextLayoutResult {
    public final float firstBaseline;
    public final float lastBaseline;
    public final TextLayoutInput layoutInput;
    public final MultiParagraph multiParagraph;
    public final List placeholderRects;
    public final long size;

    public TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j) {
        ResultKt.checkNotNullParameter(multiParagraph, "multiParagraph");
        this.layoutInput = textLayoutInput;
        this.multiParagraph = multiParagraph;
        this.size = j;
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        float f = 0.0f;
        this.firstBaseline = arrayList.isEmpty() ? 0.0f : ((ParagraphInfo) arrayList.get(0)).paragraph.layout.getLineBaseline(0);
        if (!arrayList.isEmpty()) {
            if (arrayList.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(ResultKt.getLastIndex(arrayList));
            f = paragraphInfo.paragraph.layout.getLineBaseline(r4.lineCount - 1) + paragraphInfo.top;
        }
        this.lastBaseline = f;
        this.placeholderRects = multiParagraph.placeholderRects;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutResult)) {
            return false;
        }
        TextLayoutResult textLayoutResult = (TextLayoutResult) obj;
        return ResultKt.areEqual(this.layoutInput, textLayoutResult.layoutInput) && ResultKt.areEqual(this.multiParagraph, textLayoutResult.multiParagraph) && this.size == textLayoutResult.size && this.firstBaseline == textLayoutResult.firstBaseline && this.lastBaseline == textLayoutResult.lastBaseline && ResultKt.areEqual(this.placeholderRects, textLayoutResult.placeholderRects);
    }

    public final int getLineForOffset(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        int length = multiParagraph.intrinsics.annotatedString.text.length();
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i >= length ? ResultKt.getLastIndex(arrayList) : i < 0 ? 0 : _BOUNDARY.findParagraphByIndex(i, arrayList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int i2 = paragraphInfo.startIndex;
        return androidParagraph.layout.layout.getLineForOffset(ResultKt.coerceIn(i, i2, paragraphInfo.endIndex) - i2) + paragraphInfo.startLineIndex;
    }

    public final int getLineForVerticalPosition(float f) {
        MultiParagraph multiParagraph = this.multiParagraph;
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(f <= 0.0f ? 0 : f >= multiParagraph.height ? ResultKt.getLastIndex(arrayList) : _BOUNDARY.findParagraphByY(arrayList, f));
        int i = paragraphInfo.endIndex;
        int i2 = paragraphInfo.startIndex;
        if (i - i2 == 0) {
            return Math.max(0, i2 - 1);
        }
        float f2 = f - paragraphInfo.top;
        TextLayout textLayout = paragraphInfo.paragraph.layout;
        return textLayout.layout.getLineForVertical(((int) f2) - textLayout.topPadding) + paragraphInfo.startLineIndex;
    }

    public final int getLineStart(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(_BOUNDARY.findParagraphByLineIndex(i, arrayList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        return androidParagraph.layout.layout.getLineStart(i - paragraphInfo.startLineIndex) + paragraphInfo.startIndex;
    }

    public final float getLineTop(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        multiParagraph.requireLineIndexInRange(i);
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(_BOUNDARY.findParagraphByLineIndex(i, arrayList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        return androidParagraph.layout.getLineTop(i - paragraphInfo.startLineIndex) + paragraphInfo.top;
    }

    public final int getParagraphDirection(int i) {
        MultiParagraph multiParagraph = this.multiParagraph;
        MultiParagraphIntrinsics multiParagraphIntrinsics = multiParagraph.intrinsics;
        if (i < 0 || i > multiParagraphIntrinsics.annotatedString.text.length()) {
            throw new IllegalArgumentException(("offset(" + i + ") is out of bounds [0, " + multiParagraphIntrinsics.annotatedString.text.length() + ']').toString());
        }
        int length = multiParagraphIntrinsics.annotatedString.text.length();
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i == length ? ResultKt.getLastIndex(arrayList) : _BOUNDARY.findParagraphByIndex(i, arrayList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int i2 = paragraphInfo.startIndex;
        int coerceIn = ResultKt.coerceIn(i, i2, paragraphInfo.endIndex) - i2;
        TextLayout textLayout = androidParagraph.layout;
        return textLayout.layout.getParagraphDirection(textLayout.layout.getLineForOffset(coerceIn)) == 1 ? 1 : 2;
    }

    public final int hashCode() {
        return this.placeholderRects.hashCode() + AnimationEndReason$EnumUnboxingLocalUtility.m(this.lastBaseline, AnimationEndReason$EnumUnboxingLocalUtility.m(this.firstBaseline, (Long.hashCode(this.size) + ((this.multiParagraph.hashCode() + (this.layoutInput.hashCode() * 31)) * 31)) * 31, 31), 31);
    }

    public final String toString() {
        return "TextLayoutResult(layoutInput=" + this.layoutInput + ", multiParagraph=" + this.multiParagraph + ", size=" + ((Object) IntSize.m282toStringimpl(this.size)) + ", firstBaseline=" + this.firstBaseline + ", lastBaseline=" + this.lastBaseline + ", placeholderRects=" + this.placeholderRects + ')';
    }
}
