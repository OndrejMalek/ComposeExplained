package kotlin.coroutines.jvm.internal;

import androidx.core.view.MenuHostHelper;

/* loaded from: classes.dex */
public abstract class ModuleNameRetriever {
    public static MenuHostHelper cache;
    public static final MenuHostHelper notOnJava9 = new MenuHostHelper(null, null, null);
}
