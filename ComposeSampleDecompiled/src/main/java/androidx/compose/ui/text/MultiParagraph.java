package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;
import android.graphics.Matrix;
import android.graphics.Shader;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__ReversedViewsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* loaded from: classes.dex */
public final class MultiParagraph {
    public final boolean didExceedMaxLines;
    public final float height;
    public final MultiParagraphIntrinsics intrinsics;
    public final int lineCount;
    public final int maxLines;
    public final ArrayList paragraphInfoList;
    public final ArrayList placeholderRects;
    public final float width;

    public MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z) {
        boolean z2;
        int m275getMaxHeightimpl;
        this.intrinsics = multiParagraphIntrinsics;
        this.maxLines = i;
        if (Constraints.m278getMinWidthimpl(j) != 0 || Constraints.m277getMinHeightimpl(j) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.".toString());
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = multiParagraphIntrinsics.infoList;
        int size = arrayList2.size();
        int i2 = 0;
        int i3 = 0;
        float f = 0.0f;
        while (i2 < size) {
            ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) arrayList2.get(i2);
            ParagraphIntrinsics paragraphIntrinsics = paragraphIntrinsicInfo.intrinsics;
            int m276getMaxWidthimpl = Constraints.m276getMaxWidthimpl(j);
            if (Constraints.m274getHasBoundedHeightimpl(j)) {
                m275getMaxHeightimpl = Constraints.m275getMaxHeightimpl(j) - ((int) Math.ceil(f));
                if (m275getMaxHeightimpl < 0) {
                    m275getMaxHeightimpl = 0;
                }
            } else {
                m275getMaxHeightimpl = Constraints.m275getMaxHeightimpl(j);
            }
            AndroidParagraph m3Paragraph_EkL_Y = _BOUNDARY.m3Paragraph_EkL_Y(this.maxLines - i3, ResultKt.Constraints$default(m276getMaxWidthimpl, m275getMaxHeightimpl, 5), paragraphIntrinsics, z);
            float height = m3Paragraph_EkL_Y.getHeight() + f;
            TextLayout textLayout = m3Paragraph_EkL_Y.layout;
            int i4 = i3 + textLayout.lineCount;
            arrayList.add(new ParagraphInfo(m3Paragraph_EkL_Y, paragraphIntrinsicInfo.startIndex, paragraphIntrinsicInfo.endIndex, i3, i4, f, height));
            if (textLayout.didExceedMaxLines) {
                i3 = i4;
            } else {
                i3 = i4;
                if (i3 != this.maxLines || i2 == ResultKt.getLastIndex(this.intrinsics.infoList)) {
                    i2++;
                    f = height;
                }
            }
            z2 = true;
            f = height;
            break;
        }
        z2 = false;
        this.height = f;
        this.lineCount = i3;
        this.didExceedMaxLines = z2;
        this.paragraphInfoList = arrayList;
        this.width = Constraints.m276getMaxWidthimpl(j);
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i5 = 0; i5 < size2; i5++) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i5);
            List list = paragraphInfo.paragraph.placeholderRects;
            ArrayList arrayList4 = new ArrayList(list.size());
            int size3 = list.size();
            for (int i6 = 0; i6 < size3; i6++) {
                Rect rect = (Rect) list.get(i6);
                arrayList4.add(rect != null ? rect.m82translatek4lQ0M(_BOUNDARY.Offset(0.0f, paragraphInfo.top)) : null);
            }
            CollectionsKt__ReversedViewsKt.addAll(arrayList4, arrayList3);
        }
        if (arrayList3.size() < this.intrinsics.placeholders.size()) {
            int size4 = this.intrinsics.placeholders.size() - arrayList3.size();
            ArrayList arrayList5 = new ArrayList(size4);
            for (int i7 = 0; i7 < size4; i7++) {
                arrayList5.add(null);
            }
            arrayList3 = CollectionsKt___CollectionsKt.plus(arrayList5, arrayList3);
        }
        this.placeholderRects = arrayList3;
    }

    /* renamed from: paint-LG529CI$default, reason: not valid java name */
    public static void m244paintLG529CI$default(MultiParagraph multiParagraph, Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        multiParagraph.getClass();
        ResultKt.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i);
            paragraphInfo.paragraph.m241paintLG529CI(canvas, j, shadow, textDecoration, drawStyle, 3);
            canvas.translate(0.0f, paragraphInfo.paragraph.getHeight());
        }
        canvas.restore();
    }

    /* renamed from: paint-hn5TExg$default, reason: not valid java name */
    public static void m245painthn5TExg$default(MultiParagraph multiParagraph, Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        multiParagraph.getClass();
        ResultKt.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        ArrayList arrayList = multiParagraph.paragraphInfoList;
        if (arrayList.size() <= 1) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i);
                paragraphInfo.paragraph.m242painthn5TExg(canvas, brush, f, shadow, textDecoration, drawStyle, 3);
                canvas.translate(0.0f, paragraphInfo.paragraph.getHeight());
            }
        } else if (brush instanceof BrushKt$ShaderBrush$1) {
            int size2 = arrayList.size();
            float f2 = 0.0f;
            float f3 = 0.0f;
            for (int i2 = 0; i2 < size2; i2++) {
                ParagraphInfo paragraphInfo2 = (ParagraphInfo) arrayList.get(i2);
                f3 += paragraphInfo2.paragraph.getHeight();
                f2 = Math.max(f2, paragraphInfo2.paragraph.getWidth());
            }
            _BOUNDARY.Size(f2, f3);
            Matrix matrix = new Matrix();
            Shader shader = ((BrushKt$ShaderBrush$1) brush).$shader;
            shader.getLocalMatrix(matrix);
            int size3 = arrayList.size();
            for (int i3 = 0; i3 < size3; i3++) {
                ParagraphInfo paragraphInfo3 = (ParagraphInfo) arrayList.get(i3);
                paragraphInfo3.paragraph.m242painthn5TExg(canvas, new BrushKt$ShaderBrush$1(shader), f, shadow, textDecoration, drawStyle, 3);
                AndroidParagraph androidParagraph = paragraphInfo3.paragraph;
                canvas.translate(0.0f, androidParagraph.getHeight());
                matrix.setTranslate(0.0f, -androidParagraph.getHeight());
                shader.setLocalMatrix(matrix);
            }
        }
        canvas.restore();
    }

    public final void requireLineIndexInRange(int i) {
        int i2 = this.lineCount;
        if (i < 0 || i >= i2) {
            throw new IllegalArgumentException(("lineIndex(" + i + ") is out of bounds [0, " + i2 + ')').toString());
        }
    }
}
