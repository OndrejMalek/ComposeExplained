package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class KeyInputNode extends Modifier.Node implements KeyInputModifierNode {
    public Function1 onEvent;
    public Function1 onPreEvent;

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onKeyEvent-ZmokQxo */
    public final boolean mo29onKeyEventZmokQxo(android.view.KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        Function1 function1 = this.onEvent;
        if (function1 != null) {
            return ((Boolean) function1.invoke(new KeyEvent(keyEvent))).booleanValue();
        }
        return false;
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onPreKeyEvent-ZmokQxo */
    public final boolean mo30onPreKeyEventZmokQxo(android.view.KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        Function1 function1 = this.onPreEvent;
        if (function1 != null) {
            return ((Boolean) function1.invoke(new KeyEvent(keyEvent))).booleanValue();
        }
        return false;
    }
}
