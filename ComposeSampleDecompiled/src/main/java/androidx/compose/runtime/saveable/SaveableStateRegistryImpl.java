package androidx.compose.runtime.saveable;

import androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    public final Function1 canBeSaved = InspectableValueKt$NoInspectorInfo$1.INSTANCE$16;
    public final LinkedHashMap restored;
    public final LinkedHashMap valueProviders;

    public SaveableStateRegistryImpl(LinkedHashMap linkedHashMap) {
        this.restored = linkedHashMap != null ? new LinkedHashMap(linkedHashMap) : new LinkedHashMap();
        this.valueProviders = new LinkedHashMap();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public final Map performSave() {
        LinkedHashMap linkedHashMap = this.restored;
        ResultKt.checkNotNullParameter(linkedHashMap, "<this>");
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        for (Map.Entry entry : this.valueProviders.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            int size = list.size();
            Function1 function1 = this.canBeSaved;
            if (size == 1) {
                Object invoke = ((Function0) list.get(0)).invoke();
                if (invoke == null) {
                    continue;
                } else {
                    if (!((Boolean) function1.invoke(invoke)).booleanValue()) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    linkedHashMap2.put(str, ResultKt.arrayListOf(invoke));
                }
            } else {
                int size2 = list.size();
                ArrayList arrayList = new ArrayList(size2);
                for (int i = 0; i < size2; i++) {
                    Object invoke2 = ((Function0) list.get(i)).invoke();
                    if (invoke2 != null && !((Boolean) function1.invoke(invoke2)).booleanValue()) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    arrayList.add(invoke2);
                }
                linkedHashMap2.put(str, arrayList);
            }
        }
        return linkedHashMap2;
    }
}
