package androidx.compose.ui.text.font;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public interface PlatformResolveInterceptor {

    /* loaded from: classes.dex */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Object();
    }

    static {
        Companion companion = Companion.$$INSTANCE;
    }

    default FontWeight interceptFontWeight(FontWeight fontWeight) {
        ResultKt.checkNotNullParameter(fontWeight, "fontWeight");
        return fontWeight;
    }
}
