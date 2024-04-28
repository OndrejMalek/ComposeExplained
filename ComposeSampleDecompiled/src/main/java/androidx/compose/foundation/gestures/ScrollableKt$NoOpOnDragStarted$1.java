package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* loaded from: classes.dex */
public final class ScrollableKt$NoOpOnDragStarted$1 extends SuspendLambda implements Function3 {
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ScrollableKt$NoOpOnDragStarted$1(int i, Continuation continuation) {
        super(3, continuation);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                long j = ((Offset) obj2).packedValue;
                new ScrollableKt$NoOpOnDragStarted$1(0, (Continuation) obj3).invokeSuspend(unit);
                return unit;
            default:
                long j2 = ((Offset) obj2).packedValue;
                new ScrollableKt$NoOpOnDragStarted$1(1, (Continuation) obj3).invokeSuspend(unit);
                return unit;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                ResultKt.throwOnFailure(obj);
                return unit;
            default:
                ResultKt.throwOnFailure(obj);
                return unit;
        }
    }
}
