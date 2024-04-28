package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokens;
import androidx.compose.ui.text.TextStyle;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Typography {
    public final TextStyle bodyLarge;
    public final TextStyle bodyMedium;
    public final TextStyle bodySmall;
    public final TextStyle displayLarge;
    public final TextStyle displayMedium;
    public final TextStyle displaySmall;
    public final TextStyle headlineLarge;
    public final TextStyle headlineMedium;
    public final TextStyle headlineSmall;
    public final TextStyle labelLarge;
    public final TextStyle labelMedium;
    public final TextStyle labelSmall;
    public final TextStyle titleLarge;
    public final TextStyle titleMedium;
    public final TextStyle titleSmall;

    public Typography(TextStyle textStyle, int i) {
        TextStyle textStyle2 = TypographyTokens.DisplayLarge;
        TextStyle textStyle3 = TypographyTokens.DisplayMedium;
        TextStyle textStyle4 = TypographyTokens.DisplaySmall;
        TextStyle textStyle5 = TypographyTokens.HeadlineLarge;
        TextStyle textStyle6 = TypographyTokens.HeadlineMedium;
        TextStyle textStyle7 = TypographyTokens.HeadlineSmall;
        TextStyle textStyle8 = TypographyTokens.TitleLarge;
        TextStyle textStyle9 = TypographyTokens.TitleMedium;
        TextStyle textStyle10 = TypographyTokens.TitleSmall;
        TextStyle textStyle11 = (i & 512) != 0 ? TypographyTokens.BodyLarge : textStyle;
        TextStyle textStyle12 = TypographyTokens.BodyMedium;
        TextStyle textStyle13 = TypographyTokens.BodySmall;
        TextStyle textStyle14 = TypographyTokens.LabelLarge;
        TextStyle textStyle15 = TypographyTokens.LabelMedium;
        TextStyle textStyle16 = TypographyTokens.LabelSmall;
        ResultKt.checkNotNullParameter(textStyle2, "displayLarge");
        ResultKt.checkNotNullParameter(textStyle3, "displayMedium");
        ResultKt.checkNotNullParameter(textStyle4, "displaySmall");
        ResultKt.checkNotNullParameter(textStyle5, "headlineLarge");
        ResultKt.checkNotNullParameter(textStyle6, "headlineMedium");
        ResultKt.checkNotNullParameter(textStyle7, "headlineSmall");
        ResultKt.checkNotNullParameter(textStyle8, "titleLarge");
        ResultKt.checkNotNullParameter(textStyle9, "titleMedium");
        ResultKt.checkNotNullParameter(textStyle10, "titleSmall");
        ResultKt.checkNotNullParameter(textStyle11, "bodyLarge");
        ResultKt.checkNotNullParameter(textStyle12, "bodyMedium");
        ResultKt.checkNotNullParameter(textStyle13, "bodySmall");
        ResultKt.checkNotNullParameter(textStyle14, "labelLarge");
        ResultKt.checkNotNullParameter(textStyle15, "labelMedium");
        ResultKt.checkNotNullParameter(textStyle16, "labelSmall");
        this.displayLarge = textStyle2;
        this.displayMedium = textStyle3;
        this.displaySmall = textStyle4;
        this.headlineLarge = textStyle5;
        this.headlineMedium = textStyle6;
        this.headlineSmall = textStyle7;
        this.titleLarge = textStyle8;
        this.titleMedium = textStyle9;
        this.titleSmall = textStyle10;
        this.bodyLarge = textStyle11;
        this.bodyMedium = textStyle12;
        this.bodySmall = textStyle13;
        this.labelLarge = textStyle14;
        this.labelMedium = textStyle15;
        this.labelSmall = textStyle16;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Typography)) {
            return false;
        }
        Typography typography = (Typography) obj;
        return ResultKt.areEqual(this.displayLarge, typography.displayLarge) && ResultKt.areEqual(this.displayMedium, typography.displayMedium) && ResultKt.areEqual(this.displaySmall, typography.displaySmall) && ResultKt.areEqual(this.headlineLarge, typography.headlineLarge) && ResultKt.areEqual(this.headlineMedium, typography.headlineMedium) && ResultKt.areEqual(this.headlineSmall, typography.headlineSmall) && ResultKt.areEqual(this.titleLarge, typography.titleLarge) && ResultKt.areEqual(this.titleMedium, typography.titleMedium) && ResultKt.areEqual(this.titleSmall, typography.titleSmall) && ResultKt.areEqual(this.bodyLarge, typography.bodyLarge) && ResultKt.areEqual(this.bodyMedium, typography.bodyMedium) && ResultKt.areEqual(this.bodySmall, typography.bodySmall) && ResultKt.areEqual(this.labelLarge, typography.labelLarge) && ResultKt.areEqual(this.labelMedium, typography.labelMedium) && ResultKt.areEqual(this.labelSmall, typography.labelSmall);
    }

    public final int hashCode() {
        return this.labelSmall.hashCode() + ((this.labelMedium.hashCode() + ((this.labelLarge.hashCode() + ((this.bodySmall.hashCode() + ((this.bodyMedium.hashCode() + ((this.bodyLarge.hashCode() + ((this.titleSmall.hashCode() + ((this.titleMedium.hashCode() + ((this.titleLarge.hashCode() + ((this.headlineSmall.hashCode() + ((this.headlineMedium.hashCode() + ((this.headlineLarge.hashCode() + ((this.displaySmall.hashCode() + ((this.displayMedium.hashCode() + (this.displayLarge.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Typography(displayLarge=" + this.displayLarge + ", displayMedium=" + this.displayMedium + ",displaySmall=" + this.displaySmall + ", headlineLarge=" + this.headlineLarge + ", headlineMedium=" + this.headlineMedium + ", headlineSmall=" + this.headlineSmall + ", titleLarge=" + this.titleLarge + ", titleMedium=" + this.titleMedium + ", titleSmall=" + this.titleSmall + ", bodyLarge=" + this.bodyLarge + ", bodyMedium=" + this.bodyMedium + ", bodySmall=" + this.bodySmall + ", labelLarge=" + this.labelLarge + ", labelMedium=" + this.labelMedium + ", labelSmall=" + this.labelSmall + ')';
    }
}
