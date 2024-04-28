package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScopeImpl;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class ClickableKt$handlePressInteraction$2 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Function0 $delayPressInteraction;
    public final /* synthetic */ AbstractClickableNode$InteractionData $interactionData;
    public final /* synthetic */ MutableInteractionSourceImpl $interactionSource;
    public final /* synthetic */ long $pressPoint;
    public final /* synthetic */ PressGestureScopeImpl $this_handlePressInteraction;
    public /* synthetic */ Object L$0;
    public boolean Z$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickableKt$handlePressInteraction$2(PressGestureScopeImpl pressGestureScopeImpl, long j, MutableInteractionSourceImpl mutableInteractionSourceImpl, AbstractClickableNode$InteractionData abstractClickableNode$InteractionData, Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.$this_handlePressInteraction = pressGestureScopeImpl;
        this.$pressPoint = j;
        this.$interactionSource = mutableInteractionSourceImpl;
        this.$interactionData = abstractClickableNode$InteractionData;
        this.$delayPressInteraction = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ClickableKt$handlePressInteraction$2 clickableKt$handlePressInteraction$2 = new ClickableKt$handlePressInteraction$2(this.$this_handlePressInteraction, this.$pressPoint, this.$interactionSource, this.$interactionData, this.$delayPressInteraction, continuation);
        clickableKt$handlePressInteraction$2.L$0 = obj;
        return clickableKt$handlePressInteraction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ClickableKt$handlePressInteraction$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x009f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            r20 = this;
            r0 = r20
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 3
            r5 = 0
            androidx.compose.foundation.AbstractClickableNode$InteractionData r6 = r0.$interactionData
            r7 = 5
            r8 = 4
            r9 = 2
            androidx.compose.foundation.interaction.MutableInteractionSourceImpl r10 = r0.$interactionSource
            r11 = 1
            if (r2 == 0) goto L45
            if (r2 == r11) goto L3b
            if (r2 == r9) goto L35
            if (r2 == r4) goto L2c
            if (r2 == r8) goto L27
            if (r2 != r7) goto L1f
            goto L27
        L1f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L27:
            kotlin.ResultKt.throwOnFailure(r21)
            goto Le0
        L2c:
            java.lang.Object r2 = r0.L$0
            androidx.compose.foundation.interaction.PressInteraction$Release r2 = (androidx.compose.foundation.interaction.PressInteraction$Release) r2
            kotlin.ResultKt.throwOnFailure(r21)
            goto Lb7
        L35:
            boolean r2 = r0.Z$0
            kotlin.ResultKt.throwOnFailure(r21)
            goto L9d
        L3b:
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            kotlin.ResultKt.throwOnFailure(r21)
            r7 = r21
            goto L7a
        L45:
            kotlin.ResultKt.throwOnFailure(r21)
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            androidx.compose.foundation.ClickableKt$handlePressInteraction$2$delayJob$1 r14 = new androidx.compose.foundation.ClickableKt$handlePressInteraction$2$delayJob$1
            long r12 = r0.$pressPoint
            androidx.compose.foundation.interaction.MutableInteractionSourceImpl r15 = r0.$interactionSource
            kotlin.jvm.functions.Function0 r7 = r0.$delayPressInteraction
            androidx.compose.foundation.AbstractClickableNode$InteractionData r8 = r0.$interactionData
            r18 = 0
            r16 = r12
            r12 = r14
            r13 = r7
            r7 = r14
            r19 = r15
            r14 = r16
            r16 = r19
            r17 = r8
            r12.<init>(r13, r14, r16, r17, r18)
            r8 = 0
            kotlinx.coroutines.StandaloneCoroutine r2 = kotlin.ResultKt.launch$default(r2, r5, r8, r7, r4)
            r0.L$0 = r2
            r0.label = r11
            androidx.compose.foundation.gestures.PressGestureScopeImpl r7 = r0.$this_handlePressInteraction
            java.lang.Object r7 = r7.tryAwaitRelease(r0)
            if (r7 != r1) goto L7a
            return r1
        L7a:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            boolean r8 = r2.isActive()
            if (r8 == 0) goto Lc3
            r0.L$0 = r5
            r0.Z$0 = r7
            r0.label = r9
            r2.cancel(r5)
            kotlinx.coroutines.JobSupport r2 = (kotlinx.coroutines.JobSupport) r2
            java.lang.Object r2 = r2.join(r0)
            if (r2 != r1) goto L98
            goto L99
        L98:
            r2 = r3
        L99:
            if (r2 != r1) goto L9c
            return r1
        L9c:
            r2 = r7
        L9d:
            if (r2 == 0) goto Le0
            androidx.compose.foundation.interaction.PressInteraction$Press r2 = new androidx.compose.foundation.interaction.PressInteraction$Press
            long r7 = r0.$pressPoint
            r2.<init>(r7)
            androidx.compose.foundation.interaction.PressInteraction$Release r7 = new androidx.compose.foundation.interaction.PressInteraction$Release
            r7.<init>(r2)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r2 = r10.emit(r2, r0)
            if (r2 != r1) goto Lb6
            return r1
        Lb6:
            r2 = r7
        Lb7:
            r0.L$0 = r5
            r4 = 4
            r0.label = r4
            java.lang.Object r2 = r10.emit(r2, r0)
            if (r2 != r1) goto Le0
            return r1
        Lc3:
            androidx.compose.foundation.interaction.PressInteraction$Press r2 = r6.pressInteraction
            if (r2 == 0) goto Le0
            if (r7 == 0) goto Lcf
            androidx.compose.foundation.interaction.PressInteraction$Release r4 = new androidx.compose.foundation.interaction.PressInteraction$Release
            r4.<init>(r2)
            goto Ld4
        Lcf:
            androidx.compose.foundation.interaction.PressInteraction$Cancel r4 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
            r4.<init>(r2)
        Ld4:
            r0.L$0 = r5
            r2 = 5
            r0.label = r2
            java.lang.Object r2 = r10.emit(r4, r0)
            if (r2 != r1) goto Le0
            return r1
        Le0:
            r6.pressInteraction = r5
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ClickableKt$handlePressInteraction$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
