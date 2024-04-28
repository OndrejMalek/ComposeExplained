package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScopeImpl;
import androidx.compose.ui.geometry.Offset;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* loaded from: classes.dex */
public final class ClickablePointerInputNode$pointerInput$2 extends SuspendLambda implements Function3 {
    public /* synthetic */ long J$0;
    public /* synthetic */ PressGestureScopeImpl L$0;
    public int label;
    public final /* synthetic */ ClickablePointerInputNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickablePointerInputNode$pointerInput$2(ClickablePointerInputNode clickablePointerInputNode, Continuation continuation) {
        super(3, continuation);
        this.this$0 = clickablePointerInputNode;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        long j = ((Offset) obj2).packedValue;
        ClickablePointerInputNode$pointerInput$2 clickablePointerInputNode$pointerInput$2 = new ClickablePointerInputNode$pointerInput$2(this.this$0, (Continuation) obj3);
        clickablePointerInputNode$pointerInput$2.L$0 = (PressGestureScopeImpl) obj;
        clickablePointerInputNode$pointerInput$2.J$0 = j;
        return clickablePointerInputNode$pointerInput$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
    
        if (r12 == r0) goto L19;
     */
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
            r3 = 1
            if (r1 == 0) goto L17
            if (r1 != r3) goto Lf
            kotlin.ResultKt.throwOnFailure(r12)
            goto L44
        Lf:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L17:
            kotlin.ResultKt.throwOnFailure(r12)
            androidx.compose.foundation.gestures.PressGestureScopeImpl r4 = r11.L$0
            long r5 = r11.J$0
            androidx.compose.foundation.ClickablePointerInputNode r12 = r11.this$0
            boolean r1 = r12.enabled
            if (r1 == 0) goto L44
            r11.label = r3
            androidx.compose.foundation.interaction.MutableInteractionSourceImpl r7 = r12.interactionSource
            if (r7 == 0) goto L40
            androidx.compose.foundation.ClickableKt$handlePressInteraction$2 r1 = new androidx.compose.foundation.ClickableKt$handlePressInteraction$2
            androidx.compose.ui.node.LayoutNode$_foldedChildren$1 r9 = r12.delayPressInteraction
            r10 = 0
            androidx.compose.foundation.AbstractClickableNode$InteractionData r8 = r12.interactionData
            r3 = r1
            r3.<init>(r4, r5, r7, r8, r9, r10)
            java.lang.Object r12 = kotlin.ResultKt.coroutineScope(r1, r11)
            if (r12 != r0) goto L3c
            goto L3d
        L3c:
            r12 = r2
        L3d:
            if (r12 != r0) goto L40
            goto L41
        L40:
            r12 = r2
        L41:
            if (r12 != r0) goto L44
            return r0
        L44:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ClickablePointerInputNode$pointerInput$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
