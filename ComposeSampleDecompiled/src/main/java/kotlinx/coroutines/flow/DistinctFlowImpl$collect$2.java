package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.flow.internal.UndispatchedContextCollector$emitRef$1;
import kotlinx.coroutines.internal.AtomicKt;

/* loaded from: classes.dex */
public final class DistinctFlowImpl$collect$2 implements FlowCollector {
    public final Object $collector;
    public final Object $previousKey;
    public final /* synthetic */ int $r8$classId = 0;
    public final Object this$0;

    public DistinctFlowImpl$collect$2(DistinctFlowImpl distinctFlowImpl, Ref$ObjectRef ref$ObjectRef, FlowCollector flowCollector) {
        this.this$0 = distinctFlowImpl;
        this.$previousKey = ref$ObjectRef;
        this.$collector = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r9.$r8$classId
            java.lang.Object r3 = r9.$previousKey
            java.lang.Object r4 = r9.$collector
            java.lang.Object r5 = r9.this$0
            switch(r2) {
                case 0: goto L1b;
                default: goto Lf;
            }
        Lf:
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r10 = kotlinx.coroutines.JobKt.withContextUndispatched(r5, r10, r3, r4, r11)
            if (r10 != r1) goto L1a
            r0 = r10
        L1a:
            return r0
        L1b:
            boolean r2 = r11 instanceof kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1
            if (r2 == 0) goto L2e
            r2 = r11
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1 r2 = (kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1) r2
            int r6 = r2.label
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = r6 & r7
            if (r8 == 0) goto L2e
            int r6 = r6 - r7
            r2.label = r6
            goto L33
        L2e:
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1 r2 = new kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1
            r2.<init>(r9, r11)
        L33:
            java.lang.Object r11 = r2.result
            int r6 = r2.label
            r7 = 1
            if (r6 == 0) goto L48
            if (r6 != r7) goto L40
            kotlin.ResultKt.throwOnFailure(r11)
            goto L76
        L40:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L48:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.flow.DistinctFlowImpl r5 = (kotlinx.coroutines.flow.DistinctFlowImpl) r5
            kotlin.jvm.functions.Function1 r11 = r5.keySelector
            java.lang.Object r11 = r11.invoke(r10)
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref$ObjectRef) r3
            java.lang.Object r6 = r3.element
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r6 == r8) goto L69
            kotlin.jvm.functions.Function2 r5 = r5.areEquivalent
            java.lang.Object r5 = r5.invoke(r6, r11)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L76
        L69:
            r3.element = r11
            kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
            r2.label = r7
            java.lang.Object r10 = r4.emit(r10, r2)
            if (r10 != r1) goto L76
            r0 = r1
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.DistinctFlowImpl$collect$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public DistinctFlowImpl$collect$2(FlowCollector flowCollector, CoroutineContext coroutineContext) {
        this.this$0 = coroutineContext;
        this.$previousKey = AtomicKt.threadContextElements(coroutineContext);
        this.$collector = new UndispatchedContextCollector$emitRef$1(flowCollector, null);
    }
}
