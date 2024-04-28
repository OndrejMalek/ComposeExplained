package androidx.compose.ui.text;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.intl.PlatformLocaleKt;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SaversKt$ColorSaver$2 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public static final SaversKt$ColorSaver$2 INSTANCE$1 = new SaversKt$ColorSaver$2(1);
    public static final SaversKt$ColorSaver$2 INSTANCE$2 = new SaversKt$ColorSaver$2(2);
    public static final SaversKt$ColorSaver$2 INSTANCE$3 = new SaversKt$ColorSaver$2(3);
    public static final SaversKt$ColorSaver$2 INSTANCE$4 = new SaversKt$ColorSaver$2(4);
    public static final SaversKt$ColorSaver$2 INSTANCE = new SaversKt$ColorSaver$2(0);
    public static final SaversKt$ColorSaver$2 INSTANCE$5 = new SaversKt$ColorSaver$2(5);
    public static final SaversKt$ColorSaver$2 INSTANCE$6 = new SaversKt$ColorSaver$2(6);
    public static final SaversKt$ColorSaver$2 INSTANCE$7 = new SaversKt$ColorSaver$2(7);
    public static final SaversKt$ColorSaver$2 INSTANCE$8 = new SaversKt$ColorSaver$2(8);
    public static final SaversKt$ColorSaver$2 INSTANCE$9 = new SaversKt$ColorSaver$2(9);
    public static final SaversKt$ColorSaver$2 INSTANCE$10 = new SaversKt$ColorSaver$2(10);
    public static final SaversKt$ColorSaver$2 INSTANCE$11 = new SaversKt$ColorSaver$2(11);
    public static final SaversKt$ColorSaver$2 INSTANCE$12 = new SaversKt$ColorSaver$2(12);
    public static final SaversKt$ColorSaver$2 INSTANCE$13 = new SaversKt$ColorSaver$2(13);
    public static final SaversKt$ColorSaver$2 INSTANCE$14 = new SaversKt$ColorSaver$2(14);
    public static final SaversKt$ColorSaver$2 INSTANCE$15 = new SaversKt$ColorSaver$2(15);
    public static final SaversKt$ColorSaver$2 INSTANCE$16 = new SaversKt$ColorSaver$2(16);
    public static final SaversKt$ColorSaver$2 INSTANCE$17 = new SaversKt$ColorSaver$2(17);
    public static final SaversKt$ColorSaver$2 INSTANCE$18 = new SaversKt$ColorSaver$2(18);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SaversKt$ColorSaver$2(int i) {
        super(1);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        List list = null;
        r6 = null;
        TextUnit textUnit = null;
        r6 = null;
        Shadow shadow = null;
        r6 = null;
        TextIndent textIndent = null;
        r6 = null;
        UrlAnnotation urlAnnotation = null;
        r6 = null;
        VerbatimTtsAnnotation verbatimTtsAnnotation = null;
        r6 = null;
        SpanStyle spanStyle = null;
        r6 = null;
        ParagraphStyle paragraphStyle = null;
        list = null;
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(obj, "it");
                return new Color(((ULong) obj).data);
            case 1:
                ResultKt.checkNotNullParameter(obj, "it");
                List list2 = (List) obj;
                Object obj2 = list2.get(1);
                SaverKt$Saver$1 saverKt$Saver$1 = SaversKt.AnnotationRangeListSaver;
                Boolean bool = Boolean.FALSE;
                List list3 = (ResultKt.areEqual(obj2, bool) || obj2 == null) ? null : (List) saverKt$Saver$1.$restore.invoke(obj2);
                Object obj3 = list2.get(2);
                List list4 = (ResultKt.areEqual(obj3, bool) || obj3 == null) ? null : (List) saverKt$Saver$1.$restore.invoke(obj3);
                Object obj4 = list2.get(0);
                String str = obj4 != null ? (String) obj4 : null;
                ResultKt.checkNotNull(str);
                if (list3 == null || list3.isEmpty()) {
                    list3 = null;
                }
                if (list4 == null || list4.isEmpty()) {
                    list4 = null;
                }
                Object obj5 = list2.get(3);
                if (!ResultKt.areEqual(obj5, bool) && obj5 != null) {
                    list = (List) saverKt$Saver$1.$restore.invoke(obj5);
                }
                return new AnnotatedString(str, list3, list4, list);
            case 2:
                ResultKt.checkNotNullParameter(obj, "it");
                List list5 = (List) obj;
                ArrayList arrayList = new ArrayList(list5.size());
                int size = list5.size();
                while (i < size) {
                    Object obj6 = list5.get(i);
                    AnnotatedString.Range range = (ResultKt.areEqual(obj6, Boolean.FALSE) || obj6 == null) ? null : (AnnotatedString.Range) SaversKt.AnnotationRangeSaver.$restore.invoke(obj6);
                    ResultKt.checkNotNull(range);
                    arrayList.add(range);
                    i++;
                }
                return arrayList;
            case 3:
                ResultKt.checkNotNullParameter(obj, "it");
                List list6 = (List) obj;
                Object obj7 = list6.get(0);
                AnnotationType annotationType = obj7 != null ? (AnnotationType) obj7 : null;
                ResultKt.checkNotNull(annotationType);
                Object obj8 = list6.get(2);
                Integer num = obj8 != null ? (Integer) obj8 : null;
                ResultKt.checkNotNull(num);
                int intValue = num.intValue();
                Object obj9 = list6.get(3);
                Integer num2 = obj9 != null ? (Integer) obj9 : null;
                ResultKt.checkNotNull(num2);
                int intValue2 = num2.intValue();
                Object obj10 = list6.get(4);
                String str2 = obj10 != null ? (String) obj10 : null;
                ResultKt.checkNotNull(str2);
                int ordinal = annotationType.ordinal();
                if (ordinal == 0) {
                    Object obj11 = list6.get(1);
                    SaverKt$Saver$1 saverKt$Saver$12 = SaversKt.ParagraphStyleSaver;
                    if (!ResultKt.areEqual(obj11, Boolean.FALSE) && obj11 != null) {
                        paragraphStyle = (ParagraphStyle) saverKt$Saver$12.$restore.invoke(obj11);
                    }
                    ResultKt.checkNotNull(paragraphStyle);
                    return new AnnotatedString.Range(paragraphStyle, intValue, intValue2, str2);
                }
                if (ordinal == 1) {
                    Object obj12 = list6.get(1);
                    SaverKt$Saver$1 saverKt$Saver$13 = SaversKt.SpanStyleSaver;
                    if (!ResultKt.areEqual(obj12, Boolean.FALSE) && obj12 != null) {
                        spanStyle = (SpanStyle) saverKt$Saver$13.$restore.invoke(obj12);
                    }
                    ResultKt.checkNotNull(spanStyle);
                    return new AnnotatedString.Range(spanStyle, intValue, intValue2, str2);
                }
                if (ordinal == 2) {
                    Object obj13 = list6.get(1);
                    SaverKt$Saver$1 saverKt$Saver$14 = SaversKt.VerbatimTtsAnnotationSaver;
                    if (!ResultKt.areEqual(obj13, Boolean.FALSE) && obj13 != null) {
                        verbatimTtsAnnotation = (VerbatimTtsAnnotation) saverKt$Saver$14.$restore.invoke(obj13);
                    }
                    ResultKt.checkNotNull(verbatimTtsAnnotation);
                    return new AnnotatedString.Range(verbatimTtsAnnotation, intValue, intValue2, str2);
                }
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        throw new RuntimeException();
                    }
                    Object obj14 = list6.get(1);
                    String str3 = obj14 != null ? (String) obj14 : null;
                    ResultKt.checkNotNull(str3);
                    return new AnnotatedString.Range(str3, intValue, intValue2, str2);
                }
                Object obj15 = list6.get(1);
                SaverKt$Saver$1 saverKt$Saver$15 = SaversKt.UrlAnnotationSaver;
                if (!ResultKt.areEqual(obj15, Boolean.FALSE) && obj15 != null) {
                    urlAnnotation = (UrlAnnotation) saverKt$Saver$15.$restore.invoke(obj15);
                }
                ResultKt.checkNotNull(urlAnnotation);
                return new AnnotatedString.Range(urlAnnotation, intValue, intValue2, str2);
            case 4:
                ResultKt.checkNotNullParameter(obj, "it");
                return new BaselineShift(((Float) obj).floatValue());
            case 5:
                ResultKt.checkNotNullParameter(obj, "it");
                return new FontWeight(((Integer) obj).intValue());
            case 6:
                ResultKt.checkNotNullParameter(obj, "it");
                List list7 = (List) obj;
                ArrayList arrayList2 = new ArrayList(list7.size());
                int size2 = list7.size();
                while (i < size2) {
                    Object obj16 = list7.get(i);
                    Locale locale = (ResultKt.areEqual(obj16, Boolean.FALSE) || obj16 == null) ? null : (Locale) SaversKt.LocaleSaver.$restore.invoke(obj16);
                    ResultKt.checkNotNull(locale);
                    arrayList2.add(locale);
                    i++;
                }
                return new LocaleList(arrayList2);
            case 7:
                ResultKt.checkNotNullParameter(obj, "it");
                PlatformLocaleKt.platformLocaleDelegate.getClass();
                java.util.Locale forLanguageTag = java.util.Locale.forLanguageTag((String) obj);
                ResultKt.checkNotNullExpressionValue(forLanguageTag, "forLanguageTag(languageTag)");
                return new Locale(new AndroidLocale(forLanguageTag));
            case 8:
                ResultKt.checkNotNullParameter(obj, "it");
                if (ResultKt.areEqual(obj, Boolean.FALSE)) {
                    return new Offset(Offset.Unspecified);
                }
                List list8 = (List) obj;
                Object obj17 = list8.get(0);
                Float f = obj17 != null ? (Float) obj17 : null;
                ResultKt.checkNotNull(f);
                float floatValue = f.floatValue();
                Object obj18 = list8.get(1);
                Float f2 = obj18 != null ? (Float) obj18 : null;
                ResultKt.checkNotNull(f2);
                return new Offset(_BOUNDARY.Offset(floatValue, f2.floatValue()));
            case 9:
                ResultKt.checkNotNullParameter(obj, "it");
                List list9 = (List) obj;
                Object obj19 = list9.get(0);
                TextAlign textAlign = obj19 != null ? (TextAlign) obj19 : null;
                Object obj20 = list9.get(1);
                TextDirection textDirection = obj20 != null ? (TextDirection) obj20 : null;
                Object obj21 = list9.get(2);
                TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
                SaverKt$Saver$1 saverKt$Saver$16 = SaversKt.TextUnitSaver;
                Boolean bool2 = Boolean.FALSE;
                TextUnit textUnit2 = (ResultKt.areEqual(obj21, bool2) || obj21 == null) ? null : (TextUnit) saverKt$Saver$16.$restore.invoke(obj21);
                ResultKt.checkNotNull(textUnit2);
                Object obj22 = list9.get(3);
                TextIndent textIndent2 = TextIndent.None;
                SaverKt$Saver$1 saverKt$Saver$17 = SaversKt.TextIndentSaver;
                if (!ResultKt.areEqual(obj22, bool2) && obj22 != null) {
                    textIndent = (TextIndent) saverKt$Saver$17.$restore.invoke(obj22);
                }
                return new ParagraphStyle(textAlign, textDirection, textUnit2.packedValue, textIndent, (PlatformParagraphStyle) null, (LineHeightStyle) null, (LineBreak) null, (Hyphens) null, 496);
            case 10:
                ResultKt.checkNotNullParameter(obj, "it");
                List list10 = (List) obj;
                Object obj23 = list10.get(0);
                int i2 = Color.$r8$clinit;
                SaverKt$Saver$1 saverKt$Saver$18 = SaversKt.ColorSaver;
                Boolean bool3 = Boolean.FALSE;
                Color color = (ResultKt.areEqual(obj23, bool3) || obj23 == null) ? null : (Color) saverKt$Saver$18.$restore.invoke(obj23);
                ResultKt.checkNotNull(color);
                Object obj24 = list10.get(1);
                int i3 = Offset.$r8$clinit;
                Offset offset = (ResultKt.areEqual(obj24, bool3) || obj24 == null) ? null : (Offset) SaversKt.OffsetSaver.$restore.invoke(obj24);
                ResultKt.checkNotNull(offset);
                Object obj25 = list10.get(2);
                Float f3 = obj25 != null ? (Float) obj25 : null;
                ResultKt.checkNotNull(f3);
                return new Shadow(color.value, offset.packedValue, f3.floatValue());
            case 11:
                ResultKt.checkNotNullParameter(obj, "it");
                List list11 = (List) obj;
                Object obj26 = list11.get(0);
                int i4 = Color.$r8$clinit;
                SaverKt$Saver$1 saverKt$Saver$19 = SaversKt.ColorSaver;
                Boolean bool4 = Boolean.FALSE;
                Color color2 = (ResultKt.areEqual(obj26, bool4) || obj26 == null) ? null : (Color) saverKt$Saver$19.$restore.invoke(obj26);
                ResultKt.checkNotNull(color2);
                Object obj27 = list11.get(1);
                TextUnitType[] textUnitTypeArr2 = TextUnit.TextUnitTypes;
                SaverKt$Saver$1 saverKt$Saver$110 = SaversKt.TextUnitSaver;
                TextUnit textUnit3 = (ResultKt.areEqual(obj27, bool4) || obj27 == null) ? null : (TextUnit) saverKt$Saver$110.$restore.invoke(obj27);
                ResultKt.checkNotNull(textUnit3);
                Object obj28 = list11.get(2);
                FontWeight fontWeight = FontWeight.W600;
                FontWeight fontWeight2 = (ResultKt.areEqual(obj28, bool4) || obj28 == null) ? null : (FontWeight) SaversKt.FontWeightSaver.$restore.invoke(obj28);
                Object obj29 = list11.get(3);
                FontStyle fontStyle = obj29 != null ? (FontStyle) obj29 : null;
                Object obj30 = list11.get(4);
                FontSynthesis fontSynthesis = obj30 != null ? (FontSynthesis) obj30 : null;
                Object obj31 = list11.get(6);
                String str4 = obj31 != null ? (String) obj31 : null;
                Object obj32 = list11.get(7);
                TextUnit textUnit4 = (ResultKt.areEqual(obj32, bool4) || obj32 == null) ? null : (TextUnit) saverKt$Saver$110.$restore.invoke(obj32);
                ResultKt.checkNotNull(textUnit4);
                Object obj33 = list11.get(8);
                BaselineShift baselineShift = (ResultKt.areEqual(obj33, bool4) || obj33 == null) ? null : (BaselineShift) SaversKt.BaselineShiftSaver.$restore.invoke(obj33);
                Object obj34 = list11.get(9);
                TextGeometricTransform textGeometricTransform = (ResultKt.areEqual(obj34, bool4) || obj34 == null) ? null : (TextGeometricTransform) SaversKt.TextGeometricTransformSaver.$restore.invoke(obj34);
                Object obj35 = list11.get(10);
                LocaleList localeList = (ResultKt.areEqual(obj35, bool4) || obj35 == null) ? null : (LocaleList) SaversKt.LocaleListSaver.$restore.invoke(obj35);
                Object obj36 = list11.get(11);
                Color color3 = (ResultKt.areEqual(obj36, bool4) || obj36 == null) ? null : (Color) saverKt$Saver$19.$restore.invoke(obj36);
                ResultKt.checkNotNull(color3);
                Object obj37 = list11.get(12);
                TextDecoration textDecoration = (ResultKt.areEqual(obj37, bool4) || obj37 == null) ? null : (TextDecoration) SaversKt.TextDecorationSaver.$restore.invoke(obj37);
                Object obj38 = list11.get(13);
                Shadow shadow2 = Shadow.None;
                SaverKt$Saver$1 saverKt$Saver$111 = SaversKt.ShadowSaver;
                if (!ResultKt.areEqual(obj38, bool4) && obj38 != null) {
                    shadow = (Shadow) saverKt$Saver$111.$restore.invoke(obj38);
                }
                return new SpanStyle(color2.value, textUnit3.packedValue, fontWeight2, fontStyle, fontSynthesis, (FontFamily) null, str4, textUnit4.packedValue, baselineShift, textGeometricTransform, localeList, color3.value, textDecoration, shadow, 49184);
            case 12:
                ResultKt.checkNotNullParameter(obj, "it");
                return new TextDecoration(((Integer) obj).intValue());
            case 13:
                ResultKt.checkNotNullParameter(obj, "it");
                List list12 = (List) obj;
                return new TextGeometricTransform(((Number) list12.get(0)).floatValue(), ((Number) list12.get(1)).floatValue());
            case 14:
                ResultKt.checkNotNullParameter(obj, "it");
                List list13 = (List) obj;
                Object obj39 = list13.get(0);
                TextUnitType[] textUnitTypeArr3 = TextUnit.TextUnitTypes;
                SaverKt$Saver$1 saverKt$Saver$112 = SaversKt.TextUnitSaver;
                Boolean bool5 = Boolean.FALSE;
                TextUnit textUnit5 = (ResultKt.areEqual(obj39, bool5) || obj39 == null) ? null : (TextUnit) saverKt$Saver$112.$restore.invoke(obj39);
                ResultKt.checkNotNull(textUnit5);
                Object obj40 = list13.get(1);
                if (!ResultKt.areEqual(obj40, bool5) && obj40 != null) {
                    textUnit = (TextUnit) saverKt$Saver$112.$restore.invoke(obj40);
                }
                ResultKt.checkNotNull(textUnit);
                return new TextIndent(textUnit5.packedValue, textUnit.packedValue);
            case 15:
                ResultKt.checkNotNullParameter(obj, "it");
                List list14 = (List) obj;
                Object obj41 = list14.get(0);
                Integer num3 = obj41 != null ? (Integer) obj41 : null;
                ResultKt.checkNotNull(num3);
                int intValue3 = num3.intValue();
                Object obj42 = list14.get(1);
                Integer num4 = obj42 != null ? (Integer) obj42 : null;
                ResultKt.checkNotNull(num4);
                return new TextRange(_BOUNDARY.TextRange(intValue3, num4.intValue()));
            case 16:
                ResultKt.checkNotNullParameter(obj, "it");
                List list15 = (List) obj;
                Object obj43 = list15.get(0);
                Float f4 = obj43 != null ? (Float) obj43 : null;
                ResultKt.checkNotNull(f4);
                float floatValue2 = f4.floatValue();
                Object obj44 = list15.get(1);
                TextUnitType textUnitType = obj44 != null ? (TextUnitType) obj44 : null;
                ResultKt.checkNotNull(textUnitType);
                return new TextUnit(ResultKt.pack(floatValue2, textUnitType.type));
            case 17:
                ResultKt.checkNotNullParameter(obj, "it");
                return new UrlAnnotation((String) obj);
            default:
                ResultKt.checkNotNullParameter(obj, "it");
                return new VerbatimTtsAnnotation((String) obj);
        }
    }
}
