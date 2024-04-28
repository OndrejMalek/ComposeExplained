package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    public final transient LinkedHashMap keyToCallback;
    public final ArrayList launchedKeys;
    public final LinkedHashMap parsedPendingResults;
    public final Bundle pendingResults;
    public final LinkedHashMap rcToKey = new LinkedHashMap();
    public final LinkedHashMap keyToRc = new LinkedHashMap();

    public ActivityResultRegistry() {
        new LinkedHashMap();
        this.launchedKeys = new ArrayList();
        this.keyToCallback = new LinkedHashMap();
        this.parsedPendingResults = new LinkedHashMap();
        this.pendingResults = new Bundle();
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        String str = (String) this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        DurationKt$$ExternalSyntheticCheckNotZero0.m(this.keyToCallback.get(str));
        this.parsedPendingResults.remove(str);
        this.pendingResults.putParcelable(str, new ActivityResult(intent, i2));
        return true;
    }
}
