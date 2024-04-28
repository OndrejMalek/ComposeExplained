package androidx.compose.ui.text.intl;

import androidx.compose.ui.draw.DrawResult;
import java.util.ArrayList;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidLocaleDelegateAPI24 {
    public LocaleList lastLocaleList;
    public android.os.LocaleList lastPlatformLocaleList;
    public final DrawResult lock = new Object();

    public final LocaleList getCurrent() {
        android.os.LocaleList localeList = android.os.LocaleList.getDefault();
        ResultKt.checkNotNullExpressionValue(localeList, "getDefault()");
        synchronized (this.lock) {
            LocaleList localeList2 = this.lastLocaleList;
            if (localeList2 != null && localeList == this.lastPlatformLocaleList) {
                return localeList2;
            }
            int size = localeList.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                java.util.Locale locale = localeList.get(i);
                ResultKt.checkNotNullExpressionValue(locale, "platformLocaleList[position]");
                arrayList.add(new Locale(new AndroidLocale(locale)));
            }
            LocaleList localeList3 = new LocaleList(arrayList);
            this.lastPlatformLocaleList = localeList;
            this.lastLocaleList = localeList3;
            return localeList3;
        }
    }
}
