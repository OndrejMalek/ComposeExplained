package androidx.compose.ui.text.platform.extensions;

import android.text.style.LocaleSpan;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;

/* loaded from: classes.dex */
public final class LocaleListHelperMethods {
    public static final LocaleListHelperMethods INSTANCE = new Object();

    public final Object localeSpan(LocaleList localeList) {
        ResultKt.checkNotNullParameter(localeList, "localeList");
        ArrayList arrayList = new ArrayList(MapsKt___MapsJvmKt.collectionSizeOrDefault(localeList));
        for (Locale locale : localeList.localeList) {
            ResultKt.checkNotNullParameter(locale, "<this>");
            AndroidLocale androidLocale = locale.platformLocale;
            ResultKt.checkNotNull(androidLocale, "null cannot be cast to non-null type androidx.compose.ui.text.intl.AndroidLocale");
            arrayList.add(androidLocale.javaLocale);
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        return new LocaleSpan(new android.os.LocaleList((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }

    public final void setTextLocales(AndroidTextPaint androidTextPaint, LocaleList localeList) {
        ResultKt.checkNotNullParameter(androidTextPaint, "textPaint");
        ResultKt.checkNotNullParameter(localeList, "localeList");
        ArrayList arrayList = new ArrayList(MapsKt___MapsJvmKt.collectionSizeOrDefault(localeList));
        for (Locale locale : localeList.localeList) {
            ResultKt.checkNotNullParameter(locale, "<this>");
            AndroidLocale androidLocale = locale.platformLocale;
            ResultKt.checkNotNull(androidLocale, "null cannot be cast to non-null type androidx.compose.ui.text.intl.AndroidLocale");
            arrayList.add(androidLocale.javaLocale);
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        androidTextPaint.setTextLocales(new android.os.LocaleList((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }
}
