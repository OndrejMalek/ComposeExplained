package androidx.compose.ui.platform;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class WindowInfoImpl implements WindowInfo {
    public static final ParcelableSnapshotMutableState GlobalKeyboardModifiers = ResultKt.mutableStateOf(new PointerKeyboardModifiers(0), StructuralEqualityPolicy.INSTANCE);
    public final ParcelableSnapshotMutableState _isWindowFocused = ResultKt.mutableStateOf(Boolean.FALSE, StructuralEqualityPolicy.INSTANCE);
}
