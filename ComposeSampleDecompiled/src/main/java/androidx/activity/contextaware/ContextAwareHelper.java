package androidx.activity.contextaware;

import android.content.Context;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public final class ContextAwareHelper {
    public volatile Context context;
    public final CopyOnWriteArraySet listeners = new CopyOnWriteArraySet();
}
