package androidx.compose.ui.platform;

import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AccessibilityNodeInfoVerificationHelperMethods {
    public static final AccessibilityNodeInfoVerificationHelperMethods INSTANCE = new Object();

    public final void setAvailableExtraData(AccessibilityNodeInfo accessibilityNodeInfo, List<String> list) {
        ResultKt.checkNotNullParameter(accessibilityNodeInfo, "node");
        ResultKt.checkNotNullParameter(list, "data");
        accessibilityNodeInfo.setAvailableExtraData(list);
    }
}
