package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$BooleanRef;

/* loaded from: classes.dex */
public final class FlowKt__LimitKt$dropWhile$1$1 implements FlowCollector {
    public final /* synthetic */ Ref$BooleanRef $matched;
    public final /* synthetic */ Function2 $predicate;
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    public FlowKt__LimitKt$dropWhile$1$1(Ref$BooleanRef ref$BooleanRef, FlowCollector flowCollector, Function2 function2) {
        this.$matched = ref$BooleanRef;
        this.$this_unsafeFlow = flowCollector;
        this.$predicate = function2;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1$emit$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1$emit$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L43
            if (r2 == r6) goto L3f
            if (r2 == r5) goto L37
            if (r2 != r4) goto L2f
            kotlin.ResultKt.throwOnFailure(r9)
            goto L84
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L37:
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1 r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L68
        L3f:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L57
        L43:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.internal.Ref$BooleanRef r9 = r7.$matched
            boolean r9 = r9.element
            if (r9 == 0) goto L58
            r0.label = r6
            kotlinx.coroutines.flow.FlowCollector r9 = r7.$this_unsafeFlow
            java.lang.Object r8 = r9.emit(r8, r0)
            if (r8 != r1) goto L57
            return r1
        L57:
            return r3
        L58:
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r5
            kotlin.jvm.functions.Function2 r9 = r7.$predicate
            java.lang.Object r9 = r9.invoke(r8, r0)
            if (r9 != r1) goto L67
            return r1
        L67:
            r2 = r7
        L68:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L84
            kotlin.jvm.internal.Ref$BooleanRef r9 = r2.$matched
            r9.element = r6
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r4
            kotlinx.coroutines.flow.FlowCollector r9 = r2.$this_unsafeFlow
            java.lang.Object r8 = r9.emit(r8, r0)
            if (r8 != r1) goto L84
            return r1
        L84:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$1$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
