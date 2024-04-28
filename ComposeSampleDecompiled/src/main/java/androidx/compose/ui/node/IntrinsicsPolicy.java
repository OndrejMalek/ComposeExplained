package androidx.compose.ui.node;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class IntrinsicsPolicy {
    public final ParcelableSnapshotMutableState measurePolicyState$delegate;

    public IntrinsicsPolicy(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        this.measurePolicyState$delegate = ResultKt.mutableStateOf(null, StructuralEqualityPolicy.INSTANCE);
    }
}
