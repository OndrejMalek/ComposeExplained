package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.StaticLayout;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class StaticLayoutFactory23 implements StaticLayoutFactoryImpl {
    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public StaticLayout create(StaticLayoutParams staticLayoutParams) {
        ResultKt.checkNotNullParameter(staticLayoutParams, "params");
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(staticLayoutParams.text, staticLayoutParams.start, staticLayoutParams.end, staticLayoutParams.paint, staticLayoutParams.width);
        obtain.setTextDirection(staticLayoutParams.textDir);
        obtain.setAlignment(staticLayoutParams.alignment);
        obtain.setMaxLines(staticLayoutParams.maxLines);
        obtain.setEllipsize(staticLayoutParams.ellipsize);
        obtain.setEllipsizedWidth(staticLayoutParams.ellipsizedWidth);
        obtain.setLineSpacing(staticLayoutParams.lineSpacingExtra, staticLayoutParams.lineSpacingMultiplier);
        obtain.setIncludePad(staticLayoutParams.includePadding);
        obtain.setBreakStrategy(staticLayoutParams.breakStrategy);
        obtain.setHyphenationFrequency(staticLayoutParams.hyphenationFrequency);
        obtain.setIndents(staticLayoutParams.leftIndents, staticLayoutParams.rightIndents);
        int i = Build.VERSION.SDK_INT;
        StaticLayoutFactory26.setJustificationMode(obtain, staticLayoutParams.justificationMode);
        StaticLayoutFactory28.setUseLineSpacingFromFallbacks(obtain, staticLayoutParams.useFallbackLineSpacing);
        if (i >= 33) {
            StaticLayoutFactory33.setLineBreakConfig(obtain, staticLayoutParams.lineBreakStyle, staticLayoutParams.lineBreakWordStyle);
        }
        StaticLayout build = obtain.build();
        ResultKt.checkNotNullExpressionValue(build, "obtain(params.text, para…  }\n            }.build()");
        return build;
    }
}
