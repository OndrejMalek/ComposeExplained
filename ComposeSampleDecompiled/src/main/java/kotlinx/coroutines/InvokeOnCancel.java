package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class InvokeOnCancel extends CancelHandler {
    public final /* synthetic */ int $r8$classId;
    public final Object handler;

    public InvokeOnCancel(int i, Object obj) {
        this.$r8$classId = i;
        this.handler = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        Object obj2 = this.handler;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                Throwable th = (Throwable) obj;
                switch (i) {
                    case 0:
                        ((Function1) obj2).invoke(th);
                        return unit;
                    default:
                        ((DisposableHandle) obj2).dispose();
                        return unit;
                }
            default:
                Throwable th2 = (Throwable) obj;
                switch (i) {
                    case 0:
                        ((Function1) obj2).invoke(th2);
                        return unit;
                    default:
                        ((DisposableHandle) obj2).dispose();
                        return unit;
                }
        }
    }

    public final String toString() {
        int i = this.$r8$classId;
        Object obj = this.handler;
        switch (i) {
            case 0:
                return "InvokeOnCancel[" + ((Function1) obj).getClass().getSimpleName() + '@' + _BOUNDARY.getHexAddress(this) + ']';
            default:
                return "DisposeOnCancel[" + ((DisposableHandle) obj) + ']';
        }
    }
}
