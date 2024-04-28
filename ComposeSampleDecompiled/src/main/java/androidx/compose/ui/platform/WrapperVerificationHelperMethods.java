package androidx.compose.ui.platform;

import android.view.View;
import java.util.Map;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class WrapperVerificationHelperMethods {
    public static final WrapperVerificationHelperMethods INSTANCE = new Object();

    public final Map<Integer, Integer> attributeSourceResourceMap(View view) {
        Map<Integer, Integer> attributeSourceResourceMap;
        ResultKt.checkNotNullParameter(view, "view");
        attributeSourceResourceMap = view.getAttributeSourceResourceMap();
        ResultKt.checkNotNullExpressionValue(attributeSourceResourceMap, "view.attributeSourceResourceMap");
        return attributeSourceResourceMap;
    }
}
