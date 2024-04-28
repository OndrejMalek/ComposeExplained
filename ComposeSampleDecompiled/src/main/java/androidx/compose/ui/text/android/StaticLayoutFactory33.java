package androidx.compose.ui.text.android;

import android.graphics.text.LineBreakConfig;
import android.text.StaticLayout;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class StaticLayoutFactory33 {
    public static final boolean isFallbackLineSpacingEnabled(StaticLayout staticLayout) {
        boolean isFallbackLineSpacingEnabled;
        ResultKt.checkNotNullParameter(staticLayout, "layout");
        isFallbackLineSpacingEnabled = staticLayout.isFallbackLineSpacingEnabled();
        return isFallbackLineSpacingEnabled;
    }

    public static final void setLineBreakConfig(StaticLayout.Builder builder, int i, int i2) {
        LineBreakConfig.Builder lineBreakStyle;
        LineBreakConfig.Builder lineBreakWordStyle;
        LineBreakConfig build;
        ResultKt.checkNotNullParameter(builder, "builder");
        lineBreakStyle = BoringLayoutFactory33$$ExternalSyntheticApiModelOutline0.m().setLineBreakStyle(i);
        lineBreakWordStyle = lineBreakStyle.setLineBreakWordStyle(i2);
        build = lineBreakWordStyle.build();
        ResultKt.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
        builder.setLineBreakConfig(build);
    }
}
