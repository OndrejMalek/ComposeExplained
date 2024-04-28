package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public abstract class CreationExtras {
    public final LinkedHashMap map = new LinkedHashMap();

    /* loaded from: classes.dex */
    public final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new CreationExtras();
    }
}
