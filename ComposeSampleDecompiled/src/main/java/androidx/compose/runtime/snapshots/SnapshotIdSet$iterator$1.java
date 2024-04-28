package androidx.compose.runtime.snapshots;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* loaded from: classes.dex */
public final class SnapshotIdSet$iterator$1 extends RestrictedSuspendLambda implements Function2 {
    public int I$0;
    public int I$1;
    public /* synthetic */ Object L$0;
    public int[] L$1;
    public int label;
    public final /* synthetic */ SnapshotIdSet this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotIdSet$iterator$1(SnapshotIdSet snapshotIdSet, Continuation continuation) {
        super(continuation);
        this.this$0 = snapshotIdSet;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SnapshotIdSet$iterator$1 snapshotIdSet$iterator$1 = new SnapshotIdSet$iterator$1(this.this$0, continuation);
        snapshotIdSet$iterator$1.L$0 = obj;
        return snapshotIdSet$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SnapshotIdSet$iterator$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00a7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00c8 -> B:7:0x00c9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0097 -> B:20:0x0098). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 64
            r4 = 0
            r5 = 1
            r7 = 3
            r8 = 2
            r10 = 0
            androidx.compose.runtime.snapshots.SnapshotIdSet r12 = r0.this$0
            r13 = 1
            if (r2 == 0) goto L49
            if (r2 == r13) goto L3a
            if (r2 == r8) goto L2f
            if (r2 != r7) goto L27
            int r2 = r0.I$0
            java.lang.Object r8 = r0.L$0
            kotlin.sequences.SequenceBuilderIterator r8 = (kotlin.sequences.SequenceBuilderIterator) r8
            kotlin.ResultKt.throwOnFailure(r19)
            r9 = r2
            r2 = r7
            goto Lc9
        L27:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L2f:
            int r2 = r0.I$0
            java.lang.Object r14 = r0.L$0
            kotlin.sequences.SequenceBuilderIterator r14 = (kotlin.sequences.SequenceBuilderIterator) r14
            kotlin.ResultKt.throwOnFailure(r19)
            r7 = r8
            goto L98
        L3a:
            int r2 = r0.I$1
            int r14 = r0.I$0
            int[] r15 = r0.L$1
            java.lang.Object r9 = r0.L$0
            kotlin.sequences.SequenceBuilderIterator r9 = (kotlin.sequences.SequenceBuilderIterator) r9
            kotlin.ResultKt.throwOnFailure(r19)
            int r14 = r14 + r13
            goto L57
        L49:
            kotlin.ResultKt.throwOnFailure(r19)
            java.lang.Object r2 = r0.L$0
            r9 = r2
            kotlin.sequences.SequenceBuilderIterator r9 = (kotlin.sequences.SequenceBuilderIterator) r9
            int[] r15 = r12.belowBound
            if (r15 == 0) goto L6e
            int r2 = r15.length
            r14 = 0
        L57:
            if (r14 >= r2) goto L6e
            r3 = r15[r14]
            java.lang.Integer r4 = new java.lang.Integer
            r4.<init>(r3)
            r0.L$0 = r9
            r0.L$1 = r15
            r0.I$0 = r14
            r0.I$1 = r2
            r0.label = r13
            r9.yield(r4, r0)
            return r1
        L6e:
            long r14 = r12.lowerSet
            int r2 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r2 == 0) goto L9d
            r14 = r9
            r2 = 0
        L76:
            if (r2 >= r3) goto L9c
            long r7 = r12.lowerSet
            long r16 = r5 << r2
            long r7 = r7 & r16
            int r7 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r7 == 0) goto L97
            int r3 = r12.lowerBound
            int r3 = r3 + r2
            java.lang.Integer r5 = new java.lang.Integer
            r5.<init>(r3)
            r0.L$0 = r14
            r0.L$1 = r4
            r0.I$0 = r2
            r7 = 2
            r0.label = r7
            r14.yield(r5, r0)
            return r1
        L97:
            r7 = 2
        L98:
            int r2 = r2 + r13
            r8 = r7
            r7 = 3
            goto L76
        L9c:
            r9 = r14
        L9d:
            long r7 = r12.upperSet
            int r2 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r2 == 0) goto Lcb
            r8 = r9
            r9 = 0
        La5:
            if (r9 >= r3) goto Lcb
            long r14 = r12.upperSet
            long r16 = r5 << r9
            long r14 = r14 & r16
            int r2 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r2 == 0) goto Lc8
            int r2 = r9 + 64
            int r3 = r12.lowerBound
            int r2 = r2 + r3
            java.lang.Integer r3 = new java.lang.Integer
            r3.<init>(r2)
            r0.L$0 = r8
            r0.L$1 = r4
            r0.I$0 = r9
            r2 = 3
            r0.label = r2
            r8.yield(r3, r0)
            return r1
        Lc8:
            r2 = 3
        Lc9:
            int r9 = r9 + r13
            goto La5
        Lcb:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
