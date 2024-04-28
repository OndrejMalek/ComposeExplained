package androidx.compose.ui.node;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.InnerNodeCoordinator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class LayoutNodeLayoutDelegate$performMeasure$2 extends Lambda implements Function0 {
    public final /* synthetic */ long $constraints;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LayoutNodeLayoutDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LayoutNodeLayoutDelegate$performMeasure$2(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate, long j, int i) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = layoutNodeLayoutDelegate;
        this.$constraints = j;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                m184invoke();
                return unit;
            case 1:
                m184invoke();
                return unit;
            default:
                m184invoke();
                return unit;
        }
    }

    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function0.invoke()Ljava/lang/Object; */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m184invoke() {
        int i = this.$r8$classId;
        long j = this.$constraints;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.this$0;
        switch (i) {
            case 0:
                layoutNodeLayoutDelegate.getOuterCoordinator().mo164measureBRTryo0(j);
                return;
            case 1:
                LookaheadDelegate lookaheadDelegate = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
                ResultKt.checkNotNull(lookaheadDelegate);
                Placeable.PlacementScope.m168place70tqf50(lookaheadDelegate, j, 0.0f);
                return;
            default:
                LookaheadDelegate lookaheadDelegate2 = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
                ResultKt.checkNotNull(lookaheadDelegate2);
                ((InnerNodeCoordinator.LookaheadDelegateImpl) lookaheadDelegate2).mo164measureBRTryo0(j);
                return;
        }
    }
}
