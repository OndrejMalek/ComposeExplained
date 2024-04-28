package androidx.compose.ui.text.input;

import androidx.compose.runtime.ParcelableSnapshotMutableIntState;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class PlatformTextInputPluginRegistryImpl implements PlatformTextInputPluginRegistry {
    public final SnapshotStateMap adaptersByPlugin = new SnapshotStateMap();
    public final Function2 factory;

    /* loaded from: classes.dex */
    public final class AdapterInput {
    }

    /* loaded from: classes.dex */
    public final class AdapterWithRefCount {
        public final PlatformTextInputAdapter adapter;
        public final ParcelableSnapshotMutableIntState refCount$delegate = new ParcelableSnapshotMutableIntState(0);

        public AdapterWithRefCount(PlatformTextInputAdapter platformTextInputAdapter) {
            this.adapter = platformTextInputAdapter;
        }
    }

    public PlatformTextInputPluginRegistryImpl(ComposedModifierKt$materialize$result$1 composedModifierKt$materialize$result$1) {
        this.factory = composedModifierKt$materialize$result$1;
    }
}
