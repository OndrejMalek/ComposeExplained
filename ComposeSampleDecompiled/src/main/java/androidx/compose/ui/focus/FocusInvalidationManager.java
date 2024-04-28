package androidx.compose.ui.focus;

import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import java.util.LinkedHashSet;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class FocusInvalidationManager {
    public final Function1 onRequestApplyChangesListener;
    public final LinkedHashSet focusTargetNodes = new LinkedHashSet();
    public final LinkedHashSet focusEventNodes = new LinkedHashSet();
    public final LinkedHashSet focusPropertiesNodes = new LinkedHashSet();
    public final LayoutNode$_foldedChildren$1 invalidateNodes = new LayoutNode$_foldedChildren$1(6, this);

    public FocusInvalidationManager(AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        this.onRequestApplyChangesListener = androidComposeView$focusOwner$1;
    }

    public final void scheduleInvalidation(LinkedHashSet linkedHashSet, Object obj) {
        if (linkedHashSet.add(obj)) {
            if (this.focusPropertiesNodes.size() + this.focusEventNodes.size() + this.focusTargetNodes.size() == 1) {
                this.onRequestApplyChangesListener.invoke(this.invalidateNodes);
            }
        }
    }
}
