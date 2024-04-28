package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes.dex */
public final class MutexImpl$CancellableContinuationWithOwner$resume$2 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MutexImpl this$0;
    public final /* synthetic */ MutexImpl.CancellableContinuationWithOwner this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MutexImpl$CancellableContinuationWithOwner$resume$2(MutexImpl mutexImpl, MutexImpl.CancellableContinuationWithOwner cancellableContinuationWithOwner, int i) {
        super(1);
        this.$r8$classId = i;
        this.this$0 = mutexImpl;
        this.this$1 = cancellableContinuationWithOwner;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        MutexImpl.CancellableContinuationWithOwner cancellableContinuationWithOwner = this.this$1;
        MutexImpl mutexImpl = this.this$0;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        mutexImpl.unlock(cancellableContinuationWithOwner.owner);
                        return unit;
                    default:
                        MutexImpl.owner$FU.set(mutexImpl, cancellableContinuationWithOwner.owner);
                        mutexImpl.unlock(cancellableContinuationWithOwner.owner);
                        return unit;
                }
            default:
                switch (i) {
                    case 0:
                        mutexImpl.unlock(cancellableContinuationWithOwner.owner);
                        return unit;
                    default:
                        MutexImpl.owner$FU.set(mutexImpl, cancellableContinuationWithOwner.owner);
                        mutexImpl.unlock(cancellableContinuationWithOwner.owner);
                        return unit;
                }
        }
    }
}
