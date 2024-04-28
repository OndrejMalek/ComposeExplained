package androidx.compose.ui.platform;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public final class CompositionLocalsKt$LocalDensity$1 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$1 = new CompositionLocalsKt$LocalDensity$1(1);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$2 = new CompositionLocalsKt$LocalDensity$1(2);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$3 = new CompositionLocalsKt$LocalDensity$1(3);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$4 = new CompositionLocalsKt$LocalDensity$1(4);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$5 = new CompositionLocalsKt$LocalDensity$1(5);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$6 = new CompositionLocalsKt$LocalDensity$1(6);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$7 = new CompositionLocalsKt$LocalDensity$1(7);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$8 = new CompositionLocalsKt$LocalDensity$1(8);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$9 = new CompositionLocalsKt$LocalDensity$1(9);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$10 = new CompositionLocalsKt$LocalDensity$1(10);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$11 = new CompositionLocalsKt$LocalDensity$1(11);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE = new CompositionLocalsKt$LocalDensity$1(0);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$12 = new CompositionLocalsKt$LocalDensity$1(12);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$13 = new CompositionLocalsKt$LocalDensity$1(13);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$14 = new CompositionLocalsKt$LocalDensity$1(14);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$15 = new CompositionLocalsKt$LocalDensity$1(15);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$16 = new CompositionLocalsKt$LocalDensity$1(16);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$17 = new CompositionLocalsKt$LocalDensity$1(17);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$18 = new CompositionLocalsKt$LocalDensity$1(18);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$19 = new CompositionLocalsKt$LocalDensity$1(19);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$20 = new CompositionLocalsKt$LocalDensity$1(20);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$21 = new CompositionLocalsKt$LocalDensity$1(21);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$22 = new CompositionLocalsKt$LocalDensity$1(22);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$23 = new CompositionLocalsKt$LocalDensity$1(23);
    public static final CompositionLocalsKt$LocalDensity$1 INSTANCE$24 = new CompositionLocalsKt$LocalDensity$1(24);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CompositionLocalsKt$LocalDensity$1(int i) {
        super(0);
        this.$r8$classId = i;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Choreographer choreographer;
        switch (this.$r8$classId) {
            case 0:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalDensity");
                throw null;
            case 1:
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalConfiguration");
                throw null;
            case 2:
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalContext");
                throw null;
            case 3:
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalImageVectorCache");
                throw null;
            case 4:
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalLifecycleOwner");
                throw null;
            case 5:
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalSavedStateRegistryOwner");
                throw null;
            case 6:
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalView");
                throw null;
            case 7:
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    choreographer = Choreographer.getInstance();
                } else {
                    DefaultScheduler defaultScheduler = Dispatchers.Default;
                    choreographer = (Choreographer) ResultKt.runBlocking(MainDispatcherLoader.dispatcher, new SuspendLambda(2, null));
                }
                ResultKt.checkNotNullExpressionValue(choreographer, "if (isMainThread()) Chor…eographer.getInstance() }");
                Handler createAsync = Handler.createAsync(Looper.getMainLooper());
                ResultKt.checkNotNullExpressionValue(createAsync, "createAsync(Looper.getMainLooper())");
                AndroidUiDispatcher androidUiDispatcher = new AndroidUiDispatcher(choreographer, createAsync);
                return ResultKt.plus((CoroutineContext.Element) androidUiDispatcher, (CoroutineContext) androidUiDispatcher.frameClock);
            case 8:
            case 9:
                return null;
            case 10:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalAutofillTree");
                throw null;
            case 11:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalClipboardManager");
                throw null;
            case 12:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalFocusManager");
                throw null;
            case 13:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalFontFamilyResolver");
                throw null;
            case 14:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalFontLoader");
                throw null;
            case 15:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalHapticFeedback");
                throw null;
            case 16:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalInputManager");
                throw null;
            case 17:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalLayoutDirection");
                throw null;
            case 18:
                throw new IllegalStateException("No PlatformTextInputPluginRegistry provided".toString());
            case 19:
            case 20:
                return null;
            case 21:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalTextToolbar");
                throw null;
            case 22:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalUriHandler");
                throw null;
            case 23:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalViewConfiguration");
                throw null;
            default:
                CompositionLocalsKt.access$noLocalProvidedFor("LocalWindowInfo");
                throw null;
        }
    }
}
