package kotlin.random;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.compose.ui.platform.AndroidUiDispatcher;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class FallbackThreadLocalRandom$implStorage$1 extends ThreadLocal {
    public final /* synthetic */ int $r8$classId;

    /* JADX DEBUG: Marked for inline */
    /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.ui.platform.AndroidUiDispatcher.<clinit>():void, kotlin.random.FallbackThreadLocalRandom.<init>():void] */
    public /* synthetic */ FallbackThreadLocalRandom$implStorage$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.$r8$classId) {
            case 0:
                return new java.util.Random();
            default:
                Choreographer choreographer = Choreographer.getInstance();
                ResultKt.checkNotNullExpressionValue(choreographer, "getInstance()");
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    throw new IllegalStateException("no Looper on this thread".toString());
                }
                Handler createAsync = Handler.createAsync(myLooper);
                ResultKt.checkNotNullExpressionValue(createAsync, "createAsync(\n           …d\")\n                    )");
                AndroidUiDispatcher androidUiDispatcher = new AndroidUiDispatcher(choreographer, createAsync);
                return ResultKt.plus((CoroutineContext.Element) androidUiDispatcher, (CoroutineContext) androidUiDispatcher.frameClock);
        }
    }
}
