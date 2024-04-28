package androidx.compose.runtime;

import androidx.compose.runtime.Recomposer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;

/* loaded from: classes.dex */
public final class Pending$keyMap$2 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Pending$keyMap$2(int i, Object obj) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        CancellableContinuation deriveStateLocked;
        switch (this.$r8$classId) {
            case 0:
                HashMap hashMap = new HashMap();
                Pending pending = (Pending) this.this$0;
                int size = pending.keyInfos.size();
                for (int i = 0; i < size; i++) {
                    KeyInfo keyInfo = (KeyInfo) pending.keyInfos.get(i);
                    Object obj = keyInfo.objectKey;
                    int i2 = keyInfo.key;
                    Object joinedKey = obj != null ? new JoinedKey(Integer.valueOf(i2), keyInfo.objectKey) : Integer.valueOf(i2);
                    Object obj2 = hashMap.get(joinedKey);
                    if (obj2 == null) {
                        obj2 = new LinkedHashSet();
                        hashMap.put(joinedKey, obj2);
                    }
                    ((LinkedHashSet) obj2).add(keyInfo);
                }
                return hashMap;
            default:
                Recomposer recomposer = (Recomposer) this.this$0;
                synchronized (recomposer.stateLock) {
                    deriveStateLocked = recomposer.deriveStateLocked();
                    if (((Recomposer.State) recomposer._state.getValue()).compareTo(Recomposer.State.ShuttingDown) <= 0) {
                        Throwable th = recomposer.closeCause;
                        CancellationException cancellationException = new CancellationException("Recomposer shutdown; frame clock awaiter will never resume");
                        cancellationException.initCause(th);
                        throw cancellationException;
                    }
                }
                if (deriveStateLocked != null) {
                    deriveStateLocked.resumeWith(Unit.INSTANCE);
                }
                return Unit.INSTANCE;
        }
    }
}
