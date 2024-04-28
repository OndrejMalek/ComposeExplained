package androidx.compose.ui.platform;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import androidx.compose.runtime.CompositionContext;
import eu.malek.composesample.R;
import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public abstract class WindowRecomposer_androidKt {
    public static final LinkedHashMap animationScale = new LinkedHashMap();

    public static final StateFlow access$getAnimationScaleFlowFor(Context context) {
        StateFlow stateFlow;
        LinkedHashMap linkedHashMap = animationScale;
        synchronized (linkedHashMap) {
            try {
                Object obj = linkedHashMap.get(context);
                if (obj == null) {
                    ContentResolver contentResolver = context.getContentResolver();
                    Uri uriFor = Settings.Global.getUriFor("animator_duration_scale");
                    BufferedChannel Channel$default = JobKt.Channel$default(-1, null, 6);
                    SafeFlow safeFlow = new SafeFlow(new WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1(contentResolver, uriFor, new ContentObserver(Handler.createAsync(Looper.getMainLooper())) { // from class: androidx.emoji2.text.FontRequestEmojiCompatConfig.FontRequestMetadataLoader.1
                        public final /* synthetic */ int $r8$classId;
                        public final /* synthetic */ Object this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public /* synthetic */ AnonymousClass1(BufferedChannel Channel$default2, Handler handler, int i) {
                            super(handler);
                            r3 = i;
                            r1 = Channel$default2;
                        }

                        @Override // android.database.ContentObserver
                        public final void onChange(boolean z, Uri uri) {
                            int i = r3;
                            Object obj2 = r1;
                            switch (i) {
                                case 0:
                                    ((FontRequestMetadataLoader) obj2).loadInternal();
                                    return;
                                default:
                                    ((Channel) obj2).mo306trySendJP2dKIU(Unit.INSTANCE);
                                    return;
                            }
                        }
                    }, Channel$default2, context, null));
                    JobImpl jobImpl = new JobImpl(null);
                    DefaultScheduler defaultScheduler = Dispatchers.Default;
                    obj = JobKt.stateIn(safeFlow, new ContextScope(ResultKt.plus((CoroutineContext.Element) jobImpl, (CoroutineContext) MainDispatcherLoader.dispatcher)), new StartedWhileSubscribed(0L, Long.MAX_VALUE), Float.valueOf(Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f)));
                    linkedHashMap.put(context, obj);
                }
                stateFlow = (StateFlow) obj;
            } catch (Throwable th) {
                throw th;
            }
        }
        return stateFlow;
    }

    public static final CompositionContext getCompositionContext(View view) {
        ResultKt.checkNotNullParameter(view, "<this>");
        Object tag = view.getTag(R.id.androidx_compose_ui_view_composition_context);
        if (tag instanceof CompositionContext) {
            return (CompositionContext) tag;
        }
        return null;
    }
}
