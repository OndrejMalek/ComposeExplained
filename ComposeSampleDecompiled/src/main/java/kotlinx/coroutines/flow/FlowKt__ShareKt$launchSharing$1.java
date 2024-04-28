package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class FlowKt__ShareKt$launchSharing$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Object $initialValue;
    public final /* synthetic */ MutableSharedFlow $shared;
    public final /* synthetic */ SharingStarted $started;
    public final /* synthetic */ Flow $upstream;
    public int label;

    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        public /* synthetic */ int I$0;

        /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.SuspendLambda, kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            ?? suspendLambda = new SuspendLambda(2, continuation);
            suspendLambda.I$0 = ((Number) obj).intValue();
            return suspendLambda;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(this.I$0 > 0);
        }
    }

    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends SuspendLambda implements Function2 {
        public final /* synthetic */ Object $initialValue;
        public final /* synthetic */ MutableSharedFlow $shared;
        public final /* synthetic */ Flow $upstream;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, Continuation continuation) {
            super(2, continuation);
            this.$upstream = flow;
            this.$shared = mutableSharedFlow;
            this.$initialValue = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$upstream, this.$shared, this.$initialValue, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((SharingCommand) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                int ordinal = ((SharingCommand) this.L$0).ordinal();
                MutableSharedFlow mutableSharedFlow = this.$shared;
                if (ordinal == 0) {
                    this.label = 1;
                    if (this.$upstream.collect(mutableSharedFlow, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else if (ordinal == 2) {
                    Symbol symbol = StateFlowKt.NO_VALUE;
                    Object obj2 = this.$initialValue;
                    if (obj2 == symbol) {
                        mutableSharedFlow.resetReplayCache();
                    } else {
                        mutableSharedFlow.tryEmit(obj2);
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ShareKt$launchSharing$1(SharingStarted sharingStarted, Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, Continuation continuation) {
        super(2, continuation);
        this.$started = sharingStarted;
        this.$upstream = flow;
        this.$shared = mutableSharedFlow;
        this.$initialValue = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FlowKt__ShareKt$launchSharing$1(this.$started, this.$upstream, this.$shared, this.$initialValue, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ShareKt$launchSharing$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: kotlinx.coroutines.flow.MutableSharedFlow */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0060 A[RETURN] */
    /* JADX WARN: Type inference failed for: r4v4, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r0 = r16
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 4
            r5 = 3
            r6 = 1
            kotlinx.coroutines.flow.Flow r7 = r0.$upstream
            r8 = 2
            kotlinx.coroutines.flow.MutableSharedFlow r9 = r0.$shared
            if (r2 == 0) goto L2c
            if (r2 == r6) goto L27
            if (r2 == r8) goto L23
            if (r2 == r5) goto L27
            if (r2 != r4) goto L1b
            goto L27
        L1b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L23:
            kotlin.ResultKt.throwOnFailure(r17)
            goto L58
        L27:
            kotlin.ResultKt.throwOnFailure(r17)
            goto La3
        L2c:
            kotlin.ResultKt.throwOnFailure(r17)
            kotlinx.coroutines.flow.StartedLazily r2 = kotlinx.coroutines.flow.SharingStarted.Companion.Eagerly
            kotlinx.coroutines.flow.SharingStarted r10 = r0.$started
            if (r10 != r2) goto L3e
            r0.label = r6
            java.lang.Object r2 = r7.collect(r9, r0)
            if (r2 != r1) goto La3
            return r1
        L3e:
            kotlinx.coroutines.flow.StartedLazily r2 = kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
            r6 = 0
            if (r10 != r2) goto L61
            r2 = r9
            kotlinx.coroutines.flow.internal.AbstractSharedFlow r2 = (kotlinx.coroutines.flow.internal.AbstractSharedFlow) r2
            kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow r2 = r2.getSubscriptionCount()
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1 r4 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1
            r4.<init>(r8, r6)
            r0.label = r8
            java.lang.Object r2 = kotlinx.coroutines.JobKt.first(r2, r4, r0)
            if (r2 != r1) goto L58
            return r1
        L58:
            r0.label = r5
            java.lang.Object r2 = r7.collect(r9, r0)
            if (r2 != r1) goto La3
            return r1
        L61:
            r2 = r9
            kotlinx.coroutines.flow.internal.AbstractSharedFlow r2 = (kotlinx.coroutines.flow.internal.AbstractSharedFlow) r2
            kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow r2 = r2.getSubscriptionCount()
            kotlinx.coroutines.flow.Flow r2 = r10.command(r2)
            kotlinx.coroutines.flow.Flow r12 = kotlinx.coroutines.JobKt.distinctUntilChanged(r2)
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2 r2 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2
            java.lang.Object r5 = r0.$initialValue
            r2.<init>(r7, r9, r5, r6)
            r0.label = r4
            int r4 = kotlinx.coroutines.flow.FlowKt__MergeKt.$r8$clinit
            kotlinx.coroutines.flow.FlowKt__MergeKt$mapLatest$1 r11 = new kotlinx.coroutines.flow.FlowKt__MergeKt$mapLatest$1
            r11.<init>(r2, r6)
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r2 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest
            kotlin.coroutines.EmptyCoroutineContext r4 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlinx.coroutines.channels.BufferOverflow r5 = kotlinx.coroutines.channels.BufferOverflow.SUSPEND
            r14 = -2
            r10 = r2
            r13 = r4
            r15 = r5
            r10.<init>(r11, r12, r13, r14, r15)
            r6 = 0
            kotlinx.coroutines.flow.Flow r2 = r2.fuse(r4, r6, r5)
            kotlinx.coroutines.flow.internal.NopCollector r4 = kotlinx.coroutines.flow.internal.NopCollector.INSTANCE
            java.lang.Object r2 = r2.collect(r4, r0)
            if (r2 != r1) goto L9b
            goto L9c
        L9b:
            r2 = r3
        L9c:
            if (r2 != r1) goto L9f
            goto La0
        L9f:
            r2 = r3
        La0:
            if (r2 != r1) goto La3
            return r1
        La3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
