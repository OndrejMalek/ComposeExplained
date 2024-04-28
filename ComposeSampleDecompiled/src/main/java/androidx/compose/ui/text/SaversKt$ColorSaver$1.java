package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class SaversKt$ColorSaver$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public static final SaversKt$ColorSaver$1 INSTANCE$1 = new SaversKt$ColorSaver$1(1);
    public static final SaversKt$ColorSaver$1 INSTANCE$2 = new SaversKt$ColorSaver$1(2);
    public static final SaversKt$ColorSaver$1 INSTANCE$3 = new SaversKt$ColorSaver$1(3);
    public static final SaversKt$ColorSaver$1 INSTANCE$4 = new SaversKt$ColorSaver$1(4);
    public static final SaversKt$ColorSaver$1 INSTANCE = new SaversKt$ColorSaver$1(0);
    public static final SaversKt$ColorSaver$1 INSTANCE$5 = new SaversKt$ColorSaver$1(5);
    public static final SaversKt$ColorSaver$1 INSTANCE$6 = new SaversKt$ColorSaver$1(6);
    public static final SaversKt$ColorSaver$1 INSTANCE$7 = new SaversKt$ColorSaver$1(7);
    public static final SaversKt$ColorSaver$1 INSTANCE$8 = new SaversKt$ColorSaver$1(8);
    public static final SaversKt$ColorSaver$1 INSTANCE$9 = new SaversKt$ColorSaver$1(9);
    public static final SaversKt$ColorSaver$1 INSTANCE$10 = new SaversKt$ColorSaver$1(10);
    public static final SaversKt$ColorSaver$1 INSTANCE$11 = new SaversKt$ColorSaver$1(11);
    public static final SaversKt$ColorSaver$1 INSTANCE$12 = new SaversKt$ColorSaver$1(12);
    public static final SaversKt$ColorSaver$1 INSTANCE$13 = new SaversKt$ColorSaver$1(13);
    public static final SaversKt$ColorSaver$1 INSTANCE$14 = new SaversKt$ColorSaver$1(14);
    public static final SaversKt$ColorSaver$1 INSTANCE$15 = new SaversKt$ColorSaver$1(15);
    public static final SaversKt$ColorSaver$1 INSTANCE$16 = new SaversKt$ColorSaver$1(16);
    public static final SaversKt$ColorSaver$1 INSTANCE$17 = new SaversKt$ColorSaver$1(17);
    public static final SaversKt$ColorSaver$1 INSTANCE$18 = new SaversKt$ColorSaver$1(18);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SaversKt$ColorSaver$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                long j = ((Color) obj2).value;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                return new ULong(j);
            case 1:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                AnnotatedString annotatedString = (AnnotatedString) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(annotatedString, "it");
                Object[] objArr = new Object[4];
                SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotatedStringSaver;
                objArr[0] = annotatedString.text;
                Collection collection = EmptyList.INSTANCE;
                Collection collection2 = annotatedString.spanStylesOrNull;
                if (collection2 == null) {
                    collection2 = collection;
                }
                SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.AnnotationRangeListSaver;
                objArr[1] = SaversKt.save(collection2, saverKt$Saver$12);
                Collection collection3 = annotatedString.paragraphStylesOrNull;
                if (collection3 != null) {
                    collection = collection3;
                }
                objArr[2] = SaversKt.save(collection, saverKt$Saver$12);
                objArr[3] = SaversKt.save(annotatedString.annotations, saverKt$Saver$12);
                return ResultKt.arrayListOf(objArr);
            case 2:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                List list = (List) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(list, "it");
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                while (i < size) {
                    arrayList.add(SaversKt.save((AnnotatedString.Range) list.get(i), SaversKt.AnnotationRangeSaver));
                    i++;
                }
                return arrayList;
            case 3:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                AnnotatedString.Range range = (AnnotatedString.Range) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(range, "it");
                Object obj3 = range.item;
                AnnotationType annotationType = obj3 instanceof ParagraphStyle ? AnnotationType.Paragraph : obj3 instanceof SpanStyle ? AnnotationType.Span : obj3 instanceof VerbatimTtsAnnotation ? AnnotationType.VerbatimTts : obj3 instanceof UrlAnnotation ? AnnotationType.Url : AnnotationType.String;
                int ordinal = annotationType.ordinal();
                if (ordinal == 0) {
                    ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                    obj3 = SaversKt.save((ParagraphStyle) obj3, SaversKt.ParagraphStyleSaver);
                } else if (ordinal == 1) {
                    ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                    obj3 = SaversKt.save((SpanStyle) obj3, SaversKt.SpanStyleSaver);
                } else if (ordinal == 2) {
                    ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                    obj3 = SaversKt.save((VerbatimTtsAnnotation) obj3, SaversKt.VerbatimTtsAnnotationSaver);
                } else if (ordinal == 3) {
                    ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                    obj3 = SaversKt.save((UrlAnnotation) obj3, SaversKt.UrlAnnotationSaver);
                } else {
                    if (ordinal != 4) {
                        throw new RuntimeException();
                    }
                    SaverKt$Saver$1 saverKt$Saver$13 = SaversKt.AnnotatedStringSaver;
                }
                return ResultKt.arrayListOf(annotationType, obj3, Integer.valueOf(range.start), Integer.valueOf(range.end), range.tag);
            case 4:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                float f = ((BaselineShift) obj2).multiplier;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                return Float.valueOf(f);
            case 5:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                FontWeight fontWeight = (FontWeight) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(fontWeight, "it");
                return Integer.valueOf(fontWeight.weight);
            case 6:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                LocaleList localeList = (LocaleList) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(localeList, "it");
                List list2 = localeList.localeList;
                ArrayList arrayList2 = new ArrayList(list2.size());
                int size2 = list2.size();
                while (i < size2) {
                    arrayList2.add(SaversKt.save((Locale) list2.get(i), SaversKt.LocaleSaver));
                    i++;
                }
                return arrayList2;
            case 7:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                Locale locale = (Locale) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(locale, "it");
                return locale.toLanguageTag();
            case 8:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                long j2 = ((Offset) obj2).packedValue;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                if (Offset.m75equalsimpl0(j2, Offset.Unspecified)) {
                    return Boolean.FALSE;
                }
                Float valueOf = Float.valueOf(Offset.m76getXimpl(j2));
                SaverKt$Saver$1 saverKt$Saver$14 = SaversKt.AnnotatedStringSaver;
                return ResultKt.arrayListOf(valueOf, Float.valueOf(Offset.m77getYimpl(j2)));
            case 9:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                ParagraphStyle paragraphStyle = (ParagraphStyle) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(paragraphStyle, "it");
                SaverKt$Saver$1 saverKt$Saver$15 = SaversKt.AnnotatedStringSaver;
                Object save = SaversKt.save(new TextUnit(paragraphStyle.lineHeight), SaversKt.TextUnitSaver);
                TextIndent textIndent = TextIndent.None;
                return ResultKt.arrayListOf(paragraphStyle.textAlign, paragraphStyle.textDirection, save, SaversKt.save(paragraphStyle.textIndent, SaversKt.TextIndentSaver));
            case 10:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                Shadow shadow = (Shadow) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(shadow, "it");
                return ResultKt.arrayListOf(SaversKt.save(new Color(shadow.color), SaversKt.ColorSaver), SaversKt.save(new Offset(shadow.offset), SaversKt.OffsetSaver), Float.valueOf(shadow.blurRadius));
            case 11:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                SpanStyle spanStyle = (SpanStyle) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(spanStyle, "it");
                Color color = new Color(spanStyle.textForegroundStyle.mo264getColor0d7_KjU());
                SaverKt$Saver$1 saverKt$Saver$16 = SaversKt.ColorSaver;
                Object save2 = SaversKt.save(color, saverKt$Saver$16);
                TextUnit textUnit = new TextUnit(spanStyle.fontSize);
                SaverKt$Saver$1 saverKt$Saver$17 = SaversKt.TextUnitSaver;
                Object save3 = SaversKt.save(textUnit, saverKt$Saver$17);
                FontWeight fontWeight2 = FontWeight.W600;
                Object save4 = SaversKt.save(spanStyle.fontWeight, SaversKt.FontWeightSaver);
                Object save5 = SaversKt.save(new TextUnit(spanStyle.letterSpacing), saverKt$Saver$17);
                Object save6 = SaversKt.save(spanStyle.baselineShift, SaversKt.BaselineShiftSaver);
                Object save7 = SaversKt.save(spanStyle.textGeometricTransform, SaversKt.TextGeometricTransformSaver);
                Object save8 = SaversKt.save(spanStyle.localeList, SaversKt.LocaleListSaver);
                Object save9 = SaversKt.save(new Color(spanStyle.background), saverKt$Saver$16);
                Object save10 = SaversKt.save(spanStyle.textDecoration, SaversKt.TextDecorationSaver);
                Shadow shadow2 = Shadow.None;
                return ResultKt.arrayListOf(save2, save3, save4, spanStyle.fontStyle, spanStyle.fontSynthesis, -1, spanStyle.fontFeatureSettings, save5, save6, save7, save8, save9, save10, SaversKt.save(spanStyle.shadow, SaversKt.ShadowSaver));
            case 12:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                TextDecoration textDecoration = (TextDecoration) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(textDecoration, "it");
                return Integer.valueOf(textDecoration.mask);
            case 13:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                TextGeometricTransform textGeometricTransform = (TextGeometricTransform) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(textGeometricTransform, "it");
                return ResultKt.arrayListOf(Float.valueOf(textGeometricTransform.scaleX), Float.valueOf(textGeometricTransform.skewX));
            case 14:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                TextIndent textIndent2 = (TextIndent) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(textIndent2, "it");
                TextUnit textUnit2 = new TextUnit(textIndent2.firstLine);
                SaverKt$Saver$1 saverKt$Saver$18 = SaversKt.TextUnitSaver;
                return ResultKt.arrayListOf(SaversKt.save(textUnit2, saverKt$Saver$18), SaversKt.save(new TextUnit(textIndent2.restLine), saverKt$Saver$18));
            case 15:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                long j3 = ((TextRange) obj2).packedValue;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                int i2 = TextRange.$r8$clinit;
                Integer valueOf2 = Integer.valueOf((int) (j3 >> 32));
                SaverKt$Saver$1 saverKt$Saver$19 = SaversKt.AnnotatedStringSaver;
                return ResultKt.arrayListOf(valueOf2, Integer.valueOf((int) (j3 & 4294967295L)));
            case 16:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                long j4 = ((TextUnit) obj2).packedValue;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                Float valueOf3 = Float.valueOf(TextUnit.m285getValueimpl(j4));
                SaverKt$Saver$1 saverKt$Saver$110 = SaversKt.AnnotatedStringSaver;
                return ResultKt.arrayListOf(valueOf3, new TextUnitType(TextUnit.m284getTypeUIouoOA(j4)));
            case 17:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                UrlAnnotation urlAnnotation = (UrlAnnotation) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(urlAnnotation, "it");
                SaverKt$Saver$1 saverKt$Saver$111 = SaversKt.AnnotatedStringSaver;
                return urlAnnotation.url;
            default:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                VerbatimTtsAnnotation verbatimTtsAnnotation = (VerbatimTtsAnnotation) obj2;
                ResultKt.checkNotNullParameter(null, "$this$Saver");
                ResultKt.checkNotNullParameter(verbatimTtsAnnotation, "it");
                SaverKt$Saver$1 saverKt$Saver$112 = SaversKt.AnnotatedStringSaver;
                return verbatimTtsAnnotation.verbatim;
        }
    }
}
