package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerIconModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class BackwardsCompatNode$initializeModifier$1 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BackwardsCompatNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BackwardsCompatNode$initializeModifier$1(BackwardsCompatNode backwardsCompatNode, int i) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = backwardsCompatNode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit = Unit.INSTANCE;
        BackwardsCompatNode backwardsCompatNode = this.this$0;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        backwardsCompatNode.updateModifierLocalConsumer();
                        return unit;
                    default:
                        Modifier.Element element = backwardsCompatNode.element;
                        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.modifier.ModifierLocalConsumer");
                        ((PointerIconModifierLocal) ((ModifierLocalConsumer) element)).onModifierLocalsUpdated(backwardsCompatNode);
                        return unit;
                }
            default:
                switch (i) {
                    case 0:
                        backwardsCompatNode.updateModifierLocalConsumer();
                        return unit;
                    default:
                        Modifier.Element element2 = backwardsCompatNode.element;
                        ResultKt.checkNotNull(element2, "null cannot be cast to non-null type androidx.compose.ui.modifier.ModifierLocalConsumer");
                        ((PointerIconModifierLocal) ((ModifierLocalConsumer) element2)).onModifierLocalsUpdated(backwardsCompatNode);
                        return unit;
                }
        }
    }
}
