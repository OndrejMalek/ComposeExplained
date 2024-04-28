package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class Recomposer$runRecomposeAndApplyChanges$2 extends SuspendLambda implements Function3 {
    public /* synthetic */ MonotonicFrameClock L$0;
    public List L$1;
    public List L$2;
    public List L$3;
    public Set L$4;
    public Set L$5;
    public int label;
    public final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$runRecomposeAndApplyChanges$2(Recomposer recomposer, Continuation continuation) {
        super(3, continuation);
        this.this$0 = recomposer;
    }

    public static final void access$invokeSuspend$clearRecompositionState(List list, List list2, List list3, Set set, Set set2) {
        list.clear();
        list2.clear();
        list3.clear();
        set.clear();
        set2.clear();
    }

    public static final void access$invokeSuspend$fillToInsert(List list, Recomposer recomposer) {
        list.clear();
        synchronized (recomposer.stateLock) {
            try {
                ArrayList arrayList = recomposer.compositionValuesAwaitingInsert;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(arrayList.get(i));
                    list.add(null);
                }
                recomposer.compositionValuesAwaitingInsert.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Recomposer$runRecomposeAndApplyChanges$2 recomposer$runRecomposeAndApplyChanges$2 = new Recomposer$runRecomposeAndApplyChanges$2(this.this$0, (Continuation) obj3);
        recomposer$runRecomposeAndApplyChanges$2.L$0 = (MonotonicFrameClock) obj2;
        recomposer$runRecomposeAndApplyChanges$2.invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007a A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0115 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v19, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r6v15, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x0109 -> B:6:0x0110). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x01a9 -> B:24:0x0075). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            r22 = this;
            r1 = r22
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r1.label
            r3 = 1
            r5 = 2
            if (r2 == 0) goto L57
            if (r2 == r3) goto L3a
            if (r2 != r5) goto L32
            java.util.Set r2 = r1.L$5
            java.util.Set r2 = (java.util.Set) r2
            java.util.Set r6 = r1.L$4
            java.util.Set r6 = (java.util.Set) r6
            java.util.List r7 = r1.L$3
            java.util.List r8 = r1.L$2
            java.util.List r9 = r1.L$1
            androidx.compose.runtime.MonotonicFrameClock r10 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r23)
            r19 = r10
            r10 = r2
            r2 = r19
            r20 = r9
            r9 = r6
            r6 = r20
            r21 = r8
            r8 = r7
            r7 = r21
            goto L110
        L32:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L3a:
            java.util.Set r2 = r1.L$5
            java.util.Set r2 = (java.util.Set) r2
            java.util.Set r6 = r1.L$4
            java.util.Set r6 = (java.util.Set) r6
            java.util.List r7 = r1.L$3
            java.util.List r8 = r1.L$2
            java.util.List r9 = r1.L$1
            androidx.compose.runtime.MonotonicFrameClock r10 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r23)
            r17 = r2
            r16 = r6
            r15 = r7
            r14 = r8
            r13 = r9
            r2 = r10
            goto Ld4
        L57:
            kotlin.ResultKt.throwOnFailure(r23)
            androidx.compose.runtime.MonotonicFrameClock r2 = r1.L$0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            java.util.LinkedHashSet r10 = new java.util.LinkedHashSet
            r10.<init>()
        L75:
            androidx.compose.runtime.Recomposer r11 = r1.this$0
            java.lang.Object r11 = r11.stateLock
            monitor-enter(r11)
            monitor-exit(r11)
            androidx.compose.runtime.Recomposer r11 = r1.this$0
            r1.L$0 = r2
            r1.L$1 = r6
            r1.L$2 = r7
            r1.L$3 = r8
            r12 = r9
            java.util.Set r12 = (java.util.Set) r12
            r1.L$4 = r12
            r12 = r10
            java.util.Set r12 = (java.util.Set) r12
            r1.L$5 = r12
            r1.label = r3
            boolean r12 = r11.getHasSchedulingWork()
            if (r12 != 0) goto Lc8
            kotlinx.coroutines.CancellableContinuationImpl r12 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r13 = kotlin.ResultKt.intercepted(r22)
            r12.<init>(r3, r13)
            r12.initCancellability()
            java.lang.Object r13 = r11.stateLock
            monitor-enter(r13)
            boolean r14 = r11.getHasSchedulingWork()     // Catch: java.lang.Throwable -> Lc5
            if (r14 == 0) goto Lae
            r11 = r12
            goto Lb1
        Lae:
            r11.workContinuation = r12     // Catch: java.lang.Throwable -> Lc5
            r11 = 0
        Lb1:
            monitor-exit(r13)
            if (r11 == 0) goto Lb9
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            r11.resumeWith(r13)
        Lb9:
            java.lang.Object r11 = r12.getResult()
            kotlin.coroutines.intrinsics.CoroutineSingletons r12 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r11 != r12) goto Lc2
            goto Lca
        Lc2:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            goto Lca
        Lc5:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        Lc8:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
        Lca:
            if (r11 != r0) goto Lcd
            return r0
        Lcd:
            r13 = r6
            r14 = r7
            r15 = r8
            r16 = r9
            r17 = r10
        Ld4:
            androidx.compose.runtime.Recomposer r6 = r1.this$0
            boolean r6 = androidx.compose.runtime.Recomposer.access$recordComposerModifications(r6)
            if (r6 == 0) goto L1a9
            androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$1 r12 = new androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2$1
            androidx.compose.runtime.Recomposer r7 = r1.this$0
            r6 = r12
            r8 = r13
            r9 = r14
            r10 = r16
            r11 = r15
            r4 = r12
            r12 = r17
            r6.<init>()
            r1.L$0 = r2
            r1.L$1 = r13
            r1.L$2 = r14
            r1.L$3 = r15
            r6 = r16
            java.util.Set r6 = (java.util.Set) r6
            r1.L$4 = r6
            r6 = r17
            java.util.Set r6 = (java.util.Set) r6
            r1.L$5 = r6
            r1.label = r5
            java.lang.Object r4 = r2.withFrameNanos(r4, r1)
            if (r4 != r0) goto L109
            return r0
        L109:
            r6 = r13
            r7 = r14
            r8 = r15
            r9 = r16
            r10 = r17
        L110:
            androidx.compose.runtime.Recomposer r4 = r1.this$0
            java.lang.Object r11 = r4.stateLock
            monitor-enter(r11)
            java.util.LinkedHashMap r12 = r4.compositionValuesRemoved     // Catch: java.lang.Throwable -> L177
            boolean r12 = r12.isEmpty()     // Catch: java.lang.Throwable -> L177
            r12 = r12 ^ r3
            if (r12 == 0) goto L182
            java.util.LinkedHashMap r12 = r4.compositionValuesRemoved     // Catch: java.lang.Throwable -> L177
            java.util.Collection r12 = r12.values()     // Catch: java.lang.Throwable -> L177
            java.lang.Iterable r12 = (java.lang.Iterable) r12     // Catch: java.lang.Throwable -> L177
            java.lang.String r14 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r12, r14)     // Catch: java.lang.Throwable -> L177
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L177
            r14.<init>()     // Catch: java.lang.Throwable -> L177
            java.util.Iterator r12 = r12.iterator()     // Catch: java.lang.Throwable -> L177
        L134:
            boolean r15 = r12.hasNext()     // Catch: java.lang.Throwable -> L177
            if (r15 == 0) goto L144
            java.lang.Object r15 = r12.next()     // Catch: java.lang.Throwable -> L177
            java.lang.Iterable r15 = (java.lang.Iterable) r15     // Catch: java.lang.Throwable -> L177
            kotlin.collections.CollectionsKt__ReversedViewsKt.addAll(r15, r14)     // Catch: java.lang.Throwable -> L177
            goto L134
        L144:
            java.util.LinkedHashMap r12 = r4.compositionValuesRemoved     // Catch: java.lang.Throwable -> L177
            r12.clear()     // Catch: java.lang.Throwable -> L177
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L177
            int r15 = r14.size()     // Catch: java.lang.Throwable -> L177
            r12.<init>(r15)     // Catch: java.lang.Throwable -> L177
            int r15 = r14.size()     // Catch: java.lang.Throwable -> L177
            r3 = 0
        L157:
            if (r3 >= r15) goto L179
            java.lang.Object r16 = r14.get(r3)     // Catch: java.lang.Throwable -> L177
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r16)     // Catch: java.lang.Throwable -> L177
            java.util.LinkedHashMap r5 = r4.compositionValueStatesAvailable     // Catch: java.lang.Throwable -> L177
            r13 = 0
            java.lang.Object r5 = r5.get(r13)     // Catch: java.lang.Throwable -> L177
            r18 = r0
            kotlin.Pair r0 = new kotlin.Pair     // Catch: java.lang.Throwable -> L177
            r0.<init>(r13, r5)     // Catch: java.lang.Throwable -> L177
            r12.add(r0)     // Catch: java.lang.Throwable -> L177
            int r3 = r3 + 1
            r0 = r18
            r5 = 2
            goto L157
        L177:
            r0 = move-exception
            goto L1a7
        L179:
            r18 = r0
            r13 = 0
            java.util.LinkedHashMap r0 = r4.compositionValueStatesAvailable     // Catch: java.lang.Throwable -> L177
            r0.clear()     // Catch: java.lang.Throwable -> L177
            goto L187
        L182:
            r18 = r0
            r13 = 0
            kotlin.collections.EmptyList r12 = kotlin.collections.EmptyList.INSTANCE     // Catch: java.lang.Throwable -> L177
        L187:
            monitor-exit(r11)
            int r0 = r12.size()
            r3 = 0
        L18d:
            if (r3 >= r0) goto L1a1
            java.lang.Object r4 = r12.get(r3)
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r5 = r4.first
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r5)
            java.lang.Object r4 = r4.second
            androidx.compose.runtime.MovableContentState r4 = (androidx.compose.runtime.MovableContentState) r4
            int r3 = r3 + 1
            goto L18d
        L1a1:
            r0 = r18
            r3 = 1
            r5 = 2
            goto L75
        L1a7:
            monitor-exit(r11)
            throw r0
        L1a9:
            r6 = r13
            r7 = r14
            r8 = r15
            r9 = r16
            r10 = r17
            goto L75
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
