package kotlinx.coroutines.internal;

import androidx.startup.StartupException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.BufferedChannelKt;

/* loaded from: classes.dex */
public final class OnUndeliveredElementKt$bindCancellationFun$1 extends Lambda implements Function1 {
    public final /* synthetic */ Object $context;
    public final /* synthetic */ Object $element;
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object $this_bindCancellationFun;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnUndeliveredElementKt$bindCancellationFun$1(Function1 function1, Object obj, CoroutineContext coroutineContext) {
        super(1);
        this.$this_bindCancellationFun = function1;
        this.$element = obj;
        this.$context = coroutineContext;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Throwable) obj);
                return unit;
            default:
                invoke((Throwable) obj);
                return unit;
        }
    }

    public final void invoke(Throwable th) {
        int i = this.$r8$classId;
        Object obj = this.$context;
        Object obj2 = this.$this_bindCancellationFun;
        Object obj3 = this.$element;
        switch (i) {
            case 0:
                CoroutineContext coroutineContext = (CoroutineContext) obj;
                StartupException callUndeliveredElementCatchingException = JobKt.callUndeliveredElementCatchingException((Function1) obj2, obj3, null);
                if (callUndeliveredElementCatchingException != null) {
                    ResultKt.handleCoroutineException(coroutineContext, callUndeliveredElementCatchingException);
                    return;
                }
                return;
            default:
                if (obj3 == BufferedChannelKt.CHANNEL_CLOSED) {
                    return;
                }
                Function1 function1 = ((BufferedChannel) obj2).onUndeliveredElement;
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                throw null;
        }
    }
}
