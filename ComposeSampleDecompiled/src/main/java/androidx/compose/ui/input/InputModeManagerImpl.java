package androidx.compose.ui.input;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class InputModeManagerImpl implements InputModeManager {
    public final ParcelableSnapshotMutableState inputMode$delegate;

    public InputModeManagerImpl(int i, AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        this.inputMode$delegate = ResultKt.mutableStateOf(new InputMode(i), StructuralEqualityPolicy.INSTANCE);
    }
}
