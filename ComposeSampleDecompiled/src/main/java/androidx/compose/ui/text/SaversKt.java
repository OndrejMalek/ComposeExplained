package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class SaversKt {
    public static final SaverKt$Saver$1 AnnotatedStringSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$1, SaversKt$ColorSaver$2.INSTANCE$1);
    public static final SaverKt$Saver$1 AnnotationRangeListSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$2, SaversKt$ColorSaver$2.INSTANCE$2);
    public static final SaverKt$Saver$1 AnnotationRangeSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$3, SaversKt$ColorSaver$2.INSTANCE$3);
    public static final SaverKt$Saver$1 VerbatimTtsAnnotationSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$18, SaversKt$ColorSaver$2.INSTANCE$18);
    public static final SaverKt$Saver$1 UrlAnnotationSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$17, SaversKt$ColorSaver$2.INSTANCE$17);
    public static final SaverKt$Saver$1 ParagraphStyleSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$9, SaversKt$ColorSaver$2.INSTANCE$9);
    public static final SaverKt$Saver$1 SpanStyleSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$11, SaversKt$ColorSaver$2.INSTANCE$11);
    public static final SaverKt$Saver$1 TextDecorationSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$12, SaversKt$ColorSaver$2.INSTANCE$12);
    public static final SaverKt$Saver$1 TextGeometricTransformSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$13, SaversKt$ColorSaver$2.INSTANCE$13);
    public static final SaverKt$Saver$1 TextIndentSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$14, SaversKt$ColorSaver$2.INSTANCE$14);
    public static final SaverKt$Saver$1 FontWeightSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$5, SaversKt$ColorSaver$2.INSTANCE$5);
    public static final SaverKt$Saver$1 BaselineShiftSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$4, SaversKt$ColorSaver$2.INSTANCE$4);
    public static final SaverKt$Saver$1 TextRangeSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$15, SaversKt$ColorSaver$2.INSTANCE$15);
    public static final SaverKt$Saver$1 ShadowSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$10, SaversKt$ColorSaver$2.INSTANCE$10);
    public static final SaverKt$Saver$1 ColorSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE, SaversKt$ColorSaver$2.INSTANCE);
    public static final SaverKt$Saver$1 TextUnitSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$16, SaversKt$ColorSaver$2.INSTANCE$16);
    public static final SaverKt$Saver$1 OffsetSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$8, SaversKt$ColorSaver$2.INSTANCE$8);
    public static final SaverKt$Saver$1 LocaleListSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$6, SaversKt$ColorSaver$2.INSTANCE$6);
    public static final SaverKt$Saver$1 LocaleSaver = SaverKt.Saver(SaversKt$ColorSaver$1.INSTANCE$7, SaversKt$ColorSaver$2.INSTANCE$7);

    public static final Object save(Object obj, SaverKt$Saver$1 saverKt$Saver$1) {
        Object invoke;
        ResultKt.checkNotNullParameter(saverKt$Saver$1, "saver");
        ResultKt.checkNotNullParameter(null, "scope");
        return (obj == null || (invoke = saverKt$Saver$1.$save.invoke(null, obj)) == null) ? Boolean.FALSE : invoke;
    }
}
