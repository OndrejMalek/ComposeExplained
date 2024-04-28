package androidx.compose.ui.platform;

import androidx.compose.ui.node.OwnerScope;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ScrollObservationScope implements OwnerScope {
    public final List allScopes;
    public final int semanticsNodeId;

    public ScrollObservationScope(int i, ArrayList arrayList) {
        ResultKt.checkNotNullParameter(arrayList, "allScopes");
        this.semanticsNodeId = i;
        this.allScopes = arrayList;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return this.allScopes.contains(this);
    }
}
