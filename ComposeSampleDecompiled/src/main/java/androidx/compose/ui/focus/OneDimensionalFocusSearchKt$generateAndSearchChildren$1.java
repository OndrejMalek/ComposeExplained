package androidx.compose.ui.focus;

import _COROUTINE._BOUNDARY;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class OneDimensionalFocusSearchKt$generateAndSearchChildren$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $direction;
    public final /* synthetic */ FocusTargetNode $focusedItem;
    public final /* synthetic */ Function1 $onFound;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ FocusTargetNode $this_generateAndSearchChildren;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OneDimensionalFocusSearchKt$generateAndSearchChildren$1(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i, Function1 function1, int i2) {
        super(1);
        this.$r8$classId = i2;
        this.$this_generateAndSearchChildren = focusTargetNode;
        this.$focusedItem = focusTargetNode2;
        this.$direction = i;
        this.$onFound = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                invoke$11();
                throw null;
            default:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                invoke$11();
                throw null;
        }
    }

    public final void invoke$11() {
        int i = this.$r8$classId;
        Function1 function1 = this.$onFound;
        int i2 = this.$direction;
        FocusTargetNode focusTargetNode = this.$focusedItem;
        FocusTargetNode focusTargetNode2 = this.$this_generateAndSearchChildren;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(null, "$this$searchBeyondBounds");
                if (!_BOUNDARY.m19searchChildren4C6V_qg(focusTargetNode2, focusTargetNode, i2, function1)) {
                    throw null;
                }
                return;
            default:
                ResultKt.checkNotNullParameter(null, "$this$searchBeyondBounds");
                if (!_BOUNDARY.m20searchChildren4C6V_qg$1(focusTargetNode2, focusTargetNode, i2, function1)) {
                    throw null;
                }
                return;
        }
    }
}
