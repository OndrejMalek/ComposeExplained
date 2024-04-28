package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.ResultKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class MultiParagraphIntrinsics implements ParagraphIntrinsics {
    public final AnnotatedString annotatedString;
    public final ArrayList infoList;
    public final Lazy maxIntrinsicWidth$delegate;
    public final Lazy minIntrinsicWidth$delegate;
    public final List placeholders;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:77:0x00e9 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.ArrayList] */
    public MultiParagraphIntrinsics(AnnotatedString annotatedString, TextStyle textStyle, List list, Density density, FontFamily.Resolver resolver) {
        int i;
        String str;
        ?? r6;
        int i2;
        int i3;
        String str2;
        EmptyList emptyList;
        EmptyList emptyList2;
        ArrayList arrayList;
        ArrayList arrayList2;
        String str3;
        int i4;
        int i5;
        int i6;
        AnnotatedString annotatedString2 = annotatedString;
        ResultKt.checkNotNullParameter(annotatedString2, "annotatedString");
        ResultKt.checkNotNullParameter(textStyle, "style");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        this.annotatedString = annotatedString2;
        this.placeholders = list;
        final int i7 = 1;
        this.minIntrinsicWidth$delegate = ResultKt.lazy(new Function0(this) { // from class: androidx.compose.ui.text.MultiParagraphIntrinsics$maxIntrinsicWidth$2
            public final /* synthetic */ MultiParagraphIntrinsics this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                switch (i7) {
                    case 0:
                        return invoke$2();
                    default:
                        return invoke$2();
                }
            }

            /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:0x0041 */
            /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0089 */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v10 */
            /* JADX WARN: Type inference failed for: r2v13 */
            /* JADX WARN: Type inference failed for: r2v16 */
            /* JADX WARN: Type inference failed for: r2v2 */
            /* JADX WARN: Type inference failed for: r2v4 */
            /* JADX WARN: Type inference failed for: r2v5 */
            /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v7 */
            /* JADX WARN: Type inference failed for: r2v9 */
            public final Float invoke$2() {
                ParagraphIntrinsics paragraphIntrinsics;
                ParagraphIntrinsics paragraphIntrinsics2;
                int i8 = i7;
                float f = 0.0f;
                ParagraphIntrinsicInfo paragraphIntrinsicInfo = null;
                MultiParagraphIntrinsics multiParagraphIntrinsics = this.this$0;
                int i9 = 1;
                switch (i8) {
                    case 0:
                        ArrayList arrayList3 = multiParagraphIntrinsics.infoList;
                        if (!arrayList3.isEmpty()) {
                            ?? r2 = arrayList3.get(0);
                            float maxIntrinsicWidth = ((ParagraphIntrinsicInfo) r2).intrinsics.getMaxIntrinsicWidth();
                            int lastIndex = ResultKt.getLastIndex(arrayList3);
                            boolean z = r2;
                            if (1 <= lastIndex) {
                                while (true) {
                                    Object obj = arrayList3.get(i9);
                                    float maxIntrinsicWidth2 = ((ParagraphIntrinsicInfo) obj).intrinsics.getMaxIntrinsicWidth();
                                    r2 = z;
                                    if (Float.compare(maxIntrinsicWidth, maxIntrinsicWidth2) < 0) {
                                        r2 = obj;
                                        maxIntrinsicWidth = maxIntrinsicWidth2;
                                    }
                                    if (i9 != lastIndex) {
                                        i9++;
                                        z = r2;
                                    }
                                }
                            }
                            paragraphIntrinsicInfo = r2;
                        }
                        ParagraphIntrinsicInfo paragraphIntrinsicInfo2 = paragraphIntrinsicInfo;
                        if (paragraphIntrinsicInfo2 != null && (paragraphIntrinsics = paragraphIntrinsicInfo2.intrinsics) != null) {
                            f = paragraphIntrinsics.getMaxIntrinsicWidth();
                        }
                        return Float.valueOf(f);
                    default:
                        ArrayList arrayList4 = multiParagraphIntrinsics.infoList;
                        if (!arrayList4.isEmpty()) {
                            ?? r22 = arrayList4.get(0);
                            float minIntrinsicWidth = ((ParagraphIntrinsicInfo) r22).intrinsics.getMinIntrinsicWidth();
                            int lastIndex2 = ResultKt.getLastIndex(arrayList4);
                            boolean z2 = r22;
                            if (1 <= lastIndex2) {
                                while (true) {
                                    Object obj2 = arrayList4.get(i9);
                                    float minIntrinsicWidth2 = ((ParagraphIntrinsicInfo) obj2).intrinsics.getMinIntrinsicWidth();
                                    r22 = z2;
                                    if (Float.compare(minIntrinsicWidth, minIntrinsicWidth2) < 0) {
                                        r22 = obj2;
                                        minIntrinsicWidth = minIntrinsicWidth2;
                                    }
                                    if (i9 != lastIndex2) {
                                        i9++;
                                        z2 = r22;
                                    }
                                }
                            }
                            paragraphIntrinsicInfo = r22;
                        }
                        ParagraphIntrinsicInfo paragraphIntrinsicInfo3 = paragraphIntrinsicInfo;
                        if (paragraphIntrinsicInfo3 != null && (paragraphIntrinsics2 = paragraphIntrinsicInfo3.intrinsics) != null) {
                            f = paragraphIntrinsics2.getMinIntrinsicWidth();
                        }
                        return Float.valueOf(f);
                }
            }
        });
        final int i8 = 0;
        this.maxIntrinsicWidth$delegate = ResultKt.lazy(new Function0(this) { // from class: androidx.compose.ui.text.MultiParagraphIntrinsics$maxIntrinsicWidth$2
            public final /* synthetic */ MultiParagraphIntrinsics this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                switch (i8) {
                    case 0:
                        return invoke$2();
                    default:
                        return invoke$2();
                }
            }

            /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:0x0041 */
            /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0089 */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v10 */
            /* JADX WARN: Type inference failed for: r2v13 */
            /* JADX WARN: Type inference failed for: r2v16 */
            /* JADX WARN: Type inference failed for: r2v2 */
            /* JADX WARN: Type inference failed for: r2v4 */
            /* JADX WARN: Type inference failed for: r2v5 */
            /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v7 */
            /* JADX WARN: Type inference failed for: r2v9 */
            public final Float invoke$2() {
                ParagraphIntrinsics paragraphIntrinsics;
                ParagraphIntrinsics paragraphIntrinsics2;
                int i82 = i8;
                float f = 0.0f;
                ParagraphIntrinsicInfo paragraphIntrinsicInfo = null;
                MultiParagraphIntrinsics multiParagraphIntrinsics = this.this$0;
                int i9 = 1;
                switch (i82) {
                    case 0:
                        ArrayList arrayList3 = multiParagraphIntrinsics.infoList;
                        if (!arrayList3.isEmpty()) {
                            ?? r2 = arrayList3.get(0);
                            float maxIntrinsicWidth = ((ParagraphIntrinsicInfo) r2).intrinsics.getMaxIntrinsicWidth();
                            int lastIndex = ResultKt.getLastIndex(arrayList3);
                            boolean z = r2;
                            if (1 <= lastIndex) {
                                while (true) {
                                    Object obj = arrayList3.get(i9);
                                    float maxIntrinsicWidth2 = ((ParagraphIntrinsicInfo) obj).intrinsics.getMaxIntrinsicWidth();
                                    r2 = z;
                                    if (Float.compare(maxIntrinsicWidth, maxIntrinsicWidth2) < 0) {
                                        r2 = obj;
                                        maxIntrinsicWidth = maxIntrinsicWidth2;
                                    }
                                    if (i9 != lastIndex) {
                                        i9++;
                                        z = r2;
                                    }
                                }
                            }
                            paragraphIntrinsicInfo = r2;
                        }
                        ParagraphIntrinsicInfo paragraphIntrinsicInfo2 = paragraphIntrinsicInfo;
                        if (paragraphIntrinsicInfo2 != null && (paragraphIntrinsics = paragraphIntrinsicInfo2.intrinsics) != null) {
                            f = paragraphIntrinsics.getMaxIntrinsicWidth();
                        }
                        return Float.valueOf(f);
                    default:
                        ArrayList arrayList4 = multiParagraphIntrinsics.infoList;
                        if (!arrayList4.isEmpty()) {
                            ?? r22 = arrayList4.get(0);
                            float minIntrinsicWidth = ((ParagraphIntrinsicInfo) r22).intrinsics.getMinIntrinsicWidth();
                            int lastIndex2 = ResultKt.getLastIndex(arrayList4);
                            boolean z2 = r22;
                            if (1 <= lastIndex2) {
                                while (true) {
                                    Object obj2 = arrayList4.get(i9);
                                    float minIntrinsicWidth2 = ((ParagraphIntrinsicInfo) obj2).intrinsics.getMinIntrinsicWidth();
                                    r22 = z2;
                                    if (Float.compare(minIntrinsicWidth, minIntrinsicWidth2) < 0) {
                                        r22 = obj2;
                                        minIntrinsicWidth = minIntrinsicWidth2;
                                    }
                                    if (i9 != lastIndex2) {
                                        i9++;
                                        z2 = r22;
                                    }
                                }
                            }
                            paragraphIntrinsicInfo = r22;
                        }
                        ParagraphIntrinsicInfo paragraphIntrinsicInfo3 = paragraphIntrinsicInfo;
                        if (paragraphIntrinsicInfo3 != null && (paragraphIntrinsics2 = paragraphIntrinsicInfo3.intrinsics) != null) {
                            f = paragraphIntrinsics2.getMinIntrinsicWidth();
                        }
                        return Float.valueOf(f);
                }
            }
        });
        int i9 = AnnotatedStringKt.$r8$clinit;
        ParagraphStyle paragraphStyle = textStyle.paragraphStyle;
        ResultKt.checkNotNullParameter(paragraphStyle, "defaultParagraphStyle");
        String str4 = annotatedString2.text;
        int length = str4.length();
        EmptyList emptyList3 = EmptyList.INSTANCE;
        List list2 = annotatedString2.paragraphStylesOrNull;
        list2 = list2 == null ? emptyList3 : list2;
        ArrayList arrayList3 = new ArrayList();
        int size = list2.size();
        int i10 = 0;
        int i11 = 0;
        while (i10 < size) {
            AnnotatedString.Range range = (AnnotatedString.Range) list2.get(i10);
            ParagraphStyle paragraphStyle2 = (ParagraphStyle) range.item;
            int i12 = range.start;
            List list3 = list2;
            if (i12 != i11) {
                arrayList3.add(new AnnotatedString.Range(i11, i12, paragraphStyle));
            }
            ParagraphStyle merge = paragraphStyle.merge(paragraphStyle2);
            int i13 = range.end;
            arrayList3.add(new AnnotatedString.Range(i12, i13, merge));
            i10++;
            i11 = i13;
            list2 = list3;
        }
        if (i11 != length) {
            arrayList3.add(new AnnotatedString.Range(i11, length, paragraphStyle));
        }
        if (arrayList3.isEmpty()) {
            i = 0;
            arrayList3.add(new AnnotatedString.Range(0, 0, paragraphStyle));
        } else {
            i = 0;
        }
        ArrayList arrayList4 = new ArrayList(arrayList3.size());
        int size2 = arrayList3.size();
        int i14 = i;
        while (i14 < size2) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList3.get(i14);
            int i15 = range2.start;
            int i16 = range2.end;
            if (i15 != i16) {
                str = str4.substring(i15, i16);
                ResultKt.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            } else {
                str = "";
            }
            String str5 = str;
            if (i15 == i16 || (r6 = annotatedString2.spanStylesOrNull) == 0) {
                i2 = i14;
                i3 = size2;
                str2 = str4;
                emptyList = null;
            } else {
                if (i15 != 0 || i16 < str4.length()) {
                    i2 = i14;
                    ArrayList arrayList5 = new ArrayList(r6.size());
                    int size3 = r6.size();
                    i3 = size2;
                    int i17 = 0;
                    List list4 = r6;
                    while (i17 < size3) {
                        int i18 = size3;
                        Object obj = list4.get(i17);
                        List list5 = list4;
                        AnnotatedString.Range range3 = (AnnotatedString.Range) obj;
                        if (AnnotatedStringKt.intersect(i15, i16, range3.start, range3.end)) {
                            arrayList5.add(obj);
                        }
                        i17++;
                        size3 = i18;
                        list4 = list5;
                    }
                    r6 = new ArrayList(arrayList5.size());
                    int size4 = arrayList5.size();
                    int i19 = 0;
                    while (i19 < size4) {
                        AnnotatedString.Range range4 = (AnnotatedString.Range) arrayList5.get(i19);
                        r6.add(new AnnotatedString.Range(ResultKt.coerceIn(range4.start, i15, i16) - i15, ResultKt.coerceIn(range4.end, i15, i16) - i15, range4.item));
                        i19++;
                        arrayList5 = arrayList5;
                        size4 = size4;
                        str4 = str4;
                    }
                } else {
                    i2 = i14;
                    i3 = size2;
                }
                str2 = str4;
                emptyList = r6;
            }
            ParagraphStyle paragraphStyle3 = (ParagraphStyle) range2.item;
            if (paragraphStyle3.textDirection != null) {
                i4 = i16;
                str3 = str5;
                emptyList2 = emptyList3;
                arrayList = arrayList3;
                arrayList2 = arrayList4;
            } else {
                emptyList2 = emptyList3;
                arrayList = arrayList3;
                arrayList2 = arrayList4;
                str3 = str5;
                i4 = i16;
                paragraphStyle3 = new ParagraphStyle(paragraphStyle3.textAlign, paragraphStyle.textDirection, paragraphStyle3.lineHeight, paragraphStyle3.textIndent, paragraphStyle3.platformStyle, paragraphStyle3.lineHeightStyle, paragraphStyle3.lineBreak, paragraphStyle3.hyphens, paragraphStyle3.textMotion);
            }
            TextStyle textStyle2 = new TextStyle(textStyle.spanStyle, paragraphStyle.merge(paragraphStyle3));
            EmptyList emptyList4 = emptyList == null ? emptyList2 : emptyList;
            List list6 = this.placeholders;
            ArrayList arrayList6 = new ArrayList(list6.size());
            int size5 = list6.size();
            int i20 = 0;
            while (true) {
                i5 = range2.start;
                if (i20 >= size5) {
                    break;
                }
                Object obj2 = list6.get(i20);
                AnnotatedString.Range range5 = (AnnotatedString.Range) obj2;
                int i21 = i4;
                if (AnnotatedStringKt.intersect(i5, i21, range5.start, range5.end)) {
                    arrayList6.add(obj2);
                }
                i20++;
                i4 = i21;
            }
            int i22 = i4;
            ArrayList arrayList7 = new ArrayList(arrayList6.size());
            int size6 = arrayList6.size();
            for (int i23 = 0; i23 < size6; i23++) {
                AnnotatedString.Range range6 = (AnnotatedString.Range) arrayList6.get(i23);
                int i24 = range6.start;
                if (i5 > i24 || (i6 = range6.end) > i22) {
                    throw new IllegalArgumentException("placeholder can not overlap with paragraph.".toString());
                }
                arrayList7.add(new AnnotatedString.Range(i24 - i5, i6 - i5, range6.item));
            }
            ArrayList arrayList8 = arrayList2;
            arrayList8.add(new ParagraphIntrinsicInfo(_BOUNDARY.ParagraphIntrinsics(textStyle2, resolver, density, str3, emptyList4, arrayList7), i5, i22));
            i14 = i2 + 1;
            arrayList4 = arrayList8;
            size2 = i3;
            emptyList3 = emptyList2;
            arrayList3 = arrayList;
            str4 = str2;
            annotatedString2 = annotatedString;
        }
        this.infoList = arrayList4;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final boolean getHasStaleResolvedFonts() {
        ArrayList arrayList = this.infoList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((ParagraphIntrinsicInfo) arrayList.get(i)).intrinsics.getHasStaleResolvedFonts()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMaxIntrinsicWidth() {
        return ((Number) this.maxIntrinsicWidth$delegate.getValue()).floatValue();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMinIntrinsicWidth() {
        return ((Number) this.minIntrinsicWidth$delegate.getValue()).floatValue();
    }
}
