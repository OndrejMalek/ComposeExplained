package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot$Companion$registerApplyObserver$2;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class Recomposer$recompositionRunner$2 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Function3 $block;
    public final /* synthetic */ MonotonicFrameClock $parentFrameClock;
    public /* synthetic */ Object L$0;
    public Snapshot$Companion$registerApplyObserver$2 L$1;
    public int label;
    public final /* synthetic */ Recomposer this$0;

    /* renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 extends SuspendLambda implements Function2 {
        public final /* synthetic */ Function3 $block;
        public final /* synthetic */ MonotonicFrameClock $parentFrameClock;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Function3 function3, MonotonicFrameClock monotonicFrameClock, Continuation continuation) {
            super(2, continuation);
            this.$block = function3;
            this.$parentFrameClock = monotonicFrameClock;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$block, this.$parentFrameClock, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                this.label = 1;
                if (this.$block.invoke(coroutineScope, this.$parentFrameClock, this) == coroutineSingletons) {
                    return coroutineSingletons;
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
    public Recomposer$recompositionRunner$2(Recomposer recomposer, Function3 function3, MonotonicFrameClock monotonicFrameClock, Continuation continuation) {
        super(2, continuation);
        this.this$0 = recomposer;
        this.$block = function3;
        this.$parentFrameClock = monotonicFrameClock;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        Recomposer$recompositionRunner$2 recomposer$recompositionRunner$2 = new Recomposer$recompositionRunner$2(this.this$0, this.$block, this.$parentFrameClock, continuation);
        recomposer$recompositionRunner$2.L$0 = obj;
        return recomposer$recompositionRunner$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((Recomposer$recompositionRunner$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0132 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r14.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L20
            if (r1 != r2) goto L18
            androidx.compose.runtime.snapshots.Snapshot$Companion$registerApplyObserver$2 r0 = r14.L$1
            java.lang.Object r1 = r14.L$0
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Throwable -> L15
            goto L104
        L15:
            r15 = move-exception
            goto L12a
        L18:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L20:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
            kotlin.coroutines.CoroutineContext r15 = r15.getCoroutineContext()
            kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r1 = r15.get(r1)
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            if (r1 == 0) goto L168
            androidx.compose.runtime.Recomposer r15 = r14.this$0
            java.lang.Object r4 = r15.stateLock
            monitor-enter(r4)
            java.lang.Throwable r5 = r15.closeCause     // Catch: java.lang.Throwable -> L14b
            if (r5 != 0) goto L165
            kotlinx.coroutines.flow.StateFlowImpl r5 = r15._state     // Catch: java.lang.Throwable -> L14b
            java.lang.Object r5 = r5.getValue()     // Catch: java.lang.Throwable -> L14b
            androidx.compose.runtime.Recomposer$State r5 = (androidx.compose.runtime.Recomposer.State) r5     // Catch: java.lang.Throwable -> L14b
            androidx.compose.runtime.Recomposer$State r6 = androidx.compose.runtime.Recomposer.State.ShuttingDown     // Catch: java.lang.Throwable -> L14b
            int r5 = r5.compareTo(r6)     // Catch: java.lang.Throwable -> L14b
            if (r5 <= 0) goto L159
            kotlinx.coroutines.Job r5 = r15.runnerJob     // Catch: java.lang.Throwable -> L14b
            if (r5 != 0) goto L14d
            r15.runnerJob = r1     // Catch: java.lang.Throwable -> L14b
            r15.deriveStateLocked()     // Catch: java.lang.Throwable -> L14b
            monitor-exit(r4)
            androidx.compose.runtime.Recomposer$recompositionRunner$2$unregisterApplyObserver$1 r15 = new androidx.compose.runtime.Recomposer$recompositionRunner$2$unregisterApplyObserver$1
            androidx.compose.runtime.Recomposer r4 = r14.this$0
            r15.<init>()
            androidx.compose.runtime.snapshots.Snapshot$Companion$registerApplyObserver$2 r15 = _COROUTINE.ArtificialStackFrames.registerApplyObserver(r15)
            kotlinx.coroutines.flow.StateFlowImpl r4 = androidx.compose.runtime.Recomposer._runningRecomposers
            androidx.compose.runtime.Recomposer r4 = r14.this$0
            androidx.compose.runtime.Stack r4 = r4.recomposerInfo
        L69:
            kotlinx.coroutines.flow.StateFlowImpl r5 = androidx.compose.runtime.Recomposer._runningRecomposers
            java.lang.Object r6 = r5.getValue()
            androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet r6 = (androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet) r6
            r7 = r6
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet r7 = (androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet) r7
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap r8 = r7.hashMap
            boolean r9 = r8.containsKey(r4)
            if (r9 == 0) goto L7d
            goto Lbc
        L7d:
            boolean r9 = r7.isEmpty()
            androidx.compose.runtime.external.kotlinx.collections.immutable.internal.EndOfChain r10 = androidx.compose.runtime.external.kotlinx.collections.immutable.internal.EndOfChain.INSTANCE
            if (r9 == 0) goto L95
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links r7 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links
            r7.<init>(r10, r10)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap r7 = r8.put(r4, r7)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet r8 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet
            r8.<init>(r4, r4, r7)
            r7 = r8
            goto Lbc
        L95:
            java.lang.Object r9 = r7.lastElement
            java.lang.Object r11 = r8.get(r9)
            kotlin.ResultKt.checkNotNull(r11)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links r11 = (androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links) r11
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links r12 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links
            java.lang.Object r11 = r11.previous
            r12.<init>(r11, r4)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap r8 = r8.put(r9, r12)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links r11 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links
            r11.<init>(r9, r10)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap r8 = r8.put(r4, r11)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet r9 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet
            java.lang.Object r7 = r7.firstElement
            r9.<init>(r7, r4, r8)
            r7 = r9
        Lbc:
            if (r6 == r7) goto Lc9
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r6 != 0) goto Lc3
            r6 = r8
        Lc3:
            boolean r5 = r5.updateState(r6, r7)
            if (r5 == 0) goto L69
        Lc9:
            androidx.compose.runtime.Recomposer r4 = r14.this$0     // Catch: java.lang.Throwable -> Le8
            java.lang.Object r5 = r4.stateLock     // Catch: java.lang.Throwable -> Le8
            monitor-enter(r5)     // Catch: java.lang.Throwable -> Le8
            java.util.ArrayList r4 = r4.knownCompositions     // Catch: java.lang.Throwable -> L127
            java.util.ArrayList r4 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(r4)     // Catch: java.lang.Throwable -> L127
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Le8
            int r5 = r4.size()     // Catch: java.lang.Throwable -> Le8
            r6 = 0
        Lda:
            if (r6 >= r5) goto Led
            java.lang.Object r7 = r4.get(r6)     // Catch: java.lang.Throwable -> Le8
            androidx.compose.runtime.CompositionImpl r7 = (androidx.compose.runtime.CompositionImpl) r7     // Catch: java.lang.Throwable -> Le8
            r7.invalidateAll()     // Catch: java.lang.Throwable -> Le8
            int r6 = r6 + 1
            goto Lda
        Le8:
            r0 = move-exception
            r13 = r0
            r0 = r15
            r15 = r13
            goto L12a
        Led:
            androidx.compose.runtime.Recomposer$recompositionRunner$2$3 r4 = new androidx.compose.runtime.Recomposer$recompositionRunner$2$3     // Catch: java.lang.Throwable -> Le8
            kotlin.jvm.functions.Function3 r5 = r14.$block     // Catch: java.lang.Throwable -> Le8
            androidx.compose.runtime.MonotonicFrameClock r6 = r14.$parentFrameClock     // Catch: java.lang.Throwable -> Le8
            r4.<init>(r5, r6, r3)     // Catch: java.lang.Throwable -> Le8
            r14.L$0 = r1     // Catch: java.lang.Throwable -> Le8
            r14.L$1 = r15     // Catch: java.lang.Throwable -> Le8
            r14.label = r2     // Catch: java.lang.Throwable -> Le8
            java.lang.Object r2 = kotlin.ResultKt.coroutineScope(r4, r14)     // Catch: java.lang.Throwable -> Le8
            if (r2 != r0) goto L103
            return r0
        L103:
            r0 = r15
        L104:
            r0.dispose()
            androidx.compose.runtime.Recomposer r15 = r14.this$0
            java.lang.Object r0 = r15.stateLock
            monitor-enter(r0)
            kotlinx.coroutines.Job r2 = r15.runnerJob     // Catch: java.lang.Throwable -> L113
            if (r2 != r1) goto L115
            r15.runnerJob = r3     // Catch: java.lang.Throwable -> L113
            goto L115
        L113:
            r15 = move-exception
            goto L125
        L115:
            r15.deriveStateLocked()     // Catch: java.lang.Throwable -> L113
            monitor-exit(r0)
            kotlinx.coroutines.flow.StateFlowImpl r15 = androidx.compose.runtime.Recomposer._runningRecomposers
            androidx.compose.runtime.Recomposer r15 = r14.this$0
            androidx.compose.runtime.Stack r15 = r15.recomposerInfo
            _COROUTINE.ArtificialStackFrames.access$removeRunning(r15)
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L125:
            monitor-exit(r0)
            throw r15
        L127:
            r0 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Le8
            throw r0     // Catch: java.lang.Throwable -> Le8
        L12a:
            r0.dispose()
            androidx.compose.runtime.Recomposer r0 = r14.this$0
            java.lang.Object r2 = r0.stateLock
            monitor-enter(r2)
            kotlinx.coroutines.Job r4 = r0.runnerJob     // Catch: java.lang.Throwable -> L139
            if (r4 != r1) goto L13b
            r0.runnerJob = r3     // Catch: java.lang.Throwable -> L139
            goto L13b
        L139:
            r15 = move-exception
            goto L149
        L13b:
            r0.deriveStateLocked()     // Catch: java.lang.Throwable -> L139
            monitor-exit(r2)
            kotlinx.coroutines.flow.StateFlowImpl r0 = androidx.compose.runtime.Recomposer._runningRecomposers
            androidx.compose.runtime.Recomposer r0 = r14.this$0
            androidx.compose.runtime.Stack r0 = r0.recomposerInfo
            _COROUTINE.ArtificialStackFrames.access$removeRunning(r0)
            throw r15
        L149:
            monitor-exit(r2)
            throw r15
        L14b:
            r15 = move-exception
            goto L166
        L14d:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L14b
            java.lang.String r0 = "Recomposer already running"
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L14b
            r15.<init>(r0)     // Catch: java.lang.Throwable -> L14b
            throw r15     // Catch: java.lang.Throwable -> L14b
        L159:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L14b
            java.lang.String r0 = "Recomposer shut down"
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L14b
            r15.<init>(r0)     // Catch: java.lang.Throwable -> L14b
            throw r15     // Catch: java.lang.Throwable -> L14b
        L165:
            throw r5     // Catch: java.lang.Throwable -> L14b
        L166:
            monitor-exit(r4)
            throw r15
        L168:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Current context doesn't contain Job in it: "
            r1.<init>(r2)
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            java.lang.String r15 = r15.toString()
            r0.<init>(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer$recompositionRunner$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
