package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class NodeCoordinator$hitNear$1 extends Lambda implements Function0 {
    public final /* synthetic */ float $distanceFromEdge;
    public final /* synthetic */ HitTestResult $hitTestResult;
    public final /* synthetic */ NodeCoordinator.HitTestSource $hitTestSource;
    public final /* synthetic */ boolean $isInLayer;
    public final /* synthetic */ boolean $isTouchEvent;
    public final /* synthetic */ long $pointerPosition;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Modifier.Node $this_hitNear;
    public final /* synthetic */ NodeCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NodeCoordinator$hitNear$1(NodeCoordinator nodeCoordinator, Modifier.Node node, NodeCoordinator.HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2, float f, int i) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = nodeCoordinator;
        this.$this_hitNear = node;
        this.$hitTestSource = hitTestSource;
        this.$pointerPosition = j;
        this.$hitTestResult = hitTestResult;
        this.$isTouchEvent = z;
        this.$isInLayer = z2;
        this.$distanceFromEdge = f;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                m204invoke();
                return unit;
            default:
                m204invoke();
                return unit;
        }
    }

    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function0.invoke()Ljava/lang/Object; */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m204invoke() {
        int i = 8;
        int i2 = this.$r8$classId;
        Modifier.Node node = this.$this_hitNear;
        NodeCoordinator.HitTestSource hitTestSource = this.$hitTestSource;
        switch (i2) {
            case 0:
                switch (((ULong.Companion) hitTestSource).$r8$classId) {
                    case 0:
                        break;
                    default:
                        i = 16;
                        break;
                }
                this.this$0.m198hitNearJHbHoSQ(Snake.m211access$nextUntilhw7D004(node, i), this.$hitTestSource, this.$pointerPosition, this.$hitTestResult, this.$isTouchEvent, this.$isInLayer, this.$distanceFromEdge);
                return;
            default:
                switch (((ULong.Companion) hitTestSource).$r8$classId) {
                    case 0:
                        break;
                    default:
                        i = 16;
                        break;
                }
                this.this$0.m201speculativeHitJHbHoSQ(Snake.m211access$nextUntilhw7D004(node, i), this.$hitTestSource, this.$pointerPosition, this.$hitTestResult, this.$isTouchEvent, this.$isInLayer, this.$distanceFromEdge);
                return;
        }
    }
}
