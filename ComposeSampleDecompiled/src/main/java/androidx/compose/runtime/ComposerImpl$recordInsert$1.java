package androidx.compose.runtime;

import androidx.compose.runtime.CompositionImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposerImpl$recordInsert$1 extends Lambda implements Function3 {
    public final /* synthetic */ Object $anchor;
    public final /* synthetic */ Object $insertTable;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposerImpl$recordInsert$1(Object obj, int i, Object obj2) {
        super(3);
        this.$r8$classId = i;
        this.$insertTable = obj;
        this.$anchor = obj2;
    }

    public final void invoke(Applier applier, SlotWriter slotWriter, CompositionImpl.RememberEventDispatcher rememberEventDispatcher) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "slots");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                slotWriter.beginInsert();
                SlotTable slotTable = (SlotTable) this.$insertTable;
                Anchor anchor = (Anchor) this.$anchor;
                anchor.getClass();
                ResultKt.checkNotNullParameter(slotTable, "slots");
                slotWriter.moveFrom(slotTable, slotTable.anchorIndex(anchor));
                slotWriter.endInsert();
                return;
            case 1:
                ResultKt.checkNotNullParameter(applier, "applier");
                ResultKt.checkNotNullParameter(slotWriter, "<anonymous parameter 1>");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                ((Function2) this.$insertTable).invoke(((AbstractApplier) applier).current, this.$anchor);
                return;
            default:
                ResultKt.checkNotNullParameter(applier, "<anonymous parameter 0>");
                ResultKt.checkNotNullParameter(slotWriter, "<anonymous parameter 1>");
                ResultKt.checkNotNullParameter(rememberEventDispatcher, "<anonymous parameter 2>");
                ((Function1) this.$insertTable).invoke(((ComposerImpl) this.$anchor).composition);
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
            case 1:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
            default:
                invoke((Applier) obj, (SlotWriter) obj2, (CompositionImpl.RememberEventDispatcher) obj3);
                return unit;
        }
    }
}
