package androidx.compose.runtime;

import androidx.compose.runtime.CompositionImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposerImpl$updateValue$1 extends Lambda implements Function3 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposerImpl$updateValue$1(int i, Object obj) {
        super(3);
        this.$r8$classId = i;
        this.$value = obj;
    }

    public final void invoke(Applier applier, SlotWriter slotWriter, CompositionImpl.RememberEventDispatcher rememberEventDispatcher) {
        int i = this.$r8$classId;
        Object obj = this.$value;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "<anonymous parameter 1>");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "rememberManager");
                rememberEventDispatcher.remembering((RememberObserver) obj);
                return;
            default:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                slotWriter.updateAux(obj);
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            default:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
        }
    }
}
