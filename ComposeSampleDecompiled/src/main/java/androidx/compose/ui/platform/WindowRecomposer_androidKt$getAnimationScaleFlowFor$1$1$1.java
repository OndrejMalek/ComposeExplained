package androidx.compose.ui.platform;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes.dex */
public final class WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Uri $animationScaleUri;
    public final /* synthetic */ Context $applicationContext;
    public final /* synthetic */ Channel $channel;
    public final /* synthetic */ FontRequestEmojiCompatConfig.FontRequestMetadataLoader.AnonymousClass1 $contentObserver;
    public final /* synthetic */ ContentResolver $resolver;
    public /* synthetic */ Object L$0;
    public BufferedChannel.BufferedChannelIterator L$1;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1(ContentResolver contentResolver, Uri uri, FontRequestEmojiCompatConfig.FontRequestMetadataLoader.AnonymousClass1 anonymousClass1, Channel channel, Context context, Continuation continuation) {
        super(2, continuation);
        this.$resolver = contentResolver;
        this.$animationScaleUri = uri;
        this.$contentObserver = anonymousClass1;
        this.$channel = channel;
        this.$applicationContext = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1 windowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1 = new WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1(this.$resolver, this.$animationScaleUri, this.$contentObserver, this.$channel, this.$applicationContext, continuation);
        windowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1.L$0 = obj;
        return windowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005a A[Catch: all -> 0x001b, TRY_LEAVE, TryCatch #0 {all -> 0x001b, blocks: (B:7:0x0016, B:9:0x0042, B:14:0x0052, B:16:0x005a, B:25:0x002b, B:27:0x003c), top: B:2:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x007a -> B:8:0x0019). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r10.label
            r2 = 2
            r3 = 1
            androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader$1 r4 = r10.$contentObserver
            android.content.ContentResolver r5 = r10.$resolver
            if (r1 == 0) goto L2f
            if (r1 == r3) goto L25
            if (r1 != r2) goto L1d
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = r10.L$1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L1b
        L19:
            r11 = r6
            goto L42
        L1b:
            r11 = move-exception
            goto L83
        L1d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L25:
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = r10.L$1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L1b
            goto L52
        L2f:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            android.net.Uri r1 = r10.$animationScaleUri
            r6 = 0
            r5.registerContentObserver(r1, r6, r4)
            kotlinx.coroutines.channels.Channel r1 = r10.$channel     // Catch: java.lang.Throwable -> L1b
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L1b
        L42:
            r10.L$0 = r11     // Catch: java.lang.Throwable -> L1b
            r10.L$1 = r1     // Catch: java.lang.Throwable -> L1b
            r10.label = r3     // Catch: java.lang.Throwable -> L1b
            java.lang.Object r6 = r1.hasNext(r10)     // Catch: java.lang.Throwable -> L1b
            if (r6 != r0) goto L4f
            return r0
        L4f:
            r9 = r6
            r6 = r11
            r11 = r9
        L52:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch: java.lang.Throwable -> L1b
            boolean r11 = r11.booleanValue()     // Catch: java.lang.Throwable -> L1b
            if (r11 == 0) goto L7d
            r1.next()     // Catch: java.lang.Throwable -> L1b
            android.content.Context r11 = r10.$applicationContext     // Catch: java.lang.Throwable -> L1b
            android.content.ContentResolver r11 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L1b
            java.lang.String r7 = "animator_duration_scale"
            r8 = 1065353216(0x3f800000, float:1.0)
            float r11 = android.provider.Settings.Global.getFloat(r11, r7, r8)     // Catch: java.lang.Throwable -> L1b
            java.lang.Float r7 = new java.lang.Float     // Catch: java.lang.Throwable -> L1b
            r7.<init>(r11)     // Catch: java.lang.Throwable -> L1b
            r10.L$0 = r6     // Catch: java.lang.Throwable -> L1b
            r10.L$1 = r1     // Catch: java.lang.Throwable -> L1b
            r10.label = r2     // Catch: java.lang.Throwable -> L1b
            java.lang.Object r11 = r6.emit(r7, r10)     // Catch: java.lang.Throwable -> L1b
            if (r11 != r0) goto L19
            return r0
        L7d:
            r5.unregisterContentObserver(r4)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L83:
            r5.unregisterContentObserver(r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
