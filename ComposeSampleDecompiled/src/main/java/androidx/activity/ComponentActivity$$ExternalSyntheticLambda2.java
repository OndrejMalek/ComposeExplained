package androidx.activity;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda2 {
    public final /* synthetic */ ComponentActivity f$0;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.activity.ComponentActivity.<init>():void] */
    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda2(ComponentActivity componentActivity) {
        this.f$0 = componentActivity;
    }

    public final void onContextAvailable(Context context) {
        ComponentActivity componentActivity = this.f$0;
        ResultKt.checkNotNullParameter(componentActivity, "this$0");
        ResultKt.checkNotNullParameter(context, "it");
        Bundle consumeRestoredStateForKey = componentActivity.savedStateRegistryController.savedStateRegistry.consumeRestoredStateForKey("android:support:activity-result");
        if (consumeRestoredStateForKey != null) {
            ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1 = componentActivity.activityResultRegistry;
            componentActivity$activityResultRegistry$1.getClass();
            ArrayList<Integer> integerArrayList = consumeRestoredStateForKey.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = consumeRestoredStateForKey.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList == null || integerArrayList == null) {
                return;
            }
            ArrayList<String> stringArrayList2 = consumeRestoredStateForKey.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            if (stringArrayList2 != null) {
                componentActivity$activityResultRegistry$1.launchedKeys.addAll(stringArrayList2);
            }
            Bundle bundle = consumeRestoredStateForKey.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
            Bundle bundle2 = componentActivity$activityResultRegistry$1.pendingResults;
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            int size = stringArrayList.size();
            for (int i = 0; i < size; i++) {
                String str = stringArrayList.get(i);
                LinkedHashMap linkedHashMap = componentActivity$activityResultRegistry$1.keyToRc;
                boolean containsKey = linkedHashMap.containsKey(str);
                LinkedHashMap linkedHashMap2 = componentActivity$activityResultRegistry$1.rcToKey;
                if (containsKey) {
                    Integer num = (Integer) linkedHashMap.remove(str);
                    if (!bundle2.containsKey(str)) {
                        ResultKt.asMutableMap(linkedHashMap2);
                        linkedHashMap2.remove(num);
                    }
                }
                Integer num2 = integerArrayList.get(i);
                ResultKt.checkNotNullExpressionValue(num2, "rcs[i]");
                int intValue = num2.intValue();
                String str2 = stringArrayList.get(i);
                ResultKt.checkNotNullExpressionValue(str2, "keys[i]");
                String str3 = str2;
                linkedHashMap2.put(Integer.valueOf(intValue), str3);
                linkedHashMap.put(str3, Integer.valueOf(intValue));
            }
        }
    }
}
