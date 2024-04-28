package androidx.core.provider;

import androidx.compose.runtime.Stack;
import androidx.compose.ui.text.platform.DefaultImpl$getFontLoadState$initCallback$1;
import androidx.compose.ui.text.platform.EmojiCompatStatusKt;
import androidx.compose.ui.text.platform.ImmutableBool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class CallbackWithHandler$2 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final Object this$0;
    public final Object val$callback;
    public final int val$reason;

    public CallbackWithHandler$2(DefaultImpl$getFontLoadState$initCallback$1 defaultImpl$getFontLoadState$initCallback$1, int i) {
        this(Arrays.asList(defaultImpl$getFontLoadState$initCallback$1), i, null);
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.val$callback;
        switch (i) {
            case 0:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(((Stack) obj).backing);
                return;
            default:
                List list = (List) obj;
                int size = list.size();
                int i2 = 0;
                if (this.val$reason != 1) {
                    while (i2 < size) {
                        ((DefaultImpl$getFontLoadState$initCallback$1) list.get(i2)).this$0.loadState = EmojiCompatStatusKt.Falsey;
                        i2++;
                    }
                    return;
                }
                while (i2 < size) {
                    DefaultImpl$getFontLoadState$initCallback$1 defaultImpl$getFontLoadState$initCallback$1 = (DefaultImpl$getFontLoadState$initCallback$1) list.get(i2);
                    defaultImpl$getFontLoadState$initCallback$1.$mutableLoaded.setValue(Boolean.TRUE);
                    defaultImpl$getFontLoadState$initCallback$1.this$0.loadState = new ImmutableBool(true);
                    i2++;
                }
                return;
        }
    }

    public CallbackWithHandler$2(int i, ArrayList arrayList) {
        this(arrayList, i, null);
    }

    public CallbackWithHandler$2(List list, int i, Throwable th) {
        ResultKt.checkNotNull$1(list, "initCallbacks cannot be null");
        this.val$callback = new ArrayList(list);
        this.val$reason = i;
    }
}
