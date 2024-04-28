package androidx.compose.ui.platform;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ReceiveChannel;

/* loaded from: classes.dex */
public final class GlobalSnapshotManager$ensureStarted$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Channel $channel;
    public ReceiveChannel L$0;
    public BufferedChannel.BufferedChannelIterator L$1;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlobalSnapshotManager$ensureStarted$1(Channel channel, Continuation continuation) {
        super(2, continuation);
        this.$channel = channel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GlobalSnapshotManager$ensureStarted$1(this.$channel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((GlobalSnapshotManager$ensureStarted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a A[Catch: all -> 0x0011, TRY_LEAVE, TryCatch #0 {all -> 0x0011, blocks: (B:6:0x000d, B:7:0x0032, B:9:0x003a, B:10:0x0025, B:20:0x0020), top: B:2:0x0005 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x002f -> B:7:0x0032). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L1b
            if (r1 != r2) goto L13
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = r4.L$1
            kotlinx.coroutines.channels.ReceiveChannel r3 = r4.L$0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L11
            goto L32
        L11:
            r5 = move-exception
            goto L4b
        L13:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L1b:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.channels.Channel r3 = r4.$channel
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r5 = r3.iterator()     // Catch: java.lang.Throwable -> L11
            r1 = r5
        L25:
            r4.L$0 = r3     // Catch: java.lang.Throwable -> L11
            r4.L$1 = r1     // Catch: java.lang.Throwable -> L11
            r4.label = r2     // Catch: java.lang.Throwable -> L11
            java.lang.Object r5 = r1.hasNext(r4)     // Catch: java.lang.Throwable -> L11
            if (r5 != r0) goto L32
            return r0
        L32:
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L11
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L11
            if (r5 == 0) goto L44
            java.lang.Object r5 = r1.next()     // Catch: java.lang.Throwable -> L11
            kotlin.Unit r5 = (kotlin.Unit) r5     // Catch: java.lang.Throwable -> L11
            _COROUTINE.ArtificialStackFrames.sendApplyNotifications()     // Catch: java.lang.Throwable -> L11
            goto L25
        L44:
            r5 = 0
            kotlinx.coroutines.JobKt.cancelConsumed(r3, r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L4b:
            throw r5     // Catch: java.lang.Throwable -> L4c
        L4c:
            r0 = move-exception
            kotlinx.coroutines.JobKt.cancelConsumed(r3, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
