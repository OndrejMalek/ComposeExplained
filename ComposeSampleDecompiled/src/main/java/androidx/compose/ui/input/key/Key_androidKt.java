package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class Key_androidKt {
    public static final long Key(int i) {
        long j = (i << 32) | (0 & 4294967295L);
        int i2 = Key.$r8$clinit;
        return j;
    }

    /* renamed from: getKey-ZmokQxo, reason: not valid java name */
    public static final long m151getKeyZmokQxo(android.view.KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "$this$key");
        return Key(keyEvent.getKeyCode());
    }

    /* renamed from: getType-ZmokQxo, reason: not valid java name */
    public static final int m152getTypeZmokQxo(android.view.KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "$this$type");
        int action = keyEvent.getAction();
        if (action != 0) {
            return action != 1 ? 0 : 1;
        }
        return 2;
    }

    public static final Modifier onKeyEvent(AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        return new KeyInputElement(androidComposeView$focusOwner$1);
    }
}
