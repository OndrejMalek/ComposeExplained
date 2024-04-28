package androidx.compose.ui.text.input;

import android.view.View;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class InputMethodManagerImpl {
    public final View view;

    public InputMethodManagerImpl(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
        ResultKt.lazy(new LayoutNode$_foldedChildren$1(14, this));
    }
}
