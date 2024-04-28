package androidx.compose.ui.platform;

import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryImpl;
import eu.malek.composesample2.ui.theme.ThemeKt$ComposeSample2Theme$1;
import java.util.Map;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class DisposableSaveableStateRegistry implements SaveableStateRegistry {
    public final /* synthetic */ SaveableStateRegistry $$delegate_0;
    public final Function0 onDispose;

    public DisposableSaveableStateRegistry(SaveableStateRegistryImpl saveableStateRegistryImpl, ThemeKt$ComposeSample2Theme$1 themeKt$ComposeSample2Theme$1) {
        this.onDispose = themeKt$ComposeSample2Theme$1;
        this.$$delegate_0 = saveableStateRegistryImpl;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public final Map performSave() {
        return this.$$delegate_0.performSave();
    }
}
