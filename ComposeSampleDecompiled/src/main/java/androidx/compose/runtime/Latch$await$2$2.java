package androidx.compose.runtime;

import androidx.compose.runtime.BroadcastFrameClock;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.IdentityArraySet;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CancellableContinuation;

/* loaded from: classes.dex */
public final class Latch$await$2$2 extends Lambda implements Function1 {
    public final /* synthetic */ Object $co;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Latch$await$2$2(Object obj, int i, Object obj2) {
        super(1);
        this.$r8$classId = i;
        this.this$0 = obj;
        this.$co = obj2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Throwable) obj);
                return unit;
            case 1:
                ResultKt.checkNotNullParameter(obj, "value");
                ((CompositionImpl) this.this$0).recordWriteOf(obj);
                IdentityArraySet identityArraySet = (IdentityArraySet) this.$co;
                if (identityArraySet != null) {
                    identityArraySet.add(obj);
                }
                return unit;
            case 2:
                invoke((Throwable) obj);
                return unit;
            default:
                invoke((Throwable) obj);
                return unit;
        }
    }

    public final void invoke(Throwable th) {
        Throwable th2 = null;
        switch (this.$r8$classId) {
            case 0:
                Latch latch = (Latch) this.this$0;
                Object obj = latch.lock;
                CancellableContinuation cancellableContinuation = (CancellableContinuation) this.$co;
                synchronized (obj) {
                    ((List) latch.awaiters).remove(cancellableContinuation);
                }
                return;
            case 1:
            default:
                Recomposer recomposer = (Recomposer) this.this$0;
                Object obj2 = recomposer.stateLock;
                Throwable th3 = (Throwable) this.$co;
                synchronized (obj2) {
                    if (th3 != null) {
                        if (th != null) {
                            try {
                                if (!(!(th instanceof CancellationException))) {
                                    th = null;
                                }
                                if (th != null) {
                                    ResultKt.addSuppressed(th3, th);
                                }
                            } catch (Throwable th4) {
                                throw th4;
                            }
                        }
                        th2 = th3;
                    }
                    recomposer.closeCause = th2;
                    recomposer._state.setValue(Recomposer.State.ShutDown);
                }
                return;
            case 2:
                BroadcastFrameClock broadcastFrameClock = (BroadcastFrameClock) this.this$0;
                Object obj3 = broadcastFrameClock.lock;
                Ref$ObjectRef ref$ObjectRef = (Ref$ObjectRef) this.$co;
                synchronized (obj3) {
                    List list = broadcastFrameClock.awaiters;
                    Object obj4 = ref$ObjectRef.element;
                    if (obj4 == null) {
                        ResultKt.throwUninitializedPropertyAccessException("awaiter");
                        throw null;
                    }
                    list.remove((BroadcastFrameClock.FrameAwaiter) obj4);
                }
                return;
        }
    }
}
