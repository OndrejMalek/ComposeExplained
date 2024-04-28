package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Recomposer;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Recomposer $recomposer;
    public final /* synthetic */ WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 $self;
    public final /* synthetic */ LifecycleOwner $source;
    public final /* synthetic */ Ref$ObjectRef $systemDurationScaleSettingConsumer;
    public final /* synthetic */ View $this_createLifecycleAwareWindowRecomposer;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(Ref$ObjectRef ref$ObjectRef, Recomposer recomposer, LifecycleOwner lifecycleOwner, WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2, View view, Continuation continuation) {
        super(2, continuation);
        this.$systemDurationScaleSettingConsumer = ref$ObjectRef;
        this.$recomposer = recomposer;
        this.$source = lifecycleOwner;
        this.$self = windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2;
        this.$this_createLifecycleAwareWindowRecomposer = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 = new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(this.$systemDurationScaleSettingConsumer, this.$recomposer, this.$source, this.$self, this.$this_createLifecycleAwareWindowRecomposer, continuation);
        windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0 = obj;
        return windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a9  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r11.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 0
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 r4 = r11.$self
            androidx.lifecycle.LifecycleOwner r5 = r11.$source
            r6 = 1
            if (r1 == 0) goto L24
            if (r1 != r6) goto L1c
            java.lang.Object r0 = r11.L$0
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L19
            goto L94
        L19:
            r12 = move-exception
            goto La7
        L1c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L24:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.jvm.internal.Ref$ObjectRef r1 = r11.$systemDurationScaleSettingConsumer     // Catch: java.lang.Throwable -> L63
            java.lang.Object r1 = r1.element     // Catch: java.lang.Throwable -> L63
            androidx.compose.ui.platform.MotionDurationScaleImpl r1 = (androidx.compose.ui.platform.MotionDurationScaleImpl) r1     // Catch: java.lang.Throwable -> L63
            if (r1 == 0) goto L65
            android.view.View r7 = r11.$this_createLifecycleAwareWindowRecomposer     // Catch: java.lang.Throwable -> L63
            android.content.Context r7 = r7.getContext()     // Catch: java.lang.Throwable -> L63
            android.content.Context r7 = r7.getApplicationContext()     // Catch: java.lang.Throwable -> L63
            java.lang.String r8 = "context.applicationContext"
            kotlin.ResultKt.checkNotNullExpressionValue(r7, r8)     // Catch: java.lang.Throwable -> L63
            kotlinx.coroutines.flow.StateFlow r7 = androidx.compose.ui.platform.WindowRecomposer_androidKt.access$getAnimationScaleFlowFor(r7)     // Catch: java.lang.Throwable -> L63
            java.lang.Object r8 = r7.getValue()     // Catch: java.lang.Throwable -> L63
            java.lang.Number r8 = (java.lang.Number) r8     // Catch: java.lang.Throwable -> L63
            float r8 = r8.floatValue()     // Catch: java.lang.Throwable -> L63
            androidx.compose.runtime.ParcelableSnapshotMutableFloatState r9 = r1.scaleFactor$delegate     // Catch: java.lang.Throwable -> L63
            r9.setFloatValue(r8)     // Catch: java.lang.Throwable -> L63
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1$1$1 r8 = new androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1$1$1     // Catch: java.lang.Throwable -> L63
            r8.<init>(r7, r1, r3)     // Catch: java.lang.Throwable -> L63
            r1 = 0
            r7 = 3
            kotlinx.coroutines.StandaloneCoroutine r12 = kotlin.ResultKt.launch$default(r12, r3, r1, r8, r7)     // Catch: java.lang.Throwable -> L63
            goto L66
        L61:
            r0 = r3
            goto La7
        L63:
            r12 = move-exception
            goto L61
        L65:
            r12 = r3
        L66:
            androidx.compose.runtime.Recomposer r1 = r11.$recomposer     // Catch: java.lang.Throwable -> La5
            r11.L$0 = r12     // Catch: java.lang.Throwable -> La5
            r11.label = r6     // Catch: java.lang.Throwable -> La5
            r1.getClass()     // Catch: java.lang.Throwable -> La5
            androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2 r6 = new androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2     // Catch: java.lang.Throwable -> La5
            r6.<init>(r1, r3)     // Catch: java.lang.Throwable -> La5
            kotlin.coroutines.CoroutineContext r7 = r11._context     // Catch: java.lang.Throwable -> La5
            kotlin.ResultKt.checkNotNull(r7)     // Catch: java.lang.Throwable -> La5
            androidx.compose.runtime.MonotonicFrameClock r7 = kotlin.ResultKt.getMonotonicFrameClock(r7)     // Catch: java.lang.Throwable -> La5
            androidx.compose.runtime.Recomposer$recompositionRunner$2 r8 = new androidx.compose.runtime.Recomposer$recompositionRunner$2     // Catch: java.lang.Throwable -> La5
            r8.<init>(r1, r6, r7, r3)     // Catch: java.lang.Throwable -> La5
            androidx.compose.runtime.BroadcastFrameClock r1 = r1.broadcastFrameClock     // Catch: java.lang.Throwable -> La5
            java.lang.Object r1 = kotlin.ResultKt.withContext(r1, r8, r11)     // Catch: java.lang.Throwable -> La5
            if (r1 != r0) goto L8b
            goto L8c
        L8b:
            r1 = r2
        L8c:
            if (r1 != r0) goto L8f
            goto L90
        L8f:
            r1 = r2
        L90:
            if (r1 != r0) goto L93
            return r0
        L93:
            r0 = r12
        L94:
            if (r0 == 0) goto L99
            r0.cancel(r3)
        L99:
            androidx.lifecycle.LifecycleRegistry r12 = r5.getLifecycle()
            r12.removeObserver(r4)
            return r2
        La1:
            r10 = r0
            r0 = r12
            r12 = r10
            goto La7
        La5:
            r0 = move-exception
            goto La1
        La7:
            if (r0 == 0) goto Lac
            r0.cancel(r3)
        Lac:
            androidx.lifecycle.LifecycleRegistry r0 = r5.getLifecycle()
            r0.removeObserver(r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
